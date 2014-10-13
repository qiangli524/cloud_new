package com.sitech.ssd.sc.software.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.sc.software.domain.SoftwareInstallObj;
import com.sitech.utils.randomid.RandomUUID;

@Repository("softwareInstallDao")
public class SoftwareInstallDaoImpl extends BaseDao implements SoftwareInstallDao {
	public List queryForList(SoftwareInstallObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("SoftwareInstall.queryByObjForCount",
								obj)).intValue());
			}
			lst = getSqlMap().queryForList("SoftwareInstall.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("SoftwareInstall.queryForListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	public int insertByObj(SoftwareInstallObj obj) {
		int ret = 0;
		try {
			obj.setId(RandomUUID.getUuid());
			Object o = getSqlMap().insert("SoftwareInstall.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("SoftwareInstall.insertByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	public SoftwareInstallObj queryByObj(SoftwareInstallObj obj) {
		List lst = null;
		SoftwareInstallObj tObj = null;
		lst = queryForList(obj);
		if (lst != null && lst.size() > 0) {
			tObj = (SoftwareInstallObj) lst.get(0);
		}
		return tObj;
	}

	public int updateByObj(SoftwareInstallObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("SoftwareInstall.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("SoftwareInstall.updateByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	public int deleteByObj(SoftwareInstallObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("SoftwareInstall.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("SoftwareInstall.deleteByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
}
