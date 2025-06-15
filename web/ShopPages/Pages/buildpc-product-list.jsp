<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="features_items">
    <h2 class="title text-center">Danh sách sản phẩm</h2>

    <form method="get" action="${ctx}/BuildPC" style="margin-bottom: 20px;">
        <input type="hidden" name="service" value="filter" />
        <input type="hidden" name="componentID" value="${componentID}" />

        <label>Hãng:</label>
        <select name="brand">
            <option value="">Tất cả</option>
            <c:forEach var="brand" items="${brandList}">
                <option value="${brand}" <c:if test="${param.brand == brand}">selected</c:if>>
                    ${brand}
                </option>
            </c:forEach>
        </select>

        <label>Từ:</label>
        <input type="number" name="minPrice" value="${param.minPrice}" style="width: 80px;"/>
        <label>Đến:</label>
        <input type="number" name="maxPrice" value="${param.maxPrice}" style="width: 80px;"/>

        <input type="text" name="keyword" placeholder="Từ khóa..." value="${param.keyword}" />
        <button type="submit" class="btn btn-warning">Lọc</button>
    </form>

    <div class="row">
        <c:forEach var="p" items="${products}">
            <div class="col-sm-3">
                <div class="product-image-wrapper">
                    <div class="single-products">
                        <div class="productinfo text-center">
                            <img src="${ctx}/images/products/${p.imgURL}" style="height:180px" alt="" />
                            <p>${p.brandName}</p>
                            <h2>
                                <fmt:formatNumber value="${p.price}" type="number" groupingUsed="true"/> VND
                            </h2>
                            <p>${p.categoryName}</p>
                            <button class="btn btn-default select-product-btn"
                                    data-category-id="${p.categoryID}">
                                <i class="fa fa-plus"></i> Chọn
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <c:if test="${empty products}">
        <p>Không có sản phẩm nào phù hợp.</p>
    </c:if>
</div>

<c:if test="${totalPages > 1}">
    <div class="pagination-area text-center" style="margin-top: 40px; clear: both;">
        <ul class="pagination" style="display: inline-block;">
            <c:forEach begin="1" end="${totalPages}" var="i">
                <c:url var="pageURL" value="BuildPC">
                    <c:param name="service" value="filter" />
                    <c:param name="componentID" value="${componentID}" />
                    <c:param name="page" value="${i}" />
                    <c:if test="${not empty param.brand}">
                        <c:param name="brand" value="${param.brand}" />
                    </c:if>
                    <c:if test="${not empty param.minPrice}">
                        <c:param name="minPrice" value="${param.minPrice}" />
                    </c:if>
                    <c:if test="${not empty param.maxPrice}">
                        <c:param name="maxPrice" value="${param.maxPrice}" />
                    </c:if>
                    <c:if test="${not empty param.keyword}">
                        <c:param name="keyword" value="${param.keyword}" />
                    </c:if>
                </c:url>
                <li class="${i == currentPage ? 'active' : ''}"><a href="${pageURL}">${i}</a></li>
            </c:forEach>
        </ul>
    </div>
</c:if>

<script>
    document.querySelectorAll('.select-product-btn').forEach(btn => {
        btn.addEventListener('click', e => {
            e.preventDefault();
            const categoryId = btn.dataset.categoryId;
            fetch(`${ctx}/BuildPC?service=add&categoryID=${categoryId}`)
                .then(() => window.location.href = `${ctx}/BuildPC`)
                .catch(() => alert("Lỗi khi chọn sản phẩm."));
        });
    });
</script>
