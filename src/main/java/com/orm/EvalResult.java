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
@Table(name = "EVALRESULT", schema = "ANYUE")
public class EvalResult implements java.io.Serializable {

	private Integer id;
	private String evaltime;
	private String orgcode;
	private String orgname;
	private String belongcode;
	private String belongname;
	private String score;
	
	public EvalResult() {
	}

	public EvalResult(Integer id) {
		super();
		this.id = id;
	}

	public EvalResult(Integer id,String evaltime, String orgcode, String orgname,
			String belongcode, String score) {
		super();
		this.id = id;
		this.evaltime = evaltime;
		this.orgcode = orgcode;
		this.orgname = orgname;
		this.belongcode = belongcode;
		this.score = score;
	}

	@Id
	@SequenceGenerator(name="sequenceID",sequenceName="SEQ")
	@GeneratedValue(generator="sequenceID",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "EVALTIME",  length = 20)
	public String getEvaltime() {
		return evaltime;
	}

	public void setEvaltime(String evaltime) {
		this.evaltime = evaltime;
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

	@Column(name = "BELONGCODE", length = 20)
	public String getBelongcode() {
		return belongcode;
	}

	public void setBelongcode(String belongcode) {
		this.belongcode = belongcode;
	}
	
	@Column(name = "BELONGNAME", length = 20)
	public String getBelongname() {
		return belongname;
	}

	public void setBelongname(String belongname) {
		this.belongname = belongname;
	}

	@Column(name = "SCORE", length = 20)
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
}
