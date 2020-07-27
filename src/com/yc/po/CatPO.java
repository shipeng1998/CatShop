package com.yc.po;

import java.io.Serializable;

public class CatPO implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 5062691460565723134L;
	private  Integer cid;
    private String cname;
    private String ctype;
    private Double cprice;
    private String csex;
    private String cappearance;//性格
    private  Integer cage;
    private String cphoto;
    private Double realprice;
	public Double getRealprice() {
		return realprice;
	}
	public void setRealprice(Double realprice) {
		this.realprice = realprice;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public Double getCprice() {
		return cprice;
	}
	public void setCprice(Double cprice) {
		this.cprice = cprice;
	}
	public String getCsex() {
		return csex;
	}
	public void setCsex(String csex) {
		this.csex = csex;
	}
	public String getCappearance() {
		return cappearance;
	}
	public void setCappearance(String cappearance) {
		this.cappearance = cappearance;
	}
	public Integer getCage() {
		return cage;
	}
	public void setCage(Integer cage) {
		this.cage = cage;
	}
	public String getCphoto() {
		return cphoto;
	}
	public void setCphoto(String cphoto) {
		this.cphoto = cphoto;
	}
}
