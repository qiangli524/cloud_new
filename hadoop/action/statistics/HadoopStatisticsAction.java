package action.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import service.cluster.HadoopClusterServer;
import service.datanode.HadoopDataNodeService;
import service.host.HadoopHostInfoService;
import service.metries.HadoopMetriesService;
import service.monitor.HadoopMonitorService;
import service.tree.HadoopTreeService;
import util.HadoopConstant;

import com.sitech.basd.component.domain.process.ProcessObj;
import com.sitech.basd.component.service.process.ProcessService;
import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.utils.xml.XmlProperties;

import domain.cluster.HadoopClusterObj;
import domain.datanode.HadoopDataNodeObj;
import domain.host.HadoopHostInfoObj;
import domain.metries.HadoopMetriesObj;
import domain.monitor.HadoopMonitorObj;
import domain.statistics.StatisticsObj;
import domain.tree.HadoopTreeObj;

/**
 * <p>
 * Title: HadoopStatisticsAction
 * </p>
 * <p>
 * Description: 各节点统计类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2014-1-8 下午2:31:17
 * 
 */
@SuppressWarnings("serial")
@Controller("hadoopStatisticsAction")
@Scope("prototype")
public class HadoopStatisticsAction extends BaseAction {

	@Autowired
	private HadoopTreeService hadoopTreeService;

	@Autowired
	private HadoopMonitorService hadoopMonitorService;

	@Autowired
	private HadoopHostInfoService hadoopHostInfoService;

	@Autowired
	private HadoopDataNodeService hadoopDataNodeService;

	@Autowired
	private HadoopMetriesService hadoopMetriesService;

	@Autowired
	private HadoopClusterServer hadoopClusterServer;

	@Autowired
	private ProcessService processService;

	private Map<String, String> entryMap;

	private Map<String, String> nodeTypeMap;

	private HadoopTreeObj obj;

	private StatisticsObj statisticsObj;

	private List<HadoopHostInfoObj> retHostList;

	private List resultList = new ArrayList();

	private String flag;

	public Map<String, String> getNodeTypeMap() {
		return nodeTypeMap;
	}

	public void setNodeTypeMap(Map<String, String> nodeTypeMap) {
		this.nodeTypeMap = nodeTypeMap;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public HadoopTreeObj getObj() {
		return obj;
	}

	public void setObj(HadoopTreeObj obj) {
		this.obj = obj;
	}

	public StatisticsObj getStatisticsObj() {
		return statisticsObj;
	}

	public void setStatisticsObj(StatisticsObj statisticsObj) {
		this.statisticsObj = statisticsObj;
	}

	public List<HadoopHostInfoObj> getRetHostList() {
		return retHostList;
	}

	public void setRetHostList(List<HadoopHostInfoObj> retHostList) {
		this.retHostList = retHostList;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public Map<String, String> getEntryMap() {
		return entryMap;
	}

	public void setEntryMap(Map<String, String> entryMap) {
		this.entryMap = entryMap;
	}

	/**
	 * @Title: statisticsRootNode
	 * @Description: 统计根节点信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-8 下午2:33:18
	 */
	public String statisticsRootNode() {
		if (obj == null) {
			obj = new HadoopTreeObj();
		}
		if (statisticsObj == null) {
			statisticsObj = new StatisticsObj();
		}
		if (nodeTypeMap == null) {
			nodeTypeMap = new HashMap<String, String>();
		}
		HadoopTreeObj hadObj = new HadoopTreeObj();
		hadObj.setNode_type(HadoopConstant.hadoop_dc);
		List<HadoopTreeObj> list = hadoopTreeService.queryForListByObj(hadObj);
		if (list != null && list.size() > 0) {
			statisticsObj.setClusterCount(list.size());// 集群个数
		}
		HadoopTreeObj hadoopObj = new HadoopTreeObj();
		hadoopObj.setNode_type(HadoopConstant.hostNode);
		List<HadoopHostInfoObj> hostInfoList = hadoopHostInfoService
				.queryAllHostForTree(hadoopObj);
		if (hostInfoList != null && hostInfoList.size() > 0) {
			statisticsObj.setHostCount(hostInfoList.size());// 主机个数
		}
		this.initConfigXml();

		for (HadoopTreeObj treeObj : list) {// 遍历每个数据中心
			StatisticsObj sttObj = new StatisticsObj();
			sttObj.setClusterName(treeObj.getName());
			Map<String, List<HadoopTreeObj>> map = this.initNodeMap(treeObj,
					HadoopConstant.hostNode);// 获取主机节点集合，按服务类型分组
			nodeTypeMap = hadoopTreeService.queryNodeType(treeObj);// 查询服务节点,判断哪个节点显示
			// 统计单个集群服务节点个数(用于多个集群分开展示)
			try {
				sttObj.setNameNodeCount(map.get(HadoopConstant.nameNode) == null ? 0
						: map.get(HadoopConstant.nameNode).size());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				sttObj.setDatanodeCount(map.get(HadoopConstant.dataNode) == null ? 0
						: map.get(HadoopConstant.dataNode).size());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				sttObj.setJournalnodeCount(map.get(HadoopConstant.journalNode) == null ? 0
						: map.get(HadoopConstant.journalNode).size());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				sttObj.setNodeManagerCount(map.get(HadoopConstant.nodeManager) == null ? 0
						: map.get(HadoopConstant.nodeManager).size());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				sttObj.setReduceManagerCount(map
						.get(HadoopConstant.reduceManager) == null ? 0 : map
						.get(HadoopConstant.reduceManager).size());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				sttObj.setHmasterCount(map.get(HadoopConstant.hmaster) == null ? 0
						: map.get(HadoopConstant.hmaster).size());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				sttObj.setRegionServerCount(map
						.get(HadoopConstant.regionServer) == null ? 0 : map
						.get(HadoopConstant.regionServer).size());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				sttObj.setHbaseThirftServerCount(map
						.get(HadoopConstant.hbase_thirftServer) == null ? 0
						: map.get(HadoopConstant.hbase_thirftServer).size());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				sttObj.setHiveThirftServerCount(map
						.get(HadoopConstant.hive_thirftServer) == null ? 0
						: map.get(HadoopConstant.hive_thirftServer).size());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				sttObj.setZookeeperServerCount(map.get(HadoopConstant.znode) == null ? 0
						: map.get(HadoopConstant.znode).size());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				sttObj.setImpalaServerCount(map.get(HadoopConstant.impalaxx) == null ? 0
						: map.get(HadoopConstant.impalaxx).size());
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 统计服务实例的状态
			Map<String, Integer> normalMap = new HashMap<String, Integer>();
			Set<String> keySet = map.keySet();
			for (String string : keySet) {
				// 取出每种类型的服务实例集合，获取uuid的集合
				List<HadoopTreeObj> treeList = map.get(string);
				List<String> uuidList = new ArrayList<String>();
				for (HadoopTreeObj hadoopTreeObj : treeList) {
					uuidList.add(hadoopTreeObj.getId());
				}
				// 关联服务实例和进程的关系表与进程表，查询进程个数、正常个数
				ProcessObj pObj = new ProcessObj();
				pObj.setNodeIdList(uuidList);
				List<ProcessObj> processList = processService
						.queryHadoopServiceNodeStatus(pObj);
				int normal = 0;
				if (processList.size() > 0) {
					for (ProcessObj processObj : processList) {
						if (processObj.getAllcount() == 0
								|| processObj.getAllcount() == processObj
										.getNormalcount()) {
							normal++;
						}
					}
					// 没有配进程的，都任务正常
					if (processList.size() != treeList.size()) {
						normal += (treeList.size() - processList.size());
					}
				} else {
					normal += treeList.size();
				}
				normalMap.put(string, normal);
			}
			sttObj.setNameNodeNormalCount(normalMap
					.get(HadoopConstant.nameNode) == null ? 0 : normalMap
					.get(HadoopConstant.nameNode));
			sttObj.setDatanodeNormalCount(normalMap
					.get(HadoopConstant.dataNode) == null ? 0 : normalMap
					.get(HadoopConstant.dataNode));
			sttObj.setJournalnodeNormalCount(normalMap
					.get(HadoopConstant.journalNode) == null ? 0 : normalMap
					.get(HadoopConstant.journalNode));
			sttObj.setRegionServerNormalCount(normalMap
					.get(HadoopConstant.regionServer) == null ? 0 : normalMap
					.get(HadoopConstant.regionServer));
			sttObj.setNodeManagerNormalCount(normalMap
					.get(HadoopConstant.nodeManager) == null ? 0 : normalMap
					.get(HadoopConstant.nodeManager));
			sttObj.setReduceManagerNormalCount(normalMap
					.get(HadoopConstant.reduceManager) == null ? 0 : normalMap
					.get(HadoopConstant.reduceManager));
			sttObj.setHmasterNormalCount(normalMap.get(HadoopConstant.hmaster) == null ? 0
					: normalMap.get(HadoopConstant.hmaster));
			sttObj.setHiveThirftServerNormalCount(normalMap
					.get(HadoopConstant.hive_thirftServer) == null ? 0
					: normalMap.get(HadoopConstant.hive_thirftServer));
			sttObj.setHbaseThirftServerNormalCount(normalMap
					.get(HadoopConstant.hbase_thirftServer) == null ? 0
					: normalMap.get(HadoopConstant.hbase_thirftServer));
			sttObj.setHiveThirftServerNormalCount(normalMap
					.get(HadoopConstant.hive_thirftServer) == null ? 0
					: normalMap.get(HadoopConstant.hive_thirftServer));
			sttObj.setZookeeperServerNormalCount(normalMap
					.get(HadoopConstant.znode) == null ? 0 : normalMap
					.get(HadoopConstant.znode));
			sttObj.setImpalaServerNormalCount(normalMap
					.get(HadoopConstant.impalaxx) == null ? 0 : normalMap
					.get(HadoopConstant.impalaxx));
			// 判断展示节点
			sttObj.setHadoopType(nodeTypeMap.get("hadoop"));
			sttObj.setHbaseType(nodeTypeMap.get("hbase"));
			sttObj.setHiveType(nodeTypeMap.get("hive"));
			sttObj.setZookeeperType(nodeTypeMap.get("zookeeper"));
			sttObj.setImpalaType(nodeTypeMap.get("impala"));
			sttObj.setStormType(nodeTypeMap.get("storm"));

			resultList.add(sttObj);
		}

		return "statisticsRootNode";
	}

	/**
	 * @Title: statisticsCenterNode
	 * @Description: 统计数据中心信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-8 下午2:34:20
	 */
	public String statisticsCenterNode() {
		if (obj == null) {
			obj = new HadoopTreeObj();
		}
		if (statisticsObj == null) {
			statisticsObj = new StatisticsObj();
		}
		if (nodeTypeMap == null) {
			nodeTypeMap = new HashMap<String, String>();
		}
		nodeTypeMap = hadoopTreeService.queryNodeType(obj);// 查询服务节点,判断哪个节点显示
		List<HadoopTreeObj> list = hadoopTreeService.queryForListByObj(obj);

		this.initConfigXml();

		HadoopTreeObj treeObj = list.get(0);
		Map<String, List<HadoopTreeObj>> nodeMap = this.initNodeMap(treeObj,
				HadoopConstant.hostNode);// 获取主机节点集合，按服务类型分组
		this.countServiceNode(nodeMap);// 统计各种类型服务实例的个数
		this.countServiceNodeStatus(nodeMap);
		List<HadoopTreeObj> hdfsList = new ArrayList<HadoopTreeObj>();
		List<HadoopTreeObj> yarnList = new ArrayList<HadoopTreeObj>();
		try {
			hdfsList = acquireChildNode(HadoopConstant.hdfs, null, treeObj,
					new ArrayList<HadoopTreeObj>());
			yarnList = acquireChildNode(HadoopConstant.mapReduce, null,
					treeObj, new ArrayList<HadoopTreeObj>());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (hdfsList != null && hdfsList.size() > 0) {
			// 文件系统
			this.setFileSystemValues(nodeMap, hdfsList);
			this.setFileValues(hdfsList);
		}
		if (yarnList != null && yarnList.size() > 0) {
			// 统计内存
			this.setMemoryValues(yarnList);
			// 统计mr作业
			this.setJobsValues(yarnList);
		}
		this.setFileSystemUsage();
		// 统计hbase
		this.setHbaseValues(treeObj);

		return "statisticsClusterNode";
	}

	/**
	 * @Title: statisticsHadoopNode
	 * @Description: 统计hadoop集群信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-10 下午4:42:50
	 */
	public String statisticsHadoopNode() {
		if (obj == null) {
			obj = new HadoopTreeObj();
		}
		if (statisticsObj == null) {
			statisticsObj = new StatisticsObj();
		}
		if (nodeTypeMap == null) {
			nodeTypeMap = new HashMap<String, String>();
		}
		nodeTypeMap = hadoopTreeService.queryNodeType(obj);// 查询服务节点,判断哪个节点显示
		this.initConfigXml();
		List<HadoopTreeObj> list = hadoopTreeService.queryForListByObj(obj);
		HadoopTreeObj treeObj = list.get(0);

		Map<String, List<HadoopTreeObj>> nodeMap = this.initNodeMap(treeObj,
				HadoopConstant.hostNode);// 获取主机节点集合，按服务类型分组
		this.countServiceNode(nodeMap);// 统计各种类型服务实例的个数
		this.countServiceNodeStatus(nodeMap);
		List<HadoopTreeObj> hdfsList = new ArrayList<HadoopTreeObj>();
		List<HadoopTreeObj> yarnList = new ArrayList<HadoopTreeObj>();
		try {
			hdfsList = acquireChildNode(HadoopConstant.hdfs, null, treeObj,
					new ArrayList<HadoopTreeObj>());
			yarnList = acquireChildNode(HadoopConstant.mapReduce, null,
					treeObj, new ArrayList<HadoopTreeObj>());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (hdfsList != null && hdfsList.size() > 0) {
			// 文件系统
			this.setFileSystemValues(nodeMap, hdfsList);
			this.setFileValues(hdfsList);
		}
		if (yarnList != null && yarnList.size() > 0) {
			// 统计内存
			this.setMemoryValues(yarnList);
			// 统计mr作业
			this.setJobsValues(yarnList);
			this.setReduceValues(yarnList);
		}
		this.setFileSystemUsage();
		return "statisticsHadoopNode";
	}

	/**
	 * @Title: statisticsHbaseNode
	 * @Description: 统计hbase节点
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-10 下午7:25:01
	 */
	public String statisticsHbaseNode() {
		if (obj == null) {
			obj = new HadoopTreeObj();
		}
		if (statisticsObj == null) {
			statisticsObj = new StatisticsObj();
		}
		this.initConfigXml();
		List<HadoopTreeObj> list = hadoopTreeService.queryForListByObj(obj);

		HadoopTreeObj treeObj = list.get(0);

		Map<String, List<HadoopTreeObj>> nodeMap = this.initNodeMap(treeObj,
				HadoopConstant.hostNode);// 获取主机节点集合，按服务类型分组
		this.countServiceNode(nodeMap);// 统计各种类型服务实例的个数
		this.countServiceNodeStatus(nodeMap);
		this.setHbaseValues(treeObj);
		this.setHmasterValues(treeObj);
		this.setRegionServerValues(treeObj);
		return "statisticsHbaseNode";
	}

	/**
	 * @Title: statisticsOtherClusterNode
	 * @Description: hive、impla等无监控指标的节点
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-25 上午11:29:52
	 */
	public String statisticsOtherClusterNode() {
		if (obj == null) {
			obj = new HadoopTreeObj();
		}
		if (statisticsObj == null) {
			statisticsObj = new StatisticsObj();
		}
		if (nodeTypeMap == null) {
			nodeTypeMap = new HashMap<String, String>();
		}
		nodeTypeMap = hadoopTreeService.queryNodeType(obj);// 查询服务节点,判断哪个节点显示
		List<HadoopTreeObj> list = hadoopTreeService.queryForListByObj(obj);
		HadoopTreeObj treeObj = list.get(0);
		Map<String, List<HadoopTreeObj>> nodeMap = this.initNodeMap(treeObj,
				HadoopConstant.hostNode);// 获取主机节点集合，按服务类型分组
		this.countServiceNode(nodeMap);// 统计各种类型服务实例的个数
		this.countServiceNodeStatus(nodeMap);
		String clusterName = this.getClusterName(treeObj);
		// 查询服务节点
		try {
			List<HadoopTreeObj> treeList = this.acquireChildNode(
					HadoopConstant.serviceNode, null, treeObj,
					new ArrayList<HadoopTreeObj>());
			for (HadoopTreeObj hadoopTreeObj : treeList) {
				listHosts(hadoopTreeObj, clusterName);
			}
			for (HadoopHostInfoObj hostObj : retHostList) {
				if (hostObj.getAllcount() == null) {
					hostObj.setAllcount(new Integer(0));
				}
				if (hostObj.getNormalcount() == null) {
					hostObj.setNormalcount(new Integer(0));
				}
				if (hostObj.getAllcount().intValue() == hostObj
						.getNormalcount().intValue()) {
					hostObj.setState(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "statisticsServiceNode";
	}

	/**
	 * @Title: setRegionServerValues
	 * @Description: regionServer
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-16 下午4:02:24
	 */
	private void setRegionServerValues(HadoopTreeObj treeObj) {
		String clusterId = this.getClusterId(treeObj);
		statisticsObj.setRegionSplitFailureCount(this
				.dealValue(
						this.statisticsFromCollTable(
								"hbase.regionserver.regionSplitFailureCount",
								clusterId)).intValue());
		statisticsObj.setRegionSplitSuccessCount(this
				.dealValue(
						this.statisticsFromCollTable(
								"hbase.regionserver.regionSplitSuccessCount",
								clusterId)).intValue());
	}

	/**
	 * @Title: setHmasterValues
	 * @Description: hmaster
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-16 下午3:58:08
	 */
	private void setHmasterValues(HadoopTreeObj treeObj) {
		String clusterId = this.getClusterId(treeObj);
		statisticsObj.setSplitSize_avg_time(this.dealValue(
				this.statisticsFromCollTable("hbase.master.splitSize_avg_time",
						clusterId)).doubleValue());
		statisticsObj.setSplitSize_num_ops(this.dealValue(
				this.statisticsFromCollTable("hbase.master.splitSize_avg_time",
						clusterId)).doubleValue());
		statisticsObj.setSplitTime_avg_time(this.dealValue(
				this.statisticsFromCollTable("hbase.master.splitSize_avg_time",
						clusterId)).doubleValue());
		statisticsObj.setSplitTime_num_ops(this.dealValue(
				this.statisticsFromCollTable("hbase.master.splitSize_avg_time",
						clusterId)).doubleValue());
	}

	/**
	 * @Title: statisticsHdfsNode
	 * @Description: 统计hdfs节点信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-10 下午7:02:05
	 */
	public String statisticsHdfsNode() {
		if (obj == null) {
			obj = new HadoopTreeObj();
		}
		if (statisticsObj == null) {
			statisticsObj = new StatisticsObj();
		}
		this.initConfigXml();
		List<HadoopTreeObj> list = hadoopTreeService.queryForListByObj(obj);
		HadoopTreeObj treeObj = list.get(0);

		Map<String, List<HadoopTreeObj>> nodeMap = this.initNodeMap(treeObj,
				HadoopConstant.hostNode);// 获取主机节点集合，按服务类型分组
		this.countServiceNode(nodeMap);// 统计各种类型服务实例的个数
		this.countServiceNodeStatus(nodeMap);
		this.setFileSystemValues(nodeMap, list);
		this.setFileSystemUsage();
		this.setFileValues(list);

		this.setNameNodeValues(treeObj);//

		this.setDataNodeValues(treeObj);//

		return "statisticsHdfsNode";
	}

	/**
	 * @Title: statisticsMapReduceNode
	 * @Description: 统计mapReduce节点信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-10 下午5:03:30
	 */
	public String statisticsMapReduceNode() {
		if (obj == null) {
			obj = new HadoopTreeObj();
		}
		if (statisticsObj == null) {
			statisticsObj = new StatisticsObj();
		}
		this.initConfigXml();
		List<HadoopTreeObj> list = hadoopTreeService.queryForListByObj(obj);
		HadoopTreeObj treeObj = list.get(0);

		Map<String, List<HadoopTreeObj>> nodeMap = this.initNodeMap(treeObj,
				HadoopConstant.hostNode);// 获取主机节点集合，按服务类型分组
		this.countServiceNode(nodeMap);// 统计各种类型服务实例的个数
		this.countServiceNodeStatus(nodeMap);
		this.setJobsValues(list);
		this.setMapsValues(list);
		this.setReduceValues(list);
		this.setMemoryValues(list);
		this.setNodeManagerValues(treeObj);
		return "statisticsMapReduceNode";
	}

	private void setReduceValues(List<HadoopTreeObj> list) {
		String clusterId = this.getClusterId(list.get(0));
		statisticsObj.setReducesLaunchedCount(this.dealValue(
				this.statisticsFromCollTable(
						"mapred.MRAppMetrics.reducesLaunched", clusterId))
				.intValue());
		statisticsObj.setReducesCompletedCount(this.dealValue(
				this.statisticsFromCollTable(
						"mapred.MRAppMetrics.reducesCompleted", clusterId))
				.intValue());
		statisticsObj.setReducesFailedCount(this.dealValue(
				this.statisticsFromCollTable(
						"mapred.MRAppMetrics.reducesFailed", clusterId))
				.intValue());
		statisticsObj.setReducesKilledCount(this.dealValue(
				this.statisticsFromCollTable(
						"mapred.MRAppMetrics.reducesKilled", clusterId))
				.intValue());
		statisticsObj.setReducesRunningCount(this.dealValue(
				this.statisticsFromCollTable(
						"mapred.MRAppMetrics.reducesRunning", clusterId))
				.intValue());
		statisticsObj.setReducesWaitingCount(this.dealValue(
				this.statisticsFromCollTable(
						"mapred.MRAppMetrics.reducesWaiting", clusterId))
				.intValue());
	}

	/**
	 * @Title: setMapsValues
	 * @Description:
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-16 上午10:29:13
	 */
	private void setMapsValues(List<HadoopTreeObj> list) {
		String clusterId = this.getClusterId(list.get(0));
		statisticsObj.setMapsLaunchedCount(this.dealValue(
				this.statisticsFromCollTable(
						"mapred.MRAppMetrics.mapsLaunched", clusterId))
				.intValue());
		statisticsObj.setMapsCompletedCount(this.dealValue(
				this.statisticsFromCollTable(
						"mapred.MRAppMetrics.mapsCompleted", clusterId))
				.intValue());
		statisticsObj.setMapsFailedCount(this.dealValue(
				this.statisticsFromCollTable("apred.MRAppMetrics.mapsFailed",
						clusterId)).intValue());
		statisticsObj.setMapsKilledCount(this.dealValue(
				this.statisticsFromCollTable("mapred.MRAppMetrics.mapsKilled",
						clusterId)).intValue());
		statisticsObj.setMapsRunningCount(this.dealValue(
				this.statisticsFromCollTable("mapred.MRAppMetrics.mapsRunning",
						clusterId)).intValue());
		statisticsObj.setMapsWaitingCount(this.dealValue(
				this.statisticsFromCollTable("mapred.MRAppMetrics.mapsWaiting",
						clusterId)).intValue());
	}

	/**
	 * @Title: statisticsServiceNode
	 * @Description: 统计服务节点信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-14 下午5:53:49
	 */
	public String statisticsServiceNode() {
		if (obj == null) {
			obj = new HadoopTreeObj();
		}
		if (statisticsObj == null) {
			statisticsObj = new StatisticsObj();
		}
		HadoopTreeObj hadoopTreeObj = new HadoopTreeObj();
		hadoopTreeObj.setId(obj.getId());
		List<HadoopTreeObj> treeList = hadoopTreeService
				.queryForListByObj(hadoopTreeObj);
		hadoopTreeObj = treeList.get(0);
		// 判断服务类型
		if (HadoopConstant.dataNode.equals(obj.getService_type())) {// dataNode
			// if (treeList.size() > 0) {
			// HadoopTreeObj treeObj = new HadoopTreeObj();
			// treeObj.setId(hadoopTreeObj.getParent_id());
			// treeList = hadoopTreeService.queryForListByObj(treeObj);
			// }
			// Map<String, List<HadoopTreeObj>> nodeMap =
			// this.initNodeMap(hadoopTreeObj,HadoopConstant.hostNode);
			// this.setFileSystemValues(nodeMap, treeList);
			this.setDataNodeValues(hadoopTreeObj);
		} else if (HadoopConstant.nodeManager.equals(obj.getService_type())) {// nodemanager
			this.setNodeManagerValues(hadoopTreeObj);
		} else if (HadoopConstant.nameNode.equals(obj.getService_type())) {
			this.setNameNodeValues(hadoopTreeObj);
			HadoopTreeObj treeObj = new HadoopTreeObj();
			treeObj.setParent_id(obj.getId());
			resultList = hadoopTreeService.queryForHostNodeList(treeObj);
		} else if (HadoopConstant.hmaster.equals(obj.getService_type())) {
			this.setHmasterValues(hadoopTreeObj);
		} else if (HadoopConstant.regionServer.equals(obj.getService_type())) {
			// this.setRegionServerValues(hadoopTreeObj);
			this.setHbaseValues(hadoopTreeObj);
		}

		String clusterName = this.getClusterName(hadoopTreeObj);
		// 主机列表
		this.listHosts(hadoopTreeObj, clusterName);
		for (HadoopHostInfoObj treeObj : retHostList) {
			if (treeObj.getAllcount() == null) {
				treeObj.setAllcount(new Integer(0));
			}
			if (treeObj.getNormalcount() == null) {
				treeObj.setNormalcount(new Integer(0));
			}
			if (treeObj.getAllcount().intValue() == treeObj.getNormalcount()
					.intValue()) {
				treeObj.setState(1);
			}
		}

		// 如果是regionserver，那么还需要查询每个主机的blockcache命中率
		if (HadoopConstant.regionServer.equals(obj.getService_type())) {
			HadoopMonitorObj hadoopMonitorObj = new HadoopMonitorObj();
			hadoopMonitorObj.setKpi_id("hbase.regionserver.blockCacheHitRatio");
			hadoopMonitorObj.setCluster_name(clusterName);
			for (HadoopHostInfoObj hostObj : retHostList) {
				hadoopMonitorObj.setHost_name(hostObj.getHost_name());
				List<HadoopMonitorObj> mList = hadoopMonitorService
						.queryLatestValueByKpi(hadoopMonitorObj);
				if (mList.size() > 0) {
					hostObj.setKpi_value(mList.get(0).getKpi_value());
				} else {
					hostObj.setKpi_value("0");
				}
			}
		}
		return "statisticsServiceNode";
	}

	/**
	 * @Title: getClusterName
	 * @Description: 获取集群名称，监控表中的cluster_name
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-26 上午10:58:16
	 */
	private String getClusterName(HadoopTreeObj hadoopTreeObj) {
		String clusterId = this.getClusterId(hadoopTreeObj);
		HadoopClusterObj clusterObj = new HadoopClusterObj();
		clusterObj.setId(clusterId);
		List<HadoopClusterObj> cluList = hadoopClusterServer
				.queryClusterList(clusterObj);
		String clusterName = null;
		if (cluList.size() > 0) {
			clusterName = cluList.get(0).getCluster_id();
		}
		return clusterName;
	}

	/**
	 * @Title: listHosts
	 * @Description: 列出主机
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-16 上午10:57:51
	 */
	private void listHosts(HadoopTreeObj hadoopTreeObj, String clusterName) {
		if (retHostList == null) {
			retHostList = new ArrayList<HadoopHostInfoObj>();
		}
		HadoopHostInfoObj hadoopHostInfoObj = new HadoopHostInfoObj();
		if (HadoopConstant.hostNode.equals(hadoopTreeObj.getNode_type())) {
			hadoopHostInfoObj.setId(hadoopTreeObj.getId());
		} else if (HadoopConstant.serviceNode.equals(hadoopTreeObj
				.getNode_type())) {
			hadoopHostInfoObj.setServiceId(hadoopTreeObj.getId());
		}
		hadoopHostInfoObj.setCluster_name(clusterName);
		hadoopHostInfoObj.setKpi_id("swap_total");
		hadoopHostInfoObj.setPagination(this.getPaginater().initPagination(
				request));
		retHostList.addAll(hadoopHostInfoService
				.queryForExampleList(hadoopHostInfoObj));

		// List<HadoopTreeObj> treeList = new ArrayList<HadoopTreeObj>();
		// Set<String> keySet = nodeMap.keySet();
		// for (String string : keySet) {
		// treeList.addAll(nodeMap.get(string));
		// }
		//
		// List<String> list = new ArrayList<String>();
		// for (HadoopTreeObj treeObj : treeList) {
		// list.add(treeObj.getId());
		// }
		//
		// ProcessObj processObj = new ProcessObj();
		// processObj.setNodeIdList(list);
		// List<ProcessObj> processList =
		// processService.queryHadoopServiceNodeStatus(processObj);
		// w:for (ProcessObj pObj : processList) {
		// if (pObj.getAllcount() != pObj.getNormalcount()) {
		// for (HadoopHostInfoObj hostObj : retHostList) {
		// if (pObj.getENTITY_ID() != null ) {
		//
		// }
		// }
		// }
		// }
	}

	/**
	 * @Title: setNodeManagerValues
	 * @Description: nodemanager
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-16 上午10:49:58
	 */
	private void setNodeManagerValues(HadoopTreeObj hadoopTreeObj) {
		String clusterId = this.getClusterId(hadoopTreeObj);
		statisticsObj.setNumActiveNMs(this.dealValue(
				this.statisticsFromCollTable(
						"yarn.ClusterMetrics.numActiveNMs", clusterId))
				.intValue());
		statisticsObj.setNumDecommissionedNMs(this.dealValue(
				this.statisticsFromCollTable(
						"yarn.ClusterMetrics.numDecommissionedNMs", clusterId))
				.intValue());
		statisticsObj.setNumLostNMs(this.dealValue(
				this.statisticsFromCollTable("yarn.ClusterMetrics.numLostNMs",
						clusterId)).intValue());
		statisticsObj.setNumUnhealthyNMs(this.dealValue(
				this.statisticsFromCollTable(
						"yarn.ClusterMetrics.numUnhealthyNMs", clusterId))
				.intValue());
		statisticsObj.setNumRebootedNMs(this.dealValue(
				this.statisticsFromCollTable(
						"yarn.ClusterMetrics.numRebootedNMs", clusterId))
				.intValue());
	}

	/**
	 * @Title: statisticsExampleNode
	 * @Description: 统计服务实例信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-14 下午5:55:33
	 */
	public String statisticsExampleNode() {
		if (obj == null) {
			obj = new HadoopTreeObj();
		}
		List<HadoopTreeObj> treeList = hadoopTreeService.queryForListByObj(obj);
		if (treeList.size() > 0) {
			HadoopTreeObj hadoopTreeObj = treeList.get(0);
			String clusterName = this.getClusterName(hadoopTreeObj);
			listHosts(hadoopTreeObj, clusterName);
		}
		for (HadoopHostInfoObj treeObj : retHostList) {
			if (treeObj.getAllcount() == null) {
				treeObj.setAllcount(new Integer(0));
			}
			if (treeObj.getNormalcount() == null) {
				treeObj.setNormalcount(new Integer(0));
			}
			if (treeObj.getAllcount().intValue() == treeObj.getNormalcount()
					.intValue()) {
				treeObj.setState(1);
			}
		}
		return "statisticsExampleNode";
	}

	/**
	 * @Title: countClusterNode
	 * @Description: 统计集群级别的节点
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-9 下午2:37:14
	 */
	/*
	 * private void countClusterNode(List<HadoopTreeObj> list) { HadoopTreeObj
	 * hadoopTreeObj = new HadoopTreeObj();
	 * 
	 * //根据父节点id和节点类型查询 List<String> parentIdList = new ArrayList<String>(); for
	 * (HadoopTreeObj treeObj : list) { parentIdList.add(treeObj.getId()); }
	 * hadoopTreeObj.setParentIdList(parentIdList);
	 * 
	 * //hadoop集群 hadoopTreeObj.setNode_type(HadoopConstant.hadoop_cluster);
	 * statisticsObj
	 * .setHadoopClusterCount(this.countNodeByParentIdList(hadoopTreeObj));
	 * 
	 * //hbase集群 hadoopTreeObj.setNode_type(HadoopConstant.hbase_cluster);
	 * statisticsObj
	 * .setHbaseClusterCount(this.countNodeByParentIdList(hadoopTreeObj));
	 * 
	 * //hive集群 hadoopTreeObj.setNode_type(HadoopConstant.hive);
	 * statisticsObj.setHiveClusterCount
	 * (this.countNodeByParentIdList(hadoopTreeObj));
	 * 
	 * //zookeeper集群 hadoopTreeObj.setNode_type(HadoopConstant.zookeeper);
	 * statisticsObj
	 * .setZookeeperClusterCount(this.countNodeByParentIdList(hadoopTreeObj));
	 * 
	 * //impala集群 hadoopTreeObj.setNode_type(HadoopConstant.impala);
	 * statisticsObj
	 * .setImpalaClusterCount(this.countNodeByParentIdList(hadoopTreeObj));
	 * 
	 * }
	 */

	/**
	 * @Title: countServiceNode
	 * @Description: 统计服务节点个数（数据中心叫法不妥，其实就是集群）
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @param map
	 * @createtime 2014-1-9 下午7:05:56
	 */
	private void countServiceNode(Map<String, List<HadoopTreeObj>> map) {
		try {
			statisticsObj.setNameNodeCount(statisticsObj.getNameNodeCount()
					+ (map.get(HadoopConstant.nameNode) == null ? 0 : map.get(
							HadoopConstant.nameNode).size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			statisticsObj.setDatanodeCount(statisticsObj.getDatanodeCount()
					+ (map.get(HadoopConstant.dataNode) == null ? 0 : map.get(
							HadoopConstant.dataNode).size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			statisticsObj.setJournalnodeCount(statisticsObj
					.getJournalnodeCount()
					+ (map.get(HadoopConstant.journalNode) == null ? 0 : map
							.get(HadoopConstant.journalNode).size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			statisticsObj.setRegionServerCount(statisticsObj
					.getRegionServerCount()
					+ (map.get(HadoopConstant.regionServer) == null ? 0 : map
							.get(HadoopConstant.regionServer).size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			statisticsObj.setNodeManagerCount(statisticsObj
					.getNodeManagerCount()
					+ (map.get(HadoopConstant.nodeManager) == null ? 0 : map
							.get(HadoopConstant.nodeManager).size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			statisticsObj.setReduceManagerCount(statisticsObj
					.getReduceManagerCount()
					+ (map.get(HadoopConstant.reduceManager) == null ? 0 : map
							.get(HadoopConstant.reduceManager).size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			statisticsObj.setHmasterCount(statisticsObj.getHmasterCount()
					+ (map.get(HadoopConstant.hmaster) == null ? 0 : map.get(
							HadoopConstant.hmaster).size()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			statisticsObj
					.setHbaseThirftServerCount(statisticsObj
							.getHbaseThirftServerCount()
							+ (map.get(HadoopConstant.hbase_thirftServer) == null
									? 0
									: map.get(HadoopConstant.hbase_thirftServer)
											.size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			statisticsObj
					.setHiveThirftServerCount(statisticsObj
							.getHiveThirftServerCount()
							+ (map.get(HadoopConstant.hive_thirftServer) == null
									? 0
									: map.get(HadoopConstant.hive_thirftServer)
											.size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			statisticsObj.setZookeeperServerCount(statisticsObj
					.getZookeeperServerCount()
					+ (map.get(HadoopConstant.znode) == null ? 0 : map.get(
							HadoopConstant.znode).size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			statisticsObj.setImpalaServerCount(statisticsObj
					.getImpalaServerCount()
					+ (map.get(HadoopConstant.impalaxx) == null ? 0 : map.get(
							HadoopConstant.impalaxx).size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: setFileSystemValues
	 * @Description: 文件系统
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-13 上午10:04:11
	 */
	private void setFileSystemValues(Map<String, List<HadoopTreeObj>> nodeMap,
			List<HadoopTreeObj> hdfsList) {
		List<String> hdfsUuidList = new ArrayList<String>();
		for (HadoopTreeObj hadoopTreeObj : hdfsList) {
			hdfsUuidList.add(hadoopTreeObj.getUuid());
		}

		List<String> hostList = new ArrayList<String>();
		List<HadoopTreeObj> datanodeList = nodeMap.get(HadoopConstant.dataNode);
		if(datanodeList!=null && datanodeList.size()>0){
			for (HadoopTreeObj hadoopTreeObj : datanodeList) {
				hostList.add(hadoopTreeObj.getUuid());
			}
		}

		HadoopDataNodeObj hadoopDataNodeObj = new HadoopDataNodeObj();
		hadoopDataNodeObj.setHdfsUuidList(hdfsUuidList);
		hadoopDataNodeObj.setHostUuidList(hostList);
		List<HadoopDataNodeObj> hdnoList = hadoopDataNodeService
				.queryForStatisticsList(hadoopDataNodeObj);

		// 第二步，统计各统计量的大小
		for (HadoopDataNodeObj dataNodeObj : hdnoList) {
			try {
				statisticsObj.setTotalCapacity(statisticsObj.getTotalCapacity()
						+ convert(dataNodeObj.getConfigured_capacity()
								.doubleValue() / 1024));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				statisticsObj
						.setFileSystemUsedCapacity(statisticsObj
								.getFileSystemUsedCapacity()
								+ convert(dataNodeObj.getDfs_used()
										.doubleValue() / 1024));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				statisticsObj
						.setFileSystemLeftCapacity(statisticsObj
								.getFileSystemLeftCapacity()
								+ convert(dataNodeObj.getRemaining()
										.doubleValue() / 1024));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				statisticsObj
						.setNonDfsUsedCapacity(statisticsObj
								.getNonDfsUsedCapacity()
								+ convert(dataNodeObj.getNon_dfs_used()
										.doubleValue() / 1024));
			} catch (Exception e) {
				e.printStackTrace();
			}
			statisticsObj.setBlockCount(statisticsObj.getBlockCount()
					+ dataNodeObj.getBlocks());
		}
	}

	/**
	 * @Title: setFileValues
	 * @Description: 文件
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-15 下午2:17:39
	 */
	private void setFileValues(List<HadoopTreeObj> hdfsList) {
		List<String> clusterUuidList = new ArrayList<String>();
		for (HadoopTreeObj hadoopTreeObj : hdfsList) {
			clusterUuidList.add(hadoopTreeObj.getUuid());
		}
		try {
			statisticsObj.setFileCount(statisticsObj.getFileCount()
					+ this.getInterValue(clusterUuidList,
							"hdfs_cluster_TotalFiles"));
			// statisticsObj.setBlockCount(statisticsObj.getBlockCount() +
			// this.getInterValue(clusterUuidList, "hdfs_cluster_TotalBlocks"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: setFileSystemUsage
	 * @Description: 文件系统使用率
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-15 上午9:37:27
	 */
	private void setFileSystemUsage() {
		try {
			statisticsObj.setFileSystemLeftage(convert(statisticsObj
					.getFileSystemLeftCapacity()
					/ statisticsObj.getTotalCapacity()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			statisticsObj.setFileSystemUsage(convert(statisticsObj
					.getFileSystemUsedCapacity()
					/ statisticsObj.getTotalCapacity()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			statisticsObj
					.setNonDfsUsage(convert(statisticsObj
							.getNonDfsUsedCapacity()
							/ statisticsObj.getTotalCapacity()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			statisticsObj.setTotalUsedCapacity(statisticsObj
					.getFileSystemUsedCapacity()
					+ statisticsObj.getNonDfsUsedCapacity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			statisticsObj
					.setTotalUsage(convert(statisticsObj.getTotalUsedCapacity()
							/ statisticsObj.getTotalCapacity()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: setMemoryValues
	 * @Description: 获取内存
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-15 上午11:47:56
	 */
	private void setMemoryValues(List<HadoopTreeObj> yarnList) {
		List<String> clusterUuidList = new ArrayList<String>();
		for (HadoopTreeObj hadoopTreeObj : yarnList) {
			clusterUuidList.add(hadoopTreeObj.getUuid());
		}
		try {
			statisticsObj.setReservedMB(statisticsObj.getReservedMB()
					+ convert(this.getDoubleValue(clusterUuidList,
							"yarn_cluster_reservedMB")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			statisticsObj.setAvailabledMB(statisticsObj.getAvailabledMB()
					+ convert(this.getDoubleValue(clusterUuidList,
							"yarn_cluster_availableMB")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			statisticsObj.setAllocatedMB(statisticsObj.getAllocatedMB()
					+ convert(this.getDoubleValue(clusterUuidList,
							"yarn_cluster_allocatedMB")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			statisticsObj.setTotalMB(statisticsObj.getTotalMB()
					+ convert(this.getDoubleValue(clusterUuidList,
							"yarn_cluster_totalMB")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			statisticsObj.setAllocatedAge(convert(statisticsObj
					.getAllocatedMB() / statisticsObj.getTotalMB()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: setJobsValues
	 * @Description: 统计job作业数
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-15 上午10:49:06
	 */
	private void setJobsValues(List<HadoopTreeObj> yarnList) {
		List<String> clusterUuidList = new ArrayList<String>();
		for (HadoopTreeObj hadoopTreeObj : yarnList) {
			clusterUuidList.add(hadoopTreeObj.getUuid());
		}
		statisticsObj.setJobsSubmittedCount(statisticsObj
				.getJobsSubmittedCount()
				+ this.getInterValue(clusterUuidList,
						"yarn_cluster_appsSubmitted"));
		statisticsObj.setJobsCompletedCount(statisticsObj
				.getJobsCompletedCount()
				+ this.getInterValue(clusterUuidList,
						"yarn_cluster_appsCompleted"));
		statisticsObj
				.setJobsFailedCount(statisticsObj.getJobsFailedCount()
						+ this.getInterValue(clusterUuidList,
								"yarn_cluster_appsFailed"));
		statisticsObj
				.setJobsKilledCount(statisticsObj.getJobsKilledCount()
						+ this.getInterValue(clusterUuidList,
								"yarn_cluster_appsKilled"));
		statisticsObj.setJobsPreparingCount(statisticsObj
				.getJobsPreparingCount()
				+ this.getInterValue(clusterUuidList,
						"yarn_cluster_appsPending"));
		statisticsObj.setJobsRunningCount(statisticsObj.getJobsRunningCount()
				+ this.getInterValue(clusterUuidList,
						"yarn_cluster_appsRunning"));
	}

	/**
	 * @Title: setHbaseValues
	 * @Description: 统计hbase信息
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-15 下午2:24:30
	 */
	private void setHbaseValues(HadoopTreeObj treeObj) {
		String clusterId = this.getClusterId(treeObj);
		statisticsObj.setBlockCacheHitRatioMax(statisticsObj
				.getBlockCacheHitRatioMax()
				+ this.dealValue(
						this.statisticsBCHitRadio(
								"hbase.regionserver.blockCacheHitRatio",
								clusterId, "max")).doubleValue());
		statisticsObj.setBlockCacheHitRatioMin(statisticsObj
				.getBlockCacheHitRatioMin()
				+ this.dealValue(
						this.statisticsBCHitRadio(
								"hbase.regionserver.blockCacheHitRatio",
								clusterId, "min")).doubleValue());
		statisticsObj.setCompactionQueueSize(statisticsObj
				.getCompactionQueueSize()
				+ this.dealValue(
						this.statisticsFromCollTable(
								"hbase.regionserver.compactionQueueSize",
								clusterId)).intValue());
		statisticsObj
				.setFlushQueueSize(statisticsObj.getFlushQueueSize()
						+ this.dealValue(
								this.statisticsFromCollTable(
										"hbase.regionserver.flushQueueSize",
										clusterId)).intValue());
		statisticsObj
				.setBlockCacheCount(statisticsObj.getBlockCacheCount()
						+ this.dealValue(
								this.statisticsFromCollTable(
										"hbase.regionserver.blockCacheCount",
										clusterId)).intValue());
		statisticsObj
				.setBlockCacheSize(statisticsObj.getBlockCacheSize()
						+ this.dealValue(
								this.statisticsFromCollTable(
										"hbase.regionserver.blockCacheSize",
										clusterId)).doubleValue() / 1024 / 1024);
		statisticsObj
				.setBlockCacheFree(statisticsObj.getBlockCacheFree()
						+ this.dealValue(
								this.statisticsFromCollTable(
										"hbase.regionserver.blockCacheFree",
										clusterId)).doubleValue() / 1024 / 1024);
		statisticsObj.setBlockCacheHitCount(statisticsObj
				.getBlockCacheHitCount()
				+ this.dealValue(
						this.statisticsFromCollTable(
								"hbase.regionserver.blockCacheHitCount",
								clusterId)).intValue());
		statisticsObj.setRegionsCount(statisticsObj.getRegionsCount()
				+ this.dealValue(
						this.statisticsFromCollTable(
								"hbase.regionserver.regions", clusterId))
						.intValue());
		statisticsObj
				.setMemstoreSizeMB(statisticsObj.getMemstoreSizeMB()
						+ this.dealValue(
								this.statisticsFromCollTable(
										"hbase.regionserver.memstoreSizeMB",
										clusterId)).doubleValue());
		statisticsObj.setRequests(statisticsObj.getRequests()
				+ this.dealValue(
						this.statisticsFromCollTable(
								"hbase.regionserver.requests", clusterId))
						.intValue());
	}

	/**
	 * @Title: statisticsBCHitRadio
	 * @Description: 统计bc命中率
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-26 下午4:31:21
	 */
	private String statisticsBCHitRadio(String string, String clusterId,
			String flag) {
		HadoopClusterObj clusterObj = new HadoopClusterObj();
		clusterObj.setId(clusterId);
		List<HadoopClusterObj> clusterList = hadoopClusterServer
				.queryClusterList(clusterObj);
		if (clusterList.size() > 0) {
			clusterObj = clusterList.get(0);
			clusterId = clusterObj.getCluster_id();
		}
		HadoopMonitorObj hadoopMonitorObj = new HadoopMonitorObj();
		hadoopMonitorObj.setCluster_name(clusterId);
		hadoopMonitorObj.setKpi_id(string);
		List<HadoopMonitorObj> monitorList = hadoopMonitorService
				.queryForListOrderByValueDesc(hadoopMonitorObj);
		if (monitorList.size() > 0) {
			if ("max".equals(flag)) {
				hadoopMonitorObj = monitorList.get(0);
			} else {
				hadoopMonitorObj = monitorList.get(monitorList.size() - 1);
			}
		}
		return hadoopMonitorObj.getKpi_value();
	}

	/**
	 * @Title: setNameNodeValues
	 * @Description: 获取namenode指标信息
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-16 上午9:54:27
	 */
	private void setNameNodeValues(HadoopTreeObj treeObj) {
		String clusterId = this.getClusterId(treeObj);
		statisticsObj.setFsImageLoadTime(this.dealValue(
				this.statisticsFromCollTable("dfs.namenode.FsImageLoadTime",
						clusterId)).doubleValue());
		statisticsObj.setBlockReportAvgTime(this.dealValue(
				this.statisticsFromCollTable("dfs.namenode.BlockReportAvgTime",
						clusterId)).doubleValue());
		statisticsObj.setBlockReportNumOps(this.dealValue(
				this.statisticsFromCollTable("dfs.namenode.BlockReportNumOps",
						clusterId)).doubleValue());
		statisticsObj.setFilesCreated(this.dealValue(
				this.statisticsFromCollTable("dfs.namenode.FilesCreated",
						clusterId)).intValue());
		statisticsObj.setAddBlockOps(this.dealValue(
				this.statisticsFromCollTable("dfs.namenode.AddBlockOps",
						clusterId)).doubleValue());
	}

	/**
	 * @Title: setDataNodeValues
	 * @Description: 获取datanode指标信息
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-16 上午9:54:27
	 */
	private void setDataNodeValues(HadoopTreeObj treeObj) {
		String clusterId = this.getClusterId(treeObj);
		statisticsObj.setFlushNanosAvgTime(this.dealValue(
				this.statisticsFromCollTable("dfs.datanode.FlushNanosAvgTime",
						clusterId)).doubleValue());
		statisticsObj.setFlushNanosNumOps(this.dealValue(
				this.statisticsFromCollTable("dfs.datanode.FlushNanosNumOps",
						clusterId)).doubleValue());
		statisticsObj.setBlockReportsAvgTime(this.dealValue(
				this.statisticsFromCollTable(
						"dfs.datanode.BlockReportsAvgTime", clusterId))
				.doubleValue());
		statisticsObj.setBlockReportsNumOps(this.dealValue(
				this.statisticsFromCollTable("dfs.datanode.BlockReportsNumOps",
						clusterId)).doubleValue());
		statisticsObj.setHeartbeatsAvgTime(this.dealValue(
				this.statisticsFromCollTable("dfs.datanode.HeartbeatsAvgTime",
						clusterId)).doubleValue());
		statisticsObj.setHeartbeatsNumOps(this.dealValue(
				this.statisticsFromCollTable("dfs.datanode.HeartbeatsNumOps",
						clusterId)).doubleValue());
		statisticsObj.setBlockWrittenAvgTime(convert(this.dealValue(
				this.statisticsFromCollTable(
						"dfs.datanode.WriteBlockOpAvgTime", clusterId))
				.doubleValue() / 1000));
		statisticsObj.setBlockReadAvgTime(this.dealValue(
				this.statisticsFromCollTable("dfs.datanode.ReadBlockOpAvgTime",
						clusterId)).doubleValue());
	}

	/**
	 * @Title: getClusterId
	 * @Description: 获取集群id
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-16 上午9:57:02
	 */
	private String getClusterId(HadoopTreeObj treeObj) {
		String clusterId = null;
		if (!HadoopConstant.hadoop_dc.equals(treeObj.getNode_type())) {
			try {
				clusterId = this.acquireClusterName(treeObj, 0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			clusterId = treeObj.getUuid();
		}
		return clusterId;
	}

	/**
	 * @Title: statisticsFromMetricsTable
	 * @Description: 通过集群uuid和kpi从metries表中查询
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-15 上午10:54:41
	 */
	private List<HadoopMetriesObj> statisticsFromMetricsTable(
			List<String> clusterUuidList, String kpi) {
		HadoopMetriesObj hadoopMetriesObj = new HadoopMetriesObj();
		hadoopMetriesObj.setEntityIdList(clusterUuidList);
		hadoopMetriesObj.setMetries_id(kpi);
		List<HadoopMetriesObj> metriesList = hadoopMetriesService
				.queryForListByCluster(hadoopMetriesObj);
		return metriesList;
	}

	/**
	 * @Title: getInterValue
	 * @Description: 获取int值
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-15 上午11:39:06
	 */
	private int getInterValue(List<String> clusterUuidList, String kpi) {
		int retvalue = 0;
		try {
			List<HadoopMetriesObj> metriesList = this
					.statisticsFromMetricsTable(clusterUuidList,
							entryMap.get(kpi));
			for (HadoopMetriesObj hadoopMetriesObj : metriesList) {
				int value = this.dealValue(hadoopMetriesObj.getMetries_value())
						.intValue();
				retvalue = retvalue + value;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retvalue;
	}

	/**
	 * @Title: getInterValue
	 * @Description: 获取double值
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-15 上午11:39:06
	 */
	private double getDoubleValue(List<String> clusterUuidList, String kpi) {
		double retvalue = 0.0;
		try {
			List<HadoopMetriesObj> metriesList = this
					.statisticsFromMetricsTable(clusterUuidList,
							entryMap.get(kpi));
			for (HadoopMetriesObj hadoopMetriesObj : metriesList) {
				double value = this.dealValue(
						hadoopMetriesObj.getMetries_value()).doubleValue();
				retvalue = retvalue + value;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retvalue;
	}

	/**
	 * @Title: statisticsFromCollTable
	 * @Description: 从监控表中查询
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-15 下午2:39:18
	 */
	private String statisticsFromCollTable(String kpi, String clusterId) {
		HadoopClusterObj clusterObj = new HadoopClusterObj();
		clusterObj.setId(clusterId);
		List<HadoopClusterObj> clusterList = hadoopClusterServer
				.queryClusterList(clusterObj);
		if (clusterList.size() > 0) {
			clusterObj = clusterList.get(0);
			clusterId = clusterObj.getCluster_id();
		}
		HadoopMonitorObj hadoopMonitorObj = new HadoopMonitorObj();
		hadoopMonitorObj.setCluster_name(clusterId);
		hadoopMonitorObj.setKpi_id(kpi);
		hadoopMonitorObj.setHost_name(clusterId);
		List<HadoopMonitorObj> monitorList = hadoopMonitorService
				.queryLatestValueByKpi(hadoopMonitorObj);
		if (monitorList.size() > 0) {
			hadoopMonitorObj = monitorList.get(0);
		}
		return hadoopMonitorObj.getKpi_value();
	}

	/**
	 * @Title: dealValue
	 * @Description: 处理数字
	 * @param
	 * @return Double
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-10 上午11:33:31
	 */
	private Double dealValue(String value) {
		double retvalue = Double
				.parseDouble(org.apache.commons.lang.StringUtils.isEmpty(value) ? "0.0"
						: value);
		Double ret = new Double(retvalue);
		return ret;
	}

	/**
	 * @Title: convert
	 * @Description: 四舍五入，保留两位小数
	 * @param
	 * @return double
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-9 下午9:20:34
	 */
	private double convert(double value) {
		long a = Math.round(value * 100);
		double ret = a / 100.0;
		return ret;
	}

	/**
	 * @Title: initServiceExampleUuidList
	 * @Description: 返回服务实例uuid集合
	 * @param
	 * @return List<String>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-11 下午5:26:43
	 */
	/*
	 * private List<String> initServiceExampleUuidList(Map<String,
	 * List<HadoopTreeObj>> map){ List<String> uuidList = new
	 * ArrayList<String>(); List<HadoopTreeObj> htList = new
	 * ArrayList<HadoopTreeObj>(); Set<String> keySet = map.keySet(); for
	 * (String string : keySet) { List<HadoopTreeObj> treeList =
	 * map.get(string); htList.addAll(treeList); } for (HadoopTreeObj
	 * hadoopTreeObj : htList) { uuidList.add(hadoopTreeObj.getUuid()); } return
	 * uuidList; }
	 */

	/**
	 * @Title: countChildNode
	 * @Description: 递归计算子级节点个数，不单单是子节点
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-1-9 下午7:22:04
	 */
	private List<HadoopTreeObj> acquireChildNode(String childNodeType,
			String parentNodeServiceType, HadoopTreeObj hadoopTreeObj,
			List<HadoopTreeObj> retList) throws Exception {
		try {
			HadoopTreeObj treeObj = new HadoopTreeObj();
			treeObj.setParent_id(hadoopTreeObj.getId());
			List<HadoopTreeObj> treeList = hadoopTreeService
					.queryForListByObj(treeObj);
			for (HadoopTreeObj htObj : treeList) {
				if (childNodeType.equals(htObj.getNode_type())) {
					if (parentNodeServiceType != null
							&& !"".equals(parentNodeServiceType)) {
						// if
						// (parentNodeServiceType.equals(hadoopTreeObj.getService_type()))
						// {//主机节点实际上也录入了服务类型
						if (parentNodeServiceType.equals(htObj
								.getService_type())) {
							retList.add(htObj);
						}
					} else {
						retList.add(htObj);
					}
				} else {
					acquireChildNode(childNodeType, parentNodeServiceType,
							htObj, retList);
				}
			}
			return retList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("递归查询子级节点错误，原因： ", e);
		}
	}

	/**
	 * @Title: acquireChildNodeGroup
	 * @Description: 查询子节点组，递归查询
	 * @param
	 * @return Map<String,List<HadoopTreeObj>>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-1-10 下午3:54:57
	 */
	private Map<String, List<HadoopTreeObj>> acquireChildNodeGroup(
			Map<String, List<HadoopTreeObj>> map, String childNodeType,
			HadoopTreeObj hadoopTreeObj) throws Exception {
		try {
			HadoopTreeObj treeObj = new HadoopTreeObj();
			treeObj.setParent_id(hadoopTreeObj.getId());
			List<HadoopTreeObj> treeList = hadoopTreeService
					.queryForListByObj(treeObj);
			for (HadoopTreeObj htObj : treeList) {
				if (childNodeType.equals(htObj.getNode_type())) {
					String parentNodeServiceType = htObj.getService_type();
					if (parentNodeServiceType == null
							&& "".equals(parentNodeServiceType)) {
						parentNodeServiceType = htObj.getNode_type();
					}
					List<HadoopTreeObj> list = map.get(parentNodeServiceType);
					if (list == null) {
						list = new ArrayList<HadoopTreeObj>();
					}
					list.add(htObj);
					map.put(parentNodeServiceType, list);
				} else {
					acquireChildNodeGroup(map, childNodeType, htObj);
				}
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("递归查询子级节点错误，原因：", e);
		}
	}

	/**
	 * @Title: acquireClusterName
	 * @Description: 递归获取集群的编号
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-14 下午7:22:44
	 */
	private String acquireClusterName(HadoopTreeObj hadoopTreeObj, int count)
			throws Exception {
		HadoopTreeObj treeObj = new HadoopTreeObj();
		count = count + 1;
		try {
			treeObj.setId(hadoopTreeObj.getParent_id());
			treeObj = hadoopTreeService.queryForListByObj(treeObj).get(0);
			if (HadoopConstant.hadoop_dc.equals(treeObj.getNode_type())) {
				return treeObj.getUuid();
			} else {
				if (count >= 6) {// 不能进入死循环，一定次数后强制终止
					return null;
				}
				return acquireClusterName(treeObj, count);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("递归获取集群名称出错，错误原因：", e);
		}
	}

	/**
	 * @Title: initConfigXml
	 * @Description: 初始化配置文件
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-15 上午11:24:52
	 */
	private Map<String, String> initConfigXml() {
		if (entryMap == null) {
			XmlProperties xmlProperties = new XmlProperties(
					"xml/hadoop-metries.xml");
			entryMap = xmlProperties.getMap();
		}
		return entryMap;
	}

	/**
	 * @Title: initNodeMap
	 * @Description: 根据节点类型，初始化节点集合
	 * @param
	 * @return Map<String,List<HadoopTreeObj>>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-14 下午10:00:34
	 */
	private Map<String, List<HadoopTreeObj>> initNodeMap(HadoopTreeObj treeObj,
			String hostnode) {
		// 向下递归出所有服务实例节点
		Map<String, List<HadoopTreeObj>> map = new HashMap<String, List<HadoopTreeObj>>();
		try {
			map = this.acquireChildNodeGroup(map, hostnode, treeObj);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return map;
	}

	/**
	 * @Title: countServiceNodeStatus
	 * @Description: 统计服务实例的状态
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-18 下午4:09:40
	 */
	private void countServiceNodeStatus(Map<String, List<HadoopTreeObj>> nodeMap) {
		try {
			Map<String, Integer> normalMap = new HashMap<String, Integer>();
			Set<String> keySet = nodeMap.keySet();
			for (String string : keySet) {
				// 取出每种类型的服务实例集合，获取uuid的集合
				List<HadoopTreeObj> treeList = nodeMap.get(string);
				List<String> uuidList = new ArrayList<String>();
				for (HadoopTreeObj hadoopTreeObj : treeList) {
					uuidList.add(hadoopTreeObj.getId());
				}
				// 关联服务实例和进程的关系表与进程表，查询进程个数、正常个数
				ProcessObj pObj = new ProcessObj();
				pObj.setNodeIdList(uuidList);
				List<ProcessObj> processList = processService
						.queryHadoopServiceNodeStatus(pObj);
				int normal = 0;
				if (processList.size() > 0) {
					for (ProcessObj processObj : processList) {
						if (processObj.getAllcount() == 0
								|| processObj.getAllcount() == processObj
										.getNormalcount()) {
							normal++;
						}
					}

					// 没有配进程的，都任务正常
					if (processList.size() != treeList.size()) {
						normal += (treeList.size() - processList.size());
					}
				} else {
					normal += treeList.size();
				}
				normalMap.put(string, normal);
			}
			this.setServiceNodeStatusValues(normalMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: setServiceNodeStatusValues
	 * @Description: 统计服务节点状态
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-18 下午4:53:11
	 */
	private void setServiceNodeStatusValues(Map<String, Integer> normalMap) {
		statisticsObj.setNameNodeNormalCount(statisticsObj
				.getNameNodeNormalCount()
				+ (normalMap.get(HadoopConstant.nameNode) == null
						? 0
						: normalMap.get(HadoopConstant.nameNode)));
		statisticsObj.setDatanodeNormalCount(statisticsObj
				.getDatanodeNormalCount()
				+ (normalMap.get(HadoopConstant.dataNode) == null
						? 0
						: normalMap.get(HadoopConstant.dataNode)));
		statisticsObj.setJournalnodeNormalCount(statisticsObj
				.getJournalnodeNormalCount()
				+ (normalMap.get(HadoopConstant.journalNode) == null
						? 0
						: normalMap.get(HadoopConstant.journalNode)));
		statisticsObj.setRegionServerNormalCount(statisticsObj
				.getRegionServerNormalCount()
				+ (normalMap.get(HadoopConstant.regionServer) == null
						? 0
						: normalMap.get(HadoopConstant.regionServer)));
		statisticsObj.setNodeManagerNormalCount(statisticsObj
				.getNodeManagerNormalCount()
				+ (normalMap.get(HadoopConstant.nodeManager) == null
						? 0
						: normalMap.get(HadoopConstant.nodeManager)));
		statisticsObj.setReduceManagerNormalCount(statisticsObj
				.getReduceManagerNormalCount()
				+ (normalMap.get(HadoopConstant.reduceManager) == null
						? 0
						: normalMap.get(HadoopConstant.reduceManager)));
		statisticsObj.setHmasterNormalCount(statisticsObj
				.getHmasterNormalCount()
				+ (normalMap.get(HadoopConstant.hmaster) == null
						? 0
						: normalMap.get(HadoopConstant.hmaster)));
		statisticsObj.setHbaseThirftServerNormalCount(statisticsObj
				.getHbaseThirftServerNormalCount()
				+ (normalMap.get(HadoopConstant.hbase_thirftServer) == null ? 0
						: normalMap.get(HadoopConstant.hbase_thirftServer)));
		statisticsObj.setHiveThirftServerNormalCount(statisticsObj
				.getHiveThirftServerNormalCount()
				+ (normalMap.get(HadoopConstant.hive_thirftServer) == null
						? 0
						: normalMap.get(HadoopConstant.hive_thirftServer)));
		statisticsObj.setZookeeperServerNormalCount(statisticsObj
				.getZookeeperServerNormalCount()
				+ (normalMap.get(HadoopConstant.znode) == null ? 0 : normalMap
						.get(HadoopConstant.znode)));
		statisticsObj.setImpalaServerNormalCount(statisticsObj
				.getImpalaServerNormalCount()
				+ (normalMap.get(HadoopConstant.impalaxx) == null ? 0
						: normalMap.get(HadoopConstant.impalaxx)));
	}

	/**
	 * @Title: countNodeByParentIdList
	 * @Description: 根据父节点查询子节点个数
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-9 下午2:08:44
	 */
	/*
	 * private int countNodeByParentIdList(HadoopTreeObj hadoopTreeObj){
	 * List<HadoopTreeObj> treeList =
	 * hadoopTreeService.queryForListByObj(hadoopTreeObj); return
	 * treeList.size(); }
	 */
	/**
	 * 
	 * @Title: showFusionCharts
	 * @Description: 展示图表
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-5-4 下午4:56:53
	 */
	public String showFusionCharts() {
		FusionCharts fusionCharts = new FusionCharts();
		if (obj == null) {
			obj = new HadoopTreeObj();
		}
		if (statisticsObj == null) {
			statisticsObj = new StatisticsObj();
		}
		this.initConfigXml();
		List<HadoopTreeObj> list = hadoopTreeService.queryForListByObj(obj);
		HadoopTreeObj treeObj = new HadoopTreeObj();
		if (list != null && list.size() > 0) {
			treeObj = list.get(0);
		}
		Map<String, List<HadoopTreeObj>> nodeMap = this.initNodeMap(treeObj,
				HadoopConstant.hostNode);// 获取主机节点集合，按服务类型分组
		List<HadoopTreeObj> hdfsList = new ArrayList<HadoopTreeObj>();
		List<HadoopTreeObj> yarnList = new ArrayList<HadoopTreeObj>();
		try {
			hdfsList = acquireChildNode(HadoopConstant.hdfs, null, treeObj,
					new ArrayList<HadoopTreeObj>());
			yarnList = acquireChildNode(HadoopConstant.mapReduce, null,
					treeObj, new ArrayList<HadoopTreeObj>());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ("hdfs".equals(flag)) {
			if (hdfsList != null && hdfsList.size() > 0) {
				// 文件系统
				this.setFileSystemValues(nodeMap, hdfsList);
			}
			this.setFileSystemUsage();
		} else {
			if (yarnList != null && yarnList.size() > 0) {
				// 统计内存
				this.setMemoryValues(yarnList);
			}
		}
		try {
			double totalUnUsedCapacity = statisticsObj.getTotalCapacity()
					- statisticsObj.getTotalUsedCapacity();
			double unUsedMB = statisticsObj.getTotalMB()
					- statisticsObj.getAllocatedMB();
			List<Data> dataList = new ArrayList<Data>();
			Chart chart = new Chart();
			chart.setBgcolor("FFFFFF");
			chart.setEnableRotation("1");// 开启旋转
			chart.setShowborder("0");// 显示边框
			chart.setPieRadius("30");// 饼的外半径
			chart.setShowlabels("0");// 是否显示Label
			chart.setShowpercentvalues("1");// labels上是否显示百分数
			chart.setShowvalues("0");// 是否显示值
			Data dataUsed = new Data();
			dataUsed.setColor("2675B4");
			if ("hdfs".equals(flag)) {
				dataUsed.setLabel("使用量");
				dataUsed.setValue(statisticsObj.getTotalUsedCapacity() + "");
			} else {
				dataUsed.setLabel("分配量");
				dataUsed.setValue(statisticsObj.getAllocatedMB() + "");
			}
			dataList.add(dataUsed);

			Data dataUnUsed = new Data();
			dataUnUsed.setColor("C91D1D");
			if ("hdfs".equals(flag)) {
				dataUnUsed.setLabel("未使用量");
				dataUnUsed.setValue(totalUnUsedCapacity + "");
			} else {
				dataUnUsed.setLabel("未分配量");
				dataUnUsed.setValue(unUsedMB + "");
			}
			dataList.add(dataUnUsed);
			fusionCharts.setChart(chart);
			fusionCharts.setData(dataList);
		} catch (Exception e) {
			LOG.error("查询失败", e.getMessage() + e.getClass().getName());
		}
		String fusionChartsString = JacksonUtil.toJson(fusionCharts);
		// 传输json数据到界面
		printJson(fusionChartsString);
		return null;
	}

	/**
	 * 
	 * @Title: printJson
	 * @Description: 传输Json数据到界面
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-16 上午10:61:00
	 */
	private synchronized void printJson(String... params) {
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				PrintWriterOut.printWirter(response, params[i]);
			}
		}
	}
}
