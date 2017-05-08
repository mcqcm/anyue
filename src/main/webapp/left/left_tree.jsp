<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.orm.SystemUser,com.orm.SystemRole,com.orm.SystemMenu"
    pageEncoding="UTF-8"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-1.4.js"></script>
<script type="text/javascript" src="../js/framework.js"></script>
<link href="../css/import_basic.css" rel="stylesheet" type="text/css"/>
<link  rel="stylesheet" type="text/css" id="skin"/>
<!--框架必需end-->
<script type="text/javascript" src="../js/tree/dtree/dtree.js"></script>
<link href="../js/tree/dtree/dtree.css" rel="stylesheet" type="text/css"/>
</head>
<%
SystemRole role=((SystemUser)request.getSession().getAttribute("loginuser")).getSystemrole();
Set<SystemMenu> menus=role.getSystemmenus();
Iterator<SystemMenu> it = menus.iterator();  
%>
<body>
<body leftFrame="true">
	<div style="text-align:center;" >
	<br />
	<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
	</div>
	<div id="scrollContent">
	<script type="text/javascript">

		d = new dTree('d');
		d.add(0,-1,'菜单');
 
		<%
		while (it.hasNext()) {  
			SystemMenu menu=it.next();
			if(menu.getMenuurl()!=null&&!(menu.getMenuurl().equals(""))){
			%>
			d.add(<%=menu.getId()%>,<%=menu.getParentid()%>,'<%=menu.getMenuname()%>','<%=menu.getMenuurl()%>','','frmright');
			<%
			}else{
			%>
			d.add(<%=menu.getId()%>,<%=menu.getParentid()%>,'<%=menu.getMenuname()%>');
			<%
			}
		}
		%>
<%--  		<%  --%>
// 			if(role.equals("admin")){
<%--  		%>  --%>
//  			d.add(100,0,'用户管理','user.action','','frmright');
//  			d.add(1,0,'供应商管理','supplier!ListAll.action','','frmright');
//  			d.add(10,0,'药品信息管理');
//  			d.add(11,10,'药品信息管理','medicine!ListAll.action','','frmright');
//  			d.add(12,10,'药品信息查询','medicine!List.action','','frmright');
<%-- 		<% --%>
// 			}else if(role.equals("user")){
<%--  		%>  --%>
//  			d.add(10,0,'药品信息管理');
//  			d.add(12,10,'药品信息查询','medicine!List.action','','frmright');
<%--  		<%}%> --%>
		document.write(d);

		//-->
	</script>
	</div>
</body>
</html>