package com.sitech.basd.sxcloud.workflow.domain.demandManage;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class DemandManageObj extends BaseObj implements Serializable, Cloneable {
	private int ID;

	private String DEMAND_NUMBERS;

	private String DEMAND_SPONSOR;

	private String RECEIVE_DATE;

	private String SPONSOR_PHONE;

	private String DEMAND_LEADER;

	private String TEST_LEADER;

	private String DEMAND_SOURCE;

	private String OA_NUMBER;

	private String COMPLETE_TIME;

	private String DEMAND_TYPE;

	private String OA_NAME;

	private String DEMAND_NAME;

	private String DEMAND_CONTENT;

	private String DEMAND_FILE_PATH;

	public String getCOMPLETE_TIME() {
		return COMPLETE_TIME;
	}

	public void setCOMPLETE_TIME(String complete_time) {
		COMPLETE_TIME = complete_time;
	}

	public String getDEMAND_CONTENT() {
		return DEMAND_CONTENT;
	}

	public void setDEMAND_CONTENT(String demand_content) {
		DEMAND_CONTENT = demand_content;
	}

	public String getDEMAND_FILE_PATH() {
		return DEMAND_FILE_PATH;
	}

	public void setDEMAND_FILE_PATH(String demand_file_path) {
		DEMAND_FILE_PATH = demand_file_path;
	}

	public String getDEMAND_LEADER() {
		return DEMAND_LEADER;
	}

	public void setDEMAND_LEADER(String demand_leader) {
		DEMAND_LEADER = demand_leader;
	}

	public String getDEMAND_NAME() {
		return DEMAND_NAME;
	}

	public void setDEMAND_NAME(String demand_name) {
		DEMAND_NAME = demand_name;
	}

	public String getDEMAND_NUMBERS() {
		return DEMAND_NUMBERS;
	}

	public void setDEMAND_NUMBERS(String demand_numbers) {
		DEMAND_NUMBERS = demand_numbers;
	}

	public String getDEMAND_SOURCE() {
		return DEMAND_SOURCE;
	}

	public void setDEMAND_SOURCE(String demand_source) {
		DEMAND_SOURCE = demand_source;
	}

	public String getDEMAND_SPONSOR() {
		return DEMAND_SPONSOR;
	}

	public void setDEMAND_SPONSOR(String demand_sponsor) {
		DEMAND_SPONSOR = demand_sponsor;
	}

	public String getDEMAND_TYPE() {
		return DEMAND_TYPE;
	}

	public void setDEMAND_TYPE(String demand_type) {
		DEMAND_TYPE = demand_type;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getOA_NAME() {
		return OA_NAME;
	}

	public void setOA_NAME(String oa_name) {
		OA_NAME = oa_name;
	}

	public String getOA_NUMBER() {
		return OA_NUMBER;
	}

	public void setOA_NUMBER(String oa_number) {
		OA_NUMBER = oa_number;
	}

	public String getRECEIVE_DATE() {
		return RECEIVE_DATE;
	}

	public void setRECEIVE_DATE(String receive_date) {
		RECEIVE_DATE = receive_date;
	}

	public String getSPONSOR_PHONE() {
		return SPONSOR_PHONE;
	}

	public void setSPONSOR_PHONE(String sponsor_phone) {
		SPONSOR_PHONE = sponsor_phone;
	}

	public String getTEST_LEADER() {
		return TEST_LEADER;
	}

	public void setTEST_LEADER(String test_leader) {
		TEST_LEADER = test_leader;
	}
}
