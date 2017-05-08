package com.orm;
// default package
// Generated 2015-5-21 22:24:34 by Hibernate Tools 3.2.2.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Supplier generated by hbm2java
 */
@Entity
@Table(name = "RECORD", schema = "ANYUE")
public class Record implements java.io.Serializable {

	private String recorgcode;//��������λ���������Ĳ�������λ����
	private String area;//�������
	private Integer workernum;//����Ա����
	private Integer seatnum;//��λ��
	private String address;//��ַ
	private String lawer; //����������
	private String manager;//������
	private String lawerTel;
	private String managerTel;
	private String testnum; //�������
	
	public Record() {
	}

	public Record(String recorgcode) {
		super();
		this.recorgcode = recorgcode;
	}

	public Record(String recorgcode, String area, Integer workernum,
			Integer seatnum) {
		super();
		this.recorgcode = recorgcode;
		this.area = area;
		this.workernum = workernum;
		this.seatnum = seatnum;
	}
	
	@Id
	@Column(name = "RECORGCODE", unique = true, nullable = false, length = 30)
	public String getRecorgcode() {
		return recorgcode;
	}

	public void setRecorgcode(String recorgcode) {
		this.recorgcode = recorgcode;
	}

	@Column(name = "AREA", length=30)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "WORKERNUM", precision = 22, scale = 0)
	public Integer getWorkernum() {
		return workernum;
	}

	public void setWorkernum(Integer workernum) {
		this.workernum = workernum;
	}

	@Column(name = "SEATNUM", precision = 22, scale = 0)
	public Integer getSeatnum() {
		return seatnum;
	}

	public void setSeatnum(Integer seatnum) {
		this.seatnum = seatnum;
	}

	@Column(name = "ADDRESS", length=30)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "LAWER", length=30)
	public String getLawer() {
		return lawer;
	}

	public void setLawer(String lawer) {
		this.lawer = lawer;
	}

	@Column(name = "MANAGER", length=30)
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	@Column(name = "LAWTEL", length=30)
	public String getLawerTel() {
		return lawerTel;
	}

	public void setLawerTel(String lawerTel) {
		this.lawerTel = lawerTel;
	}

	@Column(name = "MANAGERTEL", length=30)
	public String getManagerTel() {
		return managerTel;
	}

	public void setManagerTel(String managerTel) {
		this.managerTel = managerTel;
	}

	@Column(name = "TESTNUM", length=30)
	public String getTestnum() {
		return testnum;
	}

	public void setTestnum(String testnum) {
		this.testnum = testnum;
	}

	


}