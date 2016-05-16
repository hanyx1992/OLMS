$('#occupy-table').datagrid({
	width:'100%',
	height: 400,
	fitColumns:true,
	singleSelect:true,
	pagination:true,
	url:_rootPath + '/info/stuFind.do',
	method: 'post',
	loadMsg:'申请信息加载中请稍后……',
	columns:[[
		{field:'id',hidden:true},
		{field:'labName', title:'实验室名称', width:100, align:'center'},
		{field:'num', title:'申请人数', width:50, align:'center'},
		{field:'dateStr', title:'预占时间', width:80, align:'center'},
		{field:'subjectStr', title:'课程', width:80, align:'center'},
		{field:'desc', title:'描述', width:200, align:'center'},
		{field:'createDateStr', title:'提交时间', width:80, align:'center'},
		{field:'reviewUser', title:'审核人', width:80, align:'center'},
		{field:'reviewDateStr', title:'审核时间', width:80, align:'center'},
		{field:'stateStr', title:'审核状态', width:80, align:'center'},
		{field:'cancelBtn', title:'操作', width:80, align:'center'}
	]]
});

var alertChangePwd = function() {
	$('#password-window').window('open');
}

var cancelPwd = function() {
	$('#password-window').window('close');
};

var submitPwd = function() {
	$('#password-window').window('close');
	
	var oldPwd = $("#old-pwd-ipt").val();
	var newPwd = $("#new-pwd-ipt").val();
	var conPwd = $("#con-pwd-ipt").val();
	
	if (newPwd != conPwd) {
		$('#password-window').window('open');
		$.messager.alert('发生错误啦!', '两次密码输入不一致!', 'error');
		return false;
	}
	
	hyx(_rootPath + "/info/pwd.do", {oldPwd:oldPwd, newPwd : newPwd},
			function(data){
				$.messager.alert('成功', '修改密码成功!', 'info');
	});
}

$(function(){
	$('#occupy-table').datagrid('getPager').pagination({
		beforePageText: '第',
		afterPageText: '页，共 {pages} 页',
		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
	
	$(document).on('click','.dlt-btn', function(){
		var id = $(this).parents('.datagrid-row').find('[field="id"] .datagrid-cell').html();
		
		$.messager.confirm('撤销', '您确定要撤销这条申请吗?', function(r){
			if (r){
				hyx(_rootPath + "/info/cancelOccupy.do", {id:id},function(){
					$.messager.alert('成功', '申请撤销成功!', 'info');
					$('#occupy-table').datagrid('reload');
					$('form').form('clear');
				})
			}
		});
	});
});