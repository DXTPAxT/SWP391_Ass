package controller;

import dal.CategoriesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.Categories;
import models.Components;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "CategoriesController", urlPatterns = {"/CategoriesController"})
public class CategoriesController extends HttpServlet {

    private static final int PAGE_SIZE = 9;
    private final CategoriesDAO dao = new CategoriesDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String service = request.getParameter("service");
        if (service == null) {
            service = "list";
        }

        // Lấy danh sách đã chọn từ session hoặc tạo mới
        List<Categories> selectedList = (List<Categories>) session.getAttribute("selectedComponents");
        if (selectedList == null) {
            selectedList = new ArrayList<>();
        }

        switch (service) {
            case "add": {
                int categoryId = parseIntOrDefault(request.getParameter("categoryID"), -1);
                String compName = request.getParameter("componentName");

                Categories selected = dao.getCategoryByID(categoryId).stream().findFirst().orElse(null);
                if (selected != null && compName != null) {
                    selected.setComponentName(compName);

                    // Loại bỏ mục cũ theo componentName, thêm mục mới
                    selectedList.removeIf(c -> c.getComponentName().equalsIgnoreCase(compName));
                    selectedList.add(selected);
                    session.setAttribute("selectedComponents", selectedList);
                }

                String requestedWith = request.getHeader("X-Requested-With");
                if ("XMLHttpRequest".equals(requestedWith)) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    return;
                }

                response.sendRedirect(request.getContextPath() + "/BuildPC?componentName=" + URLEncoder.encode(compName, "UTF-8") + "&justAdded=true");
                return;
            }
            case "remove": {
                String compName = request.getParameter("componentName");
                selectedList.removeIf(c -> c.getComponentName().equalsIgnoreCase(compName));
                session.setAttribute("selectedComponents", selectedList);
                response.sendRedirect(request.getContextPath() + "/BuildPC");
                return;
            }
            case "reset": {
                session.removeAttribute("selectedComponents");
                response.sendRedirect(request.getContextPath() + "/BuildPC");
                return;
            }
            case "download": {
                if (selectedList.isEmpty()) {
                    response.sendRedirect(request.getContextPath() + "/BuildPC");
                    return;
                }
                response.setContentType("text/csv");
                response.setHeader("Content-Disposition", "attachment;filename=buildpc.csv");
                var out = response.getWriter();
                out.println("CategoryID,Name,Price");
                for (Categories c : selectedList) {
                    out.printf("%d,%s,%d\n", c.getCategoryID(), c.getCategoryName(), c.getPrice());
                }
                return;
            }
            case "build": {
                String componentName = request.getParameter("componentName");

                List<Categories> filteredProducts = new ArrayList<>();
                if (componentName != null && !componentName.equalsIgnoreCase("All")) {
                    filteredProducts = dao.getCategoriesByComponentName(componentName);
                }

                List<Components> components = dao.getAllComponents().stream()
                        .filter(c -> !c.getComponentName().equalsIgnoreCase("All"))
                        .collect(Collectors.toList());

                request.setAttribute("components", components);
                request.setAttribute("data", filteredProducts);
                request.setAttribute("products", filteredProducts);
                request.setAttribute("selectedComponents", selectedList);
                request.setAttribute("currentComponent", componentName);

                boolean ajax = "true".equals(request.getParameter("ajax"));
                if (ajax) {
                    request.getRequestDispatcher("/ShopPages/Pages/buildpc-product-list.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/ShopPages/Pages/BuildPC.jsp").forward(request, response);
                }
                return;
            }
            case "detail": {
                int categoryId = parseIntOrDefault(request.getParameter("categoryID"), -1);
                List<Categories> detailList = dao.getCategoryByID(categoryId);
                if (!detailList.isEmpty()) {
                    request.setAttribute("product", detailList.get(0));
                    request.getRequestDispatcher("/ShopPages/Pages/CategoriesDetails.jsp").forward(request, response);
                }
                return;
            }
            case "filter": {
                String componentName = request.getParameter("component");
                String brandName = request.getParameter("brand");
                String keyword = request.getParameter("keyword");
                Integer minPrice = parseInteger(request.getParameter("minPrice"));
                Integer maxPrice = parseInteger(request.getParameter("maxPrice"));
                int page = parseIntOrDefault(request.getParameter("page"), 1);
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
                request.setAttribute("data", data);
                request.setAttribute("currentPage", page);
                request.setAttribute("totalPages", totalPages);
                request.setAttribute("Components", dao.getAllComponents());
                request.setAttribute("BrandWithComponent", dao.getBrandsGroupedByComponent());
                request.setAttribute("listBrand", dao.getAllBrands());

                request.getRequestDispatcher("/ShopPages/Pages/Categories.jsp").forward(request, response);
                return;
            }
            default: {
                int page = parseIntOrDefault(request.getParameter("page"), 1);
                int start = (page - 1) * PAGE_SIZE;
                int totalItems = dao.countAllCategories();
                int totalPages = (int) Math.ceil(totalItems * 1.0 / PAGE_SIZE);
                List<Categories> data = dao.getAllCategoriesPaginated(page, PAGE_SIZE);

                request.setAttribute("currentService", "list");
                request.setAttribute("data", data);
                request.setAttribute("currentPage", page);
                request.setAttribute("totalPages", totalPages);
                request.setAttribute("Components", dao.getAllComponents());
                request.setAttribute("BrandWithComponent", dao.getBrandsGroupedByComponent());
                request.setAttribute("listBrand", dao.getAllBrands());

                request.getRequestDispatcher("/ShopPages/Pages/Categories.jsp").forward(request, response);
                return;
            }
        }
    }

    private Integer parseInteger(String str) {
        try {
            return (str != null && !str.isEmpty()) ? Integer.parseInt(str) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private int parseIntOrDefault(String value, int def) {
        try {
            return (value != null) ? Integer.parseInt(value) : def;
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

    @Override
    public String getServletInfo() {
        return "Controller for category browsing, filtering, and BuildPC logic";
    }
}
