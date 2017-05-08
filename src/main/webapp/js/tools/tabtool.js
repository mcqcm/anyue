	$(function() {
		tabCloseEven();
		$('#tt').tree(
				{
					onClick : function(node) {
						if ($.trim(node.menuurl) == ""
								|| $.trim(node.menuurl) == "undefined"
								|| $.trim(node.menuurl) == null) {

						} else {
							var url =  node.menuurl;
							open1(node.text, node.englishname, url); // alert node text property when clicked
						}
					}
				});
		
	});
    var id=1;
	function open1(name, englishname, url) {
		if ($('#right_tab').tabs('exists', name)) {
			$('#right_tab').tabs('select', name);
		} else {
			id=id+1;
			var iframestr=" <iframe scrolling=\"auto\" allowTransparency=\"true\" name="+englishname+" id="+id+" src="+url+" style=\"border: 0; width: 100%; height: 99%;\"></iframe>";
			if (url.indexOf("html") < 0|| url.indexOf("monitoring")< 0) {
				$('#right_tab').tabs('add', {
					title : name,
					content : iframestr,
					closable : true
				});
// 				$("#tab_iframe_div").html(iframestr);
			} else {
				$('#right_tab').tabs('add', {
					title : name,
					href : url,
					closable : true

				});
			}

		}
	}
function tabCloseEven() {
	$(".tabs-header").bind('contextmenu',function(e){
        e.preventDefault();
        $('#rcmenu').menu('show', {
            left: e.pageX,
            top: e.pageY
        });
    });
//	$(".tabs-inner").bind('contextmenu', function(e) {
//		$('#rcmenu').menu('show', {
//			left : e.pageX,
//			top : e.pageY
//		});
//		var subtitle = $(this).children(".tabs-closable").text();
//		$('#rcmenu').data("currtab", subtitle);
//		// $('#maintabs').tabs('select',subtitle);
//		return false;
//	});
	$("#tabupdate").bind("click",function() {
		var currTab = $('#right_tab').tabs('getSelected');
		var name=$(currTab.panel('options').content).attr('name');
		id=id+1;
		var url = $(currTab.panel('options').content).attr('src');
		$('#right_tab').tabs('update', {
			tab : currTab,
			options : {
				content : " <iframe scrolling=\"auto\" allowTransparency=\"true\" name="+name+" id="+id+" src="+url+" style=\"border: 0; width: 100%; height: 99%;\"></iframe>"
			}
		});
	});
    //关闭当前标签页
    $("#closecur").bind("click",function(){
        var tab = $('#right_tab').tabs('getSelected');
        var index = $('#right_tab').tabs('getTabIndex',tab);
        $('#right_tab').tabs('close',index);
    });
    //关闭所有标签页//不关i=0
    $("#closeall").bind("click",function(){
        var tablist = $('#right_tab').tabs('tabs');
        for(var i=tablist.length-1;i>0;i--){
            $('#right_tab').tabs('close',i);
        }
    });
    //关闭非当前标签页（先关闭右侧，再关闭左侧）//不关i=0
    $("#closeother").bind("click",function(){
        var tablist = $('#right_tab').tabs('tabs');
        var tab = $('#right_tab').tabs('getSelected');
        var index = $('#right_tab').tabs('getTabIndex',tab);
        for(var i=tablist.length-1;i>index;i--){
            $('#right_tab').tabs('close',i);
        }
        var num = index-1;
        for(var i=num;i>0;i--){
            $('#right_tab').tabs('close',i);
        }
    });
    //关闭当前标签页右侧标签页
    $("#closeright").bind("click",function(){
        var tablist = $('#right_tab').tabs('tabs');
        var tab = $('#right_tab').tabs('getSelected');
        var index = $('#right_tab').tabs('getTabIndex',tab);
        for(var i=tablist.length-1;i>index;i--){
            $('#right_tab').tabs('close',i);
        }
    });
    //关闭当前标签页左侧标签页//不关i=0
    $("#closeleft").bind("click",function(){
        var tab = $('#right_tab').tabs('getSelected');
        var index = $('#right_tab').tabs('getTabIndex',tab);
        var num = index-1;
        for(var i=num;i>0;i--){
            $('#right_tab').tabs('close',i);
        }
    });
}
