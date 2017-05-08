<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>XXXX管理系统</title>
<link href="css/login/skin1/style.css" rel="stylesheet" type="text/css" id="skin"/>
<script type="text/javascript" src="js/jquery-1.4.js"></script>
<script type="text/javascript" src="js/login.js"></script>

<!--引入弹窗组件start-->
<script type="text/javascript" src="js/attention/zDialog/zDrag.js"></script>
<script type="text/javascript" src="js/attention/zDialog/zDialog.js"></script>
<!--引入弹窗组件end-->

<!--修正IE6支持透明png图片start-->
<script type="text/javascript" src="js/method/pngFix/supersleight.js"></script>
<!--修正IE6支持透明png图片end-->

<!--居中显示start-->
<script type="text/javascript" src="js/method/center-plugin.js"></script>
<script>
	$(function(){
		 $('.login_main').center();
	})
</script>
<!--居中显示end-->
<style>
/*提示信息*/	
#cursorMessageDiv {
	position: absolute;
	z-index: 99999;
	border: solid 1px #cc9933;
	background: #ffffcc;
	padding: 2px;
	margin: 0px;
	display: none;
	line-height:150%;
}
.fontchange{
   font-size:50px;
}
/*提示信息*/
</style>
</head>
<body>
<div class="login_main">
		<div class="login_top">
<!-- 			<div class="login_title"></div> -->
			<div class="fontchange" style="text-align:center; position:absolute; top:30%;height:40px;width:100%;border:1px">XXXX管理系统</div>
		</div>
		<div class="login_middle">
			<div class="login_middleleft"></div>
			<div class="login_middlecenter">
					<form action="login.action" class="login_form" method="post">
					<div class="login_user"><input type="text" name="loginuser.account"></div>
<%-- 					<div><s:fielderror cssStyle="color: red" fieldName="loginuser.account"/></div> --%>
					<div class="login_pass"><input type="password" name="loginuser.password"></div>
					<div><select name="role">  
					<s:iterator value="roleslist" status="st" id="row">
					  <option value ="${row.id}">${row.rolename}</option>   
					</s:iterator>
					</select> </div>
					<div class="clear"></div>
					<div class="login_button">
						<div class="login_button_left"><input type="submit" value="" onfocus="this.blur()"/></div>
						<div class="login_button_right"><input type="reset" value="" onfocus="this.blur()"/></div>
						<div class="clear"></div>
					</div>
					</form>
					<div class="login_info">${result} </div>
			</div>
			<div class="login_middleright"></div>
			<div class="clear"></div>
		</div>
		<div class="login_bottom">
			<div class="login_copyright">XXXX管理系统</div>
		</div>
	</div>
</body>
</html>