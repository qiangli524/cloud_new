package com.sitech.basd.sxcloud.rsmu.web.hostmanage.form;

import java.util.List;


@SuppressWarnings("serial")
public class BusiHostConfigHisForm {
	  
	  private int ID          ;         //编号
	  private int HOSTID      ;         //主机编号
	  private int TYPE        ;         //
	  private String HOSTUSERNAME  ;    //主机用户
	  private String HOSRPWD;           //主机密码
	  private String INSERTTIME  ;         //插入时间
	  private String UPDATEUSER  ;         //更新人员
	  private String UPDATETYPE  ;         //1：更新 2：删除
	  private String SPACE;                //磁盘空间  
	  public List resultList ;
	  
	  
	public List getResultList() {
		return resultList;
	}
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public int getHOSTID() {
		return HOSTID;
	}
	public void setHOSTID(int hostid) {
		HOSTID = hostid;
	}
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public String getINSERTTIME() {
		return INSERTTIME;
	}
	public void setINSERTTIME(String inserttime) {
		INSERTTIME = inserttime;
	}
	public int getTYPE() {
		return TYPE;
	}
	public void setTYPE(int type) {
		TYPE = type;
	}
	public String getUPDATETYPE() {
		return UPDATETYPE;
	}
	public void setUPDATETYPE(String updatetype) {
		UPDATETYPE = updatetype;
	}
	public String getUPDATEUSER() {
		return UPDATEUSER;
	}
	public void setUPDATEUSER(String updateuser) {
		UPDATEUSER = updateuser;
	}
	public String getHOSRPWD() {
		return HOSRPWD;
	}
	public void setHOSRPWD(String hosrpwd) {
		HOSRPWD = hosrpwd;
	}
	public String getHOSTUSERNAME() {
		return HOSTUSERNAME;
	}
	public void setHOSTUSERNAME(String hostusername) {
		HOSTUSERNAME = hostusername;
	}
	public String getSPACE() {
		return SPACE;
	}
	public void setSPACE(String space) {
		SPACE = space;
	}
	  
	  
}
