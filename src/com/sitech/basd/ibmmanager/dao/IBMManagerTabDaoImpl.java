package com.sitech.basd.ibmmanager.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.fusioncharts.vo.Category;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.ibmmanager.domain.CloudOSIndexObj;
import com.sitech.basd.ibmmanager.domain.IBMIndexObj;
import com.sitech.basd.ibmmanager.domain.IBMManagerTabObj;
import com.sitech.basd.ibmmanager.domain.IBMManagerTreeObj;
import com.sitech.basd.ibmmanager.domain.LogicalPartitionObj;
import com.sitech.basd.ibmmanager.domain.PowerObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

/**
 * 
 * <p>
 * Title: IBMManagerTreeDaoImpl
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
 * @createtime 2013-11-2 下午5:31:23
 * 
 */
@Repository("ibmManagerTabDao")
public class IBMManagerTabDaoImpl extends BaseDao implements IBMManagerTabDao {

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
	@Override
	public LogicalPartitionObj queryLparInfo(LogicalPartitionObj obj) {
		LogicalPartitionObj lObj = new LogicalPartitionObj();
		try {
			lObj = (LogicalPartitionObj) getSqlMap().queryForObject(
					"IBMManagerTab.queryLparInfo", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.queryLparInfo:" + e.getMessage()
					+ getClass().getName());
		}
		return lObj;
	}

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
	@Override
	public PowerObj queryPowerInfo(PowerObj obj) {
		PowerObj lObj = new PowerObj();
		try {
			lObj = (PowerObj) getSqlMap().queryForObject(
					"IBMManagerTab.queryPowerInfo", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.queryPowerInfo:" + e.getMessage()
					+ getClass().getName());
		}
		return lObj;
	}

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
	@Override
	public VMHostObj queryVMInfo(VMHostObj obj) {
		VMHostObj vmObj = new VMHostObj();
		try {
			vmObj = (VMHostObj) getSqlMap().queryForObject(
					"IBMManagerTab.queryVMInfo", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.queryVMInfo:" + e.getMessage()
					+ getClass().getName());
		}
		return vmObj;

	}

	/**
	 * 
	 * @Title: queryCountByObj
	 * @Description: 查询IBM个数
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-11 下午3:48:00
	 */
	@Override
	public int queryCountByObj(IBMManagerTreeObj obj) {
		int ref = 0;
		try {
			ref = (Integer) getSqlMap().queryForObject(
					"IBMManagerTab.queryCountByObj", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.queryCountByObj:" + e.getMessage()
					+ getClass().getName());
		}
		return ref;
	}

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
	@Override
	public int queryLparCountByOBj(IBMManagerTreeObj obj) {
		int ref = 0;
		try {
			ref = (Integer) getSqlMap().queryForObject(
					"IBMManagerTab.queryLparCountByOBj", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.queryLparCountByOBj:"
					+ e.getMessage() + getClass().getName());
		}
		return ref;
	}

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
	@Override
	public List<IBMManagerTabObj> queryIBMPowerResource(IBMManagerTreeObj obj) {
		List<IBMManagerTabObj> list = null;
		try {
			list = getSqlMap().queryForList(
					"IBMManagerTab.queryIBMPowerResource", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.queryIBMPowerResource:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}

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
	@Override
	public IBMManagerTabObj queryIBMHmcResource(IBMManagerTreeObj obj) {
		IBMManagerTabObj ibmOb = new IBMManagerTabObj();
		try {
			ibmOb = (IBMManagerTabObj) getSqlMap().queryForObject(
					"IBMManagerTab.queryIBMHmcResource", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.queryIBMHmcResource:"
					+ e.getMessage() + getClass().getName());
		}
		return ibmOb;
	}

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
	@Override
	public IBMManagerTabObj queryHostAllResource(IBMManagerTreeObj obj) {
		IBMManagerTabObj ibmOb = new IBMManagerTabObj();
		try {
			ibmOb = (IBMManagerTabObj) getSqlMap().queryForObject(
					"IBMManagerTab.queryHostAllResource", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.queryHostAllResource:"
					+ e.getMessage() + getClass().getName());
		}
		return ibmOb;
	}

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
	@Override
	public IBMManagerTabObj queryHostUsedResource(IBMManagerTreeObj obj) {
		IBMManagerTabObj ibmOb = new IBMManagerTabObj();
		try {
			ibmOb = (IBMManagerTabObj) getSqlMap().queryForObject(
					"IBMManagerTab.queryHostUsedResource", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.queryHostUsedResource:"
					+ e.getMessage() + getClass().getName());
		}
		return ibmOb;
	}

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
	@Override
	public IBMManagerTabObj queryClusterAllResource(IBMManagerTreeObj obj) {
		IBMManagerTabObj ibmOb = new IBMManagerTabObj();
		try {
			ibmOb = (IBMManagerTabObj) getSqlMap().queryForObject(
					"IBMManagerTab.queryClusterAllResource", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.queryClusterAllResource:"
					+ e.getMessage() + getClass().getName());
		}
		return ibmOb;
	}

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
	@Override
	public IBMManagerTabObj queryClusterUsedResource(IBMManagerTreeObj obj) {
		IBMManagerTabObj ibmOb = new IBMManagerTabObj();
		try {
			ibmOb = (IBMManagerTabObj) getSqlMap().queryForObject(
					"IBMManagerTab.queryClusterUsedResource", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.queryClusterUsedResource:"
					+ e.getMessage() + getClass().getName());
		}
		return ibmOb;
	}

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
	@Override
	public IBMManagerTabObj queryAllResource(IBMManagerTreeObj obj) {
		IBMManagerTabObj ibmOb = new IBMManagerTabObj();
		try {
			ibmOb = (IBMManagerTabObj) getSqlMap().queryForObject(
					"IBMManagerTab.queryAllResource", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.queryAllResource:" + e.getMessage()
					+ getClass().getName());
		}
		return ibmOb;
	}

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
	@Override
	public IBMManagerTabObj queryUsedResource(IBMManagerTreeObj obj) {
		IBMManagerTabObj ibmOb = new IBMManagerTabObj();
		try {
			ibmOb = (IBMManagerTabObj) getSqlMap().queryForObject(
					"IBMManagerTab.queryUsedResource", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.queryUsedResource:" + e.getMessage()
					+ getClass().getName());
		}
		return ibmOb;
	}

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
	@Override
	public int queryLparIndexCount(LogicalPartitionObj obj) {
		int ref = 0;
		try {
			ref = (Integer) getSqlMap().queryForObject(
					"IBMManagerTab.queryLparIndexCount", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.queryLparIndexCount:"
					+ e.getMessage() + getClass().getName());
		}
		return ref;
	}

	/**
	 * 
	 * @Title: queryFCReadSpeed
	 * @Description: 查询光纤卡读速度
	 * @param
	 * @return Data
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-15 上午10:38:23
	 */
	@Override
	public List<Data> queryFCReadSpeed(IBMIndexObj obj) {
		List<Data> list = new ArrayList<Data>();
		try {
			list = getSqlMap().queryForList("IBMManagerTab.queryFCReadSpeed",
					obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.queryFCReadSpeed:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryFCWriteSpeed
	 * @Description: 查询光纤卡写速度
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-15 上午11:31:00
	 */
	@Override
	public List<Data> queryFCWriteSpeed(IBMIndexObj obj) {
		List<Data> list = new ArrayList<Data>();
		try {
			list = getSqlMap().queryForList("IBMManagerTab.queryFCWriteSpeed",
					obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.queryFCWriteSpeed:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: querySEAAndNetCardReadSpeed
	 * @Description: 查询网络读性能信息和网卡读速度
	 * @param
	 * @return Data
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-15 上午10:38:23
	 */
	@Override
	public List<Data> querySEAAndNetCardReadSpeed(IBMIndexObj obj) {
		List<Data> list = new ArrayList<Data>();
		try {
			list = getSqlMap().queryForList(
					"IBMManagerTab.querySEAAndNetCardReadSpeed", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.querySEAAndNetCardReadSpeed:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: querySEAAndNetCardWriteSpeed
	 * @Description: 查询网络 写性能信息和网卡写速度
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-15 上午11:31:00
	 */
	@Override
	public List<Data> querySEAAndNetCardWriteSpeed(IBMIndexObj obj) {
		List<Data> list = new ArrayList<Data>();
		try {
			list = getSqlMap().queryForList(
					"IBMManagerTab.querySEAAndNetCardWriteSpeed", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.querySEAAndNetCardWriteSpeed:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}

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
	@Override
	public List<Data> queryLparDiskReadSpeed(IBMIndexObj obj) {
		List<Data> list = new ArrayList<Data>();
		try {
			list = getSqlMap().queryForList(
					"IBMManagerTab.queryLparDiskReadSpeed", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.queryLparDiskReadSpeed:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}

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
	@Override
	public List<Data> queryLparDiskWriteSpeed(IBMIndexObj obj) {
		List<Data> list = new ArrayList<Data>();
		try {
			list = getSqlMap().queryForList(
					"IBMManagerTab.queryLparDiskWriteSpeed", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.queryLparDiskWriteSpeed:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}

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
	@Override
	public List<Data> queryIOAndNetSpeed(CloudOSIndexObj obj) {
		List<Data> list = new ArrayList<Data>();
		try {
			list = getSqlMap().queryForList("IBMManagerTab.queryIOAndNetSpeed",
					obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.queryIOAndNetSpeed:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	};

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
	@Override
	public List<Category> queryIBMIndexTimeLable(IBMIndexObj obj) {
		List<Category> list = new ArrayList<Category>();
		try {
			list = getSqlMap().queryForList(
					"IBMManagerTab.queryIBMIndexTimeLable", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.queryIBMIndexTimeLable:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}

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
	@Override
	public List<Category> queryCloudOSTimeLable(CloudOSIndexObj obj) {
		List<Category> list = new ArrayList<Category>();
		try {
			list = getSqlMap().queryForList(
					"IBMManagerTab.queryCloudOSTimeLable", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTab.queryCloudOSTimeLable:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}
}
