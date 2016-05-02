$(function(){
	//加载课程表
	$.post(_rootPath+"/laboratory/schedule.do",{no:no},function(data){
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
	},"json");
});