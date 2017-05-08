package com.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.orm.Licence;
import com.orm.Organize;
import com.orm.SystemUser;
import com.service.LicenceService;
@Service
public class LicenceServiceimpl implements LicenceService {
	@Autowired
    BaseDao dao; 
	public boolean saveOrUpdate(Licence licence) {
		// TODO Auto-generated method stub
		try {
            dao.saveOrUpdate(licence);
            return true;
        } catch (Exception e) {
            return false;
        }
	}

	public boolean deleteLicenceByid(String id) {
		// TODO Auto-generated method stub
		try {
			int licenceid=Integer.parseInt(id);
			dao.delById(Licence.class, licenceid);;
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	public Licence findLicenceByid(String id) {
		// TODO Auto-generated method stub
		int licenceid=Integer.parseInt(id);
		return (Licence)dao.loadById(Licence.class, licenceid);
	}

	public List<Licence> listAllLicence() {
		// TODO Auto-generated method stub
		return dao.listAll("Licence");
	}

	public List<SystemUser> findCheckUsers(String orgcode) {
		// TODO Auto-generated method stub
		List<Organize> orglist=dao.query("From Organize as t Where t.orgcode='"+orgcode+"'");
		if(orglist==null)
			return null;
		else{
			Organize belongOrg=orglist.get(0);
			List<SystemUser> users=dao.query("From SystemUser as t Where t.orgcode='"+belongOrg.getOrgcode()+"'");
			if(users==null)
				return null;
			else{
				return users;
			}
		}
	}

	public Licence findlastLicenceByOrgcode(String orgcode) {
		// TODO Auto-generated method stub
		List<Licence> mylist=dao.query("From Licence as t Where t.orgcode='"+orgcode+"' order by t.endtime desc");
		if(mylist!=null&&mylist.size()>0)
			return mylist.get(0);
		else return null;
	}

	public List<Licence> findOutTimeLicence(String today) {
		// TODO Auto-generated method stub
		return dao.query("From Licence as t Where t.licencestatus='yes' and t.endtime between to_date('1949-10-01','YYYY-MM-DD') and to_date('"+today+"','YYYY-MM-DD')");
	}

	public boolean haveUserFulLicence(String orgcode) {
		// TODO Auto-generated method stub
		List<Licence> mylist=dao.query("From Licence as t Where t.licencestatus='yes' and t.orgcode='"+orgcode+"'");
		if(mylist!=null&&mylist.size()>0)
			return true;
		else return false;
	}
}
