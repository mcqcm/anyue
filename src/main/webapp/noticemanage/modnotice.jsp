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
<script type="text/javascript" src="<%=path%>/ckeditor/ckeditor.js"></script>
<script src="<%=path%>/js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="<%=path%>/js/form/validationEngine.js" type="text/javascript"></script> 
<script language="JavaScript" src="<%=path%>/js/form/MyDatePicker/WdatePicker.js"></script>
<link href="<%=path%>/js/tree/stree/stree.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript">
    function show(){
    	//try { var content=CKEDITOR.instances['notice'].getData(); } catch (ex) { }
    	//var stem = CKEDITOR.instances.notice.getData();
    	//var content=CKEDITOR.instances['notice'].getData();
    	//alert(CKEDITOR.instances.notice.getData());
    	window.document.getElementById("content").value=CKEDITOR.instances.notice.getData();
    	return ture;
    }
</script>
</head>
<body>
<form name="form1" id="form1" action="notice!addNotice.action" target="noticemanage" method="post">
		<table class="tableStyle" transMode="true">
		<tr>
			<td><input type="text" name="notice.id" value="${notice.id}" style="display:none"/></td>
		</tr>
		<tr>
			<td>标题：</td><td><input type="text" name="notice.tittle" value="${notice.tittle}"/></td>
		</tr>
		<tr>
			<td>发布时间：</td><td><input type="text" class="Wdate" name="notice.publishtime" onfocus="new WdatePicker({lang:'zh-cn'})" value="${notice.publishtime}" size="15"/></td>
		</tr>
		<tr>
			<td>内容：</td><td><textarea id="notice" name="content2" cols="20" rows="2" class="ckeditor" >${notice.text}</textarea>
			<script type="text/javascript">
    //CKEDITOR.replace('notice!addNotice');
    CKEDITOR.replace('notice',
    	    {
    	        toolbar : 'Full',
    	        uiColor : '#9AB8F3',
    	        width: 650,
    	        height: 300
    	    });
</script>
			</td>
		</tr>
		<tr>
			<td><input type="text" id="content" name="notice.text" value="" style="display:none"/></td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="submit" name="Submit" value=" 提 交 " style="display:none" onclick="return show()"/>
				<input type="button" value=" 重 置 " onclick="show()" style="display:none" />
			</td>
		</tr>
	</table>
	</form>	
</body>
</html>