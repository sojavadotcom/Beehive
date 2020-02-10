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
			<input name="date" title="结算日期" dojoType="dijit.form.DateTextBox" value='<%= FormatUtil.DATE_FORMAT.format(new Date()) %>' required="true" style="width: 150px" />
			<select name="type" title="数据类型" dojoType="dijit.form.Select" required="true" style="width: 152px">
				<option value="实数" selected="selected">院内报表</option>
				<option value="须数"/>院外报表</option>
			</select>
			<select name="deptSrc" title="发放科室" dojoType="dijit.form.ComboBox">
				<option value="卫材库">wck - 卫材库</option>
				<option value="发热门诊">frmz - 发热门诊</option>
				<option value="门诊部">mzb - 门诊部</option>
			</select>
			<select name="deptDest" title="请领科室" dojoType="dijit.form.ComboBox">
				<option value="发热门诊">frmz - 发热门诊 - 发热门诊</option>
				<option value="安全保卫部">aqbwb - 行政部门 - 安全保卫部</option>
				<option value="办公室">bgs - 行政部门 - 办公室</option>
				<option value="病人服务中心">brfwzx - 行政部门 - 病人服务中心</option>
				<option value="门诊部">mzb - 行政部门 - 门诊部</option>
				<option value="器械科">qxk - 行政部门 - 器械科</option>
				<option value="统购办">tgb - 行政部门 - 统购办</option>
				<option value="药学部">yxb - 行政部门 - 药学部</option>
				<option value="总值班">zzb - 行政部门 - 总值班</option>
				<option value="保洁部">bjb - 后勤保障 - 保洁部</option>
				<option value="餐饮中心">cyzx - 后勤保障 - 餐饮中心</option>
				<option value="导医">dy - 后勤保障 - 导医</option>
				<option value="供应室">gys - 后勤保障 - 供应室</option>
				<option value="收款室">sks - 后勤保障 - 收款室</option>
				<option value="卫材库">wck - 后勤保障 - 卫材库</option>
				<option value="信息中心">xxzx - 后勤保障 - 信息中心</option>
				<option value="预检分诊">yjfz - 后勤保障 - 预检分诊</option>
				<option value="运输中心">yszx - 后勤保障 - 运输中心</option>
				<option value="患者">hz - 患者 - 患者</option>
				<option value="疾控中心">jkzx - 疾控中心 - 疾控中心</option>
				<option value="ICU">icu - 临床科室 - ICU</option>
				<option value="儿科">ek - 临床科室 - 儿科</option>
				<option value="耳鼻喉科">ebhk - 临床科室 - 耳鼻喉科</option>
				<option value="肺病科">fbk - 临床科室 - 肺病科</option>
				<option value="妇科">fk - 临床科室 - 妇科</option>
				<option value="骨伤一科">gsyk - 临床科室 - 骨伤一科</option>
				<option value="骨伤二科">gsek - 临床科室 - 骨伤二科</option>
				<option value="骨伤三科">gssk - 临床科室 - 骨伤三科</option>
				<option value="骨伤四科">gssk - 临床科室 - 骨伤四科</option>
				<option value="急诊内科">jznk - 临床科室 - 急诊内科</option>
				<option value="急诊外科">jzwk - 临床科室 - 急诊外科</option>
				<option value="口腔科">kqk - 临床科室 - 口腔科</option>
				<option value="老年病科">lnbk - 临床科室 - 老年病科</option>
				<option value="麻醉科">mzk - 临床科室 - 麻醉科</option>
				<option value="脑病一科">nbyk - 临床科室 - 脑病一科</option>
				<option value="脑病二科">nbek - 临床科室 - 脑病二科</option>
				<option value="脑病三科">nbsk - 临床科室 - 脑病三科</option>
				<option value="脾胃病科">pwbk - 临床科室 - 脾胃病科</option>
				<option value="肾病科">sbk - 临床科室 - 肾病科</option>
				<option value="普通外科">pwk - 临床科室 - 普通外科</option>
				<option value="神经外科">sjwk - 临床科室 - 神经外科</option>
				<option value="糖尿病科">tnbk - 临床科室 - 糖尿病科</option>
				<option value="体检中心">tjzx - 医技科室 - 体检中心</option>
				<option value="透析室">txs - 临床科室 - 透析室</option>
				<option value="心病二科">xbek - 临床科室 - 心病二科</option>
				<option value="心病一科">xbyk - 临床科室 - 心病一科</option>
				<option value="眼一科">yyk - 临床科室 - 眼一科</option>
				<option value="眼二科">yek - 临床科室 - 眼二科</option>
				<option value="中风科">zfk - 临床科室 - 中风科</option>
				<option value="肿瘤科">zlk - 临床科室 - 肿瘤科</option>
				<option value="电视台">dst - 其他 - 电视台</option>
				<option value="行政后勤个人配发">xzhqgrpf - 其他 - 行政后勤个人配发</option>
				<option value="临床个人配发">lcgrpf - 其他 - 临床个人配发</option>
				<option value="调用">dy - 其他 - 调用</option>
				<option value="向阳社区">xysq - 社区 - 向阳社区</option>
				<option value="超声科">csk - 医技科室 - 超声科</option>
				<option value="检验科">jyk - 医技科室 - 检验科</option>
				<option value="手术室">sss - 医技科室 - 手术室</option>
				<option value="西药局">xyj - 医技科室 - 西药局</option>
				<option value="中药局">zyj - 医技科室 - 中药局</option>
				<option value="心电室">xds - 医技科室 - 心电室</option>
				<option value="影像科">yxk - 医技科室 - 影像科</option>
			</select>
			<input name="ptkz" title="普通口罩" dojoType="dijit.form.NumberTextBox" />
			<input name="wkkz" title="外科口罩" dojoType="dijit.form.NumberTextBox" />
			<input name="n95" title="N95口罩" dojoType="dijit.form.NumberTextBox" />
			<input name="klfhkz" title="颗粒防护口罩" dojoType="dijit.form.NumberTextBox" />
			<input name="fhf" title="防护服" dojoType="dijit.form.NumberTextBox" />
			<input name="gly" title="隔离衣" dojoType="dijit.form.NumberTextBox" />
			<input name="rjst" title="乳胶手套" dojoType="dijit.form.NumberTextBox" />
			<input name="mz" title="帽子" dojoType="dijit.form.NumberTextBox" />
			<input name="hmj" title="护目镜" dojoType="dijit.form.NumberTextBox" />
			<input name="xdy84" title="84消毒液" dojoType="dijit.form.NumberTextBox" />
			<input name="jj75" title="酒精75%" dojoType="dijit.form.NumberTextBox" />
			<input name="sxdj" title="手消毒剂" dojoType="dijit.form.NumberTextBox" />
			<input name="twj" title="体温计" dojoType="dijit.form.NumberTextBox" />
			<input name="jj7545" title="酒精75%(4.5L)" dojoType="dijit.form.NumberTextBox" />
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
