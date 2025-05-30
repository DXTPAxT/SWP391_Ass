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
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/custom.css?v=1.0.10" rel="stylesheet">
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
            /* Giao diện form đăng ký với đổ bóng */
            .signup-form {
                transform: translateY(-5%);
                margin-bottom: 100px;
                background: #ffffff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1); /* đổ bóng mềm và sâu */
                transition: box-shadow 0.3s ease;
            }

            .signup-form h2 {
                margin-bottom: 25px;
                font-weight: bold;
                color: #333;
                text-align: center;
            }

            .signup-form input {
                margin-bottom: 15px;
            }

            .error-message {
                margin-top: -10px;
                font-size: 0.9rem;
                color: red;
                margin-bottom: 10px;
            }

            .custom-between {
                display: flex;
                justify-content: space-between;
                align-items: center;
                gap: 10px;
            }

            .custom-between a {
                text-decoration: underline;
                font-size: 14px;
            }

            .btn-default {
                background-color: #fd7e14;
                color: white;
                border: none;
                padding: 10px 20px;
                border-radius: 6px;
                transition: background-color 0.3s ease;
            }

            .btn-default:hover {
                background-color: #e66a00;
            }

            /* Responsive Mobile (≤768px) */
            @media (max-width: 768px) {
                .signup-form {
                    padding: 20px 15px;
                }

                .custom-between {
                    flex-direction: column;
                    align-items: stretch;
                }

                .btn-default {
                    width: 100%;
                }

                .custom-between a {
                    text-align: center;
                }
            }

            /* Responsive iPad (769px–1024px) */
            @media (min-width: 769px) and (max-width: 1024px) {
                .signup-form {
                    padding: 25px;
                }

                .custom-between {
                    flex-direction: column;
                    align-items: stretch;
                }

                .btn-default {
                    width: 100%;
                }

                .custom-between a {
                    text-align: center;
                }
            }

            #btn-signup {
                flex: 1;
                background-color: #fd7e14;
                color: white;
                border: none;
                padding: 10px 20px;
                border-radius: 8px;
                font-weight: 500;
                transition: background-color 0.3s ease, transform 0.2s ease;
            }

            #btn-signup:hover {
                background-color: #e66a00;
            }

            @media (max-width: 768px) {
                #btn-signup {
                    width: 100%;
                }
            }
        </style>

    </head><!--/head-->

    <body>
        <section id="form" class="mt-0"><!--form-->
            <center>
                <div class="logo">
                    <a href="${pageContext.request.contextPath}/HomePages"><img src="${pageContext.request.contextPath}/ShopPages/Pages/images/home/CyberBeast2.png" style="height: 210px; width: 280px;  "/></a>
                </div>
            </center>
            <div class="container">
                <div class="row custom-center">
                    <div class="col-sm-4">
                        <div class="signup-form"><!--sign up form-->
                            <h2>New User Signup!</h2>
                            <form action="SignUp" method="POST">
                                <label class="form">Email</label>
                                <input type="email" placeholder="Enter email address" class="form-control ${error == "Email existed!" ? "is-invalid" : ''}" name="email" required="" value="${not empty error ? email : ''}"/>
                                <c:if test="${error == 'Email existed!'}">
                                    <p class="text-danger error-message">${error}</p>
                                </c:if>
                                <label class="form">Full name</label>
                                <input type="text" placeholder="Enter full name" class="form-control" name="fullName" required="" value="${not empty error ? fullName : ''}"/>
                                <label class="form">Address</label>
                                <input type="text" placeholder="Enter address" class="form-control" name="address" required="" value="${not empty error ? address : ''}"/>
                                <label class="form">Phone number</label>
                                <input type="text" placeholder="Enter phone number " class="form-control ${error == "Phone number existed!" ? "is-invalid" : ''}" name="phoneNumber" required="" value="${not empty error ? phoneNumber : ''}"/>
                                <c:if test="${error == 'Phone number existed!'}">
                                    <p class="text-danger error-message">${error}</p>
                                </c:if>
                                <label class="form">Password</label>
                                <input type="password" placeholder="Enter password" class="form-control" name="password" required="" value="${not empty error ? password : ''}"/>
                                <label class="form">Confirm password</label>
                                <input type="password" placeholder="Confirm your password" class="form-control ${error == "Confirm password not match!" ? "is-invalid" : ''}" name="confirmPassword" required="" value="${not empty error ? confirmPassword : ''}"/>
                                <c:if test="${error == 'Confirm password not match!'}">
                                    <p class="text-danger error-message">${error} </p>
                                </c:if>

                                <div class="custom-between mt-5">
                                    <button type="submit" id="btn-signup">SignUp</button>
                                    <a href="Login">Login</a>
                                </div>
                            </form>
                        </div><!--/sign up form-->
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
