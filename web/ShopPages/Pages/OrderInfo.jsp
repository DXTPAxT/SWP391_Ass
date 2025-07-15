<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Cart | E-Shopper</title>
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/prettyPhoto.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/price-range.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/animate.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/main.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/responsive.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/custom.css?v=1.0.22" rel="stylesheet">
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
            .cart-card-header {
                display: flex;
                gap: 20px;
                align-items: flex-start;
            }

            .card-left {
                flex: 0 0 250px;  /* Cố định width */
                width: 250px;     /* Cố định width */
                word-break: break-word;
            }

            .card-right {
                flex: 1;          /* Chiếm phần còn lại */
                max-width: none;  /* Xoá max-width 70% */
            }

            .card-left img {
                max-width: 100%;
                height: auto;
            }

            .category-name {
                word-wrap: break-word;
            }

            .card-right {
                flex: 0 0 70%;
                max-width: 70%;
            }
        </style>
    </head>

    <body>
        <jsp:include page="/ShopPages/Pages/components/header.jsp"/>

        <section id="order_details">
            <div class="container">

                <!-- Breadcrumb -->
                <div class="breadcrumbs">
                    <ol class="breadcrumb">
                        <li><a href="${pageContext.request.contextPath}/HomePages">Home</a></li>
                        <li class="active">Order #${order.orderCode}</li>
                    </ol>
                </div>

                <!-- Order Info -->
                <div class="review-payment">
                    <h2>Order Details</h2>
                </div>

                <!-- Order Items List -->
                <div class="cart-cards">
                    <c:forEach var="item" items="${order.orderItems}">
                        <div class="cart-card clearfix">
                            <div class="cart-card-header d-flex">
                                <div class="card-left">
                                    <img src="${pageContext.request.contextPath}/ShopPages/Pages/images/cart/two.png" alt="Product">
                                    <h4 class="category-name">${item.category.categoryName}</h4>
                                </div>

                                <div class="card-right">
                                    <!-- Order Details for this Item -->
                                    <h5>Order Details:</h5>
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th>ProductID</th>
                                                <th>Unit Price</th>
                                                <th>Warranty Price</th>
                                                <th>Status</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="detail" items="${item.orderDetailList}">
                                                <tr>
                                                    <td>${detail.productID}</td>
                                                    <td><fmt:formatNumber value="${detail.unitPrice}" type="number" groupingUsed="true"/> VND</td>
                                                    <td><fmt:formatNumber value="${detail.warrantyPrice}" type="number" groupingUsed="true"/> VND</td>
                                                    <td>${detail.status}</td>
                                                    <td>
                                                        <c:if test="${detail.status == 1}">
                                                            <a href="#" class="btn btn-primary mt-0">Activate Warranty</button>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <hr/>
                    </c:forEach>
                </div>

                <!-- Total Summary -->
                <table class="table table-condensed total-result">
                    <tr>
                        <td>Sub Total</td>
                        <td><fmt:formatNumber value="${order.totalAmount}" type="number" groupingUsed="true"/> VND</td>
                    </tr>
                    <tr class="shipping-cost">
                        <td>Shipping Cost</td>
                        <td>Free</td>
                    </tr>
                    <tr>
                        <td><strong>Grand Total</strong></td>
                        <td><strong><fmt:formatNumber value="${order.totalAmount}" type="number" groupingUsed="true"/> VND</strong></td>
                    </tr>
                </table>

                <!-- Receiver Info -->
                <div class="shopper-informations mb-4">
                    <div class="bill-to">
                        <h4>Receiver's Information</h4>
                        <p><strong>Name:</strong> ${order.fullName}</p>
                        <p><strong>Phone:</strong> ${order.phoneNumber}</p>
                        <p><strong>Address:</strong> ${order.address}</p>
                        <p><strong>Note:</strong> ${order.note}</p>
                    </div>
                </div>

                <!-- Payment Info -->
                <div class="payment-options">
                    <h4>Status</h4>
                    <p>Status ID: ${order.status}</p>
                    <h4>Payment Status</h4>
                    <p>Status ID: ${order.paymentStatusID}</p>
                </div>

            </div>
        </section>

        <jsp:include page="/ShopPages/Pages/components/footer.jsp"/>

        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/bootstrap.min.js"></script>
    </body>
</html>
