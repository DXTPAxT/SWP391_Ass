<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chi tiết sản phẩm | E-Shopper</title>
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/prettyPhoto.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/price-range.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/animate.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/main.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/responsive.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/ShopPages/Pages/images/ico/favicon.ico">
    </head>
    <body>
        <header id="header">
            <div class="header_top">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="contactinfo">
                                <ul class="nav nav-pills">
                                    <li><a href="#"><i class="fa fa-phone"></i> +2 95 01 88 821</a></li>
                                    <li><a href="#"><i class="fa fa-envelope"></i> info@domain.com</a></li>
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
                                <a href="${pageContext.request.contextPath}/Home"><img src="${pageContext.request.contextPath}/ShopPages/Pages/images/home/logo.png" alt="" /></a>
                            </div>
                            <div class="btn-group pull-right">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default dropdown-toggle usa" data-toggle="dropdown">
                                        USA <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Canada</a></li>
                                        <li><a href="#">UK</a></li>
                                    </ul>
                                </div>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default dropdown-toggle usa" data-toggle="dropdown">
                                        DOLLAR <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Canadian Dollar</a></li>
                                        <li><a href="#">Pound</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <div class="shop-menu pull-right">
                                <ul class="nav navbar-nav">
                                    <li><a href="#"><i class="fa fa-user"></i> Tài khoản</a></li>
                                    <li><a href="#"><i class="fa fa-star"></i> Danh sách yêu thích</a></li>
                                    <li><a href="checkout.html"><i class="fa fa-crosshairs"></i> Thanh toán</a></li>
                                    <li><a href="cart.html"><i class="fa fa-shopping-cart"></i> Giỏ hàng</a></li>
                                    <li><a href="${pageContext.request.contextPath}/Login"><i class="fa fa-lock"></i> Đăng nhập</a></li>
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
                                        <ul role="menu" class="sub-menu">
                                            <li><a href="shop.html">Sản phẩm</a></li>
                                            <li><a href="product-details.html" class="active">Chi tiết sản phẩm</a></li>
                                            <li><a href="checkout.html">Thanh toán</a></li>
                                            <li><a href="cart.html">Giỏ hàng</a></li>
                                            <li><a href="${pageContext.request.contextPath}/Login">Đăng nhập</a></li>
                                        </ul>
                                    </li>
                                    <li class="dropdown"><a href="#">Blog<i class="fa fa-angle-down"></i></a>
                                        <ul role="menu" class="sub-menu">
                                            <li><a href="blog.html">Danh sách Blog</a></li>
                                            <li><a href="blog-single.html">Blog đơn</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="404.html">404</a></li>
                                    <li><a href="contact-us.html">Liên hệ</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="search_box pull-right">
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
                    <div class="col-sm-3">
                        <div class="left-sidebar">
                            <h2>Danh mục</h2>
                            <div class="panel-group category-products" id="accordian">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordian" href="#sportswear">
                                                <span class="badge pull-right"><i class="fa fa-plus"></i></span>
                                                Đồ thể thao
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="sportswear" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ul>
                                                <li><a href="#">Nike </a></li>
                                                <li><a href="#">Under Armour </a></li>
                                                <li><a href="#">Adidas </a></li>
                                                <li><a href="#">Puma</a></li>
                                                <li><a href="#">ASICS </a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordian" href="#mens">
                                                <span class="badge pull-right"><i class="fa fa-plus"></i></span>
                                                Nam
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="mens" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ul>
                                                <li><a href="#">Fendi</a></li>
                                                <li><a href="#">Guess</a></li>
                                                <li><a href="#">Valentino</a></li>
                                                <li><a href="#">Dior</a></li>
                                                <li><a href="#">Versace</a></li>
                                                <li><a href="#">Armani</a></li>
                                                <li><a href="#">Prada</a></li>
                                                <li><a href="#">Dolce and Gabbana</a></li>
                                                <li><a href="#">Chanel</a></li>
                                                <li><a href="#">Gucci</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordian" href="#womens">
                                                <span class="badge pull-right"><i class="fa fa-plus"></i></span>
                                                Nữ
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="womens" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ul>
                                                <li><a href="#">Fendi</a></li>
                                                <li><a href="#">Guess</a></li>
                                                <li><a href="#">Valentino</a></li>
                                                <li><a href="#">Dior</a></li>
                                                <li><a href="#">Versace</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title"><a href="#">Trẻ em</a></h4>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title"><a href="#">Thời trang</a></h4>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title"><a href="#">Đồ gia dụng</a></h4>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title"><a href="#">Nội thất</a></h4>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title"><a href="#">Quần áo</a></h4>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title"><a href="#">Túi xách</a></h4>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title"><a href="#">Giày</a></h4>
                                    </div>
                                </div>
                            </div>
                            <div class="brands_products">
                                <h2>Thương hiệu</h2>
                                <div class="brands-name">
                                    <ul class="nav nav-pills nav-stacked">
                                        <li><a href="#"> <span class="pull-right">(50)</span>Acne</a></li>
                                        <li><a href="#"> <span class="pull-right">(56)</span>Grüne Erde</a></li>
                                        <li><a href="#"> <span class="pull-right">(27)</span>Albiro</a></li>
                                        <li><a href="#"> <span class="pull-right">(32)</span>Ronhill</a></li>
                                        <li><a href="#"> <span机电-right">(5)</span>Oddmolly</a></li>
                                        <li><a href="#"> <span class="pull-right">(9)</span>Boudestijn</a></li>
                                        <li><a href="#"> <span class="pull-right">(4)</span>Rösch creative culture</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="price-range">
                                <h2>Khoảng giá</h2>
                                <div class="well">
                                    <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="600" data-slider-step="5" data-slider-value="[250,450]" id="sl2" ><br />
                                    <b>$ 0</b> <b class="pull-right">$ 600</b>
                                </div>
                            </div>
                            <div class="shipping text-center">
                                <img src="${pageContext.request.contextPath}/ShopPages/Pages/images/home/shipping.jpg" alt="" />
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-9 padding-right">
                        <div class="product-details">
                            <div class="col-sm-5">
                                <div class="view-product">
                                    <img src="${pageContext.request.contextPath}/ShopPages/Pages/images/product-details/1.jpg" alt="" />
                                    <h3>ZOOM</h3>
                                </div>
                                <div id="similar-product" class="carousel slide" data-ride="carousel">
                                    <div class="carousel-inner">
                                        <div class="item active">
                                            <a href="#"><img src="${pageContext.request.contextPath}/ShopPages/Pages/images/product-details/similar1.jpg" alt=""></a>
                                            <a href="#"><img src="${pageContext.request.contextPath}/ShopPages/Pages/images/product-details/similar2.jpg" alt=""></a>
                                            <a href="#"><img src="${pageContext.request.contextPath}/ShopPages/Pages/images/product-details/similar3.jpg" alt=""></a>
                                        </div>
                                        <div class="item">
                                            <a href="#"><img src="${pageContext.request.contextPath}/ShopPages/Pages/images/product-details/similar1.jpg" alt=""></a>
                                            <a href="#"><img src="${pageContext.request.contextPath}/ShopPages/Pages/images/product-details/similar2.jpg" alt=""></a>
                                            <a href="#"><img src="${pageContext.request.contextPath}/ShopPages/Pages/images/product-details/similar3.jpg" alt=""></a>
                                        </div>
                                        <div class="item">
                                            <a href="#"><img src="${pageContext.request.contextPath}/ShopPages/Pages/images/product-details/similar1.jpg" alt=""></a>
                                            <a href="#"><img src="${pageContext.request.contextPath}/ShopPages/Pages/images/product-details/similar2.jpg" alt=""></a>
                                            <a href="#"><img src="${pageContext.request.contextPath}/ShopPages/Pages/images/product-details/similar3.jpg" alt=""></a>
                                        </div>
                                    </div>
                                    <a class="left item-control" href="#similar-product" data-slide="prev">
                                        <i class="fa fa-angle-left"></i>
                                    </a>
                                    <a class="right item-control" href="#similar-product" data-slide="next">
                                        <i class="fa fa-angle-right"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="col-sm-7">
                                <div class="product-information">
                                    <img src="${pageContext.request.contextPath}/ShopPages/Pages/images/product-details/new.jpg" class="newarrival" alt="" />
                                    <h2>Anne Klein Sleeveless Colorblock Scuba</h2>
                                    <p>Web ID: 1089772</p>
                                    <img src="${pageContext.request.contextPath}/ShopPages/Pages/images/product-details/rating.png" alt="" />
                                    <span>
                                        <span>US $59</span>
                                        <label>Số lượng:</label>
                                        <input type="text" value="3" />
                                        <button type="button" class="btn btn-fefault cart">
                                            <i class="fa fa-shopping-cart"></i>
                                            Thêm vào giỏ
                                        </button>
                                    </span>
                                    <p><b>Tình trạng:</b> Còn hàng</p>
                                    <p><b>Điều kiện:</b> Mới</p>
                                    <p><b>Thương hiệu:</b> E-SHOPPER</p>
                                    <a href="#"><img src="${pageContext.request.contextPath}/ShopPages/Pages/images/product-details/share.png" class="share img-responsive" alt="" /></a>
                                </div>
                            </div>
                        </div>
                        <div class="category-tab shop-details-tab">
                            <div class="col-sm-12">
                                <ul class="nav nav-tabs">
                                    <li><a href="#details" data-toggle="tab">Chi tiết</a></li>
                                    <li><a href="#companyprofile" data-toggle="tab">Hồ sơ công ty</a></li>
                                    <li><a href="#tag" data-toggle="tab">Tag</a></li>
                                    <li class="active"><a href="#reviews" data-toggle="tab">Đánh giá (${not empty feedbackList ? feedbackList.size() : 0})</a></li>
                                </ul>
                            </div>
                            <div class="tab-content">
                                <div class="tab-pane fade" id="details">
                                    <div class="col-sm-3">
                                        <div class="product-image-wrapper">
                                            <div class="single-products">
                                                <div class="productinfo text-center">
                                                    <img src="${pageContext.request.contextPath}/ShopPages/Pages/images/home/gallery1.jpg" alt="" />
                                                    <h2>$56</h2>
                                                    <p>Easy Polo Black Edition</p>
                                                    <button type="button" class="btn btn-default add-to-cart">
                                                        <i class="fa fa-shopping-cart"></i> Thêm vào giỏ
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane fade" id="companyprofile">
                                </div>
                                <div class="tab-pane fade" id="tag">
                                </div>
                                <div class="tab-pane fade active in" id="reviews">
                                    <div class="col-sm-12">
                                        <c:if test="${not empty sessionScope.message}">
                                            <p class="message" style="color: green;">${sessionScope.message}</p>
                                            <c:remove var="message" scope="session" />
                                        </c:if>
                                        <c:if test="${not empty sessionScope.error}">
                                            <p class="error" style="color: red;">${sessionScope.error}</p>
                                            <c:remove var="error" scope="session" />
                                        </c:if>
                                        <div id="review-list">
                                            <c:choose>
                                                <c:when test="${not empty feedbackList}">
                                                    <c:forEach var="fb" items="${feedbackList}">
                                                        <div class="single-review">
                                                            <ul>
                                                                <li><i class="fa fa-user"></i> UserID: ${fb.userID}</li>
                                                                <li><i class="fa fa-clock-o"></i> ${fb.createdAt}</li>
                                                                <li><i class="fa fa-star"></i> Đánh giá: ${fb.rate} sao</li>
                                                            </ul>
                                                            <p>${fb.content}</p>
                                                            <!-- Debug output -->
                                                            <p style="color: blue;">Debug: Delete URL = ${pageContext.request.contextPath}/feedback?action=delete&id=${fb.feedbackID}&productID=${fb.productID}</p>
                                                            <c:if test="${not empty sessionScope.user && (sessionScope.user.userID == fb.userID || sessionScope.user.roleID == 1)}">
                                                                <div class="review-actions">
                                                                    <a href="${pageContext.request.contextPath}/feedback?action=edit&id=${fb.feedbackID}&productID=${fb.productID}" class="btn btn-link">Sửa</a>
                                                                    <a href="${pageContext.request.contextPath}/feedback?action=delete&id=${fb.feedbackID}&productID=${fb.productID}" class="btn btn-link" onclick="return confirm('Bạn có chắc muốn xóa feedback này?')">Xóa</a>
                                                                </div>
                                                            </c:if>
                                                        </div>
                                                    </c:forEach>
                                                </c:when>
                                                <c:otherwise>
                                                    <p>Chưa có đánh giá nào cho sản phẩm này.</p>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <p><b>Viết đánh giá của bạn</b></p>
                                        <form id="review-form" method="post" action="${pageContext.request.contextPath}/submitFeedback">
                                            <input type="hidden" name="productId" value="${requestScope.productID}" />
                                            <textarea name="content" id="review-content" rows="5" placeholder="Nhập đánh giá của bạn..." required></textarea>
                                            <br />
                                            <b>Đánh giá: </b>
                                            <select id="review-rating" name="rating" required>
                                                <option value="">Chọn đánh giá</option>
                                                <option value="5">5 sao</option>
                                                <option value="4">4 sao</option>
                                                <option value="3">3 sao</option>
                                                <option value="2">2 sao</option>
                                                <option value="1">1 sao</option>
                                            </select>
                                            <button type="submit" class="btn btn-default pull-right" style="margin-top:10px;">Gửi</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="recommended_items">
                            <h2 class="title text-center">Sản phẩm đề xuất</h2>
                            <div id="recommended-item-carousel" class="carousel slide" data-ride="carousel">
                                <div class="carousel-inner">
                                    <div class="item active">
                                        <div class="col-sm-4">
                                            <div class="product-image-wrapper">
                                                <div class="single-products">
                                                    <div class="productinfo text-center">
                                                        <img src="${pageContext.request.contextPath}/ShopPages/Pages/images/home/recommend1.jpg" alt="" />
                                                        <h2>$56</h2>
                                                        <p>Easy Polo Black Edition</p>
                                                        <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Thêm vào giỏ</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="product-image-wrapper">
                                                <div class="single-products">
                                                    <div class="productinfo text-center">
                                                        <img src="${pageContext.request.contextPath}/ShopPages/Pages/images/home/recommend2.jpg" alt="" />
                                                        <h2>$56</h2>
                                                        <p>Easy Polo Black Edition</p>
                                                        <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Thêm vào giỏ</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="product-image-wrapper">
                                                <div class="single-products">
                                                    <div class="productinfo text-center">
                                                        <img src="${pageContext.request.contextPath}/ShopPages/Pages/images/home/recommend3.jpg" alt="" />
                                                        <h2>$56</h2>
                                                        <p>Easy Polo Black Edition</p>
                                                        <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Thêm vào giỏ</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item">
                                        <div class="col-sm-4">
                                            <div class="product-image-wrapper">
                                                <div class="single-products">
                                                    <div class="productinfo text-center">
                                                        <img src="${pageContext.request.contextPath}/ShopPages/Pages/images/home/recommend1.jpg" alt="" />
                                                        <h2>$56</h2>
                                                        <p>Easy Polo Black Edition</p>
                                                        <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Thêm vào giỏ</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="product-image-wrapper">
                                                <div class="single-products">
                                                    <div class="productinfo text-center">
                                                        <img src="${pageContext.request.contextPath}/ShopPages/Pages/images/home/recommend2.jpg" alt="" />
                                                        <h2>$56</h2>
                                                        <p>Easy Polo Black Edition</p>
                                                        <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Thêm vào giỏ</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="product-image-wrapper">
                                                <div class="single-products">
                                                    <div class="productinfo text-center">
                                                        <img src="${pageContext.request.contextPath}/ShopPages/Pages/images/home/recommend3.jpg" alt="" />
                                                        <h2>$56</h2>
                                                        <p>Easy Polo Black Edition</p>
                                                        <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Thêm vào giỏ</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <a class="left recommended-item-control" href="#recommended-item-carousel" data-slide="prev">
                                    <i class="fa fa-angle-left"></i>
                                </a>
                                <a class="right recommended-item-control" href="#recommended-item-carousel" data-slide="next">
                                    <i class="fa fa-angle-right"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <footer id="footer">
            <div class="footer-top">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-2">
                            <div class="companyinfo">
                                <h2><span>e</span>-shopper</h2>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,sed do eiusmod tempor</p>
                            </div>
                        </div>
                        <div class="col-sm-7">
                            <div class="col-sm-3">
                                <div class="video-gallery text-center">
                                    <a href="#">
                                        <div class="iframe-img">
                                            <img src="${pageContext.request.contextPath}/ShopPages/Pages/images/home/iframe1.png" alt="" />
                                        </div>
                                        <div class="overlay-icon">
                                            <i class="fa fa-play-circle-o"></i>
                                        </div>
                                    </a>
                                    <p>Circle of Hands</p>
                                    <h2>24 DEC 2014</h2>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="video-gallery text-center">
                                    <a href="#">
                                        <div class="iframe-img">
                                            <img src="${pageContext.request.contextPath}/ShopPages/Pages/images/home/iframe2.png" alt="" />
                                        </div>
                                        <div class="overlay-icon">
                                            <i class="fa fa-play-circle-o"></i>
                                        </div>
                                    </a>
                                    <p>Circle of Hands</p>
                                    <h2>24 DEC 2014</h2>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="video-gallery text-center">
                                    <a href="#">
                                        <div class="iframe-img">
                                            <img src="${pageContext.request.contextPath}/ShopPages/Pages/images/home/iframe3.png" alt="" />
                                        </div>
                                        <div class="overlay-icon">
                                            <i class="fa fa-play-circle-o"></i>
                                        </div>
                                    </a>
                                    <p>Circle of Hands</p>
                                    <h2>24 DEC 2014</h2>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="video-gallery text-center">
                                    <a href="#">
                                        <div class="iframe-img">
                                            <img src="${pageContext.request.contextPath}/ShopPages/Pages/images/home/iframe4.png" alt="" />
                                        </div>
                                        <div class="overlay-icon">
                                            <i class="fa fa-play-circle-o"></i>
                                        </div>
                                    </a>
                                    <p>Circle of Hands</p>
                                    <h2>24 DEC 2014</h2>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="address">
                                <img src="${pageContext.request.contextPath}/ShopPages/Pages/images/home/map.png" alt="" />
                                <p>505 S Atlantic Ave Virginia Beach, VA(Virginia)</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer-widget">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-2">
                            <div class="single-widget">
                                <h2>Dịch vụ</h2>
                                <ul class="nav nav-pills nav-stacked">
                                    <li><a href="#">Hỗ trợ trực tuyến</a></li>
                                    <li><a href="#">Liên hệ</a></li>
                                    <li><a href="#">Tình trạng đơn hàng</a></li>
                                    <li><a href="#">Thay đổi địa điểm</a></li>
                                    <li><a href="#">Câu hỏi thường gặp</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="single-widget">
                                <h2>Mua sắm nhanh</h2>
                                <ul class="nav nav-pills nav-stacked">
                                    <li><a href="#">Áo thun</a></li>
                                    <li><a href="#">Đàn ông</a></li>
                                    <li><a href="#">Phụ nữ</a></li>
                                    <li><a href="#">Thẻ quà tặng</a></li>
                                    <li><a href="#">Giày</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="single-widget">
                                <h2>Chính sách</h2>
                                <ul class="nav nav-pills nav-stacked">
                                    <li><a href="#">Điều khoản sử dụng</a></li>
                                    <li><a href="#">Chính sách bảo mật</a></li>
                                    <li><a href="#">Chính sách hoàn tiền</a></li>
                                    <li><a href="#">Hệ thống thanh toán</a></li>
                                    <li><a href="#">Hệ thống vé</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="single-widget">
                                <h2>Về Shopper</h2>
                                <ul class="nav nav-pills nav-stacked">
                                    <li><a href="#">Thông tin công ty</a></li>
                                    <li><a href="#">Tuyển dụng</a></li>
                                    <li><a href="#">Vị trí cửa hàng</a></li>
                                    <li><a href="#">Chương trình liên kết</a></li>
                                    <li><a href="#">Bản quyền</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-3 col-sm-offset-1">
                            <div class="single-widget">
                                <h2>Đăng ký nhận tin</h2>
                                <form action="#" class="searchform">
                                    <input type="text" placeholder="Địa chỉ email của bạn" />
                                    <button type="submit" class="btn btn-default"><i class="fa fa-arrow-circle-o-right"></i></button>
                                    <p>Nhận các cập nhật mới nhất từ <br />trang web của chúng tôi và tự cập nhật...</p>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer-bottom">
                <div class="container">
                    <div class="row">
                        <p class="pull-left">Bản quyền © 2023 E-SHOPPER Inc. Đã đăng ký bản quyền.</p>
                        <p class="pull-right">Thiết kế bởi <span><a target="_blank" href="http://www.themeum.com">Themeum</a></span></p>
                    </div>
                </div>
            </div>
        </footer>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/price-range.js"></script>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/jquery.scrollUp.min.js"></script>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/jquery.prettyPhoto.js"></script>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/main.js"></script>
    </body>
</html>