<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String request_path = request.getContextPath();
	String image_path = request_path + "/images/blue-themes";
	String css_path = request_path + "/css/blue-themes";
	String js_path = request_path + "/js";
	request.setAttribute("request_path", request_path);
	request.setAttribute("image_path", image_path);
	request.setAttribute("css_path", css_path);
	request.setAttribute("js_path", js_path);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>AJAX Content</title>
	
	<style type="text/css">
	</style>
<script type="text/javascript">
function see(url,id,title){
	var diag = new top.Dialog();
	diag.Title = title;
	diag.Width=620;
	diag.Height=500;
	diag.URL = url+"?id="+id;
	diag.OKEvent = function(){
		diag.close();
	}
	diag.show();
}
</script>
</head>
<body>
<table width="45%" border="0" align="left" cellpadding="0" cellspacing="1" bgcolor="#999999">
			<tbody>
				<tr bgcolor="#F3F3F3">
					<td height="30" align="center" bordercolor="#000000">
					<div align="left"><font color="#990000"><img src="images/icons/ico4.gif" width="15" height="15"></font><font color="#993300">&nbsp;&nbsp;待办事项</font></div>
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td height="30" align="center">
					<table width="600" cellpadding="0" cellspacing="0" border="0">						
							<tbody>
							<c:forEach var="item" items="${noticelist}" varStatus="status">
								<tr>
								<td width="105" height="22" align="center"><font color="#FF8000">${item.publishtime.toString().substring(0,10)}</font></td>
					     		<td width="495" align="left"><a href="#" onclick="see('notice!seeDetail.action',${item.id},'通知公告')">${item.tittle}</a></td>
								</tr>
							</c:forEach>	
					        </tbody>
					</table>
					</td>
				</tr>
			</tbody>
</table>

<table width="45%" border="0" align="right" cellpadding="0" cellspacing="1" bgcolor="#999999">
			<tbody>
				<tr bgcolor="#F3F3F3">
					<td height="30" align="center" bordercolor="#000000">
					<div align="left"><font color="#990000"><img src="images/icons/ico4.gif" width="15" height="15"></font><font color="#993300">&nbsp;&nbsp;通知公告</font></div>
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td height="30" align="center">
					<table width="600" cellpadding="0" cellspacing="0" border="0">						
							<tbody>
							<c:forEach var="item" items="${noticelist}" varStatus="status">
								<tr>
								<td width="105" height="22" align="center"><font color="#FF8000">${item.publishtime.toString().substring(0,10)}</font></td>
					     		<td width="495" align="left"><a href="#" onclick="see('notice!seeDetail.action',${item.id},'通知公告')">${item.tittle}</a></td>
								</tr>
							</c:forEach>	
					        </tbody>
					</table>
					</td>
				</tr>
			</tbody>
</table>


<!-- <input name="textfield" style="color:#CCC;" type="text" value="点击文字消失" size="12" onclick="value='';focus()" onblur="if(value=='') value='点击文字消失;"/>  -->
<!-- 	<p style="font-size:14px"> hello</p> -->
<!-- 	<ul> -->
<!-- 		<li>hello</li> -->
<!-- 	</ul> -->
</body>
</html>