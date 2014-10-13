package com.sitech.basd.sxcloud.rsmu.web.deploy.form;

import java.util.List;

@SuppressWarnings("serial")
public class OnlineHistoryForm {

	// 唯一标示UUID
	private String id;
	// 部署实例ID
	private int exampleId;
	// 上线文件路径
	private String onlinePath;
	// 是否回滚0:上线1:回滚
	private String isRollback;
	// 是否成功0:失败1:成功
	private String isSuccess;
	// 插入数据时间
	private String insertTime;
	// 上线文件路径
	private String ip;
	// 上线文件路径
	private String appname;
	// 历史list
	private List onlineHisList;

	public List getOnlineHisList() {
		return onlineHisList;
	}

	public void setOnlineHisList(List onlineHisList) {
		this.onlineHisList = onlineHisList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getExampleId() {
		return exampleId;
	}

	public void setExampleId(int exampleId) {
		this.exampleId = exampleId;
	}

	public String getOnlinePath() {
		return onlinePath;
	}

	public void setOnlinePath(String onlinePath) {
		this.onlinePath = onlinePath;
	}

	public String getIsRollback() {
		return isRollback;
	}

	public void setIsRollback(String isRollback) {
		this.isRollback = isRollback;
	}

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

}
