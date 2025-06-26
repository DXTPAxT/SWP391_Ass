<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Send Notification</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/AdminLTE/AdminPages/bootstrap/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>Send Notification</h2>
    <form action="${pageContext.request.contextPath}/NotificationServlet" method="post">
        <input type="hidden" name="service" value="send" />
        <input type="hidden" name="senderID" value="1" />
        <div class="form-group">
            <label for="userID">Recipient (UserID):</label>
            <input type="number" class="form-control" id="userID" name="userID" required />
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
        <a href="${pageContext.request.contextPath}/NotificationServlet?service=list" class="btn btn-default">Cancel</a>
    </form>
</div>
</body>
</html> 