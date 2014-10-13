package com.sitech.basd.sxcloud.rsmu.domain.softmanage;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class TbSysAppHisObj extends BaseObj {
	private int id;
	private int app_id;// 应用id
	private String remark;// 备注

	private String catch_status;// 应用捕获状态
	private String catch_time;// 应用捕获时间

	public String getCatch_status() {
		return catch_status;
	}

	public void setCatch_status(String catch_status) {
		this.catch_status = catch_status;
	}

	public String getCatch_time() {
		return catch_time;
	}

	public void setCatch_time(String catch_time) {
		this.catch_time = catch_time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getApp_id() {
		return app_id;
	}

	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
