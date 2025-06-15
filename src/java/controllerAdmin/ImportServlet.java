/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import dalAdmin.CategoryAdminDAO;
import dalAdmin.ImportDAO;
import dalAdmin.ProductAdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import models.Categories;
import models.Imports;
import models.Products;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Admin
 */
@MultipartConfig
public class ImportServlet extends HttpServlet {

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

        /* TODO output your page here. You may use following sample code. */
        String service = request.getParameter("service");

        ImportDAO im = new ImportDAO();
        im.updateImportQuantitiesFromProducts();
        List<Imports> list;

        if (service == null) {
            service = "list";
        }
        if ("list".equals(service)) {

            list = im.getAllImports();

            request.setAttribute("list", list);
            request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/viewImport.jsp").forward(request, response);
        } else if ("listbycate".equals(service)) {
            int id = Integer.parseInt(request.getParameter("categoryID"));

            list = im.getImportsWithProductsByCategoryID(id);

            request.setAttribute("list", list);
            request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/viewImport.jsp").forward(request, response);
        } else if ("listbypro".equals(service)) {
            String id = request.getParameter("productCode");

            Imports imp = im.getImportByProductCode(id);

            request.setAttribute("imp", imp);
            request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/viewImportProductCode.jsp").forward(request, response);
        } else if (service.equals("insert")) {
            String submit = request.getParameter("submit");

            if (submit == null) {
                CategoryAdminDAO cateDAO = new CategoryAdminDAO();
                List<Categories> categories = cateDAO.getAllCategories();
                request.setAttribute("categories", categories);
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/insertImport.jsp").forward(request, response);
            } else {
                String importCode = request.getParameter("importCode");
                String categoryRaw = request.getParameter("categoryID");
                String priceRaw = request.getParameter("price");
                String error = null;

                try {
                    int categoryID = Integer.parseInt(categoryRaw);
                    int price = Integer.parseInt(priceRaw);
                    ImportDAO imdao = new ImportDAO();

                    if (importCode == null || importCode.trim().isEmpty()) {
                        error = "Import code cannot be empty.";
                    } else if (imdao.isImportCodeExists(importCode)) {
                        error = "Import code already exists.";
                    }
                    if (priceRaw == null || !priceRaw.matches("^\\d+$")) {
                        error = "Price must be a positive number.";
                    }

                    if (error != null) {
                        CategoryAdminDAO cateDAO = new CategoryAdminDAO();
                        List<Categories> categories = cateDAO.getAllCategories();
                        request.setAttribute("categories", categories);
                        request.setAttribute("error", error);
                        request.setAttribute("importCode", importCode);
                        request.setAttribute("categoryID", categoryID);
                        request.setAttribute("price", price);
                        request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/insertImport.jsp").forward(request, response);
                        return;
                    }

                    int importID = imdao.insertImportAndGetID(importCode, categoryID, price);
                    if (importID == -1) {
                        throw new Exception("Failed to insert import.");
                    }

                    Part filePart = request.getPart("productExcel");
                    if (filePart != null && filePart.getSize() > 0) {
                        List<String> productCodes = new ArrayList<>();
                        ProductAdminDAO pdao = new ProductAdminDAO();

                        try (InputStream is = filePart.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {

                            Sheet sheet = workbook.getSheetAt(0);
                            for (Row row : sheet) {
                                int rowNum = row.getRowNum() + 1;
                                Cell cell = row.getCell(0);
                                if (cell != null && cell.getCellType() == CellType.STRING) {
                                    String code = cell.getStringCellValue().trim();
                                    if (code.isEmpty()) {
                                        continue;
                                    }
                                    if (code.length() > 30 || !code.matches("^[a-zA-Z0-9_-]+$")) {
                                        imdao.deleteImportByID(importID); // rollback insert
                                        error = "Invalid ProductCode '" + code + "' at row " + rowNum + ". Must be ≤ 30 characters, no special characters.";
                                        CategoryAdminDAO cateDAO = new CategoryAdminDAO();
                                        List<Categories> categories = cateDAO.getAllCategories();
                                        request.setAttribute("categories", categories);
                                        request.setAttribute("error", error);
                                        request.setAttribute("importCode", importCode);
                                        request.setAttribute("categoryID", categoryID);
                                        request.setAttribute("price", price);
                                        request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/insertImport.jsp").forward(request, response);
                                        return;
                                    }
                                    if (pdao.isProductCodeExists(code)) {
                                        imdao.deleteImportByID(importID); // rollback insert
                                        error = "Duplicate ProductCode '" + code + "' at row " + rowNum;
                                        CategoryAdminDAO cateDAO = new CategoryAdminDAO();
                                        List<Categories> categories = cateDAO.getAllCategories();
                                        request.setAttribute("categories", categories);
                                        request.setAttribute("error", error);
                                        request.setAttribute("importCode", importCode);
                                        request.setAttribute("categoryID", categoryID);
                                        request.setAttribute("price", price);
                                        request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/insertImport.jsp").forward(request, response);
                                        return;
                                    }
                                    productCodes.add(code);
                                }
                            }

                            pdao.insertProductsFromExcel(productCodes, categoryID, importID);
                        }
                    }

                    response.sendRedirect("Import?service=list");

                } catch (Exception e) {
                    e.printStackTrace();
                    CategoryAdminDAO cateDAO = new CategoryAdminDAO();
                    List<Categories> categories = cateDAO.getAllCategories();
                    request.setAttribute("categories", categories);
                    request.setAttribute("error", "Error: " + e.getMessage());
                    request.setAttribute("importCode", importCode);
                    request.setAttribute("categoryID", categoryRaw);
                    request.setAttribute("price", priceRaw);
                    request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/insertImport.jsp").forward(request, response);
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
