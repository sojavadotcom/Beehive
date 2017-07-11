<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	request.setCharacterEncoding("UTF-8");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>鸡西市中医医院</title>
<link rel="icon" href="/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="/components/dojo/dijit/themes/dijit.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="/components/dojo/dijit/themes/claro/claro.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="/components/dojo/dojox/widget/Dialog/Dialog.css"/>
<link rel="stylesheet" type="text/css" href="/components/dojo/dojox/grid/enhanced/resources/claroEnhancedGrid.css"/>
<link rel="stylesheet" type="text/css" href="/components/dojo/dojox/grid/enhanced/resources/claro/EnhancedGrid.css"/>
<link rel="stylesheet" type="text/css" href="/css/base.css" media="screen"/>
<script type="text/javascript">
var systemConfig = {
	isOnline: ${online},
	isPInit: ${PInit},
	user: {
		userName: '${userInfo.userName}',
		name: '${userInfo.name}',
		deptName: '${userInfo.deptName}'
	},
	taskScheduler: {}
};
</script>
<script src="/components/dojo/dojo/dojo.js" data-dojo-config="locale: 'zh-cn', async: true, parseOnLoad: true, isDebug: false, gfxRenderer: 'svg,silverlight,vml'"></script>
<script src="/modules/index.js"></script>
</head>
<body class="claro">
	<div dojoType="dijit.layout.BorderContainer" design="sidebar" gutters="false" style="width: 100%; height: 100%;">
		<div id="top" dojoType="dojox.layout.ContentPane" href="/Menu/Entry.shtml" region="top" style="margin: 0px; padding: 0px; height: 70px; overflow: hidden;"></div>
		<div id="box" dojoType="dojox.layout.ContentPane" region="center"></div>
	</div>
</body>
</html>