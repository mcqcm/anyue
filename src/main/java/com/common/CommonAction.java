package com.common;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.CommonListEntity;
import com.common.CommonService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CommonAction extends ActionSupport {

    @Autowired
    CommonService commonService;


	@Override
    public String execute() throws Exception {
        //鐐瑰嚮鈥滅櫥闄嗏?濅箣鍚庢墽琛屾鍑芥暟
        return ERROR;
    }

	public void listALL() throws Exception {
        //删除已经登记的车辆的信息
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpServletResponse response=ServletActionContext.getResponse();
	    //获取请求次数
		String entity=request.getParameter("entity");
		if(entity==null)	return;
	    String draw = "0";
	    draw = request.getParameter("draw");
	    //数据起始位置
	    String start = request.getParameter("start");
	    //数据长度
	    String length = request.getParameter("length");
	  //获取客户端需要那一列排序
	    String orderColumn = "0";
	    orderColumn = request.getParameter("order[0][column]");
	    String orderDir = "asc";
	    orderDir = request.getParameter("order[0][dir]");
	    String searchValue = new String(request.getParameter("search[value]").getBytes("ISO-8859-1"),"UTF-8");
	    String searchColumn = request.getParameter("searchColumn");//顶部全局模糊查询的某几个列
	    String condition=request.getParameter("condition");
	    String cols="";
	    CommonListEntity listEntity=new CommonListEntity();
	    ArrayList<String> columnsearchlist=new ArrayList<String>();//没列查询输入
		ArrayList<Integer> searchcol=new ArrayList<Integer>();
	    for(int i=0;;i++){
	    	String gets=request.getParameter("columns["+i+"][data]");
	    	if(gets!=null&&(!gets.equals(""))){
	    		cols+=gets+",";
	    		String columnsearchvalue= new String(request.getParameter("columns["+i+"][search][value]").getBytes("ISO-8859-1"),"UTF-8");
	    		if(columnsearchvalue!=null&&(!columnsearchvalue.equals(""))){
	    			columnsearchlist.add(columnsearchvalue);
	    			searchcol.add(i);
	    		}
	    	}else{
	    		break;
	    	}
	    }
	    listEntity.setColumnsearchvalue(columnsearchlist);
	    listEntity.setSearchcol(searchcol);
	    listEntity.setDraw(draw);
	    listEntity.setEntity(entity);
	    listEntity.setLength(length);
	    listEntity.setOrdercolumn(orderColumn);
	    listEntity.setOrderDir(orderDir);
	    listEntity.setSearchvalue(searchValue);
	    listEntity.setSearchcolumn(searchColumn);
	    listEntity.setStart(start);
	    listEntity.setCols(cols);
	    listEntity.setCondition(condition);
	    String json=commonService.listEntity(listEntity);
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	    return;
    }
    public String exit() {
        ActionContext.getContext().getSession().clear();
        return "exit";
    }
}
