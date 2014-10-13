package com.sitech.basd.ibmmanager.service;

import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.ibmmanager.domain.CloudOSIndexObj;
import com.sitech.basd.ibmmanager.domain.IBMIndexObj;
import com.sitech.basd.ibmmanager.domain.IBMManagerTabObj;
import com.sitech.basd.ibmmanager.domain.IBMManagerTreeObj;
import com.sitech.basd.ibmmanager.domain.LogicalPartitionObj;
import com.sitech.basd.ibmmanager.domain.PowerObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;

/**
 * 
 * <p>
 * Title: IBMManagerTabService
 * </p>
 * <p>
 * Description: 相应Tab页得操作
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
 * @createtime 2013-11-14 下午3:11:56
 * 
 */
public interface IBMManagerTabService {
	/**
	 * 
	 * @Title: queryLparInfo
	 * @Description: 查询lpar摘要
	 * @param
	 * @return LogicalPartitionObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-5 下午3:54:51
	 */
	public LogicalPartitionObj queryLparInfo(LogicalPartitionObj obj);

	/**
	 * 
	 * @Title: queryPowerInfo
	 * @Description: 查询power摘要
	 * @param
	 * @return PowerObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-5 下午4:34:03
	 */
	public PowerObj queryPowerInfo(PowerObj obj);

	/**
	 * 
	 * @Title: queryVMInfo
	 * @Description: 查询虚拟机摘要
	 * @param
	 * @return VMHostObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-11 上午9:59:00
	 */
	public VMHostObj queryVMInfo(VMHostObj obj);

	/**
	 * 
	 * @Title: queryCountByObj
	 * @Description:查询IBM个数
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-11 下午4:54:58
	 */
	public int queryCountByObj(IBMManagerTreeObj obj);

	/**
	 * 
	 * @Title: queryLparCountByOBj
	 * @Description: 通过hmc的id查询出lpar的个数
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-11 下午8:43:50
	 */
	public int queryLparCountByOBj(IBMManagerTreeObj obj);

	/**
	 * 
	 * @Title: queryIBMPowerResource
	 * @Description: 主机资源使用情况
	 * @param
	 * @return IBMManagerTabObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-12 下午9:57:36
	 */
	public IBMManagerTabObj queryIBMPowerResource(IBMManagerTreeObj obj);

	/**
	 * 
	 * @Title: queryIBMHmcResource
	 * @Description: 查询HMC的资源使用情况
	 * @param
	 * @return IBMManagerTabObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-13 上午9:28:59
	 */
	public IBMManagerTabObj queryIBMHmcResource(IBMManagerTreeObj obj);

	/**
	 * 
	 * @Title: queryHostAllResource
	 * @Description: X86查询每个主机总资源
	 * @param
	 * @return IBMManagerTabObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-13 下午3:59:30
	 */
	public IBMManagerTabObj queryHostAllResource(IBMManagerTreeObj obj);

	/**
	 * 
	 * @Title: queryHostUsedResource
	 * @Description: X86查询每个主机已用资源
	 * @param
	 * @return IBMManagerTabObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-13 下午4:02:10
	 */
	public IBMManagerTabObj queryHostUsedResource(IBMManagerTreeObj obj);

	/**
	 * 
	 * @Title: queryClusterAllResource
	 * @Description: X86查询每个集群总资源
	 * @param
	 * @return IBMManagerTabObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-13 下午5:14:27
	 */
	public IBMManagerTabObj queryClusterAllResource(IBMManagerTreeObj obj);

	/**
	 * 
	 * @Title: queryClusterUsedResource
	 * @Description: X86查询每个集群已用资源
	 * @param
	 * @return IBMManagerTabObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-13 下午5:15:00
	 */
	public IBMManagerTabObj queryClusterUsedResource(IBMManagerTreeObj obj);

	/**
	 * 
	 * @Title: queryAllResource
	 * @Description: 查询x86总资源
	 * @param
	 * @return IBMManagerTabObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-13 下午5:42:16
	 */
	public IBMManagerTabObj queryAllResource(IBMManagerTreeObj obj);

	/**
	 * 
	 * @Title: queryUsedResource
	 * @Description: 查询x86已用资源
	 * @param
	 * @return IBMManagerTabObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-13 下午5:42:48
	 */
	public IBMManagerTabObj queryUsedResource(IBMManagerTreeObj obj);

	/**
	 * 
	 * @Title: queryLparIndexCount
	 * @Description: 查询Lpar指标个数
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-14 下午4:32:48
	 */
	public int queryLparIndexCount(LogicalPartitionObj obj);

	/**
	 * 
	 * @Title: splitLparUuid
	 * @Description: 拆分Lpar的uuid(因为lpar的uuid存的是 hostId_lparId)
	 * @param
	 * @return LogicalPartitionObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-14 下午5:26:25
	 */
	public LogicalPartitionObj splitLparUuid(String uuid);

	/**
	 * 
	 * @Title: queryIBMIndex
	 * @Description: 查询IBM指标
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-15 上午10:50:25
	 */
	public FusionCharts queryIBMIndex(IBMIndexObj obj);

	/**
	 * 
	 * @Title: queryIOSpeed
	 * @Description: 存储监控指标
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-19 下午6:55:01
	 */
	public FusionCharts queryIOSpeed(CloudOSIndexObj obj);

	/**
	 * 
	 * @Title: queryNetSpeed
	 * @Description: 网络监控指标
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-19 下午7:57:31
	 */
	public FusionCharts queryNetSpeed(CloudOSIndexObj obj);

}
