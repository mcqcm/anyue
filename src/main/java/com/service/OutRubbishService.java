package com.service;

import java.util.List;

import com.orm.OutRubbish;

public interface OutRubbishService {
	public boolean saveOrUpdate(OutRubbish rubbish);
	public boolean deleteOutRubbishByid(String id);
	public OutRubbish findOutRubbishByid(String id);
	public List<OutRubbish> listAllOutRubbish();
}
