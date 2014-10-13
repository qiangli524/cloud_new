package com.sitech.basd.yicloud.web.vmman.form;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;

@SuppressWarnings("serial")
public class VMManagerForm {
	private int ID;
	private String NAME_ZH; // 中文名
	private String NAME_EN; // 英文名
	private String CODE; // SN编号
	private String NET;
	private String IP; // 内网IP
	private String TYPE; // 设备类型 主机服务器/网络设备/虚拟主机/虚拟宿主机
	private String SUB_TYPE; // 设备子类型 数据库/web应用/存储/混合
	private String VH_HOST; // 虚拟主机的宿主机ID
	private String BRAND; // 品牌
	private String MODEL; // 型号
	private String CPU; // CPU
	private String MEMORY; // 内存
	private String STORAGE; // 存储空间
	private String INTERFACE;// 外围接口
	private String AUXILIARY;// 附属设备
	private String REMARK; // 备注
	private String TYPENAME; // 设备类型名称
	private String SUB_TYPENAME; // 设备子类型名称
	private String VH_HOSTNAME; // 虚拟主机的宿主ID名称
	private String BRANDNAME; // 品牌名称
	private List resultList = null; // 结果列表
	private List healthList;// 健康状况列表
	private int flag;// 判断是修改还是插入
	private String treeStr;
	private String SELECT_IMAGE;// 所选虚拟镜像
	private String IM_NAME;
	private String IM_DESC;
	private String IM_STATE;
	private String IM_VERSION;
	private String IM_TYPE;
	private String STATE;// 虚拟机状态
	private String CURRENTMEMORY;// 期望内存
	private String CURRENTCPU;// 期望cpu
	private int IM_ID;
	/** jsp源码 */
	private String jspCode;
	private List<TbCloud2IpInfoObj> ipList;
	private List netList;
	/** 虚拟机所属主机IP */
	private String hostIP;
	/** 设备ID */
	private String DEVICE_ID;
	/** IP是否阻塞 */
	private String IP_ISBLOCKED;
	/** 端口是否阻塞 */
	private String PORT_ISBLOCKED;
	/** 设备IO读写 */
	private String IO;

	/** 应用是否down */
	private String APP_HASDOWN;
	/** 中间件是否down */
	private String MIDDLE_HASDOWN;
	/** down状态的应用名称 */
	private String APP_DOWN_NAMES;
	/** down状态的中间件名称 */
	private String MIDDLE_DOWN_NAMES;
	private String UNIT;// 内存单位
	private String NAME;// 在树中添加的节点名称
	private String CLUSTER_TYPE;// 集群类型(vmware ,kvm)
	private String USERNAME;// 主机用户名
	private String PASSWORD;// 主机用户密码
	
	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String username) {
		USERNAME = username;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String password) {
		PASSWORD = password;
	}

	public String getCLUSTER_TYPE() {
		return CLUSTER_TYPE;
	}

	public void setCLUSTER_TYPE(String cluster_type) {
		CLUSTER_TYPE = cluster_type;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public String getUNIT() {
		return UNIT;
	}

	public void setUNIT(String unit) {
		UNIT = unit;
	}

	public int getIM_ID() {
		return IM_ID;
	}

	public void setIM_ID(int im_id) {
		IM_ID = im_id;
	}

	public String getCURRENTCPU() {
		return CURRENTCPU;
	}

	public void setCURRENTCPU(String currentcpu) {
		CURRENTCPU = currentcpu;
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

	public String getIO() {
		return IO;
	}

	public void setIO(String io) {
		IO = io;
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

	public List getHealthList() {
		return healthList;
	}

	public void setHealthList(List healthList) {
		this.healthList = healthList;
	}

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	public String getCURRENTMEMORY() {
		return CURRENTMEMORY;
	}

	public void setCURRENTMEMORY(String currentmemory) {
		CURRENTMEMORY = currentmemory;
	}

	public String getNET() {
		return NET;
	}

	public void setNET(String net) {
		NET = net;
	}

	public List getNetList() {
		return netList;
	}

	public void setNetList(List netList) {
		this.netList = netList;
	}

	public List<TbCloud2IpInfoObj> getIpList() {
		return ipList;
	}

	public void setIpList(List<TbCloud2IpInfoObj> ipList) {
		this.ipList = ipList;
	}

	public String getSTATE() {
		return STATE;
	}

	public void setSTATE(String state) {
		STATE = state;
	}

	public String getIM_NAME() {
		return IM_NAME;
	}

	public void setIM_NAME(String im_name) {
		IM_NAME = im_name;
	}

	public String getIM_DESC() {
		return IM_DESC;
	}

	public void setIM_DESC(String im_desc) {
		IM_DESC = im_desc;
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

	public String getIM_TYPE() {
		return IM_TYPE;
	}

	public void setIM_TYPE(String im_type) {
		IM_TYPE = im_type;
	}

	public String getSELECT_IMAGE() {
		return SELECT_IMAGE;
	}

	public void setSELECT_IMAGE(String select_image) {
		SELECT_IMAGE = select_image;
	}

	public String getJspCode() {
		return jspCode;
	}

	public void setJspCode(String jspCode) {
		this.jspCode = jspCode;
	}

	private String SELECT_TYPE;// 所选择的镜像类型
	private List typeList;// 镜像类型列表
	private List ImageList;// 镜像列表

	public String getSELECT_TYPE() {
		return SELECT_TYPE;
	}

	public void setSELECT_TYPE(String select_type) {
		SELECT_TYPE = select_type;
	}

	public List getTypeList() {
		return typeList;
	}

	public void setTypeList(List typeList) {
		this.typeList = typeList;
	}

	public List getImageList() {
		return ImageList;
	}

	public void setImageList(List imageList) {
		ImageList = imageList;
	}

	public String getTreeStr() {
		return treeStr;
	}

	public void setTreeStr(String treeStr) {
		this.treeStr = treeStr;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getNAME_ZH() {
		return NAME_ZH;
	}

	public void setNAME_ZH(String name_zh) {
		NAME_ZH = name_zh;
	}

	public String getNAME_EN() {
		return NAME_EN;
	}

	public void setNAME_EN(String name_en) {
		NAME_EN = name_en;
	}

	public String getCODE() {
		return CODE;
	}

	public void setCODE(String code) {
		CODE = code;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String ip) {
		IP = ip;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String type) {
		TYPE = type;
	}

	public String getSUB_TYPE() {
		return SUB_TYPE;
	}

	public void setSUB_TYPE(String sub_type) {
		SUB_TYPE = sub_type;
	}

	public String getVH_HOST() {
		return VH_HOST;
	}

	public void setVH_HOST(String vh_host) {
		VH_HOST = vh_host;
	}

	public String getBRAND() {
		return BRAND;
	}

	public void setBRAND(String brand) {
		BRAND = brand;
	}

	public String getMODEL() {
		return MODEL;
	}

	public void setMODEL(String model) {
		MODEL = model;
	}

	public String getCPU() {
		return CPU;
	}

	public void setCPU(String cpu) {
		CPU = cpu;
	}

	public String getMEMORY() {
		return MEMORY;
	}

	public void setMEMORY(String memory) {
		MEMORY = memory;
	}

	public String getSTORAGE() {
		return STORAGE;
	}

	public void setSTORAGE(String storage) {
		STORAGE = storage;
	}

	public String getINTERFACE() {
		return INTERFACE;
	}

	public void setINTERFACE(String interface1) {
		INTERFACE = interface1;
	}

	public String getAUXILIARY() {
		return AUXILIARY;
	}

	public void setAUXILIARY(String auxiliary) {
		AUXILIARY = auxiliary;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String remark) {
		REMARK = remark;
	}

	public String getTYPENAME() {
		return TYPENAME;
	}

	public void setTYPENAME(String typename) {
		TYPENAME = typename;
	}

	public String getSUB_TYPENAME() {
		return SUB_TYPENAME;
	}

	public void setSUB_TYPENAME(String sub_typename) {
		SUB_TYPENAME = sub_typename;
	}

	public String getVH_HOSTNAME() {
		return VH_HOSTNAME;
	}

	public void setVH_HOSTNAME(String vh_hostname) {
		VH_HOSTNAME = vh_hostname;
	}

	public String getBRANDNAME() {
		return BRANDNAME;
	}

	public void setBRANDNAME(String brandname) {
		BRANDNAME = brandname;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
}
