function add(url,title){
	var diag = new top.Dialog();
	diag.Title = title;
	diag.Width=500;
	diag.Height=400;
	diag.URL = url;
	diag.OKEvent = function(){
		diag.innerFrame.contentWindow.document.all("Submit").click();
		diag.close();
	}
	diag.show();
}
function mod(url,id,title){
	var diag = new top.Dialog();
	diag.Title = title;
	diag.Width=500;
	diag.Height=400;
	diag.URL = url+"?id="+id;
	diag.OKEvent = function(){
		diag.innerFrame.contentWindow.document.all("Submit").click();
		diag.close();
	}
	diag.show();
}
function see(url,id,title){
	var diag = new top.Dialog();
	diag.Title = title;
	diag.Width=500;
	diag.Height=400;
	diag.URL = url+"?id="+id;
	diag.OKEvent = function(){
		diag.close();
	}
	diag.show();
}
function del(action,id){
    if (!confirm("您确认要删除吗？此删除为级联删除？删除操作不可恢复！")) {
        return false;
    }
	var aj = $.ajax( {    
	    url:action,// 跳转到 action    
	    data:{    
	             id : id  
	    },    
	    type:'post',    
	    cache:false,       
	    success:function(data) {    
	        alert("Success！");
	        window.location.reload();
	     },    
	     error : function() {     
	          alert("异常！");    
	     }    
	}); 
}