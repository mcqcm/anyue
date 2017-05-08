package com.service;

import java.util.List;

import com.orm.SystemMenu;

public interface MenuService {
	public boolean saveOrUpdate(SystemMenu menu);
	public boolean deleteMenuByid(String menuid);
	public SystemMenu findMenuByid(String menuid);
	public List<SystemMenu> listAllMenu();
}
