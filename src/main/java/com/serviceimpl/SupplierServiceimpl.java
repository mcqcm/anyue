package com.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.orm.Supplier;
import com.service.SupplierService;
@Service
public class SupplierServiceimpl implements SupplierService {
	@Autowired
	BaseDao dao; 
	public boolean saveOrUpdate(Supplier supplier) {
		// TODO Auto-generated method stub
		try {
            dao.saveOrUpdate(supplier);
            return true;
        } catch (Exception e) {
            return false;
        }
	}

	public boolean deleteSupplierByid(String supplierid) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(supplierid);
		try {
			dao.delById(Supplier.class, id);;
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	public Supplier findSupplierByid(String supplierid) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(supplierid);
		return (Supplier)dao.loadById(Supplier.class, id);
	}

}
