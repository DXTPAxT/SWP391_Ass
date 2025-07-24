/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import dalAdmin.OrderBuildPCAdmin;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.BuildPCAdmin;
import models.OrderItems;

/**
 *
 * @author PC
 */
public class OrderBuildPCDetails extends HttpServlet {

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
        OrderBuildPCAdmin dao = new OrderBuildPCAdmin();
        if (service.equals("listPending")) {
            try {
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                List<BuildPCAdmin> items = dao.getBuildPCItemsByOrderID(orderID);
                System.out.println("So luong item" + items.size());
                request.setAttribute("items", items);
                request.setAttribute("orderID", orderID);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderBuildPCAdmin/OrderDoing/OrderPCDetail.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID");
            }
        } else if (service.equals("StaffMission")) {
            try {
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                List<BuildPCAdmin> items = dao.getBuildPCItemsByOrderID(orderID);

                request.setAttribute("items", items);
                request.setAttribute("orderID", orderID);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderBuildPCAdmin/OrderDoing/StaffMissionConfirm.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID");
            }
        } else if (service.equals("InProcessing")) {
            try {
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                List<BuildPCAdmin> items = dao.getBuildPCItemsByOrderID(orderID);

                request.setAttribute("items", items);
                request.setAttribute("orderID", orderID);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderBuildPCAdmin/OrderDoing/OrderPCDetail.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID");
            }
        } else if (service.equals("listItemWaitShip")) {
            try {
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                List<BuildPCAdmin> items = dao.getBuildPCItemsByOrderID(orderID);
                System.out.println("So luong item" + items.size());
                request.setAttribute("items", items);
                request.setAttribute("orderID", orderID);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderBuildPCAdmin/OrderDoing/OrderItemWaitShip.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID");
            }
        } else if (service.equals("viewProductsWait")) {
            try {
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                int orderBuildPCitemid = Integer.parseInt(request.getParameter("orderItemID"));
                List<BuildPCAdmin> items = dao.getBuildPCItemsByOrderID(orderID);
                System.out.println("So luong item" + items.size());
                List<BuildPCAdmin> products = dao.getBuildPCProductsByOrderBuildPCItemID(orderBuildPCitemid);
                System.out.println("So luong item" + products.size());
                request.setAttribute("items", items);
                request.setAttribute("products", products);
                request.setAttribute("orderID", orderID);
                request.setAttribute("selectedOrderItemID", orderBuildPCitemid);

                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderBuildPCAdmin/OrderDoing/OrderItemWaitShip.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID");
            }
        } else if (service.equals("listItemOnShip")) {
            try {
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                List<BuildPCAdmin> items = dao.getBuildPCItemsByOrderID(orderID);
                System.out.println("So luong item" + items.size());
                request.setAttribute("items", items);
                request.setAttribute("orderID", orderID);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderBuildPCAdmin/OrderDoing/ListItermOnGoing.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID");
            }
        }else if (service.equals("listItemComplete")) {
            try {
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                List<BuildPCAdmin> items = dao.getBuildPCItemsByOrderID(orderID);
                System.out.println("So luong item" + items.size());
                request.setAttribute("items", items);
                request.setAttribute("orderID", orderID);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/OrderBuildPCAdmin/OrderDoing/OrderItemCompelete.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
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
