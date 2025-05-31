package controller;

import dal.CategoryDAO;
import dal.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.BrandByCategoriesName;
import models.Categories;
import models.Products;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = {"/Product"})
public class ProductServlet extends HttpServlet {

    private static final int PAGE_SIZE = 6;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        ProductDAO dao = new ProductDAO();
        CategoryDAO cate = new CategoryDAO();
        String service = request.getParameter("service");
        if (service == null) {
            service = "list";
        }

        int page = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        List<Products> list = new ArrayList<>();
        int totalProducts = 0;

        try {
            switch (service) {
                case "Brand": {
                    String brand = request.getParameter("Brand");
                    if (brand != null && !brand.trim().isEmpty()) {
                        String where = "WHERE Brand = '" + brand + "'";
                        totalProducts = dao.countByCondition(where);
                        String baseSQL = "SELECT * FROM Products " + where;
                        list = dao.getProductsByPage(baseSQL, page, PAGE_SIZE);
                        request.setAttribute("brandName", brand);
                        request.setAttribute("currentBrand", brand);
                    }
                    break;
                }
                case "categoryFilter": {
                    String categoryName = request.getParameter("categoryName");
                    if (categoryName != null && !categoryName.trim().isEmpty()) {
                        String where = "JOIN Categories c ON p.CategoryID = c.CategoryID WHERE c.CategoryName = '" + categoryName + "'";
                        totalProducts = dao.countByCondition("JOIN Categories c ON Products.CategoryID = c.CategoryID WHERE c.CategoryName = '" + categoryName + "'");
                        String baseSQL = "SELECT Products.* FROM Products JOIN Categories c ON Products.CategoryID = c.CategoryID WHERE c.CategoryName = '" + categoryName + "'";
                        list = dao.getProductsByPage(baseSQL, page, PAGE_SIZE);
                        request.setAttribute("currentCategory", categoryName);
                    }
                    break;
                }
                case "detail": {
                    int id = Integer.parseInt(request.getParameter("productID"));
                    Products product = dao.getProductByID(id);
                    request.setAttribute("p", product);
                    request.getRequestDispatcher("ShopPages/Pages/ProductDetail.jsp").forward(request, response);
                    return;
                }
                case "priceFilter": {
                    double minPrice = 0, maxPrice = Double.MAX_VALUE;
                    String minStr = request.getParameter("minPrice");
                    String maxStr = request.getParameter("maxPrice");
                    try {
                        if (minStr != null) minPrice = Double.parseDouble(minStr);
                        if (maxStr != null) maxPrice = Double.parseDouble(maxStr);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    String where = "WHERE Price BETWEEN " + minPrice + " AND " + maxPrice;
                    totalProducts = dao.countByCondition(where);
                    String baseSQL = "SELECT * FROM Products " + where;
                    list = dao.getProductsByPage(baseSQL, page, PAGE_SIZE);
                    request.setAttribute("minPrice", minStr);
                    request.setAttribute("maxPrice", maxStr);
                    break;
                }
                default: {
                    String keyword = request.getParameter("keyword");
                    String where = "";
                    if (keyword != null && !keyword.trim().isEmpty()) {
                        where = "WHERE Name LIKE N'%" + keyword + "%'";
                        request.setAttribute("currentKeyword", keyword);
                    }
                    totalProducts = dao.countByCondition(where);
                    String baseSQL = "SELECT * FROM Products " + where;
                    list = dao.getProductsByPage(baseSQL, page, PAGE_SIZE);
                }
            }

            request.setAttribute("data", list);
            request.setAttribute("currentService", service);
            int totalPages = (int) Math.ceil((double) totalProducts / PAGE_SIZE);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            List<Categories> listcate = cate.getCategoriesName();
            List<BrandByCategoriesName> BWCN = cate.getBrandWithCategoryName();
            List<String> listBrand = cate.getAllBrands();
            request.setAttribute("categories", listcate);
            request.setAttribute("BrandWithCategoryName", BWCN);
            request.setAttribute("listBrand", listBrand);

            request.getRequestDispatcher("ShopPages/Pages/ProductList.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("error", "Lỗi xử lý yêu cầu.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "ProductServlet - xử lý hiển thị danh sách và chi tiết sản phẩm";
    }
}