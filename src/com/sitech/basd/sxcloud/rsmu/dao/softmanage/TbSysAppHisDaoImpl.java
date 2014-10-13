package com.sitech.basd.sxcloud.rsmu.dao.softmanage;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppHisObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbSysAppHisDaoImpl extends BaseDao implements TbSysAppHisDao {
	/**
	 * @Title 插入数据
	 * @author duangh
	 * @param obj
	 * @return int
	 */
	public int insertByObj(TbSysAppHisObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbSysAppHis.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("TbSysAppHis.insertByObj:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title : 查看捕获的应用镜像历史版本信息
	 * 
	 */
	public List historyVersion(TbSysAppHisObj obj) {
		List list = null;
		try {
			list = getSqlMap().queryForList("TbSysAppHis.historyVersion", obj);
		} catch (Exception e) {
			LogHelper.error("TbSysAppHis.historyVersion:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}
}
