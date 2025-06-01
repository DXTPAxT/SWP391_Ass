<%-- 
    Document   : insertProduct
    Created on : May 30, 2025, 10:18:03 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>AdminLTE 2 | Advanced form elements</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <!-- daterange picker -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/daterangepicker/daterangepicker.css">
        <!-- bootstrap datepicker -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/datepicker/datepicker3.css">
        <!-- iCheck for checkboxes and radio inputs -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/iCheck/all.css">
        <!-- Bootstrap Color Picker -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/colorpicker/bootstrap-colorpicker.min.css">
        <!-- Bootstrap time Picker -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/timepicker/bootstrap-timepicker.min.css">
        <!-- Select2 -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/select2/select2.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/dist/css/skins/_all-skins.min.css">

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
                <a href="${ctx}/AdminLTE/AdminPages/index2.html" class="logo">
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
                                    <!-- Notifications: style can be found in dropdown.less -->
                                    <li class="dropdown notifications-menu">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                            <i class="fa fa-bell-o"></i>
                                            <span class="label label-warning">10</span>
                                        </a>
                                        <ul class="dropdown-menu">
                                            <li class="header">You have 10 notifications</li>

                                            <!-- Tasks: style can be found in dropdown.less -->
                                            <li class="dropdown tasks-menu">
                                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                    <i class="fa fa-flag-o"></i>
                                                    <span class="label label-danger">9</span>
                                                </a>
                                                <ul class="dropdown-menu">
                                                    <li class="header">You have 9 tasks</li>

                                                    <!-- User Account: style can be found in dropdown.less -->
                                                    <li class="dropdown user user-menu">
                                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                            <img src="${ctx}/AdminLTE/AdminPages/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                                                            <span class="hidden-xs">Alexander Pierce</span>
                                                        </a>
                                                        <ul class="dropdown-menu">
                                                            <!-- User image -->
                                                            <li class="user-header">
                                                                <img src="${ctx}/AdminLTE/AdminPages/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

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
                                                                    <i class="fa fa-laptop"></i> <span>Component</span>
                                                                    <span class="pull-right-container">
                                                                        <i class="fa fa-angle-left pull-right"></i>
                                                                    </span>
                                                                </a>
                                                                <ul class="treeview-menu">                               
                                                                    <li><a href="${ctx}/ComAdmin"><i class="fa fa-circle-o"></i>View Component</a></li>                                
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
                                                                    <c:forEach var="com" items="${requestScope.data}">
                                                                        <li><a href=""><i class="fa fa-circle-o"></i> ${com.componentName}</a></li>
                                                                        </c:forEach>                               
                                                                </ul>
                                                            </li>                 
                                                            <li class="treeview">
                                                                <a href="#">
                                                                    <i class="fa fa-laptop"></i> <span>User</span>
                                                                    <span class="pull-right-container">
                                                                        <i class="fa fa-angle-left pull-right"></i>
                                                                    </span>
                                                                </a>
                                                                <ul class="treeview-menu">                               
                                                                    <li><a href="${ctx}/Admin/user"><i class="fa fa-circle-o"></i>View Users</a></li>                                  
                                                                    <li><a href="${ctx}/Admin/user/add"><i class="fa fa-circle-o"></i>Add Users</a></li>                              
                                                                </ul>
                                                            </li>                  
                                                    </section>
                                                    <!-- /.sidebar -->
                                                </aside>

                                                <!-- Content Wrapper. Contains page content -->
                                                <div class="content-wrapper">
                                                    <!-- Content Header (Page header) -->
                                                    <section class="content-header">
                                                        <h1>
                                                            UPDATE COMPONENT

                                                        </h1>
                                                        <ol class="breadcrumb">
                                                            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                                                            <li><a href="#">Component</a></li>
                                                            <li class="active">Update Component</li>
                                                        </ol>
                                                    </section>

                                                    <!-- Main content -->
                                                    <div class="box box-warning">

                                                        <!-- /.box-header -->                                                       
                                                        <div class="box-body">
                                                            <form role="form" method="post" action="ComAdmin">
                                                                <input type="hidden" name="service" value="update">
                                                                <input type="hidden" name="submit" value="submit">

                                                               
                                                                <c:if test="${not empty component}">
                                                                    <div class="form-group">
                                                                        <label>Component ID</label>
                                                                        <input type="text" name="component_id" class="form-control" value="${component.componentID}" readonly>
                                                                    </div>
                                                                </c:if>

                                                                <!-- Category Name -->
                                                                <div class="form-group">
                                                                    <label>Component Name</label>
                                                                    <input type="text" name="component_name" class="form-control" value="${component.componentName}" required>
                                                                </div>

                                                                <!-- Quantity -->
                                                                <div class="form-group">
                                                                    <label>Quantity</label>
                                                                    <input type="number" name="quantity" class="form-control" value="${component.quantity}" required>
                                                                </div>

                                                                <!-- Status -->
                                                                <div class="form-group">
                                                                    <label>Status</label>
                                                                    <select name="status" class="form-control">
                                                                        <option value="0" ${component.status == 0 ? 'selected' : ''}>Inactive</option>
                                                                        <option value="1" ${component.status == 1 ? 'selected' : ''}>Active</option>
                                                                    </select>
                                                                </div>
                                                                <!-- Submit -->
                                                                <button type="submit" class="btn btn-success">Update Component</button>
                                                            </form>
                                                        </div>


                                                        <!-- /.box-body -->
                                                    </div>
                                                    <!-- /.box -->
                                                </div>


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
                                                    <!-- Create the tabs -->
                                                    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
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
                                                <!-- ./wrapper -->

                                                <!-- jQuery 2.2.3 -->
                                                <script src="${ctx}/AdminLTE/AdminPages/plugins/jQuery/jquery-2.2.3.min.js"></script>
                                                <!-- Bootstrap 3.3.6 -->
                                                <script src="${ctx}/AdminLTE/AdminPages/bootstrap/js/bootstrap.min.js"></script>
                                                <!-- Select2 -->
                                                <script src="${ctx}/AdminLTE/AdminPages/plugins/select2/select2.full.min.js"></script>
                                                <!-- InputMask -->
                                                <script src="${ctx}/AdminLTE/AdminPages/plugins/input-mask/jquery.inputmask.js"></script>
                                                <script src="${ctx}/AdminLTE/AdminPages/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
                                                <script src="${ctx}/AdminLTE/AdminPages/plugins/input-mask/jquery.inputmask.extensions.js"></script>
                                                <!-- date-range-picker -->
                                                <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
                                                <script src="${ctx}/AdminLTE/AdminPages/plugins/daterangepicker/daterangepicker.js"></script>
                                                <!-- bootstrap datepicker -->
                                                <script src="${ctx}/AdminLTE/AdminPages/plugins/datepicker/bootstrap-datepicker.js"></script>
                                                <!-- bootstrap color picker -->
                                                <script src="${ctx}/AdminLTE/AdminPages/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
                                                <!-- bootstrap time picker -->
                                                <script src="${ctx}/AdminLTE/AdminPages/plugins/timepicker/bootstrap-timepicker.min.js"></script>
                                                <!-- SlimScroll 1.3.0 -->
                                                <script src="${ctx}/AdminLTE/AdminPages/plugins/slimScroll/jquery.slimscroll.min.js"></script>
                                                <!-- iCheck 1.0.1 -->
                                                <script src="${ctx}/AdminLTE/AdminPages/plugins/iCheck/icheck.min.js"></script>
                                                <!-- FastClick -->
                                                <script src="${ctx}/AdminLTE/AdminPages/plugins/fastclick/fastclick.js"></script>
                                                <!-- AdminLTE App -->
                                                <script src="${ctx}/AdminLTE/AdminPages/dist/js/app.min.js"></script>
                                                <!-- AdminLTE for demo purposes -->
                                                <script src="${ctx}/AdminLTE/AdminPages/dist/js/demo.js"></script>
                                                <!-- Page script -->
                                                <script>
                                                    $(function () {
                                                        //Initialize Select2 Elements
                                                        $(".select2").select2();

                                                        //Datemask dd/mm/yyyy
                                                        $("#datemask").inputmask("dd/mm/yyyy", {"placeholder": "dd/mm/yyyy"});
                                                        //Datemask2 mm/dd/yyyy
                                                        $("#datemask2").inputmask("mm/dd/yyyy", {"placeholder": "mm/dd/yyyy"});
                                                        //Money Euro
                                                        $("[data-mask]").inputmask();

                                                        //Date range picker
                                                        $('#reservation').daterangepicker();
                                                        //Date range picker with time picker
                                                        $('#reservationtime').daterangepicker({timePicker: true, timePickerIncrement: 30, format: 'MM/DD/YYYY h:mm A'});
                                                        //Date range as a button
                                                        $('#daterange-btn').daterangepicker(
                                                                {
                                                                    ranges: {
                                                                        'Today': [moment(), moment()],
                                                                        'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                                                                        'Last 7 Days': [moment().subtract(6, 'days'), moment()],
                                                                        'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                                                                        'This Month': [moment().startOf('month'), moment().endOf('month')],
                                                                        'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                                                                    },
                                                                    startDate: moment().subtract(29, 'days'),
                                                                    endDate: moment()
                                                                },
                                                                function (start, end) {
                                                                    $('#daterange-btn span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                                                                }
                                                        );

                                                        //Date picker
                                                        $('#datepicker').datepicker({
                                                            autoclose: true
                                                        });

                                                        //iCheck for checkbox and radio inputs
                                                        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
                                                            checkboxClass: 'icheckbox_minimal-blue',
                                                            radioClass: 'iradio_minimal-blue'
                                                        });
                                                        //Red color scheme for iCheck
                                                        $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
                                                            checkboxClass: 'icheckbox_minimal-red',
                                                            radioClass: 'iradio_minimal-red'
                                                        });
                                                        //Flat red color scheme for iCheck
                                                        $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
                                                            checkboxClass: 'icheckbox_flat-green',
                                                            radioClass: 'iradio_flat-green'
                                                        });

                                                        //Colorpicker
                                                        $(".my-colorpicker1").colorpicker();
                                                        //color picker with addon
                                                        $(".my-colorpicker2").colorpicker();

                                                        //Timepicker
                                                        $(".timepicker").timepicker({
                                                            showInputs: false
                                                        });
                                                    });
                                                </script>
                                                </body>
                                                </html>
