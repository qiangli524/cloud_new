package com.sitech.basd.ibmmanager.dao;

import java.util.List;

import com.sitech.basd.fusioncharts.vo.Category;
import com.sitech.basd.fusioncharts.vo.Data;
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
 * Title: IBMManagerTabDao
 * </p>
 * <p>
 * Description: IBM tab页上相关操作
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
 * @createtime 2013-11-14 下午3:15:54
 * 
 */
public interface IBMManagerTabDao {
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
	 * @return List<IBMManagerTabObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-12 下午9:57:36
	 */
	public List<IBMManagerTabObj> queryIBMPowerResource(IBMManagerTreeObj obj);

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
	 * @Title: queryFCReadSpeed
	 * @Description: 查询power和lpar光纤卡读速度
	 * @param
	 * @return Data
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-15 上午10:38:23
	 */
	public List<Data> queryFCReadSpeed(IBMIndexObj obj);

	/**
	 * 
	 * @Title: queryFCWriteSpeed
	 * @Description: 查询power和lpar光纤卡写速度
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-15 上午11:31:00
	 */
	public List<Data> queryFCWriteSpeed(IBMIndexObj obj);

	/**
	 * 
	 * @Title: querySEAAndNetCardReadSpeed
	 * @Description: 查询power网络读性能信息和网卡读速度(power和lpar)
	 * @param
	 * @return Data
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-15 上午10:38:23
	 */
	public List<Data> querySEAAndNetCardReadSpeed(IBMIndexObj obj);

	/**
	 * 
	 * @Title: querySEAAndNetCardWriteSpeed
	 * @Description: 查询power网络 写性能信息和网卡写速度(power和lpar)
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-15 上午11:31:00
	 */
	public List<Data> querySEAAndNetCardWriteSpeed(IBMIndexObj obj);

	/**
	 * 
	 * @Title: queryLparDiskReadSpeed
	 * @Description: 查询lpar磁盘的读速度
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-17 上午12:00:45
	 */
	public List<Data> queryLparDiskReadSpeed(IBMIndexObj obj);

	/**
	 * 
	 * @Title: queryLparDiskWriteSpeed
	 * @Description: 查询lpar磁盘的写速度
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-17 上午12:01:10
	 */
	public List<Data> queryLparDiskWriteSpeed(IBMIndexObj obj);

	/**
	 * 
	 * @Title: queryIOAndNetSpeed
	 * @Description: 存储和网络监控指标
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-19 下午6:55:01
	 */
	public List<Data> queryIOAndNetSpeed(CloudOSIndexObj obj);

	/**
	 * 
	 * @Title: queryIBMIndexTimeLable
	 * @Description: 查询图表的横坐标数据
	 * @param
	 * @return List<Category>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-15 上午11:04:07
	 */
	public List<Category> queryIBMIndexTimeLable(IBMIndexObj obj);

	/**
	 * 
	 * @Title: queryCloudOSTimeLable
	 * @Description: 查询图表的横坐标数据
	 * @param
	 * @return List<Category>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-19 下午7:17:53
	 */
	public List<Category> queryCloudOSTimeLable(CloudOSIndexObj obj);

}
