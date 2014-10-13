package com.sitech.basd.sxcloud.cloud.web.appmessage.form;

import java.util.List;

@SuppressWarnings("serial")
public class AppMessageForm {

	private String APP_ID; // 编号
	private String APP_NAME; // 应用名称
	private String PROCESS; // 进程名
	private String MEM; // 所占内存
	private String VH_ID; // 所在主机编号
	private String SYS_ID; // 所属系统编号
	private String APP_TYPE; // 应用类型
	private String VH_NAME; // 主机名称
	private String SYS_NAME; // 创建时间
	private String PROCESS_AUX; // 进程名，通过ps aux查询进程所占的cpu，内存
	private String PROTOCOL; // 协议，0是sh，1是telnet;
	private String APP_DESC; // 描述
	private String APP_STAT; // 状态，0时down，1是up，2是异常
	private int flag = 0; // 判断增加或修改的标志位
	@SuppressWarnings("unchecked")
	private List resultList = null; // 查询列表
	@SuppressWarnings("unchecked")
	private List virtualList; // 虚拟机编号集合
	@SuppressWarnings("unchecked")
	private List sysList = null; // 系统编号集合

	public String getAPP_ID() {
		return APP_ID;
	}

	public void setAPP_ID(String app_id) {
		APP_ID = app_id;
	}

	public String getAPP_NAME() {
		return APP_NAME;
	}

	public void setAPP_NAME(String app_name) {
		APP_NAME = app_name;
	}

	public String getPROCESS() {
		return PROCESS;
	}

	public void setPROCESS(String process) {
		PROCESS = process;
	}

	public String getMEM() {
		return MEM;
	}

	public void setMEM(String mem) {
		MEM = mem;
	}

	public String getVH_ID() {
		return VH_ID;
	}

	public void setVH_ID(String vh_id) {
		VH_ID = vh_id;
	}

	public String getVH_NAME() {
		return VH_NAME;
	}

	public void setVH_NAME(String vh_name) {
		VH_NAME = vh_name;
	}

	public String getSYS_ID() {
		return SYS_ID;
	}

	public void setSYS_ID(String sys_id) {
		SYS_ID = sys_id;
	}

	public String getAPP_TYPE() {
		return APP_TYPE;
	}

	public void setAPP_TYPE(String app_type) {
		APP_TYPE = app_type;
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
	public List getSysList() {
		return sysList;
	}

	@SuppressWarnings("unchecked")
	public void setSysList(List sysList) {
		this.sysList = sysList;
	}

	@SuppressWarnings("unchecked")
	public List getVirtualList() {
		return virtualList;
	}

	@SuppressWarnings("unchecked")
	public void setVirtualList(List virtualList) {
		this.virtualList = virtualList;
	}

	public String getSYS_NAME() {
		return SYS_NAME;
	}

	public void setSYS_NAME(String sys_name) {
		SYS_NAME = sys_name;
	}

	public String getAPP_DESC() {
		return APP_DESC;
	}

	public void setAPP_DESC(String app_desc) {
		APP_DESC = app_desc;
	}

	public String getPROCESS_AUX() {
		return PROCESS_AUX;
	}

	public void setPROCESS_AUX(String process_aux) {
		PROCESS_AUX = process_aux;
	}

	public String getPROTOCOL() {
		return PROTOCOL;
	}

	public void setPROTOCOL(String protocol) {
		PROTOCOL = protocol;
	}

	public String getAPP_STAT() {
		return APP_STAT;
	}

	public void setAPP_STAT(String app_stat) {
		APP_STAT = app_stat;
	}
}
