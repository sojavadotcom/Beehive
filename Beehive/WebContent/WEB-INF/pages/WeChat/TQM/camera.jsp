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
<!-- 
<link rel="stylesheet" type="text/css" href="/components/dojo/dijit/themes/dijit.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="/components/dojo/dijit/themes/claro/claro.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="/components/dojo/dojox/widget/Dialog/Dialog.css"/>
 -->
<script src="https://res.wx.qq.com/open/js/jweixin-1.6.0.js"></script>
<script src="/components/dojo/dojo.js" data-dojo-config="locale: 'zh-cn', async: true, parseOnLoad: true, isDebug: true, gfxRenderer: 'svg,silverlight,vml'"></script>
<script type="text/javascript">
require(
["dojox/mobile/parser", "dojox/mobile/compat", "dojox/mobile/Button", "dojo/domReady!"],
function(parser) {
	parser.parse();
/*
	dojo.ready(function() {
		alert(1);
		dojo.xhrGet({
			url : "/WeChat/TQM/Query.Signature.s2",
			handleAs : "json",
			content: {'url': location.href.split('#')[0]}
		}).then(function (data) {
			if (data.success) {
				var prop = data.data;
				alert(prop.signature);
				wx.config({
					debug: true,
					appId: prop.appId,
					timestamp: prop.timestamp,
					nonceStr: prop.nonceStr,
					signature: prop.signature,
					jsApiList: ["chooseImage"]
				});
			}
		});
	});
*/
});
function take() {
	wx.chooseImage({
		count: 1, // 默认9
		sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
		sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
		success: function (res) {
			var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
			alert(localIds);
		}
	});
}
wx.ready(function() {
	alert("接口处理成功验证");
});
wx.error(function(res) {
	alert(res);
});
</script>
</head>
<body class="claro">
<div style="text-align: center">
	<button data-dojo-type="dojox/mobile/Button" label="11">拍照</button>
</div>
</body>
</html>
