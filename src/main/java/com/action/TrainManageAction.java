package com.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.Emailer;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Organize;
import com.orm.TrainContent;
import com.service.OrganizeService;
import com.service.TrainContentService;

@SuppressWarnings("serial")
public class TrainManageAction extends ActionSupport {


	public TrainContent getTrainContent() {
		return trainContent;
	}
	public List<TrainContent> getTrains() {
		return trains;
	}
	public void setTrains(List<TrainContent> trains) {
		this.trains = trains;
	}
	public void setTrainContent(TrainContent trainContent) {
		this.trainContent = trainContent;
	}
	public TrainContent trainContent;
	public String orgcodes;
	public Set<Organize> getMailedorgs() {
		return mailedorgs;
	}
	public void setMailedorgs(Set<Organize> mailedorgs) {
		this.mailedorgs = mailedorgs;
	}
	public List<TrainContent> trains;
	public Set<Organize> mailedorgs;
	public String choosetrainid;
	
	public String getChoosetrainid() {
		return choosetrainid;
	}
	public void setChoosetrainid(String choosetrainid) {
		this.choosetrainid = choosetrainid;
	}
	public String getOrgcodes() {
		return orgcodes;
	}
	public void setOrgcodes(String orgcodes) {
		this.orgcodes = orgcodes;
	}
	@Autowired
	TrainContentService trainContentService;
	@Autowired
	OrganizeService organizeService;
	@Autowired
	Emailer emailer;
	@Override
    public String execute() throws Exception {
        return ERROR;
    }
	public String ListAll() throws Exception {
        return "ListAll";
    }
	public String Listcontent() throws Exception {
        return "Listcontent";
    }
	public String endstaffup() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest(); 
		TrainContent content=trainContentService.findTrainContentByid(request.getParameter("id"));
		content.setUporend("yes");
        boolean flag=trainContentService.saveOrUpdate(content);
        if (flag) {
            return "Listcontent";
        }
        return ERROR;
    }
	public String contentdel() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest(); 
        boolean flag=trainContentService.deleteTrainContentByid(request.getParameter("id"));
        if (flag) {
            return "Listcontent";
        }
        return ERROR;
    }
    public String contentmodprepare() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest(); 
		setTrainContent(trainContentService.findTrainContentByid(request.getParameter("id")));
        if (trainContent!=null) {
            	return "findSuccess";
        }
        return ERROR;
    }
	public String saveorupdatecontent() throws Exception {
        boolean flag=trainContentService.saveOrUpdate(trainContent);
        if (flag) {
            return "addSuccess";
        }
        return ERROR;
    }
	public String gettrainslist(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		setOrgcodes(request.getParameter("orgcodes"));
		setTrains(trainContentService.listAllTrainContent());
		if(trains!=null){
			return "chooseTrainpage";
		}
		return ERROR;
	}
	public String sendmail(){
		String[] sendorgcodes=getOrgcodes().split(";");
		Set<Organize> orglist=new HashSet<Organize>();
		TrainContent content=trainContentService.findTrainContentByid(choosetrainid);
		for(int i=0;i<sendorgcodes.length;i++){
			Organize org=organizeService.findOrganizeByorgcode(sendorgcodes[i]);
			if(org.getEmail()!=null&&!(org.getEmail().equals(""))){
				emailer.sendmail(org.getEmail(), content.getTittle(), content.getContent(), "text");
				orglist.add(org);
			}
		}
		content.setOrganizes(orglist);
		trainContentService.saveOrUpdate(content);
		return "ListAll";
	}
	public String gethavesendorg(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		String contentid=request.getParameter("contentid");
		Set<Organize> orgset=trainContentService.findTrainContentByid(contentid).getOrganizes();
		setMailedorgs(orgset);
		return "emailedorgs";
	}
}
