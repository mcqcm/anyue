package com.service;

import java.util.List;

import com.orm.TrainContent;

public interface TrainContentService {
	public List<TrainContent> listAllTrainContent();
	public boolean saveOrUpdate(TrainContent traincontent);
	public boolean deleteTrainContentByid(String id);
	public TrainContent findTrainContentByid(String id);
	public List<TrainContent> findTrainContentByflag(String flag);
}
