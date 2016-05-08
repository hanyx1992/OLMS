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
	<div title="权限和黑名单" data-options="closable:true" style="padding: 10px">
		<table class="easyui-datagrid" title="权限和黑名单" id="user-table"></table>

		<div id="auth-window" class="easyui-window" title="编辑权限" 
				data-options="closed:true, iconCls:'icon-save', minimizable:false, maximizable:false,
				resizable:false" 
				style="width:160px;height:230px;padding:10px;">
				<table id = "auth-table">
				
				</table>
				<table>
					<tr>
						<td>
							<a href="javascript:;" class="easyui-linkbutton" onClick="submitAuth();">提交</a>
							<a href="javascript:;" class="easyui-linkbutton" onClick="cancelAuth();">取消</a>
						</td>
					</tr>
				</table>
		</div>
	</div>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/style/js/auth-mag.js"></script>
</body>
</html>


