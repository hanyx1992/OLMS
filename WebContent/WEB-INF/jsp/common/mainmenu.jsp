<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id = "mainMenu" data-options="region:'west'" title="菜单" style="width: 100px; padding: 5px;">
	<c:forEach items="${auths}" var="m">
		<c:if test="${m eq 'main'}">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-picture',size:'large',iconAlign:'top'" val = "main" title = "首页">首页</a>
		</c:if>
		<c:if test="${m=='laboratory'}">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-picture',size:'large',iconAlign:'top'" val = "laboratory" title = "实验室">实验室</a>
		</c:if>
		<c:if test="${m=='review'}">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-picture',size:'large',iconAlign:'top'" val = "review" title = "租用审核">租用审核</a>
		</c:if>
		<c:if test="${m=='notice'}">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-shapes',size:'large',iconAlign:'top'" val = "notice" title = "公告管理">公告管理</a>
		</c:if>
		<c:if test="${m=='lab-mag'}">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-smartart',size:'large',iconAlign:'top'" val = "lab-mag" title = "教室管理">教室管理</a>
		</c:if>
		<c:if test="${m=='stu-mag'}">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'" val = "stu-mag" title = "学生管理">学生管理</a>
		</c:if>
		<c:if test="${m=='tea-mag'}">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'" val = "tea-mag" title = "教师管理">教师管理</a>
		</c:if>
		<c:if test="${m=='blk-mag'}">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'" val = "blk-mag" title = "黑名单管理">黑名单管理</a>
		</c:if>
	</c:forEach>
	<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'" val =  "info" title = "个人信息">个人信息</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100%" data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'" val = "exit" title = "退出">退出</a>
</div>
