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
	<div title="公告管理" data-options="closable:true" style="padding: 10px">
		<div class="easyui-panel" title="添加公告" style="width:100%">
			<form>
			<table cellSpacing='5px' cellPadding='5px' style="text-align:center;width:60%">
				<tr>
					<td width="10%">公告标题:</td>
					<td width="40%"><input class="easyui-textbox" type="text" name="title" data-options="required:true" style ="width:100%;height:28px"></input></td>
					<td width="10%">失效时间:</td>
					<td width="40%"><input id = "exp-date-ipt" style ="width:100%;height:28px"></input></td>
				</tr>
				<tr>
					<td>公告内容:</td>
					<td colspan="3"><input id = "notice-content-ipt"  class="easyui-textbox" data-options="multiline:true,required:true" value="" style="width:100%;height:100px"></td>
				</tr>
				<tr>
					<td colspan="4" style="text-algn:left">
						<a style ="width:30%;height:28px" href="javascript:;" class="easyui-linkbutton" onClick="submitNotice();">提交</a>
						<a style ="width:30%;height:28px" href="javascript:;" class="easyui-linkbutton" onClick="cancelNotice();">清空</a>
					</td>
				</tr>
			</table>
			</form>
		</div>
		
		<div class="heigth-div-15"></div>
		
		<table class="easyui-datagrid" title="公告列表" id="notice-table"></table>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/style/js/notice.js"></script>
</body>
</html>


