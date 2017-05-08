package com.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Supplier;
import com.orm.SystemRole;
import com.orm.SystemUser;
import com.service.RoleService;
import com.service.UserService;

@SuppressWarnings("serial")
public class RoleAction extends ActionSupport {

	private SystemRole role;
	
	@Autowired
	RoleService roleService;
	@Override
    public String execute() throws Exception {
        return ERROR;
    }
	public String ListAll() throws Exception {
        return "ListAll";
    }
	public String List() throws Exception {
        return "List";
    }
	
	public String addrole() throws Exception {
		
		if(role.getId()!=null){
			SystemRole oldrole=roleService.findRoleByid(String.valueOf(role.getId()));
			if(oldrole.getSystemmenus().size()>0)
				role.setSystemmenus(oldrole.getSystemmenus());
			if(oldrole.getSystemusers().size()>0)
				role.setSystemusers(oldrole.getSystemusers());		
			role.setRolemode(oldrole.getRolemode());
			role.setValidflag(oldrole.getValidflag());
		}
        boolean flag=roleService.saveOrUpdate(getRole());
        if (flag) {
            return "addSuccess";
        }
        return ERROR;
    }
	public String modrole() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest(); 
		role=roleService.findRoleByid(request.getParameter("id"));
        if (role!=null) {
            return "modpage";
        }
        return ERROR;
    }
	public String delrole() throws Exception {
        //鍒犻櫎鐢ㄦ埛
		HttpServletRequest request = ServletActionContext.getRequest(); 
        boolean flag=roleService.deleteRoleByid(request.getParameter("id"));
        if (flag) {
            return "delSuccess";
        }
        return ERROR;
    }
	public SystemRole getRole() {
		return role;
	}
	public void setRole(SystemRole role) {
		this.role = role;
	}

}
