package com.sitech.basd.yicloud.dao.datastore;

import java.util.List;

import com.sitech.basd.yicloud.domain.datastore.StoreDeviceObj;

public interface StoreDeviceDao {
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
	public List queryForListByObj(StoreDeviceObj obj);

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
	public int insertStoreDevice(StoreDeviceObj obj);

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
	public int updateByObj(StoreDeviceObj obj);

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
	public int deleteByObj(StoreDeviceObj obj);

	public List<StoreDeviceObj> queryForListByUseIn(
			StoreDeviceObj storeDeviceObj);
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
	public StoreDeviceObj queryByObj(StoreDeviceObj obj);

	/**
	 * @Title: queryVmwareCapacity
	 * @Description: 查询vmware存储块
	 * @param
	 * @return StoreDeviceObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-19 上午8:59:41
	 */
	public StoreDeviceObj queryVmwareCapacity(StoreDeviceObj storeDeviceObj);

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
	public List<StoreDeviceObj> queryStoreType(StoreDeviceObj storeDeviceObj);

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
	public int countByType(StoreDeviceObj storeDeviceObj);

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
	public StoreDeviceObj queryXenCapacity(StoreDeviceObj storeDeviceObj);

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
	public double queryValidSpace(StoreDeviceObj storeDeviceObj);

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
	public List<StoreDeviceObj> queryForList(StoreDeviceObj storeDeviceObj);

}
