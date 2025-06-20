package controllerAdmin;

import dalAdmin.FeedbackAdminDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.Feedback;
import java.io.IOException;

@WebServlet("/admin/replyFeedback")
public class ReplyFeedbackServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int feedbackID = Integer.parseInt(req.getParameter("feedbackID"));
        FeedbackAdminDAO dao = new FeedbackAdminDAO();
        Feedback feedback = dao.getFeedbackById(feedbackID);
        req.setAttribute("feedback", feedback);
        // Lấy thêm thông tin sản phẩm nếu cần, ví dụ:
        // Product product = dao.getProductByFeedback(feedbackID);
        // req.setAttribute("product", product);
        req.getRequestDispatcher("/AdminLTE/AdminPages/pages/tables/replyFeedback.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int feedbackID = Integer.parseInt(req.getParameter("feedbackID"));
        String reply = req.getParameter("reply");
        FeedbackAdminDAO dao = new FeedbackAdminDAO();
        dao.replyFeedback(feedbackID, reply);
        resp.sendRedirect(req.getContextPath() + "/FeedBackAdmin");
    }
}