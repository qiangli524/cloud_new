package com.sitech.basd.sxcloud.cloud.domain.leader;

/**
 * 房间信息
 * 
 * @author zhangwj
 * @date 2011.12.1
 */
public class TbCloud2RoomInfoObj {

	private String R_ID = null; // 房间编号

	private String R_NAME = null; // 房间名称

	private String CASE_NUM = null;// 机柜数量

	private String F_ID = null; // 所在楼层编号

	private int HUMIDITY; 		//机房湿度
 
 	private int TEMPERATURE;    //机房温度

	private int M_TOTAL;        //机房设备总是

	private int M_USED;         //机房设备使用数
	
	private int DECTECTION;     //机房温度监控 0未监控、1监控

	private String M_ID;        //机房编号
	
	public String getM_ID() {
		return M_ID;
	}

	public void setM_ID(String m_id) {
		M_ID = m_id;
	}

	public int getDECTECTION() {
		return DECTECTION;
	}

	public void setDECTECTION(int dectection) {
		DECTECTION = dectection;
	}

	public int getHUMIDITY() {
		return HUMIDITY;
	}

	public void setHUMIDITY(int humidity) {
		HUMIDITY = humidity;
	}

	public int getM_TOTAL() {
		return M_TOTAL;
	}

	public void setM_TOTAL(int m_total) {
		M_TOTAL = m_total;
	}

	public int getM_USED() {
		return M_USED;
	}

	public void setM_USED(int m_used) {
		M_USED = m_used;
	}

	public int getTEMPERATURE() {
		return TEMPERATURE;
	}

	public void setTEMPERATURE(int temperature) {
		TEMPERATURE = temperature;
	}

	public String getCASE_NUM() {
		return CASE_NUM;
	}

	public void setCASE_NUM(String case_num) {
		CASE_NUM = case_num;
	}

	public String getF_ID() {
		return F_ID;
	}

	public void setF_ID(String f_id) {
		F_ID = f_id;
	}

	public String getR_ID() {
		return R_ID;
	}

	public void setR_ID(String r_id) {
		R_ID = r_id;
	}

	public String getR_NAME() {
		return R_NAME;
	}

	public void setR_NAME(String r_name) {
		R_NAME = r_name;
	}

}
