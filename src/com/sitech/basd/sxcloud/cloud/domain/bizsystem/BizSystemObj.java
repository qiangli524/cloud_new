package com.sitech.basd.sxcloud.cloud.domain.bizsystem;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class BizSystemObj extends BaseObj implements Serializable, Cloneable {
	private String SYS_ID; // 系统编号
	private String SYS_NAME; // 系统名称
	private String REGION_ID; // 地区编号
	private String SYS_DESC; // 描述
	private int STATU; // 状态 0：正常 1：异常告警 2：已停止
	private String INSERT_DATE;
	private String UPDATE_DATE;

	private int countApp;// 业务系统下基准应用个数
	private int countDeploy;// 业务系统下的部署实例个数

	public String getINSERT_DATE() {
		return INSERT_DATE;
	}

	public void setINSERT_DATE(String insertdate) {
		INSERT_DATE = insertdate;
	}

	public String getUPDATE_DATE() {
		return UPDATE_DATE;
	}

	public void setUPDATE_DATE(String updatedate) {
		UPDATE_DATE = updatedate;
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

	public int getCountApp() {
		return countApp;
	}

	public void setCountApp(int countApp) {
		this.countApp = countApp;
	}

	public int getCountDeploy() {
		return countDeploy;
	}

	public void setCountDeploy(int countDeploy) {
		this.countDeploy = countDeploy;
	}

}