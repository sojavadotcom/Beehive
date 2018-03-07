<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	request.setCharacterEncoding("UTF-8");
%>
<script type="text/javascript">
require(
[
	"dojo",
	"dijit/form/Form", "dijit/form/Button", "dijit/form/TextBox", "dijit/form/ValidationTextBox",
	"dojo/domReady!"
],
function(dojo) {
	dojo.ready(function() {
	});
});
</script>
<form jsId="loginForm" action="/User/Login.s2" dojoType="dijit.form.Form" method="post" class="login">
	<script type="dojo/method" event="onSubmit">
		if(this.validate()) {
			dojo.xhrPost({form: this.domNode, handleAs: 'json'}).then(function(result) {
				var msg = "";
				if (result.success) {
					bee.login.form.reset();
					systemConfig.isOnline = true;
					systemConfig.user.userName = result.userName;
					systemConfig.user.name = result.name;
					systemConfig.user.deptName = result.deptName;
					dojo.query("#userInfo").text(result.name + " " + result.deptName);
					bee.login.close();
					systemConfig.isPInit = result.isPInit;
					if (result.isPInit) {
						bee.password.show();
						bee.alert({success: false, message: "密码未设置，请设置您的登录密码"});
					}
				} else {
					bee.alert(result);
				}
			});
		}
		return false;
	</script>
	<div class="item">
		<label style="font-weight: bold;">用户名：</label>
		<input name="userName" dojoType="dijit.form.TextBox" type="hidden"/>
		<span name="name" type="text" dojoType="dijit.form.ValidationTextBox" trim="true" selectOnClick="true" required="required" missingMessage="请填写用户名" message="请填写用户名">
			<script type="dojo/method" event="onChange" args="value">
				if (this.checkable == "false" || value == "") return;
				bee.login.form.setValues({'userName': value});
				var me = this;
				dojo.xhrPost({
					url: '/User/Query.name.s2',
					handleAs: 'json',
					content: {'value': value}
				}).then(function(result) {
					var _userName = "";
					var _name = "";
					if (result.success) {
						_userName = result.userName;
						_name = result.name;
					} else {
						_userName = "";
						_name = result.message;
					}
					me.set("checkable", "false");
					bee.login.form.setValues({'userName': _userName, 'name': _name, 'password': ''});
					setTimeout(dojo.hitch(me, function() {
						this.set("checkable", "true");
					}), 500);
				});
			</script>
		</span>
	</div>
	<div class="item">
		<label style="font-weight: bold;">密　码：</label>
		<input name="password" type="password" dojoType="dijit.form.ValidationTextBox"/>
	</div>
</form>
<div class="dijitDialogPaneActionBar" style="text-align: right;">
	<button dojoType="dijit.form.Button">登录
		<script type="dojo/method" event="onClick">
			bee.login.form.submit();
		</script>
	</button>
	<button dojoType="dijit.form.Button">重填
		<script type="dojo/method" event="onClick">
			bee.login.form.reset();
		</script>
	</button>
</div>