<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
     <link rel="stylesheet" type="text/css" href="<%=path%>/css/dataTables/dataTables.bootstrap.css"/>
<!-- jQuery -->
<script type="text/javascript" charset="utf8" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" charset="utf8" src="<%=path%>/js/tableFunctions/functions.js"></script>
 
<!-- DataTables -->
<script type="text/javascript" charset="utf8" src="<%=path%>/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" charset="utf8" src="<%=path%>/js/dataTables/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf8" src="<%=path%>/js/bootstrap/dataTables.bootstrap.js"></script>
<script type="text/javascript" charset="utf8" src="<%=path%>/plugin/date/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" charset="utf8" src="<%=path%>/plugin/handlebars-v3.0.1.js"></script>


<style type="text/css">
    body { font-size: 120%; }
    th, td { white-space: nowrap; }
              div.dataTables_wrapper {
                margin: 0 auto;
              }
</style>

<!--定义操作列按钮模板-->
<script id="tpl" type="text/x-handlebars-template">
    {{#each func}}
    <button type="button" class="btn btn-{{this.type}} btn-sm" onclick="{{this.fn}}">{{this.name}}</button>
    {{/each}}
</script>

<script type="text/javascript">
//***********************************************************************************************************
    var table;
    var editFlag = false;
    $(function () {
    	
        var tpl = $("#tpl").html();
        //预编译模板
        var template = Handlebars.compile(tpl);
        table = $('#example').DataTable({
            "pagingType":   "full_numbers",
            //"order": [[ 1, "asc" ]],//设置默认排序列4
            "language":  {
            	"url":'<%=path%>'+"/js/Chinese.json"
            },
            "dom": "<'row'<'col-xs-2'l><'#mytool.col-xs-4'><'col-xs-6'f>r>" +   //dom定位，即分页搜索功能位置
                    "t" +
                    "<'row'<'col-xs-6'i><'col-xs-6'p>>",

            ajax: {
                url: "common!listALL.action",
                data: {
//**************************************************************************************************************
                    "entity": "Supplier",//必填
                    "cols":"id,supcode,supname,location",//必填,如果不加列序号，去掉number。其它要和pojo对应
                    "condition": "",  //第一次列出来数据的条件
                    "searchColumn":"",//以“,”分割，可不填
                    }
            },
            serverSide: true,
            columns: [
                      {"data": "id"},
                      {"data": "supcode"},
                      {"data": "supname"},
                      {"data": "location"}
            ],
            initComplete: function () {
   //             $("#mytool").append('<button type="button" class="btn btn-default btn-sm" onclick="add(\'supplier/addsupplier.jsp\',\'添加供应商\')">添加供应商</button>');
            }

        });
        $('#example tbody').on('click', 'tr', function () {
            var name = $('td', this).eq(0).text()+"&"+$('td', this).eq(2).text();
            document.getElementsByName("items").value=name;
        } );

    });
</script>
</head>
<body>
<div class="container" style="position: relative;  height:95%; width:100%;">
    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
        <tr>
        	<th>ID：</th>
            <th>供应商编号：</th>
            <th>供应商名称：</th>
            <th>供应商地址：</th>
        </tr>
        </thead>
        <tbody></tbody>
        <!-- tbody是必须的 -->
    </table>
    <input type="hidden" name="items" value=""/>
</div>
</body>
</html>
