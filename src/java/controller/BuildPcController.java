package controller;

import dal.CategoriesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Categories;
import models.Components;

import java.io.IOException;
import java.util.List;

public class BuildPcController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        CategoriesDAO dao = new CategoriesDAO();

        String service = request.getParameter("service");
        if (service == null) service = "list";

        if ("list".equals(service)) {
            // Lấy danh sách linh kiện theo Component
            List<Components> components = dao.getAllComponents();
            request.setAttribute("components", components);
        }

        // Forward tới trang JSP để hiển thị
        request.getRequestDispatcher("ShopPages/Pages/BuildPC.jsp").forward(request, response);
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
        return "Build PC Controller: Show components for custom PC building.";
    }
}
