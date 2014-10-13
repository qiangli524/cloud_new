package com.sitech.ssd.ah.paas.dao.entity;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.ah.paas.domain.entity.PaasEntityObj;

@Repository("paasEntityDao")
public class PaasEntityDaoImpl extends BaseDao implements PaasEntityDao {

	@Override
	public List<PaasEntityObj> queryForEntityList(PaasEntityObj obj) {
		List<PaasEntityObj> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"PaasEntity.queryForEntityListCount", obj))
								.intValue());
			}
			lst = getSqlMap()
					.queryForList("PaasEntity.queryForEntityList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("PaasEntity.queryForEntityList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;

	}

	@Override
	public int insertByObj(PaasEntityObj obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().insert("PaasEntity.insertByObj", obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("PaasEntity.insertByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}

	@Override
	public PaasEntityObj queryByObj(PaasEntityObj obj) {
		PaasEntityObj infoObj = new PaasEntityObj();
		try {
			infoObj = (PaasEntityObj) getSqlMap().queryForObject(
					"PaasEntity.queryByObj", obj);
		} catch (Exception e) {
			LogHelper.error("PaasEntity.queryByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return infoObj;
	}

	@Override
	public int updateByObj(PaasEntityObj obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().update("PaasEntity.updateByObj", obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("PaasEntity.updateByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}

	@Override
	public int deleteByObj(PaasEntityObj obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().delete("PaasEntity.deleteByObj", obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("PaasEntity.deleteByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}

	@Override
	public List<PaasEntityObj> queryForEntityProp(PaasEntityObj obj) {
		List<PaasEntityObj> list = null;
		try {
			list = getSqlMap().queryForList("PaasEntity.queryForEntityProp",
					obj);
		} catch (SQLException e) {
			logger.error("PaasEntity.queryForEntityProp:" + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}
	
	@Override
	public List<PaasEntityObj> queryDistinctEntityNameByType(PaasEntityObj obj) {
		List<PaasEntityObj> list = null;
		try {
			list = getSqlMap().queryForList("PaasEntity.queryDistinctEntityNameByType", obj);
		} catch (SQLException e) {
			logger.error("PaasEntity.queryDistinctEntityNameByType:" + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

}
