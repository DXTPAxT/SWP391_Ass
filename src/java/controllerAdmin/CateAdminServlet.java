/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import dalAdmin.BrandComAdminDAO;
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
import java.util.ArrayList;
import java.util.List;
import models.BrandComs;
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
                int i = list.size();
                request.setAttribute("list", list);
                request.setAttribute("i", i);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/viewCate.jsp").forward(request, response);
            } else if ("listbybcid".equals(service)) {
                int id = Integer.parseInt(request.getParameter("brandComID"));

                list = cate.getAllCategoriesByBrandComID(id);

                request.setAttribute("list", list);
                //request.getRequestDispatcher("/AdminLTE/AdminPages/test.jsp").forward(request, response);            
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/viewCate.jsp").forward(request, response);
            } else if (service.equals("insert")) {

                String submit = request.getParameter("submit");

                if (submit == null) {
                    BrandAdminDAO brandDAO = new BrandAdminDAO();
                    List<Brands> brands = brandDAO.getAllBrands();

                    ComponentAdminDAO componentDAO = new ComponentAdminDAO();
                    List<Components> components = componentDAO.getAllComponent();

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

                    List<String> errors = new ArrayList<>();

                    // Validate category name
                    if (categoryName == null || categoryName.trim().isEmpty()) {
                        errors.add("Category name cannot be empty.");
                    } else if (categoryName.length() > 100) {
                        errors.add("Category name must not exceed 100 characters.");
                    } 

                    // Validate componentID
                    if (componentRaw == null || componentRaw.trim().isEmpty()) {
                        errors.add("Component must be selected.");
                    }

                    // Validate brandID
                    if (brandRaw == null || brandRaw.trim().isEmpty()) {
                        errors.add("Brand must be selected.");
                    }

                    // Validate price
                    if (priceRaw == null || !priceRaw.matches("^\\d{6,10}$")) {
                        errors.add("Price must be a number from 6 to 10 digits.");
                    }

                    // Validate description
                    if (description == null || description.trim().isEmpty()) {
                        errors.add("Description cannot be empty.");
                    } else if (description.length() > 150) {
                        errors.add("Description must not exceed 150 characters.");
                    } 

                 

                    if (!errors.isEmpty()) {
                        // Load danh sách lại nếu có lỗi
                        BrandAdminDAO brandDAO = new BrandAdminDAO();
                        ComponentAdminDAO componentDAO = new ComponentAdminDAO();
                        List<Brands> brands = brandDAO.getAllBrands();
                        List<Components> components = componentDAO.getAllComponent();

                        request.setAttribute("brands", brands);
                        request.setAttribute("components", components);
                        request.setAttribute("errors", errors);

                        // Giữ lại dữ liệu đã nhập
                        request.setAttribute("categoryName", categoryName);
                        request.setAttribute("componentID", componentRaw);
                        request.setAttribute("brandID", brandRaw);
                        request.setAttribute("price", priceRaw);
                        request.setAttribute("description", description);
                        request.setAttribute("status", statusRaw);
                        request.setAttribute("imageURL", imageURL);

                        request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/insertCate.jsp").forward(request, response);
                        return;
                    }

                    // Nếu không có lỗi → tiến hành insert
                    try {
                        int componentID = Integer.parseInt(componentRaw);
                        int brandID = Integer.parseInt(brandRaw);
                        int price = Integer.parseInt(priceRaw);
                        int status = Integer.parseInt(statusRaw);
                        int quantity = 0;

                        int brandComID = new BrandComAdminDAO().getBrandComID(brandID, componentID);
                        Categories newCate = new Categories(categoryName.trim(), brandComID, quantity, price, description.trim(), status, imageURL);
                        cate.insertCategory(newCate);

                        response.sendRedirect(request.getContextPath() + "/CateAdmin?service=listbybcid&braComID=" + brandComID);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        List<String> parseErrors = new ArrayList<>();
                        parseErrors.add("Invalid input format. Please check all fields again.");

                        BrandAdminDAO brandDAO = new BrandAdminDAO();
                        ComponentAdminDAO componentDAO = new ComponentAdminDAO();
                        List<Brands> brands = brandDAO.getAllBrands();
                        List<Components> components = componentDAO.getAllComponent();

                        request.setAttribute("brands", brands);
                        request.setAttribute("components", components);
                        request.setAttribute("errors", parseErrors);

                        // Giữ lại dữ liệu đã nhập
                        request.setAttribute("categoryName", categoryName);
                        request.setAttribute("componentID", componentRaw);
                        request.setAttribute("brandID", brandRaw);
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
                        Categories cateObj = cate.getCategoryByID(categoryID);
                        String brandName = cateObj.getBrandName();
                        request.setAttribute("category", cateObj);
                        request.setAttribute("bn", brandName);
                        request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/updateCate.jsp").forward(request, response);
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid CategoryID");
                    }

                } else {
                    String categoryIDRaw = request.getParameter("categoryID");
                    String categoryName = request.getParameter("categoryName");
                    String description = request.getParameter("description");
                    String statusRaw = request.getParameter("status");
                    String imageURL = request.getParameter("imageURL");

                    List<String> errors = new ArrayList<>();
                    int categoryID = -1;

                    // Parse trước để xử lý khi lỗi parse
                    try {
                        categoryID = Integer.parseInt(categoryIDRaw);
                    } catch (NumberFormatException e) {
                        errors.add("Invalid category ID format.");
                    }

                    // Validate category name
                    if (categoryName == null || categoryName.trim().isEmpty()) {
                        errors.add("Category name cannot be empty.");
                    } else if (categoryName.length() > 100) {
                        errors.add("Category name must not exceed 100 characters.");
                    } 

                    // Validate description
                    if (description == null || description.trim().isEmpty()) {
                        errors.add("Description cannot be empty.");
                    } else if (description.length() > 150) {
                        errors.add("Description must not exceed 150 characters.");
                    } 


                    try {
                        if (!errors.isEmpty()) {
                            Categories oldCate = cate.getCategoryByID(categoryID);
                            request.setAttribute("errors", errors);
                            request.setAttribute("category", oldCate);
                            request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/updateCate.jsp").forward(request, response);
                            return;
                        }

                        Categories oldCate = cate.getCategoryByID(categoryID);

                        Categories updatedCate = new Categories(
                                categoryID,
                                categoryName.trim(),
                                oldCate.getBrandComID(),
                                oldCate.getQuantity(),
                                oldCate.getPrice(),
                                description.trim(),
                                Integer.parseInt(statusRaw),
                                imageURL
                        );

                        cate.updateCategory(updatedCate);
                        response.sendRedirect(request.getContextPath() + "/CateAdmin");

                    } catch (Exception e) {
                        e.printStackTrace();
                        errors.add("Update failed due to invalid input or system error.");
                        Categories oldCate = cate.getCategoryByID(categoryID);
                        request.setAttribute("errors", errors);
                        request.setAttribute("category", oldCate);
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
