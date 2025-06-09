/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import dal.RoleDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import models.User;
import models.Role;
import utils.MailUtils;
import utils.PasswordUtils;

/**
 *
 * @author PC ASUS
 */
public class UserServlet extends HttpServlet {

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
            out.println("<title>Servlet UserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserServlet at " + request.getContextPath() + "</h1>");
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
        UserDAO userDAO = new UserDAO();
        ArrayList<User> users = userDAO.getUsers();
        request.setAttribute("users", users);

        String service = request.getParameter("service");
        if ("resetPassword".equals(request.getParameter("service"))) {
            int userID = Integer.parseInt(request.getParameter("userID"));
            UserDAO dao = new UserDAO();
            User user = dao.getUserByID(userID);
            if (user != null) {
                String newPassword = PasswordUtils.generateRandomPassword(10);
                String hashedPassword = PasswordUtils.hashPassword(newPassword);
                boolean ok = dao.resetPassword(userID, hashedPassword);
                if (ok) {
                    boolean mailSent = MailUtils.send(user.getEmail(),
                            "Your password has been reset",
                            "Hello " + user.getFullname() + ",\n\nYour new password is: " + newPassword + "\n\nPlease log in and change it immediately.");
                    if (mailSent) {
                        request.setAttribute("toast", "Reset password successfully!");
                        request.setAttribute("toastType", "success");
                    } else {
                        request.setAttribute("toast", "Reset password failed to send email!");
                        request.setAttribute("toastType", "warning");
                    }
                    request.getRequestDispatcher("/AdminLTE/AdminPages/pages/tables/viewUser.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("toast", "User not found!");
                request.setAttribute("toastType", "error");
                request.getRequestDispatcher("/AdminLTE/AdminPages/pages/tables/viewUser.jsp").forward(request, response);
            }
        } else if ("toggleStatus".equals(service)) {
            int userID = Integer.parseInt(request.getParameter("userID"));
            UserDAO dao = new UserDAO();
            boolean toggle = dao.toggleStatus(userID);
            HttpSession session = request.getSession();
            if (toggle) {
                session.setAttribute("toast", "Update user succesfully!");
                session.setAttribute("toastType", "success");
            } else {
                session.setAttribute("toast", "Update user failed!");
                session.setAttribute("toastType", "error");
            }
            response.sendRedirect("Admin/user");
        } else {
            // Default: show user list
            request.getRequestDispatcher("/AdminLTE/AdminPages/pages/tables/viewUser.jsp").forward(request, response);
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
        String service = request.getParameter("service");
        if ("updateUser".equals(service)) {
            UserDAO dao = new UserDAO();
            String email = request.getParameter("email");
            String fullName = request.getParameter("fullName");
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");
            int userID = Integer.parseInt(request.getParameter("userID"));
            int status = Integer.parseInt(request.getParameter("status"));
            boolean isEmailExist = dao.isEmailExist(email);
            boolean isPhoneNumberExisted = dao.isPhoneNumberExisted(phoneNumber);
            String error = null;
            if (utils.Validator.isNullOrEmpty(email)) {
                error = "Email is required!";
            } else if (isEmailExist && !email.equals(dao.getUserByID(userID).getEmail())) {
                error = "Email existed!";
            } else if (utils.Validator.isNullOrEmpty(fullName)) {
                error = "Full name is required!";
            } else if (utils.Validator.isNullOrEmpty(address)) {
                error = "Address is required!";
            } else if (utils.Validator.isNullOrEmpty(phoneNumber)) {
                error = "Phone number is required!";
            } else if (!utils.Validator.isValidPhoneNumber(phoneNumber)) {
                error = "Invalid phone number";
            } else if (isPhoneNumberExisted && !phoneNumber.equals(dao.getUserByID(userID).getPhoneNumber())) {
                error = "Phone number existed!";
            }
            if (error != null) {
                User user = dao.getUserByID(userID);
                user.setFullname(fullName);
                user.setEmail(email);
                user.setAddress(address);
                user.setPhoneNumber(phoneNumber);
                user.setStatus(status);
                // Lấy roleMap
                RoleDAO roleDAO = new RoleDAO();
                ArrayList<Role> roles = roleDAO.getRoles();
                Map<Integer, String> roleMap = new HashMap<>();
                for (Role r : roles) {
                    roleMap.put(r.getRoleID(), r.getRoleName());
                }
                request.setAttribute("roleMap", roleMap);
                request.setAttribute("error", error);
                request.setAttribute("user", user);
                request.getRequestDispatcher("/AdminLTE/AdminPages/pages/forms/updateUser.jsp").forward(request, response);
                return;
            }
            try {
                boolean updated = dao.updateUser(userID, fullName, email, phoneNumber, address, status);
                if (updated) {
                    HttpSession session = request.getSession();
                    session.setAttribute("toast", "Update user succesfully!");
                    session.setAttribute("toastType", "success");
                    response.sendRedirect("Admin/user");
                } else {
                    request.setAttribute("error", "Cập nhật thất bại!");
                    User user = dao.getUserByID(userID);
                    request.setAttribute("user", user);
                    // Lấy roleMap cho trường hợp cập nhật thất bại hoặc lỗi exception
                    RoleDAO roleDAO = new RoleDAO();
                    ArrayList<Role> roles = roleDAO.getRoles();
                    Map<Integer, String> roleMap = new HashMap<>();
                    for (Role r : roles) {
                        roleMap.put(r.getRoleID(), r.getRoleName());
                    }
                    request.setAttribute("roleMap", roleMap);
                    request.getRequestDispatcher("/AdminLTE/AdminPages/pages/forms/updateUser.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Đã xảy ra lỗi: " + e.getMessage());
                User user = dao.getUserByID(userID);
                request.setAttribute("user", user);
                request.getRequestDispatcher("/AdminLTE/AdminPages/pages/forms/updateUser.jsp").forward(request, response);
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
