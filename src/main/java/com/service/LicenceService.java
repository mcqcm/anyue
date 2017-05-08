package com.service;

import java.util.List;

import com.orm.Licence;
import com.orm.SystemUser;

public interface LicenceService {
	public boolean saveOrUpdate(Licence licence);
	public boolean deleteLicenceByid(String id);
	public Licence findLicenceByid(String id);
	public List<Licence> listAllLicence();
	public List<SystemUser> findCheckUsers(String orgcode);
	public Licence findlastLicenceByOrgcode(String orgcode);
	public List<Licence> findOutTimeLicence(String today);
	public boolean haveUserFulLicence(String orgcode);
}
