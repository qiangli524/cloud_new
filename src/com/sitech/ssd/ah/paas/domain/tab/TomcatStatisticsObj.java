package com.sitech.ssd.ah.paas.domain.tab;

import java.util.List;

/**
 * 
 * <p>
 * Title: TomcatStatisticsObj
 * </p>
 * <p>
 * Description: tomcat各种指标属性
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
public class TomcatStatisticsObj {
	// 基本信息
	private String version;
	private String full_process;
	private String created_tmp_disk_tables;
	private String created_tmp_tables;
	private String aborted_clients;
	private String opened_tables;
	private String key_reads;
	private String com_rollback;
	private String qcache_inserts;
	private String binlog_cache_disk_use;
	private String binlog_cache_use;

	// 数据库状态
	private String state;
	private String uptime;

	// 数据库innodb
	private String innodb_buffer_pool_pages_total;
	private String innodb_page_size;
	private String innodb_pages_created;
	private String innodb_row_lock_current_waits;
	private String innodb_row_lock_time;
	private String innodb_row_lock_time_avg;
	private String innodb_row_lock_time_max;
	private String innodb_row_lock_waits;

	private String entity_id;
	private String entity_name;
	private List resultList;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFull_process() {
		return full_process;
	}

	public void setFull_process(String full_process) {
		this.full_process = full_process;
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

	public String getAborted_clients() {
		return aborted_clients;
	}

	public void setAborted_clients(String aborted_clients) {
		this.aborted_clients = aborted_clients;
	}

	public String getOpened_tables() {
		return opened_tables;
	}

	public void setOpened_tables(String opened_tables) {
		this.opened_tables = opened_tables;
	}

	public String getKey_reads() {
		return key_reads;
	}

	public void setKey_reads(String key_reads) {
		this.key_reads = key_reads;
	}

	public String getCom_rollback() {
		return com_rollback;
	}

	public void setCom_rollback(String com_rollback) {
		this.com_rollback = com_rollback;
	}

	public String getQcache_inserts() {
		return qcache_inserts;
	}

	public void setQcache_inserts(String qcache_inserts) {
		this.qcache_inserts = qcache_inserts;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
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
