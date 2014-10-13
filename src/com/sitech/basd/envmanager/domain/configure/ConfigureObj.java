package com.sitech.basd.envmanager.domain.configure;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class ConfigureObj  extends BaseObj{
	
	private int cf_env ;//INTEGER ,--序号
	private String CF_DOMAIN  ;//VARCHAR2(30) ,--系统域
	private String CF_SOURCE  ;//VARCHAR2(30) ,--资源域
	private String CF_CLASS  ;//VARCHAR2(30) ,--分类
	private String CF_HOSTTYPE ;// VARCHAR2(30) ,--主机类型
	private String CF_HOSTNUM ;// VARCHAR2(30) ,--型号
	private String CF_DESCRIPTION ;//  VARCHAR2(200),--配置
	private String CF_SYSTEM ;// VARCHAR2(30),--操作系统
	private String CF_HOSTNAME ;//  VARCHAR2(30),--主机名
	private String CF_IP ;//  VARCHAR2(30) ,--IP地址
	private String CF_PRODUCT ;// VARCHAR2(30), --使用产品线
	private String CF_DEVEPROD ;// VARCHAR2(30), --研发产品
	private String CF_SID ;//  VARCHAR2(30) ,--实例
	private String CF_TABLESPACE ;// NUMBER, --表空间
	private String CF_FILESYSNAM ;// VARCHAR2(30), --文件系统
	private String CF_FILEAPPNUM ;//  NUMBER, --文件系统大小
	private String CF_CPUUSED ;// VARCHAR2(10), --CPU
	private String CF_MEM ;// NUMBER, --内存
	private String CF_FILEUSERD ;// NUMBER,--文件系统
	private String CF_FILEUSEPER ;//  VARCHAR2(30),-- 文件系统使用率
	private String CF_SID1 ;//  VARCHAR2(30), --实例
	private String CF_TABSPAUSED ;//  NUMBER, --表空间
	private String CF_TABSPAUSEPER ;//   VARCHAR2(30), --表空间使率
	private String CF_SGA ;//   VARCHAR2(30), --内存
	private String CF_CPULEFT ;//   VARCHAR2(30), --CPU
	private String CF_MEMLEFT ;//   VARCHAR2(30), --内存
	private String CF_STORAGE ;//  VARCHAR2(30),--存储
	private String CF_DATE ;//   VARCHAR2(10)--更新时间
	
	private int DEVICE_ID;
	
	

	
	public int getDEVICE_ID() {
		return DEVICE_ID;
	}
	public void setDEVICE_ID(int device_id) {
		DEVICE_ID = device_id;
	}

	public int getCf_env() {
		return cf_env;
	}
	public void setCf_env(int cf_env) {
		this.cf_env = cf_env;
	}
	public String getCF_DOMAIN() {
		return CF_DOMAIN;
	}
	public void setCF_DOMAIN(String cf_domain) {
		CF_DOMAIN = cf_domain;
	}
	public String getCF_SOURCE() {
		return CF_SOURCE;
	}
	public void setCF_SOURCE(String cf_source) {
		CF_SOURCE = cf_source;
	}
	public String getCF_CLASS() {
		return CF_CLASS;
	}
	public void setCF_CLASS(String cf_class) {
		CF_CLASS = cf_class;
	}
	public String getCF_HOSTTYPE() {
		return CF_HOSTTYPE;
	}
	public void setCF_HOSTTYPE(String cf_hosttype) {
		CF_HOSTTYPE = cf_hosttype;
	}
	public String getCF_HOSTNUM() {
		return CF_HOSTNUM;
	}
	public void setCF_HOSTNUM(String cf_hostnum) {
		CF_HOSTNUM = cf_hostnum;
	}
	public String getCF_DESCRIPTION() {
		return CF_DESCRIPTION;
	}
	public void setCF_DESCRIPTION(String cf_description) {
		CF_DESCRIPTION = cf_description;
	}
	public String getCF_SYSTEM() {
		return CF_SYSTEM;
	}
	public void setCF_SYSTEM(String cf_system) {
		CF_SYSTEM = cf_system;
	}
	public String getCF_HOSTNAME() {
		return CF_HOSTNAME;
	}
	public void setCF_HOSTNAME(String cf_hostname) {
		CF_HOSTNAME = cf_hostname;
	}
	public String getCF_IP() {
		return CF_IP;
	}
	public void setCF_IP(String cf_ip) {
		CF_IP = cf_ip;
	}
	public String getCF_PRODUCT() {
		return CF_PRODUCT;
	}
	public void setCF_PRODUCT(String cf_product) {
		CF_PRODUCT = cf_product;
	}
	public String getCF_DEVEPROD() {
		return CF_DEVEPROD;
	}
	public void setCF_DEVEPROD(String cf_deveprod) {
		CF_DEVEPROD = cf_deveprod;
	}
	public String getCF_SID() {
		return CF_SID;
	}
	public void setCF_SID(String cf_sid) {
		CF_SID = cf_sid;
	}
	public String getCF_TABLESPACE() {
		return CF_TABLESPACE;
	}
	public void setCF_TABLESPACE(String cf_tablespace) {
		CF_TABLESPACE = cf_tablespace;
	}
	public String getCF_FILESYSNAM() {
		return CF_FILESYSNAM;
	}
	public void setCF_FILESYSNAM(String cf_filesysnam) {
		CF_FILESYSNAM = cf_filesysnam;
	}
	public String getCF_FILEAPPNUM() {
		return CF_FILEAPPNUM;
	}
	public void setCF_FILEAPPNUM(String cf_fileappnum) {
		CF_FILEAPPNUM = cf_fileappnum;
	}
	public String getCF_CPUUSED() {
		return CF_CPUUSED;
	}
	public void setCF_CPUUSED(String cf_cpuused) {
		CF_CPUUSED = cf_cpuused;
	}
	public String getCF_MEM() {
		return CF_MEM;
	}
	public void setCF_MEM(String cf_mem) {
		CF_MEM = cf_mem;
	}
	public String getCF_FILEUSERD() {
		return CF_FILEUSERD;
	}
	public void setCF_FILEUSERD(String cf_fileuserd) {
		CF_FILEUSERD = cf_fileuserd;
	}
	public String getCF_FILEUSEPER() {
		return CF_FILEUSEPER;
	}
	public void setCF_FILEUSEPER(String cf_fileuseper) {
		CF_FILEUSEPER = cf_fileuseper;
	}
	public String getCF_SID1() {
		return CF_SID1;
	}
	public void setCF_SID1(String cf_sid1) {
		CF_SID1 = cf_sid1;
	}
	public String getCF_TABSPAUSED() {
		return CF_TABSPAUSED;
	}
	public void setCF_TABSPAUSED(String cf_tabspaused) {
		CF_TABSPAUSED = cf_tabspaused;
	}
	public String getCF_TABSPAUSEPER() {
		return CF_TABSPAUSEPER;
	}
	public void setCF_TABSPAUSEPER(String cf_tabspauseper) {
		CF_TABSPAUSEPER = cf_tabspauseper;
	}
	public String getCF_SGA() {
		return CF_SGA;
	}
	public void setCF_SGA(String cf_sga) {
		CF_SGA = cf_sga;
	}
	public String getCF_CPULEFT() {
		return CF_CPULEFT;
	}
	public void setCF_CPULEFT(String cf_cpuleft) {
		CF_CPULEFT = cf_cpuleft;
	}
	public String getCF_MEMLEFT() {
		return CF_MEMLEFT;
	}
	public void setCF_MEMLEFT(String cf_memleft) {
		CF_MEMLEFT = cf_memleft;
	}
	public String getCF_STORAGE() {
		return CF_STORAGE;
	}
	public void setCF_STORAGE(String cf_storage) {
		CF_STORAGE = cf_storage;
	}
	public String getCF_DATE() {
		return CF_DATE;
	}
	public void setCF_DATE(String cf_date) {
		CF_DATE = cf_date;
	}
	
	

}
