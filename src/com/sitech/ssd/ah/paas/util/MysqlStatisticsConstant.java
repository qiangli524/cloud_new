package com.sitech.ssd.ah.paas.util;

/**
 * 
 * <p>
 * Title: MysqlStatisticsConstant
 * </p>
 * <p>
 * Description: mysql指标定义
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
public class MysqlStatisticsConstant {

	// 基本信息
	public static final String version = "PS-04-00-001";
	public static final String status = "PS-04-00-002";
	public static final String slave_running = "PS-04-00-003";
	public static final String full_process = "PS-04-00-004";
	public static final String db_total = "PS-04-00-005";
	public static final String threads_connected = "PS-04-00-006";
	public static final String innodb_buffer_pool_pages_total = "PS-04-00-007";
	public static final String innodb_page_size = "PS-04-00-008";
	public static final String innodb_pages_created = "PS-04-00-009";
	public static final String aborted_clients = "PS-04-00-010";
	public static final String select_scan = "PS-04-00-011";
	public static final String threads_created = "PS-04-00-012";

	// 重要指标
	public static final String uptime = "PS-04-01-001";
	public static final String handler_read_first = "PS-04-01-002";
	public static final String innodb_buffer_pool_wait_free = "PS-04-01-003";
	public static final String max_used_connections = "PS-04-01-004";
	public static final String select_full_join = "PS-04-01-005";
	public static final String slow_queries = "PS-04-01-006";
	public static final String threads_running = "PS-04-01-007";

	// QPS(每秒查询处理量 MyISAM 引擎)
	public static final String QPS = "PS-04-02-001";// 每秒钟获得的查询数量

	// TPS（每秒传输的事物处理个数）
	// TPS = (Com_commit + Com_rollback) / seconds
	public static final String TPS = "PS-04-03-001";// 每秒传输的事物处理数

	// Query Cache命中率
	// Query_cache_hits = (Qcache_hits / (Qcache_hits + Qcache_inserts ))*100%;
	public static final String query_cache_hits = "PS-04-04-001";
	public static final String qcache_hits = "PS-04-04-002";// 查询缓存被访问的次数
	public static final String qcache_inserts = "PS-04-04-003";// 加入到缓存的查询数量

	// key Buffer 命中率
	// key_buffer_read_unhits 未命中率
	public static final String key_buffer_read_unhits = "PS-04-05-001";
	public static final String key_reads = "PS-04-05-002";// 读索引的次数
	public static final String key_read_requests = "PS-04-05-003";// 从缓存读键的数据块的请求数
	public static final String key_write_requests = "PS-04-05-004";// 将键的数据块写入缓存的请求数
	public static final String key_writes = "PS-04-05-005";// 向硬盘写入将键的数据块的物理写操作的次数

	// Table Cache状态量
	public static final String opened_tables = "PS-04-06-001";// 已经打开的表的数量
	public static final String open_tables = "PS-04-06-002";// 当前打开的表的数目
	public static final String open_files = "PS-04-06-003";// 打开的文件的数目
	public static final String open_streams = "PS-04-06-004";// 打开的流的数量
	public static final String open_table_definitions = "PS-04-06-005";// 被缓存的FRM文件数量
	public static final String opened_files = "PS-04-06-006";// 已经创建的临时文件的数量
	public static final String opened_table_definitions = "PS-04-06-007";// 被缓存过的FRM文件的数量

	// 锁定状态
	public static final String innodb_row_lock_current_waits = "PS-04-07-001";
	public static final String innodb_row_lock_time = "PS-04-07-002";
	public static final String innodb_row_lock_time_avg = "PS-04-07-003";
	public static final String innodb_row_lock_time_max = "PS-04-07-004";
	public static final String innodb_row_lock_waits = "PS-04-07-005";

	// Tmp Table 状况(临时表状况)
	public static final String created_tmp_disk_tables = "PS-04-08-001";// 服务器执行语句时在硬盘上自动创建的临时表的数量
	public static final String created_tmp_tables = "PS-04-08-002";// 服务器执行语句时自动创建的内存中的临时表的数量
	public static final String created_tmp_files = "PS-04-08-003";// mysqld已经创建的临时文件的数量mysqld已经创建的临时文件的数量

	// Binlog Cache 使用状况
	public static final String binlog_cache_disk_use = "PS-04-09-001";// 全索引的扫描次数
	public static final String binlog_cache_use = "PS-04-09-002";// 日志缓存的事务数量
	public static final String binlog_stmt_cache_disk_use = "PS-04-09-003";// 非事务语句使用二进制日志缓存
	public static final String binlog_stmt_cache_use = "PS-04-09-004";// 临时文件缓存的非事务数量
}
