/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.cart;

import dal.CartItemDAO;
import dal.OrderDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import models.CartItem;
import models.Order;
import models.User;
import utils.Validator;

/**
 *
 * @author PC ASUS
 */
public class ConfirmOrderServlet extends HttpServlet {

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
            out.println("<title>Servlet ConfirmOrderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConfirmOrderServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        OrderDAO dao = new OrderDAO();
        CartItemDAO cartDao = new CartItemDAO();
        ArrayList<CartItem> cartItems = new ArrayList<>();

        int orderId = -1;
        String error = null;

//     Get parameters
        String[] cartItemIds = request.getParameterValues("cartItemIds");
        String receiverName = request.getParameter("receiverName");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String paymentMethod = request.getParameter("paymentMethod");
        String subTotal = request.getParameter("subTotal");
        String message = request.getParameter("message");

//     Validate cart item ids   
        if (cartItemIds == null || cartItemIds.length == 0) {
            session.setAttribute("toastType", "error");
            session.setAttribute("toast", "Proceed to checkout fail!");
            response.sendRedirect("Cart");
            return;
        }

//     Get Cart Item from ids
        for (String idStr : cartItemIds) {
            int id = Integer.parseInt(idStr);
            CartItem cartItem = cartDao.getCartItemById(id);
            cartItems.add(cartItem);
        }

//     Validate parameters
        if (receiverName.trim().isEmpty()) {
            error = "Full name is required!";
        } else if (phoneNumber.trim().isEmpty()) {
            error = "Phone number is required!";
        } else if (!Validator.isValidPhoneNumber(phoneNumber)) {
            error = "Phone number is invalid!";
        } else if (address.trim().isEmpty()) {
            error = "Address is required!";
        }

//      Handle error
        if (error.isEmpty()) {
            try {
//             COD
                if (paymentMethod == "COD") {
                    Order order = new Order(0, address, 0, user.getUserId(), receiverName, null, address, phoneNumber, Integer.parseInt(subTotal), 1, 1);
                    orderId = dao.createOrderAndReturnId(order);
//             Online Banking
                } else {
                    Order order = new Order(0, address, 0, user.getUserId(), receiverName, null, address, phoneNumber, Integer.parseInt(subTotal), 1, 2);
                    orderId = dao.createOrderAndReturnId(order);
                }
//          Handle create Order error
            } catch (Exception e) {
                session.setAttribute("toastType", "error");
                session.setAttribute("toast",  e);
            }
//         Parameter Error
        } else {
            request.setAttribute("error", error);
            request.setAttribute("receiverName", receiverName);
            request.setAttribute("phoneNumber", phoneNumber);
            request.setAttribute("address", address);
            request.setAttribute("message", message);
            request.setAttribute("cartItems", cartItems);
            request.setAttribute("subTotal", subTotal);
            RequestDispatcher rd = request.getRequestDispatcher("ShopPages/Pages/checkout.jsp");
            rd.forward(request, response);
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
