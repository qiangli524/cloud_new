package com.sitech.basd.sxcloud.cloud.service.virtual;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.sitech.basd.sxcloud.cloud.dao.virtual.TbVirtualDao;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualInfoObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class VirtualServiceImpl extends BaseService implements VirtualService {

	/**
	 * @Title:删除已有虚拟机
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int deleteByObj(TbCloud2VirtualInfoObj obj) {
		boolean result = false;
		try {
			result = WorkloadsOperation.deleteWorkloadsById(obj.getVH_ID_IBM(),
					true);
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
		}
		if (result) {
			return tbVirtualDao.deleteByObj(obj);
		} else {
			return -1;
		}
	}

	/**
	 * @Title:创建虚拟机
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int insertByObj(TbCloud2VirtualInfoObj obj) {
		boolean flag = false;
		{
			// 默认从接口获取数据
			// 获取数据成功，则flag设为true
		}
		if (!flag) {
			return tbVirtualDao.insertByObj(obj);
		}
		return tbVirtualDao.insertByObj(obj);
	}

	/**
	 * @Title:查询并返回一个虚拟机对象
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public TbCloud2VirtualInfoObj queryByObj(TbCloud2VirtualInfoObj obj) {
		boolean flag = false;
		if (!flag) {
			return tbVirtualDao.queryByObj(obj);
		}
		return tbVirtualDao.queryByObj(obj);
	}

	/**
	 * @Title:查询已有虚拟机列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(TbCloud2VirtualInfoObj obj) {
		/*
		 * boolean flag = false; { //默认从接口获取数据 //获取数据成功，则flag设为true } if(!flag){
		 * return tbVirtualDao.queryForListByObj(obj); }
		 */
		return tbVirtualDao.queryForListByObj(obj);
	}

	/**
	 * @Title:更新虚拟机信息
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int updateByObj(TbCloud2VirtualInfoObj obj) {
		WorkloadsPutPropInfo wppInfo = new WorkloadsPutPropInfo();
		wppInfo.setDescription(obj.getVH_DESC());
		wppInfo.setName(obj.getVH_NAME());
		boolean result = false;
		try {
			result = WorkloadsOperation.putWorkloadById(obj.getVH_ID_IBM(),
					wppInfo);
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
		}
		if (result) {
			return tbVirtualDao.updateByObj(obj);
		} else {
			return -1;
		}
	}

	/**
	 * @Title:调整大小虚拟机内存及CPU大小
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int adjustVirtualInfo(TbCloud2VirtualInfoObj obj) {
		WorkloadsPutPropInfo wppInfo = new WorkloadsPutPropInfo();
		wppInfo.setState("RESIZING");
		List<WorkloadsPutPropertiesInfo> properties = new ArrayList<WorkloadsPutPropertiesInfo>();
		WorkloadsPutPropertiesInfo wppInfo1 = new WorkloadsPutPropertiesInfo();
		wppInfo1.setName("cpu");
		wppInfo1.setValue(obj.getVH_CPU());
		properties.add(wppInfo1);
		WorkloadsPutPropertiesInfo wppInfo2 = new WorkloadsPutPropertiesInfo();
		wppInfo2.setName("cpushare");
		wppInfo2.setValue(obj.getVH_PROCESS_UNIT());
		properties.add(wppInfo2);
		WorkloadsPutPropertiesInfo wppInfo3 = new WorkloadsPutPropertiesInfo();
		wppInfo3.setName("memsize");
		wppInfo3.setValue(obj.getVH_MEM());
		properties.add(wppInfo3);
		wppInfo.setProperties(properties);
		boolean result = false;
		try {
			result = WorkloadsOperation.putWorkloadById(obj.getVH_ID_IBM(),
					wppInfo);
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
		}
		if (result) {
			return tbVirtualDao.updateByObj(obj);
		} else {
			return -1;
		}
	}

	/**
	 * @Title:添加存储
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int addMemInfo(TbCloud2VirtualInfoObj obj) {
		List<VirtualServersInfo> vsInfoList = null;
		try {
			vsInfoList = WorkloadsOperation.getWorkloadsIdVirtualServers(obj
					.getVH_ID_IBM());
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
		}
		boolean result = false;
		if (vsInfoList != null && vsInfoList.size() > 0) {
			VirtualServersInfo vsInfo = vsInfoList.get(0);
			VirtualServersStorageInfo vssInfo = new VirtualServersStorageInfo();
			vssInfo.setName(obj.getVH_STORAGE_NAME());
			vssInfo.setSize(Integer.parseInt(obj.getVH_STORAGE_VALUE()));
			try {
				result = VirtualServicesOperation
						.postVirtualServersByIdStorages(vsInfo.getId(), vssInfo);
			} catch (Exception e) {
				LogHelper.error("KVM Or Director Connect Error："
						+ e.getMessage());
				/** 插入告警 */
				tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
			}
		}
		if (result) {
			VirtualServersInfo vsInfo = vsInfoList.get(0);
			List<VirtualServersStorageInfo> vssInfoList = null;
			try {
				vssInfoList = VirtualServicesOperation
						.getVirtualServersByIdStorages(vsInfo.getId());
			} catch (Exception e) {
				LogHelper.error("KVM Or Director Connect Error："
						+ e.getMessage());
				/** 插入告警 */
				tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
			}
			String vs_Storage = "";
			if (vssInfoList != null && vssInfoList.size() > 0) {
				for (VirtualServersStorageInfo vssi : vssInfoList) {
					vs_Storage += vssi.getName() + ":" + vssi.getSize() + ",";
				}
			}
			TbCloud2VirtualInfoObj tcObj = new TbCloud2VirtualInfoObj();
			tcObj.setVH_ID_IBM(obj.getVH_ID_IBM());
			tcObj.setVH_STORAGE(vs_Storage.substring(0, vs_Storage
					.lastIndexOf(",")));
			return tbVirtualDao.updateByObj(obj);
		} else {
			return 0;
		}
	}

	/**
	 * @Title:移至项目更新PROJECT_ID
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int updateByProjectIdObj(TbCloud2VirtualInfoObj obj) {
		WorkloadsPutPropInfo info = new WorkloadsPutPropInfo();
		info.setDescription(obj.getVH_DESC());
		info.setName(obj.getVH_NAME());
		info.setProject(obj.getPROJECT_ID());
		boolean flag = false;
		try {
			flag = WorkloadsOperation.putWorkloadById(obj.getVH_ID_IBM(), info);
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
		}
		if (flag == true) {
			return tbVirtualDao.updateByProjectIdObj(obj);
		} else {
			return -1;
		}
	}

	/**
	 * @Title:启动停止虚拟机
	 * @Copyright: Copyright (c) 2012-02-15
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public int StartAndStopByObj(TbCloud2VirtualInfoObj obj) {
		WorkloadsPutPropInfo pInfo = new WorkloadsPutPropInfo();
		pInfo.setState(obj.getVH_STAT());
		boolean result = false;
		try {
			result = WorkloadsOperation.putWorkloadById(obj.getVH_ID_IBM(),
					pInfo);
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
		}
		if (result) {
			return tbVirtualDao.StartAndStopByObj(obj);
		} else {
			return -1;
		}
	}

	/**
	 * @Title:查询虚拟机状态信息
	 * @Copyright: Copyright (c) 2012-02-15
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public List queryListIDByObj(TbCloud2VirtualInfoObj obj) {
		return tbVirtualDao.queryListIDByObj(obj);
	}

	/**
	 * @Title:查询虚拟机详细信息
	 * @Copyright: Copyright (c) 2012-02-15
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public TbCloud2VirtualInfoObj queryVirtualServerByObj(
			TbCloud2VirtualInfoObj obj) {
		try {
			WorkloadsIdCustomizationInfo info = WorkloadsOperation
					.getWorkloadsIdCustomization(obj.getVH_ID_IBM());
			WorkloadInfo workloadInfo = WorkloadsOperation
					.getWorkloadsById(info.getId());
			List<WorkloadsTimeStampsInfo> timeStampList = WorkloadsOperation
					.getWorkloadsIdTimestamps(info.getId());
			WorkloadsTimeStampsInfo timeStampInfo = timeStampList.get(0);
			String log = WorkloadsOperation.getWorkloadsIdLog(info.getId());
			List<VirtualServersInfo> vsInfoList = WorkloadsOperation
					.getWorkloadsIdVirtualServers(info.getId());
			VirtualServersInfo visualServer = new VirtualServersInfo();
			List list = new ArrayList();
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
			vObj.setVH_NETWORK(dataMap
					.get("product.AIX1.com.ibm.ovf.vim.2.networkport.6.ip"));
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
			vObj.setVH_PROCESS_UNIT_Priority(dataMap.get("cpushpri"));
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
			if (list != null) {
				return vObj;
			} else {
				return vObj;
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
	 * @Title: 获取所有虚拟机列表
	 * @Copyright: Copyright (c) Mar 20, 2012
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public List queryForAllListByObj(TbCloud2VirtualInfoObj obj) {
		return tbVirtualDao.queryForAllListByObj(obj);
	}

	/**
	 * 
	 * @Title:通过多个Id获取对应虚拟机列表
	 * @Copyright: Copyright (c) Mar 20, 2012
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List<TbCloud2VirtualInfoObj> queryVirtualList(String id) {
		return tbVirtualDao.queryVirtualList(id);
	}

	private TbVirtualDao tbVirtualDao;
	private TbCloud2MonitorAlarmDao tbCloud2MonitorAlarmDao;

	public void setTbCloud2MonitorAlarmDao(
			TbCloud2MonitorAlarmDao tbCloud2MonitorAlarmDao) {
		this.tbCloud2MonitorAlarmDao = tbCloud2MonitorAlarmDao;
	}

	public void setTbVirtualDao(TbVirtualDao tbVirtualDao) {
		this.tbVirtualDao = tbVirtualDao;
	}

}
