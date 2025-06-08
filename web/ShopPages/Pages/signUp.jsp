<%-- Document : login Created on : May 22, 2025, 9:18:17 AM Author : PC ASUS
--%> <%@page contentType="text/html" pageEncoding="UTF-8"%> <%@taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Login | E-Shopper</title>
    <link
      href="${pageContext.request.contextPath}/ShopPages/Pages/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <link
      href="${pageContext.request.contextPath}/ShopPages/Pages/css/font-awesome.min.css"
      rel="stylesheet"
    />
    <link
      href="${pageContext.request.contextPath}/ShopPages/Pages/css/prettyPhoto.css"
      rel="stylesheet"
    />
    <link
      href="${pageContext.request.contextPath}/ShopPages/Pages/css/price-range.css"
      rel="stylesheet"
    />
    <link
      href="${pageContext.request.contextPath}/ShopPages/Pages/css/animate.css"
      rel="stylesheet"
    />
    <link
      href="${pageContext.request.contextPath}/ShopPages/Pages/css/main.css"
      rel="stylesheet"
    />
    <link
      href="${pageContext.request.contextPath}/ShopPages/Pages/css/responsive.css"
      rel="stylesheet"
    />
    <link
      href="${pageContext.request.contextPath}/ShopPages/Pages/css/custom.css?v=1.0.10"
      rel="stylesheet"
    />
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
    <![endif]-->
    <link rel="shortcut icon" href="images/ico/favicon.ico" />
    <link
      rel="apple-touch-icon-precomposed"
      sizes="144x144"
      href="images/ico/apple-touch-icon-144-precomposed.png"
    />
    <link
      rel="apple-touch-icon-precomposed"
      sizes="114x114"
      href="images/ico/apple-touch-icon-114-precomposed.png"
    />
    <link
      rel="apple-touch-icon-precomposed"
      sizes="72x72"
      href="images/ico/apple-touch-icon-72-precomposed.png"
    />
    <link
      rel="apple-touch-icon-precomposed"
      href="images/ico/apple-touch-icon-57-precomposed.png"
    />
    
    </head
  ><!--/head-->

  <body>
    <%@ include file="components/header.jsp" %>
    <!--/header-->
    <section id="form" class="mt-0">
      <div class="container">
        <div class="row custom-center">
          <div class="col-sm-4">
            <div class="signup-form form-modern">
              <!--sign up form-->
              <h2>New User Signup!</h2>
              <c:if test="${error == 'Sign up failed!'}">
                <p
                  class="text-danger error-message"
                  style="text-align: center; margin-top: 18px"
                >
                  ${error}
                </p>
              </c:if>
              <form action="SignUp" method="POST">
                <label class="form">Email</label>
                <input
                  type="email"
                  placeholder="Enter email address"
                  class="form-control ${error == 'Email existed!' || error == 'Email is required!' ? 'input-modern-invalid' : ''}"
                  name="email"
                  required
                  value="${not empty error ? email : ''}"
                />
                <c:if
                  test="${error == 'Email existed!' || error == 'Email is required!'}"
                >
                  <p class="text-danger error-message">${error}</p>
                </c:if>
                <label class="form">Full name</label>
                <input
                  type="text"
                  placeholder="Enter full name"
                  class="form-control ${error == 'Full name is required!' ? 'input-modern-invalid' : ''}"
                  name="fullName"
                  required
                  value="${not empty error ? fullName : ''}"
                />
                <c:if test="${error == 'Full name is required!'}">
                  <p class="text-danger error-message">${error}</p>
                </c:if>
                <label class="form">Address</label>
                <input
                  type="text"
                  placeholder="Enter address"
                  class="form-control ${error == 'Address is required!' ? 'input-modern-invalid' : ''}"
                  name="address"
                  required
                  value="${not empty error ? address : ''}"
                />
                <c:if test="${error == 'Address is required!'}">
                  <p class="text-danger error-message">${error}</p>
                </c:if>
                <label class="form">Phone number</label>
                <input
                  type="text"
                  placeholder="Enter phone number "
                  class="form-control ${(error == 'Phone number existed!' || error == 'Phone number is required!' || error == 'Invalid phone number') ? 'input-modern-invalid' : ''}"
                  name="phoneNumber"
                  required
                  value="${not empty error ? phoneNumber : ''}"
                />
                <c:if
                  test="${error == 'Phone number existed!' || error == 'Phone number is required!' || error == 'Invalid phone number'}"
                >
                  <p class="text-danger error-message">${error}</p>
                </c:if>
                <label class="form">Password</label>
                <input
                  type="password"
                  placeholder="Enter password"
                  class="form-control ${error == 'Password is required!' ? 'input-modern-invalid' : ''}"
                  name="password"
                  required
                  value="${not empty error ? password : ''}"
                />
                <c:if test="${error == 'Password is required!'}">
                  <p class="text-danger error-message">${error}</p>
                </c:if>
                <label class="form">Confirm password</label>
                <input
                  type="password"
                  placeholder="Confirm your password"
                  class="form-control ${(error == 'Confirm password not match!' || error == 'Confirm password is required!') ? 'input-modern-invalid' : ''}"
                  name="confirmPassword"
                  required
                  value="${not empty error ? confirmPassword : ''}"
                />
                <c:if
                  test="${error == 'Confirm password not match!' || error == 'Confirm password is required!'}"
                >
                  <p class="text-danger error-message">${error}</p>
                </c:if>
                <div class="custom-between mt-5">
                  <button type="submit" id="btn-signup" class="btn-modern">SignUp</button>
                  <a href="Login">Login</a>
                </div>
              </form>
            </div>
            <!--/sign up form-->
          </div>
        </div>
      </div>
    </section>
    <!--/form-->

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
    <%@ include file="components/footer.jsp" %>
  </body>
</html>
