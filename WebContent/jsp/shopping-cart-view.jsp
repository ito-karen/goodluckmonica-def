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
	<jsp:include page="/jsp/header.jsp" />
	<div id="mainArea">
		<h1>ショッピングカート</h1>

		<%-- contents start --%>

		<div id="target" style="color: red;">
			<c:out value="${message}" />
		</div>

		<c:if test="${!empty B01ShoppingCart}">

			<form method="get" action="${pageContext.request.contextPath}/mserv">

				<table border="1" id="cart">
					<tr>
						<th>商品ID</th>
						<th>商品名</th>
						<th>単価</th>
						<th>在庫</th>
						<th>数量</th>
						<th>ポイント</th>
						<th>小計金額</th>
						<th></th>
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
							<td><select name="productQty" id="productQty${status.index}">
									<c:forEach var="count" begin="1" end="10">
										<option value="${count}"
											<c:if test="${order.quantity == count}">selected</c:if>>
											<c:out value="${count}" />
										</option>
									</c:forEach>
							</select></td>
							<td style="text-align: right;"><c:out
									value="${order.subTotalPoint}" /></td>
							<td style="text-align: right;"><c:out
									value="${order.subTotal}" /></td>
							<td><a
								href='${pageContext.request.contextPath}/mserv?flag=B0102DeleteFromCart&deleteProductId=<c:out value="${order.product.productId}"/>'>
									<img src="img/yameru.gif" border="0">
							</a></td>
						</tr>
						<c:set var="totalPoint"
							value="${totalPoint + order.subTotalPoint}" />
						<c:set var="total" value="${total + order.subTotal}" />
					</c:forEach>
					<tr>
						<th colspan="5">合計金額</th>
						<td style="text-align: right;"><c:out value="${totalPoint}" /></td>
						<td style="text-align: right;"><c:out value="${total}" /></td>
						<td></td>
					</tr>
				</table>

				<p>
					<input type="hidden" name="flag" value="">
				</p>
				<p>数量を変更する場合は、数量更新ボタンを押してください。</p>
				<p>
					<input type="submit" value="数量更新"
						onclick="this.form.flag.value='B0102UpdateCart'"> <input
						type="submit" value="レジに進む"
						onclick="this.form.flag.value='B0102GoShopping'">
				</p>
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