<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	request.setCharacterEncoding("UTF-8");
%>
<div jsId="mainMenuBar" dojoType="dijit.Toolbar">
<table cellpadding="0" cellspaceing="0" border="0" width="100%">
	<tr>
		<td>
			<div dojoType="dijit.form.Button" iconClass="iconRepair">报修任务
				<script type="dojo/event" event="onClick" args="e">
					systemConfig.uri = "/Worksheet/Repair/Entry";
					bee.open();
				</script>
			</div>
			<div dojoType="dijit.form.Button" iconClass="iconVerified">检验申请(住院)
				<script type="dojo/event" event="onClick" args="e">
					systemConfig.uri = "/His/Test/CheckInHospitalApply";
					bee.open();
				</script>
			</div>
		</td>
		<td align="right">
			<span id="userInfo">${userInfo.name} ${userInfo.deptName}</span>
<!-- 
				<span dojoType="dijit.form.Button">登录
					<script type="dojo/event" event="onClick" args="e">
						bee.login.show();
					</script>
				</span>
 -->
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
		</td>
	</tr>
</table>
</div>