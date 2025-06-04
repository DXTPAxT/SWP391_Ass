package controller;

import dal.FeedbackDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
        try {
            dao = new FeedbackDAO();
            if (!dao.isConnected()) {
                LOGGER.severe("Không kết nối được cơ sở dữ liệu");
            } else {
                LOGGER.info("FeedbackServlet khởi tạo thành công");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khởi tạo FeedbackServlet: " + e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();

        try {
            if (dao == null || !dao.isConnected()) {
                session.setAttribute("error", "Không thể kết nối cơ sở dữ liệu. Vui lòng thử lại sau.");
                req.getRequestDispatcher("/ShopPages/Pages/feedback.jsp").forward(req, res);
                return;
            }

            LOGGER.info("Xử lý yêu cầu GET với action: " + (action != null ? action : "null"));
            if (action == null) {
                List<Feedback> allFeedback = dao.getAllFeedbacks();
                req.setAttribute("feedbackList", allFeedback);
                RequestDispatcher rs = req.getRequestDispatcher("/ShopPages/Pages/feedback.jsp");
                rs.forward(req, res);
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
                        List<Feedback> allFeedback = dao.getAllFeedbacks();
                        req.setAttribute("feedbackList", allFeedback);
                        RequestDispatcher rs = req.getRequestDispatcher("/ShopPages/Pages/feedback.jsp");
                        rs.forward(req, res);
                        break;
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi trong doGet: " + e.getMessage(), e);
            session.setAttribute("error", "Đã xảy ra lỗi: " + e.getMessage());
            res.sendRedirect(req.getContextPath() + "/feedback");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();

        try {
            if (dao == null || !dao.isConnected()) {
                session.setAttribute("error", "Không thể kết nối cơ sở dữ liệu. Vui lòng thử lại sau.");
                res.sendRedirect(req.getContextPath() + "/feedback");
                return;
            }

            LOGGER.info("Xử lý yêu cầu POST với action: " + (action != null ? action : "null"));
            if ("update".equals(action)) {
                handleUpdateFeedback(req, res);
            } else {
                User currentUser = (User) session.getAttribute("user");
                if (currentUser == null) {
                    session.setAttribute("error", "Vui lòng đăng nhập để gửi feedback");
                    res.sendRedirect(req.getContextPath() + "/Login");
                    return;
                }
                int userID = currentUser.getUserID();
                int categoryID = Integer.parseInt(req.getParameter("categoryID"));
                String content = req.getParameter("content");
                int rate = Integer.parseInt(req.getParameter("rate"));

                // Xác thực đầu vào
                if (content == null || content.trim().isEmpty() || rate < 1 || rate > 5) {
                    session.setAttribute("error", "Nội dung hoặc đánh giá không hợp lệ");
                    res.sendRedirect(req.getContextPath() + "/feedback?action=category&categoryID=" + categoryID);
                    return;
                }

                Feedback fb = new Feedback(userID, content, categoryID, rate);
                boolean success = dao.insertFeedback(fb);

                if (success) {
                    session.setAttribute("message", "Gửi feedback thành công");
                    res.sendRedirect(req.getContextPath() + "/feedback?action=category&categoryID=" + categoryID);
                } else {
                    session.setAttribute("error", "Không lưu được feedback");
                    res.sendRedirect(req.getContextPath() + "/feedback?action=category&categoryID=" + categoryID);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi trong doPost: " + e.getMessage(), e);
            session.setAttribute("error", "Đã xảy ra lỗi: " + e.getMessage());
            res.sendRedirect(req.getContextPath() + "/feedback");
        }
    }

    private void handleDeleteFeedback(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");
        int feedbackId;
        try {
            feedbackId = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            session.setAttribute("error", "ID feedback không hợp lệ");
            res.sendRedirect(req.getContextPath() + "/feedback");
            return;
        }

        Feedback feedback = dao.getFeedbackById(feedbackId);
        if (feedback == null) {
            session.setAttribute("error", "Không tìm thấy feedback");
            res.sendRedirect(req.getContextPath() + "/feedback");
            return;
        }

        // Kiểm tra quyền
        if (currentUser == null || (currentUser.getUserID() != feedback.getUserID() && currentUser.getRoleID() != 1)) {
            session.setAttribute("error", "Bạn không có quyền xóa feedback này");
            res.sendRedirect(req.getContextPath() + "/feedback");
            return;
        }

        boolean success = dao.deleteFeedback(feedbackId);
        if (success) {
            session.setAttribute("message", "Xóa feedback thành công");
        } else {
            session.setAttribute("error", "Không xóa được feedback");
        }

        // Chuyển hướng về trang feedback của danh mục
        int categoryId = feedback.getCategoryID();
        res.sendRedirect(req.getContextPath() + "/feedback?action=category&categoryID=" + categoryId);
    }

    private void handleEditFeedback(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");
        int feedbackId;
        try {
            feedbackId = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            session.setAttribute("error", "ID feedback không hợp lệ");
            res.sendRedirect(req.getContextPath() + "/feedback");
            return;
        }

        Feedback feedback = dao.getFeedbackById(feedbackId);
        if (feedback == null) {
            session.setAttribute("error", "Không tìm thấy feedback");
            res.sendRedirect(req.getContextPath() + "/feedback");
            return;
        }

        // Kiểm tra quyền
        if (currentUser == null || (currentUser.getUserID() != feedback.getUserID() && currentUser.getRoleID() != 1)) {
            session.setAttribute("error", "Bạn không có quyền chỉnh sửa feedback này");
            res.sendRedirect(req.getContextPath() + "/feedback");
            return;
        }

        req.setAttribute("feedback", feedback);
        req.getRequestDispatcher("/ShopPages/Pages/edit-feedback.jsp").forward(req, res);
    }

    private void handleUpdateFeedback(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) {
            session.setAttribute("error", "Vui lòng đăng nhập để chỉnh sửa feedback");
            res.sendRedirect(req.getContextPath() + "/Login");
            return;
        }

        int feedbackId;
        try {
            feedbackId = Integer.parseInt(req.getParameter("feedbackID"));
        } catch (NumberFormatException e) {
            session.setAttribute("error", "ID feedback không hợp lệ");
            res.sendRedirect(req.getContextPath() + "/feedback");
            return;
        }

        Feedback feedback = dao.getFeedbackById(feedbackId);
        if (feedback == null) {
            session.setAttribute("error", "Không tìm thấy feedback");
            res.sendRedirect(req.getContextPath() + "/feedback");
            return;
        }

        // Kiểm tra quyền
        if (currentUser.getUserID() != feedback.getUserID() && currentUser.getRoleID() != 1) {
            session.setAttribute("error", "Bạn không có quyền chỉnh sửa feedback này");
            res.sendRedirect(req.getContextPath() + "/feedback");
            return;
        }

        String content = req.getParameter("content");
        int rate;
        int categoryID;
        try {
            rate = Integer.parseInt(req.getParameter("rate"));
            categoryID = Integer.parseInt(req.getParameter("categoryID"));
        } catch (NumberFormatException e) {
            session.setAttribute("error", "Đánh giá hoặc ID danh mục không hợp lệ");
            req.setAttribute("feedback", feedback);
            req.getRequestDispatcher("/ShopPages/Pages/edit-feedback.jsp").forward(req, res);
            return;
        }

        // Xác thực dữ liệu
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
            session.setAttribute("message", "Cập nhật feedback thành công");
            res.sendRedirect(req.getContextPath() + "/feedback?action=category&categoryID=" + categoryID);
        } else {
            session.setAttribute("error", "Không cập nhật được feedback");
            req.setAttribute("feedback", feedback);
            req.getRequestDispatcher("/ShopPages/Pages/edit-feedback.jsp").forward(req, res);
        }
    }

    private void handleCategoryFeedback(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        int categoryId;
        try {
            categoryId = Integer.parseInt(req.getParameter("categoryID"));
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("error", "ID danh mục không hợp lệ");
            res.sendRedirect(req.getContextPath() + "/feedback");
            return;
        }
        List<Feedback> categoryFeedback = dao.getFeedbackByCategoryId(categoryId);
        req.setAttribute("feedbackList", categoryFeedback);
        req.setAttribute("categoryID", categoryId);
        req.getRequestDispatcher("/ShopPages/Pages/feedback.jsp").forward(req, res);
    }

    private void handleUserFeedback(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) {
            session.setAttribute("error", "Vui lòng đăng nhập để xem feedback của bạn");
            res.sendRedirect(req.getContextPath() + "/Login");
            return;
        }

        List<Feedback> userFeedback = dao.getFeedbackByUserId(currentUser.getUserID());
        req.setAttribute("feedbackList", userFeedback);
        req.getRequestDispatcher("/ShopPages/Pages/my-feedback.jsp").forward(req, res);
    }
}