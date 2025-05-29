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

    private static final String SQL = "SELECT * FROM Products";
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

        // Lấy số trang hiện tại
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

        try {
            // take product by Brand
            if ("Brand".equals(service)) {
                String brand = request.getParameter("Brand");
                if (brand != null && !brand.trim().isEmpty()) {
                    list = dao.getProductsByBrand(brand);
                    request.setAttribute("brandName", brand);
                }
                request.setAttribute("data", list);
            } 
            // take product by Categories
            else if ("categoryFilter".equals(service)) {
                String categoryName = request.getParameter("categoryName");
                if (categoryName != null && !categoryName.trim().isEmpty()) {
                    list = dao.getAllProductsByCategoryName(categoryName);
                }
                request.setAttribute("data", list);
            }
            // Product detail
            else if ("detail".equals(service)) {
                try {
                    int id = Integer.parseInt(request.getParameter("productID"));
                    Products product = dao.getProductByID(id);
                    request.setAttribute("p", product);
                    request.getRequestDispatcher("ShopPages/Pages/ProductDetail.jsp").forward(request, response);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("error", "Lỗi khi hiển thị chi tiết sản phẩm.");
                }
            } 
            else if ("priceFilter".equals(service)) {
                double minPrice = 0;
                double maxPrice = Double.MAX_VALUE;
                try {
                    String minStr = request.getParameter("minPrice");
                    String maxStr = request.getParameter("maxPrice");
                    if (minStr != null) minPrice = Double.parseDouble(minStr);
                    if (maxStr != null) maxPrice = Double.parseDouble(maxStr);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                // Áp dụng phân trang cho price filter
                String sqlPricePaged = "SELECT * FROM Products WHERE Price BETWEEN " + minPrice + " AND " + maxPrice
                        + " ORDER BY ProductID OFFSET " + ((page - 1) * PAGE_SIZE)
                        + " ROWS FETCH NEXT " + PAGE_SIZE + " ROWS ONLY";
                list = dao.getAllProduct(sqlPricePaged);
                request.setAttribute("pageTitle", "Search Results");
                request.setAttribute("tableTitle", "Products from "
                        + String.format("%,.0f VND", minPrice) + " to "
                        + String.format("%,.0f VND", maxPrice));
                request.setAttribute("data", list);
            } 
            // find Product by Name hoặc list all với phân trang
            else {
                String keyword = request.getParameter("keyword");
                String sqlPaged;
                if (keyword == null || keyword.trim().isEmpty()) {
                    sqlPaged = "SELECT * FROM Products ORDER BY ProductID OFFSET "
                            + ((page - 1) * PAGE_SIZE) + " ROWS FETCH NEXT " + PAGE_SIZE + " ROWS ONLY";
                } else {
                    sqlPaged = "SELECT * FROM Products WHERE Name LIKE N'%" + keyword + "%' ORDER BY ProductID OFFSET "
                            + ((page - 1) * PAGE_SIZE) + " ROWS FETCH NEXT " + PAGE_SIZE + " ROWS ONLY";
                    request.setAttribute("pageTitle", "Search Results");
                    request.setAttribute("tableTitle", "Matching Products");
                }
                list = dao.getAllProduct(sqlPaged);
                request.setAttribute("data", list);
            }

            // Tính tổng số trang
            int totalProducts = dao.getAllProduct(SQL).size();
            int totalPages = (int) Math.ceil((double) totalProducts / PAGE_SIZE);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            // select categories and brand
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
