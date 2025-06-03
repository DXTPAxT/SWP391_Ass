/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import dal.RoleDAO;
import dal.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import models.Role;
import utils.MailUtils;
import utils.PasswordUtils;

/**
 *
 * @author PC ASUS
 */
public class AddUserServlet extends HttpServlet {

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
            out.println("<title>Servlet AddUserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddUserServlet at " + request.getContextPath() + "</h1>");
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
        RoleDAO dao = new RoleDAO();
        ArrayList<Role> roles = dao.getRoles();
        request.setAttribute("roles", roles);
        RequestDispatcher rs = request.getRequestDispatcher("/AdminLTE/AdminPages/pages/forms/addNewUser.jsp");
        rs.forward(request, response);
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
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String roleID = request.getParameter("roleID");
        RoleDAO roleDao = new RoleDAO();
        UserDAO userDao = new UserDAO();
        ArrayList<Role> roles = roleDao.getRoles();
        String error = "";

        boolean isEmailExisted = userDao.isEmailExist(email);
        if (isEmailExisted) {
            error = "Email existed!";
            request.setAttribute("error", error);
            request.setAttribute("fullName", fullName);
            request.setAttribute("email", email);
            request.setAttribute("phoneNumber", phoneNumber);
            request.setAttribute("address", address);
            request.setAttribute("roleID", roleID);
            request.setAttribute("roles", roles);
            RequestDispatcher rs = request.getRequestDispatcher("/AdminLTE/AdminPages/pages/forms/addNewUser.jsp");
            rs.forward(request, response);
        } else {
            try {
                // 🔐 Tạo mật khẩu ngẫu nhiên
                String newPassword = PasswordUtils.generateRandomPassword(10);
//            String hashedPassword = PasswordUtils.hashPassword(newPassword);
//
//                boolean mailSent = MailUtils.send(email,
//                        "Your password has been reset",
//                        "Hello " + fullName + ",\n\nYour new password is: " + newPassword + "\n\nPlease log in and change it immediately.");

                boolean addUser = userDao.createNewUser(email, fullName, address, phoneNumber, newPassword, Integer.parseInt(roleID));
                if (addUser) {
                    HttpSession session = request.getSession();
                    session.setAttribute("toast", "Add user succesfully!");
                    session.setAttribute("toastType", "success");
                    response.sendRedirect(request.getContextPath() + "/Admin/user");
                } else {
                    request.setAttribute("toast", "Add user failed!");
                    request.setAttribute("toastType", "success");
                }
            } catch (Exception e) {

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
