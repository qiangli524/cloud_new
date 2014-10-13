package com.sitech.ssd.ah.paas.action.tab;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.ah.paas.dao.alarm.PaasAlarmDao;
import com.sitech.ssd.ah.paas.dao.statistics.PaasStatisticsDao;
import com.sitech.ssd.ah.paas.domain.alarm.PaasAlarmObj;
import com.sitech.ssd.ah.paas.domain.entity.PaasEntityObj;
import com.sitech.ssd.ah.paas.domain.statistics.PaasKpiMonitorObj;
import com.sitech.ssd.ah.paas.domain.tab.DaasStatisticsObj;
import com.sitech.ssd.ah.paas.domain.tab.MapChartSet;
import com.sitech.ssd.ah.paas.domain.tree.PaasTreeObj;
import com.sitech.ssd.ah.paas.service.entity.PaasEntityService;
import com.sitech.ssd.ah.paas.service.tab.PaasTabService;
import com.sitech.ssd.ah.paas.service.tree.PaasTreeService;
import com.sitech.ssd.ah.paas.util.PaasConstant;
import com.sitech.ssd.ah.paas.util.PaasStatisticsConstant;
import com.sitech.utils.servlet.PrintWriterOut;

import domain.tab.ChartType;

/**
 * 
 * <p>
 * Title: OracleTabAction
 * </p>
 * <p>
 * Description: oracle资源树tab页相关操作
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
 * @createtime 2014-3-28 上午9:17:06
 * 
 */
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 * 
 */
@Controller("oracleTabAction")
@Scope("prototype")
public class OracleTabAction extends BaseAction {

	private static final Logger logger = Logger.getLogger(OracleTabAction.class);

	@Autowired
	private PaasTabService paasTabService;
	@Autowired
	private PaasEntityService paasEntityService;
	@Autowired
	private PaasTreeService paasTreeService;
	@Autowired
	private PaasStatisticsDao paasStatisticsDao;
	@Autowired
	private PaasAlarmDao paasAlarmDao;

	private PaasEntityObj paasEntityObj;

	private PaasEntityObj mysqlEntityObj;

	private DaasStatisticsObj daasDBObj;// 数据库Obj

	private DaasStatisticsObj daasInstanceObj;// 数据库实例Obj

	private DaasStatisticsObj daasServiceObj;// 数据库服务Obj

	private String id;
	private String entity_id;
	private String server_type;// 服务类型
	private String node_type;// 节点类型
	private String kpi;// kpi指标
	private String kpi_type;// kpi类型 包括：吞吐量、负载、响应等
	private String coll_time;
	private String name;
	private String start_time;// 开始时间
	private String end_time;// 结束时间
	private int top_num;
	private String desc;// 健康度倒序或正序
	private String timeline;// 时间戳
	private String event_level;
	private String cycle_time;// 自定义时间
	private List resultList;
	private List<PaasEntityObj> instanceList;// 实例集合
	private List<PaasEntityObj> businessList;// 业务集合
	private Map<String, String> map = new HashMap<String, String>();

	public PaasEntityObj getPaasEntityObj() {
		return paasEntityObj;
	}

	public void setPaasEntityObj(PaasEntityObj paasEntityObj) {
		this.paasEntityObj = paasEntityObj;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getServer_type() {
		return server_type;
	}

	public void setServer_type(String server_type) {
		this.server_type = server_type;
	}

	public String getNode_type() {
		return node_type;
	}

	public void setNode_type(String node_type) {
		this.node_type = node_type;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public DaasStatisticsObj getDaasDBObj() {
		return daasDBObj;
	}

	public void setDaasDBObj(DaasStatisticsObj daasDBObj) {
		this.daasDBObj = daasDBObj;
	}

	public DaasStatisticsObj getDaasInstanceObj() {
		return daasInstanceObj;
	}

	public void setDaasInstanceObj(DaasStatisticsObj daasInstanceObj) {
		this.daasInstanceObj = daasInstanceObj;
	}

	public DaasStatisticsObj getDaasServiceObj() {
		return daasServiceObj;
	}

	public void setDaasServiceObj(DaasStatisticsObj daasServiceObj) {
		this.daasServiceObj = daasServiceObj;
	}

	public String getKpi() {
		return kpi;
	}

	public void setKpi(String kpi) {
		this.kpi = kpi;
	}

	public String getKpi_type() {
		return kpi_type;
	}

	public void setKpi_type(String kpi_type) {
		this.kpi_type = kpi_type;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public String getColl_time() {
		return coll_time;
	}

	public void setColl_time(String coll_time) {
		this.coll_time = coll_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PaasEntityObj> getInstanceList() {
		return instanceList;
	}

	public void setInstanceList(List<PaasEntityObj> instanceList) {
		this.instanceList = instanceList;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public List<PaasEntityObj> getBusinessList() {
		return businessList;
	}

	public void setBusinessList(List<PaasEntityObj> businessList) {
		this.businessList = businessList;
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

	public String getTimeline() {
		return timeline;
	}

	public void setTimeline(String timeline) {
		this.timeline = timeline;
	}

	public String getEvent_level() {
		return event_level;
	}

	public void setEvent_level(String event_level) {
		this.event_level = event_level;
	}

	public String getCycle_time() {
		return cycle_time;
	}

	public void setCycle_time(String cycle_time) {
		this.cycle_time = cycle_time;
	}

	public PaasEntityObj getMysqlEntityObj() {
		return mysqlEntityObj;
	}

	public void setMysqlEntityObj(PaasEntityObj mysqlEntityObj) {
		this.mysqlEntityObj = mysqlEntityObj;
	}

	/**
	 * 
	 * @Title: queryBusinessCount_All
	 * @Description:查询业务总个数,正常个数,异常个数(业务健康度不是100的都是异常)
	 * @param
	 * @return PaasEntityObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-16 下午2:49:40
	 */
	private PaasEntityObj queryBusinessCount_All(String id, String server_type, String node_type) {
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		List busi_normalList = new ArrayList();// 正常业务集合
		List<PaasEntityObj> healthyLimitList = new ArrayList<PaasEntityObj>();
		PaasTreeObj paasTypeObj = new PaasTreeObj();
		if (PaasConstant.DAAS.equals(node_type)) {
			paasTypeObj.setParent_id(id);
		} else if (PaasConstant.DAAS_TYPE.equals(node_type)) {
			paasTypeObj.setId(id);
		}
		paasTypeObj.setServer_type(server_type);
		List<PaasTreeObj> paasList = paasTreeService.queryTreeNodeByObj(paasTypeObj);
		if (paasList != null && paasList.size() > 0) {
			paasTypeObj = paasList.get(0);
			paasEntityObj.setEntity_name(paasTypeObj.getName());
			PaasTreeObj paastrObj = new PaasTreeObj();
			paastrObj.setParent_id(paasTypeObj.getId());
			List<PaasTreeObj> busiList = paasTreeService.queryTreeNodeByObj(paastrObj);
			if (busiList != null && busiList.size() > 0) {
				paasEntityObj.setBusiCount(busiList.size());// 业务总个数
				for (PaasTreeObj paasBusiObj : busiList) {
					PaasTreeObj treeObj = new PaasTreeObj();
					treeObj.setId(paasBusiObj.getId());
					treeObj.setNode_type(paasBusiObj.getNode_type());
					Double limit = paasTabService.queryBusHealthyLimitForInstanceId(treeObj,
							PaasConstant.DAAS_BUSI);// 查询业务健康度的值
					PaasEntityObj busiLimitObj = new PaasEntityObj();
					busiLimitObj.setId(paasBusiObj.getId());
					busiLimitObj.setEntity_name(paasBusiObj.getName());
					busiLimitObj.setLimit(limit);
					healthyLimitList.add(busiLimitObj);
					if (limit == 100.0) {// 正常
						busi_normalList.add(busiLimitObj);
					}
				}
				List<PaasEntityObj> healList = healthyLimitList;
				for (int i = 0; i < healList.size() - 1; i++) {
					for (int j = 1; j < healList.size() - i; j++) {
						String idData = "";
						Double limitData = 0.0;
						if (healList.get(j - 1).getLimit().compareTo(healList.get(j).getLimit()) > 0) { // 比较两个数的大小
							idData = healList.get(j - 1).getId();
							limitData = healList.get(j - 1).getLimit();
							PaasEntityObj minData = new PaasEntityObj();
							minData.setId(healList.get(j).getId());
							minData.setLimit(healList.get(j).getLimit());
							healList.set((j - 1), minData);
							PaasEntityObj maxData = new PaasEntityObj();
							maxData.setId(idData);
							maxData.setLimit(limitData);
							healList.set(j, maxData);
						}
					}
				}
				paasEntityObj.setHealthyLimitList(healList);
				paasEntityObj.setBusi_normalCount(busi_normalList.size());// 正常业务
				paasEntityObj.setBusi_unusualCount(busiList.size() - busi_normalList.size());// 异常业务
			}
		}
		return paasEntityObj;
	}

	/**
	 * 
	 * @Title: queryDBAndInstanceAndServerCount_All
	 * @Description: 查询数据库,实例,服务的总数,正常数,异常数和数据库的详细信息
	 *               (实例健康度不是100,就是异常;实例异常,实例下所有服务也是异常的)
	 * @param
	 * @return PaasEntityObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-16 下午3:17:04
	 */
	private PaasEntityObj queryDBAndInstanceAndServerCount_All() throws Exception {
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		/**
		 * 查询数据库,实例,服务总个数
		 */
		PaasEntityObj entityTypeObj = new PaasEntityObj();
		entityTypeObj.setEntity_type(PaasConstant.ORACLE_DB);
		List<PaasEntityObj> dbList_all = paasEntityService.queryForEntityList(entityTypeObj);// 查询所有数据库集合
		entityTypeObj.setEntity_type(PaasConstant.ORACLE_INSTANCE);
		List<PaasEntityObj> instanceList_all = paasEntityService.queryForEntityList(entityTypeObj);// 查询所有实例集合
		entityTypeObj.setEntity_type(PaasConstant.ORACLE_SERVER);
		List<PaasEntityObj> serverList_all = paasEntityService.queryForEntityList(entityTypeObj);// 查询所有服务集合
		if (dbList_all != null && dbList_all.size() > 0) {
			paasEntityObj.setDbCount_all(dbList_all.size());// 数据库总个数
		}
		if (instanceList_all != null && instanceList_all.size() > 0) {
			paasEntityObj.setInstanceCount_all(instanceList_all.size());// 数据库实例总个数
		}
		if (serverList_all != null && serverList_all.size() > 0) {
			paasEntityObj.setServerCount_all(serverList_all.size());// 服务总个数
		}
		List<PaasKpiMonitorObj> instanceNormalList_all = new ArrayList<PaasKpiMonitorObj>();// 正常个数
		PaasEntityObj instanceTypeObj = new PaasEntityObj();
		instanceTypeObj.setEntity_type(PaasConstant.ORACLE_INSTANCE);
		List serverNormalList_all = new ArrayList();// 服务
		List serviceNormalList = new ArrayList();
		for (PaasEntityObj entityObj : instanceList_all) {
			PaasKpiMonitorObj monitorObj = new PaasKpiMonitorObj();
			monitorObj.setEntity_id(entityObj.getEntity_id());
			monitorObj.setKpi_name(PaasStatisticsConstant.health_score_total);
			List<PaasKpiMonitorObj> valueList = paasStatisticsDao.queryForLastValue(monitorObj);
			if (valueList != null && valueList.size() > 0) {
				monitorObj = valueList.get(0);
				if ("100.0".equals(monitorObj.getKpi_value_last())) {// 正常
					instanceNormalList_all.add(monitorObj);// 如果实例是正常的,那么服务也是正常的
				}
			}
		}
		// for (PaasKpiMonitorObj paasKpiMonitorObj : instanceNormalList_all) {
		// PaasEntityObj instanceEntityObj = new PaasEntityObj();
		// instanceEntityObj.setEntity_parent_id(paasKpiMonitorObj.getEntity_id());
		// serviceNormalList =
		// paasEntityService.queryForEntityList(instanceEntityObj);// 正常服务的个数
		// serverNormalList_all.addAll(serviceNormalList);
		// }
		if (instanceList_all != null && instanceList_all.size() > 0) {
			paasEntityObj.setNormalCount_all(instanceNormalList_all.size());// 实例正常总个数
			paasEntityObj.setUnusualCount_all(instanceList_all.size()
					- instanceNormalList_all.size());// 实例异常总个数
		}

		// if (serverNormalList_all != null && serverNormalList_all.size() > 0)
		// {
		// paasEntityObj.setServer_normalCount_all(serverNormalList_all.size());//
		// 服务正常总个数
		// paasEntityObj.setServer_unusualCount_all(paasEntityObj.getServerCount_all()
		// - serverNormalList_all.size());// 服务异常个数
		// }
		return paasEntityObj;
	}

	/**
	 * 
	 * @Title: queryDBInfo
	 * @Description: 查询数据库详细信息(数据库详细信息存放在tb_paas_entity_prop中)和数据库下有多少个实例
	 * @param
	 * @return PaasEntityObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-16 下午5:00:41
	 */
	private PaasEntityObj queryDBInfo() {
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		List dataBaseList = new ArrayList();
		PaasEntityObj dbServerTypeObj = new PaasEntityObj();
		dbServerTypeObj.setEntity_type(PaasConstant.ORACLE_DB);
		List<PaasEntityObj> dbEntityList = paasEntityService.queryForEntityList(dbServerTypeObj);
		for (PaasEntityObj dbEntityObj : dbEntityList) {
			PaasEntityObj entityInfoObj = new PaasEntityObj();
			entityInfoObj.setEntity_id(dbEntityObj.getEntity_id());
			List<PaasEntityObj> prolist = paasEntityService.queryForEntityProp(entityInfoObj);
			for (PaasEntityObj entity : prolist) {
				if ("oracle_db_version".equals(entity.getEntity_prop_key())) {// 版本
					entityInfoObj.setOracle_db_version(entity.getEntity_prop_val());
				} else if ("oracle_db_owner".equals(entity.getEntity_prop_key())) {// 所有者
					entityInfoObj.setOracle_db_owner(entity.getEntity_prop_val());
				} else if ("oracle_db_desc".equals(entity.getEntity_prop_key())) {// 描述
					entityInfoObj.setOracle_db_desc(entity.getEntity_prop_val());
				}
			}
			/**
			 * 数据库entity_id对应数据库实例entity_parent_id
			 */
			entityInfoObj.setEntity_parent_id(dbEntityObj.getEntity_id());
			List instanceList = paasEntityService.queryForEntityList(entityInfoObj);
			entityInfoObj.setEntity_name(dbEntityObj.getEntity_name());
			entityInfoObj.setInstanceCount(instanceList.size());// 数据库实例个数
			dataBaseList.add(entityInfoObj);
			paasEntityObj.setEntityIdList(dataBaseList);// 所有数据库集合
		}
		return paasEntityObj;
	}

	/**
	 * 
	 * @Title: queryDBAndInstanceAndServerCountInBusi
	 * @Description: 查询业务下的数据库,数据库实例,数据库服务
	 * @param
	 * @return PaasEntityObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-7-17 下午5:19:29
	 */
	private PaasEntityObj queryDBAndInstanceAndServerCountInBusi(String id) throws Exception {
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		if (resultList == null) {
			resultList = new ArrayList();
		}
		paasEntityObj.setBusi_id(id);
		String nodeName = this.queryNodeName(id);
		paasEntityObj.setNodeName(nodeName);// 业务名称
		PaasTreeObj treeObj = new PaasTreeObj();
		treeObj.setId(id);
		treeObj.setNode_type(PaasConstant.DAAS_BUSI);
		List<PaasTreeObj> dbList = paasTreeService.acquireListForType(PaasConstant.DAAS_DB_ENTITY,
				PaasConstant.ORACLE_DB, treeObj, new ArrayList<PaasTreeObj>());
		List<PaasTreeObj> instanceList = paasTreeService.acquireListForType(
				PaasConstant.DAAS_DB_ENTITY, PaasConstant.ORACLE_INSTANCE, treeObj,
				new ArrayList<PaasTreeObj>());
		List<PaasTreeObj> serverList = paasTreeService.acquireListForType(
				PaasConstant.DAAS_DB_ENTITY, PaasConstant.ORACLE_SERVER, treeObj,
				new ArrayList<PaasTreeObj>());
		if (dbList != null && dbList.size() > 0) {
			paasEntityObj.setDbCount(dbList.size());// 数据库个数
		}
		if (instanceList != null && instanceList.size() > 0) {
			paasEntityObj.setInstanceCount(instanceList.size());// 数据库实例个数
		}
		if (serverList != null && serverList.size() > 0) {
			paasEntityObj.setServerCount(serverList.size());// 服务个数
		}
		for (PaasTreeObj ptrObj : instanceList) {
			DaasStatisticsObj daasStatisticsObj = new DaasStatisticsObj();
			daasStatisticsObj.setEntity_id(ptrObj.getEntity_id());
			daasStatisticsObj.setEntity_name(ptrObj.getName());
			resultList.add(daasStatisticsObj);
		}
		for (Object object : resultList) {
			DaasStatisticsObj dsObj = (DaasStatisticsObj) object;
			PaasEntityObj enObj = new PaasEntityObj();
			enObj.setEntity_id(dsObj.getEntity_id());
			List<PaasEntityObj> kpiList = paasTabService.queryInstanceNodeList(enObj);
			for (PaasEntityObj enObj1 : kpiList) {
				if (PaasStatisticsConstant.health_score_response.equals(enObj1.getKpi_name())) {
					dsObj.setHealth_score_response(enObj1.getKpi_value_last());
				} else if (PaasStatisticsConstant.health_score_availability.equals(enObj1
						.getKpi_name())) {
					dsObj.setHealth_score_availability(enObj1.getKpi_value_last());
				} else if (PaasStatisticsConstant.health_score_efficiency.equals(enObj1
						.getKpi_name())) {
					dsObj.setHealth_score_efficiency(enObj1.getKpi_value_last());
				} else if (PaasStatisticsConstant.health_score_capacity
						.equals(enObj1.getKpi_name())) {
					dsObj.setHealth_score_capacity(enObj1.getKpi_value_last());
				} else if (PaasStatisticsConstant.health_score_resource
						.equals(enObj1.getKpi_name())) {
					dsObj.setHealth_score_resource(enObj1.getKpi_value_last());
				} else if (PaasStatisticsConstant.health_score_exception.equals(enObj1
						.getKpi_name())) {
					dsObj.setHealth_score_exception(enObj1.getKpi_value_last());
				} else if (PaasStatisticsConstant.health_score_total.equals(enObj1.getKpi_name())) {
					dsObj.setHealth_score_total(enObj1.getKpi_value_last());
				}
			}
			object = dsObj;
		}
		paasEntityObj.setResultList(resultList);
		List serverNormalList_all = new ArrayList();// 服务
		List serviceNormalList = new ArrayList();
		List<PaasKpiMonitorObj> normalList = new ArrayList<PaasKpiMonitorObj>();
		for (PaasTreeObj paasTreeObj : instanceList) {
			PaasKpiMonitorObj monitorObj = new PaasKpiMonitorObj();
			monitorObj.setEntity_id(paasTreeObj.getEntity_id());
			monitorObj.setKpi_name(PaasStatisticsConstant.health_score_total);
			List<PaasKpiMonitorObj> valueList = paasStatisticsDao.queryForLastValue(monitorObj);
			if (valueList != null && valueList.size() > 0) {
				monitorObj = valueList.get(0);
				if ("100.0".equals(monitorObj.getKpi_value_last())) {// 正常
					normalList.add(monitorObj);
				}
			}
		}
		// for (PaasKpiMonitorObj paasKpiMonitorObj : normalList) {
		// PaasEntityObj instanceEntityObj = new PaasEntityObj();
		// instanceEntityObj.setEntity_parent_id(paasKpiMonitorObj.getEntity_id());
		// serviceNormalList =
		// paasEntityService.queryForEntityList(instanceEntityObj);// 正常服务的个数
		// serverNormalList_all.addAll(serviceNormalList);
		// }
		if (instanceList != null && instanceList.size() > 0) {
			paasEntityObj.setNormalCount(normalList.size());// 实例正常个数
			paasEntityObj.setUnusualCount(paasEntityObj.getInstanceCount() - normalList.size());// 实例异常个数
		}
		// if (serverNormalList_all != null && serverNormalList_all.size() > 0)
		// {
		// paasEntityObj.setServer_normalCount(serverNormalList_all.size());//
		// 服务正常个数
		// paasEntityObj.setServer_unusualCount(paasEntityObj.getServerCount()
		// - serverNormalList_all.size());// 服务异常个数
		// }
		paasEntityObj = this.queryPaasAlarmCount(id, PaasConstant.DAAS_BUSI);// 告警个数
		return paasEntityObj;
	}

	/**
	 * 
	 * @Title: queryPaasAlarmCount
	 * @Description: 查询告警
	 * @param
	 * @return PaasEntityObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-7-22 下午3:44:26
	 */
	private PaasEntityObj queryPaasAlarmCount(String id, String node_type) throws Exception {
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		List<String> entityIdList = new ArrayList<String>();
		List<String> entityId_AllList = new ArrayList<String>();
		List<PaasAlarmObj> alarmList_all = new ArrayList<PaasAlarmObj>();
		List seriousAlarmList = new ArrayList();
		List mainsAlarmList = new ArrayList();
		List minorAlarmList = new ArrayList();
		List othorAlarmList = new ArrayList();
		if (PaasConstant.DAAS.equals(node_type) || PaasConstant.DAAS_TYPE.equals(node_type)) {
			PaasEntityObj entityTypeObj = new PaasEntityObj();
			entityTypeObj.setEntity_type(PaasConstant.ORACLE_INSTANCE);
			List<PaasEntityObj> instanceList = paasEntityService.queryForEntityList(entityTypeObj);// 查询所有实例集合
			for (PaasEntityObj entObj : instanceList) {
				entityId_AllList.add(entObj.getEntity_id());
			}
			for (String str : entityId_AllList) {
				PaasAlarmObj obj = new PaasAlarmObj();
				obj.setEntity_id(str);
				List<PaasAlarmObj> alarmList = paasAlarmDao.queryForAlarmList(obj);
				alarmList_all.addAll(alarmList);
			}
			for (PaasAlarmObj alarmObj : alarmList_all) {
				if ("0".equals(alarmObj.getEvent_level())) {
					seriousAlarmList.add(alarmObj);
				} else if ("1".equals(alarmObj.getEvent_level())) {
					mainsAlarmList.add(alarmObj);
				} else if ("2".equals(alarmObj.getEvent_level())) {
					minorAlarmList.add(alarmObj);
				} else if ("3".equals(alarmObj.getEvent_level())) {
					othorAlarmList.add(alarmObj);
				}
			}
			paasEntityObj.setSeriousAlarmCount_all(seriousAlarmList.size());
			paasEntityObj.setMainAlarmCount_all(mainsAlarmList.size());
			paasEntityObj.setMinorAlarmCount_all(minorAlarmList.size());
			paasEntityObj.setOtherAlarmCount_all(othorAlarmList.size());
		} else if (PaasConstant.DAAS_BUSI.equals(node_type)) {
			PaasTreeObj treeObj = new PaasTreeObj();
			treeObj.setId(id);
			treeObj.setNode_type(PaasConstant.DAAS_BUSI);
			List<PaasTreeObj> instanceList = paasTreeService.acquireListForType(
					PaasConstant.DAAS_DB_ENTITY, PaasConstant.ORACLE_INSTANCE, treeObj,
					new ArrayList<PaasTreeObj>());// 查询所有实例集合
			for (PaasTreeObj entObj : instanceList) {
				entityIdList.add(entObj.getEntity_id());
			}
			for (String str : entityIdList) {
				PaasAlarmObj obj = new PaasAlarmObj();
				obj.setEntity_id(str);
				List<PaasAlarmObj> alarmList = paasAlarmDao.queryForAlarmList(obj);
				alarmList_all.addAll(alarmList);
			}
			for (PaasAlarmObj alarmObj : alarmList_all) {
				if ("0".equals(alarmObj.getEvent_level())) {
					seriousAlarmList.add(alarmObj);
				} else if ("1".equals(alarmObj.getEvent_level())) {
					mainsAlarmList.add(alarmObj);
				} else if ("2".equals(alarmObj.getEvent_level())) {
					minorAlarmList.add(alarmObj);
				} else if ("3".equals(alarmObj.getEvent_level())) {
					othorAlarmList.add(alarmObj);
				}
			}
			paasEntityObj.setSeriousAlarmCount(seriousAlarmList.size());
			paasEntityObj.setMainAlarmCount(mainsAlarmList.size());
			paasEntityObj.setMinorAlarmCount(minorAlarmList.size());
			paasEntityObj.setOtherAlarmCount(othorAlarmList.size());
		}
		return paasEntityObj;
	}

	/**
	 * 
	 * @Title: showPaas
	 * @Description:展示paas层
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-15 下午9:17:23
	 */
	public String showPaas() {
		return "showPaas";
	}

	/**
	 * 
	 * @Title: showDaas
	 * @Description: 展示Daas统计信息
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-2 上午9:37:05
	 */
	public String showDaas() throws Exception {
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		if (mysqlEntityObj == null) {
			mysqlEntityObj = new PaasEntityObj();
		}
		// oralce
		paasEntityObj = this.queryBusinessCount_All(id, PaasConstant.ORACLE, node_type);// oracle业务数量
		paasEntityObj = this.queryDBAndInstanceAndServerCount_All();// 数据库,实例,服务数量
		paasEntityObj = this.queryDBInfo();// 数据库详细信息
		paasEntityObj = this.queryPaasAlarmCount(id, node_type);// 告警个数

		// mysql
		mysqlEntityObj = this.queryMysqlBusinessCount_All(id, PaasConstant.MYSQL, node_type);// mysql业务数量
		mysqlEntityObj = this.queryMysqlDBCount_All();// mysql数据库
		mysqlEntityObj = this.queryMysqlDBInfo();// mysql数据库详细信息
		mysqlEntityObj = this.queryMysqlAlarmCount(id, node_type);// mysql告警个数
		return "showDaas";
	}

	/**
	 * 
	 * @Title: showDBType
	 * @Description: 展示 数据库类型节点页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-6-23 上午11:54:13
	 */
	public String showDBType() throws Exception {
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		if (resultList == null) {
			resultList = new ArrayList();
		}

		paasEntityObj = this.queryBusinessCount_All(id, PaasConstant.ORACLE, node_type);// 业务数量
		paasEntityObj = this.queryDBAndInstanceAndServerCount_All();// 数据库,实例,服务数量

		if (paasEntityObj.getHealthyLimitList() != null
				&& paasEntityObj.getHealthyLimitList().size() > 0) {
			paasEntityObj = this.queryDBAndInstanceAndServerCountInBusi(paasEntityObj
					.getHealthyLimitList().get(0).getId());
		}
		paasEntityObj = this.queryPaasAlarmCount(id, node_type);// 告警总数
		return "showDBType";
	}

	/**
	 * 
	 * @Title: showBusiTopN
	 * @Description: 业务TopN
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-16 下午5:14:41
	 */
	public String showBusiTopN() {
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		FusionCharts fusionCharts = new FusionCharts();
		try {
			paasEntityObj.setId(id);
			paasEntityObj.setTop_num(top_num);
			fusionCharts = paasTabService.queryBusiTopN(paasEntityObj, node_type);
			String jsonString = JacksonUtil.toJson(fusionCharts);
			PrintWriterOut.printWirter(response, jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: showBusiChart
	 * @Description: 点击单个TopN展示内容
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-16 下午5:21:45
	 */
	public String showBusiChart() throws Exception {
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		paasEntityObj = this.queryDBAndInstanceAndServerCountInBusi(id);
		JSONObject json = new JSONObject();
		json.put("paasObj", paasEntityObj);
		PrintWriterOut.printWirter(response, json.toString());
		return null;
	}

	/**
	 * 
	 * @Title: showDBBusiness
	 * @Description: 数据库业务节点页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-6-16 上午10:37:03
	 */
	public String showDBBusiness() throws Exception {
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		paasEntityObj = this.queryDBAndInstanceAndServerCountInBusi(id);
		paasEntityObj = this.queryPaasAlarmCount(id, PaasConstant.DAAS_BUSI);// 告警数
		return "showDBBusiness";
	}

	/**
	 * 
	 * @Title: showLimitAverage
	 * @Description: 业务健康度平均值(实例健康度)
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-17 下午8:12:00
	 */
	public String showLimitAverage() {
		FusionCharts fusionCharts = new FusionCharts();
		PaasTreeObj treeObj = new PaasTreeObj();
		if (PaasConstant.DAAS_BUSI.equals(node_type)) {
			treeObj.setId(id);
		} else {
			treeObj.setParent_id(id);
		}
		List<PaasTreeObj> treeList = paasTreeService.queryTreeNodeByObj(treeObj);
		if (treeList != null && treeList.size() > 0) {
			treeObj = treeList.get(0);
			MapChartSet setObj = new MapChartSet();
			setObj.setEntity_id(treeObj.getEntity_id());
			setObj.setId(id);
			setObj.setNode_name(treeObj.getName());
			if (PaasConstant.DAAS_BUSI.equals(treeObj.getNode_type())) {
				setObj.setKpi_name(PaasStatisticsConstant.health_score_average);
			} else if (PaasConstant.DAAS_DB_ENTITY.equals(treeObj.getNode_type())) {
				setObj.setKpi_name(PaasStatisticsConstant.health_score_total);
			}
			setObj.setStartData(start_time);
			setObj.setEndData(end_time);
			setObj.setCycle_time(cycle_time);
			setObj.setPotCount(30);
			fusionCharts = paasTabService.queryLimitAverageChart(setObj);
			String json = JacksonUtil.toJson(fusionCharts);
			PrintWriterOut.printWirter(response, json);
		}
		return null;
	}

	/**
	 * 
	 * @Title: showInstanceChartByEntityId
	 * @Description: 通过entity_id过去实例健康度
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-23 上午10:09:25
	 */
	public String showInstanceChartByEntityId() {
		FusionCharts fusionCharts = new FusionCharts();
		MapChartSet setObj = new MapChartSet();
		setObj.setEntity_id(entity_id);
		setObj.setId(id);
		setObj.setNode_name(name);
		setObj.setCycle_time(cycle_time);
		setObj.setKpi_name(PaasStatisticsConstant.health_score_total);
		setObj.setPotCount(30);
		fusionCharts = paasTabService.queryLimitAverageChart(setObj);
		String json = JacksonUtil.toJson(fusionCharts);
		PrintWriterOut.printWirter(response, json);
		return null;
	}

	/**
	 * 
	 * @Title: showInstanceListForBusiNode
	 * @Description: 业务节点展示(业务的健康度是该业务下实例健康度的平均值); 某个时间点业务健康度下的实例集合
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-18 下午5:09:59
	 */
	public String showInstanceListForBusiNode() {
		List resultList = new ArrayList();
		PaasTreeObj treeObj = new PaasTreeObj();
		treeObj.setParent_id(id);
		treeObj.setNode_type(PaasConstant.DAAS_DB);
		treeObj.setServer_type(PaasConstant.ORACLE_INSTANCE);
		List<PaasTreeObj> treeList = paasTreeService.queryTreeNodeByObj(treeObj);
		if (treeList != null && treeList.size() > 0) {
			treeObj = treeList.get(0);
			PaasTreeObj treObj = new PaasTreeObj();
			treObj.setParent_id(treeObj.getId());
			List<PaasTreeObj> paasTreList = paasTreeService.queryTreeNodeByObj(treObj);
			for (PaasTreeObj entityIdObj : paasTreList) {
				PaasEntityObj entityObj = new PaasEntityObj();
				entityObj.setEntity_id(entityIdObj.getEntity_id());
				entityObj.setKpi_name(PaasStatisticsConstant.health_score_total);
				entityObj.setColl_time(coll_time);
				String tableName = paasTabService.queryTableNameByCycle_time(coll_time, cycle_time);
				entityObj.setTableName(tableName);
				entityObj.setPagination(this.getPaginater().initPagination(request));
				List insList = paasTabService.queryInstanceListForBusiNode(entityObj);
				resultList.addAll(insList);
			}
			for (Object obj : resultList) {
				PaasEntityObj paEnObj = (PaasEntityObj) obj;
				paEnObj.setCycle_time(cycle_time);
				obj = paEnObj;
			}
			JSONObject json = new JSONObject();
			json.put("list", resultList);
			PrintWriterOut.printWirter(response, json.toString());
		}
		return null;
	}

	/**
	 * 
	 * @Title: showHealthLimitChart
	 * @Description: 健康度Chart
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-16 上午11:04:25
	 */
	public void showHealthLimitChart() {
		JSONObject json = new JSONObject();
		if (PaasConstant.DAAS.equals(node_type)) {
			PaasTreeObj paasTreeObj = new PaasTreeObj();
			paasTreeObj.setServer_type(PaasConstant.ORACLE);
			paasTreeObj.setParent_id(id);
			List<PaasTreeObj> treIdList = paasTreeService.queryTreeNodeByObj(paasTreeObj);
			if (treIdList != null && treIdList.size() > 0) {
				paasTreeObj = treIdList.get(0);
			}
			PaasTreeObj paasTreeObj1 = new PaasTreeObj();
			paasTreeObj1.setParent_id(paasTreeObj.getId());
			List<PaasTreeObj> treeIdList = paasTreeService.queryTreeNodeByObj(paasTreeObj1);
			List<String> stringList = new ArrayList<String>();
			for (PaasTreeObj tObj : treeIdList) {
				FusionCharts fusionCharts = new FusionCharts();
				PaasTreeObj treeObj = new PaasTreeObj();
				treeObj.setId(tObj.getId());
				treeObj.setNode_type(tObj.getNode_type());
				try {
					fusionCharts = paasTabService.showHealthLimitChart(treeObj,
							PaasConstant.DAAS_BUSI);
				} catch (Exception e) {
					e.printStackTrace();
				}
				String result = JacksonUtil.toJson(fusionCharts);
				stringList.add(result);
			}
			json.put("jsonXml", stringList);
		} else if (PaasConstant.DAAS_TYPE.equals(node_type)) {
			PaasTreeObj paasTreeObj = new PaasTreeObj();
			paasTreeObj.setParent_id(id);
			List<PaasTreeObj> treeIdList = paasTreeService.queryTreeNodeByObj(paasTreeObj);
			if (treeIdList != null && treeIdList.size() > 0) {
				FusionCharts fusionCharts = new FusionCharts();
				PaasTreeObj treeObj = new PaasTreeObj();
				treeObj.setId(treeIdList.get(0).getId());
				treeObj.setNode_type(treeIdList.get(0).getNode_type());
				try {
					fusionCharts = paasTabService.showHealthLimitChart(treeObj, node_type);
				} catch (Exception e) {
					e.printStackTrace();
				}
				String result = JacksonUtil.toJson(fusionCharts);
				json.put("jsonXml", result);
			}
		} else if (PaasConstant.DAAS_BUSI.equals(node_type)) {
			FusionCharts fusionCharts = new FusionCharts();
			PaasTreeObj treeObj = new PaasTreeObj();
			treeObj.setId(id);
			treeObj.setNode_type(node_type);
			try {
				fusionCharts = paasTabService.showHealthLimitChart(treeObj, node_type);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String result = JacksonUtil.toJson(fusionCharts);
			json.put("jsonXml", result);
		}
		PrintWriterOut.printWirter(response, json.toString());
	}

	/**
	 * 
	 * @Title: queryDBList
	 * @Description: 数据库集合
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-18 下午8:06:49
	 */
	public String queryDBList() {
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		PaasEntityObj entityObj = new PaasEntityObj();
		entityObj.setParent_id(id);
		entityObj.setPagination(this.getPaginater().initPagination(request));
		resultList = paasTabService.queryNodeList(entityObj);
		for (Object object : resultList) {
			PaasEntityObj paObj = new PaasEntityObj();
			paObj = (PaasEntityObj) object;
			paObj.setEntity_id(paObj.getEntity_id());
			List<PaasEntityObj> prolist = paasEntityService.queryForEntityProp(paObj);
			for (PaasEntityObj entity : prolist) {
				if ("oracle_db_version".equals(entity.getEntity_prop_key())) {
					paObj.setOracle_db_version(entity.getEntity_prop_val());
				} else if ("oracle_db_owner".equals(entity.getEntity_prop_key())) {
					paObj.setOracle_db_owner(entity.getEntity_prop_val());
				} else if ("oracle_db_desc".equals(entity.getEntity_prop_key())) {
					paObj.setOracle_db_desc(entity.getEntity_prop_val());
				}
				object = paObj;
			}
		}
		if (resultList != null && resultList.size() > 0) {
			entityObj = (PaasEntityObj) resultList.get(0);
			String entityId = entityObj.getEntity_id();
			this.queryDBKpiValue(entityId);
			instanceList = this.queryInstanceByDBEntityId(entityId);
			if (instanceList != null && instanceList.size() > 0) {
				paasEntityObj.setInstanceCount(instanceList.size());// 数据库实例个数
			}
		} else {
			resultList = null;
		}
		return "queryDBList";
	}

	/**
	 * 
	 * @Title: queryInstanceByDBEntityId
	 * @Description: 查询数据库下的实例
	 * @param
	 * @return List<PaasEntityObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-23 上午9:11:24
	 */
	private List<PaasEntityObj> queryInstanceByDBEntityId(String entity_id) {
		/**
		 * 查询数据库下有多少个实例、实例信息(健康度等)
		 */
		PaasEntityObj enObj = new PaasEntityObj();
		enObj.setEntity_parent_id(entity_id);
		List<PaasEntityObj> instList = paasEntityService.queryForEntityList(enObj);
		DecimalFormat df = new DecimalFormat("#.##");
		for (PaasEntityObj enObj1 : instList) {
			PaasEntityObj enObj2 = new PaasEntityObj();
			enObj2.setEntity_id(enObj1.getEntity_id());
			List<PaasEntityObj> kpiList = paasTabService.queryInstanceNodeList(enObj2);
			for (PaasEntityObj enObj3 : kpiList) {
				if (PaasStatisticsConstant.response_status.equals(enObj3.getKpi_name())) {
					enObj1.setResponse_status(enObj3.getKpi_value_last());
				} else if (PaasStatisticsConstant.tbspallocation_spaceallocated.equals(enObj3
						.getKpi_name())) {
					enObj1.setTbspallocation_spaceallocated(enObj3.getKpi_value_last());
				} else if (PaasStatisticsConstant.instance_efficiency_cpuusage_ps.equals(enObj3
						.getKpi_name())) {
					enObj1.setInstance_efficiency_cpuusage_ps(df.format(Double.parseDouble(enObj3
							.getKpi_value_last())));
				} else if (PaasStatisticsConstant.memory_usage_total_memory.equals(enObj3
						.getKpi_name())) {
					enObj1.setMemory_usage_total_memory(df.format(Double.parseDouble(enObj3
							.getKpi_value_last()) / 1024));
				} else if (PaasStatisticsConstant.health_score_total.equals(enObj3.getKpi_name())) {
					enObj1.setHealth_score_total(enObj3.getKpi_value_last());
				}
			}
		}
		return instList;
	}

	/**
	 * 
	 * @Title: queryDBListByEntityId
	 * @Description: 通过entity_id查询数据库指标
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-20 上午9:22:36
	 */
	public String queryDBListByEntityId() {
		this.queryDBKpiValue(entity_id);
		instanceList = this.queryInstanceByDBEntityId(entity_id);
		if (instanceList != null && instanceList.size() > 0) {
			daasDBObj.setEntityCount(instanceList.size());
		}
		daasDBObj.setResultList(instanceList);
		JSONObject json = new JSONObject();
		json.put("daasObj", daasDBObj);
		PrintWriterOut.printWirter(response, json.toString());
		return null;
	}

	/**
	 * 
	 * @Title: queryDBInstanceList
	 * @Description: 数据库实例
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-19 上午11:12:55
	 */
	public String queryDBInstanceList() {
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		if (businessList == null) {
			businessList = new ArrayList<PaasEntityObj>();
		}
		PaasEntityObj entityObj = new PaasEntityObj();
		entityObj.setParent_id(id);
		entityObj.setKpi_name(PaasStatisticsConstant.health_score_total);
		entityObj.setPagination(this.getPaginater().initPagination(request));
		resultList = paasTabService.queryInstanceNodeList(entityObj);
		List normalList = new ArrayList();
		if (resultList != null && resultList.size() > 0) {
			paasEntityObj.setInstanceCount(resultList.size());// 数据库实例个数
			List<PaasEntityObj> resultEntityList = resultList;
			/**
			 * 添加实例对应的业务个数
			 */
			for (Object object : resultList) {
				PaasEntityObj entObj = (PaasEntityObj) object;
				PaasEntityObj paasEnttObj = new PaasEntityObj();
				paasEnttObj.setEntity_id(entObj.getEntity_id());
				List busiList = paasTabService.queryBusiListByEntityId(paasEnttObj);
				entObj.setBusiCount(busiList.size());
				if ("100.0".equals(entObj.getKpi_value_last())) {// 正常
					normalList.add(entObj);
				}
				object = entObj;
			}
			paasEntityObj.setNormalCount(normalList.size());// 实例正常个数
			paasEntityObj.setUnusualCount(resultList.size() - normalList.size());// 实例异常个数
			businessList = this.queryBusiListByInstanceEntityId(resultEntityList.get(0)
					.getEntity_id());
			if (businessList != null && businessList.size() > 0) {
				paasEntityObj.setBusiCount(businessList.size());
			}
		}
		return "queryDBInstanceList";
	}

	/**
	 * 
	 * @Title: queryBusiListByInstanceEntityId
	 * @Description: 查询业务名字和健康度公共方法
	 * @param
	 * @return List<PaasEntityObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-23 上午11:33:28
	 */
	private List<PaasEntityObj> queryBusiListByInstanceEntityId(String entity_id) {
		List<PaasEntityObj> busiIdList = new ArrayList<PaasEntityObj>();
		List<PaasEntityObj> busiList = new ArrayList<PaasEntityObj>();
		PaasEntityObj paasEntObj = new PaasEntityObj();
		paasEntObj.setEntity_id(entity_id);
		busiIdList = paasTabService.queryBusiListByEntityId(paasEntObj);
		/**
		 * 查询业务列表
		 */
		if (busiIdList != null && busiIdList.size() > 0) {
			for (PaasEntityObj tObj : busiIdList) {
				PaasTreeObj treeObj = new PaasTreeObj();
				treeObj.setId(tObj.getId());
				treeObj.setNode_type(PaasConstant.DAAS_BUSI);
				Double limit = paasTabService.queryBusHealthyLimitForInstanceId(treeObj,
						PaasConstant.DAAS_BUSI);// 查询健康度的值
				List<PaasTreeObj> nodeList = paasTreeService.queryTreeNodeByObj(treeObj);
				if (nodeList != null && nodeList.size() > 0) {
					PaasEntityObj peObj = new PaasEntityObj();
					peObj.setId(nodeList.get(0).getId());
					peObj.setEntity_name(nodeList.get(0).getName());
					peObj.setLimit(limit);
					busiList.add(peObj);
				}
			}
		}
		return busiList;
	}

	/**
	 * 
	 * @Title: queryBusiNameAndLimitListByInstanceEntityId
	 * @Description: 查询业务名字和健康度通过实例entity_id
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-23 上午11:21:08
	 */
	public String queryBusiNameAndLimitListByInstanceEntityId() {
		List<PaasEntityObj> busiList = this.queryBusiListByInstanceEntityId(entity_id);
		JSONObject json = new JSONObject();
		json.put("busiList", busiList);
		PrintWriterOut.printWirter(response, json.toString());
		return null;
	}

	/**
	 * 
	 * @Title: queryDBInstanceListByEntityId
	 * @Description: 通过entity_id查询实例指标
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-20 上午9:22:36
	 */
	public String queryDBInstanceListByEntityId() {
		this.queryInstanceKpiValue(entity_id, null, null, null);
		JSONObject json = new JSONObject();
		json.put("daasObj", daasInstanceObj);
		PrintWriterOut.printWirter(response, json.toString());
		return null;
	}

	/**
	 * 
	 * @Title: queryDBServerList
	 * @Description: 数据库服务
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-19 上午11:12:55
	 */
	public String queryDBServerList() {
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		PaasEntityObj entityObj = new PaasEntityObj();
		entityObj.setParent_id(id);
		entityObj.setPagination(this.getPaginater().initPagination(request));
		resultList = paasTabService.queryNodeList(entityObj);
		for (Object object : resultList) {
			PaasEntityObj ptObj = (PaasEntityObj) object;
			PaasEntityObj paasEntObj = paasTabService.queryParentEntityByEntityId(ptObj);
			if (ptObj.getEntity_name().contains("$")) {
				ptObj.setEntity_type("系统服务");
			} else {
				ptObj.setEntity_type("业务服务");
			}
			ptObj.setNodeName(paasEntObj.getEntity_name());// 所属实例名字
			ptObj.setId(paasEntObj.getEntity_id());// 所属实例entity_id
			object = ptObj;
		}
		if (resultList != null && resultList.size() > 0) {
			paasEntityObj.setServerCount(resultList.size());// 服务个数
		}
		return "queryDBServerList";
	}

	/**
	 * 
	 * @Title: queryDBServerListByEntityId
	 * @Description: 通过entity_id查询服务指标
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-20 上午9:22:36
	 */
	public String queryDBServerListByEntityId() {
		this.queryServerKpiValue(entity_id);
		JSONObject json = new JSONObject();
		json.put("daasObj", daasServiceObj);
		PrintWriterOut.printWirter(response, json.toString());
		return null;
	}

	/**
	 * 
	 * @Title: queryNodeName
	 * @Description: 查询节点名称
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-19 上午11:46:25
	 */
	public String queryNodeName(String id) {
		String nodeName = "";
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		PaasTreeObj paasObj = new PaasTreeObj();
		paasObj.setId(id);
		List<PaasTreeObj> resultlist = paasTreeService.queryTreeNodeByObj(paasObj);
		if (resultlist != null && resultlist.size() > 0) {
			nodeName = resultlist.get(0).getName();// 获取节点名称
		}
		return nodeName;
	}

	/**
	 * 
	 * @Title: queryDBKpiValue
	 * @Description: 查询 DB所有kpi的值
	 * @param
	 * @return DaasStatisticsObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-21 下午2:06:32
	 */
	public DaasStatisticsObj queryDBKpiValue(String entity_id) {
		if (daasDBObj == null) {
			daasDBObj = new DaasStatisticsObj();
		}
		Map<String, PaasKpiMonitorObj> kpiMap = this.queryStatisticalTable(entity_id);
		PaasEntityObj entityObj = new PaasEntityObj();
		entityObj.setEntity_id(entity_id);
		entityObj = paasEntityService.queryByObj(entityObj);
		daasDBObj.setEntity_name(entityObj.getEntity_name());
		daasDBObj.setHealth_score_efficiency(kpiMap
				.get(PaasStatisticsConstant.health_score_efficiency) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_efficiency).getKpi_value_last());// 负载
		daasDBObj
				.setUserblock_count_racdb(kpiMap.get(PaasStatisticsConstant.userblock_count_racdb) == null ? "-"
						: kpiMap.get(PaasStatisticsConstant.userblock_count_racdb)
								.getKpi_value_last());// 阻塞会话数
		daasDBObj.setUserblock_count_racdb_score(kpiMap
				.get(PaasStatisticsConstant.userblock_count_racdb) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.userblock_count_racdb).getExt_val());// 阻塞会话数健康度
		daasDBObj
				.setHealth_score_capacity(kpiMap.get(PaasStatisticsConstant.health_score_capacity) == null ? "-"
						: kpiMap.get(PaasStatisticsConstant.health_score_capacity)
								.getKpi_value_last());// 容量
		daasDBObj.setProblemtbsp_pctused_racdb(kpiMap
				.get(PaasStatisticsConstant.problemtbsp_pctused_racdb) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.problemtbsp_pctused_racdb).getKpi_value_last());// 表空间占用率
		daasDBObj.setProblemtbsp_pctused_racdb_score(kpiMap
				.get(PaasStatisticsConstant.problemtbsp_pctused_racdb) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.problemtbsp_pctused_racdb).getExt_val());// 表空间占用率健康度

		daasDBObj.setScn_growth_statistics_scn_health_racdb(kpiMap
				.get(PaasStatisticsConstant.scn_growth_statistics_scn_health_racdb) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.scn_growth_statistics_scn_health_racdb)
						.getKpi_value_last());// SCN健康值
		daasDBObj.setScn_growth_statistics_scn_health_racdb_score(kpiMap
				.get(PaasStatisticsConstant.scn_growth_statistics_scn_health_racdb) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.scn_growth_statistics_scn_health_racdb)
						.getExt_val());// SCN健康值健康度

		return daasDBObj;
	}

	/**
	 * 
	 * @Title: queryInstanceKpiValue
	 * @Description: 查询 实例所有kpi的值
	 * @param
	 * @return DaasStatisticsObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-19 下午4:26:45
	 */
	public DaasStatisticsObj queryInstanceKpiValue(String entity_id, String timeline,
			String coll_time, String cycle_time) {
		if (daasInstanceObj == null) {
			daasInstanceObj = new DaasStatisticsObj();
		}
		Map<String, PaasKpiMonitorObj> kpiMap = new HashMap<String, PaasKpiMonitorObj>();
		if (timeline == null || "".equals(timeline)) {
			kpiMap = this.queryStatisticalTable(entity_id);
		} else {
			kpiMap = this.queryRealTimeKpi(entity_id, timeline, coll_time, cycle_time);
		}
		PaasEntityObj entityObj = new PaasEntityObj();
		entityObj.setEntity_id(entity_id);
		entityObj = paasEntityService.queryByObj(entityObj);
		daasInstanceObj.setEntity_name(entityObj.getEntity_name());
		// TODO KPI
		daasInstanceObj
				.setResponseStatus(kpiMap.get(PaasStatisticsConstant.response_status) == null ? "-"
						: kpiMap.get(PaasStatisticsConstant.response_status).getKpi_value_last());// 实例状态
		daasInstanceObj
				.setHa_infoLog_mode(kpiMap.get(PaasStatisticsConstant.ha_infoLog_mode) == null ? "-"
						: kpiMap.get(PaasStatisticsConstant.ha_infoLog_mode).getKpi_value_last());// 归档状态
		daasInstanceObj.setScn_growth_statisticsScn_health(kpiMap
				.get(PaasStatisticsConstant.scn_growth_statistics_scn_health) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.scn_growth_statistics_scn_health)
						.getKpi_value_last());// SCN健康值
		daasInstanceObj.setTbspAllocationSpaceAllocated(kpiMap
				.get(PaasStatisticsConstant.tbspallocation_spaceallocated) == null ? "-" : kpiMap
				.get(PaasStatisticsConstant.tbspallocation_spaceallocated).getKpi_value_last());// 表空间占用率
		daasInstanceObj.setInstance_throughputParses_ps(kpiMap
				.get(PaasStatisticsConstant.instance_throughput_parses_ps) == null ? "-" : kpiMap
				.get(PaasStatisticsConstant.instance_throughput_parses_ps).getKpi_value_last());// 解析次数(每秒)
		daasInstanceObj.setInstance_efficiencyCpuusage_ps(kpiMap
				.get(PaasStatisticsConstant.instance_efficiency_cpuusage_ps) == null ? "-" : kpiMap
				.get(PaasStatisticsConstant.instance_efficiency_cpuusage_ps).getKpi_value_last());// CPU占用率(每一事物处理)
		daasInstanceObj
				.setUserBlockCount(kpiMap.get(PaasStatisticsConstant.userblock_count) == null ? "-"
						: kpiMap.get(PaasStatisticsConstant.userblock_count).getKpi_value_last());// 最大阻塞会话数
		daasInstanceObj.setInstance_throughputCommits_ps(kpiMap
				.get(PaasStatisticsConstant.instance_throughput_commits_ps) == null ? "-" : kpiMap
				.get(PaasStatisticsConstant.instance_throughput_commits_ps).getKpi_value_last());// 用户提交数（每秒）
		daasInstanceObj
				.setFileRead_cs(kpiMap.get(PaasStatisticsConstant.file_read_cs) == null ? "-"
						: kpiMap.get(PaasStatisticsConstant.file_read_cs).getKpi_value_last());// 平均文件读取时间
		daasInstanceObj
				.setSql_responseTime(kpiMap.get(PaasStatisticsConstant.sql_response_time) == null ? "-"
						: kpiMap.get(PaasStatisticsConstant.sql_response_time).getKpi_value_last());// 当前SQL平均响应时间
		daasInstanceObj
				.setFileWrite_cs(kpiMap.get(PaasStatisticsConstant.file_write_cs) == null ? "-"
						: kpiMap.get(PaasStatisticsConstant.file_write_cs).getKpi_value_last());// 平均文件写入时间
		daasInstanceObj.setInvalid_objectsCount(kpiMap
				.get(PaasStatisticsConstant.invalid_objects_count) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.invalid_objects_count).getKpi_value_last());// 无效对象总数
		daasInstanceObj.setMemory_usageTotal_memory(kpiMap
				.get(PaasStatisticsConstant.memory_usage_total_memory) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.memory_usage_total_memory).getKpi_value_last());// 内存使用量
		// TODO KPI健康度
		daasInstanceObj
				.setResponseStatus_score(kpiMap.get(PaasStatisticsConstant.response_status) == null ? "-"
						: kpiMap.get(PaasStatisticsConstant.response_status).getExt_val());// 实例状态健康度
		daasInstanceObj
				.setHa_infoLog_mode_score(kpiMap.get(PaasStatisticsConstant.ha_infoLog_mode) == null ? "-"
						: kpiMap.get(PaasStatisticsConstant.ha_infoLog_mode).getExt_val());// 归档状态健康度
		daasInstanceObj.setScn_growth_statisticsScn_health_score(kpiMap
				.get(PaasStatisticsConstant.scn_growth_statistics_scn_health) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.scn_growth_statistics_scn_health).getExt_val());// SCN健康值健康度
		daasInstanceObj.setTbspAllocationSpaceAllocated_score(kpiMap
				.get(PaasStatisticsConstant.tbspallocation_spaceallocated) == null ? "-" : kpiMap
				.get(PaasStatisticsConstant.tbspallocation_spaceallocated).getExt_val());// 表空间占用率健康度
		daasInstanceObj.setInstance_throughputParses_ps_score(kpiMap
				.get(PaasStatisticsConstant.instance_throughput_parses_ps) == null ? "-" : kpiMap
				.get(PaasStatisticsConstant.instance_throughput_parses_ps).getExt_val());// 解析次数(每秒)健康度
		daasInstanceObj.setInstance_efficiencyCpuusage_ps_score(kpiMap
				.get(PaasStatisticsConstant.instance_efficiency_cpuusage_ps) == null ? "-" : kpiMap
				.get(PaasStatisticsConstant.instance_efficiency_cpuusage_ps).getExt_val());// CPU占用率(每一事物处理)健康度
		daasInstanceObj
				.setUserBlockCount_score(kpiMap.get(PaasStatisticsConstant.userblock_count) == null ? "-"
						: kpiMap.get(PaasStatisticsConstant.userblock_count).getExt_val());// 最大阻塞会话数健康度
		daasInstanceObj.setInstance_throughputCommits_ps_score(kpiMap
				.get(PaasStatisticsConstant.instance_throughput_commits_ps) == null ? "-" : kpiMap
				.get(PaasStatisticsConstant.instance_throughput_commits_ps).getExt_val());// 用户提交数健康度
		daasInstanceObj
				.setFileRead_cs_score(kpiMap.get(PaasStatisticsConstant.file_read_cs) == null ? "-"
						: kpiMap.get(PaasStatisticsConstant.file_read_cs).getExt_val());// 平均文件读取时间
		daasInstanceObj.setSql_responseTime_score(kpiMap
				.get(PaasStatisticsConstant.sql_response_time) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.sql_response_time).getExt_val());// 当前SQL平均响应时间健康度
		daasInstanceObj
				.setFileWrite_cs_score(kpiMap.get(PaasStatisticsConstant.file_write_cs) == null ? "-"
						: kpiMap.get(PaasStatisticsConstant.file_write_cs).getExt_val());// 平均文件写入时间
		daasInstanceObj.setInvalid_objectsCount_score(kpiMap
				.get(PaasStatisticsConstant.invalid_objects_count) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.invalid_objects_count).getExt_val());// 无效对象总数健康度
		daasInstanceObj.setMemory_usageTotal_memory_score(kpiMap
				.get(PaasStatisticsConstant.memory_usage_total_memory) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.memory_usage_total_memory).getExt_val());// 内存使用量健康度
		daasInstanceObj.setHealth_score_availability(kpiMap
				.get(PaasStatisticsConstant.health_score_availability) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_availability).getKpi_value_last());// 健康度（可用性）
		daasInstanceObj.setHealth_score_capacity(kpiMap
				.get(PaasStatisticsConstant.health_score_capacity) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_capacity).getKpi_value_last());// 健康度(容量)
		daasInstanceObj.setHealth_score_efficiency(kpiMap
				.get(PaasStatisticsConstant.health_score_efficiency) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_efficiency).getKpi_value_last());// 健康度(负载)
		daasInstanceObj.setHealth_score_exception(kpiMap
				.get(PaasStatisticsConstant.health_score_exception) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_exception).getKpi_value_last());// 健康度(异常)
		daasInstanceObj.setHealth_score_resource(kpiMap
				.get(PaasStatisticsConstant.health_score_resource) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_resource).getKpi_value_last());// 健康度(资源)
		daasInstanceObj.setHealth_score_response(kpiMap
				.get(PaasStatisticsConstant.health_score_response) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_response).getKpi_value_last());// 健康度(响应)
		return daasInstanceObj;
	}

	/**
	 * 
	 * @Title: queryServerKpiValue
	 * @Description: 查询服务所有kpi的值
	 * @param
	 * @return DaasStatisticsObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-19 下午6:50:20
	 */
	public DaasStatisticsObj queryServerKpiValue(String entity_id) {
		if (daasServiceObj == null) {
			daasServiceObj = new DaasStatisticsObj();
		}
		Map<String, PaasKpiMonitorObj> kpiMap = this.queryStatisticalTable(entity_id);
		PaasEntityObj entityObj = new PaasEntityObj();
		entityObj.setEntity_id(entity_id);
		entityObj = paasEntityService.queryByObj(entityObj);
		daasServiceObj.setEntity_name(entityObj.getEntity_name());
		daasServiceObj.setDbServiceExecs_delta(kpiMap
				.get(PaasStatisticsConstant.dbservice_execs_delta) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_execs_delta).getKpi_value_last());// 执行计数
		daasServiceObj.setDbServicePhysrds_delta(kpiMap
				.get(PaasStatisticsConstant.dbservice_physrds_delta) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_physrds_delta).getKpi_value_last());// 服务物理读数
		daasServiceObj.setDbServiceClus_wtime_delta(kpiMap
				.get(PaasStatisticsConstant.dbservice_clus_wtime_delta) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_clus_wtime_delta).getKpi_value_last());// 集群等待时间
		daasServiceObj.setDbServiceCalls_delta(kpiMap
				.get(PaasStatisticsConstant.dbservice_calls_delta) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_calls_delta).getKpi_value_last());// 服务call总数
		daasServiceObj.setDbServiceConc_wtime_delta(kpiMap
				.get(PaasStatisticsConstant.dbservice_conc_wtime_delta) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_conc_wtime_delta).getKpi_value_last());// 并行等待时间
		daasServiceObj.setDbServiceDBTime_delta(kpiMap
				.get(PaasStatisticsConstant.dbservice_dbtime_delta) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_dbtime_delta).getKpi_value_last());// 数据库时间
		daasServiceObj.setDbServicePhyswrts_delta(kpiMap
				.get(PaasStatisticsConstant.dbservice_physwrts_delta) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_physwrts_delta).getKpi_value_last());// 物理写入数
		daasServiceObj.setDbServiceDBcpu_delta(kpiMap
				.get(PaasStatisticsConstant.dbservice_dbcpu_delta) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_dbcpu_delta).getKpi_value_last());// 数据库CPU
		daasServiceObj.setDbServiceTxns_delta(kpiMap
				.get(PaasStatisticsConstant.dbservice_txns_delta) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_txns_delta).getKpi_value_last());// 事物处理数
		daasServiceObj.setDbServiceIO_wtime_delta(kpiMap
				.get(PaasStatisticsConstant.dbservice_io_wtime_delta) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_io_wtime_delta).getKpi_value_last());// 用户I/O等待时间
		daasServiceObj.setDbServiceElapsed_time_delta(kpiMap
				.get(PaasStatisticsConstant.dbservice_elapsed_time_delta) == null ? "-" : kpiMap
				.get(PaasStatisticsConstant.dbservice_elapsed_time_delta).getKpi_value_last());// 每次用户调用的服务响应时间
		daasServiceObj
				.setDbServiceCpuUtil(kpiMap.get(PaasStatisticsConstant.dbservice_cpuutil) == null ? "-"
						: kpiMap.get(PaasStatisticsConstant.dbservice_cpuutil).getKpi_value_last());// 每次用户调用的服务CPU时间
		// TODO 健康度
		daasServiceObj.setDbServiceExecs_delta_score(kpiMap
				.get(PaasStatisticsConstant.dbservice_execs_delta) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_execs_delta).getExt_val());// 执行计数健康度
		daasServiceObj.setDbServicePhysrds_delta_score(kpiMap
				.get(PaasStatisticsConstant.dbservice_physrds_delta) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_physrds_delta).getExt_val());// 服务物理读数健康度
		daasServiceObj.setDbServiceClus_wtime_delta_score(kpiMap
				.get(PaasStatisticsConstant.dbservice_clus_wtime_delta) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_clus_wtime_delta).getExt_val());// 集群等待时间健康度
		daasServiceObj.setDbServiceCalls_delta_score(kpiMap
				.get(PaasStatisticsConstant.dbservice_calls_delta) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_calls_delta).getExt_val());// 服务call总数健康度
		daasServiceObj.setDbServiceConc_wtime_delta_score(kpiMap
				.get(PaasStatisticsConstant.dbservice_conc_wtime_delta) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_conc_wtime_delta).getExt_val());// 并行等待时间健康度
		daasServiceObj.setDbServiceDBTime_delta_score(kpiMap
				.get(PaasStatisticsConstant.dbservice_dbtime_delta) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_dbtime_delta).getExt_val());// 数据库时间健康度
		daasServiceObj.setDbServicePhyswrts_delta_score(kpiMap
				.get(PaasStatisticsConstant.dbservice_physwrts_delta) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_physwrts_delta).getExt_val());// 物理写入数健康度
		daasServiceObj.setDbServiceDBcpu_delta_score(kpiMap
				.get(PaasStatisticsConstant.dbservice_dbcpu_delta) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_dbcpu_delta).getExt_val());// 数据库CPU健康度
		daasServiceObj.setDbServiceTxns_delta_score(kpiMap
				.get(PaasStatisticsConstant.dbservice_txns_delta) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_txns_delta).getExt_val());// 事物处理数健康度
		daasServiceObj.setDbServiceIO_wtime_delta_score(kpiMap
				.get(PaasStatisticsConstant.dbservice_io_wtime_delta) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_io_wtime_delta).getExt_val());// 用户I/O等待时间健康度
		daasServiceObj.setDbServiceElapsed_time_delta_score(kpiMap
				.get(PaasStatisticsConstant.dbservice_elapsed_time_delta) == null ? "-" : kpiMap
				.get(PaasStatisticsConstant.dbservice_elapsed_time_delta).getExt_val());// 每次用户调用的服务响应时间健康度
		daasServiceObj.setDbServiceCpuUtil_score(kpiMap
				.get(PaasStatisticsConstant.dbservice_cpuutil) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_cpuutil).getExt_val());// 每次用户调用的服务CPU时间健康度

		daasServiceObj.setHealth_score_availability(kpiMap
				.get(PaasStatisticsConstant.health_score_availability) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_availability).getKpi_value_last());// 健康度（可用性）
		daasServiceObj.setHealth_score_capacity(kpiMap
				.get(PaasStatisticsConstant.health_score_capacity) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_capacity).getKpi_value_last());// 健康度(容量)
		daasServiceObj.setHealth_score_efficiency(kpiMap
				.get(PaasStatisticsConstant.health_score_efficiency) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_efficiency).getKpi_value_last());// 健康度(负载)
		daasServiceObj.setHealth_score_exception(kpiMap
				.get(PaasStatisticsConstant.health_score_exception) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_exception).getKpi_value_last());// 健康度(异常)
		daasServiceObj.setHealth_score_resource(kpiMap
				.get(PaasStatisticsConstant.health_score_resource) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_resource).getKpi_value_last());// 健康度(资源)
		daasServiceObj.setHealth_score_response(kpiMap
				.get(PaasStatisticsConstant.health_score_response) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_response).getKpi_value_last());// 健康度(响应)
		return daasServiceObj;
	}

	/**
	 * 
	 * @Title: queryStatisticalTable
	 * @Description: 查询最新数据
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-19 下午4:50:42
	 */
	public Map<String, PaasKpiMonitorObj> queryStatisticalTable(String entity_id) {
		Map<String, PaasKpiMonitorObj> map = new HashMap<String, PaasKpiMonitorObj>();
		PaasKpiMonitorObj kpiObj = new PaasKpiMonitorObj();
		kpiObj.setEntity_id(entity_id);
		List<PaasKpiMonitorObj> valueList = paasStatisticsDao.queryForLastValue(kpiObj);
		for (PaasKpiMonitorObj kpiMonitorObj : valueList) {
			map.put(kpiMonitorObj.getKpi_name(), kpiMonitorObj);
		}
		return map;
	}

	/**
	 * 
	 * @Title: queryRealTimeKpi
	 * @Description: 查询实时数据
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-19 下午4:50:42
	 */
	public Map<String, PaasKpiMonitorObj> queryRealTimeKpi(String entity_id, String timeline,
			String coll_time, String cycle_time) {
		Map<String, PaasKpiMonitorObj> map = new HashMap<String, PaasKpiMonitorObj>();
		PaasKpiMonitorObj kpiObj = new PaasKpiMonitorObj();
		kpiObj.setEntity_id(entity_id);
		kpiObj.setTimeline(timeline);
		String tableName = paasTabService.queryTableNameByCycle_time(coll_time, cycle_time);
		kpiObj.setTableName(tableName);
		List<PaasKpiMonitorObj> valueList = paasStatisticsDao.queryRealTimeKpi(kpiObj);
		for (PaasKpiMonitorObj kpiMonitorObj : valueList) {
			map.put(kpiMonitorObj.getKpi_name(), kpiMonitorObj);
		}
		return map;
	}

	/**
	 * 
	 * @Title: entityAbstract
	 * @Description: 实体摘要
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-18 下午2:08:32
	 */
	public String entityAbstract() {
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		paasEntityObj.setEntity_id(entity_id);
		paasEntityObj = paasEntityService.queryByObj(paasEntityObj);
		List<PaasEntityObj> prolist = paasEntityService.queryForEntityProp(paasEntityObj);
		for (PaasEntityObj entityObj : prolist) {
			map.put(entityObj.getEntity_prop_key(), entityObj.getEntity_prop_val());
		}
		return "entityAbstract";
	}

	/**
	 * 
	 * @Title: entityStatistics
	 * @Description: 服务和实例统计信息
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-19 下午7:38:30
	 */
	public String entityStatistics() {
		this.queryInstanceKpiValue(entity_id, timeline, coll_time, cycle_time);
		this.queryServerKpiValue(entity_id);
		return "entityStatistics";
	}

	/**
	 * 
	 * @Title: showDBChart
	 * @Description: 展示数据库图形
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-24 上午10:49:21
	 */
	public String showDBChart() {
		return "db_chart";
	}

	/**
	 * 
	 * @Title: showInstanceChart
	 * @Description: 展示实例图形
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-24 上午10:49:40
	 */
	public String showInstanceChart() {
		return "instance_chart";
	}

	/**
	 * 
	 * @Title: showServerChart
	 * @Description: 展示服务图形
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-24 上午10:50:04
	 */
	public String showServerChart() {
		return "server_chart";
	}

	/**
	 * 
	 * @Title: listDBCharts
	 * @Description: 数据库图形
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-24 上午9:47:39
	 */
	public String listDBCharts() {
		Map<String, PaasKpiMonitorObj> kpiIds = new HashMap<String, PaasKpiMonitorObj>();
		String title = "";// 标题
		String unit = "";// 单位
		if ("capacity".equals(kpi_type)) {
			title = "容量";
			if (kpi == null || kpi.equals("")) {
				unit = "%";
				kpiIds.put(PaasStatisticsConstant.problemtbsp_pctused_racdb, new PaasKpiMonitorObj(
						PaasStatisticsConstant.problemtbsp_pctused_racdb, "表空间占用率", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.problemtbsp_pctused_racdb)) {
				unit = "%";
				kpiIds.put(PaasStatisticsConstant.problemtbsp_pctused_racdb, new PaasKpiMonitorObj(
						PaasStatisticsConstant.problemtbsp_pctused_racdb, "表空间占用率", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.scn_growth_statistics_scn_health_racdb)) {
				kpiIds.put(PaasStatisticsConstant.scn_growth_statistics_scn_health_racdb,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.scn_growth_statistics_scn_health_racdb,
								"SCN健康值", ChartType.Line));
			}
		} else if ("efficiency".equals(kpi_type)) {
			title = "负载";
			unit = "个数";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(PaasStatisticsConstant.userblock_count_racdb, new PaasKpiMonitorObj(
						PaasStatisticsConstant.userblock_count_racdb, "阻塞会话数", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.userblock_count_racdb)) {
				kpiIds.put(PaasStatisticsConstant.userblock_count_racdb, new PaasKpiMonitorObj(
						PaasStatisticsConstant.userblock_count_racdb, "阻塞会话数", ChartType.Line));
			}
		}
		MapChartSet mcs = new MapChartSet();
		mcs.setEntity_id(entity_id);
		mcs.setCaption(title);
		mcs.setKpiIds(kpiIds);
		mcs.setPotCount(100);
		mcs.setStartData(start_time);
		mcs.setEndData(end_time);
		mcs.setCycle_time(cycle_time);
		FusionCharts fChart = paasTabService.queryChartData(mcs, unit);
		String chartString = JacksonUtil.toJson(fChart);
		PrintWriterOut.printWirter(response, chartString);
		return null;
	}

	/**
	 * 
	 * @Title: listInstanceCharts
	 * @Description: 数据库实例图形
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-24 上午9:47:39
	 */
	public String listInstanceCharts() {
		Map<String, PaasKpiMonitorObj> kpiIds = new HashMap<String, PaasKpiMonitorObj>();
		String title = "";// 标题
		String unit = "";// 单位
		if ("response".equals(kpi_type)) {
			title = "响应";
			unit = "ms";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(PaasStatisticsConstant.file_read_cs, new PaasKpiMonitorObj(
						PaasStatisticsConstant.file_read_cs, "平均文件读取时间(ms)", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.file_read_cs)) {
				kpiIds.put(PaasStatisticsConstant.file_read_cs, new PaasKpiMonitorObj(
						PaasStatisticsConstant.file_read_cs, "平均文件读取时间(ms)", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.file_write_cs)) {
				kpiIds.put(PaasStatisticsConstant.file_write_cs, new PaasKpiMonitorObj(
						PaasStatisticsConstant.file_write_cs, "平均文件写入时间(ms)", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.sql_response_time)) {
				kpiIds.put(PaasStatisticsConstant.sql_response_time,
						new PaasKpiMonitorObj(PaasStatisticsConstant.sql_response_time,
								"当前SQL平均响应时间(ms)", ChartType.Line));
			}
		} else if ("efficiency".equals(kpi_type)) {
			title = "负载";
			unit = "个数";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(PaasStatisticsConstant.instance_throughput_parses_ps,
						new PaasKpiMonitorObj(PaasStatisticsConstant.instance_throughput_parses_ps,
								"解析次数(每秒)", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.instance_throughput_parses_ps)) {
				kpiIds.put(PaasStatisticsConstant.instance_throughput_parses_ps,
						new PaasKpiMonitorObj(PaasStatisticsConstant.instance_throughput_parses_ps,
								"解析次数(每秒)", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.userblock_count)) {
				kpiIds.put(PaasStatisticsConstant.userblock_count, new PaasKpiMonitorObj(
						PaasStatisticsConstant.userblock_count, "阻塞会话数", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.instance_throughput_commits_ps)) {
				kpiIds.put(PaasStatisticsConstant.instance_throughput_commits_ps,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.instance_throughput_commits_ps, "用户提交数（每秒）",
								ChartType.Line));
			}
		} else if ("capacity".equals(kpi_type)) {
			title = "容量";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(PaasStatisticsConstant.scn_growth_statistics_scn_health,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.scn_growth_statistics_scn_health, "SCN健康值",
								ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.scn_growth_statistics_scn_health)) {
				kpiIds.put(PaasStatisticsConstant.scn_growth_statistics_scn_health,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.scn_growth_statistics_scn_health, "SCN健康值",
								ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.tbspallocation_spaceallocated)) {
				unit = "%";
				kpiIds.put(PaasStatisticsConstant.tbspallocation_spaceallocated,
						new PaasKpiMonitorObj(PaasStatisticsConstant.tbspallocation_spaceallocated,
								"表空间占用率", ChartType.Line));
			}

		} else if ("resource".equals(kpi_type)) {
			title = "资源";
			if (kpi == null || kpi.equals("")) {
				unit = "%";
				kpiIds.put(PaasStatisticsConstant.instance_efficiency_cpuusage_ps,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.instance_efficiency_cpuusage_ps, "CPU占用率",
								ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.instance_efficiency_cpuusage_ps)) {
				unit = "%";
				kpiIds.put(PaasStatisticsConstant.instance_efficiency_cpuusage_ps,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.instance_efficiency_cpuusage_ps, "CPU占用率",
								ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.memory_usage_total_memory)) {
				unit = "M";
				kpiIds.put(PaasStatisticsConstant.memory_usage_total_memory, new PaasKpiMonitorObj(
						PaasStatisticsConstant.memory_usage_total_memory, "内存使用量", ChartType.Line));
			}
		} else if ("exception".equals(kpi_type)) {
			title = "异常";
			unit = "个数";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(PaasStatisticsConstant.invalid_objects_count, new PaasKpiMonitorObj(
						PaasStatisticsConstant.invalid_objects_count, "无效对象总数", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.invalid_objects_count)) {
				kpiIds.put(PaasStatisticsConstant.invalid_objects_count, new PaasKpiMonitorObj(
						PaasStatisticsConstant.invalid_objects_count, "无效对象总数", ChartType.Line));
			}
		}
		MapChartSet mcs = new MapChartSet();
		mcs.setEntity_id(entity_id);
		mcs.setCaption(title);
		mcs.setKpiIds(kpiIds);
		mcs.setPotCount(100);
		mcs.setStartData(start_time);
		mcs.setEndData(end_time);
		mcs.setCycle_time(cycle_time);
		FusionCharts fChart = paasTabService.queryChartData(mcs, unit);
		String chartString = JacksonUtil.toJson(fChart);
		PrintWriterOut.printWirter(response, chartString);
		return null;
	}

	/**
	 * 
	 * @Title: listServerCharts
	 * @Description: 数据库服务图形
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-24 上午9:47:39
	 */
	public String listServerCharts() {
		Map<String, PaasKpiMonitorObj> kpiIds = new HashMap<String, PaasKpiMonitorObj>();
		String title = "";// 标题
		String unit = "";// 单位
		if ("efficiency".equals(kpi_type)) {
			title = "负载";
			unit = "个数";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(PaasStatisticsConstant.dbservice_execs_delta, new PaasKpiMonitorObj(
						PaasStatisticsConstant.dbservice_execs_delta, "执行计数", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.dbservice_execs_delta)) {
				kpiIds.put(PaasStatisticsConstant.dbservice_execs_delta, new PaasKpiMonitorObj(
						PaasStatisticsConstant.dbservice_execs_delta, "执行计数", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.dbservice_physrds_delta)) {
				kpiIds.put(PaasStatisticsConstant.dbservice_physrds_delta, new PaasKpiMonitorObj(
						PaasStatisticsConstant.dbservice_physrds_delta, "服务物理读数", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.dbservice_clus_wtime_delta)) {
				unit = "ms";
				kpiIds.put(PaasStatisticsConstant.dbservice_clus_wtime_delta,
						new PaasKpiMonitorObj(PaasStatisticsConstant.dbservice_clus_wtime_delta,
								"集群等待时间(ms)", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.dbservice_calls_delta)) {
				kpiIds.put(PaasStatisticsConstant.dbservice_calls_delta, new PaasKpiMonitorObj(
						PaasStatisticsConstant.dbservice_calls_delta, "服务call总数(ms)",
						ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.dbservice_conc_wtime_delta)) {
				unit = "ms";
				kpiIds.put(PaasStatisticsConstant.dbservice_conc_wtime_delta,
						new PaasKpiMonitorObj(PaasStatisticsConstant.dbservice_conc_wtime_delta,
								"并行等待时间", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.dbservice_dbtime_delta)) {
				unit = "ms";
				kpiIds.put(PaasStatisticsConstant.dbservice_dbtime_delta, new PaasKpiMonitorObj(
						PaasStatisticsConstant.dbservice_dbtime_delta, "数据库时间", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.dbservice_physwrts_delta)) {
				kpiIds.put(PaasStatisticsConstant.dbservice_physwrts_delta, new PaasKpiMonitorObj(
						PaasStatisticsConstant.dbservice_physwrts_delta, "物理写入数", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.dbservice_dbcpu_delta)) {
				kpiIds.put(PaasStatisticsConstant.dbservice_dbcpu_delta, new PaasKpiMonitorObj(
						PaasStatisticsConstant.dbservice_dbcpu_delta, "数据库CPU", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.dbservice_txns_delta)) {
				kpiIds.put(PaasStatisticsConstant.dbservice_txns_delta, new PaasKpiMonitorObj(
						PaasStatisticsConstant.dbservice_txns_delta, "事物处理数", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.dbservice_io_wtime_delta)) {
				unit = "ms";
				kpiIds.put(PaasStatisticsConstant.dbservice_io_wtime_delta, new PaasKpiMonitorObj(
						PaasStatisticsConstant.dbservice_io_wtime_delta, "用户I/O等待时间",
						ChartType.Line));
			}
		} else if ("response".equals(kpi_type)) {
			title = "响应";
			unit = "ms";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(PaasStatisticsConstant.dbservice_elapsed_time_delta,
						new PaasKpiMonitorObj(PaasStatisticsConstant.dbservice_elapsed_time_delta,
								"每次用户调用的服务响应时间(ms)", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.dbservice_elapsed_time_delta)) {
				kpiIds.put(PaasStatisticsConstant.dbservice_elapsed_time_delta,
						new PaasKpiMonitorObj(PaasStatisticsConstant.dbservice_elapsed_time_delta,
								"每次用户调用的服务响应时间(ms)", ChartType.Line));
			}
		} else if ("resource".equals(kpi_type)) {
			title = "资源";
			unit = "ms";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(PaasStatisticsConstant.dbservice_cpuutil, new PaasKpiMonitorObj(
						PaasStatisticsConstant.dbservice_cpuutil, "每次用户调用的服务CPU时间", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.dbservice_cpuutil)) {
				kpiIds.put(PaasStatisticsConstant.dbservice_cpuutil, new PaasKpiMonitorObj(
						PaasStatisticsConstant.dbservice_cpuutil, "每次用户调用的服务CPU时间", ChartType.Line));
			}
		}
		MapChartSet mcs = new MapChartSet();
		mcs.setEntity_id(entity_id);
		mcs.setCaption(title);
		mcs.setKpiIds(kpiIds);
		mcs.setPotCount(100);
		mcs.setStartData(start_time);
		mcs.setEndData(end_time);
		mcs.setCycle_time(cycle_time);
		FusionCharts fChart = paasTabService.queryChartData(mcs, unit);
		String chartString = JacksonUtil.toJson(fChart);
		PrintWriterOut.printWirter(response, chartString);
		return null;
	}

	/**
	 * 
	 * @Title: queryBusinessCount_All
	 * @Description:查询业务总个数,正常个数,异常个数(业务健康度不是100的都是异常)
	 * @param
	 * @return MysqlEntityObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-16 下午2:49:40
	 */
	private PaasEntityObj queryMysqlBusinessCount_All(String id, String server_type,
			String node_type) {
		if (mysqlEntityObj == null) {
			mysqlEntityObj = new PaasEntityObj();
		}
		List busi_normalList = new ArrayList();// 正常业务集合
		List<PaasEntityObj> healthyLimitList = new ArrayList<PaasEntityObj>();
		PaasTreeObj paasTypeObj = new PaasTreeObj();
		if (PaasConstant.DAAS.equals(node_type)) {
			paasTypeObj.setParent_id(id);
		} else if (PaasConstant.DAAS_TYPE.equals(node_type)) {
			paasTypeObj.setId(id);
		}
		paasTypeObj.setServer_type(server_type);
		List<PaasTreeObj> paasList = paasTreeService.queryTreeNodeByObj(paasTypeObj);
		if (paasList != null && paasList.size() > 0) {
			paasTypeObj = paasList.get(0);
			mysqlEntityObj.setEntity_name(paasTypeObj.getName());
			PaasTreeObj paastrObj = new PaasTreeObj();
			paastrObj.setParent_id(paasTypeObj.getId());
			List<PaasTreeObj> busiList = paasTreeService.queryTreeNodeByObj(paastrObj);
			if (busiList != null && busiList.size() > 0) {
				mysqlEntityObj.setBusiCount(busiList.size());// 业务总个数
				for (PaasTreeObj paasBusiObj : busiList) {
					PaasTreeObj treeObj = new PaasTreeObj();
					treeObj.setId(paasBusiObj.getId());
					treeObj.setNode_type(paasBusiObj.getNode_type());
					Double limit = paasTabService.queryBusHealthyLimitForInstanceId(treeObj,
							PaasConstant.DAAS_BUSI);// 查询业务健康度的值
					PaasEntityObj busiLimitObj = new PaasEntityObj();
					busiLimitObj.setId(paasBusiObj.getId());
					busiLimitObj.setEntity_name(paasBusiObj.getName());
					busiLimitObj.setLimit(limit);
					healthyLimitList.add(busiLimitObj);
					if (limit == 100.0) {// 正常
						busi_normalList.add(busiLimitObj);
					}
				}
				List<PaasEntityObj> healList = healthyLimitList;
				for (int i = 0; i < healList.size() - 1; i++) {
					for (int j = 1; j < healList.size() - i; j++) {
						String idData = "";
						Double limitData = 0.0;
						if (healList.get(j - 1).getLimit().compareTo(healList.get(j).getLimit()) > 0) { // 比较两个数的大小
							idData = healList.get(j - 1).getId();
							limitData = healList.get(j - 1).getLimit();
							PaasEntityObj minData = new PaasEntityObj();
							minData.setId(healList.get(j).getId());
							minData.setLimit(healList.get(j).getLimit());
							healList.set((j - 1), minData);
							PaasEntityObj maxData = new PaasEntityObj();
							maxData.setId(idData);
							maxData.setLimit(limitData);
							healList.set(j, maxData);
						}
					}
				}
				mysqlEntityObj.setHealthyLimitList(healList);
				mysqlEntityObj.setBusi_normalCount(busi_normalList.size());// 正常业务
				mysqlEntityObj.setBusi_unusualCount(busiList.size() - busi_normalList.size());// 异常业务
			}
		}
		return mysqlEntityObj;
	}

	/**
	 * 
	 * @Title: queryMysqlDBCount_All
	 * @Description: 查询数据库的总数,正常数,异常数和数据库的详细信息 (健康度不是100,就是异常;)
	 * @param
	 * @return MysqlEntityObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-16 下午3:17:04
	 */
	private PaasEntityObj queryMysqlDBCount_All() throws Exception {
		if (mysqlEntityObj == null) {
			mysqlEntityObj = new PaasEntityObj();
		}
		/**
		 * 查询数据库,实例,服务总个数
		 */
		PaasEntityObj entityTypeObj = new PaasEntityObj();
		entityTypeObj.setEntity_type(PaasConstant.MYSQL);
		List<PaasEntityObj> dbList_all = paasEntityService.queryForEntityList(entityTypeObj);// 查询所有数据库集合
		if (dbList_all != null && dbList_all.size() > 0) {
			mysqlEntityObj.setDbCount_all(dbList_all.size());// 数据库总个数
		}
		List<PaasKpiMonitorObj> instanceNormalList_all = new ArrayList<PaasKpiMonitorObj>();// 正常个数
		PaasEntityObj instanceTypeObj = new PaasEntityObj();
		instanceTypeObj.setEntity_type(PaasConstant.ORACLE_INSTANCE);
		for (PaasEntityObj entityObj : dbList_all) {
			PaasKpiMonitorObj monitorObj = new PaasKpiMonitorObj();
			monitorObj.setEntity_id(entityObj.getEntity_id());
			monitorObj.setKpi_name(PaasStatisticsConstant.health_score_total);
			List<PaasKpiMonitorObj> valueList = paasStatisticsDao.queryForLastValue(monitorObj);
			if (valueList != null && valueList.size() > 0) {
				monitorObj = valueList.get(0);
				if ("100.0".equals(monitorObj.getKpi_value_last())) {// 正常
					instanceNormalList_all.add(monitorObj);// 如果实例是正常的,那么服务也是正常的
				}
			}
		}
		if (dbList_all != null && dbList_all.size() > 0) {
			mysqlEntityObj.setNormalCount_all(instanceNormalList_all.size());// 实例正常总个数
			mysqlEntityObj.setUnusualCount_all(dbList_all.size() - instanceNormalList_all.size());// 实例异常总个数
		}

		return mysqlEntityObj;
	}

	/**
	 * 
	 * @Title: queryMysqlDBInfo
	 * @Description: 查询数据库详细信息(数据库详细信息存放在tb_paas_entity_prop中)和数据库下有多少个实例
	 * @param
	 * @return PaasEntityObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-16 下午5:00:41
	 */
	private PaasEntityObj queryMysqlDBInfo() {
		if (mysqlEntityObj == null) {
			mysqlEntityObj = new PaasEntityObj();
		}
		List dataBaseList = new ArrayList();
		PaasEntityObj dbServerTypeObj = new PaasEntityObj();
		dbServerTypeObj.setEntity_type(PaasConstant.MYSQL);
		List<PaasEntityObj> dbEntityList = paasEntityService.queryForEntityList(dbServerTypeObj);
		for (PaasEntityObj dbEntityObj : dbEntityList) {
			PaasEntityObj entityInfoObj = new PaasEntityObj();
			PaasEntityObj mysqlInfoObj = new PaasEntityObj();
			entityInfoObj.setEntity_id(dbEntityObj.getEntity_id());
			List<PaasEntityObj> prolist = paasEntityService.queryForEntityProp(entityInfoObj);
			for (PaasEntityObj entity : prolist) {
				if ("mysql_db_version".equals(entity.getEntity_prop_key())) {// 版本
					mysqlInfoObj.setMysql_db_version(entity.getEntity_prop_val());
				} else if ("mysql_db_owner".equals(entity.getEntity_prop_key())) {// 所有者
					mysqlInfoObj.setMysql_db_owner(entity.getEntity_prop_val());
				} else if ("mysql_db_desc".equals(entity.getEntity_prop_key())) {// 描述
					mysqlInfoObj.setMysql_db_desc(entity.getEntity_prop_val());
				}
			}
			mysqlInfoObj.setEntity_id(dbEntityObj.getEntity_id());
			mysqlInfoObj.setEntity_name(dbEntityObj.getEntity_name());
			dataBaseList.add(mysqlInfoObj);
			mysqlEntityObj.setResultList(dataBaseList);// 所有数据库集合
		}
		return mysqlEntityObj;
	}

	/**
	 * 
	 * @Title: queryMysqlAlarmCount
	 * @Description: 查询告警
	 * @param
	 * @return PaasEntityObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-7-22 下午3:44:26
	 */
	private PaasEntityObj queryMysqlAlarmCount(String id, String node_type) throws Exception {
		if (mysqlEntityObj == null) {
			mysqlEntityObj = new PaasEntityObj();
		}
		List<String> entityId_AllList = new ArrayList<String>();
		List<PaasAlarmObj> alarmList_all = new ArrayList<PaasAlarmObj>();
		List seriousAlarmList = new ArrayList();
		List mainsAlarmList = new ArrayList();
		List minorAlarmList = new ArrayList();
		List othorAlarmList = new ArrayList();
		PaasEntityObj entityTypeObj = new PaasEntityObj();
		entityTypeObj.setEntity_type(PaasConstant.MYSQL);
		List<PaasEntityObj> mysqlList = paasEntityService.queryForEntityList(entityTypeObj);// 查询数据库集合
		for (PaasEntityObj entObj : mysqlList) {
			entityId_AllList.add(entObj.getEntity_id());
		}
		for (String str : entityId_AllList) {
			PaasAlarmObj obj = new PaasAlarmObj();
			obj.setEntity_id(str);
			List<PaasAlarmObj> alarmList = paasAlarmDao.queryForAlarmList(obj);
			alarmList_all.addAll(alarmList);
		}
		for (PaasAlarmObj alarmObj : alarmList_all) {
			if ("0".equals(alarmObj.getEvent_level())) {
				seriousAlarmList.add(alarmObj);
			} else if ("1".equals(alarmObj.getEvent_level())) {
				mainsAlarmList.add(alarmObj);
			} else if ("2".equals(alarmObj.getEvent_level())) {
				minorAlarmList.add(alarmObj);
			} else if ("3".equals(alarmObj.getEvent_level())) {
				othorAlarmList.add(alarmObj);
			}
		}
		mysqlEntityObj.setSeriousAlarmCount_all(seriousAlarmList.size());
		mysqlEntityObj.setMainAlarmCount_all(mainsAlarmList.size());
		mysqlEntityObj.setMinorAlarmCount_all(minorAlarmList.size());
		mysqlEntityObj.setOtherAlarmCount_all(othorAlarmList.size());
		return mysqlEntityObj;
	}

}
