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
import models.User;

/**
 *
 * @author PC ASUS
 */
public class SignUpServlet extends HttpServlet {

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
            out.println("<title>Servlet SignUpServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpServlet at " + request.getContextPath() + "</h1>");
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
        if (user == null) {
            RequestDispatcher rs = request.getRequestDispatcher("ShopPages/Pages/signUp.jsp");
            rs.forward(request, response);
        } else {
            if (user.getRoleID() != 1) {
                String redirectURL = (String) session.getAttribute("redirectAfterLogin");
                if (redirectURL == null) {
                    redirectURL = "HomePages";
                }
                session.setAttribute("redirectAfterLogin", null);
                response.sendRedirect(redirectURL);
            } else {
                response.sendRedirect("Admin");
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
        UserDAO userDAO = new UserDAO();
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        boolean isEmailExist = userDAO.isEmailExist(email);
        boolean isPhoneNumberExisted = userDAO.isPhoneNumberExisted(phoneNumber);
        // Validate all String inputs for null or empty using isNullOrEmpty, in the same order as the JSP fields
        if (utils.Validator.isNullOrEmpty(email)) {
            setSignUpAttributes(request, "Email is required!", email, fullName, address, phoneNumber, password, confirmPassword);
        } else if (isEmailExist) {
            setSignUpAttributes(request, "Email existed!", email, fullName, address, phoneNumber, password, confirmPassword);
        } else if (utils.Validator.isNullOrEmpty(fullName)) {
            setSignUpAttributes(request, "Full name is required!", email, fullName, address, phoneNumber, password, confirmPassword);
        } else if (utils.Validator.isNullOrEmpty(address)) {
            setSignUpAttributes(request, "Address is required!", email, fullName, address, phoneNumber, password, confirmPassword);
        } else if (utils.Validator.isNullOrEmpty(phoneNumber)) {
            setSignUpAttributes(request, "Phone number is required!", email, fullName, address, phoneNumber, password, confirmPassword);
        } else if (!utils.Validator.isValidPhoneNumber(phoneNumber)) {
            setSignUpAttributes(request, "Invalid phone number", email, fullName, address, phoneNumber, password, confirmPassword);
        } else if (isPhoneNumberExisted) {
            setSignUpAttributes(request, "Phone number existed!", email, fullName, address, phoneNumber, password, confirmPassword);
        } else if (utils.Validator.isNullOrEmpty(password)) {
            setSignUpAttributes(request, "Password is required!", email, fullName, address, phoneNumber, password, confirmPassword);
        } else if (utils.Validator.isNullOrEmpty(confirmPassword)) {
            setSignUpAttributes(request, "Confirm password is required!", email, fullName, address, phoneNumber, password, confirmPassword);
        } else if (!password.equals(confirmPassword)) {
            setSignUpAttributes(request, "Confirm password not match!", email, fullName, address, phoneNumber, password, confirmPassword);
        } else {
            // Tạm thời không hash password, dùng password thường
            // String hashedPassword = utils.PasswordUtils.hashPassword(password);
            boolean isSuccess = userDAO.createNewUser(email, fullName, address, phoneNumber, password, 3);
            if (isSuccess) {
                request.setAttribute("isSuccess", isSuccess);
                RequestDispatcher rs = request.getRequestDispatcher("/ShopPages/Pages/login.jsp");
                rs.forward(request, response);
                return;
            } else {
                setSignUpAttributes(request, "Sign up failed!", email, fullName, address, phoneNumber, password, confirmPassword);
            }
        }
        // If any error, always forward to sign up page
        RequestDispatcher rs = request.getRequestDispatcher("/ShopPages/Pages/signUp.jsp");
        rs.forward(request, response);
    }

    // Helper method to set all sign up attributes
    private void setSignUpAttributes(HttpServletRequest request, String error, String email, String fullName, String address, String phoneNumber, String password, String confirmPassword) {
        request.setAttribute("error", error);
        request.setAttribute("email", email);
        request.setAttribute("fullName", fullName);
        request.setAttribute("address", address);
        request.setAttribute("phoneNumber", phoneNumber);
        request.setAttribute("password", password);
        request.setAttribute("confirmPassword", confirmPassword);
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
