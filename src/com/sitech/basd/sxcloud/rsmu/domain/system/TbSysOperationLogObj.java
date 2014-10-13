package com.sitech.basd.sxcloud.rsmu.domain.system;

import java.io.Serializable;

import com.sitech.basd.common.domain.BasePrivilegeObj;

@SuppressWarnings("serial")
public class TbSysOperationLogObj extends BasePrivilegeObj implements Serializable, Cloneable {
	private int ID = 0;
	private int LOGINID = 0;
	private int USERID = 0;
	private int FUNCID = 0;
	private int OPERTYPE = 0;
	private String MESSAGE = null;
	private int RESULT = 0;
	private String REMARK = null;
	private String OPERTIME = null;
	private String ACCOUNT;
	private String NAME;
	private String FUNNAME;
	private String FUNCID_STR;
	private String start_opertime;
	private String end_opertime;
	private String resoursePoolObject;
	private String count;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getResoursePoolObject() {
		return resoursePoolObject;
	}

	public void setResoursePoolObject(String resoursePoolObject) {
		this.resoursePoolObject = resoursePoolObject;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public String getFUNCID_STR() {
		return FUNCID_STR;
	}

	public void setFUNCID_STR(String funcid_str) {
		FUNCID_STR = funcid_str;
	}

	public String getFUNNAME() {
		return FUNNAME;
	}

	public void setFUNNAME(String funname) {
		FUNNAME = funname;
	}

	public String getACCOUNT() {
		return ACCOUNT;
	}

	public void setACCOUNT(String account) {
		ACCOUNT = account;
	}

	public String getStart_opertime() {
		return start_opertime;
	}

	public void setStart_opertime(String start_opertime) {
		this.start_opertime = start_opertime;
	}

	public String getEnd_opertime() {
		return end_opertime;
	}

	public void setEnd_opertime(String end_opertime) {
		this.end_opertime = end_opertime;
	}

	public int getFUNCID() {
		return FUNCID;
	}

	public void setFUNCID(int funcid) {
		FUNCID = funcid;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public int getLOGINID() {
		return LOGINID;
	}

	public void setLOGINID(int loginid) {
		LOGINID = loginid;
	}

	public String getMESSAGE() {
		return MESSAGE;
	}

	public void setMESSAGE(String message) {
		MESSAGE = message;
	}

	public String getOPERTIME() {
		return OPERTIME;
	}

	public void setOPERTIME(String opertime) {
		OPERTIME = opertime;
	}

	public int getOPERTYPE() {
		return OPERTYPE;
	}

	public void setOPERTYPE(int opertype) {
		OPERTYPE = opertype;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String remark) {
		REMARK = remark;
	}

	public int getRESULT() {
		return RESULT;
	}

	public void setRESULT(int result) {
		RESULT = result;
	}

	public int getUSERID() {
		return USERID;
	}

	public void setUSERID(int userid) {
		USERID = userid;
	}

}
