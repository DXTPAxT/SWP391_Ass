/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import dal.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Categories;

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
            if (service == null) {
                service = "list";
            }
            CategoryDAO dao = new CategoryDAO();
            if (service.equals("list")) {

                List<Categories> Categories = dao.getCategoriesName();
                request.setAttribute("data", Categories);
                //request.getRequestDispatcher("/AdminLTE/AdminPages/AdminDashbord.jsp").forward(request, response);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/viewCategory.jsp").forward(request, response);
            } else if (service.equals("update")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int categoryID = Integer.parseInt(request.getParameter("categoryID"));
                    Categories category = dao.searchCategoryByID(categoryID);
                    List<Categories> Categories = dao.getCategoriesName();
                    request.setAttribute("list", Categories);
                    request.setAttribute("category", category);
                    request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/updateCategory.jsp").forward(request, response);
                } else {
                    int categoryID = Integer.parseInt(request.getParameter("category_id"));
                    String categoryName = request.getParameter("category_name");
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    int status = Integer.parseInt(request.getParameter("status"));

                    Categories category = new Categories(categoryID, categoryName, quantity, status);
                    dao.updateCategory(category);

                    response.sendRedirect(request.getContextPath() + "/CateAdmin?service=list");
                }
            } else if (service.equals("insert")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    List<Categories> Categories = dao.getCategoriesName();
                    request.setAttribute("data", Categories);
                    request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/insertCategory.jsp").forward(request, response);
                } else {

                    String name = request.getParameter("category_name");
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    int status = Integer.parseInt(request.getParameter("status"));

                    Categories category = new Categories(); // Giả sử có constructor rỗng
                    category.setCategoryName(name);
                    category.setQuantity(quantity);
                    category.setStatus(status);

                    dao.insertCategory(category);

                    response.sendRedirect(request.getContextPath() + "/CateAdmin?service=list");
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
