<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My Account</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    </head>
    <body class="bg-gray-100">
        <div class="container mx-auto p-6">
            <h1 class="text-2xl font-bold mb-4">My Account</h1>

            <!-- Toast Notification -->
            <c:if test="${not empty toast}">
                <div class="mb-4 p-4 rounded ${toastType == 'success' ? 'bg-green-100 text-green-700' : 'bg-red-100 text-red-700'}">
                    ${toast}
                </div>
            </c:if>

            <!-- User Profile Form -->
            <form action="${pageContext.request.contextPath}/User" method="post" class="bg-white p-6 rounded shadow-md">
                <input type="hidden" name="service" value="updateProfile">
                <input type="hidden" name="userID" value="${user.userID}">
                <div class="mb-4">
                    <label class="block text-gray-700">Email</label>
                    <input type="email" name="email" value="${user.email}" class="w-full p-2 border rounded" required>
                </div>
                <div class="mb-4">
                    <label class="block text-gray-700">Full Name</label>
                    <input type="text" name="fullName" value="${user.fullname}" class="w-full p-2 border rounded" required>
                </div>
                <div class="mb-4">
                    <label class="block text-gray-700">Address</label>
                    <input type="text" name="address" value="${user.address}" class="w-full p-2 border rounded" required>
                </div>
                <div class="mb-4">
                    <label class="block text-gray-700">Phone Number</label>
                    <input type="text" name="phoneNumber" value="${user.phoneNumber}" class="w-full p-2 border rounded" required>
                </div>
                <button type="submit" class="bg-blue-500 text-white p-2 rounded hover:bg-blue-600">Update Profile</button>
            </form>
        </div>
    </body>
</html>