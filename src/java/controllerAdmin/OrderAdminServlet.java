/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import dalAdmin.OrderAdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Order;

/**
 *
 * @author Admin
 */
public class OrderAdminServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "list";
            }

            OrderAdminDAO dao = new OrderAdminDAO();
            if (service.equals("list")) {
                List<Order> orders = dao.getAllOrders();
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/viewOrder.jsp").forward(request, response);

            } else if (service.equals("update")) {
                String submit = request.getParameter("submit");

                if (submit == null) {
                    // Hiển thị form cập nhật
                    int id = Integer.parseInt(request.getParameter("orderID"));
                    Order order = dao.getOrderById(id);
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
                        Order order = dao.getOrderById(id);
                        request.setAttribute("error", "Update failed: " + e.getMessage());
                        request.setAttribute("order", order);
                        request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/updateOrder.jsp").forward(request, response);
                    }
                }
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
