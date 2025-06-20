/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.Blog_CateDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Blog_Cate;
import models.Post;

/**
 *
 * @author User
 */
@WebServlet(name = "Blog_CateServlet", urlPatterns = {"/blogc"})

public class Blog_CateServlet extends HttpServlet {

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
            out.println("<title>Servlet Blog_CateServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Blog_CateServlet at " + request.getContextPath() + "</h1>");
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

        Blog_CateDAO dao = new Blog_CateDAO();
        List<Blog_Cate> categories = dao.getAllBlogCategory();
        String Bc_id_raw = request.getParameter("Bc_id");
        String searchKeyword = request.getParameter("search");
        String sort = request.getParameter("sort");
        List<Post> postList = null;
        int count = 0;

        try {
            if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
                postList = dao.searchPosts(searchKeyword);
                count = postList.size();
            } else if (Bc_id_raw != null) {
                int Bc_id = Integer.parseInt(Bc_id_raw );
                postList = dao.getPostsByCategorySorted(Bc_id, sort);
                count = postList.size();
            } else {
                postList = dao.getAllPost();
                count = dao.countAllPosts();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            postList = dao.getAllPost();
            count = dao.countAllPosts();
        }

        int endPage = count / 4;
        if (count % 4 != 0) {
            endPage++;
        }

        List<Post> top5Posts = dao.getTop5NewestPosts();

        request.setAttribute("blog_categories", categories);
        request.setAttribute("postList", postList);
        request.setAttribute("endP", endPage);
        request.setAttribute("searchKeyword", searchKeyword);
        request.setAttribute("selectedSort", sort);// Gửi lại keyword để hiển thị lại trên form
        request.setAttribute("top5Posts", top5Posts);
        request.getRequestDispatcher("ShopPages/Pages/blog.jsp").forward(request, response);
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
