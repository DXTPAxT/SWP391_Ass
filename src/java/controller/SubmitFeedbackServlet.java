package controller;

import dal.FeedbackDAO;
import models.Feedback;
import models.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "SubmitFeedbackServlet", urlPatterns = {"/submitFeedback"})
public class SubmitFeedbackServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SubmitFeedbackServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            // Kiểm tra người dùng đã đăng nhập
            User user = (User) session.getAttribute("user");
            if (user == null) {
                session.setAttribute("error", "Please login to submit feedback");
                LOGGER.warning("User not logged in while attempting to submit feedback");
                response.sendRedirect(request.getContextPath() + "/Login");
                return;
            }
            LOGGER.info("UserID " + user.getUserID() + " is submitting feedback");

            // Lấy thông tin từ form
            String orderItemIdStr = request.getParameter("orderItemId");
            String ratingStr = request.getParameter("rating");
            String content = request.getParameter("content"); // Sửa lỗi gõ phím

            // Kiểm tra dữ liệu đầu vào
            if (orderItemIdStr == null || ratingStr == null || content == null) {
                session.setAttribute("error", "Missing required fields");
                LOGGER.warning("Missing required fields: orderItemId=" + orderItemIdStr + ", rating=" + ratingStr + ", content=" + content);
                response.sendRedirect(request.getContextPath() + "/feedback?action=category&categoryID=1");
                return;
            }

            int orderItemId;
            int rate;
            try {
                orderItemId = Integer.parseInt(orderItemIdStr);
                rate = Integer.parseInt(ratingStr);
            } catch (NumberFormatException e) {
                session.setAttribute("error", "Invalid order item ID or rating");
                LOGGER.log(Level.WARNING, "Invalid orderItemId or rating: " + e.getMessage(), e);
                response.sendRedirect(request.getContextPath() + "/feedback?action=category&categoryID=1");
                return;
            }

            // Validate dữ liệu
            if (content.trim().isEmpty()) {
                session.setAttribute("error", "Content cannot be empty");
                LOGGER.warning("Content is empty for orderItemId: " + orderItemId);
                response.sendRedirect(request.getContextPath() + "/feedback?action=category&categoryID=1");
                return;
            }
            if (rate < 1 || rate > 5) {
                session.setAttribute("error", "Rating must be between 1 and 5");
                LOGGER.warning("Invalid rating: " + rate + " for orderItemId: " + orderItemId);
                response.sendRedirect(request.getContextPath() + "/feedback?action=category&categoryID=1");
                return;
            }
            if (content.length() > 500) {
                session.setAttribute("error", "Content cannot exceed 500 characters");
                LOGGER.warning("Content exceeds 500 characters for orderItemId: " + orderItemId);
                response.sendRedirect(request.getContextPath() + "/feedback?action=category&categoryID=1");
                return;
            }

            // Tạo đối tượng Feedback
            Feedback feedback = new Feedback(user.getUserID(), content, orderItemId, rate);
            feedback.setCreatedAt(new Date());
            LOGGER.info("Feedback object created: " + feedback.toString());

            // Thêm feedback vào DB
            FeedbackDAO dao = new FeedbackDAO();
            boolean success = dao.insertFeedback(feedback);

            if (success) {
                session.setAttribute("message", "Feedback submitted successfully");
                LOGGER.info("Feedback submitted successfully for orderItemId: " + orderItemId);
                response.sendRedirect(request.getContextPath() + "/feedback?action=category&categoryID=1");
            } else {
                session.setAttribute("error", "Failed to save feedback");
                LOGGER.warning("Failed to save feedback for orderItemId: " + orderItemId);
                response.sendRedirect(request.getContextPath() + "/feedback?action=category&categoryID=1");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in SubmitFeedbackServlet: " + e.getMessage(), e);
            session.setAttribute("error", "An error occurred while submitting feedback: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/feedback?action=category&categoryID=1");
        }
    }
}