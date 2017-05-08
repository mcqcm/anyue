<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<form name="form1" id="form1" action="stafftrain!add.action"  target="stafftrain" method="post">
		<table class="tableStyle" transMode="true">
		<tr>
			<td>选择培训：</td>
			<td>
	
<select class="inputxt" id="trainID" name="trainID" style="width:126px;">
<s:iterator value="trainList" status="st" id="row"> 
<option value="${row.id}">${row.tittle}</option>
</s:iterator>
<%--     <c:forEach var="item" items="${trainList}" varStatus="status"> --%>
<%-- 			<option value="${status.id}">${status.tittle}</option> --%>
<%-- 	</c:forEach>  --%>
</select>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
			<input type="hidden" name="staffcodes" value="${staffcodes}"/>
				<input type="submit" name="Submit" value=" 提 交 " style="display:none"/>
				<input type="reset" value=" 重 置 " style="display:none"/>
			</td>
		</tr>
	</table>
	</form>	
</body>
</html>