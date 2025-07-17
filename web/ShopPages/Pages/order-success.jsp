<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đặt hàng thành công</title>
    <style>
        body {
            font-family: sans-serif;
            text-align: center;
            padding: 40px;
        }
        h1 {
            color: green;
        }
        a {
            margin-top: 20px;
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
    </style>
</head>
<body>
    <h1>🎉 Đặt hàng thành công!</h1>
    <p>Cảm ơn bạn đã mua hàng tại hệ thống của chúng tôi.</p>

    <!-- Sử dụng JSTL để dẫn link -->
    <a href="${pageContext.request.contextPath}/HomePages">Tiếp tục mua sắm</a>
</body>
</html>
