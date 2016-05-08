var editIndex = undefined;
function endEditing(){
	if (editIndex == undefined){return true}
	if ($('#user-table').datagrid('validateRow', editIndex)){
		$('#user-table').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}
function updateActions(index){
	$('#user-table').datagrid('updateRow',{
		index: index,
		row:{}
	});
}
function getRowIndex(target){
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}
function editrow(target){
	$('#user-table').datagrid('beginEdit', getRowIndex(target));
	//在编辑的时候id不可以编辑,修改的时候可以
	$('.datagrid-editable-input:eq(0)').attr("disabled","disabled");
}
function resetPwdRow(target){
	$.messager.confirm('确认','确定要重置该学生的密码为\"123456\"?',function(r){
		if (r){
			$('#user-table').datagrid('selectRow', getRowIndex(target));
			var row = $('#user-table').datagrid('getSelected');
			hyx(_rootPath+"/stu-mag/reset.do",{id:row.userid},
					function(data){
				$.messager.alert('成功', '重置密码成功!', 'info');
				$('#user-table').datagrid('reload');
			})
		}
	});
}
function append(){
	if (endEditing()){
		$('#user-table').datagrid('appendRow',{});
		editIndex = $('#user-table').datagrid('getRows').length-1;
		$('#user-table').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
	}
}
function deleterow(target){
	$.messager.confirm('确认','确定要删除该学生?',function(r){
		if (r){
			$('#user-table').datagrid('selectRow', getRowIndex(target));
			var row = $('#user-table').datagrid('getSelected');
			hyx(_rootPath+"/stu-mag/del.do",{id:row.userid},
					function(data){
				$.messager.alert('成功', '删除成功!', 'info');
				$('#user-table').datagrid('reload');
			})
		}
	});
}
function saverow(target){
	$('#user-table').datagrid('selectRow', getRowIndex(target));
	$('#user-table').datagrid('endEdit', getRowIndex(target));
	var row = $('#user-table').datagrid('getSelected');
	hyx(_rootPath+"/stu-mag/add.do",{id:row.userid,loginName:row.loginName,realName:row.realName,clzName:row.clzName},
			function(data){
		if(data.s) {
			$.messager.alert('成功', '提交成功!', 'info');
		} else {
			$.messager.alert('失败!', data.e, 'error');
		}
		$('#user-table').datagrid('reload');
	})
}
function cancelrow(target){
	$('#user-table').datagrid('cancelEdit', getRowIndex(target));
	if (editIndex != undefined) {
		$('#user-table').datagrid('deleteRow', getRowIndex(target));
		editIndex = undefined
	}
}

$('#user-table').datagrid({
	width:'100%',
	height: 500,
	fitColumns:true,
	singleSelect:true,
	pagination:true,
	idField:'userid',
	url:_rootPath + '/stu-mag/find.do',
	method: 'post',
	loadMsg:'学生信息加载中请稍后……',
	toolbar: '#tb',
	columns:[[
        {field:'userid', hidden:true},
		{field:'loginName', title:'登录名', width:150, align:'center',
			editor:{
				type:'text',
				options:{
					required:true
				}
			}
		},
		{field:'realName', title:'姓名', width:150, align:'center',
			editor:{
				type:'text',
				options:{
					required:true
				}
			}
		},
		{field:'clzName', title:'班级', width:200, align:'center',
			editor:{
				type:'text',
				options:{
					required:true
				}
			}
		},
		{field:'roleStr', title:'角色', width:150, align:'center'},
		{field:'action', title:'操作', width:200, align:'center',
			formatter:function(value,row,index){
				if (row.editing){
					var submit = '<a href="javascript:;" onclick="saverow(this)">提交</a> ';
					var cancel = '<a href="javascript:;" onclick="cancelrow(this)">取消</a>';
					return submit+cancel;
				} else {
					var edit = '<a href="javascript:;" onclick="editrow(this)">编辑</a> ';
					var resetPwd = '<a href="javascript:;" onclick="resetPwdRow(this)">重置密码</a> ';
					var deleteUser = '<a href="javascript:;" onclick="deleterow(this)">删除</a>';
					return edit+resetPwd+deleteUser;
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
	$('#user-table').datagrid('getPager').pagination({
		beforePageText: '第',
		afterPageText: '页，共 {pages} 页',
		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
});