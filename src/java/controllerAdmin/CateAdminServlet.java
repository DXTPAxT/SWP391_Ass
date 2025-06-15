/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import dalAdmin.BraComAdminDAO;
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
import models.BraComs;
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
            cate.updateCategoryStatusIfInventoryZero();
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
            } else if (service.equals("insert")) {

                String submit = request.getParameter("submit");

                if (submit == null) {
                    BrandAdminDAO brand = new BrandAdminDAO();
                    List<Brands> brands = brand.getAllBrands();

                    ComponentAdminDAO com = new ComponentAdminDAO();
                    List<Components> components = com.getAllComponent();

                    request.setAttribute("brands", brands);
                    request.setAttribute("components", components);
                    request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/insertCate.jsp").forward(request, response);
                } else {
                    String categoryName = request.getParameter("categoryName");
                    String componentRaw = request.getParameter("componentID");
                    String brandRaw = request.getParameter("brandID");
                    String priceRaw = request.getParameter("price");
                    String description = request.getParameter("description");
                    String statusRaw = request.getParameter("status");
                    String imageURL = request.getParameter("imageURL");

                    String error = null;

                    // Validate Category Name
                    if (categoryName == null || categoryName.trim().isEmpty()) {
                        error = "Category name cannot be empty.";
                    } else if (categoryName.length() > 30) {
                        error = "Category name must not exceed 30 characters.";
                    } else if (!categoryName.matches("^[\\p{L}0-9 ]+$")) {
                        error = "Category name contains invalid characters.";
                    }
                    // Validate dropdowns
                    if (componentRaw == null || componentRaw.trim().isEmpty()) {
                        error = "Component must be selected.";
                    }
                    if (brandRaw == null || brandRaw.trim().isEmpty()) {
                        error = "Brand must be selected.";
                    }

                    // Validate price
                    if (priceRaw == null || !priceRaw.matches("^\\d+$")) {
                        error = "Price must be a positive number.";
                    }
                    if (description == null || description.trim().isEmpty()) {
                        error = "Description name cannot be empty.";
                    } else if (description.length() > 50) {
                        error = "Description name must not exceed 30 characters.";
                    }

                    // Validate status
                    if (statusRaw == null || !(statusRaw.equals("0") || statusRaw.equals("1"))) {
                        error = "Invalid status.";
                    }

                    if (error != null) {
                        // Load lại danh sách và trả về form cùng thông tin cũ
                        BrandAdminDAO brand = new BrandAdminDAO();
                        List<Brands> brands = brand.getAllBrands();
                        ComponentAdminDAO com = new ComponentAdminDAO();
                        List<Components> components = com.getAllComponent();

                        request.setAttribute("brands", brands);
                        request.setAttribute("components", components);
                        request.setAttribute("error", error);
                        request.setAttribute("categoryName", categoryName);
                        request.setAttribute("brandID", brandRaw);
                        request.setAttribute("componentID", componentRaw);
                        request.setAttribute("price", priceRaw);
                        request.setAttribute("description", description);
                        request.setAttribute("status", statusRaw);
                        request.setAttribute("imageURL", imageURL);
                        request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/insertCate.jsp").forward(request, response);
                        return;
                    }

                    try {
                        int componentID = Integer.parseInt(componentRaw);
                        int brandID = Integer.parseInt(brandRaw);
                        int price = Integer.parseInt(priceRaw);
                        int status = Integer.parseInt(statusRaw);
                        int quantity = 0;

                        int brandComID = new BraComAdminDAO().getBrandComID(brandID, componentID);
                        Categories newCate = new Categories(categoryName.trim(), brandComID, quantity, price, description, status, imageURL);
                        cate.insertCategory(newCate);

                        response.sendRedirect(request.getContextPath() + "/CateAdmin?service=listbybcid&braComID=" + brandComID);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        // Fallback nếu parse lỗi
                        error = "Invalid input format.";
                        BrandAdminDAO brand = new BrandAdminDAO();
                        List<Brands> brands = brand.getAllBrands();
                        ComponentAdminDAO com = new ComponentAdminDAO();
                        List<Components> components = com.getAllComponent();

                        request.setAttribute("brands", brands);
                        request.setAttribute("components", components);
                        request.setAttribute("error", error);
                        request.setAttribute("categoryName", categoryName);
                        request.setAttribute("brandID", brandRaw);
                        request.setAttribute("componentID", componentRaw);
                        request.setAttribute("price", priceRaw);
                        request.setAttribute("description", description);
                        request.setAttribute("status", statusRaw);
                        request.setAttribute("imageURL", imageURL);
                        request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/insertCate.jsp").forward(request, response);
                    }
                }

            } else if (service.equals("update")) {
                String submit = request.getParameter("submit");

                if (submit == null) {
                    try {
                        int categoryID = Integer.parseInt(request.getParameter("categoryID"));

                        // Lấy thông tin category đã join với ComponentName và BrandName
                        Categories cateObj = cate.getCategoryByID(categoryID);

                        // Gán thông tin để hiển thị readonly trong JSP
                        request.setAttribute("category", cateObj);
                        request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/updateCate.jsp").forward(request, response);

                    } catch (Exception e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid CategoryID");
                    }
                } else {
                    // Nhận dữ liệu cập nhật
                    String categoryIDRaw = request.getParameter("categoryID");
                    String categoryName = request.getParameter("categoryName");
                    String description = request.getParameter("description");
                    String statusRaw = request.getParameter("status");
                    String imageURL = request.getParameter("imageURL");

                    String error = null;

                    // Validate
                    if (categoryName == null || categoryName.trim().isEmpty()) {
                        error = "Category name cannot be empty.";
                    } else if (categoryName.length() > 30) {
                        error = "Category name must not exceed 30 characters.";
                    } else if (!categoryName.matches("^[\\p{L}0-9 ]+$")) {
                        error = "Category name contains invalid characters.";
                    } else if (statusRaw == null || !(statusRaw.equals("0") || statusRaw.equals("1"))) {
                        error = "Invalid status.";
                    }
                    if (description == null || description.trim().isEmpty()) {
                        error = "Description name cannot be empty.";
                    } else if (description.length() > 150) {
                        error = "Description name must not exceed 150 characters.";
                    }


                    try {
                        int categoryID = Integer.parseInt(categoryIDRaw);
                        Categories oldCate = cate.getCategoryByID(categoryID);

                        if (error != null) {
                            // Nếu có lỗi thì gửi lại form
                            request.setAttribute("error", error);
                            request.setAttribute("category", oldCate);
                            request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/updateCate.jsp").forward(request, response);
                            return;
                        }

                        // Tạo object mới với các trường được phép thay đổi
                        Categories updatedCate = new Categories(
                                categoryID,
                                categoryName.trim(),
                                oldCate.getBrandComID(), // giữ nguyên
                                oldCate.getQuantity(), // giữ nguyên
                                oldCate.getPrice(), // giữ nguyên
                                description,
                                Integer.parseInt(statusRaw),
                                imageURL
                        );

                        cate.updateCategory(updatedCate);

                        response.sendRedirect(request.getContextPath() + "/CateAdmin");

                    } catch (Exception e) {
                        e.printStackTrace();
                        request.setAttribute("error", "Update failed due to invalid input.");
                        request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/updateCate.jsp").forward(request, response);
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
