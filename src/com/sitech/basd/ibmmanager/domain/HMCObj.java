package com.sitech.basd.ibmmanager.domain;

import java.util.List;

import com.sitech.vo.united.ResultSet;

/**
 * 
 * <p>
 * Title: HMCObj
 * </p>
 * <p>
 * Description: HMC标识和名字
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
 * @createtime 2013-11-14 下午3:17:37
 * 
 */
public class HMCObj extends ResultSet {
	private String hmcCode;// 对应SX_HMC中的ID
	private String hmcName;// 对应SX_HMC中的name
	private List<HMCObj> hmclist;

	public String getHmcCode() {
		return hmcCode;
	}

	public void setHmcCode(String hmcCode) {
		this.hmcCode = hmcCode;
	}

	public String getHmcName() {
		return hmcName;
	}

	public void setHmcName(String hmcName) {
		this.hmcName = hmcName;
	}

	public List<HMCObj> getHmclist() {
		return hmclist;
	}

	public void setHmclist(List<HMCObj> hmclist) {
		this.hmclist = hmclist;
	}

}
