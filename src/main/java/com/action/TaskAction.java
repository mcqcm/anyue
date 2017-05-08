package com.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.Emailer;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Licence;
import com.orm.Organize;
import com.orm.SystemUser;
import com.service.LicenceService;
import com.service.OrganizeService;
import com.service.UserService;

@SuppressWarnings("serial")
public class TaskAction extends ActionSupport {

	@Autowired
	ProcessEngine processEngine;
	@Autowired
	UserService userService;
	@Autowired
	LicenceService licenceService;
	@Autowired
	Emailer emailer;
	private Licence licenceEntity;
	private String applyername;
	private String applyerorg;
	private String applytime;
	private File licence;
    private String licenceFileName;
    private String licenceContentType;
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
	private String applyreason;
	private String taskid;
	private String formkey;
	private String suggestion;
	public String getApplyername() {
		return applyername;
	}
	public void setApplyername(String applyername) {
		this.applyername = applyername;
	}
	public String getApplyerorg() {
		return applyerorg;
	}
	public void setApplyerorg(String applyerorg) {
		this.applyerorg = applyerorg;
	}
	public String getApplytime() {
		return applytime;
	}
	public void setApplytime(String applytime) {
		this.applytime = applytime;
	}
	public String getApplyreason() {
		return applyreason;
	}
	public void setApplyreason(String applyreason) {
		this.applyreason = applyreason;
	}
	List<Task> tasklist;
	public static String fileName;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
    public String execute() throws Exception {  
		return SUCCESS;
    }
	public InputStream getDownloadFile() throws UnsupportedEncodingException{
        //解解乱码  
        //this.fileName = new String(this.fileName.getBytes("GBK"),"ISO-8859-1");  
        return ServletActionContext.getServletContext().getResourceAsStream("applymaterial/material/"+this.fileName);
	} 
	public String gettasklist(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		String userid=String.valueOf(((SystemUser)request.getSession().getAttribute("loginuser")).getUserid());
		TaskService taskService = processEngine
			.getTaskService();
		tasklist=taskService.createTaskQuery().taskAssignee(userid).orderByTaskCreateTime().desc().list();
		return "getsuccess";
	}
	public String approve(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		String taskid=request.getParameter("taskid");
		setTaskid(taskid);
		TaskService taskService = processEngine
				.getTaskService();
		applyername=(String) taskService.getVariable(taskid, "applyername");
		applyerorg=(String) taskService.getVariable(taskid, "applyerorg");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		applytime= sdf.format((Date)taskService.getVariable(taskid, "applytime"));
		applyreason=(String) taskService.getVariable(taskid, "applyreason");
		fileName=(String) taskService.getVariable(taskid, "filename");
		return "approve";
	}
	public String taskapply(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		String taskid=request.getParameter("taskid");
		setTaskid(taskid);
		TaskService taskService = processEngine
				.getTaskService();
		applyername=(String) taskService.getVariable(taskid, "applyername");
		applyerorg=(String) taskService.getVariable(taskid, "applyerorg");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		applytime= sdf.format((Date)taskService.getVariable(taskid, "applytime"));
		setSuggestion((String) taskService.getVariable(taskid, "suggestion"));
		return "applypage";
	}
	public String taskdo(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		String taskid=request.getParameter("taskid");
		setTaskid(taskid);
		FormService formService=processEngine.getFormService();
		TaskFormData formData = formService.getTaskFormData(taskid);
		formkey = formData.getFormKey();
		return "approveaction";
	}
	public String uplicence() throws IOException{
		System.out.println("文件名称："+this.licence.getName());
		System.out.println("文件路径："+this.licence);
		System.out.println("文件大小："+this.licence.length());
		System.out.println("licenceFileName："+licenceFileName);
		System.out.println("licenceContentType："+licenceContentType);
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
		String applyerid=(String) taskService.getVariable(taskid, "applier");
		SystemUser applyer=userService.findUserByid(applyerid);
        if(buffer!=null){
    		licenceEntity.setLicence(buffer);
    		licenceEntity.setFiletype(licenceContentType);
    		licenceEntity.setOrgcode(applyer.getOrgcode());
    		licenceEntity.setOrgname(applyer.getOrgname());
    		licenceEntity.setLicencestatus("yes");
    		licenceService.saveOrUpdate(licenceEntity);
        }else{
        	return null;
        }
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("back", false);
			taskService.complete(taskid,map);
			String email=applyer.getEmail();
			if(email!=null&&!(email.equals("")));
					emailer.sendmail(email,"许可证办理成功","您的许可证已办理成功！请登录系统查看。","text");
		return "taskdone";
	}
	public String gavesuggestion(){
		TaskService taskService = processEngine
				.getTaskService();
		Map<String,Object> map = new HashMap<String,Object>();
		String applyerid=(String) taskService.getVariable(taskid, "applier");
		SystemUser applyer=userService.findUserByid(applyerid);
		map.put("back", true);
		map.put("suggestion", suggestion);
		taskService.complete(taskid,map);
		String email=applyer.getEmail();
		if(email!=null&&!(email.equals("")));
				emailer.sendmail(email,"许可证办理失败","您的许可证已办理失败！请登录系统查看。","text");
		return "taskdone";
	}
	public List<Task> getTasklist() {
		return tasklist;
	}
	public void setTasklist(List<Task> tasklist) {
		this.tasklist = tasklist;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getFormkey() {
		return formkey;
	}
	public void setFormkey(String formkey) {
		this.formkey = formkey;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public Licence getLicenceEntity() {
		return licenceEntity;
	}
	public void setLicenceEntity(Licence licenceEntity) {
		this.licenceEntity = licenceEntity;
	}
}
