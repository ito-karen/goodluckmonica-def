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
		<h1>商品検索</h1>

		<%-- contents start --%>
			<div style="text-align: left; float: left;">
		<p>
			<input type="hidden" name="flag" value="">
		</p>
		<c:if test="${not empty categoryList}">
		<c:forEach var="category" items="${categoryList}">
        <a href='${pageContext.request.contextPath}/mserv?flag=B0101ShowCategory&categoryId=<c:out value="${category.categoryId}"/>'>
            <img src='${pageContext.request.contextPath}/img/<c:out value="${category.picture}"/>'>
            <c:out value="${category.categoryName}"/>
        </a>
        <p></p>
    </c:forEach>
    </c:if>
	</div>
	</div>
		<%-- contents end --%>
	<div id="footerArea"style="clear: both; margin-top: 20px;">
		<small> Copyright YYYY FUJITSU LEARNING MEDIA LIMITED </small>
	</div>
</body>
</html>