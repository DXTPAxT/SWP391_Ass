package controller;

import dal.CategoriesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.Categories;
import models.Components;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BuildPC", urlPatterns = {"/BuildPC"})
public class BuildPC extends HttpServlet {

    private final CategoriesDAO dao = new CategoriesDAO();

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        List<Categories> selectedList = (List<Categories>) session.getAttribute("selectedComponents");
        if (selectedList == null) selectedList = new ArrayList<>();

        String service = request.getParameter("service");
        if (service == null) service = "view";

        switch (service) {
            case "add" -> {
                int categoryId = parseIntOrDefault(request.getParameter("categoryID"), -1);
                Categories selected = dao.getCategoryByID(categoryId).stream().findFirst().orElse(null);
                if (selected != null) {
                    int compId = selected.getComponentID();
                    selectedList.removeIf(c -> c.getComponentID() == compId);
                    selectedList.add(selected);
                    session.setAttribute("selectedComponents", selectedList);
                }
                response.sendRedirect(request.getContextPath() + "/BuildPC");
                return;
            }
            case "remove" -> {
                int compId = parseIntOrDefault(request.getParameter("componentID"), -1);
                selectedList.removeIf(c -> c.getComponentID() == compId);
                session.setAttribute("selectedComponents", selectedList);
                String xRequestedWith = request.getHeader("X-Requested-With");
                if ("XMLHttpRequest".equals(xRequestedWith)) {
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    response.sendRedirect(request.getContextPath() + "/BuildPC");
                }
                return;
            }
            case "reset" -> {
                session.removeAttribute("selectedComponents");
                response.sendRedirect(request.getContextPath() + "/BuildPC.jsp");
                return;
            }
            case "download" -> {
                if (selectedList.isEmpty()) {
                    response.sendRedirect(request.getContextPath() + "/BuildPC.jsp");
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
            case "filter" -> {
                int componentID = parseIntOrDefault(request.getParameter("componentID"), -1);
                String brand = request.getParameter("brand");
                String keyword = request.getParameter("keyword");
                Integer min = parseNullableInt(request.getParameter("min"));
                Integer max = parseNullableInt(request.getParameter("max"));
                int page = parseIntOrDefault(request.getParameter("page"), 1);
                int size = 6;
                int start = (page - 1) * size;

                String componentName = getComponentNameByID(componentID);
                List<Categories> products = dao.getCategoriesFiltered(componentName, brand, min, max, keyword, start, size);
                int total = dao.countFiltered(componentName, brand, min, max, keyword);
                int totalPages = (int) Math.ceil(total / (double) size);

                request.setAttribute("products", products);
                request.setAttribute("brands", dao.getAllBrands());
                request.setAttribute("componentID", componentID);
                request.setAttribute("currentPage", page);
                request.setAttribute("totalPages", totalPages);
                request.getRequestDispatcher("/ShopPages/Pages/buildpc-product-list.jsp").forward(request, response);
                return;
            }
        }

        List<Components> components = dao.getAllComponents();
        request.setAttribute("components", components);
        request.setAttribute("selectedComponents", selectedList);
        request.getRequestDispatcher("ShopPages/Pages/BuildPC.jsp").forward(request, response);
    }

    private int parseIntOrDefault(String value, int defaultValue) {
        try {
            return (value != null) ? Integer.parseInt(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private Integer parseNullableInt(String val) {
        try {
            return (val != null && !val.isEmpty()) ? Integer.parseInt(val) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String getComponentNameByID(int componentID) {
        return dao.getAllComponents().stream()
            .filter(c -> c.getComponentID() == componentID)
            .map(Components::getComponentName)
            .findFirst().orElse(null);
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
        return "Xử lý chọn/xoá linh kiện và hiển thị trang BuildPC";
    }
}
