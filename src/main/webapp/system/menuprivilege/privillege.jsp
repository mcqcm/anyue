<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*,java.util.ArrayList,com.alibaba.fastjson.JSONArray"  errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
String path = request.getContextPath();
%>
<HTML>
<HEAD>
<TITLE>四川省南充烟草公司</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.2873" name=GENERATOR>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css">
<link rel="stylesheet" href="<%=path%>/images/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=path%>/js/tree/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/tree/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=path%>/js/tree/ztree/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/JavaScript">
<!--
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->
</script>
<%
	JSONArray jsonarray=(JSONArray)request.getAttribute("jsonarray");
	Object id=request.getAttribute("roleid");
	String rolename="";
	String roleid="";
	if(id!=null){
		roleid=request.getAttribute("roleid").toString(); 
		rolename=request.getAttribute("rolename").toString(); 
	}
 %>
<script language="javascript" src="../../js/public/select.js"></script>

<script language="javascript">


function setCheck() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		py = $("#py").attr("checked")? "p":"",
		sy = $("#sy").attr("checked")? "s":"",
		pn = $("#pn").attr("checked")? "p":"",
		sn = $("#sn").attr("checked")? "s":"",
		type = { "Y":"ps", "N":"ps"};
		zTree.setting.check.chkboxType = type;
		//showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
	}
var zNodes=<%=jsonarray%> ;
function change()
	{
		//alert(document.all("menu1").value);menuprivilege。action
		window.location.href="menuprivilege.action?roleid="+document.all("menu1").value;
	}
var setting = {
				check: {
					enable: true
				},
				data: {
					simpleData: {
						enable: true
					}
				}
			};

		$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		setCheck();
		$("#py").bind("change", setCheck);
		$("#sy").bind("change", setCheck);
		$("#pn").bind("change", setCheck);
		$("#sn").bind("change", setCheck);
	});
function F8()
{
	if (confirm("确定按选定值设置权限？"))
	{
		if(document.all("menu1").value=="selection")
		{
			alert("请选择角色");
			return;
		}
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
 			var str="";
            var nodes = zTree.getCheckedNodes(true);
 			var orgname,orgcode;
 			//alert(nodes.length);
            for (var i = 0; i < nodes.length; i++) {
 				str+=nodes[i].id+";";       
			}
			str+=document.all("menu1").value;
		
		document.getElementById("result").value=str;
		document.all("form1").submit();
	}
}
function F5()
{
	window.location.reload();
}

function test(){
	
	
			alert(document.all("menu1").value);
}
</script>
<BODY class="mainbody" onLoad="this.focus()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" method="post" action="menuprivilege!saveprivilege.action">
  <tr>
    <td colspan="3" valign="middle" class="table_td_jb">&nbsp;&nbsp;<a href="#" onClick="F8()">保存[F8]</a><a href="#" onClick="F5()">刷新[F5]</a></td>
  </tr>
  <tr>
    <td colspan="3" valign="top" class="main_table_centerbg" align="center">
	<table width="100%" height="40" border="0" cellpadding="3" cellspacing="5" bgcolor="b2d5ff">
      <tr>
        <td width="11%" bgcolor="#FFFFFF">请选角色：
          <select  name="menu1" id="menu1"  onChange="change()" >
          <option value="selection">==请选择==</option>
                <%if(!(roleid.equals(""))){ %>
            		<option  value="<%=roleid%>" selected><%=rolename%></option>
            	<%} %>
            <s:iterator value="roleslist" status="st" id="row">
					 <option value ="${row.id}">${row.rolename}</option> 
			</s:iterator>
          </select></td>
      </tr>
    </table>
	
	<table width="100%" border="1" cellpadding="0" cellspacing="0" class="table_list">
	<tr>
	<td style="width: 50%">
	
	 <div style="overflow-x: auto; overflow-y: auto; height: 750px;">
		<ul id="treeDemo" class="ztree"></ul>
	 </div>
	</td>
	
	</tr>
     

    </table>
    <input name="result" type="hidden" id="result" value=""></td>
  </tr>
</form>
</table>
</BODY>
</HTML>
