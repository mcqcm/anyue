package com.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.orm.Medinfo;
import com.orm.SystemMenu;
import com.service.MenuService;
@Service
public class MenuServiceimpl implements MenuService {
	@Autowired
	BaseDao dao;
	public boolean saveOrUpdate(SystemMenu menu) {
		// TODO Auto-generated method stub
		try {
            dao.saveOrUpdate(menu);
            return true;
        } catch (Exception e) {
            return false;
        }
	}

	public boolean deleteMenuByid(String menuid) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(menuid);
		try {
			dao.delById(SystemMenu.class, id);;
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	public SystemMenu findMenuByid(String menuid) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(menuid);
		return (SystemMenu)dao.loadById(SystemMenu.class, id);
	}

	public List<SystemMenu> listAllMenu() {
		// TODO Auto-generated method stub
		return dao.listAll("SystemMenu");
	}

}
