<%-- Document : insertProduct Created on : May 28, 2025, 9:48:28 PM Author : Admin --%>
    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <c:set var="ctx" value="${pageContext.request.contextPath}" />
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="utf-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <title>AdminLTE 2 | Data Tables</title>
                <!-- Tell the browser to be responsive to screen width -->
                <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
                <!-- Bootstrap 3.3.6 -->
                <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/bootstrap/css/bootstrap.min.css">
                <!-- Font Awesome -->
                <link rel="stylesheet"
                    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
                <!-- Ionicons -->
                <link rel="stylesheet"
                    href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
                <!-- DataTables -->
                <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages//plugins/datatables/dataTables.bootstrap.css">
                <!-- Theme style -->
                <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages//dist/css/AdminLTE.min.css">
                <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages//dist/css/custom.css">
                <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
                <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages//dist/css/skins/_all-skins.min.css">

                <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
                <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
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

                    <!-- Content Wrapper. Contains page content -->
                    <div class="content-wrapper">
                        <!-- Content Header (Page header) -->
                        <section class="content-header">
                            <h1>
                                User Tables
                                <small>advanced tables</small>
                            </h1>
                            <ol class="breadcrumb">
                                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                                <li><a href="#">Tables</a></li>
                                <li class="active">User tables</li>
                            </ol>
                        </section>
                        <!-- Main content -->
                        <section class="content">
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="box">
                                        <!-- /.box-header -->
                                        <div class="box-body">
                                            <table id="example2" class="table table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>User ID</th>
                                                        <th>Role ID</th>
                                                        <th>Full Name</th>
                                                        <th>Email</th>
                                                        <th>Phone number</th>
                                                        <th>Address</th>
                                                        <th>Created At</th>
                                                        <th>Status</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:if test="${not empty users}">
                                                        <c:forEach var="user" items="${users}">
                                                            <tr>
                                                                <td>${user.userID}</td>
                                                                <td>${user.roleID}</td>
                                                                <td>${user.fullname}</td>
                                                                <td>${user.email}</td>
                                                                <td>${user.phoneNumber}</td>
                                                                <td>${user.address}</td>
                                                                <td>${user.createdAt}</td>
                                                                <td>
                                                                    <c:if test="${user.status == 1}">
                                                                        <a href="${ctx}/User?service=toggleStatus&userID=${user.userID}"
                                                                            class="btn btn-success btn-sm">Active</a>
                                                                    </c:if>
                                                                    <c:if test="${user.status == 0}">
                                                                        <a href="${ctx}/User?service=toggleStatus&userID=${user.userID}"
                                                                            class="btn btn-warning btn-sm">Disable</a>
                                                                    </c:if>
                                                                </td>
                                                                <td>
                                                                    <button class="btn btn-primary btn-sm"
                                                                        data-toggle="modal"
                                                                        data-target="#updateUserModal"
                                                                        data-userid="${user.userID}"
                                                                        data-roleid="${user.roleID}"
                                                                        data-fullname="${user.fullname}"
                                                                        data-email="${user.email}"
                                                                        data-phone="${user.phoneNumber}"
                                                                        data-address="${user.address}"
                                                                        data-createdat="${user.createdAt}"
                                                                        data-status="${user.status}">
                                                                        Update
                                                                    </button>
                                                                    <a href="${ctx}/User?service=resetPassword&userID=${user.userID}"
                                                                        onclick="return confirm(`Reset user's password?`);"
                                                                        class="btn btn-danger btn-sm">Reset Password</a>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </c:if>
                                                    <c:if test="${empty users}">
                                                        <tr>
                                                            <td colspan="8" style="text-align:center;">No User.</td>
                                                        </tr>
                                                    </c:if>
                                                </tbody>
                                            </table>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                    </div>
                    <!-- /.box -->

                    <!-- /.content-wrapper -->
                    <jsp:include page="../../components/footer.jsp" />

                    <jsp:include page="../../components/control-sidebar.jsp" />
                </div>

                <!-- Modal Form -->
                <div class="modal fade" id="updateUserModal" tabindex="-1" aria-labelledby="updateUserModalLabel"
                    aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <form action="${ctx}/User" method="post">
                                <div class="modal-header">
                                    <h3 class="modal-title" id="updateUserModalLabel">Update User</h3>
                                </div>

                                <div class="modal-body row g-3">

                                    <!-- Hidden ID -->
                                    <input type="hidden" name="userID" id="modal-userID">

                                    <div class="col-md-6">
                                        <label for="modal-roleID" class="form-label">Role ID</label>
                                        <input type="text" class="form-control" id="modal-roleID" name="roleID"
                                            readonly>
                                    </div>

                                    <div class="col-md-6">
                                        <label for="modal-createdAt" class="form-label">Created At</label>
                                        <input type="text" class="form-control" id="modal-createdAt" name="createdAt"
                                            readonly>
                                    </div>

                                    <div class="col-md-6">
                                        <label for="modal-fullname" class="form-label">Full Name</label>
                                        <input type="text" class="form-control" id="modal-fullname" name="fullName"
                                            required>
                                    </div>

                                    <div class="col-md-6">
                                        <label for="modal-email" class="form-label">Email</label>
                                        <input type="email" class="form-control" id="modal-email" name="email" required>
                                    </div>

                                    <div class="col-md-6">
                                        <label for="modal-phone" class="form-label">Phone Number</label>
                                        <input type="text" class="form-control" id="modal-phone" name="phoneNumber"
                                            required>
                                    </div>

                                    <div class="col-md-6">
                                        <label for="modal-address" class="form-label">Address</label>
                                        <input type="text" class="form-control" id="modal-address" name="address"
                                            required>
                                    </div>

                                    <div class="col-md-6 mt-4">
                                        <label for="modal-status" class="form-label">Status</label>
                                        <select class="form-select" id="modal-status" name="status">
                                            <option value="1">Active (1)</option>
                                            <option value="0">Inactive (0)</option>
                                        </select>
                                    </div>

                                </div>

                                <div class="modal-footer">
                                    <button type="submit" name="service" value="updateUser" class="btn btn-primary">Save
                                        Changes</button>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>


                <!-- Toast Container -->
                <div id="toastContainer" style="
             position: fixed;
             top: 20px;
             right: 20px;
             z-index: 9999;
             min-width: 250px;
             ">
                </div>

                <!-- Toast style -->
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

                <!-- ./wrapper -->

                <!-- jQuery 2.2.3 -->
                <script src="${ctx}/AdminLTE/AdminPages/plugins/jQuery/jquery-2.2.3.min.js"></script>
                <!-- Bootstrap 3.3.6 -->
                <script src="${ctx}/AdminLTE/AdminPages/bootstrap/js/bootstrap.min.js"></script>
                <!-- DataTables -->
                <script src="${ctx}/AdminLTE/AdminPages/plugins/datatables/jquery.dataTables.min.js"></script>
                <script src="${ctx}/AdminLTE/AdminPages/plugins/datatables/dataTables.bootstrap.min.js"></script>
                <!-- SlimScroll -->
                <script src="${ctx}/AdminLTE/AdminPages/plugins/slimScroll/jquery.slimscroll.min.js"></script>
                <!-- FastClick -->
                <script src="${ctx}/AdminLTE/AdminPages/plugins/fastclick/fastclick.js"></script>
                <!-- AdminLTE App -->
                <script src="${ctx}/AdminLTE/AdminPages/dist/js/app.min.js"></script>
                <!-- AdminLTE for demo purposes -->
                <script src="${ctx}/AdminLTE/AdminPages/dist/js/demo.js"></script>
                <!-- page script -->

                <script>
                    function showToast(message, toastType) {
                        const toast = document.createElement('div');
                        toast.className = `custom-toast ${toastType}`;
                        toast.textContent = message;
                        console.log("Toast type:", toastType);
                        document.getElementById('toastContainer').appendChild(toast);

                        setTimeout(() => {
                            toast.style.opacity = '0';
                            setTimeout(() => toast.remove(), 500);
                        }, 3000);
                    }
                </script>


                <script>
                    $('#updateUserModal').on('show.bs.modal', function (event) {
                        const button = $(event.relatedTarget);

                        $('#modal-userID').val(button.data('userid'));
                        $('#modal-roleID').val(button.data('roleid'));
                        $('#modal-fullname').val(button.data('fullname'));
                        $('#modal-email').val(button.data('email'));
                        $('#modal-phone').val(button.data('phone'));
                        $('#modal-address').val(button.data('address'));
                        $('#modal-createdAt').val(button.data('createdat'));
                        $('#modal-status').val(button.data('status'));
                    });
                </script>
                <script>
                    window.addEventListener('DOMContentLoaded', function () {
                        const alertBox = document.getElementById("alertBox");
                        if (alertBox) {
                            setTimeout(function () {
                                // Bootstrap 3: use jQuery .alert('close')
                                $(alertBox).alert('close');
                            }, 5000); // Tự biến mất sau 4 giây
                        }
                    });
                </script>
                <script>
                    $(function () {
                        $("#example1").DataTable();
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
                <script>
                    $(function () {
                        $('.sidebar-menu').tree();
                    });
                </script>
            </body>
            <c:if test="${not empty toast}">
                <script>
                    showToast("${toast}", "${toastType}");
                </script>
            </c:if>
            <% String toast=(String) session.getAttribute("toast"); String toastType=(String)
                session.getAttribute("toastType"); if (toast !=null) { // Xoá sau khi hiển thị
                session.removeAttribute("toast"); session.removeAttribute("toastType"); } %>





            </html>