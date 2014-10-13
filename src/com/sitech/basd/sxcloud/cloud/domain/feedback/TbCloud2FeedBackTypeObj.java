package com.sitech.basd.sxcloud.cloud.domain.feedback;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 反馈类型实例
 * 
 * @author wangzca
 * @Date 2011.12.22
 */
@SuppressWarnings("serial")
public class TbCloud2FeedBackTypeObj extends BaseObj implements Serializable,
		Cloneable {

	private String TYPE_ID = null; // 反馈类型ID

	private String TYPE_NAME = null; // 反馈类型名称

	private String TYPE_DESC = null;// 反馈类型描述

	private String ENABLE = null; // 反馈类型是否可用

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

	public String getTYPE_DESC() {
		return TYPE_DESC;
	}

	public void setTYPE_DESC(String type_desc) {
		TYPE_DESC = type_desc;
	}

	public String getENABLE() {
		return ENABLE;
	}

	public void setENABLE(String enable) {
		ENABLE = enable;
	}

}
