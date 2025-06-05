package controller;

import dal.FeedbackDAO;
import models.Feedback;
import models.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/submitFeedback")
public class SubmitFeedbackServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(SubmitFeedbackServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                session.setAttribute("error", "Vui lòng đăng nhập để gửi phản hồi");
                res.sendRedirect("Login");
                return;
            }

            int categoryID, rate;
            String content = req.getParameter("content");
            try {
                categoryID = Integer.parseInt(req.getParameter("categoryId"));
                rate = Integer.parseInt(req.getParameter("rating"));
            } catch (NumberFormatException e) {
                session.setAttribute("error", "ID danh mục hoặc đánh giá không hợp lệ");
                res.sendRedirect("feedback?action=category&categoryID=" + req.getParameter("categoryId"));
                return;
            }

            if (content == null || content.trim().isEmpty()) {
                session.setAttribute("error", "Nội dung không được để trống");
                res.sendRedirect("feedback?action=category&categoryID=" + categoryID);
                return;
            }
            if (rate < 1 || rate > 5) {
                session.setAttribute("error", "Đánh giá phải từ 1 đến 5");
                res.sendRedirect("feedback?action=category&categoryID=" + categoryID);
                return;
            }
            if (content.length() > 500) {
                session.setAttribute("error", "Nội dung không được vượt quá 500 ký tự");
                res.sendRedirect("feedback?action=category&categoryID=" + categoryID);
                return;
            }

            Feedback feedback = new Feedback(user.getUserID(), content, categoryID, rate);
            feedback.setCreatedAt(new Date());
            FeedbackDAO dao = new FeedbackDAO();
            boolean success = dao.insertFeedback(feedback);

            if (success) {
                session.setAttribute("message", "Gửi phản hồi thành công");
                res.sendRedirect("feedback?action=category&categoryID=" + categoryID);
            } else {
                session.setAttribute("error", "Gửi phản hồi thất bại");
                res.sendRedirect("feedback?action=category&categoryID=" + categoryID);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in SubmitFeedbackServlet: " + e.getMessage(), e);
            session.setAttribute("error", "Lỗi hệ thống, vui lòng thử lại sau");
            res.sendRedirect("errorPage.jsp");
        }
    }
}