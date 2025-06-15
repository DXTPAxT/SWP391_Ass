<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div id="product-list-container">
    <form id="filterForm" method="post" action="${ctx}/BuildPC" onsubmit="return filterProducts();">
        <input type="hidden" name="componentName" value="${componentName}" />
        <input type="hidden" name="ajax" value="true" />
        <input type="hidden" name="page" value="${currentPage}" />

        <div class="row g-2 align-items-end mb-3">
            <div class="col-md-2">
                <label>Hãng:</label>
                <select name="brand" class="form-control">
                    <option value="">Tất cả</option>
                    <c:forEach var="brand" items="${brandList}">
                        <option value="${brand}" <c:if test="${brand == selectedBrand}">selected</c:if>>${brand}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-2">
                <label>Từ:</label>
                <input type="number" name="minPrice" value="${minPriceVal}" class="form-control" placeholder="Min" />
            </div>
            <div class="col-md-2">
                <label>Đến:</label>
                <input type="number" name="maxPrice" value="${maxPriceVal}" class="form-control" placeholder="Max" />
            </div>
            <div class="col-md-3">
                <label>Từ khóa:</label>
                <input type="text" name="keyword" value="${keywordVal}" class="form-control" placeholder="Tên sản phẩm..." />
            </div>
            <div class="col-md-3">
                <button type="submit" class="btn btn-warning w-100">Lọc</button>
            </div>
        </div>
    </form>

    <div class="row">
        <c:forEach var="p" items="${products}">
            <div class="col-sm-6 mb-3">
                <div class="product-image-wrapper border p-3 rounded">
                    <div class="productinfo text-center">
                        <img src="${ctx}/images/products/${p.imgURL}" style="height:80px" alt="${p.categoryName}" />
                        <p>${p.brandName}</p>
                        <h2><fmt:formatNumber value="${p.price}" type="number" groupingUsed="true"/> VND</h2>
                        <p>${p.categoryName}</p>
                        <a href="#" class="btn btn-danger select-product-btn"
                           data-category-id="${p.categoryID}"
                           data-component-name="${componentName}"
                           data-category-name="${p.categoryName}">
                            <i class="fa fa-plus"></i> Chọn
                        </a>
                    </div>
                </div>
            </div>
        </c:forEach>

        <c:if test="${empty products}">
            <div class="col-12 text-center">
                <p>Không có sản phẩm nào phù hợp.</p>
            </div>
        </c:if>
    </div>

    <c:if test="${totalPages > 1}">
        <div class="pagination-area text-center mt-3">
            <ul class="pagination justify-content-center">
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a href="#" class="page-link pagination-link" data-page="${i}">${i}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
</div>
<script>
    const ctx = '${ctx}';

    function fetchAndRender(formData, page = null) {
        if (page !== null) formData.set("page", page);
        formData.set("ajax", "true");

        fetch(ctx + "/BuildPC", {
            method: "POST",
            body: formData
        })
        .then(res => res.text())
        .then(html => {
            const container = document.getElementById("product-list-container");
            container.innerHTML = html;
            bindEventListeners(); // Rebind sự kiện sau khi update DOM
        })
        .catch(err => {
            console.error("Lỗi khi fetch:", err);
            alert("Không thể tải danh sách sản phẩm.");
        });
    }

    function filterProducts() {
        const form = document.getElementById("filterForm");
        const formData = new FormData(form);
        fetchAndRender(formData);
        return false;
    }

    function bindDeleteEvents() {
        document.querySelectorAll(".delete-selected").forEach(btn => {
            btn.removeEventListener("click", handleDelete); // tránh bind trùng
            btn.addEventListener("click", handleDelete);
        });
    }

    function handleDelete(e) {
        e.preventDefault();
        const componentName = this.dataset.componentName;

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
                // Xoá phần tử trong DOM luôn mà không reload
                const items = document.querySelectorAll(".list-group .list-group-item");
                items.forEach(item => {
                    const h5 = item.querySelector("h5");
                    if (h5 && h5.innerText.trim().toLowerCase() === componentName.trim().toLowerCase()) {
                        item.remove();
                    }
                });

                // Cập nhật lại tổng tiền
                updateTotalPrice();
            } else {
                alert("Xoá thất bại");
            }
        })
        .catch(() => alert("Lỗi xoá sản phẩm."));
    }

    function updateTotalPrice() {
        let total = 0;
        document.querySelectorAll(".list-group small").forEach(el => {
            const num = parseInt(el.innerText.replace(/[^\d]/g, ''));
            if (!isNaN(num)) total += num;
        });

        const totalBox = document.querySelector(".summary-box span");
        if (totalBox) totalBox.innerText = total.toLocaleString("vi-VN") + "₫";
    }

    function bindEventListeners() {
        // Phân trang
        document.querySelectorAll(".pagination-link").forEach(link => {
            link.removeEventListener("click", handlePagination);
            link.addEventListener("click", handlePagination);
        });

        // Nút chọn sản phẩm
        document.querySelectorAll(".select-product-btn").forEach(btn => {
            btn.removeEventListener("click", handleSelect);
            btn.addEventListener("click", handleSelect);
        });

        // Gán sự kiện cho nút xoá
        bindDeleteEvents();
    }

    function handlePagination(e) {
        e.preventDefault();
        const page = this.dataset.page;
        const form = document.getElementById("filterForm");
        const formData = new FormData(form);
        fetchAndRender(formData, page);
    }

    function handleSelect(e) {
        e.preventDefault();
        const categoryId = this.dataset.categoryId;
        const componentName = this.dataset.componentName;
        // Lấy tên sản phẩm từ data attribute chính xác
        const name = this.dataset.categoryName || "";

        const productBox = this.closest(".productinfo");
        const brand = productBox.querySelector("p")?.innerText || "";
        const priceText = productBox.querySelector("h2")?.innerText || "";

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
            if (!res.ok) throw new Error();

            // Cập nhật UI bên BuildPC.jsp
            const items = document.querySelectorAll(".list-group .list-group-item");
            items.forEach(item => {
                const h5 = item.querySelector("h5");
                if (h5 && h5.innerText.trim().toLowerCase() === componentName.trim().toLowerCase()) {
                    item.querySelector("p")?.remove();
                    item.querySelector("small")?.remove();

                    const p = document.createElement("p");
                    p.className = "mb-1";
                    p.innerText = `${name} - ${brand}`;

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
                    if (changeBtn) changeBtn.innerText = "Change";
                }
            });

            updateTotalPrice();

            // Đóng modal
            const modal = document.getElementById("productModal");
            if (modal) modal.style.display = "none";
        })
        .catch(() => alert("Không thể chọn sản phẩm."));
    }

    document.addEventListener("DOMContentLoaded", bindEventListeners);
</script>





