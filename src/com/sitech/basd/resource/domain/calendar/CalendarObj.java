package com.sitech.basd.resource.domain.calendar;

import java.sql.Timestamp;
import java.util.List;

import com.sitech.basd.fusioncharts.vo.Chart;

/**
 * 
 * <p>Title: CalendarObj</p>
 * <p>Description: 资源使用情况统计 相关属性</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH</p>
 * 
 * @author qism
 * @version 1.0
 * @createtime 2014-5-16 上午10:10:03
 * 
 */
public class CalendarObj {
	private String selectTime;
	private int eq_type;// 设备类型
	private int proportion;// 比例值
	private String all_cpu;// cpu总量
	private String use_cpu;// 已用cpu
	private String advance_cpu;// 预占cpu
	private String unuse_cpu;// 剩余cpu
	private Timestamp check_date;
	private String all_mem;
	private String use_mem;
	private String advance_mem;
	private String unuse_mem;

	private String all_storage;
	private String use_storage;
	private String advance_storage;
	private String unuse_storage;

	private String all_ip;
	private String use_ip;
	private String advance_ip;
	private String unuse_ip;
	
	private List<?> dataList;
	private Chart chart;
	public String getAll_ip() {
		return all_ip;
	}

	public void setAll_ip(String all_ip) {
		this.all_ip = all_ip;
	}

	public String getUse_ip() {
		return use_ip;
	}

	public void setUse_ip(String use_ip) {
		this.use_ip = use_ip;
	}

	public String getAdvance_ip() {
		return advance_ip;
	}

	public void setAdvance_ip(String advance_ip) {
		this.advance_ip = advance_ip;
	}

	public String getUnuse_ip() {
		return unuse_ip;
	}

	public void setUnuse_ip(String unuse_ip) {
		this.unuse_ip = unuse_ip;
	}
	public String getSelectTime() {
		return selectTime;
	}

	public void setSelectTime(String selectTime) {
		this.selectTime = selectTime;
	}

	public int getEq_type() {
		return eq_type;
	}

	public void setEq_type(int eq_type) {
		this.eq_type = eq_type;
	}

	public String getAll_cpu() {
		return all_cpu;
	}

	public void setAll_cpu(String all_cpu) {
		this.all_cpu = all_cpu;
	}

	public String getUse_cpu() {
		return use_cpu;
	}

	public void setUse_cpu(String use_cpu) {
		this.use_cpu = use_cpu;
	}

	public String getAdvance_cpu() {
		return advance_cpu;
	}

	public void setAdvance_cpu(String advance_cpu) {
		this.advance_cpu = advance_cpu;
	}

	public String getUnuse_cpu() {
		return unuse_cpu;
	}

	public void setUnuse_cpu(String unuse_cpu) {
		this.unuse_cpu = unuse_cpu;
	}

	public String getAll_mem() {
		return all_mem;
	}

	public Timestamp getCheck_date() {
		return check_date;
	}

	public void setCheck_date(Timestamp check_date) {
		this.check_date = check_date;
	}

	public void setAll_mem(String all_mem) {
		this.all_mem = all_mem;
	}

	public String getUse_mem() {
		return use_mem;
	}

	public void setUse_mem(String use_mem) {
		this.use_mem = use_mem;
	}

	public String getAdvance_mem() {
		return advance_mem;
	}

	public void setAdvance_mem(String advance_mem) {
		this.advance_mem = advance_mem;
	}

	public String getUnuse_mem() {
		return unuse_mem;
	}

	public void setUnuse_mem(String unuse_mem) {
		this.unuse_mem = unuse_mem;
	}

	public String getAll_storage() {
		return all_storage;
	}

	public void setAll_storage(String all_storage) {
		this.all_storage = all_storage;
	}

	public String getUse_storage() {
		return use_storage;
	}

	public void setUse_storage(String use_storage) {
		this.use_storage = use_storage;
	}

	public String getAdvance_storage() {
		return advance_storage;
	}

	public void setAdvance_storage(String advance_storage) {
		this.advance_storage = advance_storage;
	}

	public String getUnuse_storage() {
		return unuse_storage;
	}

	public void setUnuse_storage(String unuse_storage) {
		this.unuse_storage = unuse_storage;
	}

	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}

	public int getProportion() {
		return proportion;
	}

	public void setProportion(int proportion) {
		this.proportion = proportion;
	}

}
