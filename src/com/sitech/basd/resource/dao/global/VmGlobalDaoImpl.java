package com.sitech.basd.resource.dao.global;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import util.DomainUtil;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.resource.domain.global.VmGlobalObj;
import com.sitech.basd.resource.domain.top.TopTargetObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("vmGlobalDao")
@SuppressWarnings("all")
public class VmGlobalDaoImpl extends BaseDao implements VmGlobalDao {
	/**
	 * 
	 * @Title: queryCpuTop
	 * @Description: 查询虚拟机cpu使用率 topN
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
			list = getSqlMap().queryForList("VmGlobal.queryCpuTop", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryCpuTop:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryMemTop
	 * @Description: 查询虚拟机内存使用率topN
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
			list = getSqlMap().queryForList("VmGlobal.queryMemTop", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryMemTop:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryStoreTop
	 * @Description: 查询虚拟机存储使用率topN
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
			list = getSqlMap().queryForList("VmGlobal.queryStoreTop", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryStoreTop:" + sqlException.getMessage()
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
	public List queryCpuXData(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryCpuXData", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryCpuXData:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryCpuXData
	 * @Description: 查询内存 topN对应的x值
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
			list = getSqlMap().queryForList("VmGlobal.queryMemXData", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryMemXData:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryStoreXData
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
			list = getSqlMap().queryForList("VmGlobal.queryStoreXData", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryStoreXData:" + sqlException.getMessage()
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
			list = getSqlMap().queryForList("VmGlobal.queryCPUList", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryCPUList:" + sqlException.getMessage()
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
			list = getSqlMap().queryForList("VmGlobal.queryMemList", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryMemList:" + sqlException.getMessage()
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
			list = getSqlMap().queryForList("VmGlobal.queryStoreList", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryStoreList:" + sqlException.getMessage()
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
	public Integer countVm(VmGlobalObj obj) {
		Integer vm = 0; // vm的个数
		try {
			vm = (Integer) getSqlMap().queryForObject("VmGlobal.countVm", obj);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.countVm:" + sqlException.getMessage() + getClass().getName());
		}
		return vm;
	}

	@Override
	public List queryCpuTopClu(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryCpuTopClu", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryCpuTopClu:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List queryMemTopClu(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryMemTopClu", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryMemTopClu:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List queryStoreTopClu(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryStoreTopClu", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryStoreTopClu:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List queryCpuXDataClu(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryCpuXDataClu", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryCpuXDataClu:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List queryMemXDataClu(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryMemXDataClu", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryMemXDataClu:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List queryStoreXDataClu(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryStoreXDataClu", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryStoreXDataClu:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List queryCPUListClu(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryCPUListClu", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryCPUListClu:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List queryMemListClu(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryMemListClu", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryMemListClu:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List queryStoreListClu(Map map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryStoreListClu", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryStoreListClu:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryVmCpuTopN
	 * @Description: 查询虚拟机CpuTopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryVmCpuTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryVmCpuTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryVmCpuTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("VmGlobal.queryVmCpuTopN:" + sqlException.getMessage()
					+ sqlException, sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryClusterVmCpuTopN
	 * @Description: 查询集群虚拟机CpuTopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryClusterVmCpuTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryClusterVmCpuTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryClusterVmCpuTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("VmGlobal.queryClusterVmCpuTopN:" + sqlException.getMessage()
					+ sqlException, sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryVmMemTopN
	 * @Description: 查询虚拟机内存TopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryVmMemTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryVmMemTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryVmMemTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("VmGlobal.queryVmMemTopN:" + sqlException.getMessage()
					+ sqlException, sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryVMStoreIKbpsTopN
	 * @Description: 查询虚拟机存储写速率Topn
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-29 下午4:15:36
	 */
	public List<Data> queryVMStoreIKbpsTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryVMStoreIKbpsTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryVMStoreIKbpsTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("VmGlobal.queryVMStoreIKbpsTopN:" + sqlException.getMessage()
					+ sqlException, sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryVMStoreOKbpsTopN
	 * @Description: 查询虚拟机存储读速率Topn
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-29 下午4:16:02
	 */
	public List<Data> queryVMStoreOKbpsTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryVMStoreOKbpsTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryVMStoreOKbpsTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("VmGlobal.queryVMStoreOKbpsTopN:" + sqlException.getMessage()
					+ sqlException, sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryVMNetworkOKbpsTopN
	 * @Description: 查询虚拟机网卡读速率Topn
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-29 下午4:16:16
	 */
	public List<Data> queryVMNetworkOKbpsTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryVMNetworkOKbpsTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryVMNetworkOKbpsTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("VmGlobal.queryVMNetworkOKbpsTopN:" + sqlException.getMessage()
					+ sqlException, sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryVMNetworkIKbpsTopN
	 * @Description: 查询虚拟机网卡写速率Topn
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-29 下午4:16:27
	 */
	public List<Data> queryVMNetworkIKbpsTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryVMNetworkIKbpsTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryVMNetworkIKbpsTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("VmGlobal.queryVMNetworkIKbpsTopN:" + sqlException.getMessage()
					+ sqlException, sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryClusterVmMemTopN
	 * @Description: 查询集群虚拟机内存TopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryClusterVmMemTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryClusterVmMemTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryClusterVmMemTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("VmGlobal.queryClusterVmMemTopN:" + sqlException.getMessage()
					+ sqlException, sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryVmStorageTopN
	 * @Description: 查询虚拟机存储TopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryVmStorageTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryVmStorageTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryVmStorageTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("VmGlobal.queryVmStorageTopN:" + sqlException.getMessage()
					+ sqlException, sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryClusterVmStorageTopN
	 * @Description: 查询集群虚拟机存储TopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryClusterVmStorageTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryClusterVmStorageTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryClusterVmStorageTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("VmGlobal.queryClusterVmStorageTopN:" + sqlException.getMessage()
					+ sqlException, sqlException);
		}
		return list;
	}

	@Override
	public List<Data> queryHostVmCpuTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryHostVmCpuTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryHostVmCpuTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("VmGlobal.queryHostVmCpuTopN:" + sqlException.getMessage()
					+ sqlException, sqlException);
		}
		return list;
	}

	@Override
	public List<Data> queryHostVmMemTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryHostVmMemTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryHostVmMemTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("VmGlobal.queryHostVmMemTopN:" + sqlException.getMessage()
					+ sqlException, sqlException);
		}
		return list;
	}

	@Override
	public List<Data> queryHostVmStorageTopN(Map<String, Object> map) throws Exception {
		List<Data> list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryHostVmStorageTopN", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryHostVmStorageTopN:" + sqlException.getMessage()
					+ getClass().getName());
			throw new Exception("VmGlobal.queryHostVmStorageTopN:" + sqlException.getMessage()
					+ sqlException, sqlException);
		}
		return list;
	}

	@Override
	public List queryCPUListHost(Map<String, Object> map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryCPUListHost", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryCPUListHost:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List queryMemListHost(Map<String, Object> map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryMemListHost", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryMemListHost:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List queryStoreListHost(Map<String, Object> map) {
		List list = null;
		try {
			DomainUtil.setDomainToMap(map);
			list = getSqlMap().queryForList("VmGlobal.queryStoreListHost", map);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryStoreListHost:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForListByHost
	 * @Description: 根据主机查询记录
	 * @param
	 * @return List<VmGlobalObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-1 下午7:05:41
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<VmGlobalObj> queryForListByHost(VmGlobalObj vmGlobalObj) {
		List<VmGlobalObj> list = new ArrayList<VmGlobalObj>();
		try {
			list = getSqlMap().queryForList("VmGlobal.queryForListByHost", vmGlobalObj);
		} catch (Exception e) {
			LogHelper.error("VmGlobal.queryForListByHost:" + e.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForListByCluster
	 * @Description: 根据集群查询记录
	 * @param
	 * @return List<VmGlobalObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-1 下午7:05:41
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<VmGlobalObj> queryForListByCluster(VmGlobalObj vmGlobalObj) {
		List<VmGlobalObj> list = new ArrayList<VmGlobalObj>();
		try {
			list = getSqlMap().queryForList("VmGlobal.queryForListByCluster", vmGlobalObj);
		} catch (Exception e) {
			LogHelper.error("VmGlobal.queryForListByCluster:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForListByCenter
	 * @Description: 根据数据中心查询记录
	 * @param
	 * @return List<VmGlobalObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-1 下午7:05:41
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<VmGlobalObj> queryForListByCenter(VmGlobalObj vmGlobalObj) {
		List<VmGlobalObj> list = new ArrayList<VmGlobalObj>();
		try {
			list = getSqlMap().queryForList("VmGlobal.queryForListByCenter", vmGlobalObj);
		} catch (Exception e) {
			LogHelper.error("VmGlobal.queryForListByCenter:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryMemListVM
	 * @Description: memTOP 关联业务
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-26 下午4:41:07
	 */
	@Override
	public List<TopTargetObj> queryCPUListVM(Map<String, Object> paramMap) {
		List<TopTargetObj> list = new ArrayList<TopTargetObj>();
		try {
			DomainUtil.setDomainToMap(paramMap);
			list = getSqlMap().queryForList("VmGlobal.queryCPUListVM", paramMap);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryCPUListVM:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryMemListVM
	 * @Description: memTOP 关联业务
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-26 下午4:41:07
	 */
	@Override
	public List<TopTargetObj> queryMemListVM(Map<String, Object> paramMap) {
		List<TopTargetObj> list = new ArrayList<TopTargetObj>();
		try {
			DomainUtil.setDomainToMap(paramMap);
			list = getSqlMap().queryForList("VmGlobal.queryMemListVM", paramMap);
		} catch (Exception sqlException) {
			LogHelper.error("VmGlobal.queryMemListVM:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}
}
