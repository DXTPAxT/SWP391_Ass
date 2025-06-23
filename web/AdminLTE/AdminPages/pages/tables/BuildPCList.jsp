<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

                        <div class="box-body">
                            <table class="table table-bordered">
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
                                        <th>Status</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="b" items="${buildPCList}">
                                        <tr>
                                            <td>${b.buildPCID}</td>
                                            <td>${b.mainBoard != null ? b.mainBoard : 'N/A'}</td>
                                            <td>${b.cpu != null ? b.cpu : 'N/A'}</td>
                                            <td>${b.gpu != null ? b.gpu : 'N/A'}</td>
                                            <td>${b.ram != null ? b.ram : 'N/A'}</td>
                                            <td>${b.ssd != null ? b.ssd : 'N/A'}</td>
                                            <td>${b.pcCase != null ? b.pcCase : 'N/A'}</td>
                                            <td><fmt:formatNumber value="${b.price}" type="currency"/></td>
                                            <td>
                                                <span class="label label-${b.status == 1 ? 'success' : 'danger'}">
                                                    ${b.status == 1 ? 'Active' : 'Inactive'}
                                                </span>
                                            </td>
                                            <td>
                                                <button type="button" class="btn btn-warning btn-sm"
                                                        onclick="goToUpdate(${b.buildPCID})">
                                                    Update
                                                </button>
                                                <button type="button" class="btn btn-danger btn-sm"
                                                        onclick="confirmDelete(${b.buildPCID})">
                                                    Delete
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="box-footer text-right">
                          <a href="${ctx}/AdminLTE/AdminPages/pages/forms/BuildPCAdmin.html" class="btn btn-success">


                                Create New Build PC
                            </a>
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
                                                            function confirmDelete(buildPCID) {
                                                                if (confirm("Are you sure you want to delete Build PC ID: " + buildPCID + "?")) {
                                                                    document.getElementById("deleteBuildPCID").value = buildPCID;
                                                                    document.getElementById("deleteForm").submit();
                                                                }
                                                            }

                                                            function goToUpdate(buildPCID) {
                                                             window.location.href = '${ctx}/AdminLTE/AdminPages/pages/forms/BuildPCAdmin.html?buildPCID=' + buildPCID;

                                                            }
        </script>
    </body>
</html>
