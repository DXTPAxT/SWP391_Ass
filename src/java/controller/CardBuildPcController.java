package controller;

import dal.CartBuildPCDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
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

            List<Integer> categoryIDs = dao.getCategoryIDsInCartBuildPC(cartBuildPCID);
            List<Integer> warrantyIDs = dao.getWarrantyIDsInCartBuildPC(cartBuildPCID);

            System.out.println(">>> Đang xử lý giỏ BuildPCID: " + cartBuildPCID);
            System.out.println(">>> Category IDs: " + categoryIDs);
            System.out.println(">>> Warranty IDs: " + warrantyIDs);

            if (categoryIDs == null || categoryIDs.size() != 6) {
                System.out.println(">>> ERROR: CartBuildPCID " + cartBuildPCID + " thiếu thành phần hoặc không hợp lệ.");
                allSuccess = false;
                continue;
            }

            if (warrantyIDs == null || warrantyIDs.size() != 6) {
                System.out.println(">>> Cảnh báo: Warranty thiếu hoặc không đồng bộ, tự động thêm 0.");
                while (warrantyIDs.size() < 6) {
                    warrantyIDs.add(0);
                }
            }

            boolean inserted = dao.insertBuildPC2(categoryIDs, warrantyIDs, userID);
            if (inserted) {
                dao.deleteCartBuildPC(cartBuildPCID);
                System.out.println(">>> Insert thành công, giỏ đã xóa.");
            } else {
                System.out.println(">>> Insert thất bại cho giỏ: " + cartBuildPCID);
                allSuccess = false;
            }

        } catch (Exception ex) {
            System.err.println(">>> Lỗi khi xử lý giỏ BuildPCID: " + idStr);
            ex.printStackTrace();
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
