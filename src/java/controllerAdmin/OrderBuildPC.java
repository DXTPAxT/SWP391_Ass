/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import dalAdmin.CategoryAdminDAO;
import dalAdmin.OrderBuildPCAdmin;
import dalAdmin.OrderCateAdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import models.BuildPCAdmin;
import models.OrderCate;
import models.User;

/**
 *
 * @author PC
 */
public class OrderBuildPC extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String service = request.getParameter("service");
        if (service == null) {
            service = "listPC";
        }
        OrderBuildPCAdmin dao = new OrderBuildPCAdmin();
        if (service.equals("listRejected")) {
            List<OrderCate> orders = dao.getOrdersByStatus(0);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderBuildPCAdmin/OrderListPC/ViewListRejectBuildPC.jsp").forward(request, response);

        } else if (service.equals("listWaitingStaff")) {
            List<OrderCate> orders = dao.getOrdersByStatus(10);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderBuildPCAdmin/OrderListPC/StaffMission.jsp").forward(request, response);

        } else if (service.equals("listWaitingConfirm")) {
            List<OrderCate> orders = dao.getOrdersByStatus(1);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderBuildPCAdmin/OrderListPC/ViewOrderBuildPCList.jsp").forward(request, response);
        } else if (service.equals("listInProcess")) {
            List<OrderCate> orders = dao.getOrdersByStatus(2);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderBuildPCAdmin/OrderListPC/ListInProcess.jsp").forward(request, response);

        } else if (service.equals("listWaitingShipping")) {
            List<OrderCate> orders = dao.getOrdersByStatus(3);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderBuildPCAdmin/OrderListPC/ListWaitingShip.jsp").forward(request, response);

        } else if (service.equals("listOnShipping")) {
            List<OrderCate> orders = dao.getOrdersByStatus(4);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderBuildPCAdmin/OrderListPC/ListOnShip.jsp").forward(request, response);

        } else if (service.equals("listCompleted")) {
            List<OrderCate> orders = dao.getOrdersByStatus(5);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderBuildPCAdmin/OrderListPC/ListComplete.jsp").forward(request, response);
        } else if (service.equals("updateStatus")) {
            CategoryAdminDAO ca = new CategoryAdminDAO();
            ca.updateCategoryInventory();
            String orderID_raw = request.getParameter("orderID");
            String status_raw = request.getParameter("status");
            String[] itemIDs_raw = request.getParameterValues("itemIds");

            // Kiểm tra itemIds
            if (itemIDs_raw == null || itemIDs_raw.length == 0) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing itemIds for processing the order.");
                return;
            }

            Set<Integer> uniqueItemIDs = new HashSet<>();
            for (String idStr : itemIDs_raw) {
                try {
                    uniqueItemIDs.add(Integer.parseInt(idStr.trim()));
                } catch (NumberFormatException e) {
                    System.err.println(" Failed to parse itemID: " + idStr);
                }
            }

            // Kiểm tra orderID và status
            if (orderID_raw == null || status_raw == null || orderID_raw.trim().isEmpty() || status_raw.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing orderID or status.");
                return;
            }

            int orderID = Integer.parseInt(orderID_raw);
            int status = Integer.parseInt(status_raw);

            HttpSession session = request.getSession(false);
            User currentUser = (User) session.getAttribute("user");

            if (currentUser == null || currentUser.getRole().getRoleID() != 1) {
                if (session != null) {
                    session.invalidate();
                }
                HttpSession newSession = request.getSession(true);
                newSession.setAttribute("error", "You do not have permission to access this task.");
                response.sendRedirect(request.getContextPath() + "/Login");
                return;
            }

            System.out.println("Request to update order status:");
            System.out.println(" Order ID: " + orderID);
            System.out.println("️ New Status: " + status);

            switch (status) {
                case 0:

                    System.out.println("Order has been rejected.");
                    dao.updateOrderStatus(orderID, status);
                    response.sendRedirect("OrderBuildPC?service=listWaitingConfirm");
                    return;

                case 3:

                    List<BuildPCAdmin> details = dao.getBuildPCItemsByOrderID(orderID);
                    boolean hasInventoryIssue = false;

                    for (BuildPCAdmin detail : details) {
                        int cateID = detail.getCateId();
                        int quantityNeeded = 1;
                        int inventory = detail.getInventory();
                        System.out.println("CategoryID: " + cateID + ", Inventory: " + inventory + ", Needed: " + quantityNeeded);

                        if (inventory < quantityNeeded) {
                            hasInventoryIssue = true;
                            System.out.println("️ Không đủ hàng cho CategoryID: " + cateID);
                            break;
                        }
                    }

                    if (hasInventoryIssue) {
                        dao.updateQueueForBuildPCOrder(orderID);
                        dao.updateOrderStatus(orderID, 2);
                        response.sendRedirect("OrderBuildPC?service=listInProcess");
                        return;
                    }

                    dao.updateOrderStatus(orderID, status);

                    for (int itemID : uniqueItemIDs) {
                        System.out.println("Assigning product for BuildPCItemID: " + itemID);
                        try {
                            dao.assignProductsToBuildPCItem(itemID);
                            for (BuildPCAdmin detail : details) {
                                int cateID = detail.getCateId();
                                dao.updateInventoryAfterAssign(cateID, 1);
                            }
                        } catch (Exception e) {
                            System.err.println("Error assigning products for ItemID = " + itemID + ": " + e.getMessage());
                            e.printStackTrace();
                        }
                    }

                    int staffID = dao.getLeastBusyStaffLastMonth();
                    if (staffID == -1) {
                        System.out.println("⚠️ No suitable staff found for assignment.");
                    } else {
                        System.out.println("📌 Assigned Staff ID = " + staffID);
                        try {
                            dao.insertOrderPreparementForBuildPC(staffID, orderID);
                        } catch (Exception e) {
                            System.err.println("❌ Error inserting OrderPreparement: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }

                    response.sendRedirect("OrderBuildPC?service=listWaitingConfirm");
                    return;

                default:
                    System.out.println("⚠️ Unknown status value: " + status);
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid status value.");
                    return;
            }
        } else if (service.equals("StaffConfirm")) {
            String orderID_raw = request.getParameter("orderID");
            String status_raw = request.getParameter("status");

            if (orderID_raw == null || status_raw == null || orderID_raw.trim().isEmpty() || status_raw.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing orderID or status");
                return;
            }

            try {
                int orderID = Integer.parseInt(orderID_raw);
                int status = Integer.parseInt(status_raw);

                HttpSession session = request.getSession(false);
                User currentUser = (User) session.getAttribute("user");

                if (currentUser == null || currentUser.getRole().getRoleID() != 2) {
                    if (session != null) {
                        session.invalidate();
                    }
                    HttpSession newSession = request.getSession(true);
                    newSession.setAttribute("error", "You do not have permission to access this task.");
                    response.sendRedirect(request.getContextPath() + "/Login");
                    return;
                }

                dao.updateOrderStatus(orderID, status);
                dao.insertOrderPreparementForBuildPC(currentUser.getUserId(), orderID); // đổi tên hàm nếu bạn dùng riêng

                response.sendRedirect("OrderBuildPC?service=listWaitingStaff");

            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID or status format");
            }
        } else if (service.equals("updateStatusShip")) {
            try {
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                int status = Integer.parseInt(request.getParameter("status"));

                HttpSession session = request.getSession(false);
                User currentUser = (User) session.getAttribute("user");

                if (currentUser == null || currentUser.getRole().getRoleID() != 4) { // 4 là Shipper
                    if (session != null) {
                        session.invalidate();
                    }
                    HttpSession newSession = request.getSession(true);
                    newSession.setAttribute("error", "You do not have permission to access this task.");
                    response.sendRedirect(request.getContextPath() + "/Login");
                    return;
                }

                // Chỉ cập nhật trạng thái nếu insert shipping thành công
                boolean success = dao.insertShipping(currentUser.getUserId(), orderID, null);
                if (success) {
                    dao.updateOrderStatus(orderID, 4);
                    response.sendRedirect("OrderBuildPC?service=listWaitingShipping");
                } else {
                    request.setAttribute("error", "Không thể nhận đơn. Đã có lỗi xảy ra khi gán shipper.");
                    request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderBuildPCAdmin/OrderDoing/OrderItemWaitShip.jsp").forward(request, response);
                }

            } catch (ServletException | IOException | NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order update request.");
            }
        } else if (service.equals("updateStatusShipFinish")) {
            try {
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                int status = Integer.parseInt(request.getParameter("status"));
                String note = request.getParameter("note");
                String[] itemIDs_raw = request.getParameterValues("itemIds");

                if (itemIDs_raw == null || itemIDs_raw.length == 0) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu danh sách item cần xử lý.");
                    return;
                }

                Set<Integer> uniqueItemIDs = new HashSet<>();
                for (String idStr : itemIDs_raw) {
                    try {
                        uniqueItemIDs.add(Integer.parseInt(idStr.trim()));
                    } catch (NumberFormatException e) {
                        System.err.println("⚠️ Không thể parse itemID: " + idStr);
                    }
                }

                HttpSession session = request.getSession(false);
                User currentUser = (User) session.getAttribute("user");

                if (currentUser == null || currentUser.getRole().getRoleID() != 4) {
                    if (session != null) {
                        session.invalidate();
                    }
                    HttpSession newSession = request.getSession(true);
                    newSession.setAttribute("error", "Bạn không có quyền truy cập chức năng này.");
                    response.sendRedirect(request.getContextPath() + "/Login");
                    return;
                }

                boolean isCorrectShipper = dao.isShippingHandledByUser(currentUser.getUserId(), orderID);
                if (!isCorrectShipper) {
                    request.setAttribute("error", "Bạn không có quyền hoàn thành đơn này.");
                    request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderBuildPCAdmin/OrderDoing/ListItermOnGoing.jsp").forward(request, response);
                    return;
                }

                boolean success = dao.completeShipping(currentUser.getUserId(), orderID, note);

                if (success) {
                    dao.updateOrderStatus(orderID, status);

                    for (int itemID : uniqueItemIDs) {
                        dao.updateBuildPCProductsWarrantyDates(itemID);
                    }
                    response.sendRedirect("OrderBuildPC?service=listOnShipping");
                } else {
                    request.setAttribute("error", "❌ Cập nhật giao hàng thất bại. Vui lòng thử lại.");
                    request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderBuildPCAdmin/OrderDoing/ListItermOnGoing.jsp").forward(request, response);
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "❌ Dữ liệu không hợp lệ.");
            }
        } else if (service.equals("viewConsignee")) {
            try {
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                OrderCate order = dao.getOrderByID(orderID);

                int currentStatus = order.getStatus();
                List<OrderCate> orders = dao.getOrdersByStatus(currentStatus);

                request.setAttribute("orders", orders);
                request.setAttribute("selectedOrder", order);

                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderBuildPCAdmin/OrderListPC/ListComplete.jsp").forward(request, response);

            } catch (ServletException | IOException | NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID");
            }

        } else if (service.equals("viewShipping")) {
            try {
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                OrderCate order = dao.getOrderByID(orderID);
                int currentStatus = order.getStatus(); // hoặc bạn có thể truyền từ param
                List<OrderCate> orders = dao.getOrdersByStatus(currentStatus);

                request.setAttribute("orders", orders);
                request.setAttribute("selectedShipping", order); // dùng để hiển thị phần bên dưới
                request.setAttribute("orderID", orderID);

                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderBuildPCAdmin/OrderListPC/ListComplete.jsp").forward(request, response);

            } catch (ServletException | IOException | NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID");
            }
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
