package com.sitech.basd.resource.dao.united;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.util.ReourceInDomainUtil;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.randomid.RandomUUID;

@Repository("unitedTreeDao")
public class UnitedTreeDaoImpl extends BaseDao implements UnitedTreeDao {
	@Autowired
	private ReourceInDomainUtil reourceInDomainUtil;

	/**
	 * 
	 * @Title: queryForUnitedTree
	 * @Description: 查询统一树
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @throws Exception
	 * @createtime Jul 17, 2013 11:51:37 AM
	 */
	public List<UnitedTreeObj> queryForUnitedTree(UnitedTreeObj obj) throws SQLException {
		List<UnitedTreeObj> list = null;

		try {
			list = (List<UnitedTreeObj>) getSqlMap().queryForList("UnitedTree.queryForUnitedTree",
					obj);
		} catch (SQLException sqlException) {
			LogHelper.error("UnitedTree.queryForUnitedTree:" + sqlException.getMessage()
					+ getClass().getName());
			throw sqlException;
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询单个节点
	 * @param
	 * @return UnitedTreeObj
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Aug 2, 2013 4:40:43 PM
	 */
	public UnitedTreeObj queryByObj(UnitedTreeObj obj) throws SQLException {
		UnitedTreeObj reObj = null;
		List<UnitedTreeObj> treeLst = queryForUnitedTree(obj);
		if (treeLst != null && treeLst.size() > 0) {
			reObj = treeLst.get(0);
		}
		return reObj;
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入一条数据
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 22, 2013 2:26:15 PM
	 */
	public String insertByObj(UnitedTreeObj obj) throws SQLException {
		String id = RandomUUID.getUuid();
		if (obj.getDomain() == null || "".equals(obj.getDomain())) {
			/*
			 * 用于自动同步数据使用
			 */
			obj.setDomain(reourceInDomainUtil.initResourceDomainBySessionAndConfig(obj
					.getConnect_id()));
		}
		try {
			// 设置UUid
			obj.setId(id);
			Object o = getSqlMap().insert("UnitedTree.insertByObj", obj);
		} catch (SQLException sqlException) {
			LogHelper.error("UnitedTree.insertByObj:" + sqlException.getMessage());
			throw sqlException;
		}
		return id;
	}

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新一条数据
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 22, 2013 2:26:47 PM
	 */
	public int updateByObj(UnitedTreeObj obj) throws SQLException {
		int ret = 0;
		try {
			Object o = getSqlMap().update("UnitedTree.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (SQLException sqlException) {
			ret = -1;
			LogHelper.error("UnitedTree.updateByObj:" + sqlException.getMessage());
			throw sqlException;
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除一条数据
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 22, 2013 2:27:11 PM
	 */
	public int deleteByObj(UnitedTreeObj obj) throws SQLException {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("UnitedTree.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (SQLException sqlException) {
			ret = -1;
			LogHelper.error("UnitedTree.deleteByObj:" + sqlException.getMessage());
			throw sqlException;
		}
		return ret;
	}

	/**
	 * 
	 * @Title: getAllUniqueEntity
	 * @Description:获取所有唯一实体（用于数据比对）
	 * @param
	 * @return List<String>
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 25, 2013 8:55:12 PM
	 */
	public List<String> getAllUniqueEntity(UnitedTreeObj obj) throws SQLException {
		List list = null;
		try {
			list = getSqlMap().queryForList("UnitedTree.getAllUniqueEntity", obj);
		} catch (SQLException sqlException) {
			LogHelper.error("UnitedTree.getAllUniqueEntity:" + sqlException.getMessage()
					+ getClass().getName());
			throw sqlException;
		}
		return list;
	}

	/**
	 * 
	 * @Title: deleteByUuidAndConnectId
	 * @Description: 根据实体ID及连接ID删除对象
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-7-26 下午8:13:58
	 */
	public int deleteByUuidAndConnectId(UnitedTreeObj obj) throws SQLException {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("UnitedTree.deleteByUuidAndConnectId", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (SQLException sqlException) {
			ret = -1;
			LogHelper.error("UnitedTree.deleteByUuidAndConnectId:" + sqlException.getMessage());
			throw sqlException;
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryForAbstract
	 * @Description: 统一树首页的摘要
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws SQLException
	 * @throws Exception
	 * @createtime 2013-7-26 下午8:13:58
	 */
	public List<Map<String, String>> queryForAbstract() {
		List<Map<String, String>> list = null;

		try {
			list = getSqlMap().queryForList("UnitedTree.getIndexAbstract");
		} catch (SQLException sqlException) {
			LogHelper.error("UnitedTree.getIndexAbstract:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForTreeListUseIn
	 * @Description: 查询出满足条件的实体集合
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-14 上午9:48:43
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UnitedTreeObj> queryForTreeListUseIn(UnitedTreeObj unitedTreeObj) {
		List<UnitedTreeObj> list = new ArrayList<UnitedTreeObj>();
		try {
			list = getSqlMap().queryForList("UnitedTree.queryForTreeListUseIn", unitedTreeObj);
		} catch (Exception e) {
			LogHelper.error("UnitedTree.queryForTreeListUseIn: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryByName
	 * @Description: 是否存在相同名字的虚拟机
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author yanggl_bj
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2013-8-15
	 */
	public int queryName(UnitedTreeObj unitedTreeObj) throws SQLException {
		int ret = 0;
		try {
			Object o = getSqlMap().queryForObject("UnitedTree.queryName", unitedTreeObj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (SQLException sqlException) {
			ret = -1;
			LogHelper.error("UnitedTree.queryName:" + sqlException.getMessage());
			throw sqlException;
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryForUnitedTree
	 * @Description: 查询权限树
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 17, 2013 11:51:37 AM
	 */
	public List<UnitedTreeObj> queryForAuthTree(UnitedTreeObj obj) throws SQLException {
		List<UnitedTreeObj> list = new ArrayList<UnitedTreeObj>();
		try {
			list = getSqlMap().queryForList("UnitedTree.queryForAuthTree", obj);
		} catch (Exception e) {
			LogHelper.error("UnitedTree.queryForAuthTree: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: getExpandNodesForVM
	 * @Description: 查询展开虚拟机所需节点集合（虚拟机所在的主机在集群下,此种情况是正常情况）
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-13 下午7:01:12
	 */
	public List getExpandNodesForVMInHostAndCluster(UnitedTreeObj obj) throws SQLException {
		List<String> list = new ArrayList<String>();
		try {
			list = getSqlMap().queryForList("UnitedTree.getExpandNodesForVMInHostAndCluster", obj);
		} catch (Exception e) {
			LogHelper.error("UnitedTree.getExpandNodesForVMInHostAndCluster: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: getExpandNodesForVM
	 * @Description: 查询展开虚拟机所需节点集合（虚拟机所在的主机在集群下同时存在地域结点,此种情况是正常情况）
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-11-17 下午7:01:12
	 */
	public List getExpandNodesForVmHasLoaction(UnitedTreeObj obj) throws SQLException {
		List<String> list = new ArrayList<String>();
		try {
			list = getSqlMap().queryForList("UnitedTree.getExpandNodesHasLocation", obj);
		} catch (Exception e) {
			LogHelper.error("UnitedTree.getExpandNodesHasLocation: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: getExpandNodesForVMInCluster
	 * @Description: 查询展开虚拟机所需节点集合（此情况适用于主机在集群下，不在主机下）
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-14 上午10:25:38
	 */
	public List getExpandNodesForVMInCluster(UnitedTreeObj obj) {
		List<String> list = new ArrayList<String>();
		try {
			list = getSqlMap().queryForList("UnitedTree.getExpandNodesForVMInCluster", obj);
		} catch (Exception e) {
			LogHelper.error("UnitedTree.getExpandNodesForVMInCluster: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: getExpandNodesForVMInHostAndDc
	 * @Description: 查询展开虚拟机所需节点集合（此情况适用于虚拟机所在的主机在数据中心下，不在集群下）
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-14 上午10:25:38
	 */
	public List getExpandNodesForVMInHostAndDc(UnitedTreeObj obj) {
		List<String> list = new ArrayList<String>();
		try {
			list = getSqlMap().queryForList("UnitedTree.getExpandNodesForVMInHostAndDc", obj);
		} catch (Exception e) {
			LogHelper.error("UnitedTree.getExpandNodesForVMInHostAndDc: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: getExpandNodesForVMInCluster
	 * @Description: 查询展开主机所需节点集合
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-13 下午7:01:12
	 */
	public List getExpandNodesForHostInCluster(UnitedTreeObj obj) throws SQLException {
		List<String> list = new ArrayList<String>();
		try {
			list = getSqlMap().queryForList("UnitedTree.getExpandNodesForHostInCluster", obj);
		} catch (Exception e) {
			LogHelper.error("UnitedTree.getExpandNodesForHost: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: getExpandNodesForVM
	 * @Description: 查询展开虚拟机所需节点集合（主机在集群下）
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-13 下午7:01:12
	 */
	public List getExpandNodesForHosthasLocation(UnitedTreeObj obj) throws SQLException {
		List<String> list = new ArrayList<String>();
		try {
			list = getSqlMap().queryForList("UnitedTree.getExpandNodesForHostHasLocation", obj);
		} catch (Exception e) {
			LogHelper.error("UnitedTree.getExpandNodesForHostHasLocation: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: getExpandNodesForVMInCluster
	 * @Description: 查询展开主机所需节点集合
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-13 下午7:01:12
	 */
	public List getExpandNodesForHostInDc(UnitedTreeObj obj) throws SQLException {
		List<String> list = new ArrayList<String>();
		try {
			list = getSqlMap().queryForList("UnitedTree.getExpandNodesForHostInDc", obj);
		} catch (Exception e) {
			LogHelper.error("UnitedTree.getExpandNodesForHost: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: updateOperateByObj
	 * @Description: 更新一条数据
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-09-13
	 */
	public int updateOperateByObj(UnitedTreeObj obj) throws SQLException {
		int ret = 0;
		try {
			Object o = getSqlMap().update("UnitedTree.updateOperateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (SQLException sqlException) {
			ret = -1;
			LogHelper.error("UnitedTree.updateOperateByObj:" + sqlException.getMessage());
			throw sqlException;
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryNetDomain
	 * @Description: 查询网络域
	 * @param
	 * @return list
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-09-14
	 */
	public List queryNetDomain() {
		List list = null;
		try {
			list = getSqlMap().queryForList("UnitedTree.queryNetDomain");
		} catch (Exception e) {
			LogHelper
					.error("UnitedTree.queryNetDomain: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: querySubNet
	 * @Description: 查询子网络域
	 * @param
	 * @return list
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-09-14
	 */
	public List querySubNet(UnitedTreeObj obj) {
		List list = null;
		try {
			list = getSqlMap().queryForList("UnitedTree.querySubNet", obj);
		} catch (Exception e) {
			LogHelper.error("UnitedTree.querySubNet: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: updateObjById
	 * @Description: 通过id更新obj
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-10-26 下午4:39:27
	 */
	public int updateObjById(UnitedTreeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("UnitedTree.updateObjById", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (SQLException sqlException) {
			ret = -1;
			LogHelper.error("UnitedTree.updateObjById:" + sqlException.getMessage());
		}
		return ret;
	}

	/**
	 * @Title: queryForListForWorkOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-19 下午3:49:40
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UnitedTreeObj> queryForListForWorkOrder(UnitedTreeObj unitedTreeObj) {
		List<UnitedTreeObj> list = new ArrayList<UnitedTreeObj>();
		try {
			list = getSqlMap().queryForList("UnitedTree.queryForListForWorkOrder", unitedTreeObj);
		} catch (Exception e) {
			LogHelper.error("UnitedTree.queryForListForWorkOrder: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForCenterTreeList
	 * @Description: 统计数据中心
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-3 下午6:52:09
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UnitedTreeObj> queryForCenterTreeList(UnitedTreeObj unitedTreeObj) {
		List<UnitedTreeObj> list = new ArrayList<UnitedTreeObj>();
		try {
			list = getSqlMap().queryForList("UnitedTree.queryForCenterTreeList", unitedTreeObj);
		} catch (Exception e) {
			LogHelper.error("UnitedTree.queryForCenterTreeList: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForListUnionParent
	 * @Description:查询出父节点信息
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-22 下午7:12:14
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UnitedTreeObj> queryForListUnionParent(UnitedTreeObj unitedTreeObj) {
		List<UnitedTreeObj> treeList = new ArrayList<UnitedTreeObj>();
		try {
			treeList = getSqlMap()
					.queryForList("UnitedTree.queryForListUnionParent", unitedTreeObj);
		} catch (Exception e) {
			LogHelper.error("UnitedTree.queryForListUnionParent: " + e.getMessage()
					+ e.getClass().getName());
		}
		return treeList;
	}

	/**
	 * @Title: queryForListByObj
	 * @Description: 查询列表
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @Date 2014-4-23 下午12:36:20
	 * @author lipp
	 * @param treeObj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UnitedTreeObj> queryForListByObj(UnitedTreeObj treeObj) {
		List<UnitedTreeObj> treeList = new ArrayList<UnitedTreeObj>();
		try {
			treeList = getSqlMap().queryForList("UnitedTree.queryForListByObj", treeObj);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("UnitedTree.queryForListByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return treeList;
	}

}
