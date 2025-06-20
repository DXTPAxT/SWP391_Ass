<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="models.Blog_Cate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
    <head>
        <title>Add blogs</title>
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
            <h1 class="mb-4">Add new a blog</h1>
            <h3 style="color: red">${requestScope.error}</h3>
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    <c:out value="${error}" />
                </div>
            </c:if>
            <form action="blogadd" method="get" enctype="multipart/form-data" onsubmit="return validateForm()">
                <input type="hidden" name="action" value="create" />
                <input type="hidden" name="csrf_token" value="${sessionScope.csrfToken}" />
                <div class="form-group">
                    <label>Title</label>
                    <input type="text" class="form-control" id="title" name="title" required maxlength="200" />
                    
                </div>
                <div class="form-group">
                    <label>Author</label>
                    <input type="text" class="form-control" id="author" name="author" required maxlength="100" />
                    
                </div>
                
                <div class="form-group">
                    <label>Content</label>
                    <textarea class="form-control" id="content" name="content" rows="10" required></textarea>
                    
                </div>
                <div class="form-group">
                    <label>Thumbnail</label>
                    <input type="file" class="form-control-file" id="thumbnail" name="thumbnail" accept="image/*" />
                    <small class="form-text text-muted">Chọn file ảnh (jpg, png, gif).</small>
                    
                </div>
                <div class="form-group">
                    <label>Category</label>
                    <select name="bc_id" id="bc_id" class="form-select" aria-label="Select category" required>
                        <option value="" disabled selected>Choose Category</option>
                        <c:forEach var="cat" items="${blog_categories}">
                            <option value="${cat.bc_id}">${cat.bc_name}</option>
                        </c:forEach>
                    </select>
                    
                </div>

                <div class="form-group">
                    <label>Brief</label>
                    <input type="text" class="form-control" id="brief" name="brief" required maxlength="500" />
                   
                </div>
                <div class="form-group">
                    <label>Author_Id</label>
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