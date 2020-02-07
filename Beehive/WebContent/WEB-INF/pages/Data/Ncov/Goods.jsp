<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	request.setCharacterEncoding("UTF-8");
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
require(
[
	"dojo",
	"dijit/form/Form", "dijit/form/Button",
	"dijit/form/TextBox", "dijit/form/DateTextBox", "dijit/form/Select",
	"dojox/form/Uploader", "dojox/form/uploader/plugins/IFrame",
	"dojo/domReady!"
],
function(dojo, parser) {
	dojo.ready(function() {
	});
});
</script>
<div dojoType="dijit.layout.BorderContainer" design="sidebar" gutters="false">
	<div dojoType="dijit.layout.ContentPane" region="center">
		<form jsId="dngFrm" id="dngFrm" dojoType="dijit.form.Form" action="/Data/Ncov/Goods.Export.s2" enctype="multipart/form-data">
			<script type="dojo/on" data-dojo-event="submit">
				if(!this.validate()) {
					alert("信息提供不全！");
					return false;
				} else {
					return true;
				}
				</script>
			<input type="hidden" name="fileName" dojoType="dijit.form.TextBox" />
			<div style="padding: 5px">
				<label>统计日期：</label>
				<input name="date" dojoType="dijit.form.DateTextBox" required="true" style="width: 150px" />
			</div>
			<div style="padding: 5px;">
				<label>数据类型：</label>
				<select name="type" dojoType="dijit.form.Select" required="true" style="width: 152px">
					<option value="实数" selected="selected">院内报表</option>
					<option value="须数"/>院外报表</option>
				</select>
			</div>
			<div style="padding: 5px;">
				<label>报表模板：</label>
				<div name="file" multiple="false" type="file" dojoType="dojox.form.Uploader" required="true" label="选择..." force="iframe">
					<script type="dojo/method" event="onChange" args="fileArray">
						if (fileArray.length > 0) {
							var fileName = fileArray[0].name;
							dojo.query("#fileName")[0].innerText = fileName;
							dngFrm.setValues({"fileName": fileName});
						} else {
							dojo.query("#fileName")[0].innerText = "";
							dngFrm.setValues({"fileName": ""});
						}
					</script>
				</div>
				<span id="fileName"></span>
			</div>
			<div style="padding: 5px; padding-left: 170px;">
				<input type="submit" label="导　出" dojoType="dijit.form.Button" />
			</div>
		</form>
	</div>
</div>
