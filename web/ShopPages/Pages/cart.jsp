<%-- 
    Document   : cart
    Created on : May 25, 2025, 9:22:52 PM
    Author     : PC ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Cart | E-Shopper</title>
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/prettyPhoto.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/price-range.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/animate.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/main.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/responsive.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/ShopPages/Pages/css/custom.css?v=1.0.9" rel="stylesheet">
        <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
        <![endif]-->       
        <link rel="shortcut icon" href="images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
    </head><!--/head-->

    <body>
        <jsp:include page="/ShopPages/Pages/components/header.jsp" />

        <section id="cart_items">
            <div class="container">
                <div class="breadcrumbs">
                    <ol class="breadcrumb">
                        <li><a href="#">Home</a></li>
                        <li class="active">Shopping Cart</li>
                    </ol>
                </div>
                <h2 class="mb-4 cartTableTitle">Shopping cart</h2>
                <div class="table-responsive cart_info scrollable-table">
                    <table class="table table-condensed">
                        <thead>
                            <tr class="cart_menu">
                                <td><input class="ml-3 mr-3" type="checkbox" id="checkAll" onchange="toggleAll(this)"/></td>
                                <td class="image">Item</td>
                                <td class="description">Product</td>
                                <td class="price">Price</td>
                                <td class="quantity">Quantity</td>
                                <td class="total">Total</td>
                                <td>Delete</td>
                            </tr>
                        </thead>
                        <c:if test="${cart.size() != 0}">
                            <tbody>
                                <c:forEach var="cartItem" items="${cart}">
                                    <tr>
                                        <td><input type="checkbox" class="select-item ml-3 mr-3" value="${cartItem.getCartItemID()}"/></td>
                                        <td class="cart_product">
                                            <a href="#"><img src="${pageContext.request.contextPath}/ShopPages/Pages/images/cart/two.png" alt=""></a>
                                        </td>
                                        <td class="cart_description">
                                            <div>
                                                <h4><a href="#">${cartItem.getProduct().getName()}</a></h4>
                                                <p>ID: ${cartItem.getProduct().getProductID()}</p>
                                            </div>
                                        </td>
                                        <td class="cart_price" data-label="Giá">
                                            <p class="mb-0">
                                                <fmt:formatNumber value="${cartItem.getProduct().getPrice()}" type="number" groupingUsed="true"/> VND
                                            </p>
                                        </td>
                                        <td class="cart_quantity" data-label="Số lượng">
                                            <div class="cart_quantity_button custom-center">
                                                <button onclick="changeQty(this, -1)">−</button>
                                                <input class="cart_quantity_input"
                                                       type="number" 
                                                       name="quantity" 
                                                       value="${cartItem.getQuantity()}" 
                                                       autocomplete="off"
                                                       data-itemid="${cartItem.getCartItemID()}"
                                                       data-price="${cartItem.getProduct().getPrice()}"
                                                       data-old-value="${cartItem.getQuantity()}"
                                                       oninput="changeQty(this, 0)"
                                                       onchange="debounceTrigger(this)">    
                                                <button onclick="changeQty(this, 1)">+</button>
                                            </div>
                                        </td>
                                        <td class="cart_total" data-label="Tổng">
                                            <p class="cart_total_price">
                                                <fmt:formatNumber value="${cartItem.getProduct().getPrice() * cartItem.getQuantity()}" type="number" groupingUsed="true"/> VND
                                            </p>
                                        </td>
                                        <td class="cart_delete" data-label="Xoá">
                                            <button onclick="confirmDelete(this, ${cartItem.getCartItemID()})"><i class="fa fa-times"></i></button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </c:if>

                    </table>
                    <c:if test="${cart.size() == 0}">
                        <center class="mb-4 fs-3">
                            <p>No items. Please order new products at </p>
                            <a href="CategoriesController" class="btn btn-success">Products</a>
                        </center>
                    </c:if>
                </div>
                <div class="mt-3 mb-3 text-right fs-4">
                    Tổng giá các sản phẩm đã chọn: 
                    <span id="selected-total" style="color: orange; font-weight: bold;">0 VND</span>
                </div>
                <div class="mt-3 mb-3 text-right">
                    <button class="btn btn-success" onclick="submitOrder()" style="min-width: 200px;">Tiến hành đặt hàng</button>
                </div>
            </div>
        </section> <!--/#cart_items-->

        <section id="do_action">
            <div class="container">
                <div class="heading">
                    <h3>What would you like to do next?</h3>
                    <p>Choose if you have a discount code or reward points you want to use or would like to estimate your delivery cost.</p>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <div class="chose_area">
                            <ul class="user_option">
                                <li>
                                    <input type="checkbox">
                                    <label>Use Coupon Code</label>
                                </li>
                                <li>
                                    <input type="checkbox">
                                    <label>Use Gift Voucher</label>
                                </li>
                                <li>
                                    <input type="checkbox">
                                    <label>Estimate Shipping & Taxes</label>
                                </li>
                            </ul>
                            <ul class="user_info">
                                <li class="single_field">
                                    <label>Country:</label>
                                    <select>
                                        <option>United States</option>
                                        <option>Bangladesh</option>
                                        <option>UK</option>
                                        <option>India</option>
                                        <option>Pakistan</option>
                                        <option>Ucrane</option>
                                        <option>Canada</option>
                                        <option>Dubai</option>
                                    </select>

                                </li>
                                <li class="single_field">
                                    <label>Region / State:</label>
                                    <select>
                                        <option>Select</option>
                                        <option>Dhaka</option>
                                        <option>London</option>
                                        <option>Dillih</option>
                                        <option>Lahore</option>
                                        <option>Alaska</option>
                                        <option>Canada</option>
                                        <option>Dubai</option>
                                    </select>

                                </li>
                                <li class="single_field zip-field">
                                    <label>Zip Code:</label>
                                    <input type="text">
                                </li>
                            </ul>
                            <a class="btn btn-default update" href="">Get Quotes</a>
                            <a class="btn btn-default check_out" href="">Continue</a>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="total_area">
                            <ul>
                                <li>Cart Sub Total <span>$59</span></li>
                                <li>Eco Tax <span>$2</span></li>
                                <li>Shipping Cost <span>Free</span></li>
                                <li>Total <span>$61</span></li>
                            </ul>
                            <a class="btn btn-default update" href="">Update</a>
                            <a class="btn btn-default check_out" href="">Check Out</a>
                        </div>
                    </div>
                </div>
            </div>
        </section><!--/#do_action-->

        <jsp:include page="/ShopPages/Pages/components/footer.jsp" />

        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <script>
                        if (performance.getEntriesByType("navigation")[0].type === "back_forward") {
                            location.href = "Cart"; // hoặc bất kỳ route nào gọi lại servlet giỏ hàng
                        }

                        let debounceTimer = null;

                        function changeQty(button, delta) {
                            const input = button.parentElement.querySelector('input');
                            const oldValue = parseInt(input.value) || 1;
                            let newValue = Math.max(0, oldValue + delta);
                            input.dataset.oldValue = newValue;
                            input.value = newValue;
                            debounceTrigger(input);

                            // ✅ Cập nhật lại tổng giá đã chọn
                            updateSelectedTotal();
                        }

                        function debounceTrigger(input) {
                            clearTimeout(debounceTimer);
                            debounceTimer = setTimeout(() => {
                                validateAndUpdate(input);
                            }, 200);
                        }

                        function validateAndUpdate(input) {
                            const cartItemID = input.dataset.itemid;
                            const price = parseFloat(input.dataset.price);
                            const value = input.value.trim();
                            const oldValue = parseInt(input.dataset.oldValue || "1");

                            // ❌ Không hợp lệ: không phải số nguyên dương
                            if (!/^\d+$/.test(value)) {
                                Swal.fire({
                                    title: "Số lượng không hợp lệ!",
                                    text: "Vui lòng nhập một số nguyên dương.",
                                    icon: "error"
                                });
                                input.value = oldValue;
                                return;
                            }

                            const quantity = parseInt(value);

                            // ❗ Nếu <= 0 → hỏi xoá
                            if (quantity <= 0) {
                                comfirmDeletePopUp().then((confirmDelete) => {
                                    if (confirmDelete) {
                                        deleteCartItem(cartItemID, input);
                                    } else {
                                        // Huỷ xoá → phục hồi số lượng cũ
                                        input.value = 1;
                                        const lineTotalElem = input.closest('tr').querySelector('.cart_total');
                                        const total = Number(price) * 1;
                                        lineTotalElem.innerHTML = `<p class="cart_total_price">` + total.toLocaleString('vi-VN', {maximumFractionDigits: 0}).replace(/\./g, ',') + ' VND</p>';
                                        updateSelectedTotal();

                                        // Cập nhật lại DB với oldValue
                                        const params = new URLSearchParams();
                                        params.append("cartItemID", cartItemID);
                                        params.append("quantity", 1);

                                        fetch('UpdateCartItem', {
                                            method: 'POST',
                                            headers: {
                                                'Content-Type': 'application/x-www-form-urlencoded'
                                            },
                                            body: params.toString()
                                        }).then(res => res.text())
                                                .then(data => {
                                                    if (data.trim() !== 'success') {
                                                        Swal.fire("Lỗi", "Cập nhật thất bại", "error");
                                                    }
                                                });
                                    }
                                });
                            } else {
                                const lineTotalElem = input.closest('tr').querySelector('.cart_total');
                                const total = Number(price) * oldValue;
                                lineTotalElem.innerHTML = `<p class="cart_total_price">` + total.toLocaleString('vi-VN', {maximumFractionDigits: 0}).replace(/\./g, ',') + ' VND</p>';


                                // Cập nhật lại DB với oldValue
                                const params = new URLSearchParams();
                                params.append("cartItemID", cartItemID);
                                params.append("quantity", input.value);

                                fetch('UpdateCartItem', {
                                    method: 'POST',
                                    headers: {
                                        'Content-Type': 'application/x-www-form-urlencoded'
                                    },
                                    body: params.toString()
                                }).then(res => res.text())
                                        .then(data => {
                                            if (data.trim() !== 'success') {
                                                Swal.fire("Lỗi", "Cập nhật thất bại", "error");
                                            }
                                        });
                            }
                        }

                        function confirmDelete(button, cartItemID) {
                            comfirmDeletePopUp().then((confirmDelete) => {
                                if (confirmDelete) {
                                    deleteCartItem(cartItemID, button);
                                }
                            });
                        }

                        function comfirmDeletePopUp() {
                            return Swal.fire({
                                title: "Xoá sản phẩm?",
                                text: "Bạn có muốn bỏ sản phẩm này khỏi giỏ hàng không?",
                                icon: "warning",
                                showCancelButton: true,
                                confirmButtonText: "Có, xoá!",
                                cancelButtonText: "Không"
                            }).then((result) => result.isConfirmed);
                        }

                        function deleteCartItem(cartItemID, element) {
                            const params = new URLSearchParams();
                            params.append("cartItemID", cartItemID);

                            fetch('DeleteCartItem', {
                                method: 'POST',
                                headers: {
                                    'Content-Type': 'application/x-www-form-urlencoded'
                                },
                                body: params.toString()
                            })
                                    .then(response => response.text())
                                    .then(data => {
                                        if (data.trim() === 'success') {
                                            const row = element.closest('tr');
                                            row.parentNode.removeChild(row);

// Sau khi xóa, kiểm tra còn sản phẩm nào không
                                            const remainingItems = document.querySelectorAll('.cart_info tbody tr').length;
                                            if (remainingItems === 0) {
                                                const cartInfoDiv = document.querySelector('.cart_info');
                                                cartInfoDiv.innerHTML = cartInfoDiv.innerHTML + `
        <center class="mb-4 fs-3">
            <p>No items. Please order new products at </p>
            <a href="Product" class="btn btn-success">Products</a>
        </center>
    `;

                                                // Đặt tổng giá về 0
                                                document.getElementById("selected-total").textContent = "0 VND";
                                            }


                                            // ✅ GỌI LẠI CẬP NHẬT TỔNG TIỀN SAU KHI XOÁ
                                            updateSelectedTotal();

                                        } else {
                                            Swal.fire("Lỗi", "Xoá thất bại", "error");
                                        }
                                    });
                        }
        </script>

        <script>
            function formatCurrency(number) {
                return Number(number).toLocaleString('vi-VN', {maximumFractionDigits: 0}).replace(/\./g, ',') + ' VND';
            }

            function updateSelectedTotal() {
                let total = 0;

                document.querySelectorAll('.select-item:checked').forEach(cb => {
                    const row = cb.closest('tr');
                    const price = parseFloat(row.querySelector('input[type="number"]').dataset.price);
                    const quantity = parseInt(row.querySelector('input[type="number"]').value);
                    total += price * quantity;
                });

                document.getElementById("selected-total").textContent = formatCurrency(total);
            }

// Gọi khi checkbox hoặc quantity thay đổi
            document.addEventListener('DOMContentLoaded', function () {
                document.querySelectorAll('.select-item').forEach(cb => {
                    cb.addEventListener('change', updateSelectedTotal);
                });

                document.querySelectorAll('.cart_quantity_input').forEach(input => {
                    input.addEventListener('change', updateSelectedTotal);
                    input.addEventListener('input', updateSelectedTotal);
                });

                document.getElementById('checkAll')?.addEventListener('change', updateSelectedTotal);
            });

            function toggleAll(checkbox) {
                const checkboxes = document.querySelectorAll('.select-item');
                checkboxes.forEach(cb => cb.checked = checkbox.checked);
            }

            function submitOrder() {
                const selected = [...document.querySelectorAll('.select-item:checked')]
                        .map(cb => cb.value);

                if (selected.length === 0) {
                    Swal.fire("Thông báo", "Vui lòng chọn ít nhất một sản phẩm để đặt hàng!", "info");
                    return;
                }

                // Gửi danh sách ID qua POST (hoặc Ajax)
                const form = document.createElement('form');
                form.method = 'POST';
                form.action = 'Checkout';

                selected.forEach(id => {
                    const input = document.createElement('input');
                    input.type = 'hidden';
                    input.name = 'selectedItems';
                    input.value = id;
                    form.appendChild(input);
                });

                document.body.appendChild(form);
                form.submit();
            }
        </script>


        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
