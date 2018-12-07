<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>menubutton</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>

</head>
<body>
	<script type="text/javascript">
		
	</script>
	<a href="javascript:void(0)" id="mb" class="easyui-menubutton"
		data-options="menu:'#mm',iconCls:'icon-edit'">控制面板</a>
	<div id="mm" style="width: 150px;">
		<div data-options="iconCls:'icon-edit'">修改密码</div>
		<div>联系管理员</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'icon-quit'">退出系统</div>
	</div>




</body>
</html>