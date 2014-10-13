package com.sitech.basd.yicloud.dao.dictionary;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.dictionary.DictionaryObj;

public class YiDataDictionaryDaoImpl extends BaseDao implements
		YiDataDictionaryDao {
	/**
	 * @Title:查询已有IP列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryForListByObj(DictionaryObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"YiDataDictionary.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList(
					"YiDataDictionary.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("YiDataDictionary.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询一条信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public DictionaryObj queryByObj(DictionaryObj obj) {
		List lst = null;
		DictionaryObj tObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tObj = (DictionaryObj) lst.get(0);
		}
		return tObj;
	}

	/**
	 * @Title:插入新添加的信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int insertByObj(DictionaryObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("YiDataDictionary.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("YiDataDictionary.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:更新信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int updateByObj(DictionaryObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("YiDataDictionary.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("YiDataDictionary.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int deleteByObj(DictionaryObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("YiDataDictionary.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("YiDataDictionary.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询所需信息列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryBusinessType(DictionaryObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList(
					"YiDataDictionary.queryBusinessType", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("YiDataDictionary.queryBusinessType:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
}
