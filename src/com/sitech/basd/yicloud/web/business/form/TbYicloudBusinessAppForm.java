package com.sitech.basd.yicloud.web.business.form;

import java.util.List;

/**
 * 
 * Title: TbYicloudBusinessAppObj Description: 应用服务类 Copyright: Copyright
 * (c)2012 Company: SI-TECH
 * 
 * @author taoxue
 * @version 1.0
 */
public class TbYicloudBusinessAppForm{
	private int ID;// 序号
	private String NAME;// 业务应用名称
	private String TYPE;// 业务类型(业务/子业务应用)
	private int UPPER_ID;// 所属上级业务
	private int BIND_DEVICE;// 绑定主机ID
	private String REMARK;// 备注
	private List resultList;// 结果列表
	private List businessTypeList;// 业务类型列表
	private List upperList;// 所属上级列表
	private List bineDeviceList;// 绑定设备主机ID列表
	private int flag;// 判断插入还是更新
	private String SELECT_TYPE;// 所选择的业务类型
	private int SELECT_UPPER_ID;// 所选上级业务
	private int SELECT_BIND_DEVICE;// 所选绑定设备主机ID

	public String getSELECT_TYPE() {
		return SELECT_TYPE;
	}

	public void setSELECT_TYPE(String select_type) {
		SELECT_TYPE = select_type;
	}

	public int getSELECT_UPPER_ID() {
		return SELECT_UPPER_ID;
	}

	public void setSELECT_UPPER_ID(int select_upper_id) {
		SELECT_UPPER_ID = select_upper_id;
	}

	public int getSELECT_BIND_DEVICE() {
		return SELECT_BIND_DEVICE;
	}

	public void setSELECT_BIND_DEVICE(int select_bind_device) {
		SELECT_BIND_DEVICE = select_bind_device;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public List getBusinessTypeList() {
		return businessTypeList;
	}

	public void setBusinessTypeList(List businessTypeList) {
		this.businessTypeList = businessTypeList;
	}

	public List getUpperList() {
		return upperList;
	}

	public void setUpperList(List upperList) {
		this.upperList = upperList;
	}

	public List getBineDeviceList() {
		return bineDeviceList;
	}

	public void setBineDeviceList(List bineDeviceList) {
		this.bineDeviceList = bineDeviceList;
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
