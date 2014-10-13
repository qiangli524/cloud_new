package com.sitech.basd.ibmmanager.domain;

import java.util.List;

/**
 * 
 * <p>
 * Title: PowerStateVo
 * </p>
 * <p>
 * Description: 虚拟机电源 json字符串需要的属性
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
 * @createtime 2013-11-20 上午9:16:16
 * 
 */
public class PowerStateVo {
	private String returnCode;// 返回"000"
	private String optResult;// 返回的值是 "success"和"false"
	private List<ReturnList> returnList;// 虚拟机的属性

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getOptResult() {
		return optResult;
	}

	public void setOptResult(String optResult) {
		this.optResult = optResult;
	}

	public List<ReturnList> getReturnList() {
		return returnList;
	}

	public void setReturnList(List<ReturnList> returnList) {
		this.returnList = returnList;
	}

}
