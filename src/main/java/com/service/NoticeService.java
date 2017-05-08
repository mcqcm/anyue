package com.service;

import java.util.List;

import com.orm.Notice;

public interface NoticeService {
	public boolean saveOrUpdate(Notice notice);
	public boolean deleteNoticeByid(String id);
	public Notice findNoticeByid(String id);
	public List<Notice> listAllNotice();
}
