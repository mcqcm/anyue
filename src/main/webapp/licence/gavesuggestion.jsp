<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
String taskid=request.getParameter("taskid");
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
<script type="text/javascript">
</script>
<body>
<form name="form1" id="form1" action="task!gavesuggestion.action"  target="firstleft" method="post">
		<table class="tableStyle" transMode="true">	
		<tr>
			<td>驳回意见：</td><td><label>
		    <textarea name="suggestion" id="suggestion"  style="width:400px;height:100px"></textarea>
		   </label></td>
		</tr>
		<tr>
			<td><input type="hidden" id="taskid" name="taskid" value="<%=taskid%>"/></td><td></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" name="Submit" value=" 提 交 " style="display:none"/>
<!-- 				<input type="button" name="submit2" onclick="tijiao()" value="提 交 " style="display:none"/> -->
				<input type="reset" value=" 重 置 " />
			</td>
		</tr>
	</table>
	</form>	
</body>
</html>