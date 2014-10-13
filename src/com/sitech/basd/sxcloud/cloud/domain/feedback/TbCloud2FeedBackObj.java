package com.sitech.basd.sxcloud.cloud.domain.feedback;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 反馈obj
 * 
 * @author wangzca
 * @Date 2011.12.22
 */
@SuppressWarnings("serial")
public class TbCloud2FeedBackObj extends BaseObj implements Serializable,
		Cloneable {

	private String ID = null; // 文件ID

	private String SUBMIT_TIME = null; // 文件名

	private String DF_INFO = null;// 文件路径

	private String SENTTO_EMPLOYE = null; // 类型ID

	private String LOGIN_ID = null; // 文件描述

	private String IF_AFFIRM = null;

	private String TITLE = null; // 目录ID

	private String TYPE_ID = null; // 插入者

	private String ENABLE = null; // 插入时间

	private String TYPE = null; // 更新者

	private String HF_INFO = null; // 更新时间
	private String HF_TIME;

	public String getHF_TIME() {
		return HF_TIME;
	}

	public void setHF_TIME(String hf_time) {
		HF_TIME = hf_time;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public String getSUBMIT_TIME() {
		return SUBMIT_TIME;
	}

	public void setSUBMIT_TIME(String submit_time) {
		SUBMIT_TIME = submit_time;
	}

	public String getDF_INFO() {
		return DF_INFO;
	}

	public void setDF_INFO(String df_info) {
		DF_INFO = df_info;
	}

	public String getSENTTO_EMPLOYE() {
		return SENTTO_EMPLOYE;
	}

	public void setSENTTO_EMPLOYE(String sentto_employe) {
		SENTTO_EMPLOYE = sentto_employe;
	}

	public String getLOGIN_ID() {
		return LOGIN_ID;
	}

	public void setLOGIN_ID(String login_id) {
		LOGIN_ID = login_id;
	}

	public String getIF_AFFIRM() {
		return IF_AFFIRM;
	}

	public void setIF_AFFIRM(String if_affirm) {
		IF_AFFIRM = if_affirm;
	}

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String title) {
		TITLE = title;
	}

	public String getTYPE_ID() {
		return TYPE_ID;
	}

	public void setTYPE_ID(String type_id) {
		TYPE_ID = type_id;
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

}
