package controller;

import dal.ShippingDAO;
import models.Shipping;
import models.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/myShipping")
public class ShippingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        ShippingDAO dao = new ShippingDAO();
        List<Shipping> shippingList = dao.getShippingByUserID(user.getUserID());
        request.setAttribute("shippingList", shippingList);
        request.getRequestDispatcher("/ShopPages/Pages/myShipping.jsp").forward(request, response);
    }
}
