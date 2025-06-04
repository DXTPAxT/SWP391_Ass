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
        Blog_CateDAO dao = new Blog_CateDAO();
        List<Blog_Cate> categories = dao.getAllBlogCategory();
        request.setAttribute("blog_categories", categories);

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "create":
                request.setAttribute("categories", dao.getAllBlogCategory());
                request.getRequestDispatcher("AdminLTE/AdminPages/post-create.jsp").forward(request, response);
                break;

            case "edit":
            try {
                int editId = Integer.parseInt(request.getParameter("id"));
                Post editPost = dao.getPostById(editId);
                request.setAttribute("post", editPost);
                request.setAttribute("categories", dao.getAllBlogCategory());
                request.getRequestDispatcher("AdminLTE/AdminPages/post-edit.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                response.sendRedirect("bloga");
            }
            break;

            case "delete":
            try {
                int deleteId = Integer.parseInt(request.getParameter("id"));
                dao.deletePost(deleteId);
            } catch (NumberFormatException e) {
                // log error if needed
            }
            response.sendRedirect("bloga");
            break;

            case "list":
            default:
                List<Post> postList = dao.getAllPost();
                request.setAttribute("postList", postList);
                request.getRequestDispatcher("AdminLTE/AdminPages/blogAdmin.jsp").forward(request, response);
               break;
        }
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
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String content = request.getParameter("content");
        String thumbnail = request.getParameter("thumbnail");
        String brief = request.getParameter("brief");
        int bcId = Integer.parseInt(request.getParameter("bc_id"));
        int addId = Integer.parseInt(request.getParameter("add_id"));
        Timestamp updatedDate = new Timestamp(System.currentTimeMillis());

        Post p = new Post();
        p.setTitle(title);
        p.setAuthor(author);
        p.setContent(content);
        p.setBc_id(bcId);
        p.setThumbnail(thumbnail);
        p.setBrief(brief);
        p.setAdd_id(addId);
        p.setUpdated_date(updatedDate);

        Blog_CateDAO dao = new Blog_CateDAO();

        if ("create".equals(action)) {
            dao.insertPost(p);
        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            p.setPost_id(id);
            dao.updatePost(p);
        }

        response.sendRedirect("bloga");
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
