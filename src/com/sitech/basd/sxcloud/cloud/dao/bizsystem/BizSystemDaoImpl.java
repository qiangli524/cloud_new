package com.sitech.basd.sxcloud.cloud.dao.bizsystem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.bizsystem.BizSystemObj;
import com.sitech.basd.sxcloud.cloud.domain.bizsystem.DomainObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.util.UUIDGenerator;

@SuppressWarnings("all")
public class BizSystemDaoImpl extends BaseDao implements BizSystemDao {

	@Override
	public List queryForListByObj(BizSystemObj obj) {
		// TODO Auto-generated method stub
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"BizSystemInfo.queryByObjForCount", obj))
								.intValue());
			}
			list = getSqlMap().queryForList("BizSystemInfo.queryForListByObj",
					obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String insertByObj(BizSystemObj obj) {
		// TODO Auto-generated method stub
		UUIDGenerator uuid = new UUIDGenerator();
		String sysId = uuid.getUUID();
		obj.setSYS_ID(sysId);
		String ret = "";
		try {
			Object o = getSqlMap().insert("BizSystemInfo.insertByObj", obj);
			ret = sysId;
		} catch (Exception sqlexception) {
			ret = "-1";
			LogHelper.error("BizSystemInfo.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	@Override
	public int updateByObj(BizSystemObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().update("BizSystemInfo.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("AppMessageInfo.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	@Override
	public List queryForListByDomainObj(DomainObj obj) {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		try {
			list = getSqlMap().queryForList(
					"BizSystemInfo.queryForListByDomainObj", obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int deleteByObj(BizSystemObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().delete("BizSystemInfo.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("BizSystemInfo.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	@Override
	public BizSystemObj queryByObj(BizSystemObj obj) {
		// TODO Auto-generated method stub
		List lst = null;
		BizSystemObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (BizSystemObj) lst.get(0);
		}
		return tempObj;
	}

	/*
	 * 查询业务系统下的信息，包括基准应用，部署实例个数
	 */
	public List busiSystemInfo(BizSystemObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"BizSystemInfo.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("BizSystemInfo.busiSystemInfo", obj);
		} catch (Exception e) {
			LogHelper.error("BizSystemInfo.busiSystemInfo:" + e.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
}
