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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loginInputCheck.js"></script>
</head>
<body>
	<jsp:include page="/jsp/header-non-menu.jsp" />
	<div id="mainArea">
		<h1>ログイン</h1>
		<%-- contents start --%>

		<div id="target" style="color: red;">
			<c:forEach var="errorMessage" items="${errorMessageList}" varStatus="status">
				<p>
					<c:out value="${errorMessage}" />
				</p>
			</c:forEach>
		</div>

		<form method="post" action="${pageContext.request.contextPath}/mserv" id="chkForm">
			<table>
				<tr>
					<td>会員ID</td>
					<td><input type="text" name="memberId" id="memberId" size="10"></td>
				</tr>
				<tr>
					<td>パスワード</td>
					<td><input type="password" name="password" id="password"
						size="10"></td>
				</tr>
			</table>
			<br> <input type="hidden" name="flag" value="B0202LoginMember">
			<input type="submit" value="ログイン">
		</form>

		<p>
			<a href="${pageContext.request.contextPath}/mserv">[トップに戻る]</a>
		</p>
		<%-- contents end --%>
	</div>
	<div id="footerArea">
		<small>Copyright YYYY FUJITSU LEARNING MEDIA LIMITED</small>
	</div>
</body>
</html>