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
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link  rel="stylesheet" type="text/css" id="skin"/>
<!--框架必需end-->
<script type="text/javascript" src="../js/tree/dtree/dtree.js"></script>
<link href="../js/tree/dtree/dtree.css" rel="stylesheet" type="text/css"/>
</head>
<%
List<Organize> orglist=(List<Organize>)request.getSession().getAttribute("myshopslist");
%>
<script language="javascript">
function openright(orgcode)
{


	var newlisturl='listforsearch.jsp?orgcode='+orgcode;
	//var newtreeurl='../tree/unit_tree.jsp?pageurl=../std_allunitsearch/std_orgpostlist.jsp&pagetarget=postlist&unitccm='+unitccm;
	window.open(newlisturl,'myingoodsright');
	//window.open(newtreeurl,'unittree')
}
</script>
<body>
<body leftFrame="true">
	<div style="text-align:center;" >
	</div>
	<div id="scrollContent">
		<table  width="100%" height="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" bgcolor="#DBEAFD">
                
          
            <div style="position: relative;overflow-y:auto; overflow-x:auto; border:1px solid gray;">
          
              <%
				if (orglist!= null && orglist.size()>0)
				{
					Iterator<Organize> it = orglist.iterator();  
					while(it.hasNext())
					{
						Organize org=it.next();
						out.print("<tr><td class='table_td_jb_iframe' style='padding-left:50px; padding-top:5px;'><a href=\"#\" onclick=\"openright('"+org.getOrgcode()+"')\">○ "+org.getOrgname()+"</a></td></tr>");	
					}
				}
			%>
          
     	 	</div>
            </td>
          </tr>
          
        </table>
	</div>
</body>
</html>