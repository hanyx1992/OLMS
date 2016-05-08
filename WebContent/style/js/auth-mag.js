var cancelAuth = function() {
	$('#auth-window').window('close');
};
var submitAuth = function() {
	var auths = '';
	$("#auth-table").find("input[type=checkbox]:checked").each(function(){
		auths += $(this).attr("name") + ","; 
	});
	
	$('#auth-window').window('close');
	
	var id = $("#auth-user-id").val(); 
	hyx(_rootPath+"/auth-mag/setAuth.do", {id:id, auths:auths}, function(data) {
		$('#user-table').datagrid('reload');
	})
};

$('#user-table').datagrid({
	width:'100%',
	height: 500,
	fitColumns:true,
	singleSelect:true,
	pagination:true,
	idField:'userid',
	url:_rootPath + '/auth-mag/find.do',
	method: 'post',
	loadMsg:'用户信息加载中请稍后……',
	columns:[[
		{field:'loginName', title:'登录名', width:100, align:'center'},
		{field:'realName', title:'姓名', width:100, align:'center'},
		{field:'roleStr', title:'角色', width:100, align:'center'},
		{field:'authStr', title:'拥有权限', width:400, align:'center'},
		{field:'blkStateStr', title:'状态', width:100, align:'center'},
		{field:'action', title:'操作', width:150, align:'center', 
			formatter:function(value,row,index){
				if (row.role !=0) {
					if (row.blkStateStr == '正常') {
						return '<a href="javascript:;" class="easyui-linkbutton edit-btn">编辑权限</a> <a href="javascript:;" class="easyui-linkbutton blk-btn">拉黑</a>';
					} else {
						return '<a href="javascript:;" class="easyui-linkbutton cancelblk-btn">移除黑名单</a>'
					} 
				} else {
					return '-';
				}
			}
		}
	]]
});

$(function(){
	$('#user-table').datagrid('getPager').pagination({
		beforePageText: '第',
		afterPageText: '页，共 {pages} 页',
		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
	

	$(document).on('click','.edit-btn', function(){
		var id = $(this).parents('.datagrid-row').find('[field="loginName"] .datagrid-cell').html();
		hyx(_rootPath + "/auth-mag/getAuth.do", {id:id}, function(data){
			var auths = data.auths;
			var authdata = data.authdata;
			$('#auth-table').html('');
			var divhtml = '<tr><td><input id="auth-user-id" name="auth-user-id" type="hidden"/></td></tr>';
			for (var key in authdata) {
				divhtml +=
				'<tr><td><input id="'+key+'" name="'+key+'" keyStr="'+authdata[key]+'"type="checkbox"/><label for="'+key+'"> '+authdata[key]+'</label></td></tr>';
			}
			
			$('#auth-table').html(divhtml);
			
			if (auths != null) {
				for (var i = 0; i < auths.length; i++) {
					var key = auths[i];
					$('#'+key).attr('checked', 'checked');
				}
			}
			
			$('#auth-user-id').val(id);
			$('#auth-window').window('open');
		})
	});
	
	$(document).on('click','.cancelblk-btn', function(){
		var id = $(this).parents('.datagrid-row').find('[field="loginName"] .datagrid-cell').html();
		$.messager.confirm('取消拉黑', '您确定要将该用户从黑名单中移除吗?', function(r){
			if (r){
				hyx(_rootPath + "/auth-mag/cancelblk.do", {id:id}, function(data){
					$('#user-table').datagrid('reload');
				});
			}
		});

	});
	
	$(document).on('click','.blk-btn', function(){
		var id = $(this).parents('.datagrid-row').find('[field="loginName"] .datagrid-cell').html();
		$.messager.confirm('加入黑名单', '您确定要将该用户加入黑名单吗?', function(r){
			if (r){
				hyx(_rootPath + "/auth-mag/blk.do", {id:id}, function(data){
					$('#user-table').datagrid('reload');
				});
			}
		});
	});
});