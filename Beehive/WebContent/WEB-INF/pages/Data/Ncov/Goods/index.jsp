<%@page import="java.util.Date"%>
<%@page import="com.sojava.beehive.framework.util.FormatUtil"%>
<%@page import="java.util.Calendar"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	request.setCharacterEncoding("UTF-8");
	Calendar c = Calendar.getInstance();
	String now = FormatUtil.DATE_FORMAT.format(new Date());
%>
<script type="text/javascript">
var now = new Date(<%= c.get(Calendar.YEAR) %>, <%= c.get(Calendar.MONTH) %>, <%= c.get(Calendar.DAY_OF_MONTH) %>);
var nowStr = "<%= now %>";
var dialogExporter = null, dialogAdd = null;
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
			val = val||0;
			return val;
		}
		var ncovGoods = new EnhancedGrid({
			id: "ncovGoodsGrid",
			store: new ObjectStore({objectStore: new JsonRest({
				sortParam: "sort",
				queryEngine: [
					{start: 1},
					{count: 50},
					{date: nowStr}
				],
				target: "/Data/Ncov/Goods/Query.list.shtml"
			})}),
			selectionMode: "single",
			columnReordering: "true",
			structure: [
				[
					{field: "time", name: "登记时间"},
					{field: "kind", name: "数据类型"},
		 			{field: "deptSrc", name: "发放科室"},
		 			{field: "deptDestType", name: "请领科系"},
		 			{field: "deptDest", name: "请领科室"},
					{field: "ptkz", name: "普通口罩", formatter: formatValue},
					{field: "wkkz", name: "外科口罩", formatter: formatValue},
					{field: "n95", name: "N95口罩", formatter: formatValue},
					{field: "klfhkz", name: "颗粒防护口罩", formatter: formatValue},
					{field: "fhf", name: "防护服", formatter: formatValue},
					{field: "gly", name: "隔离衣", formatter: formatValue},
					{field: "rjst", name: "乳胶手套", formatter: formatValue},
					{field: "mz", name: "一次性帽子", formatter: formatValue},
					{field: "hmj", name: "护目镜", formatter: formatValue},
					{field: "xdy84", name: "84消毒液", formatter: formatValue},
					{field: "jj75", name: "酒精75%", formatter: formatValue},
					{field: "sxdj", name: "手消毒剂", formatter: formatValue},
					{field: "twj", name: "体温计", formatter: formatValue},
					{field: "jj7545", name: "酒精75%(4.5L)", formatter: formatValue}
				]
			],
			plugins: {
				dnd: true,
				nestedSorting: true,
				indirectSelection: false,
				pagination: {
					pageSizes: ['All', '10', '50'],
					defaultPageSize: 50,
					description: true,
					sizeSwitch: false,
					pageStepper: false,
					gotoButton: false,
					maxPageStep: 5,
					position: 'bottom'
				}
			}
		}, dojo.byId("ncovGoodsGridNode"));
		ncovGoods.startup();

		mainToolbar.addChild(new Button({
			iconClass: "dijitIconNewTask",
			label: "统计&导出",
			onClick: function(e) {
				if (dialogExporter == null) {
					dialogExporter = new Dialog({
						title: "统计&导出",
						href: "/Data/Ncov/Entry.GoodsExport.shtml",
						closable: true,
						executeScripts: true,
						onHide: function() {
							return true;
						}
					});
					dialogExporter.startup();
				}
				dialogExporter.show();
			}
		}));
		mainToolbar.addChild(new Button({
			iconClass: "dijitIconNewTask",
			label: "新增",
			onClick: function(e) {
				if (dialogAdd == null) {
					dialogAdd = new Dialog({
						title: "物资消耗详情",
						href: "/Data/Ncov/Entry.GoodsAdd.shtml",
						closable: true,
						executeScripts: true,
						onHide: function() {
							return true;
						}
					});
					dialogAdd.startup();
				}
				dialogAdd.show();
			}
		}));
	});
});
</script>
<div dojoType="dijit.layout.BorderContainer" design="sidebar" gutters="false">
	<div dojoType="dijit.layout.ContentPane" region="top" style="padding: 0px;">
		<div jsId="ncovGoodsNavMenu" dojoType="dijit.MenuBar">
		<form dojoType="dijit.form.Form">
			<div jsId="ncovGoods_Search_time" type="text" dojoType="dijit.form.DateTextBox" value="<%= now %>">
			<script type="dojo/on" event="change" args="e">
				var date = ncovGoods_Search_time.value;
				dijit.byId("ncovGoodsGrid").filter({
					date: (date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate())||nowStr
				});
			</script>
			</div>
		</form>
		</div>
	</div>
	<div dojoType="dijit.layout.ContentPane" region="center">
		<div id="ncovGoodsGridNode"></div>
	</div>
</div>
