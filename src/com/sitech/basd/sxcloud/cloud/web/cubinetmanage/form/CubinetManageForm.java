package com.sitech.basd.sxcloud.cloud.web.cubinetmanage.form;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;

@SuppressWarnings("serial")
public class CubinetManageForm {

	private String c_id; // 机柜编号

	private String c_name; // 机柜名称

	private String c_addr; // 机柜位置

	private String r_id; // 机柜所在房间编号

	private String r_name; // 机柜所在房间名称

	private String e_nums; // 机柜机位总数

	private String e_use; // 机柜已使用数量

	private String c_desc; // 机柜描述

	private String c_type; // 机柜类型

	private int canDelete; // 机柜里是否有主机

	@SuppressWarnings("unchecked")
	private List ibm_powerlist = null; // IBM_PowerList查询列表

	@SuppressWarnings("unchecked")
	private List ibm_knifelist = null; // IBM_KnifeList查询列表

	@SuppressWarnings("unchecked")
	private List hp_knifelist = null; // HP_KnifeList查询列表

	@SuppressWarnings("unchecked")
	private List tape_librarylist = null; // Tape_LibraryList查询列表

	@SuppressWarnings("unchecked")
	private List network_equlist = null; // Network_EquList查询列表

	@SuppressWarnings("unchecked")
	private List others_equlist = null; // Others_EquList查询列表

	private List roomOptionList = null; // 机柜所在机房房间列表
	private List<TbCloud2HostInfoObj> list;// 通过CQ_ID获取相应的主机列表

	public List<TbCloud2HostInfoObj> getList() {
		return list;
	}

	public void setList(List<TbCloud2HostInfoObj> list) {
		this.list = list;
	}

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

	public String getC_type() {
		return c_type;
	}

	public void setC_type(String c_type) {
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

	public List getHp_knifelist() {
		return hp_knifelist;
	}

	public int getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(int canDelete) {
		this.canDelete = canDelete;
	}

	public void setHp_knifelist(List hp_knifelist) {
		this.hp_knifelist = hp_knifelist;
	}

	public List getIbm_knifelist() {
		return ibm_knifelist;
	}

	public void setIbm_knifelist(List ibm_knifelist) {
		this.ibm_knifelist = ibm_knifelist;
	}

	public List getIbm_powerlist() {
		return ibm_powerlist;
	}

	public void setIbm_powerlist(List ibm_powerlist) {
		this.ibm_powerlist = ibm_powerlist;
	}

	public List getNetwork_equlist() {
		return network_equlist;
	}

	public void setNetwork_equlist(List network_equlist) {
		this.network_equlist = network_equlist;
	}

	public List getOthers_equlist() {
		return others_equlist;
	}

	public void setOthers_equlist(List others_equlist) {
		this.others_equlist = others_equlist;
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

	public List getTape_librarylist() {
		return tape_librarylist;
	}

	public void setTape_librarylist(List tape_librarylist) {
		this.tape_librarylist = tape_librarylist;
	}

	public List getRoomOptionList() {
		return roomOptionList;
	}

	public void setRoomOptionList(List roomOptionList) {
		this.roomOptionList = roomOptionList;
	}

}
