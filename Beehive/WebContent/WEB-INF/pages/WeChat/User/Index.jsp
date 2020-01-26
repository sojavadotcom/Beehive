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
	"dojox/mobile/View", "dojox/mobile/Heading", "dojox/mobile/GridLayout", "dojox/mobile/Pane",
	"dojo/domReady!"],
function(dojo, parser) {
	dojo.ready(function() {
	});
});
</script>
</head>
<body>
<div id="view1" dojoType="dojox.mobile.View">
	<div dojoType="dojox.mobile.GridLayout" cols="1">
		<div dojoType="dojox.mobile.Heading" style="line-height: 1em; font-size: .9em;">
			<div><img src="D:/Pictures/头像/头像2.jpg" style="height: 7em" /></div>
			<div><label>姓名</label> <label>职称</label></div>
			<div><label>科室</label> <label>职务</label></div>
		</div>
		<ul dojoType="dojox.mobile.IconMenu" style="width: 100%;" cols="3">
			<li dojoType="dojox.mobile.IconMenuItem" label="病历检查" icon="/images/icons/128/repair.png"></li>
			<li dojoType="dojox.mobile.IconMenuItem" label="病历检查" icon="/images/icons/128/repair.png"></li>
			<li dojoType="dojox.mobile.IconMenuItem" label="病历检查" icon="/images/icons/128/repair.png"></li>
			<li dojoType="dojox.mobile.IconMenuItem" label="病历检查" icon="/images/icons/128/repair.png"></li>
		</ul>
	</div>
</div>
</body>
</html>
