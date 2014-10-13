package com.sitech.basd.sxcloud.cloud.domain.fileManager;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 文件表
 * 
 * @author wangzca
 * @Date 2011.12.16
 */
@SuppressWarnings("serial")
public class TbCloud2FileInfoObj extends BaseObj implements Serializable,
		Cloneable {

	private String FILE_ID = null; // 文件ID

	private String FILE_NAME = null; // 文件名

	private String FILE_DIR = null;// 文件路径

	private String TYPE_ID = null; // 类型ID

	private String FILE_DESC = null; // 文件描述

	private String AUTH_CODE = null;

	private String LIST_ID = null; // 目录ID

	private String INSERT_USR = null; // 插入者

	private String INSERT_DATE = null; // 插入时间

	private String UPDATE_USR = null; // 更新者

	private String UPDATE_DATE = null; // 更新时间

	private String VERSION = null; // 文件版本号

	public String getFILE_ID() {
		return FILE_ID;
	}

	public void setFILE_ID(String file_id) {
		FILE_ID = file_id;
	}

	public String getFILE_NAME() {
		return FILE_NAME;
	}

	public void setFILE_NAME(String file_name) {
		FILE_NAME = file_name;
	}

	public String getFILE_DIR() {
		return FILE_DIR;
	}

	public void setFILE_DIR(String file_dir) {
		FILE_DIR = file_dir;
	}

	public String getTYPE_ID() {
		return TYPE_ID;
	}

	public void setTYPE_ID(String type_id) {
		TYPE_ID = type_id;
	}

	public String getFILE_DESC() {
		return FILE_DESC;
	}

	public void setFILE_DESC(String file_desc) {
		FILE_DESC = file_desc;
	}

	public String getAUTH_CODE() {
		return AUTH_CODE;
	}

	public void setAUTH_CODE(String auth_code) {
		AUTH_CODE = auth_code;
	}

	public String getLIST_ID() {
		return LIST_ID;
	}

	public void setLIST_ID(String list_id) {
		LIST_ID = list_id;
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

	public String getVERSION() {
		return VERSION;
	}

	public void setVERSION(String version) {
		VERSION = version;
	}

}
