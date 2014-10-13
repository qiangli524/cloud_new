package com.sitech.basd.yicloud.service.ip;

import java.util.List;

import com.sitech.basd.yicloud.dao.ip.TbYicloudAppIpDao;
import com.sitech.basd.yicloud.domain.ip.TbYicloudAppIpObj;

public class TbYicloudAppIpServiceImpl implements TbYicloudAppIpService {
	/**
	 * @Title:查询已有IP列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryForListByObj(TbYicloudAppIpObj obj) {
		return tbYicloudAppIpDao.queryForListByObj(obj);
	}

	/**
	 * @Title:查询一条IP信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public TbYicloudAppIpObj queryByObj(TbYicloudAppIpObj obj) {
		return tbYicloudAppIpDao.queryByObj(obj);
	}

	/**
	 * @Title:查询业务应用Id列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryForAppIdList() {
		return tbYicloudAppIpDao.queryForAppIdList();
	}

	/**
	 * @Title:查询绑定设备主机Id列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryForBindDeviceList() {
		return tbYicloudAppIpDao.queryForBindDeviceList();
	}

	/**
	 * @Title:插入新添加的IP信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int insertByObj(TbYicloudAppIpObj obj) {
		return tbYicloudAppIpDao.insertByObj(obj);
	}

	/**
	 * @Title:更新IP信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int updateByObj(TbYicloudAppIpObj obj) {
		return tbYicloudAppIpDao.updateByObj(obj);
	}

	/**
	 * @Title:删除P信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int deleteByObj(TbYicloudAppIpObj obj) {
		return tbYicloudAppIpDao.deleteByObj(obj);
	}

	TbYicloudAppIpDao tbYicloudAppIpDao;

	public void setTbYicloudAppIpDao(TbYicloudAppIpDao tbYicloudAppIpDao) {
		this.tbYicloudAppIpDao = tbYicloudAppIpDao;
	}

}
