package com.sitech.basd.yicloud.domain.vmman;

public class TbYicloudDeviceHealthObj {
	/** 状态ID */
	private int ID;
	/** 设备ID */
	private String DEVICE_ID;
	/** IP是否阻塞 */
	private String IP_ISBLOCKED;
	/** 端口是否阻塞 */
	private String PORT_ISBLOCKED;
	/** 设备CPU */
	private String CPU;
	/** 设备内存 */
	private String MEM;
	/** 设备IO读写 */
	private String IO;
	/** 设备存储 */
	private String STORAGE;
	/** 应用是否down */
	private String APP_HASDOWN;
	/** 中间件是否down */
	private String MIDDLE_HASDOWN;
	/** down状态的应用名称 */
	private String APP_DOWN_NAMES;
	/** down状态的中间件名称 */
	private String MIDDLE_DOWN_NAMES;

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getDEVICE_ID() {
		return DEVICE_ID;
	}

	public void setDEVICE_ID(String device_id) {
		DEVICE_ID = device_id;
	}

	public String getIP_ISBLOCKED() {
		return IP_ISBLOCKED;
	}

	public void setIP_ISBLOCKED(String ip_isblocked) {
		IP_ISBLOCKED = ip_isblocked;
	}

	public String getPORT_ISBLOCKED() {
		return PORT_ISBLOCKED;
	}

	public void setPORT_ISBLOCKED(String port_isblocked) {
		PORT_ISBLOCKED = port_isblocked;
	}

	public String getCPU() {
		return CPU;
	}

	public void setCPU(String cpu) {
		CPU = cpu;
	}

	public String getMEM() {
		return MEM;
	}

	public void setMEM(String mem) {
		MEM = mem;
	}

	public String getIO() {
		return IO;
	}

	public void setIO(String io) {
		IO = io;
	}

	public String getSTORAGE() {
		return STORAGE;
	}

	public void setSTORAGE(String storage) {
		STORAGE = storage;
	}

	public String getAPP_HASDOWN() {
		return APP_HASDOWN;
	}

	public void setAPP_HASDOWN(String app_hasdown) {
		APP_HASDOWN = app_hasdown;
	}

	public String getMIDDLE_HASDOWN() {
		return MIDDLE_HASDOWN;
	}

	public void setMIDDLE_HASDOWN(String middle_hasdown) {
		MIDDLE_HASDOWN = middle_hasdown;
	}

	public String getAPP_DOWN_NAMES() {
		return APP_DOWN_NAMES;
	}

	public void setAPP_DOWN_NAMES(String app_down_names) {
		APP_DOWN_NAMES = app_down_names;
	}

	public String getMIDDLE_DOWN_NAMES() {
		return MIDDLE_DOWN_NAMES;
	}

	public void setMIDDLE_DOWN_NAMES(String middle_down_names) {
		MIDDLE_DOWN_NAMES = middle_down_names;
	}

}
