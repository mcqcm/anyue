package com.orm;
// default package
// Generated 2015-5-21 22:24:34 by Hibernate Tools 3.2.2.GA

import java.util.Date;

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

/**
 * Medinfo generated by hbm2java
 */
@Entity
@Table(name = "MEDINFO", schema = "ANYUE")
public class Medinfo implements java.io.Serializable {

	private Integer id;
	private String medcode;
	private String medname;
	private Supplier supplier;
	private Integer mednumber;
	private String supname;
	private Integer price;
	private Date maketime;
	private Date userfultime;
	public Date getMaketime() {
		return maketime;
	}

	public void setMaketime(Date maketime) {
		this.maketime = maketime;
	}

	public Date getUserfultime() {
		return userfultime;
	}

	public void setUserfultime(Date userfultime) {
		this.userfultime = userfultime;
	}

	private String more;

	public Medinfo() {
	}

	public Medinfo(Integer id) {
		this.id = id;
	}

	public Medinfo(Integer id, Supplier supplier,  String medcode,
			String medname, Integer mednumber, String supname,
			Integer price, String more) {
		this.id = id;
		this.supplier = supplier;
		this.medcode = medcode;
		this.medname = medname;
		this.mednumber = mednumber;
		this.supname = supname;
		this.price = price;
		this.more = more;
	}

	@Id
	@SequenceGenerator(name="sequenceMed",sequenceName="SEQ")
	@GeneratedValue(generator="sequenceMed",strategy=GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SUPID")
	public Supplier getSupplier() {
		return this.supplier;
	}
	
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	@Column(name = "MEDCODE", length = 50)
	public String getMedcode() {
		return this.medcode;
	}

	public void setMedcode(String medcode) {
		this.medcode = medcode;
	}

	@Column(name = "MEDNAME", length = 100)
	public String getMedname() {
		return this.medname;
	}

	public void setMedname(String medname) {
		this.medname = medname;
	}

	@Column(name = "MEDNUMBER", precision = 22, scale = 0)
	public Integer getMednumber() {
		return this.mednumber;
	}

	public void setMednumber(Integer mednumber) {
		this.mednumber = mednumber;
	}

	@Column(name = "SUPNAME", length = 100)
	public String getSupname() {
		return this.supname;
	}

	public void setSupname(String supname) {
		this.supname = supname;
	}

	@Column(name = "PRICE", precision = 22, scale = 0)
	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Column(name = "MORE", length = 100)
	public String getMore() {
		return this.more;
	}

	public void setMore(String more) {
		this.more = more;
	}


}
