<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Blog Detail | E-Shopper</title>
        <link href="${ctx}/ShopPages/Pages/css/bootstrap.min.css" rel="stylesheet">
        <link href="${ctx}/ShopPages/Pages/css/font-awesome.min.css" rel="stylesheet">
        <link href="${ctx}/ShopPages/Pages/css/prettyPhoto.css" rel="stylesheet">
        <link href="${ctx}/ShopPages/Pages/css/price-range.css" rel="stylesheet">
        <link href="${ctx}/ShopPages/Pages/css/animate.css" rel="stylesheet">
        <link href="${ctx}/ShopPages/Pages/css/main.css" rel="stylesheet">
        <link href="${ctx}/ShopPages/Pages/css/responsive.css" rel="stylesheet">
        <!--[if lt IE 9]>
        <script src="${ctx}/ShopPages/Pages/js/html5shiv.js"></script>
        <script src="${ctx}/ShopPages/Pages/js/respond.min.js"></script>
        <![endif]-->       
        <link rel="shortcut icon" href="${ctx}/ShopPages/Pages/images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${ctx}/ShopPages/Pages/images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${ctx}/ShopPages/Pages/images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${ctx}/ShopPages/Pages/images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="${ctx}/ShopPages/Pages/images/ico/apple-touch-icon-57-precomposed.png">
        <style>
            .post-title {
                font-size: 2em;
                color: #333;
                margin-bottom: 10px;
            }
            .post-meta {
                color: #777;
                font-size: 0.9em;
                margin-bottom: 20px;
            }
            .post-thumbnail img {
                max-width: 100%;
                height: auto;
                border-radius: 8px;
                margin-bottom: 20px;
            }
            .post-brief {
                font-style: italic;
                color: #555;
                margin-bottom: 20px;
                font-size: 1.1em;
            }
            .post-content {
                line-height: 1.6;
                color: #444;
                margin-bottom: 20px;
            }
            .comments-section {
                margin-top: 30px;
            }
            .comment {
                border-top: 1px solid #ddd;
                padding: 10px 0;
            }
            .comment-meta {
                color: #777;
                font-size: 0.9em;
            }
            .comment-content {
                margin-top: 5px;
                color: #444;
            }
            .comment-form {
                margin-top: 20px;
            }
            .comment-form input, .comment-form textarea {
                width: 100%;
                padding: 8px;
                margin-bottom: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
            }
            .comment-form button {
                padding: 10px 20px;
                background: #fe980f;
                color: #fff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            .comment-form button:hover {
                background: #e68a00;
            }
            .back-link {
                display: inline-block;
                margin-top: 20px;
                text-decoration: none;
                color: #fe980f;
                font-weight: bold;
            }
            .back-link:hover {
                text-decoration: underline;
            }
            .blog-list {
    display: grid;
    grid-template-rows: repeat(5, auto);
    row-gap: 12px;
    margin-top: 10px;
}

.blog-card {
    padding: 14px;
    background-color: #f0f0f0;
    border: 1px solid #ddd;
    border-radius: 6px;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
}
.blog-card a {
    font-family: 'Arial', sans-serif;
    font-size: 13px;
    font-weight: normal;
    text-decoration: none;
    color: #000;
    display: block;
}

        </style>
        
    </head>
    <body>
        <header id="header">
            <div class="header_top">
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
            </div>

            <div class="header-middle">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="logo pull-left">
                                <a href="${ctx}/HomePages"><img src="${ctx}/ShopPages/Pages/images/home/CyberBeast2.png" style="height: 150px; width: 200px;" /></a>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <div class="shop-menu pull-right">
                                <ul class="nav navbar-nav" style="padding-top: 50px;">
                                    <li><a href="cart.html"><i class="fa fa-shopping-cart"></i> Cart</a></li>
                                    <li><a href="#"><i class="fa fa-user"></i> Account</a></li>
                                    <li><a href="checkout.html"><i class="fa fa-crosshairs"></i> Checkout</a></li>
                                    <li><a href="login.html"><i class="fa fa-lock"></i> Login</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="header-bottom">
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
                                    <li><a href="${ctx}/blogc" class="active">Blog</a></li>
                                    <li class="dropdown"><a href="#">Shop<i class="fa fa-angle-down"></i></a>
                                        <ul role="menu" class="sub-menu">
                                            <li><a href="${ctx}/Product?service=listProduct">Products</a></li>
                                            <li><a href="product-details.html">Product Details</a></li>
                                            <li><a href="checkout.html">Checkout</a></li>
                                            <li><a href="cart.html">Cart</a></li>
                                            <li><a href="login.html">Login</a></li>
                                        </ul>
                                    </li>
                                    <li class="dropdown"><a href="#">Blog<i class="fa fa-angle-down"></i></a>
                                        <ul role="menu" class="sub-menu">
                                            <li><a href="${ctx}/blogc">Blog List</a></li>
                                            <li><a href="#" class="active">Blog Single</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="404.html">404</a></li>
                                    <li><a href="contact-us.html">Contact</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="search_box pull-right">
                                <input type="text" placeholder="Search"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <section>
            <div class="container">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="left-sidebar">
                            <h2>Blog Categories</h2>
                            <div>
                                <ul>
                                    <li><a href="blogdetail" style="color: black">All</a></li> 
                                        <c:forEach var="cat" items="${blog_categories}">
                                        <li>
                                            <a href="blogc?Bc_id=${cat.bc_id}" style="color: black;">${cat.bc_name}</a>
                                        </li>
                                    </c:forEach>

                                </ul>
                                <h2 style="padding-top: 20px">Top 5 New Blogs</h2>
                            <ul>
                                <div class="blog-list">
                                <c:forEach items="${top5Posts}" var="p">
                                   
                                    <li>
                                        <div class="blog-card">
                                        <a href="blogdetail?id=${p.post_id}" style="color: black">
                                            ${p.title} - <fmt:formatDate value="${p.updated_date}" pattern="dd/MM/yyyy"/>
                                        </a>
                                        </div>
                                    </li>
                                    
                                </c:forEach>
                                </div>
                            </ul>
                            </div>
                           

                        </div>
                    </div>
                    <div class="col-sm-9">
                        <div class="blog-post-area">
                            <h2 class="title text-center">Blog Details</h2>
                            <h1 class="post-title">${post.title}</h1>
                            <div class="post-meta">
                                <span>${post.author}</span> | 
                                <span><fmt:formatDate value="${post.updated_date}" pattern="dd/MM/yyyy HH:mm"/></span> | 
                                <span>${categoryName}</span>
                            </div>

                            <c:if test="${not empty post.thumbnail}">
                                <img src="${post.thumbnail}" alt="Post Thumbnail" class="post-thumbnail">
                            </c:if>

                            <div class="post-content">
                                <p>${post.brief}</p>
                                <p>${post.content}</p>
                            </div>


                            <!-- Comments Section -->
                            <div class="comments-section">
                                <h3>Comments</h3>
                                <c:forEach var="comment" items="${comments}">
                                    <div class="comment">
                                        <div class="comment-meta">
                                            <strong>${comment.author}</strong> on ${comment.created_date}
                                        </div>
                                        <div class="comment-content">${comment.content}</div>
                                    </div>
                                </c:forEach>

                                <!-- Comment Form -->
                                <div class="comment-form">
                                    <h3>Add a Comment</h3>
                                    <form action="${ctx}/comment" method="post">
                                        <input type="hidden" name="Post_id" value="${post.post_id}">
                                        <input type="text" name="author" placeholder="Your Name" required>
                                        <textarea name="content" placeholder="Your Comment" rows="4" required></textarea>
                                        <button type="submit">Submit Comment</button>
                                    </form>
                                </div>
                            </div>

                            <a href="${ctx}/blogc" class="back-link">Back to Blog List</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <footer id="footer">
        <div class="footer-top">
            <div class="container">
                <div class="row">
                    <div class="col-sm-2">
                        <div class="companyinfo">
                            <h2><span>e</span>-shopper</h2>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,sed do eiusmod tempor</p>
                        </div>
                    </div>
                    <div class="col-sm-7">
                        <div class="col-sm-3">
                            <div class="video-gallery text-center">
                                <a href="#">
                                    <div class="iframe-img">
                                        <img src="${ctx}/ShopPages/Pages/images/home/iframe1.png" alt="" />
                                    </div>
                                    <div class="overlay-icon">
                                        <i class="fa fa-play-circle-o"></i>
                                    </div>
                                </a>
                                <p>Circle of Hands</p>
                                <h2>24 DEC 2014</h2>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="video-gallery text-center">
                                <a href="#">
                                    <div class="iframe-img">
                                        <img src="${ctx}/ShopPages/Pages/images/home/iframe2.png" alt="" />
                                    </div>
                                    <div class="overlay-icon">
                                        <i class="fa fa-play-circle-o"></i>
                                    </div>
                                </a>
                                <p>Circle of Hands</p>
                                <h2>24 DEC 2014</h2>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="video-gallery text-center">
                                <a href="#">
                                    <div class="iframe-img">
                                        <img src="${ctx}/ShopPages/Pages/images/home/iframe3.png" alt="" />
                                    </div>
                                    <div class="overlay-icon">
                                        <i class="fa fa-play-circle-o"></i>
                                    </div>
                                </a>
                                <p>Circle of Hands</p>
                                <h2>24 DEC 2014</h2>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="video-gallery text-center">
                                <a href="#">
                                    <div class="iframe-img">
                                        <img src="${ctx}/ShopPages/Pages/images/home/iframe4.png" alt="" />
                                    </div>
                                    <div class="overlay-icon">
                                        <i class="fa fa-play-circle-o"></i>
                                    </div>
                                </a>
                                <p>Circle of Hands</p>
                                <h2>24 DEC 2014</h2>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="address">
                            <img src="${ctx}/ShopPages/Pages/images/home/map.png" alt="" />
                            <p>505 S Atlantic Ave Virginia Beach, VA(Virginia)</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="footer-widget">
            <div class="container">
                <div class="row">
                    <div class="col-sm-2">
                        <div class="single-widget">
                            <h2>Service</h2>
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="">Online Help</a></li>
                                <li><a href="">Contact Us</a></li>
                                <li><a href="">Order Status</a></li>
                                <li><a href="">Change Location</a></li>
                                <li><a href="">FAQ’s</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-sm-2">
                        <div class="single-widget">
                            <h2>Quick Shop</h2>
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="">T-Shirt</a></li>
                                <li><a href="">Mens</a></li>
                                <li><a href="">Womens</a></li>
                                <li><a href="">Gift Cards</a></li>
                                <li><a href="">Shoes</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-sm-2">
                        <div class="single-widget">
                            <h2>Policies</h2>
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="">Terms of Use</a></li>
                                <li><a href="">Privacy Policy</a></li>
                                <li><a href="">Refund Policy</a></li>
                                <li><a href="">Billing System</a></li>
                                <li><a href="">Ticket System</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-sm-2">
                        <div class="single-widget">
                            <h2>About Shopper</h2>
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="">Company Information</a></li>
                                <li><a href="">Careers</a></li>
                                <li><a href="">Store Location</a></li>
                                <li><a href="">Affiliate Program</a></li>
                                <li><a href="">Copyright</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-sm-3 col-sm-offset-1">
                        <div class="single-widget">
                            <h2>About Shopper</h2>
                            <form action="#" class="searchform">
                                <input type="text" placeholder="Your email address" />
                                <button type="submit" class="btn btn-default"><i class="fa fa-arrow-circle-o-right"></i></button>
                                <p>Get the most recent updates from <br />our site and be updated your self...</p>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="footer-bottom">
            <div class="container">
                <div class="row">
                    <p class="pull-left">Copyright © 2013 E-SHOPPER Inc. All rights reserved.</p>
                    <p class="pull-right">Designed by <span><a target="_blank" href="http://www.themeum.com">Themeum</a></span></p>
                </div>
            </div>
        </div>
    </footer>

    <script src="${ctx}/ShopPages/Pages/js/jquery.js"></script>
    <script src="${ctx}/ShopPages/Pages/js/price-range.js"></script>
    <script src="${ctx}/ShopPages/Pages/js/jquery.scrollUp.min.js"></script>
    <script src="${ctx}/ShopPages/Pages/js/bootstrap.min.js"></script>
    <script src="${ctx}/ShopPages/Pages/js/jquery.prettyPhoto.js"></script>
    <script src="${ctx}/ShopPages/Pages/js/main.js"></script>
</body>
</html>