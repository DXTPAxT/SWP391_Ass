    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="ctx" value="${pageContext.request.contextPath}" />
    <style>
        .modal-overlay {
            display: none;
            position: fixed;
            z-index: 9999;
            top: 0;
            left: 0;
            width: 100vw;
            height: 100vh;
            background: rgba(0, 0, 0, 0.7);
            justify-content: center;
            align-items: center;
            overflow-y: auto;
        }

        .modal-content {
            background: #1e1e1e;
            color: #fff;
            width: 90%;
            max-width: 700px;
            margin: 40px auto;
            border-radius: 12px;
            padding: 30px 20px;
            position: relative;
            box-shadow: 0 8px 30px rgba(0, 0, 0, 0.6);
            font-family: 'Segoe UI', sans-serif;
        }

        h4 {
            font-size: 24px;
            color: #00c0ff;
            margin-bottom: 20px;
        }

        .product-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
            gap: 20px;
        }

        .product-card {
            background-color: #2a2a2a;
            padding: 12px;
            border-radius: 10px;
            text-align: center;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .product-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 6px 20px rgba(255, 255, 255, 0.1);
        }

        .product-card img {
            max-width: 100%;
            height: 120px;
            object-fit: contain;
            margin-bottom: 10px;
            border-radius: 6px;
            background-color: #fff;
        }

        .product-card h6 {
            font-size: 14px;
            color: #ccc;
            margin: 5px 0;
        }

        .product-card p {
            font-size: 13px;
            color: #aaa;
        }

        .select-product-btn {
            background-color: #ffa500;
            border: none;
            padding: 6px 14px;
            color: black;
            border-radius: 5px;
            font-weight: bold;
            margin-top: 10px;
            cursor: pointer;
        }

        .select-product-btn:hover {
            background-color: #ff8800;
        }
    </style>

    <!-- Bộ lọc -->
    <form id="filterForm" style="margin-bottom: 20px; display: flex; gap: 10px; flex-wrap: wrap;">
        <input type="hidden" name="componentID" value="${param.componentID}" />

        <input type="text" name="keyword" value="${param.keyword}" placeholder="Search..." class="form-control" style="flex: 1" />

        <select name="brand" class="form-control">
            <option value="">-- Brand --</option>
            <c:forEach var="b" items="${brands}">
                <option value="${b.brandName}" ${param.brand == b.brandName ? 'selected' : ''}>${b.brandName}</option>
            </c:forEach>
        </select>

        <input type="number" name="minPrice" value="${param.minPrice}" placeholder="Min Price" class="form-control" style="width: 100px;" />
        <input type="number" name="maxPrice" value="${param.maxPrice}" placeholder="Max Price" class="form-control" style="width: 100px;" />

        <button type="submit" class="btn btn-primary">Filter</button>
    </form>

    <!-- Danh sách sản phẩm -->
    <div>
        <h4>Sản phẩm tương ứng</h4>
        <div class="product-grid">
            <c:forEach var="p" items="${products}">
                <div class="product-card">
                    <img src="${ctx}/images/products/${p.imgURL}" alt="${p.categoryName}">
                    <h6>${p.categoryName}</h6>
                    <p>Hãng: ${p.brandName}</p>
                    <button class="select-product-btn" data-category-id="${p.categoryID}">Chọn</button>
                </div>
            </c:forEach>
        </div>
    </div>

  <script>
    const ctx = '${ctx}';
    const modal = document.getElementById('customModal');

    // AJAX lọc sản phẩm
    document.getElementById("filterForm").addEventListener("submit", function (e) {
        e.preventDefault();
        const formData = new FormData(this);
        const params = new URLSearchParams(formData);
        params.append("ajax", "true");

        fetch(`${ctx}/BuildPC?service=filter&${params.toString()}`)
            .then(res => res.text())
            .then(html => {
                document.getElementById("modalBody").innerHTML = html;
            })
            .catch(() => alert("Lỗi khi lọc sản phẩm"));
    });

    // AJAX chọn sản phẩm và cập nhật UI
    document.querySelectorAll('.select-product-btn').forEach(button => {
        button.addEventListener('click', () => {
            const categoryId = button.dataset.categoryId;

            fetch(`${ctx}/BuildPC?service=add&categoryID=${categoryId}`, {
                method: 'GET',
                headers: {
                    'X-Requested-With': 'XMLHttpRequest' // Quan trọng để servlet biết đây là AJAX
                }
            })
            .then(res => res.text())
            .then(html => {
                const componentWrapper = document.querySelector(`[data-category-id-wrapper="${categoryId}"]`);
                if (componentWrapper) {
                    componentWrapper.innerHTML = html;
                }
                modal.style.display = 'none';
            })
            .catch(() => alert("Lỗi khi thêm sản phẩm."));
        });
    });
</script>

