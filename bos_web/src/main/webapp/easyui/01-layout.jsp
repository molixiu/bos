<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>layout</title>
<!-- easyui的相关引入 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<!-- ztree的相关引入 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',title:'北部',split:true"
		style="height: 100px;"></div>
	<div data-options="region:'south',title:'南部 Title',split:true"
		style="height: 100px;"></div>
	<div
		data-options="region:'east',iconCls:'icon-reload',title:'东部',split:true"
		style="width: 100px;"></div>
	<div data-options="region:'west',title:'西部',split:true"
		style="width: 160px;">
		<div id="accordion" class="easyui-accordion"
			style="width: 300px; height: 200px;" data-options="fit:'true'">
			<div title="面板1" data-options="iconCls:'icon-save',fit:'true',"
				style="overflow: auto; padding: 10px;">
				<a id="btn" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-search'">系统管理</a>
				<script type="text/javascript">
					$("#btn")
							.click(
									function() {
										var if_exit = $("#tabs").tabs('exists',
												'新标签');
										if (if_exit) { //如果要添加的标签已经存在
											alert("您要添加的标签已经存在");
										} else { //如果要添加的标签不存在,那就添加
											$('#tabs')
													.tabs(
															'add',
															{
																title : '新标签',
																content : '<iframe src="http://www.baidu.com" frameborder="0" id="contentIframe" height="100%" width="100%"></iframe>',
																closable : true,
																tools : [ {
																	iconCls : 'icon-mini-refresh',
																	handler : function() {
																		alert('refresh');
																	}
																} ]
															});
										}
									});
				</script>
			</div>
			<div title="面板2" data-options="iconCls:'icon-reload',selected:true"
				style="padding: 10px;">
				<!-- 展示ztree效果 :使用简单json数据构造ztree-->
				<ul id="ztree1" class="ztree"></ul>
				<script type="text/javascript">
					$(function() {
						//页面加载完成后，执行这段代码----动态创建ztree
						var setting1 = {
							data : {
								simpleData : {
									enable : true
								//使用简单json数据构造ztree节点
								}
							}
						};
						//构造节点数据
						var zNodes1 = [ {
							"id" : "1",
							"pId" : "0",
							"name" : "节点一"
						},//每个json对象表示一个节点数据
						{
							"id" : "2",
							"pId" : "1",
							"name" : "节点二"
						}, {
							"id" : "3",
							"pId" : "2",
							"name" : "节点三"
						} ];
						//调用API初始化ztree
						$.fn.zTree.init($("#ztree1"), setting1, zNodes1);
					});
				</script>
			</div>
			<div title="面板3">
				<ul id="ztree2" class="ztree"></ul>
				<script type="text/javascript">
					$
							.post(
									"${pageContext.request.contextPath}/json/menu.json",
									function(data) {
										var setting2 = {
											data : {
												simpleData : {
													enable : true
												//使用简单json数据构造ztree节点
												}
											},
											callback : {
												onClick : function(event,
														treeId, treeNode) {
													if (treeNode.page != undefined) { //如果是叶子
														var if_exit = $("#tabs")
																.tabs(
																		'exists',
																		treeNode.name);
														if (if_exit) { //如果要添加的标签已经存在
															alert("您要添加的标签已经存在");
														} else { //如果要添加的标签不存在,那就添加
															$('#tabs')
																	.tabs(
																			'add',
																			{
																				title : treeNode.name,
																				content : '<iframe src="'+treeNode.page+'" frameborder="0" id="contentIframe" height="100%" width="100%"></iframe>',
																				closable : true,
																				tools : [ {
																					iconCls : 'icon-mini-refresh',
																					handler : function() {
																						alert('refresh');
																					}
																				} ]
																			});
														}
													}

												}
											}
										};
										//构造节点数据
										var zNodes2 = [ {
											"id" : "1",
											"pId" : "0",
											"name" : "节点一"
										},//每个json对象表示一个节点数据
										{
											"id" : "2",
											"pId" : "1",
											"name" : "节点二"
										}, {
											"id" : "3",
											"pId" : "2",
											"name" : "节点三"
										} ];
										//调用API初始化ztree
										$.fn.zTree.init($("#ztree2"), setting2,
												data);
									}, "json");
				</script>
			</div>
		</div>
	</div>
	<div data-options="region:'center'"
		style="padding: 5px; background: #eee;">
		<div id="tabs" class="easyui-tabs"
			style="width: 500px; height: 250px;" data-options="fit:'true'">
			<div title="标签1" style="padding: 20px; display: none;">tab1</div>
			<div title="标签2" data-options="closable:true"
				style="overflow: auto; padding: 20px; display: none;">tab2</div>
			<div title="标签3" data-options="iconCls:'icon-reload',closable:true"
				style="padding: 20px; display: none;">tab3</div>
		</div>


	</div>
</body>
</html>