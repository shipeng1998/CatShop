package com.yc.po;

import java.io.Serializable;

public class AdminPO implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = 3620089828710065940L;
private Integer aid;
   private String aname;
   public Integer getAid() {
	return aid;
}
public void setAid(Integer aid) {
	this.aid = aid;
}
public String getAname() {
	return aname;
}
public void setAname(String aname) {
	this.aname = aname;
}
public String getApwd() {
	return apwd;
}
public void setApwd(String apwd) {
	this.apwd = apwd;
}
private String apwd;
}
