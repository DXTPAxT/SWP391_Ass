/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import dal.UserDAO;
import dal.RoleDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import models.User;
import models.Role;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC ASUS
 */
public class UserViewServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(UserViewServlet.class.getName());
    
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
            out.println("<title>Servlet UserViewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserViewServlet at " + request.getContextPath() + "</h1>");
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
        try {
            UserDAO dao = new UserDAO();
            // Test database connection
            if (!dao.isConnected()) {
                LOGGER.severe("Database connection failed!");
                response.sendRedirect(request.getContextPath() + "/error.jsp");
                return;
            }
            LOGGER.info("Database connection successful");
              RoleDAO roleDAO = new RoleDAO();
            // Get the type parameter (customer, staff, or null for all users)
            String type = request.getParameter("type");
            LOGGER.info("Processing request for user type: " + type);
            
            ArrayList<User> users = new ArrayList<>();
            Role role = null;
            
            if ("customer".equals(type)) {
                LOGGER.info("Fetching customer list");
                role = roleDAO.getRoleById(3); // Customer role
                request.setAttribute("viewType", "customer");
            } else if ("staff".equals(type)) {
                LOGGER.info("Fetching staff list");
                role = roleDAO.getRoleById(2); // Staff role
                request.setAttribute("viewType", "staff");
            }
            
            if (role != null) {
                users.addAll(dao.getUsersByRole(role.getRoleID()));
                request.setAttribute("currentRole", role);
            } else {
                LOGGER.info("Fetching all users");
                users = dao.getUsers();
                request.setAttribute("viewType", "all");
            }
            
            LOGGER.info("Total users loaded: " + users.size());
            for (User user : users) {
                LOGGER.info("User loaded - ID: " + user.getUserId() + 
                          ", Role: " + user.getRole().getRoleID()+ 
                          ", Name: " + user.getFullname() +
                          ", CustomerInfo: " + (user.getCustomerInfo() != null ? "present" : "null") +
                          ", StaffInfo: " + (user.getStaffInfo() != null ? "present" : "null"));
            }
            
            request.setAttribute("users", users);
            
            // Load all roles and map roleID to roleName
            ArrayList<Role> roles = roleDAO.getRoles();
            Map<Integer, String> roleMap = new HashMap<>();
            for (Role r : roles) {
                roleMap.put(r.getRoleID(), r.getRoleName());
            }
            request.setAttribute("roleMap", roleMap);
            
            // Forward to the appropriate JSP based on type
            String jspPage;
            if ("customer".equals(type)) {
                jspPage = "/AdminLTE/AdminPages/pages/tables/viewCustomers.jsp";
            } else if ("staff".equals(type)) {
                jspPage = "/AdminLTE/AdminPages/pages/tables/viewStaffs.jsp";
            } else {
                jspPage = "/AdminLTE/AdminPages/pages/tables/viewAllUsers.jsp";
            }
            
            LOGGER.info("Forwarding to JSP: " + jspPage);
            RequestDispatcher dispatcher = request.getRequestDispatcher(jspPage);
            dispatcher.forward(request, response);
            
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in UserViewServlet", e);
            response.sendRedirect(request.getContextPath() + "/error.jsp");
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
        processRequest(request, response);
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
