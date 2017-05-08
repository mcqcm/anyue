package com.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.orm.Medinfo;
import com.orm.TrainContent;
import com.orm.TrainStaff;
import com.service.TrainContentService;
import com.service.TrainStaffService;

@Service
public class TrainStaffServiceimpl implements TrainStaffService{

	@Autowired
    BaseDao dao;
	public List<TrainStaff> listAllTrainStaff() {
		return dao.listAll("TrainStaff");
	}

	public boolean saveOrUpdate(TrainStaff trainstaff) {
		try {
            dao.saveOrUpdate(trainstaff);
            return true;
        } catch (Exception e) {
            return false;
        }
	}


	public boolean deleteTrainStaffByid(Integer id) {
		try {
			dao.delById(TrainStaff.class, id);;
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	public TrainStaff findTrainStaffByid(Integer id) {
		return (TrainStaff)dao.loadById(TrainStaff.class, id);
	}

	public List<TrainStaff> findTrainStaffByContentid(String id) {
		return dao.query("from TRANSTAFF as r where r.id='"+id+"'");
	}
	
}
