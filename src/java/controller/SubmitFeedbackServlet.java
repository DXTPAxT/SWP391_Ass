package controller;

import dal.FeedbackDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.logging.Logger;
import models.Feedback;

@WebServlet("/submitFeedback")
public class SubmitFeedbackServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(SubmitFeedbackServlet.class.getName());
    private FeedbackDAO dao;

    @Override
    public void init() {
        dao = new FeedbackDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        try {
            String productIdParam = request.getParameter("productId");
            String content = request.getParameter("content");
            String ratingParam = request.getParameter("rating");

            LOGGER.info("Submitting feedback for productID: " + productIdParam);

            if (productIdParam == null || content == null || ratingParam == null ||
                productIdParam.trim().isEmpty() || content.trim().isEmpty() || ratingParam.trim().isEmpty()) {
                request.getSession().setAttribute("error", "Vui lòng điền đầy đủ thông tin");
                LOGGER.warning("Missing parameters: productId=" + productIdParam + ", content=" + content + ", rating=" + ratingParam);
                response.sendRedirect(request.getContextPath() + "/feedback?action=product&productID=" + productIdParam);
                return;
            }

            int productId;
            int rating;
            try {
                productId = Integer.parseInt(productIdParam);
                rating = Integer.parseInt(ratingParam);
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("error", "Dữ liệu đầu vào không hợp lệ");
                LOGGER.warning("Invalid format: productId=" + productIdParam + ", rating=" + ratingParam);
                response.sendRedirect(request.getContextPath() + "/feedback?action=product&productID=" + productIdParam);
                return;
            }

            if (rating < 1 || rating > 5) {
                request.getSession().setAttribute("error", "Đánh giá phải từ 1 đến 5 sao");
                LOGGER.warning("Invalid rating: " + rating);
                response.sendRedirect(request.getContextPath() + "/feedback?action=product&productID=" + productId);
                return;
            }

            HttpSession session = request.getSession();
            models.User user = (models.User) session.getAttribute("user");
            if (user == null) {
                request.getSession().setAttribute("error", "Vui lòng đăng nhập để gửi feedback");
                LOGGER.warning("User not logged in");
                response.sendRedirect(request.getContextPath() + "/Login");
                return;
            }

            if (!dao.isValidProductId(productId)) {
                request.getSession().setAttribute("error", "Sản phẩm không tồn tại");
                LOGGER.warning("Invalid productID: " + productId);
                response.sendRedirect(request.getContextPath() + "/Home");
                return;
            }

            Feedback feedback = new Feedback();
            feedback.setUserID(user.getUserID());
            feedback.setProductID(productId);
            feedback.setContent(content);
            feedback.setRate(rating);
            feedback.setStatus(1);

            boolean success = dao.insertFeedback(feedback);
            if (success) {
                request.getSession().setAttribute("message", "Gửi feedback thành công");
                LOGGER.info("Feedback submitted: userID=" + user.getUserID() + ", productID=" + productId);
            } else {
                request.getSession().setAttribute("error", "Không thể gửi feedback");
                LOGGER.severe("Failed to insert feedback: userID=" + user.getUserID() + ", productID=" + productId);
            }
            long duration = System.currentTimeMillis() - startTime;
            LOGGER.info("Feedback submission took " + duration + "ms");
            response.sendRedirect(request.getContextPath() + "/feedback?action=product&productID=" + productId);
        } catch (Exception e) {
            LOGGER.severe("Error: " + e.getMessage());
            request.getSession().setAttribute("error", "Lỗi server khi gửi feedback");
            response.sendRedirect(request.getContextPath() + "/feedback?action=product&productID=" + request.getParameter("productId"));
        }
    }
}