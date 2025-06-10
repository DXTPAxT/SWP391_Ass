<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="models.Blog_Cate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Update blogs</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            .error-message {
                color: red;
                font-size: 0.9em;
            }
            .form-container {
                max-width: 800px;
                margin: auto;
                padding: 20px;
            }
        </style>
    </head>
    <body>
        <div class="container form-container">
            <h1 class="mb-4">Update a blog</h1>
            
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    <c:out value="${error}" />
                </div>
            </c:if>
            <form action="blogadd" method="get" enctype="multipart/form-data" onsubmit="return validateForm()">
                <input type="hidden" name="action" value="create" />
                <input type="hidden" name="csrf_token" value="${sessionScope.csrfToken}" />
                <div class="form-group">
                    <label for="title">Title:</label>
                    <input type="text" class="form-control" id="title" name="title" required maxlength="200" />
                    <small class="error-message" id="title-error"></small>
                </div>
                <div class="form-group">
                    <label for="author">Author:</label>
                    <input type="text" class="form-control" id="author" name="author" required maxlength="100" />
                    <small class="error-message" id="author-error"></small>
                </div>
                <div>
                    <label for="content" >Content:</label>
                </div>

                <div class="form-group">
                    <label for="content">Content:</label>
                    <textarea class="form-control" id="content" name="content" rows="10" required></textarea>
                    <small class="error-message" id="content-error"></small>
                </div>
                <div class="form-group">
                    <label for="thumbnail">Thumbnail:</label>
                    <input type="file" class="form-control-file" id="thumbnail" name="thumbnail" accept="image/*" />
                    <small class="form-text text-muted">Chọn file ảnh (jpg, png, gif).</small>
                    <small class="error-message" id="thumbnail-error"></small>
                </div>
                <div class="form-group">
                    <label for="bc_id">Category:</label>
                    <select class="form-control" id="bc_id" name="bc_id" required>
                        <option value="">Choose Category</option>
                        <c:forEach var="cat" items="${blog_categories}">
                            <li>
                                <a href="blogc?Bc_id=${cat.bc_id}" style="color: black;">${cat.bc_name}</a>
                            </li>
                        </c:forEach>
                    </select>
                    <small class="error-message" id="bc_id-error"></small>
                </div>
                <div class="form-group">
                    <label for="brief">Brief:</label>
                    <input type="text" class="form-control" id="brief" name="brief" required maxlength="500" />
                    <small class="error-message" id="brief-error"></small>
                </div>
                <div class="form-group">
                    <label for="add_id">Author_Id:</label>
                    <input type="number" class="form-control" id="add_id" name="add_id" value="${sessionScope.user.id}" required readonly />
                    <small class="form-text text-muted">ID của admin đăng nhập.</small>
                </div>
                <input type="submit" class="btn btn-primary" value="SAVE"/>
            </form>
        </div>

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
                    document.getElementById('title-error').textContent = 'Tiêu đề phải có ít nhất 5 ký tự.';
                    isValid = false;
                }
                if (author.length < 2) {
                    document.getElementById('author-error').textContent = 'Tác giả phải có ít nhất 2 ký tự.';
                    isValid = false;
                }
                if (brief.length < 10) {
                    document.getElementById('brief-error').textContent = 'Tóm tắt phải có ít nhất 10 ký tự.';
                    isValid = false;
                }
                if (content.length < 20) {
                    document.getElementById('content-error').textContent = 'Nội dung phải có ít nhất 20 ký tự.';
                    isValid = false;
                }
                if (!bc_id) {
                    document.getElementById('bc_id-error').textContent = 'Vui lòng chọn danh mục.';
                    isValid = false;
                }
                if (thumbnail && !thumbnail.match(/\.(jpg|jpeg|png|gif)$/i)) {
                    document.getElementById('thumbnail-error').textContent = 'File ảnh phải có định dạng jpg, jpeg, png hoặc gif.';
                    isValid = false;
                }

                return isValid;
            }
        </script>
    </body>
</html>