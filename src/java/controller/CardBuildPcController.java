/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CartBuildPCDAO;
import dal.CategoriesDAO;
import dalAdmin.BuildPCAdminDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import models.CartBuildPC;
import models.User;

/**
 *
 * @author PC
 */
public class CardBuildPcController extends HttpServlet {

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
            out.println("<title>Servlet CardBuildPcController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CardBuildPcController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Kiểm tra đăng nhập
        if (user == null) {
            response.sendRedirect("Login");  // Chưa đăng nhập thì chuyển hướng
            return;
        }

        int userID = user.getUserId();

        CartBuildPCDAO dao = new CartBuildPCDAO();
        List<CartBuildPC> cartBuildPC = dao.getCartPCView(userID);

        // Truyền dữ liệu sang JSP
        request.setAttribute("cartBuildPC", cartBuildPC);
        RequestDispatcher rd = request.getRequestDispatcher("ShopPages/Pages/CartBuildPC.jsp");
        rd.forward(request, response);
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

        String service = request.getParameter("service");
        if (service == null) {
            response.getWriter().print("FAIL");
            return;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.getWriter().print("NOT_LOGGED_IN");
            return;
        }

        CartBuildPCDAO cartDAO = new CartBuildPCDAO();
        PrintWriter out = response.getWriter();

        switch (service) {
            case "deleteCartBuildPC" -> {
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    System.out.println("Nhận yêu cầu xóa giỏ hàng ID: " + id);  // Log debug
                    boolean success = cartDAO.deleteCartBuildPC(id);
                    out.print(success ? "SUCCESS" : "FAIL");
                } catch (Exception e) {
                    e.printStackTrace();
                    out.print("FAIL");
                }
            }

            case "depositBuildPC" -> {
                String idsRaw = request.getParameter("ids");
                if (idsRaw == null || idsRaw.isEmpty()) {
                    out.print("FAIL");
                    return;
                }

                String[] idArray = idsRaw.split(",");
                boolean allSuccess = true;

                for (String idStr : idArray) {
                    try {
                        int cartBuildPCID = Integer.parseInt(idStr);

                        List<Integer> categoryIDs = cartDAO.getCategoryIDsInCartBuildPC(cartBuildPCID);
                        if (categoryIDs.size() != 6) {
                            allSuccess = false;
                            continue;
                        }

                        boolean inserted = cartDAO.insertBuildPC2(categoryIDs, user.getUserId());
                        if (inserted) {
                            cartDAO.deleteCartBuildPC(cartBuildPCID);
                        } else {
                            allSuccess = false;
                        }

                    } catch (Exception ex) {
                        allSuccess = false;
                    }
                }
                out.print(allSuccess ? "SUCCESS" : "FAIL");
            }

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
