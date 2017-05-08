<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String parentorgcode=request.getParameter("orgcode");
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Datatable-serverSide 服务器分页例子</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/dataTables/jquery.dataTables.css">
     <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap/bootstrap.min.css"/>
     <link rel="stylesheet" type="text/css" href="<%=path%>/css/input.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/tools/datatabletool.css">
     <link rel="stylesheet" type="text/css" href="<%=path%>/css/dataTables/dataTables.bootstrap.css"/>
     <link rel="stylesheet" type="text/css" href="<%=path%>/js/daterangepicker/daterangepicker-bs3.css"/>
     <link rel="stylesheet" type="text/css" href="<%=path%>/js/daterangepicker/font-awesome.min.css"/>
<!-- jQuery -->
<script type="text/javascript" charset="utf8" src="<%=path%>/js/jquery.js"></script>
 <script type="text/javascript" charset="utf8" src="<%=path%>/js/tableFunctions/functions.js"></script>
 <script type="text/javascript" charset="utf8" src="<%=path%>/js/tableFunctions/datesearch.js"></script>
<!-- DataTables -->
<script type="text/javascript" charset="utf8" src="<%=path%>/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" charset="utf8" src="<%=path%>/js/dataTables/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf8" src="<%=path%>/js/bootstrap/dataTables.bootstrap.js"></script>
<script type="text/javascript" charset="utf8" src="<%=path%>/js/daterangepicker/moment.min.js"></script>
<script type="text/javascript" charset="utf8" src="<%=path%>/js/daterangepicker/daterangepicker.js"></script>
<script type="text/javascript" charset="utf8" src="<%=path%>/plugin/handlebars-v3.0.1.js"></script>
<script language="JavaScript" src="<%=path%>/js/form/MyDatePicker/WdatePicker.js"></script>

<!--定义操作列按钮模板-->
<script id="tpl" type="text/x-handlebars-template">
    {{#each func}}
    <button type="button" class="btn btn-{{this.type}} btn-sm" onclick="{{this.fn}}">{{this.name}}</button>
    {{/each}}
</script>

</head>
<body>
<div class="container" style="position: relative;  height:95%; width:100%;">
 <form name="form1" id="form1" action="evalu!addrecord.action" target="evalurecord" style="width:450px;" method="post" class="elegant-aero"> 
<!-- <form name="form1" id="form1" action="evalu!addrecord.action" target="evalumanage" method="post" > -->
 <h1>发起评价信息</h1>
<label>
<span>评价时间：</span>
<input type="text" id="evalRecord.evaltime" name="evalRecord.evaltime"  value="${thisYear}" readonly="readonly">
</label>
<label>
<span>发起人：</span>
<input type="text" name="evalRecord.startusername" value="${evalRecord.startusername}"/>
</label>
<label>
<span>发起时间：</span>
<input type="text" class="Wdate" name="evalRecord.handletime" onfocus="new WdatePicker({lang:'zh-cn'})" value="${evalRecord.handletime}" size="15"/>
</label>

<label>
<span>&nbsp;&nbsp;</span>
<input type="submit" name="Submit" class="button" value="发起" /> &nbsp;&nbsp;&nbsp;
</label>
	</form>	
</div>
</body>
</html>
