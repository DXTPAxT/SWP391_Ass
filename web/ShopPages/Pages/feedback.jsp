<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Feedback | Computer Online Shop</title>
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/prettyPhoto.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/price-range.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/animate.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/main.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/responsive.css" rel="stylesheet">
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
        </style>
    </head>
    <body>
        <header id="header">
            <!-- Header content remains unchanged -->
            <div class="header_top">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="contactinfo">
                                <ul class="nav nav-pills">
                                    <li><a href="#"><i class="fa fa-phone"></i> +2 95 01 88 821</a></li>
                                    <li><a href="#"><i class="fa fa-envelope"></i> info@site.com</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="social-icons pull-right">
                                <ul class="nav navbar-nav">
                                    <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                    <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                    <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                    <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                                    <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="header-middle">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="logo pull-left">
                                <a href="${pageContext.request.contextPath}/Home"><img src="${pageContext.request.contextPath}/ShopImages/images/home/logo.png" alt="Logo" /></a>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <div class="shop-menu pull-right">
                                <ul class="nav navbar-nav">
                                    <li><a href="#"><i class="fa fa-user"></i> Tài khoản</a></li>
                                    <li><a href="#"><i class="fa fa-star"></i> Yêu thích</a></li>
                                    <li><a href="#"><i class="fa fa-crosshairs"></i> Thanh toán</a></li>
                                    <li><a href="#"><i class="fa fa-shopping-cart"></i> Giỏ hàng</a></li>
                                        <c:choose>
                                            <c:when test="${sessionScope.user != null}">
                                            <li><a href="${pageContext.request.contextPath}/Logout"><i class="fa fa-lock"></i> Đăng xuất</a></li>
                                            </c:when>
                                            <c:otherwise>
                                            <li><a href="${pageContext.request.contextPath}/Login"><i class="fa fa-lock"></i> Đăng nhập</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="header-bottom">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-9">
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                            </div>
                            <div class="mainmenu pull-left">
                                <ul class="nav navbar-nav collapse navbar-collapse">
                                    <li><a href="${pageContext.request.contextPath}/Home">Trang chủ</a></li>
                                    <li class="dropdown"><a href="#">Cửa hàng<i class="fa fa-angle-down"></i></a>
                                        <ul role="menu" class="dropdown-menu">
                                            <li><a href="#">Sản phẩm</a></li>
                                            <li><a href="#">Chi tiết sản phẩm</a></li>
                                            <li><a href="#">Thanh toán</a></li>
                                            <li><a href="#">Giỏ hàng</a></li>
                                            <li><a href="${pageContext.request.contextPath}/Login">Đăng nhập</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="#">Liên hệ</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="search-box pull-right">
                                <input type="text" placeholder="Tìm kiếm"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <section>
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <h2>Feedback cho Danh mục</h2>

                        <!-- Thông báo -->
                        <c:if test="${not empty message}">
                            <p class="message">${message}</p>
                            <c:remove var="message" scope="session"/>
                        </c:if>
                        <c:if test="${not empty error}">
                            <p class="error">${error}</p>
                            <c:remove var="error" scope="session"/>
                        </c:if>

                        <!-- Danh sách feedback -->
                        <div>
                            <c:choose>
                                <c:when test="${not empty feedbackList}">
                                    <c:forEach var="fb" items="${feedbackList}">
                                        <div class="feedback-item">
                                            <p><strong>User ID:</strong> ${fb.userID}</p>
                                            <p><strong>Ngày:</strong> ${fb.createdAt}</p>
                                            <p><strong>Đánh giá:</strong> ${fb.rate} sao</p>
                                            <p>${fb.content}</p>
                                            <div class="feedback-actions">
                                                <c:if test="${sessionScope.user != null && (sessionScope.user.userID == fb.userID || sessionScope.user.roleID == 1)}">
                                                    <a href="${pageContext.request.contextPath}/feedback?action=edit&id=${fb.feedbackID}" class="btn btn-primary btn-sm">Sửa</a>
                                                    <a href="${pageContext.request.contextPath}/feedback?action=delete&id=${fb.feedbackID}" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc muốn xóa feedback này?')">Xóa</a>
                                                </c:if>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <p>Chưa có đánh giá nào cho danh mục này.</p>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <!-- Form gửi feedback -->
                        <h3>Viết đánh giá của bạn</h3>
                        <c:if test="${sessionScope.user != null}">
                            <div class="review-form">
                                <form id="submitFeedbackForm" action="${pageContext.request.contextPath}/submitFeedback" method="post">
                                    <input type="hidden" name="categoryID" value="${categoryID}">
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
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.user == null}">
                            <p>Vui lòng <a href="${pageContext.request.contextPath}/Login">đăng nhập</a> để gửi feedback.</p>
                        </c:if>
                    </div>
                </div>
            </div>
        </section>

        <footer id="footer">
            <!-- Footer content remains unchanged -->
            <div class="footer-top">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-2">
                            <div class="companyinfo">
                                <h2><span>C</span>omputer Online Shop</h2>
                                <p>Chúng tôi cung cấp các sản phẩm công nghệ chất lượng cao với giá cả hợp lý.</p>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="address">
                                <img src="${pageContext.request.contextPath}/ShopImages/images/home/map.png" alt="Map" />
                                <p>123 Nguyễn Văn Cừ, Quận 5, TP.HCM</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer-bottom">
                <div class="container">
                    <div class="row">
                        <p class="pull-left">Copyright © 2023 Computer Online Shop. All rights reserved.</p>
                    </div>
                </div>
            </div>
        </footer>

        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/jquery.scrollUp.min.js"></script>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/price-range.js"></script>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/jquery.prettyPhoto.js"></script>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/main.js"></script>
    </body>
</html>