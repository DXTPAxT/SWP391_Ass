<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!-- Filter bar (ví dụ chỉ xem thương hiệu) -->
<div class="p-3 border-bottom">
    <strong>Sản phẩm: </strong> ${param.component}
    <!-- Có thể thêm filter Brand, Price, Sort here -->
</div>

<!-- Grid sản phẩm -->
<div class="row g-3 p-3">
    <c:forEach var="prod" items="${data}">
        <div class="col-6 col-md-4 col-lg-3">
            <div class="card h-100 bg-secondary text-white border-0">
                <img src="${ctx}/images/categories/${prod.categoryID}.png" class="card-img-top" alt="${prod.categoryName}">
                <div class="card-body">
                    <h6 class="card-title">${prod.categoryName}</h6>
                    <p class="card-text text-danger">${prod.price}₫</p>
                    <a href="${ctx}/CategoriesController?service=detail&categoryID=${prod.categoryID}" 
                       class="btn btn-sm btn-light">Xem thêm</a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<!-- Pagination -->
<nav class="px-3 pb-3">
    <ul class="pagination justify-content-center mb-0">
        <c:forEach begin="1" end="${totalPages}" var="p">
            <li class="page-item ${p == currentPage ? 'active' : ''}">
                <a class="page-link" href="?service=filter&component=${param.component}&page=${p}&ajax=true">${p}</a>
            </li>
        </c:forEach>
    </ul>
</nav>
