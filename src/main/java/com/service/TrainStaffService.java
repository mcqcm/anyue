package com.service;

import java.util.List;

import com.orm.Medinfo;
import com.orm.TrainContent;
import com.orm.TrainStaff;

public interface TrainStaffService {
	public List<TrainStaff> listAllTrainStaff();
	public boolean saveOrUpdate(TrainStaff trainstaff);
	public boolean deleteTrainStaffByid(Integer id);
	public TrainStaff findTrainStaffByid(Integer id);
	public List<TrainStaff> findTrainStaffByContentid(String id);
}
