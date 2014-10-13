package com.sitech.basd.yicloud.service.device;

import java.util.List;

import com.sitech.basd.yicloud.dao.device.DeviceDao;
import com.sitech.basd.yicloud.domain.device.DeviceObj;

public class DeviceServiceImpl implements DeviceService {

	private DeviceDao deviceDao;

	/**
	 * @Title:查询设备主机数据结果集
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public List queryForListByObj(DeviceObj obj) {
		// TODO Auto-generated method stub
		return deviceDao.queryForListByObj(obj);
	}

	/**
	 * @Title:添加设备主机
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int insertByObj(DeviceObj obj) {
		return deviceDao.insertByObj(obj);
	}

	/**
	 * @Title:查询一条信息
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public DeviceObj queryByObj(DeviceObj obj) {
		return deviceDao.queryByObj(obj);
	}

	/**
	 * @Title:修改设备主机信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int updateByObj(DeviceObj obj) {
		return deviceDao.updateByObj(obj);
	}

	/**
	 * @Title:根据ID删除设备主机信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int deleteByObj(DeviceObj obj) {
		return deviceDao.deleteByObj(obj);
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
		return deviceDao.getIdSequence();
	}

	public void setDeviceDao(DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}

}
