package com.sitech.basd.sxcloud.cloud.dao.reportapp;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.reportapp.TbAppCollObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbAppCollDaoImpl extends BaseDao implements TbAppCollDao {

	/**
	 * @Title:查询当天的应用监控采集信息
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppCollForToday(TbAppCollObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbAppColl.queryAppCollForToday",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbAppColl.queryAppCollForToday:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询本周的应用监控采集信息
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppCollForWeek(TbAppCollObj obj) {
		List lst = null;
		try {
			lst = getSqlMap()
					.queryForList("TbAppColl.queryAppCollForWeek", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbAppColl.queryAppCollForWeek:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:跨表查询本周的应用监控采集信息
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryUnionAppCollForWeek(TbAppCollObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList(
					"TbAppColl.queryUnionAppCollForWeek", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbAppColl.queryUnionAppCollForWeek:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询本周的应用监控采集信息
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppCollLastWeek(TbAppCollObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbAppColl.queryAppCollLastWeek",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbAppColl.queryAppCollLastWeek:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:跨表查询上周的应用监控采集信息
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryUnionAppCollLastWeek(TbAppCollObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList(
					"TbAppColl.queryUnionAppCollLastWeek", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbAppColl.queryUnionAppCollLastWeek:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询当月的应用监控采集信息
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppCollForMonth(TbAppCollObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbAppColl.queryAppCollForMonth",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbAppColl.queryAppCollForMonth:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询上月的应用监控采集信息
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppCollLastMonth(TbAppCollObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbAppColl.queryAppCollLastMonth",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbAppColl.queryAppCollLastMonth:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

}
