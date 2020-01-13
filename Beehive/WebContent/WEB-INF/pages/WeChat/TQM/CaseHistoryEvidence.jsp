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
function take(id, label) {
	wx.scanQRCode({
		desc: 'scanQRCode desc',
		needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
		scanType: [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有
		success: function(res) {
			var qrcode = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
			if (qrcode != null) { //验证码正确性
				wx.chooseImage({
					count: 1, // 默认9
					sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
					sourceType: ['camera', 'album'], // 可以指定来源是相册还是相机，默认二者都有
					success: function (res) {
						var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
						localIds.forEach(function (localId) {
							if (localId.indexOf("wxlocalresource") != -1) {
								localId = localId.replace("wxlocalresource", "wxLocalResource");
							}
							alert(localId);
							wx.uploadImage({
								localId: localId,
								isShowProgressTips: 1,
								success: function (res) {
									var serverId = res.serverId;
									alert(serverId);
									dojo.xhrPost({
										url: "/WeChat/TQM/TakeEvidence.PullPhoto.s2",
										handleAs: "json",
										content: {
											wxServerId: serverId,
											qrcode: "cn.org.jxszyyy.casehistory.evidence.1",//qrcode,
											itemNum: id,
											itemLabel: label
										}
									}).then(function (data) {
										if (data.success) {
											alert("取证失败！[" + data.errmsg + "]");
										}
									});
									alert("完成取证！");
								}
							});
						});
					}
				});
			} else {
				alert("错误的检查单码，请重新扫码！");
			}
		},
		error:function(res) {
			alert("扫码错误，请重试！");
		}
	});
}
require(
["dojo", "dojox/mobile/parser", "dojox/mobile/compat", "dojox/mobile/Button", "dojo/domReady!",
	"dojox/mobile/View", "dojox/mobile/Heading", "dojox/mobile/RoundRectList", "dojox/mobile/ListItem"],
function(dojo, parser) {
	dojo.ready(function() {
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
					jsApiList: ["scanQRCode", "chooseImage", "getLocalImgData", "uploadImage"]
				});
			}
		});
	});
});
wx.ready(function() {
});
wx.error(function(res) {
// 	alert(res);
});
</script>
</head>
<body>
<div id="view1" dojoType="dojox/mobile/View">
	<h1 data-dojo-type="dojox/mobile/Heading">病历问题取证</h1>
	<ul jsId="listGroup" data-dojo-type="dojox/mobile/RoundRectList">
	<s:iterator value="list" var="item">
		<li data-dojo-type="dojox/mobile/ListItem" data-dojo-props='transition:"slide", onClick:"take(<s:property value="id" />, \"<s:property value="label" />\")"'><s:property value="label" /></li>
	</s:iterator>
	</ul>
</div>
<div id="view2" data-dojo-type="dojox/mobile/View">
	<h2 data-dojo-type="dojox/mobile/Heading" data-dojo-props='back:"返回", moveTo:"view1"'>View 2</h1>
</div>
</body>
</html>
