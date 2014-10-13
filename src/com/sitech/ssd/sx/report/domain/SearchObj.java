/**
 * Copyright (c) 2013 SI-TECH Software, Inc.
 * All right reserved. 
 * 
 * 云管理平台
 */
package com.sitech.ssd.sx.report.domain;

import java.util.Map;

/**
 *<P>
 * 报表查询
 *</p>
 * Apr 12, 2014,12:23:03 PM
 * 
 * @author xugang
 * 
 * @version 1.0
 */
public class SearchObj {
	//power lpar
	private String type;
	private String powerId;
	private String lparId;
	//cpu 内存
	private String kpi;
	//卡ID
	private String cardId;
	//1读速度、2写速度、3读包数速度、4写包数速度
	private Integer cardKpi;
	//时间段
	private Map timeMap;
	
	private String startTime;
	private String endTime;
	/**
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 *
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 *
	 * @return the powerId
	 */
	public String getPowerId() {
		return powerId;
	}
	/**
	 *
	 * @param powerId the powerId to set
	 */
	public void setPowerId(String powerId) {
		this.powerId = powerId;
	}
	/**
	 *
	 * @return the lparId
	 */
	public String getLparId() {
		return lparId;
	}
	/**
	 *
	 * @param lparId the lparId to set
	 */
	public void setLparId(String lparId) {
		this.lparId = lparId;
	}
	/**
	 *
	 * @return the kpi
	 */
	public String getKpi() {
		return kpi;
	}
	/**
	 *
	 * @param kpi the kpi to set
	 */
	public void setKpi(String kpi) {
		this.kpi = kpi;
	}
	/**
	 *
	 * @return the cardId
	 */
	public String getCardId() {
		return cardId;
	}
	/**
	 *
	 * @param cardId the cardId to set
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	/**
	 *
	 * @return the cardKpi
	 */
	public Integer getCardKpi() {
		return cardKpi;
	}
	/**
	 *
	 * @param cardKpi the cardKpi to set
	 */
	public void setCardKpi(Integer cardKpi) {
		this.cardKpi = cardKpi;
	}
	
	/**
	 *
	 * @return the timeMap
	 */
	public Map getTimeMap() {
		return timeMap;
	}
	/**
	 *
	 * @param timeMap the timeMap to set
	 */
	public void setTimeMap(Map timeMap) {
		this.timeMap = timeMap;
	}
	/**
	 *
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 *
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 *
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 *
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
