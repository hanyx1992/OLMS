var _default_table = $('.schedule-table').html();

var reload = function() {
	$('.schedule-table').html(_default_table);
	hyx(_rootPath+"/laboratory/schedule.do",{no:no},function(data){
		data = data.sche;
		var hasClz = data.hasClz;
		for (var day = 0; day < hasClz.length; day++) {
			var eachDay = hasClz[day];
			for (var num = 0; num < eachDay.length; num++) {
				var eachHasClz = eachDay[num];
				if (eachHasClz) {
					var clzName = data.clzName[day][num];
					var clzDesc = data.clzDesc[day][num];
					$('.schedule-table tr').eq(num+1).children('td').eq(day+1).html(clzName).attr("title",clzDesc).addClass("easyui-tooltip");
				}
			}
		}
	});	
}

var submitAdd = function() {
	$("#schedult-window").window('close');

	var day = $("#day-ipt").val();
	var num = $("#num-ipt").val();
	
	var clzName = $.trim($("#clzName-ipt").val());
	var desc = $.trim($("#desc-ipt").val());
	
	
	if (clzName!='') {
		hyx(_rootPath + "/info/addClz.do", {no:no, day:day, num:num, clzName:clzName, desc:desc},function(){
			$.messager.alert('成功', '添加课程成功!', 'info');
			reload();
		})	
	} else {
		$.messager.alert('错误', '班级名称不能为空', 'error');
	}
	
};

var cancelAdd = function() {
	$("#schedult-window").window('close');
}


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
	//加载课程表
	reload();
	
	$(document).on('click','.schedule-table tr .a-td',function(){
		var clzName = $(this).html();
		var day = $(this).index() - 1;
		var num = $(this).parent('tr').index() - 1;

		if (clzName  == '') {
			//添加课程
			$("#day-ipt").val(day);
			$("#num-ipt").val(num);
			$("#schedult-window").window('open');
		} else {
			//删除课程
			$.messager.confirm('通过', '您确定要删除这节课程吗?', function(r){
				if (r){
					hyx(_rootPath + "/info/delClz.do", {no:no, day:day, num:num},function(){
						$.messager.alert('成功', '删除成功!', 'info');
						reload();
					})
				}
			});
		}
	});
});
