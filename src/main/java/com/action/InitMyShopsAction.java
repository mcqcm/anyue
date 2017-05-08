package com.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.orm.Licence;
import com.orm.Medinfo;
import com.orm.Organize;
import com.service.LicenceService;
import com.service.OrganizeService;

@SuppressWarnings("serial")
public class InitMyShopsAction extends ActionSupport {

	private Organize orgInfo;
	public Organize getOldorgInfo() {
		return oldorgInfo;
	}
	public void setOldorgInfo(Organize oldorgInfo) {
		this.oldorgInfo = oldorgInfo;
	}

	private static Organize oldorgInfo;
    @Autowired
    OrganizeService organizeService;
    private List<Organize> orglists;
    private String parentorgcode;

	private File licence;
    private String licenceFileName;
    private String licenceContentType;
	@Autowired
	LicenceService licenceService;
	private Licence licenceEntity;
	private String orgcode;
	@Autowired
	ProcessEngine processEngine;
	public File getLicence() {
		return licence;
	}
	public void setLicence(File licence) {
		this.licence = licence;
	}
	public String getLicenceFileName() {
		return licenceFileName;
	}
	public void setLicenceFileName(String licenceFileName) {
		this.licenceFileName = licenceFileName;
	}
	public String getLicenceContentType() {
		return licenceContentType;
	}
	public void setLicenceContentType(String licenceContentType) {
		this.licenceContentType = licenceContentType;
	}
	public Licence getLicenceEntity() {
		return licenceEntity;
	}
	public void setLicenceEntity(Licence licenceEntity) {
		this.licenceEntity = licenceEntity;
	}
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	@Override
    public String execute() throws Exception {
		return "ListAll";

    }
	public String addorg() throws Exception {
        //娣诲姞鐢ㄦ埛
//		Date makedate=medicineinfo.getMaketime();
		//System.out.println("get!!");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		orgInfo.setRuntime(format.parse(format.format(orgInfo.getRuntime())));
		if(orgInfo.getRuntime()==null)
			orgInfo.setRuntime(new Date());
		if(oldorgInfo!=null){
			orgInfo.setOrgstaff(oldorgInfo.getOrgstaff());
		}
		orgInfo.setBelongname(organizeService.findOrganizeByorgcode(orgInfo.getBelongcode()).getOrgname());
        boolean flag=organizeService.saveOrUpdate(orgInfo);
        if (flag) {
            return "addSuccess";
        }
        return ERROR;
    }
	public String modorg() throws Exception {
        //娣诲姞鐢ㄦ埛
		HttpServletRequest request = ServletActionContext.getRequest(); 
		//System.out.println(request.getParameter("id"));
		oldorgInfo=organizeService.findOrganizeByorgcode(request.getParameter("id"));
		
        if (oldorgInfo!=null) {
        	setOrgInfo(oldorgInfo);
            return "modpage";
        }
        return ERROR;
    }
	public String delorg() throws Exception {
        //娣诲姞鐢ㄦ埛
		HttpServletRequest request = ServletActionContext.getRequest(); 
		boolean flag=organizeService.deleteOrganizeByorgcode(request.getParameter("id"));
        if (flag) {
            return "ListAll";
        }
        return ERROR;
    }
	public String getOrgList(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		request.getSession().removeAttribute("orglist");
		request.getSession().setAttribute("orglist",organizeService.listAllOrganize());
		
		return "getorgsuccess";
	}
//	public String getChildren(){
//		HttpServletRequest request = ServletActionContext.getRequest(); 
//		parentorgcode=request.getParameter("orgcode");
//		return "getchildren";
//	}
	public String uplicence() throws IOException{
		System.out.println("文件名称："+this.licence.getName());
		System.out.println("文件路径："+this.licence);
		System.out.println("文件大小："+this.licence.length());
		System.out.println("licenceFileName："+licenceFileName);
		System.out.println("licenceContentType："+licenceContentType);
		Organize org=organizeService.findOrganizeByorgcode(orgcode);
		byte[] buffer = null;
		FileInputStream fis = new FileInputStream(licence);
		ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
        byte[] b = new byte[1000];  
        int n;  
        while ((n = fis.read(b)) != -1) {  
            bos.write(b, 0, n);  
        }  
        fis.close();  
        bos.close();  
        buffer = bos.toByteArray();
        TaskService taskService = processEngine
				.getTaskService();
        if(buffer!=null){
    		licenceEntity.setLicence(buffer);
    		licenceEntity.setOrgcode(orgcode);
    		licenceEntity.setOrgname(org.getOrgname());
    		licenceEntity.setFiletype(licenceContentType);
    		licenceEntity.setLicencestatus("yes");
    		licenceService.saveOrUpdate(licenceEntity);
    		return "ListAll";
        }else{
        	return null;
        }
	}
	public List<Organize> getOrglists() {
		return orglists;
	}

	public void setOrglists(List<Organize> orglists) {
		this.orglists = orglists;
	}

	public String getParentorgcode() {
		return parentorgcode;
	}

	public void setParentorgcode(String parentorgcode) {
		this.parentorgcode = parentorgcode;
	}

	public Organize getOrgInfo() {
		return orgInfo;
	}

	public void setOrgInfo(Organize orgInfo) {
		this.orgInfo = orgInfo;
	}
	
}
