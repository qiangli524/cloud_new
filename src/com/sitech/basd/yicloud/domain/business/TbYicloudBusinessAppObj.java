package com.sitech.basd.yicloud.domain.business;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * Title: TbYicloudBusinessAppObj Description: 应用服务类 Copyright: Copyright
 * (c)2012 Company: SI-TECH
 * 
 * @author taoxue
 * @version 1.0
 */
public class TbYicloudBusinessAppObj extends BaseObj {
	private int ID;// 序号
	private String NAME;// 业务应用名称
	private String TYPE;// 业务类型(业务/子业务应用)
	private int UPPER_ID;// 所属上级业务
	private int BIND_DEVICE;// 绑定主机ID
	private String REMARK;// 备注

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String type) {
		TYPE = type;
	}

	public int getUPPER_ID() {
		return UPPER_ID;
	}

	public void setUPPER_ID(int upper_id) {
		UPPER_ID = upper_id;
	}

	public int getBIND_DEVICE() {
		return BIND_DEVICE;
	}

	public void setBIND_DEVICE(int bind_device) {
		BIND_DEVICE = bind_device;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String remark) {
		REMARK = remark;
	}

}
