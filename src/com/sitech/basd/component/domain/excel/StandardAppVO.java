package com.sitech.basd.component.domain.excel;

/**
 * 
 * <p>
 * Title: StandardAppVO
 * </p>
 * <p>
 * Description: 基准应用数据库熟悉VO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-5-29 下午1:57:31
 * 
 */
public class StandardAppVO extends BaseAppVO {
	// 基准应用ID
	private Integer standardAppId;
	// 应用标示
	private String appIdentify;
	// 部署实例ID
	private Integer deployExampleId;

	public Integer getStandardAppId() {
		return standardAppId;
	}

	public void setStandardAppId(Integer standardAppId) {
		this.standardAppId = standardAppId;
	}

	public String getAppIdentify() {
		return appIdentify;
	}

	public void setAppIdentify(String appIdentify) {
		this.appIdentify = appIdentify;
	}

	public Integer getDeployExampleId() {
		return deployExampleId;
	}

	public void setDeployExampleId(Integer deployExampleId) {
		this.deployExampleId = deployExampleId;
	}

}
