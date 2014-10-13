package com.sitech.ssd.ah.paas.domain.tab;

import java.util.List;

/**
 * 
 * <p>
 * Title: DaasStatisticsObj
 * </p>
 * <p>
 * Description: daas各种指标属性
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
 * @createtime 2014-6-20 下午5:28:37
 * 
 */
public class DaasStatisticsObj {
	// 数据库
	private String problemtbsp_pctused_racdb;// 表空间占用率
	private String scn_growth_statistics_scn_health_racdb;// SCN健康值
	private String userblock_count_racdb;// 最大阻塞会话数

	// 数据库实例
	private String responseStatus;// 实例状态
	private String ha_infoLog_mode;// 归档状态
	private String scn_growth_statisticsScn_health;// SCN健康值
	private String tbspAllocationSpaceAllocated;// 表空间占用率
	private String instance_throughputParses_ps;// 解析次数(每秒)
	private String instance_throughputCommits_ps;// 用户提交数（每秒）
	private String instance_efficiencyCpuusage_ps;// CPU占用率(每一事物处理)
	private String userBlockCount;// 最大阻塞会话数
	private String fileRead_cs;// 平均文件读取时间
	private String sql_responseTime;// 当前SQL平均响应时间
	private String fileWrite_cs;// 平均文件写入时间
	private String invalid_objectsCount;// 无效对象总数
	private String memory_usageTotal_memory;// 内存使用量

	// 数据库服务
	private String dbServiceExecs_delta;// 执行计数
	private String dbServicePhysrds_delta;// 服务物理读数
	private String dbServiceClus_wtime_delta;// 集群等待时间
	private String dbServiceCalls_delta;// 服务call总数
	private String dbServiceConc_wtime_delta;// 并行等待时间
	private String dbServiceDBTime_delta;// 数据库时间
	private String dbServicePhyswrts_delta;// 物理写入数
	private String dbServiceDBcpu_delta;// 数据库CPU
	private String dbServiceTxns_delta;// 事物处理数
	private String dbServiceIO_wtime_delta;// 用户I/O等待时间
	private String dbServiceElapsed_time_delta;// 每次用户调用的服务响应时间
	private String dbServiceCpuUtil;// 每次用户调用的服务CPU时间

	// 健康度
	private String health_score_availability;// 健康度（可用性）
	private String health_score_capacity;// 健康度(容量)
	private String health_score_efficiency;// 健康度(效率)
	private String health_score_exception;// 健康度(异常)
	private String health_score_resource;// 健康度(资源)
	private String health_score_response;// 健康度(响应)
	private String health_score_total;// 健康度(总体)

	// 数据库健康度
	private String problemtbsp_pctused_racdb_score;// 表空间占用率
	private String scn_growth_statistics_scn_health_racdb_score;// SCN健康值
	private String userblock_count_racdb_score;// 最大阻塞会话数

	// 数据库实例健康度
	private String responseStatus_score;// 实例状态
	private String ha_infoLog_mode_score;// 归档状态
	private String scn_growth_statisticsScn_health_score;// SCN健康值
	private String tbspAllocationSpaceAllocated_score;// 表空间占用率
	private String instance_throughputParses_ps_score;// 解析次数(每秒)
	private String instance_efficiencyCpuusage_ps_score;// CPU占用率(每一事物处理)
	private String userBlockCount_score;// 最大阻塞会话数
	private String instance_throughputCommits_ps_score;// 用户提交数（每秒）
	private String fileRead_cs_score;// 平均文件读取时间
	private String sql_responseTime_score;// 当前SQL平均响应时间
	private String fileWrite_cs_score;// 平均文件写入时间
	private String invalid_objectsCount_score;// 无效对象总数
	private String memory_usageTotal_memory_score;// 内存使用量

	// 数据库服务健康度
	private String dbServiceExecs_delta_score;// 执行计数
	private String dbServicePhysrds_delta_score;// 服务物理读数
	private String dbServiceClus_wtime_delta_score;// 集群等待时间
	private String dbServiceCalls_delta_score;// 服务call总数
	private String dbServiceConc_wtime_delta_score;// 并行等待时间
	private String dbServiceDBTime_delta_score;// 数据库时间
	private String dbServicePhyswrts_delta_score;// 物理写入数
	private String dbServiceDBcpu_delta_score;// 数据库CPU
	private String dbServiceTxns_delta_score;// 事物处理数
	private String dbServiceIO_wtime_delta_score;// 用户I/O等待时间
	private String dbServiceElapsed_time_delta_score;// 每次用户调用的服务响应时间
	private String dbServiceCpuUtil_score;// 每次用户调用的服务CPU时间

	private int entityCount;
	private String entity_id;
	private String entity_name;
	private List resultList;

	public String getInstance_throughputCommits_ps() {
		return instance_throughputCommits_ps;
	}

	public void setInstance_throughputCommits_ps(String instance_throughputCommits_ps) {
		this.instance_throughputCommits_ps = instance_throughputCommits_ps;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getHa_infoLog_mode() {
		return ha_infoLog_mode;
	}

	public void setHa_infoLog_mode(String ha_infoLog_mode) {
		this.ha_infoLog_mode = ha_infoLog_mode;
	}

	public String getScn_growth_statisticsScn_health() {
		return scn_growth_statisticsScn_health;
	}

	public void setScn_growth_statisticsScn_health(String scn_growth_statisticsScn_health) {
		this.scn_growth_statisticsScn_health = scn_growth_statisticsScn_health;
	}

	public String getTbspAllocationSpaceAllocated() {
		return tbspAllocationSpaceAllocated;
	}

	public void setTbspAllocationSpaceAllocated(String tbspAllocationSpaceAllocated) {
		this.tbspAllocationSpaceAllocated = tbspAllocationSpaceAllocated;
	}

	public String getInstance_throughputParses_ps() {
		return instance_throughputParses_ps;
	}

	public void setInstance_throughputParses_ps(String instance_throughputParses_ps) {
		this.instance_throughputParses_ps = instance_throughputParses_ps;
	}

	public String getInstance_efficiencyCpuusage_ps() {
		return instance_efficiencyCpuusage_ps;
	}

	public void setInstance_efficiencyCpuusage_ps(String instance_efficiencyCpuusage_ps) {
		this.instance_efficiencyCpuusage_ps = instance_efficiencyCpuusage_ps;
	}

	public String getUserBlockCount() {
		return userBlockCount;
	}

	public void setUserBlockCount(String userBlockCount) {
		this.userBlockCount = userBlockCount;
	}

	public String getFileRead_cs() {
		return fileRead_cs;
	}

	public void setFileRead_cs(String fileRead_cs) {
		this.fileRead_cs = fileRead_cs;
	}

	public String getSql_responseTime() {
		return sql_responseTime;
	}

	public void setSql_responseTime(String sql_responseTime) {
		this.sql_responseTime = sql_responseTime;
	}

	public String getFileWrite_cs() {
		return fileWrite_cs;
	}

	public void setFileWrite_cs(String fileWrite_cs) {
		this.fileWrite_cs = fileWrite_cs;
	}

	public String getInvalid_objectsCount() {
		return invalid_objectsCount;
	}

	public void setInvalid_objectsCount(String invalid_objectsCount) {
		this.invalid_objectsCount = invalid_objectsCount;
	}

	public String getMemory_usageTotal_memory() {
		return memory_usageTotal_memory;
	}

	public void setMemory_usageTotal_memory(String memory_usageTotal_memory) {
		this.memory_usageTotal_memory = memory_usageTotal_memory;
	}

	public String getDbServiceExecs_delta() {
		return dbServiceExecs_delta;
	}

	public void setDbServiceExecs_delta(String dbServiceExecs_delta) {
		this.dbServiceExecs_delta = dbServiceExecs_delta;
	}

	public String getDbServicePhysrds_delta() {
		return dbServicePhysrds_delta;
	}

	public void setDbServicePhysrds_delta(String dbServicePhysrds_delta) {
		this.dbServicePhysrds_delta = dbServicePhysrds_delta;
	}

	public String getDbServiceClus_wtime_delta() {
		return dbServiceClus_wtime_delta;
	}

	public void setDbServiceClus_wtime_delta(String dbServiceClus_wtime_delta) {
		this.dbServiceClus_wtime_delta = dbServiceClus_wtime_delta;
	}

	public String getDbServiceCalls_delta() {
		return dbServiceCalls_delta;
	}

	public void setDbServiceCalls_delta(String dbServiceCalls_delta) {
		this.dbServiceCalls_delta = dbServiceCalls_delta;
	}

	public String getDbServiceConc_wtime_delta() {
		return dbServiceConc_wtime_delta;
	}

	public void setDbServiceConc_wtime_delta(String dbServiceConc_wtime_delta) {
		this.dbServiceConc_wtime_delta = dbServiceConc_wtime_delta;
	}

	public String getDbServiceDBTime_delta() {
		return dbServiceDBTime_delta;
	}

	public void setDbServiceDBTime_delta(String dbServiceDBTime_delta) {
		this.dbServiceDBTime_delta = dbServiceDBTime_delta;
	}

	public String getDbServicePhyswrts_delta() {
		return dbServicePhyswrts_delta;
	}

	public void setDbServicePhyswrts_delta(String dbServicePhyswrts_delta) {
		this.dbServicePhyswrts_delta = dbServicePhyswrts_delta;
	}

	public String getDbServiceDBcpu_delta() {
		return dbServiceDBcpu_delta;
	}

	public void setDbServiceDBcpu_delta(String dbServiceDBcpu_delta) {
		this.dbServiceDBcpu_delta = dbServiceDBcpu_delta;
	}

	public String getDbServiceTxns_delta() {
		return dbServiceTxns_delta;
	}

	public void setDbServiceTxns_delta(String dbServiceTxns_delta) {
		this.dbServiceTxns_delta = dbServiceTxns_delta;
	}

	public String getDbServiceIO_wtime_delta() {
		return dbServiceIO_wtime_delta;
	}

	public void setDbServiceIO_wtime_delta(String dbServiceIO_wtime_delta) {
		this.dbServiceIO_wtime_delta = dbServiceIO_wtime_delta;
	}

	public String getDbServiceElapsed_time_delta() {
		return dbServiceElapsed_time_delta;
	}

	public void setDbServiceElapsed_time_delta(String dbServiceElapsed_time_delta) {
		this.dbServiceElapsed_time_delta = dbServiceElapsed_time_delta;
	}

	public String getDbServiceCpuUtil() {
		return dbServiceCpuUtil;
	}

	public void setDbServiceCpuUtil(String dbServiceCpuUtil) {
		this.dbServiceCpuUtil = dbServiceCpuUtil;
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

	public String getInstance_throughputCommits_ps_score() {
		return instance_throughputCommits_ps_score;
	}

	public void setInstance_throughputCommits_ps_score(String instance_throughputCommits_ps_score) {
		this.instance_throughputCommits_ps_score = instance_throughputCommits_ps_score;
	}

	public String getResponseStatus_score() {
		return responseStatus_score;
	}

	public void setResponseStatus_score(String responseStatus_score) {
		this.responseStatus_score = responseStatus_score;
	}

	public String getHa_infoLog_mode_score() {
		return ha_infoLog_mode_score;
	}

	public void setHa_infoLog_mode_score(String ha_infoLog_mode_score) {
		this.ha_infoLog_mode_score = ha_infoLog_mode_score;
	}

	public String getScn_growth_statisticsScn_health_score() {
		return scn_growth_statisticsScn_health_score;
	}

	public void setScn_growth_statisticsScn_health_score(
			String scn_growth_statisticsScn_health_score) {
		this.scn_growth_statisticsScn_health_score = scn_growth_statisticsScn_health_score;
	}

	public String getTbspAllocationSpaceAllocated_score() {
		return tbspAllocationSpaceAllocated_score;
	}

	public void setTbspAllocationSpaceAllocated_score(String tbspAllocationSpaceAllocated_score) {
		this.tbspAllocationSpaceAllocated_score = tbspAllocationSpaceAllocated_score;
	}

	public String getInstance_throughputParses_ps_score() {
		return instance_throughputParses_ps_score;
	}

	public void setInstance_throughputParses_ps_score(String instance_throughputParses_ps_score) {
		this.instance_throughputParses_ps_score = instance_throughputParses_ps_score;
	}

	public String getInstance_efficiencyCpuusage_ps_score() {
		return instance_efficiencyCpuusage_ps_score;
	}

	public void setInstance_efficiencyCpuusage_ps_score(String instance_efficiencyCpuusage_ps_score) {
		this.instance_efficiencyCpuusage_ps_score = instance_efficiencyCpuusage_ps_score;
	}

	public String getUserBlockCount_score() {
		return userBlockCount_score;
	}

	public void setUserBlockCount_score(String userBlockCount_score) {
		this.userBlockCount_score = userBlockCount_score;
	}

	public String getFileRead_cs_score() {
		return fileRead_cs_score;
	}

	public void setFileRead_cs_score(String fileRead_cs_score) {
		this.fileRead_cs_score = fileRead_cs_score;
	}

	public String getSql_responseTime_score() {
		return sql_responseTime_score;
	}

	public void setSql_responseTime_score(String sql_responseTime_score) {
		this.sql_responseTime_score = sql_responseTime_score;
	}

	public String getFileWrite_cs_score() {
		return fileWrite_cs_score;
	}

	public void setFileWrite_cs_score(String fileWrite_cs_score) {
		this.fileWrite_cs_score = fileWrite_cs_score;
	}

	public String getInvalid_objectsCount_score() {
		return invalid_objectsCount_score;
	}

	public void setInvalid_objectsCount_score(String invalid_objectsCount_score) {
		this.invalid_objectsCount_score = invalid_objectsCount_score;
	}

	public String getMemory_usageTotal_memory_score() {
		return memory_usageTotal_memory_score;
	}

	public void setMemory_usageTotal_memory_score(String memory_usageTotal_memory_score) {
		this.memory_usageTotal_memory_score = memory_usageTotal_memory_score;
	}

	public String getDbServiceExecs_delta_score() {
		return dbServiceExecs_delta_score;
	}

	public void setDbServiceExecs_delta_score(String dbServiceExecs_delta_score) {
		this.dbServiceExecs_delta_score = dbServiceExecs_delta_score;
	}

	public String getDbServicePhysrds_delta_score() {
		return dbServicePhysrds_delta_score;
	}

	public void setDbServicePhysrds_delta_score(String dbServicePhysrds_delta_score) {
		this.dbServicePhysrds_delta_score = dbServicePhysrds_delta_score;
	}

	public String getDbServiceClus_wtime_delta_score() {
		return dbServiceClus_wtime_delta_score;
	}

	public void setDbServiceClus_wtime_delta_score(String dbServiceClus_wtime_delta_score) {
		this.dbServiceClus_wtime_delta_score = dbServiceClus_wtime_delta_score;
	}

	public String getDbServiceCalls_delta_score() {
		return dbServiceCalls_delta_score;
	}

	public void setDbServiceCalls_delta_score(String dbServiceCalls_delta_score) {
		this.dbServiceCalls_delta_score = dbServiceCalls_delta_score;
	}

	public String getDbServiceConc_wtime_delta_score() {
		return dbServiceConc_wtime_delta_score;
	}

	public void setDbServiceConc_wtime_delta_score(String dbServiceConc_wtime_delta_score) {
		this.dbServiceConc_wtime_delta_score = dbServiceConc_wtime_delta_score;
	}

	public String getDbServiceDBTime_delta_score() {
		return dbServiceDBTime_delta_score;
	}

	public void setDbServiceDBTime_delta_score(String dbServiceDBTime_delta_score) {
		this.dbServiceDBTime_delta_score = dbServiceDBTime_delta_score;
	}

	public String getDbServicePhyswrts_delta_score() {
		return dbServicePhyswrts_delta_score;
	}

	public void setDbServicePhyswrts_delta_score(String dbServicePhyswrts_delta_score) {
		this.dbServicePhyswrts_delta_score = dbServicePhyswrts_delta_score;
	}

	public String getDbServiceDBcpu_delta_score() {
		return dbServiceDBcpu_delta_score;
	}

	public void setDbServiceDBcpu_delta_score(String dbServiceDBcpu_delta_score) {
		this.dbServiceDBcpu_delta_score = dbServiceDBcpu_delta_score;
	}

	public String getDbServiceTxns_delta_score() {
		return dbServiceTxns_delta_score;
	}

	public void setDbServiceTxns_delta_score(String dbServiceTxns_delta_score) {
		this.dbServiceTxns_delta_score = dbServiceTxns_delta_score;
	}

	public String getDbServiceIO_wtime_delta_score() {
		return dbServiceIO_wtime_delta_score;
	}

	public void setDbServiceIO_wtime_delta_score(String dbServiceIO_wtime_delta_score) {
		this.dbServiceIO_wtime_delta_score = dbServiceIO_wtime_delta_score;
	}

	public String getDbServiceElapsed_time_delta_score() {
		return dbServiceElapsed_time_delta_score;
	}

	public void setDbServiceElapsed_time_delta_score(String dbServiceElapsed_time_delta_score) {
		this.dbServiceElapsed_time_delta_score = dbServiceElapsed_time_delta_score;
	}

	public String getDbServiceCpuUtil_score() {
		return dbServiceCpuUtil_score;
	}

	public void setDbServiceCpuUtil_score(String dbServiceCpuUtil_score) {
		this.dbServiceCpuUtil_score = dbServiceCpuUtil_score;
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

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getProblemtbsp_pctused_racdb() {
		return problemtbsp_pctused_racdb;
	}

	public void setProblemtbsp_pctused_racdb(String problemtbsp_pctused_racdb) {
		this.problemtbsp_pctused_racdb = problemtbsp_pctused_racdb;
	}

	public String getScn_growth_statistics_scn_health_racdb() {
		return scn_growth_statistics_scn_health_racdb;
	}

	public void setScn_growth_statistics_scn_health_racdb(
			String scn_growth_statistics_scn_health_racdb) {
		this.scn_growth_statistics_scn_health_racdb = scn_growth_statistics_scn_health_racdb;
	}

	public String getUserblock_count_racdb() {
		return userblock_count_racdb;
	}

	public void setUserblock_count_racdb(String userblock_count_racdb) {
		this.userblock_count_racdb = userblock_count_racdb;
	}

	public String getProblemtbsp_pctused_racdb_score() {
		return problemtbsp_pctused_racdb_score;
	}

	public void setProblemtbsp_pctused_racdb_score(String problemtbsp_pctused_racdb_score) {
		this.problemtbsp_pctused_racdb_score = problemtbsp_pctused_racdb_score;
	}

	public String getScn_growth_statistics_scn_health_racdb_score() {
		return scn_growth_statistics_scn_health_racdb_score;
	}

	public void setScn_growth_statistics_scn_health_racdb_score(
			String scn_growth_statistics_scn_health_racdb_score) {
		this.scn_growth_statistics_scn_health_racdb_score = scn_growth_statistics_scn_health_racdb_score;
	}

	public String getUserblock_count_racdb_score() {
		return userblock_count_racdb_score;
	}

	public void setUserblock_count_racdb_score(String userblock_count_racdb_score) {
		this.userblock_count_racdb_score = userblock_count_racdb_score;
	}

	public int getEntityCount() {
		return entityCount;
	}

	public void setEntityCount(int entityCount) {
		this.entityCount = entityCount;
	}

}
