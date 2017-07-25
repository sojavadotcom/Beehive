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
<form jsId="miExecutedStaffPerformanceFrm" name="miExecutedExportFrm" method="post" dojoType="dijit.form.Form">
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
	<div class="dijitDialogPaneActionBar" style="text-align: right;">
		<button label="导出" dojoType="dijit.form.Button">
			<script type="dojo/method" event="onClick" args="event">
				require(["dojo/request/iframe"], function(iframe) {
					iframe._currentDfd = null;
					iframe("/MedicalImaging/Export.StaffPerformance.shtml", {
						form: miExecutedStaffPerformanceFrm.id,
						handleAs: "text"
					}).then(function(msg) {
						bee.alert(msg);
					}, function(err) {
						bee.alert(err);
					});
				});
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
