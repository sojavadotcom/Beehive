<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	request.setCharacterEncoding("UTF-8");
%>
<form name="miExecutedImportFrm" method="post" action="/MedicalImaging/Save.import.s2" enctype="multipart/form-data" dojoType="dijit.form.Form">
	<div>
		<select name="dept">
			<option value="影像科">影像科</option>
		</select>
	</div>
	<div>
		<input id="uploader" name="uploadFile" flashFieldName="uploadFile" multiple="true" type="file" dojoType0="dojox.form.Uploader" label="选择文件..." />
	</div>
	<div class="dijitDialogPaneActionBar" style="text-align: right;">
		<input type="submit" label="导入" dojoType="dijit.form.Button" />
	</div>
</form>
