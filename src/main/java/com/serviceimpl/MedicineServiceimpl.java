package com.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.dao.BaseDao;
import com.orm.Medinfo;
import com.service.MedicineService;
@Service
public class MedicineServiceimpl implements MedicineService {
	@Autowired
	BaseDao dao;
	public boolean saveOrUpdate(Medinfo medicine) {
		// TODO Auto-generated method stub
		try {
            dao.saveOrUpdate(medicine);
            return true;
        } catch (Exception e) {
            return false;
        }
	}

	public boolean deleteMedicineByid(String medicineid) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(medicineid);
		try {
			dao.delById(Medinfo.class, id);;
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	public Medinfo findMedicineByid(String medicineid) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(medicineid);
		return (Medinfo)dao.loadById(Medinfo.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Medinfo> listAllMedinfo() {
		// TODO Auto-generated method stub
		return dao.listAll("Medinfo");
	}

}
