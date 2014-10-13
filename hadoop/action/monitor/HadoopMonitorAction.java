package action.monitor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import service.cluster.HadoopClusterServer;
import service.host.HadoopHostInfoService;
import service.monitor.HadoopMonitorService;
import service.tree.HadoopTreeService;
import util.HadoopConstant;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.servlet.PrintWriterOut;

import domain.cluster.HadoopClusterObj;
import domain.host.HadoopHostInfoObj;
import domain.monitor.HadoopMonitorObj;
import domain.tree.HadoopTreeObj;

/**
 * <p>
 * Title: HadoopMonitorAction
 * </p>
 * <p>
 * Description: hadoop服务监控
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
 * @createtime 2014-1-6 上午10:38:12
 * 
 */
@SuppressWarnings("serial")
@Controller("hadoopMonitorAction")
@Scope("prototype")
public class HadoopMonitorAction extends BaseAction {

	@Autowired
	private HadoopMonitorService hadoopMonitorService;

	@Autowired
	private HadoopTreeService hadoopTreeService;

	@Autowired
	private HadoopClusterServer hadoopClusterServer;

	private HadoopTreeObj obj;

	private HadoopMonitorObj hadoopMonitorObj;

	private List<HadoopMonitorObj> resultList;
	@Autowired
	private HadoopHostInfoService hadoopHostInfoService;

	private String refer;// 谁指向我

	public HadoopTreeObj getObj() {
		return obj;
	}

	public void setObj(HadoopTreeObj obj) {
		this.obj = obj;
	}

	public HadoopMonitorObj getHadoopMonitorObj() {
		return hadoopMonitorObj;
	}

	public void setHadoopMonitorObj(HadoopMonitorObj hadoopMonitorObj) {
		this.hadoopMonitorObj = hadoopMonitorObj;
	}

	public List<HadoopMonitorObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<HadoopMonitorObj> resultList) {
		this.resultList = resultList;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	/**
	 * @Title: listLatestKpiValue
	 * @Description: 查询各个节点的各kpi的最新值
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-7 下午6:03:26
	 */
	public String listLatestKpiValue() {
		if (obj == null) {
			obj = new HadoopTreeObj();
		}
		if (hadoopMonitorObj == null) {
			hadoopMonitorObj = new HadoopMonitorObj();
		}

		resultList = new ArrayList<HadoopMonitorObj>();
		List<HadoopTreeObj> treeList = hadoopTreeService.queryForListByObj(obj);
		if (treeList.size() > 0) {
			HadoopTreeObj hadoopTreeObj = treeList.get(0);
			hadoopMonitorObj.setService_name(hadoopTreeObj.getName());
			try {
				hadoopMonitorObj.setCluster_name(this.acquireClusterName(hadoopTreeObj, 0));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (hadoopMonitorObj.getService_name() != null
					&& hadoopMonitorObj.getCluster_name() != null) {
				resultList = hadoopMonitorService.queryLastestValueForKpi(hadoopMonitorObj);
			}
		}
		return "latestkpivalue";
	}

	/**
	 * @Title: goHisPage
	 * @Description: 进入某一节点某一KPI的历史报表页面
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-6 上午11:38:12
	 */
	public String goHisPage() {
		return "kpihistory";
	}

	/**
	 * @Title: viewHisData
	 * @Description: 查看某一节点某一KPI的历史数据报表
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-6 下午3:56:38
	 */
	public void viewHisData() {
		if (obj == null) {
			obj = new HadoopTreeObj();
		}
		if (hadoopMonitorObj == null) {
			hadoopMonitorObj = new HadoopMonitorObj();
		}
		List<HadoopTreeObj> treeList = hadoopTreeService.queryForListByObj(obj);
		if (treeList.size() > 0) {
			HadoopTreeObj hadoopTreeObj = treeList.get(0);
			try {
				String clusterId = this.acquireClusterName(hadoopTreeObj, 0);
				HadoopClusterObj hadoopClusterObj = new HadoopClusterObj();
				hadoopClusterObj.setId(clusterId);
				List<HadoopClusterObj> clusterList = hadoopClusterServer
						.queryClusterList(hadoopClusterObj);
				if (clusterList != null && clusterList.size() > 0) {
					hadoopClusterObj = clusterList.get(0);
				}
				String clustername = hadoopClusterObj.getCluster_id();
				hadoopMonitorObj.setCluster_name(clustername);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (refer == null || "".equals(refer)) {
			hadoopMonitorObj.setHost_name(hadoopMonitorObj.getCluster_name());
		}

		hadoopMonitorObj.setKpi_id(obj.getKpiId());
		String tableName = null;
		if ((obj.getEndTime() == null || "".equals(obj.getEndTime()))
				&& (obj.getStartTime() == null || "".equals(obj.getStartTime()))) {
			tableName = "tb_hadoop_coll";
			obj.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			obj.setStartTime(this.getPreTenDay());
			hadoopMonitorObj.setStartTime(obj.getStartTime());
			hadoopMonitorObj.setEndTime(obj.getEndTime());
		} else {
			if (obj.getEndTime() == null || "".equals(obj.getEndTime())) {
				obj.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				// if (obj.getStartTime() == null ||
				// "".equals(obj.getStartTime())) {
				// obj.setStartTime(this.getPreTenDay());
				// }
			}
			hadoopMonitorObj.setStartTime(obj.getStartTime());
			hadoopMonitorObj.setEndTime(obj.getEndTime());
			try {
				tableName = this.acquireTableName();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		hadoopMonitorObj.setTableName(tableName);
		String chartXml = hadoopMonitorService.buildFusionChart(hadoopMonitorObj);
		this.printToScreen(chartXml);
	}

	/**
	 * @Title: acquireTableName
	 * @Description: 获取表名
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-1-10 上午9:43:25
	 */
	private String acquireTableName() throws Exception {
		try {
			String tableName = null;
			String startTime = hadoopMonitorObj.getStartTime();
			startTime.trim();
			String[] startArr = startTime.split("-");
			String third = startArr[2].substring(0, 2);
			String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			String[] dateArr = currentDate.split("-");
			String thirdDate = dateArr[2].substring(0, 2);
			if (dateArr[0].equals(startArr[0]) && dateArr[1].equals(startArr[1])
					&& thirdDate.equals(third)) {
				tableName = "tb_hadoop_coll";
			} else {
				String endTime = hadoopMonitorObj.getEndTime();
				String currentYearString = "2014";
				String currentMonthString = "01";
				if (endTime != null) {
					endTime.trim();
					String[] arr = endTime.split("-");
					currentYearString = arr[0];
					currentMonthString = arr[1];
				}
				tableName = "tb_hadoop_coll_" + currentYearString + currentMonthString;
			}
			return tableName;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("获取表名出错，错误信息：", e);
		}
	}

	/**
	 * @Title: getPreTenDay
	 * @Description: 获取10天前
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-17 下午8:15:58
	 */
	private String getPreTenDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 10);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
	}

	/**
	 * @Title: printToScreen
	 * @Description: 向屏幕打印内容
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-6 下午4:08:43
	 */
	private void printToScreen(String chartXml) {
		try {
			response.setCharacterEncoding("UTF-8");
			// PrintWriter pw = response.getWriter();
			// pw.write(chartXml);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, chartXml);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: acquireClusterName
	 * @Description: 通过服务节点获取集群名称
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-1-9 下午3:49:30
	 */
	private String acquireClusterName(HadoopTreeObj hadoopTreeObj, int count) throws Exception {
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
	 * 
	 * @Title: jvmMonitor
	 * @Description: 获取主机java虚拟机的相关指标列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-11 上午10:42:42
	 */
	public String jvmMonitor() {
		if (hadoopMonitorObj == null) {
			hadoopMonitorObj = new HadoopMonitorObj();
		}
		if (obj == null) {
			obj = new HadoopTreeObj();
		}
		HadoopHostInfoObj host = new HadoopHostInfoObj();
		host.setId(obj.getUuid());
		host = hadoopHostInfoService.queryByObj(host);
		if (host != null) {
			hadoopMonitorObj.setHost_name(host.getHost_name());
			hadoopMonitorObj.setCluster_name(host.getCluster_id());
		}
		resultList = hadoopMonitorService.queryMonitorDataForHost(hadoopMonitorObj);
		return "jvm";
	}
}
