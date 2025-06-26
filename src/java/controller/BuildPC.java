package controller;

import dal.CategoriesDAO;
import models.Brands;
import models.BuildPCView;
import models.Categories;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class BuildPC extends HttpServlet {

    private int parseIntOrDefault(String raw, int def) {
        try {
            return Integer.parseInt(raw);
        } catch (Exception e) {
            return def;
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<h1>No service handled</h1>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String service = request.getParameter("service");
        boolean ajax = "true".equalsIgnoreCase(request.getParameter("ajax"));
        CategoriesDAO dao = new CategoriesDAO();

        if ("filter".equals(service)) {
            int componentID = parseIntOrDefault(request.getParameter("componentID"), -1);
            String keyword = request.getParameter("keyword");
            String brand = request.getParameter("brand");
            int minPrice = parseIntOrDefault(request.getParameter("minPrice"), -1);
            int maxPrice = parseIntOrDefault(request.getParameter("maxPrice"), Integer.MAX_VALUE);
            int page = parseIntOrDefault(request.getParameter("page"), 1);
            int pageSize = 5;
            int start = (page - 1) * pageSize;

            int total = dao.countFilteredByComponent(componentID, brand, minPrice, maxPrice, keyword);
            int totalPages = (int) Math.ceil((double) total / pageSize);

            List<Categories> filtered = dao.getCategoriesFiltered2(componentID, brand, keyword, minPrice, maxPrice, start, pageSize);
            List<Brands> brands = dao.getBrandsByComponent(componentID);

            request.setAttribute("products", filtered);
            request.setAttribute("brands", brands);
            request.setAttribute("componentID", componentID);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            if (ajax) {
                request.getRequestDispatcher("/ShopPages/Pages/BuildPC/BuildPCList.jsp").forward(request, response);
            } else {
                response.sendRedirect("BuildPC.html");
            }
            return;
        }

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String service = request.getParameter("service");
        CategoriesDAO dao = new CategoriesDAO();

        if ("pc".equalsIgnoreCase(service)) {
            List<BuildPCView> pcList = dao.getBuiltPCsForCustomer();
            request.setAttribute("pcList", pcList);
            request.getRequestDispatcher("/ShopPages/Pages/BuildPC/ViewPC.jsp").forward(request, response);
            return;
        }

        processRequest(request, response);
    }
}
