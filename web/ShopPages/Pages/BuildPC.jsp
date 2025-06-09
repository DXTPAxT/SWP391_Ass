<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Build PC | CyberBeast</title>
    
    <link href="${ctx}/ShopPages/Pages/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/ShopPages/Pages/css/font-awesome.min.css" rel="stylesheet">
    <link href="${ctx}/ShopPages/Pages/css/buildpc.css" rel="stylesheet">
    <link href="${ctx}/ShopPages/Pages/css/main.css" rel="stylesheet">
    <link href="${ctx}/ShopPages/Pages/css/responsive.css" rel="stylesheet">

    <link rel="shortcut icon" href="${ctx}/ShopPages/Pages/images/ico/favicon.ico">
</head>
<body>

<jsp:include page="components/header.jsp">
    <jsp:param name="activePage" value="buildpc"/>
</jsp:include>

<div class="list-group col-sm-5">
    <c:forEach var="comp" items="${components}">
        <c:if test="${comp.componentID != 1}">
            <div class="list-group-item">

                <!-- Bên trái: Ảnh + Tên + nút Xoá -->
                <div class="d-flex align-items-center">
                    <img src="${ctx}/images/products/${comp.componentID}.png"
                         alt="${comp.componentName}"
                         class="component-image" />

                    <div>
                        <div class="component-title">
                            ${comp.componentName}
                            <c:forEach var="sel" items="${selectedComponents}">
                                <c:if test="${sel.componentID eq comp.componentID}">
                                    <a href="${ctx}/BuildPC?service=remove&componentID=${comp.componentID}"
                                       class="btn btn-sm btn-outline-danger btn-remove">
                                        Xoá
                                    </a>
                                </c:if>
                            </c:forEach>
                        </div>

                        <c:forEach var="sel" items="${selectedComponents}">
                            <c:if test="${sel.componentID eq comp.componentID}">
                                <div class="component-meta">
                                    ${sel.categoryName} - ${sel.brandName}
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>

                <!-- Bên phải: Giá + Nút -->
                <div class="text-end">
                    <c:set var="found" value="false" />
                    <c:forEach var="sel" items="${selectedComponents}">
                        <c:if test="${sel.componentID eq comp.componentID}">
                            <c:set var="found" value="true" />
                            <div class="component-price">
                                <fmt:formatNumber value="${sel.price}" type="number" groupingUsed="true"/>₫
                            </div>
                        </c:if>
                    </c:forEach>

                    <button type="button"
                            class="btn btn-sm btn-primary mt-2 open-component-modal"
                            data-component-id="${comp.componentID}"
                            data-component-name="${comp.componentName}">
                        <c:choose>
                            <c:when test="${found}">Thay đổi</c:when>
                            <c:otherwise>Chọn</c:otherwise>
                        </c:choose>
                    </button>
                </div>

            </div>
        </c:if>
    </c:forEach>
</div>

<!-- Modal -->
<div id="customModal" class="modal-overlay">
    <div class="modal-content">
        <span class="close-btn" id="closeModal">&times;</span>
        <h3 id="modalTitle">Loading...</h3>
        <div id="modalBody">Đang tải...</div>
    </div>
</div>

<script>
    const ctx = '${ctx}';
    const modal = document.getElementById('customModal');
    const modalTitle = document.getElementById('modalTitle');
    const modalBody = document.getElementById('modalBody');

    document.querySelectorAll('.open-component-modal').forEach(button => {
        button.addEventListener('click', () => {
            const componentId = button.dataset.componentId;
            const componentName = button.dataset.componentName;

            modal.style.display = 'flex';
            modalTitle.textContent = componentName;
            modalBody.innerHTML = "<p>Đang tải...</p>";

            fetch(ctx + "/BuildPC?service=filter&componentID=" + encodeURIComponent(componentId) + "&ajax=true")
                .then(res => res.text())
                .then(html => {
                    modalBody.innerHTML = html;
                    modalBody.querySelectorAll('.select-product-btn').forEach(btn => {
                        btn.addEventListener('click', function () {
                            const categoryId = btn.getAttribute('data-category-id');
                            fetch(ctx + '/BuildPC?service=add&categoryID=' + categoryId)
                                .then(() => {
                                    modal.style.display = "none";
                                    window.location.href = ctx + '/BuildPC';
                                })
                                .catch(() => {
                                    alert('Lỗi khi thêm sản phẩm');
                                });
                        });
                    });
                })
                .catch(() => {
                    modalBody.innerHTML = '<p style="color:red">Lỗi khi tải dữ liệu.</p>';
                });
        });
    });

    window.onclick = function (e) {
        if (e.target === modal) {
            modal.style.display = "none";
        }
    };
</script>
</body>
</html>
