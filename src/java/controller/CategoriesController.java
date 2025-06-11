package controller;

import dal.CategoriesDAO;
import dal.FeedbackDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.Categories;
import models.Feedback;

import java.io.IOException;
import java.util.*;

@WebServlet(name = "CategoriesController", urlPatterns = {"/CategoriesController"})
public class CategoriesController extends HttpServlet {

    private static final int PAGE_SIZE = 9;
    private final CategoriesDAO dao = new CategoriesDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String service = Optional.ofNullable(request.getParameter("service")).orElse("list");

        switch (service) {
            case "detail" -> handleDetail(request, response);
            case "filter" -> handleFilter(request, response);
            default -> handleList(request, response);
        }
    }

    // Xem chi tiết sản phẩm
    private void handleDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int categoryId = parseIntegerOrDefault(request.getParameter("categoryID"), -1);
        List<Categories> detailList = dao.getCategoryByID(categoryId);

        if (!detailList.isEmpty()) {
            Categories product = detailList.get(0);
            request.setAttribute("product", product);

            FeedbackDAO feedbackDAO = new FeedbackDAO();
            List<Feedback> feedbackList = feedbackDAO.getFeedbackByCategoryId(categoryId);
            request.setAttribute("feedbackList", feedbackList);
            request.setAttribute("categoryID", categoryId);

            request.getRequestDispatcher("/ShopPages/Pages/CategoriesDetails.jsp").forward(request, response);
        }
    }

    // Hiển thị danh sách lọc
    private void handleFilter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String componentName = request.getParameter("component");
        String brandName = request.getParameter("brand");
        String keyword = request.getParameter("keyword");
        Integer minPrice = parseInteger(request.getParameter("minPrice"));
        Integer maxPrice = parseInteger(request.getParameter("maxPrice"));
        int page = parseIntegerOrDefault(request.getParameter("page"), 1);
        int start = (page - 1) * PAGE_SIZE;

        int totalItems = dao.countFiltered(componentName, brandName, minPrice, maxPrice, keyword);
        int totalPages = (int) Math.ceil(totalItems * 1.0 / PAGE_SIZE);
        List<Categories> data = dao.getCategoriesFiltered(componentName, brandName, minPrice, maxPrice, keyword, start, PAGE_SIZE);

        request.setAttribute("currentComponent", componentName);
        request.setAttribute("currentBrand", brandName);
        request.setAttribute("minPrice", request.getParameter("minPrice"));
        request.setAttribute("maxPrice", request.getParameter("maxPrice"));
        request.setAttribute("currentKeyword", keyword);
        request.setAttribute("currentService", "filter");

        setCommonAttributes(request, data, page, totalPages);
        request.getRequestDispatcher("/ShopPages/Pages/Categories.jsp").forward(request, response);
    }

    // Hiển thị danh sách mặc định
    private void handleList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int page = parseIntegerOrDefault(request.getParameter("page"), 1);
        int start = (page - 1) * PAGE_SIZE;

        int totalItems = dao.countAllCategories();
        int totalPages = (int) Math.ceil(totalItems * 1.0 / PAGE_SIZE);
        List<Categories> data = dao.getAllCategoriesPaginated(page, PAGE_SIZE);

        request.setAttribute("currentService", "list");
        setCommonAttributes(request, data, page, totalPages);
        request.getRequestDispatcher("/ShopPages/Pages/Categories.jsp").forward(request, response);
    }

    // Gán các attribute chung
    private void setCommonAttributes(HttpServletRequest request, List<Categories> data, int page, int totalPages) {
        request.setAttribute("data", data);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("Components", dao.getAllComponents());
        request.setAttribute("BrandWithComponent", dao.getBrandsGroupedByComponent());
        request.setAttribute("listBrand", dao.getAllBrands());
    }

    private Integer parseInteger(String str) {
        try {
            return (str != null && !str.isEmpty()) ? Integer.parseInt(str) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private int parseIntegerOrDefault(String str, int def) {
        try {
            return (str != null && !str.isEmpty()) ? Integer.parseInt(str) : def;
        } catch (NumberFormatException e) {
            return def;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }
}
