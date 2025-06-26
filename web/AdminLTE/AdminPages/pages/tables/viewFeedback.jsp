<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Feedback Tables</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
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
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Feedback</a></li>
                <li class="active">Feedback Tables</li>
            </ol>
        </section>
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header with-border">
                            <div class="row align-items-center">
                                <!-- Optional: place search or filter tools here -->
                            </div>
                        </div>
                        <div class="box-body">
                            <table id="example2" class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>Feedback ID</th>
                                        <th>Full Name</th>
                                        <th>Review</th>
                                        <th>Admin Reply</th>
                                        <th>Category</th>
                                        <th>Rate</th>
                                        <th>Created At</th>
                                        <th>Status</th>
                                        <th>Action</th>
                                        <th>Reply</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:choose>
                                        <c:when test="${not empty feedbacks}">
                                            <c:forEach var="feedback" items="${feedbacks}">
                                                <tr>
                                                    <td>${feedback.feedbackID}</td>
                                                    <td>${feedback.fullname}</td>
                                                    <td>${feedback.content}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${not empty feedback.reply}">${feedback.reply}</c:when>
                                                            <c:otherwise><span class="text-muted">Not replied</span></c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td>${feedback.categoryName}</td>
                                                    <td>${feedback.rate}</td>
                                                    <td>${feedback.createdAt}</td>
                                                    <td>
                                                        <span class="label 
                                                            <c:choose>
                                                                <c:when test="${feedback.status == 0}">label-danger</c:when>
                                                                <c:when test="${not empty feedback.reply}">label-primary</c:when>
                                                                <c:when test="${feedback.status == 1}">label-success</c:when>
                                                                <c:otherwise>label-default</c:otherwise>
                                                            </c:choose>">
                                                            <c:choose>
                                                                <c:when test="${feedback.status == 0}">Inactive</c:when>
                                                                <c:when test="${not empty feedback.reply}">Replied</c:when>
                                                                <c:when test="${feedback.status == 1}">Not replied</c:when>
                                                                <c:otherwise>Unknown</c:otherwise>
                                                            </c:choose>
                                                        </span>
                                                    </td>
                                                    <td>
                                                        <c:if test="${feedback.status == 1 || feedback.status == 2}">
                                                            <form action="${pageContext.request.contextPath}/admin/updateFeedbackStatus" method="post" style="display:inline;">
                                                                <input type="hidden" name="feedbackID" value="${feedback.feedbackID}" />
                                                                <button type="submit" name="status" value="0" class="btn btn-xs btn-danger">Set Inactive</button>
                                                            </form>
                                                        </c:if>
                                                        <c:if test="${feedback.status == 0}">
                                                            <form action="${pageContext.request.contextPath}/admin/updateFeedbackStatus" method="post" style="display:inline;">
                                                                <input type="hidden" name="feedbackID" value="${feedback.feedbackID}" />
                                                                <button type="submit" name="status" value="1" class="btn btn-xs btn-success">Set Active</button>
                                                            </form>
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${feedback.status == 1}">
                                                            <a href="${pageContext.request.contextPath}/admin/replyFeedback?feedbackID=${feedback.feedbackID}" class="btn btn-xs btn-primary">Reply</a>
                                                        </c:if>
                                                        <c:if test="${feedback.status == 2}">
                                                            <a href="${pageContext.request.contextPath}/admin/replyFeedback?feedbackID=${feedback.feedbackID}&edit=true" class="btn btn-xs btn-info">Edit</a>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                                <td colspan="11" class="text-center text-muted">No feedback found.</td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
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
    <div class="control-sidebar-bg"></div>
</div>
<script src="${ctx}/AdminLTE/AdminPages/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${ctx}/AdminLTE/AdminPages/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/AdminLTE/AdminPages/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${ctx}/AdminLTE/AdminPages/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="${ctx}/AdminLTE/AdminPages/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${ctx}/AdminLTE/AdminPages/plugins/fastclick/fastclick.js"></script>
<script src="${ctx}/AdminLTE/AdminPages/dist/js/app.min.js"></script>
<script src="${ctx}/AdminLTE/AdminPages/dist/js/demo.js"></script>
<script>
    $(function () {
        $('#example2').DataTable({
            "paging": true,
            "lengthChange": true,
            "searching": true,
            "ordering": true,
            "info": true,
            "autoWidth": true
        });
    });
    $(function () {
        $('.sidebar-menu').tree();
    });
</script>
</body>
</html>