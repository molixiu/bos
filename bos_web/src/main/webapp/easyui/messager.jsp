<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>accordion</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>

</head>
<body>
	<script type="text/javascript">
		$(function() {
			//$.messager.alert("四季流转","要分手了,虽然我有勇气承受,分别之后,一瞬间又有了失落的感觉","info");
			/*$.messager.confirm("我们是朋友","你确定要退出本次操作吗",function(r){
				if(r){
					alert("虽然我们穿着不同颜色的衣服,但我们追逐着同样色彩的梦想");
				}
			});*/
			/* $.messager.show({"title":"欢迎信息",
							"msg":"啊，朝夕相处的伙伴,时间在风中流逝,云霞带来清晨的信息",
							"timeout":5000,
							"showType":"slide",

			}); */
			$.messager.alert('警告','警告消息'); 
		});
	</script>
</body>
</html>