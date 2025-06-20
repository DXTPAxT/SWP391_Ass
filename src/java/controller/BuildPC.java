package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.CategoriesDAO;
import models.Categories;
import models.Brands;

public class BuildPC extends HttpServlet {

    private CategoriesDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new CategoriesDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String service = request.getParameter("service");
        boolean ajax = "true".equalsIgnoreCase(request.getParameter("ajax"));

        if ("filter".equals(service)) {
            int componentID = parseIntOrDefault(request.getParameter("componentID"), -1);
            String keyword = request.getParameter("keyword");
            String brand = request.getParameter("brand");
            int minPrice = parseIntOrDefault(request.getParameter("minPrice"), -1);
            int maxPrice = parseIntOrDefault(request.getParameter("maxPrice"), Integer.MAX_VALUE);
            int page = parseIntOrDefault(request.getParameter("page"), 1);
            int pageSize = 5;
            int start = (page - 1) * pageSize;

            // Đếm theo componentID
            int total = dao.countFilteredByComponent(componentID, brand, minPrice, maxPrice, keyword);
            int totalPages = (int) Math.ceil((double) total / pageSize);

            // Phân trang đúng theo component
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

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<h1>No service handled</h1>");
        }
    }

    private int parseIntOrDefault(String raw, int def) {
        try {
            return Integer.parseInt(raw);
        } catch (Exception e) {
            return def;
        }
    }
}
