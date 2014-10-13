package com.sitech.basd.sxcloud.rsmu.domain.deploy;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbBusiDeployVideoCommandsetObj extends BaseObj implements Serializable, Cloneable{
  private int ID;			//编号
  private int VIDEOID;		//录像编号
  private String COMMAND;	//命令
  
  //无参和有参构造方法
  public TbBusiDeployVideoCommandsetObj() {
	super();
	// TODO Auto-generated constructor stub
  }
  public TbBusiDeployVideoCommandsetObj(int iD, int vIDEOID, String cOMMAND) {
	super();
	ID = iD;
	VIDEOID = vIDEOID;
	COMMAND = cOMMAND;
  }
  
  //属性get和set方法
  public int getID() {
	return ID;
  }
  public void setID(int iD) {
	ID = iD;
  }
  public int getVIDEOID() {
	return VIDEOID;
  }
  public void setVIDEOID(int vIDEOID) {
	  VIDEOID = vIDEOID;
	}
	public String getCOMMAND() {
		return COMMAND;
	}
	public void setCOMMAND(String cOMMAND) {
		COMMAND = cOMMAND;
	}
  
  
  
}
