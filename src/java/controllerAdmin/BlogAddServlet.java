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
import models.Post;
import java.sql.Timestamp;
import java.util.List;
import models.Blog_Cate;

/**
 *
 * @author User
 */
@WebServlet(name = "BlogAddServlet", urlPatterns = {"/blogadd"})

public class BlogAddServlet extends HttpServlet {

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
            out.println("<title>Servlet BlogAddServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogAddServlet at " + request.getContextPath() + "</h1>");
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
       
        String Bc_id_raw = request.getParameter("Bc_id");
        String Post_id_raw = request.getParameter("Post_id");
        String Title = request.getParameter("Title");
        String Author = request.getParameter("Author");
        String Updated_date = request.getParameter("Updated_date");
        String Content = request.getParameter("Content");
        String Thumbnail = request.getParameter("Thumbnail");
        String Brief = request.getParameter("Brief");
        String Add_id_raw = request.getParameter("Add_id");
        
        int Post_id;
        int Bc_id;
        int Add_id;
        Blog_CateDAO bcd = new Blog_CateDAO();
        
        try {
            Post_id = Integer.parseInt(Post_id_raw);
            Bc_id = Integer.parseInt(Bc_id_raw);
            Add_id = Integer.parseInt(Add_id_raw);
            Post c = bcd.getPostById(Post_id);
            if (c == null) {
                Post cNew = new Post(Post_id, Title, Author, Timestamp.valueOf(Updated_date + " 00:00:00"), Content, Bc_id, Thumbnail, Brief, Add_id);

                bcd.insertPost(cNew);
                response.sendRedirect("listpo");
            } else {
                request.setAttribute("error", Post_id + "exitsed");
                request.getRequestDispatcher("AdminLTE/AdminPages/post-create.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
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
