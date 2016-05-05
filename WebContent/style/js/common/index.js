$("#mainMenu a").click(function(){
	var url = $(this).attr('val');
	var name = $(this).attr('title');
	if (url == "exit") {
		window.location.href = _rootPath + "/logout.do";
	} else {
		addTab(name, _rootPath + "/" + url + "/index.do");
	}
});

$(function(){
	$("#mainMenu a:eq(0)").click();
});