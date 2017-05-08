package com.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSTAFF", schema = "ANYUE")
public class TrainStaff implements java.io.Serializable{
	//员工参加培训记录 一次培训一条记录
	private Integer trainid;
	private String staffcode;
	private String staffname;
	private String orgcode;
	private String orgname;
	private String telephone;
	private String sex;
	private String identitycode;//身份证
	private TrainContent trainContent;
	
	public TrainStaff() {
		
	}
	
	
	public TrainStaff(Integer trainid,String staffcode, String staffname, String orgcode,
			String orgname, String telephone, String sex, String identitycode,
			TrainContent trainContent) {
		super();
		this.trainid=trainid;
		this.staffcode = staffcode;
		this.staffname = staffname;
		this.orgcode = orgcode;
		this.orgname = orgname;
		this.telephone = telephone;
		this.sex = sex;
		this.identitycode = identitycode;
		this.trainContent = trainContent;
	}

	public TrainStaff(Integer trainid) {
		super();
		this.trainid = trainid;
	}
	
	@Id
	@SequenceGenerator(name="sequenceTrainContent",sequenceName="SEQ")
	@GeneratedValue(generator="sequenceTrainContent",strategy=GenerationType.SEQUENCE)
	@Column(name = "TID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getTrainid() {
		return trainid;
	}

	public void setTrainid(Integer trainid) {
		this.trainid = trainid;
	}
	
	
	@Column(name = "STAFFCODE", length = 20)
	public String getStaffcode() {
		return staffcode;
	}

	public void setStaffcode(String staffcode) {
		this.staffcode = staffcode;
	}
	
	@Column(name = "STAFFNAME", length = 20)
	public String getStaffname() {
		return staffname;
	}
	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}
	
	@Column(name = "ORGCODE", length = 20)
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	
	@Column(name = "ORGNAME", length = 20)
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	
	@Column(name = "TELEPHONE", length = 15)
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Column(name = "SEX", length = 5)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Column(name = "IDENTITYCODE", length = 20)
	public String getIdentitycode() {
		return identitycode;
	}
	public void setIdentitycode(String identitycode) {
		this.identitycode = identitycode;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID")
	public TrainContent getTrainContent() {
		return trainContent;
	}

	public void setTrainContent(TrainContent trainContent) {
		this.trainContent = trainContent;
	}
	
	
	
	
}
