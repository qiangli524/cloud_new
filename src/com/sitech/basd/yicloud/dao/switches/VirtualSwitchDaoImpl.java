package com.sitech.basd.yicloud.dao.switches;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.switches.VirtualSwitch;

public class VirtualSwitchDaoImpl extends BaseDao implements VirtualSwitchDao {
	/**
	 * @Title:插入虚拟交换机信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(VirtualSwitch obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("virtualswitch.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("virtualswitch.insertByObj:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询所有虚拟交换机信息
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public List listVirtualSwitch(VirtualSwitch obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"virtualswitch.queryVirtualSwitchCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("virtualswitch.queryVirtualSwitch", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("virtualswitch.queryVirtualSwitch:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	public List queryByObj(VirtualSwitch obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("virtualswitch.queryByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("virtualswitch.queryByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
}
