<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<form id="filterForm" method="get" action="${ctx}/BuildPC" onsubmit="return false;"
      style="display: flex; flex-direction: column; gap: 10px; margin-bottom: 20px;">
    <input type="hidden" name="service" value="filter" />
    <input type="hidden" name="ajax" value="true" />
    <input type="hidden" name="componentID" value="${componentID}" />

    <input type="text" name="keyword" value="${param.keyword}" placeholder="Tìm theo tên..." class="form-control" />

    <label for="brandSelect"><strong>Hãng:</strong></label>
    <select id="brandSelect" name="brand" class="form-control">
        <option value="">-- Tất cả hãng --</option>
        <c:forEach var="b" items="${brands}">
            <option value="${b.brandName}" <c:if test="${param.brand == b.brandName}">selected</c:if>>
                ${b.brandName}
            </option>
        </c:forEach>
    </select>

    <div style="display: flex; gap: 10px;">
        <input type="text" name="minPrice" value="${param.minPrice}" placeholder="Min price" class="form-control" style="width: 120px;" />
        <input type="text" name="maxPrice" value="${param.maxPrice}" placeholder="Max price" class="form-control" style="width: 120px;" />
    </div>

    <button type="submit" class="btn btn-sm btn-primary">Lọc</button>
</form>

<!-- DANH SÁCH SẢN PHẨM -->
<div class="container-fluid">
    <div class="row">
        <c:forEach var="p" items="${products}" varStatus="loop">
            <div class="col-md-6 mb-3">
                <div class="product-item border p-2 d-flex gap-3 align-items-center">
                    <img src="${ctx}/ShopPages/Pages/images/anhproduct/${p.imgURL}" alt="${p.categoryName}" style="width: 80px; height: 80px; object-fit: cover; border: 1px solid #ccc;" />
                    <div>
                        <div>
                            <div><strong>${p.categoryName}</strong> - ${p.brandName}</div>
                            <div>Mô tả: <span style="font-size: 90%;">${p.description}</span></div>
                            <div>Giá: <fmt:formatNumber value="${p.price}" type="number" groupingUsed="true"/>₫</div>
                            <div style="margin-top: 5px;">
                                <a href="${ctx}/CategoriesController?service=detail&categoryID=${p.categoryID}" class="btn btn-info btn-xs" target="_blank">
                                    View Detail
                                </a>

                            </div>
                        </div>

                        <button class="btn btn-sm btn-success mt-2"
                                onclick="selectProduct('${p.componentID}', '${fn:escapeXml(p.categoryName)}', '${fn:escapeXml(p.brandName)}', ${p.price})">
                            Chọn
                        </button>
                    </div>
                </div>
            </div>
        </c:forEach>


    </div>



</div>


        <c:if test="${totalPages > 1}">
            <nav>
                <ul class="pagination">
                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <li class="${i == currentPage ? 'active' : ''}">
                            <a href="javascript:void(0);" class="page-link" data-page="${i}">${i}</a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
        </c:if>