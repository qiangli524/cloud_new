package com.sitech.basd.envmanager.dao.performance;

import java.util.List;

import com.sitech.basd.envmanager.domain.performance.AddressObj;
import com.sitech.basd.envmanager.domain.performance.PerformanceObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class PerformanceDaoImpl extends BaseDao implements PerformanceDao{

	/**
	 * @Title:查询所有资源使用情况
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public List queryPerformanceObj(PerformanceObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Performance.queryPerformanceObjCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("Performance.queryPerformanceObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Performance.queryPerformanceObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
	/**
	 * @Title:查询所有地址变动信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public List queryAddressInfo(AddressObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Performance.queryAddressInfoCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("Performance.queryAddressInfo", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Performance.queryAddressInfo:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
	/**
	 * @Title:增加地址变动信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public int insertAddressInfo(AddressObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Performance.insertAddressInfo", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Performance.insertAddressInfo:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	/**
	 * @Title:修改地址变动信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public int updateAddressInfo(AddressObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("Performance.updateAddressInfo", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Performance.updateAddressInfo:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	@Override
	public int deletePerformanceObj(PerformanceObj obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertPerformanceObj(PerformanceObj obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PerformanceObj queryPerformanceOne(PerformanceObj obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updatePerformanceObj(PerformanceObj obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
