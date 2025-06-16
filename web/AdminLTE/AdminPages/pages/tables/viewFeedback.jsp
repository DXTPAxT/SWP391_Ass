<%-- 
    Document   : viewFeedback
    Created on : Jun 14, 2025, 8:43:28 PM
    Author     : Admin
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/dist/css/custom.css">
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/dist/css/skins/_all-skins.min.css">
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

            <div class="content-wrapper">
                <section class="content-header">
                    <h1>Feedback Tables <small>Manage user feedback</small></h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">Tables</a></li>
                        <li class="active">Feedback Tables</li>
                    </ol>
                </section>
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box">
                                <div class="box-body">
                                    <c:if test="${sessionScope.user.roleID != 1}">
                                        <div class="alert alert-danger">Access denied. Only admins can view this page.</div>
                                    </c:if>
                                    <c:if test="${sessionScope.user.roleID == 1}">
                                        <table id="example2" class="table table-bordered table-hover">
                                            <thead>
                                                <tr>
                                                    <th>Feedback ID</th>
                                                    <th>User ID</th>
                                                    <th>Full Name</th>
                                                    <th>Content</th>
                                                    <th>Rate</th>
                                                    <th>Created At</th>
                                                    <th>Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:if test="${not empty feedbacks}">
                                                    <c:forEach var="feedback" items="${feedbacks}">
                                                        <tr>
                                                            <td>${feedback.feedbackID}</td>
                                                            <td>${feedback.userID}</td>
                                                            <td>${feedback.fullName}</td>
                                                            <td>${feedback.content}</td>
                                                            <td>${feedback.rate}</td>
                                                            <td>
                                                                <fmt:parseDate value="${feedback.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" var="createdDate"/>
                                                                <fmt:formatDate value="${createdDate}" pattern="yyyy-MM-dd"/>
                                                            </td>
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
                                                            <td>
                                                                <a href="${ctx}/FeedbackAdmin?service=edit&feedbackID=${feedback.feedbackID}" class="btn btn-primary btn-sm">Edit</a>
                                                                <a href="${ctx}/FeedbackAdmin?service=delete&feedbackID=${feedback.feedbackID}" 
                                                                   onclick="return confirm('Are you sure you want to delete this feedback?');"
                                                                   class="btn btn-danger btn-sm">Delete</a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${empty feedbacks}">
                                                    <tr>
                                                        <td colspan="8" style="text-align:center;">No Feedback.</td>
                                                    </tr>
                                                </c:if>
                                            </tbody>
                                        </table>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>

            <jsp:include page="../../components/footer.jsp" />
            <jsp:include page="../../components/control-sidebar.jsp" />
        </div>

        <div id="toastContainer" style="position: fixed; top: 20px; right: 20px; z-index: 9999; min-width: 250px;"></div>
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

        <script src="${ctx}/AdminLTE/AdminPages/plugins/jQuery/jquery-2.2.3.min.js"></script>
        <script src="${ctx}/AdminLTE/AdminPages/bootstrap/js/bootstrap.min.js"></script>
        <script src="${ctx}/AdminLTE/AdminPages/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="${ctx}/AdminLTE/AdminPages/plugins/datatables/dataTables.bootstrap.min.js"></script>
        <script src="${ctx}/AdminLTE/AdminPages/plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <script src="${ctx}/AdminLTE/AdminPages/plugins/fastclick/fastclick.js"></script>
        <script src="${ctx}/AdminLTE/AdminPages/dist/js/app.min.js"></script>
        <script src="${ctx}/AdminLTE/AdminPages/dist/js/demo.js"></script>
        <script>
                                                                       function showToast(message, toastType) {
                                                                           const toast = document.createElement('div');
                                                                           toast.className = `custom-toast ${toastType}`;
                                                                           toast.textContent = message;
                                                                           document.getElementById('toastContainer').appendChild(toast);
                                                                           setTimeout(() => {
                                                                               toast.style.opacity = '0';
                                                                               setTimeout(() => toast.remove(), 500);
                                                                           }, 3000);
                                                                       }
        </script>
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
        </script>
        <c:if test="${not empty toast}">
            <script>
                showToast("${toast}", "${toastType}");
            </script>
        </c:if>
        <% 
            String toast = (String) session.getAttribute("toast");
            String toastType = (String) session.getAttribute("toastType");
            if (toast != null) {
                session.removeAttribute("toast");
                session.removeAttribute("toastType");
            }
        %>
    </body>
</html>