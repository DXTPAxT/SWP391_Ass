<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <!-- DataTables -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages//plugins/datatables/dataTables.bootstrap.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages//dist/css/AdminLTE.min.css">
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

            <div class="content-wrapper">
                <section class="content-header">
                    <h1>Build PC List</h1>
                </section>
                <section class="content">
                    <div class="box">
                        <form id="deleteForm" action="${ctx}/BuildPC_ListCate" method="post">
                            <input type="hidden" name="service" value="delete">
                            <input type="hidden" name="buildPCID" id="deleteBuildPCID">
                        </form>

                        <!-- ✅ Box Header in English -->
                        <div class="box-header with-border">
                            <h3 class="box-title">Build PC List</h3>
                            <div class="box-tools pull-right">
                                <a href="${ctx}/AdminLTE/AdminPages/pages/forms/BuildPCAdmin.html" class="btn btn-success btn-sm">
                                    <i class="fa fa-plus"></i> Create New Build PC
                                </a>
                            </div>
                        </div>

                        <div class="box-body">
                            <table id="example2" class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>MainBoard</th>
                                        <th>CPU</th>
                                        <th>GPU</th>
                                        <th>RAM</th>
                                        <th>SSD</th>
                                        <th>CASE</th>
                                        <th>Price</th>
                                        <th>Name User</th>
                                        <th>Role</th>
                                        <th>Status</th>
                                        <th>Update</th>
                                       
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="b" items="${buildPCList}">
                                        <tr>
                                            <td>${b.buildPCID}</td>
                                            <td><c:out value="${b.mainBoard}" default="N/A"/></td>
                                            <td><c:out value="${b.cpu}" default="N/A"/></td>
                                            <td><c:out value="${b.gpu}" default="N/A"/></td>
                                            <td><c:out value="${b.ram}" default="N/A"/></td>
                                            <td><c:out value="${b.ssd}" default="N/A"/></td>
                                            <td><c:out value="${b.pcCase}" default="N/A"/></td>

                                            <td><fmt:formatNumber value="${b.price}" type="number" groupingUsed="true"/> VNĐ</td>

                                            <td>${b.fullName}</td>
                                            <td>${b.role}</td>
                                            <td>
                                                <c:choose>

                                                    <%-- Trường hợp Admin --%>
                                                    <c:when test="${b.role eq 'Admin'}">
                                                        <c:choose>
                                                            <c:when test="${b.status == 1}">
                                                                <span class="label label-success">Đang bán</span>
                                                            </c:when>
                                                            <c:when test="${b.status == 2}">
                                                                <span class="label label-danger">Ngừng bán</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="label label-default">Không xác định</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:when>

                                                    <%-- Các Role khác (ví dụ Customer) --%>
                                                    <c:otherwise>
                                                        <c:choose>
                                                            <c:when test="${b.status == 0}">
                                                                <span class="label label-warning">Chờ xác nhận</span>
                                                            </c:when>
                                                            <c:when test="${b.status == 1}">
                                                                <span class="label label-primary">Đã xác nhận</span>
                                                            </c:when>
                                                            <c:when test="${b.status == 2}">
                                                                <span class="label label-info">Đang Build PC</span>
                                                            </c:when>
                                                            <c:when test="${b.status == 3}">
                                                                <span class="label label-success">Đã Build xong</span>
                                                            </c:when>
                                                            <c:when test="${b.status == 4}">
                                                                <span class="label label-default">Chờ Ship</span>
                                                            </c:when>
                                                            <c:when test="${b.status == 5}">
                                                                <span class="label label-info">Đang Ship</span>
                                                            </c:when>
                                                            <c:when test="${b.status == 6}">
                                                                <span class="label label-success">Đã Ship</span>
                                                            </c:when>
                                                            <c:when test="${b.status == 7}">
                                                                <span class="label label-success">Hoàn thành</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="label label-danger">Không xác định</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:otherwise>

                                                </c:choose>
                                            </td>


                                            <td>
                                                <button type="button" class="btn btn-warning btn-sm"
                                                        onclick="goToUpdate(${b.buildPCID}, '${b.role}', ${b.userID})">
                                                    Update
                                                </button>


                                            </td>

                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </section>

            </div>

            <jsp:include page="../../components/footer.jsp"/>
            <jsp:include page="../../components/control-sidebar.jsp"/>
            <div class="control-sidebar-bg"></div>
        </div>
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
        <!-- Scripts -->
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
        <script>
            function confirmDelete(buildPCID) {
                if (confirm("Are you sure you want to delete Build PC ID: " + buildPCID + "?")) {
                    document.getElementById("deleteBuildPCID").value = buildPCID;
                    document.getElementById("deleteForm").submit();
                }
            }

            function goToUpdate(buildPCID, role, userID) {
                localStorage.setItem("userRole", role);
                localStorage.setItem("userID", userID);
                window.location.href = '${ctx}/AdminLTE/AdminPages/pages/forms/BuildPCAdmin.html?buildPCID=' + buildPCID;
            }


        </script>
    </body>
</html>
