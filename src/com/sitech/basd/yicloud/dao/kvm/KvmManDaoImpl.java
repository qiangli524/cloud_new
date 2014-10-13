package com.sitech.basd.yicloud.dao.kvm;

import java.util.ArrayList;
import java.util.List;

import com.ibatis.dao.client.DaoException;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.kvm.TbYicloudDeviceHealthObj;
import com.sitech.basd.yicloud.domain.kvm.TbYicloudOsTypeObj;
import com.sitech.basd.yicloud.domain.kvm.VMManagerObj;

/**
 * 
 * <p>
 * Title: KvmManDaoImpl
 * </p>
 * <p>
 * Description: Kvm管理Dao实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Jul 25, 2012 9:20:54 AM
 * 
 */
public class KvmManDaoImpl extends BaseDao implements KvmManDao {
	/**
	 * @Title:查询设备主机数据结果集
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author huojla
	 * @version 1.0
	 */
	public List queryForListByObj(VMManagerObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("kvmMan.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("kvmMan.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;

	}

	/**
	 * @Title:添加设备主机
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author huojla
	 * @version 1.0
	 */
	public int insertByObj(VMManagerObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("kvmMan.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("kvmMan.insertByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询一条信息
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author huojla
	 * @version 1.0
	 */
	public VMManagerObj queryByObj(VMManagerObj obj) {
		List lst = null;
		VMManagerObj tObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tObj = (VMManagerObj) lst.get(0);
		}
		return tObj;
	}

	/**
	 * @Title:修改设备主机信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author huojla
	 * @version 1.0
	 */
	public int updateByObj(VMManagerObj obj) {

		int ret = 0;
		try {
			Object o = getSqlMap().update("kvmMan.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("kvmMan.updateByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;

	}

	/**
	 * @Title:根据ID删除设备主机信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author huojla
	 * @version 1.0
	 */
	public int deleteByObj(VMManagerObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("kvmMan.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("kvmMan.deleteByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:根据英文名称删除设备主机信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author huojla
	 * @version 1.0
	 */
	public int deleteByName_En(VMManagerObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("kvmMan.deleteByName_En", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("kvmMan.deleteByName_En:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryHealthStateList
	 * @Description: 查询主机或虚拟机健康状态信息
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 21, 2012 10:34:05 AM
	 */
	public List queryHealthStateList(TbYicloudDeviceHealthObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("kvmMan.queryHealthStateList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("kvmMan.queryHealthStateList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryDefaultConfig
	 * @Description: 查询各个操作系统的默认配置值
	 * @param
	 * @return TbYicloudOsTypeObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 10, 2012 1:14:31 PM
	 */
	public TbYicloudOsTypeObj queryDefaultConfig(TbYicloudOsTypeObj obj) {
		TbYicloudOsTypeObj tObj = new TbYicloudOsTypeObj();
		try {
			obj = (TbYicloudOsTypeObj) getSqlMap().queryForObject(
					"kvmMan.queryDefaultConfig", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("kvmMan.queryDefaultConfig:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return obj;
	}

	/**
	 * 
	 * @Title: queryOSList
	 * @Description: 获取操作系统列表
	 * @param
	 * @return List<TbYicloudOsTypeObj>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Jul 25, 2012 2:40:34 PM
	 */
	public List<TbYicloudOsTypeObj> queryOSList(TbYicloudOsTypeObj obj) {
		List<TbYicloudOsTypeObj> list = new ArrayList<TbYicloudOsTypeObj>();
		try {
			list = (List<TbYicloudOsTypeObj>) getSqlMap().queryForList(
					"kvmMan.queryOSList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("kvmMan.queryOSList:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return list;
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
		try {

			return (String) getSqlMap().queryForObject("kvmMan.getIdSequence");
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding getTaskidSequence. Cause: "
					+ sqlexception);
		}
	}

	/**
	 * 
	 * @Title: insertByOsType
	 * @Description: 增加一条信息
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 10, 2012 1:36:46 PM
	 */
	public int insertByOsType(TbYicloudOsTypeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("kvmMan.insertByOSObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("kvmMan.insertByOSObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

}
