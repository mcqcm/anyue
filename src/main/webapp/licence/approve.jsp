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
<script type="text/javascript" src="<%=path%>/js/attention/zDialog/zDialog.js"></script>
<script type="text/javascript" src="<%=path%>/js/attention/zDialog/zDrag.js"></script>
<link href="<%=path%>/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/skins/sky/import_skin.css" rel="stylesheet" type="text/css" themeColor="blue"/>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/style.css">
<!--框架必需end-->
<script src="<%=path%>/js/form/validationEngine-cn.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
function passlicence(taskid,title){
	var diag = new top.Dialog();
	diag.Title = title;
	diag.Width=500;
	diag.Height=400;
	diag.URL = "/anyue/licence/uplicence.jsp?taskid="+taskid;
	diag.OKEvent = function(){
		if(diag.innerFrame.contentWindow.document.getElementById("licencefile").value==""){
			alert("请上传许可证！");
		}else{
// 			window.document.getElementById("pass").style.display = "none";
// 			window.document.getElementById("back").style.display = "none";
			diag.innerFrame.contentWindow.document.all("Submit").click();
			diag.close();
			parentDialog.close();
		}
	}
	diag.show();

}
function backlicence(taskid,title){
	var diag = new top.Dialog();
	diag.Title = title;
	diag.Width=800;
	diag.Height=600;
	diag.URL = "/anyue/licence/gavesuggestion.jsp?taskid="+taskid;
	diag.OKEvent = function(){
// 		window.document.getElementById("pass").style.display = "none";
// 		window.document.getElementById("back").style.display = "none";
		diag.innerFrame.contentWindow.document.all("Submit").click();
		diag.close();	
		parentDialog.close();
	}
	diag.show();
}
</script>
<body>
<form name="form1" id="form1" action=""  target="firstleft" enctype="multipart/form-data" method="post">
		<table class="tableStyle" transMode="true">
		<tr>
			<td>申请人：</td>
			<td><input type="text" name="applyername" value="${applyername}" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td>申请单位：</td><td><input type="text" name="applyerorg" value="${applyerorg}" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td>申请时间：</td><td><input type="text" name="applytime" value="${applytime}" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td>申请理由：</td><td><label>
		    <textarea name="applyreason" id="applyreason"  style="width:400px;height:100px">${applyreason}</textarea>
		   </label></td>
		</tr>
		<tr>
			<td>申请材料：</td><td><a href="task.action" class="button4">下载</a>
			</td>
		</tr>
		<tr>
			<td>
				<input type="button" id="pass" name="pass" onclick="passlicence('${taskid}','上传许可证')" value=" 通过 "/>
				</td>
				<td>
				<input type="button" id="back" name="back" onclick="backlicence('${taskid}','驳回意见')" value=" 驳回 "/>
			</td>
		</tr>
	</table>
	</form>	
</body>
</html>