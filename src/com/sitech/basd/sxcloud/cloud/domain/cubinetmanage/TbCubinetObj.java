package com.sitech.basd.sxcloud.cloud.domain.cubinetmanage;

import java.io.Serializable;

import com.sitech.basd.common.domain.BasePrivilegeObj;

@SuppressWarnings("serial")
public class TbCubinetObj extends BasePrivilegeObj implements Serializable, Cloneable {
	private String c_id; // 机柜编号
	private String c_name; // 机柜名称
	private String c_addr; // 机柜位置
	private String r_id; // 机柜所在房间编号
	private String r_name; // 机柜所在房间名称
	private String e_nums; // 机柜机位总数
	private String e_use; // 机柜已使用数量
	private String ins_date; // 登记日期
	private String c_desc; // 机柜描述
	private Integer c_type; // 1代表IBM
	// Power,2代表IBM小刀,3代表HP小刀,4代表磁带库,5代表网络设备,0其他设备
	private int canDelete; // 机柜里是否有主机

	public String getC_addr() {
		return c_addr;
	}

	public void setC_addr(String c_addr) {
		this.c_addr = c_addr;
	}

	public String getC_desc() {
		return c_desc;
	}

	public void setC_desc(String c_desc) {
		this.c_desc = c_desc;
	}

	public String getC_id() {
		return c_id;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public Integer getC_type() {
		return c_type;
	}

	public void setC_type(Integer c_type) {
		this.c_type = c_type;
	}

	public String getE_nums() {
		return e_nums;
	}

	public void setE_nums(String e_nums) {
		this.e_nums = e_nums;
	}

	public String getE_use() {
		return e_use;
	}

	public void setE_use(String e_use) {
		this.e_use = e_use;
	}

	public String getIns_date() {
		return ins_date;
	}

	public void setIns_date(String ins_date) {
		this.ins_date = ins_date;
	}

	public String getR_id() {
		return r_id;
	}

	public void setR_id(String r_id) {
		this.r_id = r_id;
	}

	public String getR_name() {
		return r_name;
	}

	public void setR_name(String r_name) {
		this.r_name = r_name;
	}

	public int getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(int canDelete) {
		this.canDelete = canDelete;
	}

}
