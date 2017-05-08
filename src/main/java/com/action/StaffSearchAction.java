package com.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.orm.OrgStaff;
import com.orm.Organize;
import com.orm.SystemUser;
import com.service.OrgStaffService;
import com.service.OrganizeService;

@SuppressWarnings("serial")
public class StaffSearchAction extends ActionSupport {

    @Autowired
    OrgStaffService orgstaffService;
    @Autowired
    OrganizeService organizeService;
    
    
    
	@Override
    public String execute() throws Exception {
		return "ListAll";

    }
	public String searchall(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		request.getSession().removeAttribute("orglist");
		request.getSession().setAttribute("orglist",organizeService.listAllOrganize());
		
		return "getorgsuccess";
	}
	public String seehealthcard(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		//System.out.println(request.getParameter("id"));
		OrgStaff orgstaff=orgstaffService.findOrgStaffBystaffcode(request.getParameter("id"));
		
        if (orgstaff!=null) {
        	request.setAttribute("healthimage", orgstaff.getHealthcard());
            return "seecard";
        }
        return ERROR;
	}
	public String mysearch(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		String orgcode=((SystemUser)request.getSession().getAttribute("loginuser")).getOrgcode();
		List<Organize> myshopslist=organizeService.findOrganizeByBelongcode(orgcode);
		if(myshopslist!=null){
			request.getSession().removeAttribute("myshopslist");
			request.getSession().setAttribute("myshopslist",myshopslist);
			return "listmyshops";
		}
		return ERROR;
	}
}
