package com.sitech.basd.yicloud.dao.busisystree;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTree;
import com.sitech.utils.randomid.RandomUUID;

/**
 * 
 * <p>
 * Title: TbBusiSysTreeDao
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
@Repository("tbBusiSysTreeDao")
public class TbBusiSysTreeDaoImpl extends BaseDao implements TbBusiSysTreeDao {
	/**
	 * 
	 * @Title: queryForLimitTree
	 * @Description: 查询业务中心，业务系统，应用等生成权限树
	 * @param
	 * @return List<TbBusiSysTree>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:48:23
	 */
	public List<TbBusiSysTree> queryForLimitTree(TbBusiSysTree obj) {
		List<TbBusiSysTree> lst = null;
		try {
			lst = (List<TbBusiSysTree>) getSqlMap().queryForList(
					"TbBusiSysTree.queryForLimitTree", obj);
		} catch (Exception e) {
			LogHelper.error("TbBusiSysTree.queryForLimitTree:" + e.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询业务中心，业务系统，应用等生成树
	 * @param
	 * @return List<TbBusiSysTree>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:48:23
	 */
	public List<TbBusiSysTree> queryForTree(TbBusiSysTree obj) {
		List<TbBusiSysTree> lst = new ArrayList<TbBusiSysTree>();
		try {
			lst = (List<TbBusiSysTree>) getSqlMap().queryForList(
					"TbBusiSysTree.queryForTree", obj);
		} catch (Exception e) {
			LogHelper.error("TbBusiSysTree.queryForTree:" + e.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: insertTbBusiSysTree
	 * @Description: 向业务中心的树中插入数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:48:30
	 */
	@SuppressWarnings("static-access")
	public String insertTbBusiSysTree(TbBusiSysTree obj) {
		String id = RandomUUID.getUuid();
		obj.setId(id);
		String ret = null;
		try {
			Object o = getSqlMap().insert("TbBusiSysTree.insertTbBusiSysTree",
					obj);
			ret = id;
		} catch (Exception sqlException) {
			ret = null;
			LogHelper.error("TbBusiSysTree.insertTbBusiSysTree:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteTbBusiSysTreeById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-22 下午2:30:11
	 */
	public int deleteTbBusiSysTreeById(TbBusiSysTree obj) {
		Integer ret = null;
		try {
			Object o = getSqlMap().delete(
					"TbBusiSysTree.deleteTbBusiSysTreeById", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("TbBusiSysTree.deleteTbBusiSysTreeById:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return ret;
	}

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
	public int updateTbBusiSysTreeByObj(TbBusiSysTree obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update(
					"TbBusiSysTree.updateTbBusiSysTreeByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbBusiSysTree.updateTbBusiSysTreeByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryBusiCenterSonNodesNum
	 * @Description: 查询业务中心下各类型子节点的个数
	 * @param
	 * @return TbBusiSysTree
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 22, 2013 11:39:18 AM
	 */
	public TbBusiSysTree queryBusiCenterSonNodesNum(TbBusiSysTree obj) {
		TbBusiSysTree treeObj = null;
		try {
			treeObj = (TbBusiSysTree) getSqlMap().queryForObject(
					"TbBusiSysTree.queryBusiCenterSonNodesNum", obj);
		} catch (Exception e) {
			LogHelper.error("TbBusiSysTree.queryBusiCenterSonNodesNum:"
					+ e.getMessage() + getClass().getName());
		}
		return treeObj;
	}

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
	@Override
	public int countByObj(TbBusiSysTree tbBusiSysTree) {
		int ret = -1;
		try {
			Object obj = getSqlMap().queryForObject("TbBusiSysTree.countByObj", tbBusiSysTree);
			if (obj != null) {
				ret = ((Integer) obj).intValue();
			}
		} catch (Exception e) {
			LogHelper.error("TbBusiSysTree.countByObj:"
					+ e.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title: queryForListByParentIdList
	 * @Description: 根据节点类型和父节点的id集合查询节点集合
	 * @param
	 * @return List<TbBusiSysTree>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-19 下午2:48:39
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TbBusiSysTree> queryForListByParentIdList(
			TbBusiSysTree tbBusiSysTree) {
		List<TbBusiSysTree> list = new ArrayList<TbBusiSysTree>();
		try {
			list = getSqlMap().queryForList("TbBusiSysTree.queryForListByParentIdList",tbBusiSysTree);
		} catch (Exception e) {
			LogHelper.error("TbBusiSysTree.queryForListByParentIdList: "
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}

}
