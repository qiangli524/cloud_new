package com.sitech.basd.yicloud.domain.datastore;

import com.sitech.basd.common.domain.BasePrivilegeObj;
import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class DataStoreInfoObj extends BasePrivilegeObj {
	private int ID;
	private String NAME;
	private String FREE_SPACE;
	private String VMFS_CAPACITY;
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String name) {
		NAME = name;
	}
	public String getFREE_SPACE() {
		return FREE_SPACE;
	}
	public void setFREE_SPACE(String free_space) {
		FREE_SPACE = free_space;
	}
	public String getVMFS_CAPACITY() {
		return VMFS_CAPACITY;
	}
	public void setVMFS_CAPACITY(String vmfs_capacity) {
		VMFS_CAPACITY = vmfs_capacity;
	}
	
}
