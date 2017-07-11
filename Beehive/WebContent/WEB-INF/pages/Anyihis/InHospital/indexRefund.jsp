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
			id: "refundGrid",
			store: new ObjectStore({objectStore: new JsonRest({sortParam: "sort",queryEngine: {start: 1, count: 20},target: "/Anyihis/InHospital/ListRefund.shtml"})}),
			columnReordering: "true",
			structure: [[
	 			{field: "bhid", name: "患者编号"},
				{field: "zyh", name: "住院号"},
				{field: "xm", name: "姓名"},
				{field: "xb", name: "性别"},
				{field: "cjsj", name: "冲减时间"},
				{field: "cjr", name: "冲减人"},
				{field: "ks", name: "科室"},
				{field: "yz", name: "医嘱"},
				{field: "yy", name: "原因"},
				{field: "yzrq", name: "医嘱日期"},
				{field: "dj", name: "单价"},
				{field: "sl", name: "数量"},
				{field: "je", name: "金额"},
				{field: "ys", name: "医嘱医生"},
				{field: "zxks", name: "执行科室"},
				{field: "shsj", name: "审核时间"},
				{field: "shr", name: "审核人"},
				{field: "jldw", name: "计量单位"}
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
		}, dojo.byId("refundNode"));
		refund.startup();
		bee.clear(mainToolbar);
		mainToolbar.addChild(new Button({
			label: "刷新",
			onClick: function(e) {
				dijit.byId("refundGrid").store.revert();
			}
		}));
	});
});
</script>
<div id="refundNode"></div>
