package controller;

import dal.CategoriesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Components;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BuildPC", urlPatterns = {"/BuildPC"})
public class BuildPC extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");

        // Lấy dữ liệu từ DAO
        CategoriesDAO dao = new CategoriesDAO();
        List<Components> components = dao.getAllComponents();

        // Truyền sang JSP
        request.setAttribute("components", components);

        // Chuyển sang giao diện BuildPCC.jsp
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
        return "Displays component and product options to build a custom PC.";
    }
}
