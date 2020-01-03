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
<script src="/components/dojo/dojo/dojo.js" data-dojo-config="locale: 'zh-cn', async: true, parseOnLoad: true, isDebug: true, gfxRenderer: 'svg,silverlight,vml'"></script>
<script type="text/javascript">
function take() {
	wx.chooseImage({
		count: 1, // 默认9
		sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
		sourceType: ['camera', 'album'], // 可以指定来源是相册还是相机，默认二者都有
		success: function (res) {
			var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
			alert(localIds);
		}
	});
}
require(
["dojo", "dojox/mobile/parser", "dojox/mobile/compat", "dojox/mobile/Button", "dojo/domReady!"],
function(dojo, parser) {
	dojo.ready(function() {
		dojo.xhrGet({
			url : "/WeChat/TQM/Query.Signature.s2",
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
					jsApiList: ["chooseImage"]
				});
			}
		});
	});
});
wx.ready(function() {
// 	take();
});
wx.error(function(res) {
	alert(res);
});
</script>
</head>
<body class="claro">
<div style="text-align: center">
	<button id="takeBtn" jsId="takeBtn" dojoType="dojox.mobile.Button" label="拍照" onclick="take()"></button>
</div>
</body>
</html>
