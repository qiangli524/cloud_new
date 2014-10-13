package com.sitech.ssd.ah.zookeeper.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.ah.zookeeper.domain.ZookeeperTreeObj;

@SuppressWarnings("unchecked")
@Repository("zookeeperTreeToDBDao")
public class ZookeeperTreeDaoImpl extends BaseDao implements ZookeeperTreeDao {

	@Override
	public ZookeeperTreeObj queryTreeByObj(ZookeeperTreeObj obj) {
		ZookeeperTreeObj ob = new ZookeeperTreeObj();
		try {
			ob = (ZookeeperTreeObj) getSqlMap().queryForObject("ZookeeperTree.queryTreeNodeByObj",
					obj);
		} catch (SQLException e) {
			logger.error("ZookeeperTree.queryTreeNodeByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ob;
	}

	@Override
	public int updateZookeeperTreeObj(ZookeeperTreeObj obj) {
		int ret = 0;
		try {
			getSqlMap().update("ZookeeperTree.updateByObj", obj);
		} catch (Exception e) {
			ret = -1;
			LogHelper
					.error("ZookeeperTree.updateByObj: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	@Override
	public int deleteZookeeperTreeObj(ZookeeperTreeObj obj) {
		int ret = 0;
		try {
			getSqlMap().delete("ZookeeperTree.deleteByObj", obj);
		} catch (SQLException e) {
			ret = -1;
			logger.error("ZookeeperTree.deleteByObj: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

}
