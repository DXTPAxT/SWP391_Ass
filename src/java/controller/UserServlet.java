/*
 * Click nfs://netbeans/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nfs://netbeans/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;
import models.Role;
import utils.MailUtils;
import utils.PasswordUtils;

/**
 *
 * @author PC ASUS
 */
public class UserServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();

        String service = request.getParameter("service");
        if ("resetPassword".equals(service)) {
            int userID = Integer.parseInt(request.getParameter("userID"));
            String roleID = request.getParameter("roleID");
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
                }
            } else {
                request.setAttribute("toast", "User not found!");
                request.setAttribute("toastType", "error");
            }
            if ("3".equals(roleID)) {
                response.sendRedirect("Admin/user?type=customer");
            } else {
                response.sendRedirect("Admin/user?type=staff");
            }
        } else if ("toggleStatus".equals(service)) {
            int userID = Integer.parseInt(request.getParameter("userID"));
            UserDAO dao = new UserDAO();
            boolean toggle = dao.toggleStatus(userID);
            HttpSession session = request.getSession();
            if (toggle) {
                session.setAttribute("toast", "Update user successfully!");
                session.setAttribute("toastType", "success");
            } else {
                session.setAttribute("toast", "Update user failed!");
                session.setAttribute("toastType", "error");
            }
            response.sendRedirect("Admin/user");
        } else if ("myAccount".equals(service)) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/ShopPages/Pages/myAccount.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/ShopPages/Pages/login.jsp");
            }
        } else {
            response.sendRedirect("Admin/user?type=customer");
        }
    }

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
            String roleID = request.getParameter("roleID");
            String StartedDate = request.getParameter("StartedDate");
            String EndDate = request.getParameter("EndDate");
            int userID = Integer.parseInt(request.getParameter("userID"));
            int status = Integer.parseInt(request.getParameter("status"));
            boolean isEmailExist = dao.isEmailExist(email);
            boolean isPhoneNumberExisted = dao.isPhoneNumberExisted(phoneNumber);
            String error = null;
            if (utils.Validator.isNullOrEmpty(fullName)) {
                error = "Full name is required!";
            } else if (utils.Validator.isNullOrEmpty(email)) {
                error = "Email is required!";
            } else if (isEmailExist && !email.equals(dao.getUserByID(userID).getEmail())) {
                error = "Email existed!";
            } else if (utils.Validator.isNullOrEmpty(address) && "3".equals(roleID)) {
                error = "Address is required!";
            } else if (utils.Validator.isNullOrEmpty(phoneNumber)) {
                error = "Phone number is required!";
            } else if (!utils.Validator.isValidPhoneNumber(phoneNumber)) {
                error = "Invalid phone number";
            } else if (isPhoneNumberExisted && !phoneNumber.equals(dao.getUserByID(userID).getPhoneNumber())) {
                error = "Phone number existed!";
            }
            if (error != null) {
                request.setAttribute("error", error);
                User user = dao.getUserByID(userID);
                user.setFullname(fullName);
                user.setEmail(email);
                user.setPhoneNumber(phoneNumber);
                request.setAttribute("user", user);
                request.getRequestDispatcher("/AdminLTE/AdminPages/pages/forms/updateUser.jsp").forward(request, response);
            } else {
                try {
                    boolean updated = dao.updateUser(userID, fullName, email, phoneNumber, status, address, StartedDate, EndDate);
                    if (updated) {
                        request.setAttribute("toast", "User updated successfully!");
                        request.setAttribute("toastType", "success");
                    } else {
                        request.setAttribute("toast", "Failed to update user!");
                        request.setAttribute("toastType", "error");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                if ("3".equals(roleID)) {
                    response.sendRedirect("Admin/user?type=customer");
                } else {
                    response.sendRedirect("Admin/user?type=staff");
                }
            }
        } else if ("updateProfile".equals(service)) {
            UserDAO dao = new UserDAO();
            HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("user");
            if (currentUser == null) {
                response.sendRedirect(request.getContextPath() + "/ShopPages/Pages/login.jsp");
                return;
            }
            String email = request.getParameter("email");
            String fullName = request.getParameter("fullName");
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");
            int userID = Integer.parseInt(request.getParameter("userID"));
            int status = currentUser.getStatus();

            boolean isEmailExist = dao.isEmailExist(email);
            boolean isPhoneNumberExisted = dao.isPhoneNumberExisted(phoneNumber);
            String error = null;
            if (utils.Validator.isNullOrEmpty(email)) {
                error = "Email is required!";
            } else if (isEmailExist && !email.equals(dao.getUserByID(userID).getEmail())) {
                error = "Email already exists!";
            } else if (utils.Validator.isNullOrEmpty(fullName)) {
                error = "Full name is required!";
            } else if (utils.Validator.isNullOrEmpty(address)) {
                error = "Address is required!";
            } else if (utils.Validator.isNullOrEmpty(phoneNumber)) {
                error = "Phone number is required!";
            } else if (!utils.Validator.isValidPhoneNumber(phoneNumber)) {
                error = "Invalid phone number!";
            } else if (isPhoneNumberExisted && !phoneNumber.equals(dao.getUserByID(userID).getPhoneNumber())) {
                error = "Phone number already exists!";
            }

            if (error != null) {
                request.setAttribute("toast", error);
                request.setAttribute("toastType", "error");
                User user = dao.getUserByID(userID);
                user.setFullname(fullName);
                user.setEmail(email);
                user.setPhoneNumber(phoneNumber);
                request.setAttribute("user", user);
                request.getRequestDispatcher("/ShopPages/Pages/myAccount.jsp").forward(request, response);
            } else {
                try {
                    boolean updated = dao.updateUser(userID, fullName, email, phoneNumber, status, address, null, null);
                    if (updated) {
                        User updatedUser = dao.getUserByID(userID);
                        session.setAttribute("user", updatedUser);
                        request.setAttribute("toast", "Profile updated successfully!");
                        request.setAttribute("toastType", "success");
                    } else {
                        request.setAttribute("toast", "Failed to update profile!");
                        request.setAttribute("toastType", "error");
                    }
                    request.setAttribute("user", dao.getUserByID(userID));
                    request.getRequestDispatcher("/ShopPages/Pages/myAccount.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "UserServlet handles user-related operations";
    }
}
