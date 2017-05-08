<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
String orgcode=request.getParameter("orgcode");
%>
<%

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
<link href="<%=path%>/oa/css/skins/sky/import_skin.css" rel="stylesheet" type="text/css" themeColor="blue"/>
<!--框架必需end-->
<script src="<%=path%>/js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="<%=path%>/js/form/validationEngine.js" type="text/javascript"></script>
<script src="<%=path%>/js/menu/contextmenu.js" type="text/javascript"></script>  
<script type="text/javascript" src="<%=path%>/js/tree/stree/stree.js"></script>
<script language="JavaScript" src="<%=path%>/js/form/MyDatePicker/WdatePicker.js"></script>
<link href="<%=path%>/js/tree/stree/stree.css" rel="stylesheet" type="text/css"/>
</head>
<script type="text/javascript">
function tijiao(){
	if(document.getElementById("licencefile").value==""){
		alert("请上传许可证！");
	}else{
		document.all("Submit").click();
		window.parent.close();
	}
}
</script>
<body>
<form name="form1" id="form1" action="initallshop!uplicence.action" target="initallshop" enctype="multipart/form-data" method="post">
		<table class="tableStyle" transMode="true">
		<tr>
			<td>上传许可证：</td><td><input id="licencefile" type="file" name="licence">
			</td>
		</tr>
		<tr>
			<td>生效时间：</td><td><input type="text" class="Wdate" name="licenceEntity.starttime" onfocus="new WdatePicker({lang:'zh-cn'})" value="<%=date%>" size="15"/>
			</td>
		</tr>
				<tr>
			<td>失效时间：</td><td><input type="text" class="Wdate" name="licenceEntity.endtime" onfocus="new WdatePicker({lang:'zh-cn'})" value="<%=date%>" size="15"/>
			</td>
		</tr>
				<tr>
			<td><input type="hidden" id="orgcode" name="orgcode" value="<%=orgcode%>"/></td><td></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" name="Submit" value=" 提 交 " style="display:none"/>
				<input type="button" name="submit2" onclick="tijiao()" value="提 交 " style="display:none"/>
				<input type="reset" value=" 重 置 " style="display:none"/>
			</td>
		</tr>
	</table>
	</form>	
</body>
</html>