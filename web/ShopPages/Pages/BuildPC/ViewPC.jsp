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

        <%-- Lấy WarrantyID từng linh kiện (nếu có) --%>
        <c:set var="mainWarrantyID" value="0" />
        <c:set var="cpuWarrantyID" value="0" />
        <c:set var="gpuWarrantyID" value="0" />
        <c:set var="ramWarrantyID" value="0" />
        <c:set var="ssdWarrantyID" value="0" />
        <c:set var="caseWarrantyID" value="0" />

        <c:if test="${not empty allWarrantyMap[pc.buildPCID][pc.mainBoardID]}">
            <c:set var="mainWarrantyID" value="${allWarrantyMap[pc.buildPCID][pc.mainBoardID][0].warrantyDetailID}" />
        </c:if>
        <c:if test="${not empty allWarrantyMap[pc.buildPCID][pc.cpuID]}">
            <c:set var="cpuWarrantyID" value="${allWarrantyMap[pc.buildPCID][pc.cpuID][0].warrantyDetailID}" />
        </c:if>
        <c:if test="${not empty allWarrantyMap[pc.buildPCID][pc.gpuID]}">
            <c:set var="gpuWarrantyID" value="${allWarrantyMap[pc.buildPCID][pc.gpuID][0].warrantyDetailID}" />
        </c:if>
        <c:if test="${not empty allWarrantyMap[pc.buildPCID][pc.ramID]}">
            <c:set var="ramWarrantyID" value="${allWarrantyMap[pc.buildPCID][pc.ramID][0].warrantyDetailID}" />
        </c:if>
        <c:if test="${not empty allWarrantyMap[pc.buildPCID][pc.ssdID]}">
            <c:set var="ssdWarrantyID" value="${allWarrantyMap[pc.buildPCID][pc.ssdID][0].warrantyDetailID}" />
        </c:if>
        <c:if test="${not empty allWarrantyMap[pc.buildPCID][pc.caseID]}">
            <c:set var="caseWarrantyID" value="${allWarrantyMap[pc.buildPCID][pc.caseID][0].warrantyDetailID}" />
        </c:if>

        <div class="build-pc-card">
            <h4>Build PC #${pc.buildPCID}</h4>

            <p><strong>MainBoard:</strong> ${pc.mainBoard}<br/>
                <img src="${ctx}/ShopPages/Pages/images/anhproduct/${pc.imgMain}" alt="MainBoard" /><br/>
                <strong>Bảo hành:</strong>
                <c:choose>
                    <c:when test="${not empty allWarrantyMap[pc.buildPCID][pc.mainBoardID]}">
                        <c:forEach var="w" items="${allWarrantyMap[pc.buildPCID][pc.mainBoardID]}">
                            ${w.description} - ${w.price}₫<br/>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>Không có thông tin bảo hành<br/></c:otherwise>
                </c:choose>
            </p>

            <p><strong>CPU:</strong> ${pc.cpu}<br/>
                <img src="${ctx}/ShopPages/Pages/images/anhproduct/${pc.imgCPU}" alt="CPU" /><br/>
                <strong>Bảo hành:</strong>
                <c:choose>
                    <c:when test="${not empty allWarrantyMap[pc.buildPCID][pc.cpuID]}">
                        <c:forEach var="w" items="${allWarrantyMap[pc.buildPCID][pc.cpuID]}">
                            ${w.description} - ${w.price}₫<br/>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>Không có thông tin bảo hành<br/></c:otherwise>
                </c:choose>
            </p>

            <p><strong>GPU:</strong> ${pc.gpu}<br/>
                <img src="${ctx}/ShopPages/Pages/images/anhproduct/${pc.imgGPU}" alt="GPU" /><br/>
                <strong>Bảo hành:</strong>
                <c:choose>
                    <c:when test="${not empty allWarrantyMap[pc.buildPCID][pc.gpuID]}">
                        <c:forEach var="w" items="${allWarrantyMap[pc.buildPCID][pc.gpuID]}">
                            ${w.description} - ${w.price}₫<br/>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>Không có thông tin bảo hành<br/></c:otherwise>
                </c:choose>
            </p>

            <p><strong>RAM:</strong> ${pc.ram}<br/>
                <img src="${ctx}/ShopPages/Pages/images/anhproduct/${pc.imgRAM}" alt="RAM" /><br/>
                <strong>Bảo hành:</strong>
                <c:choose>
                    <c:when test="${not empty allWarrantyMap[pc.buildPCID][pc.ramID]}">
                        <c:forEach var="w" items="${allWarrantyMap[pc.buildPCID][pc.ramID]}">
                            ${w.description} - ${w.price}₫<br/>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>Không có thông tin bảo hành<br/></c:otherwise>
                </c:choose>
            </p>

            <p><strong>SSD:</strong> ${pc.ssd}<br/>
                <img src="${ctx}/ShopPages/Pages/images/anhproduct/${pc.imgSSD}" alt="SSD" /><br/>
                <strong>Bảo hành:</strong>
                <c:choose>
                    <c:when test="${not empty allWarrantyMap[pc.buildPCID][pc.ssdID]}">
                        <c:forEach var="w" items="${allWarrantyMap[pc.buildPCID][pc.ssdID]}">
                            ${w.description} - ${w.price}₫<br/>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>Không có thông tin bảo hành<br/></c:otherwise>
                </c:choose>
            </p>

            <p><strong>Case:</strong> ${pc.pcCase}<br/>
                <img src="${ctx}/ShopPages/Pages/images/anhproduct/${pc.imgCase}" alt="Case" /><br/>
                <strong>Bảo hành:</strong>
                <c:choose>
                    <c:when test="${not empty allWarrantyMap[pc.buildPCID][pc.caseID]}">
                        <c:forEach var="w" items="${allWarrantyMap[pc.buildPCID][pc.caseID]}">
                            ${w.description} - ${w.price}₫<br/>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>Không có thông tin bảo hành<br/></c:otherwise>
                </c:choose>
            </p>

            <p><strong>Tổng giá:</strong> <span style="color:green;">${pc.price}₫</span></p>

            <button class="btn btn-sm btn-warning"
                    onclick="loadPC(${pc.buildPCID},
                    '${pc.imgMain}', '${pc.imgCPU}', '${pc.imgGPU}',
                    '${pc.imgRAM}', '${pc.imgSSD}', '${pc.imgCase}',
                    ${mainWarrantyID}, ${cpuWarrantyID}, ${gpuWarrantyID},
                    ${ramWarrantyID}, ${ssdWarrantyID}, ${caseWarrantyID})">
                Chọn cấu hình này
            </button>

        </div>

    </c:forEach>
</div>

<script>
    function sortPCs(order) {
        fetch(`${ctx}/BuildPC?service=pc&sort=${order}`, {method: 'POST'})
                        .then(res => res.text())
                        .then(html => document.getElementById("pcListBody").innerHTML = html)
                        .catch(() => alert("❌ Lỗi khi sắp xếp danh sách PC"));
            }
</script>
