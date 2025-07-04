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
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/custom.css?v=1.0.20" rel="stylesheet">
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
            .shopper-informations {
                background: #f9f9f9;
                padding: 30px;
                margin-top: 30px;
                border-radius: 6px;
            }

            .bill-to {
                width: 100%;
            }

            .bill-to p {
                font-weight: bold;
                margin-bottom: 15px;
                font-size: 18px;
            }

            .bill-to input {
                margin-bottom: 15px;
                height: 42px;
                padding: 10px 15px;
                width: 100%;
                border: 1px solid #ddd;
                border-radius: 4px;
            }

            .order-message {
                width: 100%;
            }

            .order-message p {
                font-weight: bold;
                margin-bottom: 15px;
                font-size: 18px;
            }

            .order-message textarea {
                width: 100%;
                border: 1px solid #ddd;
                border-radius: 4px;
                padding: 15px;
                resize: none; /* Không cho resize */
                min-height: 100px;
                height: 200px;
            }

            .payment-options {
                background: #fff;
                padding: 20px;
                margin-top: 30px;
                margin-bottom: 30px;
                border: 1px solid #eee;
                border-radius: 6px;
            }

            .payment-options label {
                display: inline-block;
                margin-right: 30px;
                font-weight: normal;
                cursor: pointer;
            }

            .total-result {
                margin-top: 30px;
                border-top: 2px solid #f05d23;
            }

            .total-result td {
                padding: 10px 0;
            }

            .btn-confirm-order {
                margin-top: 30px;
                margin-bottom: 30px;
                padding: 12px 40px;
                background-color: #f05d23;
                color: #fff;
                border: none;
                border-radius: 50px;
                font-size: 16px;
                transition: background 0.3s ease;
            }

            .btn-confirm-order:hover {
                background-color: #d84e1f;
            }
        </style>

    </head>
    <body>
        <jsp:include page="/ShopPages/Pages/components/header.jsp" />

        <section id="cart_items">
            <div class="container">
                <div class="breadcrumbs">
                    <ol class="breadcrumb">
                        <li><a href="${pageContext.request.contextPath}/Cart">Cart</a></li>
                        <li class="active">Check out</li>
                    </ol>
                </div><!--/breadcrums-->

                <div class="review-payment">
                    <h2>Review & Payment</h2>
                </div>

                <div class="table-responsive cart-table">
                    <table class="table table-bordered">
                        <thead class="thead-light">
                            <tr>
                                <th>Product</th>
                                <th>Details</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody id="cart-items-body" >
                            <c:forEach var="cartItem" items="${cartItems}">
                                <tr>
                                    <td>
                                        <img src="${pageContext.request.contextPath}/ShopPages/Pages/images/cart/two.png" alt="Product">
                                    </td>
                                    <td class="text-left">
                                        <strong>${cartItem.category.categoryName}</strong><br>
                                        Warranty: ${cartItem.warranty.warranty.warrantyPeriod} months<br>
                                        <small class="text-muted">(${cartItem.warranty.warranty.description})</small>
                                    </td>
                                    <td class="cart-price">
                                        <div>Product: <fmt:formatNumber value="${cartItem.category.price}" type="number" groupingUsed="true"/> VND</div>
                                        <div>Warranty: <fmt:formatNumber value="${cartItem.warranty.price}" type="number" groupingUsed="true"/> VND</div>
                                    </td>
                                    <td>
                                        <div class="cart-quantity-group">
                                            <input type="number" value="${cartItem.quantity}" readonly>
                                        </div>
                                    </td>
                                    <td class="cart_total">
                                        <strong><fmt:formatNumber value="${(cartItem.category.price + cartItem.warranty.price) * cartItem.quantity}" type="number" groupingUsed="true"/> VND</strong>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- === Existing content === -->
                <div class="shopper-informations">
                    <div class="row">
                        <div class="col-md-6 col-sm-12">
                            <div class="bill-to">
                                <p>Receiver's Information</p>
                                <div class="form-one">
                                    <input type="text" placeholder="Name" name="receiverName" value="${sessionScope.user.fullname}" readonly>
                                    <input type="text" placeholder="Email" name="receiverEmail" value="${sessionScope.user.email}" readonly>
                                    <input type="text" placeholder="Phone number" name="phoneNumber" required>
                                    <input type="text" placeholder="Address" name="address" required>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-5 col-sm-12">
                            <div class="order-message">
                                <p>Shipping Order</p>
                                <textarea name="message" placeholder="Notes about your order, special instructions for delivery"></textarea>
                            </div>
                        </div>					
                    </div>
                </div>

                <div class="payment-options">
                    <p><strong>Payment Options</strong></p>
                    <label><input type="radio" name="paymentMethod" value="COD" checked> Pay when receive</label>
                    <label><input type="radio" name="paymentMethod" value="Bank"> Direct bank transfer</label>
                </div>

                <table class="table table-condensed total-result">
                    <tr>
                        <td>Cart Sub Total</td>
                        <td><fmt:formatNumber value="${subTotal}" type="number" groupingUsed="true"/> VND</td>
                    </tr>
                    <tr class="shipping-cost">
                        <td>Shipping Cost</td>
                        <td>Free</td>										
                    </tr>
                    <tr>
                        <td><strong>Total</strong></td>
                        <td><span><fmt:formatNumber value="${subTotal}" type="number" groupingUsed="true"/> VND</span></td>
                    </tr>
                </table>

                <button type="submit" class="btn btn-confirm-order">Confirm Order</button>
            </div>
        </section> <!--/#cart_items-->

        <jsp:include page="/ShopPages/Pages/components/footer.jsp" />

        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/bootstrap.min.js"></script>
    </body>
</html>
