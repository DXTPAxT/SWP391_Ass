package controller;

import dal.CategoriesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.BrandByComponentName;
import models.Brands;
import models.Categories;
import models.Components;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoriesController", urlPatterns = {"/CategoriesController"})
public class CategoriesController extends HttpServlet {

    private static final int PAGE_SIZE = 9;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        CategoriesDAO dao = new CategoriesDAO();

        // --- Get service ---
        String service = request.getParameter("service");
        if (service == null) {
            service = "list";
        }

        // --- Get filters from request ---
        String componentName = request.getParameter("component");
        String brandName = request.getParameter("brand");
        String keyword = request.getParameter("keyword");

        Integer minPrice = parseInteger(request.getParameter("minPrice"));
        Integer maxPrice = parseInteger(request.getParameter("maxPrice"));
        int page = parseIntegerOrDefault(request.getParameter("page"), 1);
        int start = (page - 1) * PAGE_SIZE;

        List<Categories> categories;
        int totalItems;
        int totalPages;
        if ("list".equals(service)) {

            totalItems = dao.countAllCategories();
            totalPages = (int) Math.ceil(totalItems * 1.0 / PAGE_SIZE);

            categories = dao.getAllCategoriesPaginated(page, PAGE_SIZE);
            request.setAttribute("currentService", "list");
        } else if ("filter".equals(service)) {
            totalItems = dao.countFiltered(componentName, brandName, minPrice, maxPrice, keyword);
            totalPages = (int) Math.ceil(totalItems * 1.0 / PAGE_SIZE);

            categories = dao.getCategoriesFiltered(componentName, brandName, minPrice, maxPrice, keyword, start, PAGE_SIZE);

            request.setAttribute("currentComponent", componentName);
            request.setAttribute("currentBrand", brandName);
            request.setAttribute("minPrice", request.getParameter("minPrice"));
            request.setAttribute("maxPrice", request.getParameter("maxPrice"));
            request.setAttribute("currentKeyword", keyword);
            request.setAttribute("currentService", "filter");
        } else if ("detail".equals(service)) {
            int categoryId = parseIntegerOrDefault(request.getParameter("categoryID"), -1);
            List<Categories> detailList = dao.getCategoryByID(categoryId);
            if (detailList != null && !detailList.isEmpty()) {
                request.setAttribute("product", detailList.get(0));
                request.getRequestDispatcher("ShopPages/Pages/CategoriesDetails.jsp").forward(request, response);
                return;
            } else {
                response.sendRedirect("CategoriesController?service=list");
                return;
            }
        } else {
            // fallback nếu service sai → trả danh sách đầy đủ
            categories = dao.getAllCategoriesPaginated(page, PAGE_SIZE);
            totalItems = dao.countAllCategories();
            totalPages = (int) Math.ceil(totalItems * 1.0 / PAGE_SIZE);
            request.setAttribute("currentService", "list");
        }

        // --- Set common attributes ---
        request.setAttribute("data", categories);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("Components", dao.getAllComponents());
        request.setAttribute("BrandWithComponent", dao.getBrandsGroupedByComponent());
        request.setAttribute("listBrand", dao.getAllBrands());

        // --- Forward to JSP ---
        request.getRequestDispatcher("ShopPages/Pages/Categories.jsp").forward(request, response);
    }

    private Integer parseInteger(String str) {
        try {
            return (str != null && !str.isEmpty()) ? Integer.parseInt(str) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private int parseIntegerOrDefault(String str, int defaultValue) {
        try {
            return (str != null) ? Integer.parseInt(str) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
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
        return "Handles category filtering, listing, and pagination.";
    }
}
