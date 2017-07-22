<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	request.setCharacterEncoding("UTF-8");
%>
<script type="text/javascript">
require([
	"dijit/layout/BorderContainer", "dijit/layout/ContentPane",
	"dijit/form/Form", "dijit/form/Button", "dijit/form/TextBox", "dojox/form/Uploader", "dojox/form/uploader/FileList", "dojo/io/iframe",
	"dojo/domReady!"
],
function(Form, Button, TextBox, Uploader, FileList, IFrame) {
	dojo.ready(function() {
	});
});
</script>
<form jsId="miExecutedStaffPerformanceFrm" id="miExecutedExportFrm" name="miExecutedExportFrm" action="/MedicalImaging/Export.StaffPerformance.s2" method="post" enctype="multipart/form-data" dojoType="dijit.form.Form" target="miExecutedStaffPerformanceBuff">
	<div style="margin: 5px 0; display: none;">
		<label style="font-weight: bold;">核算科室：</label>
		<select name="dept" dojoType="dijit.form.Select" trim="true" required="required" style="width: 70%">
			<option value="影像科">影像科</option>
		</select>
	</div>
	<div style="margin: 5px 0;">
		<div class="dijitInline" style="margin: 10px; margin-top: 0px;">
			<div>
				<label style="font-weight: bold;">年　度：</label>
			</div>
			<div>
				<select name="year" dojoType="dijit.form.Select" trim="true" required="required" style="width: 80px">
					<option value="2017">2017年</option>
					<option value="2018">2018年</option>
				</select>
			</div>
		</div>
		<div class="dijitInline" style="margin: 10px; margin-top: 0px;">
			<div>
				<label style="font-weight: bold;">&nbsp;&nbsp;月　份：</label>
			</div>
			<div>
				<select name="month" dojoType="dijit.form.Select" trim="true" required="required" style="width: 70px">
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
	</div>
	<div style="margin: 5px 0;">
		<div>
			<input name="fileNames" type="hidden" dojoType="dijit.form.TextBox" />
			<input name="fileTypes" type="hidden" dojoType="dijit.form.TextBox" />
			<div id="uploader" name="files" showInput="true" flashFieldName="files" fileInputField="files" multiple="true" type="file" dojoType="dojox.form.Uploader" label="选择文件..." errMsg="请选择文件！" required="required" dropTarget="dropTarget">
				<script type="dojo/method" event="onChange" args="dataArray">
/*
					var _fileNames = new Array();
					var _fileTypes = new Array();
					for (var i = 0; i < dataArray.length; i ++) {
						var _file = dataArray[i];
						_fileNames[i] = _file.name;
						_fileTypes[i] = _file.type;
					}
					miExecutedStaffPerformanceFrm.setValues({
						fileNames: _fileNames,
						fileTypes: _fileTypes
					});
*/
				</script>
			</div>
			<div dojoType="dojox.form.uploader.FileList" uploaderId="uploader" headerType="类型" headerFilename="文件名" headerFilesize="大小"></div>
		</div>
	</div>
	<div class="dijitDialogPaneActionBar" style="text-align: right;">
		<button label="导出" dojoType="dijit.form.Button">
			<script type="dojo/method" event="onClick" args="event">
/*
				if (miExecutedStaffPerformanceFrm.getValues().fileName == "") {
					bee.alert("请选择文件！");
					return false;
				}
*/
/*
				dojo.io.iframe.send({
						url: "/MedicalImaging/Export.StaffPerformance.s2",
						form: miExecutedStaffPerformanceFrm.id,
						method: "post",
						preventCache: true,
						handleAs: "json",
						load: function(response, ioArgs){
							console.log(response);
							console.log(ioArgs);
						},
						error: function(response, ioArgs){
							console.log(response);
							console.log(ioArgs);
						}
					});
*/
				miExecutedStaffPerformanceFrm.submit();
			</script>
		</button>
		<button label="关闭" dojoType="dijit.form.Button">
			<script type="dojo/method" event="onClick" args="event">
				dialogExporter.hide();
			</script>
		</button>
	</div>
	<script type="dojo/method" event="onHide" args="event">
		//
	</script>
</form>
