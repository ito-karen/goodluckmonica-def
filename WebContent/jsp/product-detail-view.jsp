<!DOCTYPE html>
<!-- All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited -->
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>オンラインショップ</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<jsp:include page="/jsp/header.jsp" />
	<div id="mainArea">
		<%-- contents start --%>
<h1><a href="${pageContext.request.contextPath}/mserv?flag=B0101ShowCategory&categoryId=<c:out value="${product.category.categoryId}"/>">＜<c:out value="${product.category.categoryName}" />
</a>
</h1>
		<div id="target" style="color: red;">
			<c:out value="${message}" />
		</div>

<%--<c:if test="${not empty product}">
<table border="1">
        <thead>
            <tr>
                <th>商品画像</th>
                <th>商品ID</th>
                <th>商品名</th>
                <th>価格</th>
                <th>在庫数</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${productlist}">
                <tr>
                    <td><img src='${pageContext.request.contextPath}/img_DB/<c:out value="${product.picture}"/>'></td>
                    <td><c:out value="${product.productId}"/></td>
                    <td><c:out value="${product.productName}"/></td>
                    <td><c:out value="${product.price}"/></td>
                    <td><c:out value="${product.stock.stockQuantity}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

<p>
  <a href='${pageContext.request.contextPath}/mserv?flag=B0101AddToCart&productId=<c:out value="${product.productId}"/>'>カートに入れる</a>
</p>
</c:if>--%>


<div>
<c:if test="${not empty product}">
<div>
    <img src='${pageContext.request.contextPath}/img_DB/<c:out value="${product.picture}"/>'>
    <p><c:out value="${product.productId}"/></p>
    <p><c:out value="${product.productName}"/></p>
    <p><c:out value="${product.price}"/>円</p>
    <p><c:out value="${product.stock.quantity}"/>個</p>
    <p><a href='${pageContext.request.contextPath}/mserv?flag=B0101AddToCart&productId=<c:out value="${product.productId}"/>'>
				<img src="${pageContext.request.contextPath}/img/cart.gif">
				</a></p>
</div>
</c:if>
</div>

				<p>
			<a href="${pageContext.request.contextPath}/mserv">[トップに戻る]</a>
		</p>
		<%-- contents end --%>
	</div>
	<div id="footerArea"style="clear: both; margin-top: 20px;">
		<small> Copyright YYYY FUJITSU LEARNING MEDIA LIMITED </small>
	</div>
</body>
</html>