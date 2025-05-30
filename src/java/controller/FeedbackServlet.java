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
        dao = new FeedbackDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
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
                    case "product":
                        handleProductFeedback(req, res);
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
            LOGGER.log(Level.SEVERE, "Error in doGet: " + e.getMessage(), e);
            res.sendRedirect("errorPage.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("update".equals(action)) {
                handleUpdateFeedback(req, res);
            } else {
                HttpSession session = req.getSession();
                User currentUser = (User) session.getAttribute("user");
                if (currentUser == null) {
                    res.sendRedirect("Login");
                    return;
                }
                int userID = currentUser.getUserID();
                int productID = Integer.parseInt(req.getParameter("productID"));
                String content = req.getParameter("content");
                int rate = Integer.parseInt(req.getParameter("rate"));

                // Validate input
                if (content == null || content.trim().isEmpty() || rate < 1 || rate > 5) {
                    session.setAttribute("error", "Invalid content or rating");
                    res.sendRedirect("feedback?action=product&productID=" + productID);
                    return;
                }

                Feedback fb = new Feedback(userID, content, productID, rate);
                boolean success = dao.insertFeedback(fb);

                if (success) {
                    session.setAttribute("message", "Feedback submitted successfully");
                    res.sendRedirect("feedback?action=product&productID=" + productID);
                } else {
                    session.setAttribute("error", "Failed to save feedback");
                    res.sendRedirect("feedback?action=product&productID=" + productID);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in doPost: " + e.getMessage(), e);
            res.sendRedirect("errorPage.jsp");
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
            session.setAttribute("error", "Invalid feedback ID");
            res.sendRedirect("feedback");
            return;
        }

        Feedback feedback = dao.getFeedbackById(feedbackId);
        if (feedback == null) {
            session.setAttribute("error", "Feedback not found");
            res.sendRedirect("feedback");
            return;
        }

        // Kiểm tra quyền
        if (currentUser == null || (currentUser.getUserID() != feedback.getUserID() && currentUser.getRoleID() != 1)) {
            session.setAttribute("error", "You are not authorized to delete this feedback");
            res.sendRedirect("feedback");
            return;
        }

        boolean success = dao.deleteFeedback(feedbackId);
        if (success) {
            session.setAttribute("message", "Feedback deleted successfully");
        } else {
            session.setAttribute("error", "Failed to delete feedback");
        }

        // Chuyển hướng về trang feedback của sản phẩm
        int productId = feedback.getProductID();
        res.sendRedirect("feedback?action=product&productID=" + productId);
    }

    private void handleEditFeedback(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");
        int feedbackId;
        try {
            feedbackId = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            session.setAttribute("error", "Invalid feedback ID");
            res.sendRedirect("feedback");
            return;
        }

        Feedback feedback = dao.getFeedbackById(feedbackId);
        if (feedback == null) {
            session.setAttribute("error", "Feedback not found");
            res.sendRedirect("feedback");
            return;
        }

        // Kiểm tra quyền
        if (currentUser == null || (currentUser.getUserID() != feedback.getUserID() && currentUser.getRoleID() != 1)) {
            session.setAttribute("error", "You are not authorized to edit this feedback");
            res.sendRedirect("feedback");
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
            res.sendRedirect("Login");
            return;
        }

        int feedbackId;
        try {
            feedbackId = Integer.parseInt(req.getParameter("feedbackID"));
        } catch (NumberFormatException e) {
            session.setAttribute("error", "Invalid feedback ID");
            res.sendRedirect("feedback");
            return;
        }

        Feedback feedback = dao.getFeedbackById(feedbackId);
        if (feedback == null) {
            session.setAttribute("error", "Feedback not found");
            res.sendRedirect("feedback");
            return;
        }

        // Kiểm tra quyền
        if (currentUser.getUserID() != feedback.getUserID() && currentUser.getRoleID() != 1) {
            session.setAttribute("error", "You are not authorized to edit this feedback");
            res.sendRedirect("feedback");
            return;
        }

        String content = req.getParameter("content");
        int rate;
        int productID;
        try {
            rate = Integer.parseInt(req.getParameter("rate"));
            productID = Integer.parseInt(req.getParameter("productID"));
        } catch (NumberFormatException e) {
            session.setAttribute("error", "Invalid rating or product ID");
            req.setAttribute("feedback", feedback);
            req.getRequestDispatcher("/ShopPages/Pages/edit-feedback.jsp").forward(req, res);
            return;
        }

        // Validate dữ liệu
        if (content == null || content.trim().isEmpty() || rate < 1 || rate > 5) {
            session.setAttribute("error", "Invalid content or rating");
            req.setAttribute("feedback", feedback);
            req.getRequestDispatcher("/ShopPages/Pages/edit-feedback.jsp").forward(req, res);
            return;
        }

        feedback.setContent(content);
        feedback.setRate(rate);
        boolean success = dao.updateFeedback(feedback);

        if (success) {
            session.setAttribute("message", "Feedback updated successfully");
            res.sendRedirect("feedback?action=product&productID=" + productID);
        } else {
            session.setAttribute("error", "Failed to update feedback");
            req.setAttribute("feedback", feedback);
            req.getRequestDispatcher("/ShopPages/Pages/edit-feedback.jsp").forward(req, res);
        }
    }

    private void handleProductFeedback(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        int productId;
        try {
            productId = Integer.parseInt(req.getParameter("productID"));
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("error", "Invalid product ID");
            res.sendRedirect("feedback");
            return;
        }
        List<Feedback> productFeedback = dao.getFeedbackByProductId(productId);
        req.setAttribute("feedbackList", productFeedback);
        req.setAttribute("productID", productId);
        req.getRequestDispatcher("/ShopPages/Pages/feedback.jsp").forward(req, res);
    }

    private void handleUserFeedback(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
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