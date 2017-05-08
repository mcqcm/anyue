<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.orm.Organize"
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
List<Organize> orglist=(List<Organize>)request.getSession().getAttribute("orglist");
System.out.println("size:::::"+orglist.size());
Iterator<Organize> it = orglist.iterator();  
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
		d.add(0,-1,'机构树');
 
		<%
		while (it.hasNext()) {  
			Organize org=it.next();
			if(org.getParentcode()!=null&&!(org.getParentcode().equals(""))){
			%>
			d.add(<%=org.getOrgcode()%>,<%=org.getParentcode()%>,'<%=org.getOrgname()%>','','','allstaffright');
			<%
			}else{
				%>
				d.add(<%=org.getOrgcode()%>,<%=org.getBelongcode()%>,'<%=org.getOrgname()%>','stafflist.jsp?orgcode=<%=org.getOrgcode()%>','','allstaffright');
				<%
			}
		}
		%>

		document.write(d);

		//-->
	</script>
	</div>
</body>
</html>