package com.sitech.basd.yicloud.web.storage.form;

import java.util.List;


/**
 * 
 * Title: TbYicloudBusinessAppObj Description: 存储类 Copyright: Copyright (c)2012
 * Company: SI-TECH
 * 
 * @author taoxue
 * @version 1.0
 */
public class TbYicloudAppStorageForm {
	private int ID;
	private int APP_ID;// 业务应用ID
	private int BIND_DEVICE;// 绑定设备主机ID
	private String USER_NAME;// 用户
	private String USER_ID;// 用户ID
	private String GROUP_ID;// 组ID
	private String FILE_PATH;// 文件系统
	private String STORAGE_SIZE;// 存储大小
	private String AVAILABLE;// 剩余存储
	private String REMARK;// 备注
	private int SELECT_APP_ID;// 所选业务应用ID
	private int SELECT_BIND_DEVICE;// 所选绑定设备主机ID
	private int flag;// 判断是插入还是修改
	private List appIdList;// 业务应用Id列表
	private List bindDeviceList;// 绑定主机设备ID
	private List resultList;// 结果列表

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getSELECT_APP_ID() {
		return SELECT_APP_ID;
	}

	public void setSELECT_APP_ID(int select_app_id) {
		SELECT_APP_ID = select_app_id;
	}

	public int getSELECT_BIND_DEVICE() {
		return SELECT_BIND_DEVICE;
	}

	public void setSELECT_BIND_DEVICE(int select_bind_device) {
		SELECT_BIND_DEVICE = select_bind_device;
	}

	public List getAppIdList() {
		return appIdList;
	}

	public void setAppIdList(List appIdList) {
		this.appIdList = appIdList;
	}

	public List getBindDeviceList() {
		return bindDeviceList;
	}

	public void setBindDeviceList(List bindDeviceList) {
		this.bindDeviceList = bindDeviceList;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public int getAPP_ID() {
		return APP_ID;
	}

	public void setAPP_ID(int app_id) {
		APP_ID = app_id;
	}

	public int getBIND_DEVICE() {
		return BIND_DEVICE;
	}

	public void setBIND_DEVICE(int bind_device) {
		BIND_DEVICE = bind_device;
	}

	public String getUSER_NAME() {
		return USER_NAME;
	}

	public void setUSER_NAME(String user_name) {
		USER_NAME = user_name;
	}

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String user_id) {
		USER_ID = user_id;
	}

	public String getGROUP_ID() {
		return GROUP_ID;
	}

	public void setGROUP_ID(String group_id) {
		GROUP_ID = group_id;
	}

	public String getFILE_PATH() {
		return FILE_PATH;
	}

	public void setFILE_PATH(String file_path) {
		FILE_PATH = file_path;
	}

	public String getSTORAGE_SIZE() {
		return STORAGE_SIZE;
	}

	public void setSTORAGE_SIZE(String storage_size) {
		STORAGE_SIZE = storage_size;
	}

	public String getAVAILABLE() {
		return AVAILABLE;
	}

	public void setAVAILABLE(String available) {
		AVAILABLE = available;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String remark) {
		REMARK = remark;
	}

}
