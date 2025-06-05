/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import dal.CategoriesDAO;
import dal.CategoryAdminDAO;
import dal.ComponentDAO;
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
import models.Components;
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

            CategoryAdminDAO cate = new CategoryAdminDAO();
            ComponentDAO dao = new ComponentDAO();
            ProductDAO pro = new ProductDAO();
            pro.syncProductNameWithCategoryName();

            List<Products> product;
            List<Categories> list = cate.getAllCategories("SELECT * FROM Categories");
            List<Components> components = dao.getAllComponent("SELECT * FROM Components");
            if (service == null) {
                service = "list";
            }
            if ("list".equals(service)) {
                String keyword = request.getParameter("keyword");
                if (keyword != null && !keyword.trim().isEmpty()) {
                    product = pro.getAllProduct("SELECT * FROM Products WHERE ProductName LIKE '%" + keyword + "%'");
                } else {
                    product = pro.getAllProduct("SELECT * FROM Products ");
                }

                request.setAttribute("product", product);
                request.setAttribute("data", components);
                request.setAttribute("list", list);

                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/viewProduct.jsp").forward(request, response);
            } else if (service.equals("update")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int productID = Integer.parseInt(request.getParameter("productID"));
                    Products products = pro.getProductByID(productID);

                    request.setAttribute("product", products);
                    request.setAttribute("data", components);
                    request.setAttribute("list", list);

                    request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/updateProduct.jsp").forward(request, response);
                } else {
                    int productID = Integer.parseInt(request.getParameter("productID"));
                    String name = request.getParameter("name");
                    String productCode = request.getParameter("productCode");
                    Date createdAt = Date.valueOf(request.getParameter("createdAt")); 
                    int categoryID = Integer.parseInt(request.getParameter("categoryID"));
                    int status = Integer.parseInt(request.getParameter("status"));

                    Products updatedProduct = new Products(productID, name, createdAt, categoryID, productCode, status);
                    pro.updateProduct(updatedProduct);

                    response.sendRedirect(request.getContextPath() + "/ProductAdmin?service=list");
                }
            } else if (service.equals("insert")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.setAttribute("data", components);
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/insertProduct.jsp").forward(request, response);
                } else {
                    // Xử lý thêm sản phẩm mới
                    String name = request.getParameter("name");
                    String productCode = request.getParameter("productCode");
                    Date createdAt = Date.valueOf(java.time.LocalDate.now());
                    int categoryID = Integer.parseInt(request.getParameter("categoryID"));
                    int status = Integer.parseInt(request.getParameter("status"));

                    // Tạo đối tượng Products và insert
                    Products p = new Products(name, createdAt, categoryID, productCode, status);
                    pro.insertProduct(p);

                    // Redirect sau khi thêm
                    response.sendRedirect(request.getContextPath() + "/ProductAdmin?service=list");
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
