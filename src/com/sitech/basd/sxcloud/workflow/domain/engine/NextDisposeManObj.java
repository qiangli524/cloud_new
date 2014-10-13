package com.sitech.basd.sxcloud.workflow.domain.engine;

import java.io.Serializable;

public class NextDisposeManObj  implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;
	private String userId ;        //用户编号、用户唯一标识
	private int  oprate      ;     //任务操作,任务操作，(1:普通操作,2:挂起,3:工单回撤【只能回撤下一节点全然处理的工单】,4:打回)
	public int getOprate() {
		return oprate;
	}
	public void setOprate(int oprate) {
		this.oprate = oprate;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
