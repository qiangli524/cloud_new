package com.sitech.basd.sxcloud.rsmu.domain.system;

import java.io.Serializable;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbSysGroupfuncObj extends BaseObj implements Serializable,
		Cloneable {
	private int ID;
	private int GROUPID;
	private int FUNCID;
	private String PERMIT;

	private List<Integer> idsList;//符合条件的指标集合
	
	public int getFUNCID() {
		return FUNCID;
	}

	public void setFUNCID(int funcid) {
		FUNCID = funcid;
	}

	public int getGROUPID() {
		return GROUPID;
	}

	public void setGROUPID(int groupid) {
		GROUPID = groupid;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getPERMIT() {
		return PERMIT;
	}

	public void setPERMIT(String permit) {
		PERMIT = permit;
	}

	public List<Integer> getIdsList() {
		return idsList;
	}

	public void setIdsList(List<Integer> idsList) {
		this.idsList = idsList;
	}

}
