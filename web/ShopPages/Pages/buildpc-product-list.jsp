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

<div>
    <h4>Sản phẩm tương ứng</h4>
    <div class="product-grid">
        <c:forEach var="p" items="${products}">
            <div class="product-card">
                <img src="${ctx}/images/products/${p.imgURL}" alt="${p.categoryName}">
                <h6>${p.categoryName}</h6>
                <p>Hãng: ${p.brandName}</p>
                <button 
                    class="select-product-btn"
                    data-category-id="${p.categoryID}">
                    Chọn
                </button>
            </div>
        </c:forEach>
    </div>
</div>

<script>
    const modal = document.getElementById('customModal');

    document.querySelectorAll('.select-product-btn').forEach(button => {
        button.addEventListener('click', () => {
            const categoryId = button.dataset.categoryId;
            fetch(`${'${ctx}'}/BuildPC?service=add&categoryID=` + categoryId)
                .then(() => {
                    if (modal) modal.style.display = "none";
                    window.location.href = `${'${ctx}'}/BuildPC`;
                })
                .catch(() => {
                    alert("Lỗi khi thêm sản phẩm.");
                });
        });
    });
</script>
