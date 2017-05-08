package com.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.orm.OrgStaff;
import com.service.OrgStaffService;
@Service
public class OrgStaffServiceimpl implements OrgStaffService {
	@Autowired
    BaseDao dao; 
	public boolean saveOrUpdate(OrgStaff orgstaff) {
		// TODO Auto-generated method stub
		try {
            dao.saveOrUpdate(orgstaff);
            return true;
        } catch (Exception e) {
            return false;
        }
	}

	public boolean deleteOrgStaffBystaffcode(String staffcode) {
		// TODO Auto-generated method stub
		try {
			dao.delById(OrgStaff.class, staffcode);;
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	public OrgStaff findOrgStaffBystaffcode(String staffcode) {
		// TODO Auto-generated method stub
		return (OrgStaff)dao.loadById(OrgStaff.class, staffcode);
	}

	public List<OrgStaff> listAllOrgStaff() {
		// TODO Auto-generated method stub
		return dao.listAll("OrgStaff");
	}

}
