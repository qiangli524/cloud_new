package com.sitech.basd.resource.util;

import java.text.DecimalFormat;
import java.util.Date;

import com.sitech.basd.resource.domain.template.TemManObj;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.utils.number.NumberFormatUtil;
import com.sitech.vo.united.ClusterUnitedVO;
import com.sitech.vo.united.DataCenterUnitedVO;
import com.sitech.vo.united.DatastoreUnitedVO;
import com.sitech.vo.united.HostUnitedVO;
import com.sitech.vo.united.SnapshotUnitedVO;
import com.sitech.vo.united.VirtualMachinePowerStateUnitedVO;
import com.sitech.vo.united.VirtualMachineUnitedVO;
import com.sitech.vo.util.UnitedConstant;

/**
 * 
 * <p>
 * Title: RevertEntity
 * </p>
 * <p>
 * Description: 实体类转换
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author taoxue ,update by huojla
 * @20130726 增加static属性
 * @version 1.0
 * @createtime 2013-7-26 上午10:09:54
 * 
 */
public class RevertEntity {
	/**
	 * 
	 * @Title: revertToTreeEntity
	 * @Description: 将接口实体转化为数据中心实体
	 * @param
	 * @return UnitedTreeObj
	 * @throws
	 * @author taoxue,update by huojla
	 * @20130726 增加static属性
	 * @version 1.0
	 * @createtime Jul 26, 2013 9:04:27 AM
	 */
	public static UnitedTreeObj toTreeDataCenter(DataCenterUnitedVO vo) {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setName(vo.getName());
		obj.setUuid(vo.getDataCenterCode());
		obj.setConnect_id(vo.getConnectCode());
		return obj;
	}

	/**
	 * 
	 * @Title: toTreeCluster
	 * @Description: 将接口实体转化为集群实体
	 * @param
	 * @return UnitedTreeObj
	 * @throws
	 * @author taoxue,update by huojla
	 * @20130726 增加static属性
	 * @version 1.0
	 * @createtime Jul 26, 2013 9:10:38 AM
	 */
	public static UnitedTreeObj toTreeCluster(ClusterUnitedVO vo) {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setName(vo.getClusterName());
		obj.setUuid(vo.getClusterCode());
		obj.setConnect_id(vo.getConnectCode());
		return obj;
	}

	/**
	 * 
	 * @Title: toTreeHost
	 * @Description:将接口实体转化为主机实体
	 * @param
	 * @return UnitedTreeObj
	 * @throws
	 * @author taoxue,update by huojla
	 * @20130726 增加static属性
	 * @version 1.0
	 * @createtime Jul 26, 2013 9:11:34 AM
	 */
	public static UnitedTreeObj toTreeHost(HostUnitedVO vo) {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setName(vo.getHostname());
		obj.setUuid(vo.getHostCode());
		obj.setConnect_id(vo.getConnectCode());
		return obj;
	}

	/**
	 * 
	 * @Title: toTreeVM
	 * @Description:将接口实体转化为虚拟机实体
	 * @param
	 * @return UnitedTreeObj
	 * @throws
	 * @author taoxue,update by huojla
	 * @20130726 增加static属性
	 * @version 1.0
	 * @createtime Jul 26, 2013 9:37:43 AM
	 */
	public static UnitedTreeObj toTreeVM(VirtualMachineUnitedVO vo) {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setName(vo.getVmName());
		obj.setUuid(vo.getVmCode());
		obj.setConnect_id(vo.getConnectCode());
		return obj;
	}

	/**
	 * 
	 * @Title: toRestDataCenter
	 * @Description: 将数据中心实体转化为接口实体
	 * @param
	 * @return DataCenterVO
	 * @throws
	 * @author taoxue,update by huojla
	 * @20130726 增加static属性
	 * @version 1.0
	 * @createtime Jul 26, 2013 9:19:29 AM
	 */
	public static DataCenterUnitedVO toRestDataCenter(UnitedTreeObj obj) {
		DataCenterUnitedVO vo = new DataCenterUnitedVO();
		vo.setName(obj.getName());
		vo.setDataCenterCode(obj.getUuid());
		vo.setConnectCode(obj.getConnect_id());
		return vo;
	}

	/**
	 * 
	 * @Title: toRestCluster
	 * @Description: 将集群实体转化为接口实体
	 * @param
	 * @return ClusterUnitedVO
	 * @throws
	 * @author taoxue,update by huojla
	 * @20130726 增加static属性
	 * @version 1.0
	 * @createtime Jul 26, 2013 9:21:36 AM
	 */
	public static ClusterUnitedVO toRestCluster(UnitedTreeObj obj) {
		ClusterUnitedVO vo = new ClusterUnitedVO();
		vo.setClusterName(obj.getName());
		vo.setDataCenterCode(obj.getParent_uuid());
		vo.setClusterCode(obj.getUuid());
		vo.setConnectCode(obj.getConnect_id());
		return vo;
	}

	/**
	 * 
	 * @Title: toRestHost
	 * @Description:将主机实体转化为接口实体
	 * @param
	 * @return HostUnitedVO
	 * @throws
	 * @author taoxue,update by huojla
	 * @20130726 增加static属性
	 * @version 1.0
	 * @createtime Jul 26, 2013 9:32:42 AM
	 */
	public static HostUnitedVO toRestHost(UnitedTreeObj obj, HostUnitedVO vo) {
		vo.setConnectCode(obj.getConnect_id());
		vo.setClusterCode(obj.getParent_uuid());
		return vo;
	}

	/**
	 * 
	 * @Title: toRestVM
	 * @Description: 将虚拟机实体转化为接口实体
	 * @param
	 * @return VirtualMachineUnitedVO
	 * @throws
	 * @author taoxue,update by huojla
	 * @20130726 增加static属性
	 * @version 1.0
	 * @createtime Jul 26, 2013 9:39:59 AM
	 */
	public static VirtualMachineUnitedVO toRestVM(UnitedTreeObj obj) {
		VirtualMachineUnitedVO vo = new VirtualMachineUnitedVO();
		vo.setConnectCode(obj.getConnect_id());
		vo.setVmCode(obj.getUuid());
		vo.setVmName(obj.getName());
		vo.setHostCode(obj.getParent_uuid());
		return vo;

	}

	/**
	 * 
	 * @Title: toRestVMPowerState
	 * @Description: 转化为虚拟机电源状态实体类
	 * @param
	 * @return VirtualMachinePowerStateUnitedVO
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 29, 2013 12:20:41 PM
	 */
	public static VirtualMachinePowerStateUnitedVO toRestVMPowerState(UnitedTreeObj obj) {
		VirtualMachinePowerStateUnitedVO vo = new VirtualMachinePowerStateUnitedVO();
		vo.setConnectCode(obj.getConnect_id());
		vo.setHostCode(obj.getParent_uuid());
		vo.setVmCode(obj.getUuid());
		return vo;
	}

	/**
	 * 
	 * @Title: toRestVMForCloneTem
	 * @Description: 用于通过模板克隆虚拟机
	 * @param
	 * @return VirtualMachineUnitedVO
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 30, 2013 8:38:34 PM
	 */
	public static VirtualMachineUnitedVO toRestVMForCloneTem(UnitedTreeObj obj,
			VirtualMachineUnitedVO vo, String memUnit) {
		// 转化存储和内存
		if ("2".equals(memUnit)) {// 单位为G
			vo.setMemoryMB(vo.getMemoryMB() * 1024);// 转化为MB
		}
		// vo.setStorageSizeInMb(vo.getStorageSizeInMb() * 1.0);// 转化为MB
		vo.setConnectCode(obj.getConnect_id());
		/*
		 * 为什么设置parent_uuid???@huojla
		 */
		// vo.setHostCode(obj.getParent_uuid());
		return vo;

	}

	/**
	 * 
	 * @Title: toVMHostObj
	 * @Description: 转换为tb_cloud2_vmhost_info
	 * @param
	 * @return VMHostObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-26 下午2:09:13
	 */
	public static VMHostObj toVMHostObj(VirtualMachineUnitedVO vo, String vmType) {
		VMHostObj vmhostObj = new VMHostObj();
		vmhostObj.setVH_UUID(vo.getVmCode());
		vmhostObj.setVH_CPU(vo.getNumCPUs() + "");
		if (vo.getMemoryMB() != null) {
			vmhostObj.setVH_MEM(vo.getMemoryMB().intValue() + "");
		}
		vmhostObj.setVH_STORAGE(vo.getStorageSizeInMb() + "");
		vmhostObj.setVH_SYSTEM(vo.getOperationSystemName());
		vmhostObj.setVH_NAME(vo.getVmName());
		// if (vo.getIp() == null || "".equals(vo.getIp())) {
		// vmhostObj.setVH_IP(vo.getVirtualNicList().get(0).getIpAddress());
		// } else {
		vmhostObj.setVH_IP(vo.getIp());
		// }
		String vmStat = vo.getPowerState();
		if ("poweredOn".equals(vmStat)) {
			vmhostObj.setVH_STAT("1");
		} else if ("poweredOff".equals(vmStat)) {
			vmhostObj.setVH_STAT("0");
		} else if ("suspended".equals(vmStat)) {
			vmhostObj.setVH_STAT("2");
		}
		// vmhostObj.setDNS(vo.getDns());
		vmhostObj.setVH_TYPE(vmType);
		vmhostObj.setConnectId(vo.getConnectCode());
		vmhostObj.setHostCode(vo.getHostCode());
		vmhostObj.setPROJECT_ID(vo.getProject_id());
		vmhostObj.setUSER_ID(vo.getProject_user_id());
		if (vo.getIp() != null && !"".equals(vo.getIp())) {
			vmhostObj.setVH_IP(vo.getIp());
		}
		if (vo.getDns() != null && !"".equals(vo.getDns())) {
			vmhostObj.setDNS(vo.getDns());
		}
		vmhostObj.setUpdateTime(new Date());
		vmhostObj.setConnectStatus(vo.getConnectionState());
		vmhostObj.setVH_LOG(vo.getAnnotation());
		return vmhostObj;
	}

	/**
	 * 
	 * @Title: toTbCloud2HostInfoObj
	 * @Description: 转换为tb_cloud2_host_info
	 * @param
	 * @return TbCloud2HostInfoObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-26 下午5:22:56
	 */
	public static TbCloud2HostInfoObj toTbCloud2HostInfoObj(HostUnitedVO hostVO, String virtualType) {
		TbCloud2HostInfoObj hostInfoObj = new TbCloud2HostInfoObj();
		hostInfoObj.setHasvertual(virtualType);// 4为vmware虚拟化类型
		hostInfoObj.setEq_type("4");// hpx86为刀片
		hostInfoObj.setEq_hostname(hostVO.getHostname());
		hostInfoObj.setEq_name(hostVO.getHostname());
		// IP暂时写主机名称，待修正
		hostInfoObj.setEq_ip(hostVO.getManagementIp());
		hostInfoObj.setCpu_fq(hostVO.getCpuHz() + "");
		hostInfoObj.setCpu_cl(hostVO.getHostCpuNum() + "");
		hostInfoObj.setMem(hostVO.getHostMemInMb().intValue() + "");
		hostInfoObj.setMODEL(hostVO.getHostModel());
		hostInfoObj.setHOST_PROC(hostVO.getHostVendor());
		hostInfoObj.setNIC_NUM(hostVO.getNicNum());
		hostInfoObj.setCPU_DESC(hostVO.getHostCpuDesc());
		// 未设置主机状态
		if ("connected".equals(hostVO.getHostConnectionState())
				&& "poweredOn".equals(hostVO.getPowerState())) {
			hostInfoObj.setSTATUS("1");// 开机
		} else if ("connected".equals(hostVO.getHostConnectionState())
				&& "poweredOff".equals(hostVO.getPowerState())) {
			hostInfoObj.setSTATUS("2");// 关机
		} else {
			hostInfoObj.setSTATUS("3");// 进入维护模式
		}
		// 待修改
		hostInfoObj.setH_uuid(hostVO.getHostCode());
		hostInfoObj.setConnectId(hostVO.getConnectCode());
		hostInfoObj.setMaxVcpu(hostVO.getMaxHostSupportedVcpus());
		hostInfoObj.setMaxCpu(hostVO.getMaxCpuMHz());
		hostInfoObj.setUsedVcpu(hostVO.getUsedHostVcpus());
		hostInfoObj.setUsedMemMb(hostVO.getUserMemMb());
		hostInfoObj.setUsedCpu(hostVO.getUsedCpuMHz());
		// 修改为已分配
		hostInfoObj.setAllocated(1);
		// 设置数据更新时间
		hostInfoObj.setUpdateDate(new Date());
		hostInfoObj.setConnectStatus(hostVO.getHostConnectionState());
		return hostInfoObj;
	}

	/**
	 * 
	 * @Title: toDataStoreObj
	 * @Description: 转换存储信息，tb_yicloud_datastore
	 * @param
	 * @return DataStoreObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-30 下午8:23:40
	 */
	public static DataStoreObj toDataStoreObj(DatastoreUnitedVO datastoreUnitedVO) {
		DataStoreObj dataStoreObj = new DataStoreObj();
		dataStoreObj.setCAPACITY(NumberFormatUtil.numFormat(datastoreUnitedVO.getCapacityInMb(),
				"#.##"));// 单位B,存储时，存MB
		dataStoreObj.setFREE_SPACE(NumberFormatUtil.numFormat(datastoreUnitedVO.getFreeSpaceInMb(),
				"#.##"));// 单位B,存储时，存MB
		dataStoreObj.setNAME(datastoreUnitedVO.getDatastoreName());
		dataStoreObj.setSTORAGE_URL(datastoreUnitedVO.getDatastoreUrl());
		dataStoreObj.setTYPE(datastoreUnitedVO.getDatastoreType());
		dataStoreObj.setStore_uuid(datastoreUnitedVO.getDatastoreCode());
		dataStoreObj.setSTATE(datastoreUnitedVO.getDatastoreState());
		dataStoreObj.setPurchaseSpace(datastoreUnitedVO.getPurchaseStorageInMb() + "");
		// 外围获取ClusterCode及DataCenterCode
		return dataStoreObj;
	}

	/**
	 * 
	 * @Title: formVMHostObjToRestVO
	 * @Description: 将vmhostObj转化为VirtualMachineUnitedVO
	 * @param
	 * @return VirtualMachineUnitedVO
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 31, 2013 2:22:22 PM
	 */
	public static VirtualMachineUnitedVO formVMHostObjToVirtualMachineUnitedVO(VMHostObj obj) {
		VirtualMachineUnitedVO vo = new VirtualMachineUnitedVO();
		vo.setVmName(obj.getVH_NAME());
		vo.setIp(obj.getVH_IP());
		vo.setVmCode(obj.getVH_UUID());
		if (obj.getVH_CPU() != null) {
			vo.setNumCPUs(Integer.parseInt(obj.getVH_CPU()));
		}
		if (obj.getVH_MEM() != null) {
			vo.setMemoryMB(Integer.parseInt(obj.getVH_MEM()));
		}
		DecimalFormat df = new DecimalFormat("#");
		if (obj.getVH_STORAGE() != null && !"null".equals(obj.getVH_STORAGE())) {
			double store = Double.parseDouble(obj.getVH_STORAGE()) / 1024;
			String str = df.format(store);
			vo.setStorageSizeInMb(Double.parseDouble(str));
		}
		return vo;

	}

	/**
	 * 
	 * @Title: toTemManObj
	 * @Description: 转换tb_tem_manage 模板管理实体
	 * @param
	 * @return TemManObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-31 下午5:30:44
	 */
	public static TemManObj toTemManObj(VirtualMachineUnitedVO vo, String virtualTemplateType) {
		TemManObj obj = new TemManObj();
		obj.setName(vo.getVmName());
		obj.setType(virtualTemplateType);
		obj.setIsPublic("0");
		obj.setRemark(vo.getVmDesc());
		obj.setSystem(vo.getOperationSystemName());
		obj.setCpu(vo.getNumCPUs());
		obj.setMem(vo.getMemoryMB());
		obj.setStore(vo.getStorageSizeInMb());
		obj.setHostCode(vo.getHostCode());
		obj.setConnectId(vo.getConnectCode());
		obj.setTemplateCode(vo.getVmCode());
		// 模板网卡数量
		obj.setNic_count(vo.getEthernetCardList() == null ? 0 : vo.getEthernetCardList().size());
		return obj;
	}

	/**
	 * 
	 * @Title: toSnapshotUnitedVO
	 * @Description: 将UnitedTreeObj部分值复制给SnapshotUnitedVO
	 * @param
	 * @return SnapshotUnitedVO
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 1, 2013 11:36:05 AM
	 */
	public static SnapshotUnitedVO copyToSnapshotUnitedVO(UnitedTreeObj obj, SnapshotUnitedVO vo) {
		vo.setConnectCode(obj.getConnect_id());
		vo.setVmCode(obj.getUuid());
		vo.setDescription(vo.getDescription());
		return vo;
	}

	/**
	 * @Title: toVirtualMachineUnitedVO
	 * @Description: 将UnitedTreeObj部分复制给VirtualMachineUnitedVO
	 * @param
	 * @return VirtualMachineUnitedVO
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-1 下午7:17:42
	 */
	public static VirtualMachineUnitedVO toVirtualMachineUnitedVO(UnitedTreeObj obj,
			VirtualMachineUnitedVO vo) {
		VirtualMachineUnitedVO vi = new VirtualMachineUnitedVO();
		vi.setVmCode(obj.getUuid());
		vi.setConnectCode(obj.getConnect_id());
		vi.setMemoryMB(vo.getMemoryMB());
		vi.setNumCPUs(vo.getNumCPUs());
		vi.setStorageSizeInMb(vo.getStorageSizeInMb());
		vi.setHostCode(obj.getParent_uuid());
		vi.setVmdkList(vo.getVmdkList());
		if (UnitedConstant.XEN.equals(obj.getVtype())) {
			vi.setPowerState(vo.getPowerState());
		}
		vi.setTool(vo.getTool());
		return vi;
	}
}