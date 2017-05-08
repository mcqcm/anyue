package com.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.Emailer;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Organize;
import com.orm.OutRubbish;
import com.orm.SystemUser;
import com.service.OrganizeService;
import com.service.OutRubbishService;
import com.service.UserService;

@SuppressWarnings("serial")
public class RubbishAction extends ActionSupport{
	private OutRubbish outRubbish;
	@Autowired
	OutRubbishService rubbishService;
	@Autowired
    OrganizeService orgservice;
    @Autowired
    UserService userService;
    @Autowired
    OrganizeService organizeService;
    @Autowired
	Emailer emailer;
    
    public String addRubbish() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
		SystemUser user=(SystemUser)request.getSession().getAttribute("loginuser");
		outRubbish.setOrgcode(user.getOrgcode());
		outRubbish.setOrgname(user.getOrgname());
		//emailer.sendmail(user.getEmail(),"12312312","testtest!!","text");
		//emailer.sendmail(user.getEmail(),"许可证办理成功","<h1>大家鼓掌！可证办理成功html</h1>","html");
    	boolean flag=rubbishService.saveOrUpdate(outRubbish);
        if (flag) {
        	//request.setAttribute("orgcode",user.getOrgcode());
            return "addSuccess";
        }
        return ERROR;
    }
    public String listRubbish() throws Exception {
    	//rubbishService.listAllOutRubbish();
    	HttpServletRequest request = ServletActionContext.getRequest(); 
		String orgcode=((SystemUser)request.getSession().getAttribute("loginuser")).getOrgcode();
		request.setAttribute("orgcode",orgcode);
        return "List";
    }
    
    
    public String ListAll() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
    	request.getSession().removeAttribute("orglist");
		request.getSession().setAttribute("orglist",organizeService.listAllOrganize());
        return "ListAll";
    }
    public String ListMine() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
		String orgcode=((SystemUser)request.getSession().getAttribute("loginuser")).getOrgcode();
		List<Organize> myshopslist=organizeService.findOrganizeByBelongcode(orgcode);
		if(myshopslist!=null){
			request.getSession().removeAttribute("myshopslist");
			request.getSession().setAttribute("myshopslist",myshopslist);
			return "ListMine";
		}
        return ERROR;
    }
	public String seeContent() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest(); 
		outRubbish=rubbishService.findOutRubbishByid(request.getParameter("id"));
        if (outRubbish!=null) {
            return "seeContent";
        }
        return ERROR;
    }
    public String seeOnlyContent() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest(); 
		outRubbish=rubbishService.findOutRubbishByid(request.getParameter("id"));
        if (outRubbish!=null) {
            return "seeOnlyContent";
        }
        return ERROR;
    }
    
    public String del() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
		boolean flag=rubbishService.deleteOutRubbishByid(request.getParameter("id"));
        if (flag) {
            return "List";
        }
        return ERROR;
    }
    
	public OutRubbish getOutRubbish() {
		return outRubbish;
	}
	public void setOutRubbish(OutRubbish outRubbish) {
		this.outRubbish = outRubbish;
	}
    
}
