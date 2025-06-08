<%-- 
    Document   : homepages
    Created on : May 22, 2025, 11:04:18 AM
    Author     : PC
--%>

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
        <%@ include file="components/header.jsp" %>
        <!--/header-->

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
                            




                        </div>
                    </div>

                    <div class="col-sm-9 padding-right">

                        <!-- PC Section -->
                        <div class="features_items" id="pc">
                            <h2 class="title text-center" style="margin-top: 30px">PC Products</h2>
                            <div class="row">
                                <c:forEach var="product" items="${pcProducts}">
                                    <div class="col-sm-4">
                                        <div class="product-image-wrapper">
                                            <div class="productinfo text-center">
                                                <img src="${pageContext.request.contextPath}/ShopPages/Pages/images/anhproduct/1.png" class="card-img-top" alt="${product.categoryName}">
                                                <div class="card-body">
                                                    <h5 class="card-title">${product.categoryName}</h5>
                                                    <p class="card-text">Brand: ${product.brandName}</p>
                                                    <p class="card-text">Price: <fmt:formatNumber value="${product.price}" type="currency"/></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>

                            <c:if test="${empty pcProducts}">
                                <p class="text-center">Không có sản phẩm nào!</p>
                            </c:if>

                            <!-- Pagination for PC -->
                            <div class="text-center">
                                <ul class="pagination">
                                    <c:forEach begin="1" end="${totalPagesPC}" var="i">
                                        <li class="page-item ${i eq currentPagePC ? 'active' : ''}">
                                     <a class="page-link" href="HomePages?pagePC=${i}&pageLaptop=${currentPageLaptop}#pc">${i}</a>

                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>

                            <!-- VIEW MORE Button -->
                            <div class="category-tab">
                                <div class="col-sm-12 text-center">
                                    <a href="${pageContext.request.contextPath}/CategoriesController?service=filter&component=pc" class="btn btn-warning" style="margin-top: 20px;">VIEW MORE</a>
                                </div>
                            </div>
                        </div>

                        <!-- Laptop Section -->
                        <div class="features_items" id="laptop">
                            <h2 class="title text-center" style="margin-top: 30px">Laptop Products</h2>
                            <div class="row">
                                <c:forEach var="product" items="${laptopProducts}">
                                    <div class="col-sm-4">
                                        <div class="product-image-wrapper">
                                            <div class="productinfo text-center">
                                                <img src="${pageContext.request.contextPath}/ShopPages/Pages/images/anhproduct/1.png" class="card-img-top" alt="${product.categoryName}">
                                                <div class="card-body">
                                                    <h5 class="card-title">${product.categoryName}</h5>
                                                    <p class="card-text">Brand: ${product.brandName}</p>
                                                    <p class="card-text">Price: <fmt:formatNumber value="${product.price}" type="currency"/></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>

                            <c:if test="${empty laptopProducts}">
                                <p class="text-center">Không có sản phẩm nào!</p>
                            </c:if>

                            <!-- Pagination for Laptop -->
                            <div class="text-center">
                                <ul class="pagination">
                                    <c:forEach begin="1" end="${totalPagesLaptop}" var="i">
                                        <li class="page-item ${i eq currentPageLaptop ? 'active' : ''}">
                                            <a class="page-link" href="HomePages?pageLaptop=${i}&pagePC=${currentPagePC}#laptop">${i}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>

                            <!-- VIEW MORE Button -->
                            <div class="category-tab">
                                <div class="col-sm-12 text-center">
                                    <a href="${pageContext.request.contextPath}/CategoriesController?service=filter&component=laptop" class="btn btn-warning" style="margin-top: 20px;">VIEW MORE</a>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>
    </section>

    <%@ include file="components/footer.jsp" %>
    <script src="${ctx}/ShopPages/Pages/js/jquery.js"></script>
    <script src="${ctx}/ShopPages/Pages/js/bootstrap.min.js"></script>
    <script src="${ctx}/ShopPages/Pages/js/jquery.scrollUp.min.js"></script>
    <script src="${ctx}/ShopPages/Pages/js/price-range.js"></script>
    <script src="${ctx}/ShopPages/Pages/js/jquery.prettyPhoto.js"></script>
    <script src="${ctx}/ShopPages/Pages/js/main.js"></script>

    <!-- Kích hoạt carousel nếu cần -->
    <script>
        window.onload = function () {
            const urlParams = new URLSearchParams(window.location.search);
            if (urlParams.has('pagePC')) {
                document.getElementById('pc').scrollIntoView({ behavior: 'smooth' });
            } else if (urlParams.has('pageLaptop')) {
                document.getElementById('laptop').scrollIntoView({ behavior: 'smooth' });
            }
        }
        $(document).ready(function () {
            $('#slider-carousel').carousel(); // Khởi động carousel thủ công
        });
    </script>
</body>
</html>
