package controller;

import dal.CartBuildPCDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import models.CartBuildPC;
import models.User;

public class CardBuildPcController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("Login");
            return;
        }

        CartBuildPCDAO dao = new CartBuildPCDAO();
        List<CartBuildPC> cartList = dao.getCartPCView(user.getUserId());

        request.setAttribute("cartBuildPC", cartList);
        RequestDispatcher rd = request.getRequestDispatcher("ShopPages/Pages/CartBuildPC.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String service = request.getParameter("service");
        if (service == null || service.trim().isEmpty()) {
            out.print("FAIL");
            return;
        }

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            out.print("NOT_LOGGED_IN");
            return;
        }

        CartBuildPCDAO dao = new CartBuildPCDAO();

        try {
            switch (service) {
                case "deleteCartBuildPC" -> handleDelete(request, out, dao);
                case "depositBuildPC" -> handleDeposit(request, out, dao, user.getUserId());
                default -> out.print("FAIL");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("FAIL");
        }
    }

    private void handleDelete(HttpServletRequest request, PrintWriter out, CartBuildPCDAO dao) {
        try {
            int cartID = Integer.parseInt(request.getParameter("id"));
            boolean success = dao.deleteCartBuildPC(cartID);
            out.print(success ? "SUCCESS" : "FAIL");
        } catch (Exception e) {
            e.printStackTrace();
            out.print("FAIL");
        }
    }

 private void handleDeposit(HttpServletRequest request, PrintWriter out, CartBuildPCDAO dao, int userID) {
    String idsRaw = request.getParameter("ids");

    if (idsRaw == null || idsRaw.trim().isEmpty()) {
        out.print("FAIL");
        return;
    }

    String[] idArray = idsRaw.split(",");
    boolean allSuccess = true;

    for (String idStr : idArray) {
        try {
            int cartBuildPCID = Integer.parseInt(idStr.trim());

            // Gọi hàm insertOrderFromCart để xử lý đặt cọc và ẩn giỏ
            boolean success = dao.insertOrderFromCart(cartBuildPCID, userID);
           

        } catch (Exception e) {
            System.err.println(" Lỗi xử lý CartBuildPCID: " + idStr);
            e.printStackTrace();
            allSuccess = false;
        }
    }

    out.print(allSuccess ? "SUCCESS" : "FAIL");
}

    @Override
    public String getServletInfo() {
        return "Controller for managing Build PC cart operations.";
    }
}
