/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
import models.User;
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
            User user = dao.getUserByID(userID); // cần hàm getUserByID()

            if (user != null) {
                // 🔐 Tạo mật khẩu ngẫu nhiên
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
                request.setAttribute("toastType", "error"); // error | warning
                request.getRequestDispatcher("/AdminLTE/AdminPages/pages/tables/viewUser.jsp").forward(request, response);
            }
        } else if ("toggleStatus".equals(service)) {
            int userID = Integer.parseInt(request.getParameter("userID"));
            UserDAO dao = new UserDAO();
            boolean toggle = dao.toggleStatus(userID);
            if (toggle) {
                HttpSession session = request.getSession();
                session.setAttribute("toast", "Update user succesfully!");
                session.setAttribute("toastType", "success");
                response.sendRedirect("Admin/user"); // redirect lại để load danh sách mới
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("toast", "Update user failed!");
                session.setAttribute("toastType", "error");
                response.sendRedirect("Admin/user"); // redirect lại để load danh sách mới
            }
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
            try {
                // Lấy dữ liệu từ form
                int userID = Integer.parseInt(request.getParameter("userID"));
                String fullName = request.getParameter("fullName");
                String email = request.getParameter("email");
                String phone = request.getParameter("phoneNumber");
                String address = request.getParameter("address");
                int status = Integer.parseInt(request.getParameter("status"));

                // Gọi DAO để cập nhật
                UserDAO dao = new UserDAO();
                boolean updated = dao.updateUser(userID, fullName, email, phone, address, status);

                if (updated) {
                    HttpSession session = request.getSession();
                    session.setAttribute("toast", "Update user succesfully!");
                    session.setAttribute("toastType", "success");
                    response.sendRedirect("Admin/user"); // redirect lại để load danh sách mới
                } else {
                    request.setAttribute("error", "Cập nhật thất bại!");
                    request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/viewUser.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Đã xảy ra lỗi: " + e.getMessage());
                request.getRequestDispatcher("AdminLTE/AdminPages/pages/tables/viewUser.jsp").forward(request, response);
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
