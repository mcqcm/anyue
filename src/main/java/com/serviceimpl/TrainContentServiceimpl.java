package com.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.orm.Supplier;
import com.orm.SystemUser;
import com.orm.TrainContent;
import com.service.TrainContentService;
@Service
public class TrainContentServiceimpl implements TrainContentService {
	@Autowired
    BaseDao dao; 
	public List<TrainContent> listAllTrainContent() {
		// TODO Auto-generated method stub
		return dao.listAll("TrainContent");
	}

	public boolean saveOrUpdate(TrainContent traincontent) {
		// TODO Auto-generated method stub
		try {
            dao.saveOrUpdate(traincontent);
            return true;
        } catch (Exception e) {
            return false;
        }
	}

	public boolean deleteTrainContentByid(String id) {
		// TODO Auto-generated method stub
		int idd=Integer.parseInt(id);
		try {
			dao.delById(TrainContent.class, idd);;
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	public List<TrainContent> findTrainContentByflag(String flag) {
		// TODO Auto-generated method stub
		return dao.query("from TrainContent as r where r.uporend='"+flag+"'");
	}

	public TrainContent findTrainContentByid(String id) {
		// TODO Auto-generated method stub
		int idd=Integer.parseInt(id);
		return (TrainContent)dao.loadById(TrainContent.class, idd);
	}

}
