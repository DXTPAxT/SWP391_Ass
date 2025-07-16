/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import dalAdmin.OrderCateAdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import models.OrderCate;
import models.OrderItems;
import models.User;

/**
 *
 * @author Admin
 */
public class OrderAdminCateServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "listCate";
            }

            OrderCateAdminDAO dao = new OrderCateAdminDAO();
            if (service.equals("listCate")) {
                List<OrderCate> orders = dao.getOrdersByStatus(7);
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderAdmin/Order/viewAllOrderCate.jsp").forward(request, response);

            } else if (service.equals("listRejected")) {
                List<OrderCate> orders = dao.getOrdersByStatus(0);
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderAdmin/Order/viewOrderCateRejected.jsp").forward(request, response);

            } else if (service.equals("listPending")) {
                List<OrderCate> orders = dao.getOrdersByStatus(1);
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderAdmin/Order/viewOrderCatePending.jsp").forward(request, response);

            } else if (service.equals("listProcessing")) {
                List<OrderCate> orders = dao.getOrdersByStatus(2);
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderAdmin/Order/viewOrderCateProcessing.jsp").forward(request, response);

            } else if (service.equals("listWaitShip")) {
                List<OrderCate> orders = dao.getOrdersByStatus(3);
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderAdmin/Order/viewOrderCateWaitShip.jsp").forward(request, response);

            } else if (service.equals("listOnShipping")) {
                List<OrderCate> orders = dao.getOrdersByStatus(4);
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderAdmin/Order/viewOrderCateOnShipping.jsp").forward(request, response);

            } else if (service.equals("listCompleted")) {
                List<OrderCate> orders = dao.getOrdersByStatus(5);
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderAdmin/Order/viewOrderCateCompleted.jsp").forward(request, response);

            } else if (service.equals("listPendingWarranty")) {
                List<OrderCate> orders = dao.getOrdersByStatus(6);
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderAdmin/Order/viewOrderCatePendingWarranty.jsp").forward(request, response);

            } else if (service.equals("listWarranty")) {
                List<OrderCate> orders = dao.getOrdersByStatus(7);
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderAdmin/Order/viewOrderCateWarranty.jsp").forward(request, response);
            } else if (service.equals("updateStatus")) {
                try {
                    int orderID = Integer.parseInt(request.getParameter("orderID"));
                    int status = Integer.parseInt(request.getParameter("status"));

                    HttpSession session = request.getSession(false);
                    User currentUser = (User) session.getAttribute("user");

                    if (currentUser == null || currentUser.getRole().getRoleID() != 2) { // 2 là Staff
                        response.sendRedirect(request.getContextPath() + "/Login");
                        return;
                    }

                    dao.updateOrderStatus(orderID, status);

                    // Nếu là nhận đơn (status = 2), lưu staff chuẩn bị
                    if (status == 2 || status == 0) {
                        dao.insertOrderPreparement(currentUser.getRole().getRoleID(), orderID);
                    }

                    response.sendRedirect("OrderAdminCate?service=listPending");

                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order update request.");
                }
            } else if (service.equals("updateStatusProcess")) {
                try {
                    int orderID = Integer.parseInt(request.getParameter("orderID"));
                    int status = Integer.parseInt(request.getParameter("status"));

                    HttpSession session = request.getSession(false);
                    User currentUser = (User) session.getAttribute("user");

                    if (currentUser == null || currentUser.getRole().getRoleID() != 2) { // 2 là Staff
                        response.sendRedirect(request.getContextPath() + "/Login");
                        return;
                    }

                    dao.updateOrderStatus(orderID, status);

                    // Nếu là nhận đơn (status = 2), lưu staff chuẩn bị
                    if (status == 2 || status == 0) {
                        dao.insertOrderPreparement(currentUser.getRole().getRoleID(), orderID);
                    }

                    response.sendRedirect("OrderAdminCate?service=listProcessing");

                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order update request.");
                }
            } else if (service.equals("updateStatusShip")) {
                try {
                    int orderID = Integer.parseInt(request.getParameter("orderID"));
                    int status = Integer.parseInt(request.getParameter("status"));

                    HttpSession session = request.getSession(false);
                    User currentUser = (User) session.getAttribute("user");

                    if (currentUser == null || currentUser.getRole().getRoleID() != 4) { // 4 là Shipper
                        response.sendRedirect(request.getContextPath() + "/Login");
                        return;
                    }

                    // Chỉ cập nhật trạng thái nếu insert shipping thành công
                    boolean success = dao.insertShipping(currentUser.getUserId(), orderID, null);
                    if (success) {
                        dao.updateOrderStatus(orderID, status);
                        response.sendRedirect("OrderAdminCate?service=listOnShipping");
                    } else {
                        request.setAttribute("error", "Không thể nhận đơn. Đã có lỗi xảy ra khi gán shipper.");
                        request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderAdmin/Order/listOnShipping.jsp").forward(request, response);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order update request.");
                }
            } else if (service.equals("updateStatusShipFinish")) {
                try {
                    int orderID = Integer.parseInt(request.getParameter("orderID"));
                    int status = Integer.parseInt(request.getParameter("status")); // ví dụ: 5 = Delivered
                    String note = request.getParameter("note"); // lấy ghi chú từ textarea

                    HttpSession session = request.getSession(false);
                    User currentUser = (User) session.getAttribute("user");

                    // Kiểm tra quyền
                    if (currentUser == null || currentUser.getRole().getRoleID() != 4) { // 4 là Shipper
                        response.sendRedirect(request.getContextPath() + "/Login");
                        return;
                    }

                    // Kiểm tra Shipper phụ trách đơn
                    boolean isCorrectShipper = dao.isShippingHandledByUser(currentUser.getUserId(), orderID);
                    if (!isCorrectShipper) {
                        request.setAttribute("error", "Bạn không có quyền hoàn thành đơn này.");
                        request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderAdmin/Order/listOnShipping.jsp").forward(request, response);
                        return;
                    }

                    // Tiến hành cập nhật trạng thái giao hàng
                    boolean success = dao.completeShipping(currentUser.getUserId(), orderID, note);

                    if (success) {
                        dao.updateOrderStatus(orderID, status);
                        response.sendRedirect("OrderAdminCate?service=listOnShipping");
                    } else {
                        request.setAttribute("error", "Cập nhật giao hàng thất bại. Vui lòng thử lại.");
                        request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderAdmin/Order/listOnShipping.jsp").forward(request, response);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order update request.");
                }
            } else if (service.equals("viewConsignee")) {
                try {
                    int orderID = Integer.parseInt(request.getParameter("orderID"));

                    // Gọi DAO để lấy chi tiết đơn hàng
                    OrderCate order = dao.getOrderByID(orderID);

                    // Lấy danh sách các đơn theo trạng thái hiện tại nếu cần giữ bảng chính
                    int currentStatus = order.getStatus(); // hoặc bạn có thể truyền từ param
                    List<OrderCate> orders = dao.getOrdersByStatus(currentStatus);

                    // Truyền dữ liệu sang JSP
                    request.setAttribute("orders", orders);
                    request.setAttribute("selectedOrder", order); // dùng để hiển thị phần bên dưới
                    request.setAttribute("orderID", orderID);

                    request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderAdmin/Order/viewOrderCateCompleted.jsp").forward(request, response);

                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID");
                }
            }else if (service.equals("viewShipping")) {
                try {
                    int orderID = Integer.parseInt(request.getParameter("orderID"));

                    // Gọi DAO để lấy chi tiết đơn hàng
                    OrderCate order = dao.getOrderByID(orderID);

                    // Lấy danh sách các đơn theo trạng thái hiện tại nếu cần giữ bảng chính
                    int currentStatus = order.getStatus(); // hoặc bạn có thể truyền từ param
                    List<OrderCate> orders = dao.getOrdersByStatus(currentStatus);

                    // Truyền dữ liệu sang JSP
                    request.setAttribute("orders", orders);
                    request.setAttribute("selectedShipping", order); // dùng để hiển thị phần bên dưới
                    request.setAttribute("orderID", orderID);

                    request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderAdmin/Order/viewOrderCateCompleted.jsp").forward(request, response);

                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID");
                }
            }

            /*else if (service.equals("update")) {
                String submit = request.getParameter("submit");

                if (submit == null) {
                    // Hiển thị form cập nhật
                    int id = Integer.parseInt(request.getParameter("orderID"));
                    OrderCate order = dao.getOrderById(id);
                    request.setAttribute("order", order);
                    request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/updateOrder.jsp").forward(request, response);

                } else {
                    // Xử lý cập nhật status
                    try {
                        int orderID = Integer.parseInt(request.getParameter("orderID"));
                        int status = Integer.parseInt(request.getParameter("newStatus"));

                        dao.updateOrderStatus(orderID, status);
                        response.sendRedirect("OrderAdmin?service=list");

                    } catch (Exception e) {
                        e.printStackTrace();

                        // Nếu có lỗi, forward lại trang cập nhật
                        int id = Integer.parseInt(request.getParameter("orderID"));
                        OrderCate order = dao.getOrderById(id);
                        request.setAttribute("error", "Update failed: " + e.getMessage());
                        request.setAttribute("order", order);
                        request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/updateOrder.jsp").forward(request, response);
                    }
                }
            }*/
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
