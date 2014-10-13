package com.sitech.basd.cloud3.domain.departproject;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class RelationObj extends BaseObj{
	private String projectId;//项目ID
	private String relatonId;//虚拟机和项目关系表ID
	private String vmuuId;// 虚拟机标示
	private String connectId;//链接
	private String vmCount;//虚拟机个数
	private String VH_ID; // 虚拟机ID
	private String VH_NAME; // 虚拟机名称
	private String VH_MEM; // 内存
	private String VH_CPU; // CPU
	private String VH_STORAGE; // 存储
	private String VH_SYSTEM; // 操作系统
	private String VH_IP; // IP地址
	private String VH_TYPE;//虚拟机类型
	private String PROJECT_NO; // 项目编号
	private String PROJECT_NAME; // 项目名称
	private String CPU_ALL_COUNT; //CPU总量
	private String CPU_USED_COUNT; //已用CPU
	private String MEM_ALL_MB;//内存总量
	private String MEM_USED_MB;//已用内存
	private String STORAGE_ALL_MB;//存储总量
	private String STORAGE_USED_MB;//已用存储
	private String IP_ALL_COUNT;//IP总量
	private String IP_USED_COUNT;//已用IP
	private String VM_ALL_COUNT;//虚拟机个数
	private String PROJECT_LEADER;//用户账号
	private String departId; //科室id
	private String DEPART_NAME;// 部门名称
	private String PROJECT_LEADERNAME;//项目总负责人
	
	public String getPROJECT_LEADERNAME() {
		return PROJECT_LEADERNAME;
	}
	public void setPROJECT_LEADERNAME(String pROJECT_LEADERNAME) {
		PROJECT_LEADERNAME = pROJECT_LEADERNAME;
	}
	public String getDEPART_NAME() {
		return DEPART_NAME;
	}
	public void setDEPART_NAME(String dEPART_NAME) {
		DEPART_NAME = dEPART_NAME;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getRelatonId() {
		return relatonId;
	}
	public void setRelatonId(String relatonId) {
		this.relatonId = relatonId;
	}
	public String getVmuuId() {
		return vmuuId;
	}
	public void setVmuuId(String vmuuId) {
		this.vmuuId = vmuuId;
	}
	public String getConnectId() {
		return connectId;
	}
	public void setConnectId(String connectId) {
		this.connectId = connectId;
	}
	public String getVmCount() {
		return vmCount;
	}
	public void setVmCount(String vmCount) {
		this.vmCount = vmCount;
	}
	public String getVH_ID() {
		return VH_ID;
	}
	public void setVH_ID(String vH_ID) {
		VH_ID = vH_ID;
	}
	public String getVH_NAME() {
		return VH_NAME;
	}
	public void setVH_NAME(String vH_NAME) {
		VH_NAME = vH_NAME;
	}
	public String getVH_MEM() {
		return VH_MEM;
	}
	public void setVH_MEM(String vH_MEM) {
		VH_MEM = vH_MEM;
	}
	public String getVH_CPU() {
		return VH_CPU;
	}
	public void setVH_CPU(String vH_CPU) {
		VH_CPU = vH_CPU;
	}
	public String getVH_STORAGE() {
		return VH_STORAGE;
	}
	public void setVH_STORAGE(String vH_STORAGE) {
		VH_STORAGE = vH_STORAGE;
	}
	public String getVH_SYSTEM() {
		return VH_SYSTEM;
	}
	public void setVH_SYSTEM(String vH_SYSTEM) {
		VH_SYSTEM = vH_SYSTEM;
	}
	public String getVH_IP() {
		return VH_IP;
	}
	public void setVH_IP(String vH_IP) {
		VH_IP = vH_IP;
	}
	public String getPROJECT_NO() {
		return PROJECT_NO;
	}
	public void setPROJECT_NO(String pROJECT_NO) {
		PROJECT_NO = pROJECT_NO;
	}
	public String getPROJECT_NAME() {
		return PROJECT_NAME;
	}
	public void setPROJECT_NAME(String pROJECT_NAME) {
		PROJECT_NAME = pROJECT_NAME;
	}
	public String getCPU_ALL_COUNT() {
		return CPU_ALL_COUNT;
	}
	public void setCPU_ALL_COUNT(String cPU_ALL_COUNT) {
		CPU_ALL_COUNT = cPU_ALL_COUNT;
	}
	public String getCPU_USED_COUNT() {
		return CPU_USED_COUNT;
	}
	public void setCPU_USED_COUNT(String cPU_USED_COUNT) {
		CPU_USED_COUNT = cPU_USED_COUNT;
	}
	public String getMEM_ALL_MB() {
		return MEM_ALL_MB;
	}
	public void setMEM_ALL_MB(String mEM_ALL_MB) {
		MEM_ALL_MB = mEM_ALL_MB;
	}
	public String getMEM_USED_MB() {
		return MEM_USED_MB;
	}
	public void setMEM_USED_MB(String mEM_USED_MB) {
		MEM_USED_MB = mEM_USED_MB;
	}
	public String getSTORAGE_ALL_MB() {
		return STORAGE_ALL_MB;
	}
	public void setSTORAGE_ALL_MB(String sTORAGE_ALL_MB) {
		STORAGE_ALL_MB = sTORAGE_ALL_MB;
	}
	public String getSTORAGE_USED_MB() {
		return STORAGE_USED_MB;
	}
	public void setSTORAGE_USED_MB(String sTORAGE_USED_MB) {
		STORAGE_USED_MB = sTORAGE_USED_MB;
	}
	public String getIP_ALL_COUNT() {
		return IP_ALL_COUNT;
	}
	public void setIP_ALL_COUNT(String iP_ALL_COUNT) {
		IP_ALL_COUNT = iP_ALL_COUNT;
	}
	public String getIP_USED_COUNT() {
		return IP_USED_COUNT;
	}
	public void setIP_USED_COUNT(String iP_USED_COUNT) {
		IP_USED_COUNT = iP_USED_COUNT;
	}
	public String getVM_ALL_COUNT() {
		return VM_ALL_COUNT;
	}
	public void setVM_ALL_COUNT(String vM_ALL_COUNT) {
		VM_ALL_COUNT = vM_ALL_COUNT;
	}
	public String getPROJECT_LEADER() {
		return PROJECT_LEADER;
	}
	public void setPROJECT_LEADER(String pROJECT_LEADER) {
		PROJECT_LEADER = pROJECT_LEADER;
	}
	public String getVH_TYPE() {
		return VH_TYPE;
	}
	public void setVH_TYPE(String vH_TYPE) {
		VH_TYPE = vH_TYPE;
	}
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	
	
}
