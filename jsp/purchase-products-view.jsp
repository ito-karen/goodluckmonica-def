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
</head>
<body>
	<jsp:include page="/jsp/header-non-menu.jsp" />
	<div id="mainArea">
		<h1>商品購入</h1>

		<%-- contents start --%>

		<div id="target" style="color: red;">
			<c:forEach var="errorMessage" items="${errorMessageList}" varStatus="status">
				<p>
					<c:out value="${errorMessage}" />
				</p>
			</c:forEach>
		</div>

		<h2>会員情報</h2>
		<table border="1">
			<tr>
				<th>会員ID</th>
				<td><c:out value="${CommonLoginMember.memberId}" /></td>
			</tr>
			<tr>
				<th>名前</th>
				<td><c:out value="${CommonLoginMember.memberName}" /></td>
			</tr>
			<tr>
				<th>住所</th>
				<td><c:out value="${CommonLoginMember.address}" /></td>
			</tr>
			<tr>
				<th>電話番号</th>
				<td><c:out value="${CommonLoginMember.phone}" /></td>
			</tr>
		</table>

		<form method="post" action="${pageContext.request.contextPath}/mserv" id="chkForm">
		<h2>クレジットカード番号入力</h2>
			<table>
				<tr>
					<td>クレジットカード番号</td>
					<td><input type="text" name="creditCardIdfull" id="creditCardIdfull" size="20"></td>
				</tr>
			</table>
			<br> <input type="hidden" name="flag" value="B0103PurchaseProducts">
			<input type="submit" value="決定">
		</form>


		<c:if test="${!empty B01ShoppingCart}">

		<form method="get" action="${pageContext.request.contextPath}/mserv">
		<h2>購入内容</h2>

				<table border="1" id="cart">
					<tr>
						<th>商品ID</th>
						<th>商品名</th>
						<th>単価</th>
						<th>在庫</th>
						<th>数量</th>
						<th>ポイント</th>
						<th>小計金額</th>
					</tr>
					<c:forEach var="order" items="${B01ShoppingCart}"
						varStatus="status">
						<tr>
							<td><c:out value="${order.product.productId}" /></td>
							<td><c:out value="${order.product.productName}" /></td>
							<td style="text-align: right;"><c:out
									value="${order.product.price}" /></td>
							<td style="text-align: right;"><c:out
									value="${order.product.stock.quantity}" /></td>
							<td style="text-align: right;"><c:out
									value="${order.quantity}" /></td>
							<td style="text-align: right;"><c:out
									value="${order.subTotalPoint}" /></td>
							<td style="text-align: right;"><c:out
									value="${order.subTotal}" /></td>
						</tr>
						<c:set var="totalPoint"
							value="${totalPoint + order.subTotalPoint}" />
						<c:set var="total" value="${total + order.subTotal}" />
					</c:forEach>
					<tr>
						<th colspan="5">合計金額</th>
						<td style="text-align: right;"><c:out value="${totalPoint}" /></td>
						<td style="text-align: right;"><c:out value="${total}" /></td>
					</tr>
				</table>

			</form>

			</c:if>

		<p>
			<a href="${pageContext.request.contextPath}/mserv">[トップに戻る]</a>
		</p>
		<%-- contents end --%>
	</div>
	<div id="footerArea">
		<small> Copyright YYYY FUJITSU LEARNING MEDIA LIMITED </small>
	</div>
</body>
</html>