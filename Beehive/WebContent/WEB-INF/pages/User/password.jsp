<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	request.setCharacterEncoding("UTF-8");
%>
<form jsId="passwordForm" dojoType="dijit.form.Form" action="/User/Password.s2" method="post" class="password">
	<input name="action" type="hidden" dojoType="dijit.form.TextBox" value="save"/>
	<div data-dojo-type="dojox/form/PasswordValidator" name="password" oldName="oldPassword">
		<script type="dojo/method" event="pwCheck" args="password">
			//判断原密码事件
			if (password == "--------------------") return false;
			dojo.xhrPost({
				url: '/User/Password.s2',
				handleAs: 'json',
				content: {action: 'validPassword', password: password},
				async: false
			}).then(function (result) {
				if (result.success) {
					if (!result.accept) {
						var obj = dojo.query(dojo.query(passwordForm.domNode).query("[type=password]")[0]);
						obj.val("--------------------");
						with(obj[0]) {
							focus();
							select();
						}
					}
				} else {
					bee.alert(result);
				}
			});
			return true;
		</script>
		<div>
			<label>　原密码：</label><input type="password" pwType="old"/>
		</div>
		<div>
			<label>　新密码：</label><input type="password" pwType="new"/>
		</div>
		<div style="line-height: 25px;">
			<ul><li style="margin: 0px; background: none;">　　　　　</li><li>弱</li><li>中</li><li>强</li></ul>
		</div>
		<div>
			<label>确认密码：</label><input type="password" pwType="verify"/>
		</div>
	</div>
	<script type="dojo/method" event="onShow">
		dojo.query(dojo.query(passwordForm.domNode).query("[type=password]")[1]).onkeyup(function(e) {
			var pwd = this.value;
			var bars = dojo.query(passwordForm.domNode).query("ul li");
			var loop = 0;
			loop = parseInt(bee.password.strength(pwd)/10/3);
			bars.forEach(function(_item, _index, _items) {
				if (_index == 0) return false;
				dojo.query(_item).style("background", "#CCCCCC");
			});
			var colors = ["none", "#D82918", "#C69E00", "#245100"];
			for (var i = 1; i <= loop; i ++) {
				with(dojo.query(bars[i])) {
					style("background", colors[i]);
					style("color", "white");
				}
			}
		});
	</script>
	<script type="dojo/method" event="onSubmit">
		if (this.validate()) {
			bee.confirm("是否确认？", "是否确认修改密码？", function(b) {
				if (b) {
					dojo.xhrPost({
						url: '/User/Password.s2',
						handleAs: 'json',
						form: passwordForm.domNode
					}).then(function(result) {
						if (result.success) {
							systemConfig.isPInit = false;
							bee.password.close();
						} else {
							bee.alert(result);
						}
					});
				} else {
					//do nothing
				}
			});
		}
		return false;
	</script>
</form>
<div class="dijitDialogPaneActionBar" style="text-align: center;">
	<button dojoType="dijit.form.Button">确认
		<script type="dojo/method" event="onClick" args="e">
			passwordForm.submit();
		</script>
	</button>
	<button dojoType="dijit.form.Button">取消
		<script type="dojo/method" event="onClick" args="e">
			bee.password.close();
		</script>
	</button>
</div>