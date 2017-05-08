package com.action;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.SystemRole;
import com.orm.SystemUser;
import com.service.LicenceService;
import com.service.RoleService;
import com.service.UserService;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport {

    private List<SystemUser> userlist;
    public List<SystemRole> getRoleslist() {
		return roleslist;
	}



	public void setRoleslist(List<SystemRole> roleslist) {
		this.roleslist = roleslist;
	}



	private List<SystemRole> roleslist;
    private SystemUser user;
    private String queryname;
    private String oldaccount;
    public SystemUser getUser() {
		return user;
	}



	public void setUser(SystemUser user) {
		this.user = user;
	}



	public List<SystemUser> getUserlist() {
		return userlist;
	}



	public void setUserlist(List<SystemUser> userlist) {
		this.userlist = userlist;
	}



	@Autowired
    UserService userService;
    @Autowired
    RoleService roleService;


	@Override
    public String execute() throws Exception {
		return "ListAll";
    }
	public String ListAll() throws Exception {
        return "ListAll";
    }
	public String List() throws Exception {
        return "List";
    }
	public String adduser() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest(); 
		SystemRole role=roleService.findRoleByid(request.getParameter("userroleid"));
		user.setSystemrole(role);
		user.setUserrolename(role.getRolename());
		user.setPassword(getMD5(user.getPassword()));
		boolean f=userService.haveAccount(user.getAccount());
		if(f){
			return "haveuser";
		}
        boolean flag=userService.saveOrUpdate(user);
        if (flag) {
            return "addSuccess";
        }
        return ERROR;
    }
	public String moduser() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest(); 
		SystemRole role=roleService.findRoleByid(request.getParameter("userroleid"));
		user.setSystemrole(role);
		user.setUserrolename(role.getRolename());
		if(!(oldaccount.equals(user.getAccount()))){
			boolean f=userService.haveAccount(user.getAccount());
			if(f){
				return "haveuser";
			}
		}
        boolean flag=userService.saveOrUpdate(user);
        if (flag) {
            return "addSuccess";
        }
        return ERROR;
    }
	public String deluser() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest(); 
        boolean flag=userService.deleteUserByid(request.getParameter("id"));
        if (flag) {
            return "success";
        }
        return ERROR;
    }
    public String finduser() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest(); 
        setUser(userService.findUserByid(request.getParameter("id")));
        if (user!=null) {
        	oldaccount=user.getAccount();
        	roleslist = roleService.listAllRole();
            if (roleslist != null) {
            	return "findSuccess";
            }
        }
        return ERROR;
    }
    public String findrole() throws Exception {
        roleslist = roleService.listAllRole();
        if (roleslist != null) {
            return "adduser";
        }
        return ERROR;
    }
    public String querybyname() throws Exception {
    	userlist=userService.findUserByname(getQueryname());
        if (userlist!=null) {
            return "Success";
        }
        return ERROR;
    }
    public String exit() {
        ActionContext.getContext().getSession().clear();
        return "exit";
    }



	public String getQueryname() {
		return queryname;
	}



	public void setQueryname(String queryname) {
		this.queryname = queryname;
	}
	public String getMD5(String text)
	{
		String key = "1234567890";
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
			md.update(key.getBytes());
			ByteArrayInputStream bs = new ByteArrayInputStream(md.digest());
			StringWriter sw = new StringWriter();
			for (int i = 0; i < 16; i++)
			{
				String bt = Integer.toHexString(bs.read());
				if (bt.length() < 2)
				{
					sw.write("0" + bt);
				}
				else
				{
					sw.write(bt);
				}
			}
			String r = sw.toString();
			bs.close();
			sw.close();
			return r;
		}
		catch (Exception e)
		{
			return "";
		}
	}



	public String getOldaccount() {
		return oldaccount;
	}



	public void setOldaccount(String oldaccount) {
		this.oldaccount = oldaccount;
	}
}
