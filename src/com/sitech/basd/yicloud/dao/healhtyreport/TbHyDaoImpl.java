package com.sitech.basd.yicloud.dao.healhtyreport;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.reportvirtual.TbHyObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualInfoObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbHyDaoImpl extends BaseDao implements TbHyDao {

	/**
	 * @Title:查询当天的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHyForToday(TbHyObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHy.queryTbHyForToday", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHy.queryTbHyForToday:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询本周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHyForWeek(TbHyObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHy.queryTbHyForWeek", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHy.queryTbHyForWeek:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:跨表查询本周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryUnionTbHyForWeek(TbHyObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHy.queryUnionTbHyForWeek", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHy.queryUnionTbHyForWeek:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询上周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHyForLastWeek(TbHyObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHy.queryTbHyForLastWeek", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHy.queryTbHyForLastWeek:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:跨表查询上周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryUnionTbHyForLastWeek(TbHyObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHy.queryUnionTbHyForLastWeek",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHy.queryUnionTbHyForLastWeek:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询当月的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHyForMonth(TbHyObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHy.queryTbHyForMonth", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHy.queryTbHyForMonth:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询上月的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHyForLastMonth(TbHyObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHy.queryTbHyForLastMonth", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHy.queryTbHyForLastMonth:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title: 查询已经监控的主机
	 * @Copyright: Copyright (c) 20120405
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int queryMonitorVirtualCount(TbCloud2VirtualInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().queryForObject(
					"TbHy.queryMonitorVirtualCount", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbHy.queryMonitorVirtualCount:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title: 查询所有能监控的主机
	 * @Copyright: Copyright (c) 20120405
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int queryAllVirtualCount(TbCloud2VirtualInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().queryForObject("TbHy.queryAllVirtualCount",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbHy.queryAllVirtualCount:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
}
