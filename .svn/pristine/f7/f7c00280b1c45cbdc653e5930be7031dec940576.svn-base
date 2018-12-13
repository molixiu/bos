<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>tabs</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>

</head>
<body>
	<table class="easyui-datagrid" style="width: 400px; height: 250px"
		data-options="url:'${pageContext.request.contextPath}/json/staff.json',fitColumns:true,singleSelect:true">
		<thead>
			<tr>
				<th data-options="field:'id',width:100">编码</th>
				<th data-options="field:'name',width:100">名称</th>
				<th data-options="field:'telephone',width:200">价格</th>
			</tr>
		</thead>
	</table>
	<hr>
	<table class="easyui-datagrid">

	</table>
	<script type="text/javascript" id="datagrid">
		$(function() {
			$('#datagrid').datagrid({
				url : '${pageContext.request.contextPath}/json/staff.json',
				columns : [ [ {
					field : 'id',
					title : 'id',
				}, {
					field : 'name',
					title : 'name',
				}, {
					field : 'telephone',
					title : 'telephone',
				} ] ],
				toolbar : [ {
					'text' : '编辑',
					iconCls: 'icon-edit',
					handler: function(){alert('编辑按钮')}
				}, {
					'text' : '编辑'
				}, {
					'text' : '编辑'
				} ],
				rownumbers:true,
				pagination:true,
			});
		});
	</script>
</body>
</html>