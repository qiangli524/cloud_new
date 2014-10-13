package com.sitech.ssd.ah.paas.domain.tab;

import java.util.List;

/**
 * 
 * <p>
 * Title: MysqlStatisticsObj
 * </p>
 * <p>
 * Description: Mysql各种指标属性
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
 * @createtime 2014-8-19 下午2:47:34
 * 
 */
public class MysqlStatisticsObj {

	// 基本信息
	private String version;
	private String status;
	private String slave_running;
	private String full_process;
	private String db_total;
	private String threads_connected;
	private String innodb_buffer_pool_pages_total;
	private String innodb_page_size;
	private String innodb_pages_created;
	private String aborted_clients;
	private String select_scan;
	private String threads_created;

	// 重要指标
	private String uptime;
	private String handler_read_first;
	private String innodb_buffer_pool_wait_free;
	private String max_used_connections;
	private String select_full_join;
	private String slow_queries;
	private String threads_running;

	// QPS(每秒查询处理量 MyISAM 引擎)
	private String QPS;

	// TPS（每秒传输的事物处理个数）
	// TPS = (Com_commit + Com_rollback) / seconds
	private String TPS;

	// Query Cache命中率
	// Query_cache_hits = (Qcache_hits / (Qcache_hits + Qcache_inserts ))*100%;
	private String query_cache_hits;
	private String qcache_hits;
	private String qcache_inserts;

	// key Buffer 命中率
	// key_buffer_read_unhits 未命中率
	private String key_buffer_read_unhits;
	private String key_reads;
	private String key_read_requests;
	private String key_write_requests;
	private String key_writes;

	// Table Cache状态量
	private String opened_tables;
	private String open_tables;
	private String open_files;
	private String open_streams;
	private String open_table_definitions;
	private String opened_files;
	private String opened_table_definitions;

	// 锁定状态
	private String innodb_row_lock_current_waits;
	private String innodb_row_lock_time;
	private String innodb_row_lock_time_avg;
	private String innodb_row_lock_time_max;
	private String innodb_row_lock_waits;

	// Tmp Table 状况(临时表状况)
	private String created_tmp_disk_tables;
	private String created_tmp_tables;
	private String created_tmp_files;

	// Binlog Cache 使用状况
	private String binlog_cache_disk_use;
	private String binlog_cache_use;
	private String binlog_stmt_cache_disk_use;
	private String binlog_stmt_cache_use;

	private String entity_id;
	private String entity_name;
	private List resultList;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSlave_running() {
		return slave_running;
	}

	public void setSlave_running(String slave_running) {
		this.slave_running = slave_running;
	}

	public String getFull_process() {
		return full_process;
	}

	public void setFull_process(String full_process) {
		this.full_process = full_process;
	}

	public String getDb_total() {
		return db_total;
	}

	public void setDb_total(String db_total) {
		this.db_total = db_total;
	}

	public String getThreads_connected() {
		return threads_connected;
	}

	public void setThreads_connected(String threads_connected) {
		this.threads_connected = threads_connected;
	}

	public String getInnodb_buffer_pool_pages_total() {
		return innodb_buffer_pool_pages_total;
	}

	public void setInnodb_buffer_pool_pages_total(String innodb_buffer_pool_pages_total) {
		this.innodb_buffer_pool_pages_total = innodb_buffer_pool_pages_total;
	}

	public String getInnodb_page_size() {
		return innodb_page_size;
	}

	public void setInnodb_page_size(String innodb_page_size) {
		this.innodb_page_size = innodb_page_size;
	}

	public String getInnodb_pages_created() {
		return innodb_pages_created;
	}

	public void setInnodb_pages_created(String innodb_pages_created) {
		this.innodb_pages_created = innodb_pages_created;
	}

	public String getAborted_clients() {
		return aborted_clients;
	}

	public void setAborted_clients(String aborted_clients) {
		this.aborted_clients = aborted_clients;
	}

	public String getSelect_scan() {
		return select_scan;
	}

	public void setSelect_scan(String select_scan) {
		this.select_scan = select_scan;
	}

	public String getThreads_created() {
		return threads_created;
	}

	public void setThreads_created(String threads_created) {
		this.threads_created = threads_created;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public String getHandler_read_first() {
		return handler_read_first;
	}

	public void setHandler_read_first(String handler_read_first) {
		this.handler_read_first = handler_read_first;
	}

	public String getInnodb_buffer_pool_wait_free() {
		return innodb_buffer_pool_wait_free;
	}

	public void setInnodb_buffer_pool_wait_free(String innodb_buffer_pool_wait_free) {
		this.innodb_buffer_pool_wait_free = innodb_buffer_pool_wait_free;
	}

	public String getMax_used_connections() {
		return max_used_connections;
	}

	public void setMax_used_connections(String max_used_connections) {
		this.max_used_connections = max_used_connections;
	}

	public String getSelect_full_join() {
		return select_full_join;
	}

	public void setSelect_full_join(String select_full_join) {
		this.select_full_join = select_full_join;
	}

	public String getSlow_queries() {
		return slow_queries;
	}

	public void setSlow_queries(String slow_queries) {
		this.slow_queries = slow_queries;
	}

	public String getThreads_running() {
		return threads_running;
	}

	public void setThreads_running(String threads_running) {
		this.threads_running = threads_running;
	}

	public String getQPS() {
		return QPS;
	}

	public void setQPS(String qPS) {
		QPS = qPS;
	}

	public String getTPS() {
		return TPS;
	}

	public void setTPS(String tPS) {
		TPS = tPS;
	}

	public String getQuery_cache_hits() {
		return query_cache_hits;
	}

	public void setQuery_cache_hits(String query_cache_hits) {
		this.query_cache_hits = query_cache_hits;
	}

	public String getQcache_hits() {
		return qcache_hits;
	}

	public void setQcache_hits(String qcache_hits) {
		this.qcache_hits = qcache_hits;
	}

	public String getQcache_inserts() {
		return qcache_inserts;
	}

	public void setQcache_inserts(String qcache_inserts) {
		this.qcache_inserts = qcache_inserts;
	}

	public String getKey_buffer_read_unhits() {
		return key_buffer_read_unhits;
	}

	public void setKey_buffer_read_unhits(String key_buffer_read_unhits) {
		this.key_buffer_read_unhits = key_buffer_read_unhits;
	}

	public String getKey_reads() {
		return key_reads;
	}

	public void setKey_reads(String key_reads) {
		this.key_reads = key_reads;
	}

	public String getKey_read_requests() {
		return key_read_requests;
	}

	public void setKey_read_requests(String key_read_requests) {
		this.key_read_requests = key_read_requests;
	}

	public String getKey_write_requests() {
		return key_write_requests;
	}

	public void setKey_write_requests(String key_write_requests) {
		this.key_write_requests = key_write_requests;
	}

	public String getKey_writes() {
		return key_writes;
	}

	public void setKey_writes(String key_writes) {
		this.key_writes = key_writes;
	}

	public String getOpened_tables() {
		return opened_tables;
	}

	public void setOpened_tables(String opened_tables) {
		this.opened_tables = opened_tables;
	}

	public String getOpen_tables() {
		return open_tables;
	}

	public void setOpen_tables(String open_tables) {
		this.open_tables = open_tables;
	}

	public String getOpen_files() {
		return open_files;
	}

	public void setOpen_files(String open_files) {
		this.open_files = open_files;
	}

	public String getOpen_streams() {
		return open_streams;
	}

	public void setOpen_streams(String open_streams) {
		this.open_streams = open_streams;
	}

	public String getOpen_table_definitions() {
		return open_table_definitions;
	}

	public void setOpen_table_definitions(String open_table_definitions) {
		this.open_table_definitions = open_table_definitions;
	}

	public String getOpened_files() {
		return opened_files;
	}

	public void setOpened_files(String opened_files) {
		this.opened_files = opened_files;
	}

	public String getOpened_table_definitions() {
		return opened_table_definitions;
	}

	public void setOpened_table_definitions(String opened_table_definitions) {
		this.opened_table_definitions = opened_table_definitions;
	}

	public String getInnodb_row_lock_current_waits() {
		return innodb_row_lock_current_waits;
	}

	public void setInnodb_row_lock_current_waits(String innodb_row_lock_current_waits) {
		this.innodb_row_lock_current_waits = innodb_row_lock_current_waits;
	}

	public String getInnodb_row_lock_time() {
		return innodb_row_lock_time;
	}

	public void setInnodb_row_lock_time(String innodb_row_lock_time) {
		this.innodb_row_lock_time = innodb_row_lock_time;
	}

	public String getInnodb_row_lock_time_avg() {
		return innodb_row_lock_time_avg;
	}

	public void setInnodb_row_lock_time_avg(String innodb_row_lock_time_avg) {
		this.innodb_row_lock_time_avg = innodb_row_lock_time_avg;
	}

	public String getInnodb_row_lock_time_max() {
		return innodb_row_lock_time_max;
	}

	public void setInnodb_row_lock_time_max(String innodb_row_lock_time_max) {
		this.innodb_row_lock_time_max = innodb_row_lock_time_max;
	}

	public String getInnodb_row_lock_waits() {
		return innodb_row_lock_waits;
	}

	public void setInnodb_row_lock_waits(String innodb_row_lock_waits) {
		this.innodb_row_lock_waits = innodb_row_lock_waits;
	}

	public String getCreated_tmp_disk_tables() {
		return created_tmp_disk_tables;
	}

	public void setCreated_tmp_disk_tables(String created_tmp_disk_tables) {
		this.created_tmp_disk_tables = created_tmp_disk_tables;
	}

	public String getCreated_tmp_tables() {
		return created_tmp_tables;
	}

	public void setCreated_tmp_tables(String created_tmp_tables) {
		this.created_tmp_tables = created_tmp_tables;
	}

	public String getCreated_tmp_files() {
		return created_tmp_files;
	}

	public void setCreated_tmp_files(String created_tmp_files) {
		this.created_tmp_files = created_tmp_files;
	}

	public String getBinlog_cache_disk_use() {
		return binlog_cache_disk_use;
	}

	public void setBinlog_cache_disk_use(String binlog_cache_disk_use) {
		this.binlog_cache_disk_use = binlog_cache_disk_use;
	}

	public String getBinlog_cache_use() {
		return binlog_cache_use;
	}

	public void setBinlog_cache_use(String binlog_cache_use) {
		this.binlog_cache_use = binlog_cache_use;
	}

	public String getBinlog_stmt_cache_disk_use() {
		return binlog_stmt_cache_disk_use;
	}

	public void setBinlog_stmt_cache_disk_use(String binlog_stmt_cache_disk_use) {
		this.binlog_stmt_cache_disk_use = binlog_stmt_cache_disk_use;
	}

	public String getBinlog_stmt_cache_use() {
		return binlog_stmt_cache_use;
	}

	public void setBinlog_stmt_cache_use(String binlog_stmt_cache_use) {
		this.binlog_stmt_cache_use = binlog_stmt_cache_use;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getEntity_name() {
		return entity_name;
	}

	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

}
