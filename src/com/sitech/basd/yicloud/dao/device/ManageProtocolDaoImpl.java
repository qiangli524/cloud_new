package com.sitech.basd.yicloud.dao.device;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.device.ManageProtocolObj;
import com.sitech.utils.randomid.RandomUUID;

@Repository("manageProtocolDao")
public class ManageProtocolDaoImpl extends BaseDao implements ManageProtocolDao {
	public List queryForList(ManageProtocolObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("ManageProtocol.queryByObjForCount",
								obj)).intValue());
			}
			lst = getSqlMap().queryForList("ManageProtocol.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("ManageProtocol.queryForListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	public int insertByObj(ManageProtocolObj obj) {
		int ret = 0;
		try {
			obj.setId(RandomUUID.getUuid());
			Object o = getSqlMap().insert("ManageProtocol.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("ManageProtocol.insertByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	public ManageProtocolObj queryByObj(ManageProtocolObj obj) {
		List lst = null;
		ManageProtocolObj tObj = null;
		lst = queryForList(obj);
		if (lst != null && lst.size() > 0) {
			tObj = (ManageProtocolObj) lst.get(0);
		}
		return tObj;
	}

	public int updateByObj(ManageProtocolObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("ManageProtocol.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("ManageProtocol.updateByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	public int deleteByObj(ManageProtocolObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("ManageProtocol.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("ManageProtocol.deleteByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
}
