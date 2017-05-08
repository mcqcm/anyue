package com.common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.orm.EvalResult;
import com.orm.SystemUser;

public class CommonMethod {

	public static String getNowSeason() {
		String result="";
		Date date=new Date();
		Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
		result=result+String.valueOf(year)+"��";
		int month=date.getMonth();
		if(month>=1&&month<=3){
			result+="��һ����";
		}else if(month>=4&&month<=6) {
			result+="�ڶ�����";
		}else if(month>=7&&month<=9) {
			result+="��������";
		}else if(month>=10&&month<=12) {
			result+="���ļ���";
		}
		return result;
	}

	public static List<EvalResult> pickThisYearScore(List<EvalResult> listAllEvalResult) {
		List<EvalResult> list=new ArrayList<EvalResult>();
		for(EvalResult e:listAllEvalResult){
			if(e.getEvaltime().equals(getNowSeason())){
				list.add(e);
			}
		}
		return list;
	}

	public static String getEvaluNoticeText(List<EvalResult> list) {
		String result="";
		result="<table style='width:600px;border-spacing:1px;' class='table1' id='status'><thead> <tr>";
		result+="<th field='name1' width='50'>����������</th> <th field='name2' width='50'>��λ���</th> <th field='name3' width='50'>��������</th> <th field='name4' width='50'>�÷�</th></tr></thead><tbody>";                          
		for(EvalResult e:list){
			result+="<tr><td style='text-align:center;'>"+e.getOrgname()+"</td><td style='text-align:center;'>"+e.getOrgcode()+"</td><td style='text-align:center;'>"+e.getBelongname()+"</td><td style='text-align:center;'>"+e.getScore()+"</td></tr>";
		}
		result+="</tbody></table>";
		return result;
	}

	public static List<String> getAllShopEmailAddress(List<SystemUser> userList) {
		List<String> emails=new ArrayList<String>();
		for(SystemUser u:userList){
			if(u.getUserrolename().equals("������λ")&&u.getEmail()!=null){
				emails.add(u.getEmail());
			}
		}
		return emails;
	}

	public static List<String> getAllUserEmailAddress(List<SystemUser> userList) {
		List<String> emails=new ArrayList<String>();
		for(SystemUser u:userList){
			if(!u.getUserrolename().equals("����Ա")&&u.getEmail()!=null){
				emails.add(u.getEmail());
				}
			}
		return emails;
	}

	public static List<String> getAllManagerEmailAddress(List<SystemUser> userList) {
		List<String> emails=new ArrayList<String>();
		for(SystemUser u:userList){
			if((u.getUserrolename().equals("ֱ���־�/�����")||u.getUserrolename().equals("�ؼ�ܾ�"))&&u.getEmail()!=null){
				emails.add(u.getEmail());
				}
			}
		return emails;
	}

	public static String removePathFromHtml(String text) {
		String result=text.replace(">/anyue/img/postImg/",">");
		return result;
	}

}
