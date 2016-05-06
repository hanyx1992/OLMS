$('#lab-table').datagrid({
	width:'100%',
	height: 500,
	fitColumns:true,
	singleSelect:true,
	pagination:true,
	url:_rootPath + '/notice/find.do',
	method: 'post',
	loadMsg:'公告信息加载中请稍后……',
	toolbar:"#tb",
	onClickRow:  function(index, rowData) {
		//parent.addTab("实验室详情", _rootPath + "/laboratory/desc.do?no="+rowData.no);
	},
	columns:[[
		{field:'id', hidden:true},
		{field:'title', title:'标题', width:100, sortable:"true", align:'center'},
		{field:'content', title:'正文', width:500, sortable:"true", align:'center'},
		{field:'createDateStr', title:'发布时间', width:80, sortable:"true", align:'center'},
		{field:'expDateStr', title:'过期时间', width:80, sortable:"true", align:'center'},
	]]
});


$(function(){
	$('#lab-table').datagrid('getPager').pagination({
		beforePageText: '第',
		afterPageText: '页，共 {pages} 页',
		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
});