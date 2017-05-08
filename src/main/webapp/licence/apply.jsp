<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
%>
<%
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
<script type="text/javascript">
function tijiao(){
	if(document.getElementById("licencefile").value==""){
		alert("请上传许可证！");
	}else{
		document.all("submitt").click();
	}
}
</script>
<body>
<form name="form1" id="form1" action="applylicence!submitapply.action"  target="licenceapply" enctype="multipart/form-data" method="post">
		<table class="tableStyle" transMode="true">
		<tr>
			<td>申请人：</td>
			<td><input type="text" name="applyer.username" value="${applyer.username}" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td>申请单位：</td><td><input type="text" name="applyer.orgname" value="${applyer.orgname}" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td>申请时间：</td><td><input type="text" class="Wdate" name="applytime" onfocus="new WdatePicker({lang:'zh-cn'})" value="<%=date%>" size="15"/>
			</td>
		</tr>
		<tr>
			<td>申请理由：</td><td><label>
		    <textarea name="applyreason" id="applyreason"  style="width:400px;height:100px" value=""></textarea>
		   </label></td>
		</tr>
		<tr>
			<td>上传申请材料(压缩包)：</td><td><input type="file" id="licencefile" name="applyzip">
			</td>
		</tr>
		<tr>
			<td><input type="hidden" id="applyer.userid" name="applyer.userid" value="${applyer.userid}"/></td><td></td>
		</tr>
		<tr>
			<td colspan="2">
								<input type="submit" name="submitt" value=" 提 交 " style="display:none"/>
				<input type="button" name="Submit" onclick="tijiao()" value="提 交 " />
				<input type="reset" value=" 重 置 " />
			</td>
		</tr>
	</table>
	</form>	
</body>
</html>