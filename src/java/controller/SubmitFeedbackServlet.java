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

@WebServlet(name = "SubmitFeedbackServlet", urlPatterns = {"/submitFeedback"})
public class SubmitFeedbackServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy userID từ session, tránh người dùng giả mạo
            HttpSession session = request.getSession();
            Integer userID = null;
            if (session.getAttribute("currentUser") != null) {
                userID = (Integer) session.getAttribute("currentUser.userID");
                // hoặc nếu currentUser là object, bạn phải cast và lấy id:
                // User currentUser = (User) session.getAttribute("currentUser");
                // userID = currentUser.getUserID();
            }

            if (userID == null) {
                // chưa đăng nhập -> chuyển về trang login hoặc báo lỗi
                response.sendRedirect("login.jsp");
                return;
            }

            // Lấy thông tin từ form
            int productID = Integer.parseInt(request.getParameter("productId"));  // chú ý tên phải đúng với form
            int rate = Integer.parseInt(request.getParameter("rating"));          // tương ứng với name="rating"
            String content = request.getParameter("content");

            // Lấy thời gian hiện tại và chuyển sang java.util.Date
            LocalDateTime now = LocalDateTime.now();
            Date createdAt = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

            // Tạo đối tượng Feedback
            Feedback feedback = new Feedback(0, userID, content, productID, createdAt, rate);

            // Thêm feedback vào DB
            FeedbackDAO dao = new FeedbackDAO();
            dao.insertFeedback(feedback);

            // Chuyển hướng về trang chi tiết sản phẩm
            response.sendRedirect("productDetail.jsp?id=" + productID);

        } catch (Exception e) {
            e.printStackTrace();
            // Redirect về trang lỗi nếu có exception
            response.sendRedirect("error.jsp");
        }
    }
}
