// ProductServlet.java
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
import java.util.Vector;

@WebServlet(name = "ProductServlet", urlPatterns = {"/Product"})
public class ProductServlet extends HttpServlet {

    private static final String SQL = "SELECT * FROM Products";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        ProductDAO dao = new ProductDAO();
        CategoryDAO cate = new CategoryDAO();
        String service = request.getParameter("service");
        if (service == null) {
            service = "list";
        }

        Vector<Products> list;

        if ("list".equals(service)) {
            String keyword = request.getParameter("keyword");

            if (keyword == null || keyword.trim().isEmpty()) {
                list = dao.getAllProduct(SQL);
            } else {
                list = dao.getAllProduct("SELECT * FROM Products WHERE Name LIKE N'%" + keyword + "%'");
            }

            request.setAttribute("data", list);
            request.setAttribute("pageTitle", "Search Results");
            request.setAttribute("tableTitle", "Matching Products");

            List<Categories> listcate = cate.getCategoriesName();
            List<BrandByCategoriesName> BWCN = cate.getBrandWithCategoryName();
            request.setAttribute("categories", listcate);
            request.setAttribute("BrandWithCategoryName", BWCN);

            request.getRequestDispatcher("ShopPages/Pages/ProductList.jsp").forward(request, response);
        }

        if ("categoryFilter".equals(service)) {
            String categoryName = request.getParameter("categoryName");
            List<Products> PBC = new ArrayList<>();

            if (categoryName != null && !categoryName.isEmpty()) {
                PBC = dao.getAllProductsByCategoryName(categoryName);
            }

            request.setAttribute("PBC", PBC);

            List<Categories> listcate = cate.getCategoriesName();
            List<BrandByCategoriesName> BWCN = cate.getBrandWithCategoryName();
            request.setAttribute("categories", listcate);
            request.setAttribute("BrandWithCategoryName", BWCN);
            request.getRequestDispatcher("ShopPages/Pages/ProductByCategories.jsp").forward(request, response);
        }

        if ("detail".equals(service)) {
            try {
                int id = Integer.parseInt(request.getParameter("productID"));
                Products product = dao.getProductByID(id);

                request.setAttribute("p", product);
                request.getRequestDispatcher("ShopPages/Pages/ProductDetail.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if ("priceFilter".equals(service)) {
            String minStr = request.getParameter("minPrice");
            String maxStr = request.getParameter("maxPrice");

            double minPrice = 0;
            double maxPrice = Double.MAX_VALUE;

            try {
                if (minStr != null) {
                    minPrice = Double.parseDouble(minStr);
                }
                if (maxStr != null) {
                    maxPrice = Double.parseDouble(maxStr);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
// loc gia
            list = dao.getAllProduct("SELECT * FROM Products WHERE Price BETWEEN " + minPrice + " AND " + maxPrice);

            request.setAttribute("data", list);
            request.setAttribute("pageTitle", "Search Results");
            request.setAttribute("tableTitle", "Products from "
                    + String.format("%,.0f VND", minPrice) + " to "
                    + String.format("%,.0f VND", maxPrice));

            List<Categories> listcate = cate.getCategoriesName();
            List<BrandByCategoriesName> BWCN = cate.getBrandWithCategoryName();
            request.setAttribute("categories", listcate);
            request.setAttribute("BrandWithCategoryName", BWCN);

            request.getRequestDispatcher("ShopPages/Pages/ProductList.jsp").forward(request, response);
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
        return "Short description";
    }
}
