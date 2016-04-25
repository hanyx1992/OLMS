$('#lab-table').datagrid({
	width:'100%',
	height: 500,
	fitColumns:true,
	singleSelect:true,
	pagination:true,
	url:_rootPath + '/laboratory/find.do',
	method: 'post',
	loadMsg:'实验室信息加载中请稍后……',
	columns:[[
//		{field:'content',hidden:true},
		{field:'no', title:'编号', width:100, align:'center'},
		{field:'name', title:'名称', width:150, align:'center'},
		{field:'location', title:'位置', width:250, align:'center'},
		{field:'desc', title:'描述', width:400, align:'center'},
		{field:'stateStr', title:'状态', width:150, align:'center'}
	]]
});

$(function(){
	$('#lab-table').datagrid('getPager').pagination({
		beforePageText: '第',
		afterPageText: '页，共 {pages} 页',
		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
});