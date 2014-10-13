package com.sitech.ssd.ah.paas.util;

/**
 * 
 * <p>
 * Title: PaasConstant
 * </p>
 * <p>
 * Description: paas资源树节点类型
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-3-28 上午9:16:12
 * 
 */
public class PaasConstant {
	// 节点类型
	public static final String ROOT = "0";
	public static final String SERVICE = "1";
	public static final String RESOURCEPOOL = "2";
	public static final String EXAMPLES = "3";
	public static final String HOST = "4";

	public static final String CAAS = "10";// 通讯、内容与计算即服务
	public static final String CAAS_TYPE = "11";// 类型
	public static final String CAAS_BUSI = "12";// 业务
	public static final String CAAS_EXAMPLE = "13";// 实例
	public static final String CAAS_HOST = "14";// 主机

	public static final String DAAS = "20";// 数据库即服务
	public static final String DAAS_TYPE = "21";// 数据库类型(oracle,mysql)
	public static final String DAAS_BUSI = "22";// 业务
	public static final String DAAS_DB = "23";// 数据库
	public static final String DAAS_DB_ENTITY = "24";// 实体

	public static final String MAAS = "30";// 监控/管理即服务
	public static final String MAAS_TYPE = "31";// 类型(Tomcat、Weblogic)
	public static final String MAAS_APP = "32";// 应用
	public static final String MAAS_DOMAIN = "33";// 域
	public static final String MAAS_ENTITY = "34";// 实体

	public static final String HADOOP = "40";// 大数据

	// 失败、成功标示
	public static final String FAIL = "操作失败";
	public static final String SUCCESS = "操作成功";

	// KPI 类型
	public static final String MEMCACHE = "1";
	public static final String TOMCAT = "2";
	public static final String ORACLE = "3";
	public static final String MYSQL = "4";
	public static final String WEBLOGIC = "5";
	public static final String GP = "6";

	public static final String TOMCAT_DOMAIN = "21";
	public static final String TOMCAT_INSTANCE = "22";
	public static final String TOMCAT_SERVER = "23";
	public static final String WEBLOGIC_DATASOURCE = "51";
	public static final String WEBLOGIC_SERVER = "52";
	// public static final String WEBLOGIC_DOMAIN = "53";

	public static final String ORACLE_DB = "31";
	public static final String ORACLE_INSTANCE = "32";
	public static final String ORACLE_SERVER = "33";
	public static final String ORACLE_BUSI_SERVER = "34";
	public static final String ORACLE_CONTORLMONITOR = "35";

	// 指标类型
	public static final String MONITOR = "1";
	public static final String BASIC = "2";

	// 命令KPI
	public static final String GET_HITS = "get_hits";
	public static final String GET_MISSES = "get_misses";
	public static final String DELETE_HITS = "delete_hits";
	public static final String DELETE_MISSES = "delete_misses";
	public static final String INCR_HITS = "incr_hits";
	public static final String INCR_MISSES = "incr_misses";
	public static final String DECR_HITS = "decr_hits";
	public static final String DECR_MISSES = "decr_misses";
	public static final String CAS_HITS = "cas_hits";
	public static final String CAS_MISSES = "cas_misses";
	public static final String TOUCH_HITS = "touch_hits";
	public static final String TOUCH_MISSES = "touch_misses";
	
	public static final String TABLE_PAAS = "paas";
	public static final String TABLE_HADOOP = "hadoop";

}
