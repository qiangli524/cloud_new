package com.sitech.basd.sxcloud.cloud.domain.fileManager;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 文件目录表
 * 
 * @author wangzca
 * @Date 2011.12.16
 */
@SuppressWarnings("serial")
public class TbCloud2FileListInfoObj extends BaseObj implements Serializable,
		Cloneable {

	private String LIST_ID = null; // 文件目录ID

	private String LIST_NAME = null; // 文件目录名

	private String AUTH_CODE = null;// 权限代码

	private String ENABLE = null; // 是否可用

	private String INSERT_USR = null; // 插入者

	private String INSERT_DATE = null; // 插入时间

	private String UPDATE_USR = null; // 更新者

	private String UPDATE_DATE = null; // 更新时间

	private String SI_ID = null; // 与文件类型表的关联ID

	public String getLIST_ID() {
		return LIST_ID;
	}

	public void setLIST_ID(String list_id) {
		LIST_ID = list_id;
	}

	public String getLIST_NAME() {
		return LIST_NAME;
	}

	public void setLIST_NAME(String list_name) {
		LIST_NAME = list_name;
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

	public String getSI_ID() {
		return SI_ID;
	}

	public void setSI_ID(String si_id) {
		SI_ID = si_id;
	}

}
