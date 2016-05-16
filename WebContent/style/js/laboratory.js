$('#lab-table').datagrid({
	width:'100%',
	height: 500,
	fitColumns:true,
	singleSelect:true,
	pagination:true,
	url:_rootPath + '/laboratory/find.do',
	method: 'post',
	loadMsg:'实验室信息加载中请稍后……',
	toolbar:"#tb",
	onClickRow:  function(index, rowData) {
		parent.closetab("实验室详情");
		parent.addTab("实验室详情", _rootPath + "/laboratory/desc.do?no="+rowData.no);
	},
	columns:[[
		{field:'no', title:'编号', width:100, sortable:"true", align:'center'},
		{field:'name', title:'名称', width:150, sortable:"true", align:'center'},
		{field:'size', title:'容纳人数', width:80, sortable:"true", align:'center'},
		{field:'location', title:'位置', width:250, sortable:"true", align:'center'},
		{field:'desc', title:'描述', width:370, sortable:"true", align:'center'},
		{field:'stateStr', title:'状态', width:150, sortable:"true", align:'center'}
	]]
});


var doSearch = function() {
	$('#lab-table').datagrid('load',{
		no: $("#no").val(),
		name: $("#name").val(),
		location: $("#location").val()
	});
}

$(function(){
	$('#lab-table').datagrid('getPager').pagination({
		beforePageText: '第',
		afterPageText: '页，共 {pages} 页',
		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
});