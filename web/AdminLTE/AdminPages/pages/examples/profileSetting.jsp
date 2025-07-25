<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="models.User" %>
<% User user = (User) session.getAttribute("user"); %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>User Profile | AdminLTE</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

  <!-- Correct paths to AdminLTE static resources -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/AdminLTE/AdminPages/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/AdminLTE/AdminPages/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/AdminLTE/AdminPages/dist/css/skins/_all-skins.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <div class="content-wrapper">
    <section class="content-header">
      <h1>User Profile</h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/AdminDashbordServlet"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Profile</li>
      </ol>
    </section>

    <section class="content">
      <div class="row">
        <div class="col-md-3">
          <div class="box box-primary">
            <div class="box-body box-profile">
              <img class="profile-user-img img-responsive img-circle" src="${pageContext.request.contextPath}/AdminLTE/AdminPages/dist/img/user2-160x160.jpg" alt="User profile picture">
              <h3 class="profile-username text-center">${user.fullname}</h3>
              <p class="text-muted text-center">${user.role.roleName}</p>
              <ul class="list-group list-group-unbordered">
                <li class="list-group-item">
                  <b>Email</b> <span class="pull-right">${user.email}</span>
                </li>
                <li class="list-group-item">
                  <b>Phone</b> <span class="pull-right">${user.phoneNumber}</span>
                </li>
              </ul>
              <a href="#" class="btn btn-primary btn-block"><b>Follow</b></a>
            </div>
          </div>
        </div>

        <div class="col-md-9">
          <div class="nav-tabs-custom">
            <ul class="nav nav-tabs">
              <li class="active"><a href="#settings" data-toggle="tab">Settings</a></li>
            </ul>
            <div class="tab-content">
              <div class="active tab-pane" id="settings">
                <form class="form-horizontal" method="post" action="UserController">
                  <input type="hidden" name="service" value="updateProfile" />
                  <div class="form-group">
                    <label class="col-sm-2 control-label">Full Name</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" name="fullName" value="${user.fullname}" required>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                      <input type="email" class="form-control" name="email" value="${user.email}" required>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">Phone</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" name="phone" value="${user.phoneNumber}">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                      <button type="submit" class="btn btn-danger">Update</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>

  <div class="control-sidebar-bg"></div>
</div>

<!-- Scripts with correct paths -->
<script src="${pageContext.request.contextPath}/AdminLTE/AdminPages/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${pageContext.request.contextPath}/AdminLTE/AdminPages/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/AdminLTE/AdminPages/dist/js/app.min.js"></script>
<script src="${pageContext.request.contextPath}/AdminLTE/AdminPages/dist/js/demo.js"></script>
</body>
</html>