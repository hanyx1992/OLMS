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
	<div title="我的" data-options="closable:true" style="padding: 10px">
		
		<div class="easyui-panel" title="基本信息" style="width:100%">
			<div style="padding:10px 60px 20px 60px">
		    <form id="mainForm" method="post">
				<table style="text-align:center;table-layout:fixed;width:80%">
					<tr>
						<td>登录名:</td>
						<td>${OLMS_USER_INFO.loginName}</td>
						<td> </td>
						<td>姓名:</td>
						<td>${OLMS_USER_INFO.realName}</td>
					</tr>
					<tr>
						<td>班级:</td>
						<td>${OLMS_USER_INFO.clzName}</td>
						<td> </td>
						<td>最后登录:</td>
						<td>${OLMS_USER_INFO.lastLoginDateStr}</td>
					</tr>
					<tr>
						<td>描述:</td>
						<td style="text-align:left" colspan="4">无</td>
					</tr>
				</table>
			</form>
			</div>
		</div>
		
		<div class="heigth-div-15"></div>
		
		<table class="easyui-datagrid" title="我的申请记录" id="occupy-table"></table>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/style/js/info_stu.js"></script>
</body>
</html>


