package com.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.orm.Medinfo;
import com.orm.SystemRole;
import com.service.RoleService;
@Service
public class RoleServiceimpl implements RoleService {
	@Autowired
	BaseDao dao;
	public boolean saveOrUpdate(SystemRole role) {
		// TODO Auto-generated method stub
		try {
            dao.saveOrUpdate(role);
            return true;
        } catch (Exception e) {
            return false;
        }
	}

	public boolean deleteRoleByid(String roleid) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(roleid);
		try {
			dao.delById(SystemRole.class, id);;
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	public SystemRole findRoleByid(String roleid) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(roleid);
		return (SystemRole)dao.loadById(SystemRole.class, id);
	}

	public List<SystemRole> listAllRole() {
		// TODO Auto-generated method stub
		return dao.listAll("SystemRole");
	}

}
