package com.sitech.basd.yicloud.dao.interfaces;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.device.DeviceObj;
import com.sitech.basd.yicloud.domain.dictionary.DictionaryObj;
import com.sitech.basd.yicloud.domain.interfaces.InterfaceObj;

/**
 * @author lzh
 * 
 */
public class InterfaceDaoImpl extends BaseDao implements InterfaceDao {

	/**
	 * @Title:查询外围接口数据结果集
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public List queryForListByObj(InterfaceObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"InterfaceInfo.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("InterfaceInfo.queryForListByObj",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("InterfaceInfo.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;

	}

	/**
	 * @Title:根据ID删除外围接口信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int deleteByObj(InterfaceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("InterfaceInfo.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("InterfaceInfo.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:添加外围接口
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int insertByObj(InterfaceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("InterfaceInfo.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("InterfaceInfo.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询一条记录
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 10:33:33 AM
	 * @version 1.0
	 */
	public InterfaceObj queryByObj(InterfaceObj obj) {
		List lst = null;
		InterfaceObj tObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tObj = (InterfaceObj) lst.get(0);
		}
		return tObj;
	}

	/**
	 * @Title:查询下拉设备主机ID的值
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public List queryForListByDeviceObj(DeviceObj obj) {

		List lst = null;
		try {
			lst = getSqlMap().queryForList(
					"InterfaceInfo.queryForListByDeviceObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("InterfaceInfo.queryForListByDeviceObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;

	}

	/**
	 * @Title:查询下拉框端口状态的值
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public List queryForListByStatusObj(DictionaryObj obj) {

		List lst = null;
		try {
			lst = getSqlMap().queryForList(
					"InterfaceInfo.queryForListByStatusObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("InterfaceInfo.queryForListByStatusObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;

	}

	/**
	 * @Title:查询下拉框外围接口类型的值
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public List queryForListByTypeObj(DictionaryObj obj) {

		List lst = null;
		try {
			lst = getSqlMap().queryForList(
					"InterfaceInfo.queryForListByTypeObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("InterfaceInfo.queryForListByTypeObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;

	}

	/**
	 * @Title:修改外围接口信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 10:50:28 AM
	 * @version 1.0
	 */
	public int updateByObj(InterfaceObj obj) {
		int result = 0;
		try {
			Object object = getSqlMap().update("InterfaceInfo.updateByObj", obj);
			if (object != null) {
				result = Integer.parseInt(object.toString());
			}
		} catch (Exception e) {
			result = -1;
			LogHelper.error("InterfaceInfo.updateByObj:"
					+ e.getMessage() + getClass().getName());

		}
		return result;
	}

}
