<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>XXXX管理系统</title>
<link href="<%=path%>/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/skins/sky/import_skin.css" rel="stylesheet" type="text/css" themeColor="blue"/>
<script type="text/javascript" src="<%=path%>/js/jquery-1.4.js"></script>
<script type="text/javascript" src="<%=path%>/js/bsFormat.js"></script>
<!--框架必需end-->

<!--引入组件start-->
<script type="text/javascript" src="<%=path%>/js/attention/zDialog/zDrag.js"></script>
<script type="text/javascript" src="<%=path%>/js/attention/zDialog/zDialog.js"></script>
<!--引入弹窗组件end-->

<!--修正IE6支持透明png图片start-->
<script type="text/javascript" src="<%=path%>/js/method/pngFix/supersleight.js"></script>
<style>

.fontchange{
   font-size:40px;
}
/*提示信息*/
</style>
</head>
<script type="text/javascript">
</script>
<body>
<div style="text-align: center;position: relative;  height:100%; width:100%; border:1px ">
<table width="100%" height="100%"  border="0" align="left" cellpadding="0" cellspacing="1" class="table_border0">
	<tr>
		<!--左侧区域start-->
		<td width="50%" height="100%">

				<div style="text-align: center;position: relative;  height:100%; width:100%; border:1px ">
					<IFRAME scrolling="no" width="100%" height="100%"  frameBorder=0 id=firstleft name=firstleft src="task!gettasklist.action"  allowTransparency="true"></IFRAME>
				</div>
		</td>
		<!--左侧区域end-->
		<!--中间栏区域start-->
		<td class="main_shutiao">
			<div class="bs_leftArr" id="bs_center" title="收缩面板"></div>
		</td>
		<!--中间栏区域end-->
		<!--右侧区域start-->
		<td width="50%" height="100%" >
									<div style="text-align: center;position: relative;  height:100%; width:100%; border:1px ">
									       <IFRAME scrolling="auto" width="100%" height="99%" frameBorder=0 id=firstright name=firstright src="/anyue/noticemanage/firstpage/noticelist.jsp"  allowTransparency="true"></IFRAME>
									</div>
		</td>
		<!--右侧区域end-->
	</tr>
</table>
</div>
</body>
</html>