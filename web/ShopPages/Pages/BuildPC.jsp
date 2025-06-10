<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Build PC</title>
    <link rel="stylesheet" href="${ctx}/ShopPages/Pages/css/bootstrap.min.css">
    <style>
        .component-item {
            background-color: #1e1e1e;
            color: white;
            border-radius: 8px;
            padding: 15px;
            margin-bottom: 10px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        .component-info {
            display: flex;
            align-items: center;
            gap: 15px;
        }

        .component-info img {
            width: 60px;
            height: 60px;
            object-fit: contain;
        }

        .btn-select {
            background-color: #d9534f;
            color: white;
            border: none;
            padding: 6px 16px;
        }

        .btn-select:hover {
            background-color: #c9302c;
        }
    </style>
</head>
<body class="bg-dark text-white">

<div class="container mt-5">
    <h3 class="mb-2">🛠 Build PC</h3>
    <p class="text-muted">Chọn các linh kiện máy tính bạn cần để xây dựng cấu hình máy</p>

    <c:forEach var="comp" items="${components}">
        <div class="component-item">
            <div class="component-info">
                <img src="${ctx}/images/components/${comp.componentID}.png" alt="${comp.componentName}" />
                <span class="fw-bold fs-5">${comp.componentName}</span>
            </div>
            <form action="${ctx}/ChooseComponent" method="get">
                <input type="hidden" name="componentID" value="${comp.componentID}" />
                <button type="submit" class="btn btn-select">Chọn</button>
            </form>
        </div>
    </c:forEach>
</div>

</body>
</html>
