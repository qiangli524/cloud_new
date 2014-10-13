package com.sitech.basd.ibmmanager.web;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.ibmmanager.domain.CloudOSIndexObj;
import com.sitech.basd.ibmmanager.domain.IBMIndexObj;
import com.sitech.basd.ibmmanager.domain.IBMManagerTabObj;
import com.sitech.basd.ibmmanager.domain.IBMManagerTreeObj;
import com.sitech.basd.ibmmanager.domain.LogicalPartitionObj;
import com.sitech.basd.ibmmanager.domain.PowerObj;
import com.sitech.basd.ibmmanager.service.IBMManagerTabService;
import com.sitech.basd.ibmmanager.util.IBMConstant;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * 
 * <p>
 * Title: IBMManagerTabAction
 * </p>
 * <p>
 * Description: 相应tab页得操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2013-11-14 下午3:15:14
 * 
 */
@Controller("ibmManagerTabAction")
@Scope("prototype")
public class IBMManagerTabAction extends BaseAction {
	@Autowired
	private IBMManagerTabService ibmManagerTabService;

	private String uuid; // 当前结点的id比如：集群、主机
	private String type;
	private String vtype;
	private String id;
	private String name;
	private String parent_id;
	private String collType;// CPU=1,内存=2
	private String kpi;
	private LogicalPartitionObj lparObj;
	private PowerObj powerObj;
	private VMHostObj vmObj;
	private IBMManagerTabObj ibmManagerTabObj;
	private IBMManagerTabObj ibmUsedObj;

	private int hmcCount; // IBM 的个数
	private int clusterCount; // X86的个数
	private int powerCount; // power个数
	private int lparCount;// lpar个数
	private int hostCount;// 主机的个数
	private int vmCount; // 虚拟机的个数
	private int cloudOSCpuCount;// X86 CPU总量
	private int cloudOSCpuUsedCount;// X86 cpu已用量
	private int cloudOSCpuUnUsedCount;// x86 cpu未用量
	private String cloudOSMemCount;// X86 内存总量
	private String cloudOSMemUsedCount;// X86 内存 已用量
	private String cloudOSMemUnUsedCount;// X86 内存未用量
	private int ethCount;// 网卡个数
	private int fcCount;// 光纤卡个数
	private int pcardCount;// 物理卡的个数
	private int scsiCount;// scsi个数
	private String flag;

	/**
	 * 
	 * @Title: tabs
	 * @Description: tab页的显示
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-5 上午9:40:48
	 */
	public String tabs() {

		return "tabs";
	}

	/**
	 * 
	 * @Title: queryCount
	 * @Description: 查询各个资源个数
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-13 上午11:53:08
	 */
	public int queryCount(String type, String id) {
		int ref = 0;
		IBMManagerTreeObj obj = new IBMManagerTreeObj();
		if (type != null) {
			obj.setType(type);
		}
		if (id != null) {
			obj.setParent_id(id);
		}
		ref = ibmManagerTabService.queryCountByObj(obj);
		return ref;
	}

	/**
	 * 
	 * @Title: queryLparCount
	 * @Description: 点击hmc节点查询Lpar资源使用情况
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-13 下午12:00:30
	 */
	public int queryLparCount(String id) {
		int ref = 0;
		IBMManagerTreeObj obj = new IBMManagerTreeObj();
		obj.setParent_id(id);
		ref = ibmManagerTabService.queryLparCountByOBj(obj);
		return ref;
	}

	/**
	 * 
	 * @Title: showFirstPage
	 * @Description: IBM小型机首页
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-5 下午5:40:12
	 */
	public String showFirstPage() {
		IBMManagerTreeObj obj = new IBMManagerTreeObj();
		hmcCount = queryCount("1", null);
		powerCount = queryCount("2", null);
		lparCount = queryCount("3", null);
		clusterCount = queryCount("4", null);
		hostCount = queryCount("5", null);
		vmCount = queryCount("6", null);
		// X86
		obj.setId("2");// X86 节点是固定ID,x86ID=2
		ibmManagerTabObj = ibmManagerTabService.queryAllResource(obj);
		ibmUsedObj = ibmManagerTabService.queryUsedResource(obj);
		queryResources(ibmManagerTabObj, ibmUsedObj);
		// IBM小型机
		ibmManagerTabObj = ibmManagerTabService.queryIBMHmcResource(obj);
		return "showFirstPage";
	}

	/**
	 * 
	 * @Title: showMinPage
	 * @Description: 小型机页
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-11 下午2:55:30
	 */
	public String showMinPage() {
		IBMManagerTreeObj obj = new IBMManagerTreeObj();
		hmcCount = queryCount("1", null);
		powerCount = queryCount("2", null);
		lparCount = queryCount("3", null);
		ibmManagerTabObj = ibmManagerTabService.queryIBMHmcResource(obj);
		return "showMinPage";
	}

	/**
	 * 
	 * @Title: showCloudOSPage
	 * @Description: X86页
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-11 下午2:56:22
	 */
	public String showCloudOSPage() {
		IBMManagerTreeObj obj = new IBMManagerTreeObj();
		clusterCount = queryCount("4", null);
		hostCount = queryCount("5", null);
		vmCount = queryCount("6", null);
		obj.setId(id);
		ibmManagerTabObj = ibmManagerTabService.queryAllResource(obj);
		ibmUsedObj = ibmManagerTabService.queryUsedResource(obj);
		queryResources(ibmManagerTabObj, ibmUsedObj);
		return "showCloudOSPage";
	}

	/**
	 * 
	 * @Title: showHMCPage
	 * @Description: 展示HMC摘要
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-5 下午5:40:50
	 */
	public String showHMCPage() {
		IBMManagerTreeObj obj = new IBMManagerTreeObj();
		powerCount = queryCount("2", id);
		lparCount = queryLparCount(id);
		obj.setUuid(uuid);
		ibmManagerTabObj = ibmManagerTabService.queryIBMHmcResource(obj);
		return "showHMCPage";
	}

	/**
	 * 
	 * @Title: powerInfo
	 * @Description: power摘要
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-5 下午4:22:54
	 */
	public String powerInfo() {
		IBMManagerTreeObj obj = new IBMManagerTreeObj();
		lparCount = queryCount("3", id);
		obj.setUuid(uuid);
		ibmManagerTabObj = ibmManagerTabService.queryIBMPowerResource(obj);
		LogicalPartitionObj lparObj = new LogicalPartitionObj();
		lparObj.setMachineserialnumber(uuid);
		lparObj.setTableName("SX_LOGICAL_PARTITION_ETH");
		ethCount = ibmManagerTabService.queryLparIndexCount(lparObj);// 网卡个数
		lparObj.setTableName("SX_LOGICAL_PARTITION_FC");
		fcCount = ibmManagerTabService.queryLparIndexCount(lparObj);// 光纤卡个数
		lparObj.setTableName("SX_LOGICAL_PARTITION_PCARD");
		pcardCount = ibmManagerTabService.queryLparIndexCount(lparObj);// 物理卡的个数
		lparObj.setTableName("SX_LOGICAL_PARTITION_SCSI");
		scsiCount = ibmManagerTabService.queryLparIndexCount(lparObj);// scsi个数
		return "powerInfo";

	}

	/**
	 * 
	 * @Title: lparInfo
	 * @Description: lpar摘要
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-5 下午3:51:12
	 */
	public String lparInfo() {
		LogicalPartitionObj obj = new LogicalPartitionObj();
		obj = ibmManagerTabService.splitLparUuid(uuid);
		lparObj = ibmManagerTabService.queryLparInfo(obj);
		obj.setTableName("SX_LOGICAL_PARTITION_ETH");
		ethCount = ibmManagerTabService.queryLparIndexCount(obj);// 网卡个数
		obj.setTableName("SX_LOGICAL_PARTITION_FC");
		fcCount = ibmManagerTabService.queryLparIndexCount(obj);// 光纤卡个数
		obj.setTableName("SX_LOGICAL_PARTITION_PCARD");
		pcardCount = ibmManagerTabService.queryLparIndexCount(obj);// 物理卡的个数
		obj.setTableName("SX_LOGICAL_PARTITION_SCSI");
		scsiCount = ibmManagerTabService.queryLparIndexCount(obj);// scsi个数
		return "lparInfo";
	}

	/**
	 * 
	 * @Title: showCpuView
	 * @Description: 展示CPU视图
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-5 上午10:55:19
	 */
	public String showCpuView() {
		return "showCpuView";
	}

	/**
	 * 
	 * @Title: showMemView
	 * @Description: 展示内存视图
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-5 上午10:55:24
	 */
	public String showMemView() {
		return "showMemView";
	}

	/**
	 * 
	 * @Title: showClusterInfo
	 * @Description: 集群摘要
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-8 下午5:08:14
	 */
	public String showClusterInfo() {
		IBMManagerTreeObj obj = new IBMManagerTreeObj();
		hostCount = queryCount("5", id);
		vmCount = queryLparCount(id);
		obj.setId(id);
		ibmManagerTabObj = ibmManagerTabService.queryClusterAllResource(obj);
		ibmUsedObj = ibmManagerTabService.queryClusterUsedResource(obj);
		queryResources(ibmManagerTabObj, ibmUsedObj);
		return "showClusterInfo";
	}

	/**
	 * 
	 * @Title: showHostInfo
	 * @Description: 主机摘要
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-8 下午5:08:34
	 */
	public String showHostInfo() {
		IBMManagerTreeObj obj = new IBMManagerTreeObj();
		vmCount = queryCount("6", id);
		obj.setUuid(uuid);
		ibmManagerTabObj = ibmManagerTabService.queryHostAllResource(obj);
		ibmUsedObj = ibmManagerTabService.queryHostUsedResource(obj);
		queryResources(ibmManagerTabObj, ibmUsedObj);
		return "showHostInfo";
	}

	/**
	 * 
	 * @Title: showVMInfo
	 * @Description: 虚拟机机摘要
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-8 下午5:12:34
	 */
	public String showVMInfo() {
		VMHostObj obj = new VMHostObj();
		obj.setVH_UUID(uuid);
		vmObj = ibmManagerTabService.queryVMInfo(obj);
		return "showVMInfo";
	}

	/**
	 * 
	 * @Title: queryResources
	 * @Description: 查询X86资源
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-13 下午5:26:08
	 */
	public void queryResources(IBMManagerTabObj a, IBMManagerTabObj b) {
		DecimalFormat df = new DecimalFormat(".00");
		cloudOSCpuCount = a.getCloudOSCpuCount();// X86 CPU总量
		cloudOSCpuUsedCount = b.getCloudOSCpuUsedCount();// X86 cpu已用量
		cloudOSCpuUnUsedCount = cloudOSCpuCount - cloudOSCpuUsedCount;// x86cpu未用量

		cloudOSMemCount = df.format(a.getCloudOSMemCount() / 1024);// X86 内存总量
		cloudOSMemUsedCount = df.format(b.getCloudOSMemUsedCount() / 1024);// X86内存已用量
		cloudOSMemUnUsedCount = df
				.format((a.getCloudOSMemCount() - b.getCloudOSMemUsedCount()) / 1024);// X86内存未用量
	}

	/**
	 * 
	 * @Title: showHostFCSpeed
	 * @Description: 展示光纤卡流量信息
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-15 上午9:47:44
	 */
	public String showPowerFCSpeed() {
		return "showPowerFCSpeed";
	}

	/**
	 * 
	 * @Title: showPowerSEASpeed
	 * @Description: 展示Power网络性能信息
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-16 下午8:33:13
	 */
	public String showPowerSEASpeed() {
		return "showPowerSEASpeed";
	}

	/**
	 * 
	 * @Title: showPowerNetCardPerformance
	 * @Description: 展现Power网卡流量监控
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-16 下午8:38:48
	 */
	public String showPowerNetCardPerformance() {
		return "showPowerNetCardPerformance";
	}

	/**
	 * 
	 * @Title: showLparFCSpeed
	 * @Description: 展示Lpar光纤卡流量信息
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-15 上午9:47:44
	 */
	public String showLparFCSpeed() {
		return "showLparFCSpeed";
	}

	/**
	 * 
	 * @Title: showLparDiskSpeed
	 * @Description: 展示Lpar磁盘的读写速度
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-16 下午8:42:32
	 */
	public String showLparDiskSpeed() {
		return "showLparDiskSpeed";
	}

	/**
	 * 
	 * @Title: showLparNetCardPerformance
	 * @Description: 展示Lpar网卡流量监控
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-16 下午8:43:14
	 */
	public String showLparNetCardPerformance() {
		return "showLparNetCardPerformance";
	}

	/**
	 * 
	 * @Title: queryIBMIndex
	 * @Description: 查询IBM指标信息
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-15 上午9:50:01
	 */
	public String queryIBMIndex() {
		IBMIndexObj obj = new IBMIndexObj();
		obj.setFlag(flag);
		obj.setMachine_serial_number(uuid);
		FusionCharts fusionCharts = new FusionCharts();
		try {
			fusionCharts = ibmManagerTabService.queryIBMIndex(obj);
		} catch (Exception e) {
			LOG.error("查询失败", e.getMessage() + e);
			fusionCharts = initQueryErrorCharts();
		}
		String fuString = JacksonUtil.toJson(fusionCharts);
		printJson(fuString);
		return null;
	}

	/**
	 * 
	 * @Title: showIOSpeed
	 * @Description: 存储监控指标
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-19 下午6:00:30
	 */
	public String showIOSpeed() {
		return "showIOSpeed";
	}

	/**
	 * 
	 * @Title: showNetSpeed
	 * @Description: 网络监控指标
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-19 下午6:01:02
	 */
	public String showNetSpeed() {
		return "showNetSpeed";
	}

	/**
	 * 
	 * @Title: queryIOSpeed
	 * @Description: 查询存储监控指标
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-19 下午6:47:05
	 */
	public String queryIOSpeed() {
		CloudOSIndexObj obj = new CloudOSIndexObj();
		if (IBMConstant.HOST.equals(type)) {
			obj.setEq_id(uuid);
		} else if (IBMConstant.VM.equals(type)) {
			obj.setHy_id(uuid);
		}
		obj.setKpi_id(kpi);
		obj.setType(type);
		FusionCharts fusionCharts = new FusionCharts();
		try {
			fusionCharts = ibmManagerTabService.queryIOSpeed(obj);
		} catch (Exception e) {
			LOG.error("查询失败", e.getMessage() + e);
			fusionCharts = initQueryErrorCharts();
		}
		String fuString = JacksonUtil.toJson(fusionCharts);
		printJson(fuString);
		return null;
	}

	/**
	 * 
	 * @Title: queryNetSpeed
	 * @Description: 查询网络监控指标
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-19 下午7:45:34
	 */
	public String queryNetSpeed() {
		CloudOSIndexObj obj = new CloudOSIndexObj();
		if (IBMConstant.HOST.equals(type)) {
			obj.setEq_id(uuid);
		} else if (IBMConstant.VM.equals(type)) {
			obj.setHy_id(uuid);
		}
		obj.setKpi_id(kpi);
		obj.setType(type);
		FusionCharts fusionCharts = new FusionCharts();
		try {
			fusionCharts = ibmManagerTabService.queryNetSpeed(obj);
		} catch (Exception e) {
			LOG.error("查询失败", e.getMessage() + e);
			fusionCharts = initQueryErrorCharts();
		}
		String fuString = JacksonUtil.toJson(fusionCharts);
		printJson(fuString);
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
	 * @createtime 2013-9-14 上午10:41:00
	 */
	private synchronized void printJson(String... params) {
		response.setCharacterEncoding("UTF-8");
		// out = response.getWriter();
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				// out.println(params[i]);
				PrintWriterOut.printWirter(response, params[i]);
			}
		}
		// out.close();
	}

	/**
	 * 
	 * @Title: initQueryErrorCharts
	 * @Description: 当查询失败时，显示报表
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-14 上午9:45:04
	 */
	private FusionCharts initQueryErrorCharts() {
		FusionCharts fusionCharts = new FusionCharts();
		return fusionCharts;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVtype() {
		return vtype;
	}

	public void setVtype(String vtype) {
		this.vtype = vtype;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getCollType() {
		return collType;
	}

	public void setCollType(String collType) {
		this.collType = collType;
	}

	public LogicalPartitionObj getLparObj() {
		return lparObj;
	}

	public void setLparObj(LogicalPartitionObj lparObj) {
		this.lparObj = lparObj;
	}

	public PowerObj getPowerObj() {
		return powerObj;
	}

	public void setPowerObj(PowerObj powerObj) {
		this.powerObj = powerObj;
	}

	public VMHostObj getVmObj() {
		return vmObj;
	}

	public void setVmObj(VMHostObj vmObj) {
		this.vmObj = vmObj;
	}

	public IBMManagerTabService getIbmManagerTabService() {
		return ibmManagerTabService;
	}

	public void setIbmManagerTabService(IBMManagerTabService ibmManagerTabService) {
		this.ibmManagerTabService = ibmManagerTabService;
	}

	public int getHmcCount() {
		return hmcCount;
	}

	public void setHmcCount(int hmcCount) {
		this.hmcCount = hmcCount;
	}

	public int getClusterCount() {
		return clusterCount;
	}

	public void setClusterCount(int clusterCount) {
		this.clusterCount = clusterCount;
	}

	public int getPowerCount() {
		return powerCount;
	}

	public void setPowerCount(int powerCount) {
		this.powerCount = powerCount;
	}

	public int getLparCount() {
		return lparCount;
	}

	public void setLparCount(int lparCount) {
		this.lparCount = lparCount;
	}

	public int getHostCount() {
		return hostCount;
	}

	public void setHostCount(int hostCount) {
		this.hostCount = hostCount;
	}

	public int getVmCount() {
		return vmCount;
	}

	public void setVmCount(int vmCount) {
		this.vmCount = vmCount;
	}

	public IBMManagerTabObj getIbmManagerTabObj() {
		return ibmManagerTabObj;
	}

	public void setIbmManagerTabObj(IBMManagerTabObj ibmManagerTabObj) {
		this.ibmManagerTabObj = ibmManagerTabObj;
	}

	public IBMManagerTabObj getIbmUsedObj() {
		return ibmUsedObj;
	}

	public void setIbmUsedObj(IBMManagerTabObj ibmUsedObj) {
		this.ibmUsedObj = ibmUsedObj;
	}

	public String getCloudOSMemCount() {
		return cloudOSMemCount;
	}

	public void setCloudOSMemCount(String cloudOSMemCount) {
		this.cloudOSMemCount = cloudOSMemCount;
	}

	public String getCloudOSMemUsedCount() {
		return cloudOSMemUsedCount;
	}

	public void setCloudOSMemUsedCount(String cloudOSMemUsedCount) {
		this.cloudOSMemUsedCount = cloudOSMemUsedCount;
	}

	public String getCloudOSMemUnUsedCount() {
		return cloudOSMemUnUsedCount;
	}

	public void setCloudOSMemUnUsedCount(String cloudOSMemUnUsedCount) {
		this.cloudOSMemUnUsedCount = cloudOSMemUnUsedCount;
	}

	public int getFcCount() {
		return fcCount;
	}

	public void setFcCount(int fcCount) {
		this.fcCount = fcCount;
	}

	public int getEthCount() {
		return ethCount;
	}

	public void setEthCount(int ethCount) {
		this.ethCount = ethCount;
	}

	public int getScsiCount() {
		return scsiCount;
	}

	public void setScsiCount(int scsiCount) {
		this.scsiCount = scsiCount;
	}

	public int getPcardCount() {
		return pcardCount;
	}

	public void setPcardCount(int pcardCount) {
		this.pcardCount = pcardCount;
	}

	public int getCloudOSCpuCount() {
		return cloudOSCpuCount;
	}

	public void setCloudOSCpuCount(int cloudOSCpuCount) {
		this.cloudOSCpuCount = cloudOSCpuCount;
	}

	public int getCloudOSCpuUsedCount() {
		return cloudOSCpuUsedCount;
	}

	public void setCloudOSCpuUsedCount(int cloudOSCpuUsedCount) {
		this.cloudOSCpuUsedCount = cloudOSCpuUsedCount;
	}

	public int getCloudOSCpuUnUsedCount() {
		return cloudOSCpuUnUsedCount;
	}

	public void setCloudOSCpuUnUsedCount(int cloudOSCpuUnUsedCount) {
		this.cloudOSCpuUnUsedCount = cloudOSCpuUnUsedCount;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getKpi() {
		return kpi;
	}

	public void setKpi(String kpi) {
		this.kpi = kpi;
	}

}
