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
                response.sendRedirect("Login");
                return;
            }

            // Lấy thông tin từ form
            int productID;
            int rate;
            String content = request.getParameter("content");
            try {
                productID = Integer.parseInt(request.getParameter("productId"));
                rate = Integer.parseInt(request.getParameter("rating"));
            } catch (NumberFormatException e) {
                session.setAttribute("error", "Invalid product ID or rating");
                response.sendRedirect("feedback?action=product&productID=" + request.getParameter("productId"));
                return;
            }

            // Validate dữ liệu
            if (content == null || content.trim().isEmpty()) {
                session.setAttribute("error", "Content cannot be empty");
                response.sendRedirect("feedback?action=product&productID=" + productID);
                return;
            }
            if (rate < 1 || rate > 5) {
                session.setAttribute("error", "Rating must be between 1 and 5");
                response.sendRedirect("feedback?action=product&productID=" + productID);
                return;
            }
            if (content.length() > 500) {
                session.setAttribute("error", "Content cannot exceed 500 characters");
                response.sendRedirect("feedback?action=product&productID=" + productID);
                return;
            }

            // Tạo đối tượng Feedback
            Feedback feedback = new Feedback(user.getUserID(), content, productID, rate);
            feedback.setCreatedAt(new Date());

            // Thêm feedback vào DB
            FeedbackDAO dao = new FeedbackDAO();
            boolean success = dao.insertFeedback(feedback);

            if (success) {
                session.setAttribute("message", "Feedback submitted successfully");
                response.sendRedirect("feedback?action=product&productID=" + productID);
            } else {
                session.setAttribute("error", "Failed to save feedback");
                response.sendRedirect("feedback?action=product&productID=" + productID);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in SubmitFeedbackServlet: " + e.getMessage(), e);
            session.setAttribute("error", "An error occurred while submitting feedback");
            response.sendRedirect("errorPage.jsp");
        }
    }
}
