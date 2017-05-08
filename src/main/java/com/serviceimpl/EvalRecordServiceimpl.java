package com.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.orm.EvalRecord;
import com.service.EvalRecordService;
@Service
public class EvalRecordServiceimpl implements EvalRecordService {
	@Autowired
    BaseDao dao; 
	public boolean saveOrUpdate(EvalRecord evalRecord) {
		// TODO Auto-generated method stub
		try {
            dao.saveOrUpdate(evalRecord);
            return true;
        } catch (Exception e) {
            return false;
        }
	}

	public boolean deleteEvalRecordByid(String id) {
		// TODO Auto-generated method stub
		try {
			dao.delById(EvalRecord.class, id);;
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	public EvalRecord findEvalRecordByid(String id) {
		// TODO Auto-generated method stub
		return (EvalRecord)dao.loadById(EvalRecord.class, id);
	}

	public List<EvalRecord> listAllEvalRecord() {
		// TODO Auto-generated method stub
		return dao.listAll("EvalRecord");
	}

	public List<EvalRecord> listAllEvalRecordByTime() {
		//as c order by c.id descselect t.*, t.rowid from EVALRECORD t 
		
		String sql="from EvalRecord as t order by t.evaltime asc";
		return dao.query(sql);
	}
	

}
