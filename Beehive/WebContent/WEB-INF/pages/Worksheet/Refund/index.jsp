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
	"dijit/form/Textarea",
	"dojo/domReady!"
],
function(dojo, JsonRest, ObjectStore, EnhancedGrid, EnhancedGridPagination, EnhancedGridFilter, EnhancedGridMenu, EnhancedGridIndirectSelection, EnhancedGridNestedSorting, EnhancedGridDnD, Dialog, Button) {
	dojo.ready(function() {
		var refund = new EnhancedGrid({
			id: "refundGrid",
			store: new ObjectStore({objectStore: new JsonRest({sortParam: "sort",queryEngine: {start: 1, count: 20},target: "/Worksheet/Refund/List.shtml"})}),
			selectionMode: "multiple",
			columnReordering: "true",
			structure: [[
	 			{field: "patientName", name: "姓名"},
				{field: "deptName", name: "科室"},
				{field: "outpatientSerial", name: "流水号"},
				{field: "invoiceNo", name: "发票号"},
				{field: "cashierCause", name: "退款员原因"},
				{field: "operatorCause", name: "执行科室原因"},
				{field: "patientCause", name: "患者原因"},
				{field: "summaryCause", name: "汇总原因"},
				{field: "item", name: "项目"},
				{field: "sum", name: "金额"},
				{field: "operator", name: "退款员"},
				{field: "date", name: "退款日期"},
				{field: "kind", name: "类型"}
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
			label: "添加",
			onClick: function(e) {
				dialog = new Dialog({
					title:"添加退款信息",
					href:"/Worksheet/Refund/Edit.shtml",
					style: {width:"800px", height: "90%"},
					closable: true,
					onHide: function() {
						this.destroy();
						return true;
					}
				});
				dialog.startup();
				dialog.show();
			}
		}));
		mainToolbar.addChild(new Button({label: "删除", onClick: function(e) {
			console.log(this);
		}}));
	});
});
</script>
<div id="refundNode"></div>
