package com.sitech.basd.sxcloud.cloud.web.templet.form;

import java.util.List;

public class TempletForm {
	private String TEM_ID; // 模板编号
	private String ID;
	private String TYPE; // 模板类型
	private String TEM_NAME; // 模板名称
	private String[] CONFIG_NAME; // 配置项
	private String[] CONFIG_VALUE; // 配置值
	private String TEM_DESC; // 配置描述
	private int flag = 0;
	private String VH_CPUVALUE; // cpu的值
	private String VH_CPU; // cpu
	private String VH_MEM; // 内存
	private String VH_MEMVALUE; // 内存的值
	private String RELEASE_FLAG; // 模板发布状态
	private String VH_MAX_CPU; // CPU最大值
	private String VH_MAX_CPUVALUE;
	private String IMAGE_ID; // 虚拟映像ID
	private String IMAGE_IDVALUE;
	private String VH_MIN_CPU; // CPU最小值
	private String VH_MIN_CPUVALUE;
	private String VHMAX_MEM; // 内存最大值
	private String VHMAX_MEMVALUE;
	private String VH_MIN_MEM; // 内存最小值
	private String VH_MIN_MEMVALUE;
	private String APP_ID; // 应用编号标识
	private String APP_IDVALUE; // 应用编号的值
	private String APP_PORT; // 应用端口标识
	private String APP_PORTVALUE; // 应用端口值
	private String APP_NAME; // 应用名称标识
	private String APP_NAMEVALUE; // 应用名称标识
	private String VH_PROCESS_UNIT; // CPU处理单元(0.1单位)
	private String VH_PROCESS_UNITVALUE; // 存储CPU处理单元
	private String VH_MAX_PROCESS_UNIT;// CPU处理单元最大值(0.1单位)
	private String VH_MAX_PROCESS_UNITVALUE; // 用来存储CPU处理单元最大值(0.1单位)
	private String VH_MIN_PROCESS_UNIT; // CPU处理单元最小值
	private String VH_MIN_PROCESS_UNITVALUE; // 用来存储CPU处理单元最小值
	private String VH_STORAGE; // 存储(MB)
	private String VH_MAX_MEM;
	private String VH_MAX_MEMVALUE;
	private String VH_STORAGEVALUE; // 用来保存输入的存储的值
	private List typeList; // 模板类型下拉列表
	private String TYPE_NAME; // 模板配置名
	private String OPERTYPE; // 模板操作类别
	private String OPERTIME; // 操作模板时间
	private String MESSAGE; // 模板操作信息说明
	private String RESULT; // 模板操作是否成功
	private List ResultList = null;

	private String VH_TYPE;
	private String VH_TYPEVALUE;

	private String KV1;
	private String KV2;
	private String KV3;
	private String KV4;
	private String KV5;
	private String KV6;
	private String KV7;
	private String KV8;
	private String KV9;
	private String KV10;
	private String KV11;

	/**
	 * XEN模板相关
	 */
	private String VH_NUM; // 虚拟机个数
	private String VH_NUMVALUE;
	private String POOL_UUID;// 池ID
	private String POOL_UUIDVALUE;
	private String VH_IP;// ip地址
	private String VH_IPVALUE;
	private String VH_NAME;// 虚拟机名称

	public String getVH_NAME() {
		return VH_NAME;
	}

	public void setVH_NAME(String vh_name) {
		VH_NAME = vh_name;
	}

	public String getPOOL_UUID() {
		return POOL_UUID;
	}

	public void setPOOL_UUID(String pool_uuid) {
		POOL_UUID = pool_uuid;
	}

	public String getPOOL_UUIDVALUE() {
		return POOL_UUIDVALUE;
	}

	public void setPOOL_UUIDVALUE(String pool_uuidvalue) {
		POOL_UUIDVALUE = pool_uuidvalue;
	}

	public String getVH_IP() {
		return VH_IP;
	}

	public void setVH_IP(String vh_ip) {
		VH_IP = vh_ip;
	}

	public String getVH_IPVALUE() {
		return VH_IPVALUE;
	}

	public void setVH_IPVALUE(String vh_ipvalue) {
		VH_IPVALUE = vh_ipvalue;
	}

	public String getVH_NUM() {
		return VH_NUM;
	}

	public void setVH_NUM(String vh_num) {
		VH_NUM = vh_num;
	}

	public String getVH_NUMVALUE() {
		return VH_NUMVALUE;
	}

	public void setVH_NUMVALUE(String vh_numvalue) {
		VH_NUMVALUE = vh_numvalue;
	}

	public String getKV2() {
		return KV2;
	}

	public void setKV2(String kv2) {
		KV2 = kv2;
	}

	public String getKV3() {
		return KV3;
	}

	public void setKV3(String kv3) {
		KV3 = kv3;
	}

	public String getKV4() {
		return KV4;
	}

	public void setKV4(String kv4) {
		KV4 = kv4;
	}

	public String getKV5() {
		return KV5;
	}

	public void setKV5(String kv5) {
		KV5 = kv5;
	}

	public String getKV6() {
		return KV6;
	}

	public void setKV6(String kv6) {
		KV6 = kv6;
	}

	public String getKV7() {
		return KV7;
	}

	public void setKV7(String kv7) {
		KV7 = kv7;
	}

	public String getKV8() {
		return KV8;
	}

	public void setKV8(String kv8) {
		KV8 = kv8;
	}

	public String getKV9() {
		return KV9;
	}

	public void setKV9(String kv9) {
		KV9 = kv9;
	}

	public String getKV10() {
		return KV10;
	}

	public void setKV10(String kv10) {
		KV10 = kv10;
	}

	public String getKV11() {
		return KV11;
	}

	public void setKV11(String kv11) {
		KV11 = kv11;
	}

	public String getKV1() {
		return KV1;
	}

	public void setKV1(String kv1) {
		KV1 = kv1;
	}

	public String getRELEASE_FLAG() {
		return RELEASE_FLAG;
	}

	public void setRELEASE_FLAG(String release_flag) {
		RELEASE_FLAG = release_flag;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public List getResultList() {
		return ResultList;
	}

	public void setResultList(List resultList) {
		ResultList = resultList;
	}

	public String[] getCONFIG_NAME() {
		return CONFIG_NAME;
	}

	public void setCONFIG_NAME(String[] config_name) {
		CONFIG_NAME = config_name;
	}

	public String[] getCONFIG_VALUE() {
		return CONFIG_VALUE;
	}

	public void setCONFIG_VALUE(String[] config_value) {
		CONFIG_VALUE = config_value;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String type) {
		TYPE = type;
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

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getTEM_DESC() {
		return TEM_DESC;
	}

	public void setTEM_DESC(String tem_desc) {
		TEM_DESC = tem_desc;
	}

	public String getVH_CPUVALUE() {
		return VH_CPUVALUE;
	}

	public void setVH_CPUVALUE(String vh_cpuvalue) {
		VH_CPUVALUE = vh_cpuvalue;
	}

	public String getVH_CPU() {
		return VH_CPU;
	}

	public void setVH_CPU(String vh_cpu) {
		VH_CPU = vh_cpu;
	}

	public String getVH_MEM() {
		return VH_MEM;
	}

	public void setVH_MEM(String vh_mem) {
		VH_MEM = vh_mem;
	}

	public String getVH_MEMVALUE() {
		return VH_MEMVALUE;
	}

	public void setVH_MEMVALUE(String vh_memvalue) {
		VH_MEMVALUE = vh_memvalue;
	}

	public String getVH_MAX_CPU() {
		return VH_MAX_CPU;
	}

	public void setVH_MAX_CPU(String vh_max_cpu) {
		VH_MAX_CPU = vh_max_cpu;
	}

	public String getVH_MAX_CPUVALUE() {
		return VH_MAX_CPUVALUE;
	}

	public void setVH_MAX_CPUVALUE(String vh_max_cpuvalue) {
		VH_MAX_CPUVALUE = vh_max_cpuvalue;
	}

	public String getIMAGE_ID() {
		return IMAGE_ID;
	}

	public void setIMAGE_ID(String image_id) {
		IMAGE_ID = image_id;
	}

	public String getIMAGE_IDVALUE() {
		return IMAGE_IDVALUE;
	}

	public void setIMAGE_IDVALUE(String image_idvalue) {
		IMAGE_IDVALUE = image_idvalue;
	}

	public String getVH_MIN_CPU() {
		return VH_MIN_CPU;
	}

	public void setVH_MIN_CPU(String vh_min_cpu) {
		VH_MIN_CPU = vh_min_cpu;
	}

	public String getVH_MIN_CPUVALUE() {
		return VH_MIN_CPUVALUE;
	}

	public void setVH_MIN_CPUVALUE(String vh_min_cpuvalue) {
		VH_MIN_CPUVALUE = vh_min_cpuvalue;
	}

	public String getVHMAX_MEM() {
		return VHMAX_MEM;
	}

	public void setVHMAX_MEM(String vhmax_mem) {
		VHMAX_MEM = vhmax_mem;
	}

	public String getVHMAX_MEMVALUE() {
		return VHMAX_MEMVALUE;
	}

	public void setVHMAX_MEMVALUE(String vhmax_memvalue) {
		VHMAX_MEMVALUE = vhmax_memvalue;
	}

	public String getVH_MIN_MEM() {
		return VH_MIN_MEM;
	}

	public void setVH_MIN_MEM(String vh_min_mem) {
		VH_MIN_MEM = vh_min_mem;
	}

	public String getVH_MIN_MEMVALUE() {
		return VH_MIN_MEMVALUE;
	}

	public void setVH_MIN_MEMVALUE(String vh_min_memvalue) {
		VH_MIN_MEMVALUE = vh_min_memvalue;
	}

	public List getTypeList() {
		return typeList;
	}

	public void setTypeList(List typeList) {
		this.typeList = typeList;
	}

	public String getTYPE_NAME() {
		return TYPE_NAME;
	}

	public void setTYPE_NAME(String type_name) {
		TYPE_NAME = type_name;
	}

	public String getOPERTYPE() {
		return OPERTYPE;
	}

	public void setOPERTYPE(String opertype) {
		OPERTYPE = opertype;
	}

	public String getOPERTIME() {
		return OPERTIME;
	}

	public void setOPERTIME(String opertime) {
		OPERTIME = opertime;
	}

	public String getMESSAGE() {
		return MESSAGE;
	}

	public void setMESSAGE(String message) {
		MESSAGE = message;
	}

	public String getRESULT() {
		return RESULT;
	}

	public void setRESULT(String result) {
		RESULT = result;
	}

	public String getAPP_ID() {
		return APP_ID;
	}

	public void setAPP_ID(String app_id) {
		APP_ID = app_id;
	}

	public String getAPP_IDVALUE() {
		return APP_IDVALUE;
	}

	public void setAPP_IDVALUE(String app_idvalue) {
		APP_IDVALUE = app_idvalue;
	}

	public String getAPP_PORT() {
		return APP_PORT;
	}

	public void setAPP_PORT(String app_port) {
		APP_PORT = app_port;
	}

	public String getAPP_PORTVALUE() {
		return APP_PORTVALUE;
	}

	public void setAPP_PORTVALUE(String app_portvalue) {
		APP_PORTVALUE = app_portvalue;
	}

	public String getAPP_NAME() {
		return APP_NAME;
	}

	public void setAPP_NAME(String app_name) {
		APP_NAME = app_name;
	}

	public String getAPP_NAMEVALUE() {
		return APP_NAMEVALUE;
	}

	public void setAPP_NAMEVALUE(String app_namevalue) {
		APP_NAMEVALUE = app_namevalue;
	}

	public String getVH_PROCESS_UNIT() {
		return VH_PROCESS_UNIT;
	}

	public void setVH_PROCESS_UNIT(String vh_process_unit) {
		VH_PROCESS_UNIT = vh_process_unit;
	}

	public String getVH_PROCESS_UNITVALUE() {
		return VH_PROCESS_UNITVALUE;
	}

	public void setVH_PROCESS_UNITVALUE(String vh_process_unitvalue) {
		VH_PROCESS_UNITVALUE = vh_process_unitvalue;
	}

	public String getVH_MAX_PROCESS_UNIT() {
		return VH_MAX_PROCESS_UNIT;
	}

	public void setVH_MAX_PROCESS_UNIT(String vh_max_process_unit) {
		VH_MAX_PROCESS_UNIT = vh_max_process_unit;
	}

	public String getVH_MAX_PROCESS_UNITVALUE() {
		return VH_MAX_PROCESS_UNITVALUE;
	}

	public void setVH_MAX_PROCESS_UNITVALUE(String vh_max_process_unitvalue) {
		VH_MAX_PROCESS_UNITVALUE = vh_max_process_unitvalue;
	}

	public String getVH_MIN_PROCESS_UNIT() {
		return VH_MIN_PROCESS_UNIT;
	}

	public void setVH_MIN_PROCESS_UNIT(String vh_min_process_unit) {
		VH_MIN_PROCESS_UNIT = vh_min_process_unit;
	}

	public String getVH_MIN_PROCESS_UNITVALUE() {
		return VH_MIN_PROCESS_UNITVALUE;
	}

	public void setVH_MIN_PROCESS_UNITVALUE(String vh_min_process_unitvalue) {
		VH_MIN_PROCESS_UNITVALUE = vh_min_process_unitvalue;
	}

	public String getVH_STORAGE() {
		return VH_STORAGE;
	}

	public void setVH_STORAGE(String vh_storage) {
		VH_STORAGE = vh_storage;
	}

	public String getVH_STORAGEVALUE() {
		return VH_STORAGEVALUE;
	}

	public void setVH_STORAGEVALUE(String vh_storagevalue) {
		VH_STORAGEVALUE = vh_storagevalue;
	}

	public String getVH_MAX_MEM() {
		return VH_MAX_MEM;
	}

	public void setVH_MAX_MEM(String vh_max_mem) {
		VH_MAX_MEM = vh_max_mem;
	}

	public String getVH_MAX_MEMVALUE() {
		return VH_MAX_MEMVALUE;
	}

	public void setVH_MAX_MEMVALUE(String vh_max_memvalue) {
		VH_MAX_MEMVALUE = vh_max_memvalue;
	}

	public String getVH_TYPE() {
		return VH_TYPE;
	}

	public void setVH_TYPE(String vh_type) {
		VH_TYPE = vh_type;
	}

	public String getVH_TYPEVALUE() {
		return VH_TYPEVALUE;
	}

	public void setVH_TYPEVALUE(String vh_typevalue) {
		VH_TYPEVALUE = vh_typevalue;
	}

}
