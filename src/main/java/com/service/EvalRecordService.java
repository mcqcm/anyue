package com.service;

import java.util.List;

import com.orm.EvalRecord;

public interface EvalRecordService {
	public boolean saveOrUpdate(EvalRecord evalRecord);
	public boolean deleteEvalRecordByid(String id);
	public EvalRecord findEvalRecordByid(String id);
	public List<EvalRecord> listAllEvalRecord();
	public List<EvalRecord> listAllEvalRecordByTime();
}
