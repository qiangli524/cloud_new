package com.sitech.basd.sxcloud.cloud.domain.fileManager;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 业务系统表
 * 
 * @author zhangwj
 * @Date 2011.12.2
 */
@SuppressWarnings("serial")
public class TbCloud2FileTypeInfoObj extends BaseObj implements Serializable,
		Cloneable {

	private String TYPE_ID = null; // 文件类型ID

	private String TYPE_NAME = null; // 文件类型名称

	private String AUTH_CODE = null;// 权限编码

	private String ENABLE = null; // 是否可用

	private String INSERT_USR = null; // 插入者

	private String INSERT_DATE = null; // 插入时间

	private String UPDATE_USR = null; // 更新者

	private String UPDATE_DATE = null; // 更新时间

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

	public String getAUTH_CODE() {
		return AUTH_CODE;
	}

	public void setAUTH_CODE(String auth_code) {
		AUTH_CODE = auth_code;
	}

	public String getENABLE() {
		return ENABLE;
	}

	public void setENABLE(String enable) {
		ENABLE = enable;
	}

	public String getINSERT_USR() {
		return INSERT_USR;
	}

	public void setINSERT_USR(String insert_usr) {
		INSERT_USR = insert_usr;
	}

	public String getINSERT_DATE() {
		return INSERT_DATE;
	}

	public void setINSERT_DATE(String insert_date) {
		INSERT_DATE = insert_date;
	}

	public String getUPDATE_USR() {
		return UPDATE_USR;
	}

	public void setUPDATE_USR(String update_usr) {
		UPDATE_USR = update_usr;
	}

	public String getUPDATE_DATE() {
		return UPDATE_DATE;
	}

	public void setUPDATE_DATE(String update_date) {
		UPDATE_DATE = update_date;
	}

}
