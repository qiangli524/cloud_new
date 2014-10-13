package com.sitech.basd.sxcloud.cloud.domain.resource;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class TbCloud2HostHisObj extends BaseObj {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String eq_id = null;

	private String eq_name = null;
	private String eq_type = null;
	private String eq_ip = null;
	private String eq_hostname = null;
	private String eq_temperature = null;
	private String hasvertual = null;

	private String cq_id = null;
	private String cq_name = null;
	private int operation = 0; // 0:删除，1修改
	private String ins_date = null;

	private String start_date = null;

	private String end_date = null;

	private int control = 0; // 云平台能否管控 0 不管控、1 管控

	public int getControl() {
		return control;
	}

	public void setControl(int control) {
		this.control = control;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getIns_date() {
		return ins_date;
	}

	public void setIns_date(String ins_date) {
		this.ins_date = ins_date;
	}

	public String getCq_id() {
		return cq_id;
	}

	public void setCq_id(String cq_id) {
		this.cq_id = cq_id;
	}

	public String getEq_hostname() {
		return eq_hostname;
	}

	public void setEq_hostname(String eq_hostname) {
		this.eq_hostname = eq_hostname;
	}

	public String getEq_id() {
		return eq_id;
	}

	public void setEq_id(String eq_id) {
		this.eq_id = eq_id;
	}

	public String getEq_ip() {
		return eq_ip;
	}

	public void setEq_ip(String eq_ip) {
		this.eq_ip = eq_ip;
	}

	public String getEq_name() {
		return eq_name;
	}

	public void setEq_name(String eq_name) {
		this.eq_name = eq_name;
	}

	public String getEq_temperature() {
		return eq_temperature;
	}

	public void setEq_temperature(String eq_temperature) {
		this.eq_temperature = eq_temperature;
	}

	public String getEq_type() {
		return eq_type;
	}

	public void setEq_type(String eq_type) {
		this.eq_type = eq_type;
	}

	public String getHasvertual() {
		return hasvertual;
	}

	public void setHasvertual(String hasvertual) {
		this.hasvertual = hasvertual;
	}

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}

	public String getCq_name() {
		return cq_name;
	}

	public void setCq_name(String cq_name) {
		this.cq_name = cq_name;
	}

}
