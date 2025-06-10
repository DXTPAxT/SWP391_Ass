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

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        List<Categories> selectedList = (List<Categories>) session.getAttribute("selectedComponents");
        if (selectedList == null) {
            selectedList = new ArrayList<>();
        }

        String service = request.getParameter("service");
        if (service == null) service = "view";

        if (service.equals("add")) {
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

        if (service.equals("remove")) {
            int compId = parseIntOrDefault(request.getParameter("componentID"), -1);
            selectedList.removeIf(c -> c.getComponentID() == compId);
            session.setAttribute("selectedComponents", selectedList);

            // Kiểm tra nếu là request fetch/ajax thì trả về 200 OK, không redirect
            String xRequestedWith = request.getHeader("X-Requested-With");
            if ("XMLHttpRequest".equals(xRequestedWith)) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.sendRedirect(request.getContextPath() + "/BuildPC");
            }
            return;
        }

        if (service.equals("reset")) {
            session.removeAttribute("selectedComponents");
            response.sendRedirect(request.getContextPath() + "/BuildPC.jsp");
            return;
        }

        if (service.equals("download")) {
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

        if (service.equals("filter")) {
            boolean ajax = "true".equals(request.getParameter("ajax"));
            int componentID = parseIntOrDefault(request.getParameter("componentID"), -1);
            List<Categories> list = new ArrayList<>();
            if (componentID != 1 && componentID != -1) {
                list = dao.getCategoriesByComponentID(componentID);
            }
            request.setAttribute("products", list);
            if (ajax) {
                request.getRequestDispatcher("/ShopPages/Pages/buildpc-product-list.jsp").forward(request, response);
            } else {
                request.setAttribute("data", list);
                request.getRequestDispatcher("/ShopPages/Pages/BuildPC.jsp").forward(request, response);
            }
            return;
        }

        // Default view
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

    @Override
    public String getServletInfo() {
        return "Xử lý chọn/xoá linh kiện và hiển thị trang BuildPC";
    }
}
