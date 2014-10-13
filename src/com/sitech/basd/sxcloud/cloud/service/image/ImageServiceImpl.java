package com.sitech.basd.sxcloud.cloud.service.image;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.sitech.basd.monitor.MonitorUtil;
import com.sitech.basd.rest.appliance.domain.ApplianceInfo;
import com.sitech.basd.rest.appliance.domain.ApplianceTargetInfo;
import com.sitech.basd.rest.appliance.domain.AppliancesIdCustPropInfo;
import com.sitech.basd.rest.appliance.domain.AppliancesIdCustomizationInfo;
import com.sitech.basd.rest.appliance.domain.AppliancesInfo;
import com.sitech.basd.rest.appliance.operation.ApplianceOperation;
import com.sitech.basd.rest.project.operation.ProjectOperation;
import com.sitech.basd.rest.virtualservers.domain.VirtualServersInfo;
import com.sitech.basd.rest.virtualservers.domain.VirtualServersNetworkInfo;
import com.sitech.basd.rest.virtualservers.domain.VirtualServersStorageInfo;
import com.sitech.basd.rest.virtualservers.operation.VirtualServicesOperation;
import com.sitech.basd.rest.workloads.domain.VirtualServerCredentialsInfo;
import com.sitech.basd.rest.workloads.domain.WorkloadInfo;
import com.sitech.basd.rest.workloads.domain.WorkloadsIdCustPropInfo;
import com.sitech.basd.rest.workloads.domain.WorkloadsIdCustomizationInfo;
import com.sitech.basd.rest.workloads.domain.WorkloadsPutPropInfo;
import com.sitech.basd.rest.workloads.domain.WorkloadsPutPropertiesInfo;
import com.sitech.basd.rest.workloads.domain.WorkloadsTimeStampsInfo;
import com.sitech.basd.rest.workloads.operation.WorkloadsOperation;
import com.sitech.basd.sxcloud.cloud.dao.alarm.TbCloud2MonitorAlarmDao;
import com.sitech.basd.sxcloud.cloud.dao.globalconfig.TbGlobalConfigDao;
import com.sitech.basd.sxcloud.cloud.dao.image.TbImageInfoDao;
import com.sitech.basd.sxcloud.cloud.dao.virtual.TbVirtualDao;
import com.sitech.basd.sxcloud.cloud.domain.alarm.TbCloud2MonitorAlarmObj;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.image.TbCloud2ImageInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.image.TbCloud2ImageTargetObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.web.image.form.ImageForm;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class ImageServiceImpl extends BaseService implements ImageService {

	/**
	 * @Title:删除已有镜像
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int deleteByObj(TbCloud2ImageInfoObj obj) {
		boolean flag = false;
		{
			// 默认从接口获取数据
			// 获取数据成功，则flag设为true
		}
		if (!flag) {
			return tbImageInfoDao.deleteByObj(obj);
		}
		return tbImageInfoDao.deleteByObj(obj);
	}

	/**
	 * @Title:创建虚拟镜像
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int insertByObj(TbCloud2ImageInfoObj obj) {
		boolean flag = false;
		{
			// 默认从接口获取数据
			// 获取数据成功，则flag设为true
		}
		if (!flag) {
			return tbImageInfoDao.insertByObj(obj);
		}
		return tbImageInfoDao.insertByObj(obj);
	}

	/**
	 * @Title:查询并返回一个虚拟镜像对象
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public TbCloud2ImageInfoObj queryByObj(TbCloud2ImageInfoObj obj) {
		try {
			AppliancesIdCustomizationInfo acInfo = ApplianceOperation
					.getAppliancesIdCustomization(obj.getIM_ID());
			ApplianceInfo aInfo = ApplianceOperation.getApplianceById(obj
					.getIM_ID());
			String log = ApplianceOperation.getAppliancesIdLog(obj.getIM_ID());
			List<AppliancesIdCustPropInfo> properties = acInfo.getProperties();
			Map<String, String> dataMap = new HashMap<String, String>();
			for (AppliancesIdCustPropInfo acpInfo : properties) {
				dataMap.put(acpInfo.getName(), acpInfo.getValue());
			}
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy/MM/dd hh:mm:ss");
			TbCloud2ImageInfoObj tiObj = new TbCloud2ImageInfoObj();
			tiObj.setIM_ID(obj.getIM_ID());
			tiObj.setIM_CPU(dataMap.get("cpushared"));
			tiObj.setIM_CPU_MODE(dataMap.get("cpumode"));
			tiObj.setIM_MAX_CPU(dataMap.get("cpushmax"));
			tiObj.setIM_MAX_MEM(dataMap.get("memmax"));
			tiObj.setIM_MAX_PROCESS_UNIT(dataMap.get("cpushmaxu"));
			tiObj.setIM_MEM(dataMap.get("memsize"));
			tiObj.setIM_MIN_CPU(dataMap.get("cpushmin"));
			tiObj.setIM_MIN_MEM(dataMap.get("memmin"));
			tiObj.setIM_MIN_PROCESS_UNIT(dataMap.get("cpushminu"));
			tiObj.setCHANGED_DATE(aInfo.getChangedDate());
			tiObj.setIM_DESC(aInfo.getDescription());
			tiObj.setIM_NAME(aInfo.getName());
			tiObj.setIM_PROCESS_UNIT(dataMap.get("cpushu"));
			tiObj.setIM_PROCESS_UNIT_MODE(dataMap.get("cpushmode"));
			tiObj.setIM_CPU_MODE(dataMap.get("cpumode"));
			tiObj.setIM_PROCESS_UNIT_Priority(dataMap.get("cpushpri"));
			tiObj.setIM_STATE(aInfo.getState());
			tiObj.setIM_LOG(log);
			tiObj.setIM_ROOT_PASS(dataMap.get("rootpassword"));
			if (tiObj.getIM_NAME() != null) {
				return tiObj;
			} else {
				return tbImageInfoDao.queryByObj(obj);
			}
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
			return null;
		}
	}

	/**
	 * 
	 * @Title: getImageIdTargets
	 * @Description: 获取虚拟镜像目标列表
	 * @param
	 * @return List<TbCloud2ImageTargetObj>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2012-3-1 下午04:11:51
	 */
	public List<TbCloud2ImageTargetObj> getImageIdTargets(
			TbCloud2ImageInfoObj obj) {
		List<ApplianceTargetInfo> list = null;
		try {
			list = ApplianceOperation.getApplianceIdTargets(obj.getIM_ID());
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
			return tbImageInfoDao.queryForListByObj(obj);
		}
		List<TbCloud2ImageTargetObj> tList = new ArrayList<TbCloud2ImageTargetObj>();
		for (ApplianceTargetInfo info : list) {
			TbCloud2ImageTargetObj tObj = new TbCloud2ImageTargetObj();
			tObj.setId(info.getId());
			tObj.setName(info.getName());
			tList.add(tObj);
		}
		return tList;
	}

	/**
	 * @Title:查询已有虚拟镜像列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public List queryForListByObj(TbCloud2ImageInfoObj obj) {
		/** 目前项目ID写死，待确定project选择后，进行修改 */
		List<AppliancesInfo> infoList = null;
		try {
			infoList = ProjectOperation.getProjectsIdAppliances("51");
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
		}
		if (infoList != null) {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(infoList.size());
			}
			if (infoList != null && infoList.size() > 0) {
				List list = new ArrayList();
				for (AppliancesInfo info : infoList) {
					TbCloud2ImageInfoObj tObj = new TbCloud2ImageInfoObj();
					tObj.setIM_ID(info.getId());
					tObj.setIM_DESC(info.getDescription());
					tObj.setIM_NAME(info.getName());
					if ("OK".equals(info.getState())) {
						tObj.setIM_STATE("2");
					}
					if ("UNKNOWN".equals(info.getState())) {
						tObj.setIM_STATE("1");
					}
					if ("FAILED".equals(info.getState())) {
						tObj.setIM_STATE("0");
					}
					tObj.setIM_VERSION(info.getVersion());
					list.add(tObj);
				}
				return list;
			} else {
				return tbImageInfoDao.queryForListByObj(obj);
			}
		} else {
			return tbImageInfoDao.queryForListByObj(obj);
		}
	}

	/**
	 * @Title:更新虚拟镜像信息
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int updateByObj(TbCloud2ImageInfoObj obj) {
		boolean flag = false;
		{
			// 默认从接口获取数据
			// 获取数据成功，则flag设为true
		}
		if (!flag) {
			return tbImageInfoDao.deleteByObj(obj);
		}
		return tbImageInfoDao.updateByObj(obj);
	}

	/**
	 * @Title:部署虚拟映像
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public WorkloadInfo deployImage(TbCloud2ImageInfoObj obj) {
		try {
			WorkloadsIdCustomizationInfo info = WorkloadsOperation
					.postWorkload("appliance", obj.getIM_ID());
			WorkloadInfo workloadInfo = WorkloadsOperation
					.getWorkloadsById(info.getId());
			List<WorkloadsTimeStampsInfo> timeStampList = WorkloadsOperation
					.getWorkloadsIdTimestamps(info.getId());
			WorkloadsTimeStampsInfo timeStampInfo = timeStampList.get(0);
			String log = WorkloadsOperation.getWorkloadsIdLog(info.getId());
			List<VirtualServersInfo> vsInfoList = WorkloadsOperation
					.getWorkloadsIdVirtualServers(info.getId());
			VirtualServersInfo visualServer = new VirtualServersInfo();
			if (vsInfoList != null && vsInfoList.size() > 0) {
				visualServer = vsInfoList.get(0);
			}
			List<VirtualServersNetworkInfo> vsNetworkList = null;
			List<VirtualServersStorageInfo> vsStroageInfoList = null;
			VirtualServerCredentialsInfo vsCredInfo = new VirtualServerCredentialsInfo();
			if (visualServer.getId() != null) {
				vsNetworkList = VirtualServicesOperation
						.getVirtualServersByIdNetworks(visualServer.getId());
				vsStroageInfoList = VirtualServicesOperation
						.getVirtualServersByIdStorages(visualServer.getId());
				vsCredInfo = WorkloadsOperation
						.getWorkloadsIdVirtualServersIdCredentials(
								info.getId(), visualServer.getId());
			}
			String stroageResult = "";
			if (vsStroageInfoList != null && vsStroageInfoList.size() > 0) {
				for (VirtualServersStorageInfo vss : vsStroageInfoList) {
					stroageResult += vss.getName() + ":" + vss.getSize() + ",";
				}
			}
			String vsNetwork = "";
			if (vsNetworkList != null && vsNetworkList.size() > 0) {
				for (VirtualServersNetworkInfo vsnInfo : vsNetworkList) {
					vsNetwork += vsnInfo.getNetworkId() + ",";
				}
			}
			if (info == null) {
				return null;
			}
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy/MM/dd hh:mm:ss");
			Map<String, String> dataMap = new HashMap<String, String>();
			TbCloud2VirtualInfoObj vObj = new TbCloud2VirtualInfoObj();
			vObj.setVH_ID(info.getId());
			List<WorkloadsIdCustPropInfo> properties = info.getProperties();
			for (WorkloadsIdCustPropInfo wInfo : properties) {
				dataMap.put(wInfo.getName(), wInfo.getValue());
			}
			vObj.setVH_ID(info.getId());
			vObj.setVH_ID_IBM(info.getId());
			vObj.setVH_CPU(dataMap.get("cpushared"));
			vObj.setVH_CPU_MODE(dataMap.get("cpumode"));
			vObj.setVH_MAX_CPU(dataMap.get("cpushmax"));
			vObj.setVH_MAX_MEM(dataMap.get("memmax"));
			vObj.setVH_MAX_PROCESS_UNIT(dataMap.get("cpushmaxu"));
			vObj.setVH_MEM(dataMap.get("memsize"));
			vObj.setVH_MIN_CPU(dataMap.get("cpushmin"));
			vObj.setVH_MIN_MEM(dataMap.get("memmin"));
			vObj.setVH_MIN_PROCESS_UNIT(dataMap.get("cpushminu"));
			vObj.setVH_DESC(workloadInfo.getDescription());
			vObj.setVH_NAME(workloadInfo.getName());
			vObj.setVH_PROCESS_UNIT(dataMap.get("cpushu"));
			vObj.setVH_PROCESS_UNIT_MODE(dataMap.get("cpushmode"));
			vObj.setVH_CPU_MODE(dataMap.get("cpumode"));
			vObj.setVH_RUNTIME(format
					.format(new Date(timeStampInfo.getUptime())));
			vObj.setVH_COMPLETED(format.format(new Date(timeStampInfo
					.getCompleted())));
			vObj.setVH_DEPLOYETIME(format.format(new Date(timeStampInfo
					.getDeployTime())));
			vObj.setVH_STARTED(format.format(new Date(timeStampInfo
					.getStarted())));
			vObj.setVH_STAT(workloadInfo.getState());
			vObj.setVH_LOG(log);
			vObj.setVH_USER(vsCredInfo.getUser());
			vObj.setVH_PASS(vsCredInfo.getPassword());
			if ("".equals(stroageResult)) {
				vObj.setVH_STORAGE(null);
			} else {
				vObj.setVH_STORAGE(stroageResult);
			}
			vObj.setVH_IP(visualServer.getIp());
			if ("".equals(vsNetwork)) {
				vObj.setVH_NETWORK(null);
			} else {
				vObj.setVH_NETWORK(vsNetwork);
			}
			tbVirtualDao.insertByObj(vObj);
			return workloadInfo;
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
			return null;
		}
	}

	/**
	 * 
	 * @Title: deployVirtualStatToRunStat
	 * @Description: 部署镜像成为运行状态虚拟机
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 18, 2012 4:19:00 PM
	 */
	public int deployVirtualStatToRunStat(TbCloud2VirtualInfoObj obj) {
		/** 检测资源是否充足 */
		// 由于IBM即将升级，暂未进行资源判断
		// if (checkResourceIfOK()) {
		if (true) {
			WorkloadsPutPropInfo wppInfo = new WorkloadsPutPropInfo();
			wppInfo.setName(obj.getVH_NAME());
			wppInfo.setProject(obj.getPROJECT_ID());
			wppInfo.setDescription(obj.getVH_DESC());
			wppInfo.setState("EXECUTING");
			List<WorkloadsPutPropertiesInfo> properties = new ArrayList<WorkloadsPutPropertiesInfo>();
			if (obj.getVH_CPU() != null && !"".equals(obj.getVH_CPU())) {
				WorkloadsPutPropertiesInfo info = new WorkloadsPutPropertiesInfo();
				info.setName("cskCPU");
				info.setValue(obj.getVH_CPU());
				info.setBasic(true);
				properties.add(info);
			}
			if (obj.getVH_MEM() != null && !"".equals(obj.getVH_MEM())) {
				WorkloadsPutPropertiesInfo info = new WorkloadsPutPropertiesInfo();
				info.setName("cskRAM");
				info.setValue(obj.getVH_MEM());
				info.setBasic(true);
				properties.add(info);
			}
			wppInfo.setProperties(properties);
			boolean result = false;
			try {
				result = WorkloadsOperation.putWorkloadById(obj.getVH_ID_IBM(),
						wppInfo);
			} catch (Exception e) {
				LogHelper.error("KVM Or Director Connect Error："
						+ e.getMessage());
				/** 插入告警 */
				tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
			}
			if (result) {
				return tbVirtualDao.updateByObj(obj);
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}

	/**
	 * 
	 * @Title: advanceddeployVirtualStatToRunStat
	 * @Description: 高级部署镜像成为运行状态虚拟机
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 20, 2012 9:41:58 AM
	 */
	public int advanceddeployVirtualStatToRunStat(TbCloud2VirtualInfoObj obj) {
		/** 检测资源是否充足 */
		// 由于IBM即将升级，暂未进行资源判断
		// if (checkResourceIfOK()) {
		if (true) {
			WorkloadsPutPropInfo wppInfo = new WorkloadsPutPropInfo();
			wppInfo.setName(obj.getVH_NAME());
			wppInfo.setProject(obj.getPROJECT_ID());
			wppInfo.setDescription(obj.getVH_DESC());
			List<WorkloadsPutPropertiesInfo> properties = new ArrayList<WorkloadsPutPropertiesInfo>();
			if (obj.getVH_CPU() != null && !"".equals(obj.getVH_CPU())) {
				WorkloadsPutPropertiesInfo info = new WorkloadsPutPropertiesInfo();
				info.setName("cpushu");
				info.setValue(obj.getVH_CPU());
				info.setBasic(true);
				properties.add(info);
			}
			if (obj.getVH_MEM() != null && !"".equals(obj.getVH_MEM())) {
				WorkloadsPutPropertiesInfo info = new WorkloadsPutPropertiesInfo();
				info.setName("memsize");
				info.setValue(obj.getVH_MEM());
				properties.add(info);
			}
			if (obj.getVH_NETWORK() != null && !"".equals(obj.getVH_NETWORK())) {
				WorkloadsPutPropertiesInfo info = new WorkloadsPutPropertiesInfo();
				info.setName("product.AIX1.com.ibm.ovf.vim.2.system.hostname");
				info.setValue("{" + obj.getVH_NETWORK() + "}");
				properties.add(info);
				WorkloadsPutPropertiesInfo info1 = new WorkloadsPutPropertiesInfo();
				info1
						.setName("product.AIX1.com.ibm.ovf.vim.2.system.domainname");
				info1.setValue("{" + obj.getVH_NETWORK() + "}");
				properties.add(info1);
				WorkloadsPutPropertiesInfo info2 = new WorkloadsPutPropertiesInfo();
				info2
						.setName("product.AIX1.com.ibm.ovf.vim.2.networkport.6.ip");
				info2.setValue("{" + obj.getVH_NETWORK() + "}");
				properties.add(info2);
				WorkloadsPutPropertiesInfo info3 = new WorkloadsPutPropertiesInfo();
				info3
						.setName("product.AIX1.com.ibm.ovf.vim.2.networkport.6.hostname");
				info3.setValue("{" + obj.getVH_NETWORK() + "}");
				properties.add(info3);
				WorkloadsPutPropertiesInfo info4 = new WorkloadsPutPropertiesInfo();
				info4
						.setName("product.AIX1.com.ibm.ovf.vim.2.networkport.6.gateway");
				info4.setValue("{" + obj.getVH_NETWORK() + "}");
				properties.add(info4);
				WorkloadsPutPropertiesInfo info5 = new WorkloadsPutPropertiesInfo();
				info5
						.setName("product.AIX1.com.ibm.ovf.vim.2.networkport.6.netmask");
				info5.setValue("{" + obj.getVH_NETWORK() + "}");
				properties.add(info5);
				WorkloadsPutPropertiesInfo info6 = new WorkloadsPutPropertiesInfo();
				info6
						.setName("product.AIX1.com.ibm.ovf.vim.2.networkport.6.dns1.ip");
				info6.setValue("{" + obj.getVH_NETWORK() + "}");
				properties.add(info6);
				WorkloadsPutPropertiesInfo info7 = new WorkloadsPutPropertiesInfo();
				info7
						.setName("product.AIX1.com.ibm.ovf.vim.2.networkport.6.dns2.ip");
				info7.setValue("{" + obj.getVH_NETWORK() + "}");
				properties.add(info7);
				WorkloadsPutPropertiesInfo info8 = new WorkloadsPutPropertiesInfo();
				info8
						.setName("product.AIX1.com.ibm.ovf.vim.2.networkport.6.domainname");
				info8.setValue("{" + obj.getVH_NETWORK() + "}");
				properties.add(info8);
				WorkloadsPutPropertiesInfo info9 = new WorkloadsPutPropertiesInfo();
				info9.setName("virtualnetworks-1");
				info9.setValue("{" + obj.getVH_NETWORK() + "}");
				properties.add(info9);
			}
			if (obj.getVH_NETWORK_DOUBLE() != null
					&& !"".equals(obj.getVH_NETWORK_DOUBLE())) {
				WorkloadsPutPropertiesInfo info2 = new WorkloadsPutPropertiesInfo();
				info2
						.setName("product.AIX1.com.ibm.ovf.vim.2.networkport.7.ip");
				info2.setValue("{" + obj.getVH_NETWORK_DOUBLE() + "}");
				properties.add(info2);
				WorkloadsPutPropertiesInfo info3 = new WorkloadsPutPropertiesInfo();
				info3
						.setName("product.AIX1.com.ibm.ovf.vim.2.networkport.7.hostname");
				info3.setValue("{" + obj.getVH_NETWORK_DOUBLE() + "}");
				properties.add(info3);
				WorkloadsPutPropertiesInfo info4 = new WorkloadsPutPropertiesInfo();
				info4
						.setName("product.AIX1.com.ibm.ovf.vim.2.networkport.7.gateway");
				info4.setValue("{" + obj.getVH_NETWORK_DOUBLE() + "}");
				properties.add(info4);
				WorkloadsPutPropertiesInfo info5 = new WorkloadsPutPropertiesInfo();
				info5
						.setName("product.AIX1.com.ibm.ovf.vim.2.networkport.7.netmask");
				info5.setValue("{" + obj.getVH_NETWORK_DOUBLE() + "}");
				properties.add(info5);
				WorkloadsPutPropertiesInfo info6 = new WorkloadsPutPropertiesInfo();
				info6
						.setName("product.AIX1.com.ibm.ovf.vim.2.networkport.7.dns1.ip");
				info6.setValue("{" + obj.getVH_NETWORK_DOUBLE() + "}");
				properties.add(info6);
				WorkloadsPutPropertiesInfo info7 = new WorkloadsPutPropertiesInfo();
				info7
						.setName("product.AIX1.com.ibm.ovf.vim.2.networkport.7.dns2.ip");
				info7.setValue("{" + obj.getVH_NETWORK_DOUBLE() + "}");
				properties.add(info7);
				WorkloadsPutPropertiesInfo info8 = new WorkloadsPutPropertiesInfo();
				info8
						.setName("product.AIX1.com.ibm.ovf.vim.2.networkport.7.domainname");
				info8.setValue("{" + obj.getVH_NETWORK_DOUBLE() + "}");
				properties.add(info8);
				WorkloadsPutPropertiesInfo info9 = new WorkloadsPutPropertiesInfo();
				info9.setName("virtualnetworks-2");
				info9.setValue("{" + obj.getVH_NETWORK_DOUBLE() + "}");
				properties.add(info9);
			}
			if (obj.getVH_MAX_CPU() != null && !"".equals(obj.getVH_MAX_CPU())) {
				WorkloadsPutPropertiesInfo info = new WorkloadsPutPropertiesInfo();
				info.setName("cpushmax");
				info.setValue(obj.getVH_MAX_CPU());
				properties.add(info);
			}
			if (obj.getVH_MIN_CPU() != null && !"".equals(obj.getVH_MIN_CPU())) {
				WorkloadsPutPropertiesInfo info = new WorkloadsPutPropertiesInfo();
				info.setName("cpushmin");
				info.setValue(obj.getVH_MIN_CPU());
				properties.add(info);
			}
			if (obj.getVH_MAX_MEM() != null && !"".equals(obj.getVH_MAX_MEM())) {
				WorkloadsPutPropertiesInfo info = new WorkloadsPutPropertiesInfo();
				info.setName("memmax");
				info.setValue(obj.getVH_MAX_MEM());
				properties.add(info);
			}
			if (obj.getVH_MIN_MEM() != null && !"".equals(obj.getVH_MIN_MEM())) {
				WorkloadsPutPropertiesInfo info = new WorkloadsPutPropertiesInfo();
				info.setName("memmin");
				info.setValue(obj.getVH_MIN_MEM());
				properties.add(info);
			}
			if (obj.getVH_PROCESS_UNIT() != null
					&& !"".equals(obj.getVH_PROCESS_UNIT())) {
				WorkloadsPutPropertiesInfo info = new WorkloadsPutPropertiesInfo();
				info.setName("cpushu");
				info.setValue(obj.getVH_PROCESS_UNIT());
				properties.add(info);
			}
			if (obj.getVH_MAX_PROCESS_UNIT() != null
					&& !"".equals(obj.getVH_MAX_PROCESS_UNIT())) {
				WorkloadsPutPropertiesInfo info = new WorkloadsPutPropertiesInfo();
				info.setName("cpushmaxu");
				info.setValue(obj.getVH_MAX_PROCESS_UNIT());
				properties.add(info);
			}
			if (obj.getVH_MIN_PROCESS_UNIT() != null
					&& !"".equals(obj.getVH_MIN_PROCESS_UNIT())) {
				WorkloadsPutPropertiesInfo info = new WorkloadsPutPropertiesInfo();
				info.setName("cpushminu");
				info.setValue(obj.getVH_MIN_PROCESS_UNIT());
				properties.add(info);
			}
			if (obj.getVH_CPU_MODE() != null
					&& !"".equals(obj.getVH_CPU_MODE())) {
				WorkloadsPutPropertiesInfo info = new WorkloadsPutPropertiesInfo();
				info.setName("cpumode");
				info.setValue(obj.getVH_CPU_MODE());
				if ("0".equals(obj.getVH_CPU_MODE())) {
					info.setValue("SHARED");
				} else if ("1".equals(obj.getVH_CPU_MODE())) {
					info.setValue("DEDICATED");
				}
				properties.add(info);
			}
			if (obj.getVH_PROCESS_UNIT_Priority() != null
					&& !"".equals(obj.getVH_PROCESS_UNIT_Priority())) {
				WorkloadsPutPropertiesInfo info = new WorkloadsPutPropertiesInfo();
				info.setName("cpushpri");
				info.setValue(obj.getVH_PROCESS_UNIT_Priority());
				properties.add(info);
			}
			if (obj.getVH_PROCESS_UNIT_MODE() != null
					&& !"".equals(obj.getVH_PROCESS_UNIT_MODE())) {
				WorkloadsPutPropertiesInfo info = new WorkloadsPutPropertiesInfo();
				info.setName("cpushmode");
				if ("1".equals(obj.getVH_PROCESS_UNIT_MODE())) {
					info.setValue("CAP");
				} else if ("0".equals(obj.getVH_PROCESS_UNIT_MODE())) {
					info.setValue("UNCAP");
				}
				properties.add(info);
			}
			wppInfo.setProperties(properties);
			wppInfo.setState("EXECUTING");
			boolean result = false;
			try {
				result = WorkloadsOperation.putWorkloadById(obj.getVH_ID_IBM(),
						wppInfo);
			} catch (Exception e) {
				LogHelper.error("KVM Or Director Connect Error："
						+ e.getMessage());
				/** 插入告警 */
				tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
			}
			if (result) {
				return tbVirtualDao.updateByObj(obj);
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}

	/**
	 * 
	 * @Title: checkMapIfHasFalse
	 * @Description: 核查
	 * @param
	 * @return boolean
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 29, 2012 10:11:05 AM
	 */
	public Map<Boolean, String> checkMapIfHasFalse(Map<String, String> map) {
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		Map<Boolean, String> resMap = new HashMap<Boolean, String>();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			String key = entry.getKey();
			String value = entry.getValue();
			boolean b = Boolean.parseBoolean(key.split(",")[0]);
			if (!b) {
				resMap.put(b, value);
			}
		}
		return resMap;
	}

	/**
	 * 
	 * @Title: checkResourceIfOK
	 * @Description: 检测资源是否符合要求，若符合则进行虚拟机创建，若不符合，则告警
	 * @param
	 * @return boolean
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 29, 2012 10:26:41 AM
	 */
	public boolean checkResourceIfOK() {
		TbGlobalConfigObj obj = new TbGlobalConfigObj();
		obj.setKEY("Virtual_CPU_Threshold");
		double CPU = Double.parseDouble(tbGlobalConfigDao.queryByObj(obj)
				.getVALUE());
		obj.setKEY("Virtual_Mem_Threshold");
		double mem = Double.parseDouble(tbGlobalConfigDao.queryByObj(obj)
				.getVALUE());
		obj.setKEY("Virtual_Storage_Threshold");
		double storage = Double.parseDouble(tbGlobalConfigDao.queryByObj(obj)
				.getVALUE());
		try {
			Map<String, String> directorMap = MonitorUtil
					.checkImageIfCanDeployToVirtualByDirector(CPU, storage, mem);
			Map<String, String> skcMap = MonitorUtil
					.checkImageIfCanDeployToVirtualBySkc(CPU, storage, mem);
			/** 检测资源池 */
			Map<Boolean, String> directorResultMap = checkMapIfHasFalse(directorMap);
			/** 检测SKC资源 */
			Map<Boolean, String> skcResultMap = checkMapIfHasFalse(skcMap);
			String pattern = "yyyyMMddHHmmss";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			TbCloud2MonitorAlarmObj alarmObj = new TbCloud2MonitorAlarmObj();
			alarmObj.setALARM_TIME(dateFormat.format(new Date()));
			alarmObj.setEVENT_LEVEL("0");// 0严重告警,1主要告警，2次要告警，3不确定告警
			alarmObj.setEVENT_LOCATION("模板创建或者调整虚拟机(后台处理程序)");
			alarmObj.setEVENT_STAT("0");// 默认0未处理状态
			alarmObj.setEVENT_TYPE("1");// 0应用告警，1虚拟机，2物理主机，3机房告警
			if (directorResultMap != null && directorResultMap.size() > 0) {
				alarmObj.setTITILE("资源池资源不足告警");
				alarmObj.setCONTENT("资源池(Director)检测资源不足告警："
						+ directorResultMap.get(false));
				tbCloud2MonitorAlarmDao.insertOrUpdateAlarm(alarmObj);
				return false;
			} else if (skcResultMap != null && skcResultMap.size() > 0) {
				alarmObj.setTITILE("SKC资源不足告警");
				alarmObj.setCONTENT("SCK(IBM-SKC)检测资源不足告警："
						+ skcResultMap.get(false));
				tbCloud2MonitorAlarmDao.insertOrUpdateAlarm(alarmObj);
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
			return false;
		}
	}

	/**
	 * @Title:保存虚拟映像
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public boolean saveImage(ImageForm form) {
		return true;
	}

	/**
	 * @Title:移至项目更新PROJECT_ID
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0i
	 */

	public int updateByProjectIdObj(TbCloud2ImageInfoObj obj) {
		ApplianceInfo ap = new ApplianceInfo();
		// ap.setName(obj.getIM_NAME());
		ap.setProject(obj.getPROJECT_ID());
		boolean b = ApplianceOperation.updateApplianceProjectById(obj
				.getIM_ID(), ap);
		return 1;
	}

	/**
	 * @Title:查询虚拟镜像类型列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public List queryForTypeList(TbCloud2ImageInfoObj obj) {
		return tbImageInfoDao.queryForTypeList(obj);

	}

	/**
	 * 
	 * @Title: updateName
	 * @Description: 只更新镜像名称
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 2, 2012 2:51:31 PM
	 */
	public int updateName(TbCloud2ImageInfoObj obj) {
		return tbImageInfoDao.updateName(obj);
	}

	/**
	 * 
	 * @Title:查询虚拟机镜像
	 * @Copyright: Copyright (c) 2012-9-14
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public List queryVMHostByVMObj(VMHostObj obj) {
		return tbImageInfoDao.queryVMHostByVMObj(obj);
	}

	private TbImageInfoDao tbImageInfoDao;
	private TbVirtualDao tbVirtualDao;
	private TbCloud2MonitorAlarmDao tbCloud2MonitorAlarmDao;
	private TbGlobalConfigDao tbGlobalConfigDao;

	public void setTbCloud2MonitorAlarmDao(
			TbCloud2MonitorAlarmDao tbCloud2MonitorAlarmDao) {
		this.tbCloud2MonitorAlarmDao = tbCloud2MonitorAlarmDao;
	}

	public void setTbVirtualDao(TbVirtualDao tbVirtualDao) {
		this.tbVirtualDao = tbVirtualDao;
	}

	public void setTbImageInfoDao(TbImageInfoDao tbImageInfoDao) {
		this.tbImageInfoDao = tbImageInfoDao;
	}

	public void setTbGlobalConfigDao(TbGlobalConfigDao tbGlobalConfigDao) {
		this.tbGlobalConfigDao = tbGlobalConfigDao;
	}

}
