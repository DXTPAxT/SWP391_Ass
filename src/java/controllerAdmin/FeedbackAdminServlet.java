/*
 * Click nb://source://SystemFileSystemAdmin/Templates/LicensesAdmin/license-default.txt to change this license
 * Click nb://source://SystemFileSystemAdmin/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import dalAdmin.FeedbackAdminDAO;
import models.Feedback;
import models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author haiga
 */
@WebServlet(name = "FeedbackAdminServlet", urlPatterns = {"/FeedbackAdmin"})
public class FeedbackAdminServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") == null || ((User) session.getAttribute("user")).getRole().getRoleID()!= 1) {
                session.setAttribute("toast", "Access denied. Admins only.");
                session.setAttribute("toastType", "error");
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }

            String service = request.getParameter("service");
            if (service == null) {
                service = "listall";
            }

            FeedbackAdminDAO feedbackDAO = new FeedbackAdminDAO();

            if (service.equals("listall")) {
                List<Feedback> feedbacks = feedbackDAO.getAllFeedbacks();
                request.setAttribute("feedbacks", feedbacks);
                request.getRequestDispatcher("AdminLTE/AdminPages/viewFeedback.jsp").forward(request, response);

            } else if (service.equals("insert")) {
                String submit = request.getParameter("submit");

                if (submit == null) {
                    request.getRequestDispatcher("AdminLTE/AdminPages/insertFeedback.jsp").forward(request, response);
                } else {
                    String userIDRaw = request.getParameter("userID");
                    String orderItemIDRaw = request.getParameter("orderItemID");
                    String content = request.getParameter("content");
                    String rateRaw = request.getParameter("rate");
                    String statusRaw = request.getParameter("status");

                    String error = null;
                    if (userIDRaw == null || userIDRaw.trim().isEmpty()) {
                        error = "User ID cannot be empty.";
                    } else if (orderItemIDRaw == null || orderItemIDRaw.trim().isEmpty()) {
                        error = "Order Item ID cannot be empty.";
                    } else if (content != null && content.trim().length() > 500) {
                        error = "Content cannot exceed 500 characters.";
                    } else if (content != null && !content.trim().isEmpty() && !content.matches("^[A-Za-z0-9 .,?!]+$")) {
                        error = "Content must contain only letters, digits, spaces, and basic punctuation.";
                    }

                    if (error != null) {
                        request.setAttribute("error", error);
                        request.setAttribute("userID", userIDRaw);
                        request.setAttribute("orderItemID", orderItemIDRaw);
                        request.setAttribute("content", content);
                        request.setAttribute("rate", rateRaw);
                        request.setAttribute("status", statusRaw);
                        request.getRequestDispatcher("AdminLTE/AdminPages/insertFeedback.jsp").forward(request, response);
                        return;
                    }

                    try {
                        int userID = Integer.parseInt(userIDRaw);
                        int orderItemID = Integer.parseInt(orderItemIDRaw);
                        int rate = Integer.parseInt(rateRaw);
                        int status = Integer.parseInt(statusRaw);
                        if (rate < 1 || rate > 5) {
                            throw new NumberFormatException("Rate must be between 1 and 5.");
                        }
                        if (status != 0 && status != 1) {
                            throw new NumberFormatException("Status must be 0 or 1.");
                        }
                        Feedback feedback = new Feedback();
                        feedback.setUserID(userID);
                        feedback.setOrderItemID(orderItemID);
                        feedback.setContent(content != null && !content.trim().isEmpty() ? content.trim() : null);
                        // Không đặt createdAt vì nó không được truyền từ form, để null
                        feedback.setRate(rate);
                        feedback.setStatus(status);
                        feedback.setUser(new User("User " + userID)); // Giả lập user
                        feedbackDAO.insertFeedback(feedback);
                        session.setAttribute("toast", "Feedback added successfully.");
                        session.setAttribute("toastType", "success");
                        response.sendRedirect("FeedbackAdmin?service=listall");
                    } catch (NumberFormatException e) {
                        request.setAttribute("error", e.getMessage());
                        request.setAttribute("userID", userIDRaw);
                        request.setAttribute("orderItemID", orderItemIDRaw);
                        request.setAttribute("content", content);
                        request.setAttribute("rate", rateRaw);
                        request.setAttribute("status", statusRaw);
                        request.getRequestDispatcher("AdminLTE/AdminPages/insertFeedback.jsp").forward(request, response);
                    } catch (IllegalArgumentException e) {
                        request.setAttribute("error", e.getMessage());
                        request.setAttribute("userID", userIDRaw);
                        request.setAttribute("orderItemID", orderItemIDRaw);
                        request.setAttribute("content", content);
                        request.setAttribute("rate", rateRaw);
                        request.setAttribute("status", statusRaw);
                        request.getRequestDispatcher("AdminLTE/AdminPages/insertFeedback.jsp").forward(request, response);
                    }
                }

            } else if (service.equals("edit")) {
                String submit = request.getParameter("submit");

                if (submit == null) {
                    int feedbackID = Integer.parseInt(request.getParameter("feedbackID"));
                    Feedback feedback = feedbackDAO.getFeedbackById(feedbackID);
                    if (feedback != null) {
                        request.setAttribute("feedback", feedback);
                        request.getRequestDispatcher("AdminLTE/AdminPages/editFeedback.jsp").forward(request, response);
                    } else {
                        session.setAttribute("toast", "Feedback not found.");
                        session.setAttribute("toastType", "error");
                        response.sendRedirect("FeedbackAdmin?service=listall");
                    }
                } else {
                    String feedbackIDRaw = request.getParameter("feedbackID");
                    String content = request.getParameter("content");
                    String rateRaw = request.getParameter("rate");
                    String statusRaw = request.getParameter("status");

                    String error = null;
                    if (content != null && content.trim().length() > 500) {
                        error = "Content cannot exceed 500 characters.";
                    } else if (content != null && !content.trim().isEmpty() && !content.matches("^[A-Za-z0-9 .,?!]+$")) {
                        error = "Content must contain only letters, digits, spaces, and basic punctuation.";
                    }

                    if (error != null) {
                        request.setAttribute("error", error);
                        request.setAttribute("feedbackID", feedbackIDRaw);
                        request.setAttribute("content", content);
                        request.setAttribute("rate", rateRaw);
                        request.setAttribute("status", statusRaw);
                        request.getRequestDispatcher("AdminLTE/AdminPages/editFeedback.jsp").forward(request, response);
                        return;
                    }

                    try {
                        int feedbackID = Integer.parseInt(feedbackIDRaw);
                        int rate = Integer.parseInt(rateRaw);
                        int status = Integer.parseInt(statusRaw);
                        if (rate < 1 || rate > 5) {
                            throw new NumberFormatException("Rate must be between 1 and 5.");
                        }
                        if (status != 0 && status != 1) {
                            throw new NumberFormatException("Status must be 0 or 1.");
                        }
                        Feedback feedback = new Feedback();
                        feedback.setFeedbackID(feedbackID);
                        feedback.setContent(content != null && !content.trim().isEmpty() ? content.trim() : null);
                        // Không cập nhật createdAt từ form, giữ nguyên giá trị hiện tại
                        feedback.setRate(rate);
                        feedback.setStatus(status);
                        boolean updated = feedbackDAO.updateFeedback(feedbackID, feedback);
                        session.setAttribute("toast", updated ? "Feedback updated successfully." : "Failed to update feedback.");
                        session.setAttribute("toastType", updated ? "success" : "error");
                        response.sendRedirect("FeedbackAdmin?service=listall");
                    } catch (NumberFormatException e) {
                        request.setAttribute("error", "Feedback ID, Rate, and Status must be valid numbers. Rate must be 1-5, Status must be 0 or 1.");
                        request.setAttribute("feedbackID", feedbackIDRaw);
                        request.setAttribute("content", content);
                        request.setAttribute("rate", rateRaw);
                        request.setAttribute("status", statusRaw);
                        request.getRequestDispatcher("AdminLTE/AdminPages/editFeedback.jsp").forward(request, response);
                    } catch (IllegalArgumentException e) {
                        request.setAttribute("error", e.getMessage());
                        request.setAttribute("feedbackID", feedbackIDRaw);
                        request.setAttribute("content", content);
                        request.setAttribute("rate", rateRaw);
                        request.setAttribute("status", statusRaw);
                        request.getRequestDispatcher("AdminLTE/AdminPages/editFeedback.jsp").forward(request, response);
                    }
                }

            } else if (service.equals("delete")) {
                int feedbackID = Integer.parseInt(request.getParameter("feedbackID"));
                boolean deleted = feedbackDAO.deleteFeedback(feedbackID);
                session.setAttribute("toast", deleted ? "Feedback deleted successfully." : "Failed to delete feedback.");
                session.setAttribute("toastType", deleted ? "success" : "error");
                response.sendRedirect("FeedbackAdmin?service=listall");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet for managing feedback in admin panel";
    }
}
