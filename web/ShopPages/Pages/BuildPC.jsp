<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Build PC | CyberBeast</title>
        <link href="${ctx}/ShopPages/Pages/css/bootstrap.min.css" rel="stylesheet">
        <link href="${ctx}/ShopPages/Pages/css/font-awesome.min.css" rel="stylesheet">
        <link href="${ctx}/ShopPages/Pages/css/buildpc.css" rel="stylesheet">
        <style>
            .modal-overlay {
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                width: 100vw;
                height: 100vh;
                background: rgba(0, 0, 0, 0.6);
                justify-content: center;
                align-items: center;
                z-index: 10000;
            }
            .modal-content {
                background: #fff;
                padding: 20px;
                border-radius: 12px;
                width: 90%;
                max-width: 960px;
                max-height: 90vh;
                overflow-y: auto;
            }
            .close-btn {
                float: right;
                font-size: 24px;
                cursor: pointer;
            }
        </style>
    </head>
    <body>

        <jsp:include page="components/header.jsp">
            <jsp:param name="activePage" value="buildpc"/>
        </jsp:include>

        <div class="container mt-5">
            <div class="row">
                <div class="list-group col-sm-5">
                    <c:forEach var="comp" items="${components}">
                        <c:if test="${comp.componentID != 1}">
                            <div class="list-group-item">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div>
                                        <h5 class="mb-1">${comp.componentName}</h5>
                                        <c:forEach var="sel" items="${selectedComponents}">
                                            <c:if test="${sel.componentName eq comp.componentName}">
                                                <p class="mb-1">${sel.categoryName} - ${sel.brandName}</p>
                                                <small>
                                                    <fmt:formatNumber value="${sel.price}" type="number" groupingUsed="true"/>₫
                                                </small>
                                                <a href="#"
                                                   class="btn btn-sm btn-outline-danger mt-2 delete-selected"
                                                   data-component-name="${comp.componentName}">Delete</a>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                    <div>
                                        <c:set var="found" value="false"/>
                                        <c:forEach var="sel" items="${selectedComponents}">
                                            <c:if test="${sel.componentName eq comp.componentName}">
                                                <c:set var="found" value="true"/>
                                            </c:if>
                                        </c:forEach>

                                        <a href="${ctx}/BuildPC?componentName=${fn:escapeXml(comp.componentName)}&ajax=true"
                                           class="btn btn-sm btn-primary open-modal"
                                           data-component-name="${comp.componentName}">
                                            <c:choose>
                                                <c:when test="${found}">Change</c:when>
                                                <c:otherwise>Choose</c:otherwise>
                                            </c:choose>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>

            <c:set var="totalPrice" value="0"/>
            <c:forEach var="sel" items="${selectedComponents}">
                <c:if test="${not empty sel.price}">
                    <c:set var="totalPrice" value="${totalPrice + sel.price}"/>
                </c:if>
            </c:forEach>

            <div class="summary-box card p-4 mt-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <strong style="font-size: 20px">Provisional Amount:</strong>
                    <span style="font-size: 20px">
                        <fmt:formatNumber value="${totalPrice}" type="number" groupingUsed="true"/>₫
                    </span>
                </div>
                <div class="d-grid gap-3 mt-3">
                    <button class="btn btn-outline-danger btn-lg" type="button">
                        <i class="fa fa-shopping-cart me-2"></i> Add to cart
                    </button>
                    <button class="btn btn-danger btn-lg" type="button">Buy Now</button>
                </div>
            </div>
        </div>

        <!-- MODAL -->
        <div id="productModal" class="modal-overlay">
            <div class="modal-content">
                <span class="close-btn" id="closeModal">&times;</span>
                <div id="modalBody">Loading products...</div>
            </div>
        </div>

        <script>
    const ctx = '${ctx}';
    const modal = document.getElementById('productModal');
    const modalBody = document.getElementById('modalBody');
    const closeBtn = document.getElementById('closeModal');

    // Mở modal khi click nút Choose / Change
    document.querySelectorAll('.open-modal').forEach(btn => {
        btn.addEventListener('click', function (e) {
            e.preventDefault();
            fetch(this.href)
                .then(res => res.text())
                .then(html => {
                    modalBody.innerHTML = html;
                    modal.style.display = 'flex';
                });
        });
    });

    // Đóng modal
    closeBtn.onclick = () => modal.style.display = 'none';
    window.onclick = (e) => {
        if (e.target === modal)
            modal.style.display = 'none';
    };

    // Xử lý click chung: chọn sản phẩm hoặc xoá sản phẩm đã chọn
    document.addEventListener('click', function (e) {
        // Xử lý chọn sản phẩm
        const selectBtn = e.target.closest('.select-product-btn');
        if (selectBtn) {
            e.preventDefault();

            const categoryId = selectBtn.dataset.categoryId;
            const componentName = selectBtn.dataset.componentName.trim();
            const categoryName = selectBtn.dataset.categoryName.trim();

            const productBox = selectBtn.closest('.productinfo');
            const brand = productBox.querySelector("p")?.innerText.trim() || "";
            const priceText = productBox.querySelector("h2")?.innerText.trim() || "";

            const params = new URLSearchParams();
            params.append("categoryID", categoryId);
            params.append("componentName", componentName);

            fetch(`${ctx}/CategoriesController?service=add`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                    "X-Requested-With": "XMLHttpRequest"
                },
                body: params.toString()
            })
            .then(res => {
                if (!res.ok)
                    throw new Error();
                return res.text();
            })
            .then(() => {
                // Cập nhật UI bên trái mà không reload trang
                const items = document.querySelectorAll(".list-group .list-group-item");
                items.forEach(item => {
                    const h5 = item.querySelector("h5");
                    if (h5 && h5.innerText.trim().toLowerCase() === componentName.toLowerCase()) {
                        item.querySelector("p")?.remove();
                        item.querySelector("small")?.remove();

                        const p = document.createElement("p");
                        p.className = "mb-1";
                        p.innerText = `${categoryName} - ${brand}`;

                        const small = document.createElement("small");
                        small.innerText = priceText;

                        const btnContainer = item.querySelector("div");
                        btnContainer.insertBefore(p, btnContainer.children[1]);
                        btnContainer.insertBefore(small, btnContainer.children[2]);

                        if (!item.querySelector(".btn-outline-danger")) {
                            const del = document.createElement("a");
                            del.href = "#";
                            del.className = "btn btn-sm btn-outline-danger mt-2 delete-selected";
                            del.dataset.componentName = componentName;
                            del.innerText = "Delete";
                            btnContainer.appendChild(del);
                        }

                        const changeBtn = item.querySelector(".open-modal");
                        if (changeBtn)
                            changeBtn.innerText = "Change";
                    }
                });

                // Cập nhật tổng tiền
                let total = 0;
                document.querySelectorAll(".list-group small").forEach(el => {
                    const num = parseInt(el.innerText.replace(/[^\d]/g, ''));
                    if (!isNaN(num))
                        total += num;
                });

                const totalBox = document.querySelector(".summary-box span");
                if (totalBox)
                    totalBox.innerText = total.toLocaleString("vi-VN") + "₫";

                // Đóng modal
                modal.style.display = 'none';
            })
            .catch(() => alert("Không thể chọn sản phẩm."));
        }

        // Xử lý xoá sản phẩm đã chọn
        const deleteBtn = e.target.closest('.delete-selected');
        if (deleteBtn) {
            e.preventDefault();

            const componentName = deleteBtn.dataset.componentName.trim();

            const params = new URLSearchParams();
            params.append("componentName", componentName);

            fetch(`${ctx}/CategoriesController?service=remove`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                    "X-Requested-With": "XMLHttpRequest"
                },
                body: params.toString()
            })
            .then(res => {
                if (res.ok) {
                    // Xóa phần tử trong DOM luôn mà không reload trang
                    const items = document.querySelectorAll(".list-group .list-group-item");
                    items.forEach(item => {
                        const h5 = item.querySelector("h5");
                        if (h5 && h5.innerText.trim().toLowerCase() === componentName.toLowerCase()) {
                            item.querySelector("p")?.remove();
                            item.querySelector("small")?.remove();

                            // Đổi nút Change về Choose
                            const changeBtn = item.querySelector(".open-modal");
                            if (changeBtn) changeBtn.innerText = "Choose";
                        }
                    });

                    // Cập nhật tổng tiền
                    let total = 0;
                    document.querySelectorAll(".list-group small").forEach(el => {
                        const num = parseInt(el.innerText.replace(/[^\d]/g, ''));
                        if (!isNaN(num)) total += num;
                    });

                    const totalBox = document.querySelector(".summary-box span");
                    if (totalBox)
                        totalBox.innerText = total.toLocaleString("vi-VN") + "₫";
                } else {
                    alert("Xoá thất bại");
                }
            })
            .catch(() => alert("Lỗi xoá sản phẩm."));
        }
    });
</script>

   <jsp:include page="components/footer.jsp"/>
        <script src="${ctx}/ShopPages/Pages/js/jquery.js"></script>
        <script src="${ctx}/ShopPages/Pages/js/bootstrap.min.js"></script>
        <script src="${ctx}/ShopPages/Pages/js/jquery.scrollUp.min.js"></script>
        <script src="${ctx}/ShopPages/Pages/js/price-range.js"></script>
        <script src="${ctx}/ShopPages/Pages/js/jquery.prettyPhoto.js"></script>
        <script src="${ctx}/ShopPages/Pages/js/main.js"></script>
    </body>
</html>
