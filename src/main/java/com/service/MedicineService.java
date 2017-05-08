package com.service;

import java.util.List;

import com.orm.Medinfo;

public interface MedicineService {
	public boolean saveOrUpdate(Medinfo medicine);
	public boolean deleteMedicineByid(String medicineid);
	public Medinfo findMedicineByid(String medicineid);
	public List<Medinfo> listAllMedinfo();
}
