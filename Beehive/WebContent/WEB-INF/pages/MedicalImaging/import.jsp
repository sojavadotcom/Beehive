<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	request.setCharacterEncoding("UTF-8");
%>
<script type="text/javascript">
require([
	"dijit/Fieldset", "dojo/data/ItemFileReadStore",
	"dijit/form/Form", "dijit/form/TextBox", "dijit/form/ValidationTextBox", "dijit/form/NumberSpinner",
	"dijit/form/Button",
	"dojox/form/Uploader", "dojox/form/uploader/FileList",
	"dojo/io/iframe",
	"dojo/domReady!"],
function(Fieldset, ItemFileReadStore, Form, TextBox, ValidationTextBox, NumberSpinner, Button, Uploader, FileList, ioIFrame) {
	dojo.ready(function() {
	});
});
</script>
<form jsId="miExecutedImportFrm" name="miExecutedImportFrm" method="post" action="/MedicalImaging/Save.import.s2" enctype="multipart/form-data" dojoType="dijit.form.Form">
	<div style="margin: 10px 0px;">
		<select name="dept" dojoType="dijit.form.Select">
			<option value="影像科">影像科</option>
		</select>
	</div>
	<div style="margin: 10px 0px;">
		<span name="workloadFile" type="file" multiple="false" dojoType="dojox.form.Uploader" required="required" label="选择文件...">
			<script type="dojo/method" event="onChange" args="dataArray"">
				var _fileName = "";
				for (var i = 0; i < dataArray.length; i ++) {
					var _file = dataArray[i];
					_fileName += _file.name + " ";
				}
				miExecutedImportFrm.setValues({'workloadFileName': _fileName.trim()});
			</script>
		</span>
		<input name="workloadFileName" type="text" dojoType="dijit.form.TextBox" readonly="readOnly" />
	</div>
	<div class="dijitDialogPaneActionBar" style="text-align: right;">
		<button label="导入" dojoType="dijit.form.Button">
			<script type="dojo/method" event="onClick" args="event">
				require(["dojo/request/iframe"], function(iframe) {
					iframe._currentDfd = null;
					iframe("/MedicalImaging/Save.import.s2", {
						form: miExecutedImportFrm.id,
						handleAs: "text"
					}).then(function(msg) {
						bee.alert("导入完成");
					}, function(err) {
						bee.alert(err);
					});
				});
			</script>
		</button>
		<button label="关闭" dojoType="dijit.form.Button">
			<script type="dojo/method" event="onClick" args="event">
				dialogImport.hide();
			</script>
		</button>
	</div>
</form>
