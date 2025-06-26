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
@WebServlet(name = "BlogUpdateServlet", urlPatterns = {"/blogupdate"})

public class BlogUpdateServlet extends HttpServlet {

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
            out.println("<title>Servlet BlogUpdateServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogUpdateServlet at " + request.getContextPath() + "</h1>");
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
        String post_id_raw = request.getParameter("post_id");
        Blog_CateDAO dao = new Blog_CateDAO();
        try {
            int post_id = Integer.parseInt(post_id_raw);
            Post post = dao.getPostById(post_id);
            request.setAttribute("post", post);

            // Load thêm danh sách category để hiển thị trong select
            List<Blog_Cate> categories = dao.getAllBlogCategory();
            request.setAttribute("blog_categories", categories);

            request.getRequestDispatcher("/AdminLTE/AdminPages/pages/forms/update-post.jsp")
                    .forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("blogAdmin?error=Invalid+Post+ID");
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
         try {
           String Post_id_raw = request.getParameter("Post_id");
            String Title = request.getParameter("Title");
            String Author = request.getParameter("Author");
            String Updated_date = request.getParameter("Updated_date");
            String Content = request.getParameter("Content");
            String Thumbnail = request.getParameter("Thumbnail");
            String Bc_id_raw = request.getParameter("Bc_id");
            String Brief = request.getParameter("Brief");
            String Add_id_raw = request.getParameter("Add_id");

            int post_id = Integer.parseInt(Post_id_raw);
            int bc_id = Integer.parseInt(Bc_id_raw);
            int add_id = Integer.parseInt(Add_id_raw);
            Timestamp updateTime = new Timestamp(System.currentTimeMillis());

            Post updated = new Post(post_id, Title, Author, updateTime, Content,
                    bc_id, Thumbnail, Brief, add_id);
            updated.setStatus(1); // giả sử mặc định là active

            Blog_CateDAO dao = new Blog_CateDAO();
            dao.updatePost(updated);

            response.sendRedirect("blogAdmin?message=Update+success");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Update failed: " + e.getMessage());
            doGet(request, response); // hiển thị lại form với lỗi
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
