<!-- All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited -->
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="headerArea">
	<div style="width: 400px; float: left;">
		<h1 style="font-size: 1.25em; color: #ffffff; vertical-align: middle;">
			<a class="header" href="${pageContext.request.contextPath}/mserv?flag=TOP">A社オンラインショッピング</a>
		</h1>
	</div>
	<div
		style="text-align: right; font-size: 1em; width: 330px; padding: 10px; float: left;">
		<c:if test="${CommonLoginMember != null}">
        <span>ようこそ、${CommonLoginMember.memberName}様</span>
        <br>
        <a class="header" href="${pageContext.request.contextPath}/mserv?flag=B0101LogOut">ログアウト</a>
      </c:if>
      <c:if test="${CommonLoginMember == null}">
      <span>ようこそゲスト様</span>
      <br>
        <a href="${pageContext.request.contextPath}/mserv?flag=CommonLogin">ログイン</a>
        <a href="${pageContext.request.contextPath}/mserv?flag=B0201CheckEmailAction">会員登録</a>
      </c:if>

	</div>
	<div style = "width: 50px; float: left">
	<a href="${pageContext.request.contextPath}/mserv?flag=CommonViewCart"><img src="${pageContext.request.contextPath}/img/Cart.png" style="background-color: white;"
	>
      </a>
      </div>
	<br style="clear: both;">
</div>
