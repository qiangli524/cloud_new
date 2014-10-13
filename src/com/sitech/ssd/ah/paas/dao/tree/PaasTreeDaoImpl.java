package com.sitech.ssd.ah.paas.dao.tree;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.ssd.ah.paas.domain.entity.PaasEntityObj;
import com.sitech.ssd.ah.paas.domain.tree.PaasTreeObj;
import com.sitech.utils.randomid.RandomUUID;

/**
 * 
 * <p>
 * Title: PaasTreeDaoImpl
 * </p>
 * <p>
 * Description: paas资源树相关操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-3-28 上午9:14:46
 * 
 */
@Repository("paasTreeDao")
public class PaasTreeDaoImpl extends BaseDao implements PaasTreeDao {

	/**
	 * 
	 * @Title: queryForPaasTree
	 * @Description: 查询资源树
	 * @param
	 * @return List<PaasTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-17 下午5:34:20
	 */
	@Override
	public List<PaasTreeObj> queryForPaasTree(PaasTreeObj obj) {
		List<PaasTreeObj> list = new ArrayList<PaasTreeObj>();
		try {
			list = getSqlMap().queryForList("PaasTree.queryForPaasTree", obj);
		} catch (SQLException e) {
			logger.error("PaasTree.queryForPaasTree:" + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 添加
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-25 下午4:30:24
	 */
	@Override
	public int insertByObj(PaasTreeObj obj) {
		int ret = 0;
		String id = RandomUUID.getUuid();
		try {
			obj.setId(id);
			getSqlMap().insert("PaasTree.insertByObj", obj);
		} catch (SQLException e) {
			ret = -1;
			logger.error("PaasTree.insertByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 修改
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-25 下午4:30:37
	 */
	@Override
	public int updateByObj(PaasTreeObj obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().update("PaasTree.updateByObj", obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (SQLException e) {
			ret = -1;
			logger.error("PaasTree.updateByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-25 下午4:30:47
	 */
	@Override
	public int deleteByObj(PaasTreeObj obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().delete("PaasTree.deleteByObj", obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (SQLException e) {
			ret = -1;
			logger.error("PaasTree.deleteByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryNodesForResourcePool
	 * @Description: 查询资源池集合
	 * @param
	 * @return List<PaasTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-28 上午9:18:31
	 */
	@Override
	public List<PaasTreeObj> queryNodesForResourcePool(PaasTreeObj obj) {
		List<PaasTreeObj> list = new ArrayList<PaasTreeObj>();
		try {
			list = getSqlMap().queryForList(
					"PaasTree.queryNodesForResourcePool", obj);
		} catch (SQLException e) {
			logger.error("PaasTree.queryNodesForResourcePool:" + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryNodesForExamples
	 * @Description: 查询实例集合
	 * @param
	 * @return List<PaasTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-28 上午9:18:35
	 */
	@Override
	public List<PaasTreeObj> queryNodesForExamples(PaasTreeObj obj) {
		List<PaasTreeObj> list = new ArrayList<PaasTreeObj>();
		try {
			list = getSqlMap().queryForList("PaasTree.queryNodesForExamples",
					obj);
		} catch (SQLException e) {
			logger.error("PaasTree.queryNodesForExamples:" + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}
	
	/**
	 * 
	 * @Title: insertByEntityObj
	 * @Description: 添加实例时插入实体表
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-23 上午9:15:56
	 */
	@Override
	public int insertByEntityObj(PaasEntityObj obj) {
		int ret = 0;
		String id = RandomUUID.getUuid();
		try {
			obj.setId(id);
			getSqlMap().insert("PaasTree.insertByEntityObj", obj);
		} catch (SQLException e) {
			ret = -1;
			logger.error("PaasTree.insertByEntityObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}
}
