/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;


import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.Vector;
import models.Products;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/Product"})
public class ProductServlet extends HttpServlet {

    private static final String SQL = "SELECT * FROM Products";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ProductDAO dao = new ProductDAO();
       
        String service = request.getParameter("service");
        if (service == null) {
            service = "listProduct";
        }
       
        if (service.equals("listProduct")) {
            String submit = request.getParameter("submit");
            String productName = request.getParameter("productName");
            //Call models
            Vector<Products> list;
            if (submit == null) {
                list = dao.getAllProduct(SQL);
            } else {
                list = dao.getAllProduct("SELECT *\n"
                        + "FROM Products");
            }
            //set data for view
            request.setAttribute("data", list);
            request.setAttribute("pageTitle", "Product Manager");
            request.setAttribute("tableTitle", "List of Product");

            //Select view
            request.getRequestDispatcher("ShopPages/Pages/ProductList.jsp").forward(request, response);
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
