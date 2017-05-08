<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组织机构管理</title>
<!--框架必需start-->
<script type="text/javascript" src="../js/jquery-1.4.js"></script>
<script type="text/javascript" src="../js/framework.js"></script>
<link href="../css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="../oa/css/skins/sky/import_skin.css" rel="stylesheet" type="text/css" themeColor="blue"/>
<!--框架必需end-->
<script src="../js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="../js/form/validationEngine.js" type="text/javascript"></script>
<script src="../js/menu/contextmenu.js" type="text/javascript"></script>  
<script type="text/javascript" src="../js/tree/stree/stree.js"></script>
<script language="JavaScript" src="../js/form/datePicker/WdatePicker.js"></script>
<link href="../js/tree/stree/stree.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<form name="form1" id="form1" action="login!modaccount.action"  method="post">
		<table class="tableStyle" transMode="true">
		<tr>
			<td>登陆账号：</td><td><input type="text" name="loginuser.account"  value="${loginuser.account}" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>登陆密码：</td><td><input type="password" name="loginuser.password"  value="${loginuser.password}"/></td>
		</tr>
		</table>
		<table class="tableStyle" transMode="true">
		<tr>
			<td><input type="hidden" name="loginuser.userid" value="${loginuser.userid}"/></td>
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