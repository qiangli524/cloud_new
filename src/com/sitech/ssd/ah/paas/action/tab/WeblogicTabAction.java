package com.sitech.ssd.ah.paas.action.tab;

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
import com.sitech.ssd.ah.paas.domain.tab.MaasStatisticsObj;
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
 * Title: WeblogicTabAction
 * </p>
 * <p>
 * Description: weblogic相关操作
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
 * @createtime 2014-6-26 下午3:46:51
 * 
 */
@Controller("weblogicTabAction")
@Scope("prototype")
public class WeblogicTabAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(WeblogicTabAction.class);

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

	private PaasEntityObj tomcatEntityObj;

	private MaasStatisticsObj maasDataSourceObj;

	private MaasStatisticsObj maasServiceObj;

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

	public MaasStatisticsObj getMaasDataSourceObj() {
		return maasDataSourceObj;
	}

	public void setMaasDataSourceObj(MaasStatisticsObj maasDataSourceObj) {
		this.maasDataSourceObj = maasDataSourceObj;
	}

	public MaasStatisticsObj getMaasServiceObj() {
		return maasServiceObj;
	}

	public void setMaasServiceObj(MaasStatisticsObj maasServiceObj) {
		this.maasServiceObj = maasServiceObj;
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

	public List<PaasEntityObj> getInstanceList() {
		return instanceList;
	}

	public void setInstanceList(List<PaasEntityObj> instanceList) {
		this.instanceList = instanceList;
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

	public PaasEntityObj getTomcatEntityObj() {
		return tomcatEntityObj;
	}

	public void setTomcatEntityObj(PaasEntityObj tomcatEntityObj) {
		this.tomcatEntityObj = tomcatEntityObj;
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
		if (PaasConstant.MAAS.equals(node_type)) {
			paasTypeObj.setParent_id(id);
		} else if (PaasConstant.MAAS_TYPE.equals(node_type)) {
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
							PaasConstant.MAAS_APP);// 查询业务健康度的值
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
	 * @Title: queryDSAndInstanceCount_All
	 * @Description: 查询数据源,实例的总数,正常数,异常数 (实例健康度不是100,就是异常;实例异常,实例下所有服务也是异常的)
	 * @param
	 * @return PaasEntityObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-16 下午3:17:04
	 */
	private PaasEntityObj queryDSAndInstanceCount_All() throws Exception {
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		/**
		 * 查询数据源,实例总个数
		 */
		PaasEntityObj entityTypeObj = new PaasEntityObj();
		entityTypeObj.setEntity_type(PaasConstant.WEBLOGIC_DATASOURCE);
		List<PaasEntityObj> dsList_all = paasEntityService.queryForEntityList(entityTypeObj);// 查询所有数据源集合
		entityTypeObj.setEntity_type(PaasConstant.WEBLOGIC_SERVER);
		List<PaasEntityObj> instanceList_all = paasEntityService.queryForEntityList(entityTypeObj);// 查询所有实例集合
		if (dsList_all != null && dsList_all.size() > 0) {
			paasEntityObj.setDsCount_all(dsList_all.size());// 数据源总个数
		}
		if (instanceList_all != null && instanceList_all.size() > 0) {
			paasEntityObj.setInstanceCount_all(instanceList_all.size());// 实例总个数
		}
		List<PaasKpiMonitorObj> instanceNormalList_all = new ArrayList<PaasKpiMonitorObj>();// 正常个数
		PaasEntityObj instanceTypeObj = new PaasEntityObj();
		instanceTypeObj.setEntity_type(PaasConstant.WEBLOGIC_SERVER);
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
		paasEntityObj.setNormalCount_all(instanceNormalList_all.size());// 实例正常总个数
		paasEntityObj.setUnusualCount_all(instanceList_all.size() - instanceNormalList_all.size());// 实例异常总个数
		return paasEntityObj;
	}

	/**
	 * 
	 * @Title: queryDSAndInstanceCountInBusi
	 * @Description: 查询业务下的数据源,实例
	 * @param
	 * @return PaasEntityObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-7-17 下午5:19:29
	 */
	private PaasEntityObj queryDSAndInstanceCountInBusi(String id) throws Exception {
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
		treeObj.setNode_type(PaasConstant.MAAS_APP);
		List<PaasTreeObj> dsList = paasTreeService.acquireListForType(PaasConstant.MAAS_ENTITY,
				PaasConstant.WEBLOGIC_DATASOURCE, treeObj, new ArrayList<PaasTreeObj>());
		List<PaasTreeObj> instanceList = paasTreeService.acquireListForType(
				PaasConstant.MAAS_ENTITY, PaasConstant.WEBLOGIC_SERVER, treeObj,
				new ArrayList<PaasTreeObj>());
		if (dsList != null && dsList.size() > 0) {
			paasEntityObj.setDsCount(dsList.size());// 数据库个数
		}
		if (instanceList != null && instanceList.size() > 0) {
			paasEntityObj.setInstanceCount(instanceList.size());// 数据库实例个数
		}
		for (PaasTreeObj ptrObj : instanceList) {
			MaasStatisticsObj maasStatisticsObj = new MaasStatisticsObj();
			maasStatisticsObj.setEntity_id(ptrObj.getEntity_id());
			maasStatisticsObj.setEntity_name(ptrObj.getName());
			resultList.add(maasStatisticsObj);
		}
		for (Object object : resultList) {
			MaasStatisticsObj dsObj = (MaasStatisticsObj) object;
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
		if (instanceList != null && instanceList.size() > 0) {
			paasEntityObj.setNormalCount(normalList.size());// 实例正常个数
			paasEntityObj.setUnusualCount(paasEntityObj.getInstanceCount() - normalList.size());// 实例异常个数
		}
		paasEntityObj = this.queryPaasAlarmCount(id, PaasConstant.MAAS_APP);// 告警个数
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
		if (PaasConstant.MAAS.equals(node_type) || PaasConstant.MAAS_TYPE.equals(node_type)) {
			PaasEntityObj entityTypeObj = new PaasEntityObj();
			entityTypeObj.setEntity_type(PaasConstant.WEBLOGIC_SERVER);
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
		} else if (PaasConstant.MAAS_APP.equals(node_type)) {
			PaasTreeObj treeObj = new PaasTreeObj();
			treeObj.setId(id);
			treeObj.setNode_type(PaasConstant.MAAS_APP);
			List<PaasTreeObj> instanceList = paasTreeService.acquireListForType(
					PaasConstant.MAAS_ENTITY, PaasConstant.WEBLOGIC_SERVER, treeObj,
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
	 * @Title: showMaas
	 * @Description: 展示Maas统计信息
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-2 上午9:37:05
	 */
	public String showMaas() throws Exception {
		// weblogic
		paasEntityObj = this.queryBusinessCount_All(id, PaasConstant.WEBLOGIC, node_type);// 业务数量
		paasEntityObj = this.queryDSAndInstanceCount_All();// 数据源,实例
		paasEntityObj = this.queryPaasAlarmCount(id, node_type);// 告警个数
		// tomcat
		tomcatEntityObj = this.queryTomcatBusinessCount_All(id, PaasConstant.TOMCAT, node_type);// 业务数量
		tomcatEntityObj = this.queryTomcatDSAndInstanceCount_All();// 数据源,实例
		tomcatEntityObj = this.queryTomcatAlarmCount(id, node_type);// 告警个数
		return "showMaas";
	}

	/**
	 * 
	 * @Title: showMiddlewareType
	 * @Description: 中间件类型页面展示
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-26 下午4:05:49
	 */
	public String showMiddlewareType() throws Exception {
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		if (resultList == null) {
			resultList = new ArrayList();
		}
		paasEntityObj = this.queryBusinessCount_All(id, PaasConstant.WEBLOGIC, node_type);// 业务数量
		paasEntityObj = this.queryDSAndInstanceCount_All();// 数据源,实例数量

		if (paasEntityObj.getHealthyLimitList() != null
				&& paasEntityObj.getHealthyLimitList().size() > 0) {
			paasEntityObj = this.queryDSAndInstanceCountInBusi(paasEntityObj.getHealthyLimitList()
					.get(0).getId());
		}
		paasEntityObj = this.queryPaasAlarmCount(id, node_type);// 告警总数
		return "showMiddlewareType";
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
			fusionCharts = paasTabService.queryBusiTopN(paasEntityObj, PaasConstant.MAAS_APP);
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
		paasEntityObj = this.queryDSAndInstanceCountInBusi(id);
		JSONObject json = new JSONObject();
		json.put("paasObj", paasEntityObj);
		PrintWriterOut.printWirter(response, json.toString());
		return null;
	}

	/**
	 * 
	 * @Title: showMiddlewareBusiness
	 * @Description: 中间件业务节点页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-26 下午4:13:32
	 */
	public String showMiddlewareBusiness() throws Exception {
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		paasEntityObj = this.queryDSAndInstanceCountInBusi(id);
		paasEntityObj = this.queryPaasAlarmCount(id, PaasConstant.DAAS_BUSI);// 告警数
		return "showMiddlewareBusiness";
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
		if (PaasConstant.MAAS_APP.equals(node_type)) {
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
			if (PaasConstant.MAAS_APP.equals(treeObj.getNode_type())) {
				setObj.setKpi_name(PaasStatisticsConstant.health_score_average);
			} else if (PaasConstant.MAAS_ENTITY.equals(treeObj.getNode_type())) {
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
		if (resultList == null) {
			resultList = new ArrayList();
		}
		PaasTreeObj treeObj = new PaasTreeObj();
		treeObj.setParent_id(id);
		treeObj.setNode_type(PaasConstant.MAAS_DOMAIN);
		treeObj.setServer_type(PaasConstant.WEBLOGIC_SERVER);
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
		if (PaasConstant.MAAS.equals(node_type)) {
			PaasTreeObj paasTreeObj = new PaasTreeObj();
			paasTreeObj.setServer_type(PaasConstant.WEBLOGIC);
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
							PaasConstant.MAAS_APP);
				} catch (Exception e) {
					e.printStackTrace();
				}
				String result = JacksonUtil.toJson(fusionCharts);
				stringList.add(result);
			}
			json.put("jsonXml", stringList);
		} else if (PaasConstant.MAAS_TYPE.equals(node_type)) {
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
		} else if (PaasConstant.MAAS_APP.equals(node_type)) {
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
	 * @Title: queryDataSourceList
	 * @Description: 数据源
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-26 下午4:17:19
	 */
	public String queryDataSourceList() {
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		PaasEntityObj entityObj = new PaasEntityObj();
		entityObj.setParent_id(id);
		entityObj.setPagination(this.getPaginater().initPagination(request));
		resultList = paasTabService.queryNodeList(entityObj);
		if (resultList != null && resultList.size() > 0) {
			paasEntityObj.setDsCount(resultList.size());// 数据源个数
		}
		return "queryDataSourceList";
	}

	/**
	 * 
	 * @Title: queryDataSourceListByEntityId
	 * @Description: 通过entity_id查询数据源指标
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-26 下午4:58:14
	 */
	public String queryDataSourceListByEntityId() {
		this.queryDataSourceKpiValue(entity_id);
		JSONObject json = new JSONObject();
		json.put("maasObj", maasDataSourceObj);
		PrintWriterOut.printWirter(response, json.toString());
		return null;
	}

	/**
	 * 
	 * @Title: queryServerList
	 * @Description: 实例
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-19 上午11:12:55
	 */
	public String queryServerList() {
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		if (businessList == null) {
			businessList = new ArrayList<PaasEntityObj>();
		}
		paasEntityObj.setParent_id(id);
		paasEntityObj.setKpi_name(PaasStatisticsConstant.health_score_total);
		paasEntityObj.setPagination(this.getPaginater().initPagination(request));
		resultList = paasTabService.queryInstanceNodeList(paasEntityObj);
		if (resultList != null && resultList.size() > 0) {
			paasEntityObj.setInstanceCount(resultList.size());// 实例个数
			List normalList = new ArrayList();
			for (Object obj : resultList) {
				PaasEntityObj entObj = new PaasEntityObj();
				entObj = (PaasEntityObj) obj;
				PaasKpiMonitorObj monitorObj = new PaasKpiMonitorObj();
				monitorObj.setEntity_id(entObj.getEntity_id());
				monitorObj.setKpi_name(PaasStatisticsConstant.health_score_total);
				List<PaasKpiMonitorObj> valueList = paasStatisticsDao.queryForLastValue(monitorObj);// 查询健康度
				if (valueList != null && valueList.size() > 0) {
					monitorObj = valueList.get(0);
					if ("100.0".equals(monitorObj.getKpi_value_last())) {// 正常
						normalList.add(monitorObj);
					}
				}
			}
			paasEntityObj.setNormalCount(normalList.size());// 实例正常个数
			paasEntityObj.setUnusualCount(resultList.size() - normalList.size());// 实例异常个数
		}
		return "queryServerList";
	}

	/**
	 * 
	 * @Title: queryInstanceListByName
	 * @Description: 通过名字查询实例集合
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-23 下午6:09:38
	 */
	public String queryInstanceListByName() {
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		paasEntityObj.setParent_id(id);
		paasEntityObj.setKpi_name(PaasStatisticsConstant.health_score_total);
		paasEntityObj.setPagination(this.getPaginater().initPagination(request));
		resultList = paasTabService.queryInstanceNodeList(paasEntityObj);
		for (Object obj : resultList) {
			PaasEntityObj paEnObj = (PaasEntityObj) obj;
			paEnObj.setCycle_time(cycle_time);
			obj = paEnObj;
		}
		JSONObject json = new JSONObject();
		json.put("resultList", resultList);
		PrintWriterOut.printWirter(response, json.toString());
		return null;
	}

	/**
	 * 
	 * @Title: queryServerListByEntityId
	 * @Description: 通过entity_id查询实例指标
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-20 上午9:22:36
	 */
	public String queryServerListByEntityId() {
		this.queryServerKpiValue(entity_id, null, null, null);
		JSONObject json = new JSONObject();
		json.put("maasObj", maasServiceObj);
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
	 * @Title: queryDataSourceKpiValue
	 * @Description: 查询 数据源所有kpi的值
	 * @param
	 * @return MaasStatisticsObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-26 下午4:55:44
	 */
	public MaasStatisticsObj queryDataSourceKpiValue(String entity_id) {
		if (maasDataSourceObj == null) {
			maasDataSourceObj = new MaasStatisticsObj();
		}
		Map<String, PaasKpiMonitorObj> kpiMap = this.queryStatisticalTable(entity_id);
		PaasEntityObj entityObj = new PaasEntityObj();
		entityObj.setEntity_id(entity_id);
		entityObj = paasEntityService.queryByObj(entityObj);
		maasDataSourceObj.setEntity_name(entityObj.getEntity_name());
		// TODO KPI
		maasDataSourceObj.setDatasource_state_state(kpiMap
				.get(PaasStatisticsConstant.datasource_state_state) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.datasource_state_state).getKpi_value_last());// 数据源状态
		maasDataSourceObj
				.setDatasource_connectionRequestFailures_throughput(kpiMap
						.get(PaasStatisticsConstant.datasource_connectionRequestFailures_throughput) == null ? "-"
						: kpiMap.get(
								PaasStatisticsConstant.datasource_connectionRequestFailures_throughput)
								.getKpi_value_last());// 数据源连接请求失败数(每分钟)
		maasDataSourceObj.setDatasource_connectionRequests_throughput(kpiMap
				.get(PaasStatisticsConstant.datasource_connectionRequests_throughput) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.datasource_connectionRequests_throughput)
						.getKpi_value_last());// 数据源连接请求数(每分钟)
		maasDataSourceObj.setDatasource_connectionCreated_time(kpiMap
				.get(PaasStatisticsConstant.datasource_connectionCreated_time) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.datasource_connectionCreated_time)
						.getKpi_value_last());// 数据源连接创建时间(毫秒)
		maasDataSourceObj.setDatasource_connectionPoolSize_active(kpiMap
				.get(PaasStatisticsConstant.datasource_connectionPoolSize_active) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.datasource_connectionPoolSize_active)
						.getKpi_value_last());// 数据源连接池大小
		maasDataSourceObj.setDatasource_connectionAvailable_active(kpiMap
				.get(PaasStatisticsConstant.datasource_connectionAvailable_active) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.datasource_connectionAvailable_active)
						.getKpi_value_last());// 数据源可用连接数
		maasDataSourceObj.setDatasource_connectionUnavailable_active(kpiMap
				.get(PaasStatisticsConstant.datasource_connectionUnavailable_active) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.datasource_connectionUnavailable_active)
						.getKpi_value_last());// 数据源不可用连接数

		// TODO KPI健康度
		maasDataSourceObj.setDatasource_state_state_score(kpiMap
				.get(PaasStatisticsConstant.datasource_state_state) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.datasource_state_state).getExt_val());// 数据源状态
		maasDataSourceObj
				.setDatasource_connectionRequestFailures_throughput_score(kpiMap
						.get(PaasStatisticsConstant.datasource_connectionRequestFailures_throughput) == null ? "-"
						: kpiMap.get(
								PaasStatisticsConstant.datasource_connectionRequestFailures_throughput)
								.getExt_val());// 数据源连接请求失败数(每分钟)
		maasDataSourceObj.setDatasource_connectionRequests_throughput_score(kpiMap
				.get(PaasStatisticsConstant.datasource_connectionRequests_throughput) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.datasource_connectionRequests_throughput)
						.getExt_val());// 数据源连接请求数(每分钟)
		maasDataSourceObj
				.setDatasource_connectionCreated_time_score(kpiMap
						.get(PaasStatisticsConstant.datasource_connectionCreated_time) == null ? "-"
						: kpiMap.get(PaasStatisticsConstant.datasource_connectionCreated_time)
								.getExt_val());// 数据源连接创建时间(毫秒)
		maasDataSourceObj.setDatasource_connectionPoolSize_active_score(kpiMap
				.get(PaasStatisticsConstant.datasource_connectionPoolSize_active) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.datasource_connectionPoolSize_active)
						.getExt_val());// 数据源连接池大小
		maasDataSourceObj.setDatasource_connectionAvailable_active_score(kpiMap
				.get(PaasStatisticsConstant.datasource_connectionAvailable_active) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.datasource_connectionAvailable_active)
						.getExt_val());// 数据源可用连接数
		maasDataSourceObj.setDatasource_connectionUnavailable_active_score(kpiMap
				.get(PaasStatisticsConstant.datasource_connectionUnavailable_active) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.datasource_connectionUnavailable_active)
						.getExt_val());// 数据源不可用连接数
		maasDataSourceObj.setHealth_score_availability(kpiMap
				.get(PaasStatisticsConstant.health_score_availability) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_availability).getKpi_value_last());// 健康度（可用性）
		maasDataSourceObj.setHealth_score_capacity(kpiMap
				.get(PaasStatisticsConstant.health_score_capacity) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_capacity).getKpi_value_last());// 健康度(容量)
		maasDataSourceObj.setHealth_score_efficiency(kpiMap
				.get(PaasStatisticsConstant.health_score_efficiency) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_efficiency).getKpi_value_last());// 健康度(负载)
		maasDataSourceObj.setHealth_score_exception(kpiMap
				.get(PaasStatisticsConstant.health_score_exception) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_exception).getKpi_value_last());// 健康度(异常)
		maasDataSourceObj.setHealth_score_response(kpiMap
				.get(PaasStatisticsConstant.health_score_response) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_response).getKpi_value_last());// 健康度(响应)

		return maasDataSourceObj;
	}

	/**
	 * 
	 * @Title: queryServerKpiValue
	 * @Description: 查询服务所有kpi的值
	 * @param
	 * @return MaasStatisticsObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-26 下午5:10:35
	 */
	public MaasStatisticsObj queryServerKpiValue(String entity_id, String timeline,
			String coll_time, String cycle_time) {
		if (maasServiceObj == null) {
			maasServiceObj = new MaasStatisticsObj();
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
		maasServiceObj.setEntity_name(entityObj.getEntity_name());
		// TODO KPI
		maasServiceObj.setServer_servlet_jsp_service_time(kpiMap
				.get(PaasStatisticsConstant.server_servlet_jsp_service_time) == null ? "-" : kpiMap
				.get(PaasStatisticsConstant.server_servlet_jsp_service_time).getKpi_value_last());// 页面请求处理时间(毫秒)
		maasServiceObj.setServer_servlet_jsp_service_throughput(kpiMap
				.get(PaasStatisticsConstant.server_servlet_jsp_service_throughput) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.server_servlet_jsp_service_throughput)
						.getKpi_value_last());// 页面请求处理数(每分钟)
		maasServiceObj.setThread_pool_pendingRequests_active(kpiMap
				.get(PaasStatisticsConstant.thread_pool_pendingRequests_active) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.thread_pool_pendingRequests_active)
						.getKpi_value_last());// 线程池暂挂请求数
		maasServiceObj.setThread_pool_totalThreads_active(kpiMap
				.get(PaasStatisticsConstant.thread_pool_totalThreads_active) == null ? "-" : kpiMap
				.get(PaasStatisticsConstant.thread_pool_totalThreads_active).getKpi_value_last());// 线程池线程总数
		maasServiceObj.setThread_pool_idleThreads_active(kpiMap
				.get(PaasStatisticsConstant.thread_pool_idleThreads_active) == null ? "-" : kpiMap
				.get(PaasStatisticsConstant.thread_pool_idleThreads_active).getKpi_value_last());// 线程池空闲线程数
		maasServiceObj.setJvm_garbage_collectors_invocationsPerMin(kpiMap
				.get(PaasStatisticsConstant.jvm_garbage_collectors_invocationsPerMin) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.jvm_garbage_collectors_invocationsPerMin)
						.getKpi_value_last());// GC调用数(每分钟)
		maasServiceObj
				.setJvm_garbage_collectors_msecsPerInvocation(kpiMap
						.get(PaasStatisticsConstant.jvm_garbage_collectors_msecsPerInvocation) == null ? "-"
						: kpiMap.get(
								PaasStatisticsConstant.jvm_garbage_collectors_msecsPerInvocation)
								.getKpi_value_last());// GC调用时间(毫秒)
		maasServiceObj.setJvm_garbage_collectors_percentTimeInGc(kpiMap
				.get(PaasStatisticsConstant.jvm_garbage_collectors_percentTimeInGc) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.jvm_garbage_collectors_percentTimeInGc)
						.getKpi_value_last());// GC时间成本(%)
		maasServiceObj.setServer_1_sockets_active(kpiMap
				.get(PaasStatisticsConstant.server_1_sockets_active) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.server_1_sockets_active).getKpi_value_last());// 活动套接字数
		maasServiceObj.setServer_level_work_manager_wmStuck_active(kpiMap
				.get(PaasStatisticsConstant.server_level_work_manager_wmStuck_active) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.server_level_work_manager_wmStuck_active)
						.getKpi_value_last());// 工作管理器粘滞线程数
		maasServiceObj
				.setServer_level_work_manager_wmProcessed_throughput(kpiMap
						.get(PaasStatisticsConstant.server_level_work_manager_wmProcessed_throughput) == null ? "-"
						: kpiMap.get(
								PaasStatisticsConstant.server_level_work_manager_wmProcessed_throughput)
								.getKpi_value_last());// 工作管理器请求数(每分钟)
		maasServiceObj
				.setServer_state_state(kpiMap.get(PaasStatisticsConstant.server_state_state) == null ? "-"
						: kpiMap.get(PaasStatisticsConstant.server_state_state).getKpi_value_last());// 实例状态
		maasServiceObj.setJvm_heapUsedPercentage_value(kpiMap
				.get(PaasStatisticsConstant.jvm_heapUsedPercentage_value) == null ? "-" : kpiMap
				.get(PaasStatisticsConstant.jvm_heapUsedPercentage_value).getKpi_value_last());// JVM堆使用率(%)
		maasServiceObj.setJvm_threads_peakThreadCount_value(kpiMap
				.get(PaasStatisticsConstant.jvm_threads_peakThreadCount_value) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.jvm_threads_peakThreadCount_value)
						.getKpi_value_last());// JVM线程峰值数
		maasServiceObj.setJvm_threads_deadlockedThreadCount_value(kpiMap
				.get(PaasStatisticsConstant.jvm_threads_deadlockedThreadCount_value) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.jvm_threads_deadlockedThreadCount_value)
						.getKpi_value_last());// JVM死锁线程数
		maasServiceObj.setJvm_memory_usage_nonHeapMemoryUsed_value(kpiMap
				.get(PaasStatisticsConstant.jvm_memory_usage_nonHeapMemoryUsed_value) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.jvm_memory_usage_nonHeapMemoryUsed_value)
						.getKpi_value_last());// JVM非堆内存使用量(KB)
		maasServiceObj.setJvm_memory_usage_heapMemoryUsed_value(kpiMap
				.get(PaasStatisticsConstant.dbservice_conc_wtime_delta) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_conc_wtime_delta).getKpi_value_last());// JVM堆内存使用量(KB)
		maasServiceObj.setJvm_cpuUsage_percentage(kpiMap
				.get(PaasStatisticsConstant.dbservice_dbtime_delta) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.dbservice_dbtime_delta).getKpi_value_last());// CPU占用率(%)

		// TODO 健康度
		maasServiceObj.setServer_servlet_jsp_service_time_score(kpiMap
				.get(PaasStatisticsConstant.server_servlet_jsp_service_time) == null ? "-" : kpiMap
				.get(PaasStatisticsConstant.server_servlet_jsp_service_time).getExt_val());// 页面请求处理时间(毫秒)
		maasServiceObj.setServer_servlet_jsp_service_throughput_score(kpiMap
				.get(PaasStatisticsConstant.server_servlet_jsp_service_throughput) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.server_servlet_jsp_service_throughput)
						.getExt_val());// 页面请求处理数(每分钟)
		maasServiceObj.setThread_pool_pendingRequests_active_score(kpiMap
				.get(PaasStatisticsConstant.thread_pool_pendingRequests_active) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.thread_pool_pendingRequests_active)
						.getExt_val());// 线程池暂挂请求数
		maasServiceObj.setThread_pool_totalThreads_active_score(kpiMap
				.get(PaasStatisticsConstant.thread_pool_totalThreads_active) == null ? "-" : kpiMap
				.get(PaasStatisticsConstant.thread_pool_totalThreads_active).getExt_val());// 线程池线程总数
		maasServiceObj.setThread_pool_idleThreads_active_score(kpiMap
				.get(PaasStatisticsConstant.thread_pool_idleThreads_active) == null ? "-" : kpiMap
				.get(PaasStatisticsConstant.thread_pool_idleThreads_active).getExt_val());// 线程池空闲线程数
		maasServiceObj.setJvm_garbage_collectors_invocationsPerMin_score(kpiMap
				.get(PaasStatisticsConstant.jvm_garbage_collectors_invocationsPerMin) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.jvm_garbage_collectors_invocationsPerMin)
						.getExt_val());// GC调用数(每分钟)
		maasServiceObj
				.setJvm_garbage_collectors_msecsPerInvocation_score(kpiMap
						.get(PaasStatisticsConstant.jvm_garbage_collectors_msecsPerInvocation) == null ? "-"
						: kpiMap.get(
								PaasStatisticsConstant.jvm_garbage_collectors_msecsPerInvocation)
								.getExt_val());// GC调用时间(毫秒)
		maasServiceObj.setJvm_garbage_collectors_percentTimeInGc_score(kpiMap
				.get(PaasStatisticsConstant.jvm_garbage_collectors_percentTimeInGc) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.jvm_garbage_collectors_percentTimeInGc)
						.getExt_val());// GC时间成本(%)
		maasServiceObj.setServer_1_sockets_active_score(kpiMap
				.get(PaasStatisticsConstant.server_1_sockets_active) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.server_1_sockets_active).getExt_val());// 活动套接字数
		maasServiceObj.setServer_level_work_manager_wmStuck_active_score(kpiMap
				.get(PaasStatisticsConstant.server_level_work_manager_wmStuck_active) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.server_level_work_manager_wmStuck_active)
						.getExt_val());// 工作管理器粘滞线程数
		maasServiceObj
				.setServer_level_work_manager_wmProcessed_throughput_score(kpiMap
						.get(PaasStatisticsConstant.server_level_work_manager_wmProcessed_throughput) == null ? "-"
						: kpiMap.get(
								PaasStatisticsConstant.server_level_work_manager_wmProcessed_throughput)
								.getExt_val());// 工作管理器请求数(每分钟)
		maasServiceObj.setServer_state_state_score(kpiMap
				.get(PaasStatisticsConstant.server_state_state) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.server_state_state).getExt_val());// 实例状态
		maasServiceObj.setJvm_heapUsedPercentage_value_score(kpiMap
				.get(PaasStatisticsConstant.jvm_heapUsedPercentage_value) == null ? "-" : kpiMap
				.get(PaasStatisticsConstant.jvm_heapUsedPercentage_value).getExt_val());// JVM堆使用率(%)
		maasServiceObj
				.setJvm_threads_peakThreadCount_value_score(kpiMap
						.get(PaasStatisticsConstant.jvm_threads_peakThreadCount_value) == null ? "-"
						: kpiMap.get(PaasStatisticsConstant.jvm_threads_peakThreadCount_value)
								.getExt_val());// JVM线程峰值数
		maasServiceObj.setJvm_threads_deadlockedThreadCount_value_score(kpiMap
				.get(PaasStatisticsConstant.jvm_threads_deadlockedThreadCount_value) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.jvm_threads_deadlockedThreadCount_value)
						.getExt_val());// JVM死锁线程数
		maasServiceObj.setJvm_memory_usage_nonHeapMemoryUsed_value_score(kpiMap
				.get(PaasStatisticsConstant.jvm_memory_usage_nonHeapMemoryUsed_value) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.jvm_memory_usage_nonHeapMemoryUsed_value)
						.getExt_val());// JVM非堆内存使用量(KB)
		maasServiceObj.setJvm_memory_usage_heapMemoryUsed_value_score(kpiMap
				.get(PaasStatisticsConstant.jvm_memory_usage_heapMemoryUsed_value) == null ? "-"
				: kpiMap.get(PaasStatisticsConstant.jvm_memory_usage_heapMemoryUsed_value)
						.getExt_val());// JVM堆内存使用量(KB)
		maasServiceObj.setJvm_cpuUsage_percentage_score(kpiMap
				.get(PaasStatisticsConstant.jvm_cpuUsage_percentage) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.jvm_cpuUsage_percentage).getExt_val());// CPU占用率(%)

		maasServiceObj.setHealth_score_availability(kpiMap
				.get(PaasStatisticsConstant.health_score_availability) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_availability).getKpi_value_last());// 健康度（可用性）
		maasServiceObj.setHealth_score_capacity(kpiMap
				.get(PaasStatisticsConstant.health_score_capacity) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_capacity).getKpi_value_last());// 健康度(容量)
		maasServiceObj.setHealth_score_efficiency(kpiMap
				.get(PaasStatisticsConstant.health_score_efficiency) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_efficiency).getKpi_value_last());// 健康度(负载)
		maasServiceObj.setHealth_score_exception(kpiMap
				.get(PaasStatisticsConstant.health_score_exception) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_exception).getKpi_value_last());// 健康度(异常)
		maasServiceObj.setHealth_score_resource(kpiMap
				.get(PaasStatisticsConstant.health_score_resource) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_resource).getKpi_value_last());// 健康度(资源)
		maasServiceObj.setHealth_score_response(kpiMap
				.get(PaasStatisticsConstant.health_score_response) == null ? "-" : kpiMap.get(
				PaasStatisticsConstant.health_score_response).getKpi_value_last());// 健康度(响应)
		return maasServiceObj;
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
		this.queryDataSourceKpiValue(entity_id);
		this.queryServerKpiValue(entity_id, timeline, coll_time, cycle_time);
		return "entityStatistics";
	}

	/**
	 * 
	 * @Title: showDataSourceChart
	 * @Description: 展示数据源图形
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-26 下午5:26:24
	 */
	public String showDataSourceChart() {
		return "dataSource_chart";
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
	 * @Title: listDataSourceCharts
	 * @Description: 数据源图形
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-27 下午2:12:32
	 */
	public String listDataSourceCharts() {
		Map<String, PaasKpiMonitorObj> kpiIds = new HashMap<String, PaasKpiMonitorObj>();
		String title = "";// 标题
		String unit = "";// 单位
		if ("response".equals(kpi_type)) {
			title = "响应";
			unit = "ms";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(PaasStatisticsConstant.datasource_connectionCreated_time,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.datasource_connectionCreated_time,
								"数据源连接创建时间(ms)", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.datasource_connectionCreated_time)) {
				kpiIds.put(PaasStatisticsConstant.datasource_connectionCreated_time,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.datasource_connectionCreated_time,
								"数据源连接创建时间", ChartType.Line));
			}
		} else if ("efficiency".equals(kpi_type)) {
			title = "负载";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(PaasStatisticsConstant.datasource_connectionRequests_throughput,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.datasource_connectionRequests_throughput,
								"数据源连接请求数(/min)", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.datasource_connectionRequests_throughput)) {
				kpiIds.put(PaasStatisticsConstant.datasource_connectionRequests_throughput,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.datasource_connectionRequests_throughput,
								"数据源连接请求数", ChartType.Line));
			}
		} else if ("capacity".equals(kpi_type)) {
			title = "容量";
			unit = "个数";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(PaasStatisticsConstant.datasource_connectionPoolSize_active,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.datasource_connectionPoolSize_active,
								"数据源连接池大小", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.datasource_connectionPoolSize_active)) {
				kpiIds.put(PaasStatisticsConstant.datasource_connectionPoolSize_active,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.datasource_connectionPoolSize_active,
								"数据源连接池大小", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.datasource_connectionAvailable_active)) {
				kpiIds.put(PaasStatisticsConstant.datasource_connectionAvailable_active,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.datasource_connectionAvailable_active,
								"数据源可用连接数", ChartType.Line));
			}

		} else if ("exception".equals(kpi_type)) {
			title = "异常";
			unit = "个数";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(PaasStatisticsConstant.datasource_connectionUnavailable_active,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.datasource_connectionUnavailable_active,
								"数据源不可用连接数", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.datasource_connectionUnavailable_active)) {
				kpiIds.put(PaasStatisticsConstant.datasource_connectionUnavailable_active,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.datasource_connectionUnavailable_active,
								"数据源不可用连接数", ChartType.Line));
			} else if (kpi
					.equals(PaasStatisticsConstant.datasource_connectionRequestFailures_throughput)) {
				kpiIds.put(
						PaasStatisticsConstant.datasource_connectionRequestFailures_throughput,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.datasource_connectionRequestFailures_throughput,
								"数据源连接请求失败数(/min)", ChartType.Line));
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
	 * @Description: 服务图形
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
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(PaasStatisticsConstant.jvm_garbage_collectors_invocationsPerMin,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.jvm_garbage_collectors_invocationsPerMin,
								"GC调用数", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.jvm_garbage_collectors_invocationsPerMin)) {
				kpiIds.put(PaasStatisticsConstant.jvm_garbage_collectors_invocationsPerMin,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.jvm_garbage_collectors_invocationsPerMin,
								"GC调用数", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.server_servlet_jsp_service_throughput)) {
				kpiIds.put(PaasStatisticsConstant.server_servlet_jsp_service_throughput,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.server_servlet_jsp_service_throughput,
								"页面请求处理数", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.thread_pool_totalThreads_active)) {
				kpiIds.put(PaasStatisticsConstant.thread_pool_totalThreads_active,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.thread_pool_totalThreads_active, "线程池线程总数",
								ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.thread_pool_pendingRequests_active)) {
				kpiIds.put(PaasStatisticsConstant.thread_pool_pendingRequests_active,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.thread_pool_pendingRequests_active,
								"线程池暂挂请求数", ChartType.Line));
			} else if (kpi
					.equals(PaasStatisticsConstant.server_level_work_manager_wmProcessed_throughput)) {
				kpiIds.put(
						PaasStatisticsConstant.server_level_work_manager_wmProcessed_throughput,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.server_level_work_manager_wmProcessed_throughput,
								"工作管理器请求数", ChartType.Line));
			}
		} else if ("response".equals(kpi_type)) {
			title = "响应";
			unit = "ms";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(PaasStatisticsConstant.jvm_garbage_collectors_msecsPerInvocation,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.jvm_garbage_collectors_msecsPerInvocation,
								"GC调用时间", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.jvm_garbage_collectors_msecsPerInvocation)) {
				kpiIds.put(PaasStatisticsConstant.jvm_garbage_collectors_msecsPerInvocation,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.jvm_garbage_collectors_msecsPerInvocation,
								"GC调用时间", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.server_servlet_jsp_service_time)) {
				kpiIds.put(PaasStatisticsConstant.server_servlet_jsp_service_time,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.server_servlet_jsp_service_time, "页面请求处理时间",
								ChartType.Line));
			}
		} else if ("resource".equals(kpi_type)) {
			title = "资源";
			unit = "ms";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(PaasStatisticsConstant.jvm_cpuUsage_percentage, new PaasKpiMonitorObj(
						PaasStatisticsConstant.jvm_cpuUsage_percentage, "CPU占用率", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.jvm_cpuUsage_percentage)) {
				kpiIds.put(PaasStatisticsConstant.jvm_cpuUsage_percentage, new PaasKpiMonitorObj(
						PaasStatisticsConstant.jvm_cpuUsage_percentage, "CPU占用率", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.jvm_garbage_collectors_percentTimeInGc)) {
				kpiIds.put(PaasStatisticsConstant.jvm_garbage_collectors_percentTimeInGc,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.jvm_garbage_collectors_percentTimeInGc,
								"GC时间成本", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.server_1_sockets_active)) {
				kpiIds.put(PaasStatisticsConstant.server_1_sockets_active, new PaasKpiMonitorObj(
						PaasStatisticsConstant.server_1_sockets_active, "活动套接字数", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.jvm_heapUsedPercentage_value)) {
				kpiIds.put(PaasStatisticsConstant.jvm_heapUsedPercentage_value,
						new PaasKpiMonitorObj(PaasStatisticsConstant.jvm_heapUsedPercentage_value,
								"JVM堆使用率", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.jvm_threads_peakThreadCount_value)) {
				kpiIds.put(PaasStatisticsConstant.jvm_threads_peakThreadCount_value,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.jvm_threads_peakThreadCount_value,
								"JVM线程峰值数", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.jvm_memory_usage_heapMemoryUsed_value)) {
				kpiIds.put(PaasStatisticsConstant.jvm_memory_usage_heapMemoryUsed_value,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.jvm_memory_usage_heapMemoryUsed_value,
								"JVM堆内存使用量", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.jvm_memory_usage_nonHeapMemoryUsed_value)) {
				kpiIds.put(PaasStatisticsConstant.jvm_memory_usage_nonHeapMemoryUsed_value,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.jvm_memory_usage_nonHeapMemoryUsed_value,
								"JVM非堆内存使用量", ChartType.Line));
			}
		} else if ("capacity".equals(kpi_type)) {
			title = "容量";
			unit = "%";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(PaasStatisticsConstant.thread_pool_idleThreads_active,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.thread_pool_idleThreads_active, "线程池空闲线程数",
								ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.thread_pool_idleThreads_active)) {
				kpiIds.put(PaasStatisticsConstant.thread_pool_idleThreads_active,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.thread_pool_idleThreads_active, "线程池空闲线程数",
								ChartType.Line));
			}
		} else if ("exception".equals(kpi_type)) {
			title = "异常";
			unit = "个数";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(PaasStatisticsConstant.jvm_threads_deadlockedThreadCount_value,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.jvm_threads_deadlockedThreadCount_value,
								"JVM死锁线程数", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.jvm_threads_deadlockedThreadCount_value)) {
				kpiIds.put(PaasStatisticsConstant.jvm_threads_deadlockedThreadCount_value,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.jvm_threads_deadlockedThreadCount_value,
								"JVM死锁线程数", ChartType.Line));
			} else if (kpi.equals(PaasStatisticsConstant.server_level_work_manager_wmStuck_active)) {
				kpiIds.put(PaasStatisticsConstant.server_level_work_manager_wmStuck_active,
						new PaasKpiMonitorObj(
								PaasStatisticsConstant.server_level_work_manager_wmStuck_active,
								"工作管理器粘滞线程数", ChartType.Line));
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
	 * @Title: queryTomcatBusinessCount_All
	 * @Description:查询业务总个数,正常个数,异常个数(业务健康度不是100的都是异常)
	 * @param
	 * @return PaasEntityObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-16 下午2:49:40
	 */
	private PaasEntityObj queryTomcatBusinessCount_All(String id, String server_type,
			String node_type) {
		if (tomcatEntityObj == null) {
			tomcatEntityObj = new PaasEntityObj();
		}
		List busi_normalList = new ArrayList();// 正常业务集合
		List<PaasEntityObj> healthyLimitList = new ArrayList<PaasEntityObj>();
		PaasTreeObj paasTypeObj = new PaasTreeObj();
		if (PaasConstant.MAAS.equals(node_type)) {
			paasTypeObj.setParent_id(id);
		} else if (PaasConstant.MAAS_TYPE.equals(node_type)) {
			paasTypeObj.setId(id);
		}
		paasTypeObj.setServer_type(server_type);
		List<PaasTreeObj> paasList = paasTreeService.queryTreeNodeByObj(paasTypeObj);
		if (paasList != null && paasList.size() > 0) {
			paasTypeObj = paasList.get(0);
			tomcatEntityObj.setEntity_name(paasTypeObj.getName());
			PaasTreeObj paastrObj = new PaasTreeObj();
			paastrObj.setParent_id(paasTypeObj.getId());
			List<PaasTreeObj> busiList = paasTreeService.queryTreeNodeByObj(paastrObj);
			if (busiList != null && busiList.size() > 0) {
				tomcatEntityObj.setBusiCount(busiList.size());// 业务总个数
				for (PaasTreeObj paasBusiObj : busiList) {
					PaasTreeObj treeObj = new PaasTreeObj();
					treeObj.setId(paasBusiObj.getId());
					treeObj.setNode_type(paasBusiObj.getNode_type());
					Double limit = paasTabService.queryBusHealthyLimitForInstanceId(treeObj,
							PaasConstant.MAAS_APP);// 查询业务健康度的值
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
				tomcatEntityObj.setHealthyLimitList(healList);
				tomcatEntityObj.setBusi_normalCount(busi_normalList.size());// 正常业务
				tomcatEntityObj.setBusi_unusualCount(busiList.size() - busi_normalList.size());// 异常业务
			}
		}
		return tomcatEntityObj;
	}

	/**
	 * 
	 * @Title: queryTomcatDSAndInstanceCount_All
	 * @Description: 查询数据源,实例的总数,正常数,异常数 (实例健康度不是100,就是异常;实例异常,实例下所有服务也是异常的)
	 * @param
	 * @return PaasEntityObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-16 下午3:17:04
	 */
	private PaasEntityObj queryTomcatDSAndInstanceCount_All() throws Exception {
		if (tomcatEntityObj == null) {
			tomcatEntityObj = new PaasEntityObj();
		}
		/**
		 * 查询实例总个数
		 */
		PaasEntityObj entityTypeObj = new PaasEntityObj();
		entityTypeObj.setEntity_type(PaasConstant.TOMCAT);
		List<PaasEntityObj> instanceList_all = paasEntityService.queryForEntityList(entityTypeObj);// 查询所有实例集合
		if (instanceList_all != null && instanceList_all.size() > 0) {
			tomcatEntityObj.setInstanceCount_all(instanceList_all.size());// 实例总个数
		}
		List<PaasKpiMonitorObj> instanceNormalList_all = new ArrayList<PaasKpiMonitorObj>();// 正常个数
		PaasEntityObj instanceTypeObj = new PaasEntityObj();
		instanceTypeObj.setEntity_type(PaasConstant.TOMCAT);
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
		tomcatEntityObj.setNormalCount_all(instanceNormalList_all.size());// 实例正常总个数
		tomcatEntityObj
				.setUnusualCount_all(instanceList_all.size() - instanceNormalList_all.size());// 实例异常总个数
		return tomcatEntityObj;
	}

	/**
	 * 
	 * @Title: queryTomcatAlarmCount
	 * @Description: 查询告警
	 * @param
	 * @return PaasEntityObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-7-22 下午3:44:26
	 */
	private PaasEntityObj queryTomcatAlarmCount(String id, String node_type) throws Exception {
		if (tomcatEntityObj == null) {
			tomcatEntityObj = new PaasEntityObj();
		}
		List<String> entityId_AllList = new ArrayList<String>();
		List<PaasAlarmObj> alarmList_all = new ArrayList<PaasAlarmObj>();
		List seriousAlarmList = new ArrayList();
		List mainsAlarmList = new ArrayList();
		List minorAlarmList = new ArrayList();
		List othorAlarmList = new ArrayList();
		PaasEntityObj entityTypeObj = new PaasEntityObj();
		entityTypeObj.setEntity_type(PaasConstant.TOMCAT);
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
		tomcatEntityObj.setSeriousAlarmCount_all(seriousAlarmList.size());
		tomcatEntityObj.setMainAlarmCount_all(mainsAlarmList.size());
		tomcatEntityObj.setMinorAlarmCount_all(minorAlarmList.size());
		tomcatEntityObj.setOtherAlarmCount_all(othorAlarmList.size());
		return tomcatEntityObj;
	}

}
