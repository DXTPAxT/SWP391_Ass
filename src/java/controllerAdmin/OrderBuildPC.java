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
import java.util.List;
import models.OrderCate;
import models.User;

/**
 *
 * @author PC
 */
public class OrderBuildPC extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
        else if (service.equals("updateStatus")) {
          try {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        int status = Integer.parseInt(request.getParameter("status"));

        HttpSession session = request.getSession(false);
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null || currentUser.getRole().getRoleID() != 1) { // 1 là Admin
            response.sendRedirect(request.getContextPath() + "/Logout");
            return;
        }

        
        dao.updateOrderStatus(orderID, status);

        response.sendRedirect("OrderBuildPCDetails?service=listPending");

    } catch (IOException | NumberFormatException e) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order update request.");
    }
}

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
