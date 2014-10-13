package action.tab;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import service.cluster.HadoopClusterServer;
import service.host.HadoopHostInfoService;
import service.tab.HadoopHostServers;
import service.tree.HadoopTreeService;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.servlet.PrintWriterOut;

import dao.kpi.HadoopKpiDao;
import domain.cluster.HadoopClusterObj;
import domain.host.HadoopHostInfoObj;
import domain.kpi.HadoopKpiObj;
import domain.tab.ChartType;
import domain.tab.HadoopHostServerObj;
import domain.tab.MapDataSet;
import domain.tree.HadoopTreeObj;

@Controller("hadoopHostServerAction")
@Scope("prototype")
public class HadoopHostServerAction extends BaseAction {

	private List<HadoopHostServerObj> resultList;

	private String entityId;

	private String parentId;

	private int isCluster = 0;// 标识当前节点是否是集群,0为主机1为集群

	private String kpiDesc;

	private String kpiUuid;// kpi

	private int interval = -1;

	private int potCount = 50;

	private String service_name;

	private String hostName;

	private String clusterName;

	private List<HadoopKpiObj> kpiIds;

	private int isHideKpiList;// 是否在页面上隐藏其他列表 0 隐藏 1不隐藏

	private String kpiId;

	private String flag;

	@Autowired
	private HadoopKpiDao hadoopKpiDao;

	@Autowired
	private HadoopHostServers hadoopHostServers;

	@Autowired
	private HadoopTreeService hadoopTreeService;

	@Autowired
	private HadoopHostInfoService hadoopHostInfoService;

	@Autowired
	private HadoopClusterServer hadoopClusterServer;

	private Map<String, String> resultMap;

	public String listHadoopHostServer() {
		try {
			/**
			 * 初始化主机名称 ，集群名称，和父节点的服务id
			 */
			HadoopHostInfoObj hostObj = new HadoopHostInfoObj();
			hostObj.setId(entityId);
			hostObj = hadoopHostInfoService
					.queryForHadoopHostAndClusterInfoObj(hostObj);
			hostName = hostObj.getHost_name();
			clusterName = hostObj.getCluster_id();
			HadoopTreeObj treeObj = new HadoopTreeObj();
			treeObj.setId(parentId);
			List<HadoopTreeObj> treeObjs = hadoopTreeService
					.queryForListByObj(treeObj);
			if (treeObjs.size() == 1) {
				treeObj = treeObjs.get(0);
			}

			String service_name = treeObj.getName();
			/**
			 * 如果没有主机和集群的这个指标的监控信息就取不到其监控指标，目前主机名称和集群名称并没有使用
			 */
			if (0 == isHideKpiList) {
				HadoopKpiObj obj = new HadoopKpiObj();
				obj.setService_name(service_name);
				obj.setHostName(hostName);
				obj.setClusterName(clusterName);
				obj.setKpi_id(kpiId);
				obj.setDescription(kpiDesc);
				obj.setPagination(this.getPaginater().initPagination(request));// 分页
				kpiIds = hadoopHostServers.queryKpiIdDataByHost(obj);
			}
			resultMap = this.queryLastData(hostName, clusterName);// 查询最新一条数据
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list_hadoop_host_server";
	}

	public String listHadoopHostServerKpi() {
		try {
			/**
			 * 初始化主机名称 ，集群名称，和父节点的服务id
			 */
			HadoopHostInfoObj hostObj = new HadoopHostInfoObj();
			hostObj.setId(entityId);
			hostObj = hadoopHostInfoService.queryForHadoopHostAndClusterInfoObj(hostObj);
			hostName = hostObj.getHost_name();
			clusterName = hostObj.getCluster_id();
			HadoopTreeObj treeObj = new HadoopTreeObj();
			treeObj.setId(parentId);
			List<HadoopTreeObj> treeObjs = hadoopTreeService.queryForListByObj(treeObj);
			if (treeObjs.size() == 1) {
				treeObj = treeObjs.get(0);
			}

			String service_name = treeObj.getName();
			/**
			 * 如果没有主机和集群的这个指标的监控信息就取不到其监控指标，目前主机名称和集群名称并没有使用
			 */
			if (0 == isHideKpiList) {
				HadoopKpiObj obj = new HadoopKpiObj();
				obj.setService_name(service_name);
				obj.setHostName(hostName);
				obj.setClusterName(clusterName);
				obj.setKpi_id(kpiId);
				obj.setDescription(kpiDesc);
				obj.setPagination(this.getPaginater().initPagination(request));// 分页
				kpiIds = hadoopHostServers.queryKpiIdDataByHost(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list_hadoop_host_server_kpi";
	}

	public String listHadoopOnlyHostServer() {
		try {
			/**
			 * 初始化主机名称 ，集群名称，和父节点的服务id
			 */
			HadoopHostInfoObj hostObj = new HadoopHostInfoObj();
			hostObj.setId(entityId);
			hostObj = hadoopHostInfoService.queryForHadoopHostAndClusterInfoObj(hostObj);
			hostName = hostObj.getHost_name();
			clusterName = hostObj.getCluster_id();
			resultMap = this.queryLastData(hostName, clusterName);// 查询最新一条数据
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list_hadoop_only_host_server";
	}

	public String listHadoopHostSystemLoad() {
		Map<String, HadoopKpiObj> kpiIds = new HashMap<String, HadoopKpiObj>();
		if (flag == null || flag.equals("")) {
			kpiIds.put("load_one", new HadoopKpiObj("load_one", "1分钟系统负载",
					ChartType.Area));
			kpiIds.put("load_five", new HadoopKpiObj("load_five", "5分钟系统负载 ",
					ChartType.Area));
		} else if (flag.equals("load_one")) {
			kpiIds.put("load_one", new HadoopKpiObj("load_one", "1分钟系统负载",
					ChartType.Area));
		} else if (flag.equals("load_five")) {
			kpiIds.put("load_five", new HadoopKpiObj("load_five", "5分钟系统负载 ",
					ChartType.Area));
		}
		// kpiIds.put("load_fifteen",new
		// HadoopKpiObj("load_fifteen","load_fifteen",ChartType.Area));
		// kpiIds.put("proc_run", new HadoopKpiObj("proc_run", "进程运行个数",
		// ChartType.Area));
		// kpiIds.put("proc_total", new HadoopKpiObj("proc_total", "进程总数",
		// ChartType.Area));
		String title = "系统负载的最新数据";
		MapDataSet mds = new MapDataSet();
		if (1 == isCluster) {
			mds.setHostName(clusterName);
			title = "集群System负载的最新数据";
		} else {
			mds.setHostName(hostName);
		}
		mds.setClusterName(clusterName);
		mds.setCaption(title);
		mds.setInterval(interval);
		mds.setPotCount(potCount);
		mds.setKpiIds(kpiIds);
		FusionCharts fChart = hadoopHostServers.queryChartDayData(mds, "", "Loads/Procs", false);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		response.setCharacterEncoding("UTF-8");
		// out = response.getWriter();
		// out.print(fusionChartsString);
		// out.close();
		PrintWriterOut.printWirter(response, fusionChartsString);
		return null;
	}

	public String listHadoopHostSystemCpu() {
		Map<String, HadoopKpiObj> kpiIds = new HashMap<String, HadoopKpiObj>();
		// kpiIds.put("cpu_aidle",new
		// HadoopKpiObj("cpu_aidle","cpu_aidle",ChartType.Line));
		// kpiIds.put("cpu_num", new HadoopKpiObj("cpu_num", "cpu总核数",
		// ChartType.Area));
		// kpiIds.put("cpu_nice", new HadoopKpiObj("cpu_nice", "Nice",
		// ChartType.Line));
		// kpiIds.put("cpu_steal",new
		// HadoopKpiObj("cpu_steal","cpu_steal",ChartType.Line));
		if (flag == null || flag.equals("")) {
			kpiIds.put("cpu_idle", new HadoopKpiObj("cpu_idle", "cpu空闲",
					ChartType.Line));
			kpiIds.put("cpu_system", new HadoopKpiObj("cpu_system",
					"系统空间占用CPU", ChartType.Line));
			kpiIds.put("cpu_user", new HadoopKpiObj("cpu_user", "用户空间占用CPU",
					ChartType.Line));
			kpiIds.put("cpu_wio", new HadoopKpiObj("cpu_wio", "等待输入输出占用CPU",
					ChartType.Line));
		} else if (flag.equals("cpu_idle")) {
			kpiIds.put("cpu_idle", new HadoopKpiObj("cpu_idle", "cpu空闲",
					ChartType.Line));
		} else if (flag.equals("cpu_system")) {
			kpiIds.put("cpu_system", new HadoopKpiObj("cpu_system",
					"系统空间占用CPU", ChartType.Line));
		} else if (flag.equals("cpu_user")) {
			kpiIds.put("cpu_user", new HadoopKpiObj("cpu_user", "用户空间占用CPU",
					ChartType.Line));
		} else if (flag.equals("cpu_wio")) {
			kpiIds.put("cpu_wio", new HadoopKpiObj("cpu_wio", "等待输入输出占用CPU",
					ChartType.Line));
		}
		String title = "系统CPU性能的最新数据";
		MapDataSet mds = new MapDataSet();
		if (1 == isCluster) {
			mds.setHostName(clusterName);
			title = "集群CPU性能的最新数据";
		} else {
			mds.setHostName(hostName);
		}
		mds.setClusterName(clusterName);
		mds.setCaption(title);
		mds.setInterval(interval);
		mds.setPotCount(potCount);
		mds.setKpiIds(kpiIds);
		FusionCharts fChart = hadoopHostServers.queryChartDayData(mds, "", "percent", false);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		response.setCharacterEncoding("UTF-8");
		// out = response.getWriter();
		// out.print(fusionChartsString);
		// out.close();
		PrintWriterOut.printWirter(response, fusionChartsString);
		return null;
	}

	public String listHadoopHostSystemMemory() {
		Map<String, HadoopKpiObj> kpiIds = new HashMap<String, HadoopKpiObj>();
		kpiIds.put("mem_total", new HadoopKpiObj("mem_total", "内存总量",
				ChartType.Area));
		kpiIds.put("mem_free", new HadoopKpiObj("mem_free", "内存余量",
				ChartType.Area));
		kpiIds.put("mem_usage", new HadoopKpiObj("mem_usage", "内存使用率",
				ChartType.Line));
		// kpiIds.put("mem_cached", new HadoopKpiObj("mem_cached", "缓冲的交换区总量",
		// ChartType.Area));
		// kpiIds.put("mem_buffers", new HadoopKpiObj("mem_buffers", "内核缓存的内存量",
		// ChartType.Area));
		// kpiIds.put("swap_total", new HadoopKpiObj("swap_total", "交换区总量",
		// ChartType.Area));
		// kpiIds.put("mem_shared", new HadoopKpiObj("mem_shared", "Shared",
				// ChartType.Area));
		String title = "系统Memory性能的最新数据";
		MapDataSet mds = new MapDataSet();
		if (1 == isCluster) {
			mds.setHostName(clusterName);
			title = "集群Memory性能的最新数据";
		} else {
			mds.setHostName(hostName);
		}
		mds.setClusterName(clusterName);
		mds.setCaption(title);
		mds.setInterval(interval);
		mds.setPotCount(potCount);
		mds.setKpiIds(kpiIds);
		FusionCharts fChart = hadoopHostServers.queryChartDayData(mds, "mem",
				"%", false);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		response.setCharacterEncoding("UTF-8");
		// out = response.getWriter();
		// out.print(fusionChartsString);
		// out.close();
		PrintWriterOut.printWirter(response, fusionChartsString);
		return null;
	}

	public String listHadoopHostSystemNetwork() {
		Map<String, HadoopKpiObj> kpiIds = new HashMap<String, HadoopKpiObj>();
		if (flag == null || flag.equals("")) {
			kpiIds.put("pkts_in", new HadoopKpiObj("pkts_in", "网络输入",
					ChartType.Line));
			kpiIds.put("pkts_out", new HadoopKpiObj("pkts_out", "网络输出",
					ChartType.Line));
		} else if (flag.equals("pkts_in")) {
			kpiIds.put("pkts_in", new HadoopKpiObj("pkts_in", "网络输入",
					ChartType.Line));
		} else if (flag.equals("pkts_out")) {
			kpiIds.put("pkts_out", new HadoopKpiObj("pkts_out", "网络输出",
					ChartType.Line));
		}
		String title = "系统Network读写的最新数据";
		MapDataSet mds = new MapDataSet();
		if (1 == isCluster) {
			mds.setHostName(clusterName);
			title = "集群Network读写的最新数据";
		} else {
			mds.setHostName(hostName);
		}
		mds.setClusterName(clusterName);
		mds.setCaption(title);
		mds.setInterval(interval);
		mds.setPotCount(potCount);
		mds.setKpiIds(kpiIds);
		FusionCharts fChart = hadoopHostServers.queryChartDayData(mds, "", "Bytes/sec", false);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		// response.setCharacterEncoding("UTF-8");
		// out = response.getWriter();
		// out.print(fusionChartsString);
		// out.close();
		PrintWriterOut.printWirter(response, fusionChartsString);
		return null;
	}

	public String listClusterHadoopHostDFS() {
		Map<String, HadoopKpiObj> kpiIds = new HashMap<String, HadoopKpiObj>();
		kpiIds.put("disk_total", new HadoopKpiObj("disk_total", " 集群磁盘总容量",
				ChartType.Area));
		kpiIds.put("dfs.FSNamesystem.CapacityTotalGB", new HadoopKpiObj(
				"dfs.FSNamesystem.BlockCapacity", " HDFS文件系的总容量",
				ChartType.Area));
		kpiIds.put("dfs.FSNamesystem.CapacityRemainingGB", new HadoopKpiObj(
				"dfs.FSNamesystem.BlocksTotal", " HDFS文件系统剩余的容量",
				ChartType.Area));
		String title = "HDFS文件系统容量的最新数据";
		MapDataSet mds = new MapDataSet();
		mds.setHostName(clusterName);
		mds.setClusterName(clusterName);
		mds.setCaption(title);
		mds.setInterval(interval);
		mds.setPotCount(potCount);
		mds.setKpiIds(kpiIds);
		FusionCharts fChart = hadoopHostServers.queryChartDayData(mds, "dfs", "GB", false);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		response.setCharacterEncoding("UTF-8");
		// out = response.getWriter();
		// out.print(fusionChartsString);
		// out.close();
		PrintWriterOut.printWirter(response, fusionChartsString);
		return null;
	}

	private DefaultCategoryDataset createDataSet(String title, List<Data> datas) {
		DefaultCategoryDataset linedataset = new DefaultCategoryDataset();
		for (Data d : datas) {
			linedataset.addValue(Double.parseDouble(d.getValue()), title, d.getLabel());
		}
		return linedataset;

	}

	/**
	 * 点击缩略图后弹出的详细图信息
	 * 
	 * @Title: hadoopAlertMonitor
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-8 下午5:55:17
	 */
	public String hadoopAlertMonitor() {
		HadoopKpiObj kpiObj = new HadoopKpiObj();
		kpiObj.setId(kpiUuid);
		List<HadoopKpiObj> kpiObjs = hadoopKpiDao.queryHadoopKpiLists(kpiObj);
		kpiObj = kpiObjs.get(0);
		kpiId = kpiObj.getKpi_id();
		String unit = kpiObj.getUnit();
		Map<String, HadoopKpiObj> kpiIds = new HashMap<String, HadoopKpiObj>();
		String shape = kpiObj.getShape();
		String desc = kpiObj.getDescription();
		if (shape != null && "1".equals(shape)) {
			// 1.线图2.面图
			kpiIds.put(kpiId, new HadoopKpiObj(kpiId, desc, ChartType.Line));
		} else if (shape != null && "2".equals(shape)) {
			kpiIds.put(kpiId, new HadoopKpiObj(kpiId, desc, ChartType.Area));
		} else {
			kpiIds.put(kpiId, new HadoopKpiObj(kpiId, desc, ChartType.Line));
		}
		String title = desc + "(" + kpiId + ")  " + "最后一段时间数据";
		MapDataSet mds = new MapDataSet();
		mds.setHostName(hostName);
		mds.setClusterName(clusterName);
		mds.setCaption(title);
		mds.setInterval(interval);
		mds.setPotCount(potCount);
		mds.setKpiIds(kpiIds);

		FusionCharts fChart = hadoopHostServers.queryChartDayData(mds, "", unit, true);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		response.setCharacterEncoding("UTF-8");
		// out = response.getWriter();
		// out.print(fusionChartsString);
		// out.close();
		PrintWriterOut.printWirter(response, fusionChartsString);
		return null;
	}

	public String listClusterHadoopHostServer() {
		/**
		 * 初始化集群
		 */
		isCluster = 1;
		HadoopClusterObj obj = new HadoopClusterObj();
		obj.setId(entityId);
		List<HadoopClusterObj> objs = hadoopClusterServer.queryClusterList(obj);
		obj = objs.get(0);
		clusterName = obj.getCluster_id();
		return "list_hadoop_cluster_host_server";
	}

	/**
	 * 获取kpiid对应的详细信息
	 * 
	 * @Title: getKpiIdChart
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-7 下午5:39:35
	 */
	public String getKpiIdChart() {
		return "get_kpi_id_chart";
	}

	/**
	 * 
	 * @Title: queryLastData
	 * @Description: 查询最后一条数据
	 * @param
	 * @return Map<String,String>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-5-7 下午2:46:36
	 */
	public Map<String, String> queryLastData(String hostName, String clusterName) {
		Map<String, String> map = new HashMap<String, String>();
		// 最后一条数据
		Map<String, HadoopKpiObj> kpiMap = new HashMap<String, HadoopKpiObj>();
		kpiMap.put("load_one", new HadoopKpiObj("load_one", "1分钟系统负载",
				ChartType.Area));
		kpiMap.put("load_five", new HadoopKpiObj("load_five", "5分钟系统负载 ",
				ChartType.Area));
		kpiMap.put("cpu_num", new HadoopKpiObj("cpu_num", "cpu核数",
				ChartType.Area));
		kpiMap.put("cpu_idle", new HadoopKpiObj("cpu_idle", "cpu空闲",
				ChartType.Line));
		kpiMap.put("cpu_system", new HadoopKpiObj("cpu_system", "系统空间占用CPU百分比",
				ChartType.Line));
		kpiMap.put("cpu_user", new HadoopKpiObj("cpu_user", "用户空间占用CPU百分比",
				ChartType.Line));
		kpiMap.put("cpu_wio", new HadoopKpiObj("cpu_wio", "等待输入输出占用CPU百分比",
				ChartType.Line));
		kpiMap.put("mem_total", new HadoopKpiObj("mem_total", "内存总量",
				ChartType.Area));
		kpiMap.put("mem_free", new HadoopKpiObj("mem_free", "内存余量",
				ChartType.Area));
		kpiMap.put("mem_cached", new HadoopKpiObj("mem_cached", "缓冲的交换区总量",
				ChartType.Area));
		kpiMap.put("mem_buffers", new HadoopKpiObj("mem_buffers", "内核缓存的内存量",
				ChartType.Area));
		kpiMap.put("swap_total", new HadoopKpiObj("swap_total", "交换区总量",
				ChartType.Area));
		kpiMap.put("pkts_in", new HadoopKpiObj("pkts_in", "网络输入",
				ChartType.Line));
		kpiMap.put("pkts_out", new HadoopKpiObj("pkts_out", "网络输出",
				ChartType.Line));
		kpiMap.put("disk_total", new HadoopKpiObj("disk_total", " 集群磁盘总容量",
				ChartType.Area));
		kpiMap.put("dfs.FSNamesystem.CapacityTotalGB", new HadoopKpiObj(
				"dfs.FSNamesystem.BlockCapacity", " HDFS文件系的总容量",
				ChartType.Area));
		kpiMap.put("dfs.FSNamesystem.CapacityRemainingGB", new HadoopKpiObj(
				"dfs.FSNamesystem.BlocksTotal", " HDFS文件系统剩余的容量",
				ChartType.Area));
		MapDataSet mds = new MapDataSet();
		mds.setKpiIds(kpiMap);
		mds.setHostName(hostName);
		mds.setClusterName(clusterName);
		mds.setPotCount(1);
		map = hadoopHostServers.queryHostLastData(mds);
		return map;
	}
	public List<HadoopHostServerObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<HadoopHostServerObj> resultList) {
		this.resultList = resultList;
	}

	public String getKpiId() {
		return kpiId;
	}

	public void setKpiId(String kpiId) {
		this.kpiId = kpiId;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public HadoopHostServers getHadoopHostServers() {
		return hadoopHostServers;
	}

	public void setHadoopHostServers(HadoopHostServers hadoopHostServers) {
		this.hadoopHostServers = hadoopHostServers;
	}

	public HadoopTreeService getHadoopTreeService() {
		return hadoopTreeService;
	}

	public void setHadoopTreeService(HadoopTreeService hadoopTreeService) {
		this.hadoopTreeService = hadoopTreeService;
	}

	public HadoopHostInfoService getHadoopHostInfoService() {
		return hadoopHostInfoService;
	}

	public int getIsCluster() {
		return isCluster;
	}

	public String getKpiDesc() {
		return kpiDesc;
	}

	public void setKpiDesc(String kpiDesc) {
		this.kpiDesc = kpiDesc;
	}

	public void setIsCluster(int isCluster) {
		this.isCluster = isCluster;
	}

	public int getPotCount() {
		return potCount;
	}

	public String getKpiUuid() {
		return kpiUuid;
	}

	public void setKpiUuid(String kpiUuid) {
		this.kpiUuid = kpiUuid;
	}

	public int getIsHideKpiList() {
		return isHideKpiList;
	}

	public void setIsHideKpiList(int isHideKpiList) {
		this.isHideKpiList = isHideKpiList;
	}

	public void setPotCount(int potCount) {
		this.potCount = potCount;
	}

	public void setHadoopHostInfoService(HadoopHostInfoService hadoopHostInfoService) {
		this.hadoopHostInfoService = hadoopHostInfoService;
	}

	public List<HadoopKpiObj> getKpiIds() {
		return kpiIds;
	}

	public void setKpiIds(List<HadoopKpiObj> kpiIds) {
		this.kpiIds = kpiIds;
	}

	public Map<String, String> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, String> resultMap) {
		this.resultMap = resultMap;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
