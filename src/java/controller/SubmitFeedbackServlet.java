package controller;

import dal.FeedbackDAO;
import models.Feedback;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import models.User;

@WebServlet(name = "SubmitFeedbackServlet", urlPatterns = {"/submitFeedback"})
public class SubmitFeedbackServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy userID từ session, tránh người dùng giả mạo
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if (user == null) {
                // chưa đăng nhập -> chuyển về trang login hoặc báo lỗi
                response.sendRedirect("Login");
                return;
            }

            // Lấy thông tin từ form
//            int productID = Integer.parseInt(request.getParameter("productId"));  // chú ý tên phải đúng với form
            int productID = 1;
            int rate = Integer.parseInt(request.getParameter("rating"));          // tương ứng với name="rating"
            String content = request.getParameter("content");

            // Lấy thời gian hiện tại và chuyển sang java.util.Date
//            LocalDateTime now = LocalDateTime.now();
//            Date createdAt = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

            // Tạo đối tượng Feedback
            Feedback feedback = new Feedback(0, user.getUserID(), content, productID, rate);

            // Thêm feedback vào DB
            FeedbackDAO dao = new FeedbackDAO();
            dao.insertFeedback(feedback);

            // Chuyển hướng về trang chi tiết sản phẩm
//            response.sendRedirect("Product?service=Detail&ProductID=" + productID);
            response.sendRedirect("feedback");

        } catch (Exception e) {
            HttpSession session = request.getSession();
            session.setAttribute("error", e.getMessage());  
            // Redirect về trang lỗi nếu có exception
            response.sendRedirect("test.jsp");
        }
    }
}
