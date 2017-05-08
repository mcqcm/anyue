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
<link href="<%=path%>/css/skins/sky/import_skin.css" rel="stylesheet" type="text/css" themeColor="blue"/>
<!--框架必需end-->
<script src="<%=path%>/js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="<%=path%>/js/form/validationEngine.js" type="text/javascript"></script> 
<script language="JavaScript" src="<%=path%>/js/form/MyDatePicker/WdatePicker.js"></script>
<link href="<%=path%>/js/tree/stree/stree.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<form name="form1" id="form1" action="organize!addorg.action" target="orgmanage" method="post">
		<table class="tableStyle" transMode="true">
		<tr>
			<td>单位编码：</td>
			<td><input type="text" name="orgInfo.orgcode" value="${orgInfo.orgcode}" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td>单位名称：</td><td><input type="text" name="orgInfo.orgname" value="${orgInfo.orgname}"/>
			</td>
		</tr>
		<tr>
			<td>地址：</td><td><input type="text" name="orgInfo.orglocation" value="${orgInfo.orglocation}"/>
			</td>
		</tr>
		<tr>
			<td>负责人：</td><td><input type="text" name="orgInfo.chairman" value="${orgInfo.chairman}"/></td>
		</tr>
		<tr>
			<td>联系方式：</td><td><input type="text" id="suppliername" name="orgInfo.callnum" value="${orgInfo.callnum}" /></td>
		</tr>
		<tr>
			<td>创建日期：</td><td><input type="text" class="Wdate" name="orgInfo.addtime" onfocus="new WdatePicker({lang:'zh-cn'})" value="${orgInfo.addtime}" size="15"/></td>
		</tr>
		<tr>
			<td>辖区：</td><td><input type="text" name="orgInfo.managearea" value="${orgInfo.managearea}"/></td>
		</tr>
		<tr hidden="true">
			<td>父code：</td><td><input type="text" name="orgInfo.parentcode" value="${orgInfo.parentcode}"/></td>
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