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
	<div class="easyui-layout" style="width: auto; height: 800px;">
		<div data-options="region:'north'" style="height: 57px; background: #f2f2f2; padding: 1px">
			<img alt="Open Laboratory Management System By Chen Lu" src="<%=request.getContextPath()%>/style/img/logo.jpg">
		</div>
		<jsp:include page="mainmenu.jsp"></jsp:include>
		<div id = "mainTabs" class="easyui-tabs" data-options="region:'center',tools:'#tab-tools'" style="width: 100%"></div>
		<script type="text/javascript" src="<%=request.getContextPath()%>/style/js/common/index.js"></script>
	</div>
</body>
</html>