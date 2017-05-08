package com.service;

import java.util.List;

import com.orm.Organize;

public interface OrganizeService {
	public boolean saveOrUpdate(Organize organize);
	public boolean deleteOrganizeByorgcode(String orgcode);
	public Organize findOrganizeByorgcode(String orgcode);
	public List<Organize> listAllOrganize();
	public List<Organize> findOrganizeByBelongcode(String belongcode);
}
