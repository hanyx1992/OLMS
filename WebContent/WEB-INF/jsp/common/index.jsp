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
	<div class="easyui-layout" style="width: auto; height: 800px;">
		<div data-options="region:'north'" style="height: 57px; background: #f2f2f2; padding: 1px">
			<img alt="Open Laboratory Management System By Chen Lu" src="<%=request.getContextPath()%>/style/img/logo.jpg">
		</div>
		<div id = "mainMenu" data-options="region:'west'" title="菜单" style="width: 100px; padding: 5px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-picture',size:'large',iconAlign:'top'" val = "main" title = "首页">首页</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-picture',size:'large',iconAlign:'top'" val = "laboratory" title = "实验室">实验室</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-picture',size:'large',iconAlign:'top'" val = "review" title = "租用审核">租用审核</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-shapes',size:'large',iconAlign:'top'" val = "notice" title = "公告管理">公告管理</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-smartart',size:'large',iconAlign:'top'" val = "lab-mag" title = "教室管理">教室管理</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'" val = "stu-mag" title = "学生管理">学生管理</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'" val = "tea-mag" title = "教师管理">教师管理</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'" val = "blk-mag" title = "黑名单管理">黑名单管理</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'" val =  "info" title = "个人信息">个人信息</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'" val = "exit" title = "退出">退出</a>
		</div>
		<div id = "mainTabs" class="easyui-tabs" data-options="region:'center',tools:'#tab-tools'" style="width: 100%"></div>
		<script type="text/javascript" src="<%=request.getContextPath()%>/style/js/common/index.js"></script>
	</div>
</body>
</html>