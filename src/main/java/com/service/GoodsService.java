package com.service;

import java.util.List;

import com.orm.Goods;

public interface GoodsService {
	public boolean saveOrUpdate(Goods goods);
	public boolean deleteGoodsByid(String id);
	public Goods findGoodsByid(String id);
	public List<Goods> listAllGoods();
}
