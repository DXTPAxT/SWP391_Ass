package controller;

import dal.FeedbackDAO;
import jakarta.servlet.RequestDispatcher;
import models.Feedback;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/feedback")
public class FeedbackServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(FeedbackServlet.class.getName());
    private FeedbackDAO dao;

    @Override
    public void init() {
        dao = new FeedbackDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        LOGGER.info("Processing GET action: " + action);
        try {
            if (action == null || action.equals("list")) {
                List<Feedback> feedbackList = dao.getAllFeedbacks();
                request.setAttribute("feedbackList", feedbackList);
                LOGGER.info("Forwarding to /ShopPages/Pages/feedback.jsp for list action");
                RequestDispatcher rs = request.getRequestDispatcher("/ShopPages/Pages/feedback.jsp");
                rs.forward(request, response);
            } else if (action.equals("product")) {
                String productIdParam = request.getParameter("productID");
                if (productIdParam == null || productIdParam.trim().isEmpty()) {
                    request.getSession().setAttribute("error", "Thiếu ID sản phẩm");
                    LOGGER.warning("Missing productID, redirecting to /Home");
                    response.sendRedirect(request.getContextPath() + "/Home");
                    return;
                }
                int productId;
                try {
                    productId = Integer.parseInt(productIdParam);
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("error", "ID sản phẩm không hợp lệ");
                    LOGGER.warning("Invalid productID format: " + productIdParam);
                    response.sendRedirect(request.getContextPath() + "/Home");
                    return;
                }
                if (!dao.isValidProductId(productId)) {
                    request.getSession().setAttribute("error", "ID sản phẩm không hợp lệ");
                    LOGGER.warning("Invalid productID: " + productId);
                    response.sendRedirect(request.getContextPath() + "/Home");
                    return;
                }
                List<Feedback> productFeedback = dao.getFeedbackByProductId(productId);
                request.setAttribute("feedbackList", productFeedback);
                request.setAttribute("productID", productId);
                LOGGER.info("Forwarding to /ShopPages/Pages/feedback.jsp for product action, productID: " + productId);
                RequestDispatcher rs = request.getRequestDispatcher("/ShopPages/Pages/feedback.jsp");
                rs.forward(request, response);
            } else if (action.equals("edit")) {
                String idParam = request.getParameter("id");
                String productIdParam = request.getParameter("productID");
                if (idParam == null || productIdParam == null || idParam.trim().isEmpty() || productIdParam.trim().isEmpty()) {
                    request.getSession().setAttribute("error", "Thiếu thông tin hoặc sản phẩm");
                    LOGGER.warning("Missing id or productID for edit action");
                    response.sendRedirect(request.getContextPath() + "/feedback?action=list");
                    return;
                }
                int id;
                int productId;
                try {
                    id = Integer.parseInt(idParam);
                    productId = Integer.parseInt(productIdParam);
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("error", "ID feedback hoặc sản phẩm không hợp lệ");
                    LOGGER.warning("Invalid id or productID format: id=" + idParam + ", productID=" + productIdParam);
                    response.sendRedirect(request.getContextPath() + "/feedback?action=list");
                    return;
                }
                Feedback feedback = dao.getFeedbackById(id);
                if (feedback == null) {
                    request.getSession().setAttribute("error", "Không tìm thấy feedback");
                    LOGGER.warning("Feedback not found: id=" + id);
                    response.sendRedirect(request.getContextPath() + "/feedback?action=product&productID=" + productId);
                    return;
                }
                HttpSession session = request.getSession();
                models.User user = (models.User) session.getAttribute("user");
                if (user == null || (user.getUserID() != feedback.getUserID() && user.getRoleID() != 1)) {
                    session.setAttribute("error", "Bạn không có quyền sửa feedback này");
                    LOGGER.warning("Unauthorized edit attempt: userID=" + (user != null ? user.getUserID() : "null") + ", feedbackID=" + id);
                    response.sendRedirect(request.getContextPath() + "/feedback?action=product&productID=" + productId);
                    return;
                }
                request.setAttribute("feedback", feedback);
                request.setAttribute("productID", productId);
                LOGGER.info("Forwarding to /ShopPages/Pages/edit-feedback.jsp for edit action");
                RequestDispatcher rs = request.getRequestDispatcher("/ShopPages/Pages/edit-feedback.jsp");
                rs.forward(request, response);
            } else if (action.equals("delete")) {
                String idParam = request.getParameter("id");
                String productIdParam = request.getParameter("productID");
                if (idParam == null || productIdParam == null || idParam.trim().isEmpty() || productIdParam.trim().isEmpty()) {
                    request.getSession().setAttribute("error", "Thiếu thông tin feedback hoặc sản phẩm");
                    LOGGER.warning("Missing id or productID for delete action");
                    response.sendRedirect(request.getContextPath() + "/feedback?action=list");
                    return;
                }
                int id;
                int productId;
                try {
                    id = Integer.parseInt(idParam);
                    productId = Integer.parseInt(productIdParam);
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("error", "ID feedback hoặc sản phẩm không hợp lệ");
                    LOGGER.warning("Invalid id or productID format: id=" + idParam + ", productID=" + productIdParam);
                    response.sendRedirect(request.getContextPath() + "/feedback?action=list");
                    return;
                }
                Feedback feedback = dao.getFeedbackById(id);
                if (feedback == null) {
                    request.getSession().setAttribute("error", "Không tìm thấy feedback");
                    LOGGER.warning("Feedback not found: id=" + id);
                    response.sendRedirect(request.getContextPath() + "/feedback?action=product&productID=" + productId);
                    return;
                }
                HttpSession session = request.getSession();
                models.User user = (models.User) session.getAttribute("user");
                if (user == null || (user.getUserID() != feedback.getUserID() && user.getRoleID() != 1)) {
                    session.setAttribute("error", "Bạn không có quyền xóa feedback này");
                    LOGGER.warning("Unauthorized delete attempt: userID=" + (user != null ? user.getUserID() : "null") + ", feedbackID=" + id);
                    response.sendRedirect(request.getContextPath() + "/feedback?action=product&productID=" + productId);
                    return;
                }
                boolean success = dao.deleteFeedback(id);
                if (success) {
                    request.getSession().setAttribute("message", "Xóa feedback thành công");
                    LOGGER.info("Feedback deleted successfully: id=" + id);
                } else {
                    request.getSession().setAttribute("error", "Không thể xóa feedback");
                    LOGGER.severe("Failed to delete feedback: id=" + id);
                }
                response.sendRedirect(request.getContextPath() + "/feedback?action=product&productID=" + productId);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Hành động không hợp lệ");
                LOGGER.warning("Invalid action: " + action);
            }
        } catch (Exception e) {
            LOGGER.severe("Server error: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi server");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            LOGGER.info("Processing POST action: " + action);
            if ("update".equals(action)) {
                String feedbackIdParam = request.getParameter("feedbackId");
                String productIdParam = request.getParameter("productId");
                String content = request.getParameter("content");
                String ratingParam = request.getParameter("rating");
                String statusParam = request.getParameter("status");

                if (feedbackIdParam == null || productIdParam == null || content == null || ratingParam == null || statusParam == null
                        || feedbackIdParam.trim().isEmpty() || productIdParam.trim().isEmpty() || content.trim().isEmpty()) {
                    request.getSession().setAttribute("error", "Vui lòng điền đầy đủ thông tin");
                    LOGGER.warning("Missing required parameters for update action");
                    response.sendRedirect(request.getContextPath() + "/feedback?action=edit&id=" + feedbackIdParam + "&productID=" + productIdParam);
                    return;
                }

                int feedbackId;
                int productId;
                int rate;
                int status;
                try {
                    feedbackId = Integer.parseInt(feedbackIdParam);
                    productId = Integer.parseInt(productIdParam);
                    rate = Integer.parseInt(ratingParam);
                    status = Integer.parseInt(statusParam);
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("error", "Dữ liệu đầu vào không hợp lệ");
                    LOGGER.warning("Invalid parameter format: feedbackId=" + feedbackIdParam + ", productId=" + productIdParam);
                    response.sendRedirect(request.getContextPath() + "/feedback?action=edit&id=" + feedbackIdParam + "&productID=" + productIdParam);
                    return;
                }

                if (rate < 1 || rate > 5) {
                    request.getSession().setAttribute("error", "Đánh giá phải từ 1 đến 5 sao");
                    LOGGER.warning("Invalid rating: " + rate);
                    response.sendRedirect(request.getContextPath() + "/feedback?action=edit&id=" + feedbackId + "&productID=" + productId);
                    return;
                }
                if (status != 0 && status != 1) {
                    request.getSession().setAttribute("error", "Trạng thái không hợp lệ");
                    LOGGER.warning("Invalid status: " + status);
                    response.sendRedirect(request.getContextPath() + "/feedback?action=edit&id=" + feedbackId + "&productID=" + productId);
                    return;
                }

                Feedback feedback = dao.getFeedbackById(feedbackId);
                if (feedback == null) {
                    request.getSession().setAttribute("error", "Không tìm thấy feedback");
                    LOGGER.warning("Feedback not found: id=" + feedbackId);
                    response.sendRedirect(request.getContextPath() + "/feedback?action=product&productID=" + productId);
                    return;
                }

                HttpSession session = request.getSession();
                models.User user = (models.User) session.getAttribute("user");
                if (user == null || (user.getUserID() != feedback.getUserID() && user.getRoleID() != 1)) {
                    session.setAttribute("error", "Bạn không có quyền cập nhật feedback này");
                    LOGGER.warning("Unauthorized update attempt: userID=" + (user != null ? user.getUserID() : "null") + ", feedbackID=" + feedbackId);
                    response.sendRedirect(request.getContextPath() + "/feedback?action=product&productID=" + productId);
                    return;
                }

                feedback.setContent(content);
                feedback.setRate(rate);
                feedback.setStatus(status);
                boolean success = dao.updateFeedback(feedback);
                if (success) {
                    request.getSession().setAttribute("message", "Cập nhật feedback thành công");
                    LOGGER.info("Feedback updated successfully: id=" + feedbackId);
                } else {
                    request.getSession().setAttribute("error", "Không thể cập nhật feedback");
                    LOGGER.severe("Failed to update feedback: id=" + feedbackId);
                }
                response.sendRedirect(request.getContextPath() + "/feedback?action=product&productID=" + productId);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Hành động không hợp lệ");
                LOGGER.warning("Invalid POST action: " + action);
            }
        } catch (Exception e) {
            LOGGER.severe("Server error in POST: " + e.getMessage());
            e.printStackTrace();
            request.getSession().setAttribute("error", "Lỗi server khi cập nhật feedback");
            response.sendRedirect(request.getContextPath() + "/feedback?action=list");
        }
    }
}
