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
	"dijit/layout/BorderContainer", "dijit/layout/ContentPane", "dijit/layout/SplitContainer", "dijit/layout/LayoutContainer", "dijit/layout/AccordionContainer", "dijit/layout/AccordionPane",
	"dijit/form/Form", "dijit/form/DateTextBox", "dijit/form/TimeTextBox", "dijit/form/TextBox", "dijit/form/ValidationTextBox", "dijit/form/Textarea", "dijit/form/RadioButton", "dijit/form/ComboBox",
	"dijit/form/FilteringSelect",
	"dijit/form/Button",
	"dojox/form/FileUploader", "dojox/form/Uploader",
	"dojo/domReady!"
],
function(dojo, BorderContainer, ContentPane, SplitContainer, LayoutContainer, AccordionContainer, AccordionPane, Form, DateTextBox, TimeTextBox, TextBox, ValidationTextBox, Textarea, RadioButton, ComboBox, FilteringSelect, Button, FileUploader, Uploader) {
	dojo.ready(function() {
		
	});
});
</script>
<div jsId="worksheetRepairContainer" class="worksheetRepair" dojoType="dijit.layout.BorderContainer" design="sidebar" gutters="false">
	<div dojoType="dijit.layout.ContentPane" region="top" style="padding: 0px;">
		<div dojoType="dijit.Toolbar">
			<table cellpadding="0" cellspaceing="0" border="0" width="100%">
				<tr>
					<td>
						<div jsId="worksheetRepairTBnew" dojoType="dijit.form.Button" id="toolbar.new" iconClass="dijitIconNewTask" showLabel="true">
							登记报修
							<script type="dojo/method" event="onClick" args="event">
								worksheetRepairContainer.getRepair("");
							</script>
						</div>
					</td>
					<td align="right">
						<div jsId="worksheetRepairTBsave" dojoType="dijit.form.Button" disabled="disabled" id="toolbar.save" iconClass="dijitEditorIcon dijitEditorIconSave" showLabel="true">
							保存
							<script type="dojo/method" event="onClick" args="event">
								worksheetRepairForm.submit();
							</script>
						</div>
						<div jsId="worksheetRepairTBsend" dojoType="dijit.form.Button" disabled="disabled" id="toolbar.send" iconClass="dijitIconFilter" showLabel="true">
							派发任务
							<script type="dojo/method" event="onClick" args="event">
								var repair = worksheetRepairForm.getValues().repair;
								if (dojo.trim(repair.repairUserName) == "") {
									require(["dojox/dialog/AlertDialog"], function (AlertDialog) {
										worksheetRepairContainer.alertDialog = new AlertDialog({message: "未指定维修人员，不能派发。"});
										worksheetRepairContainer.alertDialog.startup();
										worksheetRepairContainer.alertDialog.show();
									});
								} else {
									require(["dojox/dialog/ConfirmDialog"], function (ConfirmDialog) {
										worksheetRepairContainer.confirmDialog = new ConfirmDialog({
											title: '是否确认？',
											message: '是否确定派发该任务？',
											defaultReturnValue: false,
											onButtonClick: function(val) {
												if (val) {
													dojo.xhrPost({
														url: '/Worksheet/Repair/Operate.s2',
														handleAs: 'json',
														content: {
															'action': 'send',
															'repair.id': repair.id
														}
													}).then(function (result) {
														var msg = "派发任务操作完成";
														if (result.success) {
															worksheetRepairForm.setAttribute("changed", false);
															worksheetRepairContainer.setButton();
														} else {
															msg = result.message;
														}
														require(["dojox/dialog/AlertDialog"], function (AlertDialog) {
															worksheetRepairContainer.alertDialog = new AlertDialog({message: msg});
															worksheetRepairContainer.alertDialog.startup();
															worksheetRepairContainer.alertDialog.show();
														});
													});
												} else {
													//do nothing
												}
											}
										});
										worksheetRepairContainer.confirmDialog.startup();
										worksheetRepairContainer.confirmDialog.show();
									});
								}
							</script>
						</div>
						<div jsId="worksheetRepairTBfinish" dojoType="dijit.form.Button" disabled="disabled" id="toolbar.finish" iconClass="dijitIconPackage" showLabel="true">
							结束任务
							<script type="dojo/method" event="onClick" args="event">
								var repair = worksheetRepairForm.getValues().repair;
								require(["dojox/dialog/ConfirmDialog"], function (ConfirmDialog) {
									worksheetRepairContainer.confirmDialog = new ConfirmDialog({
										title: '是否确认？',
										message: '是否确定结束该任务？',
										defaultReturnValue: false,
										onButtonClick: function(val) {
											if (val) {
												dojo.xhrPost({
													url: '/Worksheet/Repair/Operate.s2',
													handleAs: 'json',
													content: {
														'action': 'finish',
														'repair.id': repair.id
													}
												}).then(function (result) {
													var msg = "结束任务操作完成";
													if (result.success) {
														worksheetRepairForm.setAttribute("changed", false);
														worksheetRepairContainer.setButton();
													} else {
														msg = result.message;
													}
													require(["dojox/dialog/AlertDialog"], function (AlertDialog) {
														worksheetRepairContainer.alertDialog = new AlertDialog({message: msg});
														worksheetRepairContainer.alertDialog.startup();
														worksheetRepairContainer.alertDialog.show();
													});
												});
											} else {
												//do nothing
											}
										}
									});
									worksheetRepairContainer.confirmDialog.startup();
									worksheetRepairContainer.confirmDialog.show();
								});
							</script>
						</div>
						<div jsId="worksheetRepairTBcancel" dojoType="dijit.form.Button" disabled="disabled" id="toolbar.cancel" iconClass="dijitEditorIcon dijitEditorIconCancel" showLabel="true">
							取消任务
							<script type="dojo/method" event="onClick" args="event">
								var repair = worksheetRepairForm.getValues().repair;
								require(["dojox/dialog/ConfirmDialog"], function (ConfirmDialog) {
									worksheetRepairContainer.confirmDialog = new ConfirmDialog({
										title: '是否确认？',
										message: '是否确定取消该任务？',
										defaultReturnValue: false,
										onButtonClick: function(val) {
											if (val) {
												dojo.xhrPost({
													url: '/Worksheet/Repair/Operate.s2',
													handleAs: 'json',
													content: {
														'action': 'cancel',
														'repair.id': repair.id
													}
												}).then(function (result) {
													var msg = "取消任务操作完成";
													if (result.success) {
														worksheetRepairForm.setAttribute("changed", false);
														worksheetRepairContainer.setButton();
													} else {
														msg = result.message;
													}
													require(["dojox/dialog/AlertDialog"], function (AlertDialog) {
														worksheetRepairContainer.alertDialog = new AlertDialog({message: msg});
														worksheetRepairContainer.alertDialog.startup();
														worksheetRepairContainer.alertDialog.show();
													});
												});
											} else {
												//do nothing
											}
										}
									});
									worksheetRepairContainer.confirmDialog.startup();
									worksheetRepairContainer.confirmDialog.show();
								});
							</script>
						</div>
						<div jsId="worksheetRepairTBprint" dojoType="dijit.form.Button" disabled="disabled" id="toolbar.print" iconClass="dijitEditorIcon dijitEditorIconPrint" showLabel="true" disabled="disabled">
							打印
							<script type="dojo/method" event="onClick" args="event">
								//recording
								var repair = worksheetRepairForm.getValues().repair;
								dojo.xhrPost({
									url: '/Worksheet/Repair/Operate.s2',
									handleAs: 'json',
									content: {
										'action': 'print',
										'repair.id': repair.id
									}
								}).then(function (result) {
									var msg = "接受任务/打印完成";
									if (result.success) {
										worksheetRepairForm.setAttribute("changed", false);
										worksheetRepairContainer.setButton();
									} else {
										msg = result.message;
									}
									require(["dojox/dialog/AlertDialog"], function (AlertDialog) {
										worksheetRepairContainer.alertDialog = new AlertDialog({message: msg});
										worksheetRepairContainer.alertDialog.startup();
										worksheetRepairContainer.alertDialog.show();
									});
								});
								//printing
								var data = worksheetRepairForm.data;
								data.repairUserName = worksheetRepairSubItem_repairUserName.get("displayedValue");
								showModalDialog("/Worksheet/Repair/Entry.shtml?action=preview", data, "status:false;resizable:true;scroll:false;dialogWidth:1000px;dialogHeight:700px");
							</script>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div jsId="worksheetRepairLayoutLeft" dojoType="dijit.layout.ContentPane" region="left" splitter="true" style="width: 200px; padding: 0px;">
		<div jsId="worksheetRepairLeftMenu" dojoType="dijit.layout.AccordionContainer" region="leading">
			<div dojoType="dijit.layout.AccordionPane" title="当前报修" style="padding: 3px;">
				<div jsId="currentRepairStore" dojoType="dojo/store/JsonRest" target="/Worksheet/Repair/List.s2" idAttribute="id" labelAttribute="label"></div>
				<div jsId="currentRepairDataStore" dojoType="dojo/data/ObjectStore" objectStore="currentRepairStore"></div>
				<div jsId="currentRepairModel" dojoType="dijit/tree/ForestStoreModel" store="currentRepairDataStore" deferItemLoadingUntilExpand="false" labelAttr="label"  rootLabel="报修记录" childrenAttrs="children" query="{action: 'currentRepair', status: ['', '']}"></div>
				<div dojoType="dijit/Tree" jsId="currentRepairTree" model="currentRepairModel" showRoot="false" style="height: 100%">
					<script type="dojo/method" event="onClick" args="item, node, evt">
						if (item.id != "") worksheetRepairContainer.getRepair(item.id);
					</script>
				</div>
			</div>
			<div dojoType="dijit.layout.AccordionPane" title="以往报修" style="padding: 3px;">
				<div jsId="historyRepairStore" dojoType="dojo/store/JsonRest" target="/Worksheet/Repair/List.s2" idAttribute="id" labelAttribute="label"></div>
				<div jsId="historyRepairDataStore" dojoType="dojo/data/ObjectStore" objectStore="historyRepairStore"></div>
				<div jsId="historyRepairModel" dojoType="dijit/tree/ForestStoreModel" store="historyRepairDataStore" deferItemLoadingUntilExpand="false" labelAttr="label"  rootLabel="报修记录" childrenAttrs="children" query="{action: 'historyRepair', status: ['', '']}"></div>
				<div dojoType="dijit/Tree" jsId="historyRepairTree" model="historyRepairModel" showRoot="false" style="height: 100%">
					<script type="dojo/method" event="onClick" args="item, node, evt">
						if (item.id != "") worksheetRepairContainer.getRepair(item.id);
					</script>
				</div>
			</div>
		</div>
	</div>
	<div jsId="worksheetRepairLayoutCenter" dojoType="dijit.layout.ContentPane" region="center" style="padding-right: 30px; overflow-y: auto;">
		<form jsId="worksheetRepairForm" action="/Worksheet/Repair/Save.s2" dojoType="dijit.form.Form" method="post" changed="false">
			<script type="dojo/method" event="_onChildChange" args="c">
				worksheetRepairForm.setAttribute("changed", true);
				worksheetRepairContainer.setButton();
			</script>
			<script type="dojo/method" event="onSubmit">
				if(this.validate()) {
					var _repair = this.getValues().repair;
					var _callTime0 = _repair.callTime0 == null ? "1900-01-01" : dojo.date.locale.format(_repair.callTime0, {datePattern:'yyyy-MM-dd',selector:'date'});
					var _callTime1 = _repair.callTime1 == null ? "00:00" : dojo.date.locale.format(_repair.callTime1, {timePattern:'HH:mm',selector:'time'});
					var _callTime = dojo.date.locale.parse((_callTime0 + " " + _callTime1), {datePattern: 'yyyy-MM-dd', timePattern: 'HH:mm'});
					worksheetRepairForm.setValues({repair:{callTime:_callTime}});
					var _arrivalTime0 = _repair.arrivalTime0 == null ? "1900-01-01" : dojo.date.locale.format(_repair.arrivalTime0, {datePattern:'yyyy-MM-dd',selector:'date'});
					var _arrivalTime1 = _repair.arrivalTime1 == null ? "00:00" : dojo.date.locale.format(_repair.arrivalTime1, {timePattern:'HH:mm',selector:'time'});
					var _arrivalTime = dojo.date.locale.parse((_arrivalTime0 + " " + _arrivalTime1), {datePattern: 'yyyy-MM-dd', timePattern: 'HH:mm'});
					worksheetRepairForm.setValues({repair:{arrivalTime:_arrivalTime}});
					var _completeTime0 = _repair.completeTime0 == null ? "1900-01-01" : dojo.date.locale.format(_repair.completeTime0, {datePattern:'yyyy-MM-dd',selector:'date'});
					var _completeTime1 = _repair.completeTime1 == null ? "00:00" : dojo.date.locale.format(_repair.completeTime1, {timePattern:'HH:mm',selector:'time'});
					var _completeTime = dojo.date.locale.parse((_completeTime0 + " " + _completeTime1), {datePattern: 'yyyy-MM-dd', timePattern: 'HH:mm'});
					worksheetRepairForm.setValues({repair:{completeTime:_completeTime}});

					worksheetRepairForm.data = this.getValues().repair;

					dojo.xhrPost({form: this.domNode, handleAs: 'json'}).then(function(result) {
						var msg = "保存完成";
						if (result.success) {
							worksheetRepairForm.setAttribute("changed", false);
							worksheetRepairContainer.setButton();
						} else {
							msg = result.message;
						}
						require(["dojox/dialog/AlertDialog"], function (AlertDialog) {
							worksheetRepairContainer.alertDialog = new AlertDialog({message: msg});
							worksheetRepairContainer.alertDialog.startup();
							worksheetRepairContainer.alertDialog.show();
						});
					});
				}
				return false;
			</script>
			<input name="repair.id" dojoType="dijit.form.TextBox" type="hidden"/>
			<input name="repair.status" dojoType="dijit.form.TextBox" type="hidden"/>
			<div style="text-align: center; font-size: 18px; font-family: 微软雅黑,黑体; font-weight: bold;">报修任务单</div>
			<div style="height: 1px; margin: 10px 0px;"></div>
			<div style="padding: 5px;">
				<div style="line-height: 30px;white-space: nowrap;">
					<div class="dijitInline" style="width:49%;">
						<font color="red">*</font>
						<label style="font-weight: bold;">报修科室：</label>
						<span jsId="worksheetRepairDepts" dojoType="dojo.store.JsonRest" idProperty="label" target="/User/Query.s2?action=depts"></span>
						<select name="repair.clientDeptName" autoComplete="false" required="required" placeHolder="输入姓名拼音声母查询" missingMessage="请选择报修科室" message="请选择报修科室" dojoType="dijit.form.ComboBox" store="worksheetRepairDepts" labelAttr="label" searchAttr="label"></select>
					</div>
					<div class="dijitInline">
						<font color="red">*</font>
						<label style="font-weight: bold;">报修项目：</label>
						<input name="repair.repairDevice" type="text" dojoType="dijit.form.ValidationTextBox" trim="true" required="required" missingMessage="请填写报修项目" message="请填写报修项目" style="width: 300px;"/>
					</div>
				</div>
			</div>
			<div style="padding: 5px;">
				<div style="line-height: 30px;white-space: nowrap;">
					<div class="dijitInline" style="width:49%;">
						<font color="red">*</font>
						<label style="font-weight: bold;">　报修人：</label>
						<input name="repair.clientUserName" type="text" dojoType="dijit.form.ValidationTextBox" trim="true" required="required" missingMessage="请填写报修人" message="请填写报修人" style="width: 300px;"/>
					</div>
					<div class="dijitInline">
						<font color="red">*</font>
						<label style="font-weight: bold;">报修时间：</label>
						<input name="repair.callTime" dojoType="dijit/form/_DateTimeTextBox" constraints="{datePattern:'yyyy-MM-dd',timePattern:'HH:mm'}" style="display: none"/>
						<input name="repair.callTime0" type="text" dojoType="dijit/form/DateTextBox" required="required" missingMessage="请选择报修日期" message="请选择报修日期" constraints="{selector:'date',datePattern:'yyyy-MM-dd'}" style="width: 110px;"/>
						<input name="repair.callTime1" type="text" dojoType="dijit/form/TimeTextBox" required="required" missingMessage="请选择报修时间" message="请选择报修时间" constraints="{selector:'time',timePattern:'HH:mm'}" style="width: 70px;"/>
					</div>
				</div>
			</div>
			<div style="padding: 5px;">
				<div style="line-height: 30px;white-space: nowrap;">
					<div class="dijitInline">
						<font color="white">*</font>
						<label style="font-weight: bold;">维修人员：</label>
						<span jsId="repairerSource" dojoType="dojo/store/JsonRest" target="/User/Query.s2?action=users&value=" idProperty="userName" queryEngine="{userName:'*'}"></span>
						<span jsId="repairerStore" dojoType="dojo/data/ObjectStore" objectStore="repairerSource" labelProperty="label"></span>
						<input jsId="worksheetRepairSubItem_repairUserName" name="repair.repairUserName" type="text" dojoType="dijit.form.FilteringSelect" store="repairerStore" placeHolder="输入姓名拼音声母查询" autoComplete="true" required="false" searchDelay="1000" labelAttr="label" labelType="text" searchAttr="label" trim="true" style="width: 300px;"/>
					</div>
					<div class="dijitInline">
						<font color="white">*</font>
						<label style="font-weight: bold;">　到场时间：</label>
						<input name="repair.arrivalTime" dojoType="dijit/form/_DateTimeTextBox" constraints="{datePattern:'yyyy-MM-dd',timePattern:'HH:mm'}" style="display: none"/>
						<input name="repair.arrivalTime0" dojoType="dijit/form/DateTextBox" trim="true" constraints="{selector:'date',datePattern:'yyyy-MM-dd'}" style="width: 110px;"/>
						<input name="repair.arrivalTime1" dojoType="dijit/form/TimeTextBox" trim="true" constraints="{selector:'time',timePattern:'HH:mm'}" style="width: 70px;"/>
					</div>
					<div class="dijitInline">
						<font color="white">*</font>
						<label style="font-weight: bold;">　完成时间：</label>
						<input name="repair.completeTime" dojoType="dijit/form/_DateTimeTextBox" constraints="{datePattern:'yyyy-MM-dd',timePattern:'HH:mm'}" style="display: none"/>
						<input name="repair.completeTime0" dojoType="dijit/form/DateTextBox" trim="true" constraints="{selector:'date',datePattern:'yyyy-MM-dd'}" style="width: 110px;"/>
						<input name="repair.completeTime1" dojoType="dijit/form/TimeTextBox" trim="true" constraints="{selector:'time',timePattern:'HH:mm'}" style="width: 70px;"/>
					</div>
				</div>
			</div>
			<div style="padding: 5px;">
				<div style="line-height: 30px;">
					<font color="white">*</font>
					<label style="font-weight: bold;">使用材料明细：</label>
					<textarea name="repair.materials" dojoType="dijit.form.Textarea" trim="true" style="min-height: 100px; _height: 100px;"></textarea>
				</div>
			</div>
			<div style="padding: 5px;">
				<div style="line-height: 30px;">
					<font color="white">*</font>
					<label style="font-weight: bold;">使用耗材明细：</label>
					<textarea name="repair.consumables" dojoType="dijit.form.Textarea" trim="true" style="min-height: 100px; _height: 100px;"></textarea>
				</div>
			</div>
			<div style="padding: 5px;">
				<div style="line-height: 30px;">
					<font color="white">*</font>
					<label style="font-weight: bold;">利旧材料明细：</label>
					<textarea name="repair.oldMaterials" dojoType="dijit.form.Textarea" trim="true" style="min-height: 100px; _height: 100px;"></textarea>
				</div>
			</div>
			<div style="padding: 5px;">
				<div style="line-height: 30px;">
					<font color="white">*</font>
					<label style="font-weight: bold;">客户评价：</label>
					<textarea name="repair.clientEvaluated" dojoType="dijit.form.Textarea" trim="true" style="min-height: 70px; _height: 50px;"></textarea>
				</div>
			</div>
			<div style="padding: 30px 5px 0px 5px;">
				<div style="white-space: nowrap;text-align: right;">
					<div class="dijitInline">
						<label style="font-weight: bold;">客户签字：</label>
						<input name="repair.clientSign" type="text" dojoType="dijit.form.ValidationTextBox" trim="true" style="width: 300px;"/>
					</div>
				</div>
			</div>
			<div style="padding: 5px;">
				<div style="line-height: 30px;">
					<font color="white">*</font>
					<label style="font-weight: bold;">更换材料入库明细：</label>
					<textarea name="repair.MaterialPutStore" dojoType="dijit.form.Textarea" trim="true" style="min-height: 100px; _height: 100px;"></textarea>
				</div>
			</div>
			<div style="padding: 30px 5px 10px 5px;">
				<div style="white-space: nowrap;text-align: right;">
					<div class="dijitInline">
						<label style="font-weight: bold;">库管员签字：</label>
						<input name="repair.StorerSign" type="text" dojoType="dijit.form.ValidationTextBox" trim="true" style="width: 300px;"/>
					</div>
				</div>
			</div>
		</form>
		<script type="dojo/method" event="onShow">
			/************************* define ****************************/
			worksheetRepairContainer._getRepair = function(id) {
				dojo.xhrGet({
					url : "/Worksheet/Repair/Get.s2",
					handleAs : "json",
					content: {'repair.id':id}
				}).then(function (data) {
					var rec = data;
					rec.callTime = rec.callTime0 = rec.callTime1 = dojo.date.locale.parse(rec.callTime, {datePattern:'yyyy-MM-dd',timePattern:'HH:mm:ss'});
					rec.arrivalTime0 = rec.arrivalTime1 = rec.completeTime0 = rec.completeTime1 = 0;
					if (rec.arrivalTime != "") {
						rec.arrivalTime = dojo.date.locale.parse(rec.arrivalTime, {datePattern:'yyyy-MM-dd',timePattern:'HH:mm:ss'});
						if (rec.arrivalTime.getFullYear() != 1900) rec.arrivalTime0 = rec.arrivalTime;
						if ((rec.arrivalTime.getHours() + rec.arrivalTime.getMinutes() + rec.arrivalTime.getSeconds()) > 0) rec.arrivalTime1 = rec.arrivalTime;
					}
					if (rec.completeTime != "") {
						rec.completeTime = dojo.date.locale.parse(rec.completeTime, {datePattern:'yyyy-MM-dd',timePattern:'HH:mm:ss'});
						if (rec.completeTime.getFullYear() != 1900) rec.completeTime0 = rec.completeTime;
						if ((rec.completeTime.getHours() + rec.completeTime.getMinutes() + rec.completeTime.getSeconds()) > 0) rec.completeTime1 = rec.completeTime;
					}
					worksheetRepairForm.data = rec;
					worksheetRepairForm.setValues({repair: rec});
					if (rec.repairUserName != "") worksheetRepairSubItem_repairUserName.set("displayedValue", rec.repairUserName);
					worksheetRepairForm.setAttribute("changed", false);
					worksheetRepairContainer.setButton();
				});
			}
			worksheetRepairContainer.setButton = function() {
				var user = systemConfig.user.userName, deptName = systemConfig.user.deptName;
				var status = worksheetRepairForm.getValues().repair.status;

				if (deptName == "客服中心") {
					worksheetRepairTBnew.domNode.removeAttribute("style");
					worksheetRepairTBsave.domNode.removeAttribute("style");
					worksheetRepairTBsend.domNode.removeAttribute("style");
					worksheetRepairTBfinish.domNode.removeAttribute("style");
					worksheetRepairTBcancel.domNode.removeAttribute("style");
					worksheetRepairTBprint.set("style", "display:none");
					worksheetRepairTBprint.setLabel("打印");
					if (status == "已登记") {
						worksheetRepairTBnew.setDisabled(false);
						worksheetRepairTBsave.setDisabled(!worksheetRepairForm.attr("changed"));
						worksheetRepairTBsend.setDisabled(worksheetRepairForm.attr("changed"));
						worksheetRepairTBfinish.setDisabled(worksheetRepairForm.attr("changed"));
						worksheetRepairTBcancel.setDisabled(true);
						worksheetRepairTBprint.setDisabled(true);
					} else if (status == "已派发") {
						worksheetRepairTBnew.setDisabled(false);
						worksheetRepairTBsave.setDisabled(!worksheetRepairForm.attr("changed"));
						worksheetRepairTBsend.setDisabled(true);
						worksheetRepairTBfinish.setDisabled(true);
						worksheetRepairTBcancel.setDisabled(false);
						worksheetRepairTBprint.setDisabled(false);
					} else if (status == "维修中") {
						worksheetRepairTBnew.setDisabled(false);
						worksheetRepairTBsave.setDisabled(!worksheetRepairForm.attr("changed"));
						worksheetRepairTBsend.setDisabled(true);
						worksheetRepairTBfinish.setDisabled(false);
						worksheetRepairTBcancel.setDisabled(false);
						worksheetRepairTBprint.setDisabled(false);
					} else if (status == "已完成") {
						worksheetRepairTBnew.setDisabled(false);
						worksheetRepairTBsave.setDisabled(true);
						worksheetRepairTBsend.setDisabled(true);
						worksheetRepairTBfinish.setDisabled(true);
						worksheetRepairTBcancel.setDisabled(true);
						worksheetRepairTBprint.setDisabled(false);
					} else if (status == "已取消") {
						worksheetRepairTBnew.setDisabled(false);
						worksheetRepairTBsave.setDisabled(true);
						worksheetRepairTBsend.setDisabled(true);
						worksheetRepairTBfinish.setDisabled(true);
						worksheetRepairTBcancel.setDisabled(true);
						worksheetRepairTBprint.setDisabled(false);
					} else {
						worksheetRepairTBnew.setDisabled(false);
						worksheetRepairTBsave.setDisabled(!worksheetRepairForm.attr("changed"));
						worksheetRepairTBsend.setDisabled(true);
						worksheetRepairTBfinish.setDisabled(true);
						worksheetRepairTBcancel.setDisabled(true);
						worksheetRepairTBprint.setDisabled(true);
					}
				} else {
					worksheetRepairTBnew.set("style", "display:none");
					worksheetRepairTBsave.set("style", "display:none");
					worksheetRepairTBsend.set("style", "display:none");
					worksheetRepairTBfinish.set("style", "display:none");
					worksheetRepairTBcancel.set("style", "display:none");
					worksheetRepairTBprint.domNode.removeAttribute("style");
					worksheetRepairTBprint.setLabel("接受任务");
					if (status == "已派发") {
						worksheetRepairTBnew.setDisabled(true);
						worksheetRepairTBsave.setDisabled(true);
						worksheetRepairTBsend.setDisabled(true);
						worksheetRepairTBfinish.setDisabled(true);
						worksheetRepairTBcancel.setDisabled(true);
						worksheetRepairTBprint.setDisabled(false);
					} else if (status == "维修中") {
						worksheetRepairTBnew.setDisabled(true);
						worksheetRepairTBsave.setDisabled(true);
						worksheetRepairTBsend.setDisabled(true);
						worksheetRepairTBfinish.setDisabled(true);
						worksheetRepairTBcancel.setDisabled(true);
						worksheetRepairTBprint.setLabel("重打");
						worksheetRepairTBprint.setDisabled(false);
					} else {
						worksheetRepairTBnew.setDisabled(true);
						worksheetRepairTBsave.setDisabled(true);
						worksheetRepairTBsend.setDisabled(true);
						worksheetRepairTBfinish.setDisabled(true);
						worksheetRepairTBcancel.setDisabled(true);
						worksheetRepairTBprint.setDisabled(true);
					}
				}
			}
			worksheetRepairContainer.getRepair = function(id) {
				if (eval(worksheetRepairForm.attr("changed"))) {
					require(["dojox/dialog/ConfirmDialog"], function (ConfirmDialog) {
						worksheetRepairContainer.confirmDialog = new ConfirmDialog({
							title: '是否确认放弃修改？',
							message: '修改的内容未保存，是否放弃修改？',
							defaultReturnValue: false,
							onButtonClick: function(val) {
								if (val) {
									worksheetRepairContainer._getRepair(id);
								} else {
									//do nothing
								}
							}
						});
						worksheetRepairContainer.confirmDialog.startup();
						worksheetRepairContainer.confirmDialog.show();
					});
				} else {
					worksheetRepairContainer._getRepair(id);
				}
			}
			worksheetRepairContainer.refreshRepairList = function() {
				//current repairs
				currentRepairTree.dndController.selectNone();
				currentRepairTree.model._requeryTop();
				if (currentRepairTree.rootNode) {
					currentRepairTree.rootNode.state = "UNCHECKED";
					currentRepairTree.rootNode.destroyRecursive();
				}
				currentRepairTree.postMixInProperties();
				currentRepairTree._load();
				//history repairs
				historyRepairTree.dndController.selectNone();
				historyRepairTree.model._requeryTop();
				if (historyRepairTree.rootNode) {
					historyRepairTree.rootNode.state = "UNCHECKED";
					historyRepairTree.rootNode.destroyRecursive();
				}
				historyRepairTree.postMixInProperties();
				historyRepairTree._load();
			}
			/***************** fire ********************/
			worksheetRepairContainer.getRepair("");
			if (!systemConfig.taskScheduler.worksheetRepairRefreshList) {
				systemConfig.taskScheduler.worksheetRepairRefreshList = setInterval(function() {
					try {
						worksheetRepairContainer.refreshRepairList();
					}
					catch(e) {}
				}, 10000);
			}
		</script>
	</div>
</div>
