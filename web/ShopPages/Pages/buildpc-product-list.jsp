<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
    .product-grid {
        display: flex;
        flex-wrap: wrap;
        gap: 16px;
    }

    .product-card {
        background: #1e1e1e;
        border-radius: 10px;
        padding: 12px;
        width: 30%;
        box-shadow: 0 0 5px #000;
    }

    .product-card img {
        width: 100%;
        height: 160px;
        object-fit: contain;
        border-radius: 6px;
    }

    .product-card h6, .product-card p {
        margin: 8px 0;
    }

    .product-card a {
        display: inline-block;
        margin-top: 10px;
        padding: 6px 12px;
        background: #3498db;
        color: white;
        text-decoration: none;
        border-radius: 4px;
    }

    .product-card a:hover {
        background: #2980b9;
    }
</style>

<div>
    <h4 style="color:#00c0ff;">Sản phẩm tương ứng</h4>
    <div class="product-grid">
        <c:forEach var="p" items="${products}">
            <div class="product-card">
                <img src="${ctx}/images/products/${p.imgURL}" alt="${p.categoryName}">
                <h6>${p.categoryName}</h6>
                <p>Hãng: ${p.brandName}</p>
                <a href="${ctx}/CategoriesController?service=add&categoryID=${p.categoryID}">Chọn</a>
            </div>
        </c:forEach>
    </div>
</div>
