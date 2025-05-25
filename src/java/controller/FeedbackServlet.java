package controller;

import dal.FeedbackDAO;
import models.Feedback;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/feedback")
public class FeedbackServlet extends HttpServlet {
    private FeedbackDAO dao;

    @Override
    public void init() {
        dao = new FeedbackDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "delete":
                String idRaw = req.getParameter("id");
                try {
                    int id = Integer.parseInt(idRaw);
                    dao.deleteFeedback(id);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                res.sendRedirect("feedback");
                break;

            case "list":
            default:
                List<Feedback> list = dao.getAllFeedbacks();
                req.setAttribute("feedbackList", list);
                req.getRequestDispatcher("feedback-list.jsp").forward(req, res);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            int userID = Integer.parseInt(req.getParameter("userID"));
            int productID = Integer.parseInt(req.getParameter("productID"));
            String content = req.getParameter("content");

            Feedback fb = new Feedback(0, userID, content, productID, null);
            dao.insertFeedback(fb);
        } catch (Exception e) {
            e.printStackTrace();
        }
        res.sendRedirect("feedback");
    }
}
