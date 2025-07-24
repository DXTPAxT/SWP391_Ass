package controller;

import dal.BuildPCOrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.BuildPCAdmin;
import models.User;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "BuildPCOrderHistoryServlet", urlPatterns = {"/OrderHistory2"})
public class BuildPCOrderHistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String orderIDParam = request.getParameter("orderID");

        BuildPCOrderDAO dao = new BuildPCOrderDAO();

        if (orderIDParam != null) {
            try {
                int orderID = Integer.parseInt(orderIDParam);
                ArrayList<BuildPCAdmin> orderDetail = dao.getBuildPCOrderByID(orderID);

                if (orderDetail == null || orderDetail.isEmpty()) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found or does not belong to user.");
                    return;
                }

                // Bảo vệ: nếu người khác cố xem đơn không phải của họ
                if (orderDetail.get(0).getUserId() != user.getUserId()) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to view this order.");
                    return;
                }

                request.setAttribute("orderDetail", orderDetail);
                request.getRequestDispatcher("/ShopPages/Pages/OrderDetailBuildPC.jsp").forward(request, response);
                return;
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid orderID.");
                return;
            }
        }

        // Hiển thị danh sách lịch sử đơn hàng Build PC
        int customerID = user.getUserId();
        int selectedStatus = -1;

        String statusParam = request.getParameter("status");
        if (statusParam != null) {
            try {
                selectedStatus = Integer.parseInt(statusParam);
            } catch (NumberFormatException ignored) {}
        }

        ArrayList<BuildPCAdmin> orders = (selectedStatus == -1)
                ? dao.getBuildPCOrdersByCustomerID(customerID)
                : dao.getBuildPCOrdersByCustomerAndStatus(customerID, selectedStatus);

        request.setAttribute("orders", orders);
        request.setAttribute("selectedStatus", selectedStatus);
        request.getRequestDispatcher("/ShopPages/Pages/OrderPcHistory.jsp").forward(request, response);
    }
}
