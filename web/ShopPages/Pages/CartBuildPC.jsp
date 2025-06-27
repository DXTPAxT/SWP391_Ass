<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cart | E-Shopper</title>

        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/prettyPhoto.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/price-range.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/animate.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/main.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/responsive.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/custom.css?v=1.0.9" rel="stylesheet">
    </head>

    <body>
        <jsp:include page="/ShopPages/Pages/components/header.jsp" />

        <section id="cart_items">
            <div class="container">
                <div class="breadcrumbs">
                    <ol class="breadcrumb">
                        <li><a href="#">Home</a></li>
                        <li class="active">Cart - Build PC</li>
                    </ol>
                </div>

                <h2 class="mb-4 cartTableTitle">Your Build PC Cart</h2>

                <div class="table-responsive cart_info scrollable-table">
                    <table class="table table-condensed">
                        <thead>
                            <tr class="cart_menu">
                                <td><input class="ml-3 mr-3" type="checkbox" id="checkAll" onchange="toggleAll(this)"/></td>
                                <td>cartBuildPCID</td>
                                <td>MainBoard</td>
                                <td>CPU</td>
                                <td>GPU</td>
                                <td>RAM</td>
                                <td>SSD</td>
                                <td>Case</td>
                                <td>Price</td>
                                <td>Delete</td>
                            </tr>
                        </thead>

                        <c:if test="${not empty cartBuildPC}">
                            <tbody>
                                <c:forEach var="pc" items="${cartBuildPC}">
                                    <tr>
                                        <td><input type="checkbox" class="select-item ml-3 mr-3" value="${pc.cartBuildPCID}"/></td>
                                        <td>${pc.cartBuildPCID}</td>
                                        <td>${pc.mainBoard}</td>
                                        <td>${pc.cpu}</td>
                                        <td>${pc.gpu}</td>
                                        <td>${pc.ram}</td>
                                        <td>${pc.ssd}</td>
                                        <td>${pc.pcCase}</td>
                                        <td>
                                            <fmt:formatNumber value="${pc.price}" type="number" groupingUsed="true"/> VND
                                        </td>
                                        <td>
                                            <button onclick="confirmDelete(this, ${pc.cartBuildPCID})">
                                                <i class="fa fa-times"></i>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </c:if>
                    </table>

                    <c:if test="${empty cartBuildPC}">
                        <center class="mb-4 fs-3">
                            <p>No PC builds in cart. Please build your PC at:</p>
                            <a href="BuildPCController" class="btn btn-success">Build PC</a>
                        </center>
                    </c:if>
                </div>
                <div class="mt-3 mb-3 text-right fs-4">
                    Total price of selected builds:
                    <span id="selected-total" style="color: orange; font-weight: bold;">0 VND</span>
                </div>

                <div class="mt-3 mb-3 text-right fs-4">
                    Deposit amount (20%):
                    <span id="deposit-amount" style="color: green; font-weight: bold;">0 VND</span>
                </div>


                <div class="mt-3 mb-3 text-right">
                    <button class="btn btn-success" onclick="submitOrder()" style="min-width: 200px;">DEPOSIT</button>
                </div>
            </div>
        </section>
  <script>
    function updateTotal() {
        let total = 0;
        const selectedCheckboxes = document.querySelectorAll(".select-item:checked");

        selectedCheckboxes.forEach(cb => {
            const row = cb.closest("tr");
            const priceText = row.querySelector("td:nth-child(9)").innerText.replace(/[^0-9]/g, '');
            total += parseInt(priceText) || 0;
        });

        document.getElementById("selected-total").innerText = total.toLocaleString() + " VND";
        const deposit = Math.floor(total * 0.2); // 20% tiền cọc
        document.getElementById("deposit-amount").innerText = deposit.toLocaleString() + " VND";
    }

    function toggleAll(master) {
        const allCheckboxes = document.querySelectorAll(".select-item");
        allCheckboxes.forEach(cb => cb.checked = master.checked);
        updateTotal();
    }

    document.addEventListener("DOMContentLoaded", function () {
        const itemCheckboxes = document.querySelectorAll(".select-item");
        const checkAllBox = document.getElementById("checkAll");

        itemCheckboxes.forEach(cb => {
            cb.addEventListener("change", () => {
                const allChecked = [...itemCheckboxes].every(c => c.checked);
                checkAllBox.checked = allChecked;
                updateTotal();
            });
        });

        checkAllBox.addEventListener("change", () => toggleAll(checkAllBox));
    });

    function submitOrder() {
        const selected = document.querySelectorAll(".select-item:checked");
        if (selected.length === 0) {
            alert("Vui lòng chọn ít nhất một Build PC để đặt cọc.");
            return;
        }

        const ids = Array.from(selected).map(cb => cb.value);
        const depositText = document.getElementById("deposit-amount").innerText;

        if (!confirm(`Bạn sẽ đặt cọc ${depositText} cho ${ids.length} Build PC. Tiếp tục?`)) return;

        fetch("CardBuildPc", {
            method: "POST",
            headers: {"Content-Type": "application/x-www-form-urlencoded"},
            body: new URLSearchParams({
                service: "depositBuildPC",
                ids: ids.join(",")
            })
        })
            .then(res => res.text())
            .then(msg => {
                if (msg === "SUCCESS") {
                    alert("Đặt cọc thành công. Tiền còn lại sẽ thanh toán sau.");
                    location.reload();
                } else {
                    alert("Có lỗi xảy ra khi đặt cọc.");
                }
            })
            .catch(() => alert("Lỗi kết nối tới server!"));
    }
</script>


        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/jquery.scrollUp.min.js"></script>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/jquery.prettyPhoto.js"></script>
        <script src="${pageContext.request.contextPath}/ShopPages/Pages/js/main.js"></script>
    </body>

</html>
