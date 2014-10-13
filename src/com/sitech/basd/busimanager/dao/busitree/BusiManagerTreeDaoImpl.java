package com.sitech.basd.busimanager.dao.busitree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sitech.basd.busimanager.domain.busitree.BusiManagerTree;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.resource.domain.top.TopTargetObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.util.session.UserSession;

/**
 * 
 * <p>
 * Title: BusiManagerTreeDao
 * </p>
 * <p>
 * Description: 业务系统树数据库Dao实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-5-20 下午1:44:34
 * 
 */
@Repository("BusiManagerTreeDao")
public class BusiManagerTreeDaoImpl extends BaseDao implements BusiManagerTreeDao {
	/** 获取用户所在域的标识 */
	private static final String domain = UserSession.getHttpSessionAttribute(Constant.USER_DOMAIN, String.class);

	/**
	 * 
	 * @Title: queryForLimitTree
	 * @Description: 查询业务中心，业务系统，应用等生成权限树
	 * @param
	 * @return List<BusiManagerTree>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:48:23
	 */
	// public List<BusiManagerTree> queryForLimitTree(BusiManagerTree obj) {
	// List<BusiManagerTree> lst = null;
	// try {
	// lst = (List<BusiManagerTree>) getSqlMap().queryForList(
	// "BusiManagerTree.queryForLimitTree", obj);
	// } catch (Exception e) {
	// LogHelper.error("BusiManagerTree.queryForLimitTree:" + e.getMessage()
	// + getClass().getName());
	// }
	// return lst;
	// }
	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询业务中心，业务系统，应用等生成树
	 * @param
	 * @return List<BusiManagerTree>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:48:23
	 */
	public List<BusiManagerTree> queryForTree(BusiManagerTree obj) {
		List<BusiManagerTree> lst = new ArrayList<BusiManagerTree>();
		try {
			lst = (List<BusiManagerTree>) getSqlMap().queryForList("BusiManagerTree.queryForTree", obj);
		} catch (Exception e) {
			LogHelper.error("BusiManagerTree.queryForTree:" + e.getMessage() + getClass().getName());
		}
		return lst;
	}

	@Override
	public List<BusiManagerTree> queryForLimitTree(BusiManagerTree obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertBusiManagerTree(BusiManagerTree obj) throws Exception {
		try {
			Object o = getSqlMap().insert("BusiManagerTree.insertForTree", obj);
		} catch (Exception e) {
			LogHelper.error("BusiManagerTree.insertForTree:" + e.getMessage());
			throw new Exception(e);
		}
	}

	@Override
	public int deleteBusiManagerTreeById(BusiManagerTree obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("BusiManagerTree.deleteForTree", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("BusiManagerTree.deleteForTree:" + e.getMessage());
		}
		return ret;
	}

	@Override
	public int updateBusiManagerTreeByObj(BusiManagerTree obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("BusiManagerTree.updateForTree", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("BusiManagerTree.updateForTree:" + e.getMessage());
		}
		return ret;
	}

	@Override
	public BusiManagerTree queryBusiCenterSonNodesNum(BusiManagerTree obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByObj(BusiManagerTree BusiManagerTree) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BusiManagerTree> queryForListByParentIdList(BusiManagerTree BusiManagerTree) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @Title: insertBusiManagerTree
	 * @Description: 向业务中心的树中插入数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:48:30
	 */
	// @SuppressWarnings("static-access")
	// public String insertBusiManagerTree(BusiManagerTree obj) {
	// String id = RandomUUID.getUuid();
	// obj.setId(id);
	// String ret = null;
	// try {
	// Object o = getSqlMap().insert("BusiManagerTree.insertBusiManagerTree",
	// obj);
	// ret = id;
	// } catch (Exception sqlException) {
	// ret = null;
	// LogHelper.error("BusiManagerTree.insertBusiManagerTree:"
	// + sqlException.getMessage() + getClass().getName());
	// }
	// return ret;
	// }
	/**
	 * 
	 * @Title: deleteBusiManagerTreeById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-22 下午2:30:11
	 */
	// public int deleteBusiManagerTreeById(BusiManagerTree obj) {
	// Integer ret = null;
	// try {
	// Object o = getSqlMap().delete(
	// "BusiManagerTree.deleteBusiManagerTreeById", obj);
	// if (o != null) {
	// ret = Integer.parseInt(o.toString());
	// }
	// } catch (Exception sqlException) {
	// ret = -1;
	// LogHelper.error("BusiManagerTree.deleteBusiManagerTreeById:"
	// + sqlException.getMessage() + getClass().getName());
	// }
	// return ret;
	// }
	/**
	 * 
	 * @Title: updateTreeNode
	 * @Description: 更新树节点信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 30, 2013 6:54:45 PM
	 */
	// public int updateBusiManagerTreeByObj(BusiManagerTree obj) {
	// int ret = 0;
	// try {
	// Object o = getSqlMap().update(
	// "BusiManagerTree.updateBusiManagerTreeByObj", obj);
	// if (o != null) {
	// ret = Integer.parseInt(o.toString());
	// }
	// } catch (Exception sqlexception) {
	// ret = -1;
	// LogHelper.error("BusiManagerTree.updateBusiManagerTreeByObj:"
	// + sqlexception.getMessage() + getClass().getName());
	// }
	// return ret;
	// }
	/**
	 * 
	 * @Title: queryBusiCenterSonNodesNum
	 * @Description: 查询业务中心下各类型子节点的个数
	 * @param
	 * @return BusiManagerTree
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 22, 2013 11:39:18 AM
	 */
	// public BusiManagerTree queryBusiCenterSonNodesNum(BusiManagerTree obj) {
	// BusiManagerTree treeObj = null;
	// try {
	// treeObj = (BusiManagerTree) getSqlMap().queryForObject(
	// "BusiManagerTree.queryBusiCenterSonNodesNum", obj);
	// } catch (Exception e) {
	// LogHelper.error("BusiManagerTree.queryBusiCenterSonNodesNum:"
	// + e.getMessage() + getClass().getName());
	// }
	// return treeObj;
	// }
	/**
	 * @Title: countByObj
	 * @Description: 统计业务系统下各子节点个数
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-14 下午5:41:08
	 */
	// public int countByObj(BusiManagerTree BusiManagerTree) {
	// int ret = -1;
	// try {
	// Object obj = getSqlMap().queryForObject("BusiManagerTree.countByObj",
	// BusiManagerTree);
	// if (obj != null) {
	// ret = ((Integer) obj).intValue();
	// }
	// } catch (Exception e) {
	// LogHelper.error("BusiManagerTree.countByObj:"
	// + e.getMessage() + getClass().getName());
	// }
	// return ret;
	// }
	/**
	 * @Title: queryForListByParentIdList
	 * @Description: 根据节点类型和父节点的id集合查询节点集合
	 * @param
	 * @return List<BusiManagerTree>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-19 下午2:48:39
	 */
	// @SuppressWarnings("unchecked")
	// @Override
	// public List<BusiManagerTree> queryForListByParentIdList(
	// BusiManagerTree BusiManagerTree) {
	// List<BusiManagerTree> list = new ArrayList<BusiManagerTree>();
	// try {
	// list =
	// getSqlMap().queryForList("BusiManagerTree.queryForListByParentIdList",BusiManagerTree);
	// } catch (Exception e) {
	// LogHelper.error("BusiManagerTree.queryForListByParentIdList: "
	// + e.getMessage() + getClass().getName());
	// }
	// return list;
	// }
	public Map<Long, Long> getTypeCounts(BusiManagerTree obj) {
		Map<Long, Long> map = new HashMap<Long, Long>();
		try {
			map = getSqlMap().queryForMap("BusiManagerTree.getTypeNum", obj, "type", "typecount");
		} catch (Exception sqlexception) {
			LogHelper.error("BusiManagerTree.getTypeNum:" + sqlexception.getMessage() + getClass().getName());
		}
		return map;
	}

	/**
	 * @Title: queryForVmList
	 * @Description: 获取对应的虚拟机列表（去掉已存在的）
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-13 下午2:25:16
	 */
	public List<VMHostObj> queryForVmList(VMHostObj obj) {
		List<VMHostObj> lst = new ArrayList<VMHostObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("BusiManagerTree.queryForVmCount", obj)).intValue());
			}
			lst = (List<VMHostObj>) getSqlMap().queryForList("BusiManagerTree.queryForVmList", obj);
		} catch (Exception e) {
			LogHelper.error("BusiManagerTree.queryForVmList:" + e.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryVmTopN
	 * @Description: 查询业务系统下虚拟机CpuTopN（不带承载业务）
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<Data> queryVmCpuTopN(Map<String, Object> map) throws Exception {
		map.put("domain", domain);
		List<Data> list = null;
		try {
			list = getSqlMap().queryForList("BusiManagerTree.queryForCpu", map);
		} catch (Exception sqlException) {
			LogHelper.error("BusiManagerTree.queryForCpu:" + sqlException.getMessage() + getClass().getName());
			throw new Exception("BusiManagerTree.queryForCpu:" + sqlException.getMessage() + sqlException, sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryVmTopN
	 * @Description: 查询子系统下的虚拟机CpuTopN（带承载业务）
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<Data> queryVmCpuTopN2(Map<String, Object> map) throws Exception {
		map.put("domain", domain);
		List<Data> list = null;
		try {
			list = getSqlMap().queryForList("BusiManagerTree.queryForCpu2", map);
		} catch (Exception sqlException) {
			LogHelper.error("BusiManagerTree.queryForCpu2:" + sqlException.getMessage() + getClass().getName());
			throw new Exception("BusiManagerTree.queryForCpu2:" + sqlException.getMessage() + sqlException,
					sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryVmMemTopN
	 * @Description: 查询业务子系统下虚拟机menTopN（不带承载业务）
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<Data> queryVmMemTopN(Map<String, Object> map) throws Exception {
		map.put("domain", domain);
		List<Data> list = null;
		try {
			list = getSqlMap().queryForList("BusiManagerTree.queryForMem", map);
		} catch (Exception sqlException) {
			LogHelper.error("BusiManagerTree.queryForMem:" + sqlException.getMessage() + getClass().getName());
			throw new Exception("BusiManagerTree.queryForMem:" + sqlException.getMessage() + sqlException, sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryVmMemTopN2
	 * @Description: 查询业务子系统下虚拟机menTopN（带承载业务）
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<Data> queryVmMemTopN2(Map<String, Object> map) throws Exception {
		map.put("domain", domain);
		List<Data> list = null;
		try {
			list = getSqlMap().queryForList("BusiManagerTree.queryForMem2", map);
		} catch (Exception sqlException) {
			LogHelper.error("BusiManagerTree.queryForMem2:" + sqlException.getMessage() + getClass().getName());
			throw new Exception("BusiManagerTree.queryForMem2:" + sqlException.getMessage() + sqlException,
					sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryVmStoreTopN
	 * @Description: 查询虚拟机StoreTopN
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<Data> queryVmStoreTopN(Map<String, Object> map) throws Exception {
		map.put("domain", domain);
		List<Data> list = null;
		try {
			list = getSqlMap().queryForList("BusiManagerTree.queryForStore", map);
		} catch (Exception sqlException) {
			LogHelper.error("BusiManagerTree.queryForStore:" + sqlException.getMessage() + getClass().getName());
			throw new Exception("BusiManagerTree.queryForStore:" + sqlException.getMessage() + sqlException,
					sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryVmCpuList
	 * @Description: 查询业务子系统下虚拟机cpuList（不带承载业务）
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<TopTargetObj> queryVmCpuList(Map<String, Object> map) throws Exception {
		map.put("domain", domain);
		List<TopTargetObj> list = null;
		try {
			list = getSqlMap().queryForList("BusiManagerTree.queryCPUList", map);
		} catch (Exception sqlException) {
			LogHelper.error("BusiManagerTree.queryCPUList:" + sqlException.getMessage() + getClass().getName());
			throw new Exception("BusiManagerTree.queryCPUList:" + sqlException.getMessage() + sqlException,
					sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryVmCpuList2
	 * @Description: 查询业务子系统下虚拟机cpuList（带承载业务）
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<TopTargetObj> queryVmCpuList2(Map<String, Object> map) throws Exception {
		map.put("domain", domain);
		List<TopTargetObj> list = null;
		try {
			list = getSqlMap().queryForList("BusiManagerTree.queryCPUList2", map);
		} catch (Exception sqlException) {
			LogHelper.error("BusiManagerTree.queryCPUList2:" + sqlException.getMessage() + getClass().getName());
			throw new Exception("BusiManagerTree.queryCPUList2:" + sqlException.getMessage() + sqlException,
					sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryVmMemList
	 * @Description: 查询业务子系统虚拟机MemList（不带承载业务）
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<TopTargetObj> queryVmMemList(Map<String, Object> map) throws Exception {
		map.put("domain", domain);
		List<TopTargetObj> list = null;
		try {
			list = getSqlMap().queryForList("BusiManagerTree.queryMemList", map);
		} catch (Exception sqlException) {
			LogHelper.error("BusiManagerTree.queryMemList:" + sqlException.getMessage() + getClass().getName());
			throw new Exception("BusiManagerTree.queryMemList:" + sqlException.getMessage() + sqlException,
					sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryVmMemList2
	 * @Description: 业务子系统下查询虚拟机MemList（带承载业务）
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<TopTargetObj> queryVmMemList2(Map<String, Object> map) throws Exception {
		map.put("domain", domain);
		List<TopTargetObj> list = null;
		try {
			list = getSqlMap().queryForList("BusiManagerTree.queryMemList2", map);
		} catch (Exception sqlException) {
			LogHelper.error("BusiManagerTree.queryMemList2:" + sqlException.getMessage() + getClass().getName());
			throw new Exception("BusiManagerTree.queryMemList2:" + sqlException.getMessage() + sqlException,
					sqlException);
		}
		return list;
	}

	/** ***********业务系统下的topN************************************ */

	/**
	 * 
	 * @Title: querySysVmCpuTopN
	 * @Description: 业务系统下查询虚拟机cpuTopN
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<Data> querySysVmCpuTopN(Map<String, Object> map) throws Exception {
		map.put("domain", domain);
		List<Data> list = null;
		try {
			list = getSqlMap().queryForList("BusiManagerTree.queryForSysCpu", map);
		} catch (Exception sqlException) {
			LogHelper.error("BusiManagerTree.queryForSysCpu:" + sqlException.getMessage() + getClass().getName());
			throw new Exception("BusiManagerTree.queryForSysCpu:" + sqlException.getMessage() + sqlException,
					sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryVmCpuTopN1
	 * @Description: 查询业务系统下的虚拟机CpuTopN（带承载业务）
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<Data> queryVmCpuTopN1(Map<String, Object> map) throws Exception {
		map.put("domain", domain);
		List<Data> list = null;
		try {
			list = getSqlMap().queryForList("BusiManagerTree.queryForCpu1", map);
		} catch (Exception sqlException) {
			LogHelper.error("BusiManagerTree.queryForCpu1:" + sqlException.getMessage() + getClass().getName());
			throw new Exception("BusiManagerTree.queryForCpu1:" + sqlException.getMessage() + sqlException,
					sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: querySysVmMemTopN
	 * @Description: 业务系统下查询虚拟机menTopN（不带承载业务）
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<Data> querySysVmMemTopN(Map<String, Object> map) throws Exception {
		map.put("domain", domain);
		List<Data> list = null;
		try {
			list = getSqlMap().queryForList("BusiManagerTree.queryForSysMem", map);
		} catch (Exception sqlException) {
			LogHelper.error("BusiManagerTree.queryForSysMem:" + sqlException.getMessage() + getClass().getName());
			throw new Exception("BusiManagerTree.queryForSysMem:" + sqlException.getMessage() + sqlException,
					sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryVmMemTopN1
	 * @Description: 查询业务系统下虚拟机menTopN（带承载业务）
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<Data> queryVmMemTopN1(Map<String, Object> map) throws Exception {
		map.put("domain", domain);
		List<Data> list = null;
		try {
			list = getSqlMap().queryForList("BusiManagerTree.queryForMem1", map);
		} catch (Exception sqlException) {
			LogHelper.error("BusiManagerTree.queryForMem1:" + sqlException.getMessage() + getClass().getName());
			throw new Exception("BusiManagerTree.queryForMem1:" + sqlException.getMessage() + sqlException,
					sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: querySysVmCpuList
	 * @Description: 业务系统下查询虚拟机cpuList（不带承载业务）
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<TopTargetObj> querySysVmCpuList(Map<String, Object> map) throws Exception {
		map.put("domain", domain);
		List<TopTargetObj> list = null;
		try {
			list = getSqlMap().queryForList("BusiManagerTree.querySysCPUList", map);
		} catch (Exception sqlException) {
			LogHelper.error("BusiManagerTree.querySysCPUList:" + sqlException.getMessage() + getClass().getName());
			throw new Exception("BusiManagerTree.querySysCPUList:" + sqlException.getMessage() + sqlException,
					sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryVmCpuList1
	 * @Description: 查询业务系统下虚拟机cpuList（带承载业务）
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<TopTargetObj> queryVmCpuList1(Map<String, Object> map) throws Exception {
		map.put("domain", domain);
		List<TopTargetObj> list = null;
		try {
			list = getSqlMap().queryForList("BusiManagerTree.queryCPUList1", map);
		} catch (Exception sqlException) {
			LogHelper.error("BusiManagerTree.queryCPUList1:" + sqlException.getMessage() + getClass().getName());
			throw new Exception("BusiManagerTree.queryCPUList1:" + sqlException.getMessage() + sqlException,
					sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: querySysVmMemList
	 * @Description: 业务系统下查询虚拟机MemList（不带承载业务）
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<TopTargetObj> querySysVmMemList(Map<String, Object> map) throws Exception {
		map.put("domain", domain);
		List<TopTargetObj> list = null;
		try {
			list = getSqlMap().queryForList("BusiManagerTree.querySysMemList", map);
		} catch (Exception sqlException) {
			LogHelper.error("BusiManagerTree.querySysMemList:" + sqlException.getMessage() + getClass().getName());
			throw new Exception("BusiManagerTree.querySysMemList:" + sqlException.getMessage() + sqlException,
					sqlException);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryVmMemList1
	 * @Description: 业务系统下查询虚拟机MemList（带承载业务）
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<TopTargetObj> queryVmMemList1(Map<String, Object> map) throws Exception {
		map.put("domain", domain);
		List<TopTargetObj> list = null;
		try {
			list = getSqlMap().queryForList("BusiManagerTree.queryMemList1", map);
		} catch (Exception sqlException) {
			LogHelper.error("BusiManagerTree.queryMemList1:" + sqlException.getMessage() + getClass().getName());
			throw new Exception("BusiManagerTree.queryMemList1:" + sqlException.getMessage() + sqlException,
					sqlException);
		}
		return list;
	}

	@Override
	public List<BusiManagerTree> queryAllStatisticsCount(BusiManagerTree BusiManagerTree) {
		List<BusiManagerTree> lst = new ArrayList<BusiManagerTree>();
		try {
			lst = (List<BusiManagerTree>) getSqlMap().queryForList("BusiManagerTree.queryAllStatisticsCount",
					BusiManagerTree);
		} catch (Exception e) {
			LogHelper.error("BusiManagerTree.queryForVmList:" + e.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryExpandNodes
	 * @Description: 查询展开树所需结点(虚拟机)
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List queryExpandNodes(BusiManagerTree busi) throws Exception {
		List lst = new ArrayList<BusiManagerTree>();
		try {
			lst = getSqlMap().queryForList("BusiManagerTree.getExpandNodesForVm", busi);
		} catch (Exception e) {
			LogHelper.error("BusiManagerTree.getExpandNodesForVm:" + e.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: querySysVmMemList（带承载业务）
	 * @Description: 查询展开树所需结点
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List queryExpandNodes1(BusiManagerTree busi) throws Exception {
		List lst = new ArrayList<BusiManagerTree>();
		try {
			lst = getSqlMap().queryForList("BusiManagerTree.getExpandNodesForVm1", busi);
		} catch (Exception e) {
			LogHelper.error("BusiManagerTree.getExpandNodesForVm1:" + e.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryExpandNodesForBusi
	 * @Description: 查询展开树所需结点
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List queryExpandNodesForBusi(BusiManagerTree busi) throws Exception {
		List lst = new ArrayList<BusiManagerTree>();
		try {
			lst = getSqlMap().queryForList("BusiManagerTree.getExpandNodesForbusi", busi);
		} catch (Exception e) {
			LogHelper.error("BusiManagerTree.getExpandNodesForbusi:" + e.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 *
	 * @see com.sitech.basd.busimanager.dao.busitree.BusiManagerTreeDao#getBusiTreeList(java.lang.String)
	 */
	@Override
	public List<BusiManagerTree> getBusiTreeList(String id,String type) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("type",type);
		List lst = new ArrayList<BusiManagerTree>();
		try {
			lst =  getSqlMap().queryForList("BusiManagerTree.getBusiTreeList", map);
		} catch (Exception e) {
			LogHelper.error("BusiManagerTree.getBusiTreeList:" + e.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	/**
	 *
	 * @see com.sitech.basd.busimanager.dao.busitree.BusiManagerTreeDao#getVmhostListByBusiId(java.lang.String, java.util.List)
	 */
	@Override
	public void getVmhostListByBusiId(BusiManagerTree bt, List list) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("id", bt.getId());
		map.put("businame", bt.getName());
		List lst = new ArrayList();
		try {
			lst =  getSqlMap().queryForList("BusiManagerTree.getVmhostList", map);
			list.addAll(lst);
		} catch (Exception e) {
			LogHelper.error("BusiManagerTree.getVmhostList:" + e.getMessage()
					+ getClass().getName());
		}
	}
	public List<BusiManagerTree> queryForList(BusiManagerTree obj){
		List<BusiManagerTree> lst = new ArrayList<BusiManagerTree>();
		try {
			lst = (List<BusiManagerTree>) getSqlMap().queryForList("BusiManagerTree.queryForList", obj);
		} catch (Exception e) {
			LogHelper.error("BusiManagerTree.queryForList:" + e.getMessage() + getClass().getName());
		}
		return lst;
	}
	
}
