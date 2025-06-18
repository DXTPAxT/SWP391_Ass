package controllerAdmin;

import dalAdmin.FeedbackAdminDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/replyFeedback")
public class ReplyFeedbackServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int feedbackID = Integer.parseInt(req.getParameter("feedbackID"));
        String reply = req.getParameter("reply");
        FeedbackAdminDAO dao = new FeedbackAdminDAO();
        dao.replyFeedback(feedbackID, reply);
        resp.sendRedirect(req.getContextPath() + "/FeedBackAdmin");
    }
}