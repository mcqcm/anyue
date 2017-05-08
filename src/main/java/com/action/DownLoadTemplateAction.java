package com.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
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
import com.service.RoleService;
import com.service.UserService;

@SuppressWarnings("serial")
public class DownLoadTemplateAction extends ActionSupport {

	public int number;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String fileName;
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
    public String downloadpage() throws Exception {
        return "downloadpage";
    }
	public InputStream getDownloadFile() throws UnsupportedEncodingException{
		{  
	        if(1 == number)  
	        {  
	           this.fileName = "applyguide.docx";  
	           //获取资源路径  
	           return ServletActionContext.getServletContext().getResourceAsStream("applymaterial/applyguide.docx") ;  
	        }  
	          
	        else if(2 == number)  
	        {  
	            this.fileName = "template.rar" ;  
	            //解解乱码  
	            this.fileName = new String(this.fileName.getBytes("GBK"),"ISO-8859-1");  
	            return ServletActionContext.getServletContext().getResourceAsStream("applymaterial/template.rar") ;  
	        }  
	        else  
	           return null ;  
	    } 
	}
}
