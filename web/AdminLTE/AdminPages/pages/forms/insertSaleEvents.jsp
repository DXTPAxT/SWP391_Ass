<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Insert Sale Event</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

        <!-- Stylesheets -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/daterangepicker/daterangepicker.css">
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/datepicker/datepicker3.css">
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/iCheck/all.css">
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/colorpicker/bootstrap-colorpicker.min.css">
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/timepicker/bootstrap-timepicker.min.css">
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/select2/select2.min.css">
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/dist/css/AdminLTE.min.css">    
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/dist/css/custom.css">
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/dist/css/skins/_all-skins.min.css">
    </head>

    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <jsp:include page="../../components/header.jsp" />
            <jsp:include page="../../components/sidebar.jsp">
                <jsp:param name="ctx" value="${ctx}" />
            </jsp:include>

            <!-- Content Wrapper -->
            <div class="content-wrapper">
                <section class="content-header">
                    <h1>Insert Sale Event</h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">Sale Events</a></li>
                        <li class="active">Insert</li>
                    </ol>
                </section>

                <!-- Main content -->
                <div class="box box-success">
                    <div class="box-body">

                        <c:if test="${not empty error}">
                            <div class="alert alert-danger" style="font-weight:bold;">${error}</div>
                        </c:if>

                        <form action="${ctx}/addsale" method="post">
                            <!-- Category -->
                            <div class="form-group">
                                <label>Category:</label>
                                <select name="categoryID" class="form-control" required>
                                    <c:forEach var="cat" items="${categoryList}">
                                        <option value="${cat.categoryID}">${cat.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                   

                    <!-- Post -->
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Post</label>
                        <div class="col-sm-10">
                            <select name="Post_id" class="form-control" required>
                                <c:forEach var="post" items="${postList}">
                                    <option value="${post.post_id}">${post.title}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <!-- Start Date -->
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Start Date</label>
                        <div class="col-sm-10">
                            <input type="date" name="startDate" class="form-control" required />
                        </div>
                    </div>

                    <!-- End Date -->
                    <div class="form-group">
                        <label class="col-sm-2 control-label">End Date</label>
                        <div class="col-sm-10">
                            <input type="date" name="endDate" class="form-control" required />
                        </div>
                    </div>

                    <!-- Discount -->
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Discount (%)</label>
                        <div class="col-sm-10">
                            <input type="number" step="0.01" name="discountPercent" class="form-control" placeholder="Enter discount percent" required />
                        </div>
                    </div>

                    
                    <div class="box-footer">
                        <a href="${ctx}/saleevents" class="btn btn-secondary">Cancel</a>
                        <button type="submit" class="btn btn-primary pull-right">Add Sale Event</button>
                    </div>
                    </form>
                </div>
            </div>

            <jsp:include page="../../components/footer.jsp" />
            <jsp:include page="../../components/control-sidebar.jsp" />
        </div>
    </div>

    <!-- Scripts -->
    <script src="${ctx}/AdminLTE/AdminPages/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <script src="${ctx}/AdminLTE/AdminPages/bootstrap/js/bootstrap.min.js"></script>
    <script src="${ctx}/AdminLTE/AdminPages/plugins/select2/select2.full.min.js"></script>
    <script src="${ctx}/AdminLTE/AdminPages/plugins/input-mask/jquery.inputmask.js"></script>
    <script src="${ctx}/AdminLTE/AdminPages/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
    <script src="${ctx}/AdminLTE/AdminPages/plugins/input-mask/jquery.inputmask.extensions.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
    <script src="${ctx}/AdminLTE/AdminPages/plugins/daterangepicker/daterangepicker.js"></script>
    <script src="${ctx}/AdminLTE/AdminPages/plugins/datepicker/bootstrap-datepicker.js"></script>
    <script src="${ctx}/AdminLTE/AdminPages/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
    <script src="${ctx}/AdminLTE/AdminPages/plugins/timepicker/bootstrap-timepicker.min.js"></script>
    <script src="${ctx}/AdminLTE/AdminPages/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <script src="${ctx}/AdminLTE/AdminPages/plugins/iCheck/icheck.min.js"></script>
    <script src="${ctx}/AdminLTE/AdminPages/plugins/fastclick/fastclick.js"></script>
    <script src="${ctx}/AdminLTE/AdminPages/dist/js/app.min.js"></script>
    <script src="${ctx}/AdminLTE/AdminPages/dist/js/demo.js"></script>
</body>
</html>
