/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import dal.CategoryDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;
import java.util.Vector;
import models.Categories;
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

            ProductDAO dao = new ProductDAO();
            CategoryDAO cate = new CategoryDAO();
            List<Products> list;

            if ("list".equals(service)) {
                String id = request.getParameter("categoryID");
                list = dao.getAllProduct("SELECT * FROM Products WHERE categoryID = " + id);

                CategoryDAO dao1 = new CategoryDAO();

                List<Categories> Categories = dao1.getCategoriesName();
                request.setAttribute("data", list);
                request.setAttribute("list", Categories);
                //request.getRequestDispatcher("/AdminLTE/AdminPages/test.jsp").forward(request, response);            
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/viewProduct.jsp").forward(request, response);
            } else if ("cate".equals(service)) {
                CategoryDAO dao1 = new CategoryDAO();
                List<Categories> Categories = dao1.getCategoriesName();

                request.setAttribute("list", Categories);
            } else if (service.equals("update")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int productID = Integer.parseInt(request.getParameter("productID"));
                    Products product = dao.getProductByID(productID);
                    CategoryDAO dao1 = new CategoryDAO();

                    List<Categories> Categories = dao1.getCategoriesName();

                    request.setAttribute("list", Categories);

                    request.setAttribute("product", product);
                    request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/updateProduct.jsp").forward(request, response);
                    //request.getRequestDispatcher("AdminLTE/AdminPages/test.jsp").forward(request, response);
                } else {
                    int productID = Integer.parseInt(request.getParameter("product_id"));
                    String name = request.getParameter("name");
                    String description = request.getParameter("description");
                    String brand = request.getParameter("brand");
                    double price = Double.parseDouble(request.getParameter("price"));
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    int warranty = Integer.parseInt(request.getParameter("warranty"));
                    Date createdAt = Date.valueOf(request.getParameter("created_at")); // định dạng yyyy-[m]m-[d]d
                    int categoryID = Integer.parseInt(request.getParameter("category_id"));
                    int status = Integer.parseInt(request.getParameter("status"));
                    Products p = new Products(productID, name, description, brand, price, quantity, warranty, createdAt, categoryID, status);
                    dao.updateProduct(p);
                    response.sendRedirect(request.getContextPath() + "/ProductAdmin?service=list&categoryID=" + p.getCategoryID());
                }
            } else if (service.equals("insert")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    
                    List<Categories> categories = cate.getCategoriesName();
                    request.setAttribute("list", categories);
                    request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/insertProduct.jsp").forward(request, response);
                } else {
                
                    String name = request.getParameter("name");
                    String description = request.getParameter("description");
                    String brand = request.getParameter("brand");
                    double price = Double.parseDouble(request.getParameter("price"));
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    int warranty = Integer.parseInt(request.getParameter("warranty"));
                    Date createdAt = Date.valueOf(request.getParameter("created_at"));
                    int categoryID = Integer.parseInt(request.getParameter("category_id"));
                    int status = Integer.parseInt(request.getParameter("status"));

                    
                    Products p = new Products(name, description, brand, price, quantity, warranty, createdAt, categoryID, status);
                    dao.insertProduct(p);

                   
                    response.sendRedirect(request.getContextPath() + "/ProductAdmin?service=list&categoryID=" + categoryID);
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
