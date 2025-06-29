<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="activePage" value="${param.activePage}" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- Header Section Extracted from homepages.jsp -->
<style>
    .cart-dropdown {
        position: relative;
        list-style: none;
    }

    .cart-dropdown .dropdown-content {
        display: none;
        position: absolute;
        background-color: #fff;
        min-width: 150px;
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        z-index: 100;
        right: 0;
        padding: 10px;
        border-radius: 4px;
        flex-direction: column;
    }

    .cart-dropdown .dropdown-content a {
        display: block;
        padding: 8px;
        text-decoration: none;
        color: #333;
    }

    .cart-dropdown .dropdown-content a:hover {
        background-color: #f0f0f0;
    }

    .cart-dropdown:hover .dropdown-content {
        display: flex;
    }
    .arrow-up {
        width: 0;
        height: 0;
        border-left: 8px solid transparent;
        border-right: 8px solid transparent;
        border-bottom: 8px solid #fff;
        position: absolute;
        top: -8px;
        right: 10px;
    }
</style>
<header id="header"><!--header-->
    <div class="header_top"><!--header_top-->
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <div class="contactinfo">
                        <ul class="nav nav-pills">
                            <li><a href="#"><i class="fa fa-phone"></i> 0337593524</a></li>
                            <li><a href="#"><i class="fa fa-envelope"></i> CyberBeast@gmail.com</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="social-icons pull-right">
                        <ul class="nav navbar-nav">
                            <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                            <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                            <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                            <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                            <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/header_top-->

    <div class="header-middle"><!--header-middle-->
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <div class="logo pull-left">
                        <a href="${pageContext.request.contextPath}/HomePages"><img src="${ctx}/ShopPages/Pages/images/home/CyberBeast2.png" style="height: 150px; width: 200px;  "/></a>
                    </div>
                    <div class="btn-group pull-right">
                    </div>
                </div>
                <div class="col-sm-8">
                    <div class="shop-menu pull-right">
                        <ul class="nav navbar-nav"  style=" padding-top: 50px ">
                            <c:choose>
                                <c:when test="${not empty sessionScope.user}">
                                    <c:if test="${not empty sessionScope.user and sessionScope.user.role.roleID == 3}">
                                        <li class="cart-dropdown">
                                        <a href="#"><i class="fa fa-shopping-cart"></i> Cart</a>
                                        <div class="dropdown-content">
                                            <div class="arrow-up"></div>
                                            <a href="${pageContext.request.contextPath}/Cart">CartItem</a>
                                            <a href="CardBuildPc">CartPC</a>
                                        </div>
                                    </li>
                                        <li><a href="${pageContext.request.contextPath}/User?service=myAccount"><i class="fa fa-user"></i> Account</a></li>
                                        <li><a href="checkout.html"><i class="fa fa-check-square-o"></i> Checklist</a></li>
                                        </c:if>

                                    <li><a href="Logout"><i class="fa fa-sign-out"></i> Logout</a></li>
                                    </c:when>
                                    <c:otherwise>
                                    <li><a href="Login"><i class="fa fa-lock"></i> Login</a></li>
                                    <li><a href="SignUp"><i class="fa fa-user-plus"></i> Sign Up</a></li>
                                    </c:otherwise>
                                </c:choose>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/header-middle-->

    <div class="header-bottom"><!--header-bottom-->
        <div class="container">
            <div class="row">
                <div class="col-sm-9">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <div class="mainmenu pull-left">
                        <ul class="nav navbar-nav collapse navbar-collapse">
                            <li><a href="${pageContext.request.contextPath}/HomePages" class="${activePage == 'home' ? 'active' : ''}">Home</a></li>
                            <li><a href="${pageContext.request.contextPath}/CategoriesController?service=list" class="${activePage == 'products' ? 'active' : ''}">Products</a></li>
                            <li><a href="${ctx}/blogc" class="${activePage == 'blog' ? 'active' : ''}">Blog</a></li>
                            <li><a href="404.html" class="${activePage == '404' ? 'active' : ''}">404</a></li>
                            <li><a href="contact-us.html" class="${activePage == 'contact' ? 'active' : ''}">Contact</a></li>

                        </ul>

                    </div>
                </div>
                <div class="search_box pull-right">
                    <form action="${ctx}/CategoriesController" method="get" style="display: flex;">
                        <input type="hidden" name="service" value="filter"/>
                        <input type="text" name="keyword" class="form-control" placeholder="Insert name product"
                               value="${not empty param.keyword ? param.keyword : ''}" style="width: 180px;" />
                    </form>
                </div>
            </div>
        </div>
    </div><!--/header-bottom-->
</header><!--/header-->
