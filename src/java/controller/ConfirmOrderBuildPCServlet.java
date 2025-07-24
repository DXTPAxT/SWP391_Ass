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

            // Làm sạch cartIDs
            String[] cartIDs = cartIDsParam.trim().split(",");
            amountRaw = amountRaw.replaceAll("[^\\d]", "");
            int amount = Integer.parseInt(amountRaw);

            CartBuildPCDAO dao = new CartBuildPCDAO();

            if ("cod".equalsIgnoreCase(paymentMethod)) {
                // ✅ Xử lý COD: tạo đơn hàng cho từng cartID
                for (String idStr : cartIDs) {
                    int cartID = Integer.parseInt(idStr.trim());
                    int orderID = dao.insertOrderFromCartAndReturnOrderID(cartID, user.getUserId());
                    dao.updateOrderCustomerInfo(orderID, fullname, phone, address, note, 1); // 1 = COD
                }

                // Sau khi xong hết
                response.sendRedirect("ShopPages/Pages/order-success.jsp");

            } else {
                // ✅ Xử lý VNPay: lưu session và chuyển đến trang thanh toán

                // Gộp cartIDs thành 1 chuỗi lưu lại cho VNPay callback
                session.setAttribute("pendingCartIDs", cartIDsParam);
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
