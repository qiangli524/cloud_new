package com.sitech.basd.sxcloud.cloud.domain.leader;

/**
 * 虚拟机基本信息
 * @author zhangwj
 * @Date 2011.12.1
 */
public class TbCloud2VmhostInfoObj {
	
	private String VH_ID = null;         //虚拟机编号
	private String VH_NAME = null;       //虚拟机名称
	private String VH_MEM = null;        //虚拟机内存
	private String VH_CPU = null;	    //虚拟机CPU	
	private String VH_STORAGE = null;    //虚拟机存储
	private String VH_SYSTEM = null;     //虚拟机操作系统
	private String VH_IP = null;         //虚拟机IP地址
	private String VH_NETWORK = null;    //虚拟机网络
	private String VH_RUNTIME = null;    //虚拟机运行时间
	
	private String EQ_ID = null;         //虚拟机部署物理主机编号
	private String VH_STAT = null;       //虚拟机状态
	private String VH_DESC = null;       //虚拟机描述
	
	private String PROJECT_ID = null;         //所属项目编号
	private String VH_MIN_CPU = null;    //cpu最小值
	private String VH_MAX_CPU = null;    //cpu最大值
	private String VH_MIN_MEM = null;    //存储最小值
	private String VH_MAX_MEM = null;    //存储最大值
	
	private String VH_STARTED = null;    //开始时间
	private String VH_COMPLETED = null;  //完成时间
	private String VH_DEPLOYETIME = null;//部署时间
	private String VH_UPTIME = null;		//更新时间
	
	private String VH_LOG = null;		//日志
	private String VH_USER = null;       //用户名
	private String VH_PASS = null;       //密码
	
	private String EQ_NAME = null;      //主机名
	
	private String VH_STATUS = null;
	
	public String getVH_STATUS() {
		return VH_STATUS;
	}
	public void setVH_STATUS(String vh_status) {
		VH_STATUS = vh_status;
	}
	public String getEQ_NAME() {
		return EQ_NAME;
	}
	public void setEQ_NAME(String eq_name) {
		EQ_NAME = eq_name;
	}
	public String getEQ_ID() {
		return EQ_ID;
	}
	public void setEQ_ID(String eq_id) {
		EQ_ID = eq_id;
	}
	public String getPROJECT_ID() {
		return PROJECT_ID;
	}
	public void setPROJECT_ID(String project_id) {
		PROJECT_ID = project_id;
	}
	public String getVH_COMPLETED() {
		return VH_COMPLETED;
	}
	public void setVH_COMPLETED(String vh_completed) {
		VH_COMPLETED = vh_completed;
	}
	public String getVH_CPU() {
		return VH_CPU;
	}
	public void setVH_CPU(String vh_cpu) {
		VH_CPU = vh_cpu;
	}
	public String getVH_DEPLOYETIME() {
		return VH_DEPLOYETIME;
	}
	public void setVH_DEPLOYETIME(String vh_deployetime) {
		VH_DEPLOYETIME = vh_deployetime;
	}
	public String getVH_DESC() {
		return VH_DESC;
	}
	public void setVH_DESC(String vh_desc) {
		VH_DESC = vh_desc;
	}
	public String getVH_ID() {
		return VH_ID;
	}
	public void setVH_ID(String vh_id) {
		VH_ID = vh_id;
	}
	public String getVH_IP() {
		return VH_IP;
	}
	public void setVH_IP(String vh_ip) {
		VH_IP = vh_ip;
	}
	public String getVH_LOG() {
		return VH_LOG;
	}
	public void setVH_LOG(String vh_log) {
		VH_LOG = vh_log;
	}
	public String getVH_MAX_CPU() {
		return VH_MAX_CPU;
	}
	public void setVH_MAX_CPU(String vh_max_cpu) {
		VH_MAX_CPU = vh_max_cpu;
	}
	public String getVH_MAX_MEM() {
		return VH_MAX_MEM;
	}
	public void setVH_MAX_MEM(String vh_max_mem) {
		VH_MAX_MEM = vh_max_mem;
	}
	public String getVH_MEM() {
		return VH_MEM;
	}
	public void setVH_MEM(String vh_mem) {
		VH_MEM = vh_mem;
	}
	public String getVH_MIN_CPU() {
		return VH_MIN_CPU;
	}
	public void setVH_MIN_CPU(String vh_min_cpu) {
		VH_MIN_CPU = vh_min_cpu;
	}
	public String getVH_MIN_MEM() {
		return VH_MIN_MEM;
	}
	public void setVH_MIN_MEM(String vh_min_mem) {
		VH_MIN_MEM = vh_min_mem;
	}
	public String getVH_NAME() {
		return VH_NAME;
	}
	public void setVH_NAME(String vh_name) {
		VH_NAME = vh_name;
	}
	public String getVH_NETWORK() {
		return VH_NETWORK;
	}
	public void setVH_NETWORK(String vh_network) {
		VH_NETWORK = vh_network;
	}
	public String getVH_PASS() {
		return VH_PASS;
	}
	public void setVH_PASS(String vh_pass) {
		VH_PASS = vh_pass;
	}
	public String getVH_RUNTIME() {
		return VH_RUNTIME;
	}
	public void setVH_RUNTIME(String vh_runtime) {
		VH_RUNTIME = vh_runtime;
	}
	public String getVH_STARTED() {
		return VH_STARTED;
	}
	public void setVH_STARTED(String vh_started) {
		VH_STARTED = vh_started;
	}
	public String getVH_STAT() {
		return VH_STAT;
	}
	public void setVH_STAT(String vh_stat) {
		VH_STAT = vh_stat;
	}
	public String getVH_STORAGE() {
		return VH_STORAGE;
	}
	public void setVH_STORAGE(String vh_storage) {
		VH_STORAGE = vh_storage;
	}
	public String getVH_SYSTEM() {
		return VH_SYSTEM;
	}
	public void setVH_SYSTEM(String vh_system) {
		VH_SYSTEM = vh_system;
	}
	public String getVH_UPTIME() {
		return VH_UPTIME;
	}
	public void setVH_UPTIME(String vh_uptime) {
		VH_UPTIME = vh_uptime;
	}
	public String getVH_USER() {
		return VH_USER;
	}
	public void setVH_USER(String vh_user) {
		VH_USER = vh_user;
	}
	public TbCloud2VmhostInfoObj() {
	}
}
