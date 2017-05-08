function datesearch(){
 

       //时间插件
       $('#reportrange input').html(moment().subtract(1,'hours').format('YYYY-MM-DD') + ' - ' + moment().format('YYYY-MM-DD'));
 
       $('#reportrange input').daterangepicker(
               {
                    startDate: moment().startOf('day'),
                   endDate: moment(),
                   minDate: '01/01/2012',    //最小时间
                   maxDate : moment(), //最大时间
                   timeZone: moment().utcOffset(),
                   dateLimit : {
                       days : 2000
                   }, //起止时间的最大间隔
                   showDropdowns : true,
                   showWeekNumbers : false, //是否显示第几周
                   timePicker : true, //是否显示小时和分钟
                   timePickerIncrement : 60, //时间的增量，单位为分钟
                   timePicker12Hour : false, //是否使用12小时制来显示时间
                   ranges : {
                       //'最近1小时': [moment().subtract('hours',1), moment()],
                       '今日': [moment().startOf('day'), moment()],
                       '昨日': [moment().subtract('days', 1).startOf('day'), moment().subtract('days', 1).endOf('day')],
                       '最近7日': [moment().subtract('days', 6), moment()],
                       '最近30日': [moment().subtract('days', 29), moment()]
                   },
                   opens : 'right', //日期选择框的弹出位置
                   buttonClasses : [ 'btn btn-default' ],
                   applyClass : 'btn-small btn-primary blue',
                   cancelClass : 'btn-small',
                   format : 'YYYY-MM-DD HH:mm:ss', //控件中from和to 显示的日期格式
                   separator : ' to ',
                   locale : {
                       applyLabel : '确定',
                       cancelLabel : '取消',
                       fromLabel : '起始时间',
                       toLabel : '结束时间',
                       customRangeLabel : '自定义',
                       daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
                       monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月',
                           '七月', '八月', '九月', '十月', '十一月', '十二月' ],
                       firstDay : 1
                   }
               }, function(start, end, label) {//格式化日期显示框
 
                   $('#reportrange input').html(start.format('YYYY-MM-DD') + ' - ' + end.format('YYYY-MM-DD'));
               });
 
       //设置日期菜单被选项  --开始--
       var dateOption ;
       if("${riqi}"=='day') {
           dateOption = "今日";
       }else if("${riqi}"=='yday') {
           dateOption = "昨日";
       }else if("${riqi}"=='week'){
           dateOption ="最近7日";
       }else if("${riqi}"=='month'){
           dateOption ="最近30日";
       }else if("${riqi}"=='year'){
           dateOption ="最近一年";
       }else{
           dateOption = "自定义";
       }
       $(".daterangepicker").find("li").each(function (){
           if($(this).hasClass("active")){
               $(this).removeClass("active");
           }
           if(dateOption==$(this).html()){
               $(this).addClass("active");
           }
       });
       //设置日期菜单被选项  --结束--
 
 
       //选择时间后触发重新加载的方法
       $("#reportrange").on('apply.daterangepicker',function(){
           //当选择时间后，出发dt的重新加载数据的方法
           table.ajax.reload();
           //获取dt请求参数
           var args = table.ajax.params();
           console.log("额外传到后台的参数值extra_search为："+args.extra_search);
       });
 
       function getParam(url) {
           var data = decodeURI(url).split("?")[1];
           var param = {};
           var strs = data.split("&");
 
           for(var i = 0; i<strs.length; i++){
               param[strs[i].split("=")[0]] = strs[i].split("=")[1];
           }
           return param;
       }
   }