package com.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.orm.EvalRecord;
import com.orm.EvalResult;
import com.service.EvalResultService;
@Service
public class EvalResultServiceimpl implements EvalResultService {
	@Autowired
    BaseDao dao; 
	public boolean saveOrUpdate(EvalResult evalResult) {
		// TODO Auto-generated method stub
		try {
            dao.saveOrUpdate(evalResult);
            return true;
        } catch (Exception e) {
            return false;
        }
	}

	public boolean deleteEvalResultBykey(Integer id) {
		// TODO Auto-generated method stub
		try {
			dao.delById(EvalResult.class, id);;
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	public EvalResult findEvalResultByid(Integer id) {
		// TODO Auto-generated method stub
		return (EvalResult)dao.loadById(EvalResult.class, id);
	}

	public List<EvalResult> listAllEvalResult() {
		// TODO Auto-generated method stub
		return dao.listAll("EvalResult");
	}

	public List<EvalResult> listAllEvalResultThisYear(String time) {
			
		String sql="from EvalResult as t where t.evaltime='"+time+"'";
		return dao.query(sql);
	}

}
