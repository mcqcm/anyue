<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();

Calendar c = Calendar.getInstance();
	 String year = "" + c.get(c.YEAR);
String month = "" + (c.get(c.MONTH) + 1);
String day = "" + c.get(c.DATE);
String date=year+"-"+month+"-"+day;
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
<form name="form1" id="form1" action="trainmanage!saveorupdatecontent.action" target="traincontent" method="post">
		<table class="tableStyle" transMode="true">
		<tr>
			<td>标题：</td><td><input type="text" name="trainContent.tittle" value="${trainContent.tittle}"/></td>
		</tr>
		<tr>
			<td>发布时间：</td><td><input type="text" name="trainContent.publishtime" value="${trainContent.publishtime}" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>内容：</td><td><textarea id="notice" name="trainContent.content" cols="20" rows="2" >${trainContent.content}</textarea>
			</td>
		</tr>	
		<tr>
			<td></td><td><input type="hidden" name="trainContent.id" value="${trainContent.id}"/><input type="hidden" name="trainContent.uporend" value="${trainContent.uporend}"/></td>
		</tr>	
		<tr>
			<td colspan="2">
				<input type="submit" name="Submit" value=" 提 交 " style="display:none"/>
				<input type="button" value=" 重 置 " onclick="show()" style="display:none"/>
			</td>
		</tr>
	</table>
	</form>	
</body>
</html>