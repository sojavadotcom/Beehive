<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>鸡西市中医医院</title>
</head>
<body>
<script type="text/javascript">
require([
	"dojox/charting/Chart",
	"dojox/charting/themes/Claro",
	"dojox/charting/plot2d/Lines",
	"dojox/charting/action2d/Tooltip", "dojox/charting/action2d/MoveSlice", "dojox/charting/widget/Legend",
	"dojox/charting/plot2d/Markers",
	"dojox/charting/axis2d/Default",
	"dojox/charting/DataChart", "dojox/charting/DataSeries", "dojo/store/JsonRest", "dojo/data/ObjectStore", "dojo/data/ItemFileWriteStore",
	"dojo/domReady!"
], function(Chart, Theme, Plot, Tooltip, MoveSlice, Legend, Marker, Default, DataChart, DataSeries, JsonRest, ObjectStore, ItemFileWriteStore) {
	//在院患者
	var inhospitalChart = new Chart("inhospitalNode", {
//		title: "在院患者情况",
		titlePos:"bottom",
		titleGap: 5,
		titleFont: "normal normal normal 12px 微软雅黑"
	});
	inhospitalChart.setTheme(Theme);
	inhospitalChart.addPlot("default", {
		type: Plot,
		lines: true,
		areas: false,
		markers: true,
		tension: "S",
		label: true,
		labelStyle: "outside",
		labelOffset: 25,
		radius: 100
	});
	inhospitalChart.addAxis("x", {includeZero: false, fixLower: "major", fixUpper: "major", majorTickStep: 1, title: "", font: "normal normal normal 11px 微软雅黑", titleOrientation: "away", labels: [{"value":1,"text":"4月26日"},{"value":2,"text":"4月27日"},{"value":3,"text":"4月28日"},{"value":4,"text":"4月29日"},{"value":5,"text":"4月30日"},{"value":6,"text":"5月1日"},{"value":7,"text":"5月2日"},{"value":8,"text":"5月3日"},{"value":9,"text":"5月4日"},{"value":10,"text":"5月5日"},{"value":11,"text":"5月6日"},{"value":12,"text":"5月7日"},{"value":13,"text":"5月8日"},{"value":14,"text":"5月9日"},{"value":15,"text":"5月10日"},{"value":16,"text":"5月11日"},{"value":17,"text":"5月12日"},{"value":18,"text":"5月13日"},{"value":19,"text":"5月14日"},{"value":20,"text":"5月15日"},{"value":21,"text":"5月16日"},{"value":22,"text":"5月17日"},{"value":23,"text":"5月18日"},{"value":24,"text":"5月19日"},{"value":25,"text":"5月20日"},{"value":26,"text":"5月21日"},{"value":27,"text":"5月22日"},{"value":28,"text":"5月23日"},{"value":29,"text":"5月24日"},{"value":30,"text":"5月25日"}]});
	inhospitalChart.addAxis("y", {includeZero: false, title: "在院人数", majorLabels: true, fixed: true, majorTickStep: 30, font: "normal normal normal 11px 微软雅黑", min: 400, max: 900, vertical: true, fixLower: "major", fixUpper: "major"});
	inhospitalChart.addSeries("2016年", [{"x":1,"y":760},{"x":2,"y":739},{"x":3,"y":722},{"x":4,"y":662},{"x":5,"y":648},{"x":6,"y":645},{"x":7,"y":687},{"x":8,"y":692},{"x":9,"y":695},{"x":10,"y":715},{"x":11,"y":701},{"x":12,"y":666},{"x":13,"y":659},{"x":14,"y":734},{"x":15,"y":713},{"x":16,"y":720},{"x":17,"y":722},{"x":18,"y":705},{"x":19,"y":684},{"x":20,"y":681},{"x":21,"y":765},{"x":22,"y":732},{"x":23,"y":751},{"x":24,"y":759},{"x":25,"y":748},{"x":26,"y":749},{"x":27,"y":749},{"x":28,"y":749},{"x":29,"y":749},{"x":30,"y":749}]);
	inhospitalChart.addSeries("2015年(同期)", [{"x":1,"y":446},{"x":2,"y":491},{"x":3,"y":460},{"x":4,"y":458},{"x":5,"y":434},{"x":6,"y":412},{"x":7,"y":411},{"x":8,"y":422},{"x":9,"y":475},{"x":10,"y":453},{"x":11,"y":464},{"x":12,"y":468},{"x":13,"y":483},{"x":14,"y":455},{"x":15,"y":457},{"x":16,"y":515},{"x":17,"y":468},{"x":18,"y":464},{"x":19,"y":472},{"x":20,"y":466},{"x":21,"y":435},{"x":22,"y":431},{"x":23,"y":464},{"x":24,"y":445},{"x":25,"y":472},{"x":26,"y":467},{"x":27,"y":458},{"x":28,"y":446},{"x":29,"y":446},{"x":30,"y":478}]);
	var tip = new Tooltip(inhospitalChart, "default");
	var mag = new MoveSlice(inhospitalChart,"default");

	inhospitalChart.render();
	var inhospitalLegend = new Legend({chart: inhospitalChart}, "inhospitalLegendNode");
	//门诊量
	var outpatientStore = new ItemFileWriteStore({
		url: "/Anyihis/StatisticOutpatient.shtml"
//		data: {"identifier":"id","idAttribute":"id","label":"amount","items":[{"id":1,"sort":"20150426","monthDay":"04月26日","amount":262,"year":"2015","month":"04","day":"26"},{"id":2,"sort":"20150427","monthDay":"04月27日","amount":696,"year":"2015","month":"04","day":"27"},{"id":3,"sort":"20150428","monthDay":"04月28日","amount":570,"year":"2015","month":"04","day":"28"},{"id":4,"sort":"20150429","monthDay":"04月29日","amount":562,"year":"2015","month":"04","day":"29"},{"id":5,"sort":"20150430","monthDay":"04月30日","amount":441,"year":"2015","month":"04","day":"30"},{"id":6,"sort":"20150501","monthDay":"05月01日","amount":244,"year":"2015","month":"05","day":"01"},{"id":7,"sort":"20150502","monthDay":"05月02日","amount":324,"year":"2015","month":"05","day":"02"},{"id":8,"sort":"20150503","monthDay":"05月03日","amount":261,"year":"2015","month":"05","day":"03"},{"id":9,"sort":"20150504","monthDay":"05月04日","amount":666,"year":"2015","month":"05","day":"04"},{"id":10,"sort":"20150505","monthDay":"05月05日","amount":591,"year":"2015","month":"05","day":"05"},{"id":11,"sort":"20150506","monthDay":"05月06日","amount":557,"year":"2015","month":"05","day":"06"},{"id":12,"sort":"20150507","monthDay":"05月07日","amount":527,"year":"2015","month":"05","day":"07"},{"id":13,"sort":"20150508","monthDay":"05月08日","amount":525,"year":"2015","month":"05","day":"08"},{"id":14,"sort":"20150509","monthDay":"05月09日","amount":358,"year":"2015","month":"05","day":"09"},{"id":15,"sort":"20150510","monthDay":"05月10日","amount":227,"year":"2015","month":"05","day":"10"},{"id":16,"sort":"20150511","monthDay":"05月11日","amount":683,"year":"2015","month":"05","day":"11"},{"id":17,"sort":"20150512","monthDay":"05月12日","amount":442,"year":"2015","month":"05","day":"12"},{"id":18,"sort":"20150513","monthDay":"05月13日","amount":512,"year":"2015","month":"05","day":"13"},{"id":19,"sort":"20150514","monthDay":"05月14日","amount":494,"year":"2015","month":"05","day":"14"},{"id":20,"sort":"20150515","monthDay":"05月15日","amount":470,"year":"2015","month":"05","day":"15"},{"id":21,"sort":"20150516","monthDay":"05月16日","amount":384,"year":"2015","month":"05","day":"16"},{"id":22,"sort":"20150517","monthDay":"05月17日","amount":236,"year":"2015","month":"05","day":"17"},{"id":23,"sort":"20150518","monthDay":"05月18日","amount":656,"year":"2015","month":"05","day":"18"},{"id":24,"sort":"20150519","monthDay":"05月19日","amount":442,"year":"2015","month":"05","day":"19"},{"id":25,"sort":"20150520","monthDay":"05月20日","amount":610,"year":"2015","month":"05","day":"20"},{"id":26,"sort":"20150521","monthDay":"05月21日","amount":481,"year":"2015","month":"05","day":"21"},{"id":27,"sort":"20150522","monthDay":"05月22日","amount":489,"year":"2015","month":"05","day":"22"},{"id":28,"sort":"20150523","monthDay":"05月23日","amount":380,"year":"2015","month":"05","day":"23"},{"id":29,"sort":"20150524","monthDay":"05月24日","amount":263,"year":"2015","month":"05","day":"24"},{"id":30,"sort":"20150525","monthDay":"05月25日","amount":645,"year":"2015","month":"05","day":"25"},{"id":31,"sort":"20160426","monthDay":"04月26日","amount":465,"year":"2016","month":"04","day":"26"},{"id":32,"sort":"20160427","monthDay":"04月27日","amount":489,"year":"2016","month":"04","day":"27"},{"id":33,"sort":"20160428","monthDay":"04月28日","amount":423,"year":"2016","month":"04","day":"28"},{"id":34,"sort":"20160429","monthDay":"04月29日","amount":331,"year":"2016","month":"04","day":"29"},{"id":35,"sort":"20160430","monthDay":"04月30日","amount":375,"year":"2016","month":"04","day":"30"},{"id":36,"sort":"20160501","monthDay":"05月01日","amount":191,"year":"2016","month":"05","day":"01"},{"id":37,"sort":"20160502","monthDay":"05月02日","amount":376,"year":"2016","month":"05","day":"02"},{"id":38,"sort":"20160503","monthDay":"05月03日","amount":440,"year":"2016","month":"05","day":"03"},{"id":39,"sort":"20160504","monthDay":"05月04日","amount":432,"year":"2016","month":"05","day":"04"},{"id":40,"sort":"20160505","monthDay":"05月05日","amount":480,"year":"2016","month":"05","day":"05"},{"id":41,"sort":"20160506","monthDay":"05月06日","amount":383,"year":"2016","month":"05","day":"06"},{"id":42,"sort":"20160507","monthDay":"05月07日","amount":390,"year":"2016","month":"05","day":"07"},{"id":43,"sort":"20160508","monthDay":"05月08日","amount":215,"year":"2016","month":"05","day":"08"},{"id":44,"sort":"20160509","monthDay":"05月09日","amount":638,"year":"2016","month":"05","day":"09"},{"id":45,"sort":"20160510","monthDay":"05月10日","amount":439,"year":"2016","month":"05","day":"10"},{"id":46,"sort":"20160511","monthDay":"05月11日","amount":550,"year":"2016","month":"05","day":"11"},{"id":47,"sort":"20160512","monthDay":"05月12日","amount":416,"year":"2016","month":"05","day":"12"},{"id":48,"sort":"20160513","monthDay":"05月13日","amount":421,"year":"2016","month":"05","day":"13"},{"id":49,"sort":"20160514","monthDay":"05月14日","amount":395,"year":"2016","month":"05","day":"14"},{"id":50,"sort":"20160515","monthDay":"05月15日","amount":209,"year":"2016","month":"05","day":"15"},{"id":51,"sort":"20160516","monthDay":"05月16日","amount":632,"year":"2016","month":"05","day":"16"},{"id":52,"sort":"20160517","monthDay":"05月17日","amount":488,"year":"2016","month":"05","day":"17"},{"id":53,"sort":"20160518","monthDay":"05月18日","amount":477,"year":"2016","month":"05","day":"18"},{"id":54,"sort":"20160519","monthDay":"05月19日","amount":409,"year":"2016","month":"05","day":"19"},{"id":55,"sort":"20160520","monthDay":"05月20日","amount":446,"year":"2016","month":"05","day":"20"},{"id":56,"sort":"20160521","monthDay":"05月21日","amount":338,"year":"2016","month":"05","day":"21"}]}
	});
	var outpatientChart = new Chart("outpatientNode", {
//		title: "门诊情况",
		titlePos:"bottom",
		titleGap: 5,
		titleFont: "normal normal normal 12px 微软雅黑"
	});
	outpatientChart.setTheme(Theme);
	outpatientChart.addPlot("default", {
		type: Plot,
		lines: true,
		areas: false,
		markers: true,
		tension: "S",
		label: true,
		labelStyle: "outside",
		labelOffset: 25,
		radius: 100
	});
	outpatientChart.addAxis("y", {
		includeZero: false,
		title: "门诊量",
		majorLabels: true,
		fixed: true,
		majorTickStep: 30,
		font: "normal normal normal 11px 微软雅黑",
		min: 200,
		max: 800,
		vertical: true,
		fixLower: "major",
		fixUpper: "major"
	});
	outpatientChart.addSeries("2016年", new DataSeries(outpatientStore, {query: {year: '2016'}}, "amount"));
	outpatientChart.addSeries("2015年(同期)", new DataSeries(outpatientStore, {query: {year: '2015'}}, "amount"));
	var tip = new Tooltip(outpatientChart, "default");
	var mag = new MoveSlice(outpatientChart,"default");
	outpatientStore.fetch({
		onComplete: function(items, request) {
			var xAxis = {};
			var xAxisLabels = [];
			for(var i = 0; i < items.length; i ++) {
				var item = items[i];
				var monthDay = item.monthDay;
				if (xAxis[monthDay] != null) break;
				xAxis[monthDay] = i+1;
				var label = {value: i+1, text: parseInt(item.day) + "日"};
				xAxisLabels.push(label);
			}
			outpatientChart.addAxis("x", {
				includeZero: false,
				fixLower: "major",
				fixUpper: "major",
				majorTickStep: 1,
				title: "",
				font: "normal normal normal 11px 微软雅黑",
				titleOrientation: "away",
				labels: xAxisLabels
			});
			outpatientChart.render();
			new Legend({chart: outpatientChart}, "outpatientLegendNode");
		}
	});
/*
/	var outpatientStore = new ItemFileWriteStore({url: "/Anyihis/StatisticOutpatient.shtml"});
	var outpatientStore = new ItemFileWriteStore({data: {"identifier":"amount","idAttribute":"amount","label":"门诊量",
		"items":[
			{"amount":700,"label": "201505","amounts":[262,696,570,562,441,244,324,261,666,591,557,527,525,358,227,683,442,512,494,470,384,236,656,442,610,481,489,380,263,645]},
			{"amount":800,"label": "201605","amounts":[465,489,423,331,375,191,376,440,432,480,383,390,215,638,439,550,416,421,395,209,632,488,477,409,446,316]}
		]}});
	var outpatientChart = new DataChart("outpatientNode", {
		chartTheme: Theme,
		store: outpatientStore,
		query: {amount: "*"},
		fieldName: "amounts",
		comparative: false,
		chartPlot: {
			type: Plot,
			lines: true,
			areas: false,
			markers: true,
			tension: "S",
			label: true,
			labelStyle: "outside",
			labelOffset: 25,
			radius: 100
		}
	});
	outpatientChart.addAxis("x", {
		labelFunc: "seriesLabels",
		includeZero: false,
		fixLower: "major",
		fixUpper: "major",
		majorTickStep: 1,
		title: "",
		font: "normal normal normal 11px 微软雅黑",
		titleOrientation: "away",
		labels: [{"value":1,"text":"4月26日"},{"value":2,"text":"4月27日"},{"value":3,"text":"4月28日"},{"value":4,"text":"4月29日"},{"value":5,"text":"4月30日"},{"value":6,"text":"5月1日"},{"value":7,"text":"5月2日"},{"value":8,"text":"5月3日"},{"value":9,"text":"5月4日"},{"value":10,"text":"5月5日"},{"value":11,"text":"5月6日"},{"value":12,"text":"5月7日"},{"value":13,"text":"5月8日"},{"value":14,"text":"5月9日"},{"value":15,"text":"5月10日"},{"value":16,"text":"5月11日"},{"value":17,"text":"5月12日"},{"value":18,"text":"5月13日"},{"value":19,"text":"5月14日"},{"value":20,"text":"5月15日"},{"value":21,"text":"5月16日"},{"value":22,"text":"5月17日"},{"value":23,"text":"5月18日"},{"value":24,"text":"5月19日"},{"value":25,"text":"5月20日"},{"value":26,"text":"5月21日"},{"value":27,"text":"5月22日"},{"value":28,"text":"5月23日"},{"value":29,"text":"5月24日"},{"value":30,"text":"5月25日"}]
	});
	outpatientChart.addAxis("y", {
		includeZero: false,
		title: "门诊量",
		majorLabels: true,
		fixed: true,
		majorTickStep: 30,
		font: "normal normal normal 11px 微软雅黑",
		min: 200,
		max: 800,
		vertical: true,
		fixLower: "major",
		fixUpper: "major"
	});
	var tip = new Tooltip(outpatientChart, "default");
	var mag = new MoveSlice(outpatientChart,"default");
*/
});
</script>
<div dojoType="dijit.layout.BorderContainer" design="sidebar" gutters="false" style="width: 100%; height: 100%; padding: 0; margin: 0px;">
	<div dojoType="dojox.layout.ContentPane" region="left" style="padding: 0; margin: 0px;">
		<div id="outpatientNode" style="width:50%;height:400px;"></div>
		<div id="outpatientLegendNode"></div>
	</div>
	<div dojoType="dojox.layout.ContentPane" region="left" style="padding: 0; margin: 0px;">
		<div id="inhospitalNode" style="width:50%;height:400px;"></div>
		<div id="inhospitalLegendNode"></div>
	</div>
</div>
</body>
</html>
