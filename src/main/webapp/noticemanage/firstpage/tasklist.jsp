<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String orgcode=(String)request.getSession().getAttribute("orgcode");
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Datatable-serverSide 服务器分页例子</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/dataTables/jquery.dataTables.css">
     <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css">
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
<script type="text/javascript">
function taskdo(taskid,title){
	var diag = new top.Dialog();
	diag.Title = title;
	diag.Width=800;
	diag.Height=600;
	diag.URL = "task!taskdo.action?taskid="+taskid;
	diag.OKEvent = function(){
		diag.innerFrame.contentWindow.document.all("Submit").click();
		diag.close();
	}
	diag.show();
}
</script>
<script type="text/javascript">
//***********************************************************************************************************
    var table;
    $(function () {
    	
        var tpl = $("#tpl").html();
        //预编译模板
        var template = Handlebars.compile(tpl);
        table = $('#example').DataTable({
            "pagingType":   "full_numbers",
            "language":  { "decimal":",",  
            	"thousands":".", 
            	"sProcessing":"处理中...", 
            	"sLengthMenu":"显示_MENU_项结果",
            	"sZeroRecords":"没有匹配结果",
            	"sInfo":"显示第_START_至_END_项结果，共_TOTAL_项",
            	"sInfoEmpty":"显示第0至0项结果，共0项",
            	"sInfoFiltered":"(由_MAX_项结果过滤)",
            	"sInfoPostFix":"",
            	"sSearch":"搜索:",
            	"sUrl":"",
            	"sEmptyTable":"表中数据为空",
            	"sLoadingRecords":"载入中...",
            	"sInfoThousands":",",
            	"oPaginate":{ "sFirst":"首页","sPrevious":"上页","sNext":"下页","sLast":"末页"}, 
            	"oAria":{ "sSortAscending":":以升序排列此列","sSortDescending":":以降序排列此列"}},
            "dom": "<'row'<'col-xs-2'l><'#mytool.col-xs-4'><'col-xs-6'f>r>" +   //dom定位，即分页搜索功能位置
                    "t" +
                    "<'row'<'col-xs-6'i><'col-xs-6'p>>"

        });
    });
</script>
</head>
<body>
<div align="center"><font color="#990000"><img src="<%=path%>/images/icons/ico4.gif" width="15" height="15"></font><font color="#993300">&nbsp;&nbsp;待办事项</font></div>
<div class="container" style="position: relative;  height:95%; width:100%;">
    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
        <tr>
        	<th>ID：</th>
        	<th>时间：</th>
            <th>任务名称：</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
	<s:iterator value="tasklist" status="st" id="row">
		<tr>
		<td><s:property value="#row.getId()" /></td>
		<td><s:property value="#row.getCreateTime()" /></td>
		<td><s:property value="#row.getName()" /></td>
		<td><a href="#" class="button4" onclick="taskdo('<s:property value="#row.getId()"/>','任务处理')">处理</a></td>
		</tr>
	</s:iterator>
        </tbody>
        <!-- tbody是必须的 -->
    </table>
</div>
</body>
</html>
