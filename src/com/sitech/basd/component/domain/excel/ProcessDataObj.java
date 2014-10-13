package com.sitech.basd.component.domain.excel;

import java.util.List;

import com.sitech.basd.component.domain.process.ProcessObj;

/**
 * 
 * <p>
 * Title: ProcessDataObj
 * </p>
 * <p>
 * Description: 进程数据Obj
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-1-5 下午4:19:53
 * 
 */
public class ProcessDataObj {
	// 部署实例名列表
	private List<String> exampleNameList;
	// 进程列表
	private List<ProcessObj> processList;
	// 部署实例名称字符串，js数组
	private String exampleNameString;

	public String getExampleNameString() {
		return exampleNameString;
	}

	public void setExampleNameString(String exampleNameString) {
		this.exampleNameString = exampleNameString;
	}

	public List<String> getExampleNameList() {
		return exampleNameList;
	}

	public void setExampleNameList(List<String> exampleNameList) {
		this.exampleNameList = exampleNameList;
	}

	public List<ProcessObj> getProcessList() {
		return processList;
	}

	public void setProcessList(List<ProcessObj> processList) {
		this.processList = processList;
	}
}
