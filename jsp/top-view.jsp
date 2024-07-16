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
.category-image {
width: 100px; /* 画像の幅 */
height: 100px; /* 画像の高さ */
}
  .link{
color: black; /* リンクの色を黒に設定 */

}
body {
            font-family: Arial, sans-serif;
        }
</style>
</head>
<body>
<jsp:include page="/jsp/header.jsp" />
<div id="mainArea">
<h1>商品検索</h1>
	<%-- contents start --%>
	<p>
		<input type="hidden" name="flag" value="">
	</p>
	<c:if test="${not empty categoryList}">
	<table>
	<c:forEach var="category" items="${categoryList}" varStatus="status">
		<c:if test="${status.first}">
		<tr>
		</c:if>
			<td>
	<div style="background-color:#E5E5E5; padding:20px; border-radius:5px;">
    <a class="link" href='${pageContext.request.contextPath}/mserv?flag=B0101ShowCategory&categoryId=<c:out value="${category.categoryId}"/>'>
        <img class="category-image" src='${pageContext.request.contextPath}/img/<c:out value="${category.picture}"/>'>
        <br><c:out value="${category.categoryName}"/>
    </a>
    <p></p>
                </div>
                </td>
	<c:if test="${(status.count % 3 == 0) && (!status.first)}"><%-- index starts from 0, count starts from 1--%>
		</tr>
		<tr>
	</c:if>
	<c:if test="${status.last}">
		</tr>
	</c:if>

</c:forEach>
</table>
</c:if>
</div>
	<%-- contents end --%>
<div id="footerArea"style="clear: both; margin-top: 20px;">
	<small> Copyright YYYY FUJITSU LEARNING MEDIA LIMITED </small>
</div>

</body>
</html>