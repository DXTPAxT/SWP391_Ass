<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Send Notification</title>
    <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/datatables/dataTables.bootstrap.css">
    <style>
        .checkbox-custom {
            width: 18px; height: 18px;
            accent-color: #007bff;
        }
        .checkbox-custom:checked {
            box-shadow: 0 0 0 2px #007bff;
        }
        .sendall-label {
            font-weight: bold;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Send Notification</h2>
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success" role="alert">
            ${successMessage}
        </div>
    </c:if>
    <form action="${ctx}/NotificationServlet" method="post">
        <input type="hidden" name="service" value="send" />
        <input type="hidden" name="senderID" value="${sessionScope.user.userId}" />
        <div class="form-group">
            <label class="sendall-label">
                <input type="checkbox" id="sendAll" name="sendAll" value="true" class="checkbox-custom">
                <span id="sendAllText">Send to all users</span>
            </label>
        </div>
        <div class="form-group">
            <label>Select users to send notification:</label>
            <table id="userTable" class="table table-bordered table-hover">
                <thead>
                    <tr>
                        <th><input type="checkbox" id="checkAll" class="checkbox-custom"></th>
                        <th>User ID</th>
                        <th>Full Name</th>
                        <th>Email</th>
                        <th>Role</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="u" items="${userList}">
                        <tr>
                            <td><input type="checkbox" name="userID" value="${u.userId}" class="user-checkbox checkbox-custom"></td>
                            <td>${u.userId}</td>
                            <td>${u.fullname}</td>
                            <td>${u.email}</td>
                            <td>${u.role.roleName}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" class="form-control" id="title" name="title" required />
        </div>
        <div class="form-group">
            <label for="message">Message:</label>
            <textarea class="form-control" id="message" name="message" rows="4" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Send</button>
        <a href="${ctx}/NotificationServlet?service=list" class="btn btn-default">Cancel</a>
    </form>
</div>
<script src="${ctx}/AdminLTE/AdminPages/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${ctx}/AdminLTE/AdminPages/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${ctx}/AdminLTE/AdminPages/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script>
    $(function () {
        $('#userTable').DataTable({
            "columnDefs": [
                { "orderable": false, "targets": 0 }
            ]
        });
        $('#checkAll').on('change', function() {
            $('.user-checkbox').prop('checked', this.checked);
        });
        $('#sendAll').on('change', function() {
            var checked = this.checked;
            $('.user-checkbox, #checkAll').prop('disabled', checked);
            if (checked) {
                $('.user-checkbox, #checkAll').prop('checked', true);
            } else {
                $('.user-checkbox, #checkAll').prop('checked', false);
            }
        });
    });
</script>
</body>
</html> 