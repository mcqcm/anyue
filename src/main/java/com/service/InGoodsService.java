package com.service;

import java.util.List;

import com.orm.InGoods;

public interface InGoodsService {
	public boolean saveOrUpdate(InGoods ingoods);
	public boolean deleteInGoodsByid(String id);
	public InGoods findInGoodsByid(Integer id);
	public List<InGoods> listAllInGoods();
}
