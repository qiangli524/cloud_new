package com.sitech.basd.envmanager.dao.condevice;

import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoException;
import com.sitech.basd.envmanager.domain.condevice.CondeviceObj;
import com.sitech.basd.envmanager.domain.condevice.VirtualObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class CondeviceDaoImpl extends BaseDao implements CondeviceDao {
	/**
	 * @Title:根据ID删除设备主机信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	@Override
	public int deleteCondeviceObjOne(CondeviceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Condevice.deleteCondeviceObjOne",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Condevice.deleteCondeviceObjOne:"
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
	@Override
	public String getIdCondevice() {
		String ret = "0";
		try {
			Object o = getSqlMap().queryForObject("Condevice.getIdCondevice");
			if (o != null) {
				ret = o.toString();
			}
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding getTaskidSequence. Cause: "
					+ sqlexception);
		}
		return ret;
	}

	/**
	 * @Title:添加设备主机
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	@Override
	public int insertCondeviceObj(CondeviceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Condevice.insertCondeviceObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Condevice.insertCondeviceObj:"
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
	@Override
	public CondeviceObj queryCondeviceObjOne(CondeviceObj obj) {
		CondeviceObj cObj = null;
		try {
			cObj = (CondeviceObj) getSqlMap().queryForObject(
					"Condevice.queryCondeviceObjOne", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Condevice.queryCondeviceObjOne:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return cObj;
	}

	/**
	 * @Title:修改设备主机信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	@Override
	public int updateCondeviceObjOne(CondeviceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("Condevice.updateCondeviceObjOne",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Condevice.updateCondeviceObjOne:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询设备主机数据结果集
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	@Override
	public List queryCondeviceObj(CondeviceObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Condevice.queryCondeviceCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("Condevice.queryCondeviceObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Condevice.queryCondeviceObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;

	}

	@Override
	public List queryManageObj(CondeviceObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Condevice.queryManageCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("Condevice.queryManageObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Condevice.queryManageObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryForExportList
	 * @Description: 用于导出数据获取列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 15, 2013 1:24:30 PM
	 */
	public List queryForExportList(Map map) {
		List lst = null;
		try {

			lst = getSqlMap().queryForList("Condevice.queryForExportList", map);
		} catch (Exception sqlexception) {
			LogHelper.error("Condevice.queryForExportList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	@Override
	public List queryVirtualObj(VirtualObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Condevice.queryVirtualObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Condevice.queryVirtualObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	@Override
	public int insertVirtualObj(VirtualObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Condevice.insertVirtualObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Condevice.insertVirtualObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除虚拟IP
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int deleteVirtualObj(VirtualObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Condevice.deleteVirtualObj",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Condevice.deleteVirtualObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

}
