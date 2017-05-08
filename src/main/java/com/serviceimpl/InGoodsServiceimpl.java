package com.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.orm.InGoods;
import com.service.InGoodsService;
@Service
public class InGoodsServiceimpl implements InGoodsService {
	@Autowired
    BaseDao dao; 
	public boolean saveOrUpdate(InGoods ingoods) {
		// TODO Auto-generated method stub
		try {
            dao.saveOrUpdate(ingoods);
            return true;
        } catch (Exception e) {
            return false;
        }
	}

	public boolean deleteInGoodsByid(String id) {
		// TODO Auto-generated method stub
		Integer ids=Integer.valueOf(id);
		try {
			dao.delById(InGoods.class, ids);;
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	public InGoods findInGoodsByid(Integer id) {
		// TODO Auto-generated method stub
		return (InGoods)dao.loadById(InGoods.class, Integer.valueOf(id));
	}

	public List<InGoods> listAllInGoods() {
		// TODO Auto-generated method stub
		return dao.listAll("InGoods");
	}

}
