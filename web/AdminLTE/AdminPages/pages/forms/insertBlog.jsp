<%-- 
    Document   : addNewUser
    Created on : Jun 3, 2025, 10:14:57 AM
    Author     : PC ASUS
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>AdminLTE 2 | Advanced form elements</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <!-- daterange picker -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/daterangepicker/daterangepicker.css">
        <!-- bootstrap datepicker -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/datepicker/datepicker3.css">
        <!-- iCheck for checkboxes and radio inputs -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/iCheck/all.css">
        <!-- Bootstrap Color Picker -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/colorpicker/bootstrap-colorpicker.min.css">
        <!-- Bootstrap time Picker -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/timepicker/bootstrap-timepicker.min.css">
        <!-- Select2 -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/plugins/select2/select2.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/dist/css/AdminLTE.min.css">    
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/dist/css/custom.css">

        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="${ctx}/AdminLTE/AdminPages/dist/css/skins/_all-skins.min.css">

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
                    <h1>INSERT BLOG</h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">Blog</a></li>
                        <li class="active">Insert Blog</li>
                    </ol>
                </section>
                <!-- Main content -->
                <div class="box box-success">
                   <div class="box-body">
    <c:if test="${not empty error}">
        <div class="alert alert-danger" style="font-weight:bold;">${error}</div>
    </c:if>

   <form method="post" action="${ctx}/blogadd" enctype="multipart/form-data">

        <input type="hidden" name="service" value="insert">
        <input type="hidden" name="submit" value="submit">

        <!-- Title -->
        <div class="form-group">
            <label for="title">Blog Title</label>
            <input type="text" id="title" name="title" class="form-control" placeholder="Enter blog title" required value="${title}">
        </div>

        <!-- Author -->
        <div class="form-group">
            <label for="author">Author</label>
            <input type="text" id="author" name="author" class="form-control" placeholder="Enter author name" required value="${author}">
        </div>

        <!-- Content -->
        <div class="form-group">
            <label for="content">Content</label>
            <textarea id="content" name="content" rows="8" class="form-control" placeholder="Enter blog content" required>${content}</textarea>
        </div>

        <!-- Brief -->
        <div class="form-group">
            <label for="brief">Brief</label>
            <input type="text" id="brief" name="brief" class="form-control" placeholder="Enter short description" required value="${brief}">
        </div>

        <!-- Thumbnail -->
        <div class="form-group">
            <label for="thumbnail">Thumbnail</label>
            <input type="file" id="thumbnail" name="thumbnail" class="form-control" accept="image/*">
        </div>

        <!-- Category -->
        <div class="form-group">
            <label for="bc_id">Category</label>
            <select id="bc_id" name="bc_id" class="form-control" required>
                <option value="">Choose Category</option>
                <c:forEach var="cat" items="${blog_categories}">
                    <option value="${cat.bc_id}" ${cat.bc_id == bc_id ? 'selected' : ''}>${cat.bc_name}</option>
                </c:forEach>
            </select>
        </div>

        <!-- Add_id (Author ID) -->
        <div class="form-group">
            <label for="add_id">Author ID</label>
            <input type="number" id="add_id" name="add_id" class="form-control" value="${sessionScope.user.id}">
        </div>

        <!-- Submit -->
        <button type="submit" class="btn btn-primary">Add Blog</button>
    </form>
</div>

                </div>
                <!-- /.box -->
            </div>
            <jsp:include page="../../components/footer.jsp" />
            <jsp:include page="../../components/control-sidebar.jsp" />
        </div>
        <!-- ./wrapper -->
        <!-- jQuery 2.2.3 -->
        <script src="${ctx}/AdminLTE/AdminPages/plugins/jQuery/jquery-2.2.3.min.js"></script>
        <!-- Bootstrap 3.3.6 -->
        <script src="${ctx}/AdminLTE/AdminPages/bootstrap/js/bootstrap.min.js"></script>
        <!-- Select2 -->
        <script src="${ctx}/AdminLTE/AdminPages/plugins/select2/select2.full.min.js"></script>
        <!-- InputMask -->
        <script src="${ctx}/AdminLTE/AdminPages/plugins/input-mask/jquery.inputmask.js"></script>
        <script src="${ctx}/AdminLTE/AdminPages/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
        <script src="${ctx}/AdminLTE/AdminPages/plugins/input-mask/jquery.inputmask.extensions.js"></script>
        <!-- date-range-picker -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
        <script src="${ctx}/AdminLTE/AdminPages/plugins/daterangepicker/daterangepicker.js"></script>
        <!-- bootstrap datepicker -->
        <script src="${ctx}/AdminLTE/AdminPages/plugins/datepicker/bootstrap-datepicker.js"></script>
        <!-- bootstrap color picker -->
        <script src="${ctx}/AdminLTE/AdminPages/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
        <!-- bootstrap time picker -->
        <script src="${ctx}/AdminLTE/AdminPages/plugins/timepicker/bootstrap-timepicker.min.js"></script>
        <!-- SlimScroll 1.3.0 -->
        <script src="${ctx}/AdminLTE/AdminPages/plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <!-- iCheck 1.0.1 -->
        <script src="${ctx}/AdminLTE/AdminPages/plugins/iCheck/icheck.min.js"></script>
        <!-- FastClick -->
        <script src="${ctx}/AdminLTE/AdminPages/plugins/fastclick/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="${ctx}/AdminLTE/AdminPages/dist/js/app.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="${ctx}/AdminLTE/AdminPages/dist/js/demo.js"></script>
        <!-- Page script -->
        <script>
            function validateForm() {
                let isValid = true;
                const title = document.getElementById('title').value.trim();
                const author = document.getElementById('author').value.trim();
                const brief = document.getElementById('brief').value.trim();
                const content = document.getElementById('content').value.trim();
                const bc_id = document.getElementById('bc_id').value;
                const thumbnail = document.getElementById('thumbnail').value;

                document.getElementById('title-error').textContent = '';
                document.getElementById('author-error').textContent = '';
                document.getElementById('brief-error').textContent = '';
                document.getElementById('content-error').textContent = '';
                document.getElementById('bc_id-error').textContent = '';
                document.getElementById('thumbnail-error').textContent = '';

                if (title.length < 5) {
                    document.getElementById('title-error').textContent = 'Tiêu ?? ph?i có ít nh?t 5 ký t?.';
                    isValid = false;
                }
                if (author.length < 2) {
                    document.getElementById('author-error').textContent = 'Tác gi? ph?i có ít nh?t 2 ký t?.';
                    isValid = false;
                }
                if (brief.length < 10) {
                    document.getElementById('brief-error').textContent = 'Tóm t?t ph?i có ít nh?t 10 ký t?.';
                    isValid = false;
                }
                if (content.length < 20) {
                    document.getElementById('content-error').textContent = 'N?i dung ph?i có ít nh?t 20 ký t?.';
                    isValid = false;
                }
                if (!bc_id) {
                    document.getElementById('bc_id-error').textContent = 'Vui lòng ch?n danh m?c.';
                    isValid = false;
                }
                if (thumbnail && !thumbnail.match(/\.(jpg|jpeg|png|gif)$/i)) {
                    document.getElementById('thumbnail-error').textContent = 'File ?nh ph?i có ??nh d?ng jpg, jpeg, png ho?c gif.';
                    isValid = false;
                }

                return isValid;
            }
        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const inputs = document.querySelectorAll(".is-invalid");

                inputs.forEach(input => {
                    input.addEventListener("input", function () {
                        
                        input.classList.remove("is-invalid");

                        
                        const errorMsg = input.parentElement.querySelector("p");
                        if (errorMsg) {
                            errorMsg.remove();
                        }
                    });
                });
            });
        </script>
    </body>
</html>

