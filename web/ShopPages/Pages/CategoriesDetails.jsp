<%-- 
    Document   : ProductDetail.jsp
    Created on : May 24, 2025, 7:32:03 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Product Details | E-Shopper</title>
        <link href="${ctx}/ShopPages/Pages/css/bootstrap.min.css" rel="stylesheet">
        <link href="${ctx}/ShopPages/Pages/css/font-awesome.min.css" rel="stylesheet">
        <link href="${ctx}/ShopPages/Pages/css/prettyPhoto.css" rel="stylesheet">
        <link href="${ctx}/ShopPages/Pages/css/price-range.css" rel="stylesheet">
        <link href="${ctx}/ShopPages/Pages/css/animate.css" rel="stylesheet">
        <link href="${ctx}/ShopPages/Pages/css/main.css" rel="stylesheet">
        <link href="${ctx}/ShopPages/Pages/css/responsive.css" rel="stylesheet">
        <link href="${ctx}/ShopPages/Pages/css/custom.css?v=1.0.14" rel="stylesheet">
        <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
        <![endif]-->       
        <link rel="shortcut icon" href="images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
        <style>
            .price-box {
                background-color: #ebf7ff;
                width: 240px;
                padding: 15px;
                margin-bottom: 20px;
                border-radius: 8px;
                border: #a3acff 1px solid;
            }
            .price-box span {
                margin-bottom: 0;
                color: #000;
                font-weight: 600;
                font-size: 2.4rem;
            }
            .price-box p {
                margin-bottom: 0;
            }
            .warranty-card-list {
                display: flex;
                flex-wrap: wrap;
                gap: 16px;
            }
            .warranty-card {
                background: #f7faff;
                border: 2px solid #e0e7ff;
                border-radius: 12px;
                padding: 18px 22px;
                min-width: 220px;
                cursor: pointer;
                transition: border 0.2s, box-shadow 0.2s;
                box-shadow: 0 2px 8px 0 rgba(31, 38, 135, 0.07);
                margin-bottom: 0;
            }
            .warranty-card-content {
                font-size: 15px;
                color: #222;
            }
            .warranty-card input[type="radio"]:checked + .warranty-card-content,
            .warranty-card.selected {
                border-color: #4f8cff;
                background: #eaf3ff;
                box-shadow: 0 4px 16px 0 rgba(79, 140, 255, 0.08);
            }
            .warranty-card:hover {
                border-color: #4f8cff;
            }
            .warranty-price {
                color: #e53935;
                font-size: 1.5rem;
                font-weight: bold;
                margin-top: 6px;
            }


            .warranty-card-list {
                display: flex;
                flex-wrap: wrap;
                gap: 16px;
            }
            .warranty-card {
                background: #f7faff;
                border: 2px solid #e0e7ff;
                border-radius: 12px;
                padding: 18px 22px;
                min-width: 220px;
                cursor: pointer;
                transition: border 0.2s, box-shadow 0.2s;
                box-shadow: 0 2px 8px 0 rgba(31, 38, 135, 0.07);
                margin-bottom: 0;
            }
            .warranty-card-content {
                font-size: 15px;
                color: #222;
            }
            .warranty-card input[type="radio"]:checked + .warranty-card-content,
            .warranty-card.selected {
                border-color: #4f8cff;
                background: #eaf3ff;
                box-shadow: 0 4px 16px 0 rgba(79, 140, 255, 0.08);
            }
            .warranty-card:hover {
                border-color: #4f8cff;
            }
            .warranty-price {
                color: #e53935;
                font-size: 1.5rem;
                font-weight: bold;
                margin-top: 6px;
            }

            .star-rating .fa-star {
                font-size: 18px;
                margin-right: 2px;
            }
            .star-rating .fa-star.star-gold {
                color: #FFD700;
            }
            .star-rating .fa-star.star-gray {
                color: #ccc;
            }
        </style>
    </head><!--/head-->

    <body>

        <jsp:include page="/ShopPages/Pages/components/header.jsp" />

        <!-- Breadcrumb navigation -->
        <div class="container mt-3">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${ctx}/index.jsp">Home</a></li>
                    <li class="breadcrumb-item">
                        <a href="${ctx}/CategoriesController?service=filter&component=${product.componentName}">${product.componentName}</a>
                    </li>
                    <li class="breadcrumb-item">
                        <a href="${ctx}/CategoriesController?service=filter&component=${product.componentName}&brand=${product.brandName}">${product.brandName}</a>
                    </li>
                    <li class="breadcrumb-item active" aria-current="page">${product.categoryName}</li>
                </ol>
            </nav>
        </div>

        <section>
            <div class="container">
                <div class="row">
                    <!-- Bỏ hoàn toàn left-sidebar ở đây -->
                    <div class="col-sm-12 padding-right">
                        <div class="product-details"><!--product-details-->
                            <div class="container mt-5">
                                <div class="row">
                                    <div class="col-md-6 mb-4 mb-md-0">
                                        <h2>${product.categoryName}</h2>
                                        <img src="${ctx}/ShopPages/Pages/images/product-details/1.jpg" class="img-fluid" alt="Product Image">
                                    </div>
                                    <div class="col-md-6">
                                        <div class="price-box">
                                            <p>Price:</p>
                                            <p>
                                                <span>
                                                    <fmt:formatNumber value="${product.price}" type="number" groupingUsed="true" /> Đ
                                                </span>
                                            </p>
                                        </div>
                                        <p><strong>Brand:</strong> ${product.brandName}</p>
                                        <p><strong>Description:</strong></p>
                                        <p class="text-muted" id="descShort">${product.description}</p>
                                        <button class="see-more-btn" id="seeMoreBtn" onclick="toggleDescription()">See more</button>
                                        <!-- Warranty card section in English -->
                                        <c:if test="${not empty warrantyList}">
                                            <div class="warranty-section" style="margin-top:20px;">
                                                <h5>Warranty Policy</h5>
                                                <div class="warranty-card-list">
                                                    <c:forEach var="wd" items="${warrantyList}">
                                                        <label class="warranty-card">
                                                            <input type="radio" name="warrantyOption" value="${wd.warrantyDetailID}" style="display:none;">
                                                            <div class="warranty-card-content">
                                                                <div><b>Period:</b> ${wd.warranty.warrantyPeriod} months</div>
                                                                <c:if test="${not empty wd.warranty.description}">
                                                                    <div><b>Description:</b> ${wd.warranty.description}</div>
                                                                </c:if>
                                                                <c:if test="${not empty wd.brandName}">
                                                                    <div><b>Brand:</b> ${wd.brandName}</div>
                                                                </c:if>
                                                                <c:if test="${not empty wd.componentName}">
                                                                    <div><b>Component:</b> ${wd.componentName}</div>
                                                                </c:if>
                                                                <div class="warranty-price"><fmt:formatNumber value="${wd.price}" type="number" groupingUsed="true" /> VND</div>
                                                            </div>
                                                        </label>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </c:if>
                                        <button class="btn btn-success mt-3"
                                                data-userid="${user.userId}"
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
                    </div><!--/product-details-->
                    <div class="category-tab shop-details-tab"><!--category-tab-->
                        <div class="col-sm-12">
                            <ul class="nav nav-tabs">
                                <li class="active">
                                    <a href="#reviews" data-toggle="tab">
                                        Reviews (
                                        <c:choose>
                                            <c:when test="${not empty feedbackList}">${fn:length(feedbackList)}</c:when>
                                            <c:otherwise>0</c:otherwise>
                                        </c:choose>
                                        )
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div class="tab-content">

                            <div class="tab-pane fade active in" id="reviews" >

                                <!-- PHẦN REVIEW: Hiển thị danh sách feedback liên quan đến category này -->
                                <div class="review-section">
                                    <h3>Feedback / Đánh giá sản phẩm</h3>
                                    <c:choose>
                                        <c:when test="${not empty feedbackList}">
                                            <c:forEach var="fb" items="${feedbackList}">
                                                <div class="feedback-item" style="border:1px solid #ccc; margin-bottom:10px; padding:10px; border-radius:8px; background:#fafbfc;">
                                                    <p><b>Name:</b> 
                                                        <c:choose>
                                                            <c:when test="${not empty fb.fullname}">${fb.fullname}</c:when>
                                                            <c:otherwise>Unknown</c:otherwise>
                                                        </c:choose>
                                                    </p>
                                                    <p><b>Date:</b>
                                                        <c:choose>
                                                            <c:when test="${not empty fb.createdAt}">
                                                                <span>${fn:substringBefore(fb.createdAt, ' ')} lúc ${fn:substringAfter(fb.createdAt, ' ')}</span>
                                                            </c:when>
                                                            <c:otherwise>Unknown</c:otherwise>
                                                        </c:choose>
                                                    </p>
                                                    <p><b>Đánh giá:</b> 
                                                        <span class="star-rating">
                                                            <c:forEach begin="1" end="5" var="i">
                                                                <c:choose>
                                                                    <c:when test="${i <= fb.rate}">
                                                                        <i class="fa fa-star star-gold"></i>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <i class="fa fa-star star-gray"></i>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                            (${fb.rate} sao)
                                                        </span>
                                                    </p>
                                                    <p>${fb.content}</p>
                                                    <div>
                                                        <c:if test="${sessionScope.user != null && (sessionScope.user.userID == fb.userID || sessionScope.user.roleID == 1)}">
                                                            <a href="${pageContext.request.contextPath}/feedback?action=edit&id=${fb.feedbackID}&categoryID=${categoryID}" class="btn btn-primary btn-sm">Sửa</a>
                                                            <a href="${pageContext.request.contextPath}/feedback?action=delete&id=${fb.feedbackID}&categoryID=${categoryID}" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc chắn muốn xóa feedback này?')">Xóa</a>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <p>Chưa có feedback nào cho sản phẩm này.</p>
                                        </c:otherwise>
                                    </c:choose>
                                </div>

                            </div>

                        </div>
                    </div><!--/category-tab-->

                    <div class="recommended_items"><!--recommended_items-->
                        <h2 class="title text-center">recommended items</h2>

                        <div id="recommended-item-carousel" class="carousel slide" data-ride="carousel">
                            <div class="carousel-inner">
                                <div class="item active">	
                                    <div class="col-sm-4">
                                        <div class="product-image-wrapper">
                                            <div class="single-products">
                                                <div class="productinfo text-center">
                                                    <img src="images/home/recommend1.jpg" alt="" />
                                                    <h2>$56</h2>
                                                    <p>Easy Polo Black Edition</p>
                                                    <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="product-image-wrapper">
                                            <div class="single-products">
                                                <div class="productinfo text-center">
                                                    <img src="images/home/recommend2.jpg" alt="" />
                                                    <h2>$56</h2>
                                                    <p>Easy Polo Black Edition</p>
                                                    <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="product-image-wrapper">
                                            <div class="single-products">
                                                <div class="productinfo text-center">
                                                    <img src="images/home/recommend3.jpg" alt="" />
                                                    <h2>$56</h2>
                                                    <p>Easy Polo Black Edition</p>
                                                    <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
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
                                                    <img src="images/home/recommend1.jpg" alt="" />
                                                    <h2>$56</h2>
                                                    <p>Easy Polo Black Edition</p>
                                                    <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="product-image-wrapper">
                                            <div class="single-products">
                                                <div class="productinfo text-center">
                                                    <img src="images/home/recommend2.jpg" alt="" />
                                                    <h2>$56</h2>
                                                    <p>Easy Polo Black Edition</p>
                                                    <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="product-image-wrapper">
                                            <div class="single-products">
                                                <div class="productinfo text-center">
                                                    <img src="images/home/recommend3.jpg" alt="" />
                                                    <h2>$56</h2>
                                                    <p>Easy Polo Black Edition</p>
                                                    <button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
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
                    </div><!--/recommended_items-->

                </div>
            </div>
        </div>
    </section>

    <jsp:include page="/ShopPages/Pages/components/footer.jsp" />


    <script>
        // Highlight thẻ được chọn (toggle selection)
        document.addEventListener('DOMContentLoaded', function () {
            const cards = document.querySelectorAll('.warranty-card');
            let selectedCard = null;
            cards.forEach(card => {
                card.addEventListener('click', function (e) {
                    // Prevent double event on label/input
                    if (e.target.tagName === 'INPUT')
                        return;
                    if (card.classList.contains('selected')) {
                        card.classList.remove('selected');
                        card.querySelector('input[type="radio"]').checked = false;
                        selectedCard = null;
                    } else {
                        cards.forEach(c => {
                            c.classList.remove('selected');
                            c.querySelector('input[type="radio"]').checked = false;
                        });
                        card.classList.add('selected');
                        card.querySelector('input[type="radio"]').checked = true;
                        selectedCard = card;
                    }
                });
            });
        });
    </script>

    <script>
        function toggleDescription() {
            var desc = document.getElementById('descShort');
            var btn = document.getElementById('seeMoreBtn');
            desc.classList.toggle('expanded');
            if (desc.classList.contains('expanded')) {
                btn.textContent = 'Thu gọn';
            } else {
                btn.textContent = 'Xem chi tiết';
            }
        }
    </script>
    <script src="js/jquery.js"></script>
    <script src="js/price-range.js"></script>
    <script src="js/jquery.scrollUp.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>
</body>
</html>
