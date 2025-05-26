package controller;

import dal.FeedbackDAO;
import jakarta.servlet.RequestDispatcher;
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

//        RequestDispatcher rs = req.getRequestDispatcher("/ShopPages/Pages/feedback.jsp");
//        rs.forward(req, res);
        if (action == null) {
            List<Feedback> allFeedback = dao.getAllFeedbacks();
            req.setAttribute("feedbackList", allFeedback);
            RequestDispatcher rs = req.getRequestDispatcher("/ShopPages/Pages/feedback.jsp");
            rs.forward(req, res);
        }
//
//        try {
//            switch (action) {
//                case "delete":
//                    int id = Integer.parseInt(req.getParameter("id"));
//                    dao.deleteFeedback(id);
//                    res.sendRedirect("feedback");
//                    break;
//
//                case "product":
//                    int productId = Integer.parseInt(req.getParameter("productID"));
//                    List<Feedback> productFeedback = dao.getFeedbackByProductId(productId);
//                    req.setAttribute("feedbackList", productFeedback);
//                    req.setAttribute("productID", productId);
//                    req.getRequestDispatcher("feedback.jsp").forward(req, res);
//                    break;
//
//                case "list":
//                default:
//                    List<Feedback> allFeedback = dao.getAllFeedbacks();
//                    req.setAttribute("feedbackList", allFeedback);
//                    req.getRequestDispatcher("feedback-list.jsp").forward(req, res);
//                    break;
//            }
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
//        } catch (Exception e) {
//            e.printStackTrace();
//            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error");
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            int userID = Integer.parseInt(req.getParameter("userID"));
            int productID = Integer.parseInt(req.getParameter("productID"));
            String content = req.getParameter("content");
            int rate = Integer.parseInt(req.getParameter("rate"));

            Feedback fb = new Feedback(userID, content, productID, rate);
            boolean success = dao.insertFeedback(fb);

            if (success) {
                // Sau khi insert, redirect về trang product feedback để thấy feedback mới
                res.sendRedirect("feedback?action=product&productID=" + productID);
            } else {
                // Nếu insert thất bại, gửi lỗi
                req.setAttribute("error", "Failed to save feedback");
                req.getRequestDispatcher("feedback.jsp").forward(req, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Cannot save feedback");
        }
    }
}
