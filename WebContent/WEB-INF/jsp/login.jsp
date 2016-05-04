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
<body class="easyui-layout login-back-image" >

<div class ="height-div-200"></div>

<div class = "login-div">
	<div class="easyui-panel" title="开放实验室管理系统 - 登录" style="width:290px">
		<div style="padding:5px 40px">
			<form class="login-frm" id="login-frm" method="post" action="verify.do">
				<table>
					<tr>
						<td>用户名:</td>
						<td><input class="easyui-textbox" type="text" name="username"
							data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>密&nbsp;&nbsp;&nbsp;码:</td>
						<td><input class="easyui-textbox" type="password" name="password"
							data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>验证码:</td>
						<td><input class="easyui-textbox" type="text" name="message"
							data-options="required:true"></input></td>
					</tr>
					<tr>
						<td></td>
						<td><img src="checkcode" onClick="javascript:$('table tr td img').attr('src', 'checkcode?t='+new Date());"></td>
					</tr>
				</table>
			</form>
			<div style="text-align: center; padding: 5px">
				<a href="javascript:;" class="easyui-linkbutton" onclick="submitForm()">&nbsp;登&nbsp;录&nbsp;</a>
				<a href="javascript:;" class="easyui-linkbutton" onclick="clearForm()">&nbsp;重&nbsp;置&nbsp;</a>
			</div>
		</div>
	</div>
</div>

<script>
	function submitForm() {
		hyx(_rootPath+"/verify.do", {username: $("input[name='username']").val(), password: $("input[name='password']").val(),
			message: $("input[name='message']").val()}, function() {
				window.location.href = _rootPath + "/main/main.do";
			}, function(){
				$("table tr td img").click();
			});
	}
	function clearForm() {
		$('#login-frm').form('clear');
	}
</script>
</body>
</html>