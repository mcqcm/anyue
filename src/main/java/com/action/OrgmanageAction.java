package com.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.orm.Medinfo;
import com.orm.Organize;
import com.service.OrganizeService;

@SuppressWarnings("serial")
public class OrgmanageAction extends ActionSupport {

	private Organize orgInfo;
	private static Organize oldorgInfo;
    @Autowired
    OrganizeService organizeService;
    private List<Organize> orglists;
    private String parentorgcode;

	@Override
    public String execute() throws Exception {
		return null;

    }

	public String addorg() throws Exception {
        //添加用户
//		Date makedate=medicineinfo.getMaketime();
		//System.out.println("get!!");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		orgInfo.setAddtime(format.parse(format.format(orgInfo.getAddtime())));
		if(orgInfo.getAddtime()==null)
			orgInfo.setAddtime(new Date());
		if(oldorgInfo!=null){
			orgInfo.setOrgstaff(oldorgInfo.getOrgstaff());
		}
        boolean flag=organizeService.saveOrUpdate(orgInfo);
        if (flag) {
            return "addSuccess";
        }
        return ERROR;
    }
	public String modorg() throws Exception {
        //添加用户
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
        //添加用户
		System.out.println("get!!");
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

	public Organize getOldorgInfo() {
		return oldorgInfo;
	}

	public void setOldorgInfo(Organize oldorgInfo) {
		this.oldorgInfo = oldorgInfo;
	}
	
}
