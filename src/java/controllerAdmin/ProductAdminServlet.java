/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import dalAdmin.ProductAdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;
import java.util.Vector;
import models.Products;

/**
 *
 * @author Admin
 */
public class ProductAdminServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            ProductAdminDAO pro = new ProductAdminDAO();
            List<Products> product;
            if (service == null) {
                service = "list";
            }
            if ("list".equals(service)) {
                product = pro.getAllProducts();
                request.setAttribute("product", product);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/viewProduct.jsp").forward(request, response);
            } else if ("listbycate".equals(service)) {
                int id = Integer.parseInt(request.getParameter("categoryID"));

                product = pro.getAllProductsByCategoryID(id);
                request.setAttribute("product", product);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/viewProduct.jsp").forward(request, response);
            } else if ("listbyim".equals(service)) {
                int id = Integer.parseInt(request.getParameter("ImportID"));

                product = pro.getAllProductsByImportID(id);
                request.setAttribute("product", product);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/viewProduct.jsp").forward(request, response);
            } else if (service.equals("toggleStatus")) {
                int productID = Integer.parseInt(request.getParameter("productID"));
                ProductAdminDAO dao = new ProductAdminDAO();
                dao.toggleStatus(productID);
                response.sendRedirect("ProductAdmin");
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
