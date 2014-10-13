package com.sitech.ssd.ah.busiMon.domain;
import java.io.Serializable;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;
/**
 * <p>Title:TbBossBusiMonAlarmCfg</p>
 * <p>Description:boss业务监控报警配置实体</p>
 * <p>Copyright:Copyright (c) 2014</p>
 * <p>Company:SI-TECH </p>
 * @author wangjl_cmi_jl
 * @version 1.0
 * @createtime Sep 6, 2014 11:01:22 AM
 */
@SuppressWarnings("serial")
public class TbBossBusiMonAlarmCfg extends BaseObj implements Serializable {
	/**
	 * 物理ID
	 */
	private String ID;
	/**
	 * 指标ID
	 */
	private String KPI_ID;
	/**
	 * 是否配置到子实体标识0-是1-否
	 */
	private String FLAG;
	/**
	 * 主机IP
	 */
	private String HOST_IP;
	/**
	 * 子实体的值
	 */
	private String SUB_ENTITY;
	/**
	 * 不告警开始时间
	 */
	private String START_TIME;
	/**
	 * 不告警结束时间
	 */
	private String END_TIME;
	/**
	 * 严重告警表达式
	 */
	private String SERIOUS_ALARM_EXPRESSION;
	/**
	 * 严重告警信息表达式
	 */
	private String SERIOUS_ALARM_INFO_EXPRESSION;
	/**
	 * 一般告警表达式
	 */
	private String COMMON_ALARM_EXPRESSION;
	/**
	 * 一般告警信息表达式
	 */
	private String COMMON_ALARM_INFO_EXPRESSION;
	/**
	 * 失效标志
	 */
	private String STATE;
	
	/**
	 * 结果list
	 */
	private List<String> resultList;
	
	public String getID() {
		return ID;
	}
	public void setID(String id) {
		ID = id;
	}
	public String getKPI_ID() {
		return KPI_ID;
	}
	public void setKPI_ID(String kpi_id) {
		KPI_ID = kpi_id;
	}
	public String getFLAG() {
		return FLAG;
	}
	public void setFLAG(String flag) {
		FLAG = flag;
	}
	public String getSUB_ENTITY() {
		return SUB_ENTITY;
	}
	public void setSUB_ENTITY(String sub_entity) {
		SUB_ENTITY = sub_entity;
	}
	public String getSTART_TIME() {
		return START_TIME;
	}
	public void setSTART_TIME(String start_time) {
		START_TIME = start_time;
	}
	public String getEND_TIME() {
		return END_TIME;
	}
	public void setEND_TIME(String end_time) {
		END_TIME = end_time;
	}
	public String getSERIOUS_ALARM_EXPRESSION() {
		return SERIOUS_ALARM_EXPRESSION;
	}
	public void setSERIOUS_ALARM_EXPRESSION(String serious_alarm_expression) {
		SERIOUS_ALARM_EXPRESSION = serious_alarm_expression;
	}
	public String getSERIOUS_ALARM_INFO_EXPRESSION() {
		return SERIOUS_ALARM_INFO_EXPRESSION;
	}
	public void setSERIOUS_ALARM_INFO_EXPRESSION(
			String serious_alarm_info_expression) {
		SERIOUS_ALARM_INFO_EXPRESSION = serious_alarm_info_expression;
	}
	public String getCOMMON_ALARM_EXPRESSION() {
		return COMMON_ALARM_EXPRESSION;
	}
	public void setCOMMON_ALARM_EXPRESSION(String common_alarm_expression) {
		COMMON_ALARM_EXPRESSION = common_alarm_expression;
	}
	public String getCOMMON_ALARM_INFO_EXPRESSION() {
		return COMMON_ALARM_INFO_EXPRESSION;
	}
	public void setCOMMON_ALARM_INFO_EXPRESSION(String common_alarm_info_expression) {
		COMMON_ALARM_INFO_EXPRESSION = common_alarm_info_expression;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String state) {
		STATE = state;
	}
	public List<String> getResultList() {
		return resultList;
	}
	public void setResultList(List<String> resultList) {
		this.resultList = resultList;
	}
	public String getHOST_IP() {
		return HOST_IP;
	}
	public void setHOST_IP(String host_ip) {
		HOST_IP = host_ip;
	}
}
