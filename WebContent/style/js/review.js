$('#review-table').datagrid({
	width:'100%',
	height: 300,
	fitColumns:true,
	singleSelect:true,
	pagination:true,
	url:_rootPath + '/review/findReview.do',
	method: 'post',
	loadMsg:'申请信息加载中请稍后……',
	columns:[[
		{field:'id', hidden:true},
		{field:'reviewState', hidden:true},
		{field:'labName', title:'实验室名称', width:100, align:'center'},
		{field:'realName', title:'申请人', width:50, align:'center'},
		{field:'num', title:'申请人数', width:50, align:'center'},
		{field:'dateStr', title:'预占时间', width:80, align:'center'},
		{field:'subjectStr', title:'课程', width:80, align:'center'},
		{field:'desc', title:'描述', width:200, align:'center'},
		{field:'createDateStr', title:'提交时间', width:80, align:'center'},
		{field:'action', title:'操作', width:80, align:'center',
			formatter:function(value,row,index){
				if (row.reviewState == 0) {
					return "<a href='javascript:;' class='ok-btn'>通过</a> " + 
						"<a href='javascript:;' class='no-btn'>拒绝</a>"
				}
			}}
	]]
});
$('#occupy-table').datagrid({
	width:'100%',
	height: 300,
	fitColumns:true,
	singleSelect:true,
	pagination:true,
	url:_rootPath + '/review/findOccupy.do',
	method: 'post',
	loadMsg:'申请信息加载中请稍后……',
	columns:[[
  		{field:'id', hidden:true},
		{field:'reviewState', hidden:true},
		{field:'labName', title:'实验室名称', width:100, align:'center'},
		{field:'realName', title:'申请人', width:50, align:'center'},
		{field:'num', title:'申请人数', width:50, align:'center'},
		{field:'dateStr', title:'预占时间', width:80, align:'center'},
		{field:'subjectStr', title:'课程', width:80, align:'center'},
		{field:'desc', title:'描述', width:200, align:'center'},
		{field:'createDateStr', title:'提交时间', width:80, align:'center'},
		{field:'reviewUser', title:'审核人', width:80, align:'center'},
		{field:'reviewDateStr', title:'审核时间', width:80, align:'center'},
		{field:'stateStr', title:'审核状态', width:80, align:'center'},
		{field:'reviewDesc', title:'审核描述', width:200, align:'center'}
	]]
});

var refuse = function() {
	$("#occupy-window").window('close');

	var id = $("#review-id-ipt").val();
	var desc = $("#occupy-desc-ipt").val();
	
	$.messager.confirm('通过', '您确定要拒绝这条申请吗?', function(r){
		if (r){
			hyx(_rootPath + "/review/no.do", {id:id, desc:desc},function(){
				$.messager.alert('成功', '拒绝成功!', 'info');
				$('#review-table').datagrid('reload');
				$('#occupy-table').datagrid('reload');
			})
		}
	});
	
};

var cancelRefuse = function() {
	$("#occupy-window").window('close');
	$("#occupy-desc-ipt").val('');
}


$(function(){
	$('#review-table').datagrid('getPager').pagination({
		beforePageText: '第',
		afterPageText: '页，共 {pages} 页',
		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
	
	$('#occupy-table').datagrid('getPager').pagination({
		beforePageText: '第',
		afterPageText: '页，共 {pages} 页',
		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
	
	$(document).on('click','.ok-btn', function(){
		var id = $(this).parents('.datagrid-row').find('[field="id"] .datagrid-cell').html();
		
		$.messager.confirm('通过', '您确定要批准这条申请吗?', function(r){
			if (r){
				hyx(_rootPath + "/review/ok.do", {id:id},function(){
					$.messager.alert('成功', '批准成功!', 'info');
					$('#review-table').datagrid('reload');
					$('#occupy-table').datagrid('reload');
				})
			}
		});
	});
	
	$(document).on('click','.no-btn', function(){
		var id = $(this).parents('.datagrid-row').find('[field="id"] .datagrid-cell').html();
		$("#review-id-ipt").val(id);
		$("#occupy-window").window('open');
	});
});