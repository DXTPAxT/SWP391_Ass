/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

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
        }
        if (service.equals("updateStatus")) {
            String orderID_raw = request.getParameter("orderID");
            String status_raw = request.getParameter("status");
            String[] itemIDs_raw = request.getParameterValues("itemIds");

            // Check if itemIds are missing
            if (itemIDs_raw == null || itemIDs_raw.length == 0) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing itemIds for processing the order.");
                return;
            }

            // ✅ Use Set to avoid duplicates
            Set<Integer> uniqueItemIDs = new HashSet<>();
            for (String idStr : itemIDs_raw) {
                try {
                    uniqueItemIDs.add(Integer.parseInt(idStr.trim()));
                } catch (NumberFormatException e) {
                    System.err.println("⚠️ Failed to parse itemID: " + idStr);
                }
            }

            // Check for missing orderID or status
            if (orderID_raw == null || status_raw == null || orderID_raw.trim().isEmpty() || status_raw.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing orderID or status.");
                return;
            }

            int orderID = Integer.parseInt(orderID_raw);
            int status = Integer.parseInt(status_raw);

            HttpSession session = request.getSession(false);
            User currentUser = (User) session.getAttribute("user");

            if (currentUser == null || currentUser.getRole().getRoleID() != 1) {
                response.sendRedirect(request.getContextPath() + "/Logout");
                return;
            }

            System.out.println("📥 Request to update order status:");
            System.out.println("➡️ Order ID: " + orderID);
            System.out.println("➡️ New Status: " + status);

            switch (status) {
                case 0:
                    System.out.println("❌ Order has been rejected.");
                    dao.updateOrderStatus(orderID, status);
                    response.sendRedirect("OrderBuildPC?service=listWaitingConfirm");
                    return;

                case 3:
                    System.out.println("✅ Order accepted. Processing...");
                    dao.updateOrderStatus(orderID, status);

                    for (int itemID : uniqueItemIDs) {
                        System.out.println("🔧 Assigning products for BuildPCItemID: " + itemID);
                        try {
                            dao.assignProductsToBuildPCItem(itemID);
                        } catch (Exception e) {
                            System.err.println("❌ Error assigning products for ItemID = " + itemID + ": " + e.getMessage());
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
                    response.sendRedirect(request.getContextPath() + "/Logout");
                    return;
                }

                dao.updateOrderStatus(orderID, status);
                dao.insertOrderPreparementForBuildPC(currentUser.getUserId(), orderID); // đổi tên hàm nếu bạn dùng riêng

                response.sendRedirect("OrderBuildPC?service=listWaitingStaff");

            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID or status format");
            }
        } else if (service.equals("waitingShip")) {

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
