package com.sitech.basd.yicloud.web.entitytree.form;

import java.util.List;

@SuppressWarnings("all")
public class EntityTreeForm {
	private List resultList;
	private String cpu;// cpu
	private String mem;// 内存
	private List alarmList;// 警报信息李彪
	private List temList;// 模板列表
	private String TEM_ID;
	private String TEM_NAME;
	private String STORAGE;
	private String TEM_DESC;
	private String CLONE_NUM;
	private String ip; // 虚拟机IP
	private String port;// 端口
	private String userName;// 用户名
	private String password;// 密码

	private String cpu_fq;// cpu主频
	private String cpu_cl;// cpu芯数
	private String MODEL;// 主机型号
	private int NIC_NUM;// 主机网卡数量
	private String CPU_DESC;// CPU描述
	private String HOST_PROC;// 主机制造商
	private String host_status;// 主机状态
	private String vm_authority;// 虚拟机权限

	public String getVm_authority() {
		return vm_authority;
	}

	public void setVm_authority(String vm_authority) {
		this.vm_authority = vm_authority;
	}

	public String getHost_status() {
		return host_status;
	}

	public void setHost_status(String host_status) {
		this.host_status = host_status;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCLONE_NUM() {
		return CLONE_NUM;
	}

	public void setCLONE_NUM(String clone_num) {
		CLONE_NUM = clone_num;
	}

	public String getTEM_DESC() {
		return TEM_DESC;
	}

	public void setTEM_DESC(String tem_desc) {
		TEM_DESC = tem_desc;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getMem() {
		return mem;
	}

	public void setMem(String mem) {
		this.mem = mem;
	}

	public String getTEM_ID() {
		return TEM_ID;
	}

	public void setTEM_ID(String tem_id) {
		TEM_ID = tem_id;
	}

	public String getTEM_NAME() {
		return TEM_NAME;
	}

	public void setTEM_NAME(String tem_name) {
		TEM_NAME = tem_name;
	}

	public String getSTORAGE() {
		return STORAGE;
	}

	public void setSTORAGE(String storage) {
		STORAGE = storage;
	}

	public List getTemList() {
		return temList;
	}

	public void setTemList(List temList) {
		this.temList = temList;
	}

	public List getAlarmList() {
		return alarmList;
	}

	public void setAlarmList(List alarmList) {
		this.alarmList = alarmList;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getCpu_fq() {
		return cpu_fq;
	}

	public void setCpu_fq(String cpu_fq) {
		this.cpu_fq = cpu_fq;
	}

	public String getCpu_cl() {
		return cpu_cl;
	}

	public void setCpu_cl(String cpu_cl) {
		this.cpu_cl = cpu_cl;
	}

	public String getMODEL() {
		return MODEL;
	}

	public void setMODEL(String model) {
		MODEL = model;
	}

	public int getNIC_NUM() {
		return NIC_NUM;
	}

	public void setNIC_NUM(int nic_num) {
		NIC_NUM = nic_num;
	}

	public String getCPU_DESC() {
		return CPU_DESC;
	}

	public void setCPU_DESC(String cpu_desc) {
		CPU_DESC = cpu_desc;
	}

	public String getHOST_PROC() {
		return HOST_PROC;
	}

	public void setHOST_PROC(String host_proc) {
		HOST_PROC = host_proc;
	}
}
