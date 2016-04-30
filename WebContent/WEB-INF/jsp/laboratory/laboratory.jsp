<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Open Laboratory Management System</title>
<jsp:include page="../common/commonHeader.jsp"></jsp:include>
</head>
<body class="easyui-layout" >
	<div title="实验室" data-options="closable:true" style="padding: 10px">
		<table class="easyui-datagrid" title="实验室列表" id="lab-table"></table>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/style/js/laboratory.js"></script>
</body>
</html>


