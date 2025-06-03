<%-- 
    Document   : insertProduct
    Created on : May 28, 2025, 9:48:28 PM
    Author     : Admin
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>AdminLTE 2 | Data Tables</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/l/font-awesome/4.5.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/l/ionicons/2.0.1/css/ionicons.min.css">
        <!-- DataTables -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages//plugins/datatables/dataTables.bootstrap.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages//dist/css/AdminLTE.min.css">
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages//dist/css/custom.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages//dist/css/skins/_all-skins.min.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

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
                    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>

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
                                                        <img src="${ctx}/AdminLTE/AdminPages//dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
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
                                                        <img src="${ctx}/AdminLTE/AdminPages/dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
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
                                                        <img src="${ctx}/AdminLTE/AdminPages/dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
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
                                                        <img src="${ctx}/AdminLTE/AdminPages/dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
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
                                                        <img src="${ctx}/AdminLTE/AdminPages/dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
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
                            <li class="dropdown notifications-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-bell-o"></i>
                                    <span class="label label-warning">10</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="header">You have 10 notifications</li>
                                    <li>
                                        <!-- inner menu: contains the actual data -->
                                        <ul class="menu">
                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-users text-aqua"></i> 5 new members joined today
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-warning text-yellow"></i> Very long description here that may not fit into the
                                                    page and may cause design problems
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-users text-red"></i> 5 new members joined
                                                </a>
                                            </li>

                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-shopping-cart text-green"></i> 25 sales made
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-user text-red"></i> You changed your username
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="footer"><a href="#">View all</a></li>
                                </ul>
                            </li>
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
                                    <img src="${ctx}/AdminLTE/AdminPages//dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                                    <span class="hidden-xs">Alexander Pierce</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- User image -->
                                    <li class="user-header">
                                        <img src="${ctx}/AdminLTE/AdminPages//dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
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
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="${ctx}/AdminLTE/AdminPages/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p>Alexander Pierce</p>
                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <!-- search form -->
                    <form action="#" method="get" class="sidebar-form">
                        <div class="input-group">
                            <input type="text" name="q" class="form-control" placeholder="Search...">
                            <span class="input-group-btn">
                                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                                </button>
                            </span>
                        </div>
                    </form>
                    <!-- /.search form -->
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu">
                        <li class="header">MAIN NAVIGATION</li>
                        <li class="treeview">
                            <a href="#">
                                <i class="fa fa-laptop"></i> <span>Category</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">                               
                                <li><a href="${ctx}/CateAdmin"><i class="fa fa-circle-o"></i>View Category</a></li>                                
                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#">
                                <i class="fa fa-table"></i> <span>Products</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <c:forEach var="cate" items="${list}">
                                    <li><a href="${ctx}/ProductAdmin?service=list&categoryID=${cate.categoryID}"><i class="fa fa-circle-o"></i> ${cate.categoryName}</a></li>
                                    </c:forEach>
                            </ul>
                        </li>                       
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        User Tables
                        <small>advanced tables</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">Tables</a></li>
                        <li class="active">User tables</li>
                    </ol>
                </section>
                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box">
                                <!-- /.box-header -->
                                <div class="box-body">
                                    <a href="${ctx}/Admin/user/add"
                                       class="btn btn-success btn">Add new user</a>
                                    <table id="example2" class="table table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th>User ID</th>
                                                <th>Role ID</th>
                                                <th>Full Name</th>
                                                <th>Email</th>
                                                <th>Phone number</th>
                                                <th>Address</th>
                                                <th>Created At</th>
                                                <th>Status</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:if test="${not empty users}">
                                                <c:forEach var="user" items="${users}">
                                                    <tr>
                                                        <td>${user.userID}</td>
                                                        <td>${user.roleID}</td>
                                                        <td>${user.fullname}</td>
                                                        <td>${user.email}</td>
                                                        <td>${user.phoneNumber}</td>
                                                        <td>${user.address}</td>
                                                        <td>${user.createdAt}</td>
                                                        <td>
                                                            <c:if test="${user.status == 1}">
                                                                <a href="${ctx}/User?service=toggleStatus&userID=${user.userID}"
                                                                   class="btn btn-success btn-sm">Active</a>
                                                            </c:if>
                                                            <c:if test="${user.status == 0}">
                                                                <a href="${ctx}/User?service=toggleStatus&userID=${user.userID}"
                                                                   class="btn btn-warning btn-sm">Disable</a>
                                                            </c:if>
                                                        </td>
                                                        <td>
                                                            <button
                                                                class="btn btn-primary btn-sm"
                                                                data-toggle="modal"
                                                                data-target="#updateUserModal"
                                                                data-userid="${user.userID}"
                                                                data-roleid="${user.roleID}"
                                                                data-fullname="${user.fullname}"
                                                                data-email="${user.email}"
                                                                data-phone="${user.phoneNumber}"
                                                                data-address="${user.address}"
                                                                data-createdat="${user.createdAt}"
                                                                data-status="${user.status}">
                                                                Update
                                                            </button>
                                                            <a href="${ctx}/User?service=resetPassword&userID=${user.userID}"
                                                               onclick="return confirm(`Reset user's password?`);"
                                                               class="btn btn-danger btn-sm">Reset Password</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${empty users}">
                                                <tr>
                                                    <td colspan="8" style="text-align:center;">No User.</td>
                                                </tr>
                                            </c:if>
                                        </tbody>
                                    </table>
                                </div>

                            </div>
                            <!-- /.box-body -->
                        </div>
                        <!-- /.box -->

                        <!-- /.content-wrapper -->
                        <footer class="main-footer">
                            <div class="pull-right hidden-xs">
                                <b>Version</b> 2.3.12
                            </div>
                            <strong>Copyright &copy; 2014-2016 <a href="http://almsaeedstudio.com">Almsaeed Studio</a>.</strong> All rights
                            reserved.
                        </footer>

                        <!-- Control Sidebar -->
                        <aside class="control-sidebar control-sidebar-dark">
                            <!-- Create the t -->
                            <ul class="nav nav-t nav-justified control-sidebar-t">
                                <li><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
                                <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
                            </ul>
                            <!-- Tab panes -->
                            <div class="tab-content">
                                <!-- Home tab content -->
                                <div class="tab-pane" id="control-sidebar-home-tab">
                                    <h3 class="control-sidebar-heading">Recent Activity</h3>
                                    <ul class="control-sidebar-menu">
                                        <li>
                                            <a href="javascript:void(0)">
                                                <i class="menu-icon fa fa-birthday-cake bg-red"></i>

                                                <div class="menu-info">
                                                    <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

                                                    <p>Will be 23 on April 24th</p>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="javascript:void(0)">
                                                <i class="menu-icon fa fa-user bg-yellow"></i>

                                                <div class="menu-info">
                                                    <h4 class="control-sidebar-subheading">Frodo Updated His Profile</h4>

                                                    <p>New phone +1(800)555-1234</p>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="javascript:void(0)">
                                                <i class="menu-icon fa fa-envelope-o bg-light-blue"></i>

                                                <div class="menu-info">
                                                    <h4 class="control-sidebar-subheading">Nora Joined Mailing List</h4>

                                                    <p>nora@example.com</p>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="javascript:void(0)">
                                                <i class="menu-icon fa fa-file-code-o bg-green"></i>

                                                <div class="menu-info">
                                                    <h4 class="control-sidebar-subheading">Cron Job 254 Executed</h4>

                                                    <p>Execution time 5 seconds</p>
                                                </div>
                                            </a>
                                        </li>
                                    </ul>
                                    <!-- /.control-sidebar-menu -->

                                    <h3 class="control-sidebar-heading">Tasks Progress</h3>
                                    <ul class="control-sidebar-menu">
                                        <li>
                                            <a href="javascript:void(0)">
                                                <h4 class="control-sidebar-subheading">
                                                    Custom Template Design
                                                    <span class="label label-danger pull-right">70%</span>
                                                </h4>

                                                <div class="progress progress-xxs">
                                                    <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="javascript:void(0)">
                                                <h4 class="control-sidebar-subheading">
                                                    Update Resume
                                                    <span class="label label-success pull-right">95%</span>
                                                </h4>

                                                <div class="progress progress-xxs">
                                                    <div class="progress-bar progress-bar-success" style="width: 95%"></div>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="javascript:void(0)">
                                                <h4 class="control-sidebar-subheading">
                                                    Laravel Integration
                                                    <span class="label label-warning pull-right">50%</span>
                                                </h4>

                                                <div class="progress progress-xxs">
                                                    <div class="progress-bar progress-bar-warning" style="width: 50%"></div>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="javascript:void(0)">
                                                <h4 class="control-sidebar-subheading">
                                                    Back End Framework
                                                    <span class="label label-primary pull-right">68%</span>
                                                </h4>

                                                <div class="progress progress-xxs">
                                                    <div class="progress-bar progress-bar-primary" style="width: 68%"></div>
                                                </div>
                                            </a>
                                        </li>
                                    </ul>
                                    <!-- /.control-sidebar-menu -->

                                </div>
                                <!-- /.tab-pane -->
                                <!-- Stats tab content -->
                                <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
                                <!-- /.tab-pane -->
                                <!-- Settings tab content -->
                                <div class="tab-pane" id="control-sidebar-settings-tab">
                                    <form method="post">
                                        <h3 class="control-sidebar-heading">General Settings</h3>

                                        <div class="form-group">
                                            <label class="control-sidebar-subheading">
                                                Report panel usage
                                                <input type="checkbox" class="pull-right" checked>
                                            </label>

                                            <p>
                                                Some information about this general settings option
                                            </p>
                                        </div>
                                        <!-- /.form-group -->

                                        <div class="form-group">
                                            <label class="control-sidebar-subheading">
                                                Allow mail redirect
                                                <input type="checkbox" class="pull-right" checked>
                                            </label>

                                            <p>
                                                Other sets of options are available
                                            </p>
                                        </div>
                                        <!-- /.form-group -->

                                        <div class="form-group">
                                            <label class="control-sidebar-subheading">
                                                Expose author name in posts
                                                <input type="checkbox" class="pull-right" checked>
                                            </label>

                                            <p>
                                                Allow the user to show his name in blog posts
                                            </p>
                                        </div>
                                        <!-- /.form-group -->

                                        <h3 class="control-sidebar-heading">Chat Settings</h3>

                                        <div class="form-group">
                                            <label class="control-sidebar-subheading">
                                                Show me as online
                                                <input type="checkbox" class="pull-right" checked>
                                            </label>
                                        </div>
                                        <!-- /.form-group -->

                                        <div class="form-group">
                                            <label class="control-sidebar-subheading">
                                                Turn off notifications
                                                <input type="checkbox" class="pull-right">
                                            </label>
                                        </div>
                                        <!-- /.form-group -->

                                        <div class="form-group">
                                            <label class="control-sidebar-subheading">
                                                Delete chat history
                                                <a href="javascript:void(0)" class="text-red pull-right"><i class="fa fa-trash-o"></i></a>
                                            </label>
                                        </div>
                                        <!-- /.form-group -->
                                    </form>
                                </div>
                                <!-- /.tab-pane -->
                            </div>
                        </aside>
                        <!-- /.control-sidebar -->
                        <!-- Add the sidebar's background. This div must be placed
                             immediately after the control sidebar -->
                        <div class="control-sidebar-bg"></div>
                    </div>

                    <!-- Modal Form -->
                    <div class="modal fade" id="updateUserModal" tabindex="-1" aria-labelledby="updateUserModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <form action="${ctx}/User" method="post">
                                    <div class="modal-header">
                                        <h3 class="modal-title" id="updateUserModalLabel">Update User</h3>
                                    </div>

                                    <div class="modal-body row g-3">

                                        <!-- Hidden ID -->
                                        <input type="hidden" name="userID" id="modal-userID">

                                        <div class="col-md-6">
                                            <label for="modal-roleID" class="form-label">Role ID</label>
                                            <input type="text" class="form-control" id="modal-roleID" name="roleID" readonly>
                                        </div>

                                        <div class="col-md-6">
                                            <label for="modal-createdAt" class="form-label">Created At</label>
                                            <input type="text" class="form-control" id="modal-createdAt" name="createdAt" readonly>
                                        </div>

                                        <div class="col-md-6">
                                            <label for="modal-fullname" class="form-label">Full Name</label>
                                            <input type="text" class="form-control" id="modal-fullname" name="fullName" required>
                                        </div>

                                        <div class="col-md-6">
                                            <label for="modal-email" class="form-label">Email</label>
                                            <input type="email" class="form-control" id="modal-email" name="email" required>
                                        </div>

                                        <div class="col-md-6">
                                            <label for="modal-phone" class="form-label">Phone Number</label>
                                            <input type="text" class="form-control" id="modal-phone" name="phoneNumber" required>
                                        </div>

                                        <div class="col-md-6">
                                            <label for="modal-address" class="form-label">Address</label>
                                            <input type="text" class="form-control" id="modal-address" name="address" required>
                                        </div>

                                        <div class="col-md-6 mt-4">
                                            <label for="modal-status" class="form-label">Status</label>
                                            <select class="form-select" id="modal-status" name="status">
                                                <option value="1">Active (1)</option>
                                                <option value="0">Inactive (0)</option>
                                            </select>
                                        </div>

                                    </div>

                                    <div class="modal-footer">
                                        <button type="submit" name="service" value="updateUser" class="btn btn-primary">Save Changes</button>
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>


                    <!-- Toast Container -->
                    <div id="toastContainer" style="
                         position: fixed;
                         top: 20px;
                         right: 20px;
                         z-index: 9999;
                         min-width: 250px;
                         ">
                    </div>

                    <!-- Toast style -->
                    <style>
                        .custom-toast {
                            min-width: 250px;
                            padding: 12px 20px;
                            margin-bottom: 12px;
                            border-radius: 6px;
                            color: white;
                            font-weight: bold;
                            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.3);
                            opacity: 1;
                            transition: opacity 0.5s ease;
                        }

                        .custom-toast.success {
                            background-color: #28a745;
                        }
                        .custom-toast.error {
                            background-color: #dc3545;
                        }
                        .custom-toast.warning {
                            background-color: #ffc107;
                            color: #333;
                        }
                    </style>

                    <!-- ./wrapper -->

                    <!-- jQuery 2.2.3 -->
                    <script src="${ctx}/AdminLTE/AdminPages/plugins/jQuery/jquery-2.2.3.min.js"></script>
                    <!-- Bootstrap 3.3.6 -->
                    <script src="${ctx}/AdminLTE/AdminPages/bootstrap/js/bootstrap.min.js"></script>
                    <!-- DataTables -->
                    <script src="${ctx}/AdminLTE/AdminPages/plugins/datatables/jquery.dataTables.min.js"></script>
                    <script src="${ctx}/AdminLTE/AdminPages/plugins/datatables/dataTables.bootstrap.min.js"></script>
                    <!-- SlimScroll -->
                    <script src="${ctx}/AdminLTE/AdminPages/plugins/slimScroll/jquery.slimscroll.min.js"></script>
                    <!-- FastClick -->
                    <script src="${ctx}/AdminLTE/AdminPages/plugins/fastclick/fastclick.js"></script>
                    <!-- AdminLTE App -->
                    <script src="${ctx}/AdminLTE/AdminPages/dist/js/app.min.js"></script>
                    <!-- AdminLTE for demo purposes -->
                    <script src="${ctx}/AdminLTE/AdminPages/dist/js/demo.js"></script>
                    <!-- page script -->

                    <script>
                                                                   function showToast(message, toastType) {
                                                                       const toast = document.createElement('div');
                                                                       toast.className = `custom-toast ${toastType}`;
                                                                       toast.textContent = message;
                                                                       console.log("Toast type:", toastType);
                                                                       document.getElementById('toastContainer').appendChild(toast);

                                                                       setTimeout(() => {
                                                                           toast.style.opacity = '0';
                                                                           setTimeout(() => toast.remove(), 500);
                                                                       }, 3000);
                                                                   }
                    </script>


                    <script>
                        $('#updateUserModal').on('show.bs.modal', function (event) {
                            const button = $(event.relatedTarget);

                            $('#modal-userID').val(button.data('userid'));
                            $('#modal-roleID').val(button.data('roleid'));
                            $('#modal-fullname').val(button.data('fullname'));
                            $('#modal-email').val(button.data('email'));
                            $('#modal-phone').val(button.data('phone'));
                            $('#modal-address').val(button.data('address'));
                            $('#modal-createdAt').val(button.data('createdat'));
                            $('#modal-status').val(button.data('status'));
                        });
                    </script>
                    <script>
                        window.addEventListener('DOMContentLoaded', function () {
                            const alertBox = document.getElementById("alertBox");
                            if (alertBox) {
                                setTimeout(function () {
                                    // Bootstrap 3: use jQuery .alert('close')
                                    $(alertBox).alert('close');
                                }, 5000); // Tự biến mất sau 4 giây
                            }
                        });
                    </script>
                    <script>
                        $(function () {
                            $("#example1").DataTable();
                            $('#example2').DataTable({
                                "paging": true,
                                "lengthChange": false,
                                "searching": false,
                                "ordering": true,
                                "info": true,
                                "autoWidth": false
                            });
                        });
                    </script>
                    <script>
                        $(function () {
                            $('.sidebar-menu').tree();
                        });
                    </script>
                    </body>
                    <c:if test="${not empty toast}">
                        <script>
                            showToast("${toast}", "${toastType}");
                        </script>
                    </c:if>
                    <%
                        String toast = (String) session.getAttribute("toast");
                        String toastType = (String) session.getAttribute("toastType");
                        if (toast != null) {
                            // Xoá sau khi hiển thị
                            session.removeAttribute("toast");
                            session.removeAttribute("toastType");
                        }
                    %>





                    </html>

