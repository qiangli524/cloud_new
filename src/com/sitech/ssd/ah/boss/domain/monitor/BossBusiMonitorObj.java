package com.sitech.ssd.ah.boss.domain.monitor;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class BossBusiMonitorObj extends BaseObj {
	/**
	 * 物理主键
	 */
	private String ID;
	/**
	 * 采集实体ID
	 */
	private String EQ_ID;
	/**
	 * 实体ID
	 */
	private String EXT_EQ_ID;
	/**
	 * 主机IP
	 */
	private String HOST_IP;
	/**
	 * 在线/离线标志
	 */
	private String ONLINE_FLAG;
	/**
	 * 集群名称
	 */
	private String CLUSTER_NAME;
	/**
	 * 池名称
	 */
	private String POOL_NAME;
	/**
	 * 程序节点
	 */
	private String PROGRESS_NODE;
	/**
	 * 开始时间
	 */
	private String START_TIME;
	/**
	 * 结束时间
	 */
	private String END_TIME;
	/**
	 * 入口业务量
	 */
	private String IN_COUNT;
	/**
	 * 出口业务量
	 */
	private String OUT_COUNT;
	/**
	 * 无主量
	 */
	private String NO_MASTER_COUNT;
	/**
	 * 错误量
	 */
	private String ERROR_COUNT;
	/**
	 * 业务指标
	 */
	private String BUSI_KPI;
	/**
	 * 批次号
	 */
	private String GATHER_BATCH_ID;
	/**
	 * 采集时间
	 */
	private String COLL_TMIE;
	/**
	 * 入库时间
	 */
	private String DB_TIME;
	/**
	 * 业务编号
	 */
	private String BUSI_NO;
	/**
	 * 业务编号描述
	 */
	private String BUSI_NO_DESC;
	/**
	 * 采集时间序列
	 */
	private String TIME_SEQ;
	/**
	 * 集群列表
	 */
	private List clusterList;
	/**
	 * 程序池列表
	 */
	private List poolList;
	/**
	 * 展示点数
	 */
	private Integer num;
	/**
	 * 别名
	 */
	private String nodename;
	/**
	 * 采集错误信息
	 */
	private String ERROR_INFO;
	/**
	 * 目录名
	 */
	private String dirName;
	/**
	 * 告警标志
	 */
	private String alarm_flag;
	/**
	 * 告警描述
	 */
	private String alarm_desc;

	public String getAlarm_desc() {
		return alarm_desc;
	}

	public void setAlarm_desc(String alarm_desc) {
		this.alarm_desc = alarm_desc;
	}

	public String getAlarm_flag() {
		return alarm_flag;
	}

	public void setAlarm_flag(String alarm_flag) {
		this.alarm_flag = alarm_flag;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public String getERROR_INFO() {
		return ERROR_INFO;
	}

	public void setERROR_INFO(String eRROR_INFO) {
		ERROR_INFO = eRROR_INFO;
	}

	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public List getClusterList() {
		return clusterList;
	}

	public void setClusterList(List clusterList) {
		this.clusterList = clusterList;
	}

	public List getPoolList() {
		return poolList;
	}

	public void setPoolList(List poolList) {
		this.poolList = poolList;
	}

	public String getTIME_SEQ() {
		return TIME_SEQ;
	}

	public void setTIME_SEQ(String tIME_SEQ) {
		TIME_SEQ = tIME_SEQ;
	}

	public String getBUSI_NO() {
		return BUSI_NO;
	}

	public void setBUSI_NO(String bUSI_NO) {
		BUSI_NO = bUSI_NO;
	}

	public String getBUSI_NO_DESC() {
		return BUSI_NO_DESC;
	}

	public void setBUSI_NO_DESC(String bUSI_NO_DESC) {
		BUSI_NO_DESC = bUSI_NO_DESC;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public String getEQ_ID() {
		return EQ_ID;
	}

	public void setEQ_ID(String eq_id) {
		EQ_ID = eq_id;
	}

	public String getEXT_EQ_ID() {
		return EXT_EQ_ID;
	}

	public void setEXT_EQ_ID(String ext_eq_id) {
		EXT_EQ_ID = ext_eq_id;
	}

	public String getHOST_IP() {
		return HOST_IP;
	}

	public void setHOST_IP(String host_ip) {
		HOST_IP = host_ip;
	}

	public String getONLINE_FLAG() {
		return ONLINE_FLAG;
	}

	public void setONLINE_FLAG(String online_flag) {
		ONLINE_FLAG = online_flag;
	}

	public String getCLUSTER_NAME() {
		return CLUSTER_NAME;
	}

	public void setCLUSTER_NAME(String cluster_name) {
		CLUSTER_NAME = cluster_name;
	}

	public String getPOOL_NAME() {
		return POOL_NAME;
	}

	public void setPOOL_NAME(String pool_name) {
		POOL_NAME = pool_name;
	}

	public String getPROGRESS_NODE() {
		return PROGRESS_NODE;
	}

	public void setPROGRESS_NODE(String progress_node) {
		PROGRESS_NODE = progress_node;
	}

	public String getIN_COUNT() {
		return IN_COUNT;
	}

	public void setIN_COUNT(String in_count) {
		IN_COUNT = in_count;
	}

	public String getOUT_COUNT() {
		return OUT_COUNT;
	}

	public void setOUT_COUNT(String out_count) {
		OUT_COUNT = out_count;
	}

	public String getERROR_COUNT() {
		return ERROR_COUNT;
	}

	public void setERROR_COUNT(String error_count) {
		ERROR_COUNT = error_count;
	}

	public String getBUSI_KPI() {
		return BUSI_KPI;
	}

	public void setBUSI_KPI(String busi_kpi) {
		BUSI_KPI = busi_kpi;
	}

	public String getGATHER_BATCH_ID() {
		return GATHER_BATCH_ID;
	}

	public void setGATHER_BATCH_ID(String gather_batch_id) {
		GATHER_BATCH_ID = gather_batch_id;
	}

	public String getSTART_TIME() {
		return START_TIME;
	}

	public void setSTART_TIME(String start_time) {
		START_TIME = start_time;
	}

	public String getEND_TIME() {
		return END_TIME;
	}

	public void setEND_TIME(String end_time) {
		END_TIME = end_time;
	}

	public String getCOLL_TMIE() {
		return COLL_TMIE;
	}

	public void setCOLL_TMIE(String coll_tmie) {
		COLL_TMIE = coll_tmie;
	}

	public String getDB_TIME() {
		return DB_TIME;
	}

	public void setDB_TIME(String db_time) {
		DB_TIME = db_time;
	}

	public String getNO_MASTER_COUNT() {
		return NO_MASTER_COUNT;
	}

	public void setNO_MASTER_COUNT(String nO_MASTER_COUNT) {
		NO_MASTER_COUNT = nO_MASTER_COUNT;
	}

}
