/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import dalAdmin.BrandAdminDAO;
import dalAdmin.CategoryAdminDAO;
import dalAdmin.ComponentAdminDAO;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;
import models.Brands;
import models.Categories;
import models.Components;
import models.Products;

/**
 *
 * @author Admin
 */
public class CateAdminServlet extends HttpServlet {

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
           
            CategoryAdminDAO cate = new CategoryAdminDAO();
            List<Categories> list;
            cate.updateCategoryQuantities();
            cate.updateCategoryInventory();
            if (service == null) {
                service = "list";
            }
            if ("list".equals(service)) {
                
                list = cate.getAllCategories();

                request.setAttribute("list", list);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/viewCate.jsp").forward(request, response);
            } else if ("listbybcid".equals(service)) {
                int id = Integer.parseInt(request.getParameter("braComID"));       
                   
                list = cate.getAllCategoriesByBrandComID(id);
                
                request.setAttribute("list", list);
                //request.getRequestDispatcher("/AdminLTE/AdminPages/test.jsp").forward(request, response);            
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/viewCate.jsp").forward(request, response);
            } else if (service.equals("update")) {

                String submit = request.getParameter("submit");
                if (submit == null) {

                    int categoryID = Integer.parseInt(request.getParameter("categoryID"));
                    Categories category = cate.getCategoryByID(categoryID);
                  
                    request.setAttribute("category", category);

                    request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/updateCate.jsp").forward(request, response);
                } else {
                    int categoryID = Integer.parseInt(request.getParameter("categoryID"));
                    String categoryName = request.getParameter("categoryName");
                    int componentID = Integer.parseInt(request.getParameter("componentID"));
                    int brandID = Integer.parseInt(request.getParameter("brandID"));
                    int quantity = 0;
                    int price = Integer.parseInt(request.getParameter("price"));
                    String description = request.getParameter("description");
                    int status = Integer.parseInt(request.getParameter("status"));

                    response.sendRedirect(request.getContextPath() + "/CateAdmin?service=listbycom&componentID=" + componentID);
                }

            } else if (service.equals("insert")) {

                String submit = request.getParameter("submit");

                if (submit == null) {
                    BrandAdminDAO brand = new BrandAdminDAO();
                    List<Brands> brands = brand.getAllBrands();


                    request.setAttribute("brands", brands);
                    request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/insertCate.jsp").forward(request, response);
                } else {
                    String categoryName = request.getParameter("categoryName");
                    int componentID = Integer.parseInt(request.getParameter("componentID"));
                    int brandID = Integer.parseInt(request.getParameter("brandID"));
                    int quantity = 0;
                    int price = Integer.parseInt(request.getParameter("price"));
                    String description = request.getParameter("description");
                    int status = Integer.parseInt(request.getParameter("status"));

                 
                    response.sendRedirect(request.getContextPath() + "/CateAdmin?service=listbycom&componentID=" + componentID);
                }
            } else if (service.equals("changestatus")) {
                int categoryID = Integer.parseInt(request.getParameter("categoryID"));

                Categories category = cate.getCategoryByID(categoryID);

                if (category != null) {
                    int newStatus = (category.getStatus() == 0) ? 1 : 0;
                    cate.updateStatus(categoryID, newStatus);
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
