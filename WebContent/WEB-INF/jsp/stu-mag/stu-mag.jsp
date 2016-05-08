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
<script type="text/javascript">
	var no = '${lab.no}';
	var size = '${lab.size}';
</script>
</head>
<body class="easyui-layout" >
	<div title="学生管理" data-options="closable:true" style="padding: 10px">
		<table class="easyui-datagrid" title="学生列表" id="user-table"></table>
	</div>
	<div id="tb" style="height:auto">
		<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">增加学生</a>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/style/js/stu-mag.js"></script>
</body>
</html>


