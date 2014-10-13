package com.sitech.basd.sxcloud.cloud.domain.appmessage;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbCloudAppInfoObj extends BaseObj implements Serializable,
		Cloneable {
	private int id;// 应用id，唯一标识
	private String sysName;// 业务系统名称
	private String name;// 应用名称
	private String app_desc;// 应用描述
	private String sys_id;// 业务系统id
	private int soft_id;// 软件包id
	private String base_id;// 基准应用Id
	private int database_id;// 数据库Id

	/** 基准应用信息 */
	private String base_name;// 基准应用名称
	private int status;// 1：已注册，2：部署，3：已部署，4：注销（卸载），5：已注销（已卸载），6：升级，7：已升级
	private int type;// 1：集中式升级，2：分布式升级
	private String remark;// 备注
	private String deploypath;// 部署路径
	private String basepath;// 基准主机 部署应用路径
	private String operationname;// 业务名称
	private int stategy_type;
	private String hostusername;

	public String getHostusername() {
		return hostusername;
	}

	public void setHostusername(String hostusername) {
		this.hostusername = hostusername;
	}

	public int getStategy_type() {
		return stategy_type;
	}

	public void setStategy_type(int stategy_type) {
		this.stategy_type = stategy_type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSys_id() {
		return sys_id;
	}

	public void setSys_id(String sys_id) {
		this.sys_id = sys_id;
	}

	public int getSoft_id() {
		return soft_id;
	}

	public void setSoft_id(int soft_id) {
		this.soft_id = soft_id;
	}

	public String getBase_id() {
		return base_id;
	}

	public void setBase_id(String base_id) {
		this.base_id = base_id;
	}

	public int getDatabase_id() {
		return database_id;
	}

	public void setDatabase_id(int database_id) {
		this.database_id = database_id;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getApp_desc() {
		return app_desc;
	}

	public void setApp_desc(String app_desc) {
		this.app_desc = app_desc;
	}

	public String getBase_name() {
		return base_name;
	}

	public void setBase_name(String base_name) {
		this.base_name = base_name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDeploypath() {
		return deploypath;
	}

	public void setDeploypath(String deploypath) {
		this.deploypath = deploypath;
	}

	public String getBasepath() {
		return basepath;
	}

	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}

	public String getOperationname() {
		return operationname;
	}

	public void setOperationname(String operationname) {
		this.operationname = operationname;
	}
}
