$.messager.progress({interval:77});

var getStartDate = function() {
	var text = $("#date-label").html();
	var startDate = text.substring(0,text.indexOf("~")-1);
	return startDate
}

var lastWeek = function() {
	hyx(_rootPath+"/laboratory/getOccupyInfo.do",{no:no, isLast:true, startDate:getStartDate()},function(data){
		refreshOccupy(data);
	});
};

var nextWeek = function() {
	hyx(_rootPath+"/laboratory/getOccupyInfo.do",{no:no, isLast:false, startDate:getStartDate()},function(data){
		refreshOccupy(data);
	});
};

var refreshOccupy = function(data) {
	$("#date-label").html(data.startDate +" ~ " + data.endDate);
}


$(function(){
	//加载课程表
	var loadSche = hyx(_rootPath+"/laboratory/schedule.do",{no:no},function(data){
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
	
	//加载预占信息
	var loadOccupy = hyx(_rootPath+"/laboratory/getOccupyInfo.do",{no:no},function(data){

	});
	
	$.when(loadSche,loadOccupy).then(function(){
		//加载串课信息
		hyx(_rootPath+"/laboratory/getChangeClzInfo.do",{no:no},function(data){
			$.messager.progress('close');
		});
	});
	
});