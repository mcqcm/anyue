package com.service;

import java.util.List;

import com.orm.Record;

public interface RecordService {
	public boolean saveOrUpdate(Record record);
	public boolean deleteRecordBykey(String recorgcode);
	public Record findRecordBykey(String recorgcode);
	public List<Record> listAllRecord();
}
