package com.sitech.basd.ibmmanager.domain;

import java.util.List;

import com.sitech.vo.united.ResultSet;

/**
 * 
 * <p>
 * Title: LparObj
 * </p>
 * <p>
 * Description: 逻辑分区标识和名字
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2013-11-14 下午3:18:46
 * 
 */
public class LparObj extends ResultSet {
	private String powerCode;// 对应SX_LOGICAL_PARTITION中的machineserialnumber
	private String lparCode;// 对应SX_LOGICAL_PARTITION中的lpar_id
	private String lparName;// 对应SX_LOGICAL_PARTITION中的lpar_name
	private List<LparObj> list;

	public String getPowerCode() {
		return powerCode;
	}

	public void setPowerCode(String powerCode) {
		this.powerCode = powerCode;
	}

	public String getLparCode() {
		return lparCode;
	}

	public void setLparCode(String lparCode) {
		this.lparCode = lparCode;
	}

	public String getLparName() {
		return lparName;
	}

	public void setLparName(String lparName) {
		this.lparName = lparName;
	}

	public List<LparObj> getList() {
		return list;
	}

	public void setList(List<LparObj> list) {
		this.list = list;
	}

}
