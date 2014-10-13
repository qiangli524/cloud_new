package com.sitech.basd.yicloud.dao.device;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.device.SnmpParamObj;
import com.sitech.utils.randomid.RandomUUID;

@Repository("snmpParamDao")
public class SnmpParamDaoImpl extends BaseDao implements SnmpParamDao {

	public List queryForList(SnmpParamObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("SnmpParam.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("SnmpParam.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("SnmpParam.queryForListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	public int insertByObj(SnmpParamObj obj) {
		int ret = 0;
		try {
			obj.setId(RandomUUID.getUuid());
			Object o = getSqlMap().insert("SnmpParam.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("SnmpParam.insertByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	public SnmpParamObj queryByObj(SnmpParamObj obj) {
		List lst = null;
		SnmpParamObj tObj = null;
		lst = queryForList(obj);
		if (lst != null && lst.size() > 0) {
			tObj = (SnmpParamObj) lst.get(0);
		}
		return tObj;
	}

	public int updateByObj(SnmpParamObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("SnmpParam.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("SnmpParam.updateByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	public int deleteByObj(SnmpParamObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("SnmpParam.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("SnmpParam.deleteByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
}
