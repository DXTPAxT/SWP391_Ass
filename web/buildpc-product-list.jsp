<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="row p-4">
    <c:forEach var="p" items="${products}">
        <div class="col-md-4 mb-4">
            <div class="card bg-light text-dark h-100">
                <img src="${ctx}/images/products/${p.imgURL}" class="card-img-top" style="height: 180px; object-fit: contain;">
                <div class="card-body d-flex flex-column">
                    <h5 class="card-title">${p.categoryName}</h5>
                    <p><strong>Brand:</strong> ${p.brandName}</p>
                    <p class="text-danger fw-bold">
                        <fmt:formatNumber value="${p.price}" type="currency" currencySymbol="₫" />
                    </p>
                    <a href="${ctx}/CategoriesController?service=add&categoryID=${p.categoryID}" 
                       class="btn btn-danger mt-auto">Chọn</a>
                </div>
            </div>
        </div>
    </c:forEach>

    <c:if test="${empty products}">
        <div class="text-center py-4 text-muted">Không có sản phẩm nào.</div>
    </c:if>
</div>
