package com.sitech.basd.sxcloud.cloud.web.bizsystem.form;

import java.util.List;

@SuppressWarnings("rawtypes")
public class BizSystemForm {
	private String SYS_ID; // 系统编号
	private String SYS_NAME; // 系统名称
	private String REGION_ID; // 地区编号
	private String SYS_DESC;// 描述
	private int STATU; // 状态
	private String INSERTDATE;// 插入时间
	private String UPDATEDATE;// 更新时间
	private int flag = 0; // 判断是增加还是修改的标志位
	private List resultList = null; // 查新列表
	private List regionList = null; // 地区编号列表
	private int app_num = 0;// 基准应用的个数
	private int example_num = 0;// 部署实例的个数
	private List appList;// 基准应用列表
	private List exampleList;// 部署实例列表
	private List busiCenterList;
	private String busiCenterId;

	public String getBusiCenterId() {
		return busiCenterId;
	}

	public void setBusiCenterId(String busiCenterId) {
		this.busiCenterId = busiCenterId;
	}

	public List getBusiCenterList() {
		return busiCenterList;
	}

	public void setBusiCenterList(List busiCenterList) {
		this.busiCenterList = busiCenterList;
	}

	public List getAppList() {
		return appList;
	}

	public void setAppList(List appList) {
		this.appList = appList;
	}

	public List getExampleList() {
		return exampleList;
	}

	public void setExampleList(List exampleList) {
		this.exampleList = exampleList;
	}

	public int getApp_num() {
		return app_num;
	}

	public void setApp_num(int app_num) {
		this.app_num = app_num;
	}

	public int getExample_num() {
		return example_num;
	}

	public void setExample_num(int example_num) {
		this.example_num = example_num;
	}

	public String getINSERTDATE() {
		return INSERTDATE;
	}

	public void setINSERTDATE(String insertdate) {
		INSERTDATE = insertdate;
	}

	public String getUPDATEDATE() {
		return UPDATEDATE;
	}

	public void setUPDATEDATE(String updatedate) {
		UPDATEDATE = updatedate;
	}

	public List getRegionList() {
		return regionList;
	}

	public void setRegionList(List regionList) {
		this.regionList = regionList;
	}

	public String getSYS_ID() {
		return SYS_ID;
	}

	public void setSYS_ID(String sys_id) {
		SYS_ID = sys_id;
	}

	public String getSYS_NAME() {
		return SYS_NAME;
	}

	public void setSYS_NAME(String sys_name) {
		SYS_NAME = sys_name;
	}

	public String getREGION_ID() {
		return REGION_ID;
	}

	public void setREGION_ID(String region_id) {
		REGION_ID = region_id;
	}

	public String getSYS_DESC() {
		return SYS_DESC;
	}

	public void setSYS_DESC(String sys_desc) {
		SYS_DESC = sys_desc;
	}

	public int getSTATU() {
		return STATU;
	}

	public void setSTATU(int statu) {
		STATU = statu;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
