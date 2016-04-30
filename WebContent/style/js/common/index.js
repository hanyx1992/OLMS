function addTab(title, url){
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

$("#mainMenu").click(function(){
	addTab("实验室", _rootPath + "/laboratory/index.do");
});