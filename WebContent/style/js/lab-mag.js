var editIndex = undefined;
function endEditing(){
	if (editIndex == undefined){return true}
	if ($('#lab-table').datagrid('validateRow', editIndex)){
		$('#lab-table').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}
function updateActions(index){
	$('#lab-table').datagrid('updateRow',{
		index: index,
		row:{}
	});
}
function getRowIndex(target){
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}
function editrow(target){
	$('#lab-table').datagrid('beginEdit', getRowIndex(target));
	//在编辑的时候id不可以编辑,修改的时候可以
	$('.datagrid-editable-input:eq(0)').attr("disabled","disabled");
}
function resetRow(target){
	$('#lab-table').datagrid('selectRow', getRowIndex(target));
	var row = $('#lab-table').datagrid('getSelected');
	var labid = row.no;
	var state = row.state == 0?"暂时关闭":"启用";
	$.messager.confirm('确认','确定要?'+state+'这个实验室?',function(r){
		if (r){
			hyx(_rootPath+"/lab-mag/reset.do",{id:labid},
					function(data){
				$.messager.alert('成功', state+'实验室成功!', 'info');
				$('#lab-table').datagrid('reload');
			})
		}
	});
}
function append(){
	if (endEditing()){
		$('#lab-table').datagrid('appendRow',{});
		editIndex = $('#lab-table').datagrid('getRows').length-1;
		$('#lab-table').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
	}
}
function deleterow(target){
	$.messager.confirm('确认','确定要删除该实验室?所有预占信息都将失效!',function(r){
		if (r){
			$('#lab-table').datagrid('selectRow', getRowIndex(target));
			var row = $('#lab-table').datagrid('getSelected');
			hyx(_rootPath+"/lab-mag/del.do",{id:row.no},
					function(data){
				$.messager.alert('成功', '删除成功!', 'info');
				$('#lab-table').datagrid('reload');
			})
		}
	});
}
function saverow(target){
	$('#lab-table').datagrid('selectRow', getRowIndex(target));
	$('#lab-table').datagrid('endEdit', getRowIndex(target));
	var row = $('#lab-table').datagrid('getSelected');
	hyx(_rootPath+"/lab-mag/add.do",{id:row.labid, no:row.no,name:row.name,size:row.size,location:row.location,desc:row.desc},
			function(data){
		if(data.s) {
			$.messager.alert('成功', '提交成功!', 'info');
		} else {
			$.messager.alert('失败!', data.e, 'error');
		}
		$('#lab-table').datagrid('reload');
	})
}
function cancelrow(target){
	$('#lab-table').datagrid('cancelEdit', getRowIndex(target));
	if (editIndex != undefined) {
		$('#lab-table').datagrid('deleteRow', getRowIndex(target));
		editIndex = undefined
	}
}

$('#lab-table').datagrid({
	width:'100%',
	height: 500,
	fitColumns:true,
	singleSelect:true,
	pagination:true,
	idField:'no',
	url:_rootPath + '/lab-mag/find.do',
	method: 'post',
	loadMsg:'实验室信息加载中请稍后……',
	toolbar: '#tb',
	columns:[[
        {field:'labid', hidden:true},
        {field:'no', title:'编号', width:80, align:'center', editor:'text'},
		{field:'name', title:'名称', width:100, align:'center', editor:'text'},
		{field:'size', title:'容纳人数', width:80, align:'center', editor:'numberbox'},
		{field:'location', title:'位置', width:150, align:'center', editor:'text'},
		{field:'desc', title:'描述', width:220, align:'center', editor:'text'},
		{field:'stateStr', title:'状态', width:80, align:'center'},
		{field:'state', hidden:true},
		{field:'action', title:'操作', width:200, align:'center',
			formatter:function(value,row,index){
				var state = row.state == 0?"暂时关闭":"启用";
				if (row.editing){
					var submit = '<a href="javascript:;" onclick="saverow(this)">提交</a> ';
					var cancel = '<a href="javascript:;" onclick="cancelrow(this)">取消</a>';
					return submit+cancel;
				} else {
					var edit = '<a href="javascript:;" onclick="editrow(this)">编辑</a> ';
					var reset = '<a href="javascript:;" onclick="resetRow(this)">'+state+ '</a> ';
					var deleteUser = '<a href="javascript:;" onclick="deleterow(this)">删除</a>';
					return edit+reset+deleteUser;
				}
			}}
	]],
	onBeforeEdit:function(index,row){
		row.editing = true;
		updateActions(index);
	},
	onAfterEdit:function(index,row){
		row.editing = false;
		updateActions(index);
	},
	onCancelEdit:function(index,row){
		row.editing = false;
		updateActions(index);
	}
});

$(function(){
	$('#lab-table').datagrid('getPager').pagination({
		beforePageText: '第',
		afterPageText: '页，共 {pages} 页',
		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
});