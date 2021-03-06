package com.orm;
// default package
// Generated 2015-5-21 22:24:34 by Hibernate Tools 3.2.2.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Supplier generated by hbm2java
 */
@Entity
@Table(name = "EVALRECORD", schema = "ANYUE")
public class EvalRecord implements java.io.Serializable {
	//管理员在此表添加一条数据，各监管局开始测评
	private Integer id;
	private String evaltime;//设置季度或者年
	private String startusercode;
	private String startusername;
	private Date handletime;//管理员操作时间
	
	public EvalRecord() {
	}

	public EvalRecord(Integer id) {
		super();
		this.id = id;
	}

	public EvalRecord(Integer id, String evaltime, String startusercode,
			String startusername, Date handletime) {
		super();
		this.id = id;
		this.evaltime = evaltime;
		this.startusercode = startusercode;
		this.startusername = startusername;
		this.handletime = handletime;
	}



	@Id
	@SequenceGenerator(name="sequenceID",sequenceName="SEQ")
	@GeneratedValue(generator="sequenceID",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "EVALTIME", length = 20)
	public String getEvaltime() {
		return evaltime;
	}

	public void setEvaltime(String evaltime) {
		this.evaltime = evaltime;
	}

	@Column(name = "STARTUSERCODE", length = 30)
	public String getStartusercode() {
		return startusercode;
	}

	public void setStartusercode(String startusercode) {
		this.startusercode = startusercode;
	}

	@Column(name = "STARTUSERNAME", length = 30)
	public String getStartusername() {
		return startusername;
	}

	public void setStartusername(String startusername) {
		this.startusername = startusername;
	}

	public Date getHandletime() {
		return handletime;
	}

	public void setHandletime(Date handletime) {
		this.handletime = handletime;
	}
	
	
}
