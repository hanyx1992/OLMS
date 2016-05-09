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
	var no = '${OLMS_USER_INFO.clzName}';
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
						<td>我的教室:</td>
						<td>${OLMS_USER_INFO.clzName}${clzLabName}</td>
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
		
		
		
		<div class="easyui-panel" title="我的课表" style="width:100%">
		<c:if test = "${hasClass}">
			<div style="padding:10px 60px 20px 60px">
			<table style="text-align:center;table-layout:fixed;width:80%">
	    		<tr>
	    			<td>我的授课教室</td>
	    		</tr>
			    <tr>
					<td>${OLMS_USER_INFO.clzName}${clzLabName}</td>
	    		</tr>
		    </table>
			<table class="schedule-table">
					<tr>
						<td>-</td>
						<td>周一</td>
						<td>周二</td>
						<td>周三</td>
						<td>周四</td>
						<td>周五</td>
						<td>周六</td>
						<td>周日</td>
					</tr>
					<tr>
						<td>第一大节</td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
					</tr>
					<tr>
						<td>第二大节</td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
					</tr>
					<tr>
						<td>第三大节</td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
					</tr>
					<tr>
						<td>第四大节</td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
					</tr>
					<c:if test="${num>4}">
					<tr>
						<td>第五大节</td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
					</tr>
					</c:if>
					<c:if test="${num>5}">
					<tr>
						<td>第六大节</td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
						<td class="a-td"></td>
					</tr>
					</c:if>
				</table>
				<table style="color:#acacac ;text-align:center;table-layout:fixed;width:80%">
				    <tr>
						<td>点击空白可以添加课程,点击已有的课程可以删除课程</td>
		    		</tr>
			    </table>			
				</div>
		</c:if>
		</div>
		
		
		<div id="schedult-window" class="easyui-window" title="添加课程" 
			data-options="closed:true, iconCls:'icon-save', minimizable:false, maximizable:false,
			resizable:false" 
			style="width:280px;height:200px;padding:10px;">
			<table>
				<tr>
					<td>班级:<input id = "day-ipt" type="hidden" name="day-ipt"/><input id = "num-ipt" type="hidden" name="num-ipt"/></td>
					<td><input id = "clzName-ipt"  class="easyui-textbox" data-options="" value="" style="width:150px;height:28px"></td>
				</tr>
				<tr>
					<td>备注:</td>
					<td><input id = "desc-ipt"  class="easyui-textbox" data-options="multiline:true" value="" style="width:150px;height:50px"></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:;" class="easyui-linkbutton" onClick="submitAdd();">提交</a>
						<a href="javascript:;" class="easyui-linkbutton" onClick="cancelAdd();">取消</a>
					</td>
				</tr>
			</table>
		</div>
		
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/style/js/info-tea.js"></script>
</body>
</html>


