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
	"dojo/store/JsonRest", "dojo/data/ObjectStore",
	"dijit/layout/BorderContainer", "dijit/layout/ContentPane", "dijit/layout/SplitContainer", "dijit/layout/LayoutContainer", "dojox/layout/TableContainer",
	"dojox/grid/EnhancedGrid", "dojox/grid/enhanced/plugins/Pagination", "dojox/grid/enhanced/plugins/Filter", "dojox/grid/enhanced/plugins/Menu", "dojox/grid/enhanced/plugins/IndirectSelection", "dojox/grid/enhanced/plugins/NestedSorting", "dojox/grid/enhanced/plugins/DnD", "dojox/grid/enhanced/plugins/CellMerge",
	"dojox/widget/DialogSimple", "dojox/dialog/AlertDialog",
	"dijit/form/Form", "dijit/form/Button",
	"dijit/form/TextBox", "dijit/form/DateTextBox", "dijit/form/Select", "dijit/form/ComboBox", "dijit/form/NumberTextBox",
	"dojox/form/Uploader", "dojox/form/uploader/plugins/IFrame",
	"dojo/domReady!"
],
function(dojo, parser) {
	dojo.ready(function() {
	});
});
</script>
<form jsId="dngAddFrm" dojoType="dijit.form.Form" action="/Data/NCP/Goods/Save.s2" method="post" enctype="multipart/form-data">
	<div dojoType="dijit/layout/LayoutContainer">
		<div dojoType="dojox.layout.TableContainer" cols="2" region="center">
			<input name="date" title="结算日期：" dojoType="dijit.form.DateTextBox" value='<%= FormatUtil.DATE_FORMAT.format(new Date()) %>' required="true" style="width: 150px" />
			<select name="type" title="数据类型：" dojoType="dijit.form.Select" required="true" style="width: 152px">
				<option value="实数" selected="selected">院内报表</option>
				<option value="须数"/>院外报表</option>
			</select>
			<select name="deptSrc" title="发放科室：" dojoType="dijit.form.ComboBox">
				<option value="卫材库">wck卫材库</option>
				<option value="发热门诊">frmz发热门诊</option>
				<option value="门诊部">mzb门诊部</option>
			</select>
			<select name="deptDest" title="请领科室：" dojoType="dijit.form.ComboBox">
			</select>
			<input name="ptkz" title="普通口罩：" dojoType="dijit.form.NumberTextBox" />
			<input name="wkkz" title="外科口罩：" dojoType="dijit.form.NumberTextBox" />
			<input name="n95" title="N95口罩：" dojoType="dijit.form.NumberTextBox" />
			<input name="klfhkz" title="颗粒防护口罩：" dojoType="dijit.form.NumberTextBox" />
			<input name="fhf" title="防护服：" dojoType="dijit.form.NumberTextBox" />
			<input name="gly" title="隔离衣：" dojoType="dijit.form.NumberTextBox" />
			<input name="rjst" title="乳胶手套：" dojoType="dijit.form.NumberTextBox" />
			<input name="mz" title="帽子：" dojoType="dijit.form.NumberTextBox" />
			<input name="hmj" title="护目镜：" dojoType="dijit.form.NumberTextBox" />
			<input name="xdy84" title="84消毒液：" dojoType="dijit.form.NumberTextBox" />
			<input name="jj75" title="酒精75%：" dojoType="dijit.form.NumberTextBox" />
			<input name="sxdj" title="手消毒剂：" dojoType="dijit.form.NumberTextBox" />
			<input name="twj" title="体温计：" dojoType="dijit.form.NumberTextBox" />
			<input name="jj7545" title="酒精75%(4.5L)：" dojoType="dijit.form.NumberTextBox" />
		</div>
		<div dojoType="dijit.layout.ContentPane" region="bottom" style0="padding: 5px; padding-left: 170px;">
			<button label="保　存" dojoType="dijit.form.Button">
				<script type="dojo/on" event="click" args="e">
					var valid = dngAddFrm.validate();
					if(!valid) {}
					else if (dngAddFrm.getValues().file == null || dngAddFrm.getValues().file == "") dojox.dialog.AlertDialog({message: "未选择模板！"}).show();
//					else dngAddFrm.submit();
				</script>
			</button>
		</div>
	</div>
</form>
