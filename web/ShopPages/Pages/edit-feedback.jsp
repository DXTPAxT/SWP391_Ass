<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sửa Feedback | E-Shopper</title>
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/main.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/responsive.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h2>Sửa Feedback</h2>
            <c:if test="${not empty sessionScope.message}">
                <p style="color: green;">${sessionScope.message}</p>
                <c:remove var="message" scope="session" />
            </c:if>
            <c:if test="${not empty sessionScope.error}">
                <p style="color: red;">${sessionScope.error}</p>
                <c:remove var="error" scope="session" />
            </c:if>
            <form method="post" action="${pageContext.request.contextPath}/feedback">
                <input type="hidden" name="action" value="update" />
                <input type="hidden" name="feedbackId" value="${feedback.feedbackID}" />
                <input type="hidden" name="productId" value="${requestScope.productID}" />
                <div class="form-group">
                    <label for="content">Nội dung:</label>
                    <textarea name="content" id="content" rows="5" class="form-control" required>${feedback.content}</textarea>
                </div>
                <div class="form-group">
                    <label for="rating">Đánh giá:</label>
                    <select name="rating" id="rating" required class="form-control">
                        <option value="1" ${feedback.rate == 1 ? 'selected' : ''}>1 sao</option>
                        <option value="2" ${feedback.rate == 2 ? 'selected' : ''}>2 sao</option>
                        <option value="3" ${feedback.rate == 3 ? 'selected' : ''}>3 sao</option>
                        <option value="4" ${feedback.rate == 4 ? 'selected' : ''}>4 sao</option>
                        <option value="5" ${feedback.rate == 5 ? 'selected' : ''}>5 sao</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="status">Trạng thái:</label>
                    <select name="status" id="status" class="form-control">
                        <option value="1" ${feedback.status == 1 ? 'selected' : ''}>Hoạt động</option>
                        <option value="0" ${feedback.status == 0 ? 'selected' : ''}>Không hoạt động</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Cập nhật</button>
                <a href="${pageContext.request.contextPath}/feedback?action=product&productID=${requestScope.productID}" class="btn btn-link">Hủy</a>
            </form>
        </div>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/bootstrap.min.js"></script>
    </body>
</html>