package com.sitech.ssd.ah.paas.util;

/**
 * 
 * <p>
 * Title: TomcatStatisticsConstant
 * </p>
 * <p>
 * Description: tomcat指标定义
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
 * @createtime 2014-8-19 下午3:45:43
 * 
 */
public class TomcatStatisticsConstant {

	// 基本信息
	public static final String version = "PS-04-00-001";
	public static final String full_process = "PS-04-00-002";
	public static final String Created_tmp_disk_tables = "PS-04-00-004";
	public static final String Created_tmp_tables = "PS-04-00-005";
	public static final String Aborted_clients = "PS-04-00-006";
	public static final String Opened_tables = "PS-04-00-007";
	public static final String Key_reads = "PS-04-00-008";
	public static final String Com_rollback = "PS-04-00-009";
	public static final String Qcache_inserts = "PS-04-00-010";
	public static final String Binlog_cache_disk_use = "PS-04-00-011";
	public static final String Binlog_cache_use = "PS-04-00-012";

	// 数据库状态
	public static final String state = "PS-04-01-001";
	public static final String Uptime = "PS-04-01-002";

	// 数据库Innodb
	public static final String Innodb_buffer_pool_pages_total = "PS-04-02-001";
	public static final String Innodb_page_size = "PS-04-02-002";
	public static final String Innodb_pages_created = "PS-04-02-003";
	public static final String Innodb_row_lock_current_waits = "PS-04-02-004";
	public static final String Innodb_row_lock_time = "PS-04-02-005";
	public static final String Innodb_row_lock_time_avg = "PS-04-02-006";
	public static final String Innodb_row_lock_time_max = "PS-04-02-007";
	public static final String Innodb_row_lock_waits = "PS-04-02-008";
}
