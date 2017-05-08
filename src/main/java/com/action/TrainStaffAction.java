package com.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.orm.OrgStaff;
import com.orm.SystemUser;
import com.orm.TrainContent;
import com.orm.TrainStaff;
import com.service.OrgStaffService;
import com.service.OrganizeService;
import com.service.TrainContentService;
import com.service.TrainStaffService;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class TrainStaffAction extends ActionSupport {
	TrainStaff trainStaff;
	 @Autowired
	 TrainContentService trainService;
	 @Autowired
	 TrainStaffService trainStaffService;
	 @Autowired
	 OrgStaffService staffService;

	 List<TrainContent> trainList;
	 String trainID;
	 String staffcodes;
	 String contentid;
	 
	public String add() throws Exception{
		String[] ids=staffcodes.split(";");
		for(int i=0;i<ids.length;i++){
			TrainStaff newstaff=new TrainStaff();
			OrgStaff staff=staffService.findOrgStaffBystaffcode(ids[i]);
			//System.out.println(staff.getIdentitycode());
			newstaff.setIdentitycode(staff.getIdentitycode());
			newstaff.setOrgcode(staff.getOrganize().getOrgcode());
			newstaff.setOrgname(staff.getOrganize().getOrgname());
			newstaff.setSex(staff.getSex());
			newstaff.setStaffcode(ids[i]);
			newstaff.setStaffname(staff.getStaffname());
			newstaff.setTelephone(staff.getTelephone());
			newstaff.setTrainContent(trainService.findTrainContentByid(trainID));
			trainStaffService.saveOrUpdate(newstaff);
		}
        if (trainStaff!=null) {
            return "addSuccess";
        }
        return "addSuccess";
	}
	
	public String getstaffs() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		staffcodes=request.getParameter("staffcodes");
		//String[] ids=request.getParameter("staffcodes").split(";");
		trainList=trainService.findTrainContentByflag("no");
		System.out.println(trainList.size());
        return "chooseTrain";
    }
	
	public String seeAttendee() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		contentid=request.getParameter("contentid");
		request.setAttribute("contentid", contentid);
		return "attendeelist";
	}
	

	@Override
    public String execute() throws Exception {
        return ERROR;
    }
	public String ListAll() throws Exception {
        return "ListAll";
    }
	public String List() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest(); 
		String orgcode=((SystemUser)request.getSession().getAttribute("loginuser")).getOrgcode();
		request.setAttribute("orgcode",orgcode);
        return "List";
    }
	public List<TrainContent> getTrainList() {
		return trainList;
	}

	public void setTrainList(List<TrainContent> trainList) {
		this.trainList = trainList;
	}

	public TrainStaff getTrainStaff() {
		return trainStaff;
	}

	public void setTrainStaff(TrainStaff trainStaff) {
		this.trainStaff = trainStaff;
	}

	public String getTrainID() {
		return trainID;
	}

	public void setTrainID(String trainID) {
		this.trainID = trainID;
	}

	public String getStaffcodes() {
		return staffcodes;
	}

	public void setStaffcodes(String staffcodes) {
		this.staffcodes = staffcodes;
	}

	public String getContentid() {
		return contentid;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	}
	
	
	
}
