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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Supplier generated by hbm2java
 */
@Entity
@Table(name = "INGOODS", schema = "ANYUE")
public class InGoods implements java.io.Serializable {

	private Integer id;
	private String orgcode;
	private String orgname;
	private String supplier;
	private Date intime;//进货时间
	private byte[] suppliercard;//供货方资质证明
	private Set<Goods> goods = new HashSet<Goods>(0);//货单
	
	public InGoods() {
	}

	public InGoods(Integer id) {
		this.id = id;
	}


	public InGoods(Integer id, String orgcode, String orgname, String supplier,
			Date intime, byte[] suppliercard, Set<Goods> goods) {
		super();
		this.id = id;
		this.orgcode = orgcode;
		this.orgname = orgname;
		this.supplier = supplier;
		this.intime = intime;
		this.suppliercard = suppliercard;
		this.goods = goods;
	}

	@Id
	@SequenceGenerator(name="sequenceIngoods",sequenceName="SEQ")
	@GeneratedValue(generator="sequenceIngoods",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ORGCODE", length = 30)
	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	@Column(name = "ORGNAME", length = 50)
	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	@Column(name = "SUPPLIER", length = 50)
	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "SUPPLIERCARD", columnDefinition = "BLOB",nullable=true)
	public byte[] getSuppliercard() {
		return suppliercard;
	}

	public void setSuppliercard(byte[] suppliercard) {
		this.suppliercard = suppliercard;
	}

	@OneToMany(targetEntity=Goods.class,cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "ingoods")
	public Set<Goods> getGoods() {
		return goods;
	}

	public void setGoods(Set<Goods> goods) {
		this.goods = goods;
	}

}
