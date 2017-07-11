<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	request.setCharacterEncoding("UTF-8");
%>
<script type="text/javascript">
//mainNavTool_LeftArea
require(
[
	"dojo",
	"dijit/form/Button",
	"dojo/store/JsonRest", "dojo/data/ObjectStore",
	"dojox/grid/EnhancedGrid", "dojox/grid/enhanced/plugins/Pagination", "dojox/grid/enhanced/plugins/Filter", "dojox/grid/enhanced/plugins/Menu", "dojox/grid/enhanced/plugins/IndirectSelection", "dojox/grid/enhanced/plugins/NestedSorting", "dojox/grid/enhanced/plugins/DnD",
	"dojo/domReady!"
],
function(dojo, Button, JsonRest, ObjectStore, EnhancedGrid, EnhancedGridPagination, EnhancedGridFilter, EnhancedGridMenu, EnhancedGridIndirectSelection, EnhancedGridNestedSorting, EnhancedGridDnD) {
	dojo.ready(function() {
		var hisTestApplysSource = new JsonRest({
			queryEngine: {start: 1, count: 20},
			target: "/Anyihis/Test/CheckInHospitalApply.shtml?action=getApplys"
		});
		var hisTestApplysStore = new ObjectStore({objectStore: hisTestApplysSource});
		var layout = [[
			{field: "bhid", name: "患者编号"},
			{field: "zyh", name: "住院号"},
			{field: "jydh", name: "申请单号"},
			{field: "xm", name: "姓名"},
			{field: "xb", name: "性别"},
			{field: "ks", name: "科室"},
			{field: "ys", name: "医生"},
			{field: "jyxm", name: "项目"},
			{field: "sqrq", name: "日期"},
			{field: "jldw", name: "单位"},
			{field: "dj", name: "单价"},
			{field: "sl", name: "数量"},
			{field: "je", name: "金额"}
		]];
		var hisTestApplys = new EnhancedGrid({
			id: "hisTestApplysGrid",
			store: hisTestApplysStore,
			columnReordering: "true",
			structure: layout,
			plugins: {
				pagination: {
					pageSizes: ['20', '50', '100', '200', 'All'],
					defaultPageSize: 20,
					description: true,
					sizeSwitch: true,
					pageStepper: true,
					gotoButton: true,
					maxPageStep: 5,
					gotoButton: true,
					position: 'bottom'
				},
				filter: {
					closeFilterbarButton: true,
					ruleCount: 5,
					itemsName: 'recRange',
					isServerSide: true,
					setupFilterQuery: function(commands, request) {
						if(commands.filter && commands.enable) request.query = {filter: JSON.stringify(commands.filter)};
					}
				},
				dnd: true,
				nestedSorting: true,
				indirectSelection: true
			}
		}, dojo.byId("hisTestApplysNode"));
		hisTestApplys.startup();
	});
});
</script>
<div id="hisTestApplysNode"></div>
<!-- 
<div jsId="hisTestApplysSource" dojoType="dojo.store.JsonRest" queryEngine="{start: 1, count: 20}" target="/Anyihis/Test/CheckInHospitalApply.shtml?action=getApplys"></div>
<div jsId="hisTestApplysStore" dojoType="dojo.data.ObjectStore" objectStore="hisTestApplysSource"></div>
<table jsId="hisTestApplys" dojoType="dojox.grid.EnhancedGrid" store="hisTestApplysStore" selectionMode="none" columnReordering="true" plugins="{pagination: {pageSizes: ['20', '50', '100', '200', 'All'], defaultPageSize: 20, description: true, sizeSwitch: true, pageStepper: true, gotoButton: true, maxPageStep: 5, gotoButton: true, position: 'bottom'}, filter: {closeFilterbarButton: true, ruleCount: 5, itemsName: 'recRange', isServerSide: true, setupFilterQuery: function(commands, request) {if(commands.filter && commands.enable) request.query = {filter: JSON.stringify(commands.filter)};}}, dnd: true, nestedSorting: true, indirectSelection: true}">
<thead>
	<tr>
		<th field="bhid" width="80">患者编号</th>
		<th field="zyh" width="100">住院号</th>
		<th field="jydh">申请单号</th>
		<th field="xm">姓名</th>
		<th field="xb">性别</th>
		<th field="ks">科室</th>
		<th field="ys">医生</th>
		<th field="jyxm" width="70%">项目</th>
		<th field="sqrq">日期</th>
		<th field="jldw">单位</th>
		<th field="dj">单价</th>
		<th field="sl">数量</th>
		<th field="je">金额</th>
	</tr>
</thead>
</table>
 -->
<!-- 
<div jsId="hisTestContainer" class="his" dojoType="dijit.layout.BorderContainer" design="sidebar" gutters="false" style="width: 100%; height: 100%">
	<div jsId="hisTestLayoutTop" dojoType="dijit.layout.ContentPane" region="top">
		<div jsId="hisTestMenuBar" dojoType="dijit.Toolbar">
			<div dojoType="dijit.form.Button" iconClass="">刷新
				<script type="dojo/event" event="onClick" args="e">
					hisTestApplysStore.revert();
				</script>
			</div>
		</div>
	</div>
	<div jsId="hisTestLayoutCenter" dojoType="dijit.layout.ContentPane" region="center" style="overflow-y: auto;">
		<div jsId="hisTestApplysSource" dojoType="dojo.store.JsonRest" queryEngine="{start: 1, count: 20}" target="/Anyihis/Test/CheckInHospitalApply.shtml?action=getApplys"></div>
		<div jsId="hisTestApplysStore" dojoType="dojo.data.ObjectStore" objectStore="hisTestApplysSource"></div>
		<table jsId="hisTestApplys" dojoType="dojox.grid.EnhancedGrid" store="hisTestApplysStore" selectionMode="none" columnReordering="true" plugins="{pagination: {pageSizes: ['20', '50', '100', '200', 'All'], defaultPageSize: 20, description: true, sizeSwitch: true, pageStepper: true, gotoButton: true, maxPageStep: 5, gotoButton: true, position: 'bottom'}, filter: {closeFilterbarButton: true, ruleCount: 5, itemsName: 'recRange', isServerSide: true, setupFilterQuery: function(commands, request) {if(commands.filter && commands.enable) request.query = {filter: JSON.stringify(commands.filter)};}}, dnd: true, nestedSorting: true, indirectSelection: true}">
		<thead>
			<tr>
				<th field="bhid" width="80">患者编号</th>
				<th field="zyh" width="100">住院号</th>
				<th field="jydh">申请单号</th>
				<th field="xm">姓名</th>
				<th field="xb">性别</th>
				<th field="ks">科室</th>
				<th field="ys">医生</th>
				<th field="jyxm" width="70%">项目</th>
				<th field="sqrq">日期</th>
				<th field="jldw">单位</th>
				<th field="dj">单价</th>
				<th field="sl">数量</th>
				<th field="je">金额</th>
			</tr>
		</thead>
		</table>
	</div>
</div>
 -->