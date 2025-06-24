/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.cart;

import dal.CartItemDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static java.awt.SystemColor.window;
import models.User;

/**
 *
 * @author PC ASUS
 */
public class AddCartItemServlet extends HttpServlet {

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
            out.println("<title>Servlet AddCartItemServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCartItemServlet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/plain");

        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                String lastPage = request.getParameter("lastPage");
                session.setAttribute("lastPage", lastPage);
                response.sendRedirect("Login");
            } else {
                String userIDparam = request.getParameter("userID");
                String productIDparam = request.getParameter("productID");
                String warrantyDetailIDparam = request.getParameter("warrantyDetailID");
                String quantityparam = request.getParameter("quantity");

                int userID = Integer.parseInt(userIDparam);
                int productID = Integer.parseInt(productIDparam);
                int warrantyDetailID = Integer.parseInt(warrantyDetailIDparam);
                int quantity = Integer.parseInt(quantityparam);

                CartItemDAO dao = new CartItemDAO();
                boolean added = dao.addCartItem(userID, productID, warrantyDetailID, quantity);

                if (added) {
                    session.setAttribute("toast", "Add to cart successfully!");
                    session.setAttribute("toastType", "success");
                } else {
                    session.setAttribute("toast", "Add to cart failed!");
                    session.setAttribute("toastType", "error");
                }
                String lastPage = request.getParameter("lastPage");
                response.sendRedirect(lastPage);
            }
        } catch (Exception e) {
            e.printStackTrace(); // để xem trong console
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
