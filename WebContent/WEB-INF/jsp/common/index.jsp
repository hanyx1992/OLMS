<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Open Laboratory Management System</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/js/easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/css/olms.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/style/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/style/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/style/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body class="easyui-layout" >
	<div class="easyui-layout" style="width: auto; height: 700px;">
		<div data-options="region:'north'">
			Title and logo
			<!-- logo -->
		</div>
		<div data-options="region:'west'" title="菜单" style="width: 100px; padding: 5px;">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-picture',size:'large',iconAlign:'top'">首页</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-picture',size:'large',iconAlign:'top'">租用审核</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-shapes',size:'large',iconAlign:'top'">公告管理</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-smartart',size:'large',iconAlign:'top'">教室管理</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'">学生管理</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'">黑名单管理</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'">个人信息</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'">退出</a>
		</div>
		<div id = "main" class="easyui-tabs" data-options="region:'center',tools:'#tab-tools'" style="width: 100%">
			<div title="Help" data-options="closable:true" style="padding: 10px">
				<table class="easyui-datagrid" title="实验室列表" 
					data-options="singleSelect:true,collapsible:true,url:'datagrid_data1.json',method:'get'">
					<thead>
						<tr>
							<th data-options="field:'itemid',width:100,align:'center'">编号</th>
							<th data-options="field:'productid',width:150,align:'center'">名称</th>
							<th data-options="field:'listprice',width:250,align:'center'">位置</th>
							<th data-options="field:'unitcost',width:400,align:'center'">描述</th>
							<th data-options="field:'status',width:150,align:'center'">状态</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		
	</div>
	<script>
		
	</script>
</body>
</html>