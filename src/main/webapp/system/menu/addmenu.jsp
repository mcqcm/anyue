<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组织机构管理</title>
<!--框架必需start-->
<script type="text/javascript" src="<%=path%>/js/jquery-1.4.js"></script>
<script type="text/javascript" src="<%=path%>/js/framework.js"></script>
<link href="<%=path%>/css/import_basic.css" rel="stylesheet" type="text/css"/>
<!--框架必需end-->
<script src="<%=path%>/js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="<%=path%>/js/form/validationEngine.js" type="text/javascript"></script>
<script src="<%=path%>/js/menu/contextmenu.js" type="text/javascript"></script>  
<script type="text/javascript" src="<%=path%>/js/tree/stree/stree.js"></script>
<script language="JavaScript" src="<%=path%>/js/form/datePicker/WdatePicker.js"></script>
<link href="<%=path%>/js/tree/stree/stree.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<form name="form1" id="form1" action="menu!addmenu.action" target="menumanage" method="post">
		<table class="tableStyle" transMode="true">
		<tr>
			<td>菜单id：</td>
			<td><input type="text" name="menu.id" value="${menu.id}"/>
			</td>
		</tr>
		<tr>
			<td>菜单名称：</td>
			<td><input type="text" name="menu.menuname" value="${menu.menuname}"/>
			</td>
		</tr>
		<tr>
			<td>菜单别名（英文）：</td>
			<td><input type="text" name="menu.englishname" value="${menu.englishname}"/>
			</td>
		</tr>
		<tr>
			<td>菜单url：</td>
			<td><input type="text" name="menu.menuurl" value="${menu.menuurl}"/>
			</td>
		</tr>
		<tr>
			<td>父菜单id：</td>
			<td><input type="text" name="menu.parentid" value="${menu.parentid}"/>
			</td>
		</tr>
		<tr>
			<td>菜单类型：</td>
			<td><input type="text" name="menu.menutype" value="${menu.menutype}"/>
			</td>
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