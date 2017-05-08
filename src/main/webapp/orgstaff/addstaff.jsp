<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
String orgcode=request.getParameter("orgcode");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组织机构管理</title>
<!--框架必需start-->
<script type="text/javascript" src="<%=path%>/js/jquery-1.4.js"></script>
<script type="text/javascript" src="<%=path%>/js/framework.js"></script>
<link href="<%=path%>/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/oa/css/skins/sky/import_skin.css" rel="stylesheet" type="text/css" themeColor="blue"/>
<!--框架必需end-->
<script src="<%=path%>/js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="<%=path%>/js/form/validationEngine.js" type="text/javascript"></script>
<script src="<%=path%>/js/menu/contextmenu.js" type="text/javascript"></script>  
<script type="text/javascript" src="<%=path%>/js/tree/stree/stree.js"></script>
<%-- <script language="JavaScript" src="<%=path%>/js/form/datePicker/WdatePicker.js"></script> --%>
<link href="<%=path%>/js/tree/stree/stree.css" rel="stylesheet" type="text/css"/>
</head>
<script type="text/javascript">
</script>
<body>
<form name="form1" id="form1" action="orgstaff!addstaff.action" target="orgstaff" enctype="multipart/form-data" method="post">
		<table class="tableStyle" transMode="true">
		<tr>
			<td>员工编号：</td>
			<td><input type="text" name="staff.staffcode"/>
			</td>
		</tr>
		<tr>
			<td>员工姓名：</td>
			<td><input type="text" name="staff.staffname"/>
			</td>
		</tr>
		<tr>
			<td>身份证号：</td><td><input type="text" name="staff.identitycode"/>
			</td>
		</tr>
		<tr>
			<td>员工电话：</td><td><input type="text" name="staff.telephone"/></td>
		</tr>
		<tr>
			<td>员工性别：</td>
			<td><select name="staff.sex">  
					  <option selected="selected" value ="男">男</option>  
					  <option value ="女">女</option> 
					</select></td>
		</tr>
		<tr>
			<td>地址：</td><td><input type="text" name="staff.address"/>
			</td>
		</tr>
		<tr>
			<td>上传健康证：</td><td><input type="file" name="healthcard">
			</td>
		</tr>
<!-- 		<tr> -->
<!-- 			<td><input type="hidden" name="user.userid"/></td><td><input type="hidden" name="user.password" value="000000"/></td> -->
<!-- 		</tr> -->
				<tr>
			<td><input type="hidden" id="orgcode" name="orgcode" value="<%=orgcode%>"/></td><td></td>
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