<%@page import="java.util.Date"%>
<%@page import="com.sojava.beehive.framework.util.FormatUtil"%>
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
	"dojox/dialog/AlertDialog",
	"dojo/domReady!"
],
function(dojo, parser) {
	dojo.ready(function() {
	});
});
</script>
<div dojoType="dijit.layout.BorderContainer" design="sidebar" gutters="false">
	<div dojoType="dijit.layout.ContentPane" region="center">
		<form jsId="dngFrm" id="dngFrm" dojoType="dijit.form.Form" action="/Data/Ncov/Goods.Export.s2" method="post" enctype="multipart/form-data">
			<input type="hidden" name="fileName" dojoType="dijit.form.TextBox" />
			<div style="padding: 5px">
				<label>统计日期：</label>
				<input name="date" dojoType="dijit.form.DateTextBox" value='<%= FormatUtil.DATE_FORMAT.format(new Date()) %>' required="true" style="width: 150px" />
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
					<script type="dojo/method" event="onChange" args="list">
						if (list.length > 0) {
							var fileName = list[0].name;
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
				<button label="导　出" dojoType="dijit.form.Button">
					<script type="dojo/on" event="click" args="e">
						var valid = dngFrm.validate();
						if(!valid) {}
						else if (dngFrm.getValues().file == null || dngFrm.getValues().file == "") dojox.dialog.AlertDialog({message: "未选择模板！"}).show();
						else dngFrm.submit();
					</script>
				</button>
			</div>
		</form>
	</div>
</div>
