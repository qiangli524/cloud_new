package com.sitech.basd.sxcloud.cloud.domain.image;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * @author liys
 * 
 */
@SuppressWarnings("serial")
public class TbCloud2ImageInfoObj extends BaseObj implements Serializable,
		Cloneable {
	private String IM_ID;// 映像编号
	private String IM_NAME;// 映像名称
	private String IM_DESC;// 映像描述
	private String IM_STATE;// 映像状态
	private String IM_VERSION;// 映像版本
	private String IM_SPECVERSION;// 原始版本
	private String IM_LOG;// 日志
	private String PROJECT_ID;// 所属项目编号
	private String IM_MEM;// 内存
	private String IM_CPU;// CPU
	private String IM_STORAGE;// 存储
	private String IM_SYSTEM;// 操作系统
	private String IM_IP;// IP地址
	private String IM_NETWORK;// 网络
	private String IM_MIN_CPU;// CPU最小值
	private String IM_MAX_CPU;// CPU最大值
	private String IM_MIN_MEM;// 内存最小值
	private String IM_MAX_MEM;// 内存最大值
	private String IM_ROOT_PASS;// 镜像密码
	private String IM_TYPE;// 镜像类型（IBM和KVM）
	private String TEM_ID;// 模板ID
	private String NAME_ZH;
	private int OS_ID;
	/** CPU处理单元 */
	private String IM_PROCESS_UNIT;
	/** CPU处理单元最大值 */
	private String IM_MAX_PROCESS_UNIT;
	/** CPU处理单元最小值 */
	private String IM_MIN_PROCESS_UNIT;
	/** CPU独占或共享模式 */
	private String IM_CPU_MODE;
	/** CPU处理单元独占或共享模式 */
	private String IM_PROCESS_UNIT_MODE;
	/** 虚拟机ID-IBM云-SKC返回值 */
	private String IM_ID_IBM;
	/** CPU处理单元在共享处理单元处理池权重 */
	private String IM_PROCESS_UNIT_Priority;

	public int getOS_ID() {
		return OS_ID;
	}

	public void setOS_ID(int os_id) {
		OS_ID = os_id;
	}

	public String getNAME_ZH() {
		return NAME_ZH;
	}

	public void setNAME_ZH(String name_zh) {
		NAME_ZH = name_zh;
	}

	public String getTEM_ID() {
		return TEM_ID;
	}

	public void setTEM_ID(String tem_id) {
		TEM_ID = tem_id;
	}

	public String getIM_TYPE() {
		return IM_TYPE;
	}

	public void setIM_TYPE(String im_type) {
		IM_TYPE = im_type;
	}

	public String getIM_ROOT_PASS() {
		return IM_ROOT_PASS;
	}

	public void setIM_ROOT_PASS(String iMROOTPASS) {
		IM_ROOT_PASS = iMROOTPASS;
	}

	private String CHANGED_DATE;// 修改日期

	public String getCHANGED_DATE() {
		return CHANGED_DATE;
	}

	public void setCHANGED_DATE(String cHANGEDDATE) {
		CHANGED_DATE = cHANGEDDATE;
	}

	public String getIM_MEM() {
		return IM_MEM;
	}

	public void setIM_MEM(String iMMEM) {
		IM_MEM = iMMEM;
	}

	public String getIM_CPU() {
		return IM_CPU;
	}

	public void setIM_CPU(String iMCPU) {
		IM_CPU = iMCPU;
	}

	public String getIM_STORAGE() {
		return IM_STORAGE;
	}

	public void setIM_STORAGE(String iMSTORAGE) {
		IM_STORAGE = iMSTORAGE;
	}

	public String getIM_SYSTEM() {
		return IM_SYSTEM;
	}

	public void setIM_SYSTEM(String iMSYSTEM) {
		IM_SYSTEM = iMSYSTEM;
	}

	public String getIM_IP() {
		return IM_IP;
	}

	public void setIM_IP(String iMIP) {
		IM_IP = iMIP;
	}

	public String getIM_NETWORK() {
		return IM_NETWORK;
	}

	public void setIM_NETWORK(String iMNETWORK) {
		IM_NETWORK = iMNETWORK;
	}

	public String getIM_MIN_CPU() {
		return IM_MIN_CPU;
	}

	public void setIM_MIN_CPU(String iMMINCPU) {
		IM_MIN_CPU = iMMINCPU;
	}

	public String getIM_MAX_CPU() {
		return IM_MAX_CPU;
	}

	public void setIM_MAX_CPU(String iMMAXCPU) {
		IM_MAX_CPU = iMMAXCPU;
	}

	public String getIM_MIN_MEM() {
		return IM_MIN_MEM;
	}

	public void setIM_MIN_MEM(String iMMINMEM) {
		IM_MIN_MEM = iMMINMEM;
	}

	public String getIM_MAX_MEM() {
		return IM_MAX_MEM;
	}

	public void setIM_MAX_MEM(String iMMAXMEM) {
		IM_MAX_MEM = iMMAXMEM;
	}

	public String getIM_PROCESS_UNIT() {
		return IM_PROCESS_UNIT;
	}

	public void setIM_PROCESS_UNIT(String iMPROCESSUNIT) {
		IM_PROCESS_UNIT = iMPROCESSUNIT;
	}

	public String getIM_MAX_PROCESS_UNIT() {
		return IM_MAX_PROCESS_UNIT;
	}

	public void setIM_MAX_PROCESS_UNIT(String iMMAXPROCESSUNIT) {
		IM_MAX_PROCESS_UNIT = iMMAXPROCESSUNIT;
	}

	public String getIM_MIN_PROCESS_UNIT() {
		return IM_MIN_PROCESS_UNIT;
	}

	public void setIM_MIN_PROCESS_UNIT(String iMMINPROCESSUNIT) {
		IM_MIN_PROCESS_UNIT = iMMINPROCESSUNIT;
	}

	public String getIM_CPU_MODE() {
		return IM_CPU_MODE;
	}

	public void setIM_CPU_MODE(String iMCPUMODE) {
		IM_CPU_MODE = iMCPUMODE;
	}

	public String getIM_PROCESS_UNIT_MODE() {
		return IM_PROCESS_UNIT_MODE;
	}

	public void setIM_PROCESS_UNIT_MODE(String iMPROCESSUNITMODE) {
		IM_PROCESS_UNIT_MODE = iMPROCESSUNITMODE;
	}

	public String getIM_ID_IBM() {
		return IM_ID_IBM;
	}

	public void setIM_ID_IBM(String iMIDIBM) {
		IM_ID_IBM = iMIDIBM;
	}

	public String getIM_PROCESS_UNIT_Priority() {
		return IM_PROCESS_UNIT_Priority;
	}

	public void setIM_PROCESS_UNIT_Priority(String iMPROCESSUNITPriority) {
		IM_PROCESS_UNIT_Priority = iMPROCESSUNITPriority;
	}

	public String getIM_DESC() {
		return IM_DESC;
	}

	public void setIM_DESC(String im_desc) {
		IM_DESC = im_desc;
	}

	public String getIM_ID() {
		return IM_ID;
	}

	public void setIM_ID(String im_id) {
		IM_ID = im_id;
	}

	public String getIM_LOG() {
		return IM_LOG;
	}

	public void setIM_LOG(String im_log) {
		IM_LOG = im_log;
	}

	public String getIM_NAME() {
		return IM_NAME;
	}

	public void setIM_NAME(String im_name) {
		IM_NAME = im_name;
	}

	public String getIM_SPECVERSION() {
		return IM_SPECVERSION;
	}

	public void setIM_SPECVERSION(String im_specversion) {
		IM_SPECVERSION = im_specversion;
	}

	public String getIM_STATE() {
		return IM_STATE;
	}

	public void setIM_STATE(String im_state) {
		IM_STATE = im_state;
	}

	public String getIM_VERSION() {
		return IM_VERSION;
	}

	public void setIM_VERSION(String im_version) {
		IM_VERSION = im_version;
	}

	public String getPROJECT_ID() {
		return PROJECT_ID;
	}

	public void setPROJECT_ID(String project_id) {
		PROJECT_ID = project_id;
	}

}
