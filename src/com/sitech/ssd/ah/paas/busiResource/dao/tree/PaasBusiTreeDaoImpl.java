package com.sitech.ssd.ah.paas.busiResource.dao.tree;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.ssd.ah.paas.busiResource.domain.tree.PaasBusiTreeObj;
import com.sitech.utils.randomid.RandomUUID;
/**
 * <p>Title: PaasBusiTreeDaoImpl</p>
 * <p>Description: paas业务资源树DAO</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author qism
 * @version 1.0
 * @createtime 2014-7-24 下午5:35:23
 *
 */
@SuppressWarnings("unchecked")
@Repository("paasBusiTreeDao")
public class PaasBusiTreeDaoImpl extends BaseDao implements PaasBusiTreeDao{

	
	@Override
	public List<PaasBusiTreeObj> queryForPaasBusiTree(PaasBusiTreeObj obj) {
		List<PaasBusiTreeObj> list = new ArrayList<PaasBusiTreeObj>();
		try {
			list = getSqlMap().queryForList("PaasBusiTree.queryForPaasBusiTree", obj);
		} catch (SQLException e) {
			logger.error("PaasBusiTree.queryForPaasBusiTree:" + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	@Override
	public int insertByObj(PaasBusiTreeObj obj) {
		int ret = 0;
		String uuid = RandomUUID.getUuid();
		try {
			obj.setUuid(uuid);
			getSqlMap().insert("PaasBusiTree.insertByObj", obj);
		} catch (SQLException e) {
			ret = -1;
			logger.error("PaasBusiTree.insertByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}

	@Override
	public int updateByObj(PaasBusiTreeObj obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().update("PaasBusiTree.updateByObj", obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (SQLException e) {
			ret = -1;
			logger.error("PaasBusiTree.updateByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}

	@Override
	public int deleteByObj(PaasBusiTreeObj obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().delete("PaasBusiTree.deleteByObj", obj);
		} catch (SQLException e) {
			ret = -1;
			logger.error("PaasBusiTree.deleteByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}
}
