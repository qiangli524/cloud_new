package com.sitech.ssd.ah.paas.util;

public class PaasStatisticsConstant {
	// oracle数据库
	public static final String problemtbsp_pctused_racdb = "PS-03-02-003";// 表空间占用率
	public static final String scn_growth_statistics_scn_health_racdb = "PS-03-02-004";// SCN健康值
	public static final String userblock_count_racdb = "PS-03-04-013";// 最大阻塞会话数

	// oracle数据库实例
	public static final String file_read_cs = "PS-03-05-003";// 平均文件读取时间
	public static final String file_write_cs = "PS-03-05-004";// 平均文件写入时间
	public static final String sql_response_time = "PS-03-05-001";// 当前SQL平均响应时间
	public static final String instance_throughput_parses_ps = "PS-03-03-002";// 解析次数(每秒)
	public static final String userblock_count = "PS-03-04-003";// 最大阻塞会话数
	public static final String instance_throughput_commits_ps = "PS-03-03-001";// 用户提交数（每秒）
	public static final String scn_growth_statistics_scn_health = "PS-03-02-001";// SCN健康值
	public static final String tbspallocation_spaceallocated = "PS-03-02-002";// 表空间占用率
	public static final String response_status = "PS-03-01-001";// 实例状态
	public static final String ha_infoLog_mode = "PS-03-01-002";// 归档状态
	public static final String instance_efficiency_cpuusage_ps = "PS-03-04-010";// CPU占用率(每一事物处理)
	public static final String memory_usage_total_memory = "PS-03-07-001";// 内存使用量
	public static final String invalid_objects_count = "PS-03-06-001";// 无效对象总数

	// oracle数据库服务
	public static final String dbservice_execs_delta = "PS-03-04-001";// 执行计数
	public static final String dbservice_physrds_delta = "PS-03-04-002";// 服务物理读数
	public static final String dbservice_clus_wtime_delta = "PS-03-04-004";// 集群等待时间
	public static final String dbservice_calls_delta = "PS-03-04-005";// 服务call总数
	public static final String dbservice_conc_wtime_delta = "PS-03-04-006";// 并行等待时间
	public static final String dbservice_dbtime_delta = "PS-03-04-007";// 数据库时间
	public static final String dbservice_physwrts_delta = "PS-03-04-008";// 物理写入数
	public static final String dbservice_dbcpu_delta = "PS-03-04-009";// 数据库CPU
	public static final String dbservice_txns_delta = "PS-03-04-011";// 事物处理数
	public static final String dbservice_io_wtime_delta = "PS-03-04-012";// 用户I/O等待时间
	public static final String dbservice_elapsed_time_delta = "PS-03-05-002";// 每次用户调用的服务响应时间
	public static final String dbservice_cpuutil = "PS-03-07-002";// 每次用户调用的服务CPU时间

	// oracle,weblogic健康度
	public static final String health_score_availability = "PS-03-08-001";// 健康度（可用性）
	public static final String health_score_capacity = "PS-03-08-002";// 健康度(容量)
	public static final String health_score_efficiency = "PS-03-08-003";// 健康度(负载)
	public static final String health_score_exception = "PS-03-08-004";// 健康度(异常)
	public static final String health_score_resource = "PS-03-08-005";// 健康度(资源)
	public static final String health_score_response = "PS-03-08-006";// 健康度(响应)
	public static final String health_score_total = "PS-03-08-007";// 健康度(总体)
	public static final String health_score_average = "PS-03-08-008";// 健康度(业务健康度)

	// weblogic数据源
	public static final String datasource_state_state = "PS-05-01-001";// 数据源状态
	public static final String datasource_connectionRequests_throughput = "PS-05-04-003";// 数据源连接请求数(每分钟)
	public static final String datasource_connectionCreated_time = "PS-05-05-002";// 数据源连接创建时间(毫秒)
	public static final String datasource_connectionPoolSize_active = "PS-05-02-003";// 数据源连接池大小
	public static final String datasource_connectionAvailable_active = "PS-05-02-004";// 数据源可用连接数
	public static final String datasource_connectionUnavailable_active = "PS-05-06-003";// 数据源不可用连接数
	public static final String datasource_connectionRequestFailures_throughput = "PS-05-06-001";// 数据源连接请求失败数(每分钟)

	// weblogic服务
	public static final String server_servlet_jsp_service_throughput = "PS-05-04-001";// 页面请求处理数(每分钟)(load)
	public static final String thread_pool_pendingRequests_active = "PS-05-04-002";// 线程池暂挂请求数(load)
	public static final String thread_pool_totalThreads_active = "PS-05-02-001";// 线程池线程总数(load)
	public static final String jvm_garbage_collectors_invocationsPerMin = "PS-05-04-004";// GC调用数(每分钟)(load)
	public static final String server_level_work_manager_wmProcessed_throughput = "PS-05-07-002";// 工作管理器请求数(每分钟)(load)

	public static final String server_1_sockets_active = "PS-05-07-001";// 活动套接字数(resource)
	public static final String jvm_garbage_collectors_percentTimeInGc = "PS-05-04-005";// GC时间成本(%)(resource)
	public static final String jvm_heapUsedPercentage_value = "PS-05-07-003";// JVM堆使用率(%)(resource)
	public static final String jvm_threads_peakThreadCount_value = "PS-05-07-004";// JVM线程峰值数(resource)
	public static final String jvm_memory_usage_nonHeapMemoryUsed_value = "PS-05-07-005";// JVM非堆内存使用量(KB)(resource)
	public static final String jvm_memory_usage_heapMemoryUsed_value = "PS-05-07-006";// JVM堆内存使用量(KB)(resource)
	public static final String jvm_cpuUsage_percentage = "PS-05-07-007";// CPU占用率(%)(resource)

	public static final String server_state_state = "PS-05-01-002";// 实例状态(availability)
	public static final String thread_pool_idleThreads_active = "PS-05-02-002";// 线程池空闲线程数(capacity)

	public static final String server_servlet_jsp_service_time = "PS-05-05-001";// 页面请求处理时间(毫秒)(response)
	public static final String jvm_garbage_collectors_msecsPerInvocation = "PS-05-05-003";// GC调用时间(毫秒)(response)
	public static final String server_level_work_manager_wmStuck_active = "PS-05-06-002";// 工作管理器粘滞线程数(exception)
	public static final String jvm_threads_deadlockedThreadCount_value = "PS-05-06-004";// JVM死锁线程数(exception)

}
