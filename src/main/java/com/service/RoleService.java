package com.service;

import java.util.List;

import com.orm.SystemRole;

public interface RoleService {
	public boolean saveOrUpdate(SystemRole role);
	public boolean deleteRoleByid(String roleid);
	public SystemRole findRoleByid(String roleid);
	public List<SystemRole> listAllRole();
}
