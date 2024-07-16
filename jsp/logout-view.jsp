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
	</style>
</head>
<body>
	<jsp:include page="/jsp/header.jsp" />
	<div id="mainArea">
		<h1>ログアウト</h1>

		<%-- contents start --%>

				<div id="target" style="color: red;">
			<c:out value="${message}" />
		</div>

		<p>ご来店ありがとうございました！</p>
		<p></p>
		<p></p>
		<p></p>
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