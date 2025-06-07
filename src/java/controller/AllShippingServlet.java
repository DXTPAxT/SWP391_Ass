package controller;

import dal.ShippingDAO;
import models.Shipping;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/allShipping")
public class AllShippingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ShippingDAO dao = new ShippingDAO();
        List<Shipping> shippingList = dao.getAllShipping();
        request.setAttribute("shippingList", shippingList);
        request.getRequestDispatcher("/ShopPages/Pages/myShipping.jsp").forward(request, response);
    }
}
