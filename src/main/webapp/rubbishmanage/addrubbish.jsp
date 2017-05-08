<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
String parentcode=request.getParameter("parentcode");

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
<form name="form1" id="form1" action="rubbish!addRubbish.action" target="rubbishadd" method="post">
		<table class="tableStyle" transMode="true">
		<tr>
			<td>简介：</td><td><input type="text" name="outRubbish.tittle"/></td>
		</tr>
		<tr>
			<td>废物处理说明：</td>
			<td><textarea  name="outRubbish.outcontent" style="overflow-x:hidden;width:300px;height:150px"></textarea>
			</td>
		</tr>
		<tr>
			<td>处理时间：</td><td><input type="text" class="Wdate" name="outRubbish.outtime" onfocus="new WdatePicker({lang:'zh-cn'})" value="<%=date%>" size="15"/></td>
		</tr>
		<tr>
			<td>处理数量：</td><td><input type="text" name="outRubbish.outnumber"/></td>
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