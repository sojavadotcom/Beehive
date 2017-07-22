<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	request.setCharacterEncoding("UTF-8");
%>
<script type="text/javascript">
var currentYear = 0, currentMonth = 0;
function resetData(year, month) {
	dojo.xhrGet({
		url : "/MedicalImaging/Entry.query.workStatistics.shtml",
		handleAs : "json",
		content: {
			'year': year||0,
			'month': month||0
		}
	}).then(function (data) {
		var rec = data;
		var frmObj = medicalimagingMeritFrm||null;
		if (!frmObj) return false;
		currentYear = rec.year;
		currentMonth = rec.month;
		frmObj.setValues(rec);
		frmObj.setValues({beginDate: rec.beginDate.substr(0, 10), endDate: rec.endDate.substr(0, 10)});
	});
}
function dateChange() {
	var frmObj = medicalimagingMeritFrm||null;
	if (!frmObj) return false;
	var year = frmObj.getValues().year;
	var month = frmObj.getValues().month;
	if (currentYear == year && currentMonth == month) return false;
	resetData(year, month);
}
function calculateBudget(o) {
	var frmObj = medicalimagingMeritFrm||null;
	if (!frmObj) return false;
	var _budget = (parseFloat(frmObj.getValues().budget)||0).toFixed(2);
	var _overtimeCost = parseFloat(frmObj.getValues().overtimeCost)||0;
	var _nurseRate = parseFloat(frmObj.getValues().nurseRate)||0;
	var _nurseCost = (_nurseRate/100*(_budget - _overtimeCost)).toFixed(2);
	var _performanceTotal = ((_budget - _overtimeCost) - _nurseCost).toFixed(2);
	var _medicalRate = parseFloat(frmObj.getValues().medicalRate)||80;
	var _manageRate = parseFloat(frmObj.getValues().manageRate)||20;
	if (o&&o.name == "medicalRate") {
		_medicalRate = _medicalRate > 100 ? 100 : _medicalRate < 0 ? 0 : _medicalRate;
		_manageRate = 100-_medicalRate;
	} else if (o&&o.name == "manageRate") {
		_manageRate = _manageRate > 100 ? 100 : _manageRate < 0 ? 0 : _manageRate;
		_medicalRate = 100-_manageRate;
	}
	var _medicalTotal = (_medicalRate/100*_performanceTotal).toFixed(2);
	var _manageTotal = (_manageRate/100*_performanceTotal).toFixed(2);
	frmObj.setValues({
		budget: _budget,
		overtimeCost: _overtimeCost,
		nurseRate: _nurseRate,
		nurseCost: _nurseCost,
		performanceTotal: _performanceTotal,
		medicalRate: _medicalRate,
		medicalTotal: _medicalTotal,
		manageRate: _manageRate,
		manageTotal: _manageTotal
	});
}
require(["dijit/Fieldset", "dojo/data/ItemFileReadStore", "dojo/domReady!"],
function(Fieldset, ItemFileReadStore) {
	dojo.ready(function() {
	});
});
</script>
<form jsId="medicalimagingMeritFrm" name="miMeritFrm" method="post" action="/MedicalImaging/Save.merit.s2" dojoType="dijit.form.Form">
	<div style="margin: 5px 0; display: none;">
		<label style="font-weight: bold;">核算科室：</label>
		<select name="dept" dojoType="dijit.form.Select" trim="true" required="required" style="width: 70%">
			<option value="影像科">影像科</option>
		</select>
	</div>
	<div dojoType="dijit.Fieldset" title="基础信息" toggleable="false">
		<div style="margin: 5px 0;">
			<div class="dijitInline">
				<label style="font-weight: bold;">年　度：</label>
				<select name="year" dojoType="dijit.form.Select" trim="true" required="required" style="width: 80px" onChange="dateChange()">
					<option value="2017">2017年</option>
					<option value="2018">2018年</option>
				</select>
			</div>
			<div class="dijitInline">
				<label style="font-weight: bold;">&nbsp;&nbsp;月　份：</label>
				<select name="month" dojoType="dijit.form.Select" trim="true" required="required" style="width: 70px" onChange="dateChange()">
					<option value="1">一月</option>
					<option value="2">二月</option>
					<option value="3">三月</option>
					<option value="4">四月</option>
					<option value="5">五月</option>
					<option value="6">六月</option>
					<option value="7">七月</option>
					<option value="8">八月</option>
					<option value="9">九月</option>
					<option value="10">十月</option>
					<option value="11">十一月</option>
					<option value="12">十二月</option>
				</select>
			</div>
		</div>
		<div style="margin: 5px 0;">
			<div class="dijitInline">
				<label style="font-weight: bold;">日期段：</label>
				<input name="beginDate" type="text" dojoType="dijit.form.DateTextBox" trim="true" required="required" missingMessage="请选择开始日期" message="请选择开始日期" style="width: 100px;" />
			</div>
			<div class="dijitInline">
				<label style="font-weight: bold;">～</label>
				<input name="endDate" type="text" dojoType="dijit.form.DateTextBox" trim="true" required="required" missingMessage="请选择截止日期" message="请选择截止日期" style="width: 100px;" />
			</div>
		</div>
	</div>
	<div dojoType="dijit.Fieldset" title="科室总额度" toggleable="false">
		<input name="budget" type="text" dojoType="dijit.form.NumberTextBox" trim="true" required="required" selectOnClick="true" missingMessage="请填写科室总额度" message="请填写科室总额度" style="width: 300px;" onChange="calculateBudget(this)" />元
	</div>
	<div dojoType="dijit.Fieldset" title="支出项目" toggleable="false">
		<div style="margin: 5px 0;">
			<label style="font-weight: bold;">科室加班费：</label>
			<input name="overtimeCost" type="text" dojoType="dijit.form.NumberTextBox" trim="true" required="required" selectOnClick="true" missingMessage="请填写科室加班费" message="请填写科室加班费" onChange="calculateBudget(this)" style="width: 100px;" />元
		</div>
		<div>
			<div class="dijitInline">
				<label style="font-weight: bold;">护理绩效占比：</label>
				<input name="nurseRate" type="text" dojoType="dijit.form.NumberSpinner" trim="true" required="required" selectOnClick="true" missingMessage="请填写护理额度占比" message="请填写护理额度占比" constraints="{max:100, min:0}" onChange="calculateBudget(this)" onMouseDown="calculateBudget(this)" onMouseUp="calculateBudget(this)" style="width: 50px;" />%
			</div>
			<div class="dijitInline">
				<input name="nurseCost" type="text" dojoType="dijit.form.NumberTextBox" trim="true" required="required" readOnly="readonly" style="width: 100px; border: none;" />元
			</div>
		</div>
	</div>
	<div dojoType="dijit.Fieldset" title="绩效核算" toggleable="false">
		<div style="margin: 5px 0;">
			<label style="font-weight: bold;">绩效分配额度：</label>
			<input name="performanceTotal" type="text" dojoType="dijit.form.NumberTextBox" trim="true" required="required" readOnly="readonly" style="width: 100px;" />元
		</div>
		<div style="margin: 5px 0;">
			<div class="dijitInline">
				<label style="font-weight: bold;">医疗绩效占比：</label>
				<input name="medicalRate" type="text" dojoType="dijit.form.NumberSpinner" trim="true" required="required" selectOnClick="true" missingMessage="请填写医疗绩效占比" message="请填写医疗绩效占比" constraints="{max:100, min:0}" maxLength="3" onChange="calculateBudget(this)" onMouseDown="calculateBudget(this)" onMouseUp="calculateBudget(this)" style="width: 50px;" />%
			</div>
			<div class="dijitInline">
				<input name="medicalTotal" type="text" dojoType="dijit.form.NumberTextBox" trim="true" required="required" readOnly="readonly" style="width: 100px; border: none;" />元
			</div>
		</div>
		<div style="margin: 5px 0;">
			<div class="dijitInline">
				<label style="font-weight: bold;">管理绩效占比：</label>
				<input name="manageRate" type="text" dojoType="dijit.form.NumberSpinner" trim="true" required="required" selectOnClick="true" missingMessage="请填写管理绩效占比" message="请填写管理绩效占比" constraints="{max:100, min:0}" maxLength="3" onChange="calculateBudget(this)" onMouseDown="calculateBudget(this)" onMouseUp="calculateBudget(this)" style="width: 50px;" />%
			</div>
			<div class="dijitInline">
				<input name="manageTotal" type="text" dojoType="dijit.form.NumberTextBox" trim="true" required="required" readOnly="readonly" style="width: 100px; border: none;" />元
			</div>
		</div>
	</div>
	<div class="dijitDialogPaneActionBar" style="text-align: right;">
		<button label="核算" dojoType="dijit.form.Button">
			<script type="dojo/method" event="onClick" args="event">
				medicalimagingMeritFrm.getChildren()[medicalimagingMeritFrm.getChildren().length-1].disabled=true;
				var rec = medicalimagingMeritFrm.getValues();
				var xhrArgs = {
					url: "/MedicalImaging/Save.merit.shtml",
					handleAs: "text",
					preventCache: true,
					content: {
						budget: rec.budget,
						overtimeCost: rec.overtimeCost,
						nurseRate: rec.nurseRate,
						nurseCost: rec.nurseCost,
						performanceCost: rec.performanceCost,
						medicalRate: rec.medicalRate,
						medicalTotal: rec.medicalTotal,
						manageRate: rec.manageRate,
						manageTotal: rec.manageTotal,
						year: rec.year,
						month: rec.month,
						beginDate: dojo.date.locale.format(rec.beginDate, {selector: 'date', datePattern: 'yyyy-MM-dd'}),
						endDate: dojo.date.locale.format(rec.endDate, {selector: 'date', datePattern: 'yyyy-MM-dd'}),
						dept: rec.dept
					},
					load: function(msg, ioargs) {
						bee.alert(msg);
						medicalimagingMeritFrm.getChildren()[medicalimagingMeritFrm.getChildren().length-1].disabled=false;
					},
					error: function(err, ioargs) {
						var msg = "";
						switch (ioargs) {
						case 404:
							msg = "The requested page was not found.";
							break;
						case 500:
							msg = "The server reported an error.";
							break;
						case 407:
							msg = "You need authenticate with a proxy.";
							break;
						default:
							msg = "Unknown error.";
							break;
						}
						bee.alert(err);
						medicalimagingMeritFrm.getChildren()[medicalimagingMeritFrm.getChildren().length-1].disabled=false;
					}
				}
				dojo.xhrGet(xhrArgs);
			</script>
		</button>
	</div>
	<script type="dojo/method" event="onShow">
		resetData();
	</script>
</form>