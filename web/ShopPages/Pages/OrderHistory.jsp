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
                        <li class="active">Order History</li>
                    </ol>
                </div>

                <c:if test="${not empty orders}">
                    <div class="container">
                        <h2 class="text-center" style="margin-bottom: 30px;">Order History</h2>
                        <c:forEach var="order" items="${orders}">
                            <div class="panel panel-default" style="margin-bottom: 25px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); border-radius: 8px;">
                                <div class="panel-heading" style="background-color: #f5f5f5; border-bottom: 1px solid #ddd; border-radius: 8px 8px 0 0;">
                                    <strong>Order Code:</strong> ${order.orderCode} |
                                    <strong>Date:</strong> <fmt:formatDate value="${order.orderDate}" pattern="dd/MM/yyyy HH:mm" /> |
                                    <strong>Status:</strong>    
                                    <c:choose>
                                        <c:when test="${order.status == 0}">🕒 Pending</c:when>
                                        <c:when test="${order.status == 1}">⚙️ Processing</c:when>
                                        <c:when test="${order.status == 2}">✅ Completed</c:when>
                                        <c:otherwise>❌ Cancelled</c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <p><strong>Receiver:</strong> ${order.fullName}</p>
                                            <p><strong>Phone:</strong> ${order.phoneNumber}</p>
                                            <p><strong>Address:</strong> ${order.address}</p>
                                            <p><strong>Note:</strong> ${order.note}</p>
                                        </div>
                                        <div class="col-md-6 text-right">
                                            <p><strong>Payment:</strong>
                                                <c:choose>
                                                    <c:when test="${order.paymentStatusID == 1}">💵 Unpaid</c:when>
                                                    <c:when test="${order.paymentStatusID == 2}">💳 Paid</c:when>
                                                    <c:otherwise>Unknown</c:otherwise>
                                                </c:choose>
                                            </p>
                                            <p><strong>Total:</strong> <fmt:formatNumber value="${order.totalAmount}" type="number" groupingUsed="true" /> VND</p>
                                            <a href="Order?orderID=${order.orderID}" class="btn btn-info">View Details</a>
                                        </div>
                                    </div>

                                    <hr>
                                    <table class="table table-striped table-hover table-bordered">
                                        <thead style="background-color: #eee;">
                                            <tr>
                                                <th>Product Name</th>
                                                <th>Quantity</th>
                                                <th>Unit Price</th>
                                                <th>Subtotal</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="item" items="${order.orderItems}">
                                                <tr>
                                                    <td>${item.category.categoryName}</td>
                                                    <td>${item.quantity}</td>
                                                    <td><fmt:formatNumber value="${item.price}" type="number" groupingUsed="true" /> VND</td>
                                                    <td><fmt:formatNumber value="${item.quantity * item.price}" type="number" groupingUsed="true"/> VND</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>


            </div>
        </section>

        <jsp:include page="/ShopPages/Pages/components/footer.jsp"/>

        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/bootstrap.min.js"></script>
    </body>
</html>
