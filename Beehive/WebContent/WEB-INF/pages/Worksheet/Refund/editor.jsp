<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	request.setCharacterEncoding("UTF-8");
%>
<script type="text/javascript">
require(
[
	"dojo",
	"dijit/layout/BorderContainer", "dijit/layout/ContentPane",
	"dijit/form/Form", "dijit/form/DateTextBox", "dijit/form/TimeTextBox", "dijit/form/TextBox", "dijit/form/ValidationTextBox", "dijit/form/Textarea", "dijit/form/RadioButton", "dijit/form/ComboBox",
	"dijit/form/FilteringSelect",
	"dijit/form/Button",
	"dojox/form/FileUploader", "dojox/form/Uploader",
	"dojo/domReady!"
],
function(dojo, BorderContainer, ContentPane, Form, DateTextBox, TimeTextBox, TextBox, ValidationTextBox, Textarea, RadioButton, ComboBox, FilteringSelect, Button, FileUploader, Uploader) {
	dojo.ready(function() {
/*
		worksheetRefundForm.connect("onSubmit", function() {
			if(this.validate()) {
			}
			return false;
		});
*/
	});
});
</script>
<form jsId="worksheetRefundForm" action="/Worksheet/Refund/Save.s2" dojoType="dijit.form.Form" method="post" changed="false">
<script type="dojo/method" event="_onChildChange" args="c">
	worksheetRefundForm.setAttribute("changed", true);
</script>
<input name="refund.id" dojoType="dijit.form.TextBox" type="hidden"/>
<div style="text-align: center; font-size: 18px; font-family: 微软雅黑,黑体; font-weight: bold;">退费登记</div>
<div style="height: 1px; margin: 10px 0px;"></div>
<div style="padding: 5px;">
	<div style="line-height: 30px;white-space: nowrap;">
		<div class="dijitInline" style="width:49%;">
			<font color="red">*</font>
			<label style="font-weight: bold;">退费日期：</label>
			<input name="refund.date" type="text" dojoType="dijit/form/DateTextBox" required="required" missingMessage="请选择退费日期" message="请选择退费日期" constraints="{selector:'date',datePattern:'yyyy-MM-dd'}" style="width: 110px;"/>
		</div>
		<div class="dijitInline">
			<font color="red">*</font>
			<label style="font-weight: bold;">患者姓名：</label>
			<input name="refund.patientName" type="text" dojoType="dijit.form.TextBox" trim="true" required="required" missingMessage="请填写患者姓名" message="请填写患者姓名" style="width: 300px;"/>
		</div>
	</div>
</div>
<div style="padding: 5px;">
	<div style="line-height: 30px;white-space: nowrap;">
		<div class="dijitInline" style="width:49%;">
			<font color="red">*</font>
			<label style="font-weight: bold;">　科室：</label>
			<span jsId="worksheetRefundDepts" dojoType="dojo.store.JsonRest" idProperty="label" target="/User/Query.s2?action=depts"></span>
			<select name="refund.deptName" autoComplete="false" required="required" placeHolder="输入科室拼音声母查询" missingMessage="请选择科室" message="请选择科室" dojoType="dijit.form.ComboBox" store="worksheetRefundDepts" labelAttr="label" searchAttr="label"></select>
		</div>
		<div class="dijitInline">
			<font color="red">*</font>
			<label style="font-weight: bold;">退费项目：</label>
			<input name="refund.item" type="text" dojoType="dijit.form.TextBox" trim="true" required="required" missingMessage="请填写退费项目" message="请填写退费项目" style="width: 300px;"/>
		</div>
	</div>
</div>
<div style="padding: 5px;">
	<div style="line-height: 30px;white-space: nowrap;">
		<div class="dijitInline" style="width:49%;">
			<font color="red">*</font>
			<label style="font-weight: bold;">门诊流水号：</label>
			<input name="refund.outpatientSerial" type="text" dojoType="dijit.form.TextBox" trim="true" required="required" missingMessage="请填写门诊流水号" message="请填写门诊流水号" style="width: 300px;"/>
		</div>
		<div class="dijitInline">
			<font color="red">*</font>
			<label style="font-weight: bold;">发票编号：</label>
			<input name="refund.invoiceNo" type="text" dojoType="dijit.form.TextBox" trim="true" required="required" missingMessage="请填写发票编号" message="请填写发票编号" style="width: 300px;"/>
		</div>
	</div>
</div>
<div style="padding: 5px;">
	<div style="line-height: 30px;">
		<font color="white">*</font>
		<label style="font-weight: bold;">患者原因：</label>
		<textarea name="refund.patientCause" dojoType="dijit.form.Textarea" trim="true" style="min-height: 70px; _height: 50px;"></textarea>
	</div>
</div>
<div style="padding: 5px;">
	<div style="line-height: 30px;">
		<font color="white">*</font>
		<label style="font-weight: bold;">医生原因：</label>
		<textarea name="refund.doctorCause" dojoType="dijit.form.Textarea" trim="true" style="min-height: 70px; _height: 50px;"></textarea>
	</div>
</div>
<div style="padding: 5px;">
	<div style="line-height: 30px;">
		<font color="white">*</font>
		<label style="font-weight: bold;">科室原因：</label>
		<textarea name="refund.operatorCause" dojoType="dijit.form.Textarea" trim="true" style="min-height: 70px; _height: 50px;"></textarea>
	</div>
</div>
<div></div>
</form>