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
<script src="/components/dojo/dojox/mobile/deviceTheme.js" data-dojo-config="mblUserAgent: 'Holodark'"></script>
<script src="/components/dojo/dojo/dojo.js" data-dojo-config="locale: 'zh-cn', async: true, parseOnLoad: true, isDebug: true, gfxRenderer: 'svg,silverlight,vml'"></script>
<script type="text/javascript">
require(
["dojo", "dojox/mobile/parser", "dojox/mobile/compat",
	"dijit/form/DataList",
	"dojox/mobile/View", "dojox/mobile/Heading", "dojox/mobile/FormLayout", "dojox/mobile/GridLayout", "dojox/mobile/Pane", "dojox/mobile/TextBox",
	"dojox/mobile/Button",
	"dojo/domReady!"],
function(dojo, parser) {
	dojo.ready(function() {
	});
});
function signup() {
// 	var data = {};
	var list = dojo.query("#signupFrm [required='true']");
	for (var i = 0; i < list.length; i ++) {
		var obj = dojo.query(list[i]);

		var name = obj.attr("name").toString();
		var val = obj.attr("value").toString();
		var required = obj.attr("required");
		var errmsg = obj.attr("errmsg").toString();

		if (required && val.trim() == "") {
			alert(errmsg);
			obj[0].focus();
			return false;
// 		} else {
// 			data[name] = val;
		}
	}
// 	data.code = dojo.query("#signupFrm [name='code']")[0].value;
	dojo.query("#signupFrm")[0].submit();
/*
	dojo.xhrPost({
		url : "/WeChat/User/Signup.s2",
		handleAs : "json",
		content: data
	}).then(function (data) {
		
	});
*/
}
</script>
</head>
<body>
<div id="view1" dojoType="dojox.mobile.View">
	<h1 dojoType="dojox.mobile.Heading">
		<div dojoType="dojox.mobile.GridLayout" cols="2">
			<div dojoType="dojox.mobile.Pane" align="left">&nbsp;<label>信息登记</label></div>
			<div dojoType="dojox.mobile.Pane" align="right">
				<button dojoType="dojox.mobile.Button" class="mblBlueButton" onClick="signup()" style="width: 5em">登记</button>
			</div>
		</div>
	</h1>
	<form jsId="signupFrm" id="signupFrm" action="/WeChat/User/Signup.s2" dojoType="dojox.mobile.FormLayout" columns="auto" rightAlign="false">
		<input type="hidden" name="code" value="<s:property value="user.openid" />" />
		<div>
			<label>姓名：</label>
			<fieldset>
				<input name="user.staffName" dojoType="dojox.mobile.TextBox" trim="true" required="true" errmsg="必须填写姓名！" />
			</fieldset>
		</div>
		<div>
			<label>所在科室：</label>
			<fieldset>
				<input name="user.deptName" dojoType="dojox.mobile.TextBox" trim="true" required="true" errmsg="必须填写科室！"  />
			</fieldset>
		</div>
		<div>
			<label>集团小号：</label>
			<fieldset>
				<input name="user.mobileShortNumber" dojoType="dojox.mobile.TextBox" trim="true" required="true" errmsg="必须填写院内小号，没有小号填 0 ！"  />
			</fieldset>
		</div>
		<div>
			<label>手机号码：</label>
			<fieldset>
				<input name="user.mobileNumber" dojoType="dojox.mobile.TextBox" trim="true" required="true" errmsg="必须填写手机号码！"  />
			</fieldset>
		</div>
	</form>
</div>
</body>
</html>
