
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ page isErrorPage="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Shop | E-Shopper</title>
        <link href="${ctx}/ShopPages/Pages/css/bootstrap.min.css" rel="stylesheet">
        <link href="${ctx}/ShopPages/Pages/css/font-awesome.min.css" rel="stylesheet">
        <link href="${ctx}/ShopPages/Pages/css/prettyPhoto.css" rel="stylesheet">
        <link href="${ctx}/ShopPages/Pages/css/price-range.css" rel="stylesheet">
        <link href="${ctx}/ShopPages/Pages/css/animate.css" rel="stylesheet">
        <link href="${ctx}/ShopPages/Pages/css/main.css" rel="stylesheet">
        <link href="${ctx}/ShopPages/Pages/css/responsive.css" rel="stylesheet">
        <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
        <![endif]-->       
        <link rel="shortcut icon" href="${ctx}/ShopPages/Pages/images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${ctx}/ShopPages/Pages/images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${ctx}/ShopPages/Pages/images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${ctx}/ShopPages/Pages/images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="${ctx}/ShopPages/Pages/images/ico/apple-touch-icon-57-precomposed.png">
    </head><!--/head-->



    <body>
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="contactinfo">
                                <ul class="nav nav-pills">
                                    <li><a href="#"><i class="fa fa-phone"></i> 0337593524</a></li>
                                    <li><a href="#"><i class="fa fa-envelope"></i> CyberBeast@gmail.com</a></li>
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
            </div><!--/header_top-->

            <div class="header-middle"><!--header-middle-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="logo pull-left">
                                <a href="${pageContext.request.contextPath}/HomePages"><img src="${ctx}/ShopPages/Pages/images/home/CyberBeast2.png" style="height: 150px; width: 200px;  "/></a>
                            </div>
                            <div class="btn-group pull-right">
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <div class="shop-menu pull-right">
                                <ul class="nav navbar-nav"  style=" padding-top: 50px ">
                                    <li><a href="cart.html"><i class="fa fa-shopping-cart"></i> Cart</a></li>
                                    <li><a href="#"><i class="fa fa-user"></i> Account</a></li>
                                    <li><a href="checkout.html"><i class="fa fa-crosshairs"></i> Checkout</a></li>
                                    <li><a href="login.html"><i class="fa fa-lock"></i> Login</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/header-middle-->

            <div class="header-bottom"><!--header-bottom-->
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
                                    <li><a href="${pageContext.request.contextPath}/HomePages" class="active">Home</a></li>

                                    <li><a href="${pageContext.request.contextPath}/CategoriesController?service=list" class="active">Products</a></li>
                                    </li> 
                                    <li class="dropdown"><a href="#">Blog<i class=""></i></a>

                                    </li> 
                                    <li><a href="404.html">404</a></li>
                                    <li><a href="contact-us.html">Contact</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="search_box pull-right">
                            <form action="${ctx}/CategoriesController" method="get" style="display: flex;">
                                <input type="hidden" name="service" value="filter"/>
                                <input type="text" name="keyword" class="form-control" placeholder="Insert name product"
                                       value="${not empty param.keyword ? param.keyword : ''}" style="width: 180px;" />

                            </form>
                        </div>
                    </div>
                </div>
            </div><!--/header-bottom-->
        </header><!--/header-->

        <section id="slider"><!--slider-->
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <div id="slider-carousel" class="carousel slide" data-ride="carousel">
                            <!-- Indicators -->
                            <ol class="carousel-indicators">
                                <li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
                                <li data-target="#slider-carousel" data-slide-to="1"></li>
                                <li data-target="#slider-carousel" data-slide-to="2"></li>
                            </ol>

                            <!-- Slides -->
                            <div class="carousel-inner">
                                <!-- Slide 1 -->
                                <div class="item active">
                                    <div class="col-sm-6">
                                        <h1>
                                            <span style="color: red">Cyber</span><span style="color: #111111;">Beast</span>
                                        </h1>
                                        <h3>Best Seller</h3>
                                        <p>Efficient Performance: Intel® Core™ i5/i7 (12th Gen) processor with Intel® Iris® Xe Graphics.</p>
                                        <p>Stunning Display: 14” Full HD (1920x1080) NanoEdge IPS display with ultra-slim bezels.</p>
                                        <p>Premium Portability: Lightweight aluminum chassis, only ~1.3kg and 15.9mm thin—ideal for mobility.</p>

                                        <button type="button" class="btn btn-default get">Get it now</button>
                                    </div>
                                    <div class="col-sm-6 text-center">
                                        <img src="${ctx}/ShopPages/Pages/images/home/nitrov15.png"
                                             style="height: 450px; width: auto; padding-top: 30px;"
                                             class="girl img-responsive" alt="CyberBeast" />
                                    </div>
                                </div>

                                <!-- Slide 2 -->
                                <div class="item">
                                    <div class="col-sm-6">
                                        <h1><span style="color: red">Cyber</span><span style="color: #111111;">Beast</span></h1>
                                        <h3>Best Seller</h3>
                                        <p>Powerful Performance: Intel® Core™ i7-12700F CPU with ASUS TUF Gaming B660 motherboard.</p>
                                        <p>Graphics Excellence: NVIDIA® GeForce RTX™ 3060 12GB for smooth 1440p gaming and rendering.</p>
                                        <p>Solid Setup: 16GB DDR4 RAM, 512GB NVMe SSD, 650W PSU, housed in ASUS Prime case with RGB fans.</p>

                                        <button type="button" class="btn btn-default get">Get it now</button>
                                    </div>
                                    <div class="col-sm-6 text-center">
                                        <img src="${ctx}/ShopPages/Pages/images/home/1.png"
                                             style="height: 400px; width: auto; padding-top: 20px;"
                                             class="girl img-responsive" alt="Responsive Design" />

                                    </div>
                                </div>

                                <!-- Slide 3 -->
                                <div class="item">
                                    <div class="col-sm-6">
                                        <h1><span style="color: red">Cyber</span><span style="color: #111111;">Beast</span></h1>
                                        <h3>Recommend Main</h3>
                                        <p>Extreme Compatibility: Supports Intel® 12th & 13th Gen processors with LGA1700 socket, built for gaming and productivity.</p>
                                        <p>Next-Gen Memory: Ready for DDR5 RAM up to 6400MHz (OC), 4 DIMM slots for up to 128GB high-speed memory.</p>
                                        <p>High-Speed Connectivity: PCIe 5.0 x16 slot for latest GPUs, 4x M.2 NVMe slots, and Wi-Fi 6E with Bluetooth 5.2 onboard.</p>
                                        <button type="button" class="btn btn-default get">Get it now</button>
                                    </div>
                                    <div class="col-sm-6 text-center">
                                        <img src="${ctx}/ShopPages/Pages/images/home/asus.png"
                                             style="height: 450px; width: auto; padding-top: 20px;"
                                             class="girl img-responsive" alt="Free Template" />

                                    </div>
                                </div>
                            </div>

                            <!-- Controls -->
                            <a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
                                <i class="fa fa-angle-left"></i>
                            </a>
                            <a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </section><!--/slider-->


        <section>
            <div class="container">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="left-sidebar">
                            <h2 class="title text-center">CATEGORY</h2>
                            <div class="panel-group category-products" id="accordian">
                                <c:forEach var="comp" items="${Components}">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title" style="display: flex; justify-content: space-between; align-items: center;">
                                                <!-- Link: lọc theo Component -->
                                                <a href="${ctx}/CategoriesController?service=filter&component=${fn:escapeXml(comp.componentName)}" style="flex-grow: 1;">
                                                    ${comp.componentName}
                                                </a>

                                                <!-- Dấu + để mở brand list -->
                                                <a data-toggle="collapse" href="#comp${comp.componentID}" style="margin-left: 10px;">
                                                    <i class="fa fa-plus"></i> <!-- luôn là dấu + -->
                                                </a>
                                            </h4>
                                        </div>

                                        <!-- Brand list bên trong component -->
                                        <div id="comp${comp.componentID}" class="panel-collapse collapse">
                                            <div class="panel-body">
                                                <ul>
                                                    <c:forEach var="item" items="${BrandWithComponent}">
                                                        <c:if test="${item.componentID == comp.componentID}">
                                                            <li>
                                                                <a href="${ctx}/CategoriesController?service=filter&component=${fn:escapeXml(comp.componentName)}&brand=${fn:escapeXml(item.brandName)}">
                                                                    ${item.brandName}
                                                                </a>
                                                            </li>
                                                        </c:if>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>



                            <div class="brands_products"><!--brands_products-->
                                <h2>Brands</h2>
                                <div class="brands-name">
                                    <ul class="nav nav-pills nav-stacked">
                                        <c:forEach var="brand" items="${listBrand}">
                                            <li>
                                                <a href="${ctx}/CategoriesController?service=filter&brand=${fn:escapeXml(brand.brandName)}">
                                                    ${brand.brandName}
                                                </a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div><!--/brands_products-->

                            <div class="brands_products"><!-- price range styled like brands box -->
                                <h2 style="color: orange; font-weight: bold; text-align: center; position: relative;">
                                    <span style="background: #fff; padding: 0 10px; z-index: 2; position: relative;">PRICE RANGE</span>
                                </h2>
                                <div class="brands-name" style="border: 1px solid #f0f0f0; padding: 20px;">
                                    <form action="${ctx}/CategoriesController" method="get">
                                        <input type="hidden" name="service" value="filter" />
                                        <input type="hidden" name="component" value="${currentComponent}" />
                                        <input type="hidden" name="brand" value="${currentBrand}" />

                                        <div class="form-group">
                                            <label style="font-weight: bold;">Min Price:</label>
                                            <input type="number" name="minPrice" class="form-control"
                                                   placeholder="e.g. 10000000"
                                                   value="${not empty minPrice ? minPrice : ''}" min="0" />
                                        </div>

                                        <div class="form-group" style="margin-top: 10px;">
                                            <label style="font-weight: bold;">Max Price:</label>
                                            <input type="number" name="maxPrice" class="form-control"
                                                   placeholder="e.g. 50000000"
                                                   value="${not empty maxPrice ? maxPrice : ''}" min="0" />
                                        </div>

                                        <button type="submit" class="btn btn-warning btn-block" style="margin-top: 15px;">
                                            Search by Price
                                        </button>
                                    </form>
                                </div>
                            </div><!-- /price-range -->

                            <div class="brands_products"><!-- build pc -->
                                <h2 style="color: green; font-weight: bold; text-align: center; position: relative;">
                                    <span style="background: #fff; padding: 0 10px; z-index: 2; position: relative;">BUILD PC</span>
                                </h2>
                                <div class="brands-name" style="border: 1px solid #f0f0f0; padding: 20px;">
                                    <ul class="nav nav-pills nav-stacked">
                                        <li>
                                            <a href="${ctx}/BuildPC?service=list">
                                                <i class="fa fa-cogs"></i> Customize Your PC
                                            </a>
                                        </li>
                                        <li>
                                            <a href="${ctx}/ShopPages/Pages/HomePages">
                                                <i class="fa fa-book"></i> Build Guide
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div><!-- /build pc -->



                        </div>
                    </div>

                    <div class="col-sm-9 padding-right">
                        <div class="features_items"><!--features_items-->
                            <h2 class="title text-center">Products </h2>

                            <c:forEach var="product" items="${requestScope.data}">
                                <div class="col-sm-4">
                                    <div class="product-image-wrapper">
                                        <div class="single-products">
                                            <div class="productinfo text-center">
                                                <a href="${ctx}/CategoriesController?service=detail&categoryID=${product.categoryID}">
                                                    <img src="${ctx}/ShopPages/Pages/images/shop/product12.jpg" alt="" />
                                                    <p>${product.brandName}</p>
                                                    <h2>
                                                        <fmt:formatNumber value="${product.price}" type="number" groupingUsed="true" /> VND
                                                    </h2>
                                                    <p>${product.categoryName}</p>
                                                </a>
                                                <button class="add-to-cart"
                                                        data-userid="${user.userID}"
                                                        data-productid="${product.categoryID}"
                                                        data-name="${product.categoryName}"
                                                        data-image="${ctx}/ShopPages/Pages/images/shop/product12.jpg"
                                                        data-price="${product.price}"
                                                        class="btn btn-default add-to-cart">
                                                    <i class="fa fa-shopping-cart"></i>
                                                    Add to cart
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                            <c:if test="${empty requestScope.data}">
                                <p>Không có sản phẩm nào!</p>
                            </c:if>

                            <c:if test="${totalPages gt 1}">
                                <div class="pagination-area text-center" style="margin-top: 40px; clear: both;">
                                    <ul class="pagination" style="display: inline-block; float: none;">
                                        <c:forEach begin="1" end="${totalPages}" var="i">
                                            <c:url var="pageURL" value="CategoriesController">
                                                <c:param name="service" value="filter" />
                                                <c:param name="page" value="${i}" />
                                                <c:if test="${not empty currentBrand}">
                                                    <c:param name="brand" value="${currentBrand}" />
                                                </c:if>
                                                <c:if test="${not empty currentComponent}">
                                                    <c:param name="component" value="${currentComponent}" />
                                                </c:if>
                                                <c:if test="${not empty currentKeyword}">
                                                    <c:param name="keyword" value="${currentKeyword}" />
                                                </c:if>
                                                <c:if test="${not empty minPrice}">
                                                    <c:param name="minPrice" value="${minPrice}" />
                                                </c:if>
                                                <c:if test="${not empty maxPrice}">
                                                    <c:param name="maxPrice" value="${maxPrice}" />
                                                </c:if>
                                            </c:url>
                                            <li class="${i == currentPage ? 'active' : ''}">
                                                <a href="${pageURL}">${i}</a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </c:if>


                        </div><!--features_items-->
                    </div>
                </div>
            </div>
        </section>

        <footer id="footer"><!--Footer-->
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
                                            <img src="images/home/iframe1.png" alt="" />
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
                                            <img src="images/home/iframe2.png" alt="" />
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
                                            <img src="images/home/iframe3.png" alt="" />
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
                                            <img src="images/home/iframe4.png" alt="" />
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
                                <img src="images/home/map.png" alt="" />
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
                                <h2>Service</h2>
                                <ul class="nav nav-pills nav-stacked">
                                    <li><a href="">Online Help</a></li>
                                    <li><a href="">Contact Us</a></li>
                                    <li><a href="">Order Status</a></li>
                                    <li><a href="">Change Location</a></li>
                                    <li><a href="">FAQ’s</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="single-widget">
                                <h2>Quock Shop</h2>
                                <ul class="nav nav-pills nav-stacked">
                                    <li><a href="">T-Shirt</a></li>
                                    <li><a href="">Mens</a></li>
                                    <li><a href="">Womens</a></li>
                                    <li><a href="">Gift Cards</a></li>
                                    <li><a href="">Shoes</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="single-widget">
                                <h2>Policies</h2>
                                <ul class="nav nav-pills nav-stacked">
                                    <li><a href="">Terms of Use</a></li>
                                    <li><a href="">Privecy Policy</a></li>
                                    <li><a href="">Refund Policy</a></li>
                                    <li><a href="">Billing System</a></li>
                                    <li><a href="">Ticket System</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="single-widget">
                                <h2>About Shopper</h2>
                                <ul class="nav nav-pills nav-stacked">
                                    <li><a href="">Company Information</a></li>
                                    <li><a href="">Careers</a></li>
                                    <li><a href="">Store Location</a></li>
                                    <li><a href="">Affillate Program</a></li>
                                    <li><a href="">Copyright</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-3 col-sm-offset-1">
                            <div class="single-widget">
                                <h2>About Shopper</h2>
                                <form action="#" class="searchform">
                                    <input type="text" placeholder="Your email address" />
                                    <button type="submit" class="btn btn-default"><i class="fa fa-arrow-circle-o-right"></i></button>
                                    <p>Get the most recent updates from <br />our site and be updated your self...</p>
                                </form>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <div class="footer-bottom">
                <div class="container">
                    <div class="row">
                        <p class="pull-left">Copyright © 2013 E-Shopper. All rights reserved.</p>
                        <p class="pull-right">Designed by <span><a target="_blank" href="http://www.themeum.com">Themeum</a></span></p>
                    </div>
                </div>
            </div>

        </footer><!--/Footer-->

        <script src="${ctx}/ShopPages/Pages/js/jquery.js"></script>
        <script src="${ctx}/ShopPages/Pages/js/bootstrap.min.js"></script>
        <script src="${ctx}/ShopPages/Pages/js/jquery.scrollUp.min.js"></script>
        <script src="${ctx}/ShopPages/Pages/js/price-range.js"></script>
        <script src="${ctx}/ShopPages/Pages/js/jquery.prettyPhoto.js"></script>
        <script src="${ctx}/ShopPages/Pages/js/main.js"></script>
        <!-- SweetAlert2 CDN -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <!-- Kích hoạt carousel nếu cần -->
        <script>
            $(document).ready(function () {
                $('#slider-carousel').carousel(); // Khởi động carousel thủ công
            });
        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const form = document.querySelector("form[action='Product']");
                const minInput = document.getElementById("minPrice");
                const maxInput = document.getElementById("maxPrice");

                // Ngăn nhập chữ cái và ký tự không hợp lệ
                function preventNonNumericInput(e) {
                    const invalidChars = ['e', 'E', '+', '-', ','];
                    if (invalidChars.includes(e.key)) {
                        e.preventDefault();
                    }
                }

                minInput.addEventListener('keydown', preventNonNumericInput);
                maxInput.addEventListener('keydown', preventNonNumericInput);

                // Validate khi submit form
                form.addEventListener("submit", function (e) {
                    const min = parseInt(minInput.value);
                    const max = parseInt(maxInput.value);

                    if (isNaN(min) || isNaN(max)) {
                        alert("Please enter both minimum and maximum prices.");
                        e.preventDefault();
                        return;
                    }

                    if (min < 0 || max < 0) {
                        alert("Price cannot be negative.");
                        e.preventDefault();
                        return;
                    }

                    if (max < min) {
                        alert("Maximum price must be greater than or equal to minimum price.");
                        e.preventDefault();
                    }
                });
            });
        </script>

        <script>
            document.querySelectorAll('.add-to-cart').forEach(btn => {
                btn.addEventListener('click', () => {
                    const userID = btn.dataset.userid;
                    const productID = btn.dataset.productid;
                    const name = btn.dataset.name;
                    const image = btn.dataset.image;
                    const price = btn.dataset.price;
                    console.log("Tên:", name, "Ảnh:", image);
                    addItem(userID, productID, name, image, price);
                });
            });

            function addItem(userID, productID, productName, productImageURL, productPrice) {
                confirmAddProductToCart(productName, productImageURL, productPrice).then(quantity => {
                    if (quantity !== null && quantity > 0) {
                        const params = new URLSearchParams();
                        params.append("userID", userID);
                        params.append("productID", productID);
                        params.append("quantity", quantity);

                        fetch('AddCartItem', {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/x-www-form-urlencoded'
                            },
                            body: params.toString()
                        })
                                .then(response => response.text())
                                .then(data => {
                                    if (data.trim() === 'success') {
                                        Swal.fire("Thành công", "Sản phẩm đã được thêm vào giỏ", "success");
                                        // updateCartTotal(); // nếu có
                                    } else {
                                        Swal.fire("Lỗi", "Thêm thất bại", "error");
                                    }
                                });
                    } else {
                        console.log("Người dùng hủy thêm sản phẩm hoặc nhập sai số lượng");
                    }
                });
            }


            function confirmAddProductToCart(productName, productImageURL, productPrice) {
                return Swal.fire({
                    title: "Thêm sản phẩm vào giỏ hàng",
                    html: `
    <div style="text-align: center; margin-bottom: 10px;">
        <img src="` + productImageURL + `" alt="` + productName + `" style="width: 80px; height: 80px; object-fit: contain; margin-bottom: 5px;" />
        <div style="font-weight: bold; font-size: 16px;">` + productName + `</div>
        <div style="margin-top: 5px; font-size: 14px; color: #fd7e14;">Giá: 
            <span id="unitPrice">` + Number(productPrice).toLocaleString('vi-VN', {maximumFractionDigits: 0}).replace(/\./g, ',') + `</span> VND
        </div>
    </div>
    <div style="display: flex; align-items: center; justify-content: center; gap: 5px;">
        <button id="decreaseBtn" class="swal2-styled" style="padding: 10px; font-size: 18px;">–</button>
        <input id="quantity" type="number" min="1" value="1"
               class="swal2-input"
               style="width: 60px; text-align: center; font-size: 18px; height: 40px; padding: 0; margin: 0;" />
        <button id="increaseBtn" class="swal2-styled" style="padding: 10px; font-size: 18px;">+</button>
    </div>
    <div style="margin-top: 5px; font-size: 18px; color: #e8590c; font-weight: bold;">Tổng: 
        <span id="totalPrice">` + Number(productPrice).toLocaleString('vi-VN', {maximumFractionDigits: 0}).replace(/\./g, ',') + `</span> VND
    </div>
`,

                    icon: "question",
                    showCancelButton: true,
                    confirmButtonText: "<span style='font-size: 18px;'>Thêm</span>",
                    cancelButtonText: "<span style='font-size: 18px;'>Hủy</span>",
                    didOpen: () => {
                        const qtyInput = document.getElementById('quantity');
                        const totalElem = document.getElementById('totalPrice');

                        const updateTotal = () => {
                            const qty = parseInt(qtyInput.value);
                            totalElem.textContent = (qty * productPrice).toLocaleString();
                        };

                        document.getElementById('increaseBtn').addEventListener('click', () => {
                            qtyInput.value = parseInt(qtyInput.value) + 1;
                            updateTotal();
                        });

                        document.getElementById('decreaseBtn').addEventListener('click', () => {
                            const current = parseInt(qtyInput.value);
                            if (current > 1) {
                                qtyInput.value = current - 1;
                                updateTotal();
                            }
                        });

                        qtyInput.addEventListener('input', () => {
                            const val = parseInt(qtyInput.value);
                            if (val >= 1)
                                updateTotal();
                        });
                    },
                    preConfirm: () => {
                        const qty = document.getElementById('quantity').value;
                        if (!qty || qty <= 0) {
                            Swal.showValidationMessage("Vui lòng nhập số lượng hợp lệ");
                            return false;
                        }
                        return parseInt(qty);
                    }
                }).then(result => {
                    if (result.isConfirmed) {
                        return result.value;
                    }
                    return null;
                });
            }


        </script>

    </body>
</html>
