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
                        <div class="d-flex align-items-center">
                            <img src="${ctx}/images/products/${comp.componentID}.png" alt="${comp.componentName}" class="component-image" />
                            <div>
                                <div class="component-title">${comp.componentName}
                                    <c:forEach var="sel" items="${selectedComponents}">
                                        <c:if test="${sel.componentID eq comp.componentID}">
                                            <div class="selected-item">
                                                <div class="component-meta">${sel.categoryName} - ${sel.brandName}</div>
                                                <a href="#" class="btn btn-sm btn-outline-danger btn-remove mt-2" data-delete-url="${ctx}/BuildPC?service=remove&componentID=${comp.componentID}">Delete</a>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>

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
                            <button type="button" class="btn btn-sm btn-primary mt-2 open-component-modal" data-component-id="${comp.componentID}" data-component-name="${comp.componentName}">
                                <c:choose>
                                    <c:when test="${found}">Changes</c:when>
                                    <c:otherwise>Choose</c:otherwise>
                                </c:choose>
                            </button>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>

        <div id="customModal" class="modal-overlay">
            <div class="modal-content">
                <span class="close-btn" id="closeModal">&times;</span>
                <h3 id="modalTitle">Loading...</h3>
                <div id="modalBody">Đang tải...</div>
            </div>
        </div>

        <div id="deleteModal" class="modal-overlay" style="display: none;">
            <div class="modal-content text-center" style="background: #fff; padding: 30px; border-radius: 10px; width: 400px; max-width: 90%;">
                <h4>Xác nhận xoá</h4>
                <p>Bạn có chắc muốn xoá linh kiện này không?</p>
                <div class="mt-3 d-flex justify-content-center gap-3">
                    <button id="cancelDelete" class="btn btn-secondary px-4">Hủy</button>
                    <button id="confirmDelete" class="btn btn-danger px-4">Xoá</button>
                </div>
            </div>
        </div>

        <!-- Toast thông báo -->
        <div id="toast" style="position: fixed; bottom: 20px; right: 20px; background: #28a745; color: white; padding: 14px 20px; border-radius: 8px; display: none; font-weight: bold; z-index: 99999;">
            Delete successful
        </div>

        <c:set var="totalPrice" value="0" />
        <c:forEach var="sel" items="${selectedComponents}">
            <c:if test="${not empty sel.price}">
                <c:set var="totalPrice" value="${totalPrice + sel.price}" />
            </c:if>
        </c:forEach>

        <div class="summary-box card p-4">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <strong class="summary-label" style="font-size: 30px">Provisional Amount:</strong>
                <span class="summary-price" style="font-size: 30px">
                    <fmt:formatNumber value="${totalPrice}" type="number" groupingUsed="true" />₫
                </span>
            </div>
            <div class="d-grid gap-3 mt-4">
                <button class="btn btn-lg btn-outline-danger btn-cart" type="button">
                    <i class="fa fa-shopping-cart me-2"></i> Add to cart
                </button>
                <button class="btn btn-lg btn-danger btn-buy" type="button">Buy Now</button>
            </div>
        </div>

        <script>
            const ctx = '${ctx}';
            const modal = document.getElementById('customModal');
            const modalTitle = document.getElementById('modalTitle');
            const modalBody = document.getElementById('modalBody');
            const deleteModal = document.getElementById('deleteModal');
            const cancelDelete = document.getElementById('cancelDelete');
            const confirmDelete = document.getElementById('confirmDelete');
            const toast = document.getElementById('toast');

            let deleteUrl = null;

            document.querySelectorAll('.open-component-modal').forEach(button => {
                button.addEventListener('click', () => {
                    const componentId = button.dataset.componentId;
                    const componentName = button.dataset.componentName;
                    modal.style.display = 'flex';
                    modalTitle.textContent = componentName;
                    modalBody.innerHTML = "<p>Loading...</p>";
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

            document.querySelectorAll('.btn-remove').forEach(btn => {
                btn.addEventListener('click', function (e) {
                    e.preventDefault();
                    deleteUrl = this.getAttribute('data-delete-url');
                    deleteModal.style.display = 'flex';
                });
            });

            cancelDelete.onclick = () => {
                deleteModal.style.display = 'none';
                deleteUrl = null;
            };

            confirmDelete.onclick = () => {
                if (deleteUrl) {
                    fetch(deleteUrl, {
                        headers: {'X-Requested-With': 'XMLHttpRequest'}
                    }).then(response => {
                        if (response.ok) {
                            deleteModal.style.display = 'none';
                            toast.style.display = 'block';
                            setTimeout(() => {
                                toast.style.display = 'none';
                                window.location.reload();
                            });
                        } else {
                            alert('Không thể xoá. Đã có lỗi xảy ra.');
                        }
                    }).catch(() => {
                        alert('Không thể xoá. Lỗi mạng.');
                    });
                }
            };

            window.onclick = function (e) {
                if (e.target === modal || e.target === deleteModal) {
                    modal.style.display = "none";
                    deleteModal.style.display = "none";
                }
            };
        </script>
    </body>
</html>
