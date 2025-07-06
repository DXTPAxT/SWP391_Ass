package controller;

import dal.CategoriesDAO;
import models.Brands;
import models.BuildPCView;
import models.Categories;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.User;
import models.WarrantyDetails;

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
            int pageSize = 4;
            int start = (page - 1) * pageSize;

            int total = dao.countFilteredByComponent(componentID, brand, minPrice, maxPrice, keyword);
            int totalPages = (int) Math.ceil((double) total / pageSize);

            List<Categories> filtered = dao.getCategoriesFiltered2(componentID, brand, keyword, minPrice, maxPrice, start, pageSize);
            List<Brands> brands = dao.getBrandsByComponent(componentID);
            Map<Integer, List<WarrantyDetails>> warrantyMap = new HashMap<>();
            for (Categories c : filtered) {
                List<WarrantyDetails> warranties = dao.getWarrantyByCategory(c.getCategoryID());
                warrantyMap.put(c.getCategoryID(), warranties);
            }
            request.setAttribute("products", filtered);
            request.setAttribute("brands", brands);
            request.setAttribute("componentID", componentID);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("warrantyMap", warrantyMap);
            if (ajax) {
                request.getRequestDispatcher("/ShopPages/Pages/BuildPC/BuildPCList.jsp").forward(request, response);
            } else {
                response.sendRedirect("BuildPC.html");
            }

            return;
        }
      if ("loadBuildPC".equals(service)) {
    int buildPCID = parseIntOrDefault(request.getParameter("buildPCID"), -1);

    response.setContentType("text/plain;charset=UTF-8");

    try (PrintWriter out = response.getWriter()) {
        List<Categories> items = dao.getCategoriesInBuildPC(buildPCID);
        StringBuilder sb = new StringBuilder();

        for (Categories c : items) {
            sb.append(c.getCategoryID()).append("|")
              .append(escape(c.getCategoryName())).append("|")
              .append(escape(c.getBrandName())).append("|")
              .append(c.getPrice()).append("|")
              .append(escape(c.getImgURL())).append("|")
              .append(c.getComponentID()).append("|")
              .append(escapeOrDefault(c.getWarrantyDesc(), "Không chọn")).append("|")
              .append(warrantyPrice).append("|")
              .append(warrantyDetailID).append(";");
        }
        out.print(sb.toString());
    }
    return;
}
                                                                                          
                                                    
        processRequest(request, response);
    }
private String escape(String s) {
    if (s == null) return "";
    return s.replace("|", "").replace(";", "").replace("\n", "").replace("\r", "");
}

private String escapeOrDefault(String s, String defaultValue) {
    if (s == null || s.trim().isEmpty()) return defaultValue;
    return escape(s);
}



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String service = request.getParameter("service");
        CategoriesDAO dao = new CategoriesDAO();

        if ("pc".equalsIgnoreCase(service)) {
            List<BuildPCView> pcList = dao.getBuiltPCsForCustomer();
            Map<Integer, Map<Integer, List<WarrantyDetails>>> allWarrantyMap = new HashMap<>();

            for (BuildPCView pc : pcList) {
                Map<Integer, List<WarrantyDetails>> warrantyMap = dao.getWarrantyMapForBuildPC(pc.getBuildPCID());
                allWarrantyMap.put(pc.getBuildPCID(), warrantyMap);
            }

            request.setAttribute("pcList", pcList);
            request.setAttribute("allWarrantyMap", allWarrantyMap);
            request.getRequestDispatcher("/ShopPages/Pages/BuildPC/ViewPC.jsp").forward(request, response);
            return;
        }

        if ("addBuildPCToCart".equals(service)) {
            HttpSession session = request.getSession();
            Object userObj = session.getAttribute("user");

            if (userObj == null || !(userObj instanceof User)) {
                response.getWriter().print("NOT_LOGGED_IN");
                return;
            }

            User user = (User) userObj;

            String[] cateIDs = request.getParameterValues("categories[]");
            String[] warrantyDetailIDsRaw = request.getParameterValues("warrantyDetailIDs[]");

            if (cateIDs == null || cateIDs.length != 6 || warrantyDetailIDsRaw == null || warrantyDetailIDsRaw.length != 6) {
                response.getWriter().print("INVALID");
                return;
            }

            List<Integer> categoryIDs = new ArrayList<>();
            List<Integer> warrantyDetailIDs = new ArrayList<>();

            try {
                for (int i = 0; i < cateIDs.length; i++) {
                    categoryIDs.add(Integer.parseInt(cateIDs[i]));

                    String rawWarrantyDetailID = warrantyDetailIDsRaw[i];
                    int warrantyDetailID = 0;

                    if (rawWarrantyDetailID != null && !rawWarrantyDetailID.trim().isEmpty()) {
                        warrantyDetailID = Integer.parseInt(rawWarrantyDetailID.trim());
                    }

                    warrantyDetailIDs.add(warrantyDetailID);
                }
            } catch (Exception e) {
                response.getWriter().print("INVALID");
                return;
            }

            boolean success = dao.insertBuildPCToCart(categoryIDs, warrantyDetailIDs, user.getUserId());
            response.getWriter().print(success ? "SUCCESS" : "ERROR");
            return;
        }

        processRequest(request, response);
    }

   
}
