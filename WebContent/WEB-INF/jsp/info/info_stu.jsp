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
						<td>无</td>
						<td> </td>
						<td>操作:</td>
						<td><a href="javascript:;" class="easyui-linkbutton" onClick="alertChangePwd();">点击修改密码</a></td>
					</tr>
				</table>
			</form>
			</div>
		</div>
		
		<div class="heigth-div-15"></div>
		
		<table class="easyui-datagrid" title="我的申请记录" id="occupy-table"></table>
		
		<div id="password-window" class="easyui-window" title="修改密码" 
			data-options="closed:true, iconCls:'icon-save', minimizable:false, maximizable:false,
			resizable:false" 
			style="width:280px;height:230px;padding:10px;">
			<table>
				<tr>
					<td>旧的密码:</td>
					<td><input id = "old-pwd-ipt"  class="easyui-textbox" type="password" data-options="" value="" style="width:150px;height:28px"></td>
				</tr>
				<tr>
					<td>新的密码:</td>
					<td><input id = "new-pwd-ipt"  class="easyui-textbox" type="password" data-options="" value="" style="width:150px;height:28px"></td>
				</tr>
				<tr>
					<td>再次输入:</td>
					<td><input id = "con-pwd-ipt"  class="easyui-textbox" type="password" data-options="" value="" style="width:150px;height:28px"></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" class="easyui-linkbutton" onClick="submitPwd();">提交</a>
						<a href="javascript:;" class="easyui-linkbutton" onClick="cancelPwd();">取消</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/style/js/info_stu.js"></script>
</body>
</html>


