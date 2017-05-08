package com.service;

import java.util.List;

import com.orm.OrgStaff;

public interface OrgStaffService {
	public boolean saveOrUpdate(OrgStaff orgstaff);
	public boolean deleteOrgStaffBystaffcode(String staffcode);
	public OrgStaff findOrgStaffBystaffcode(String staffcode);
	public List<OrgStaff> listAllOrgStaff();
}
