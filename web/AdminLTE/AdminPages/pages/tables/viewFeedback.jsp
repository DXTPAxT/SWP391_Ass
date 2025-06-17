<%-- 
    Document   : viewFeedback
    Created on : Jun 14, 2025, 8:43:28 PM
    Author     : Admin
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>AdminLTE 2 | Data Tables</title>
    <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/dist/css/skins/_all-skins.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../../components/header.jsp" />
    <jsp:include page="../../components/sidebar.jsp">
        <jsp:param name="ctx" value="${ctx}" />
    </jsp:include>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                Feedback Tables
                <small>advanced tables</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Tables</a></li>
                <li class="active">Feedback tables</li>
            </ol>
        </section>
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-body">
                            <table id="example2" class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>Feedback ID</th>
                                        <th>User ID</th>
                                        <th>Full Name</th>
                                        <th>Review</th>
                                        <th>Order Item ID</th>
                                        <th>Rate</th>
                                        <th>Created At</th>
                                        <th>Status</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="feedback" items="${feedbacks}">
                                        <tr>
                                            <td>${feedback.feedbackID}</td>
                                            <td>${feedback.userID}</td>
                                            <td>${feedback.fullname}</td>
                                            <td>${feedback.content}</td>
                                            <td>${feedback.orderItemID}</td>
                                            <td>${feedback.rate}</td>
                                            <td>${feedback.createdAt}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${feedback.status == 1}">
                                                        <span class="label label-success">Active</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="label label-warning">Inactive</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="../../components/footer.jsp" />
    <jsp:include page="../../components/control-sidebar.jsp" />
</div>
<script src="${ctx}/AdminLTE/AdminPages/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${ctx}/AdminLTE/AdminPages/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${ctx}/AdminLTE/AdminPages/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script>
    $(function () {
        $('#example2').DataTable({
            "language": {
                "emptyTable": "No Feedback."
            }
        });
    });
</script>
</body>
</html>