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
</script>
</head>
<body class="easyui-layout" >
	<div title="实验室详情" data-options="closable:true" style="padding: 10px">
		
		<div class="easyui-panel" title="基本信息" style="width:100%">
			<div style="padding:10px 60px 20px 60px">
		    <form id="mainForm" method="post">
				<table style="text-align:center;table-layout:fixed;width:80%">
					<tr>
						<td>编号:</td>
						<td>${lab.no}</td>
						<td> </td>
						<td>名称:</td>
						<td>${lab.name}</td>
					</tr>
					<tr>
						<td>位置:</td>
						<td>${lab.location}</td>
						<td> </td>
						<td>可容纳人数:</td>
						<td>${lab.size}</td>
					</tr>
					<tr>
						<td>描述:</td>
						<td style="text-align:left" colspan="4">${lab.desc}</td>
					</tr>
				</table>
			</form>
			</div>
		</div>
		
		<div class="heigth-div-15"></div>
		
		<div class="easyui-panel" title="预占信息" style="width:100%">
			<div style="padding:10px 60px 20px 60px">
		    <form id="scheduleForm" method="post">
		    	<table style="text-align:center;table-layout:fixed;width:80%">
		    		<tr>
		    			<td colspan="2" style="text-align:left"><a href="javascript:;" onClick="lastWeek();" class="easyui-linkbutton">&nbsp;上一周&nbsp;</a></td>
		    			<td></td>
		    			<td colspan="4" id="date-label">${startDate} ~ ${endDate}</td>
		    			<td></td>
		    			<td colspan="2" style="text-align:right"><a href="javascript:;" onClick="nextWeek();" class="easyui-linkbutton">&nbsp;下一周&nbsp;</a></td>
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
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>第二大节</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>第三大节</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>第四大节</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<c:if test="${num>4}">
					<tr>
						<td>第五大节</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					</c:if>
					<c:if test="${num>5}">
					<tr>
						<td>第六大节</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					</c:if>
				</table>
			</form>
			</div>
		</div>
		
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/style/js/laboratory-desc.js"></script>
</body>
</html>


