package com.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.CommonMethod;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.EvalRecord;
import com.orm.EvalResult;
import com.orm.Organize;
import com.orm.Record;
import com.orm.SystemUser;
import com.service.EvalRecordService;
import com.service.EvalResultService;
import com.service.OrganizeService;
import com.service.UserService;

@SuppressWarnings("serial")
public class EvaluAction extends ActionSupport{
	private EvalRecord evalRecord;
	private EvalResult evalResult;
	public String evaluYear;
	public String thisYear;
	public String thisOrg;
	public List<String> scoreList=new ArrayList<String>();
	@Autowired
    EvalRecordService evalRecordService;
    @Autowired
    EvalResultService evalResultService;
    @Autowired
    OrganizeService orgservice;
    @Autowired
    UserService userService;
    
    private List<Organize> orglists;
    private List<EvalResult> resultlists=new ArrayList<EvalResult>();

    public String addEvalu() throws Exception {
    	orglists=orgservice.listAllOrganize();
    	HttpServletRequest request = ServletActionContext.getRequest(); 
    	SystemUser user=(SystemUser)request.getSession().getAttribute("loginuser");
    	thisOrg=user.getOrgname();
    	int count=0;
		for(Organize e:orglists){
			if(e.getBelongcode()==null){
				;
			}else if(e.getBelongcode().equals(user.getOrgcode())){
				EvalResult er=new EvalResult();
				//er.setId(new Integer(new Random().nextInt()));
				er.setEvaltime(evaluYear);
				er.setOrgcode(e.getOrgcode());
				er.setBelongcode(e.getBelongcode());
				er.setBelongname(orgservice.findOrganizeByorgcode(e.getBelongcode()).getOrgname());
				er.setOrgname(e.getOrgname());
				er.setScore(scoreList.get(count++));
				resultlists.add(er);
			}
		}
    	boolean flag = true;
    	for(EvalResult er:resultlists){
    		flag&=evalResultService.saveOrUpdate(er);
    	}
        if (flag) {
           return "addSuccess";
        }
        return ERROR;
    }
    
    public String startEvalu() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest(); 
		SystemUser user=(SystemUser) request.getSession().getAttribute("loginuser");
		//EvalRecord er=evalRecordService.listAllEvalRecord().get(0);
		List<EvalRecord> tlist=evalRecordService.listAllEvalRecordByTime();
		if(tlist.size()!=0){
			evaluYear=tlist.get(tlist.size()-1).getEvaltime();
			System.out.println(evaluYear);
			thisYear=CommonMethod.getNowSeason();
		}else{
			evaluYear="no data";
		    thisYear=CommonMethod.getNowSeason();
		}		
		if(user.getUserrolename().equals("管理员")){
			if(thisYear.equals(evaluYear)){
				return "noneedstart"; //这里改为查看发起记录 提示不能重复发起
			}
			return "getadmin";
		}else if(!user.getUserrolename().equals("餐饮单位")){
			if(thisYear.equals(evaluYear)){
				thisOrg=user.getOrgname();
				resultlists=CommonMethod.pickThisYearScore(evalResultService.listAllEvalResult());
				if(resultlists.size()!=0){
					return "alreadyevalu";
				}
				orglists=orgservice.listAllOrganize();
				List<Organize> templist=new ArrayList<Organize>();
				for(Organize e:orglists){
					System.out.println(user.getOrgcode());
					if(e.getBelongcode()==null){
						templist.add(e);
					}else if(e.getBelongcode().equals(user.getOrgcode())){
						;
					}else{
						templist.add(e);
					}
				}
				orglists.removeAll(templist);
				return "getuser";
			}
			return "noneedevalu";
		}
        return "getuser";
    }
    
    public String addrecord() throws Exception {
    	String userCode=userService.findUserByname(evalRecord.getStartusername()).get(0).getUsercode();
        evalRecord.setStartusercode(userCode);
    	boolean flag=evalRecordService.saveOrUpdate(evalRecord);
        if (flag) {
            return "addRecordSuccess";
        }
        return ERROR;
    }
    public String evaluNotice() throws Exception {
    	resultlists=evalResultService.listAllEvalResultThisYear(CommonMethod.getNowSeason());
    	
        if (resultlists!=null) {
            return "noticeList";
        }
        return ERROR;
    }
    
    public String ListAll() throws Exception {
        return "ListAll";
    }

	public EvalRecord getEvalRecord() {
		return evalRecord;
	}

	public void setEvalRecord(EvalRecord evalRecord) {
		this.evalRecord = evalRecord;
	}

	public EvalResult getEvalResult() {
		return evalResult;
	}

	public void setEvalResult(EvalResult evalResult) {
		this.evalResult = evalResult;
	}

	public EvalRecordService getEvalRecordService() {
		return evalRecordService;
	}

	public void setEvalRecordService(EvalRecordService evalRecordService) {
		this.evalRecordService = evalRecordService;
	}

	public EvalResultService getEvalResultService() {
		return evalResultService;
	}

	public void setEvalResultService(EvalResultService evalResultService) {
		this.evalResultService = evalResultService;
	}

	public List<EvalResult> getResultlists() {
		return resultlists;
	}

	public void setResultlists(List<EvalResult> resultlists) {
		this.resultlists = resultlists;
	}

	public String getEvaluYear() {
		return evaluYear;
	}

	public void setEvaluYear(String evaluYear) {
		this.evaluYear = evaluYear;
	}

	public List<Organize> getOrglists() {
		return orglists;
	}

	public void setOrglists(List<Organize> orglists) {
		this.orglists = orglists;
	}
    
	
    
    
}
