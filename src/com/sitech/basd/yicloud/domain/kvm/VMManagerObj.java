package com.sitech.basd.yicloud.domain.kvm;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class VMManagerObj extends BaseObj implements Serializable, Cloneable {

	private int ID;
	private String NAME_ZH; // 中文名
	private String NAME_EN; // 英文名
	private String CODE; // SN编号
	private String IP; // 内网IP
	private String TYPE; // 设备类型 主机服务器/网络设备/虚拟主机/虚拟宿主机
	private String SUB_TYPE; // 设备子类型 数据库/web应用/存储/混合
	private String VH_HOST; // 虚拟主机的宿主机ID
	private String BRAND; // 品牌
	private String MODEL; // 型号
	private String CPU; // CPU
	private String MEMORY; // 内存
	private String CURRENTMEMORY;// 期望内存
	private String CURRENTCPU;// 期望cpu
	private String STORAGE; // 存储空间
	private String datastoreName;// 存储名称
	private String INTERFACE;// 外围接口
	private String AUXILIARY;// 附属设备
	private String REMARK; // 备注
	private String TYPENAME; // 设备类型名称
	private String SUB_TYPENAME; // 设备子类型名称
	private String VH_HOSTNAME; // 虚拟主机的宿主ID名称
	private String BRANDNAME; // 品牌名称
	private String state;// 虚拟机状态
	private static final long serialVersionUID = -1714469510588445079L;
	private String USERNAME;// 主机用户名
	private String PASSWORD;// 主机用户密码
	private String guestOsId;// 客户机操作系统类型
	private String host_name;// 主机名称
	private String NET_ID;
	private String hostUuid;

	public String getHostUuid() {
		return hostUuid;
	}

	public void setHostUuid(String hostUuid) {
		this.hostUuid = hostUuid;
	}

	public String getNET_ID() {
		return NET_ID;
	}

	public void setNET_ID(String net_id) {
		NET_ID = net_id;
	}

	public String getHost_name() {
		return host_name;
	}

	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}

	public String getGuestOsId() {
		return guestOsId;
	}

	public void setGuestOsId(String guestOsId) {
		this.guestOsId = guestOsId;
	}

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

	public String getCURRENTCPU() {
		return CURRENTCPU;
	}

	public void setCURRENTCPU(String currentcpu) {
		CURRENTCPU = currentcpu;
	}

	public String getCURRENTMEMORY() {
		return CURRENTMEMORY;
	}

	public void setCURRENTMEMORY(String currentmemory) {
		CURRENTMEMORY = currentmemory;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDatastoreName() {
		return datastoreName;
	}

	public void setDatastoreName(String datastoreName) {
		this.datastoreName = datastoreName;
	}

}
