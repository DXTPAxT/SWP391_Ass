/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import dal.Blog_CateDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;
import models.Blog_Cate;
import models.Post;

/**
 *
 * @author User
 */
@WebServlet(name = "BlogAdminServlet", urlPatterns = {"/bloga"})

public class BlogAdminServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BlogAdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogAdminServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String selectedRole = request.getParameter("role");
        Blog_CateDAO dao = new Blog_CateDAO();
        List<Blog_Cate> categories = dao.getAllBlogCategory();

        String sort = request.getParameter("sort");
        List<Post> post;
        if (selectedRole == null || selectedRole.equals("all")) {
            post = dao.getAllPost();
        } else {
            // Convert to proper RoleName in DB
            String roleName = switch (selectedRole) {
                case "admin" ->
                    "Admin";
                case "staff" ->
                    "Staff";
                case "customer" ->
                    "Customer";
                default ->
                    "";
            };
            post = dao.getPostsByAuthorRole(roleName);
        }
        if ("A to Z".equalsIgnoreCase(sort)) {
            post = dao.getPostsSortedByTitle(true);  // ASC
        } else if ("Z to A".equalsIgnoreCase(sort)) {
            post = dao.getPostsSortedByTitle(false); // DESC
        } else {
            post = dao.getAllPost(); // default
        }

        request.setAttribute("blog_categories", categories);
        request.setAttribute("postlist", post);
        request.setAttribute("selectedSort", sort); // để giữ trạng thái dropdown 
        request.setAttribute("selectedRole", selectedRole);
        request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/blogAdmin.jsp").forward(request, response);
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
