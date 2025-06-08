<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Build PC | CyberBeast</title>
        <style>
            body {
                background: #121212;
                color: white;
                font-family: sans-serif;
                margin: 0;
                padding: 20px;
            }

            .list-group {
                margin-top: 30px;
            }

            .list-group-item {
                background: #1e1e1e;
                padding: 15px;
                margin-bottom: 10px;
                border-radius: 8px;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

            .btn {
                padding: 8px 16px;
                background: #3498db;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .btn:hover {
                background: #2980b9;
            }

            /* Modal */
            .modal-overlay {
                display: none;
                position: fixed;
                z-index: 9999;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(0,0,0,0.7);
                justify-content: center;
                align-items: center;
            }

            .modal-content {
                background: #222;
                color: #fff;
                width: 80%;
                max-height: 80%;
                overflow-y: auto;
                border-radius: 10px;
                padding: 20px;
                position: relative;
            }

            .close-btn {
                position: absolute;
                top: 10px;
                right: 20px;
                font-size: 24px;
                cursor: pointer;
                color: white;
            }

            .component-info {
                display: flex;
                align-items: center;
            }

            .component-info img {
                width: 60px;
                height: 60px;
                object-fit: contain;
                margin-right: 20px;
            }
        </style>
    </head>
    <body>

        <h2>🛠 Build PC</h2>
        <p>Chọn các linh kiện để xây dựng máy tính của bạn</p>

        <!-- Component List -->
        <div class="list-group">
            <c:forEach var="comp" items="${components}" begin="1">
                <div class="list-group-item">
                    <div class="component-info">
                        <img src="${ctx}/images/components/${comp.componentID}.png" alt="${comp.componentName}" />
                        <span class="fs-5 fw-bold">${comp.componentName}</span>
                    </div>
                    <button type="button"
                            class="btn open-component-modal"
                            data-component-id="${comp.componentID}"
                            data-component-name="${comp.componentName}">
                        Chọn
                    </button>
                </div>
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

        <!-- Script -->
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const modal = document.getElementById('customModal');
                const modalTitle = document.getElementById('modalTitle');
                const modalBody = document.getElementById('modalBody');
                const closeModal = document.getElementById('closeModal');

                closeModal.addEventListener('click', () => {
                    modal.style.display = 'none';
                });

                document.querySelectorAll('.open-component-modal').forEach(button => {
                    button.addEventListener('click', () => {
                        const componentName = button.dataset.componentName;

                        modal.style.display = 'flex';
                        modalTitle.textContent = componentName;
                        modalBody.innerHTML = "<p>Đang tải...</p>";

                        // ✅ gọi servlet với contextPath chính xác
                        fetch('<c:out value="${pageContext.request.contextPath}" />/CategoriesController?service=filter&component='
                                + encodeURIComponent(componentName) + '&ajax=true')
                                .then(res => res.text())
                                .then(html => {
                                    modalBody.innerHTML = html;
                                })
                                .catch(err => {
                                    modalBody.innerHTML = '<p style="color:red">Lỗi khi tải dữ liệu.</p>';
                                });
                    });
                });

                window.onclick = function (e) {
                    if (e.target === modal) {
                        modal.style.display = "none";
                    }
                };
            });

        </script>
    </body>
</html>
