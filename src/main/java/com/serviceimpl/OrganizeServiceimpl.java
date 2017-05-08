package com.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.orm.Organize;
import com.service.OrganizeService;
@Service
public class OrganizeServiceimpl implements OrganizeService {
	@Autowired
    BaseDao dao; 
	public boolean saveOrUpdate(Organize organize) {
		// TODO Auto-generated method stub
		try {
            dao.saveOrUpdate(organize);
            return true;
        } catch (Exception e) {
            return false;
        }
	}

	public boolean deleteOrganizeByorgcode(String orgcode) {
		// TODO Auto-generated method stub
		try {
			dao.delById(Organize.class, orgcode);;
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	public Organize findOrganizeByorgcode(String orgcode) {
		// TODO Auto-generated method stub
		return (Organize)dao.loadById(Organize.class, orgcode);
	}

	@SuppressWarnings("unchecked")
	public List<Organize> listAllOrganize() {
		// TODO Auto-generated method stub
		return dao.listAll("Organize");
	}

	public List<Organize> findOrganizeByBelongcode(String belongcode) {
		// TODO Auto-generated method stub
		return dao.query("From Organize as t where t.belongcode="+belongcode);
	}

}
