package com.sitech.basd.sxcloud.cloud.web.image.form;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.image.TbCloud2ImageTargetObj;

@SuppressWarnings("serial")
public class ImageForm {

	private String IM_ID;// 映像编号
	private String IM_NAME;// 映像名称
	private String IM_DESC;// 映像描述
	private String IM_STATE;// 映像状态
	private String IM_VERSION;// 映像版本
	private String IM_SPECVERSION;// 原始版本
	private String IM_LOG;// 日志
	private String PROJECT_ID;// 所属项目编号
	private String VH_CPU; // 虚拟机CPU
	private String VH_STORAGE; // 虚拟机存储单元
	private String VH_MEM; // 虚拟机内存
	private String SETTINGS; // 处理器设置
	private String MODEL; // 虚拟服务器共享处理单元模式
	private String NAME; // 网络配置名称
	private String DOMAIN; // 域名
	private String NET_ID; // 网络配置ID
	private String FREECOUNT; // 静态IP地址
	private String HOSTNAME; // 主机名
	private String GATEWAY1; // 网关
	private String SUBNET; // 子网掩码
	private String DNS1; // 主DNS
	private String DNS2; // 辅助DNS
	private String HOSTNAMESYS; // 系统主机名
	private String DOMAINSYS; // 系统域名
	private List<TbCloud2ImageTargetObj> TARGET; // 映像目标
	private String TARGETID;
	private String USER_NAME; // 用户名
	private String PASSWORD; // 用户名密码
	private String PRIORITY; // 虚拟服务器使用共享处理器池中的可用处理单元的优先级:
	private List projectList = null; // 工程下拉列表
	private List netList = null; // 网络下拉列表
	private List resultList = null; //
	/** 镜像部署后工作负载ID */
	private String workloadInfoId;
	private String VH_MIN_CPU;// CPU最小值
	private String VH_MAX_CPU;// CPU最大值
	private String VH_MIN_MEM;// 内存最小值
	private String VH_MAX_MEM;// 内存最大值
	/** CPU处理单元 */
	private String VH_PROCESS_UNIT;
	/** CPU处理单元最大值 */
	private String VH_MAX_PROCESS_UNIT;
	/** CPU处理单元最小值 */
	private String VH_MIN_PROCESS_UNIT;
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
	/** 双网卡时，第2个网卡信息 */
	private String NET_ID_DOUBLE;
	
	private String templetPath;
	

	public String getTempletPath() {
		return templetPath;
	}

	public void setTempletPath(String templetPath) {
		this.templetPath = templetPath;
	}

	public String getVH_MIN_CPU() {
		return VH_MIN_CPU;
	}

	public void setVH_MIN_CPU(String vh_min_cpu) {
		VH_MIN_CPU = vh_min_cpu;
	}

	public String getVH_MAX_CPU() {
		return VH_MAX_CPU;
	}

	public void setVH_MAX_CPU(String vh_max_cpu) {
		VH_MAX_CPU = vh_max_cpu;
	}

	public String getVH_MIN_MEM() {
		return VH_MIN_MEM;
	}

	public void setVH_MIN_MEM(String vh_min_mem) {
		VH_MIN_MEM = vh_min_mem;
	}

	public String getVH_MAX_MEM() {
		return VH_MAX_MEM;
	}

	public void setVH_MAX_MEM(String vh_max_mem) {
		VH_MAX_MEM = vh_max_mem;
	}

	public String getVH_PROCESS_UNIT() {
		return VH_PROCESS_UNIT;
	}

	public void setVH_PROCESS_UNIT(String vh_process_unit) {
		VH_PROCESS_UNIT = vh_process_unit;
	}

	public String getVH_MAX_PROCESS_UNIT() {
		return VH_MAX_PROCESS_UNIT;
	}

	public void setVH_MAX_PROCESS_UNIT(String vh_max_process_unit) {
		VH_MAX_PROCESS_UNIT = vh_max_process_unit;
	}

	public String getVH_MIN_PROCESS_UNIT() {
		return VH_MIN_PROCESS_UNIT;
	}

	public void setVH_MIN_PROCESS_UNIT(String vh_min_process_unit) {
		VH_MIN_PROCESS_UNIT = vh_min_process_unit;
	}

	public String getIM_DESC() {
		return IM_DESC;
	}

	public String getPRIORITY() {
		return PRIORITY;
	}

	public void setPRIORITY(String priority) {
		PRIORITY = priority;
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

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getVH_CPU() {
		return VH_CPU;
	}

	public void setVH_CPU(String vh_cpu) {
		VH_CPU = vh_cpu;
	}

	public String getVH_STORAGE() {
		return VH_STORAGE;
	}

	public void setVH_STORAGE(String vh_storage) {
		VH_STORAGE = vh_storage;
	}

	public String getVH_MEM() {
		return VH_MEM;
	}

	public void setVH_MEM(String vh_mem) {
		VH_MEM = vh_mem;
	}

	public String getSETTINGS() {
		return SETTINGS;
	}

	public void setSETTINGS(String settings) {
		SETTINGS = settings;
	}

	public String getMODEL() {
		return MODEL;
	}

	public void setMODEL(String model) {
		MODEL = model;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public String getDOMAIN() {
		return DOMAIN;
	}

	public void setDOMAIN(String domain) {
		DOMAIN = domain;
	}

	public String getNET_ID() {
		return NET_ID;
	}

	public void setNET_ID(String net_id) {
		NET_ID = net_id;
	}

	public String getFREECOUNT() {
		return FREECOUNT;
	}

	public void setFREECOUNT(String freecount) {
		FREECOUNT = freecount;
	}

	public String getHOSTNAME() {
		return HOSTNAME;
	}

	public void setHOSTNAME(String hostname) {
		HOSTNAME = hostname;
	}

	public String getGATEWAY1() {
		return GATEWAY1;
	}

	public void setGATEWAY1(String gateway1) {
		GATEWAY1 = gateway1;
	}

	public String getSUBNET() {
		return SUBNET;
	}

	public void setSUBNET(String subnet) {
		SUBNET = subnet;
	}

	public String getDNS1() {
		return DNS1;
	}

	public void setDNS1(String dns1) {
		DNS1 = dns1;
	}

	public String getDNS2() {
		return DNS2;
	}

	public void setDNS2(String dns2) {
		DNS2 = dns2;
	}

	public List getNetList() {
		return netList;
	}

	public void setNetList(List netList) {
		this.netList = netList;
	}

	public List getProjectList() {
		return projectList;
	}

	public void setProjectList(List projectList) {
		this.projectList = projectList;
	}

	public String getHOSTNAMESYS() {
		return HOSTNAMESYS;
	}

	public void setHOSTNAMESYS(String hostnamesys) {
		HOSTNAMESYS = hostnamesys;
	}

	public String getDOMAINSYS() {
		return DOMAINSYS;
	}

	public void setDOMAINSYS(String domainsys) {
		DOMAINSYS = domainsys;
	}

	public String getUSER_NAME() {
		return USER_NAME;
	}

	public void setUSER_NAME(String user_name) {
		USER_NAME = user_name;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String password) {
		PASSWORD = password;
	}

	public String getWorkloadInfoId() {
		return workloadInfoId;
	}

	public void setWorkloadInfoId(String workloadInfoId) {
		this.workloadInfoId = workloadInfoId;
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

	public String getIM_ROOT_PASS() {
		return IM_ROOT_PASS;
	}

	public void setIM_ROOT_PASS(String iMROOTPASS) {
		IM_ROOT_PASS = iMROOTPASS;
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

	public List<TbCloud2ImageTargetObj> getTARGET() {
		return TARGET;
	}

	public void setTARGET(List<TbCloud2ImageTargetObj> target) {
		TARGET = target;
	}

	public String getTARGETID() {
		return TARGETID;
	}

	public void setTARGETID(String targetid) {
		TARGETID = targetid;
	}

	public String getNET_ID_DOUBLE() {
		return NET_ID_DOUBLE;
	}

	public void setNET_ID_DOUBLE(String net_id_double) {
		NET_ID_DOUBLE = net_id_double;
	}
}
