package controller;

import dal.FeedbackDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Feedback;
import models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/feedback")
public class FeedbackServlet extends HttpServlet {

    private FeedbackDAO dao;
    private static final Logger LOGGER = Logger.getLogger(FeedbackServlet.class.getName());

    @Override
    public void init() {
        dao = new FeedbackDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();

        try {
//            if (action == null) {
//                session.removeAttribute("error");
//                List<Feedback> allFeedback = dao.getAllFeedbacks();
//                req.setAttribute("feedbackList", allFeedback);
//                RequestDispatcher rs = req.getRequestDispatcher("/ShopPages/Pages/feedback.jsp");
//                rs.forward(req, res);
//            } else {
//                switch (action) {
//                    case "delete":
//                        handleDeleteFeedback(req, res);
//                        break;
//                    case "edit":
//                        handleEditFeedback(req, res);
//                        break;
//                    case "category":
//                        handleCategoryFeedback(req, res);
//                        break;
//                    case "user":
//                        handleUserFeedback(req, res);
//                        break;
//                    default:
//                        session.removeAttribute("error");
//                        List<Feedback> allFeedback = dao.getAllFeedbacks();
//                        req.setAttribute("feedbackList", allFeedback);
//                        RequestDispatcher rs = req.getRequestDispatcher("/ShopPages/Pages/feedback.jsp");
//                        rs.forward(req, res);
//                        break;
//                }
//            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in doGet: " + e.getMessage(), e);
            session.setAttribute("error", "Failed to load feedback page: " + e.getMessage());
            req.setAttribute("feedbackList", new ArrayList<>());
            req.getRequestDispatcher("/ShopPages/Pages/feedback.jsp").forward(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        try {
            if ("update".equals(action)) {
//                handleUpdateFeedback(req, res);
            } else {
                User currentUser = (User) session.getAttribute("user");
                if (currentUser == null) {
                    session.setAttribute("error", "Please login to submit feedback");
                    res.sendRedirect(req.getContextPath() + "/Login");
                    return;
                }
                int userID = currentUser.getRole().getRoleID();
                int orderItemID = Integer.parseInt(req.getParameter("orderItemID"));
                String content = req.getParameter("content");
                int rate = Integer.parseInt(req.getParameter("rate"));

                if (content == null || content.trim().isEmpty() || content.length() > 500 || rate < 1 || rate > 5) {
                    session.setAttribute("error", "Invalid content or rating");
                    res.sendRedirect(req.getContextPath() + "/feedback?action=category&categoryID=1");
                    return;
                }

                Feedback fb = new Feedback(userID, content, orderItemID, rate);
                boolean success = dao.insertFeedback(fb);

                if (success) {
                    session.setAttribute("message", "Feedback submitted successfully");
                    res.sendRedirect(req.getContextPath() + "/feedback?action=category&categoryID=1");
                } else {
                    session.setAttribute("error", "Failed to save feedback");
                    res.sendRedirect(req.getContextPath() + "/feedback?action=category&categoryID=1");
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in doPost: " + e.getMessage(), e);
            session.setAttribute("error", "An error occurred while submitting feedback: " + e.getMessage());
            res.sendRedirect(req.getContextPath() + "/feedback?action=category&categoryID=1");
        }
    }
    private void handleDeleteFeedback(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");
        int feedbackId;
        int categoryId = 1; // default fallback
        try {
            feedbackId = Integer.parseInt(req.getParameter("id"));
            if (req.getParameter("categoryID") != null) {
                categoryId = Integer.parseInt(req.getParameter("categoryID"));
            }
        } catch (NumberFormatException e) {
            session.setAttribute("error", "Invalid feedback ID");
            res.sendRedirect(req.getContextPath() + "/feedback?action=category&categoryID=" + categoryId);
            return;
        }

        Feedback feedback = dao.getFeedbackById(feedbackId);
        if (feedback == null) {
            session.setAttribute("error", "Feedback not found");
            res.sendRedirect(req.getContextPath() + "/feedback?action=category&categoryID=" + categoryId);
            return;
        }

        if (currentUser == null || (currentUser.getUserID() != feedback.getUserID() && currentUser.getRoleID() != 1)) {
            session.setAttribute("error", "You are not authorized to delete this feedback");
            res.sendRedirect(req.getContextPath() + "/feedback?action=category&categoryID=" + categoryId);
            return;
        }

        boolean success = dao.deleteFeedback(feedbackId);
        if (success) {
            session.setAttribute("message", "Feedback deleted successfully");
        } else {
            session.setAttribute("error", "Failed to delete feedback");
        }
        res.sendRedirect(req.getContextPath() + "/feedback?action=category&categoryID=" + categoryId);
    }

    private void handleEditFeedback(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");
        int feedbackId;
        try {
            feedbackId = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            session.setAttribute("error", "Invalid feedback ID");
            res.sendRedirect(req.getContextPath() + "/ShopPages/Pages/feedback.jsp");
            return;
        }

        Feedback feedback = dao.getFeedbackById(feedbackId);
        if (feedback == null) {
            session.setAttribute("error", "Feedback not found");
            res.sendRedirect(req.getContextPath() + "/ShopPages/Pages/feedback.jsp");
            return;
        }

        if (currentUser == null || (currentUser.getUserID() != feedback.getUserID() && currentUser.getRoleID() != 1)) {
            session.setAttribute("error", "You are not authorized to edit this feedback");
            res.sendRedirect(req.getContextPath() + "/ShopPages/Pages/feedback.jsp");
            return;
        }

        req.setAttribute("feedback", feedback);
        req.getRequestDispatcher("/ShopPages/Pages/edit-feedback.jsp").forward(req, res);
    }

    private void handleUpdateFeedback(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) {
            session.setAttribute("error", "Please login to edit feedback");
            res.sendRedirect(req.getContextPath() + "/Login");
            return;
        }

        int feedbackId;
        int categoryId = 1; // default fallback
        try {
            feedbackId = Integer.parseInt(req.getParameter("feedbackID"));
            if (req.getParameter("categoryID") != null) {
                categoryId = Integer.parseInt(req.getParameter("categoryID"));
            }
        } catch (NumberFormatException e) {
            session.setAttribute("error", "Invalid feedback ID");
            res.sendRedirect(req.getContextPath() + "/feedback?action=category&categoryID=" + categoryId);
            return;
        }

        Feedback feedback = dao.getFeedbackById(feedbackId);
        if (feedback == null) {
            session.setAttribute("error", "Feedback not found");
            res.sendRedirect(req.getContextPath() + "/feedback?action=category&categoryID=" + categoryId);
            return;
        }

        if (currentUser.getUserID() != feedback.getUserID() && currentUser.getRoleID() != 1) {
            session.setAttribute("error", "You are not authorized to edit this feedback");
            res.sendRedirect(req.getContextPath() + "/feedback?action=category&categoryID=" + categoryId);
            return;
        }

        String content = req.getParameter("content");
        int rate;
        int orderItemID;
        try {
            rate = Integer.parseInt(req.getParameter("rate"));
            orderItemID = Integer.parseInt(req.getParameter("orderItemID"));
        } catch (NumberFormatException e) {
            session.setAttribute("error", "Invalid rating or order item ID");
            req.setAttribute("feedback", feedback);
            req.getRequestDispatcher("/ShopPages/Pages/edit-feedback.jsp").forward(req, res);
            return;
        }

        if (content == null || content.trim().isEmpty() || rate < 1 || rate > 5) {
            session.setAttribute("error", "Invalid content or rating");
            req.setAttribute("feedback", feedback);
            req.getRequestDispatcher("/ShopPages/Pages/edit-feedback.jsp").forward(req, res);
            return;
        }

        feedback.setContent(content);
        feedback.setRate(rate);
        feedback.setOrderItemID(orderItemID);
        boolean success = dao.updateFeedback(feedback);

        if (success) {
            session.setAttribute("message", "Feedback updated successfully");
            res.sendRedirect(req.getContextPath() + "/feedback?action=category&categoryID=" + categoryId);
        } else {
            session.setAttribute("error", "Failed to update feedback");
            req.setAttribute("feedback", feedback);
            req.getRequestDispatcher("/ShopPages/Pages/edit-feedback.jsp").forward(req, res);
        }
    }

    private void handleCategoryFeedback(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        int categoryId;
        try {
            categoryId = Integer.parseInt(req.getParameter("categoryID"));
        } catch (NumberFormatException e) {
            session.setAttribute("error", "Invalid category ID");
            req.setAttribute("feedbackList", new ArrayList<>());
            req.getRequestDispatcher("/ShopPages/Pages/CategoriesDetails.jsp").forward(req, res);
            return;
        }

        try {
            session.removeAttribute("error");
            List<Feedback> categoryFeedback = dao.getFeedbackByCategoryId(categoryId);
            req.setAttribute("feedbackList", categoryFeedback != null ? categoryFeedback : new ArrayList<>());
            req.setAttribute("categoryID", categoryId);
            req.getRequestDispatcher("/ShopPages/Pages/CategoriesDetails.jsp").forward(req, res);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in handleCategoryFeedback: " + e.getMessage(), e);
            session.setAttribute("error", "Failed to retrieve feedbacks for category: " + e.getMessage());
            req.setAttribute("feedbackList", new ArrayList<>());
            req.setAttribute("categoryID", categoryId);
            req.getRequestDispatcher("/ShopPages/Pages/CategoriesDetails.jsp").forward(req, res);
        }
    }

    private void handleUserFeedback(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) {
            session.setAttribute("error", "Please login to view your feedback");
            res.sendRedirect(req.getContextPath() + "/Login");
            return;
        }

        try {
            session.removeAttribute("error");
            List<Feedback> userFeedback = dao.getFeedbackByUserId(currentUser.getUserID());
            req.setAttribute("feedbackList", userFeedback != null ? userFeedback : new ArrayList<>());
            req.getRequestDispatcher("/ShopPages/Pages/my-feedback.jsp").forward(req, res);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in handleUserFeedback: " + e.getMessage(), e);
            session.setAttribute("error", "Failed to retrieve your feedbacks: " + e.getMessage());
            req.setAttribute("feedbackList", new ArrayList<>());
            req.getRequestDispatcher("/ShopPages/Pages/my-feedback.jsp").forward(req, res);
        }
    }
}