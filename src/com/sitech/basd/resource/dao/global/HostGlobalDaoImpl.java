package com.sitech.basd.resource.dao.global;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import util.DomainUtil;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.resource.domain.global.HostGlobalObj;
import com.sitech.basd.resource.domain.united.CMSObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("hostGlobalDao")
public class HostGlobalDaoImpl extends BaseDao implements HostGlobalDao {
	/**
	 * 
	 * @Title: queryCpuTop
	 * @Description: 查询主机cpu使用率 topN
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 12, 2013 2:15:38 PM
	 */
	public List queryCpuTop(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryCpuTop", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryCpuTop:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryCpuXData
	 * @Description: 查询store topN对应的x值
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 19, 2013 8:42:32 AM
	 */
	public List queryCpuXData(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryCpuXData", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryCpuXData:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryMemTop
	 * @Description: 查询主机内存使用率topN
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 12, 2013 2:17:11 PM
	 */
	public List queryMemTop(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryMemTop", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryMemTop:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryCpuXData
	 * @Description: 查询store topN对应的x值
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 19, 2013 8:42:32 AM
	 */
	public List queryMemXData(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryMemXData", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryMemXData:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryStoreTop
	 * @Description: 查询主机存储使用率topN
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 12, 2013 2:18:50 PM
	 */
	public List queryStoreTop(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryStoreTop", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryStoreTop:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryCpuXData
	 * @Description: 查询store topN对应的x值
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 19, 2013 8:42:32 AM
	 */
	public List queryStoreXData(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryStoreXData", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryStoreXData:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryCPUList
	 * @Description: 查询按照CPU降序的列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 24, 2013 9:23:23 AM
	 */
	public List queryCPUList(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryCPUList", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryCPUList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryMemList
	 * @Description: 查询按照内存降序的列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 24, 2013 9:23:23 AM
	 */
	public List queryMemList(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryMemList", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryMemList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryStoreList
	 * @Description: 查询按照存储降序的列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 24, 2013 9:23:23 AM
	 */
	public List queryStoreList(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryStoreList", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryStoreList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: countHost
	 * @Description: 统计当前集群下主机的个数
	 * @param
	 * @return integer
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 31, 2013 15:06 PM
	 */
	public Integer countHost(HostGlobalObj obj) {
		Integer host = 0; // host的个数
		try {
			host = (Integer) getSqlMap().queryForObject("HostGlobal.countHost", obj);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.countHost:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return host;
	}

	@Override
	public List queryCpuTopClu(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryCpuTopClu", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryCpuTopClu:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List queryMemTopClu(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryMemTopClu", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryMemTopClu:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List queryStoreTopClu(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryStoreTopClu", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryStoreTopClu:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List queryCpuXDataClu(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryCpuXDataClu", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryCpuXDataClu:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List queryMemXDataClu(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryMemXDataClu", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryMemXDataClu:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List queryStoreXDataClu(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryStoreXDataClu", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryStoreXDataClu:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List queryCPUListClu(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryCPUListClu", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryCPUListClu:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List queryMemListClu(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryMemListClu", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryMemListClu:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List queryStoreListClu(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryStoreListClu", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryStoreListClu:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryHostCpuTopN
	 * @Description: 查询宿主机CpuTopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	@SuppressWarnings("unchecked")
	public List<Data> queryHostCpuTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryHostCpuTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryHostCpuTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("HostGlobal.queryHostCpuTopN:" + sqlException.getMessage()
					+ sqlException, sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryClusterHostCpuTopN
	 * @Description: 查询集群宿主机CpuTopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryClusterHostCpuTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryClusterHostCpuTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryClusterHostCpuTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("HostGlobal.queryClusterHostCpuTopN:" + sqlException.getMessage()
					+ sqlException, sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryHostMemTopN
	 * @Description: 查询宿主机内存TopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryHostMemTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryHostMemTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryHostMemTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("HostGlobal.queryHostMemTopN:" + sqlException.getMessage()
					+ sqlException, sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryHostStoreIKbpsTopN
	 * @Description: 查询宿主机存储写TopN
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-29 下午2:20:38
	 */
	public List<Data> queryHostStoreIKbpsTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryHostStoreIKbpsTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryHostStoreIKbpsTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("HostGlobal.queryHostStoreIKbpsTopN:" + sqlException.getMessage()
					+ sqlException, sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryHostStoreOKbpsTopN
	 * @Description:查询宿主机存储读TopN
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-29 下午2:20:53
	 */
	public List<Data> queryHostStoreOKbpsTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryHostStoreOKbpsTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryHostStoreOKbpsTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("HostGlobal.queryHostStoreOKbpsTopN:" + sqlException.getMessage()
					+ sqlException, sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryHostNetworkOKbpsTopN
	 * @Description: 查询宿主机网络读TopN
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-29 下午2:21:01
	 */
	public List<Data> queryHostNetworkOKbpsTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryHostNetworkOKbpsTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryHostNetworkOKbpsTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("HostGlobal.queryHostNetworkOKbpsTopN:" + sqlException.getMessage()
					+ sqlException, sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryHostNetworkIKbpsTopN
	 * @Description: T 查询宿主机网络写TopN
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-29 下午2:21:17
	 */
	public List<Data> queryHostNetworkIKbpsTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryHostNetworkIKbpsTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryHostNetworkIKbpsTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("HostGlobal.queryHostNetworkIKbpsTopN:" + sqlException.getMessage()
					+ sqlException, sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryClusterHostMemTopN
	 * @Description: 查询集群宿主机内存TopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryClusterHostMemTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryClusterHostMemTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryClusterHostMemTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("HostGlobal.queryClusterHostMemTopN:" + sqlException.getMessage()
					+ sqlException, sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryHostStorageTopN
	 * @Description: 查询宿主机存储TopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryHostStorageTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryHostStorageTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryHostStorageTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("HostGlobal.queryHostStorageTopN:" + sqlException.getMessage()
					+ sqlException, sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryClusterHostStorageTopN
	 * @Description: 查询集群宿主机存储TopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryClusterHostStorageTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryClusterHostStorageTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryClusterHostStorageTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("HostGlobal.queryClusterHostStorageTopN:"
					+ sqlException.getMessage() + sqlException, sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryHostByMap
	 * @Description: 查询主机通过集群uuid和connect_id
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-17
	 */
	public List queryHostByMap(Map<String, String> map) {
		List list = null;
		try {
			DomainUtil.setDomainToStringMap(map);
			list = getSqlMap().queryForList("HostGlobal.queryHostByMap", map);
		} catch (Exception sqlException) {
			LogHelper.error("HostGlobal.queryHostByMap:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForListUseClusterIn
	 * @Description: 根据集群集合查询主机集合
	 * @param
	 * @return List<HostGlobalObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 上午9:51:47
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HostGlobalObj> queryForListUseClusterIn(HostGlobalObj hostObj) {
		List<HostGlobalObj> list = new ArrayList<HostGlobalObj>();
		try {
			list = getSqlMap().queryForList("HostGlobal.queryForListUseClusterIn", hostObj);
		} catch (Exception e) {
			LogHelper.error("HostGlobal.queryForListUseClusterIn:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForListUseClusterIn
	 * @Description: 根据数据中心集合查询主机集合
	 * @param
	 * @return List<HostGlobalObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 上午9:51:47
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HostGlobalObj> queryForListUseCenterIn(HostGlobalObj hostObj) {
		List<HostGlobalObj> list = new ArrayList<HostGlobalObj>();
		try {
			list = getSqlMap().queryForList("HostGlobal.queryForListUseCenterIn", hostObj);
		} catch (Exception e) {
			LogHelper.error("HostGlobal.queryForListUseCenterIn:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: countResource
	 * @Description: 查询cpu、内存等
	 * @param
	 * @return CMSObj
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 上午10:00:46
	 */
	@Override
	public CMSObj countResourceForHost(HostGlobalObj hostObj) {
		CMSObj cmsObj = new CMSObj();
		try {
			Object obj = getSqlMap().queryForObject("HostGlobal.countResourceForHost", hostObj);
			if (obj != null) {
				cmsObj = (CMSObj) obj;
			}
		} catch (Exception e) {
			LogHelper.error("HostGlobal.countResourceForHost:" + e.getMessage()
					+ getClass().getName());
		}
		return cmsObj;
	}

	/**
	 * @Title: countResource
	 * @Description: 查询cpu、内存等
	 * @param
	 * @return CMSObj
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 上午10:00:46
	 */
	@Override
	public CMSObj countResourceForCluster(HostGlobalObj hostObj) {
		CMSObj cmsObj = new CMSObj();
		try {
			Object obj = getSqlMap().queryForObject("HostGlobal.countResourceForCluster", hostObj);
			if (obj != null) {
				cmsObj = (CMSObj) obj;
			}
		} catch (Exception e) {
			LogHelper.error("HostGlobal.countResourceForCluster:" + e.getMessage()
					+ getClass().getName());
		}
		return cmsObj;
	}

	/**
	 * @Title: countResource
	 * @Description: 查询cpu、内存等
	 * @param
	 * @return CMSObj
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 上午10:00:46
	 */
	@Override
	public CMSObj countResourceForCenter(HostGlobalObj hostObj) {
		CMSObj cmsObj = new CMSObj();
		try {
			Object obj = getSqlMap().queryForObject("HostGlobal.countResourceForCenter", hostObj);
			if (obj != null) {
				cmsObj = (CMSObj) obj;
			}
		} catch (Exception e) {
			LogHelper.error("HostGlobal.countResourceForCenter:" + e.getMessage()
					+ getClass().getName());
		}
		return cmsObj;
	}

	/**
	 * @Title: queryForClusterListByCenter
	 * @Description: 根据数据中心查询集群
	 * @param
	 * @return List<HostGlobalObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 上午11:51:58
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HostGlobalObj> queryForClusterListByCenter(HostGlobalObj hostGlobalObj) {
		List<HostGlobalObj> list = new ArrayList<HostGlobalObj>();
		try {
			list = getSqlMap()
					.queryForList("HostGlobal.queryForClusterListByCenter", hostGlobalObj);
		} catch (Exception e) {
			LogHelper.error("HostGlobal.queryForClusterListByCenter:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}

}
