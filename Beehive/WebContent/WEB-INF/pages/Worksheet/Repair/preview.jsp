<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE HTML>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/components/dojo/dijit/themes/dijit.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="/components/dojo/dijit/themes/claro/claro.css" media="screen"/>
<title>鸡西市中医医院</title>
<script src="/components/dojo/dojo/dojo.js" data-dojo-config="locale: 'zh-cn', async: true, parseOnLoad: true, isDebug: false"></script>
<style>
* {font-family: 宋体; font-size: 12px;}
.dijitTextBox,.dijitTextArea {border: none;}
table {border: 1px solid #999999; border-top: none;}
td {white-space: nowrap; padding: 5px;}
</style>
</head>
<body>
<script>
//window.onerror = function() {return true;}
require(
[
	"dojo", "dojo/parser", "dojo/NodeList-html", "dojo/NodeList-manipulate",
	"dijit/form/Form", "dijit/form/DateTextBox", "dijit/form/TimeTextBox", "dijit/form/_DateTimeTextBox", "dijit/form/TextBox", "dijit/form/ValidationTextBox", "dijit/form/Textarea",
	"dijit/form/Button",
	"dojo/domReady!"
],
function(dojo) {
	dojo.ready(function() {
		worksheetRepairPreview.setValues({repair: window.dialogArguments});
		for (var key in window.dialogArguments) {
			var value = window.dialogArguments[key];
			if (key == "callTime" || key == "arrivalTime" || key == "completeTime") {
				var _year = value.getFullYear();
				var _month = value.getMonth();
				var _day = value.getDate();
				var _hours = value.getHours();
				var _minutes = value.getMinutes();
				var _seconds = value.getSeconds();
				var _date = _year == 1900 ? "　年　月　日" : _year + "年" + _month + "月" + _day + "日";
				var _time = (_hours+_minutes+_seconds == 0) ? "　时　分" : _hours + "时" + _minutes + "分"
				value = _date + " " + _time;
			}
			with(dojo.query("[name=" + key + "]")) {
				text(value);
				val(value);
			}
		}
		window.print();
		window.close();
	})
});
</script>
<form jsId="worksheetRepairPreview" dojoType="dijit.form.Form" method="post">
	<div style="text-align: center; font-size: 18px; font-family: 微软雅黑,黑体; font-weight: bold;">报修任务单</div>
	<div style="height: 1px; margin: 10px 0px;"></div>
	<table cellpadding="0" cellspacing="0" border="0" width="100%" style="border-top: 1px solid #999999;">
		<tr>
			<td>
				<label style="font-weight: bold;">报修科室:</label>
				<span name="clientDeptName"></span>
			</td>
			<td>
				<label style="font-weight: bold;">报修项目:</label>
				<span name="repairDevice"></span>
			</td>
		</tr>
	</table>
	<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td>
				<label style="font-weight: bold;">报修人:</label>
				<span name="clientUserName"></span>
			</td>
			<td>
				<label style="font-weight: bold;">报修时间:</label>
				<span name="callTime"></span>
			</td>
		</tr>
	</table>
	<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td>
				<label style="font-weight: bold;">维修人员:</label>
				<span name="repairUserName"></span>
			</td>
			<td>
				<label style="font-weight: bold;">到场时间:</label>
				<span name="arrivalTime"></span>
			</td>
			<td>
				<label style="font-weight: bold;">完成时间:</label>
				<span name="completeTime"></span>
			</td>
		</tr>
	</table>
	<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td><label style="font-weight: bold;">使用材料明细：</label></td>
		</tr>
		<tr>
			<td><textarea name="materials" dojoType="dijit.form.Textarea" trim="true" style="min-height: 100px; _height: 100px;"></textarea></td>
		</tr>
	</table>
	<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td><label style="font-weight: bold;">使用耗材明细：</label></td>
		</tr>
		<tr>
			<td><textarea name="consumables" dojoType="dijit.form.Textarea" trim="true" style="min-height: 100px; _height: 100px;"></textarea></td>
		</tr>
	</table>
	<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td><label style="font-weight: bold;">利旧材料明细：</label></td>
		</tr>
		<tr>
			<td><textarea name="oldMaterials" dojoType="dijit.form.Textarea" trim="true" style="min-height: 100px; _height: 100px;"></textarea></td>
		</tr>
	</table>
	<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td><label style="font-weight: bold;">客户评价：</label></td>
		</tr>
		<tr>
			<td><textarea name="clientEvaluated" dojoType="dijit.form.Textarea" trim="true" style="min-height: 70px; _height: 50px;"></textarea></td>
		</tr>
		<tr>
			<td align="right">
				<label style="font-weight: bold;">客户签字：</label>
				<input name="clientSign" style="border:none;width: 100px;"/>
			</td>
		</tr>
	</table>
	<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td><label style="font-weight: bold;">更换材料入库明细：</label></td>
		</tr>
		<tr>
			<td><textarea name="MaterialPutStore" dojoType="dijit.form.Textarea" style="min-height: 100px; _height: 100px;"></textarea></td>
		</tr>
		<tr>
			<td align="right">
				<label style="font-weight: bold;">库管员签字：</label>
				<input name="StorerSign" style="border:none;width: 100px;"/>
			</td>
		</tr>
	</table>
</form>
</body>