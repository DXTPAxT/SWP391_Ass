<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh sửa Feedback</title>
    <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/main.css" rel="stylesheet">
    <style>
        .error { color: red; font-weight: bold; }
        .message { color: green; font-weight: bold; }
        form { max-width: 600px; margin: 20px auto; padding: 20px; border: 1px solid #ccc; border-radius: 5px; }
        label { display: inline-block; width: 100px; font-weight: bold; margin-bottom: 10px; }
        textarea, select { width: 100%; margin-bottom: 15px; padding: 8px; }
        .btn { margin-right: 10px; }
        .form-group { margin-bottom: 15px; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Chỉnh sửa Feedback</h2>

        <c:if test="${not empty message}">
            <p class="message">${message}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>

        <c:if test="${not empty feedback}">
            <form id="editFeedbackForm" action="${pageContext.request.contextPath}/feedback?action=update" method="post">
                <input type="hidden" name="feedbackID" value="${feedback.feedbackID}">
                <input type="hidden" name="productID" value="${feedback.productID}">
                <div class="form-group">
                    <label>Đánh giá:</label>
                    <select name="rate" required>
                        <option value="">Chọn đánh giá</option>
                        <option value="5" <c:if test="${feedback.rate == 5}">selected</c:if>>5 sao</option>
                        <option value="4" <c:if test="${feedback.rate == 4}">selected</c:if>>4 sao</option>
                        <option value="3" <c:if test="${feedback.rate == 3}">selected</c:if>>3 sao</option>
                        <option value="2" <c:if test="${feedback.rate == 2}">selected</c:if>>2 sao</option>
                        <option value="1" <c:if test="${feedback.rate == 1}">selected</c:if>>1 sao</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Nội dung:</label>
                    <textarea name="content" rows="5" required>${feedback.content}</textarea>
                </div>
                <button type="submit" class="btn btn-primary">Cập nhật</button>
                <a href="${pageContext.request.contextPath}/feedback?action=product&productID=${feedback.productID}" class="btn btn-secondary">Hủy</a>
            </form>
        </c:if>
        <c:if test="${empty feedback}">
            <p>Không tìm thấy feedback.</p>
            <a href="${pageContext.request.contextPath}/feedback" class="btn btn-link">Quay lại</a>
        </c:if>
    </div>

    <script>
        document.getElementById('editFeedbackForm').addEventListener('submit', function(event) {
            const content = document.querySelector('textarea[name="content"]').value.trim();
            const rate = document.querySelector('select[name="rate"]').value;
            if (!content) {
                event.preventDefault();
                alert('Nội dung không được để trống.');
            } else if (content.length > 500) {
                event.preventDefault();
                alert('Nội dung không được vượt quá 500 ký tự.');
            } else if (!rate) {
                event.preventDefault();
                alert('Vui lòng chọn đánh giá.');
            } else {
                if (!confirm('Bạn có chắc chắn muốn cập nhật feedback này?')) {
                    event.preventDefault();
                }
            }
        });
    </script>
</body>
</html>