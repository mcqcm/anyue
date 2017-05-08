package com.action;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Notice;
import com.orm.SystemRole;
import com.orm.SystemUser;
import com.service.LicenceService;
import com.service.NoticeService;
import com.service.RoleService;
import com.service.UserService;

@SuppressWarnings("serial")
public class LogInAction extends ActionSupport {

    private SystemUser loginuser;
    private String result;
    private String checkcode;
    private List<SystemRole> roleslist;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    LicenceService licenceService;
    
    public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	private String message;


	public SystemUser getLoginuser() {
		return loginuser;
	}



	public void setLoginuser(SystemUser loginuser) {
		this.loginuser = loginuser;
	}



	@Override
    public String execute() throws Exception {
        ActionContext.getContext().getSession().clear();
        Map sessionMap = ActionContext.getContext().getSession(); 
        roleslist = roleService.listAllRole();
        if(loginuser.getAccount()==null||loginuser.getAccount().equals("")){
        	setResult("用户名不能为空!");  
        	return "loginpage";
        }
        if(loginuser.getPassword()==null||loginuser.getPassword().equals("")){
        	setResult("密码不能为空!");  
        	return "loginpage";
        }
//        if(checkcode==null||checkcode.equals("")){
//        	setResult("验证码不能为空!");  
//        	return "loginpage";
//        }
        HttpServletRequest request = ServletActionContext.getRequest();
//        String randomcode = (String) (request.getSession().getAttribute("RANDOMIMAGES"));
//        if(!(randomcode.equals(checkcode))){
//        	setResult("验证码错误!");  
//        	return "loginpage";
//        }
        loginuser.setPassword(getMD5(loginuser.getPassword()));
        
        SystemUser s = userService.haveUser(loginuser);
        if (s != null) {
        	SystemRole userrole=s.getSystemrole();
        	String role=request.getParameter("role");
        	if(role.equals(String.valueOf(userrole.getId()))){
        		loginuser=s;
        		sessionMap.put("loginuser" , s); 
        		if(role.equals("1102")){
	        		boolean flag=licenceService.haveUserFulLicence(s.getOrgcode());
	        		if(!flag)
	        			setMessage("licenceouttime");
        		}
        		return "loginSuccess";
        	}
        }
//        this.addFieldError("error","用户名或密码错误.");  
        setResult("用户名或密码错误！");
        return "loginpage";
    }
	public String modaccount() throws Exception {
		String newpassword=getMD5(loginuser.getPassword());
		SystemUser newuser=userService.findUserByid(String.valueOf(loginuser.getUserid()));
		newuser.setPassword(newpassword);
        boolean flag=userService.saveOrUpdate(newuser);
        if (flag) {
            return "loginSuccess";
        }
        return ERROR;
    }
    public String index() throws Exception {
        ActionContext.getContext().getSession().clear();
        roleslist = roleService.listAllRole();
        if (roleslist != null) {
            return "loginpage";
        }
        return ERROR;
    }
    public String getaccount() throws Exception {
        //修改密码是获得原始账号信息
    	Map sessionMap = ActionContext.getContext().getSession(); 
    	setLoginuser((SystemUser)sessionMap.get("loginuser"));
        if (loginuser != null) {
            return "getloginSuccess";
        }
        return ERROR;
    }
    public String exit() {
        ActionContext.getContext().getSession().clear();
        return "exit";
    }



	public String getResult() {
		return result;
	}



	public void setResult(String result) {
		this.result = result;
	}



	public List<SystemRole> getRoleslist() {
		return roleslist;
	}



	public void setRoleslist(List<SystemRole> roleslist) {
		this.roleslist = roleslist;
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



	public String getCheckcode() {
		return checkcode;
	}



	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
}
