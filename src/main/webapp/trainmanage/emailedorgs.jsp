<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Datatable-serverSide 服务器分页例子</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/dataTables/jquery.dataTables.css">
     <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap/bootstrap.min.css"/>
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
//***********************************************************************************************************
    var table;
    $(function () {
    	
        var tpl = $("#tpl").html();
        //预编译模板
        var template = Handlebars.compile(tpl);
        $('#example tfoot th').each( function () {
            	$(this).html( '<input type="text" placeholder="Search" />' );
        } );
        table = $('#example').DataTable({
            "columnDefs": [{
                "searchable": false,
                "orderable": false,
                "targets": 0
            }],
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
                    "<'row'<'col-xs-6'i><'col-xs-6'p>>",
            "order": [[ 7, "desc" ]]

        });
		   table.on('order.dt search.dt',//将ID列改为列序号
				   function() {
				       table.column(0, {
				           search: 'applied',
				           order: 'applied'
				       }).nodes().each(function(cell, i) {
				           cell.innerHTML = i + 1;
				       });
				   }).draw();
        $('#example tbody').on('click', 'tr', function () {
            var name = $('td', this).eq(0).text();
           // alert( '你点击了 '+name+'这行' );
        } );
//**************************************************************************************************************
      $('a.toggle-vis').on( 'click', function (e) {//字段隐藏和显示
        e.preventDefault();
 
        // Get the column API object
        var column = table.column( $(this).attr('data-column') );
        // Toggle the visibility
        column.visible( ! column.visible() );
    } );

        table.columns().eq( 0 ).each( function ( colIdx ) {
            $( 'input', table.column( colIdx ).footer() ).on( 'keyup change', function () {
                table
                    .column( colIdx )
                    .search( this.value )
                    .draw();
            } );
        } );
    });
</script>
</head>
<body>
<div class="container" style="position: relative;  height:95%; width:100%;">
	<div align="left" >
	<a align="left">列隐藏：</a>&nbsp;
	<a class="toggle-vis" data-column="0">序号</a>&nbsp;
	<a class="toggle-vis" data-column="1">餐饮单位名称</a>&nbsp;
	<a class="toggle-vis" data-column="2">地址</a>&nbsp;
	<a class="toggle-vis" data-column="3">负责人</a>&nbsp;
	<a class="toggle-vis" data-column="4">联系方式</a>&nbsp;
	<a class="toggle-vis" data-column="5">邮箱地址</a>&nbsp;
	<a class="toggle-vis" data-column="6">餐饮类型</a>&nbsp;
	<a class="toggle-vis" data-column="7">所属监管部门</a>&nbsp;
	</div>
    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
        <tr>
        	<th>序号</th>
        	<th>餐饮单位名称</th>
            <th>地址</th>
            <th>负责人</th>
            <th>联系方式</th>
            <th>邮箱地址</th>
            <th>餐饮类型</th>
            <th>所属监管部门</th>
        </tr>
        </thead>
        <tfoot>
        <tr>
        	<th></th>
        	<th></th>
            <th></th>
            <th></th>
        	<th></th>
        	<th></th>
            <th></th>
            <th></th>
        </tr>
        </tfoot>
        <tbody>
                <s:iterator value="mailedorgs" status="st" id="row">
				<tr>
		        	<th></th>
		        	<th>${row.orgname}</th>
		            <th>${row.orglocation}</th>
		            <th>${row.chairman}</th>
		            <th>${row.callnum}</th>
		            <th>${row.email}</th>
		            <th>${row.cateringtype}</th>
		            <th>${row.belongname}</th>
		        </tr>
		</s:iterator>
        </tbody>
        <!-- tbody是必须的 -->
    </table>
</div>
</body>
</html>
