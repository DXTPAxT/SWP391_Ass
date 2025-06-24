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
                                        <th>Admin Reply</th> <!-- Thêm dòng này -->
                                        <th>Order Item ID</th>
                                        <th>Category</th>
                                        <th>Rate</th>
                                        <th>Created At</th>
                                        <th>Status</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="feedback" items="${feedbacks}">
                                        <tr>
                                            <td>${feedback.feedbackID}</td>
                                            <td>${feedback.userID}</td>
                                            <td>${feedback.fullname}</td>
                                            <td>${feedback.content}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${not empty feedback.reply}">
                                                        ${feedback.reply}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="text-muted">Chưa trả lời</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${feedback.orderItemID}</td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/CategoriesController?service=detail&categoryID=${feedback.categoryID}" 
                                                   class="btn btn-xs btn-info" target="_blank">
                                                    ${feedback.categoryName}
                                                </a>
                                            </td>
                                            <td>${feedback.rate}</td>
                                            <td>${feedback.createdAt}</td>
                                            <td>
                                                <span class="label 
                                                    <c:choose>
                                                        <c:when test="${feedback.status == 0}">label-warning</c:when>
                                                        <c:when test="${not empty feedback.reply}">label-primary</c:when>
                                                        <c:when test="${feedback.status == 1}">label-success</c:when>
                                                        <c:otherwise>label-default</c:otherwise>
                                                    </c:choose>
                                                ">
                                                    <c:choose>
                                                        <c:when test="${feedback.status == 0}">Inactive</c:when>
                                                        <c:when test="${not empty feedback.reply}">Đã trả lời</c:when>
                                                        <c:when test="${feedback.status == 1}">Chưa trả lời</c:when>
                                                        <c:otherwise>Unknown</c:otherwise>
                                                    </c:choose>
                                                </span>
                                            </td>
                                            <td>
                                                <!-- Nút chuyển trạng thái Active/Inactive cho mọi trạng thái (0, 1, 2) -->
                                                <c:if test="${feedback.status == 1 || feedback.status == 2}">
                                                    <form action="${pageContext.request.contextPath}/admin/updateFeedbackStatus" method="post" style="display:inline;">
                                                        <input type="hidden" name="feedbackID" value="${feedback.feedbackID}" />
                                                        <button type="submit" name="status" value="0"
                                                            class="btn btn-xs btn-warning">
                                                            Đổi Inactive
                                                        </button>
                                                    </form>
                                                </c:if>
                                                <c:if test="${feedback.status == 0}">
                                                    <form action="${pageContext.request.contextPath}/admin/updateFeedbackStatus" method="post" style="display:inline;">
                                                        <input type="hidden" name="feedbackID" value="${feedback.feedbackID}" />
                                                        <button type="submit" name="status" value="1"
                                                            class="btn btn-xs btn-success">
                                                            Đổi Active
                                                        </button>
                                                    </form>
                                                </c:if>
                                                <!-- Nút trả lời: chỉ hiện nếu đang Active (status == 1) -->
                                                <c:if test="${feedback.status == 1}">
                                                    <a href="${pageContext.request.contextPath}/admin/replyFeedback?feedbackID=${feedback.feedbackID}" 
                                                       class="btn btn-xs btn-primary" style="margin-left:5px;">Trả lời</a>
                                                </c:if>
                                                <!-- Nút sửa: chỉ hiện nếu đã trả lời (status == 2) -->
                                                <c:if test="${feedback.status == 2}">
                                                    <a href="${pageContext.request.contextPath}/admin/replyFeedback?feedbackID=${feedback.feedbackID}&edit=true" 
                                                       class="btn btn-xs btn-info" style="margin-left:5px;">Sửa</a>
                                                </c:if>
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