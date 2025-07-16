<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
    java.util.Calendar cal = java.util.Calendar.getInstance();
    models.OrderCate orderObj = (models.OrderCate) request.getAttribute("order");
%>
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
                max-width: 100%;
            }

            /* ===== ORDER TRACKING ===== */
            .order-steps {
                display: flex;
                justify-content: space-between;
                margin: 30px 0;
                list-style: none;
                padding-left: 0;
                gap: 20px;
            }

            .order-steps li {
                flex: 1;
                text-align: center;
                position: relative;
                color: #aaa;
                font-weight: 400;
                padding: 0 5px;
            }

            .order-steps li::before {
                content: "";
                position: absolute;
                top: 20px;
                left: 0;
                right: 0;
                height: 4px;
                background-color: #ccc;
                z-index: -1;
                margin: 0 auto;
                width: 100%;
            }

            /* ✅ Đã hoàn thành */
            .order-steps li.completed {
                color: #28a745;
                font-weight: bold;
            }

            .order-steps li.completed::before {
                background-color: #28a745;
            }

            .order-steps li.completed::after {
                content: "✓";
                display: block;
                margin-top: 5px;
                color: #28a745;
                font-weight: bold;
            }

            /* ✅ Đang thực hiện */
            .order-steps li.current {
                color: #ffc107;
                font-weight: bold;
            }

            .order-steps li.current::before {
                background-color: #ffc107;
            }

            .order-steps li.current::after {
                content: "⟳";
                display: block;
                margin-top: 5px;
                color: #ffc107;
                font-weight: bold;
            }

            /* ===== REJECT STATUS ===== */
            .reject-step {
                text-align: center;
                color: #dc3545;
                font-weight: bold;
                font-size: 18px;
                margin: 30px 0;
            }

            .reject-step::after {
                content: "✘";
                display: block;
                font-size: 24px;
                color: #dc3545;
                margin-top: 5px;
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

                <h3>Order Status</h3>
                <h4>Payment status: ${order.paymentStatusID == 1 ? 'Not Paid' : 'Paid'}</h4>
                <strong>Order Date:</strong> <fmt:formatDate value="${order.orderDate}" pattern="HH:mm dd/MM/yyyy"/>
                <c:choose>
                    <c:when test="${order.order_Status.statusID == 0}">
                        <div class="reject-step" style="color: red; font-weight: bold;">❌ Rejected</div>
                    </c:when>

                    <c:otherwise>
                        <ul class="order-steps">
                            <c:set var="currentStatus" value="${order.order_Status.statusID}" />
                            <c:forEach var="step" begin="1" end="5">
                                <c:set var="stepClass" value="" />
                                <c:choose>
                                    <c:when test="${step < currentStatus}">
                                        <c:set var="stepClass" value="completed" />
                                    </c:when>
                                    <c:when test="${step == currentStatus}">
                                        <c:choose>
                                            <c:when test="${currentStatus == 5}">
                                                <c:set var="stepClass" value="completed" />
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="stepClass" value="current" />
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                </c:choose>

                                <li class="${stepClass}">
                                    <c:choose>
                                        <c:when test="${step == 1}">Pending</c:when>
                                        <c:when test="${step == 2}">On-progress</c:when>
                                        <c:when test="${step == 3}">Waiting for ship</c:when>
                                        <c:when test="${step == 4}">On Ship</c:when>
                                        <c:when test="${step == 5}">Complete</c:when>
                                    </c:choose>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:otherwise>
                </c:choose>

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
                                    <a href="${pageContext.request.contextPath}/CategoriesController?service=detail&categoryID=${item.category.categoryID}"
                                       class="btn btn-success"
                                       >
                                        Buy new one
                                    </a>                                    
                                    <a href="${pageContext.request.contextPath}/CategoriesController?service=detail&categoryID=${item.category.categoryID}"
                                       class="btn btn-success"
                                       >
                                        Feedback
                                    </a>
                                </div>

                                <div class="card-right">
                                    <!-- Order Details for this Item -->
                                    <h5>Order Details:</h5>
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th>Product Code</th>
                                                <th>Unit Price</th>
                                                <th>Warranty Price</th>
                                                <th>Warranty Period</th>
                                                <th>Warranty desc</th>
                                                <th>Status</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="detail" items="${item.orderDetailList}">
                                                <tr>
                                                    <td>${empty detail.product.productCode ? 'Not Prepared' : detail.product.productCode}</td>
                                                    <td><fmt:formatNumber value="${detail.unitPrice}" type="number" groupingUsed="true"/> VND</td>
                                                    <td><fmt:formatNumber value="${detail.warrantyPrice}" type="number" groupingUsed="true"/> VND</td>
                                                    <td>${detail.warranty.warrantyPeriod} Months</td>   
                                                    <td>${detail.warranty.description} Months</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${detail.product.status == 0}">Delivered</c:when>
                                                            <c:when test="${detail.product.status == 1}">Not Prepared Yet</c:when>
                                                            <c:when test="${detail.product.status == 2}">Under Warranty</c:when>
                                                            <c:otherwise>Preparing</c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${order.order_Status.statusID >= 5 && detail.product.status == 0}">
                                                                <a href="#" class="btn btn-primary mt-0">Activate Warranty</a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <button class="btn btn-secondary mt-0" disabled>Activate Warranty</button>
                                                            </c:otherwise>
                                                        </c:choose>
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
            </div>
        </section>

        <jsp:include page="/ShopPages/Pages/components/footer.jsp"/>

        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/bootstrap.min.js"></script>
    </body>
</html>
