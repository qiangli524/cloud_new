package com.sitech.ssd.ah.paas.busiResource.action.statistics;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.united.UnitedTreeService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.ssd.ah.paas.busiResource.domain.tree.PaasBusiTreeObj;
import com.sitech.ssd.ah.paas.busiResource.service.statistics.PaasBusiStatisticsService;
import com.sitech.ssd.ah.paas.domain.host.GreenPlumHostInfoObj;
import com.sitech.ssd.ah.reportform.vm.domain.VmReportForm;
import com.sitech.ssd.ah.zookeeper.domain.ZookeeperMonitorObj;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.utils.zookeeper.BossDynamicTreeMonitor;
import com.sitech.utils.zookeeper.BossDynamicTreeMonitor.StaticTreeLeafNode;
import com.sitech.vo.util.UnitedConstant;

@Controller("paasBusiStatisticsAction")
@Scope("prototype")
@SuppressWarnings("all")
public class PaasBusiStatisticsAction extends BaseAction {
	/** 打印日志 **/
	private static final org.slf4j.Logger logger = LoggerFactory
			.getLogger(PaasBusiStatisticsAction.class);
	@Autowired
	PaasBusiStatisticsService paasBusiStatisticsService;
	@Autowired
	private UnitedTreeService unitedTreeService;
	Properties p;
	List<GreenPlumHostInfoObj> hostInforesultList;// 平台物理主机集合
	List<VmReportForm> vmReportFormresultList;// 虚拟机集合
	List<PaasBusiTreeObj> systemTreeObjList = new ArrayList<PaasBusiTreeObj>();
	Map<String, List> mapResult = new HashMap<String, List>();
	PaasBusiTreeObj obj;
	private String uuid;
	private String server_type;
	private int hostsNum;// 物理主机个数
	private int vmhostsNum;// 虚拟机个数
	private int systemNum;// boss下子系统个数
	private int busiNum;// 业务数
	private int zookNormalNum = 0;// zookeeper正常数
	private int zookAbNormalNum = 0;// zookeeper异常数

	public List<PaasBusiTreeObj> getSystemTreeObjList() {
		return systemTreeObjList;
	}

	public void setSystemTreeObjList(List<PaasBusiTreeObj> systemTreeObjList) {
		this.systemTreeObjList = systemTreeObjList;
	}

	public int getVmhostsNum() {
		return vmhostsNum;
	}

	public void setVmhostsNum(int vmhostsNum) {
		this.vmhostsNum = vmhostsNum;
	}

	public int getSystemNum() {
		return systemNum;
	}

	public void setSystemNum(int systemNum) {
		this.systemNum = systemNum;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getServer_type() {
		return server_type;
	}

	public void setServer_type(String server_type) {
		this.server_type = server_type;
	}

	public Map<String, List> getMapResult() {
		return mapResult;
	}

	public void setMapResult(Map<String, List> mapResult) {
		this.mapResult = mapResult;
	}

	public int getZookNormalNum() {
		return zookNormalNum;
	}

	public void setZookNormalNum(int zookNormalNum) {
		this.zookNormalNum = zookNormalNum;
	}

	public int getZookAbNormalNum() {
		return zookAbNormalNum;
	}

	public void setZookAbNormalNum(int zookAbNormalNum) {
		this.zookAbNormalNum = zookAbNormalNum;
	}

	public List<GreenPlumHostInfoObj> getHostInforesultList() {
		return hostInforesultList;
	}

	public void setHostInforesultList(List<GreenPlumHostInfoObj> hostInforesultList) {
		this.hostInforesultList = hostInforesultList;
	}

	public int getHostsNum() {
		return hostsNum;
	}

	public void setHostsNum(int hostsNum) {
		this.hostsNum = hostsNum;
	}

	public PaasBusiTreeObj getObj() {
		return obj;
	}

	public void setObj(PaasBusiTreeObj obj) {
		this.obj = obj;
	}

	public List<VmReportForm> getVmReportFormresultList() {
		return vmReportFormresultList;
	}

	public void setVmReportFormresultList(List<VmReportForm> vmReportFormresultList) {
		this.vmReportFormresultList = vmReportFormresultList;
	}

	/**
	 * @Title: RootResourcePool
	 * @Description: 根节点摘要
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-25 下午1:18:50
	 */
	public String bossResourcePool() {
		if (obj == null) {
			obj = new PaasBusiTreeObj();
		}
		// 子系统集合
		List<PaasBusiTreeObj> systemTreeObjListOld = paasBusiStatisticsService.queryForSysList(obj);
		if (systemTreeObjListOld != null && systemTreeObjListOld.size() >= 1) {
			systemNum = systemTreeObjListOld.size();
			// 获取每个子系统的其它指标信息
			for (PaasBusiTreeObj tObj : systemTreeObjListOld) {
				int num = paasBusiStatisticsService.queryForSysBusiCount(tObj);
				tObj.setBusiNum(num);
				systemTreeObjList.add(tObj);
			}
		} else {
			systemNum = 0;
		}

		return "bossResourcePool";
	}

	/**
	 * @Title: childSysResourcePool
	 * @Description: boss节点摘要信息
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-28 上午10:43:17
	 */
	public String childSysResourcePool() {
		// 统计boss下业务数
		busiNum = paasBusiStatisticsService.queryForSysBusiCount(obj);
		// 统计boss下物理主机个数
		hostsNum = paasBusiStatisticsService.queryForSysCount(obj);
		// 统计boss下虚拟机个数
		vmhostsNum = paasBusiStatisticsService.queryForSysVmCount(obj);
		// 统计zookeeper树节点正常和异常个数
		this.zookeeperDetailTotail();
		return "childSysResourcePool";
	}

	/**
	 * @Title: businessResourcePool
	 * @Description: 业务节点摘要信息
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-30 上午11:14:45
	 */
	public String businessResourcePool() {
		if (obj == null) {
			obj = new PaasBusiTreeObj();
		}
		// 统计该业务下物理主机个数
		hostsNum = paasBusiStatisticsService.queryForBusiCount(obj);
		// 统计该业务下虚拟机个数
		vmhostsNum = paasBusiStatisticsService.queryForBusiVmCount(obj);
		return "businessResourcePool";
	}

	/**
	 * @Title: serviceResourcePool
	 * @Description: 服务节点摘要信息
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-28 下午8:22:01
	 */
	public String serviceResourcePool() {
		return "serviceResourcePool";
	}

	/**
	 * @Title: hostsList
	 * @Description: 服务节点下关联主机列表
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-29 上午8:56:23
	 */
	public String hostsList() {
		if (obj == null) {
			obj = new PaasBusiTreeObj();
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		hostInforesultList = paasBusiStatisticsService.queryHostsForService(obj);
		return "hostsList";
	}

	/**
	 * @Title: zookeeperMonDetail
	 * @Description: 返回zookeeper监控详细信息
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-29 下午3:59:00
	 */
	public String zookeeperMonDetail() {
		try {
			p = PropertiesUtil.getProperties("properties/zookeeperIP.properties");
			String CONNECTION_STRING = p.getProperty("CONNECTION_STRING");
			int SESSION_TIMEOUT = Integer.parseInt(p.getProperty("SESSION_TIMEOUT"));
			BossDynamicTreeMonitor monitor = new BossDynamicTreeMonitor(CONNECTION_STRING,
					SESSION_TIMEOUT);
			// key 指静态节点
			Map<String, List<StaticTreeLeafNode>> map = monitor.monitorDynTree();
			Set<Entry<String, List<StaticTreeLeafNode>>> entrySet = map.entrySet();
			// 通过迭代entrySet变通的迭代Map集合, Map不能直接迭代
			for (Entry<String, List<StaticTreeLeafNode>> e : entrySet) {
				String key = e.getKey();
				List<ZookeeperMonitorObj> zookMonitorList = new ArrayList<ZookeeperMonitorObj>();
				System.out.println("四级父路径：" + key);
				if (e.getValue() != null && e.getValue().size() >= 1) {
					System.out.println("子节点个数：" + e.getValue().size());
					// 迭代该父节点下的每一个五级节点
					for (StaticTreeLeafNode staicNode : e.getValue()) {
						ZookeeperMonitorObj zookMonObj = new ZookeeperMonitorObj();
						if (staicNode.getDynNodePathList() != null
								&& staicNode.getDynNodePathList().size() >= 1) {
							// 不为空，说明节点正常
							zookMonObj.setSiPath(key);
							zookMonObj.setNodeName(staicNode.getNodeName());
							zookMonObj.setFlag(true);// 该五级节点正常
						} else {
							zookMonObj.setSiPath(key);
							zookMonObj.setNodeName(staicNode.getNodeName());
							zookMonObj.setFlag(false);
						}
						zookMonitorList.add(zookMonObj);
					}
				}
				mapResult.put(key, zookMonitorList);
			}
		} catch (Exception e) {
			logger.error("监控过程出错！", e);
		}
		return "zookeeperMonDetail";
	}

	/**
	 * @Title: zookeeperDetail
	 * @Description: 统计zookeeper树节点正常和异常数
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-29 下午2:22:27
	 */
	public void zookeeperDetailTotail() {
		try {
			p = PropertiesUtil.getProperties("properties/zookeeperIP.properties");
			String CONNECTION_STRING = p.getProperty("CONNECTION_STRING");
			int SESSION_TIMEOUT = Integer.parseInt(p.getProperty("SESSION_TIMEOUT"));
			BossDynamicTreeMonitor monitor = new BossDynamicTreeMonitor(CONNECTION_STRING,
					SESSION_TIMEOUT);
			Map<String, List<StaticTreeLeafNode>> map = monitor.monitorDynTree();
			Set<Entry<String, List<StaticTreeLeafNode>>> entrySet = map.entrySet();
			// 通过迭代entrySet变通的迭代Map集合, Map不能直接迭代
			for (Entry<String, List<StaticTreeLeafNode>> e : entrySet) {
				String key = e.getKey();
				List<ZookeeperMonitorObj> zookMonitorList = new ArrayList<ZookeeperMonitorObj>();
				System.out.println("四级父路径：" + key);
				if (e.getValue() != null && e.getValue().size() >= 1) {
					System.out.println("子节点个数：" + e.getValue().size());
					// 迭代该父节点下的每一个五级节点
					int i = 0;// 正常数
					int j = 0;// 异常数
					for (StaticTreeLeafNode staicNode : e.getValue()) {
						ZookeeperMonitorObj zookMonObj = new ZookeeperMonitorObj();
						if (staicNode.getDynNodePathList() != null
								&& staicNode.getDynNodePathList().size() >= 1) {
							// 不为空，说明节点正常
							zookMonObj.setSiPath(key);
							zookMonObj.setNodeName(staicNode.getNodeName());
							zookMonObj.setFlag(true);// 该五级节点正常
							++i;
						} else {
							zookMonObj.setSiPath(key);
							zookMonObj.setNodeName(staicNode.getNodeName());
							zookMonObj.setFlag(false);
							++j;
						}
						zookMonitorList.add(zookMonObj);
					}
					zookNormalNum += i;
					zookAbNormalNum += j;
				}
			}
		} catch (Exception e) {
			logger.error("检查zookeeper节点出错！", e);
		}
	}

	/**
	 * @Title: vmHostsList
	 * @Description: 查询虚拟机列表
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-30 下午5:40:11
	 */
	public String vmHostsList() {
		if (obj == null) {
			obj = new PaasBusiTreeObj();
		}
		obj.setDataCenterList(initTreeList());
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		vmReportFormresultList = paasBusiStatisticsService.queryVmHostsForService(obj);
		return "vmHostsList";
	}

	/**
	 * @Title: initTreeList
	 * @Description: 得到数据中心列表
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-30 下午3:32:23
	 */
	private List<UnitedTreeObj> initTreeList() {
		List<UnitedTreeObj> treeList = null;
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setType(UnitedConstant.DATACENTER);
		try {
			List<UnitedTreeObj> utreeList = unitedTreeService.queryForTreeList(unitedTreeObj);
			for (UnitedTreeObj unitedTreeObj2 : utreeList) {
				if (UnitedConstant.VMWARE.equals(unitedTreeObj2.getVtype())
						|| UnitedConstant.XEN.equals(unitedTreeObj2.getVtype())) {
					if (treeList == null) {
						treeList = new ArrayList<UnitedTreeObj>();
					}
					treeList.add(unitedTreeObj2);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return treeList;
	}
}
