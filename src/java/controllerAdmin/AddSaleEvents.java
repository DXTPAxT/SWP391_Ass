/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import dal.Blog_CateDAO;
import dal.CategoriesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import models.Categories;
import models.Post;
import models.SaleEvents;
import models.User;

/**
 *
 * @author User
 */
@WebServlet(name = "AddSaleEvents", urlPatterns = {"/addsale"})
public class AddSaleEvents extends HttpServlet {

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
            out.println("<title>Servlet AddSaleEvents</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddSaleEvents at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("AdminLTE/AdminPages/pages/forms/insertSaleEvents.jsp").forward(request, response);
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
//        processRequest(request, response);
        try {
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));
            int postID = Integer.parseInt(request.getParameter("postID"));
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startDate"));
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endDate"));
            double discount = Double.parseDouble(request.getParameter("discount"));

            HttpSession session = request.getSession();
            User staff = (User) session.getAttribute("user");

            SaleEvents event = new SaleEvents();
            event.setCategoryID(categoryID);
            event.setPost_id(postID);
            event.setStartDate(startDate);
            event.setEndDate(endDate);
            event.setDiscountPercent(discount);
            event.setStatus(2); // Chờ duyệt
            event.setCreatedBy(staff.getUserId());
            event.setApprovedBy(null); // Chưa duyệt

            Blog_CateDAO dao = new Blog_CateDAO(); 
            dao.addSaleEvent(event);

            response.sendRedirect("saleevents");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
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
