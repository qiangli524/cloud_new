/*
 * Created on 2006-4-5
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.sitech.basd.sxcloud.cloud.web.feedback.form;

import java.util.List;

/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class PublishFeedBackForm {
	public String ID;
	public String[] IDS;
	public String SUBMIT_TIME;
	public String TYPE_ID;
	public String TYPE_NAME;
	public List TYPE_LIST;
	public String TITLE;
	public String DF_INFO;
	public String SENTTO_EMPLOYE;
	public String LOGIN_ID;
	public String IF_AFFIRM;
	public String ENABLE;
	public String TYPE;
	public List resultList;
	public String selectTimeFrom;
	public String selectTimeTo;
	public String HF_INFO;
	public String HF_TIME;
	private String GROUP_ID;
	private List GROUP_LIST;
	private List USER_LIST;

	public List getUSER_LIST() {
		return USER_LIST;
	}

	public void setUSER_LIST(List user_list) {
		USER_LIST = user_list;
	}

	public String getHF_TIME() {
		return HF_TIME;
	}

	public void setHF_TIME(String hf_time) {
		HF_TIME = hf_time;
	}

	public String getTYPE_NAME() {
		return TYPE_NAME;
	}

	public void setTYPE_NAME(String type_name) {
		TYPE_NAME = type_name;
	}

	public List getTYPE_LIST() {
		return TYPE_LIST;
	}

	public void setTYPE_LIST(List type_list) {
		TYPE_LIST = type_list;
	}

	public String getGROUP_ID() {
		return GROUP_ID;
	}

	public void setGROUP_ID(String group_id) {
		GROUP_ID = group_id;
	}

	public List getGROUP_LIST() {
		return GROUP_LIST;
	}

	public void setGROUP_LIST(List group_list) {
		GROUP_LIST = group_list;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getDF_INFO() {
		return DF_INFO;
	}

	public void setDF_INFO(String df_info) {
		DF_INFO = df_info;
	}

	public String getLOGIN_ID() {
		return LOGIN_ID;
	}

	public void setLOGIN_ID(String login_id) {
		LOGIN_ID = login_id;
	}

	public String getSENTTO_EMPLOYE() {
		return SENTTO_EMPLOYE;
	}

	public void setSENTTO_EMPLOYE(String sentto_employe) {
		SENTTO_EMPLOYE = sentto_employe;
	}

	public String getSUBMIT_TIME() {
		return SUBMIT_TIME;
	}

	public void setSUBMIT_TIME(String submit_time) {
		SUBMIT_TIME = submit_time;
	}

	public String getTYPE_ID() {
		return TYPE_ID;
	}

	public void setTYPE_ID(String type_id) {
		TYPE_ID = type_id;
	}

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String title) {
		TITLE = title;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public String[] getIDS() {
		return IDS;
	}

	public void setIDS(String[] ids) {
		IDS = ids;
	}

	public String getIF_AFFIRM() {
		return IF_AFFIRM;
	}

	public void setIF_AFFIRM(String if_affirm) {
		IF_AFFIRM = if_affirm;
	}

	public String getSelectTimeFrom() {
		return selectTimeFrom;
	}

	public void setSelectTimeFrom(String selectTimeFrom) {
		this.selectTimeFrom = selectTimeFrom;
	}

	public String getSelectTimeTo() {
		return selectTimeTo;
	}

	public void setSelectTimeTo(String selectTimeTo) {
		this.selectTimeTo = selectTimeTo;
	}

	public String getENABLE() {
		return ENABLE;
	}

	public void setENABLE(String enable) {
		ENABLE = enable;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String type) {
		TYPE = type;
	}

	public String getHF_INFO() {
		return HF_INFO;
	}

	public void setHF_INFO(String hf_info) {
		HF_INFO = hf_info;
	}

	public void reset() {
		this.TITLE = "";
		this.IF_AFFIRM = "";
	}

}