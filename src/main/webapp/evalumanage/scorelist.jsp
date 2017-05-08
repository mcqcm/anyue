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
function add(url,title){
	var diag = new top.Dialog();
	diag.Title = title;
	diag.Width=650;
	diag.Height=500;
	diag.URL = url;
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
    var editFlag = false;
    $(function () {
    	
        var tpl = $("#tpl").html();
        //预编译模板
        var template = Handlebars.compile(tpl);
        $('#example tfoot th').each( function () {
			var datatype = $('#example tfoot th').eq( $(this).index() ).text();
			if(datatype == "date"){
				$(this).html( '<div id="reportrange"; margin-right: 2px"> '+
		                '<input type="text"  placeholder="Search"/> '+
		                '<b class="caret"></b></div> ');			
			}else{
            	$(this).html( '<input type="text" placeholder="Search" />' );
			}
        } );
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
                    "<'row'<'col-xs-6'i><'col-xs-6'p>>",

            ajax: {
                url: "common!listALL.action",
                data: {
//**************************************************************************************************************
                    "entity": "EvalResult",//必填
                    "condition": "",  //第一次列出来数据的条件
                    "searchColumn":"",//以“,”分割，可不填。对哪几列模糊查询
                    }
            },
            serverSide: true,
            columns: [
                      {"data": "id"},
                      {"data": "evaltime"},
                      {"data": "orgcode"},
                      {"data": "orgname"},
                      {"data": "belongname"},
                      {"data": "score"},
                      {"data": null}//操作
            ],
            columnDefs: [
                {//列添加函数
                    targets:6,
                    render: function (a, b, c, d) {
                        var context =
                        {
                            func: [
                                   {"name": "整改通知", "fn": "mod(\'" + "medicine!modmedicine.action" + "\',\'" + c.id + "\',\'" + "通知整改" + "\')", "type": "primary"}
                            ]
                        };
                        var html = template(context);
                        return html;
                    }
                }

            ],
            initComplete: function () {
                $("#mytool").append('<button type="button" class="btn btn-default btn-sm" onclick="add(\'evalu!evaluNotice.action\',\'发布结果公告\')">发布结果公告</button>');
            }

        });
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
	<a class="toggle-vis" data-column="0">ID</a>&nbsp;
	<a class="toggle-vis" data-column="1">评价季度</a>&nbsp;
	<a class="toggle-vis" data-column="2">食品店编号</a>&nbsp;
	<a class="toggle-vis" data-column="3">单位名称</a>&nbsp;
	<a class="toggle-vis" data-column="4">所属管辖单位</a>&nbsp;
	<a class="toggle-vis" data-column="5">得分</a>&nbsp;
	<a class="toggle-vis" data-column="6">操作</a>&nbsp;
	</div>
    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
        <tr>
        	<th>ID</th>
        	<th>评价季度</th>
            <th>食品店编号</th>
            <th>单位名称</th>
            <th>所属管辖单位</th>
            <th>得分</th>
            <th>操作</th>
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

        </tr>
        </tfoot>
        <tbody></tbody>
        <!-- tbody是必须的 -->
    </table>
</div>
</body>
</html>
