$.messager.progress({interval:77});

var _day=0;
var _num=0;
var _tableData = '';
var _lockIndex = -1

var getStartDate = function() {
	var text = $("#date-label").html();
	var startDate = text.substring(0,text.indexOf("~")-1);
	return startDate
};

var getEndDate = function() {
	var text = $("#date-label").html();
	var endDate = text.substring(text.indexOf("~") + 2);
	return endDate;
};

var getDateStr = function(day) {
	var s = getStartDate();
	var e = getEndDate();
	var sDay = parseInt(s.substring(s.lastIndexOf("-")+1));
	var eDay = parseInt(e.substring(e.lastIndexOf("-")+1));
	var cur = 0;
	var str = e.substring(0,e.lastIndexOf("-")+1);
	if (eDay < sDay) {
		cur = eDay - 6 + day;
		if (cur < 10) {
			str = e + "0" +cur;
		} else {
			str = e + cur;
		}
	}
	if (cur < 1) {
		cur = sDay + day;
		if (cur < 10) {
			str = s.substring(0,s.lastIndexOf("-")+1) + "0" +cur;
		} else {
			str = s.substring(0,s.lastIndexOf("-")+1) + cur;
		}
	}
	return str;
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
	if (!data.lockFlag) {
		reloadTableData();
		_lockIndex = data.lockIndex;
		$("#date-label").html(data.startDate +" ~ " + data.endDate);
		
		var list  = data.list;
		for (var i = 0; i < list.length; i++) {
			var bean = list[i];
			if (size > bean.size) {			
				$('.schedule-table tr').eq(bean.num+1).children('td').eq(bean.day+1).html('未满(' + bean.size + '/' + size + ')').attr('nowOccupySize', bean.size);
			} else {
				$('.schedule-table tr').eq(bean.num+1).children('td').eq(bean.day+1).html('已满').attr('nowOccupySize', bean.size);
			}
		}
	}
};

var cancelOccupy = function() {
	$('#occupy-window').window('close');
};

var submitOccupy = function() {
	$('#occupy-window').window('close');
	hyx(_rootPath + "/laboratory/occupy.do", {no:no, day : _day, num : _num, onum: $("#occupy-num-ipt").val(),startDate:getStartDate(), desc: $("#occupy-desc-ipt").val()},
			function(data){
				$.messager.alert('成功', '预占申请提交成功,请耐心等待老师或者管理员审核!', 'info');
	});
}

var reloadTableData = function() {
	if (_tableData.length > 0) {
		$('.schedule-table').html(_tableData);
	}
}
$(function(){
	
	//可预约
	$(document).on('click','.schedule-table tr .a-td[title="可预约"]',function(){
		var nowSize = $(this).attr('nowOccupySize');

		_day = $(this).index() - 1;
		
		if (_day < _lockIndex) {
			$.messager.alert('不可选', '这个时间可不能进行预占哦~', 'info');
			return false;
		}
		
		_num = $(this).parent('tr').index() - 1;
		$('#occupy-num-ipt').numberspinner({max: parseInt(size)});
		
		if (parseInt(nowSize) >= parseInt(size)) {
			$.messager.alert('不可选', '已经满了哦~', 'info');
			return false;
		}
		
		if (nowSize != undefined) {
			var nowSize = parseInt(nowSize);
			$('#occupy-num-ipt').numberspinner({max: parseInt(size)-nowSize});
		}
		
		$("#date-span").html(getDateStr(_day) + " " + $(".schedule-table tr:eq(0) td").eq(_day+1).html() + " " + $(".schedule-table tr").eq(_num+1).children("td:eq(0)").html());
		
		$('#occupy-window').window('open');

	});
	
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
		_tableData = $('.schedule-table').html();
	});
	
	//加载预占信息
	var loadOccupy = hyx(_rootPath+"/laboratory/getOccupyInfo.do",{no:no},function(data){
		refreshOccupy(data);
	});
	
	$.when(loadSche,loadOccupy).then(function(){
		//加载串课信息
		hyx(_rootPath+"/laboratory/getChangeClzInfo.do",{no:no},function(data){
			$.messager.progress('close');
		});
	});
	
});