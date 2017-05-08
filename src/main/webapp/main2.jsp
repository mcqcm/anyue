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
	String message="";
	if(request.getAttribute("message")!=null)
		message=(String)request.getAttribute("message");
	System.out.println("message:::::::"+message);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理系统</title>
<link href="css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="css/skins/sky/import_skin.css" rel="stylesheet" type="text/css" themeColor="blue"/>
<link rel="stylesheet" type="text/css"
	href="${css_path}/admin/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/icon.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/demo.css">
<link rel="stylesheet" type="text/css"
	href="${css_path}/jquery.alerts.css" />
<link rel="stylesheet" type="text/css" href="${css_path}/comments.css" />

<script type="text/javascript" src="${js_path}/jquery.js"></script>
<script type="text/javascript" src="${js_path}/menu.js"></script>
<script type="text/javascript" src="${js_path}/jquery.json-2.4.min.js"></script>
<script type="text/javascript"
	src="${js_path}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${js_path}/jquery.alerts.js"></script>
<link href="${request_path}/umeditor/themes/default/css/umeditor.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8"
	src="${request_path}/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${request_path}/umeditor/umeditor.min.js"></script>
<script type="text/javascript"
	src="${request_path}/umeditor/lang/zh-cn/zh-cn.js"></script>
<script language="JavaScript" type="text/javascript"
	src="${js_path}/footer.js" charset="UTF-8"></script>
	<!--引入组件start-->
<script type="text/javascript" src="js/attention/zDialog/zDrag.js"></script>
<script type="text/javascript" src="js/attention/zDialog/zDialog.js"></script>
<script type="text/javascript" src="js/tools/tabtool.js"></script>
<!--引入弹窗组件end-->
<style type="text/css">
a:link,a:visited{color:#000000;}
.easyui-tabs .panel-body{overflow:hidden;}
</style>
</head>
<script type="text/javascript">
$(function () {
	if('<%=message%>'=='licenceouttime'){
	var diag = new Dialog();
	diag.Width = 200;
	diag.Height = 100;
	diag.Modal = false;
	diag.Title = "消息通知";
	diag.Top="100%";
	diag.Left="100%";
	diag.URL = "javascript:void(document.write(\'您的许可证已经到期，请及时办理！\'))";
	diag.show()
	}
})

function modpassword(){
	var diag = new Dialog();
	diag.Title = "修改密码";
	diag.Width=500;
	diag.Height=400;
	diag.URL = "login!getaccount.action";
	diag.OKEvent = function(){
		diag.innerFrame.contentWindow.document.all("Submit").click();
		diag.close();
	}
	diag.show();
}

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
  <style type="text/css">
        html,body {
            width:100%;
            height:100%;
            margin:0px;
            padding:0px;
        }
    </style>
<body class="easyui-layout" style="text-align:center; position:relative; width:100%;overflow-y:hidden;">
		<div data-options="region:'north'"
			style="height: 80px;background: url(${image_path}/body_bg.png) repeat-x scroll center 0 rgba(0, 0, 0, 0);">
			<div style="float: left; margin-bottom: 5px; margin-top: 0px;">
			<div style="float: left; margin-bottom: 0px; margin-top: 0px;">
			&nbsp;&nbsp;&nbsp;&nbsp;<img src="/anyue/images/logo.png" height="60" alt="" /> 
			</div>
			<div style="float: right; margin-bottom: 0px; margin-top: 0px;">
			<font color="#009933" style="font-weight:bold;font-size: 45px; line-height: 40px; font-style: normal;">&nbsp;&nbsp;&nbsp;&nbsp;安岳县食品药品监督管理局</font><br>
			<font color="#009933" style="font-weight:bold;font-size: 25px; line-height: 20px; font-style: normal;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--餐饮服务食品安全管理系统</font>
			</div>
			</div>
					<div style="float: right; margin-bottom: 5px; margin-top: 20px;">
						<li><span style="float:right;font-family:华文中宋; color:red; " class="icon_no hand" onclick='top.Dialog.confirm("确定要退出系统吗",function(){window.location="login!index.action"});'>退出系统</span>
						<span style="float:right;font-family:华文中宋; color:red; " class="icon_no hand" onclick='modpassword()'>修改密码</span>
						</li><br>
						<li>
							欢迎用户:<span style="font-family:华文中宋; color:red; ">${loginuser.username}</span>，今天是
						<script>
							var weekDayLabels = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
							var now = new Date();
						    var year=now.getFullYear();
							var month=now.getMonth()+1;
							var day=now.getDate()
						    var currentime = year+"年"+month+"月"+day+"日 "+weekDayLabels[now.getDay()]
							document.write(currentime)
						</script>

						</li>
					</div>
		</div>
<!-- max-width: 177px; -->
		<div data-options="region:'west',split:true" title="系统菜单"
			style="width: 180px">
			<div class="easyui-accordion" style="width: 180px; overflow:auto" data-options="fit:true,border:false,plain:true">
				<ul id="tt" class="easyui-tree"
					data-options="url:'${request_path}/menu!query.action',method:'get',animate:true,dnd:true">
				</ul>
			</div>
		</div>
		<div id="mainPanle" data-options="region:'center',split:true,title:'主界面',iconCls:'icon-ok'"
			style="width: auto; overflow-y:hidden">
			<div id="right_tab" class="easyui-tabs" 
				data-options="fit:true,border:false,plain:true" >
<!-- 				<div title="首页" id="tab_iframe_div" -->
<%-- 					data-options="href:'${request_path}/about.html'" --%>
<!-- 					style="padding: 10px;"> -->
					
<!-- 				</div> -->
                 <div title="首页" id="tab_iframe_div"
					data-options="href:'${request_path}/noticemanage/firstpage/firstmain.jsp'"
					style="padding: 10px;">
					
				</div>
				  </div>

			</div>
		</div>

<div id="rcmenu" class="easyui-menu" style="">
<div id="tabupdate">刷新</div>
    <div data-options="iconCls:'icon-cancel'" id="closecur">
        关闭
    </div>
    <div id="closeall">
        关闭全部
    </div>
    <div id="closeother">
        关闭其他
    </div>
    <div class="menu-sep"></div>
    <div id="closeright">
        关闭右侧标签页
    </div>
    <div id="closeleft">
        关闭左侧标签页
    </div>
</div>

	<div data-options="region:'south',border:false" style="text-align:center;position:relative;height:30px; overflow:hidden;"><jsp:include page="bottom.jsp"></jsp:include></div>
</body>
</html>