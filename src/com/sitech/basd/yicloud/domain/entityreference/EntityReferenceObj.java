package com.sitech.basd.yicloud.domain.entityreference;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class EntityReferenceObj extends BaseObj implements Serializable,
		Cloneable {

	private static final long serialVersionUID = 1L;
	private String unit_id;
	private String code;
	private String c_id;

	public String getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getC_id() {
		return c_id;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

}
