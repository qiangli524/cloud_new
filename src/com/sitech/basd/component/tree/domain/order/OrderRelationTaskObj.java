package com.sitech.basd.component.tree.domain.order;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class OrderRelationTaskObj extends BaseObj{	
	
	private String TASK_ID; //进程的ID
	
	private String ORDER_ID;//订单id

	public String getTASK_ID() {
		return TASK_ID;
	}

	public void setTASK_ID(String tASK_ID) {
		TASK_ID = tASK_ID;
	}

	public String getORDER_ID() {
		return ORDER_ID;
	}

	public void setORDER_ID(String oRDER_ID) {
		ORDER_ID = oRDER_ID;
	}
	
}