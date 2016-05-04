var addTab = function(title, url) {
	if ($('#mainTabs').tabs('exists', title)){
		$('#mainTabs').tabs('select', title);
	} else {
		var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
		$('#mainTabs').tabs('add',{
			title:title,
			content:content,
			closable:true
		});
	}
}

var hyx = function(url,data,func, errfunc) {
	return 	$.post(url,data,function(data){
		if (data.success) {
			func(data);
		} else {
			$.messager.alert('发生错误啦!', data.errMsg, 'error');
			errfunc(data);
		}
	},"json");
}