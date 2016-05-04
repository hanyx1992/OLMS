<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Open Laboratory Management System</title>
	<jsp:include page="common/commonHeader.jsp"></jsp:include>
</head>
<body class="easyui-layout" >

<script>
	$(function(){
		$.messager.alert('发生错误啦!', '你并没有权限这么做!', 'error');
	})
</script>
</body>
</html>