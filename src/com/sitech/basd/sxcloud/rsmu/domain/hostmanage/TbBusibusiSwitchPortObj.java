package com.sitech.basd.sxcloud.rsmu.domain.hostmanage;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbBusibusiSwitchPortObj extends BaseObj implements Serializable, Cloneable{

    private int ID         ;         //编号
    private int VLAN       ;         //vlan
    private int SWITCHPORT ;         //交换机口
    private int STATUS;              //是否可用
	
    public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public int getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(int status) {
		STATUS = status;
	}
	public int getSWITCHPORT() {
		return SWITCHPORT;
	}
	public void setSWITCHPORT(int switchport) {
		SWITCHPORT = switchport;
	}
	public int getVLAN() {
		return VLAN;
	}
	public void setVLAN(int vlan) {
		VLAN = vlan;
	}
	  
	  
}
