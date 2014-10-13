package com.sitech.basd.sxcloud.cloud.domain.leader;

public class TbCloud2CubinetInfoObj {

	// private String C_ID = null; //机柜编号
	//
	// private String C_NAME = null; //机柜名称
	// private String C_ADDR = null; //机柜位置
	// private String E_NUMS = null; //机柜机位总数
	// private String E_USE = null; //机柜已使用数量
	// private String INS_DATE = null;//
	//	
	// private String R_ID = null; //机柜所在房间编号

	private String c_id = null;
	private String c_name = null;
	private String c_addr = null;
	private String c_nums = null;
	private String e_use = null;
	private String ins_date = null;
	private String r_id = null;
	private String c_desc = null;
	private Integer c_x; // 机柜横坐标
	private Integer c_y; // 机柜纵坐标

	private int c_type;

	public int getC_type() {
		return c_type;
	}

	public void setC_type(int c_type) {
		this.c_type = c_type;
	}

	public String getC_desc() {
		return c_desc;
	}

	public void setC_desc(String c_desc) {
		this.c_desc = c_desc;
	}

	public String getC_addr() {
		return c_addr;
	}

	public void setC_addr(String c_addr) {
		this.c_addr = c_addr;
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

	public String getC_nums() {
		return c_nums;
	}

	public void setC_nums(String c_nums) {
		this.c_nums = c_nums;
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

	public Integer getC_x() {
		return c_x;
	}

	public void setC_x(Integer c_x) {
		this.c_x = c_x;
	}

	public Integer getC_y() {
		return c_y;
	}

	public void setC_y(Integer c_y) {
		this.c_y = c_y;
	}
	
}
