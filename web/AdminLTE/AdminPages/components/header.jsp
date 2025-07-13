<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<header class="main-header">
    <!-- Logo -->
    <a href="${ctx}/AdminDashbordServlet" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>A</b>LT</span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>Admin</b>LTE</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <button class="sidebar-toggle" data-toggle="offcanvas" aria-label="Toggle navigation">
            <span class="sr-only">Toggle navigation</span>
        </button>
        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <!-- Messages: style can be found in dropdown.less-->
                <li class="dropdown messages-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-envelope-o"></i>
                        <span class="label label-success">4</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">You have 4 messages</li>
                        <li>
                            <!-- inner menu: contains the actual data -->
                            <ul class="menu">
                                <li><!-- start message -->
                                    <a href="#">
                                        <div class="pull-left">
                                            <img src="${ctx}/AdminLTE/AdminPages/dist/img/user2-160x160.jpg" class="img-circle" alt="User">
                                        </div>
                                        <h4>
                                            Support Team
                                            <small><i class="fa fa-clock-o"></i> 5 mins</small>
                                        </h4>
                                        <p>Why not buy a new awesome theme?</p>
                                    </a>
                                </li>
                                <!-- end message -->
                                <li>
                                    <a href="#">
                                        <div class="pull-left">
                                            <img src="${ctx}/AdminLTE/AdminPages/dist/img/user3-128x128.jpg" class="img-circle" alt="User">
                                        </div>
                                        <h4>
                                            AdminLTE Design Team
                                            <small><i class="fa fa-clock-o"></i> 2 hours</small>
                                        </h4>
                                        <p>Why not buy a new awesome theme?</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <div class="pull-left">
                                            <img src="${ctx}/AdminLTE/AdminPages/dist/img/user4-128x128.jpg" class="img-circle" alt="User">
                                        </div>
                                        <h4>
                                            Developers
                                            <small><i class="fa fa-clock-o"></i> Today</small>
                                        </h4>
                                        <p>Why not buy a new awesome theme?</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <div class="pull-left">
                                            <img src="${ctx}/AdminLTE/AdminPages/dist/img/user3-128x128.jpg" class="img-circle" alt="User">
                                        </div>
                                        <h4>
                                            Sales Department
                                            <small><i class="fa fa-clock-o"></i> Yesterday</small>
                                        </h4>
                                        <p>Why not buy a new awesome theme?</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <div class="pull-left">
                                            <img src="${ctx}/AdminLTE/AdminPages/dist/img/user4-128x128.jpg" class="img-circle" alt="User">
                                        </div>
                                        <h4>
                                            Reviewers
                                            <small><i class="fa fa-clock-o"></i> 2 days</small>
                                        </h4>
                                        <p>Why not buy a new awesome theme?</p>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="footer"><a href="#">See All Messages</a></li>
                    </ul>
                </li>
                <!-- Notifications: style can be found in dropdown.less -->
                <c:if test="${not empty sessionScope.user}">
                    <li class="dropdown notifications-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                            <i class="fa fa-bell-o"></i>
                            <span class="label label-warning" id="notification-count">0</span>
                        </a>
                        <ul class="dropdown-menu" id="notification-dropdown">
                            <li class="header">You have <span id="notification-count-header">0</span> notifications</li>
                            <li>
                                <!-- inner menu: contains the actual data -->
                                <ul class="menu" id="notification-list">
                                    <!-- JS sẽ render các thông báo ở đây -->
                                    <li><a href="#"><i class="fa fa-info-circle text-aqua"></i> No notifications</a></li>
                                </ul>
                            </li>
                            <li class="footer"><a href="${ctx}/NotificationServlet?service=list">View All</a></li>
                        </ul>
                    </li>
                </c:if>
                <!-- Tasks: style can be found in dropdown.less -->
                <li class="dropdown tasks-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-flag-o"></i>
                        <span class="label label-danger">9</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">You have 9 tasks</li>
                        <li>
                            <!-- inner menu: contains the actual data -->
                            <ul class="menu">
                                <li><!-- Task item -->
                                    <a href="#">
                                        <h3>
                                            Design some buttons
                                            <small class="pull-right">20%</small>
                                        </h3>
                                        <div class="progress xs">
                                            <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                <span class="sr-only">20% Complete</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <!-- end task item -->
                                <li><!-- Task item -->
                                    <a href="#">
                                        <h3>
                                            Create a nice theme
                                            <small class="pull-right">40%</small>
                                        </h3>
                                        <div class="progress xs">
                                            <div class="progress-bar progress-bar-green" style="width: 40%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                <span class="sr-only">40% Complete</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <!-- end task item -->
                                <li><!-- Task item -->
                                    <a href="#">
                                        <h3>
                                            Some task I need to do
                                            <small class="pull-right">60%</small>
                                        </h3>
                                        <div class="progress xs">
                                            <div class="progress-bar progress-bar-red" style="width: 60%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                <span class="sr-only">60% Complete</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <!-- end task item -->
                                <li><!-- Task item -->
                                    <a href="#">
                                        <h3>
                                            Make beautiful transitions
                                            <small class="pull-right">80%</small>
                                        </h3>
                                        <div class="progress xs">
                                            <div class="progress-bar progress-bar-yellow" style="width: 80%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                <span class="sr-only">80% Complete</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <!-- end task item -->
                            </ul>
                        </li>
                        <li class="footer">
                            <a href="#">View all tasks</a>
                        </li>
                    </ul>
                </li>
                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="${ctx}/AdminLTE/AdminPages/dist/img/user2-160x160.jpg" class="user-image" alt="User">
                        <span class="hidden-xs">Alexander Pierce</span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <img src="${ctx}/AdminLTE/AdminPages/dist/img/user2-160x160.jpg" class="img-circle" alt="User">

                            <p>
                                Alexander Pierce - Web Developer
                                <small>Member since Nov. 2012</small>
                            </p>
                        </li>
                        <!-- Menu Body -->
                        <li class="user-body">
                            <div class="row">
                                <div class="col-xs-4 text-center">
                                    <a href="#">Followers</a>
                                </div>
                                <div class="col-xs-4 text-center">
                                    <a href="#">Sales</a>
                                </div>
                                <div class="col-xs-4 text-center">
                                    <a href="#">Friends</a>
                                </div>
                            </div>
                            <!-- /.row -->
                        </li>
                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <div class="pull-left">
                                <a href="#" class="btn btn-default btn-flat">Profile</a>
                            </div>
                            <div class="pull-right">
                                <a href="#" class="btn btn-default btn-flat">Sign out</a>
                            </div>
                        </li>
                    </ul>
                </li>
                <!-- Control Sidebar Toggle Button -->
                <li>
                    <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                </li>
            </ul>
        </div>
    </nav>
</header>
<c:choose>
  <c:when test="${not empty sessionScope.user}">
    <script src="${ctx}/AdminLTE/AdminPages/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <script>
    $(document).ready(function() {
        // Đếm số lượng thông báo chưa đọc
        $.ajax({
            url: "${ctx}/NotificationServlet?service=getUnreadCount",
            type: "GET",
            dataType: "json",
            success: function(data) {
                $("#notification-count").text(data.count);
                $("#notification-count-header").text(data.count);
            }
        });

        // Khi click vào chuông, load danh sách thông báo
        $('.notifications-menu > a').on('click', function(e) {
            $.ajax({
                url: "${ctx}/NotificationServlet?service=ajaxList",
                type: "GET",
                dataType: "json",
                success: function(data) {
                    var html = '';
                    if (data.notifications.length === 0) {
                        html = '<li><a href="#"><i class="fa fa-info-circle text-aqua"></i> No notifications</a></li>';
                    } else {
                        data.notifications.forEach(function(noti) {
                            html += '<li><a href="${ctx}/NotificationServlet?service=detail&id=' + noti.id + '">'
                                + '<i class="fa fa-info-circle text-aqua"></i> <b>' + noti.title + '</b>'
                                + '<br><small>' + noti.message + '</small>'
                                + '<br><small>' + noti.createdAt + '</small>'
                                + '</a></li>';
                        });
                    }
                    // Hiển thị tổng số chưa đọc
                    $('.dropdown-menu .header').html('You have <span id="notification-count-header">' + data.totalUnread + '</span> notifications');
                    $('#notification-list').html(html);
                    // Đồng bộ lại số trên icon chuông nếu cần
                    $('#notification-count').text(data.totalUnread);
                }
            });
        });
    });
    </script>
  </c:when>
  <c:otherwise>
    <script>$(function() { $("#notification-count").text(0); });</script>
  </c:otherwise>
</c:choose>