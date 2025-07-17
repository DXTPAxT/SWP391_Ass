package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.User;
import dal.CartBuildPCDAO;

import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "ConfirmOrderBuildPCServlet", urlPatterns = {"/ConfirmOrderBuildPC"})
public class ConfirmOrderBuildPCServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");

            String fullname = request.getParameter("fullname");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String note = request.getParameter("note");
            String bankCode = request.getParameter("bankCode");
            String paymentMethod = request.getParameter("paymentMethod");
            String cartIDsParam = request.getParameter("cartIDs");
            String amountRaw = request.getParameter("amount");

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if (user == null) {
                response.sendRedirect("Login");
                return;
            }

            if (cartIDsParam == null || cartIDsParam.trim().isEmpty()) {
                throw new IllegalArgumentException("Không có cartID nào được chọn.");
            }

            // Làm sạch dữ liệu
            String[] cartIDs = cartIDsParam.trim().split(",");
            int cartID = Integer.parseInt(cartIDs[0].trim());

            amountRaw = amountRaw.replaceAll("[^\\d]", "");
            int amount = Integer.parseInt(amountRaw);

            if ("cod".equalsIgnoreCase(paymentMethod)) {
                // ✅ Xử lý thanh toán COD: tạo đơn hàng trực tiếp
                CartBuildPCDAO dao = new CartBuildPCDAO();
                int orderID = dao.insertOrderFromCartAndReturnOrderID(cartID, user.getUserId());
                  
                // Cập nhật thông tin người nhận và payment
               dao.updateOrderCustomerInfo(orderID, fullname, phone, address, note, 1);

              

                // Xoá cart nếu cần (hàm insertOrderFromCart... đã làm rồi)
                response.sendRedirect("ShopPages/Pages/order-success.jsp");

            } else {
                // ✅ Nếu chọn thanh toán VNPAY thì redirect sang trang thanh toán
                session.setAttribute("pendingCartID", cartID);
                session.setAttribute("pendingAmount", amount);
                session.setAttribute("pendingNote", note != null ? note : "");
                session.setAttribute("pendingBankCode", bankCode != null ? bankCode : "");
                session.setAttribute("pendingFullname", fullname);
                session.setAttribute("pendingPhone", phone);
                session.setAttribute("pendingAddress", address);

                String redirectURL = request.getContextPath() + "/pay-vnpay"
                        + "?amount=" + amount
                        + "&bankCode=" + URLEncoder.encode(bankCode != null ? bankCode : "", "UTF-8")
                        + "&note=" + URLEncoder.encode(note != null ? note : "", "UTF-8");

                response.sendRedirect(redirectURL);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đã xảy ra lỗi khi xác nhận đơn hàng: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
