package com.sitech.basd.bol.domain.bolresource;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * <p>Title: BolResourceObj</p>
 * <p>Description: 资源视图实体类</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-10-31 上午11:28:36
 *
 */
public class BolResourceObj extends BaseObj{

	private String ID;
	private String TYPE;
	private String SUBTYPE;
	private Double RSIZE;
	private Double RUSAGE;
	private String PROG;
	private String NAME;
	private Integer BUSY;
	private String LOC;
	private Integer STATUS;
	private String HOST;
	private String TIME;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getSUBTYPE() {
		return SUBTYPE;
	}
	public void setSUBTYPE(String sUBTYPE) {
		SUBTYPE = sUBTYPE;
	}
	public Double getRSIZE() {
		return RSIZE;
	}
	public void setRSIZE(Double rSIZE) {
		RSIZE = rSIZE;
	}
	public Double getRUSAGE() {
		return RUSAGE;
	}
	public void setRUSAGE(Double rUSAGE) {
		RUSAGE = rUSAGE;
	}
	public String getPROG() {
		return PROG;
	}
	public void setPROG(String pROG) {
		PROG = pROG;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public Integer getBUSY() {
		return BUSY;
	}
	public void setBUSY(Integer bUSY) {
		BUSY = bUSY;
	}
	public String getLOC() {
		return LOC;
	}
	public void setLOC(String lOC) {
		LOC = lOC;
	}
	public Integer getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
	}
	public String getHOST() {
		return HOST;
	}
	public void setHOST(String hOST) {
		HOST = hOST;
	}
	public String getTIME() {
		return TIME;
	}
	public void setTIME(String tIME) {
		TIME = tIME;
	}
	
}
