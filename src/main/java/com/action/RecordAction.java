package com.action;



import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.orm.Licence;
import com.orm.Organize;
import com.orm.Record;
import com.orm.SystemUser;
import com.service.LicenceService;
import com.service.OrganizeService;
import com.service.RecordService;

@SuppressWarnings("serial")
public class RecordAction extends ActionSupport{
	private Record record;
	private Organize orgInfo;
	private Licence licence;
    @Autowired
    RecordService recordService;
    @Autowired
    OrganizeService orgService;
    @Autowired
    LicenceService licenceService;
    //private List<Record> recordlists;
    
    public Licence getLicence() {
		return licence;
	}

	public void setLicence(Licence licence) {
		this.licence = licence;
	}

	@Override
    public String execute() throws Exception {
		return null;

    }

	public String addrecord() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest(); 
		//Record record=recordService.findRecordBykey(request.getParameter("id"));
		System.out.println("id="+request.getParameter("id"));
		getRecord().setRecorgcode(request.getParameter("id"));
        if (record!=null) {
        	System.out.println("id="+request.getParameter("id"));
        	setRecord(record);
        }
        boolean flag=recordService.saveOrUpdate(record);
        if (flag) {
            return "addSuccess";
        }
        return ERROR;
    }
	public String modrecord() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest(); 
		Record record=recordService.findRecordBykey(request.getParameter("id"));
		System.out.println("id="+request.getParameter("id"));
        if (record!=null) {
        	System.out.println("id="+request.getParameter("id"));
        	setRecord(record);
            return "modpage";
        }
        return ERROR;
    }
	public String seerecord() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest(); 
		//Record record=recordService.findRecordBykey(request.getParameter("id"));
		String orgcode=request.getParameter("id");
		System.out.println(orgcode);
		Record record=recordService.findRecordBykey(orgcode);
		System.out.println(record);
        if (record!=null) {
        	//System.out.println("id="+request.getParameter("id"));
        	setRecord(record);
        	Organize org=orgService.findOrganizeByorgcode(orgcode);
			setOrgInfo(org);
			licence=licenceService.findlastLicenceByOrgcode(orgcode);
			if(licence!=null&&licence.getLicence()!=null){
				request.getSession().removeAttribute("imagelicence");
				request.getSession().setAttribute("imagelicence", licence.getLicence());
			}
        	return "seeRecord";
        }else {
        	return "noRecord";
		}
    }
	
	public String delrecord() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest(); 
		boolean flag=recordService.deleteRecordBykey(request.getParameter("id"));
        if (flag) {
            return "ListAll";
        }
        return ERROR;
    }
	
	public String getRecordList(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		//request.getSession().setAttribute("orglist",recordService.listAllRecord());
		SystemUser user=(SystemUser) request.getSession().getAttribute("loginuser");
		String orgcode=user.getOrgcode();
		System.out.println(orgcode);
		if(user.getUserrolename().equals("²ÍÒûµ¥Î»")){
			Record record=recordService.findRecordBykey(orgcode);
			setRecord(record);
			System.out.println(record);
			Organize org=orgService.findOrganizeByorgcode(orgcode);
			setOrgInfo(org);
			licence=licenceService.findlastLicenceByOrgcode(orgcode);
			if(licence!=null&&licence.getLicence()!=null){
				request.getSession().removeAttribute("imagelicence");
				request.getSession().setAttribute("imagelicence", licence.getLicence());
			}
			return "getrecordsuccess";
		}
		return "isnotshop";
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public RecordService getRecordService() {
		return recordService;
	}

	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
	}

	public Organize getOrgInfo() {
		return orgInfo;
	}

	public void setOrgInfo(Organize orgInfo) {
		this.orgInfo = orgInfo;
	}

	public OrganizeService getOrgService() {
		return orgService;
	}

	public void setOrgService(OrganizeService orgService) {
		this.orgService = orgService;
	}

	
	
}
