package com.sitech.basd.yicloud.dao.datastore;

import java.util.ArrayList;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.datastore.StoreDeviceObj;

public class StoreDeviceDaoImpl extends BaseDao implements StoreDeviceDao {
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询存储设备列表
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-11 下午5:55:34
	 */
	public List queryForListByObj(StoreDeviceObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"StoreDevice.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap()
					.queryForList("StoreDevice.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("StoreDevice.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: insertStoreDevice
	 * @Description: 插入存储设备
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-11 下午5:57:41
	 */
	public int insertStoreDevice(StoreDeviceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("StoreDevice.insertDatastore", obj);
		} catch (Exception sqlException) {
			LogHelper.error("StoreDevice.insertDatastore:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新存储设备
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-11 下午5:58:01
	 */
	public int updateByObj(StoreDeviceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("StoreDevice.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			LogHelper.error("StoreDevice.updateByObj:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除存储设备
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-11 下午5:58:26
	 */
	public int deleteByObj(StoreDeviceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("StoreDevice.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			LogHelper.error("StoreDevice.deleteByObj:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StoreDeviceObj> queryForListByUseIn(
			StoreDeviceObj storeDeviceObj) {
		List<StoreDeviceObj> list = new ArrayList<StoreDeviceObj>();
		try {
			if (storeDeviceObj.getPagination() != null) {
				storeDeviceObj.setFIRSTROWNUM(storeDeviceObj.getPagination()
						.getFirstRownum());
				storeDeviceObj.setPAGESIZE(storeDeviceObj.getPagination()
						.getPageSize());
				storeDeviceObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"StoreDevice.countByUseIn", storeDeviceObj))
								.intValue());
			}
			list = getSqlMap().queryForList("StoreDevice.queryForListByUseIn",
					storeDeviceObj);
		} catch (Exception e) {
			LogHelper.error("StoreDevice.queryForListByUseIn:" + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}
	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询一条记录
	 * @param
	 * @return obj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-13
	 */
	public StoreDeviceObj queryByObj(StoreDeviceObj obj) {
		StoreDeviceObj storeObj = null;
		try {
			storeObj = (StoreDeviceObj) getSqlMap().queryForObject(
					"StoreDevice.queryByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("StoreDevice.queryByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return storeObj;
	}

	/**
	 * @Title: findChartList
	 * @Description: 查询总量
	 * @param
	 * @return List<List<Object>>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-15 上午10:00:11
	 */
	@Override
	public StoreDeviceObj queryVmwareCapacity(StoreDeviceObj storeDeviceObj) {
		try {
			Object obj = getSqlMap().queryForObject(
					"StoreDevice.queryVmwareCapacity", storeDeviceObj);
			if (obj != null) {
				storeDeviceObj = (StoreDeviceObj) obj;
			}
		} catch (Exception e) {
			LogHelper.error("StoreDevice.queryVmwareCapacity: "
					+ e.getMessage() + e.getClass().getName());
		}
		return storeDeviceObj;
	}

	/**
	 * @Title: queryStoreType
	 * @Description: 查询存储的类型
	 * @param
	 * @return List<String>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-15 下午12:00:40
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<StoreDeviceObj> queryStoreType(StoreDeviceObj storeDeviceObj) {
		List<StoreDeviceObj> list = new ArrayList<StoreDeviceObj>();
		try {
			list = getSqlMap().queryForList("StoreDevice.queryStoreType",
					storeDeviceObj);
		} catch (Exception e) {
			LogHelper.error("StoreDevice.queryStoreType: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: countByType
	 * @Description: 根据类型统计存储设备的块数
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-25 下午8:13:35
	 */
	@Override
	public int countByType(StoreDeviceObj storeDeviceObj) {
		int ret = 0;
		try {
			Object obj = getSqlMap().queryForObject("StoreDevice.countByType",
					storeDeviceObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper.error("StoreDevice.countByType: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title: queryXenCapacity
	 * @Description: 查询xen存储块
	 * @param
	 * @return StoreDeviceObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-19 上午9:00:35
	 */
	@Override
	public StoreDeviceObj queryXenCapacity(StoreDeviceObj storeDeviceObj) {
		try {
			Object obj = getSqlMap().queryForObject(
					"StoreDevice.queryXenCapacity", storeDeviceObj);
			if (obj != null) {
				storeDeviceObj = (StoreDeviceObj) obj;
			}
		} catch (Exception e) {
			LogHelper.error("StoreDevice.queryXenCapacity: " + e.getMessage()
					+ e.getClass().getName());
		}
		return storeDeviceObj;
	}

	/**
	 * @Title: queryValidSpace
	 * @Description: 查询有效容量
	 * @param
	 * @return double
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-11-26 下午4:15:56
	 */
	@Override
	public double queryValidSpace(StoreDeviceObj storeDeviceObj) {
		double ret = 0.0;
		try {
			Object obj = sqlMapClient.queryForObject(
					"StoreDevice.queryValidSpace_sh", storeDeviceObj);
			if (obj != null) {
				ret = (Double) obj;
			}
		} catch (Exception e) {
			LogHelper.error("StoreDevice.queryValidSpace_sh: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}

	 /**  
	  * @Title: queryForList  
	  * @Description: 查询存储设备列表
	  * @return List<StoreDeviceObj>   
	  * @throws  
	  * @Date 2014-6-2 上午10:22:44
	  * @author lipp
	  * @param storeDeviceObj
	  * @return
	  */
	@SuppressWarnings("unchecked")
	@Override
	public List<StoreDeviceObj> queryForList(StoreDeviceObj storeDeviceObj) {
		List<StoreDeviceObj> list = new ArrayList<StoreDeviceObj>();
		try {
			list = getSqlMap().queryForList("StoreDevice.queryForList", storeDeviceObj);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("StoreDevice.queryForList: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

}
