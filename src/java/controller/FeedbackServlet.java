package controller;

import dal.FeedbackDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Feedback;
import models.User;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/feedback")
public class FeedbackServlet extends HttpServlet {

    private FeedbackDAO dao;
    private static final Logger LOGGER = Logger.getLogger(FeedbackServlet.class.getName());

    @Override
    public void init() {
        dao = new FeedbackDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if (action == null || action.isEmpty()) {
                listAllFeedbacks(req, res);
            } else {
                switch (action) {
                    case "delete":
                        handleDeleteFeedback(req, res);
                        break;
                    case "edit":
                        handleEditFeedback(req, res);
                        break;
                    case "category":
                        handleCategoryFeedback(req, res);
                        break;
                    case "user":
                        handleUserFeedback(req, res);
                        break;
                    default:
                        listAllFeedbacks(req, res);
                        break;
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in doGet: " + e.getMessage(), e);
            req.getSession().setAttribute("error", "Lỗi hệ thống, vui lòng thử lại sau");
            res.sendRedirect("errorPage.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("update".equals(action)) {
                handleUpdateFeedback(req, res);
            } else {
                req.getSession().setAttribute("error", "Hành động không hợp lệ");
                res.sendRedirect("feedback");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in doPost: " + e.getMessage(), e);
            req.getSession().setAttribute("error", "Lỗi hệ thống, vui lòng thử lại sau");
            res.sendRedirect("errorPage.jsp");
        }
    }

    private void listAllFeedbacks(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Feedback> feedbackList = dao.getAllFeedbacks();
        req.setAttribute("feedbackList", feedbackList);
        req.getRequestDispatcher("/ShopPages/Pages/feedback.jsp").forward(req, res);
    }

    private void handleDeleteFeedback(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");
        int feedbackId;
        try {
            feedbackId = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            session.setAttribute("error", "ID phản hồi không hợp lệ");
            res.sendRedirect("feedback");
            return;
        }

        Feedback feedback = dao.getFeedbackById(feedbackId);
        if (feedback == null) {
            session.setAttribute("error", "Không tìm thấy phản hồi");
            res.sendRedirect("feedback");
            return;
        }

        if (currentUser == null || (currentUser.getUserID() != feedback.getUserID() && currentUser.getRoleID() != 1)) {
            session.setAttribute("error", "Bạn không có quyền xóa phản hồi này");
            res.sendRedirect("feedback");
            return;
        }

        boolean success = dao.deleteFeedbackById(feedbackId); // Đã sửa từ deleteFeedback thành deleteFeedbackById
        if (success) {
            session.setAttribute("message", "Xóa phản hồi thành công");
        } else {
            session.setAttribute("error", "Xóa phản hồi thất bại");
        }
        res.sendRedirect("feedback?action=category&categoryID=" + feedback.getCategoryID());
    }

    private void handleEditFeedback(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");
        int feedbackId;
        try {
            feedbackId = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            session.setAttribute("error", "ID phản hồi không hợp lệ");
            res.sendRedirect("feedback");
            return;
        }

        Feedback feedback = dao.getFeedbackById(feedbackId);
        if (feedback == null) {
            session.setAttribute("error", "Không tìm thấy phản hồi");
            res.sendRedirect("feedback");
            return;
        }

        if (currentUser == null || (currentUser.getUserID() != feedback.getUserID() && currentUser.getRoleID() != 1)) {
            session.setAttribute("error", "Bạn không có quyền chỉnh sửa phản hồi này");
            res.sendRedirect("feedback");
            return;
        }

        req.setAttribute("feedback", feedback);
        req.getRequestDispatcher("/ShopPages/Pages/edit-feedback.jsp").forward(req, res);
    }

    private void handleUpdateFeedback(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            res.sendRedirect("Login");
            return;
        }

        int feedbackId, rate, categoryID;
        String content = req.getParameter("content");
        try {
            feedbackId = Integer.parseInt(req.getParameter("feedbackID"));
            rate = Integer.parseInt(req.getParameter("rate"));
            categoryID = Integer.parseInt(req.getParameter("categoryID"));
        } catch (NumberFormatException e) {
            session.setAttribute("error", "Dữ liệu không hợp lệ");
            res.sendRedirect("feedback?action=edit&id=" + req.getParameter("feedbackID"));
            return;
        }

        Feedback feedback = dao.getFeedbackById(feedbackId);
        if (feedback == null) {
            session.setAttribute("error", "Không tìm thấy phản hồi");
            res.sendRedirect("feedback");
            return;
        }

        if (currentUser.getUserID() != feedback.getUserID() && currentUser.getRoleID() != 1) {
            session.setAttribute("error", "Bạn không có quyền chỉnh sửa phản hồi này");
            res.sendRedirect("feedback");
            return;
        }

        if (content == null || content.trim().isEmpty() || rate < 1 || rate > 5) {
            session.setAttribute("error", "Nội dung hoặc đánh giá không hợp lệ");
            req.setAttribute("feedback", feedback);
            req.getRequestDispatcher("/ShopPages/Pages/edit-feedback.jsp").forward(req, res);
            return;
        }

        feedback.setContent(content);
        feedback.setRate(rate);
        boolean success = dao.updateFeedback(feedback);

        if (success) {
            session.setAttribute("message", "Cập nhật phản hồi thành công");
            res.sendRedirect("feedback?action=category&categoryID=" + categoryID);
        } else {
            session.setAttribute("error", "Cập nhật phản hồi thất bại");
            req.setAttribute("feedback", feedback);
            req.getRequestDispatcher("/ShopPages/Pages/edit-feedback.jsp").forward(req, res);
        }
    }

    private void handleCategoryFeedback(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int categoryId;
        try {
            categoryId = Integer.parseInt(req.getParameter("categoryID"));
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("error", "ID danh mục không hợp lệ");
            res.sendRedirect("feedback");
            return;
        }
        List<Feedback> feedbackList = dao.getFeedbackByCategoryId(categoryId);
        req.setAttribute("feedbackList", feedbackList);
        req.setAttribute("categoryID", categoryId);
        req.getRequestDispatcher("/ShopPages/Pages/feedback.jsp").forward(req, res);
    }

    private void handleUserFeedback(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            res.sendRedirect("Login");
            return;
        }
        List<Feedback> userFeedback = dao.getFeedbackByUserId(currentUser.getUserID());
        req.setAttribute("feedbackList", userFeedback);
        req.getRequestDispatcher("/ShopPages/Pages/my-feedback.jsp").forward(req, res);
    }
}
