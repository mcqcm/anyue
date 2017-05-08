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

<!--定义操作列按钮模板-->
<script id="tpl" type="text/x-handlebars-template">
    {{#each func}}
    <button type="button" class="btn btn-{{this.type}} btn-sm" onclick="{{this.fn}}">{{this.name}}</button>
    {{/each}}
</script>

</head>
<body>
<div class="container" style="position: relative;  height:95%; width:100%;">
 <form name="form1" id="form1" action="record!addrecord.action?id=${orgInfo.orgcode}" target="recordmanage" method="post" class="elegant-aero">
 <h1 style="text-align:center">${orgInfo.orgname}
<span>档案信息</span>
</h1>
<!-- <div style="float:left"> -->
<label>
<span>店名：</span>
<input type="text" name="orgInfo.orgname" value="${orgInfo.orgname}" readonly="readonly"/>
</label>
<label style="margin: 0px 0px 0px;">
<span>地址：</span>
<input type="text" name="orgInfo.orglocation" value="${orgInfo.orglocation}" readonly="readonly"/>
</label>
<label>
<span>占地面积：</span>
<input type="text" name="record.area" value="${record.area}"/>
</label>
<label>
<span>员工总数：</span>
<input type="text" id="record.workernum" name="record.workernum" value="${record.workernum}" />
</label>
<label>
<span>就餐位置数量：</span>
<input type="text" name=record.seatnum  value="${record.seatnum}" />
</label>
<label>
<span>餐饮类型：</span>
<input type="text" name="orgInfo.cateringtype" value="${orgInfo.cateringtype}" readonly="readonly"/>
</label>
<!-- </div> -->
<!-- <div style="display: inline;"> -->
<label>
<span>许可证生效日期：</span>
<input type="text" name="licence.starttime" value="${licence.starttime}" readonly="readonly"/>
</label>
<label>
<span>许可证失效日期：</span>
<input type="text" name="licence.endtime" value="${licence.endtime}" readonly="readonly"/>
</label>
<label>
<span>从业许可证：</span>
<img src="/anyue/recordmanage/licenceimage.jsp" width="300" height="150" alt="" /> 
</label>
<!-- </div> -->
<label>
<span>&nbsp;&nbsp;</span>
<span>&nbsp;&nbsp;</span>
</label>
<label>
<span>&nbsp;&nbsp;</span>
<a href="applylicence!downlicence.action?id=${licence.id}" class="button">下载许可证</a>
</label>
	</form>	
</div>
</body>
</html>
