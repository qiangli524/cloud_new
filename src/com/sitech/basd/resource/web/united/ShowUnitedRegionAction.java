package com.sitech.basd.resource.web.united;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.resource.domain.global.HostGlobalObj;
import com.sitech.basd.resource.domain.global.VmGlobalObj;
import com.sitech.basd.resource.domain.united.CMSObj;
import com.sitech.basd.resource.domain.united.ResourceStatisticsObj;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.global.HostGlobalService;
import com.sitech.basd.resource.service.global.VmGlobalService;
import com.sitech.basd.resource.service.united.ResourceStatisticsService;
import com.sitech.basd.resource.service.united.UnitedTreeService;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.net.IpService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.AlarmHostStatistics;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.MyComparator;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.basd.yicloud.domain.datastore.StoreDeviceObj;
import com.sitech.basd.yicloud.domain.xenstore.XenStoreObj;
import com.sitech.basd.yicloud.service.datastore.DataStoreService;
import com.sitech.basd.yicloud.service.datastore.StoreDeviceService;
import com.sitech.basd.yicloud.service.xenstore.XenStoreService;
import com.sitech.ssd.ah.nas.domain.NasKpiObj;
import com.sitech.ssd.ah.nas.domain.NasStorePoolObj;
import com.sitech.ssd.ah.nas.service.NasKpiService;
import com.sitech.ssd.ah.nas.service.NasStorePoolService;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.vo.united.HostSystemConnectStatus;
import com.sitech.vo.united.VirtualMachineConnectStatus;
import com.sitech.vo.util.UnitedConstant;

/**
 * <p>
 * Title: ShowUnitedRegionAction
 * </p>
 * <p>
 * Description: 统一树首页展示
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-8-11 上午9:52:15
 * 
 */
@SuppressWarnings("all")
@Controller("showUnitedRegionAction")
@Scope("prototype")
public class ShowUnitedRegionAction extends BaseAction {
	@Autowired
	private ResourceStatisticsService resourceStatisticsService;
	@Autowired
	private UnitedTreeService unitedTreeService;
	@Autowired
	private HostInfoService hostInfoService;
	@Autowired
	private VMHostService vmHostService;
	@Autowired
	private DataStoreService dataStoreService;
	@Autowired
	private XenStoreService xenStoreService;
	@Autowired
	private IpService ipService;
	@Autowired
	private StoreDeviceService storeDeviceService;
	@Autowired
	private HostGlobalService hostGlobalService;
	@Autowired
	private VmGlobalService vmGlobalService;
	@Autowired
	private NasKpiService nasKpiService;
	@Autowired
	private NasStorePoolService nasStorePoolService;
	// @Autowired
	// private ShowResourceService showResourceService;

	private List<ResourceStatisticsObj> resultList;

	private List<UnitedTreeObj> ulist;

	private String id;

	private String uuid;

	private String connect_id;

	private String type;

	private String vtype;

	private int clucount;

	private int vmwareclucount;

	private int xenclucount;

	private int hostcount;

	private int vmcount;

	private int hostruncount;

	private int hoststopcount;

	private int x86hostcount;

	private int framehostcount;

	private int vmwarecount;

	private int xencount;

	private int vmwareruncount;

	private int vmwarestopcount;

	private int xenruncount;

	private int xenstopcount;

	private int cpuallcount;

	private int cpuusedcount;

	private double memallcount;

	private double memusedcount;

	private double srallcount;

	private double srusedcount;

	private int netcount;

	private int domaincount;

	private int subdomaincount;

	private int vlancount;

	private int ipcount;

	private int ipusedcount;

	private int hostseriouscount;

	private int vmwareseriouscount;

	private int xenseriouscount;

	private List ipList;

	private List<DataStoreObj> storeList;

	private List<AlarmHostStatistics> netList;

	private DataStoreObj dataStoreObj;

	private Integer allo;// 是否已分配

	private List<TbCloud2HostInfoObj> hostList;
	
	private TbCloud2HostInfoObj hostDetail;

	private List<VMHostObj> vmList;

	private String cluid;

	private String centerid;

	private String hostid;

	private String cluuuid;

	private int localdevice;

	private int SANdevice;

	private int NASdevice;

	private StoreDeviceObj storeDevice;

	private int cpuallocount;// 已分配cpu个数
	private double memallocount;// 已分配内存大小
	private double srallocount;// 已分配存储大小

	private String storemeasuretype;// 存储计量维度 san存储 分布式存储等

	private int centercount;// 数据中心个数

	private int unalloclucount;// 未分配集群个数

	private int hostallo;// 已分配主机个数

	private int hostunallo;// 未分配主机个数

	private int hostunalloruncount;// 未分配主机运行个数

	private int hostunallostopcount;// 未分配主机停止数

	private int hostunalloseriouscount;// 未分配主机异常个数

	private int framehostunallocount;// 未分配主机机架个数

	private int x86hostunallocount;// 未分配主机x86刀片个数

	private List<String> parentHostList;// 主机uuid集合

	private List<String> clusterList;// 集群id或uuid

	private List<String> unalloclusterList;// 未分配集群，用于地域

	private List<NasStorePoolObj> nasStorePoolList;// nas摘要实体--nas存储池

	private NasKpiObj nasKpiObj;// nas摘要实体--kpi

	private String status;//

	private String eq_type;//

	public StoreDeviceService getStoreDeviceService() {
		return storeDeviceService;
	}

	public void setStoreDeviceService(StoreDeviceService storeDeviceService) {
		this.storeDeviceService = storeDeviceService;
	}

	private Map<String, String> map = new HashMap<String, String>();

	/**
	 * @Title: 四舍五入,保留两位小数
	 * @Description:
	 * @param
	 * @return double
	 * @throws
	 * @author yanggl_bj
	 * @version 1.0
	 * @createtime 2013-8-13
	 */
	private double convert(double value) {
		long a = Math.round(value * 100);
		double ret = a / 100.0;
		return ret;
	}

	/**
	 * @Title: showFirstPage
	 * @Description: 展示统一树的首页统计信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-12 下午2:19:07
	 */
	public String showFirstPage() {
		ResourceStatisticsObj obj = new ResourceStatisticsObj();
		resultList = resourceStatisticsService.queryForListByObj(obj);

		if (resultList != null && resultList.size() > 0) {
			for (ResourceStatisticsObj rsObj : resultList) {
				if (rsObj.getKey().equals("storage_valid_mb")
						|| rsObj.getKey().equals("storage_mount_mb")
						|| rsObj.getKey().equals("storage_allo_mb")
						|| rsObj.getKey().equals("storage_used_mb")) {
					String sam = rsObj.getValue();
					double a = Double.parseDouble(sam);
					String b = convert(a / 1024 / 1024) + "";
					rsObj.setValue(b);
				} else if (rsObj.getKey().equals("mem_all_mb")
						|| rsObj.getKey().equals("mem_used_mb")
						|| rsObj.getKey().equals("mem_allo_mb")) {
					String sam = rsObj.getValue();
					double a = Double.parseDouble(sam);
					String b = convert(a / 1024) + "";
					rsObj.setValue(b);
				}
				map.put(rsObj.getKey(), rsObj.getValue());
			}
		}
		return "showfirstpage";
	}
	
	
	/**
	 * @Title: showResourceTotal
	 * @Description: 展示全部资源数目
	 * @param
	 * @return String
	 * @throws
	 * @author zhoucang
	 * @version 1.0
	 * @createtime 2014-8-15 下午2:19:07
	 */
	public String showResourceTotal() {
		ResourceStatisticsObj obj = new ResourceStatisticsObj();
		resultList = resourceStatisticsService.queryForListByObj(obj);

		if (resultList != null && resultList.size() > 0) {
			for (ResourceStatisticsObj rsObj : resultList) {
				if (rsObj.getKey().equals("storage_valid_mb")
						|| rsObj.getKey().equals("storage_mount_mb")
						|| rsObj.getKey().equals("storage_allo_mb")
						|| rsObj.getKey().equals("storage_used_mb")) {
					String sam = rsObj.getValue();
					double a = Double.parseDouble(sam);
					String b = convert(a / 1024 / 1024) + "";
					rsObj.setValue(b);
				} else if (rsObj.getKey().equals("mem_all_mb")
						|| rsObj.getKey().equals("mem_used_mb")
						|| rsObj.getKey().equals("mem_allo_mb")) {
					String sam = rsObj.getValue();
					double a = Double.parseDouble(sam);
					String b = convert(a / 1024) + "";
					rsObj.setValue(b);
				}
				map.put(rsObj.getKey(), rsObj.getValue());
			}
		}
		return "showResourceTotal";
	}

	/**
	 * @Title: queryForUnVirHost
	 * @Description: 统计首页tab非虚拟化主机信息
	 * @param
	 * @return Map<String, String>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-0 pm 21:37
	 */
	public String showUnVirtual() {
		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		obj.setHasvertual("0"); // 非虚拟化主机的暂定5用来测试 ，实际上为1待修改
		map = resourceStatisticsService.queryForUnVirHost(obj);
		return "showUnVirtual";
	}

	/**
	 * @Title: ShowDataCenterInfo
	 * @Description: 展示数据中心信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-9 下午8:42:42
	 */
	// public String showDataCenterInfo() {
	// List<UnitedTreeObj> treeList = new ArrayList<UnitedTreeObj>();
	// UnitedTreeObj centerObj = new UnitedTreeObj();
	// centerObj.setId(id);
	// try {
	// centerObj = unitedTreeService.queryByObj(centerObj);
	// centerid = centerObj.getUuid();
	// connect_id = centerObj.getConnect_id();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// String vtyper = centerObj.getVtype();
	//
	// /*--------------------统计集群个数-------------------*/
	// UnitedTreeObj cluUnitedTreeObj = new UnitedTreeObj();
	// List<String> pidList = new ArrayList<String>();
	// pidList.add(id);
	// cluUnitedTreeObj.setPidlist(pidList);
	// cluUnitedTreeObj.setType(UnitedConstant.CLUSTER);
	// if (pidList.size() > 0) {
	// uList = unitedTreeService.queryForTreeListUseIn(cluUnitedTreeObj);
	// clucount = uList.size();
	// treeList.addAll(uList);
	//
	// cluUnitedTreeObj.setVtype(UnitedConstant.VMWARE);
	// uList = unitedTreeService.queryForTreeListUseIn(cluUnitedTreeObj);
	// vmwareclucount = uList.size();
	//
	// cluUnitedTreeObj.setVtype(UnitedConstant.XEN);
	// uList = unitedTreeService.queryForTreeListUseIn(cluUnitedTreeObj);
	// xenclucount = uList.size();
	//
	// } else {
	// clucount = 0;
	// vmwareclucount = 0;
	// xenclucount = 0;
	// }
	//
	// /*--------------------统计主机个数-------------------*/
	// List<UnitedTreeObj> hostList = this.attchiveHost(treeList, vtyper);
	//
	// /*--------------------统计虚拟机个数-------------------*/
	// this.attchiveVM(hostList, vtyper);
	//
	// return "datacenterpage";
	// }
	//
	// /**
	// * @Title: attchiveHost
	// * @Description: 获取主机信息
	// * @param
	// * @return Map<String,Integer>
	// * @throws
	// * @author lipengpeng
	// * @version 1.0
	// * @createtime 2013-9-9 下午9:47:26
	// */
	// private List<UnitedTreeObj> attchiveHost(List<UnitedTreeObj> list,
	// String vtyper) {
	// List<String> pidList = new ArrayList<String>();
	// UnitedTreeObj hostUnitedTreeObj = new UnitedTreeObj();
	// for (UnitedTreeObj unitedTreeObj : list) {
	// pidList.add(unitedTreeObj.getId());
	// }
	// hostUnitedTreeObj.setPidlist(pidList);
	// hostUnitedTreeObj.setType(UnitedConstant.HOST);
	// if (pidList.size() > 0) {
	// uList = unitedTreeService.queryForTreeListUseIn(hostUnitedTreeObj);
	// } else {
	// uList = new ArrayList<UnitedTreeObj>();
	// }
	// hostcount = uList.size();
	//
	// pidList.clear();
	// for (UnitedTreeObj obj : uList) {
	// pidList.add(obj.getUuid());
	// }
	// TbCloud2HostInfoObj hostInfoObj = new TbCloud2HostInfoObj();
	// hostInfoObj.setSTATUS("1");
	// hostInfoObj.setUuidList(pidList);
	// List<TbCloud2HostInfoObj> hostList = new
	// ArrayList<TbCloud2HostInfoObj>();
	// if (pidList.size() > 0) {
	// if (UnitedConstant.UNALLOCATED.equals(vtyper)) {// 未分配的
	// hostList = hostInfoService
	// .queryHostListUseInByEqid(hostInfoObj);
	// } else if (UnitedConstant.UNVIRTUAL.equals(vtyper)) {// 非虚拟化的
	// hostList = hostInfoService
	// .queryHostListUseInByEqid(hostInfoObj);
	// } else {// 已分配的
	// hostInfoObj.setConnectStatus(HostSystemConnectStatus.connected);
	// if (UnitedConstant.VMWARE.equals(vtyper)) {// VMWARE
	// hostInfoObj.setConnectId(connect_id);
	// }
	// hostList = hostInfoService.queryHostListUseIn(hostInfoObj);
	// }
	// }
	// hostruncount = hostList.size();
	//
	// hostInfoObj.setSTATUS("2");
	// hostInfoObj.setUuidList(pidList);
	// if (pidList.size() > 0) {
	// if (UnitedConstant.UNALLOCATED.equals(vtyper)) {// 未分配的
	// hostList = hostInfoService
	// .queryHostListUseInByEqid(hostInfoObj);
	// } else if (UnitedConstant.UNVIRTUAL.equals(vtyper)) {// 非虚拟化的
	// hostList = hostInfoService
	// .queryHostListUseInByEqid(hostInfoObj);
	// } else {// 已分配的
	// hostInfoObj.setConnectStatus(HostSystemConnectStatus.connected);
	// if (UnitedConstant.VMWARE.equals(vtyper)) {// VMWARE
	// hostInfoObj.setConnectId(connect_id);
	// }
	// hostList = hostInfoService.queryHostListUseIn(hostInfoObj);
	// }
	// }
	// hoststopcount = hostList.size();
	//
	// TbCloud2HostInfoObj thObj = new TbCloud2HostInfoObj();
	// if (pidList.size() > 0) {
	// thObj.setUuidList(pidList);
	// if (UnitedConstant.UNALLOCATED.equals(vtyper)) {// 未分配的
	// hostList = hostInfoService
	// .querySeriousHostListUseInUnAllo(thObj);
	// hostseriouscount = hostList.size();
	// } else if (UnitedConstant.UNVIRTUAL.equals(vtyper)) {// 非虚拟化的
	// hostseriouscount = 0;
	// } else {// 已分配的
	// if (UnitedConstant.VMWARE.equals(vtyper)) {// VMWARE
	// thObj.setConnectId(connect_id);
	// }
	// hostList = hostInfoService.querySeriousHostListUseIn(thObj);
	// hostseriouscount = hostList.size();
	// }
	// }
	//
	// TbCloud2HostInfoObj infoObj = new TbCloud2HostInfoObj();
	// infoObj.setEq_type("5");
	// infoObj.setUuidList(pidList);
	// if (pidList.size() > 0) {
	// if (UnitedConstant.UNALLOCATED.equals(vtyper)) {// 未分配的
	// hostList = hostInfoService.queryHostListUseInByEqid(infoObj);
	// } else if (UnitedConstant.UNVIRTUAL.equals(vtyper)) {// 非虚拟化的
	// hostList = hostInfoService.queryHostListUseInByEqid(infoObj);
	// } else {// 已分配的
	// if (UnitedConstant.VMWARE.equals(vtyper)) {
	// infoObj.setConnectId(connect_id);
	// }
	// hostList = hostInfoService.queryHostListUseIn(infoObj);
	// }
	// }
	// framehostcount = hostList.size();
	//
	// infoObj.setEq_type("4");
	// if (pidList.size() > 0) {
	// if (UnitedConstant.UNALLOCATED.equals(vtyper)) {// 未分配的
	// hostList = hostInfoService.queryHostListUseInByEqid(infoObj);
	// } else if (UnitedConstant.UNVIRTUAL.equals(vtyper)) {// 非虚拟化的
	// hostList = hostInfoService.queryHostListUseInByEqid(infoObj);
	// } else {// 已分配的
	// if (UnitedConstant.VMWARE.equals(vtyper)) {
	// infoObj.setConnectId(connect_id);
	// }
	// hostList = hostInfoService.queryHostListUseIn(infoObj);
	// }
	// }
	// x86hostcount = hostList.size();
	//
	// // 统计cpu、内存、存储等信息
	// TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
	// hostObj.setUuidList(pidList);
	// CMSObj cmsObj = new CMSObj();
	// if (pidList.size() > 0) {
	// if (UnitedConstant.UNALLOCATED.equals(vtyper)) {// 未分配的
	// cmsObj = hostInfoService.countResourceByEqid(hostObj);
	// } else if (UnitedConstant.UNVIRTUAL.equals(vtyper)) {// 非虚拟化的
	// cmsObj = hostInfoService.countResourceByEqid(hostObj);
	// } else {// 已分配的
	// if (UnitedConstant.VMWARE.equals(vtyper)) {
	// hostObj.setConnectId(connect_id);
	// }
	// cmsObj = hostInfoService.countResource(hostObj);
	// }
	// }
	// cpuallcount = cmsObj.getAllcpu() == null ? 0 : cmsObj.getAllcpu();
	// memallcount = cmsObj.getAllmem() == null ? 0 : convert(cmsObj
	// .getAllmem() * 1.0 / 1024);
	// cpuallocount = cmsObj.getAllocpu() == null ? 0 : cmsObj.getAllocpu();
	// memallocount = cmsObj.getAllomem() == null ? 0 : convert(cmsObj
	// .getAllomem() * 1.0 / 1024);
	// cpuusedcount = cmsObj.getUsedcpu() == null ? 0 : cmsObj.getUsedcpu();
	// memusedcount = cmsObj.getUsedmem() == null ? 0 : convert(cmsObj
	// .getUsedmem() * 1.0 / 1024);
	//
	// if (UnitedConstant.UNALLOCATED.equals(vtyper)) {// 未分配的
	// srallcount = cmsObj.getAllsr() == null
	// ? 0
	// : cmsObj.getAllsr() * 1.0 / 1024 / 1024;
	// srusedcount = 0;
	// srallocount = 0;
	// } else if (UnitedConstant.UNVIRTUAL.equals(vtyper)) {// 非虚拟化的
	// srallcount = cmsObj.getAllsr() == null
	// ? 0
	// : cmsObj.getAllsr() * 1.0 / 1024 / 1024;
	// srusedcount = 0;
	// srallocount = 0;
	// } else {// 已分配的
	// // vmware
	// DataStoreObj dataStoreObj = new DataStoreObj();
	// dataStoreObj.setHostidlist(pidList);
	// if (UnitedConstant.VMWARE.equals(vtyper)) {
	// dataStoreObj.setConnectId(connect_id);
	// }
	// if (pidList.size() > 0) {
	// cmsObj = dataStoreService.countResource(dataStoreObj);
	// }
	// double vmwaresrallcount = cmsObj.getAllsr() == null
	// ? 0
	// : convert(cmsObj.getAllsr() * 1.0 / 1024 / 1024);
	// double freestore = cmsObj.getUsedsr() == null ? 0 : convert(cmsObj
	// .getUsedsr() * 1.0 / 1024 / 1024);
	// double pursrcount = cmsObj.getAllosr() == null ? 0 : convert(cmsObj
	// .getAllosr() * 1.0 / 1024 / 1024);
	// double vmwaresrusedcount = vmwaresrallcount - freestore;
	//
	// XenStoreObj xenStoreObj = new XenStoreObj();
	// xenStoreObj.setHostidlist(pidList);
	// if (pidList.size() > 0) {
	// cmsObj = xenStoreService.countResource(xenStoreObj);
	// }
	// double xensrallcount = cmsObj.getAllsr() == null
	// ? 0
	// : convert(cmsObj.getAllsr() / 1024 / 1024);
	// double freestorexen = cmsObj.getUsedsr() == null
	// ? 0
	// : convert(cmsObj.getUsedsr() / 1024 / 1024);
	// double xensrusedcount = xensrallcount - freestorexen;
	//
	// srallcount = convert(vmwaresrallcount + xensrallcount);
	// srusedcount = convert(vmwaresrusedcount + xensrusedcount);
	// srallocount = convert(pursrcount + xensrusedcount);
	// }
	// return uList;
	// }
	//
	// /**
	// * @Title: attchiveVM
	// * @Description: 获取虚拟机信息
	// * @param
	// * @return Map<String,Integer>
	// * @throws
	// * @author lipengpeng
	// * @version 1.0
	// * @param vtyper
	// * @createtime 2013-9-9 下午9:50:15
	// */
	// private List<UnitedTreeObj> attchiveVM(List<UnitedTreeObj> list,
	// String vtyper) {
	// List<UnitedTreeObj> vmList = new ArrayList<UnitedTreeObj>();
	//
	// List<String> pidList = new ArrayList<String>();
	// List<String> xenpidList = new ArrayList<String>();
	// for (UnitedTreeObj unitedTreeObj : list) {
	// pidList.add(unitedTreeObj.getId());
	// }
	// xenpidList.addAll(pidList);
	//
	// UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
	// unitedTreeObj.setPidlist(pidList);
	// unitedTreeObj.setType(UnitedConstant.VM);
	// if (pidList.size() > 0) {
	// uList = unitedTreeService.queryForTreeListUseIn(unitedTreeObj);
	// vmList.addAll(uList);
	// } else {
	// uList = new ArrayList<UnitedTreeObj>();
	// }
	// vmcount = uList.size();
	//
	// unitedTreeObj.setVtype(UnitedConstant.VMWARE);
	// List<VMHostObj> vlist = new ArrayList<VMHostObj>();
	// if (pidList.size() > 0) {
	// uList = unitedTreeService.queryForTreeListUseIn(unitedTreeObj);
	// } else {
	// uList = new ArrayList<UnitedTreeObj>();
	// }
	// vmwarecount = uList.size();
	//
	// pidList.clear();
	// for (UnitedTreeObj obj : uList) {
	// pidList.add(obj.getUuid());
	// }
	// VMHostObj vmHostObj = new VMHostObj();
	// vmHostObj.setUuidList(pidList);
	// vmHostObj.setVH_TYPE("1");
	// vmHostObj.setConnectStatus(VirtualMachineConnectStatus.connected);
	// vmHostObj.setVH_STAT("0");
	// if (UnitedConstant.VMWARE.equals(vtyper)) {
	// vmHostObj.setConnectId(connect_id);
	// }
	// if (pidList.size() > 0) {
	// vlist = vmHostService.queryForListByUUIDList(vmHostObj);
	// }
	// vmwarestopcount = vlist.size();
	//
	// vmHostObj.setVH_STAT("1");
	// if (pidList.size() > 0) {
	// vlist = vmHostService.queryForListByUUIDList(vmHostObj);
	// vmwareruncount = vlist.size();
	// } else {
	// vmwareruncount = 0;
	// }
	//
	// vmHostObj.setVH_STAT(null);
	// if (pidList.size() > 0) {
	// vlist = vmHostService.querySeriousList(vmHostObj);
	// vmwareseriouscount = vlist.size();
	// } else {
	// vmwareseriouscount = 0;
	// }
	//
	// unitedTreeObj.setVtype(UnitedConstant.XEN);
	// unitedTreeObj.setPidlist(xenpidList);
	// if (xenpidList.size() > 0) {
	// uList = unitedTreeService.queryForTreeListUseIn(unitedTreeObj);
	// xencount = uList.size();
	// } else {
	// xencount = 0;
	// }
	//
	// pidList.clear();
	// for (UnitedTreeObj obj : uList) {
	// pidList.add(obj.getUuid());
	// }
	// vmHostObj.setUuidList(pidList);
	// vmHostObj.setVH_TYPE("3");
	// vmHostObj.setVH_STAT("0");
	// if (pidList.size() > 0) {
	// vlist = vmHostService.queryForListByUUIDList(vmHostObj);
	// xenstopcount = vlist.size();
	// } else {
	// xenstopcount = 0;
	// }
	//
	// vmHostObj.setVH_STAT("1");
	// if (pidList.size() > 0) {
	// vlist = vmHostService.queryForListByUUIDList(vmHostObj);
	// xenruncount = vlist.size();
	// } else {
	// xenruncount = 0;
	// }
	//
	// vmHostObj.setVH_STAT(null);
	// if (pidList.size() > 0) {
	// vlist = vmHostService.querySeriousList(vmHostObj);
	// xenseriouscount = vlist.size();
	// } else {
	// xenseriouscount = 0;
	// }
	// return vmList;
	// }
	//
	// /**
	// * @Title: ShowClusterInfo
	// * @Description: 展示集群信息
	// * @param
	// * @return String
	// * @throws
	// * @author lipengpeng
	// * @version 1.0
	// * @createtime 2013-9-9 下午8:44:32
	// */
	// public String showClusterInfo() {
	// UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
	// if (uuid != null && connect_id != null) {
	// unitedTreeObj.setUuid(uuid);
	// unitedTreeObj.setConnect_id(connect_id);
	// } else {
	// unitedTreeObj.setId(id);
	// }
	// try {
	// unitedTreeObj = unitedTreeService.queryByObj(unitedTreeObj);
	// cluid = unitedTreeObj.getId();
	// cluuuid = unitedTreeObj.getUuid();
	// connect_id = unitedTreeObj.getConnect_id();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// List<UnitedTreeObj> list = new ArrayList<UnitedTreeObj>();
	// list.add(unitedTreeObj);
	// String vtyper = unitedTreeObj.getVtype();
	// List<UnitedTreeObj> hostlist = this.attchiveHost(list, vtyper);
	// this.attchiveVM(hostlist, vtyper);
	// return "clusterpage";
	// }
	//
	// /**
	// * @Title: showHostInfo
	// * @Description: 展示主机信息
	// * @param
	// * @return String
	// * @throws
	// * @author lipengpeng
	// * @version 1.0
	// * @createtime 2013-9-9 下午8:45:22
	// */
	// public String showHostInfo() {
	// UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
	// if (uuid != null && connect_id != null) {
	// unitedTreeObj.setUuid(uuid);
	// unitedTreeObj.setConnect_id(connect_id);
	// } else {
	// unitedTreeObj.setId(id);
	// }
	// try {
	// unitedTreeObj = unitedTreeService.queryByObj(unitedTreeObj);
	// hostid = unitedTreeObj.getUuid();
	// connect_id = unitedTreeObj.getConnect_id();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// String vtyper = unitedTreeObj.getVtype();
	//
	// List<UnitedTreeObj> unList = new ArrayList<UnitedTreeObj>();
	// unList.add(unitedTreeObj);
	//
	// List<String> pidList = new ArrayList<String>();
	// pidList.add(unitedTreeObj.getUuid());
	// // 统计cpu、内存、存储等信息
	// TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
	// hostObj.setUuidList(pidList);
	// CMSObj cmsObj = new CMSObj();
	// if (pidList.size() > 0) {
	// if (uuid != null && connect_id != null) {
	// cmsObj = hostInfoService.countResource(hostObj);
	// } else {
	// cmsObj = hostInfoService.countResourceByEqid(hostObj);
	// }
	// }
	// cpuallcount = cmsObj.getAllcpu() == null ? 0 : cmsObj.getAllcpu();
	// memallcount = cmsObj.getAllmem() == null ? 0 : convert(cmsObj
	// .getAllmem() * 1.0 / 1024);
	// cpuusedcount = cmsObj.getUsedcpu() == null ? 0 : cmsObj.getUsedcpu();
	// memusedcount = cmsObj.getUsedmem() == null ? 0 : convert(cmsObj
	// .getUsedmem() * 1.0 / 1024);
	// cpuallocount = cmsObj.getAllocpu() == null ? 0 : cmsObj.getAllocpu();
	// memallocount = cmsObj.getAllomem() == null ? 0 : convert(cmsObj
	// .getAllomem() * 1.0 / 1024);
	//
	// if (uuid != null && connect_id != null) {
	// // vmware
	// DataStoreObj dataStoreObj = new DataStoreObj();
	// dataStoreObj.setHostidlist(pidList);
	// if (pidList.size() > 0) {
	// cmsObj = dataStoreService.countResource(dataStoreObj);
	// }
	// double srallcountvmware = cmsObj.getAllsr() == null
	// ? 0
	// : convert(cmsObj.getAllsr() * 1.0 / 1024 / 1024);
	// double freestore = cmsObj.getUsedsr() == null ? 0 : convert(cmsObj
	// .getUsedsr() * 1.0 / 1024 / 1024);
	// double srusedcountvmware = srallcountvmware - freestore;
	// double srallocountvmware = cmsObj.getAllosr() == null
	// ? 0
	// : convert(cmsObj.getAllosr() * 1.0 / 1024 / 1024);
	//
	// // xen
	// XenStoreObj xenStoreObj = new XenStoreObj();
	// xenStoreObj.setHostidlist(pidList);
	// if (pidList.size() > 0) {
	// cmsObj = xenStoreService.countResource(xenStoreObj);
	// }
	// double srallcountxen = cmsObj.getAllsr() == null
	// ? 0
	// : convert(cmsObj.getAllsr() * 1.0 / 1024 / 1024);
	// double freestorexen = cmsObj.getUsedsr() == null
	// ? 0
	// : convert(cmsObj.getUsedsr() * 1.0 / 1024 / 1024);
	// double srusedcountxen = srallcountxen - freestorexen;
	//
	// srallcount = convert(srallcountvmware + srallcountxen);
	// srusedcount = convert(srusedcountvmware + srusedcountxen);
	// srallocount = convert(srallocountvmware + srusedcountxen);
	// // srallocount = srusedcount;
	// } else {
	// srallcount = cmsObj.getAllsr() == null ? 0 : convert(cmsObj
	// .getAllsr() * 1.0 / 1024 / 1024);
	// srusedcount = 0;
	// srallocount = 0;
	// }
	//
	// this.attchiveVM(unList, vtyper);
	// return "hostpage";
	// }

	/**
	 * @Title: showHostInfo
	 * @Description: 展示主机信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-1 下午4:04:13
	 */
	public String showHostInfo() {
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setId(id);
		try {
			unitedTreeObj = unitedTreeService.queryByObj(unitedTreeObj);
			hostid = unitedTreeObj.getUuid();
			connect_id = unitedTreeObj.getConnect_id();
			vtype = unitedTreeObj.getVtype();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<String> uuidList = new ArrayList<String>();
		uuidList.add(hostid);
		this.countVm(uuidList, "host");
		this.countResource(uuidList, "host");
		if (parentHostList == null) {
			parentHostList = new ArrayList<String>();
		}
		parentHostList.add(hostid + "_" + connect_id);
		return "hostpage";
	}

	/**
	 * @Title: showClusterInfo
	 * @Description: 展示集群信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-1 下午7:23:10
	 */
	public String showClusterInfo() {
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setId(id);
		try {
			unitedTreeObj = unitedTreeService.queryByObj(unitedTreeObj);
			cluuuid = unitedTreeObj.getUuid();
			connect_id = unitedTreeObj.getConnect_id();
			vtype = unitedTreeObj.getVtype();
			cluid = id;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<String> pidList = new ArrayList<String>();
		// 统计主机个数
		if (UnitedConstant.UNALLOCATED.equals(vtype)) {// 未分配的
			pidList.add(id);
			pidList = this.countHostUnAllo(pidList);
		} else {// 已分配的
			pidList.add(cluuuid);
			this.countHostAllo(pidList, "cluster");
			// 统计虚拟机个数
			this.countVm(pidList, "cluster");
		}

		// 统计cpu、内存、存储等
		this.countResource(pidList, "cluster");

		if (clusterList == null) {
			clusterList = new ArrayList<String>();
		}
		if (unalloclusterList == null) {
			unalloclusterList = new ArrayList<String>();
		}
		if (UnitedConstant.UNALLOCATED.equals(vtype)) {
			unalloclusterList.add(cluid);
		} else {
			clusterList.add(cluuuid + "_" + connect_id);/*添加集群id*/
		}
		return "clusterpage";
	}

	/**
	 * @Title: showDataCenterInfo
	 * @Description: 统计数据中心信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-1 下午8:59:45
	 */
	public String showDataCenterInfo() {
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setId(id);
		try {
			unitedTreeObj = unitedTreeService.queryByObj(unitedTreeObj);
			centerid = unitedTreeObj.getUuid();
			connect_id = unitedTreeObj.getConnect_id();
			vtype = unitedTreeObj.getVtype();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<String> pidList = new ArrayList<String>();

		if (clusterList == null) {
			clusterList = new ArrayList<String>();
		}
		if (unalloclusterList == null) {
			unalloclusterList = new ArrayList<String>();
		}
		// 统计集群个数
		if (UnitedConstant.UNALLOCATED.equals(vtype)) {
			pidList.add(id);
			pidList = this.countCluUnAllo(pidList);
			unalloclusterList.addAll(pidList);
			pidList = this.countHostUnAllo(pidList);
		} else {
			if (UnitedConstant.XEN.equals(vtype)) {
				connect_id = null;
			}
			pidList.add(centerid);
			this.countCluAllo(pidList);
			this.countHostAllo(pidList, "center");
			this.countVm(pidList, "center");
		}

		// 统计cpu、内存、存储
		this.countResource(pidList, "center");

		return "datacenterpage";
	}

	/**
	 * @Title: showRegionInfo
	 * @Description: 统计地域信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-3 上午9:38:22
	 */
	public String showRegionInfo() {
		/*-----------------统计数据中心个数------------------*/
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setParent_id(id);
		unitedTreeObj.setType(UnitedConstant.DATACENTER);
		List<UnitedTreeObj> centerList = unitedTreeService.queryForCenterTreeList(unitedTreeObj);
		centercount = centerList.size();

		if (clusterList == null) {
			clusterList = new ArrayList<String>();
		}
		if (unalloclusterList == null) {
			unalloclusterList = new ArrayList<String>();
		}
		/*-----------------统计集群个数-----------------*/
		// 直接在地域下的集群----此种结构复杂，暂不考虑
		List<String> pidList = this.countClusterRegion(centerList);

		// 统计主机个数
		pidList = this.countHostRegion(centerList, pidList);

		// 统计虚拟机个数
		this.countVMRegion(centerList);

		// 统计cpu、内存、存储
		this.countResourceRegion(centerList, pidList);

		return "regionpage";
	}

	/**
	 * @Title: countResourceRegion
	 * @Description: 统计地域下cpu、内存、存储等信息
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @param pidList
	 * @createtime 2014-1-3 上午11:27:55
	 */
	private void countResourceRegion(List<UnitedTreeObj> centerList, List<String> pidList) {
		int cpuall = 0;
		int cpuallo = 0;
		int cpuused = 0;
		double memall = 0;
		double memallo = 0;
		double memused = 0;
		double srall = 0;
		double srallo = 0;
		double srused = 0;

		for (UnitedTreeObj unitedTreeObj : centerList) {
			vtype = unitedTreeObj.getVtype();
			if (UnitedConstant.UNALLOCATED.equals(vtype)) {
				this.countResource(pidList, "center");
				cpuall += cpuallcount;
				cpuallo += cpuallocount;
				cpuused += cpuusedcount;
				memall += memallcount;
				memallo += memallocount;
				memused += memusedcount;
			} else if (UnitedConstant.VMWARE.equals(vtype) || UnitedConstant.XEN.equals(vtype)) {
				connect_id = unitedTreeObj.getConnect_id();
				List<String> uuidList = new ArrayList<String>();
				uuidList.add(unitedTreeObj.getUuid());
				this.countResource(uuidList, "center");
				cpuall += cpuallcount;
				cpuallo += cpuallocount;
				cpuused += cpuusedcount;
				memall += memallcount;
				memallo += memallocount;
				memused += memusedcount;
				srall += srallcount;
				srallo += srallocount;
				srused += srusedcount;
			}
		}

		cpuallcount = cpuall;
		cpuallocount = cpuallo;
		cpuusedcount = cpuused;
		memallcount = memall;
		memallocount = memallo;
		memusedcount = memused;
		srallcount = srall;
		srallocount = srallo;
		srusedcount = srused;
	}

	/**
	 * @Title: countVMRegion
	 * @Description: 统计地域下虚拟机个数
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-3 上午11:08:48
	 */
	private void countVMRegion(List<UnitedTreeObj> centerList) {
		int vmsum = 0;
		int vmwaresum = 0;
		int xensum = 0;
		int vmwarer = 0;
		int xenr = 0;
		int vmwarest = 0;
		int xenst = 0;
		int vmwareser = 0;
		int xenser = 0;

		for (UnitedTreeObj unitedTreeObj : centerList) {
			vtype = unitedTreeObj.getVtype();
			if (UnitedConstant.VMWARE.equals(vtype)) {
				connect_id = unitedTreeObj.getConnect_id();
				List<String> pidList = new ArrayList<String>();
				pidList.add(unitedTreeObj.getUuid());
				this.countVm(pidList, "center");
				vmsum += vmcount;
				vmwaresum += vmwarecount;
				vmwarer += vmwareruncount;
				vmwarest += vmwarestopcount;
				vmwareser += vmwareseriouscount;
			} else if (UnitedConstant.XEN.equals(vtype)) {
				connect_id = unitedTreeObj.getConnect_id();
				List<String> pidList = new ArrayList<String>();
				pidList.add(unitedTreeObj.getUuid());
				this.countVm(pidList, "center");
				vmsum += vmcount;
				xensum += xencount;
				xenr += xenruncount;
				xenst += xenstopcount;
				xenser += xenseriouscount;
			}
		}
		vmcount = vmsum;
		vmwarecount = vmwaresum;
		xencount = xensum;
		vmwareruncount = vmwarer;
		xenruncount = xenr;
		vmwarestopcount = vmwarest;
		xenstopcount = xenst;
		vmwareseriouscount = vmwareser;
		xenseriouscount = xenser;
	}

	/**
	 * @Title: countHostRegion
	 * @Description: 统计地域下的主机总数
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @param pidList
	 * @createtime 2014-1-3 上午10:39:06
	 */
	private List<String> countHostRegion(List<UnitedTreeObj> centerList, List<String> pidList) {
		// 统计未分配主机个数
		List<String> retList = this.countHostUnAllo(pidList);
		hostunallo = hostcount;
		hostunalloruncount = hostruncount;
		hostunallostopcount = hoststopcount;
		hostunalloseriouscount = hostseriouscount;
		framehostunallocount = framehostcount;
		x86hostunallocount = x86hostcount;

		// 统计已分配主机个数
		int hostr = 0;
		int hostst = 0;
		int hostser = 0;
		int frhost = 0;
		int x86host = 0;
		for (UnitedTreeObj unitedTreeObj : centerList) {
			vtype = unitedTreeObj.getVtype();
			if (UnitedConstant.VMWARE.equals(vtype) || UnitedConstant.XEN.equals(vtype)) {
				List<String> paridList = new ArrayList<String>();
				paridList.add(unitedTreeObj.getUuid());
				connect_id = unitedTreeObj.getConnect_id();
				this.countHostAllo(paridList, "center");
				hostallo += hostcount;
				hostr += hostruncount;
				hostst += hoststopcount;
				hostser += hostseriouscount;
				frhost += framehostcount;
				x86host += x86hostcount;
			}
		}
		hostruncount = hostr;
		hoststopcount = hostst;
		hostseriouscount = hostser;
		framehostcount = frhost;
		x86hostcount = x86host;

		hostcount = hostunallo + hostallo;

		return retList;
	}

	/**
	 * @Title: countClusterRegion
	 * @Description: 统计集群个数
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-3 上午9:47:07
	 */
	private List<String> countClusterRegion(List<UnitedTreeObj> centerList) {
		List<String> retList = new ArrayList<String>();
		int vmwarec = 0;
		int xenc = 0;
		for (UnitedTreeObj unitedTreeObj : centerList) {
			vtype = unitedTreeObj.getVtype();
			if (UnitedConstant.UNALLOCATED.equals(vtype)) {
				// 统计未分配集群个数
				List<String> pidList = new ArrayList<String>();
				pidList.add(unitedTreeObj.getId());
				retList.addAll(this.countCluUnAllo(pidList));
				unalloclusterList.addAll(retList);
				unalloclucount += clucount;
			} else if (UnitedConstant.VMWARE.equals(vtype)) {
				List<String> pidList = new ArrayList<String>();
				pidList.add(unitedTreeObj.getUuid());
				connect_id = unitedTreeObj.getConnect_id();
				this.countCluAllo(pidList);
				vmwarec += vmwareclucount;
			} else if (UnitedConstant.XEN.equals(vtype)) {
				// 统计xen集群个数
				List<String> pidList = new ArrayList<String>();
				pidList.add(unitedTreeObj.getUuid());
				connect_id = unitedTreeObj.getConnect_id();
				this.countCluAllo(pidList);
				xenc += xenclucount;
			}
		}
		clucount = unalloclucount + vmwarec + xenc;
		vmwareclucount = vmwarec;
		xenclucount = xenc;
		return retList;
	}

	/**
	 * @Title: countCluAllo
	 * @Description: 统计已分配集群个数
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-1 下午9:24:33
	 */
	private void countCluAllo(List<String> pidList) {
		clucount = 0;
		vmwareclucount = 0;
		xenclucount = 0;
		HostGlobalObj hostGlobalObj = new HostGlobalObj();
		hostGlobalObj.setUuidList(pidList);
		hostGlobalObj.setConnect_uuid(connect_id);
		List<HostGlobalObj> hostglobalList = hostGlobalService
				.queryForClusterListByCenter(hostGlobalObj);
		clucount = hostglobalList.size();
		if (UnitedConstant.VMWARE.equals(vtype)) {
			vmwareclucount = clucount;
		} else if (UnitedConstant.XEN.equals(vtype)) {
			xenclucount = clucount;
		}

		for (HostGlobalObj globalObj : hostglobalList) {
			clusterList.add(globalObj.getCluster_uuid() + "_" + globalObj.getConnect_uuid());
		}
	}

	/**
	 * @Title: countCluUnAllo
	 * @Description: 统计未分配的集群
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-1 下午9:14:24
	 */
	private List<String> countCluUnAllo(List<String> pidList) {
		clucount = 0;
		UnitedTreeObj cluUnitedTreeObj = new UnitedTreeObj();
		cluUnitedTreeObj.setPidlist(pidList);
		cluUnitedTreeObj.setType(UnitedConstant.CLUSTER);
		if (pidList.size() > 0) {
			ulist = unitedTreeService.queryForTreeListUseIn(cluUnitedTreeObj);
			clucount = ulist.size();
		}
		pidList.clear();
		for (UnitedTreeObj obj : ulist) {
			pidList.add(obj.getId());
		}
		return pidList;
	}

	/**
	 * @Title: countHostAllo
	 * @Description: 统计已分配主机
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-1 下午8:06:30
	 */
	private void countHostAllo(List<String> pidList, String flag) {
		hostcount = 0;
		hostruncount = 0;
		hoststopcount = 0;
		hostseriouscount = 0;
		framehostcount = 0;
		x86hostcount = 0;
		HostGlobalObj hostGlobalObj = new HostGlobalObj();
		hostGlobalObj.setUuidList(pidList);
		hostGlobalObj.setConnect_uuid(connect_id);
		List<HostGlobalObj> hostglobalList = new ArrayList<HostGlobalObj>();
		if ("cluster".equals(flag)) {
			hostglobalList = hostGlobalService.queryForListUseClusterIn(hostGlobalObj);
		} else if ("center".equals(flag)) {
			hostglobalList = hostGlobalService.queryForListUseCenterIn(hostGlobalObj);
		}
		hostcount = hostglobalList.size();

		List<String> huuidList = new ArrayList<String>();
		for (HostGlobalObj hostGlobalObj2 : hostglobalList) {
			huuidList.add(hostGlobalObj2.getHost_uuid());
		}
		TbCloud2HostInfoObj hostInfoObj = new TbCloud2HostInfoObj();

		hostInfoObj.setConnectId(connect_id);
		hostInfoObj.setUuidList(huuidList);
		hostInfoObj.setConnectStatus(HostSystemConnectStatus.connected);
		hostInfoObj.setSTATUS("1");
		List<TbCloud2HostInfoObj> hostList = new ArrayList<TbCloud2HostInfoObj>();
		if (huuidList.size() > 0) {
			hostList = hostInfoService.queryHostListUseIn(hostInfoObj);
			hostruncount = hostList.size();
			hostInfoObj.setSTATUS("2");
			hostList = hostInfoService.queryHostListUseIn(hostInfoObj);
			hoststopcount = hostList.size();
			hostInfoObj.setSTATUS(null);
			hostList = hostInfoService.querySeriousHostListUseIn(hostInfoObj);
			hostseriouscount = hostList.size();
			hostInfoObj.setEq_type("5");
			hostList = hostInfoService.queryHostListUseIn(hostInfoObj);
			framehostcount = hostList.size();
			hostInfoObj.setEq_type("4");
			hostList = hostInfoService.queryHostListUseIn(hostInfoObj);
			x86hostcount = hostList.size();
		}
	}

	/**
	 * @Title: countHostUnAllo
	 * @Description: 统计未分配主机
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-1 下午8:04:29
	 */
	private List<String> countHostUnAllo(List<String> pidList) {
		hostcount = 0;
		hostruncount = 0;
		hoststopcount = 0;
		hostseriouscount = 0;
		framehostcount = 0;
		x86hostcount = 0;
		TbCloud2HostInfoObj hostInfoObj = new TbCloud2HostInfoObj();
		UnitedTreeObj hostObj = new UnitedTreeObj();
		hostObj.setPidlist(pidList);
		hostObj.setType(UnitedConstant.HOST);
		ulist = unitedTreeService.queryForTreeListUseIn(hostObj);
		hostcount = ulist.size();

		pidList.clear();
		for (UnitedTreeObj obj : ulist) {
			pidList.add(obj.getUuid());
		}
		List<TbCloud2HostInfoObj> hostList = new ArrayList<TbCloud2HostInfoObj>();
		if (pidList.size() > 0) {
			hostInfoObj.setUuidList(pidList);
			hostInfoObj.setSTATUS("1");
			hostList = hostInfoService.queryHostListUseInByEqid(hostInfoObj);
			hostruncount = hostList.size();
			hostInfoObj.setSTATUS("2");
			hostList = hostInfoService.queryHostListUseInByEqid(hostInfoObj);
			hoststopcount = hostList.size();
			hostInfoObj.setSTATUS(null);
			hostList = hostInfoService.querySeriousHostListUseInUnAllo(hostInfoObj);
			hostseriouscount = hostList.size();
			hostInfoObj.setEq_type("5");
			hostList = hostInfoService.queryHostListUseInByEqid(hostInfoObj);
			framehostcount = hostList.size();
			hostInfoObj.setEq_type("4");
			hostList = hostInfoService.queryHostListUseInByEqid(hostInfoObj);
			x86hostcount = hostList.size();
		}
		return pidList;
	}

	/**
	 * @Title: countVm
	 * @Description: 统计虚拟机
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-1 下午5:55:31
	 */
	private void countVm(List<String> uuidList, String flag) {
		vmcount = 0;
		vmwarecount = 0;
		vmwarestopcount = 0;
		vmwareruncount = 0;
		vmwareseriouscount = 0;
		xencount = 0;
		xenstopcount = 0;
		xenruncount = 0;
		xenseriouscount = 0;
		if (!UnitedConstant.UNALLOCATED.equals(vtype)) {
			List<VmGlobalObj> vmglobalList = new ArrayList<VmGlobalObj>();
			VmGlobalObj vmGlobalObj = new VmGlobalObj();
			vmGlobalObj.setUuidList(uuidList);
			vmGlobalObj.setConnect_uuid(connect_id);
			if ("host".equals(flag)) {// 统计主机的
				vmglobalList = vmGlobalService.queryForListByHost(vmGlobalObj);
			} else if ("cluster".equals(flag)) {// 统计集群的
				vmglobalList = vmGlobalService.queryForListByCluster(vmGlobalObj);
			} else if ("center".equals(flag)) {// 统计中心的
				vmglobalList = vmGlobalService.queryForListByCenter(vmGlobalObj);
			}
			vmcount = vmglobalList.size();
			VMHostObj vmHostObj = new VMHostObj();
			List<String> vmuuidList = new ArrayList<String>();
			for (VmGlobalObj vmGlobalObj2 : vmglobalList) {
				vmuuidList.add(vmGlobalObj2.getVm_uuid());
			}
			vmHostObj.setUuidList(vmuuidList);
			vmHostObj.setConnectId(connect_id);
			vmHostObj.setConnectStatus(VirtualMachineConnectStatus.connected);
			if (vmuuidList.size() > 0) {
				vmHostObj.setVH_STAT("0");
				vmList = vmHostService.queryForListByUUIDList(vmHostObj);
				int stopcount = vmList.size();
				vmHostObj.setVH_STAT("1");
				vmList = vmHostService.queryForListByUUIDList(vmHostObj);
				int runcount = vmList.size();
				vmHostObj.setVH_STAT(null);
				vmList = vmHostService.querySeriousList(vmHostObj);
				int seriouscount = vmList.size();
				if (UnitedConstant.VMWARE.equals(vtype)) {
					vmwarecount = vmcount;
					vmwarestopcount = stopcount;
					vmwareruncount = runcount;
					vmwareseriouscount = seriouscount;
				} else if (UnitedConstant.XEN.equals(vtype)) {
					xencount = vmcount;
					xenstopcount = stopcount;
					xenruncount = runcount;
					xenseriouscount = seriouscount;
				}
			}
		}
	}

	/**
	 * @Title: countResource
	 * @Description: 统计cpu、内存、存储
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-1 下午8:51:20
	 */
	private void countResource(List<String> uuidList, String flag) {
		srallcount = 0;
		srusedcount = 0;
		srallocount = 0;
		cpuallcount = 0;
		memallcount = 0;
		cpuusedcount = 0;
		memusedcount = 0;
		cpuallocount = 0;
		memallocount = 0;
		CMSObj cmsObj = new CMSObj();
		if (UnitedConstant.UNALLOCATED.equals(vtype)) {// 未分配的主机
			TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
			hostObj.setUuidList(uuidList);
			cmsObj = hostInfoService.countResourceByEqid(hostObj);
			// srallcount = cmsObj.getAllsr() == null ? 0 : convert(cmsObj
			// .getAllsr() * 1.0 / 1024 / 1024);
			srallcount = 0;
			srusedcount = 0;
			srallocount = 0;
		} else {// 已分配的
			HostGlobalObj hostObj = new HostGlobalObj();
			hostObj.setConnect_uuid(connect_id);
			hostObj.setUuidList(uuidList);
			List<HostGlobalObj> hostgList = new ArrayList<HostGlobalObj>();
			if ("host".equals(flag)) {
				cmsObj = hostGlobalService.countResourceForHost(hostObj);
			} else if ("cluster".equals(flag)) {
				cmsObj = hostGlobalService.countResourceForCluster(hostObj);
				hostgList = hostGlobalService.queryForListUseClusterIn(hostObj);
				uuidList.clear();
				for (HostGlobalObj hostGlobalObj : hostgList) {
					uuidList.add(hostGlobalObj.getHost_uuid());
				}
			} else if ("center".equals(flag)) {
				cmsObj = hostGlobalService.countResourceForCenter(hostObj);
				hostgList = hostGlobalService.queryForListUseCenterIn(hostObj);
				uuidList.clear();
				for (HostGlobalObj hostGlobalObj : hostgList) {
					uuidList.add(hostGlobalObj.getHost_uuid());
				}
			}
			if (UnitedConstant.VMWARE.equals(vtype)) {// vmware
				DataStoreObj dataStoreObj = new DataStoreObj();
				dataStoreObj.setHostidlist(uuidList);
				dataStoreObj.setConnectId(connect_id);
				if (uuidList.size() > 0) {
					CMSObj cmsObj1 = dataStoreService.countResource(dataStoreObj);
					srallcount = cmsObj1.getAllsr() == null ? 0
							: convert(cmsObj1.getAllsr() * 1.0 / 1024 / 1024);
					double freestore = cmsObj1.getUsedsr() == null ? 0 : convert(cmsObj1
							.getUsedsr() * 1.0 / 1024 / 1024);
					srusedcount = srallcount - freestore;
					srallocount = cmsObj1.getAllosr() == null ? 0
							: convert(cmsObj1.getAllosr() * 1.0 / 1024 / 1024);
				}
			} else if (UnitedConstant.XEN.equals(vtype)) {// xen
				XenStoreObj xenStoreObj = new XenStoreObj();
				xenStoreObj.setHostidlist(uuidList);
				xenStoreObj.setConnect_id(connect_id);
				if (uuidList.size() > 0) {
					CMSObj cmsObj1 = xenStoreService.countResource(xenStoreObj);
					srallcount = cmsObj1.getAllsr() == null ? 0
							: convert(cmsObj1.getAllsr() * 1.0 / 1024 / 1024);
					double freestorexen = cmsObj1.getUsedsr() == null ? 0 : convert(cmsObj1
							.getUsedsr() * 1.0 / 1024 / 1024);
					srusedcount = srallcount - freestorexen;
					srallocount = cmsObj1.getAllosr() == null ? 0
							: convert(cmsObj1.getAllosr() * 1.0 / 1024 / 1024);
				}
			}
		}

		cpuallcount = cmsObj.getAllcpu() == null ? 0 : cmsObj.getAllcpu();
		memallcount = cmsObj.getAllmem() == null ? 0 : convert(cmsObj.getAllmem() * 1.0 / 1024);
		cpuusedcount = cmsObj.getUsedcpu() == null ? 0 : cmsObj.getUsedcpu();
		memusedcount = cmsObj.getUsedmem() == null ? 0 : convert(cmsObj.getUsedmem() * 1.0 / 1024);
		cpuallocount = cmsObj.getAllocpu() == null ? 0 : cmsObj.getAllocpu();
		memallocount = cmsObj.getAllomem() == null ? 0 : convert(cmsObj.getAllomem() * 1.0 / 1024);
	}

	/**
	 * @Title: showNetPage
	 * @Description: 展示网络信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-12 下午11:46:44
	 */
	@SuppressWarnings("unchecked")
	public String showNetPage() {
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		// unitedTreeObj.setType(UnitedConstant.DATACENTER);
		unitedTreeObj.setVtype(UnitedConstant.NETWORKS);
		unitedTreeObj.setParent_id(id);
		try {
			// 目前只算一个网络中心的情况
			unitedTreeObj.setType(UnitedConstant.DOMAIN);
			ulist = unitedTreeService.queryForTreeList(unitedTreeObj);
			domaincount = ulist.size();

			unitedTreeObj.setType(UnitedConstant.SUBDOMIAN);
			List<String> pidList = new ArrayList<String>();
			for (UnitedTreeObj obj : ulist) {
				pidList.add(obj.getId());
			}
			unitedTreeObj.setPidlist(pidList);
			ulist = unitedTreeService.queryForTreeListUseIn(unitedTreeObj);
			subdomaincount = ulist.size();

			pidList.clear();
			for (UnitedTreeObj obj : ulist) {
				pidList.add(obj.getId());
			}
			unitedTreeObj.setPidlist(pidList);
			unitedTreeObj.setType(UnitedConstant.VLAN);
			ulist = unitedTreeService.queryForTreeListUseIn(unitedTreeObj);
			vlancount = ulist.size();

			ResourceStatisticsObj resourceStatisticsObj = new ResourceStatisticsObj();
			resourceStatisticsObj.setKey("ip_all_count");
			List<ResourceStatisticsObj> list = resourceStatisticsService
					.queryForListByObj(resourceStatisticsObj);
			if (list.size() > 0) {
				resourceStatisticsObj = list.get(0);
				ipcount = Integer.parseInt(resourceStatisticsObj.getValue());
			} else {
				ipcount = 0;
			}

			resourceStatisticsObj.setKey("ip_used_count");
			list = resourceStatisticsService.queryForListByObj(resourceStatisticsObj);
			if (list.size() > 0) {
				resourceStatisticsObj = list.get(0);
				ipusedcount = Integer.parseInt(resourceStatisticsObj.getValue());
			} else {
				ipusedcount = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "showNetPage";
	}

	/**
	 * @Title: showDomainPage
	 * @Description: 展示网络域信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-12 下午11:46:57
	 */
	@SuppressWarnings("unchecked")
	public String showDomainPage() {
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setParent_id(id);
		try {
			ulist = unitedTreeService.queryForTreeList(unitedTreeObj);
			subdomaincount = ulist.size();

			List<String> pidList = new ArrayList<String>();
			for (UnitedTreeObj obj : ulist) {
				pidList.add(obj.getId());
			}

			unitedTreeObj.setPidlist(pidList);
			ulist = unitedTreeService.queryForTreeListUseIn(unitedTreeObj);
			vlancount = ulist.size();

			pidList.clear();
			for (UnitedTreeObj obj : ulist) {
				pidList.add(obj.getUuid());
			}

			TbCloud2IpInfoObj ipInfoObj = new TbCloud2IpInfoObj();
			ipInfoObj.setNetList(pidList);

			// 统计每个vlan下ip的利用率
			netList = ipService.queryIpUsageByVlanId(ipInfoObj);
			DecimalFormat df = new DecimalFormat("#.00");
			for (AlarmHostStatistics obj : netList) {
				String s = df.format(Double.parseDouble(obj.getUsedCount())
						/ Double.parseDouble(obj.getAllCount()));
				obj.setIpusage(s);
			}
			Collections.sort(netList, new MyComparator());

			List<TbCloud2IpInfoObj> ipList = ipService.queryForListUseNetIn(ipInfoObj);
			ipcount = ipList.size();

			ipInfoObj.setISUSED("1");
			ipList = ipService.queryForListUseNetIn(ipInfoObj);
			ipusedcount = ipList.size();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "showDomainPage";
	}

	/**
	 * @Title: showSubDomainPage
	 * @Description: 展示子域信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-12 下午11:47:10
	 */
	@SuppressWarnings("unchecked")
	public String showSubDomainPage() {
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setParent_id(id);
		try {
			ulist = unitedTreeService.queryForTreeList(unitedTreeObj);
			vlancount = ulist.size();

			List<String> pidList = new ArrayList<String>();
			for (UnitedTreeObj obj : ulist) {
				pidList.add(obj.getUuid());
			}
			TbCloud2IpInfoObj ipInfoObj = new TbCloud2IpInfoObj();
			ipInfoObj.setNetList(pidList);
			netList = ipService.queryIpUsageByVlanId(ipInfoObj);
			DecimalFormat df = new DecimalFormat("#.00");
			for (AlarmHostStatistics obj : netList) {
				String s = df.format(Double.parseDouble(obj.getUsedCount())
						/ Double.parseDouble(obj.getAllCount()));
				obj.setIpusage(s);
			}

			Collections.sort(netList, new MyComparator());

			List<TbCloud2IpInfoObj> ipList = ipService.queryForListUseNetIn(ipInfoObj);
			ipcount = ipList.size();

			ipInfoObj.setISUSED("1");
			ipList = ipService.queryForListUseNetIn(ipInfoObj);
			ipusedcount = ipList.size();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "showSubDomainPage";
	}

	/**
	 * @Title: showSubDomainPage
	 * @Description: 展示子域信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-12 下午11:47:10
	 */
	@SuppressWarnings("unchecked")
	public String showSubDomainPageForVlan() {
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setId(id);
		try {
			ulist = unitedTreeService.queryForTreeList(unitedTreeObj);
			vlancount = ulist.size();

			List<String> pidList = new ArrayList<String>();
			for (UnitedTreeObj obj : ulist) {
				pidList.add(obj.getUuid());
			}
			TbCloud2IpInfoObj ipInfoObj = new TbCloud2IpInfoObj();
			ipInfoObj.setNetList(pidList);
			netList = ipService.queryIpUsageByVlanId(ipInfoObj);
			DecimalFormat df = new DecimalFormat("#.00");
			for (AlarmHostStatistics obj : netList) {
				String s = df.format(Double.parseDouble(obj.getUsedCount())
						/ Double.parseDouble(obj.getAllCount()));
				obj.setIpusage(s);
			}

			Collections.sort(netList, new MyComparator());

			List<TbCloud2IpInfoObj> ipList = ipService.queryForListUseNetIn(ipInfoObj);
			ipcount = ipList.size();

			ipInfoObj.setISUSED("1");
			ipList = ipService.queryForListUseNetIn(ipInfoObj);
			ipusedcount = ipList.size();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "showSubDomainPageForVlan";
	}

	/**
	 * @Title: showVlanPage
	 * @Description: 展示vlan信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-12 下午11:47:23
	 */
	public String showVlanPage() {
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setId(id);
		try {
			unitedTreeObj = unitedTreeService.queryByObj(unitedTreeObj);
			List<String> pidList = new ArrayList<String>();
			pidList.add(unitedTreeObj.getUuid());
			TbCloud2IpInfoObj ipInfoObj = new TbCloud2IpInfoObj();
			ipInfoObj.setNetList(pidList);
			ipInfoObj.setPagination(this.getPaginater().initPagination(request));
			ipList = ipService.queryForListUseNetIn(ipInfoObj);
			// ipService.queryForListByObj(ipInfoObj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "showVlanPage";
	}

	/**
	 * @Title: showVlan
	 * @Description: vlan结点下展示ip信息
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-10-29 下午 16:37
	 */
	public String showVlan() {
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setId(id);
		try {
			unitedTreeObj = unitedTreeService.queryByObj(unitedTreeObj);
			TbCloud2IpInfoObj ipInfoObj = new TbCloud2IpInfoObj();
			ipInfoObj.setNET_ID(unitedTreeObj.getUuid());
			ipInfoObj.setPagination(this.getPaginater().initPagination(request));
			ipList = ipService.queryIpForTree(ipInfoObj);
			// ipService.queryForListByObj(ipInfoObj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "showVlan";
	}

	/**
	 * @Title: showStorePage
	 * @Description: 展示存储信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-13 上午12:41:15
	 */
	public String showStorePage() {
		if (id != null && !"".equals(id)) {
			UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
			unitedTreeObj.setId(id);
			try {
				unitedTreeObj = unitedTreeService.queryByObj(unitedTreeObj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			uuid = unitedTreeObj.getUuid();
		}
		if (dataStoreObj == null) {
			dataStoreObj = new DataStoreObj();
		}
		dataStoreObj.setDeviceId(uuid);
		dataStoreObj.setPagination(this.getPaginater().initPagination(request));
		storeList = dataStoreService.queryForListByDeviceId(dataStoreObj);

		for (DataStoreObj obj : storeList) {
			srallcount += Double.parseDouble(obj.getCAPACITY());
			srusedcount += Double.parseDouble(obj.getFREE_SPACE());
		}

		srusedcount = srallcount - srusedcount;
		srallcount = Double.parseDouble(new DecimalFormat("#.00").format(srallcount / 1024.0));
		srusedcount = Double.parseDouble(new DecimalFormat("#.00").format(srusedcount / 1024.0));

		return "showStorePage";
	}

	/**
	 * @Title: showStoreCenterPie
	 * @Description: 跳转到存储饼图展示页
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-15 上午9:49:18
	 */
	public String showStoreCenterPie() {
		return "showStoreCenterPie";
	}

	/**
	 * @Title: ah_showStoreCenterPie
	 * @Description: 展示存储饼状图，安徽使用
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-11-26 下午3:33:38
	 */
	public void ah_showStoreCenterPie() {
		String validXml = "";
		if (storemeasuretype == null || storemeasuretype.equals("san")) {
			StoreDeviceObj storeDeviceObj = new StoreDeviceObj();
			storeDeviceObj.setType(1);
			storeDeviceObj.setId(id);
			Map<String, Double> map = storeDeviceService.getStoreMap(storeDeviceObj);
			if (type != null && "1".equals(type)) {
				// 有效容量维度（接入、未接入）
				validXml = storeDeviceService.buildPieXml(map, "valid", "75");
			} else if (type != null && "2".equals(type)) {
				// 接入容量维度（分配、未分配）
				validXml = storeDeviceService.buildPieXml(map, "mount", "75");
			} else {
				// 分配容量维度（使用、未使用）
				validXml = storeDeviceService.buildPieXml(map, "allo", "75");
			}
		} else if (storemeasuretype.equals("hadoop")) {
			// 分布式维度（使用、未使用）
			TbCloud2HostInfoObj tbCloud2HostInfoObj = new TbCloud2HostInfoObj();
			tbCloud2HostInfoObj.setAllocated(0);
			tbCloud2HostInfoObj.setIs_distributed(1);
			Map<String, Double> map = hostInfoService.queryForDistribute(tbCloud2HostInfoObj);
			validXml = storeDeviceService.buildPieXml(map, "allo", "90");
		} else if (storemeasuretype.equals("nas")) {
			StoreDeviceObj storeDeviceObj = new StoreDeviceObj();
			storeDeviceObj.setType(2);
			Map<String, Double> map = storeDeviceService.getStoreMap(storeDeviceObj);
			if (type != null && "1".equals(type)) {
				// 有效容量维度（接入、未接入）
				validXml = storeDeviceService.buildPieXml(map, "valid", "75");
			} else if (type != null && "2".equals(type)) {
				// 接入容量维度（分配、未分配）
				validXml = storeDeviceService.buildPieXml(map, "mount", "75");
			} else {
				// 分配容量维度（使用、未使用）
				validXml = storeDeviceService.buildPieXml(map, "allo", "75");
			}

		}
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriterOut.printWirter(response, validXml);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: showStorePie
	 * @Description: 显示不同类型存储的总量及余量
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-15 上午11:57:08
	 */
	// public void showStorePie(){
	// StoreDeviceObj storeDeviceObj = new StoreDeviceObj();
	// if ("-1".equals(type)) {
	// storeDeviceObj.setType(null);
	// } else {
	// storeDeviceObj.setType(Integer.parseInt(type));
	// }
	// List<List<Object>> dataSetList =
	// storeDeviceService.findChartList(storeDeviceObj);
	// chartXml = FusionChartUtil.buildPieXml(dataSetList, "存储分析", "单位：G",
	// false);
	// response.setContentType("text/plain;charset=gbk");
	// try {
	// PrintWriter pw = response.getWriter();
	// pw.print(chartXml);
	// pw.flush();
	// pw.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	/**
	 * 
	 * @Title: listStoreDevice
	 * @Description: 展示存储设备的相关信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-10-30 下午4:06:40
	 */
	public String listStoreDevice() {
		if (storeDevice == null) {
			storeDevice = new StoreDeviceObj();
		}
		storeDevice.setId(id);
		storeDevice = storeDeviceService.queryByObj(storeDevice);
		if ((storeDevice.getType()) == 1) {
			NumberFormat nf = NumberFormat.getNumberInstance(Locale.CHINA);
			nf.setMaximumFractionDigits(2);
			nf.setGroupingUsed(false);
			storeDevice.setStoreBlockTotalSize(nf.format(Double.parseDouble(StringUtils
					.isEmpty(storeDevice.getStoreBlockTotalSize()) ? "0" : storeDevice
					.getStoreBlockTotalSize())));
			storeDevice.setStoreBlockFreeSpace(nf.format(Double.parseDouble(StringUtils
					.isEmpty(storeDevice.getStoreBlockFreeSpace()) ? "0" : storeDevice
					.getStoreBlockFreeSpace())));
			storeDevice
					.setPurchaseSpace(nf.format(Double.parseDouble(StringUtils.isEmpty(storeDevice
							.getPurchaseSpace()) ? "0" : storeDevice.getPurchaseSpace())));
			storeDevice.setValid_space(nf.format(Double.parseDouble(StringUtils.isEmpty(storeDevice
					.getValid_space()) ? "0" : storeDevice.getValid_space())));
		} else if ((storeDevice.getType()) == 2) {
			if (nasKpiObj == null) {
				nasKpiObj = new NasKpiObj();
			}
			nasKpiObj.setNas_device_id(id);
			nasKpiObj = nasKpiService.getNasKpi(nasKpiObj);
			NasStorePoolObj storeObj = new NasStorePoolObj();
			storeObj.setNas_device_id(id);
			nasStorePoolList = nasStorePoolService.getStorePoolList(storeObj);
			return "NasFile";
		}
		return "store_device";
	}

	/**
	 * @Title: showDataCenterPage
	 * @Description: 统计数据中心数据
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-14 上午9:59:38
	 */
	public String showDataCenterPage() {
		// 改数据中心下虚拟机总数
		List<UnitedTreeObj> vmlist = new ArrayList<UnitedTreeObj>();
		// 查询中心下集群个数
		List<String> pidlist = new ArrayList<String>();
		pidlist.add(id);
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setPidlist(pidlist);
		List<UnitedTreeObj> clulist = unitedTreeService.queryForTreeListUseIn(unitedTreeObj);
		List<UnitedTreeObj> clusterlist = new ArrayList<UnitedTreeObj>();
		for (UnitedTreeObj obj : clulist) {
			if (UnitedConstant.CLUSTER.equals(obj.getType())) {
				clusterlist.add(obj);
			}
		}
		map.put("clucount", clusterlist.size() + "");

		// 查询数据中心下主机个数
		// 挂在集群下的主机
		List<UnitedTreeObj> hostlist = new ArrayList<UnitedTreeObj>();
		if (clusterlist.size() > 0) {
			List<String> cluidlist = new ArrayList<String>();
			for (UnitedTreeObj obj : clusterlist) {
				cluidlist.add(obj.getId());
			}
			unitedTreeObj.setPidlist(cluidlist);
			List<UnitedTreeObj> hostlist1 = unitedTreeService.queryForTreeListUseIn(unitedTreeObj);
			for (UnitedTreeObj obj : hostlist1) {
				if (UnitedConstant.HOST.equals(obj.getType())) {
					hostlist.add(obj);
				}
			}
			// 直接挂在集群下的虚拟机集合
			hostlist1.removeAll(hostlist);
			vmlist.addAll(hostlist1);
		}
		// 直接挂在数据中心下的主机
		clulist.removeAll(clusterlist);
		if (clulist.size() > 0) {
			hostlist.addAll(clulist);
		}
		map.put("hostcount", hostlist.size() + "");

		// 查询中心下虚拟机个数
		// 挂在主机下的虚拟机个数
		List<String> hostidlist = new ArrayList<String>();
		for (UnitedTreeObj obj : hostlist) {
			hostidlist.add(obj.getId());
		}
		unitedTreeObj.setPidlist(hostidlist);
		List<UnitedTreeObj> vmlist1 = unitedTreeService.queryForTreeListUseIn(unitedTreeObj);
		vmlist.addAll(vmlist1);
		map.put("vmcount", vmlist.size() + "");
		return "showdatacenterpage";
	}

	/**
	 * @Title: showClusterPage
	 * @Description: 展示集群摘要
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-14 上午10:08:22
	 */
	public String showClusterPage() {
		// 获取到集群id
		UnitedTreeObj uobj = new UnitedTreeObj();
		uobj.setConnect_id(connect_id);
		uobj.setUuid(uuid);
		String cluid = "";
		try {
			uobj = unitedTreeService.queryByObj(uobj);
			cluid = uobj.getId();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 查询集群下主机个数
		List<String> pidlist = new ArrayList<String>();
		pidlist.add(cluid);
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setPidlist(pidlist);
		List<UnitedTreeObj> hlist = unitedTreeService.queryForTreeListUseIn(unitedTreeObj);
		List<UnitedTreeObj> hostlist = new ArrayList<UnitedTreeObj>();
		for (UnitedTreeObj obj : hlist) {
			if (UnitedConstant.HOST.equals(obj.getType())) {
				hostlist.add(obj);
			}
		}
		map.put("hostcount", hostlist.size() + "");

		// 查询集群下虚拟机个数
		List<UnitedTreeObj> vmlist = new ArrayList<UnitedTreeObj>();
		// 挂在主机下的虚拟机个数
		if (hostlist.size() > 0) {
			List<String> hostidlist = new ArrayList<String>();
			for (UnitedTreeObj obj : hostlist) {
				hostidlist.add(obj.getId());
			}
			unitedTreeObj.setPidlist(hostidlist);
			List<UnitedTreeObj> vmlist1 = unitedTreeService.queryForTreeListUseIn(unitedTreeObj);
			vmlist.addAll(vmlist1);
		}

		// 直接挂在集群下的虚拟机个数
		hlist.removeAll(hostlist);
		if (hlist.size() > 0) {
			vmlist.addAll(hlist);
		}
		map.put("vmcount", vmlist.size() + "");
		return "showclusterpage";
	}

	/**
	 * @Title: viewSeriousHost
	 * @Description: 显示异常主机信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-17 下午4:21:55
	 */
	public String viewSeriousHost() {
		TbCloud2HostInfoObj hostInfoObj = new TbCloud2HostInfoObj();
		hostInfoObj.setAllocated(allo);
		hostInfoObj.setPagination(this.getPaginater().initPagination(request));
		if (1 == allo) {// 已分配
			clusterList = this.trimXWorkList(clusterList);
			hostInfoObj.setUuidList(clusterList);
			hostList = hostInfoService.queryHostListByAllocatedSerious(hostInfoObj);
		} else {// 未分配
			unalloclusterList = this.trimXWorkList(unalloclusterList);
			hostInfoObj.setUuidList(unalloclusterList);
			hostList = hostInfoService.queryHostListByUnAllocatedSerious(hostInfoObj);
		}

		// hostInfoObj.setCenterid(centerid);
		// if (!"null".equals(connect_id)) {
		// hostInfoObj.setConnectId(connect_id);
		// }
		// if (1 == allo) {// 已分配的
		// hostInfoObj.setCluid(cluuuid);
		// hostList = hostInfoService
		// .queryHostListByAllocatedSerious(hostInfoObj);
		// } else {// 未分配的
		// hostInfoObj.setCluid(cluid);
		// hostList = hostInfoService
		// .queryHostListByUnAllocatedSerious(hostInfoObj);
		// }
		return "showSeriousHost";
	}

	public String viewHost() {
		TbCloud2HostInfoObj hostInfoObj = new TbCloud2HostInfoObj();
		hostInfoObj.setPagination(this.getPaginater().initPagination(request));
		// if (1 == allo) {// 已分配
		clusterList = this.trimXWorkList(clusterList);
		hostInfoObj.setUuidList(clusterList);
		hostInfoObj.setHasvertual(vtype);
		hostInfoObj.setEq_type(eq_type);
		hostInfoObj.setSTATUS(status);
		hostInfoObj.setCluid(cluid);
		hostList = hostInfoService.queryHostListThroughCluster(hostInfoObj);
		// } else {// 未分配
		// unalloclusterList = this.trimXWorkList(unalloclusterList);
		// hostInfoObj.setUuidList(unalloclusterList);
		// hostList =
		// hostInfoService.queryHostListByUnAllocatedSerious(hostInfoObj);
		// }
		return "viewHost";
	}

	/**
	 * @Title: viewSeriousVM
	 * @Description: 显示异常虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-18 上午10:33:29
	 */
	public String viewSeriousVM() {
		VMHostObj vmHostObj = new VMHostObj();
		vmHostObj.setVH_TYPE(vtype);
		vmHostObj.setPagination(this.getPaginater().initPagination(request));
		clusterList = this.trimXWorkList(clusterList);
		vmHostObj.setClusterList(clusterList);
		parentHostList = this.trimXWorkList(parentHostList);
		vmHostObj.setUuidList(parentHostList);
		vmList = vmHostService.querySeriousVMList(vmHostObj);

		// vmHostObj.setCenterid(centerid);
		// vmHostObj.setCluid(cluuuid);
		// vmHostObj.setHostCode(hostid);
		// if (!"null".equals(connect_id)) {
		// vmHostObj.setConnectId(connect_id);
		// }
		// vmList = vmHostService.querySeriousVMList(vmHostObj);
		return "showSeriousVM";
	}

	public String viewVM() {
		VMHostObj vmHostObj = new VMHostObj();
		vmHostObj.setPagination(this.getPaginater().initPagination(request));
		clusterList = this.trimXWorkList(clusterList);
		vmHostObj.setClusterList(clusterList);
		parentHostList = this.trimXWorkList(parentHostList);
		vmHostObj.setUuidList(parentHostList);
		vmHostObj.setVH_TYPE(vtype);
		vmHostObj.setVH_STAT(status);
		vmList = vmHostService.queryVMListByClusterOrHost(vmHostObj);

		// vmHostObj.setCenterid(centerid);
		// vmHostObj.setCluid(cluuuid);
		// vmHostObj.setHostCode(hostid);
		// if (!"null".equals(connect_id)) {
		// vmHostObj.setConnectId(connect_id);
		// }
		// vmList = vmHostService.querySeriousVMList(vmHostObj);
		return "viewVM";
	}

	/**
	 * @Title: viewCluster
	 * @Description: 查看集群信息
	 * @return String
	 * @throws
	 * @Date 2014-8-2 下午4:30:46
	 * @author lipp
	 * @return
	 * @throws SQLException
	 */
	public String viewCluster() throws SQLException {
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setVtype(vtype);
		unitedTreeObj.setType(UnitedConstant.CLUSTER);
		clusterList = this.trimXWorkList(clusterList);
		unitedTreeObj.setConn_uuidList(clusterList);
		// unitedTreeObj.setPagination(this.getPaginater().initPagination(request));
		ulist = unitedTreeService.queryForListForWorkOrder(unitedTreeObj);
		return "listcluster";
	}

	/**
	 * @Title: viewClusterUnallo
	 * @Description: 查看未分配集群
	 * @return String
	 * @throws
	 * @Date 2014-8-2 下午6:29:35
	 * @author lipp
	 * @return
	 * @throws SQLException
	 */
	public String viewClusterUnallo() throws SQLException {
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setVtype(vtype);
		unitedTreeObj.setType(UnitedConstant.CLUSTER);
		unalloclusterList = this.trimXWorkList(unalloclusterList);
		unitedTreeObj.setUniqueIdList(unalloclusterList);
		// unitedTreeObj.setPagination(this.getPaginater().initPagination(request));
		ulist = unitedTreeService.queryForListForWorkOrder(unitedTreeObj);
		return "listcluster";
	}

	/**
	 * @Title: trimXWorkList
	 * @Description: 将XWorkList转换成普通List
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-3 下午6:17:26
	 */
	private List<String> trimXWorkList(List<String> sourceList) {
		String str = sourceList == null ? "" : sourceList.get(0);
		str = str.replace("[", "");
		str = str.replace("]", "");
		str = str.replaceAll("\\s", "");
		if ("".equals(str)) {
			return null;
		} else {
			String[] arr = str.split(",");
			sourceList = Arrays.asList(arr);
			return sourceList;
		}
	}

	/**
	 * @Title: showHostDetail
	 * @Description: 展现空闲主机资产信息
	 * @param
	 * @return void
	 * @throws
	 * @author JamTau
	 * @version 1.0
	 * @createtime 2014年8月4日
	 */
	public String showHostDetail(){
		
		if (hostDetail == null) {
			hostDetail = new TbCloud2HostInfoObj();
		}
		hostDetail.setEq_id(connect_id);
		hostDetail = hostInfoService.queryByObj(hostDetail);
		return "showHostDetail";
	}
	
	public List<ResourceStatisticsObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<ResourceStatisticsObj> resultList) {
		this.resultList = resultList;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getConnect_id() {
		return connect_id;
	}

	public void setConnect_id(String connect_id) {
		this.connect_id = connect_id;
	}

	public int getClucount() {
		return clucount;
	}

	public void setClucount(int clucount) {
		this.clucount = clucount;
	}

	public int getVmwareclucount() {
		return vmwareclucount;
	}

	public void setVmwareclucount(int vmwareclucount) {
		this.vmwareclucount = vmwareclucount;
	}

	public int getXenclucount() {
		return xenclucount;
	}

	public void setXenclucount(int xenclucount) {
		this.xenclucount = xenclucount;
	}

	public int getHostcount() {
		return hostcount;
	}

	public void setHostcount(int hostcount) {
		this.hostcount = hostcount;
	}

	public int getVmcount() {
		return vmcount;
	}

	public void setVmcount(int vmcount) {
		this.vmcount = vmcount;
	}

	public int getHostruncount() {
		return hostruncount;
	}

	public void setHostruncount(int hostruncount) {
		this.hostruncount = hostruncount;
	}

	public int getHoststopcount() {
		return hoststopcount;
	}

	public void setHoststopcount(int hoststopcount) {
		this.hoststopcount = hoststopcount;
	}

	public int getX86hostcount() {
		return x86hostcount;
	}

	public void setX86hostcount(int x86hostcount) {
		this.x86hostcount = x86hostcount;
	}

	public int getFramehostcount() {
		return framehostcount;
	}

	public void setFramehostcount(int framehostcount) {
		this.framehostcount = framehostcount;
	}

	public int getVmwarecount() {
		return vmwarecount;
	}

	public void setVmwarecount(int vmwarecount) {
		this.vmwarecount = vmwarecount;
	}

	public int getXencount() {
		return xencount;
	}

	public void setXencount(int xencount) {
		this.xencount = xencount;
	}

	public int getVmwareruncount() {
		return vmwareruncount;
	}

	public void setVmwareruncount(int vmwareruncount) {
		this.vmwareruncount = vmwareruncount;
	}

	public int getVmwarestopcount() {
		return vmwarestopcount;
	}

	public void setVmwarestopcount(int vmwarestopcount) {
		this.vmwarestopcount = vmwarestopcount;
	}

	public int getXenruncount() {
		return xenruncount;
	}

	public void setXenruncount(int xenruncount) {
		this.xenruncount = xenruncount;
	}

	public int getXenstopcount() {
		return xenstopcount;
	}

	public void setXenstopcount(int xenstopcount) {
		this.xenstopcount = xenstopcount;
	}

	public int getCpuallcount() {
		return cpuallcount;
	}

	public void setCpuallcount(int cpuallcount) {
		this.cpuallcount = cpuallcount;
	}

	public int getCpuusedcount() {
		return cpuusedcount;
	}

	public void setCpuusedcount(int cpuusedcount) {
		this.cpuusedcount = cpuusedcount;
	}

	public double getMemallcount() {
		return memallcount;
	}

	public void setMemallcount(double memallcount) {
		this.memallcount = memallcount;
	}

	public double getMemusedcount() {
		return memusedcount;
	}

	public void setMemusedcount(double memusedcount) {
		this.memusedcount = memusedcount;
	}

	public double getSrallcount() {
		return srallcount;
	}

	public void setSrallcount(double srallcount) {
		this.srallcount = srallcount;
	}

	public double getSrusedcount() {
		return srusedcount;
	}

	public void setSrusedcount(double srusedcount) {
		this.srusedcount = srusedcount;
	}

	public int getNetcount() {
		return netcount;
	}

	public void setNetcount(int netcount) {
		this.netcount = netcount;
	}

	public int getDomaincount() {
		return domaincount;
	}

	public void setDomaincount(int domaincount) {
		this.domaincount = domaincount;
	}

	public int getSubdomaincount() {
		return subdomaincount;
	}

	public void setSubdomaincount(int subdomaincount) {
		this.subdomaincount = subdomaincount;
	}

	public int getVlancount() {
		return vlancount;
	}

	public void setVlancount(int vlancount) {
		this.vlancount = vlancount;
	}

	public int getIpcount() {
		return ipcount;
	}

	public void setIpcount(int ipcount) {
		this.ipcount = ipcount;
	}

	public int getIpusedcount() {
		return ipusedcount;
	}

	public void setIpusedcount(int ipusedcount) {
		this.ipusedcount = ipusedcount;
	}

	public List<TbCloud2IpInfoObj> getIpList() {
		return ipList;
	}

	public void setIpList(List<TbCloud2IpInfoObj> ipList) {
		this.ipList = ipList;
	}

	public List<DataStoreObj> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<DataStoreObj> storeList) {
		this.storeList = storeList;
	}

	public List<AlarmHostStatistics> getNetList() {
		return netList;
	}

	public void setNetList(List<AlarmHostStatistics> netList) {
		this.netList = netList;
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

	public DataStoreObj getDataStoreObj() {
		return dataStoreObj;
	}

	public void setDataStoreObj(DataStoreObj dataStoreObj) {
		this.dataStoreObj = dataStoreObj;
	}

	public Integer getAllo() {
		return allo;
	}

	public void setAllo(Integer allo) {
		this.allo = allo;
	}

	public List<TbCloud2HostInfoObj> getHostList() {
		return hostList;
	}

	public void setHostList(List<TbCloud2HostInfoObj> hostList) {
		this.hostList = hostList;
	}

	public int getHostseriouscount() {
		return hostseriouscount;
	}

	public void setHostseriouscount(int hostseriouscount) {
		this.hostseriouscount = hostseriouscount;
	}

	public int getVmwareseriouscount() {
		return vmwareseriouscount;
	}

	public void setVmwareseriouscount(int vmwareseriouscount) {
		this.vmwareseriouscount = vmwareseriouscount;
	}

	public int getXenseriouscount() {
		return xenseriouscount;
	}

	public void setXenseriouscount(int xenseriouscount) {
		this.xenseriouscount = xenseriouscount;
	}

	public List<VMHostObj> getVmList() {
		return vmList;
	}

	public void setVmList(List<VMHostObj> vmList) {
		this.vmList = vmList;
	}

	public String getCluid() {
		return cluid;
	}

	public void setCluid(String cluid) {
		this.cluid = cluid;
	}

	public String getCenterid() {
		return centerid;
	}

	public void setCenterid(String centerid) {
		this.centerid = centerid;
	}

	public String getHostid() {
		return hostid;
	}

	public void setHostid(String hostid) {
		this.hostid = hostid;
	}

	public String getCluuuid() {
		return cluuuid;
	}

	public void setCluuuid(String cluuuid) {
		this.cluuuid = cluuuid;
	}

	public int getLocaldevice() {
		return localdevice;
	}

	public void setLocaldevice(int localdevice) {
		this.localdevice = localdevice;
	}

	public int getSANdevice() {
		return SANdevice;
	}

	public void setSANdevice(int sANdevice) {
		SANdevice = sANdevice;
	}

	public int getNASdevice() {
		return NASdevice;
	}

	public void setNASdevice(int nASdevice) {
		NASdevice = nASdevice;
	}

	public StoreDeviceObj getStoreDevice() {
		return storeDevice;
	}

	public void setStoreDevice(StoreDeviceObj storeDevice) {
		this.storeDevice = storeDevice;
	}

	public int getCpuallocount() {
		return cpuallocount;
	}

	public void setCpuallocount(int cpuallocount) {
		this.cpuallocount = cpuallocount;
	}

	public double getMemallocount() {
		return memallocount;
	}

	public void setMemallocount(double memallocount) {
		this.memallocount = memallocount;
	}

	public double getSrallocount() {
		return srallocount;
	}

	public void setSrallocount(double srallocount) {
		this.srallocount = srallocount;
	}

	public String getStoremeasuretype() {
		return storemeasuretype;
	}

	public void setStoremeasuretype(String storemeasuretype) {
		this.storemeasuretype = storemeasuretype;
	}

	public int getCentercount() {
		return centercount;
	}

	public void setCentercount(int centercount) {
		this.centercount = centercount;
	}

	public int getUnalloclucount() {
		return unalloclucount;
	}

	public void setUnalloclucount(int unalloclucount) {
		this.unalloclucount = unalloclucount;
	}

	public int getHostallo() {
		return hostallo;
	}

	public void setHostallo(int hostallo) {
		this.hostallo = hostallo;
	}

	public int getHostunallo() {
		return hostunallo;
	}

	public void setHostunallo(int hostunallo) {
		this.hostunallo = hostunallo;
	}

	public int getHostunalloruncount() {
		return hostunalloruncount;
	}

	public void setHostunalloruncount(int hostunalloruncount) {
		this.hostunalloruncount = hostunalloruncount;
	}

	public int getHostunallostopcount() {
		return hostunallostopcount;
	}

	public void setHostunallostopcount(int hostunallostopcount) {
		this.hostunallostopcount = hostunallostopcount;
	}

	public int getHostunalloseriouscount() {
		return hostunalloseriouscount;
	}

	public void setHostunalloseriouscount(int hostunalloseriouscount) {
		this.hostunalloseriouscount = hostunalloseriouscount;
	}

	public int getFramehostunallocount() {
		return framehostunallocount;
	}

	public void setFramehostunallocount(int framehostunallocount) {
		this.framehostunallocount = framehostunallocount;
	}

	public int getX86hostunallocount() {
		return x86hostunallocount;
	}

	public void setX86hostunallocount(int x86hostunallocount) {
		this.x86hostunallocount = x86hostunallocount;
	}

	public List<String> getParentHostList() {
		return parentHostList;
	}

	public void setParentHostList(List<String> parentHostList) {
		this.parentHostList = parentHostList;
	}

	public List<String> getClusterList() {
		return clusterList;
	}

	public void setClusterList(List<String> clusterList) {
		this.clusterList = clusterList;
	}

	public List<String> getUnalloclusterList() {
		return unalloclusterList;
	}

	public void setUnalloclusterList(List<String> unalloclusterList) {
		this.unalloclusterList = unalloclusterList;
	}

	public List<NasStorePoolObj> getNasStorePoolList() {
		return nasStorePoolList;
	}

	public void setNasStorePoolList(List<NasStorePoolObj> nasStorePoolList) {
		this.nasStorePoolList = nasStorePoolList;
	}

	public NasKpiObj getNasKpiObj() {
		return nasKpiObj;
	}

	public void setNasKpiObj(NasKpiObj nasKpiObj) {
		this.nasKpiObj = nasKpiObj;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEq_type() {
		return eq_type;
	}

	public void setEq_type(String eq_type) {
		this.eq_type = eq_type;
	}

	public List<UnitedTreeObj> getUlist() {
		return ulist;
	}

	public void setUlist(List<UnitedTreeObj> ulist) {
		this.ulist = ulist;
	}

	public TbCloud2HostInfoObj getHostDetail() {
		return hostDetail;
	}

	public void setHostDetail(TbCloud2HostInfoObj hostDetail) {
		this.hostDetail = hostDetail;
	}

}
