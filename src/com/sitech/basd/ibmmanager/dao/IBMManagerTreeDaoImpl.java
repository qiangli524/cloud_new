package com.sitech.basd.ibmmanager.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.ibmmanager.domain.IBMManagerTreeObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

/**
 * 
 * <p>
 * Title: IBMManagerTreeDaoImpl
 * </p>
 * <p>
 * Description: IBM小型机相关操作
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
@Repository("ibmManagerTreeDao")
public class IBMManagerTreeDaoImpl extends BaseDao implements IBMManagerTreeDao {
	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询左侧树节点
	 * @param
	 * @return List<IBMManagerTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-2 下午5:25:34
	 */
	@Override
	public List<IBMManagerTreeObj> queryForTree(IBMManagerTreeObj obj) {
		List<IBMManagerTreeObj> list = new ArrayList<IBMManagerTreeObj>();
		try {
			list = getSqlMap().queryForList("IBMManagerTree.queryForTree", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTree.queryForTree:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: insertTree
	 * @Description: 插入操作
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-2 下午5:26:58
	 */
	@Override
	public void insertTree(IBMManagerTreeObj obj) {
		try {
			Object o = getSqlMap().insert("IBMManagerTree.insertForTree", obj);
		} catch (Exception e) {
			LogHelper.error("IBMManagerTree.insertForTree:" + e.getMessage());
		}
	}

	/**
	 * 
	 * @Title: deleteTreeById
	 * @Description: 删除操作
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-2 下午5:27:25
	 */
	@Override
	public int deleteTreeById(IBMManagerTreeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("IBMManagerTree.deleteForTree", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("IBMManagerTree.deleteForTree:" + e.getMessage());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateTreeByObj
	 * @Description: 修改操作
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-2 下午5:27:38
	 */
	@Override
	public int updateTreeByObj(IBMManagerTreeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("IBMManagerTree.updateForTree", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("IBMManagerTree.updateForTree:" + e.getMessage());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryExpandNodesForPower
	 * @Description: 查询展开树所需结点(整机)
	 * @param
	 * @return List<IBMManagerTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午5:42:37
	 */
	@Override
	public List<IBMManagerTreeObj> queryExpandNodesForPower(
			IBMManagerTreeObj obj) {
		List<IBMManagerTreeObj> list = new ArrayList<IBMManagerTreeObj>();
		try {
			list = getSqlMap().queryForList(
					"IBMManagerTree.queryExpandNodesForPower", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTree.queryExpandNodesForPower:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryExpandNodesForLP
	 * @Description: 查询展开树所需节点(逻辑分区)
	 * @param
	 * @return List<IBMManagerTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午5:41:41
	 */
	@Override
	public List<IBMManagerTreeObj> queryExpandNodesForLP(IBMManagerTreeObj obj) {
		List<IBMManagerTreeObj> list = new ArrayList<IBMManagerTreeObj>();
		try {
			list = getSqlMap().queryForList(
					"IBMManagerTree.queryExpandNodesForLP", obj);
		} catch (Exception e) {
			LogHelper.error("IBMManagerTree.queryExpandNodesForLP:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryForTreeByName
	 * @Description: 通过名字查询左侧树节点
	 * @param
	 * @return List<IBMManagerTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午6:01:20
	 */
	@Override
	public List<IBMManagerTreeObj> queryForTreeByName(IBMManagerTreeObj obj) {
		List<IBMManagerTreeObj> list = new ArrayList<IBMManagerTreeObj>();
		try {
			list = getSqlMap().queryForList(
					"IBMManagerTree.queryForTreeByName", obj);
		} catch (SQLException e) {
			LogHelper.error("IBMManagerTree.queryForTreeByName:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}
}
