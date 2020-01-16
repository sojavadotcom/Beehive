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
<style type="text/css">
 .mblCarouselItemHeaderText {
 	white-space: nowrap;
 	text-overflow: ellipsis;
 }
 .mblCarouselSlotSelected .mblCarouselItemImage {
 	opacity: 1 !important;
 }
</style>
<script type="text/javascript">
require(
["dojo", "dojox/mobile/parser", "dojox/mobile/compat", "dojox/mobile/Button", "dojo/domReady!",
	"dojox/mobile/View", "dojox/mobile/Heading", "dojox/mobile/RoundRectList", "dojox/mobile/ListItem",
	"dojox/mobile/Carousel", "dojox/mobile/CarouselItem", "dojox/mobile/SwapView",
	"dojox/mobile/Button"],
function(dojo, parser) {
	dojo.ready(function() {
		dojo.query(".mblCarouselItemImage").style("width", dojo.query("#carousel").style("width") + "px");
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
					jsApiList: ["scanQRCode", "previewImage"]
				});
			}
		});
	});
});
wx.ready(function() {
	<s:if test="list.size == 0">
	scanQRCode();
	</s:if>
});
function scanQRCode() {
	wx.scanQRCode({
		desc: 'scanQRCode desc',
		needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
		scanType: [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有
		success: function(res) {
			var qrcode = res.resultStr;
			window.location.href = window.location.href.toString() + "?qrcode=" + qrcode;
		}
	});
}
function previewPhoto(id) {
	var url = "https://wx.jxszyyy.org.cn/WeChat/TQM/TakeEvidence.Photo.shtml?id=";
	wx.previewImage({
		current: url + id,
		urls: [
			<s:iterator value="list" var="photo">
			url + <s:property value="id" />,
			</s:iterator>
		]
	});
}
</script>
</head>
<body>
<div id="carousel" dojoType="dojox.mobile.Carousel" navButton=false numVisible=<s:property value="list.size" /> title="病历问题取证照片" height="30em">
	<s:if test="list.size == 0">
		<button dojoType="dojox.mobile.Button" onClick="scanQRCode()" style="margin-top: 10%">扫描单号</button>
	</s:if>
	<s:iterator value="list" var="photo">
	<div dojoType="dojox/mobile/SwapView" style="text-align: center; width: 100%">
		<div dojoType="dojox.mobile.CarouselItem" onClick="previewPhoto(<s:property value="id"/>)" style="width: 100%;" data-dojo-props='src:"data:image/jpeg;base64,<s:property value="photo" />", value:"<s:property value="num"/>.&nbsp;<s:property value="label"/>", headerText:"<s:property value="num"/>.&nbsp;<s:property value="label"/>"'></div>
	</div>
	</s:iterator>
</div>
</body>
</html>
