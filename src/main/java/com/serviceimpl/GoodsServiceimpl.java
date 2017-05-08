package com.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.orm.Goods;
import com.service.GoodsService;
@Service
public class GoodsServiceimpl implements GoodsService {
	@Autowired
    BaseDao dao; 
	public boolean saveOrUpdate(Goods goods) {
		// TODO Auto-generated method stub
		try {
            dao.saveOrUpdate(goods);
            return true;
        } catch (Exception e) {
            return false;
        }
	}

	public boolean deleteGoodsByid(String id) {
		// TODO Auto-generated method stub
		Integer ids=Integer.valueOf(id);
		try {
			dao.delById(Goods.class, ids);;
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	public Goods findGoodsByid(String id) {
		// TODO Auto-generated method stub
		Integer ids=Integer.valueOf(id);
		return (Goods)dao.loadById(Goods.class, ids);
	}

	public List<Goods> listAllGoods() {
		// TODO Auto-generated method stub
		return dao.listAll("Goods");
	}

}
