package controller;

import dal.CategoriesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.Categories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// ... các import và annotation giữ nguyên ...

@WebServlet(name = "CategoriesController", urlPatterns = {"/CategoriesController"})
public class CategoriesController extends HttpServlet {

    private static final int PAGE_SIZE = 9;
    private final CategoriesDAO dao = new CategoriesDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String service = request.getParameter("service");
        if (service == null) {
            service = "list";
        }

        // RESET
        if ("reset".equals(service)) {
            request.getSession().removeAttribute("selectedComponents");
            response.sendRedirect(request.getContextPath() + "/BuildPC.jsp");
            return;
        }

        // DOWNLOAD
        if ("download".equals(service)) {
            HttpSession session = request.getSession();
            @SuppressWarnings("unchecked")
            List<Categories> sel = (List<Categories>) session.getAttribute("selectedComponents");
            if (sel == null || sel.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/BuildPC.jsp");
                return;
            }
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment;filename=buildpc.csv");
            var out = response.getWriter();
            out.println("CategoryID,Name,Price");
            for (Categories c : sel) {
                out.printf("%d,%s,%d\n", c.getCategoryID(), c.getCategoryName(), c.getPrice());
            }
            return;
        }

        // ✅ AJAX FILTER by componentName (not ID)
        if ("filter".equals(service) && "true".equals(request.getParameter("ajax"))) {
            String componentName = request.getParameter("component");
            List<Categories> list = dao.getCategoriesByComponentName(componentName);
            request.setAttribute("products", list);
            request.getRequestDispatcher("/ShopPages/Pages/buildpc-product-list.jsp").forward(request, response);
            return;
        }

        // ADD selected component
        if ("add".equals(service)) {
            int categoryId = parseIntegerOrDefault(request.getParameter("categoryID"), -1);
            Categories selected = dao.getCategoryByID(categoryId).stream().findFirst().orElse(null);
            if (selected != null) {
                HttpSession session = request.getSession();
                List<Categories> selectedList = (List<Categories>) session.getAttribute("selectedComponents");
                if (selectedList == null) {
                    selectedList = new ArrayList<>();
                }

                int selectedCompId = selected.getComponentID();
                selectedList.removeIf(c -> c.getComponentID() == selectedCompId);
                selectedList.add(selected);

                session.setAttribute("selectedComponents", selectedList);
            }
            response.sendRedirect(request.getContextPath() + "/ShopPages/Pages/BuildPC.jsp");
            return;
        }

        // FILTER (for Categories.jsp)
        String componentName = request.getParameter("component");
        String brandName = request.getParameter("brand");
        String keyword = request.getParameter("keyword");
        Integer minPrice = parseInteger(request.getParameter("minPrice"));
        Integer maxPrice = parseInteger(request.getParameter("maxPrice"));
        int page = parseIntegerOrDefault(request.getParameter("page"), 1);
        int start = (page - 1) * PAGE_SIZE;

        List<Categories> data;
        int totalItems;
        int totalPages;

        if ("filter".equals(service)) {
            totalItems = dao.countFiltered(componentName, brandName, minPrice, maxPrice, keyword);
            totalPages = (int) Math.ceil(totalItems * 1.0 / PAGE_SIZE);
            data = dao.getCategoriesFiltered(componentName, brandName, minPrice, maxPrice, keyword, start, PAGE_SIZE);

            request.setAttribute("currentComponent", componentName);
            request.setAttribute("currentBrand", brandName);
            request.setAttribute("minPrice", request.getParameter("minPrice"));
            request.setAttribute("maxPrice", request.getParameter("maxPrice"));
            request.setAttribute("currentKeyword", keyword);
            request.setAttribute("currentService", "filter");
        } else if ("detail".equals(service)) {
            int categoryId = parseIntegerOrDefault(request.getParameter("categoryID"), -1);
            List<Categories> detailList = dao.getCategoryByID(categoryId);
            if (!detailList.isEmpty()) {
                request.setAttribute("product", detailList.get(0));
                request.getRequestDispatcher("/ShopPages/Pages/CategoriesDetails.jsp").forward(request, response);
            }
            return;
        } else {
            totalItems = dao.countAllCategories();
            totalPages = (int) Math.ceil(totalItems * 1.0 / PAGE_SIZE);
            data = dao.getAllCategoriesPaginated(page, PAGE_SIZE);
            request.setAttribute("currentService", "list");
        }

        request.setAttribute("data", data);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("components", dao.getAllComponents());
        request.setAttribute("brandWithComponent", dao.getBrandsGroupedByComponent());
        request.setAttribute("listBrand", dao.getAllBrands());

        boolean ajax = "true".equals(request.getParameter("ajax"));
        String view = ajax ? "/ShopPages/Pages/CategoriesFragment.jsp" : "/ShopPages/Pages/Categories.jsp";
        request.getRequestDispatcher(view).forward(request, response);
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
            return (str != null) ? Integer.parseInt(str) : def;
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
