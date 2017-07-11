<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	request.setCharacterEncoding("UTF-8");
%>
<script type="text/javascript">
var dialogImport = null, dialogMerit = null;
require(
[
	"dojo",
	"dojo/store/JsonRest", "dojo/data/ObjectStore",
	"dojox/grid/EnhancedGrid", "dojox/grid/enhanced/plugins/Pagination", "dojox/grid/enhanced/plugins/Filter", "dojox/grid/enhanced/plugins/Menu", "dojox/grid/enhanced/plugins/IndirectSelection", "dojox/grid/enhanced/plugins/NestedSorting", "dojox/grid/enhanced/plugins/DnD",
	"dojox/widget/DialogSimple",
	"dijit/layout/BorderContainer", "dijit/layout/ContentPane", "dijit/layout/SplitContainer", "dijit/layout/LayoutContainer", "dijit/layout/AccordionContainer", "dijit/layout/AccordionPane",
	"dijit/form/Form", "dijit/form/DateTextBox", "dijit/form/TimeTextBox", "dijit/form/TextBox", "dijit/form/ValidationTextBox", "dijit/form/Textarea", "dijit/form/RadioButton", "dijit/form/ComboBox", "dijit/form/NumberSpinner",
	"dijit/form/FilteringSelect",
	"dijit/form/Button",
	"dojox/form/Uploader", "dojox/form/uploader/plugins/IFrame",
	"dojo/domReady!"
],
function(dojo, JsonRest, ObjectStore, EnhancedGrid, EnhancedGridPagination, EnhancedGridFilter, EnhancedGridMenu, EnhancedGridIndirectSelection, EnhancedGridNestedSorting, EnhancedGridDnD, Dialog, BorderContainer, ContentPane, SplitContainer, LayoutContainer, AccordionContainer, AccordionPane, Form, DateTextBox, TimeTextBox, TextBox, ValidationTextBox, Textarea, RadioButton, ComboBox, NumberSpinner, FilteringSelect, Button, Uploader, IFrame) {
	dojo.ready(function() {
/*
		var miExecuted = new EnhancedGrid({
			id: "miExecutedGrid",
			store: new ObjectStore({objectStore: new JsonRest({sortParam: "sort",queryEngine: {start: 1, count: 20},target: "/MedicalImaging/Query.shtml"})}),
			selectionMode: "multiple",
			columnReordering: "true",
			structure: [[
	 			{field: "patientName", name: "姓名"},
	 			{field: "registerNo", name: "登记号"},
				{field: "medicalItem", name: "检查项目"},
				{field: "executeTechnician", name: "技师"},
				{field: "executeTechnicianAssociate", name: "辅助技师"},
				{field: "executeDiagnostician", name: "诊断医师"},
				{field: "executeVerifier", name: "审核医师"},
				{field: "executeNurse", name: "分诊护士"},
				{field: "patientType", name: "患者类型"}
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
		}, dojo.byId("miExecutedNode"));
		miExecuted.startup();
*/
		bee.clear(mainToolbar);
		mainToolbar.addChild(new Button({
			iconClass: "dijitIconNewTask",
			label: "导入数据",
			onClick: function(e) {
				dialogImport = dialogImport || new Dialog({
					title:"导入数据",
					href:"/MedicalImaging/Entry.import.shtml",
					style: {width:"800px", height: "90%"},
					closable: true,
					onHide: function() {
						/* this.destroy(); */
						return true;
					}
				});
				dialogImport.startup();
				dialogImport.show();
			}
		}));
		mainToolbar.addChild(new Button({
			iconClass: "dijitIconNewTask",
			label: "核算绩效",
			onClick: function(e) {
				dialogMerit = dialogMerit || new Dialog({
					title:"核算绩效",
					href:"/MedicalImaging/Entry.merit.shtml",
					style: {width:"800px", height: "90%"},
					closable: true,
					executeScripts: true,
					onHide: function() {
						/* this.destroy(); */
						return true;
					}
				});
				dialogMerit.startup();
				dialogMerit.show();
			}
		}));
		mainToolbar.addChild(new Button({
			iconClass: "dijitIconNewTask",
			label: "核算绩效",
			onClick: function(e) {
				var xhrArgs = {
					url: "/MedicalImaging/Entry.merit.shtml",
					handleAs: "text",
					preventCache: true,
					content: {
						budget: 80000,
						year: 2017,
						month: 5,
						beginDate: '2017-04-26',
						endDate: '2017-05-25',
						dept: '影像科'
					},
					load: function(msg, ioargs) {
						alert(msg);
					},
					error: function(err, ioargs) {
						var msg = "";
						switch (ioargs) {
						case 404:
							msg = "The requested page was not found.";
							break;
						case 500:
							msg = "The server reported an error.";
							break;
						case 407:
							msg = "You need authenticate with a proxy.";
							break;
						default:
							msg = "Unknown error.";
							break;
						}
						alert(err);
					}
				}
				dojo.xhrGet(xhrArgs);
			}
		}));
	});
});
</script>
<div id="miExecutedNode"></div>