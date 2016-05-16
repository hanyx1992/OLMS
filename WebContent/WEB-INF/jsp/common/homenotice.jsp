<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Open Laboratory Management System</title>
<jsp:include page="commonHeader.jsp"></jsp:include>
</head>
<body class="easyui-layout" >

	<div title="首页" data-options="closable:true" style="text-align:center;padding: 10px">
		<c:forEach items="${allNotice}" var="notice" varStatus="i">
			<c:if test="${i.index>0}">
				<div class="heigth-div-15"></div>
			</c:if>
			<div class="easyui-panel" title="${notice.title}" style="width:100%">
				<div style="text-align:center;padding:15px;word-break:break-all; width:85%; overflow:auto;">
					<p>${notice.content}</p>
					<br/>
					<p style="text-align:right;">发布人 : ${notice.userName}</p>
					<p style="text-align:right;">发布时间 : ${notice.createDateStr}</p>
					<p style="text-align:right;">过期时间 : ${notice.expDateStr}</p>
				</div>
			</div>
		</c:forEach>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/style/js/common/homenotice.js"></script>
</body>
</html>