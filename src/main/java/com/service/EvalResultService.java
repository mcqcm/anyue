package com.service;

import java.util.List;

import com.orm.EvalRecord;
import com.orm.EvalResult;

public interface EvalResultService {
	public boolean saveOrUpdate(EvalResult evalResult);
	public boolean deleteEvalResultBykey(Integer id);
	public EvalResult findEvalResultByid(Integer id);
	public List<EvalResult> listAllEvalResult();
	public List<EvalResult> listAllEvalResultThisYear(String time);
}
