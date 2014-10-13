package com.sitech.basd.sxcloud.cloud.dao.exportconfig;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.exportconfig.ExportConfigObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.util.UUIDGenerator;

@SuppressWarnings("all")
public class ExportConfigDaoImpl extends BaseDao implements ExportConfigDao {
	/**
	 * @Title:查询出导出配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryForListByObj(ExportConfigObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("ExportConfig.queryByObjForCount",
								obj)).intValue());
			}
			lst = getSqlMap().queryForList("ExportConfig.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("ExportConfig.queryForListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询导出配置信息返回一个对象
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public ExportConfigObj queryByObj(ExportConfigObj obj) {
		List lst = null;
		ExportConfigObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (ExportConfigObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:添加导出配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(ExportConfigObj obj) {
		UUIDGenerator uuid = new UUIDGenerator();
		obj.setID(uuid.getUUID());
		int ret = 0;
		try {
			Object o = getSqlMap().insert("ExportConfig.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("ExportConfig.insertByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:更新导出配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updateByObj(ExportConfigObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("ExportConfig.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("ExportConfig.updateByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除导出配置信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int deleteByObj(ExportConfigObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("ExportConfig.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("ExportConfig.deleteByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	@Override
	public int queryForObjByObj(ExportConfigObj obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().queryForObject("ExportConfig.queryForObjByObj",obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("ExportConfig.queryForObjByObj:"+e.getMessage()+e.getClass().getName());
		}
		return ret;
	}

}
