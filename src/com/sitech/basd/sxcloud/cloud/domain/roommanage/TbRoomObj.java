package com.sitech.basd.sxcloud.cloud.domain.roommanage;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbRoomObj extends BaseObj implements Serializable, Cloneable {
	private String r_id; // 房间编号
	private String r_name; // 房间名称
	private String f_id; // 所在楼层编号
	private String case_num; // 机柜数量

	public String getCase_num() {
		return case_num;
	}

	public void setCase_num(String case_num) {
		this.case_num = case_num;
	}

	public String getF_id() {
		return f_id;
	}

	public void setF_id(String f_id) {
		this.f_id = f_id;
	}

	public String getR_id() {
		return r_id;
	}

	public void setR_id(String r_id) {
		this.r_id = r_id;
	}

	public String getR_name() {
		return r_name;
	}

	public void setR_name(String r_name) {
		this.r_name = r_name;
	}

}
