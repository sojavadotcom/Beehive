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
	var rec = miExecutedGridForm.getValues();
	var year = rec.year;
	var month = rec.month;
	dojo.xhrGet({
		url : "/MedicalImaging/Entry.query.workStatistics.shtml",
		handleAs : "json",
		content: {
			'year': year||0,
			'month': month||0
		}
	}).then(function (data) {
		var rec = data;
		var frmObj = miExecutedGridForm||null;
		if (frmObj == null) return false;
		frmObj.setValues(rec);
		frmObj.setValues({beginDate: rec.beginDate.substr(0, 10), endDate: rec.endDate.substr(0, 10)});
		dijit.byId("staffPerformanceGrid").filter({
			year: rec.year,
			month: rec.month
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
		var formatValue = function(val, rowIndex, cell) {
			var staffName = staffPerformance.getItem(rowIndex).staffName;
			val = val||0;
			if (val == 0) val = "<div style='width: 100%; text-align: right;" + (staffName == "总计" ? "font-weight:bold;" : "") + "'>-</div>";
			else val = "<div style='width: 100%; text-align: right;" + (staffName == "总计" ? "font-weight:bold;" : "") + "'>" + val + "</div>";

			return val;
		}
		var staffPerformance = new EnhancedGrid({
			id: "staffPerformanceGrid",
			store: new ObjectStore({objectStore: new JsonRest({sortParam: "sort",queryEngine: {start: 1, count: 200},target: "/MedicalImaging/Entry.query.staffPerformance.shtml"})}),
			selectionMode: "single",
			columnReordering: "true",
			structure: [
				[
					{field: "groupName", name: "诊查组", hidden: true},
		 			{field: "staffName", name: "姓名", formatter: function(text) {
		 				text = text||"";
		 				if (text.indexOf("组") > 1) {
		 					text = "<b>" + text + "</b>";
		 				} else if (text == "合计") {
		 					text = "<div style='font-weight: bold; text-align: center;'>合　计</div>";
		 				} else if (text == "总计") {
		 					text = "<div style='font-weight: bold; text-align: center;'>总　计</div>";
		 				}
		 				return text;
		 			}},
					{field: "medicalAmount", name: "医疗绩效", formatter: formatValue},
					{field: "manageAmount", name: "经营管理绩效", formatter: formatValue},
					{field: "nurseAmount", name: "护理绩效", formatter: formatValue},
					{field: "overtimeAmount", name: "误餐费", formatter: formatValue},
					{field: "amountTotal", name: "合计", formatter: formatValue},
					{field: "techPoints", name: "投照点数", formatter: formatValue},
					{field: "diagnosePoints", name: "诊断点数", formatter: formatValue},
					{field: "studentPoints", name: "辅助点数", formatter: formatValue},
					{field: "pointAmount", name: "点数合计", formatter: formatValue}
				]
			],
			plugins: {
				dnd: false,
				nestedSorting: true,
				indirectSelection: false,
				pagination: {
					pageSizes: ['All'],
					defaultPageSize: 9999999,
					description: true,
					sizeSwitch: false,
					pageStepper: false,
					gotoButton: false,
					maxPageStep: 5,
					position: 'bottom'
				},
				cellMerge: {}
			}
		}, dojo.byId("miExecutedStaffPerformanceGridNode"));
		dojo.connect(staffPerformance, '_onFetchComplete', function(items, req) {
			for(var i = 0; i < items.length; i ++) {
				var _groupName = items[i].groupName;
				var _staffName = items[i].staffName;
				if (_groupName == _staffName) {
					staffPerformance.mergeCells(i, 0, 10, 1);
				}
			}
		});
		staffPerformance.startup();

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
		var exporterMenu = new Menu({});
		var exporterExcelMenu = new MenuItem({label: "Excel", onClick: function () {
				var rec = miExecutedGridForm.getValues();
				require(["dojo/request/iframe"], function(iframe) {
					iframe._currentDfd = null;
					iframe("/MedicalImaging/Export.StaffPerformance.shtml", {
						data: {
							year: rec.year,
							month: rec.month
						},
						handleAs: "text"
					}).then(function(msg) {
						bee.alert(msg);
					}, function(err) {
						bee.alert(err);
					});
				});
			}
		});
		exporterMenu.addChild(exporterExcelMenu);
		mainToolbar.addChild(new ComboButton({
			iconClass: "dijitIconNewTask",
			label: "导出...",
			dropDown: exporterMenu,
			onClick: function() {
				dialogExporter = dialogExporter || new Dialog({
					title:"核算绩效",
					href:"/MedicalImaging/Entry.export.shtml",
					style: {width:"800px", height: "90%"},
					closable: true,
					executeScripts: true,
					onHide: function() {
						return true;
					}
				});
				dialogExporter.startup();
				dialogExporter.show();
			}
		}));
	});
});
</script>
<div dojoType="dijit.layout.BorderContainer" design="sidebar" gutters="false" style="width: 100%; height: 100%;">
	<div id="miExecutedStaffPerformanceMainInfoNode" dojoType="dijit.layout.ContentPane" region="top" style="height: 90px;">
	<form jsId="miExecutedGridForm" dojoType="dijit.form.Form">
		<div dojoType="dijit.layout.BorderContainer" gutters="false">
			<div dojoType="dijit.layout.ContentPane" region="top" style="text-align: center;">
				<div class="dijitInline" style="text-align: center;">
					<span style="font-size: 16px">医学影像科</span>
					<select name="year" dojoType="dijit.form.Select" style="width: 80px; font-size: 16px; border: none;" onChange="refreshGrid()">
						<option value="2017">2017年</option>
						<option value="2018">2018年</option>
					</select>
					<select name="month" dojoType="dijit.form.Select" style="width: 70px; font-size: 16px; border: none;" onChange="refreshGrid()">
						<option value="1">一月</option>
						<option value="2">二月</option>
						<option value="3">三月</option>
						<option value="4">四月</option>
						<option value="5">五月</option>
						<option value="6">六月</option>
						<option value="7">七月</option>
						<option value="8">八月</option>
						<option value="9">九月</option>
						<option value="10">十月</option>
						<option value="11">十一月</option>
						<option value="12">十二月</option>
					</select>
					<span style="font-size: 16px">绩效核算</span>
				</div>
				<div class="dijitInline" style="width: 16px; height: 16px; background: url('/images/icons/16/refresh.png') no-repeat center; font-size: 0px; cursor: pointer;" onClick="refreshGrid()">&nbsp;</div>
				<hr style="border: none; border-top: 1px solid #333; margin: 10px auto;" />
			</div>
			<div dojoType="dijit.layout.ContentPane" region="left" style="overflow: hidden;">
				<div class="dijitInline">
					<div>日期：<input name="beginDate" dojoType="dijit.form.TextBox" style="width: 80px; border: none; text-align: right;" readonly="readonly" />～<input name="endDate" dojoType="dijit.form.TextBox" style="width: 80px; border: none;" readonly="readonly" /></div>
				</div>
			</div>
			<div dojoType="dijit.layout.ContentPane" region="right" style="overflow: hidden;">
				<div class="dijitInline">
					<div>总额度：<input name="budget" dojoType="dijit.form.TextBox" style="width: 150px; border: none" readonly="readonly" /></div>
				</div>
				<div class="dijitInline">
					<div>点值：<input name="pointValue" dojoType="dijit.form.TextBox" style="width: 150px; border: none" readonly="readonly" /></div>
				</div>
			</div>
		</div>
	</form>
	</div>
	<div dojoType="dijit.layout.ContentPane" region="center">
		<div id="miExecutedStaffPerformanceGridNode"></div>
	</div>
</div>
