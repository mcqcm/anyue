<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jscomponent/easyui/themes/icon.css">
<script type="text/javascript" src="jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="jscomponent/tools/datagrid.js"></script>
<style type="text/css">
*{font-size:12px; font-family:微软雅黑,新宋体}
</style>
<style type="text/css"> 
table.table1{
    font-size: 16px;
    font-weight: bold;
    line-height: 0.6em;
    font-style: normal;
    border-collapse:separate;
}
.table1 thead th{
    padding:6px;
    text-align:center;
    color:#666;
    text-shadow:1px 1px 1px #fff;
    border:2px solid #000000;
}
.table1 thead th:empty{
    background:transparent;
    border:none;
}
.table1 tbody th{
    color:#fff;
    text-shadow:1px 1px 1px #fff;
    background-color:#9DD929;
    border:2px solid #000000;
    border-right:3px solid #9ED929;
}
.table1 tbody td{
    padding:6px;
    text-align:center;
    border: 2px solid #000000;
    color:#666;
    text-shadow:1px 1px 1px #fff;
}
</style>
</head>
<body>

<form name="form1" id="form1" action="notice!evaluNotice" target="scoretable" method="post">
<span>&nbsp;&nbsp;评价季度:</span>
<input id="evaluYear" name="evaluYear" style="width: 120px" value="${resultlists[0].evaltime}" readonly="readonly">
	<div>
	<br>
	<span>&nbsp;&nbsp;通知标题:</span>
	<input id="notice.tittle" name="notice.tittle" style="width: 120px">
	 <table style="width:600px;border-spacing:1px;" class="table1" id="status">
	  <thead>
        <tr>
            <th field="name1" width="50">餐饮店名称</th>
            <th field="name2" width="50">单位编号</th>
            <th field="name3" width="50">所属部门</th>
            <th field="name4" width="50">得分</th>
        </tr>                          
    </thead>                           
    <tbody>

<c:forEach var="item" items="${resultlists}" varStatus="status">
    <tr>
    <td>${item.orgname}</td>
    <td>${item.orgcode}</td>
    <td>${item.belongname}</td>
    <td>${item.score}</td>
    </tr>
</c:forEach> 
	</tbody>
	 </table>
	</div>
	<input type="submit" name="Submit" class="button" value="发布" style="display:none"/>
	</form>
</body>

<script type="text/javascript" src="jscomponent/easyui/jquery.easyui.min.js"></script>
</html>