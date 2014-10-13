package com.sitech.basd.sxcloud.cloud.domain.monitor;

import java.io.Serializable;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class HealthyAlarmObj extends BaseObj implements Serializable, Cloneable {
	private List<String> entityIds; // 实体id 

	public List<String> getEntityIds() {
		return entityIds;
	}

	public void setEntityIds(List<String> entityIds) {
		this.entityIds = entityIds;
	}
	

}
