package com.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.orm.Notice;
import com.service.NoticeService;
@Service
public class NoticeServiceimpl implements NoticeService {
	@Autowired
    BaseDao dao; 
	public boolean saveOrUpdate(Notice notice) {
		// TODO Auto-generated method stub
		try {
            dao.saveOrUpdate(notice);
            return true;
        } catch (Exception e) {
            return false;
        }
	}

	public boolean deleteNoticeByid(String id) {
		// TODO Auto-generated method stub
		try {
			dao.delById(Notice.class, Integer.valueOf(id));;
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	public Notice findNoticeByid(String id) {
		// TODO Auto-generated method stub
		
		return (Notice)dao.loadById(Notice.class, Integer.valueOf(id));
	}

	public List<Notice> listAllNotice() {
		// TODO Auto-generated method stub
		return dao.listAll("Notice");
	}

}
