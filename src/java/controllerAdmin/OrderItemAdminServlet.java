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
import java.util.List;
import models.OrderItems;
import models.Products;

/**
 *
 * @author Admin
 */
public class OrderItemAdminServlet extends HttpServlet {

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
        String service = request.getParameter("service");

        OrderCateAdminDAO dao = new OrderCateAdminDAO();
        if (service.equals("listPending")) {
            try {
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                List<OrderItems> items = dao.getAllOrderCateItemsByOrderID(orderID);

                request.setAttribute("items", items);
                request.setAttribute("orderID", orderID); // nếu cần truyền về JSP
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderAdmin/OrderItem/viewOrderItemPending.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID");
            }
        } else if (service.equals("listProcess")) {
            try {
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                List<OrderItems> items = dao.getAllOrderCateItemsByOrderID(orderID);

                request.setAttribute("items", items);
                request.setAttribute("orderID", orderID); // nếu cần truyền về JSP
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderAdmin/OrderItem/viewOrderItemProcess.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID");
            }
        } else if (service.equals("insertProduct")) {
            int orderItemID = Integer.parseInt(request.getParameter("orderItemID"));
            dao.fillProductsForOrderItem(orderItemID);

            int orderID = Integer.parseInt(request.getParameter("orderID"));
            response.sendRedirect("OrderItemAdmin?service=listProcess&orderID=" + orderID);
        } else if (service.equals("viewProducts")) {
            int orderItemID = Integer.parseInt(request.getParameter("orderItemID"));
            int orderID = Integer.parseInt(request.getParameter("orderID")); // cần truyền thêm từ form

            List<Products> products = dao.getProductsByOrderItemID(orderItemID);
            List<OrderItems> items = dao.getAllOrderCateItemsByOrderID(orderID);

            request.setAttribute("products", products);
            request.setAttribute("items", items);
            request.setAttribute("selectedOrderItemID", orderItemID); // dùng cho so sánh hiển thị
            request.setAttribute("orderID", orderID); // để hiển thị nút Receive / Reject

            request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderAdmin/OrderItem/viewOrderItemProcess.jsp").forward(request, response);
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
