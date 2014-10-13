package com.sitech.basd.ibmmanager.domain;

import java.util.List;

import com.sitech.vo.united.ResultSet;

/**
 * 
 * <p>
 * Title: PowObj
 * </p>
 * <p>
 * Description: power的标识和名字
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
 * @createtime 2013-11-14 下午3:19:06
 * 
 */
public class PowObj extends ResultSet {
	private String hmcCode;// 对应SX_POWER中的hmcid
	private String powerCode;// 对应SX_POWER中的machineserialnumber
	private String powerName;// 对应SX_POWER中的sysname
	private List<PowObj> list;

	public String getHmcCode() {
		return hmcCode;
	}

	public void setHmcCode(String hmcCode) {
		this.hmcCode = hmcCode;
	}

	public String getPowerCode() {
		return powerCode;
	}

	public void setPowerCode(String powerCode) {
		this.powerCode = powerCode;
	}

	public String getPowerName() {
		return powerName;
	}

	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}

	public List<PowObj> getList() {
		return list;
	}

	public void setList(List<PowObj> list) {
		this.list = list;
	}

}
