<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Phản hồi danh mục</title>
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/main.css" rel="stylesheet">
        <style>
            .error {
                color: red;
                font-weight: bold;
            }
            .message {
                color: green;
                font-weight: bold;
            }
            .feedback-item {
                margin-bottom: 20px;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 5px;
            }
            .feedback-actions a {
                margin-right: 10px;
            }
            .review-form {
                max-width: 600px;
                margin: 20px 0;
            }
            .review-form label {
                display: inline-block;
                width: 100px;
                font-weight: bold;
            }
            .review-form textarea, .review-form select {
                width: 100%;
                margin-bottom: 15px;
                padding: 8px;
            }
            .category-info {
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <section>
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <h2>Phản hồi cho danh mục</h2>

                        <div class="category-info">
                            <c:if test="${not empty category}">
                                <h3>${fn:escapeXml(category.categoryName)}</h3>
                                <p><strong>Mô tả:</strong> ${fn:escapeXml(category.description)}</p>
                                <p><strong>Giá:</strong> ${category.price} VNĐ</p>
                            </c:if>
                            <c:if test="${empty category}">
                                <p>Không tìm thấy danh mục.</p>
                            </c:if>
                        </div>

                        <c:if test="${not empty message}">
                            <p class="message">${fn:escapeXml(message)}</p>
                        </c:if>
                        <c:if test="${not empty error}">
                            <p class="error">${fn:escapeXml(error)}</p>
                        </c:if>

                        <c:choose>
                            <c:when test="${not empty feedbackList}">
                                <c:forEach var="fb" items="${feedbackList}">
                                    <div class="feedback-item">
                                        <p><strong>Người dùng:</strong> ${fn:escapeXml(fb.userName)}</p>
                                        <p><strong>Ngày:</strong> ${fb.createdAt}</p>
                                        <p><strong>Đánh giá:</strong> ${fb.rate} sao</p>
                                        <p>${fn:escapeXml(fb.content)}</p>
                                        <div class="feedback-actions">
                                            <c:if test="${sessionScope.user != null && (sessionScope.user.userID == fb.userID || sessionScope.user.roleID == 1)}">
                                                <a href="${pageContext.request.contextPath}/feedback?action=edit&id=${fb.feedbackID}" class="btn btn-primary btn-sm">Sửa</a>
                                                <a href="${pageContext.request.contextPath}/feedback?action=delete&id=${fb.feedbackID}" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc muốn xóa phản hồi này không?')">Xóa</a>
                                            </c:if>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <p>Chưa có phản hồi nào cho danh mục này.</p>
                            </c:otherwise>
                        </c:choose>

                        <h3>Viết phản hồi của bạn</h3>
                        <c:if test="${sessionScope.user != null}">
                            <form id="submitFeedbackForm" action="${pageContext.request.contextPath}/submitFeedback" method="post" class="review-form">
                                <input type="hidden" name="categoryId" value="${categoryID}">
                                <div class="form-group">
                                    <label>Đánh giá:</label>
                                    <select name="rating" required>
                                        <option value="">Chọn đánh giá</option>
                                        <option value="5">5 sao</option>
                                        <option value="4">4 sao</option>
                                        <option value="3">3 sao</option>
                                        <option value="2">2 sao</option>
                                        <option value="1">1 sao</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Nội dung:</label>
                                    <textarea name="content" rows="5" required></textarea>
                                </div>
                                <button type="submit" class="btn btn-primary">Gửi</button>
                            </form>
                        </c:if>
                        <c:if test="${sessionScope.user == null}">
                            <p>Vui lòng <a href="${pageContext.request.contextPath}/Login">đăng nhập</a> để gửi phản hồi.</p>
                        </c:if>
                    </div>
                </div>
            </div>
        </section>

        <script>
            document.getElementById('submitFeedbackForm')?.addEventListener('submit', function (event) {
                const content = document.querySelector('textarea[name="content"]').value.trim();
                const rating = document.querySelector('select[name="rating"]').value;
                if (!content) {
                    event.preventDefault();
                    alert('Nội dung không được để trống.');
                } else if (content.length > 500) {
                    event.preventDefault();
                    alert('Nội dung không được vượt quá 500 ký tự.');
                } else if (!rating) {
                    event.preventDefault();
                    alert('Vui lòng chọn đánh giá.');
                }
            });
        </script>
    </body>
</html>