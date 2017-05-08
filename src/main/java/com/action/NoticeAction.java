package com.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.CommonMethod;
import com.common.Emailer;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Notice;
import com.orm.SystemUser;
import com.service.EvalResultService;
import com.service.NoticeService;
import com.service.OrganizeService;
import com.service.UserService;


@SuppressWarnings("serial")
public class NoticeAction extends ActionSupport{
	private Notice notice;
	private String content;
	@Autowired
    OrganizeService orgservice;
    @Autowired
    UserService userService;
    @Autowired
    NoticeService noticeService;
    @Autowired
    EvalResultService resultService;
    @Autowired
	Emailer emailer;
    
    public List<Notice> noticelist;
    
    public String isEmail;
    
    private File upload;
	private String uploadContentType;
	private String uploadFileName;

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

    
    
    public String addNotice() throws Exception {
    	System.out.println("isEmail="+isEmail);
    	HttpServletRequest request = ServletActionContext.getRequest(); 
		SystemUser user=(SystemUser)request.getSession().getAttribute("loginuser");
		notice.setPublisherid(user.getUsercode());
		notice.setPublishername(user.getUsername());
		notice.setText(CommonMethod.removePathFromHtml(notice.getText()));
    	boolean flag=noticeService.saveOrUpdate(notice);
        if (flag) {
        	if(isEmail==null){
        		return "addSuccess";
        	}else if(isEmail.equals("all")){
        		List<SystemUser> userList=userService.listAlluser();
            	List<String> emaillist=CommonMethod.getAllUserEmailAddress(userList);
            	for(String e:emaillist){
            		emailer.sendmail(e,notice.getTittle(),notice.getText(),"html");
            	}
        	}else if(isEmail.equals("shop")){
        		List<SystemUser> userList=userService.listAlluser();
            	List<String> emaillist=CommonMethod.getAllShopEmailAddress(userList);
            	for(String e:emaillist){
            		emailer.sendmail(e,notice.getTittle(),notice.getText(),"html");
            	}
        	}else if(isEmail.equals("manager")){
        		List<SystemUser> userList=userService.listAlluser();
            	List<String> emaillist=CommonMethod.getAllManagerEmailAddress(userList);
            	for(String e:emaillist){
            		emailer.sendmail(e,notice.getTittle(),notice.getText(),"html");
            	}
			}        	
            return "addSuccess";
        }
		return ERROR;
    }
    public String evaluNotice() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
		SystemUser user=(SystemUser)request.getSession().getAttribute("loginuser");
		notice.setPublisherid(user.getUsercode());
		notice.setPublishername(user.getUsername());
		notice.setPublishtime(new Date());
		String noticeText=CommonMethod.getEvaluNoticeText(resultService.listAllEvalResultThisYear(CommonMethod.getNowSeason()));
    	notice.setText(noticeText);
		boolean flag=noticeService.saveOrUpdate(notice);
        if (flag) {
        	List<SystemUser> userList=userService.listAlluser();
        	List<String> emaillist=CommonMethod.getAllShopEmailAddress(userList);
        	for(String e:emaillist){
        		emailer.sendmail(e,CommonMethod.getNowSeason()+"���۽��",noticeText,"html");
        	}
            return "List";
        }
		return ERROR;
    }
    //seeDetail
    public String seeDetail() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
		notice=noticeService.findNoticeByid(request.getParameter("id"));
        if (notice!=null) {
            return "seeSuccess";
        }
		return ERROR;
    }
    public String mod() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
		notice=noticeService.findNoticeByid(request.getParameter("id"));
        if (notice!=null) {
            return "getMod";
        }
		return ERROR;
    }
    
    public String del() throws Exception {
    	HttpServletRequest request = ServletActionContext.getRequest(); 
		boolean flag=noticeService.deleteNoticeByid(request.getParameter("id"));
        if (flag) {
            return "ListAll";
        }
        return ERROR;
    }
    
    public String ListAll() throws Exception {
        return "ListAll";
    }
    
    public String getAllNotice() throws Exception {
    	noticelist=noticeService.listAllNotice();
    	System.out.println("this here!");
        return "getallnotice";
    }
    
    public String get() throws Exception {		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		// CKEditor�ύ�ĺ���Ҫ��һ������
		String callback = ServletActionContext.getRequest().getParameter("CKEditorFuncNum");

		String expandedName = ""; // �ļ���չ��
		if (uploadContentType.equals("image/pjpeg") || uploadContentType.equals("image/jpeg")) {
			// IE6�ϴ�jpgͼƬ��headimageContentType��image/pjpeg����IE9�Լ�����ϴ���jpgͼƬ��image/jpeg
			expandedName = ".jpg";
		} else if (uploadContentType.equals("image/png") || uploadContentType.equals("image/x-png")) {
			// IE6�ϴ���pngͼƬ��headimageContentType��"image/x-png"
			expandedName = ".png";
		} else if (uploadContentType.equals("image/gif")) {
			expandedName = ".gif";
		} else if (uploadContentType.equals("image/bmp")) {
			expandedName = ".bmp";
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
					+ ",'','�ļ���ʽ����ȷ������Ϊ.jpg/.gif/.bmp/.png�ļ���');");
			out.println("</script>");
			return null;
		}

		if (upload.length() > 600 * 1024) {
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
					+ ",''," + "'�ļ���С���ô���600k');");
			out.println("</script>");
			return null;
		}

		InputStream is = new FileInputStream(upload);
		//String uploadPath = ServletActionContext.getServletContext().getRealPath("/");
		String uploadPath = ServletActionContext.getServletContext().getRealPath("/img/postImg");
//		HttpServletRequest request = ServletActionContext.getRequest(); 
//		String uploadPath =request.getContextPath(); 
//		uploadPath+="/img/postImg";
		System.out.println(uploadPath);
		String fileName = java.util.UUID.randomUUID().toString(); // ����UUID�ķ�ʽ������֤�����ظ�
		fileName += expandedName;
		File toFile = new File(uploadPath, fileName);
		OutputStream os = new FileOutputStream(toFile);
		
		// �ļ����Ƶ�ָ��λ��
		byte[] buffer = new byte[1024];
		int length = 0;
		while ((length = is.read(buffer)) > 0) {
			os.write(buffer, 0, length);
		}
		is.close();
		os.close();

		// ���ء�ͼ��ѡ�����ʾͼƬ
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
				+ ",'" + "/anyue/img/postImg/"+ fileName + "','')"); // ���·��������ʾͼƬ
		out.println("</script>");
		return null;
	}

public String getfile() throws Exception {	
	HttpServletResponse response = ServletActionContext.getResponse();
	response.setCharacterEncoding("utf-8");
	PrintWriter out = response.getWriter();

	// CKEditor�ύ�ĺ���Ҫ��һ������
	String callback = ServletActionContext.getRequest().getParameter("CKEditorFuncNum");
	System.out.println(upload.getAbsolutePath());
	System.out.println(uploadContentType);
	String expandedName = "";
	if (uploadContentType.equals("application/msword")) {
		expandedName = ".doc";
	} else if (uploadContentType.equals("application/vnd.ms-excel")) {
		expandedName = ".xls";
	} else if (uploadContentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
		expandedName = ".xlsx";
	} else if (uploadContentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
		expandedName = ".docx";
	} else if (uploadContentType.equals("application/octet-stream")) {
		expandedName = ".zip";
	} else if (uploadContentType.equals("text/plain")) {
		expandedName = ".txt";
	} else if (uploadContentType.equals("application/pdf")) {
		expandedName = ".pdf";
	}else {
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
				+ ",'','��֧�ֵĸ�����ʽ');");
		out.println("</script>");
		return null;
	}
	upload.getAbsolutePath().substring(upload.getAbsolutePath().lastIndexOf(".")); // �ļ���չ��
	System.out.println(expandedName);

	if (upload.length() > 50 * 1024*1024) {
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
				+ ",''," + "'�ļ���С���ô���50M');");
		out.println("</script>");
		return null;
	}

	InputStream is = new FileInputStream(upload);
	//String uploadPath = ServletActionContext.getServletContext().getRealPath("/");
	String uploadPath = ServletActionContext.getServletContext().getRealPath("/img/postImg");
//	HttpServletRequest request = ServletActionContext.getRequest(); 
//	String uploadPath =request.getContextPath(); 
//	uploadPath+="/img/postImg";
	System.out.println(uploadPath);
	//String fileName = java.util.UUID.randomUUID().toString(); // ����UUID�ķ�ʽ������֤�����ظ�
	String fileName=uploadFileName;
	//fileName += expandedName;
	File toFile = new File(uploadPath, fileName);
	OutputStream os = new FileOutputStream(toFile);
	
	// �ļ����Ƶ�ָ��λ��
	byte[] buffer = new byte[1024];
	int length = 0;
	while ((length = is.read(buffer)) > 0) {
		os.write(buffer, 0, length);
	}
	is.close();
	os.close();

	// ���ء�ͼ��ѡ�����ʾͼƬ
	System.out.println(fileName);
	out.println("<script type=\"text/javascript\">");
	out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
			+ ",'" + "/anyue/img/postImg/"+ fileName + "','')"); // ���·��������ʾͼƬ
	out.println("</script>");
	return null;
}



	public Notice getNotice() {
		return notice;
	}


	public void setNotice(Notice notice) {
		this.notice = notice;
	}
    
    

}
