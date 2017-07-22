var bee = {
	alert: function(e) {
		var msg = "";
		if (typeof e == "string") msg = e;
		else msg = e.message||"";

		if (msg == "") return fasle;

		if (typeof e.loginTimeout != "undefined" && e.loginTimeout) {
			systemConfig.isOnline = false;
			bee.login.show();
		}

		require(["dojox/dialog/AlertDialog"], function (AlertDialog) {
			var dialog = new AlertDialog({message: msg});
			dialog.startup();
			dialog.show();
		});
	},
	confirm: function(title, msg, fn) {
		require(["dojox/dialog/ConfirmDialog"], function (ConfirmDialog) {
			var dialog = new ConfirmDialog({
				title: title,
				message: msg,
				defaultReturnValue: false,
				onButtonClick: fn
			});
			dialog.startup();
			dialog.show();
		});
	},
	login: {
		inited: false,
		obj: null,
		initialize: function() {
			require(["dijit/Dialog"], function(Dialog) {
				bee.login.obj = new Dialog({
					closeable: false,
					title: '系统登录',
					href: '/User/Entry.shtml?action=login',
					onHide: function () {
						if (!systemConfig.isOnline) bee.login.show();
					}
				});
				bee.login.obj.startup();
				bee.login.obj.show();
				bee.login.inited = true;
			});
		},
		show: function() {
			if (!bee.login.inited) bee.login.initialize();
			else bee.login.obj.show();
		},
		close: function() {
			bee.login.obj.hide();
		},
		form: {
			getValues: function() {
				return loginForm.getValues();
			},
			setValues: function(values) {
				loginForm.setValues(values);
			},
			submit: function() {
				loginForm.submit();
			},
			reset: function() {
				loginForm.reset();
			}
		}
	},
	logout: function() {
		dojo.xhrPost({url: '/User/Logout.s2',handleAs: 'json'}).then(function(result) {
			if (result.success) {
				systemConfig.isOnline = false;
				systemConfig.user.userName = systemConfig.user.name = systemConfig.user.deptName = "";
				dojo.query("#userInfo").text("");
				bee.login.show();
			} else {
				msg = result.message;
				require(["dojox/dialog/AlertDialog"], function (AlertDialog) {
					var alertDialog = new AlertDialog({message: msg});
					alertDialog.startup();
					alertDialog.show();
				});
			}
		});
	},
	password: {
		obj: null,
		initialize: function() {
			require(["dijit/Dialog"], function(Dialog) {
				bee.password.obj = new Dialog({
					closeable: true,
					title: '修改密码',
					href: '/User/Entry.shtml?action=password',
					onHide: function() {
						if (systemConfig.isPInit) bee.password.obj.show();
						else {
							setTimeout(dojo.hitch(this, function() {
								this.destroyRecursive();
							}), 0);
						}
					}
				});
				bee.password.obj.startup();
			});
		},
		show: function() {
			bee.password.initialize();
			bee.password.obj.show();
		},
		close: function() {
			bee.password.obj.hide();
		},
		strength: function(H) {
			var D = H.length;
			if (D > 5) D = 5;
			var F = H.replace(/[0-9]/g, "");
			var G = H.length - F.length;
			if (G > 3) G = 3;
			var A = H.replace(/\W/g, "");
			var C = H.length - A.length;
			if (C > 3) C = 3;
			var B = H.replace(/[A-Z]/g, "");
			var I = H.length - B.length;
			if (I > 3) I = 3;
			var E = ((D * 10) - 20) + (G * 10) + (C * 15) + (I * 10);
			if(E < 0) E = 0;
			if(E > 100) E = 100;

			return E;
		}
	},
	open: function(uri) {
//		bee.clear(mainToolbar);
		systemConfig.uri = uri || systemConfig.uri || (systemConfig.isOnline ? "/welcome.html" : "/welcome.html");
		dijit.byId("box").set("href", (systemConfig.uri.indexOf(".") == -1 ? systemConfig.uri + ".shtml" : systemConfig.uri));
	},
	clear: function(tb) {
		while (tb.getChildren().length > 0) {
			tb.getChildren()[0].destroy();
		}
	}
};
require(
[
	"dojo", "dojo/parser", "dojo/dom",
	"dojo/NodeList-traverse", "dojo/NodeList-html", "dojo/NodeList-data", "dojo/NodeList-dom", "dojo/NodeList-manipulate",
	"dijit/layout/ContentPane", "dojox/layout/ContentPane", "dijit/layout/BorderContainer", "dijit/layout/SplitContainer", "dijit/layout/LayoutContainer", "dijit/layout/AccordionContainer", "dijit/layout/AccordionPane",
	"dijit/Toolbar", "dijit/ToolbarSeparator",
	"dijit/MenuBar", "dijit/DropDownMenu", "dijit/PopupMenuBarItem", "dijit/PopupMenuItem", "dijit/MenuItem", "dijit/Dialog",
	"dijit/form/Form", "dijit/form/Button", "dijit/form/TextBox", "dijit/form/ValidationTextBox",
	"dojo/domReady!"
],
function(dojo) {
	dojo.ready(function() {
		if (!dojo.isIE && !dojo.isFF && !dojo.isChrome && !dojo.isSafari) {
			var userAgent = navigator.userAgent.toLowerCase();
			var ieReg = /(msie\s|trident.*rv:)([\w.]+)/;
			var match = ieReg.exec(userAgent);
			dojo.isIE = match ? match[2] - 0 : undefined;
		}
		if (!systemConfig.isOnline) bee.login.show();
		else if (systemConfig.isPInit) bee.password.show();
		bee.open();
	});
});
