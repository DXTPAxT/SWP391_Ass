<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
    .scroll-container {
        max-height: 500px;
        overflow-y: auto;
        padding-right: 10px;
    }

    .build-pc-card {
        border: 1px solid #ccc;
        border-radius: 6px;
        padding: 15px;
        margin-bottom: 15px;
        background: #fff;
    }

    .build-pc-card h4 {
        margin: 0 0 10px;
        color: #007bff;
    }

    .build-pc-card img {
        width: 100px;
        height: 80px;
        object-fit: cover;
        margin-bottom: 5px;
        border: 1px solid #ddd;
        display: block;
    }
</style>

<div class="scroll-container">
    <c:forEach var="pc" items="${pcList}">
        <div class="build-pc-card" id="pc-card-${pc.buildPCID}">
            <h4>Build PC #${pc.buildPCID}</h4>

            <p><strong>MainBoard:</strong> ${pc.mainBoard}<br/>
                <img src="${ctx}/ShopPages/Pages/images/anhproduct/${pc.imgMain}" alt="MainBoard" /><br/>
                <small><strong>Bảo hành:</strong> ${pc.mainWarranty} tháng</small>
            </p>

            <p><strong>CPU:</strong> ${pc.cpu}<br/>
                <img src="${ctx}/ShopPages/Pages/images/anhproduct/${pc.imgCPU}" alt="CPU" /><br/>
                <small><strong>Bảo hành:</strong> ${pc.cpuWarranty} tháng</small>
            </p>

            <p><strong>GPU:</strong> ${pc.gpu}<br/>
                <img src="${ctx}/ShopPages/Pages/images/anhproduct/${pc.imgGPU}" alt="GPU" /><br/>
                <small><strong>Bảo hành:</strong> ${pc.gpuWarranty} tháng</small>
            </p>

            <p><strong>RAM:</strong> ${pc.ram}<br/>
                <img src="${ctx}/ShopPages/Pages/images/anhproduct/${pc.imgRAM}" alt="RAM" /><br/>
                <small><strong>Bảo hành:</strong> ${pc.ramWarranty} tháng</small>
            </p>

            <p><strong>SSD:</strong> ${pc.ssd}<br/>
                <img src="${ctx}/ShopPages/Pages/images/anhproduct/${pc.imgSSD}" alt="SSD" /><br/>
                <small><strong>Bảo hành:</strong> ${pc.ssdWarranty} tháng</small>
            </p>

            <p><strong>Case:</strong> ${pc.pcCase}<br/>
                <img src="${ctx}/ShopPages/Pages/images/anhproduct/${pc.imgCase}" alt="Case" /><br/>
                <small><strong>Bảo hành:</strong> ${pc.caseWarranty} tháng</small>
            </p>

            <p><strong>Tổng giá:</strong> <span style="color:green;">${pc.price}₫</span></p>

            <button class="btn btn-sm btn-warning"
                    onclick="loadPC(${pc.buildPCID},
                        '${pc.imgMain}', '${pc.imgCPU}', '${pc.imgGPU}',
                        '${pc.imgRAM}', '${pc.imgSSD}', '${pc.imgCase}')">
                Chọn cấu hình này
            </button>
        </div>
    </c:forEach>
</div>

<script>
    function sortPCs(order) {
        fetch(`${ctx}/BuildPC?service=pc&sort=` + order, {
            method: 'POST'
        })
        .then(res => res.text())
        .then(html => {
            document.getElementById("pcListBody").innerHTML = html;
        })
        .catch(() => {
            alert("❌ Lỗi khi sắp xếp danh sách PC");
        });
    }
</script>
