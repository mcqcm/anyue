package com.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orm.Licence;
import com.orm.Organize;
import com.orm.SystemUser;
import com.service.LicenceService;
import com.service.OrganizeService;
import com.service.UserService;
@Service
public class MyListener implements ServletContextListener{
	Timer timer;

	@Autowired
	LicenceService licenceService;
	@Autowired
	OrganizeService organizeService;
	@Autowired
	Emailer emailer;
	@Autowired
	UserService userService;
	public void contextDestroyed(ServletContextEvent arg0) {
		 timer.cancel();
		 System.out.println("��ʱ��������");
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		  timer = new java.util.Timer(true);
	      SampleTask sampleTask =   new SampleTask(arg0.getServletContext());
	      System.out.println("��ʱ��������");
	      //timer.schedule(sampleTask, 0, 6 * 1000);
	      Date time=new Date();
	      time.setHours(23);
	      time.setMinutes(59);
	      time.setSeconds(59);
	      timer.schedule(sampleTask, time, 24*60*60*1000);
	      System.out.println("�Ѿ����������ȱ�");
		
	}

	public void doWork() {
		Calendar c = Calendar.getInstance();
			 String year = "" + c.get(c.YEAR);
		String month = "" + (c.get(c.MONTH) + 1);
		String day = "" + c.get(c.DATE);
		String date=year+"-"+month+"-"+day;
		List<Licence> outtimelicence=licenceService.findOutTimeLicence(date);
		for(int i=0;i<outtimelicence.size();i++){
			Licence licence=outtimelicence.get(i);
			licence.setLicencestatus("no");
			licenceService.saveOrUpdate(licence);
		}
		List<Organize> orglist=organizeService.listAllOrganize();
		for(int i=0;i<orglist.size();i++){
			Organize org=orglist.get(i);
			if(org.getBelongcode()!=null&&!(org.getBelongcode().equals(""))){
				boolean flag=licenceService.haveUserFulLicence(org.getOrgcode());
				if(!flag){
					List<SystemUser> users=userService.findUserByorgcode(org.getOrgcode());
					for(int j=0;j<users.size();j++){
						String email=users.get(j).getEmail();
						if(email!=null&&!(email.equals("")))
						emailer.sendmail(email,"���֤����","�������֤�ѵ����뼴ʱ����","text");
					}
				}
			}
		}
	}

}
