<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>供应商</title>
<!--框架必需start-->
<script type="text/javascript" src="<%=path%>/js/jquery-1.4.js"></script>
<script type="text/javascript" src="<%=path%>/js/framework.js"></script>
<link href="<%=path%>/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/skins/sky/import_skin.css" rel="stylesheet" type="text/css" themeColor="blue"/>
<!--框架必需end-->
<script src="<%=path%>/js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="<%=path%>/js/form/validationEngine.js" type="text/javascript"></script>
<script src="<%=path%>/js/menu/contextmenu.js" type="text/javascript"></script>  
<script type="text/javascript" src="<%=path%>/js/tree/stree/stree.js"></script>
<link href=<%=path%>/js/tree/stree/stree.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<form name="form1" id="form1" action="supplier!addsupplier.action"  target="frmright" method="post">
		<table class="tableStyle" transMode="true">
		<tr>
			<td>供应商编号：</td>
			<td><input type="text" name="supplier.supcode" value="${supplier.supcode}"/>
			</td>
		</tr>
		<tr>
			<td>供应商名称：</td><td><input type="text" name="supplier.supname" value="${supplier.supname}"/>
			</td>
		</tr>
		<tr>
			<td>供应商地址：</td><td><input type="text" name="supplier.location" value="${supplier.location}"/>
			</td>
		</tr>
		<tr>
			<td>备注：</td><td><input type="text" name="supplier.more" value="${supplier.more}"/></td>
		</tr>
		<tr>
			<td><input type="hidden" name="supplier.id" value="${supplier.id}"/></td><td></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" name="Submit" value=" 提 交 " style="display:none"/>
				<input type="reset" value=" 重 置 " style="display:none"/>
			</td>
		</tr>
	</table>
	</form>	
</body>
</html>