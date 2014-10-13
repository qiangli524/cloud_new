package com.sitech.ssd.ah.paas.domain.entity;

import java.util.Date;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * <p>
 * Title: PaasEntityObj
 * </p>
 * <p>
 * Description: paas实体表
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
 * @createtime 2014-3-31 下午3:13:13
 * 
 */
public class PaasEntityObj extends BaseObj {
	private String id;// 唯一标识
	private String entity_id;// 实体ID
	private String entity_name;// 实体名字
	private String entity_parent_id;
	private String host_address;// 实例所在服务器地址
	private Integer host_port;// 实例所开启的端口
	private String entity_type;// 实体类型
	private Date insert_time;// 入库时间
	private String nodeName;// 节点名称
	private String parent_id;
	private String kpi_name;// kpi名称
	private String entity_prop_key;
	private String entity_prop_val;
	private String busi_id;
	private List entityIdList;
	private String oracle_db_version;
	private String oracle_db_owner;
	private String oracle_db_desc;
	private String mysql_db_version;
	private String mysql_db_owner;
	private String mysql_db_desc;
	private String kpi_value_last;
	private String timeline;
	private String cycle_time;
	private String tableName;
	private int top_num;// topn个数
	private String desc;// 倒序
	private Double limit;// 健康度
	private int dbCount;// 数据库个数
	private int instanceCount;// 实例个数
	private int serverCount;// 服务个数
	private int dbCount_all;// 数据库总个数
	private int instanceCount_all;// 实例总个数
	private int serverCount_all;// 服务总个数
	private int dsCount;// 数据源个数
	private int dsCount_all;// 数据源总个数
	private int busiCount;// 业务个数
	private int normalCount;// 正常个数
	private int unusualCount;// 异常个数
	private int normalCount_all;// 正常总个数
	private int unusualCount_all;// 异常总个数
	private int busi_normalCount;// 业务正常个数
	private int busi_unusualCount;// 业务异常个数
	private int server_normalCount;// 服务正常个数
	private int server_unusualCount;// 服务异常个数
	private int server_normalCount_all;// 服务正常总个数
	private int server_unusualCount_all;// 服务异常总个数
	private String coll_time;
	private String health_score_total;// 健康度
	private String response_status;// 实例状态
	private String instance_efficiency_cpuusage_ps;// cpu占用率
	private String memory_usage_total_memory;// 内存使用量
	private String tbspallocation_spaceallocated;// 表空间占用率
	private List<PaasEntityObj> healthyLimitList;
	private List resultList;
	private int seriousAlarmCount;// 严重告警数
	private int mainAlarmCount;// 主要告警数
	private int minorAlarmCount;// 次要告警数
	private int otherAlarmCount;// 不确定告警数
	private int seriousAlarmCount_all;// 严重告警数
	private int mainAlarmCount_all;// 主要告警数
	private int minorAlarmCount_all;// 次要告警数
	private int otherAlarmCount_all;// 不确定告警数

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEntity_name() {
		return entity_name;
	}

	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}

	public String getHost_address() {
		return host_address;
	}

	public void setHost_address(String host_address) {
		this.host_address = host_address;
	}

	public Integer getHost_port() {
		return host_port;
	}

	public void setHost_port(Integer host_port) {
		this.host_port = host_port;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public Date getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(Date insert_time) {
		this.insert_time = insert_time;
	}

	public String getEntity_parent_id() {
		return entity_parent_id;
	}

	public void setEntity_parent_id(String entity_parent_id) {
		this.entity_parent_id = entity_parent_id;
	}

	public List getEntityIdList() {
		return entityIdList;
	}

	public void setEntityIdList(List entityIdList) {
		this.entityIdList = entityIdList;
	}

	public int getDbCount() {
		return dbCount;
	}

	public void setDbCount(int dbCount) {
		this.dbCount = dbCount;
	}

	public int getInstanceCount() {
		return instanceCount;
	}

	public void setInstanceCount(int instanceCount) {
		this.instanceCount = instanceCount;
	}

	public int getServerCount() {
		return serverCount;
	}

	public void setServerCount(int serverCount) {
		this.serverCount = serverCount;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getKpi_name() {
		return kpi_name;
	}

	public void setKpi_name(String kpi_name) {
		this.kpi_name = kpi_name;
	}

	public String getEntity_prop_key() {
		return entity_prop_key;
	}

	public void setEntity_prop_key(String entity_prop_key) {
		this.entity_prop_key = entity_prop_key;
	}

	public String getEntity_prop_val() {
		return entity_prop_val;
	}

	public void setEntity_prop_val(String entity_prop_val) {
		this.entity_prop_val = entity_prop_val;
	}

	public int getDsCount() {
		return dsCount;
	}

	public void setDsCount(int dsCount) {
		this.dsCount = dsCount;
	}

	public String getOracle_db_version() {
		return oracle_db_version;
	}

	public void setOracle_db_version(String oracle_db_version) {
		this.oracle_db_version = oracle_db_version;
	}

	public String getOracle_db_owner() {
		return oracle_db_owner;
	}

	public void setOracle_db_owner(String oracle_db_owner) {
		this.oracle_db_owner = oracle_db_owner;
	}

	public String getOracle_db_desc() {
		return oracle_db_desc;
	}

	public void setOracle_db_desc(String oracle_db_desc) {
		this.oracle_db_desc = oracle_db_desc;
	}

	public String getKpi_value_last() {
		return kpi_value_last;
	}

	public void setKpi_value_last(String kpi_value_last) {
		this.kpi_value_last = kpi_value_last;
	}

	public int getTop_num() {
		return top_num;
	}

	public void setTop_num(int top_num) {
		this.top_num = top_num;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Double getLimit() {
		return limit;
	}

	public void setLimit(Double limit) {
		this.limit = limit;
	}

	public int getNormalCount() {
		return normalCount;
	}

	public void setNormalCount(int normalCount) {
		this.normalCount = normalCount;
	}

	public int getUnusualCount() {
		return unusualCount;
	}

	public void setUnusualCount(int unusualCount) {
		this.unusualCount = unusualCount;
	}

	public String getColl_time() {
		return coll_time;
	}

	public void setColl_time(String coll_time) {
		this.coll_time = coll_time;
	}

	public String getResponse_status() {
		return response_status;
	}

	public void setResponse_status(String response_status) {
		this.response_status = response_status;
	}

	public String getInstance_efficiency_cpuusage_ps() {
		return instance_efficiency_cpuusage_ps;
	}

	public void setInstance_efficiency_cpuusage_ps(String instance_efficiency_cpuusage_ps) {
		this.instance_efficiency_cpuusage_ps = instance_efficiency_cpuusage_ps;
	}

	public String getMemory_usage_total_memory() {
		return memory_usage_total_memory;
	}

	public void setMemory_usage_total_memory(String memory_usage_total_memory) {
		this.memory_usage_total_memory = memory_usage_total_memory;
	}

	public String getTbspallocation_spaceallocated() {
		return tbspallocation_spaceallocated;
	}

	public void setTbspallocation_spaceallocated(String tbspallocation_spaceallocated) {
		this.tbspallocation_spaceallocated = tbspallocation_spaceallocated;
	}

	public String getHealth_score_total() {
		return health_score_total;
	}

	public void setHealth_score_total(String health_score_total) {
		this.health_score_total = health_score_total;
	}

	public int getBusiCount() {
		return busiCount;
	}

	public void setBusiCount(int busiCount) {
		this.busiCount = busiCount;
	}

	public int getBusi_normalCount() {
		return busi_normalCount;
	}

	public void setBusi_normalCount(int busi_normalCount) {
		this.busi_normalCount = busi_normalCount;
	}

	public int getBusi_unusualCount() {
		return busi_unusualCount;
	}

	public void setBusi_unusualCount(int busi_unusualCount) {
		this.busi_unusualCount = busi_unusualCount;
	}

	public int getDbCount_all() {
		return dbCount_all;
	}

	public void setDbCount_all(int dbCount_all) {
		this.dbCount_all = dbCount_all;
	}

	public int getInstanceCount_all() {
		return instanceCount_all;
	}

	public void setInstanceCount_all(int instanceCount_all) {
		this.instanceCount_all = instanceCount_all;
	}

	public int getServerCount_all() {
		return serverCount_all;
	}

	public void setServerCount_all(int serverCount_all) {
		this.serverCount_all = serverCount_all;
	}

	public int getNormalCount_all() {
		return normalCount_all;
	}

	public void setNormalCount_all(int normalCount_all) {
		this.normalCount_all = normalCount_all;
	}

	public int getUnusualCount_all() {
		return unusualCount_all;
	}

	public void setUnusualCount_all(int unusualCount_all) {
		this.unusualCount_all = unusualCount_all;
	}

	public int getDsCount_all() {
		return dsCount_all;
	}

	public void setDsCount_all(int dsCount_all) {
		this.dsCount_all = dsCount_all;
	}

	public int getServer_normalCount() {
		return server_normalCount;
	}

	public void setServer_normalCount(int server_normalCount) {
		this.server_normalCount = server_normalCount;
	}

	public int getServer_unusualCount() {
		return server_unusualCount;
	}

	public void setServer_unusualCount(int server_unusualCount) {
		this.server_unusualCount = server_unusualCount;
	}

	public int getServer_normalCount_all() {
		return server_normalCount_all;
	}

	public void setServer_normalCount_all(int server_normalCount_all) {
		this.server_normalCount_all = server_normalCount_all;
	}

	public int getServer_unusualCount_all() {
		return server_unusualCount_all;
	}

	public void setServer_unusualCount_all(int server_unusualCount_all) {
		this.server_unusualCount_all = server_unusualCount_all;
	}

	public List<PaasEntityObj> getHealthyLimitList() {
		return healthyLimitList;
	}

	public void setHealthyLimitList(List<PaasEntityObj> healthyLimitList) {
		this.healthyLimitList = healthyLimitList;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getTimeline() {
		return timeline;
	}

	public void setTimeline(String timeline) {
		this.timeline = timeline;
	}

	public int getSeriousAlarmCount() {
		return seriousAlarmCount;
	}

	public void setSeriousAlarmCount(int seriousAlarmCount) {
		this.seriousAlarmCount = seriousAlarmCount;
	}

	public int getMainAlarmCount() {
		return mainAlarmCount;
	}

	public void setMainAlarmCount(int mainAlarmCount) {
		this.mainAlarmCount = mainAlarmCount;
	}

	public int getMinorAlarmCount() {
		return minorAlarmCount;
	}

	public void setMinorAlarmCount(int minorAlarmCount) {
		this.minorAlarmCount = minorAlarmCount;
	}

	public int getOtherAlarmCount() {
		return otherAlarmCount;
	}

	public void setOtherAlarmCount(int otherAlarmCount) {
		this.otherAlarmCount = otherAlarmCount;
	}

	public int getSeriousAlarmCount_all() {
		return seriousAlarmCount_all;
	}

	public void setSeriousAlarmCount_all(int seriousAlarmCount_all) {
		this.seriousAlarmCount_all = seriousAlarmCount_all;
	}

	public int getMainAlarmCount_all() {
		return mainAlarmCount_all;
	}

	public void setMainAlarmCount_all(int mainAlarmCount_all) {
		this.mainAlarmCount_all = mainAlarmCount_all;
	}

	public int getMinorAlarmCount_all() {
		return minorAlarmCount_all;
	}

	public void setMinorAlarmCount_all(int minorAlarmCount_all) {
		this.minorAlarmCount_all = minorAlarmCount_all;
	}

	public int getOtherAlarmCount_all() {
		return otherAlarmCount_all;
	}

	public void setOtherAlarmCount_all(int otherAlarmCount_all) {
		this.otherAlarmCount_all = otherAlarmCount_all;
	}

	public String getBusi_id() {
		return busi_id;
	}

	public void setBusi_id(String busi_id) {
		this.busi_id = busi_id;
	}

	public String getCycle_time() {
		return cycle_time;
	}

	public void setCycle_time(String cycle_time) {
		this.cycle_time = cycle_time;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getMysql_db_version() {
		return mysql_db_version;
	}

	public void setMysql_db_version(String mysql_db_version) {
		this.mysql_db_version = mysql_db_version;
	}

	public String getMysql_db_owner() {
		return mysql_db_owner;
	}

	public void setMysql_db_owner(String mysql_db_owner) {
		this.mysql_db_owner = mysql_db_owner;
	}

	public String getMysql_db_desc() {
		return mysql_db_desc;
	}

	public void setMysql_db_desc(String mysql_db_desc) {
		this.mysql_db_desc = mysql_db_desc;
	}

}
