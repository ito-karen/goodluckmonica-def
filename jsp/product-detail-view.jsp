<!DOCTYPE html>
<!-- All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited -->
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>オンラインショップ</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
<style>
body {
            font-family: Arial, sans-serif;
        }
  .product-info {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
  }
  .product-info p {
    margin: 0;
  }
  .product-price, .product-stock, .product-cart {
    text-align: left;
    margin-left: 0; /* 同じスタート地点から表示 */
  }
  .blue-line {
    border-top: 2px solid #0099ff;
    margin: 10px 0;
    width: 100%;
  }
  .product-container {
    display: flex;
    align-items: center;
  }
  .product-details {
  text-align: left;
    margin-left: 20px;
  }
  .product-image-container {
    background-color: #E5E5E5;
    padding: 20px;
    border-radius: 5px;
  }
  .full-width-line {
    width: calc(100% - 40px); /* Adjust the width to match the first blue line */
  }
  .link{
color: black; /* リンクの色を黒に設定 */
}
</style>
</head>
<body>
	<jsp:include page="/jsp/header.jsp" />
	<div id="mainArea">
		<%-- contents start --%>
		<h1>
			<a class="link" href="${pageContext.request.contextPath}/mserv?flag=B0101ShowCategory&categoryId=<c:out value="${product.category.categoryId}"/>">
				<c:out value="${product.category.categoryName}" />
			</a>
		</h1>
		<div id="target" style="color: red;">
			<c:out value="${message}" />
		</div>

		<c:if test="${not empty product}">
			<div class="product-container">
				<div class="product-image-container">
					<img src='${pageContext.request.contextPath}/img_DB/<c:out value="${product.picture}"/>'>
				</div>
				<div style="width: 35%;" class="product-details">
					<p><c:out value="${product.productId}"/> <c:out value="${product.productName}"/></p>
					<div class="blue-line full-width-line"></div>
					<p class="product-price">価格：<c:out value="${product.price}"/>円</p>
					<p class="product-stock">在庫数：<c:out value="${product.stock.quantity}"/>個</p>
					<p class="product-cart">
						<a href='${pageContext.request.contextPath}/mserv?flag=B0101AddToCart&productId=<c:out value="${product.productId}"/>'>
							<img src="${pageContext.request.contextPath}/img/cart.gif">
						</a>
					</p>
				</div>
			</div>
		</c:if>

		<p>
			<a href="${pageContext.request.contextPath}/mserv">[トップに戻る]</a>
		</p>
		<%-- contents end --%>
	</div>
	<div id="footerArea" style="clear: both; margin-top: 20px;">
		<small> Copyright YYYY FUJITSU LEARNING MEDIA LIMITED </small>
	</div>
</body>
</html>