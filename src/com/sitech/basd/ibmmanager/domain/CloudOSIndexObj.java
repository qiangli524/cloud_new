package com.sitech.basd.ibmmanager.domain;

/**
 * 
 * <p>
 * Title: ClouldOSIndexObj
 * </p>
 * <p>
 * Description: X86性能指标
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
 * @createtime 2013-11-19 下午6:52:57
 * 
 */
public class CloudOSIndexObj {
	private String eq_id;// 主机ID
	private String hy_id;// 虚拟机ID
	private String kpi_id;
	private String kpi_value;
	private String coll_time;// 时间
	private String tableName;// 表名字
	private String type;// 类型 主机 type=5 虚拟机 type=6

	public String getEq_id() {
		return eq_id;
	}

	public void setEq_id(String eq_id) {
		this.eq_id = eq_id;
	}

	public String getHy_id() {
		return hy_id;
	}

	public void setHy_id(String hy_id) {
		this.hy_id = hy_id;
	}

	public String getKpi_id() {
		return kpi_id;
	}

	public void setKpi_id(String kpi_id) {
		this.kpi_id = kpi_id;
	}

	public String getKpi_value() {
		return kpi_value;
	}

	public void setKpi_value(String kpi_value) {
		this.kpi_value = kpi_value;
	}

	public String getColl_time() {
		return coll_time;
	}

	public void setColl_time(String coll_time) {
		this.coll_time = coll_time;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
