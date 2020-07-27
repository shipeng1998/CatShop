package com.yc.po;

import java.io.Serializable;

public class OrderitemPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5956740317686011351L;
	private Integer iid;
	private String oid;
	private Integer cid;
	private Integer spid;
	private Double sprice;
	private Integer snum;
	public Integer getIid() {
		return iid;
	}
	public void setIid(Integer iid) {
		this.iid = iid;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getSpid() {
		return spid;
	}
	public void setSpid(Integer spid) {
		this.spid = spid;
	}
	public Double getSprice() {
		return sprice;
	}
	public void setSprice(Double sprice) {
		this.sprice = sprice;
	}
	public Integer getSnum() {
		return snum;
	}
	public void setSnum(Integer snum) {
		this.snum = snum;
	}

}
