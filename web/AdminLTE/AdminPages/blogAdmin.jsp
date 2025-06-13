<%-- 
    Document   : AdminDashboard
    Created on : May 28, 2025, 10:03:33 AM
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>BlogAdmin | Dashboard</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/dist/css/skins/_all-skins.min.css">
        <!-- iCheck -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/iCheck/flat/blue.css">
        <!-- Morris chart -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/morris/morris.css">
        <!-- jvectormap -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
        <!-- Date Picker -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/datepicker/datepicker3.css">
        <!-- Daterange picker -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/daterangepicker/daterangepicker.css">
        <!-- Bootstrap WYSIHTML5 -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
        <script type="text/javascript">
            function doDelete(Post_id) {
                if (confirm("Are you sure to delete Post with Post_id =" + Post_id)) {
                    window.location = "${pageContext.request.contextPath}/blogdelete?Post_id=" + Post_id;
                }
            }
        </script>
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <header class="main-header">
                <a href="${ctx}/AdminLTE/index2.html" class="logo">
                    <span class="logo-mini"><b>A</b>LT</span>
                    <span class="logo-lg"><b>Admin</b>LTE</span>
                </a>
                <nav class="navbar navbar-static-top">
                    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <li class="dropdown messages-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-envelope-o"></i>
                                    <span class="label label-success">4</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="header">You have 4 messages</li>
                                    <li>
                                        <ul class="menu">
                                            <li>
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="${ctx}/AdminLTE/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                                                    </div>
                                                    <h4>Support Team <small><i class="fa52 fa-clock-o"></i> 5 mins</small></h4>
                                                    <p>Why not buy a new awesome theme?</p>
                                                </a>
                                            </li>
                                            <!-- Other message items omitted for brevity -->
                                        </ul>
                                    </li>
                                    <li class="footer"><a href="#">See All Messages</a></li>
                                </ul>
                            </li>
                            <li class="dropdown notifications-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-bell-o"></i>
                                    <span class="label label-warning">10</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="header">You have 10 notifications</li>
                                    <li>
                                        <ul class="menu">
                                            <li><a href="#"><i class="fa fa-users text-aqua"></i> 5 new members joined today</a></li>
                                            <!-- Other notification items omitted for brevity -->
                                        </ul>
                                    </li>
                                    <li class="footer"><a href="#">View all</a></li>
                                </ul>
                            </li>
                            <li class="dropdown tasks-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-flag-o"></i>
                                    <span class="label label-danger">9</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="header">You have 9 tasks</li>
                                    <li>
                                        <ul class="menu">
                                            <li>
                                                <a href="#">
                                                    <h3>Design some buttons <small class="pull-right">20%</small></h3>
                                                    <div class="progress xs">
                                                        <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                            <span class="sr-only">20% Complete</span>
                                                        </div>
                                                    </div>
                                                </a>
                                            </li>
                                            <!-- Other task items omitted for brevity -->
                                        </ul>
                                    </li>
                                    <li class="footer"><a href="#">View all tasks</a></li>
                                </ul>
                            </li>
                            <li class="dropdown user user-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <img src="${ctx}/AdminLTE/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                                    <span class="hidden-xs">Alexander Pierce</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="user-header">
                                        <img src="${ctx}/AdminLTE/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                                        <p>Alexander Pierce - Web Developer <small>Member since Nov. 2012</small></p>
                                    </li>
                                    <li class="user-body">
                                        <div class="row">
                                            <div class="col-xs-4 text-center"><a href="#">Followers</a></div>
                                            <div class="col-xs-4 text-center"><a href="#">Sales</a></div>
                                            <div class="col-xs-4 text-center"><a href="#">Friends</a></div>
                                        </div>
                                    </li>
                                    <li class="user-footer">
                                        <div class="pull-left"><a href="#" class="btn btn-default btn-flat">Profile</a></div>
                                        <div class="pull-right"><a href="#" class="btn btn-default btn-flat">Sign out</a></div>
                                    </li>
                                </ul>
                            </li>
                            <li><a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a></li>
                        </ul>
                    </div>
                </nav>
            </header>
            <aside class="main-sidebar">
                <section class="sidebar">
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="${ctx}/AdminLTE/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p>Alexander Pierce</p>
                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <form action="#" method="get" class="sidebar-form">
                        <div class="input-group">
                            <input type="text" name="q" class="form-control" placeholder="Search...">
                            <span class="input-group-btn">
                                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
                            </span>
                        </div>
                    </form>
                    <ul class="sidebar-menu">
                        <li class="header">MAIN NAVIGATION</li>
                        <li class="treeview active">
                            <a href="#">
                                <i class="fa fa-table"></i> <span>BlogAdmin</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="${ctx}/post-create.jsp"><i class="fa fa-plus"></i> Add New Blog</a></li>
                                <li><a href="#"><i class="fa fa-list"></i> Manage Categories</a></li>
                            </ul>
                        </li>
                    </ul>
                </section>
            </aside>
            <div class="content-wrapper">
                <section class="content-header">
                    <h1>Data Tables <small>advanced tables</small></h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">Blogs</a></li>
                        <li class="active">Blog Tables</li>
                    </ol>
                </section>
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">Manage Blogs</h3>
                                    <a href="${ctx}/AdminLTE/AdminPages/post-create.jsp" class="btn btn-primary pull-right" style="margin-top: -28px;">Add New Blog</a>
                                </div>
                                <div class="box-body">
                                    <c:if test="${empty requestScope.postlist}">
                                        <p>No blog posts available.</p>
                                    </c:if>
                                    <c:if test="${not empty requestScope.postlist}">
                                        <table id="example2" class="table table-bordered table-hover">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Title</th>
                                                    <th>Author</th>
                                                    <th>Updated Date</th>
                                                    <th>Content</th>
                                                    <th>Category</th>
                                                    <th>Thumbnail</th>
                                                    <th>Brief</th>
                                                    <th>Author ID</th>

                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="po" items="${requestScope.postlist}">
                                                    <tr>
                                                        <td>${po.post_id}</td>
                                                        <td><c:out value="${po.title}" /></td>
                                                        <td><c:out value="${po.author}" /></td>
                                                        <td><fmt:formatDate value="${po.updated_date}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
                                                        <td><c:out value="${fn:substring(po.content, 0, 50)}..." /></td>
                                                        <td>${po.bc_name}</td>

                                                        <td><img src="${po.thumbnail}" alt="Thumbnail" style="max-width: 100px;" onerror="this.src='${ctx}/AdminLTE/dist/img/default-thumbnail.jpg'" /></td>
                                                        <td><c:out value="${fn:substring(po.brief, 0, 50)}..." /></td>
                                                        <td>${po.add_id}</td>
                                                        <td>
                                                            <a href="${ctx}/update-post?post_id=${po.post_id}" class="btn btn-sm btn-primary">Update</a>
                                                            <a href="javascript:void(0);" onclick="doDelete(${po.post_id})" class="btn btn-sm btn-danger">Delete</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
            <footer class="main-footer">
                <div class="pull-right hidden-xs">
                    <b>Version</b> 2.3.8
                </div>
                <strong>Copyright &copy; 2025 <a href="#">Your Company</a>.</strong> All rights reserved.
            </footer>
            <aside class="control-sidebar control-sidebar-dark">
                <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
                    <li><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
                    <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
                </ul>
                <div class="tab-content">
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
                            <!-- Other recent activity items omitted for brevity -->
                        </ul>
                        <h3 class="control-sidebar-heading">Tasks Progress</h3>
                        <ul class="control-sidebar-menu">
                            <li>
                                <a href="javascript:void(0)">
                                    <h4 class="control-sidebar-subheading">Custom Template Design <span class="label label-danger pull-right">70%</span></h4>
                                    <div class="progress progress-xxs">
                                        <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
                                    </div>
                                </a>
                            </li>
                            <!-- Other task progress items omitted for brevity -->
                        </ul>
                    </div>
                    <div class="tab-pane" id="control-sidebar-settings-tab">
                        <form method="post">
                            <h3 class="control-sidebar-heading">General Settings</h3>
                            <div class="form-group">
                                <label class="control-sidebar-subheading">Report panel usage <input type="checkbox" class="pull-right" checked></label>
                                <p>Some information about this general settings option</p>
                            </div>
                            <!-- Other settings omitted for brevity -->
                        </form>
                    </div>
                </div>
            </aside>
            <div class="control-sidebar-bg"></div>
        </div>
        <!-- jQuery 2.2.3 -->
        <script src="${ctx}/AdminLTE/plugins/jQuery/jquery-2.2.3.min.js"></script>
        <!-- Bootstrap 3.3.6 -->
        <script src="${ctx}/AdminLTE/bootstrap/js/bootstrap.min.js"></script>
        <!-- DataTables -->
        <script src="${ctx}/AdminLTE/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="${ctx}/AdminLTE/plugins/datatables/dataTables.bootstrap.min.js"></script>
        <!-- SlimScroll -->
        <script src="${ctx}/AdminLTE/plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <!-- FastClick -->
        <script src="${ctx}/AdminLTE/plugins/fastclick/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="${ctx}/AdminLTE/dist/js/app.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="${ctx}/AdminLTE/dist/js/demo.js"></script>
        <script>
                                                                $(function () {
                                                                    $('#example2').DataTable({
                                                                        "paging": true,
                                                                        "lengthChange": false,
                                                                        "searching": true,
                                                                        "ordering": true,
                                                                        "info": true,
                                                                        "autoWidth": false
                                                                    });
                                                                });
        </script>
    </body>
</html>