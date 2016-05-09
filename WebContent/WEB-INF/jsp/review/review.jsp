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
	<div title="租用审核" data-options="closable:true" style="padding: 10px">
	
		<table class="easyui-datagrid" title="未审批申请" id="review-table"></table>

		<div class="heigth-div-15"></div>
		
		<table class="easyui-datagrid" title="已审批申请" id="occupy-table"></table>
		
		<div id="occupy-window" class="easyui-window" title="拒绝申请" 
			data-options="closed:true, iconCls:'icon-save', minimizable:false, maximizable:false,
			resizable:false" 
			style="width:280px;height:150px;padding:10px;">
			<table>
				<tr>
					<td>拒绝理由:<input id = "review-id-ipt" type="hidden" name="review-id"/></td>
					<td><input id = "occupy-desc-ipt"  class="easyui-textbox" data-options="multiline:true" value="" style="width:150px;height:50px"></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" class="easyui-linkbutton" onClick="refuse();">提交</a>
						<a href="javascript:;" class="easyui-linkbutton" onClick="cancelRefuse();">取消</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/style/js/review.js"></script>
</body>
</html>


