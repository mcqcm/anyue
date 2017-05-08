package com.service;

import java.util.List;

import com.orm.SystemUser;

public interface UserService {
	public SystemUser haveUser(SystemUser user);
	public List<SystemUser> listAlluser();
	public boolean saveOrUpdate(SystemUser user);
	public boolean deleteUserByid(String userid);
	public SystemUser findUserByid(String userid);
	public List<SystemUser> findUserByname(String username);
	public boolean haveAccount(String account);
	public List<SystemUser> findUserByorgcode(String orgcode);
}
