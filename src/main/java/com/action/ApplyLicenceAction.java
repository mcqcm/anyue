package com.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Licence;
import com.orm.Organize;
import com.orm.Supplier;
import com.orm.SystemRole;
import com.orm.SystemUser;
import com.service.LicenceService;
import com.service.OrganizeService;
import com.service.RoleService;
import com.service.UserService;

@SuppressWarnings("serial")
public class ApplyLicenceAction extends ActionSupport {

	private SystemUser applyer;
	private Date applytime;
	private String applyreason;
	private String taskid;
    public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	private File applyzip;
    private String applyzipFileName;
    private String applyzipContentType;
    private String id;
	public String fileName;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Autowired
	ProcessEngine processEngine;
	@Autowired
	UserService userService;
	@Autowired
	OrganizeService organizeService;
	@Autowired
	LicenceService licenceService;
	@Override
    public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest(); 
		applyer=(SystemUser)request.getSession().getAttribute("loginuser");
        return "applypage";
    }
	public SystemUser getApplyer() {
		return applyer;
	}
	public void setApplyer(SystemUser applyer) {
		this.applyer = applyer;
	}
	public String submitapply() throws IOException{
		setApplyer(userService.findUserByid(String.valueOf(applyer.getUserid())));
		Organize organize=organizeService.findOrganizeByorgcode(applyer.getOrgcode());
		List<SystemUser> users=licenceService.findCheckUsers(organize.getBelongcode());
		if(users==null||users.size()==0)
			return "applyerror";
		else{
			RuntimeService runtimeService = processEngine
					.getRuntimeService();
			IdentityService identityService=processEngine.getIdentityService();
				Map<String,Object> map = new HashMap<String,Object>();
			map.put("applier",String.valueOf(applyer.getUserid()));
			List<String> checkgroup=new ArrayList<String>();
			for(int i=0;i<users.size();i++){
				checkgroup.add(String.valueOf(users.get(i).getUserid()));
			}
			map.put("checkgroup", checkgroup);
			identityService.setAuthenticatedUserId(String.valueOf(applyer.getUserid()));
			ProcessInstance pi=runtimeService.startProcessInstanceByKey("apply",map);
			if(pi!=null){
				System.out.println("文件名称："+this.applyzip.getName());
				System.out.println("文件路径："+this.applyzip);
				System.out.println("文件大小："+this.applyzip.length());
				System.out.println("healthcardFileName："+applyzipFileName);
				System.out.println("healthcardContentType："+applyzipContentType);
				String fileName=UUID.randomUUID()+applyzipFileName;
				String filepath=ServletActionContext.getServletContext().getRealPath("/")+"applymaterial/material/"+fileName;
				File outFile=new File(filepath);
				OutputStream output=new FileOutputStream(outFile);
				InputStream input = new FileInputStream(applyzip);
				byte data[] =new byte[1024];
				int temp=0;
				while((temp=input.read(data))!=-1){
					output.write(data,0,temp);
				}
				output.close();
				input.close();
				 TaskService taskService = processEngine
							.getTaskService();
				 List<Task> groupTaskList=taskService.createTaskQuery().taskAssignee(String.valueOf(applyer.getUserid())).orderByTaskCreateTime().desc().list();
					Task task=groupTaskList.get(0);
					Map<String,Object> map2 = new HashMap<String,Object>();
					map2.put("applyername", applyer.getUsername());
					map2.put("applyerorg", applyer.getOrgname());
					map2.put("applytime",applytime);
					map2.put("applyreason", applyreason);
					map2.put("filepath", filepath);
					map2.put("filename", fileName);
					taskService.complete(task.getId(),map2);
					return "submitsuccess";
			}
		}
		return null;
	}
	public String submitapply2() throws IOException{

		System.out.println("文件名称："+this.applyzip.getName());
		System.out.println("文件路径："+this.applyzip);
		System.out.println("文件大小："+this.applyzip.length());
		System.out.println("applyzipFileName："+applyzipFileName);
		System.out.println("applyzipContentType："+applyzipContentType);
		String fileName=UUID.randomUUID()+applyzipFileName;
		String filepath=ServletActionContext.getServletContext().getRealPath("/")+"applymaterial/material/"+fileName;
		File outFile=new File(filepath);
		OutputStream output=new FileOutputStream(outFile);
		InputStream input = new FileInputStream(applyzip);
		byte data[] =new byte[1024];
		int temp=0;
		while((temp=input.read(data))!=-1){
			output.write(data,0,temp);
		}
		output.close();
		input.close();
		TaskService taskService = processEngine
				.getTaskService();
			Map<String,Object> map2 = new HashMap<String,Object>();
			map2.put("filepath", filepath);
			map2.put("filename", fileName);
			taskService.complete(taskid,map2);
			return "submitsuccess";
}
	public String seelicence(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		id=request.getParameter("id");
		System.out.println("seelicence:::"+id);
		Licence licence=licenceService.findLicenceByid(id);
		if(licence!=null){
			request.getSession().removeAttribute("licenceimage");
			request.getSession().setAttribute("licenceimage", licence.getLicence());
			return "seelicence";
		}
		return ERROR;
	}
	public String downlicence(){
		return SUCCESS;
	}
	public InputStream getDownloadFile() throws UnsupportedEncodingException{
		System.out.println("iddddd::::"+id);
		Licence licence=licenceService.findLicenceByid(id);
		this.fileName=(UUID.randomUUID()+licence.getFiletype()).replaceAll("/", ".");
		return new ByteArrayInputStream(licence.getLicence());
	}
	public String getmylicence(){
		return "licencelist";
	}
	public File getApplyzip() {
		return applyzip;
	}
	public void setApplyzip(File applyzip) {
		this.applyzip = applyzip;
	}
	public String getApplyzipFileName() {
		return applyzipFileName;
	}
	public void setApplyzipFileName(String applyzipFileName) {
		this.applyzipFileName = applyzipFileName;
	}
	public String getApplyzipContentType() {
		return applyzipContentType;
	}
	public void setApplyzipContentType(String applyzipContentType) {
		this.applyzipContentType = applyzipContentType;
	}
	public String getApplyreason() {
		return applyreason;
	}
	public void setApplyreason(String applyreason) {
		this.applyreason = applyreason;
	}
	public Date getApplytime() {
		return applytime;
	}
	public void setApplytime(Date applytime) {
		this.applytime = applytime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
