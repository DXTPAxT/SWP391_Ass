
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
        <div class="choseCate">
            <!-- Repeatable Component Blocks -->

            <div class="list-group-item" id="component-wrapper-2">
                <div class="d-flex align-items-center">
                    <img src="${ctx}/ShopPages/Pages/images/anhproduct/2.jpg" alt="MainBoard" class="component-image" />
                    <div>
                        <div class="component-title">MainBoard
                            <c:forEach var="sel" items="${selectedComponents}">
                                <c:if test="${sel.componentID == 2}">
                                    <div class="selected-item">
                                        <div class="component-meta">${sel.categoryName} - ${sel.brandName}</div>
                                        <a href="#" class="btn btn-sm btn-outline-danger btn-remove mt-2" 
                                           data-delete-url="${ctx}/BuildPC?service=remove&componentID=2">Delete</a>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="text-end">
                    <c:set var="found" value="false" />
                    <c:forEach var="sel" items="${selectedComponents}">
                        <c:if test="${sel.componentID == 2}">
                            <c:set var="found" value="true" />
                            <div class="component-price">
                                <fmt:formatNumber value="${sel.price}" type="number" groupingUsed="true"/>₫
                            </div>
                        </c:if>
                    </c:forEach>
                    <button type="button" class="btn btn-sm btn-primary mt-2 open-component-modal" 
                            data-component-id="2" data-component-name="MainBoard">
                        <c:choose>
                            <c:when test="${found}">Changes</c:when>
                            <c:otherwise>Choose</c:otherwise>
                        </c:choose>
                    </button>
                </div>
            </div>

            <div class="list-group-item" id="component-wrapper-3">
                <div class="d-flex align-items-center">
                    <img src="${ctx}/ShopPages/Pages/images/anhproduct/3.jpg" alt="CPU" class="component-image" />
                    <div>
                        <div class="component-title">CPU
                            <c:forEach var="sel" items="${selectedComponents}">
                                <c:if test="${sel.componentID == 3}">
                                    <div class="selected-item">
                                        <div class="component-meta">${sel.categoryName} - ${sel.brandName}</div>
                                        <a href="#" class="btn btn-sm btn-outline-danger btn-remove mt-2" 
                                           data-delete-url="${ctx}/BuildPC?service=remove&componentID=3">Delete</a>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="text-end">
                    <c:set var="found" value="false" />
                    <c:forEach var="sel" items="${selectedComponents}">
                        <c:if test="${sel.componentID == 3}">
                            <c:set var="found" value="true" />
                            <div class="component-price">
                                <fmt:formatNumber value="${sel.price}" type="number" groupingUsed="true"/>₫
                            </div>
                        </c:if>
                    </c:forEach>
                    <button type="button" class="btn btn-sm btn-primary mt-2 open-component-modal" 
                            data-component-id="3" data-component-name="CPU">
                        <c:choose>
                            <c:when test="${found}">Changes</c:when>
                            <c:otherwise>Choose</c:otherwise>
                        </c:choose>
                    </button>
                </div>
            </div>

            <div class="list-group-item" id="component-wrapper-4">
                <div class="d-flex align-items-center">
                    <img src="${ctx}/ShopPages/Pages/images/anhproduct/4.jpg" alt="GPU" class="component-image" />
                    <div>
                        <div class="component-title">GPU
                            <c:forEach var="sel" items="${selectedComponents}">
                                <c:if test="${sel.componentID == 4}">
                                    <div class="selected-item">
                                        <div class="component-meta">${sel.categoryName} - ${sel.brandName}</div>
                                        <a href="#" class="btn btn-sm btn-outline-danger btn-remove mt-2" 
                                           data-delete-url="${ctx}/BuildPC?service=remove&componentID=4">Delete</a>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="text-end">
                    <c:set var="found" value="false" />
                    <c:forEach var="sel" items="${selectedComponents}">
                        <c:if test="${sel.componentID == 4}">
                            <c:set var="found" value="true" />
                            <div class="component-price">
                                <fmt:formatNumber value="${sel.price}" type="number" groupingUsed="true"/>₫
                            </div>
                        </c:if>
                    </c:forEach>
                    <button type="button" class="btn btn-sm btn-primary mt-2 open-component-modal" 
                            data-component-id="4" data-component-name="GPU">
                        <c:choose>
                            <c:when test="${found}">Changes</c:when>
                            <c:otherwise>Choose</c:otherwise>
                        </c:choose>
                    </button>
                </div>
            </div>

            <div class="list-group-item" id="component-wrapper-5">
                <div class="d-flex align-items-center">
                    <img src="${ctx}/ShopPages/Pages/images/anhproduct/5.jpg" alt="RAM" class="component-image" />
                    <div>
                        <div class="component-title">RAM
                            <c:forEach var="sel" items="${selectedComponents}">
                                <c:if test="${sel.componentID == 5}">
                                    <div class="selected-item">
                                        <div class="component-meta">${sel.categoryName} - ${sel.brandName}</div>
                                        <a href="#" class="btn btn-sm btn-outline-danger btn-remove mt-2" 
                                           data-delete-url="${ctx}/BuildPC?service=remove&componentID=5">Delete</a>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="text-end">
                    <c:set var="found" value="false" />
                    <c:forEach var="sel" items="${selectedComponents}">
                        <c:if test="${sel.componentID == 5}">
                            <c:set var="found" value="true" />
                            <div class="component-price">
                                <fmt:formatNumber value="${sel.price}" type="number" groupingUsed="true"/>₫
                            </div>
                        </c:if>
                    </c:forEach>
                    <button type="button" class="btn btn-sm btn-primary mt-2 open-component-modal" 
                            data-component-id="5" data-component-name="RAM">
                        <c:choose>
                            <c:when test="${found}">Changes</c:when>
                            <c:otherwise>Choose</c:otherwise>
                        </c:choose>
                    </button>
                </div>
            </div>

            <div class="list-group-item" id="component-wrapper-6">
                <div class="d-flex align-items-center">
                    <img src="${ctx}/ShopPages/Pages/images/anhproduct/6.jpg" alt="SSD" class="component-image" />
                    <div>
                        <div class="component-title">SSD
                            <c:forEach var="sel" items="${selectedComponents}">
                                <c:if test="${sel.componentID == 6}">
                                    <div class="selected-item">
                                        <div class="component-meta">${sel.categoryName} - ${sel.brandName}</div>
                                        <a href="#" class="btn btn-sm btn-outline-danger btn-remove mt-2" 
                                           data-delete-url="${ctx}/BuildPC?service=remove&componentID=6">Delete</a>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="text-end">
                    <c:set var="found" value="false" />
                    <c:forEach var="sel" items="${selectedComponents}">
                        <c:if test="${sel.componentID == 6}">
                            <c:set var="found" value="true" />
                            <div class="component-price">
                                <fmt:formatNumber value="${sel.price}" type="number" groupingUsed="true"/>₫
                            </div>
                        </c:if>
                    </c:forEach>
                    <button type="button" class="btn btn-sm btn-primary mt-2 open-component-modal" 
                            data-component-id="6" data-component-name="SSD">
                        <c:choose>
                            <c:when test="${found}">Changes</c:when>
                            <c:otherwise>Choose</c:otherwise>
                        </c:choose>
                    </button>
                </div>
            </div>

            <div class="list-group-item" id="component-wrapper-7">
                <div class="d-flex align-items-center">
                    <img src="${ctx}/ShopPages/Pages/images/anhproduct/7.jpg" alt="Case" class="component-image" />
                    <div>
                        <div class="component-title">Case
                            <c:forEach var="sel" items="${selectedComponents}">
                                <c:if test="${sel.componentID == 7}">
                                    <div class="selected-item">
                                        <div class="component-meta">${sel.categoryName} - ${sel.brandName}</div>
                                        <a href="#" class="btn btn-sm btn-outline-danger btn-remove mt-2" 
                                           data-delete-url="${ctx}/BuildPC?service=remove&componentID=7">Delete</a>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="text-end">
                    <c:set var="found" value="false" />
                    <c:forEach var="sel" items="${selectedComponents}">
                        <c:if test="${sel.componentID == 7}">
                            <c:set var="found" value="true" />
                            <div class="component-price">
                                <fmt:formatNumber value="${sel.price}" type="number" groupingUsed="true"/>₫
                            </div>
                        </c:if>
                    </c:forEach>
                    <button type="button" class="btn btn-sm btn-primary mt-2 open-component-modal" 
                            data-component-id="7" data-component-name="Case">
                        <c:choose>
                            <c:when test="${found}">Changes</c:when>
                            <c:otherwise>Choose</c:otherwise>
                        </c:choose>
                    </button>
                </div>
            </div>

        </div>

        <!-- Modals -->
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

        <div id="toast" style="position: fixed; bottom: 20px; right: 20px; background: #28a745; color: white; padding: 14px 20px; border-radius: 8px; display: none; font-weight: bold; z-index: 99999;">
            Delete successful
        </div>

        <!-- Tổng tiền -->
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

        <script src="${ctx}/ShopPages/Pages/js/buildpc-ajax.js"></script>
    </body>
</html>
