package com.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.orm.OutRubbish;
import com.service.OutRubbishService;
@Service
public class OutRubbishServiceimpl implements OutRubbishService {
	@Autowired
    BaseDao dao; 
	public boolean saveOrUpdate(OutRubbish rubbish) {
		// TODO Auto-generated method stub
		try {
            dao.saveOrUpdate(rubbish);
            return true;
        } catch (Exception e) {
            return false;
        }
	}

	public boolean deleteOutRubbishByid(String id) {
		// TODO Auto-generated method stub
		try {
			dao.delById(OutRubbish.class, Integer.valueOf(id));;
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	public OutRubbish findOutRubbishByid(String id) {
		// TODO Auto-generated method stub
		return (OutRubbish)dao.loadById(OutRubbish.class, Integer.valueOf(id));
	}

	public List<OutRubbish> listAllOutRubbish() {
		// TODO Auto-generated method stub
		return dao.listAll("OutRubbish");
	}

}
