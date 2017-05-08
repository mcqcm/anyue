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
<script type="text/javascript">
function CHOOSE(){
	var diag = new top.Dialog();
	diag.Title = "选择供应商";
	diag.Width=600;
	diag.Height=500;
	diag.URL = "medicine/supplylist.jsp";
	diag.OKEvent = function(){
		//diag.innerFrame.contentWindow.document.getElementById("hid").click();
		var select=diag.innerFrame.contentWindow.document.getElementsByName("items").value;
// 		var select_array=new Array();
// 		var j=0;
// 		for(var i=0;i<check_array.length;i++){
// 			if(check_array[i].checked==true){
// 				select_array[j]=check_array[i].value;
// 				j=j+1;
// 			}
// 		}
		if(select==""){
			alert("您没有选择供应商！请重新选择！");
		}else{
			
			var sele=select.split('&');
			alert('您选择的供应商：'+sele[1]);
			window.document.getElementById("supplierid").value=sele[0];
			window.document.getElementById("suppliername").value=sele[1];
		}
		diag.close();
	}
	diag.show();
	
}
</script>
<body>
<form name="form1" id="form1" action="medicine!addmedicine.action"  target="medinfomanage" method="post">
		<table class="tableStyle" transMode="true">
		<tr>
			<td>药品编号：</td>
			<td><input type="text" name="medicineinfo.medcode" value="${medicineinfo.medcode}"/>
			</td>
		</tr>
		<tr>
			<td>药品名称：</td><td><input type="text" name="medicineinfo.medname" value="${medicineinfo.medname}"/>
			</td>
		</tr>
		<tr>
			<td>药品数量：</td><td><input type="text" name="medicineinfo.mednumber" value="${medicineinfo.mednumber}"/>
			</td>
		</tr>
		<tr>
			<td>药品价格：</td><td><input type="text" name="medicineinfo.price" value="${medicineinfo.price}"/></td>
		</tr>
		<tr>
			<td>选择供应商：</td>
			<td><input type="button" value="选择" onclick="CHOOSE()">
			</td>
		</tr>
		<tr>
			<td>供应商：</td><td><input type="text" id="suppliername" name="medicineinfo.supname" value="${medicineinfo.supname}" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>生产日期：</td><td><input type="text" class="Wdate" name="medicineinfo.maketime" onfocus="new WdatePicker({lang:'zh-cn'})" value="${medicineinfo.maketime}" size="15"/></td>
		</tr>
		<tr>
			<td>有效期：</td><td><input type="text" class="Wdate" name="medicineinfo.userfultime" onfocus="new WdatePicker({lang:'zh-cn'})" value="${medicineinfo.userfultime}" size="15"/></td>
		</tr>
		<tr>
			<td>备注：</td><td><input type="text" name="medicineinfo.more" value="${medicineinfo.more}"/></td>
		</tr>
		<tr>
			<td><input type="hidden" id="supplierid" name="supid" value="${medicineinfo.supplier.id}"/></td><td><input type="hidden" name="medicineinfo.id" value="${medicineinfo.id}"/></td>
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