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
public class PublishFeedBackTypeForm {
	private String TYPE_ID;
	private String[] TYPE_IDS;
	private String TYPE_NAME;
	private String TYPE_DESC;
	private String ENABLE;
	private List resultList;
	private String ISMP_JSP_COMMAND;
	private String TYPE_CODE;

	public String getTYPE_CODE() {
		return TYPE_CODE;
	}

	public void setTYPE_CODE(String typeCode) {
		this.TYPE_CODE = typeCode;
	}

	public String getISMP_JSP_COMMAND() {
		return ISMP_JSP_COMMAND;
	}

	public void setISMP_JSP_COMMAND(String ismp_jsp_command) {
		ISMP_JSP_COMMAND = ismp_jsp_command;
	}

	public String getENABLE() {
		return ENABLE;
	}

	public void setENABLE(String enable) {
		ENABLE = enable;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getTYPE_DESC() {
		return TYPE_DESC;
	}

	public void setTYPE_DESC(String type_desc) {
		TYPE_DESC = type_desc;
	}

	public String getTYPE_ID() {
		return TYPE_ID;
	}

	public void setTYPE_ID(String type_id) {
		TYPE_ID = type_id;
	}

	public String getTYPE_NAME() {
		return TYPE_NAME;
	}

	public void setTYPE_NAME(String type_name) {
		TYPE_NAME = type_name;
	}

	public String[] getTYPE_IDS() {
		return TYPE_IDS;
	}

	public void setTYPE_IDS(String[] type_ids) {
		TYPE_IDS = type_ids;
	}

}