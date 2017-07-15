<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	request.setCharacterEncoding("UTF-8");
%>
<script type="text/javascript">
function calculateBudget(o) {
	var frmObj = medicalimagingMeritFrm;
	var _budget = parseFloat(frmObj.getValues().budget)||0;
	var _medicalRate = parseFloat(frmObj.getValues().medicalRate)||80;
	var _manageRate = parseFloat(frmObj.getValues().manageRate)||20;
	if (o&&o.name == "medicalRate") {
		_medicalRate = _medicalRate > 100 ? 100 : _medicalRate < 0 ? 0 : _medicalRate;
		_manageRate = 100-_medicalRate;
	} else if (o&&o.name == "manageRate") {
		_manageRate = _manageRate > 100 ? 100 : _manageRate < 0 ? 0 : _manageRate;
		_medicalRate = 100-_manageRate;
	}
	var _medicalTotal = (_medicalRate/100*_budget).toFixed(2);
	var _manageTotal = (_manageRate/100*_budget).toFixed(2);
	frmObj.setValues({
		budget: _budget,
		medicalRate: _medicalRate,
		medicalTotal: _medicalTotal,
		manageRate: _manageRate,
		manageTotal: _manageTotal
	});
}
require(["dijit/Fieldset", "dojo/domReady!"],
function(Fieldset) {
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
	<div dojoType="dijit.Fieldset" title="科室总额度" toggleable="false">
		<input name="totalAmount" type="text" dojoType="dijit.form.NumberTextBox" trim="true" value="0" required="required" missingMessage="请填写科室总额度" message="请填写科室总额度" style="width: 90%;" />元
	</div>
	<fieldset>
		<legend>支出项目</legend>
		<div style="margin: 5px 0;">
			<div class="dijitInline">
				<label style="font-weight: bold;">科室加班费：</label>
				<input name="overtimeCost" type="text" dojoType="dijit.form.NumberTextBox" trim="true" value="0" required="required" missingMessage="请填写科室加班费" message="请填写科室加班费" style="width: 100px;" />元
			</div>
		</div>
		<div>
			<div class="dijitInline">
				<label style="font-weight: bold;">护理额度占比：</label>
				<input name="nurseRate" type="text" dojoType="dijit.form.NumberSpinner" trim="true" value="80" required="required" missingMessage="请填写护理额度占比" message="请填写护理额度占比" constraints="{max:100, min:0}" maxLength="3" onChange="calculateBudget(this)" onInput="calculateBudget(this)" onMouseDown="calculateBudget(this)" onMouseUp="calculateBudget(this)" style="width: 50px;" />%
			</div>
			<div>
				<input name="nurseCost" type="text" dojoType="dijit.form.NumberTextBox" trim="true" value="0" required="required" readOnly="readonly" style="width: 100px; border: none;" />元
			</div>
		</div>
	</fieldset>
	<div style="margin: 5px 0;">
		<div class="dijitInline">
			<label style="font-weight: bold;">科室预算总额：</label>
			<input name="budget" type="text" dojoType="dijit.form.NumberTextBox" trim="true" value="0" required="required" missingMessage="请填写预算额" message="请填写预算额" onChange="calculateBudget(this)" onInput="calculateBudget(this)" style="width: 100px;" />元
		</div>
	</div>
	<div style="margin: 5px 0;">
		<div class="dijitInline">
			<label style="font-weight: bold;">医疗绩效占比：</label>
			<input name="medicalRate" type="text" dojoType="dijit.form.NumberSpinner" trim="true" value="80" required="required" missingMessage="请填写医疗绩效占比" message="请填写医疗绩效占比" constraints="{max:100, min:0}" maxLength="3" onChange="calculateBudget(this)" onInput="calculateBudget(this)" onMouseDown="calculateBudget(this)" onMouseUp="calculateBudget(this)" style="width: 50px;" />%
		</div>
		<div class="dijitInline">
			<input name="medicalTotal" type="text" dojoType="dijit.form.NumberTextBox" trim="true" value="0" required="required" readOnly="readonly" style="width: 100px; border: none;" />元
		</div>
	</div>
	<div style="margin: 5px 0;">
		<div class="dijitInline">
			<label style="font-weight: bold;">诊断组占比：</label>
			<input name="diagnoRate" type="text" dojoType="dijit.form.NumberSpinner" trim="true" value="65" required="required" missingMessage="请填写诊断组占比" message="请填写诊断组占比" constraints="{max:100, min:0}" maxLength="3" style="width: 50px;" />%
		</div>
		<div class="dijitInline">
			<label style="font-weight: bold;">投照组占比：</label>
			<input name="techRate" type="text" dojoType="dijit.form.NumberSpinner" trim="true" value="35" required="required" missingMessage="请填写投照组占比" message="请填写投照组占比" constraints="{max:100, min:0}" maxLength="3" style="width: 50px;" />%
		</div>
	</div>
	<div style="margin: 5px 0;">
		<div class="dijitInline">
			<label style="font-weight: bold;">管理绩效占比：</label>
			<input name="manageRate" type="text" dojoType="dijit.form.NumberSpinner" trim="true" value="20" required="required" missingMessage="请填写管理绩效占比" message="请填写管理绩效占比" constraints="{max:100, min:0}" maxLength="3" onChange="calculateBudget(this)" onInput="calculateBudget(this)" onMouseDown="calculateBudget(this)" onMouseUp="calculateBudget(this)" style="width: 50px;" />%
		</div>
		<div class="dijitInline">
			<input name="manageTotal" type="text" dojoType="dijit.form.NumberTextBox" trim="true" value="0" required="required" readOnly="readonly" style="width: 100px; border: none;" />元
		</div>
	</div>
	<div style="margin: 5px 0;">
		<div class="dijitInline">
			<label style="font-weight: bold;">年　度：</label>
			<select name="year" dojoType="dijit.form.Select" trim="true" required="required" style="width: 80px">
				<option value="2017">2017年</option>
				<option value="2018">2018年</option>
			</select>
		</div>
		<div class="dijitInline">
			<label style="font-weight: bold;">月　份：</label>
			<select name="month" dojoType="dijit.form.Select" trim="true" required="required" style="width: 70px" value="5">
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
			<input name="beginDate" type="text" dojoType="dijit.form.DateTextBox" trim="true" value="2017-04-26" required="required" missingMessage="请选择开始日期" message="请选择开始日期" style="width: 100px;" />
		</div>
		<div class="dijitInline">
			<label style="font-weight: bold;">～</label>
			<input name="endDate" type="text" dojoType="dijit.form.DateTextBox" trim="true" value="2017-05-25" required="required" missingMessage="请选择截止日期" message="请选择截止日期" style="width: 100px;" />
		</div>
	</div>
	<div style="text-align: right;">
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
						medicalRate: rec.medicalRate,
						medicalTotal: rec.medicalTotal,
						manageRate: rec.manageRate,
						manageTotal: rec.manageTotal,
						techRate: rec.techRate,
						techTotal: rec.techTotal,
						diagnoRate: rec.diagnoRate,
						diagnoTotal: rec.diagnoTotal,
						year: rec.year,
						month: rec.month,
						beginDate: dojo.date.locale.format(rec.beginDate, {selector: 'date', datePattern: 'yyyy-MM-dd'}),
						endDate: dojo.date.locale.format(rec.endDate, {selector: 'date', datePattern: 'yyyy-MM-dd'}),
						dept: rec.dept
					},
					load: function(msg, ioargs) {
						alert(msg);
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
						alert(err);
						medicalimagingMeritFrm.getChildren()[medicalimagingMeritFrm.getChildren().length-1].disabled=false;
					}
				}
				dojo.xhrGet(xhrArgs);
			</script>
		</button>
	</div>
</form>
