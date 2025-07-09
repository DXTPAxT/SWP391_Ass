<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Order Details | E-Shopper</title>
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/main.css" rel="stylesheet">
    </head>

    <body>
        <jsp:include page="/ShopPages/Pages/components/header.jsp"/>

        <section id="order_details">
            <div class="container">

                <!-- Breadcrumb -->
                <div class="breadcrumbs">
                    <ol class="breadcrumb">
                        <li><a href="${pageContext.request.contextPath}/Home">Home</a></li>
                        <li class="active">Order #${order.orderCode}</li>
                    </ol>
                </div>

                <!-- Order Info -->
                <div class="review-payment">
                    <h2>Order Details</h2>
                </div>

                <div class="cart-cards">
                    <c:forEach var="item" items="${orderItems}">
                        <div class="cart-card clearfix">
                            <div class="cart-card-image pull-left">
                                <img src="${pageContext.request.contextPath}/ShopPages/Pages/images/cart/two.png"
                                     alt="Product">
                            </div>


                        </div>
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
                <div class="shopper-informations">
                    <div class="bill-to">
                        <h4>Receiver's Information</h4>
                        <p><strong>Name:</strong> ${order.receiverName}</p>
                        <p><strong>Phone:</strong> ${order.phoneNumber}</p>
                        <p><strong>Address:</strong> ${order.address}</p>
                    </div>
                </div>

                <!-- Payment Info -->
                <div class="payment-options">
                    <h4>Payment Method</h4>
                    <p>${order.paymentMethod}</p>
                </div>

            </div>
        </section>

        <jsp:include page="/ShopPages/Pages/components/footer.jsp"/>

        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/bootstrap.min.js"></script>
    </body>
</html>
