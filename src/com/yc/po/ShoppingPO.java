package com.yc.po;

import java.io.Serializable;

public class ShoppingPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 372788136418307113L;
	private Integer spid;
	private String spname;
	private Double spprice;
	private Integer spnum;
	private String spphoto;
	private Double realprice;
	public Double getRealprice() {
		return realprice;
	}
	public void setRealprice(Double realprice) {
		this.realprice = realprice;
	}
	public Integer getSpid() {
		return spid;
	}
	public void setSpid(Integer spid) {
		this.spid = spid;
	}
	public String getSpname() {
		return spname;
	}
	public void setSpname(String spname) {
		this.spname = spname;
	}
	public Double getSpprice() {
		return spprice;
	}
	public void setSpprice(Double spprice) {
		this.spprice = spprice;
	}
	public Integer getSpnum() {
		return spnum;
	}
	public void setSpnum(Integer spnum) {
		this.spnum = spnum;
	}
	public String getSpphoto() {
		return spphoto;
	}
	public void setSpphoto(String spphoto) {
		this.spphoto = spphoto;
	}

}
