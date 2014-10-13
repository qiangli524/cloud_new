package com.sitech.basd.yicloud.service.interfaces;

import java.util.List;

import com.sitech.basd.yicloud.dao.interfaces.InterfaceDao;
import com.sitech.basd.yicloud.domain.device.DeviceObj;
import com.sitech.basd.yicloud.domain.dictionary.DictionaryObj;
import com.sitech.basd.yicloud.domain.interfaces.InterfaceObj;

public class InterfaceServiceImpl implements InterfaceService {

	private InterfaceDao interfaceDao;

	/**
	 * @Title:根据ID删除外围接口信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int deleteByObj(InterfaceObj obj) {
		return interfaceDao.deleteByObj(obj);
	}

	/**
	 * @Title:添加外围接口
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int insertByObj(InterfaceObj obj) {
		return interfaceDao.insertByObj(obj);
	}

	/**
	 * @Title:查询一条信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public InterfaceObj queryByObj(InterfaceObj obj) {
		return interfaceDao.queryByObj(obj);
	}

	/**
	 * @Title:查询下拉框设备主机ID的值
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public List queryForListByDeviceObj(DeviceObj obj) {
		return interfaceDao.queryForListByDeviceObj(obj);
	}

	/**
	 * @Title:查询外围接口数据结果集
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public List queryForListByObj(InterfaceObj obj) {
		return interfaceDao.queryForListByObj(obj);
	}

	/**
	 * @Title:查询下拉框端口状态的值
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public List queryForListByStatusObj(DictionaryObj obj) {
		return interfaceDao.queryForListByStatusObj(obj);
	}

	/**
	 * @Title:查询下拉框外围接口类型的值
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public List queryForListByTypeObj(DictionaryObj obj) {
		return interfaceDao.queryForListByTypeObj(obj);
	}

	/**
	 * @Title:修改外围接口信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int updateByObj(InterfaceObj obj) {
		return interfaceDao.updateByObj(obj);
	}

	public void setInterfaceDao(InterfaceDao interfaceDao) {
		this.interfaceDao = interfaceDao;
	}

}
