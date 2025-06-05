<%-- 
    Document   : login
    Created on : May 22, 2025, 9:18:17 AM
    Author     : PC ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Login | E-Shopper</title>
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/prettyPhoto.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/price-range.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/animate.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/main.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/responsive.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/custom.css?v=1.0.11" rel="stylesheet">
        <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
        <![endif]-->       
        <link rel="shortcut icon" href="images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">

    </head><!--/head-->

    <body>
        <section id="login-section" class="position-container"><!--form-->
            <center>
                <div class="logo">
                    <a href="${pageContext.request.contextPath}/HomePages"><img src="${pageContext.request.contextPath}/ShopPages/Pages/images/home/CyberBeast2.png" style="height: 210px; width: 280px;  "/></a>
                </div>
            </center>
            <div class="container form-wrapper">
                <div class="row custom-center">
                    <div class="col-sm-4 col-sm-offset-1 ml-0">
                        <div class="login-form"><!--login form-->
                            <h2>Login to your account</h2>
                            <form action="Login" method="POST">
                                <label class="form">Email</label>
                                <input type="text" placeholder="Enter email address" class="form-control ${error == "Email is not exitsted" ? "is-invalid" : ''}" name="email" placeholder="Enter email" required="" value="${not empty error ? email : ''}"/>
                                <c:if test="${error == 'Email is not exitsted'}">
                                    <p class="error-messager">${error}</p>
                                </c:if>
                                <label>Password</label>
                                <input type="password" placeholder="Enter password" class="form-control ${error == "Incorrect password!" ? "is-invalid" : ''}" name="password" placeholder="Enter password" required="" value="${not empty error ? password : ''}"/>
                                <c:if test="${error == 'Incorrect password!'}">
                                    <p class="error-messager">${error}</p>
                                </c:if>
                                <span>
                                    <input type="checkbox" class="checkbox"> 
                                    Keep me signed in
                                </span>
                                <div class="custom-between mt-3">
                                    <button type="submit" class="btn btn-default " id="loginButton">Login</button>
                                    <a href="SignUp">SignUp</a>
                                </div>
                            </form>
                        </div><!--/login form-->
                    </div>
                </div>
            </div>
        </section><!--/form-->

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Chọn tất cả input có class is-invalid
                const invalidInputs = document.querySelectorAll("input.is-invalid");

                invalidInputs.forEach(function (input) {
                    input.addEventListener("input", function () {
                        // Xóa class is-invalid khỏi input
                        input.classList.remove("is-invalid");

                        // Ẩn tất cả phần tử có class .error-message (nếu có)
                        const errorMessages = document.querySelectorAll(".error-message");
                        errorMessages.forEach(function (el) {
                            el.style.display = "none";
                        });
                    });
                });
            });
        </script>

        <script src="js/jquery.js"></script>
        <script src="js/price-range.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
