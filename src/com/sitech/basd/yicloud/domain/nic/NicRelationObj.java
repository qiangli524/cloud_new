package com.sitech.basd.yicloud.domain.nic;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class NicRelationObj extends BaseObj{
	private int id;
	private String type;
	private String toUuid;
	private String fromUuid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getToUuid() {
		return toUuid;
	}
	public void setToUuid(String toUuid) {
		this.toUuid = toUuid;
	}
	public String getFromUuid() {
		return fromUuid;
	}
	public void setFromUuid(String fromUuid) {
		this.fromUuid = fromUuid;
	}
	
}
