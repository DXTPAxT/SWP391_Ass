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
            // Kiểm tra đăng nhập
            User user = (User) session.getAttribute("user");
            if (user == null) {
                session.setAttribute("error", "Vui lòng đăng nhập để gửi feedback");
                response.sendRedirect(request.getContextPath() + "/Login");
                return;
            }

            // Lấy thông tin từ form
            int categoryID;
            int rate;
            String content = request.getParameter("content");
            try {
                categoryID = Integer.parseInt(request.getParameter("categoryID"));
                rate = Integer.parseInt(request.getParameter("rating"));
            } catch (NumberFormatException e) {
                session.setAttribute("error", "ID danh mục hoặc đánh giá không hợp lệ");
                response.sendRedirect(request.getContextPath() + "/feedback?action=category&categoryID=" + request.getParameter("categoryID"));
                return;
            }

            // Xác thực dữ liệu
            if (content == null || content.trim().isEmpty()) {
                session.setAttribute("error", "Nội dung không được để trống");
                response.sendRedirect(request.getContextPath() + "/feedback?action=category&categoryID=" + categoryID);
                return;
            }
            if (rate < 1 || rate > 5) {
                session.setAttribute("error", "Đánh giá phải từ 1 đến 5");
                response.sendRedirect(request.getContextPath() + "/feedback?action=category&categoryID=" + categoryID);
                return;
            }
            if (content.length() > 500) {
                session.setAttribute("error", "Nội dung không được vượt quá 500 ký tự");
                response.sendRedirect(request.getContextPath() + "/feedback?action=category&categoryID=" + categoryID);
                return;
            }

            // Tạo Feedback
            Feedback feedback = new Feedback(user.getUserID(), content, categoryID, rate);
            feedback.setCreatedAt(new Date());

            // Thêm vào cơ sở dữ liệu
            FeedbackDAO dao = new FeedbackDAO();
            boolean success = dao.insertFeedback(feedback);

            if (success) {
                session.setAttribute("message", "Gửi feedback thành công");
                response.sendRedirect(request.getContextPath() + "/feedback?action=category&categoryID=" + categoryID);
            } else {
                session.setAttribute("error", "Không lưu được feedback");
                response.sendRedirect(request.getContextPath() + "/feedback?action=category&categoryID=" + categoryID);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi trong SubmitFeedbackServlet: " + e.getMessage(), e);
            session.setAttribute("error", "Đã xảy ra lỗi khi gửi feedback: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/feedback");
        }
    }
}