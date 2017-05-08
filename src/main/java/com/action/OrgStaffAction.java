package com.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.orm.OrgStaff;
import com.service.OrgStaffService;
import com.service.OrganizeService;

@SuppressWarnings("serial")
public class OrgStaffAction extends ActionSupport {

    @Autowired
    OrgStaffService orgstaffService;
    @Autowired
    OrganizeService organizeService;
    private OrgStaff staff;
    private File healthcard;
    private String healthcardFileName;
    private String healthcardContentType;
    private String orgcode;
    
    
    
	@Override
    public String execute() throws Exception {
		return "ListAll";

    }

	public String addstaff() throws IOException{
		if(healthcard!=null){
			System.out.println("文件名称："+this.healthcard.getName());
			System.out.println("文件路径："+this.healthcard);
			System.out.println("文件大小："+this.healthcard.length());
			System.out.println("员工编号："+this.staff.getStaffcode());
			System.out.println("healthcardFileName："+healthcardFileName);
			System.out.println("healthcardContentType："+healthcardContentType);
			byte[] buffer = null;
			FileInputStream fis = new FileInputStream(healthcard);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
	        byte[] b = new byte[1000];  
	        int n;  
	        while ((n = fis.read(b)) != -1) {  
	            bos.write(b, 0, n);  
	        }  
	        fis.close();  
	        bos.close();  
	        buffer = bos.toByteArray();
	        if(buffer!=null)
	        	staff.setHealthcard(buffer);
		}
        staff.setOrganize(organizeService.findOrganizeByorgcode(orgcode));
        boolean flag=orgstaffService.saveOrUpdate(staff);
        if(flag)
        	return "addSuccess";
        else
        	return ERROR;
	}
	public String modstaff() throws Exception {
        //娣诲姞鐢ㄦ埛
		HttpServletRequest request = ServletActionContext.getRequest(); 
		//System.out.println(request.getParameter("id"));
		OrgStaff orgstaff=orgstaffService.findOrgStaffBystaffcode(request.getParameter("id"));
		
        if (orgstaff!=null) {
        	request.getSession().removeAttribute("healthimage");
        	request.getSession().setAttribute("healthimage", orgstaff.getHealthcard());
        	setStaff(orgstaff);
            return "modpage";
        }
        return ERROR;
    }
	public String savemodstaff() throws Exception {
        //娣诲姞鐢ㄦ埛
		HttpServletRequest request = ServletActionContext.getRequest(); 
		//System.out.println(request.getParameter("id"));
		OrgStaff orgstaff=orgstaffService.findOrgStaffBystaffcode(staff.getStaffcode());
		if(healthcard==null){
			staff.setHealthcard(orgstaff.getHealthcard());
		}else{
			byte[] buffer = null;
			FileInputStream fis = new FileInputStream(healthcard);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
	        byte[] b = new byte[1000];  
	        int n;  
	        while ((n = fis.read(b)) != -1) {  
	            bos.write(b, 0, n);  
	        }  
	        fis.close();  
	        bos.close();  
	        buffer = bos.toByteArray();
	        if(buffer!=null)
	        	staff.setHealthcard(buffer);
		}
		staff.setOrganize(orgstaff.getOrganize());
		boolean flag=orgstaffService.saveOrUpdate(staff);
        if(flag)
        	return "addSuccess";
        else
        	return ERROR;
    }

	public String delstaff() throws Exception {
        //娣诲姞鐢ㄦ埛
		HttpServletRequest request = ServletActionContext.getRequest(); 
		boolean flag=orgstaffService.deleteOrgStaffBystaffcode(request.getParameter("id"));
        if (flag) {
            return "ListAll";
        }
        return ERROR;
    }
	public OrgStaff getStaff() {
		return staff;
	}



	public void setStaff(OrgStaff staff) {
		this.staff = staff;
	}



	public File getHealthcard() {
		return healthcard;
	}



	public void setHealthcard(File healthcard) {
		this.healthcard = healthcard;
	}

	public String getHealthcardFileName() {
		return healthcardFileName;
	}

	public void setHealthcardFileName(String healthcardFileName) {
		this.healthcardFileName = healthcardFileName;
	}

	public String getHealthcardContentType() {
		return healthcardContentType;
	}

	public void setHealthcardContentType(String healthcardContentType) {
		this.healthcardContentType = healthcardContentType;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	
}
