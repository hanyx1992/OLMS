var _exp;

var submitNotice = function() {
	var title = $('input[name="title"]').val();
	var exp = _exp;
	var content = $("#notice-content-ipt").val();
	hyx(_rootPath+"/notice/addNotice.do",{title:title, content:content, exp:exp},function(){
		$.messager.alert('成功', '公告添加成功!', 'info');
		$('#notice-table').datagrid('reload');
		$('form').form('clear');
	});
}

var cancelNotice = function() {
	$('form').form('clear');
}

$('#notice-table').datagrid({
	width:'100%',
	height: 350,
	fitColumns:true,
	singleSelect:true,
	pagination:true,
	url:_rootPath + '/notice/find.do',
	method: 'post',
	loadMsg:'公告信息加载中请稍后……',
	onClickRow:  function(index, rowData) {
		//parent.addTab("实验室详情", _rootPath + "/laboratory/desc.do?no="+rowData.no);
	},
	columns:[[
		{field:'id', hidden:true},
		{field:'title', title:'标题', width:100, align:'center'},
		{field:'content', title:'正文', width:420, align:'center'},
		{field:'createDateStr', title:'发布时间', width:80, sortable:"true", align:'center', sorter:dateSorter},
		{field:'expDateStr', title:'过期时间', width:80, sortable:"true", align:'center',sorter:dateSorter},
		{field:'deletebtn', title:'操作', width:80, align:'center'}
	]]
});


$(function(){
	$("#exp-date-ipt").datebox({
		onSelect: function(date){
			_exp = date.getTime();
		}
	}).datebox('calendar').calendar({
		validator: function(date){
			var now = new Date();
			var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
			var d2 = new Date(now.getFullYear(), now.getMonth()+5, now.getDate());
			return d1<=date && date<=d2;
		}
	});

	
	$('#notice-table').datagrid('getPager').pagination({
		beforePageText: '第',
		afterPageText: '页，共 {pages} 页',
		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
	
	$(document).on('click','.dlt-btn', function(){
		var id = $(this).parents('.datagrid-row').find('[field="id"] .datagrid-cell').html();
		
		$.messager.confirm('删除公告', '您确定要删除这条公告吗?', function(r){
			if (r){
				hyx(_rootPath + "/notice/delNotice.do", {id:id},function(){
					$.messager.alert('成功', '公告删除成功!', 'info');
					$('#notice-table').datagrid('reload');
					$('form').form('clear');
				})
			}
		});
	});
});