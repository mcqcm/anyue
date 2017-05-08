package com.service;

import com.orm.Supplier;

public interface SupplierService {
	public boolean saveOrUpdate(Supplier supplier);
	public boolean deleteSupplierByid(String supplierid);
	public Supplier findSupplierByid(String supplierid);
}
