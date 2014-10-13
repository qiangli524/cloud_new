package com.sitech.basd.ibmmanager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.exception.DataSynchroException;
import com.sitech.basd.ibmmanager.service.CloudOSDataCompareService;
import com.sitech.basd.ibmmanager.service.IBMDataCompareService;
import com.sitech.basd.ibmmanager.util.IBMConstant;

/**
 * 
 * <p>
 * Title: IBMDataCompareAction
 * </p>
 * <p>
 * Description: 数据同步
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
 * @createtime 2013-11-14 下午3:14:39
 * 
 */
@Controller("ibmDataCompareAction")
@Scope("prototype")
public class IBMDataCompareAction {
	private String result = "success";
	@Autowired
	private IBMDataCompareService ibmDataCompareService;
	@Autowired
	private CloudOSDataCompareService cloudOSDataCompareService;

	/**
	 * 
	 * @Title: compareIBMData
	 * @Description: 数据同步
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-7 上午11:19:49
	 */
	public String compareIBMData() {
		result = IBMConstant.SUCCESS;
		try {
			ibmDataCompareService.IBMDataCompare();// IBM数据同步
			cloudOSDataCompareService.CloudOSDataCompare();// x86数据同步
		} catch (DataSynchroException e) {
			e.printStackTrace();
		}
		return "dataResult";
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
