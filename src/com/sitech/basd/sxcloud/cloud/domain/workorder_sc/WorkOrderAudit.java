package com.sitech.basd.sxcloud.cloud.domain.workorder_sc;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class WorkOrderAudit  extends BaseObj {

	private Integer id;
	private String workorderid;
	private String audit_type;
	private String audit_date;
	private String audit_info;
	private String audit_user;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWorkorderid() {
		return workorderid;
	}
	public void setWorkorderid(String workorderid) {
		this.workorderid = workorderid;
	}
	public String getAudit_type() {
		return audit_type;
	}
	public void setAudit_type(String auditType) {
		audit_type = auditType;
	}
	public String getAudit_date() {
		return audit_date;
	}
	public void setAudit_date(String auditDate) {
		audit_date = auditDate;
	}
	public String getAudit_info() {
		return audit_info;
	}
	public void setAudit_info(String auditInfo) {
		audit_info = auditInfo;
	}
	public String getAudit_user() {
		return audit_user;
	}
	public void setAudit_user(String auditUser) {
		audit_user = auditUser;
	}

}
