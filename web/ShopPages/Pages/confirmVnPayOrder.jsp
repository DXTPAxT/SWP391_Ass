<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="models.User" %>
<%
    // Lấy thông tin người dùng từ session
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("Login");
        return;
    }

    // Lấy dữ liệu từ request
    String cartIDs = request.getParameter("ids");
    String amountStr = request.getParameter("amount");

    int amount = 0;
    try {
        if (amountStr != null) {
            amountStr = amountStr.replaceAll("[^\\d]", "");
            amount = Integer.parseInt(amountStr);
        }
    } catch (Exception e) {
        amount = 0;
    }

    // Lấy thông tin người nhận mặc định từ user
    String fullName = user.getFullname();
    String phone = user.getPhoneNumber();
    String address = (user.getCustomerInfo() != null && user.getCustomerInfo().getAddress() != null)
            ? user.getCustomerInfo().getAddress() : "";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xác nhận đặt hàng</title>
    <style>
        body {
            font-family: sans-serif;
            max-width: 600px;
            margin: auto;
            padding-top: 30px;
        }
        label { font-weight: bold; }
        input[type="text"], textarea {
            width: 100%;
            padding: 8px;
            margin-bottom: 12px;
        }
        button {
            padding: 10px 20px;
            background-color: green;
            color: white;
            border: none;
            cursor: pointer;
        }
        .amount-box {
            background-color: #f9f9f9;
            padding: 12px;
            border: 1px solid #ccc;
            margin-top: 12px;
            margin-bottom: 12px;
        }
        #vnpayModal {
            display: none;
            position: fixed;
            z-index: 999;
            left: 0; top: 0;
            width: 100%; height: 100%;
            background-color: rgba(0,0,0,0.6);
        }
        #vnpayModalContent {
            background-color: white;
            margin: 10% auto;
            padding: 20px;
            width: 400px;
            text-align: center;
        }
    </style>
</head>
<body>
<h2>Xác nhận thông tin đặt hàng</h2>

<div class="amount-box">
    <strong>Số tiền đặt cọc (20%):</strong> <%= amount %> VND
</div>

<form id="orderForm" action="<%= request.getContextPath() %>/ConfirmOrderBuildPC" method="post" onsubmit="return handleSubmit()">
    <label>Họ tên:</label>
    <input type="text" name="fullname" value="<%= fullName %>" required>

    <label>Số điện thoại:</label>
    <input type="text" name="phone" value="<%= phone %>" required>

    <label>Địa chỉ:</label>
    <input type="text" name="address" value="<%= address %>" required>

    <label>Ghi chú:</label>
    <textarea name="note" rows="3"></textarea>

    <label>Phương thức thanh toán:</label><br>
    <input type="radio" name="methodOption" value="vnpay" checked> Thanh toán VNPay<br>
    <input type="radio" name="methodOption" value="cod"> Thanh toán khi nhận hàng (COD)<br><br>

    <!-- Dữ liệu cần thiết -->
    <input type="hidden" name="cartIDs" value="<%= cartIDs %>">
    <input type="hidden" name="amount" value="<%= amount %>">
    <input type="hidden" id="paymentMethodInput" name="paymentMethod" value="vnpay">
    <input type="hidden" id="bankCodeInput" name="bankCode" value="">

    <button type="submit">Xác nhận đặt hàng</button>
</form>

<!-- Modal loading -->
<div id="vnpayModal">
    <div id="vnpayModalContent">
        <h3>Đang chuyển đến VNPay...</h3>
        <img src="assets/images/loading.gif" width="80" alt="Loading..." />
        <p>Nếu không chuyển trang, vui lòng chờ trong giây lát...</p>
    </div>
</div>

<script>
    function handleSubmit() {
        const selectedMethod = document.querySelector('input[name="methodOption"]:checked').value;
        document.getElementById("paymentMethodInput").value = selectedMethod;

        if (selectedMethod === "vnpay") {
            document.getElementById("vnpayModal").style.display = "block";
        }
        return true;
    }
</script>
</body>
</html>
