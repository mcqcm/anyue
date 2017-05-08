package com.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.orm.Record;
import com.service.RecordService;
@Service
public class RecordServiceimpl implements RecordService {
	@Autowired
    BaseDao dao; 
	public boolean saveOrUpdate(Record record) {
		// TODO Auto-generated method stub
		try {
            dao.saveOrUpdate(record);
            return true;
        } catch (Exception e) {
            return false;
        }
	}

	public boolean deleteRecordBykey(String recorgcode) {
		// TODO Auto-generated method stub
		try {
			dao.delById(Record.class, recorgcode);;
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	public Record findRecordBykey(String recorgcode) {
		// TODO Auto-generated method stub
		return (Record)dao.loadById(Record.class, recorgcode);
	}

	public List<Record> listAllRecord() {
		// TODO Auto-generated method stub
		return dao.listAll("Record");
	}

}
