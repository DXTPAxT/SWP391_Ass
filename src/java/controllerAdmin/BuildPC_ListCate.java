/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import dalAdmin.BuildPCAdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Brands;
import models.BuildPCView;
import models.Categories;

/**
 *
 * @author PC
 */
public class BuildPC_ListCate extends HttpServlet {

    BuildPCAdminDAO dao = new BuildPCAdminDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String service = request.getParameter("service");
        boolean ajax = "true".equalsIgnoreCase(request.getParameter("ajax"));
        if("list".equals(service)){
            
         List<BuildPCView> buildPCList = dao.getBuildPCSummaryView();
        request.setAttribute("buildPCList", buildPCList);
        request.getRequestDispatcher("/AdminLTE/AdminPages/pages/tables/BuildPCList.jsp").forward(request, response);
        }
        if ("filter".equals(service)) {
            String componentID = request.getParameter("componentID");
            String keyword = request.getParameter("keyword");
            String brand_raw = request.getParameter("brand");
            String minPrice = request.getParameter("minPrice");
            String maxPrice = request.getParameter("maxPrice");
            String page = request.getParameter("page");
            int pageSize = 5;
            int totalPages;
            try {
                int comID = safeParseInt(componentID, -1);
                String key = (keyword != null) ? keyword.trim() : "";
                String brand = (brand_raw != null) ? brand_raw.trim() : "";
                int min = safeParseInt(minPrice, -1);
                int max = safeParseInt(maxPrice, Integer.MAX_VALUE);
                int pages = safeParseInt(page, 1);
                int start = (pages - 1) * pageSize;
                if (comID == -1) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "componentID không hợp lệ");
                    return;
                }
                // Cout Component to pagination
                int total = dao.countFilteredByComponent(comID, brand, min, max, key);
                totalPages = (int) Math.ceil((double) total / pageSize);
                // Pagination with component
                List<Categories> listFiltered = dao.getCategoriesFiltered2(comID, brand, key, min, max, start, pageSize);

                List<Brands> brands = dao.getBrandsByComponent(comID);

                request.setAttribute("products", listFiltered);
                request.setAttribute("brands", brands);
                request.setAttribute("componentID", componentID);
                request.setAttribute("currentPage", pages);

                request.setAttribute("totalPages", totalPages);
                if (ajax) {
                 request.getRequestDispatcher("/AdminLTE/AdminPages/pages/tables/BuildPC_Cate_List.jsp").forward(request, response);

                } else {
                    response.sendRedirect("BuildPC.html");
                }

            } catch (Exception e) {
                e.printStackTrace(); // nên ghi log để dễ debug
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi xử lý filter");
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
    }

    public int safeParseInt(String value, int defaultVal) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return defaultVal;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
