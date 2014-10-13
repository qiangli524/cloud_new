package com.sitech.ssd.ah.paas.domain.monitor;

import java.util.List;

public class PaasResourceMonitorObj {
	private String id;//唯一标识
	private String kpiName;//kpi指标
	private String kpiValue;//最新的数值
	private String kpiDec;//kpi描述
	private int kpiType;//kpi类型
	private String insertTime;//入库时间
	
	/*总量相关属性*/
	private String curr_items;//当前存储的数据总数
	private String bytes;//当前存储占用的字节数
	private String actives_slabs;//slab数量
	private String total_malloced;//总内存数量
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKpiName() {
		return kpiName;
	}
	public void setKpiName(String kpiName) {
		this.kpiName = kpiName;
	}
	public String getKpiValue() {
		return kpiValue;
	}
	public void setKpiValue(String kpiValue) {
		this.kpiValue = kpiValue;
	}
	public String getKpiDec() {
		return kpiDec;
	}
	public void setKpiDec(String kpiDec) {
		this.kpiDec = kpiDec;
	}
	public int getKpiType() {
		return kpiType;
	}
	public void setKpiType(int kpiType) {
		this.kpiType = kpiType;
	}
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	public String getCurr_items() {
		return curr_items;
	}
	public void setCurr_items(String curr_items) {
		this.curr_items = curr_items;
	}
	public String getBytes() {
		return bytes;
	}
	public void setBytes(String bytes) {
		this.bytes = bytes;
	}
	public String getActives_slabs() {
		return actives_slabs;
	}
	public void setActives_slabs(String actives_slabs) {
		this.actives_slabs = actives_slabs;
	}
	public String getTotal_malloced() {
		return total_malloced;
	}
	public void setTotal_malloced(String total_malloced) {
		this.total_malloced = total_malloced;
	}
	
}
