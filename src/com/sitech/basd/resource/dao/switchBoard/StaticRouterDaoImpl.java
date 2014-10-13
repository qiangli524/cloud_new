package com.sitech.basd.resource.dao.switchBoard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.resource.domain.switchBoard.StaticRouterObj;
import com.sitech.basd.resource.domain.switchBoard.SwitchObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("staticRouterDao")
public class StaticRouterDaoImpl extends BaseDao implements StaticRouterDao {
	@Override
	public List<StaticRouterObj> queryForList(StaticRouterObj obj) {
		List<StaticRouterObj> list = new ArrayList<StaticRouterObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("staticRouter.queryForListCount", obj))
								.intValue()); // 分页查询的基本信息 }
			}
			list = getSqlMap().queryForList("staticRouter.queryForList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("staticRouter.queryForList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}
	@Override
	public int insertByObj(StaticRouterObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("staticRouter.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("staticRouter.insertByObj:" + e.getMessage());
		}
		return ret;
	}
	
	@Override
	public int updateByObj(StaticRouterObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("staticRouter.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("staticRouter.updateByObj:" + e.getMessage());
		}
		return ret;
	}
	@Override
	public int deleteByObj(StaticRouterObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("staticRouter.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("staticRouter.deleteByObj:" + e.getMessage());
		}
		return ret;
	}
}
