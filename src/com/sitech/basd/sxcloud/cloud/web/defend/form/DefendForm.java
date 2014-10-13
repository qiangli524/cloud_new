package com.sitech.basd.sxcloud.cloud.web.defend.form;

import java.util.List;

@SuppressWarnings("serial")
public class DefendForm {

	private int DEFEND_ID; // 防篡改编号
	private String DEFEND_DIR; // 受控目录
	private String HOST_ID; // 所属物理主机编号
	private String INS_DATE; // 更新时间
	private int ENABLE = -1; // 是否生效
	private int ID; // 主机表ID，查询下拉列表使用
	private String HOSTNAME; // 所属物理主机名称
	private int flag = 0; // 标志，判断是增加还是修改
	private String IP; // 主机IP地址
	private String EXCUTE_FLAG; // 执行标记0：未添加，1：已添加 ，2：正在添加
	private String DEFEND_TYPE; // 操作类型 ；0是添加操作，1是修改操作，2是删除操作
	private int TYPE; // 操作类型。1是添加操作，2是修改操作，3是删除操作
	private int APP_ID; // 应用编号
	private List appList; // 应用列表
	private String PLATFORM_ID; // 防篡改:平台ID-MAC地址
	private String PLATFORM_IP; // 防篡改:平台IP
	@SuppressWarnings("unchecked")
	private List resultList = null; // 结果列表
	@SuppressWarnings("unchecked")
	private List hphostList = null; // HP主机列表作为下拉列表显示

	public int getDEFEND_ID() {
		return DEFEND_ID;
	}

	public void setDEFEND_ID(int defend_id) {
		DEFEND_ID = defend_id;
	}

	public String getDEFEND_DIR() {
		return DEFEND_DIR;
	}

	public void setDEFEND_DIR(String defend_dir) {
		DEFEND_DIR = defend_dir;
	}

	public String getHOST_ID() {
		return HOST_ID;
	}

	public void setHOST_ID(String host_id) {
		HOST_ID = host_id;
	}

	public String getINS_DATE() {
		return INS_DATE;
	}

	public void setINS_DATE(String ins_date) {
		INS_DATE = ins_date;
	}

	public String getHOSTNAME() {
		return HOSTNAME;
	}

	public void setHOSTNAME(String hostname) {
		HOSTNAME = hostname;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@SuppressWarnings("unchecked")
	public List getResultList() {
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	@SuppressWarnings("unchecked")
	public List getHphostList() {
		return hphostList;
	}

	@SuppressWarnings("unchecked")
	public void setHphostList(List hphostList) {
		this.hphostList = hphostList;
	}

	public int getAPP_ID() {
		return APP_ID;
	}

	public void setAPP_ID(int app_id) {
		APP_ID = app_id;
	}

	public List getAppList() {
		return appList;
	}

	public void setAppList(List appList) {
		this.appList = appList;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String ip) {
		IP = ip;
	}

	public String getEXCUTE_FLAG() {
		return EXCUTE_FLAG;
	}

	public void setEXCUTE_FLAG(String excute_flag) {
		EXCUTE_FLAG = excute_flag;
	}

	public String getDEFEND_TYPE() {
		return DEFEND_TYPE;
	}

	public void setDEFEND_TYPE(String defend_type) {
		DEFEND_TYPE = defend_type;
	}

	public int getENABLE() {
		return ENABLE;
	}

	public void setENABLE(int enable) {
		ENABLE = enable;
	}

	public int getTYPE() {
		return TYPE;
	}

	public void setTYPE(int type) {
		TYPE = type;
	}

	public String getPLATFORM_ID() {
		return PLATFORM_ID;
	}

	public void setPLATFORM_ID(String platform_id) {
		PLATFORM_ID = platform_id;
	}

	public String getPLATFORM_IP() {
		return PLATFORM_IP;
	}

	public void setPLATFORM_IP(String platform_ip) {
		PLATFORM_IP = platform_ip;
	}

}
