package com.sitech.basd.cloud3.domain.departproject;

import com.sitech.basd.common.domain.BasePrivilegeObj;

public class DepartManObj extends BasePrivilegeObj {
	private String id;
	private String name;// 部门名称
	private String leader;// 部门负责人
	private String leaderName;// 负责人名称
	private int cpu;// cpu核数
	private int mem;// 内存
	private int store;// 存储
	private int ip_num;// ip个数
	private String type;// 类型 1、部门 2、科室
	private String parent_id;

	// 用以统计使用率
	private String CPU_ALL_COUNT; // CPU总量
	private String CPU_USED_COUNT; // 已用CPU
	private String MEM_ALL_MB;// 内存总量
	private String MEM_USED_MB;// 已用内存
	private String STORAGE_ALL_MB;// 存储总量
	private String STORAGE_USED_MB;// 已用存储
	private String IP_ALL_COUNT;// IP总量
	private String IP_USED_COUNT;// 已用IP
	private String VM_ALL_COUNT;// 虚拟机个数

	public int getCpu() {
		return cpu;
	}

	public void setCpu(int cpu) {
		this.cpu = cpu;
	}

	public int getMem() {
		return mem;
	}

	public void setMem(int mem) {
		this.mem = mem;
	}

	public int getStore() {
		return store;
	}

	public void setStore(int store) {
		this.store = store;
	}

	public int getIp_num() {
		return ip_num;
	}

	public void setIp_num(int ip_num) {
		this.ip_num = ip_num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
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

}
