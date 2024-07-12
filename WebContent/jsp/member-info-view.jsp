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
		<h1>会員情報照会</h1>
		<%-- contents start --%>
		いつもご利用いただきありがとうございます。<br> 現在のポイント数は
		<c:out value="${CommonLoginMember.memberPoint}" />
		ポイントです。

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

		<h2>注文情報</h2>

		<c:if test="${empty orderList}">
			<c:out value="${message}" />
		</c:if>

		<c:if test="${!empty orderList}">
			<table border="1">
				<tr bgcolor="#9999ff">
					<th>注文番号</th>
					<th>日付</th>
					<th>商品ID</th>
					<th>商品名</th>
					<th>単価</th>
					<th>数量</th>
					<th>ポイント</th>
					<th>小計金額</th>
				</tr>
				<c:forEach var="order" items="${orderList}" varStatus="status">
					<tr>
						<td><c:out value="${order.orderId}" /></td>
						<td><c:out value="${order.orderDate}" /></td>
						<td><c:out value="${order.product.productId}" /></td>
						<td><c:out value="${order.product.productName}" /></td>
						<td style="text-align: right;"><c:out
								value="${order.product.price}" /></td>
						<td style="text-align: right;"><c:out
								value="${order.quantity}" /></td>
						<td style="text-align: right;"><c:out
								value="${order.subTotalPoint}" /></td>
						<td style="text-align: right;"><c:out
								value="${order.subTotal}" /></td>
					</tr>
					<c:set var="total" value="${total + order.subTotal}" />
				</c:forEach>
				<tr>
					<th colspan="7">合計金額</th>
					<td style="text-align: right;"><c:out value="${total}" /></td>
				</tr>
			</table>
		</c:if>

		<br> <br> <a
			href="${pageContext.request.contextPath}/mserv?flag=XXXX">[会員情報更新]</a>&nbsp;&nbsp;
		<a href="${pageContext.request.contextPath}/mserv">[トップに戻る]</a>

		<%-- contents end --%>
	</div>
	<div id="footerArea">
		<small>Copyright YYYY FUJITSU LEARNING MEDIA LIMITED</small>
	</div>
</body>
</html>