/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import models.User;

/**
 *
 * @author PC ASUS
 */
public class LoginServlet extends HttpServlet {

    private static final String REDIRECT_AFTER_LOGIN = "redirectAfterLogin";

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
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                RequestDispatcher rs = request.getRequestDispatcher("/ShopPages/Pages/login.jsp");
                rs.forward(request, response);
            } else {
                if (user.getRole().getRoleID() == 3) {
                    String redirectURL = (String) session.getAttribute(REDIRECT_AFTER_LOGIN);

                    if (redirectURL == null || redirectURL.trim().isEmpty() || "null".equalsIgnoreCase(redirectURL)) {
                        redirectURL = request.getContextPath() + "/HomePages";
                    }

                    session.removeAttribute(REDIRECT_AFTER_LOGIN);
                    response.sendRedirect(redirectURL);
                } else {
                    response.sendRedirect("AdminDashbordServlet");
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Có thể thay bằng log framework như Log4j
            request.setAttribute("error", "An unexpected error occurred in login page.");
            request.getRequestDispatcher("/ShopPages/Pages/error.jsp").forward(request, response);
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
            UserDAO userDAO = new UserDAO();
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (utils.Validator.isNullOrEmpty(email)) {
                setLoginAttributes(request, "Email is required!", email, password);
                forwardToLogin(request, response);
                return;
            } else if (utils.Validator.isNullOrEmpty(password)) {
                setLoginAttributes(request, "Password is required!", email, password);
                forwardToLogin(request, response);
                return;
            }

            boolean isEmailExist = userDAO.isEmailExist(email);
            if (!isEmailExist) {
                setLoginAttributes(request, "Email does not exist!", email, password);
                forwardToLogin(request, response);
                return;
            }

            User user = userDAO.getUserByEmailAndPassword(email, password);
            if (user != null) {
                if (user.getStatus() != 1) {
                    setLoginAttributes(request, "Your account has been disabled!", email, password);
                    forwardToLogin(request, response);
                    return;
                }

                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("id", user.getUserId());

                if (user.getRole().getRoleID() != 3) {
                    response.sendRedirect("AdminDashbordServlet");
                } else {
                    String redirectURL = (String) session.getAttribute(REDIRECT_AFTER_LOGIN);

                    if (redirectURL == null || redirectURL.trim().isEmpty() || "null".equalsIgnoreCase(redirectURL)) {
                        redirectURL = request.getContextPath() + "/HomePages";
                    }

                    session.removeAttribute(REDIRECT_AFTER_LOGIN);
                    response.sendRedirect(redirectURL);
                }
            } else {
                setLoginAttributes(request, "Incorrect password!", email, password);
                forwardToLogin(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Ghi log chi tiết lỗi
            request.setAttribute("error", "An unexpected error occurred during login.");
            request.getRequestDispatcher("/ShopPages/Pages/login.jsp").forward(request, response);
        }
    }

    private void setLoginAttributes(HttpServletRequest request, String error, String email, String password) {
        request.setAttribute("error", error);
        request.setAttribute("email", email);
        request.setAttribute("password", password);
    }

    private void forwardToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rs = request.getRequestDispatcher("/ShopPages/Pages/login.jsp");
        rs.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Login servlet for user authentication.";
    }// </editor-fold>

}
