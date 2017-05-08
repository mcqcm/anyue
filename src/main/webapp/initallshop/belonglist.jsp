<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
String path = request.getContextPath();
String belongcode="";
if(request.getAttribute("belongcode")!=null){
	belongcode=(String)request.getAttribute("belongcode");
}
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
function uplicence(orgcode,title){
	var diag = new top.Dialog();
	diag.Title = title;
	diag.Width=500;
	diag.Height=400;
	diag.URL = "/anyue/initallshop/uplicence.jsp?orgcode="+orgcode;
	diag.OKEvent = function(){
		diag.innerFrame.contentWindow.document.all("submit2").click();
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
                    "entity": "Organize",//必填
                    "condition": "t.belongcode="+"'<%=belongcode%>'",  //第一次列出来数据的条件
                    "searchColumn":"",//以“,”分割，可不填。对哪几列模糊查询
                    }
            },
            serverSide: true,
            columns: [
                      {"data": "orgcode"},
                      {"data": "orgname"},
                      {"data": "orglocation"},
                      {"data": "chairman"},
                      {"data": "callnum"},
                      {"data": "email"},
                      {"data": "cateringtype"},
                      {"data": "runtime"},
                      {"data": null}//操作
            ],
            columnDefs: [
                {//列添加函数
                    targets:8,
                    render: function (a, b, c, d) {
                        var context =
                        {
                            func: [
                                 {"name": "修改", "fn": "mod(\'" + "initallshop!modorg.action" + "\',\'" + c.orgcode + "\',\'" + "修改餐饮单位信息" + "\')", "type": "primary"},
                                 {"name": "上传许可证", "fn": "uplicence(\'" + c.orgcode + "\',\'" + "上传许可证" + "\')", "type": "primary"},
                                 {"name": "删除", "fn": "del(\'" + "initallshop!delorg.action" + "\',\'" + c.orgcode + "\')", "type": "danger"},
                                 {"name": "查看档案", "fn": "see(\'" + "record!seerecord.action" + "\',\'" + c.orgcode + "\',\'" + "查看档案" + "\')", "type": "primary"}
                            ]
                        };
                        var html = template(context);
                        return html;
                    }
                }

            ],
            initComplete: function () {
                $("#mytool").append('<button type="button" class="btn btn-default btn-sm" onclick="add(\'initallshop/addshop.jsp?belongcode=<%=belongcode%>\',\'添加辖区餐饮单位\')">添加辖区餐饮单位</button>');
                datesearch();//没有日期查询可以不加
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
	<a class="toggle-vis" data-column="0">餐饮单位编码</a>&nbsp;
	<a class="toggle-vis" data-column="1">餐饮单位名称</a>&nbsp;
	<a class="toggle-vis" data-column="2">地址</a>&nbsp;
	<a class="toggle-vis" data-column="3">负责人</a>&nbsp;
	<a class="toggle-vis" data-column="4">联系方式</a>&nbsp;
	<a class="toggle-vis" data-column="5">邮箱地址</a>&nbsp;
	<a class="toggle-vis" data-column="6">餐饮类型</a>&nbsp;
	<a class="toggle-vis" data-column="7">开业时间</a>&nbsp;
	<a class="toggle-vis" data-column="8">操作</a>&nbsp;
	</div>
    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
        <tr>
        	<th>餐饮单位编码：</th>
        	<th>餐饮单位名称：</th>
            <th>地址：</th>
            <th>负责人：</th>
            <th>联系方式：</th>
            <th>邮箱地址：</th>
            <th>餐饮类型：</th>
            <th>开业时间：</th>
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
            <th></th>
            <th>date</th>

        </tr>
        </tfoot>
        <tbody></tbody>
        <!-- tbody是必须的 -->
    </table>
</div>
</body>
</html>
