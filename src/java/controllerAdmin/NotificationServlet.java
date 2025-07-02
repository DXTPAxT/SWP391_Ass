package controllerAdmin;

import dalAdmin.NotificationAdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import models.Notification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dalAdmin.DBAdminContext;
import models.User;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name="NotificationServlet", urlPatterns={"/NotificationServlet"})
public class NotificationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String service = request.getParameter("service");
        NotificationAdminDAO dao = new NotificationAdminDAO();
        
        if (service == null) {
            service = "list";
        }
        
        switch (service) {
            case "list":
                // Hiển thị danh sách tất cả thông báo
                List<Notification> allNotifications = dao.getAllNotifications();
                request.setAttribute("notifications", allNotifications);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/notifications.jsp").forward(request, response);
                break;
                
            case "markAsRead": {
                // Đánh dấu thông báo đã đọc
                HttpSession session = request.getSession(false);
                User user = (session != null) ? (models.User) session.getAttribute("user") : null;
                int userID = (user != null) ? user.getUserId() : -1;
                if (userID > 0) {
                    String notificationID = request.getParameter("notificationID");
                    if (notificationID != null) {
                        dao.markAsRead(Integer.parseInt(notificationID));
                    }
                }
                response.sendRedirect("NotificationServlet?service=list");
                break;
            }
                
            case "markAllAsRead": {
                // Đánh dấu tất cả thông báo đã đọc
                String userIDStr = request.getParameter("userID");
                if (userIDStr != null) {
                    int userID = Integer.parseInt(userIDStr);
                    dao.markAllAsRead(userID);
                }
                response.sendRedirect("NotificationServlet?service=list");
                break;
            }
                
            case "delete": {
                // Xóa thông báo
                HttpSession session = request.getSession(false);
                User user = (session != null) ? (models.User) session.getAttribute("user") : null;
                int userID = (user != null) ? user.getUserId() : -1;
                if (userID > 0) {
                    String deleteID = request.getParameter("notificationID");
                    if (deleteID != null) {
                        dao.deleteNotification(Integer.parseInt(deleteID));
                    }
                }
                response.sendRedirect("NotificationServlet?service=list");
                break;
            }
                
            case "getUnreadCount": {
                // Lấy số lượng thông báo chưa đọc (cho AJAX)
                int adminUserID = 1; // Giả sử admin có UserID = 1
                int unreadCount = dao.getUnreadCount(adminUserID);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.print("{\"count\": " + unreadCount + "}");
                out.flush();
                break;
            }
                
            case "getUnreadNotifications": {
                // Lấy danh sách thông báo chưa đọc (cho dropdown)
                int adminID = 1; // Giả sử admin có UserID = 1
                List<Notification> unreadNotifications = dao.getUnreadNotifications(adminID);
                request.setAttribute("unreadNotifications", unreadNotifications);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/notifications.jsp").forward(request, response);
                break;
            }
                
            case "send": {
                // Gửi thông báo mới
                try {
                    int userId = Integer.parseInt(request.getParameter("userID"));
                    int senderId = Integer.parseInt(request.getParameter("senderID"));
                    String title = request.getParameter("title");
                    String message = request.getParameter("message");
                    Notification notification = new Notification();
                    notification.setUserID(userId);
                    notification.setSenderID(senderId);
                    notification.setTitle(title);
                    notification.setMessage(message);
                    notification.setIsRead(false);
                    notification.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    dao.addNotification(notification);
                    response.sendRedirect("NotificationServlet?service=list");
                } catch (Exception e) {
                    response.sendRedirect("NotificationServlet?service=list");
                }
                break;
            }
                
            case "detail": {
                // Xem chi tiết thông báo
                String id = request.getParameter("id");
                if (id != null) {
                    Notification notification = dao.getNotificationById(Integer.parseInt(id));
                    request.setAttribute("notification", notification);
                    request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/NotificationDetail.jsp").forward(request, response);
                } else {
                    response.sendRedirect("NotificationServlet?service=list");
                }
                break;
            }
                
            case "toggleRead": {
                // Chuyển đổi trạng thái đã đọc/chưa đọc
                String toggleId = request.getParameter("id");
                if (toggleId != null) {
                    int nid = Integer.parseInt(toggleId);
                    boolean currentStatus = dao.getReadStatus(nid);
                    dao.updateReadStatus(nid, !currentStatus);
                }
                response.sendRedirect("NotificationServlet?service=list");
                break;
            }
                
            case "ajaxList": {
                // Trả về JSON danh sách thông báo chưa đọc cho user (hoặc tất cả)
                int adminID = 1; // hoặc lấy từ session
                List<Notification> notifications = dao.getUnreadNotifications(adminID);
                int totalUnread = notifications.size();
                if (notifications.size() > 5) {
                    notifications = notifications.subList(0, 5);
                }
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                StringBuilder json = new StringBuilder();
                json.append("{");
                json.append("\"totalUnread\":").append(totalUnread).append(",");
                json.append("\"notifications\": [");
                for (int i = 0; i < notifications.size(); i++) {
                    Notification n = notifications.get(i);
                    json.append("{")
                        .append("\"id\":").append(n.getNotificationID()).append(",")
                        .append("\"title\":\"").append(n.getTitle().replace("\"", "\\\"")).append("\",")
                        .append("\"message\":\"").append(n.getMessage().replace("\"", "\\\"")).append("\",")
                        .append("\"createdAt\":\"").append(n.getCreatedAt()).append("\"")
                        .append("}");
                    if (i < notifications.size() - 1) json.append(",");
                }
                json.append("]}");
                out.print(json.toString());
                out.flush();
                break;
            }
                
            default:
                response.sendRedirect("NotificationServlet?service=list");
                break;
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
        return "Short description";
    }
}