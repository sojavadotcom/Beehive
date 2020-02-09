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
	"dojo/domReady!"
],
function(dojo) {
	dojo.ready(function() {
	});
});
</script>
<div js="mainNavMenu" dojoType="dijit.MenuBar">
	<div dojoType="dijit.MenuBarItem">
		<span>Dashboard</span>
		<script type="dojo/event" event="onClick" args="e">
			bee.open("/welcome.shtml");
		</script>
	</div>
	<div dojoType="dijit.MenuBarItem">
		<span>RSS</span>
		<script type="dojo/event" event="onClick" args="e">
			bee.open("/Catcher/Index.shtml", true);
		</script>
	</div>
	<div dojoType="dijit.MenuBarItem">
		<span>影像科绩效核算</span>
		<script type="dojo/event" event="onClick" args="e">
			bee.open("/MedicalImaging/Entry.shtml");
		</script>
	</div>
	<div dojoType="dijit.PopupMenuBarItem">
		<span>Ncov</span>
		<div dojoType="dijit.DropDownMenu">
			<div dojoType="dijit.MenuItem" iconClass="iconRepair">物资统计
				<script type="dojo/event" event="onClick" args="e">
					bee.open("/Data/Ncov/Entry.Goods.shtml", true);
				</script>
			</div>
		</div>
	</div>
	<div dojoType="dijit.PopupMenuBarItem">
		<span>质控</span>
		<div dojoType="dijit.DropDownMenu">
			<div dojoType="dijit.MenuItem" iconClass="iconVerified">门诊退费登记与查询
				<script type="dojo/event" event="onClick" args="e">
					bee.open("/Worksheet/Refund/index");
				</script>
			</div>
		</div>
	</div>
	<div dojoType="dijit.PopupMenuBarItem">
		<span>检验查询</span>
		<div dojoType="dijit.DropDownMenu">
			<div dojoType="dijit.MenuItem" iconClass="iconVerified">检验申请(住院)
				<script type="dojo/event" event="onClick" args="e">
					bee.open("/Anyihis/Test/CheckInHospitalApply");
				</script>
			</div>
		</div>
	</div>
	<div dojoType="dijit.PopupMenuBarItem">
		<span>门诊查询</span>
		<div dojoType="dijit.DropDownMenu">
			<div dojoType="dijit.MenuItem" iconClass="iconVerified">...
				<script type="dojo/event" event="onClick" args="e">
					bee.open("/welcome.html");
				</script>
			</div>
		</div>
	</div>
	<div dojoType="dijit.PopupMenuBarItem">
		<span>住院查询</span>
		<div dojoType="dijit.DropDownMenu">
			<div dojoType="dijit.MenuItem" iconClass="iconVerified">在院一级与重患查询
				<script type="dojo/event" event="onClick" args="e">
					bee.open("/Anyihis/InHospital/indexCriticalPatient");
				</script>
			</div>
			<div dojoType="dijit.MenuItem" iconClass="iconVerified">在院患者退费查询
				<script type="dojo/event" event="onClick" args="e">
					bee.open("/Anyihis/InHospital/indexRefund");
				</script>
			</div>
		</div>
	</div>
	<div dojoType="dijit.PopupMenuBarItem">
		<span>后勤保障</span>
		<div dojoType="dijit.DropDownMenu">
			<div dojoType="dijit.MenuItem" iconClass="iconRepair">报修任务
				<script type="dojo/event" event="onClick" args="e">
					bee.open("/Worksheet/Repair/Entry");
				</script>
			</div>
		</div>
	</div>
</div>
<div dojoType="dijit.layout.BorderContainer" design="sidebar" gutters="false" style="width: 100%; height: 50%;">
	<div dojoType="dijit.layout.ContentPane" region="center" style="margin: 0px; padding: 0px; overflow: hidden;">
		<div jsId="mainToolbar" dojoType="dijit.Toolbar"><span dojoType="dijit.form.Button">　</span></div>
	</div>
	<div dojoType="dijit.layout.ContentPane" region="right" style="margin: 0px; padding: 0px; overflow: hidden; width: 370px">
		<div dojoType="dijit.Toolbar">
			<span id="userInfo">${userInfo.name} ${userInfo.deptName}</span>
			<span dojoType="dijit.form.Button">登录
				<script type="dojo/event" event="onClick" args="e">
					bee.login.show();
				</script>
			</span>
			<span dojoType="dijit.form.Button" iconClass="dijitIconKey">修改密码
				<script type="dojo/event" event="onClick" args="e">
					bee.password.show();
				</script>
			</span>
			<span dojoType="dijit.form.Button" iconClass="dijitIconError">退出
				<script type="dojo/event" event="onClick" args="e">
					bee.logout();
				</script>
			</span>
		</div>
	</div>
</div>
