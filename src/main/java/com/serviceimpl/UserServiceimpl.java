package com.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.orm.SystemUser;
import com.service.UserService;
@Service
public class UserServiceimpl implements UserService {
	@Autowired
    BaseDao dao; 
	public SystemUser haveUser(SystemUser user) {
		// TODO Auto-generated method stub
		return (SystemUser)dao.loadObject("from SystemUser as a where a.account = '"+user.getAccount()+"' and a.password = '"+user.getPassword()+"'");
	}
	@SuppressWarnings("unchecked")
	public List<SystemUser> listAlluser() {
		// TODO Auto-generated method stub
		return dao.listAll("SystemUser");
	}
	public boolean saveOrUpdate(SystemUser user) {
		// TODO Auto-generated method stub
		try {
            dao.saveOrUpdate(user);
            return true;
        } catch (Exception e) {
            return false;
        }
	}
	public boolean deleteUserByid(String userid) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(userid);
		try {
			dao.delById(SystemUser.class, id);;
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}
	public SystemUser findUserByid(String userid) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(userid);
			return (SystemUser)dao.loadById(SystemUser.class, id);
	}
	@SuppressWarnings("unchecked")
	public List<SystemUser> findUserByname(String username) {
		// TODO Auto-generated method stub
		return dao.query("from SystemUser as r where r.username='"+username+"'");
	}
	public List<SystemUser> findUserByorgcode(String orgcode) {
		// TODO Auto-generated method stub
		return dao.query("from SystemUser as r where r.orgcode='"+orgcode+"'");
	}
	public boolean haveAccount(String account) {
		// TODO Auto-generated method stub
		List<SystemUser> userlist= dao.query("from SystemUser as r where r.account='"+account+"'");
		if(userlist!=null&&userlist.size()>0){
			return true;
		}
		return false;
	}
}
