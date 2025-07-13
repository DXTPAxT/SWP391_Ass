<%-- 
    Document   : addNewUser
    Created on : Jun 3, 2025, 10:14:57 AM
    Author     : PC ASUS
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
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/dist/css/custom.css">

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
            <jsp:include page="../../components/header.jsp" />
            <jsp:include page="../../components/sidebar.jsp">
                <jsp:param name="ctx" value="${ctx}" />
            </jsp:include>
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>INSERT SALE EVENTS</h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">Sale Events</a></li>
                        <li class="active">Insert Sale Events</li>
                    </ol>
                </section>
                <!-- Main content -->
                <div class="box box-success">
                    <div class="box-body">
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger" style="font-weight:bold;">${error}</div>
                        </c:if>


                        <form action="${ctx}/addsale" method="post" class="form-horizontal">
                            <!-- Category Dropdown -->
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Category</label>
                                <div class="col-sm-10">
                                    <select name="categoryID" class="form-control" required>
                                        <c:forEach var="cat" items="${categories}">
                                            <option value="${cat.categoryID}">${cat.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <!-- Post Dropdown -->
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Post</label>
                                <div class="col-sm-10">
                                    <select name="postID" class="form-control" required>
                                        <c:forEach var="post" items="${activePosts}">
                                            <option value="${post.post_id}">${post.title}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">Start Date</label>
                                <div class="col-sm-10">
                                    <input type="date" name="startDate" class="form-control" required />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">End Date</label>
                                <div class="col-sm-10">
                                    <input type="date" name="endDate" class="form-control" required />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">Discount (%)</label>
                                <div class="col-sm-10">
                                    <input type="number" step="0.01" name="discountPercent" class="form-control" placeholder="Enter discount percent" required />
                                </div>
                            </div>
                            <!-- Created By -->
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Created By (Staff)</label>
                                <div class="col-sm-10">
                                    <input type="number" name="createdBy" class="form-control" required />
                                </div>
                            </div>

                            <!-- Approved By -->
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Approved By (Admin)</label>
                                <div class="col-sm-10">
                                    <input type="number" name="approvedBy" class="form-control" />
                                </div>
                            </div>
                            <div class="box-footer">
                                <button type="submit" class="btn btn-primary pull-right">Add Sale Event</button>
                            </div>
                        </form>



                        <jsp:include page="../../components/footer.jsp" />
                        <jsp:include page="../../components/control-sidebar.jsp" />
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
                    </body>
                    </html>