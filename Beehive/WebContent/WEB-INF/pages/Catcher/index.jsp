<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	request.setCharacterEncoding("UTF-8");
%>
<script type="text/javascript">
var dialogImport = null, dialogMerit = null, dialogExporter = null;
function refreshGrid() {
	var rec = catcherGridForm.getValues();
	dojo.xhrGet({
		url : "/Catcher/Query.shtml",
		handleAs : "json",
		content: {}
	}).then(function (data) {
		var rec = data;
		var frmObj = catcherGridForm||null;
		if (frmObj == null) return false;
		frmObj.setValues(rec);
		dijit.byId("catcherGrid").filter({
			title: rec.title
		});
	});
}
require(
[
	"dojo",
	"dojo/store/JsonRest", "dojo/data/ObjectStore",
	"dojox/grid/EnhancedGrid", "dojox/grid/enhanced/plugins/Pagination", "dojox/grid/enhanced/plugins/Filter", "dojox/grid/enhanced/plugins/Menu", "dojox/grid/enhanced/plugins/IndirectSelection", "dojox/grid/enhanced/plugins/NestedSorting", "dojox/grid/enhanced/plugins/DnD", "dojox/grid/enhanced/plugins/CellMerge",
	"dojox/widget/DialogSimple",
	"dijit/layout/BorderContainer", "dijit/layout/ContentPane", "dijit/layout/SplitContainer", "dijit/layout/LayoutContainer", "dijit/layout/AccordionContainer", "dijit/layout/AccordionPane",
	"dijit/form/Form", "dijit/form/DateTextBox", "dijit/form/TimeTextBox", "dijit/form/TextBox", "dijit/form/ValidationTextBox", "dijit/form/Textarea", "dijit/form/RadioButton", "dijit/form/ComboBox", "dijit/form/NumberSpinner",
	"dijit/Fieldset",
	"dijit/form/FilteringSelect",
	"dijit/form/Button", "dijit/form/ComboButton", "dijit/DropDownMenu", "dijit/Menu", "dijit/MenuItem",
	"dojox/form/Uploader", "dojo/io/iframe",
	"dojo/domReady!"
],
function(dojo, JsonRest, ObjectStore, EnhancedGrid, EnhancedGridPagination, EnhancedGridFilter, EnhancedGridMenu, EnhancedGridIndirectSelection, EnhancedGridNestedSorting, EnhancedGridDnD, EnhancedGridCellMerge, Dialog, BorderContainer, ContentPane, SplitContainer, LayoutContainer, AccordionContainer, AccordionPane, Form, DateTextBox, TimeTextBox, TextBox, ValidationTextBox, Textarea, RadioButton, ComboBox, NumberSpinner, Fieldset, FilteringSelect, Button, ComboButton, DropDownMenu, Menu, MenuItem, Uploader, IFrame) {
	dojo.ready(function() {
		var catcher = new EnhancedGrid({
			id: "catcherGrid",
			store: new ObjectStore({objectStore: new JsonRest({sortParam: "sort",queryEngine: {start: 1, count: 100, sort: "-date"},target: "/Catcher/Query.shtml"})}),
			selectionMode: "single",
			columnReordering: "true",
			structure: [
				[
					{field: "title", name: "标题", width: "50", filterable: true, datatype: "string", formatter: function (val, rowIndex, cell) {
						var url = catcher.getItem(rowIndex).url;
						return "<a href='" + url + "' target='blank'>" + val + "</a>";
					}},
					{field: "date", name: "日期", filterable: true, datatype: "date", dataTypeArgs: {datePattern: "yyyy-M-d"}},
					{field: "kind", name: "来源", filterable: true, datatype: "string", autoComplete: true}
				]
			],
			plugins: {
				dnd: true,
				nestedSorting: true,
				indirectSelection: false,
				pagination: {
					pageSizes: ['100', '200', '300'],
					defaultPageSize: 100,
					description: true,
					sizeSwitch: true,
					pageStepper: true,
					gotoButton: true,
					maxPageStep: 5,
					position: 'bottom'
				},
				filter: {
					isServerSide: true,
					setupFilterQuery: function (commands, request) {
						console.log(commands);
						console.log(request);
					}
				}
			}
		}, dojo.byId("catcherGridNode"));
		catcher.startup();
	});
});
</script>
<div dojoType="dijit.layout.BorderContainer" design="sidebar" gutters="false" style="width: 100%; height: 100%;">
	<div id="catcherFunNode" dojoType="dijit.layout.ContentPane" region="top" style="height: 50px;">
	<form jsId="catcherGridForm" dojoType="dijit.form.Form">
		<div dojoType="dijit.layout.BorderContainer" gutters="false">
			<div dojoType="dijit.layout.ContentPane" region="top" style="text-align: center;">
				<div class="dijitInline">
					<input name="title" dojoType="dijit.form.TextBox"><button dojoType="dijit.form.Button"> 查询 </button>
				</div>
			</div>
		</div>
	</form>
	</div>
	<div dojoType="dijit.layout.ContentPane" region="center">
		<div id="catcherGridNode"></div>
	</div>
</div>
