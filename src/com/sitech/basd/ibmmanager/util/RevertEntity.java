package com.sitech.basd.ibmmanager.util;

import com.sitech.basd.ibmmanager.domain.ClusterObj;
import com.sitech.basd.ibmmanager.domain.HMCObj;
import com.sitech.basd.ibmmanager.domain.HostObj;
import com.sitech.basd.ibmmanager.domain.IBMManagerTreeObj;
import com.sitech.basd.ibmmanager.domain.LparObj;
import com.sitech.basd.ibmmanager.domain.PowObj;
import com.sitech.basd.ibmmanager.domain.VMObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;

/**
 * 
 * <p>
 * Title: RevertEntity
 * </p>
 * <p>
 * Description: 对象的转换(各个表中查出来的标示和tree中进行转换)
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
 * @createtime 2013-11-14 下午3:13:35
 * 
 */
public class RevertEntity {

	public static IBMManagerTreeObj toTreeHMC(HMCObj hmcObj) {
		IBMManagerTreeObj obj = new IBMManagerTreeObj();
		obj.setName(hmcObj.getHmcName());
		obj.setUuid(hmcObj.getHmcCode());
		return obj;
	}

	public static IBMManagerTreeObj toTreePower(PowObj powObj) {
		IBMManagerTreeObj obj = new IBMManagerTreeObj();
		obj.setName(powObj.getPowerName());
		obj.setUuid(powObj.getPowerCode());
		return obj;
	}

	public static IBMManagerTreeObj toTreeLpar(LparObj lparObj) {
		IBMManagerTreeObj obj = new IBMManagerTreeObj();
		obj.setName(lparObj.getLparName());
		String uuuid = lparObj.getPowerCode() + "_" + lparObj.getLparCode();
		obj.setUuid(uuuid);
		return obj;
	}

	public static IBMManagerTreeObj toTreeCluster(ClusterObj clusterObj) {
		IBMManagerTreeObj obj = new IBMManagerTreeObj();
		obj.setName(clusterObj.getClusterName());
		obj.setUuid(clusterObj.getClusterCode());
		return obj;
	}

	public static IBMManagerTreeObj toTreeHost(HostObj hostObj) {
		IBMManagerTreeObj obj = new IBMManagerTreeObj();
		obj.setName(hostObj.getHostName());
		obj.setUuid(hostObj.getHostCode());
		return obj;
	}

	public static IBMManagerTreeObj toTreeVM(VMObj vmObj) {
		IBMManagerTreeObj obj = new IBMManagerTreeObj();
		obj.setName(vmObj.getVmName());
		obj.setUuid(vmObj.getVmCode());
		return obj;
	}

	/**
	 * 
	 * @Title: toTbCloud2HostInfoObj
	 * @Description: 转换为宿主机表VO对象
	 * @param
	 * @return TbCloud2HostInfoObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-11-14 下午6:51:59
	 */
	public static TbCloud2HostInfoObj toTbCloud2HostInfoObj(PowObj powObj, String virtualType) {
		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		obj.setEq_name(powObj.getPowerName());
		obj.setHasvertual(virtualType);
		return obj;
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
	 * @createtime 2013-11-15 上午10:56:56
	 */
	public static VMHostObj toVMHostObj(LparObj lparObj, String virtualType) {
		VMHostObj obj = new VMHostObj();
		obj.setVH_UUID(lparObj.getPowerCode() + "_" + lparObj.getLparCode());
		obj.setVH_TYPE(virtualType);
		return obj;
	}

}