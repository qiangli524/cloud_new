package com.sitech.basd.yicloud.domain.vmauthority;

import java.io.Serializable;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class VmAuthorityObj extends BaseObj implements Serializable, Cloneable{
	private int ID;
	private String ENTITY_NAME;
	private String TYPE;// 类型
	private Integer USERID;// 用户Id
	private String OPERAUTHORITY;// 是否具有操作虚拟机权限
	private String ENTITY_ID;// 实体ID
	private String ENTITY_TYPE;// 实体类型，0vmware，1xen，2kvm，3powervm
	private String CONNECT_ID; // 资源池ID
	
	private List<String> entityIdList;//实体uuid集合

	public String getENTITY_TYPE() {
		return ENTITY_TYPE;
	}

	public void setENTITY_TYPE(String entity_type) {
		ENTITY_TYPE = entity_type;
	}

	public String getENTITY_ID() {
		return ENTITY_ID;
	}

	public void setENTITY_ID(String entity_id) {
		ENTITY_ID = entity_id;
	}

	public String getOPERAUTHORITY() {
		return OPERAUTHORITY;
	}

	public void setOPERAUTHORITY(String operauthority) {
		OPERAUTHORITY = operauthority;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getENTITY_NAME() {
		return ENTITY_NAME;
	}

	public void setENTITY_NAME(String entity_name) {
		ENTITY_NAME = entity_name;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String type) {
		TYPE = type;
	}

	public String getCONNECT_ID() {
		return CONNECT_ID;
	}

	public void setCONNECT_ID(String connect_id) {
		CONNECT_ID = connect_id;
	}

	public Integer getUSERID() {
		return USERID;
	}

	public void setUSERID(Integer uSERID) {
		USERID = uSERID;
	}

	public List<String> getEntityIdList() {
		return entityIdList;
	}

	public void setEntityIdList(List<String> entityIdList) {
		this.entityIdList = entityIdList;
	}

}
