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
<body>
<div>
	<div style="height: 100%; background: #f2f2f2; padding: 1px;">
		<img alt="Open Laboratory Management System By Chen Lu" src="<%=request.getContextPath()%>/style/img/logo.jpg">
	</div>
  <div  style="float:right;margin:0 0 0 -110px; width:100%;">
    <div  style="margin:0 0 0 110px; background:#f2f2f2; border:1px solid #dddddd;">
		<div id = "mainTabs" class="easyui-tabs" data-options="tools:'#tab-tools',height:'670px'" style="width: 100%;"></div>
    </div>
  </div>
  <div  style="float:left;width:100px;border:1px solid #dddddd;">
		<jsp:include page="mainmenu.jsp"></jsp:include>
  </div>
</div>

</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/style/js/common/index.js"></script>
</html>