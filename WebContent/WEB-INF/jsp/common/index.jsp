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
<body class="easyui-layout" >
	<div class="easyui-layout" style="width: auto; height: 700px;">
		<div data-options="region:'north'" style="height: 57px; background: #f2f2f2; padding: 1px">
			<img alt="Open Laboratory Management System By Chen Lu" src="<%=request.getContextPath()%>/style/img/logo.jpg">
		</div>
		<div id = "mainMenu" data-options="region:'west'" title="菜单" style="width: 100px; padding: 5px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-picture',size:'large',iconAlign:'top'">首页</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-picture',size:'large',iconAlign:'top'">实验室</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-picture',size:'large',iconAlign:'top'">租用审核</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-shapes',size:'large',iconAlign:'top'">公告管理</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-smartart',size:'large',iconAlign:'top'">教室管理</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'">学生管理</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'">黑名单管理</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'">个人信息</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'">退出</a>
		</div>
		<div id = "mainTabs" class="easyui-tabs" data-options="region:'center',tools:'#tab-tools'" style="width: 100%">
			<div title="首页" data-options="closable:true" style="padding: 10px">
				<p>显示公告</p>
			</div>
		</div>
		<script type="text/javascript" src="<%=request.getContextPath()%>/style/js/common/index.js"></script>
	</div>
</body>
</html>