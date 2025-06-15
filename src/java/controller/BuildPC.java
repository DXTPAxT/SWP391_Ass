package controller;

import dal.CategoriesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.Categories;
import models.Components;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "BuildPC", urlPatterns = {"/BuildPC"})
public class BuildPC extends HttpServlet {

    private final CategoriesDAO dao = new CategoriesDAO();
    private static final int PAGE_SIZE = 6;

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Categories> selectedList = (List<Categories>) session.getAttribute("selectedComponents");
        if (selectedList == null) {
            selectedList = new ArrayList<>();
        }

        String componentName = request.getParameter("componentName");
        String brand = request.getParameter("brand");
        String keyword = request.getParameter("keyword");
        Integer minPrice = parseInteger(request.getParameter("minPrice"));
        Integer maxPrice = parseInteger(request.getParameter("maxPrice"));
        int page = parseIntOrDefault(request.getParameter("page"), 1);
        int start = (page - 1) * PAGE_SIZE;

        List<Categories> filteredList = dao.getCategoriesFiltered(
                componentName, brand, minPrice, maxPrice, keyword, start, PAGE_SIZE
        );

        int total = dao.countFiltered(componentName, brand, minPrice, maxPrice, keyword);
        int totalPages = (int) Math.ceil((double) total / PAGE_SIZE);

        List<String> brandList = dao.getCategoriesByComponentName(componentName).stream()
                .map(Categories::getBrandName)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        List<Components> components = dao.getAllComponents().stream()
                .filter(c -> !c.getComponentName().equalsIgnoreCase("All"))
                .collect(Collectors.toList());

        request.setAttribute("products", filteredList);
        request.setAttribute("brandList", brandList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("componentName", componentName);
        request.setAttribute("selectedComponents", selectedList);
        request.setAttribute("components", components);
        request.setAttribute("selectedBrand", brand);
        request.setAttribute("minPriceVal", minPrice);
        request.setAttribute("maxPriceVal", maxPrice);
        request.setAttribute("keywordVal", keyword);

        System.out.printf(">> Filtered list: %d | Total: %d | Page: %d/%d\n",
                filteredList.size(), total, page, totalPages);

        boolean ajax = "true".equals(request.getParameter("ajax"));
        if (ajax) {
            request.getRequestDispatcher("/ShopPages/Pages/buildpc-product-list.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/ShopPages/Pages/BuildPC.jsp").forward(request, response);
        }
    }

    private int parseIntOrDefault(String val, int def) {
        try {
            return (val != null) ? Integer.parseInt(val) : def;
        } catch (NumberFormatException e) {
            return def;
        }
    }

    private Integer parseInteger(String val) {
        try {
            return (val != null && !val.trim().isEmpty()) ? Integer.parseInt(val.trim()) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles filtering and product list rendering for BuildPC modal";
    }
}
