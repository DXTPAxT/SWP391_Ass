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
        <jsp:include page="components/header.jsp">
            <jsp:param name="activePage" value="products"/>
        </jsp:include>

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

        <jsp:include page="components/footer.jsp"/>
    </body>
</html>
