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
<link rel="stylesheet" type="text/css" href="/components/dojo/dijit/themes/dijit.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="/components/dojo/dijit/themes/claro/claro.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="/components/dojo/dojox/widget/Dialog/Dialog.css"/>
<link rel="stylesheet" type="text/css" href="/css/base.css" media="screen"/>
<script src="https://res.wx.qq.com/open/js/jweixin-1.6.0.js"></script>
<script src="/components/dojo/dojo/dojo.js" data-dojo-config="locale: 'zh-cn', async: true, parseOnLoad: true, isDebug: false, gfxRenderer: 'svg,silverlight,vml'"></script>
<script type="text/javascript">
require(
[
	"dojo",
	"dojo/domReady!"
],
function(dojo) {
	dojo.ready(function() {
		alert('<s:property value="appId" />');
		alert('<s:property value="timestamp" />');
	});
});
</script>
</head>
<body class="claro">
	<div jsId="take" dojoType="dijit.form.Button">
	拍照
	<script type="dojo/method" event="onClick" args="event">
/*
		wx.config({
			debug: true,
			appId: '<s:property value="appId" />',
			timestamp: <s:property value="timestamp" />,
			nonceStr: '<s:property value="nonceStr" />',
			signature: '<s:property value="signature" />',
			jsApiList: []
		});
*/
	</script>
	</div>
</body>
</html>
