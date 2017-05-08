<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
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
<title>登陆_${sitename}</title>
<link rel="stylesheet" type="text/css"
	href="${css_path}/admin/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${request_path}/css/style.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/icon.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/demo.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/adminlogin.css">
<link rel="shortcut icon" href="favicon.ico" />
<script language="JavaScript" type="text/javascript" src="${js_path}/jquery.js" ></script>
<script type="text/javascript"
	src="${js_path}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	function reset(){
	     $('#loginForm').reset();
	}
</script>
</head>

<body>
<table width="562" border="0" align="center" cellpadding="0" cellspacing="0" class="right-table03">
  <tr>
    <td>
    <form action="login.action" method="post" name="loginForm" id="loginForm">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="35">许 可 证 申 请 指 南：</td>
        <td><a href="download.action?number=1" class="button4">下载</a></td>
      </tr>
      <tr>
        <td height="35">许可证   申请   办理模版：</td>
          <td><a href="download.action?number=2" class="button4">下载</a></td>
      </tr>
    </table>
    </form>
    </td>
  </tr>
</table>
</body>
</html>