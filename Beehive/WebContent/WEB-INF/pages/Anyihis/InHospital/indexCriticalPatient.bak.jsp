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
	"dojo/store/JsonRest", "dojo/data/ObjectStore",
	"dojox/grid/EnhancedGrid", "dojox/grid/enhanced/plugins/Pagination", "dojox/grid/enhanced/plugins/Filter", "dojox/grid/enhanced/plugins/Menu", "dojox/grid/enhanced/plugins/IndirectSelection", "dojox/grid/enhanced/plugins/NestedSorting", "dojox/grid/enhanced/plugins/DnD",
	"dijit/Dialog",
	"dijit/form/Button",
	"dojo/domReady!"
],
function(dojo, JsonRest, ObjectStore, EnhancedGrid, EnhancedGridPagination, EnhancedGridFilter, EnhancedGridMenu, EnhancedGridIndirectSelection, EnhancedGridNestedSorting, EnhancedGridDnD, Dialog, Button) {
	dojo.ready(function() {
		var refund = new EnhancedGrid({
			id: "criticalPatientGrid",
			store: new ObjectStore({objectStore: new JsonRest({sortParam: "sort",queryEngine: {start: 1, count: 20},target: "/Anyihis/InHospital/ListCriticalPatient.shtml"})}),
			columnReordering: "true",
			structure: [[
	 			{field: "bhid", name: "患者编号"},
				{field: "hljb", name: "护理级别"},
				{field: "zyh", name: "住院号"},
				{field: "ch", name: "床号"},
				{field: "xm", name: "姓名"},
				{field: "xb", name: "性别"},
				{field: "ks", name: "科室"},
				{field: "ys", name: "医生"}
			]],
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
		}, dojo.byId("criticalPatientNode"));
		refund.startup();
		bee.clear(mainToolbar);
		mainToolbar.addChild(new Button({
			label: "刷新",
			onClick: function(e) {
				dijit.byId("criticalPatientGrid").store.revert();
			}
		}));
	});
});
</script>
<div id="criticalPatientNode"></div>
