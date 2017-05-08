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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Supplier generated by hbm2java
 */
@Entity
@Table(name = "TRAINCONTENT", schema = "ANYUE")
public class TrainContent implements java.io.Serializable {

	private Integer id;//id
	private String tittle;//标题
	private Date publishtime;//通知发布时间
	private String content;//内容
	private String uporend;//是否结束上报
	private Set<Organize> organizes = new HashSet<Organize>(0);//餐饮单位 
	
	public TrainContent() {
	}

	public TrainContent(Integer id) {
		this.id = id;
	}



	public TrainContent(Integer id, String tittle, Date publishtime, String content, String uporend ,Set<Organize> organizes) {
		super();
		this.id = id;
		this.tittle = tittle;
		this.publishtime = publishtime;
		this.content = content;
		this.uporend = uporend;
		this.setOrganizes(organizes);
	}

	@Id
	@SequenceGenerator(name="sequenceTrainContent",sequenceName="SEQ")
	@GeneratedValue(generator="sequenceTrainContent",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "TITTLE", length = 50)
	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public Date getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}

	@Column(name = "CONTENT", length = 1000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "UPOREND", length = 10)
	public String getUporend() {
		return uporend;
	}

	public void setUporend(String uporend) {
		this.uporend = uporend;
	}

	@ManyToMany(targetEntity=Organize.class, cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinTable(name = "TRAIN_ORG", schema = "ANYUE", joinColumns = { @JoinColumn(name = "TRAINID", nullable=false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ORGID", nullable = false, updatable = false) })
	public Set<Organize> getOrganizes() {
		return organizes;
	}

	public void setOrganizes(Set<Organize> organizes) {
		this.organizes = organizes;
	}

}
