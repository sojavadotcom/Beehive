<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	request.setCharacterEncoding("UTF-8");
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>鸡西市中医医院</title>
<link rel="icon" href="/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no" />
<meta name="apple-mobie-web-app-capable" content="yes" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="pragma" content="no-cache" />
<script src="https://res.wx.qq.com/open/js/jweixin-1.6.0.js"></script>
<script src="/components/dojo/dojox/mobile/deviceTheme.js" data-dojo-config="mblUserAgent: 'Holodark'"></script>
<script src="/components/dojo/dojo/dojo.js" data-dojo-config="locale: 'zh-cn', async: true, parseOnLoad: true, isDebug: true, gfxRenderer: 'svg,silverlight,vml'"></script>
<script type="text/javascript">
require(
["dojo", "dojox/mobile/parser", "dojox/mobile/compat", "dojox/mobile/Button",
	"dijit/form/DataList",
	"dojox/mobile/View", "dojox/mobile/Heading", "dojox/mobile/TextBox", "dojox/mobile/ComboBox",
	"dojo/domReady!"],
function(dojo, parser) {
	dojo.ready(function() {
/*
		dojo.xhrGet({
			url : "/WeChat/Token.TQM.s2",
			handleAs : "json",
			content: {'url': location.href.split('#')[0]}
		}).then(function (data) {
			if (data.success) {
				var prop = data.data;
				wx.config({
					debug: false,
					appId: prop.appId,
					timestamp: prop.timestamp,
					nonceStr: prop.nonceStr,
					signature: prop.signature,
					jsApiList: ["getLocalImgData"]
				});
			}
		});
*/
	});
});
wx.ready(function(data) {
	console.log(data);
});
wx.error(function(res) {
// 	alert(res);
});
</script>
</head>
<body>
<div id="view1" dojoType="dojox.mobile.View">
	<h1 dojoType="dojox.mobile.Heading">职工认证</h1>
	<input dojoType="dojox.mobile.TextBox" placeHolder="姓名" style="width: 100%" />
	<input dojoType="dojox.mobile.TextBox" placeHolder="医院登记手机号码" style="width: 100%" />
	<select dojoType="dijit.form.DataList" data-dojo-props='id:"deptDataList"' style="display: none">
		<option value="领导班子">领导班子</option>
		<option value="办公室">办公室</option>
		<option value="宣传科">宣传科</option>
		<option value="工会">工会</option>
		<option value="财务部">财务部</option>
		<option value="人力资源科">人力资源科</option>
		<option value="组织科">组织科</option>
		<option value="纪检监察室">纪检监察室</option>
		<option value="审计科">审计科</option>
		<option value="医务部">医务部</option>
		<option value="感染科">感染科</option>
		<option value="科教部">科教部</option>
		<option value="器械科">器械科</option>
		<option value="信息科">信息科</option>
		<option value="统购办">统购办</option>
		<option value="医保科">医保科</option>
		<option value="门诊部">门诊部</option>
		<option value="社区">社区</option>
		<option value="安全保卫部">安全保卫部</option>
		<option value="后勤维修部">后勤维修部</option>
		<option value="基建办">基建办</option>
		<option value="护理部">护理部</option>
		<option value="质量管理办公室">质量管理办公室</option>
		<option value="绩效考核管理办公室">绩效考核管理办公室</option>
		<option value="洗衣中心">洗衣中心</option>
		<option value="餐饮中心">餐饮中心</option>
		<option value="保洁部">保洁部</option>
		<option value="脑病一科">脑病一科</option>
		<option value="脑病二科">脑病二科</option>
		<option value="脑病三科">脑病三科</option>
		<option value="心病一科">心病一科</option>
		<option value="心病二科">心病二科</option>
		<option value="糖尿病一科">糖尿病一科</option>
		<option value="糖尿病二科">糖尿病二科</option>
		<option value="糖尿病护理组">糖尿病护理组</option>
		<option value="脾胃病科">脾胃病科</option>
		<option value="肾病科">肾病科</option>
		<option value="脾胃病科护理组">脾胃病科护理组</option>
		<option value="肿瘤科">肿瘤科</option>
		<option value="肺病科">肺病科</option>
		<option value="肿瘤科护理组">肿瘤科护理组</option>
		<option value="老年病科">老年病科</option>
		<option value="儿科">儿科</option>
		<option value="眼科一科">眼科一科</option>
		<option value="眼科二科">眼科二科</option>
		<option value="耳鼻喉科">耳鼻喉科</option>
		<option value="妇科一科">妇科一科</option>
		<option value="妇科二科">妇科二科</option>
		<option value="骨伤一科">骨伤一科</option>
		<option value="骨伤二科">骨伤二科</option>
		<option value="骨伤三科">骨伤三科</option>
		<option value="骨伤四科">骨伤四科</option>
		<option value="急诊内科">急诊内科</option>
		<option value="口腔科">口腔科</option>
		<option value="麻醉科">麻醉科</option>
		<option value="手术室">手术室</option>
		<option value="皮肤一科">皮肤一科</option>
		<option value="皮肤二科">皮肤二科</option>
		<option value="普通外科">普通外科</option>
		<option value="泌尿外一科">泌尿外一科</option>
		<option value="泌尿外二科">泌尿外二科</option>
		<option value="肛肠科">肛肠科</option>
		<option value="普外科护理组">普外科护理组</option>
		<option value="神经外二科">神经外二科</option>
		<option value="神经外科护理组">神经外科护理组</option>
		<option value="ICU">ICU</option>
		<option value="体检中心">体检中心</option>
		<option value="透析室">透析室</option>
		<option value="针灸科">针灸科</option>
		<option value="治未病中心">治未病中心</option>
		<option value="中风科">中风科</option>
		<option value="心电室">心电室</option>
		<option value="脑电室">脑电室</option>
		<option value="肌电室">肌电室</option>
		<option value="影像科">影像科</option>
		<option value="彩超室">彩超室</option>
		<option value="脑电室">脑电室</option>
		<option value="肌电室">肌电室</option>
		<option value="药学部">药学部</option>
		<option value="检验科">检验科</option>
		<option value="供应室">供应室</option>
	</select>
	<input type="text" dojoType="dojox.mobile.ComboBox" list="deptDataList" placeHolder="所属部门或科室" style="width: 100%" />
</div>
</body>
</html>
