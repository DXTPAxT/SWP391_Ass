package controller;

import dal.ShippingDAO;
import models.Shipping;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/addShipping")
public class AddShippingServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        int shipperID = Integer.parseInt(request.getParameter("shipperID"));
        String shippingStatus = request.getParameter("status");
        String feedback = request.getParameter("feedback");

        Shipping ship = new Shipping(0, orderID, shipperID, shippingStatus, feedback);
        ShippingDAO dao = new ShippingDAO();
        dao.insertShipping(ship);

        response.sendRedirect("myShipping");
    }
}
