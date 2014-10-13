package com.sitech.basd.yicloud.dao.device;

import java.util.List;

import com.ibatis.dao.client.DaoException;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.device.DeviceObj;

public class DeviceDaoImpl extends BaseDao implements DeviceDao {

	/**
	 * @Title:查询设备主机数据结果集
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public List queryForListByObj(DeviceObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"DeviceInfo.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("DeviceInfo.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DeviceInfo.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;

	}

	/**
	 * @Title:添加设备主机
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int insertByObj(DeviceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("DeviceInfo.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("DeviceInfo.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询一条信息
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public DeviceObj queryByObj(DeviceObj obj) {
		List lst = null;
		DeviceObj tObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tObj = (DeviceObj) lst.get(0);
		}
		return tObj;
	}

	/**
	 * @Title:修改设备主机信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int updateByObj(DeviceObj obj) {

		int ret = 0;
		try {
			Object o = getSqlMap().update("DeviceInfo.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("DeviceInfo.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;

	}

	/**
	 * @Title:根据ID删除设备主机信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int deleteByObj(DeviceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("DeviceInfo.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("DeviceInfo.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: getIdSequence
	 * @Description: 查询device表的ID
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 18, 2012 9:44:10 AM
	 */
	public String getIdSequence() {
		String ret = "0";
		try {
			 Object o = getSqlMap().queryForObject("DeviceInfo.getIdSequence");
			 if(o!=null){
				 ret = o.toString();
			 }
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding getTaskidSequence. Cause: "
					+ sqlexception);
		}
		return ret;
	}

}
