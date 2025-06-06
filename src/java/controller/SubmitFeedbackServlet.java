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
        HttpSession session = request.getSession(); // Khai báo một lần duy nhất
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
            String categoryIdStr = request.getParameter("categoryId");
            String ratingStr = request.getParameter("rating");
            String content = request.getParameter("content");

            // Kiểm tra dữ liệu đầu vào
            if (categoryIdStr == null || ratingStr == null || content == null) {
                session.setAttribute("error", "Missing required fields");
                LOGGER.warning("Missing required fields: categoryId=" + categoryIdStr + ", rating=" + ratingStr + ", content=" + content);
                response.sendRedirect(request.getContextPath() + "/feedback?action=category&categoryID=" + (categoryIdStr != null ? categoryIdStr : "1"));
                return;
            }

            int categoryId;
            int rate;
            try {
                categoryId = Integer.parseInt(categoryIdStr);
                rate = Integer.parseInt(ratingStr);
            } catch (NumberFormatException e) {
                session.setAttribute("error", "Invalid category ID or rating");
                LOGGER.log(Level.WARNING, "Invalid categoryId or rating: " + e.getMessage(), e);
                response.sendRedirect(request.getContextPath() + "/feedback?action=category&categoryID=" + categoryIdStr);
                return;
            }

            // Validate dữ liệu
            if (content.trim().isEmpty()) {
                session.setAttribute("error", "Content cannot be empty");
                LOGGER.warning("Content is empty for categoryId: " + categoryId);
                response.sendRedirect(request.getContextPath() + "/feedback?action=category&categoryID=" + categoryId);
                return;
            }
            if (rate < 1 || rate > 5) {
                session.setAttribute("error", "Rating must be between 1 and 5");
                LOGGER.warning("Invalid rating: " + rate + " for categoryId: " + categoryId);
                response.sendRedirect(request.getContextPath() + "/feedback?action=category&categoryID=" + categoryId);
                return;
            }
            if (content.length() > 500) {
                session.setAttribute("error", "Content cannot exceed 500 characters");
                LOGGER.warning("Content exceeds 500 characters for categoryId: " + categoryId);
                response.sendRedirect(request.getContextPath() + "/feedback?action=category&categoryID=" + categoryId);
                return;
            }

            // Tạo đối tượng Feedback
            Feedback feedback = new Feedback(user.getUserID(), content, categoryId, rate);
            feedback.setCreatedAt(new Date());
            LOGGER.info("Feedback object created: " + feedback.toString());

            // Thêm feedback vào DB
            FeedbackDAO dao = new FeedbackDAO();
            boolean success = dao.insertFeedback(feedback);

            if (success) {
                session.setAttribute("message", "Feedback submitted successfully");
                LOGGER.info("Feedback submitted successfully for categoryId: " + categoryId);
                response.sendRedirect(request.getContextPath() + "/feedback?action=category&categoryID=" + categoryId);
            } else {
                session.setAttribute("error", "Failed to save feedback");
                LOGGER.warning("Failed to save feedback for categoryId: " + categoryId);
                response.sendRedirect(request.getContextPath() + "/feedback?action=category&categoryID=" + categoryId);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in SubmitFeedbackServlet: " + e.getMessage(), e);
            session.setAttribute("error", "An error occurred while submitting feedback: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/feedback?action=category&categoryID=" + (request.getParameter("categoryId") != null ? request.getParameter("categoryId") : "1"));
        }
    }
}