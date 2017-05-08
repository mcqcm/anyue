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
<form name="form1" id="form1" action="initallshop!addorg.action" target="shopright" method="post">
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
			<td>联系方式：</td><td><input type="text" name="orgInfo.callnum" value="${orgInfo.callnum}" /></td>
		</tr>
		<tr>
			<td>邮箱地址：</td><td><input type="text" name="orgInfo.email" value="${orgInfo.email}"/></td>
		</tr>
		<tr>
			<td>开业日期：</td><td><input type="text" class="Wdate" name="orgInfo.runtime" onfocus="new WdatePicker({lang:'zh-cn'})" value="${orgInfo.runtime}" size="15"/></td>
		</tr>
		<tr>
			<td>餐饮类型：</td>			
			<td><select name="orgInfo.cateringtype">  
					<option selected="selected" value ="${orgInfo.cateringtype}">${orgInfo.cateringtype}</option> 
					  <option value ="特大型餐馆">特大型餐馆</option>   
					  <option value ="大型餐馆">大型餐馆</option> 
					  <option value ="中型餐馆">中型餐馆</option> 
					  <option value ="小型餐馆">小型餐馆</option> 
					  <option value ="快餐店">快餐店</option> 
					  <option value ="小吃店">小吃店</option> 
					  <option value ="饮品店">饮品店</option> 
					  <option value ="食堂">食堂</option> 
					  <option value ="集体用餐配送单位">集体用餐配送单位</option> 
					  <option value ="其他">其他</option> 
					</select></td>
		</tr>
				<tr hidden="true">
			<td><input type="text" name="orgInfo.orgtype" value="${orgInfo.orgtype}"/></td><td><input type="text" name="orgInfo.belongcode" value="${orgInfo.belongcode}"/></td>
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