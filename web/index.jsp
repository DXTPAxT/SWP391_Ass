<%-- 
    Document   : index
    Created on : May 23, 2025, 11:58:49 AM
    Author     : PC ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${user.getUserRoleID() != 1}">
            <% response.sendRedirect(request.getContextPath() + "/Home"); %>
        </c:if>
        <c:if test="${user.getUserRoleID() == 1}">
            <% response.sendRedirect(request.getContextPath() + "/Admin"); %>
        </c:if>
    </body>
</html>
