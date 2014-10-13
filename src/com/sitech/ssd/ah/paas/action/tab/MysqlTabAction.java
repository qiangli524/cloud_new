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
import com.sitech.ssd.ah.paas.domain.tab.MysqlStatisticsObj;
import com.sitech.ssd.ah.paas.domain.tree.PaasTreeObj;
import com.sitech.ssd.ah.paas.service.entity.PaasEntityService;
import com.sitech.ssd.ah.paas.service.tab.PaasTabService;
import com.sitech.ssd.ah.paas.service.tree.PaasTreeService;
import com.sitech.ssd.ah.paas.util.MysqlStatisticsConstant;
import com.sitech.ssd.ah.paas.util.PaasConstant;
import com.sitech.ssd.ah.paas.util.PaasStatisticsConstant;
import com.sitech.utils.servlet.PrintWriterOut;

import domain.tab.ChartType;

/**
 * 
 * <p>
 * Title: MysqlTabAction
 * </p>
 * <p>
 * Description: mysql资源树tab页相关操作
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
@Controller("mysqlTabAction")
@Scope("prototype")
public class MysqlTabAction extends BaseAction {

	private static final Logger logger = Logger.getLogger(MysqlTabAction.class);

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

	private MysqlStatisticsObj mysqlObj;// mysql数据库Obj

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
	private List<PaasEntityObj> mysqlList;// 实例集合
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

	public MysqlStatisticsObj getMysqlObj() {
		return mysqlObj;
	}

	public void setMysqlObj(MysqlStatisticsObj mysqlObj) {
		this.mysqlObj = mysqlObj;
	}

	/**
	 * 
	 * @Title: showMysqlEntityStatistics
	 * @Description: mysql数据库展示
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-19 上午11:12:55
	 */
	public String showMysqlEntityStatistics() {
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		PaasEntityObj entityObj = new PaasEntityObj();
		entityObj.setParent_id(id);
		// entityObj.setKpi_name(PaasStatisticsConstant.health_score_total);查询健康度
		entityObj.setPagination(this.getPaginater().initPagination(request));
		resultList = paasTabService.queryInstanceNodeList(entityObj);
		if (resultList != null && resultList.size() > 0) {
			paasEntityObj.setDbCount(resultList.size());// mysql数据库个数
			List normalList = new ArrayList();
			for (Object obj : resultList) {
				PaasEntityObj entObj = new PaasEntityObj();
				entObj = (PaasEntityObj) obj;
				PaasKpiMonitorObj monitorObj = new PaasKpiMonitorObj();
				monitorObj.setEntity_id(entObj.getEntity_id());
				// monitorObj.setKpi_name(PaasStatisticsConstant.health_score_total);
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
		return "mysqlEntityStatistics";
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
			// TODO 需要修改 重新定义健康度指标
			if (PaasConstant.DAAS_BUSI.equals(treeObj.getNode_type())) {
				setObj.setKpi_name(PaasStatisticsConstant.health_score_average);// 健康度平局值
			} else if (PaasConstant.DAAS_DB_ENTITY.equals(treeObj.getNode_type())) {
				setObj.setKpi_name(PaasStatisticsConstant.health_score_total);// 健康度总量
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
	 * @Title: showChartByEntityId
	 * @Description: 通过entity_id展示健康度
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-23 上午10:09:25
	 */
	public String showChartByEntityId() {
		FusionCharts fusionCharts = new FusionCharts();
		MapChartSet setObj = new MapChartSet();
		setObj.setEntity_id(entity_id);
		setObj.setId(id);
		setObj.setNode_name(name);
		setObj.setCycle_time(cycle_time);
		// TODO 需要修改 重新定义健康度指标
		setObj.setKpi_name(PaasStatisticsConstant.health_score_total);
		setObj.setPotCount(30);
		fusionCharts = paasTabService.queryLimitAverageChart(setObj);
		String json = JacksonUtil.toJson(fusionCharts);
		PrintWriterOut.printWirter(response, json);
		return null;
	}

	/**
	 * 
	 * @Title: entityStatistics
	 * @Description: mysql统计信息
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-19 下午7:38:30
	 */
	public String entityStatistics() {
		this.queryInstanceKpiValue(entity_id, timeline, coll_time, cycle_time);
		return "entityStatistics";
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
		paasEntityObj = this.queryDBCountInBusi(id);
		paasEntityObj = this.queryPaasAlarmCount(id, PaasConstant.DAAS_BUSI);// 告警数
		return "showDBBusiness";
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
	private PaasEntityObj queryDBCountInBusi(String id) throws Exception {
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
		List<PaasTreeObj> mysqlList = paasTreeService.acquireListForType(
				PaasConstant.DAAS_DB_ENTITY, PaasConstant.MYSQL, treeObj,
				new ArrayList<PaasTreeObj>());
		if (mysqlList != null && mysqlList.size() > 0) {
			paasEntityObj.setDbCount(mysqlList.size());// 数据库个数
		}
		// TODO 需要修改 重新定义新的健康度指标
		for (PaasTreeObj ptrObj : mysqlList) {
			DaasStatisticsObj mysqlStatisticsObj = new DaasStatisticsObj();
			mysqlStatisticsObj.setEntity_id(ptrObj.getEntity_id());
			mysqlStatisticsObj.setEntity_name(ptrObj.getName());
			resultList.add(mysqlStatisticsObj);
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
		for (PaasTreeObj paasTreeObj : mysqlList) {
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
		if (mysqlList != null && mysqlList.size() > 0) {
			paasEntityObj.setNormalCount(normalList.size());// 实例正常个数
			paasEntityObj.setUnusualCount(paasEntityObj.getDbCount() - normalList.size());// 实例异常个数
		}
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
			entityTypeObj.setEntity_type(PaasConstant.MYSQL);
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
					PaasConstant.DAAS_DB_ENTITY, PaasConstant.MYSQL, treeObj,
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
			paasTreeObj.setServer_type(PaasConstant.MYSQL);
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
		paasEntityObj = this.queryDBCountInBusi(id);
		JSONObject json = new JSONObject();
		json.put("paasObj", paasEntityObj);
		PrintWriterOut.printWirter(response, json.toString());
		return null;
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

		paasEntityObj = this.queryBusinessCount_All(id, PaasConstant.MYSQL, node_type);// 业务数量
		paasEntityObj = this.queryDBCount_All();// 数据库

		if (paasEntityObj.getHealthyLimitList() != null
				&& paasEntityObj.getHealthyLimitList().size() > 0) {
			paasEntityObj = this.queryDBCountInBusi(paasEntityObj.getHealthyLimitList().get(0)
					.getId());
		}
		paasEntityObj = this.queryPaasAlarmCount(id, node_type);// 告警总数
		return "showDBType";
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
	 * @Description: 查询数据库的总数,正常数,异常数和数据库的详细信息 (健康度不是100,就是异常)
	 * @param
	 * @return PaasEntityObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-16 下午3:17:04
	 */
	private PaasEntityObj queryDBCount_All() throws Exception {
		if (paasEntityObj == null) {
			paasEntityObj = new PaasEntityObj();
		}
		/**
		 * 查询数据库个数
		 */
		PaasEntityObj entityTypeObj = new PaasEntityObj();
		entityTypeObj.setEntity_type(PaasConstant.MYSQL);
		List<PaasEntityObj> dbList_all = paasEntityService.queryForEntityList(entityTypeObj);// 查询所有数据库集合
		if (dbList_all != null && dbList_all.size() > 0) {
			paasEntityObj.setDbCount_all(dbList_all.size());// 数据库总个数
		}
		List<PaasKpiMonitorObj> mysqlNormalList_all = new ArrayList<PaasKpiMonitorObj>();// 正常个数
		// TODO 需要修改
		for (PaasEntityObj entityObj : dbList_all) {
			PaasKpiMonitorObj monitorObj = new PaasKpiMonitorObj();
			monitorObj.setEntity_id(entityObj.getEntity_id());
			monitorObj.setKpi_name(PaasStatisticsConstant.health_score_total);
			List<PaasKpiMonitorObj> valueList = paasStatisticsDao.queryForLastValue(monitorObj);
			if (valueList != null && valueList.size() > 0) {
				monitorObj = valueList.get(0);
				if ("100.0".equals(monitorObj.getKpi_value_last())) {// 正常
					mysqlNormalList_all.add(monitorObj);// 如果实例是正常的,那么服务也是正常的
				}
			}
		}
		if (dbList_all != null && dbList_all.size() > 0) {
			paasEntityObj.setNormalCount_all(mysqlNormalList_all.size());// 实例正常总个数
			paasEntityObj.setUnusualCount_all(dbList_all.size() - mysqlNormalList_all.size());// 实例异常总个数
		}

		return paasEntityObj;
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
	public String showMysqlListForBusiNode() {
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
		List<PaasKpiMonitorObj> valueList = paasStatisticsDao.queryForLastValueNoHandle(kpiObj);
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
	 * @Title: showMysqlChart
	 * @Description: 展示实例图形
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-24 上午10:49:40
	 */
	public String showMysqlChart() {
		return "mysql_chart";
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
		if ("qps".equals(kpi_type)) {
			title = "QPS";
			unit = "s";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(MysqlStatisticsConstant.QPS, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.QPS, "每秒钟获得的查询数量(s)", ChartType.Line));
			} else if (kpi.equals(MysqlStatisticsConstant.QPS)) {
				kpiIds.put(MysqlStatisticsConstant.QPS, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.QPS, "每秒钟获得的查询数量(s)", ChartType.Line));
			}
		} else if ("tps".equals(kpi_type)) {
			title = "TPS";
			unit = "s";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(MysqlStatisticsConstant.TPS, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.TPS, "每秒传输的事物处理数", ChartType.Line));
			} else if (kpi.equals(MysqlStatisticsConstant.TPS)) {
				kpiIds.put(MysqlStatisticsConstant.TPS, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.TPS, "每秒传输的事物处理数", ChartType.Line));
			}
		} else if ("query_cache".equals(kpi_type)) {
			title = "Query Cache";
			unit = "个数";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(MysqlStatisticsConstant.qcache_hits, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.qcache_hits, "查询缓存被访问的次数", ChartType.Line));
			} else if (kpi.equals(MysqlStatisticsConstant.qcache_hits)) {
				kpiIds.put(MysqlStatisticsConstant.qcache_hits, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.qcache_hits, "查询缓存被访问的次数", ChartType.Line));
			} else if (kpi.equals(MysqlStatisticsConstant.qcache_inserts)) {
				kpiIds.put(MysqlStatisticsConstant.qcache_inserts, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.qcache_inserts, "加入到缓存的查询数量", ChartType.Line));
			}
		} else if ("key_buffer".equals(kpi_type)) {
			title = "key Buffer";
			unit = "个数";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(MysqlStatisticsConstant.key_reads, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.key_reads, "读索引的次数", ChartType.Line));
			} else if (kpi.equals(MysqlStatisticsConstant.key_reads)) {
				kpiIds.put(MysqlStatisticsConstant.key_reads, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.key_reads, "读索引的次数", ChartType.Line));
			} else if (kpi.equals(MysqlStatisticsConstant.key_read_requests)) {
				kpiIds.put(MysqlStatisticsConstant.key_read_requests, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.key_read_requests, "从缓存读键的数据块的请求数", ChartType.Line));
			} else if (kpi.equals(MysqlStatisticsConstant.key_write_requests)) {
				kpiIds.put(MysqlStatisticsConstant.key_write_requests, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.key_write_requests, "将键的数据块写入缓存的请求数",
						ChartType.Line));
			} else if (kpi.equals(MysqlStatisticsConstant.key_writes)) {
				kpiIds.put(MysqlStatisticsConstant.key_writes, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.key_writes, "向硬盘写入将键的数据块的物理写操作的次数", ChartType.Line));
			}
		} else if ("table_cache".equals(kpi_type)) {
			title = "Table Cache";
			unit = "个数";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(MysqlStatisticsConstant.opened_tables, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.opened_tables, "已经打开的表的数量", ChartType.Line));
			} else if (kpi.equals(MysqlStatisticsConstant.opened_tables)) {
				kpiIds.put(MysqlStatisticsConstant.opened_tables, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.opened_tables, "已经打开的表的数量", ChartType.Line));
			} else if (kpi.equals(MysqlStatisticsConstant.open_tables)) {
				kpiIds.put(MysqlStatisticsConstant.open_tables, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.open_tables, "当前打开的表的数目", ChartType.Line));
			} else if (kpi.equals(MysqlStatisticsConstant.open_files)) {
				kpiIds.put(MysqlStatisticsConstant.open_files, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.open_files, "打开的文件的数目", ChartType.Line));
			} else if (kpi.equals(MysqlStatisticsConstant.open_streams)) {
				kpiIds.put(MysqlStatisticsConstant.open_streams, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.open_streams, "打开的流的数量", ChartType.Line));
			} else if (kpi.equals(MysqlStatisticsConstant.open_table_definitions)) {
				kpiIds.put(MysqlStatisticsConstant.open_table_definitions, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.open_table_definitions, "被缓存的FRM文件数量",
						ChartType.Line));
			} else if (kpi.equals(MysqlStatisticsConstant.opened_files)) {
				kpiIds.put(MysqlStatisticsConstant.opened_files, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.opened_files, "已经创建的临时文件的数量", ChartType.Line));
			} else if (kpi.equals(MysqlStatisticsConstant.opened_table_definitions)) {
				kpiIds.put(MysqlStatisticsConstant.opened_table_definitions, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.opened_table_definitions, "被缓存过的FRM文件的数量",
						ChartType.Line));
			}
		} else if ("tmp_table".equals(kpi_type)) {
			title = "Tmp Table";
			unit = "个数";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(MysqlStatisticsConstant.created_tmp_disk_tables, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.created_tmp_disk_tables, "服务器执行语句时在硬盘上自动创建的临时表的数量",
						ChartType.Line));
			} else if (kpi.equals(MysqlStatisticsConstant.created_tmp_disk_tables)) {
				kpiIds.put(MysqlStatisticsConstant.created_tmp_disk_tables, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.created_tmp_disk_tables, "服务器执行语句时在硬盘上自动创建的临时表的数量",
						ChartType.Line));
			} else if (kpi.equals(MysqlStatisticsConstant.created_tmp_tables)) {
				kpiIds.put(MysqlStatisticsConstant.created_tmp_tables, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.created_tmp_tables, "服务器执行语句时自动创建的内存中的临时表的数量",
						ChartType.Line));
			} else if (kpi.equals(MysqlStatisticsConstant.created_tmp_files)) {
				kpiIds.put(MysqlStatisticsConstant.created_tmp_files, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.created_tmp_files,
						"mysqld已经创建的临时文件的数量mysqld已经创建的临时文件的数量", ChartType.Line));
			}
		} else if ("binlog_cache".equals(kpi_type)) {
			title = "Binlog Cache";
			unit = "个数";
			if (kpi == null || kpi.equals("")) {
				kpiIds.put(MysqlStatisticsConstant.binlog_cache_disk_use, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.binlog_cache_disk_use, "全索引的扫描次数", ChartType.Line));
			} else if (kpi.equals(MysqlStatisticsConstant.binlog_cache_disk_use)) {
				kpiIds.put(MysqlStatisticsConstant.binlog_cache_disk_use, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.binlog_cache_disk_use, "全索引的扫描次数", ChartType.Line));
			} else if (kpi.equals(MysqlStatisticsConstant.binlog_cache_use)) {
				kpiIds.put(MysqlStatisticsConstant.binlog_cache_use, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.binlog_cache_use, "日志缓存的事务数量", ChartType.Line));
			} else if (kpi.equals(MysqlStatisticsConstant.binlog_stmt_cache_disk_use)) {
				kpiIds.put(MysqlStatisticsConstant.binlog_stmt_cache_disk_use,
						new PaasKpiMonitorObj(MysqlStatisticsConstant.binlog_stmt_cache_disk_use,
								"非事务语句使用二进制日志缓存", ChartType.Line));
			} else if (kpi.equals(MysqlStatisticsConstant.binlog_stmt_cache_use)) {
				kpiIds.put(MysqlStatisticsConstant.binlog_stmt_cache_use, new PaasKpiMonitorObj(
						MysqlStatisticsConstant.binlog_stmt_cache_use, "临时文件缓存的非事务数量",
						ChartType.Line));
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
	 * @Title: queryInstanceKpiValue
	 * @Description: 查询 实例所有kpi的值
	 * @param
	 * @return DaasStatisticsObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-19 下午4:26:45
	 */
	public MysqlStatisticsObj queryInstanceKpiValue(String entity_id, String timeline,
			String coll_time, String cycle_time) {
		if (mysqlObj == null) {
			mysqlObj = new MysqlStatisticsObj();
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
		mysqlObj.setEntity_name(entityObj.getEntity_name());
		// TODO KPI
		// 基本信息
		mysqlObj.setVersion(kpiMap.get(MysqlStatisticsConstant.version) == null ? "-" : kpiMap.get(
				MysqlStatisticsConstant.version).getKpi_value_last());// 数据库版本
		mysqlObj.setStatus(kpiMap.get(MysqlStatisticsConstant.status) == null ? "-" : kpiMap.get(
				MysqlStatisticsConstant.status).getKpi_value_last());// 数据库状态
		mysqlObj.setSlave_running(kpiMap.get(MysqlStatisticsConstant.slave_running) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.slave_running).getKpi_value_last());// 健康状态
		mysqlObj.setFull_process(kpiMap.get(MysqlStatisticsConstant.full_process) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.full_process).getKpi_value_last());// 连接数
		mysqlObj.setDb_total(kpiMap.get(MysqlStatisticsConstant.db_total) == null ? "-" : kpiMap
				.get(MysqlStatisticsConstant.db_total).getKpi_value_last());// 全库数据量
		mysqlObj.setThreads_connected(kpiMap.get(MysqlStatisticsConstant.threads_connected) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.threads_connected).getKpi_value_last());// 当前客户端已连接的数量
		mysqlObj.setInnodb_buffer_pool_pages_total(kpiMap
				.get(MysqlStatisticsConstant.innodb_buffer_pool_pages_total) == null ? "-" : kpiMap
				.get(MysqlStatisticsConstant.innodb_buffer_pool_pages_total).getKpi_value_last());// 缓冲池总大小
		mysqlObj.setInnodb_page_size(kpiMap.get(MysqlStatisticsConstant.innodb_page_size) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.innodb_page_size).getKpi_value_last());// 编译的InnoDB页大小
		mysqlObj.setInnodb_pages_created(kpiMap.get(MysqlStatisticsConstant.innodb_pages_created) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.innodb_pages_created).getKpi_value_last());// 创建的页数
		mysqlObj.setAborted_clients(kpiMap.get(MysqlStatisticsConstant.aborted_clients) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.aborted_clients).getKpi_value_last());// 客户端被异常中断的数值
		mysqlObj.setSelect_scan(kpiMap.get(MysqlStatisticsConstant.select_scan) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.select_scan).getKpi_value_last());// 执行全表搜索查询的数量
		mysqlObj.setThreads_created(kpiMap.get(MysqlStatisticsConstant.threads_created) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.threads_created).getKpi_value_last());// 创建用来处理连接的线程数

		// 重要指标
		mysqlObj.setUptime(kpiMap.get(MysqlStatisticsConstant.uptime) == null ? "-" : kpiMap.get(
				MysqlStatisticsConstant.uptime).getKpi_value_last());// 运行时间
		mysqlObj.setHandler_read_first(kpiMap.get(MysqlStatisticsConstant.handler_read_first) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.handler_read_first).getKpi_value_last());// 读表索引的第一行
		mysqlObj.setInnodb_buffer_pool_wait_free(kpiMap
				.get(MysqlStatisticsConstant.innodb_buffer_pool_wait_free) == null ? "-" : kpiMap
				.get(MysqlStatisticsConstant.innodb_buffer_pool_wait_free).getKpi_value_last());// 等待实例数
		mysqlObj.setMax_used_connections(kpiMap.get(MysqlStatisticsConstant.max_used_connections) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.max_used_connections).getKpi_value_last());// 最大连接数
		mysqlObj.setSelect_full_join(kpiMap.get(MysqlStatisticsConstant.select_full_join) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.select_full_join).getKpi_value_last());// 全连接的查询数目
		mysqlObj.setSlow_queries(kpiMap.get(MysqlStatisticsConstant.slow_queries) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.slow_queries).getKpi_value_last());// 慢查询的数目
		mysqlObj.setThreads_running(kpiMap.get(MysqlStatisticsConstant.threads_running) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.threads_running).getKpi_value_last());// 持续查询的语句

		// QPS(每秒查询处理量 MyISAM 引擎)
		mysqlObj.setQPS(kpiMap.get(MysqlStatisticsConstant.QPS) == null ? "-" : kpiMap.get(
				MysqlStatisticsConstant.QPS).getKpi_value_last());// 每秒钟获得的查询数量

		// TPS（每秒传输的事物处理个数）
		mysqlObj.setTPS(kpiMap.get(MysqlStatisticsConstant.TPS) == null ? "-" : kpiMap.get(
				MysqlStatisticsConstant.TPS).getKpi_value_last());// 每秒传输的事物处理数

		// Query Cache命中率
		mysqlObj.setQuery_cache_hits(kpiMap.get(MysqlStatisticsConstant.query_cache_hits) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.query_cache_hits).getKpi_value_last());// QueryCache命中率
		mysqlObj.setQcache_hits(kpiMap.get(MysqlStatisticsConstant.qcache_hits) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.qcache_hits).getKpi_value_last());// 查询缓存被访问的次数
		mysqlObj.setQcache_inserts(kpiMap.get(MysqlStatisticsConstant.qcache_inserts) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.qcache_inserts).getKpi_value_last());// 加入到缓存的查询数量

		// key Buffer 命中率
		mysqlObj.setKey_buffer_read_unhits(kpiMap
				.get(MysqlStatisticsConstant.key_buffer_read_unhits) == null ? "-" : kpiMap.get(
				MysqlStatisticsConstant.key_buffer_read_unhits).getKpi_value_last());// keyBuffer未命中率
		mysqlObj.setKey_reads(kpiMap.get(MysqlStatisticsConstant.key_reads) == null ? "-" : kpiMap
				.get(MysqlStatisticsConstant.key_reads).getKpi_value_last());// 读索引的次数
		mysqlObj.setKey_read_requests(kpiMap.get(MysqlStatisticsConstant.key_read_requests) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.key_read_requests).getKpi_value_last());// 从缓存读键的数据块的请求数
		mysqlObj.setKey_write_requests(kpiMap.get(MysqlStatisticsConstant.key_write_requests) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.key_write_requests).getKpi_value_last());// 将键的数据块写入缓存的请求数
		mysqlObj.setKey_writes(kpiMap.get(MysqlStatisticsConstant.key_writes) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.key_writes).getKpi_value_last());// 向硬盘写入将键的数据块的物理写操作的次数

		// Table Cache状态量
		mysqlObj.setOpened_tables(kpiMap.get(MysqlStatisticsConstant.opened_tables) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.opened_tables).getKpi_value_last());// 已经打开的表的数量
		mysqlObj.setOpen_tables(kpiMap.get(MysqlStatisticsConstant.open_tables) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.open_tables).getKpi_value_last());// 当前打开的表的数目
		mysqlObj.setOpen_files(kpiMap.get(MysqlStatisticsConstant.open_files) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.open_files).getKpi_value_last());// 打开的文件的数目
		mysqlObj.setOpen_streams(kpiMap.get(MysqlStatisticsConstant.open_streams) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.open_streams).getKpi_value_last());// 打开的流的数量
		mysqlObj.setOpen_table_definitions(kpiMap
				.get(MysqlStatisticsConstant.open_table_definitions) == null ? "-" : kpiMap.get(
				MysqlStatisticsConstant.open_table_definitions).getKpi_value_last());// 被缓存的FRM文件数量
		mysqlObj.setOpened_files(kpiMap.get(MysqlStatisticsConstant.opened_files) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.opened_files).getKpi_value_last());// 已经创建的临时文件的数量
		mysqlObj.setOpened_table_definitions(kpiMap
				.get(MysqlStatisticsConstant.opened_table_definitions) == null ? "-" : kpiMap.get(
				MysqlStatisticsConstant.opened_table_definitions).getKpi_value_last());// 被缓存过的FRM文件的数量

		// 锁定状态
		mysqlObj.setInnodb_row_lock_current_waits(kpiMap
				.get(MysqlStatisticsConstant.innodb_row_lock_current_waits) == null ? "-" : kpiMap
				.get(MysqlStatisticsConstant.innodb_row_lock_current_waits).getKpi_value_last());// 当前等待的待锁定的行数
		mysqlObj.setInnodb_row_lock_time(kpiMap.get(MysqlStatisticsConstant.innodb_row_lock_time) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.innodb_row_lock_time).getKpi_value_last());// 行锁定花费的总时间，单位毫秒
		mysqlObj.setInnodb_row_lock_time_avg(kpiMap
				.get(MysqlStatisticsConstant.innodb_row_lock_time_avg) == null ? "-" : kpiMap.get(
				MysqlStatisticsConstant.innodb_row_lock_time_avg).getKpi_value_last());// 行锁定的平均时间，单位毫秒
		mysqlObj.setInnodb_row_lock_time_max(kpiMap
				.get(MysqlStatisticsConstant.innodb_row_lock_time_max) == null ? "-" : kpiMap.get(
				MysqlStatisticsConstant.innodb_row_lock_time_max).getKpi_value_last());// 行锁定的最长时间，单位毫秒
		mysqlObj.setInnodb_row_lock_waits(kpiMap.get(MysqlStatisticsConstant.innodb_row_lock_waits) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.innodb_row_lock_waits).getKpi_value_last());// 一行锁定必须等待的时间数

		// Tmp Table 状况(临时表状况)
		mysqlObj.setCreated_tmp_disk_tables(kpiMap
				.get(MysqlStatisticsConstant.created_tmp_disk_tables) == null ? "-" : kpiMap.get(
				MysqlStatisticsConstant.created_tmp_disk_tables).getKpi_value_last());// 服务器执行语句时在硬盘上自动创建的临时表的数量
		mysqlObj.setCreated_tmp_tables(kpiMap.get(MysqlStatisticsConstant.created_tmp_tables) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.created_tmp_tables).getKpi_value_last());// 服务器执行语句时自动创建的内存中的临时表的数量
		mysqlObj.setCreated_tmp_files(kpiMap.get(MysqlStatisticsConstant.created_tmp_files) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.created_tmp_files).getKpi_value_last());// mysqld已经创建的临时文件的数量

		// Binlog Cache 使用状况
		mysqlObj.setBinlog_cache_disk_use(kpiMap.get(MysqlStatisticsConstant.binlog_cache_disk_use) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.binlog_cache_disk_use).getKpi_value_last());// 全索引的扫描次数
		mysqlObj.setBinlog_cache_use(kpiMap.get(MysqlStatisticsConstant.binlog_cache_use) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.binlog_cache_use).getKpi_value_last());// 日志缓存的事务数量
		mysqlObj.setBinlog_stmt_cache_disk_use(kpiMap
				.get(MysqlStatisticsConstant.binlog_stmt_cache_disk_use) == null ? "-" : kpiMap
				.get(MysqlStatisticsConstant.binlog_stmt_cache_disk_use).getKpi_value_last());// 非事务语句使用二进制日志缓存
		mysqlObj.setBinlog_stmt_cache_use(kpiMap.get(MysqlStatisticsConstant.binlog_stmt_cache_use) == null ? "-"
				: kpiMap.get(MysqlStatisticsConstant.binlog_stmt_cache_use).getKpi_value_last());// 临时文件缓存的非事务数量

		// TODO KPI健康度 还没有

		return mysqlObj;
	}
}
