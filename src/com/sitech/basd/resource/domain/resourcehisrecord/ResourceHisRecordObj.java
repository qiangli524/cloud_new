package com.sitech.basd.resource.domain.resourcehisrecord;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * <p>
 * Title: ResourceHisRecord
 * </p>
 * <p>
 * Description: 资源历史记录实体类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-10-20 上午11:42:12
 * 
 */
public class ResourceHisRecordObj extends BaseObj {

	private String KPI_ID;// 采集指标
	private String KPI_VALUE;// 指标值
	private String COLL_TIME;// 采集时间
	private String DESCR;//
	public String getKPI_ID() {
		return KPI_ID;
	}
	public void setKPI_ID(String kPI_ID) {
		KPI_ID = kPI_ID;
	}
	public String getKPI_VALUE() {
		return KPI_VALUE;
	}
	public void setKPI_VALUE(String kPI_VALUE) {
		KPI_VALUE = kPI_VALUE;
	}
	public String getCOLL_TIME() {
		return COLL_TIME;
	}
	public void setCOLL_TIME(String cOLL_TIME) {
		COLL_TIME = cOLL_TIME;
	}
	public String getDESCR() {
		return DESCR;
	}
	public void setDESCR(String dESCR) {
		DESCR = dESCR;
	}

}
