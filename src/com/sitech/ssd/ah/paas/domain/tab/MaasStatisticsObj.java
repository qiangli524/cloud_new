package com.sitech.ssd.ah.paas.domain.tab;

import java.util.List;

/**
 * 
 * <p>
 * Title: MaasStatisticsObj
 * </p>
 * <p>
 * Description: maas相关属性
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
 * @createtime 2014-6-26 下午4:40:33
 * 
 */
public class MaasStatisticsObj {
	// weblogic数据源
	private String datasource_state_state;// 数据源状态(availability)
	private String datasource_connectionRequestFailures_throughput;// 数据源连接请求失败数(每分钟)(exception)
	private String datasource_connectionRequests_throughput;// 数据源连接请求数(每分钟)(load)
	private String datasource_connectionCreated_time;// 数据源连接创建时间(毫秒)(response)
	private String datasource_connectionPoolSize_active;// 数据源连接池大小(capacity)
	private String datasource_connectionAvailable_active;// 数据源可用连接数(capacity)
	private String datasource_connectionUnavailable_active;// 数据源不可用连接数(exception)

	// weblogic服务
	private String server_servlet_jsp_service_time;// 页面请求处理时间(毫秒)
	private String server_servlet_jsp_service_throughput;// 页面请求处理数(每分钟)
	private String thread_pool_pendingRequests_active;// 线程池暂挂请求数
	private String thread_pool_totalThreads_active;// 线程池线程总数
	private String thread_pool_idleThreads_active;// 线程池空闲线程数
	private String jvm_garbage_collectors_invocationsPerMin;// GC调用数(每分钟)
	private String jvm_garbage_collectors_msecsPerInvocation;// GC调用时间(毫秒)
	private String jvm_garbage_collectors_percentTimeInGc;// GC时间成本(%)
	private String server_1_sockets_active;// 活动套接字数
	private String server_level_work_manager_wmStuck_active;// 工作管理器粘滞线程数
	private String server_level_work_manager_wmProcessed_throughput;// 工作管理器请求数(每分钟)
	private String server_state_state;// 实例状态
	private String jvm_heapUsedPercentage_value;// JVM堆使用率(%)
	private String jvm_threads_peakThreadCount_value;// JVM线程峰值数
	private String jvm_threads_deadlockedThreadCount_value;// JVM死锁线程数
	private String jvm_memory_usage_nonHeapMemoryUsed_value;// JVM非堆内存使用量(KB)
	private String jvm_memory_usage_heapMemoryUsed_value;// JVM堆内存使用量(KB)
	private String jvm_cpuUsage_percentage;// CPU占用率(%)

	// weblogic数据源健康度
	private String datasource_state_state_score;// 数据源状态
	private String datasource_connectionRequestFailures_throughput_score;// 数据源连接请求失败数(每分钟)
	private String datasource_connectionRequests_throughput_score;// 数据源连接请求数(每分钟)
	private String datasource_connectionCreated_time_score;// 数据源连接创建时间(毫秒)
	private String datasource_connectionPoolSize_active_score;// 数据源连接池大小
	private String datasource_connectionAvailable_active_score;// 数据源可用连接数
	private String datasource_connectionUnavailable_active_score;// 数据源不可用连接数

	// weblogic服务健康度
	private String server_servlet_jsp_service_time_score;// 页面请求处理时间(毫秒)
	private String server_servlet_jsp_service_throughput_score;// 页面请求处理数(每分钟)
	private String thread_pool_pendingRequests_active_score;// 线程池暂挂请求数
	private String thread_pool_totalThreads_active_score;// 线程池线程总数
	private String thread_pool_idleThreads_active_score;// 线程池空闲线程数
	private String jvm_garbage_collectors_invocationsPerMin_score;// GC调用数(每分钟)
	private String jvm_garbage_collectors_msecsPerInvocation_score;// GC调用时间(毫秒)
	private String jvm_garbage_collectors_percentTimeInGc_score;// GC时间成本(%)
	private String server_1_sockets_active_score;// 活动套接字数
	private String server_level_work_manager_wmStuck_active_score;// 工作管理器粘滞线程数
	private String server_level_work_manager_wmProcessed_throughput_score;// 工作管理器请求数(每分钟)
	private String server_state_state_score;// 实例状态
	private String jvm_heapUsedPercentage_value_score;// JVM堆使用率(%)
	private String jvm_threads_peakThreadCount_value_score;// JVM线程峰值数
	private String jvm_threads_deadlockedThreadCount_value_score;// JVM死锁线程数
	private String jvm_memory_usage_nonHeapMemoryUsed_value_score;// JVM非堆内存使用量(KB)
	private String jvm_memory_usage_heapMemoryUsed_value_score;// JVM堆内存使用量(KB)
	private String jvm_cpuUsage_percentage_score;// CPU占用率(%)

	// 健康度
	private String health_score_availability;// 健康度（可用性）
	private String health_score_capacity;// 健康度(容量)
	private String health_score_efficiency;// 健康度(效率)
	private String health_score_exception;// 健康度(异常)
	private String health_score_resource;// 健康度(资源)
	private String health_score_response;// 健康度(响应)
	private String health_score_total;// 健康度(总体)
	private String entity_id;
	private String entity_name;
	private List resultList;

	public String getDatasource_state_state() {
		return datasource_state_state;
	}

	public void setDatasource_state_state(String datasource_state_state) {
		this.datasource_state_state = datasource_state_state;
	}

	public String getDatasource_connectionRequestFailures_throughput() {
		return datasource_connectionRequestFailures_throughput;
	}

	public void setDatasource_connectionRequestFailures_throughput(
			String datasource_connectionRequestFailures_throughput) {
		this.datasource_connectionRequestFailures_throughput = datasource_connectionRequestFailures_throughput;
	}

	public String getDatasource_connectionRequests_throughput() {
		return datasource_connectionRequests_throughput;
	}

	public void setDatasource_connectionRequests_throughput(
			String datasource_connectionRequests_throughput) {
		this.datasource_connectionRequests_throughput = datasource_connectionRequests_throughput;
	}

	public String getDatasource_connectionCreated_time() {
		return datasource_connectionCreated_time;
	}

	public void setDatasource_connectionCreated_time(String datasource_connectionCreated_time) {
		this.datasource_connectionCreated_time = datasource_connectionCreated_time;
	}

	public String getDatasource_connectionPoolSize_active() {
		return datasource_connectionPoolSize_active;
	}

	public void setDatasource_connectionPoolSize_active(String datasource_connectionPoolSize_active) {
		this.datasource_connectionPoolSize_active = datasource_connectionPoolSize_active;
	}

	public String getDatasource_connectionAvailable_active() {
		return datasource_connectionAvailable_active;
	}

	public void setDatasource_connectionAvailable_active(
			String datasource_connectionAvailable_active) {
		this.datasource_connectionAvailable_active = datasource_connectionAvailable_active;
	}

	public String getDatasource_connectionUnavailable_active() {
		return datasource_connectionUnavailable_active;
	}

	public void setDatasource_connectionUnavailable_active(
			String datasource_connectionUnavailable_active) {
		this.datasource_connectionUnavailable_active = datasource_connectionUnavailable_active;
	}

	public String getServer_servlet_jsp_service_time() {
		return server_servlet_jsp_service_time;
	}

	public void setServer_servlet_jsp_service_time(String server_servlet_jsp_service_time) {
		this.server_servlet_jsp_service_time = server_servlet_jsp_service_time;
	}

	public String getServer_servlet_jsp_service_throughput() {
		return server_servlet_jsp_service_throughput;
	}

	public void setServer_servlet_jsp_service_throughput(
			String server_servlet_jsp_service_throughput) {
		this.server_servlet_jsp_service_throughput = server_servlet_jsp_service_throughput;
	}

	public String getThread_pool_pendingRequests_active() {
		return thread_pool_pendingRequests_active;
	}

	public void setThread_pool_pendingRequests_active(String thread_pool_pendingRequests_active) {
		this.thread_pool_pendingRequests_active = thread_pool_pendingRequests_active;
	}

	public String getThread_pool_totalThreads_active() {
		return thread_pool_totalThreads_active;
	}

	public void setThread_pool_totalThreads_active(String thread_pool_totalThreads_active) {
		this.thread_pool_totalThreads_active = thread_pool_totalThreads_active;
	}

	public String getThread_pool_idleThreads_active() {
		return thread_pool_idleThreads_active;
	}

	public void setThread_pool_idleThreads_active(String thread_pool_idleThreads_active) {
		this.thread_pool_idleThreads_active = thread_pool_idleThreads_active;
	}

	public String getJvm_garbage_collectors_invocationsPerMin() {
		return jvm_garbage_collectors_invocationsPerMin;
	}

	public void setJvm_garbage_collectors_invocationsPerMin(
			String jvm_garbage_collectors_invocationsPerMin) {
		this.jvm_garbage_collectors_invocationsPerMin = jvm_garbage_collectors_invocationsPerMin;
	}

	public String getJvm_garbage_collectors_msecsPerInvocation() {
		return jvm_garbage_collectors_msecsPerInvocation;
	}

	public void setJvm_garbage_collectors_msecsPerInvocation(
			String jvm_garbage_collectors_msecsPerInvocation) {
		this.jvm_garbage_collectors_msecsPerInvocation = jvm_garbage_collectors_msecsPerInvocation;
	}

	public String getJvm_garbage_collectors_percentTimeInGc() {
		return jvm_garbage_collectors_percentTimeInGc;
	}

	public void setJvm_garbage_collectors_percentTimeInGc(
			String jvm_garbage_collectors_percentTimeInGc) {
		this.jvm_garbage_collectors_percentTimeInGc = jvm_garbage_collectors_percentTimeInGc;
	}

	public String getServer_1_sockets_active() {
		return server_1_sockets_active;
	}

	public void setServer_1_sockets_active(String server_1_sockets_active) {
		this.server_1_sockets_active = server_1_sockets_active;
	}

	public String getServer_level_work_manager_wmStuck_active() {
		return server_level_work_manager_wmStuck_active;
	}

	public void setServer_level_work_manager_wmStuck_active(
			String server_level_work_manager_wmStuck_active) {
		this.server_level_work_manager_wmStuck_active = server_level_work_manager_wmStuck_active;
	}

	public String getServer_level_work_manager_wmProcessed_throughput() {
		return server_level_work_manager_wmProcessed_throughput;
	}

	public void setServer_level_work_manager_wmProcessed_throughput(
			String server_level_work_manager_wmProcessed_throughput) {
		this.server_level_work_manager_wmProcessed_throughput = server_level_work_manager_wmProcessed_throughput;
	}

	public String getServer_state_state() {
		return server_state_state;
	}

	public void setServer_state_state(String server_state_state) {
		this.server_state_state = server_state_state;
	}

	public String getJvm_heapUsedPercentage_value() {
		return jvm_heapUsedPercentage_value;
	}

	public void setJvm_heapUsedPercentage_value(String jvm_heapUsedPercentage_value) {
		this.jvm_heapUsedPercentage_value = jvm_heapUsedPercentage_value;
	}

	public String getJvm_threads_peakThreadCount_value() {
		return jvm_threads_peakThreadCount_value;
	}

	public void setJvm_threads_peakThreadCount_value(String jvm_threads_peakThreadCount_value) {
		this.jvm_threads_peakThreadCount_value = jvm_threads_peakThreadCount_value;
	}

	public String getJvm_threads_deadlockedThreadCount_value() {
		return jvm_threads_deadlockedThreadCount_value;
	}

	public void setJvm_threads_deadlockedThreadCount_value(
			String jvm_threads_deadlockedThreadCount_value) {
		this.jvm_threads_deadlockedThreadCount_value = jvm_threads_deadlockedThreadCount_value;
	}

	public String getJvm_memory_usage_nonHeapMemoryUsed_value() {
		return jvm_memory_usage_nonHeapMemoryUsed_value;
	}

	public void setJvm_memory_usage_nonHeapMemoryUsed_value(
			String jvm_memory_usage_nonHeapMemoryUsed_value) {
		this.jvm_memory_usage_nonHeapMemoryUsed_value = jvm_memory_usage_nonHeapMemoryUsed_value;
	}

	public String getJvm_memory_usage_heapMemoryUsed_value() {
		return jvm_memory_usage_heapMemoryUsed_value;
	}

	public void setJvm_memory_usage_heapMemoryUsed_value(
			String jvm_memory_usage_heapMemoryUsed_value) {
		this.jvm_memory_usage_heapMemoryUsed_value = jvm_memory_usage_heapMemoryUsed_value;
	}

	public String getJvm_cpuUsage_percentage() {
		return jvm_cpuUsage_percentage;
	}

	public void setJvm_cpuUsage_percentage(String jvm_cpuUsage_percentage) {
		this.jvm_cpuUsage_percentage = jvm_cpuUsage_percentage;
	}

	public String getDatasource_state_state_score() {
		return datasource_state_state_score;
	}

	public void setDatasource_state_state_score(String datasource_state_state_score) {
		this.datasource_state_state_score = datasource_state_state_score;
	}

	public String getDatasource_connectionRequestFailures_throughput_score() {
		return datasource_connectionRequestFailures_throughput_score;
	}

	public void setDatasource_connectionRequestFailures_throughput_score(
			String datasource_connectionRequestFailures_throughput_score) {
		this.datasource_connectionRequestFailures_throughput_score = datasource_connectionRequestFailures_throughput_score;
	}

	public String getDatasource_connectionRequests_throughput_score() {
		return datasource_connectionRequests_throughput_score;
	}

	public void setDatasource_connectionRequests_throughput_score(
			String datasource_connectionRequests_throughput_score) {
		this.datasource_connectionRequests_throughput_score = datasource_connectionRequests_throughput_score;
	}

	public String getDatasource_connectionCreated_time_score() {
		return datasource_connectionCreated_time_score;
	}

	public void setDatasource_connectionCreated_time_score(
			String datasource_connectionCreated_time_score) {
		this.datasource_connectionCreated_time_score = datasource_connectionCreated_time_score;
	}

	public String getDatasource_connectionPoolSize_active_score() {
		return datasource_connectionPoolSize_active_score;
	}

	public void setDatasource_connectionPoolSize_active_score(
			String datasource_connectionPoolSize_active_score) {
		this.datasource_connectionPoolSize_active_score = datasource_connectionPoolSize_active_score;
	}

	public String getDatasource_connectionAvailable_active_score() {
		return datasource_connectionAvailable_active_score;
	}

	public void setDatasource_connectionAvailable_active_score(
			String datasource_connectionAvailable_active_score) {
		this.datasource_connectionAvailable_active_score = datasource_connectionAvailable_active_score;
	}

	public String getDatasource_connectionUnavailable_active_score() {
		return datasource_connectionUnavailable_active_score;
	}

	public void setDatasource_connectionUnavailable_active_score(
			String datasource_connectionUnavailable_active_score) {
		this.datasource_connectionUnavailable_active_score = datasource_connectionUnavailable_active_score;
	}

	public String getServer_servlet_jsp_service_time_score() {
		return server_servlet_jsp_service_time_score;
	}

	public void setServer_servlet_jsp_service_time_score(
			String server_servlet_jsp_service_time_score) {
		this.server_servlet_jsp_service_time_score = server_servlet_jsp_service_time_score;
	}

	public String getServer_servlet_jsp_service_throughput_score() {
		return server_servlet_jsp_service_throughput_score;
	}

	public void setServer_servlet_jsp_service_throughput_score(
			String server_servlet_jsp_service_throughput_score) {
		this.server_servlet_jsp_service_throughput_score = server_servlet_jsp_service_throughput_score;
	}

	public String getThread_pool_pendingRequests_active_score() {
		return thread_pool_pendingRequests_active_score;
	}

	public void setThread_pool_pendingRequests_active_score(
			String thread_pool_pendingRequests_active_score) {
		this.thread_pool_pendingRequests_active_score = thread_pool_pendingRequests_active_score;
	}

	public String getThread_pool_totalThreads_active_score() {
		return thread_pool_totalThreads_active_score;
	}

	public void setThread_pool_totalThreads_active_score(
			String thread_pool_totalThreads_active_score) {
		this.thread_pool_totalThreads_active_score = thread_pool_totalThreads_active_score;
	}

	public String getThread_pool_idleThreads_active_score() {
		return thread_pool_idleThreads_active_score;
	}

	public void setThread_pool_idleThreads_active_score(String thread_pool_idleThreads_active_score) {
		this.thread_pool_idleThreads_active_score = thread_pool_idleThreads_active_score;
	}

	public String getJvm_garbage_collectors_invocationsPerMin_score() {
		return jvm_garbage_collectors_invocationsPerMin_score;
	}

	public void setJvm_garbage_collectors_invocationsPerMin_score(
			String jvm_garbage_collectors_invocationsPerMin_score) {
		this.jvm_garbage_collectors_invocationsPerMin_score = jvm_garbage_collectors_invocationsPerMin_score;
	}

	public String getJvm_garbage_collectors_msecsPerInvocation_score() {
		return jvm_garbage_collectors_msecsPerInvocation_score;
	}

	public void setJvm_garbage_collectors_msecsPerInvocation_score(
			String jvm_garbage_collectors_msecsPerInvocation_score) {
		this.jvm_garbage_collectors_msecsPerInvocation_score = jvm_garbage_collectors_msecsPerInvocation_score;
	}

	public String getJvm_garbage_collectors_percentTimeInGc_score() {
		return jvm_garbage_collectors_percentTimeInGc_score;
	}

	public void setJvm_garbage_collectors_percentTimeInGc_score(
			String jvm_garbage_collectors_percentTimeInGc_score) {
		this.jvm_garbage_collectors_percentTimeInGc_score = jvm_garbage_collectors_percentTimeInGc_score;
	}

	public String getServer_1_sockets_active_score() {
		return server_1_sockets_active_score;
	}

	public void setServer_1_sockets_active_score(String server_1_sockets_active_score) {
		this.server_1_sockets_active_score = server_1_sockets_active_score;
	}

	public String getServer_level_work_manager_wmStuck_active_score() {
		return server_level_work_manager_wmStuck_active_score;
	}

	public void setServer_level_work_manager_wmStuck_active_score(
			String server_level_work_manager_wmStuck_active_score) {
		this.server_level_work_manager_wmStuck_active_score = server_level_work_manager_wmStuck_active_score;
	}

	public String getServer_level_work_manager_wmProcessed_throughput_score() {
		return server_level_work_manager_wmProcessed_throughput_score;
	}

	public void setServer_level_work_manager_wmProcessed_throughput_score(
			String server_level_work_manager_wmProcessed_throughput_score) {
		this.server_level_work_manager_wmProcessed_throughput_score = server_level_work_manager_wmProcessed_throughput_score;
	}

	public String getServer_state_state_score() {
		return server_state_state_score;
	}

	public void setServer_state_state_score(String server_state_state_score) {
		this.server_state_state_score = server_state_state_score;
	}

	public String getJvm_heapUsedPercentage_value_score() {
		return jvm_heapUsedPercentage_value_score;
	}

	public void setJvm_heapUsedPercentage_value_score(String jvm_heapUsedPercentage_value_score) {
		this.jvm_heapUsedPercentage_value_score = jvm_heapUsedPercentage_value_score;
	}

	public String getJvm_threads_peakThreadCount_value_score() {
		return jvm_threads_peakThreadCount_value_score;
	}

	public void setJvm_threads_peakThreadCount_value_score(
			String jvm_threads_peakThreadCount_value_score) {
		this.jvm_threads_peakThreadCount_value_score = jvm_threads_peakThreadCount_value_score;
	}

	public String getJvm_threads_deadlockedThreadCount_value_score() {
		return jvm_threads_deadlockedThreadCount_value_score;
	}

	public void setJvm_threads_deadlockedThreadCount_value_score(
			String jvm_threads_deadlockedThreadCount_value_score) {
		this.jvm_threads_deadlockedThreadCount_value_score = jvm_threads_deadlockedThreadCount_value_score;
	}

	public String getJvm_memory_usage_nonHeapMemoryUsed_value_score() {
		return jvm_memory_usage_nonHeapMemoryUsed_value_score;
	}

	public void setJvm_memory_usage_nonHeapMemoryUsed_value_score(
			String jvm_memory_usage_nonHeapMemoryUsed_value_score) {
		this.jvm_memory_usage_nonHeapMemoryUsed_value_score = jvm_memory_usage_nonHeapMemoryUsed_value_score;
	}

	public String getJvm_memory_usage_heapMemoryUsed_value_score() {
		return jvm_memory_usage_heapMemoryUsed_value_score;
	}

	public void setJvm_memory_usage_heapMemoryUsed_value_score(
			String jvm_memory_usage_heapMemoryUsed_value_score) {
		this.jvm_memory_usage_heapMemoryUsed_value_score = jvm_memory_usage_heapMemoryUsed_value_score;
	}

	public String getJvm_cpuUsage_percentage_score() {
		return jvm_cpuUsage_percentage_score;
	}

	public void setJvm_cpuUsage_percentage_score(String jvm_cpuUsage_percentage_score) {
		this.jvm_cpuUsage_percentage_score = jvm_cpuUsage_percentage_score;
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

	public String getHealth_score_availability() {
		return health_score_availability;
	}

	public void setHealth_score_availability(String health_score_availability) {
		this.health_score_availability = health_score_availability;
	}

	public String getHealth_score_capacity() {
		return health_score_capacity;
	}

	public void setHealth_score_capacity(String health_score_capacity) {
		this.health_score_capacity = health_score_capacity;
	}

	public String getHealth_score_efficiency() {
		return health_score_efficiency;
	}

	public void setHealth_score_efficiency(String health_score_efficiency) {
		this.health_score_efficiency = health_score_efficiency;
	}

	public String getHealth_score_exception() {
		return health_score_exception;
	}

	public void setHealth_score_exception(String health_score_exception) {
		this.health_score_exception = health_score_exception;
	}

	public String getHealth_score_resource() {
		return health_score_resource;
	}

	public void setHealth_score_resource(String health_score_resource) {
		this.health_score_resource = health_score_resource;
	}

	public String getHealth_score_response() {
		return health_score_response;
	}

	public void setHealth_score_response(String health_score_response) {
		this.health_score_response = health_score_response;
	}

	public String getHealth_score_total() {
		return health_score_total;
	}

	public void setHealth_score_total(String health_score_total) {
		this.health_score_total = health_score_total;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

}
