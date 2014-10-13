package com.sitech.basd.yicloud.dao.ip;

import java.util.List;

import com.sitech.basd.yicloud.domain.ip.TbYicloudAppIpObj;

public interface TbYicloudAppIpDao {
	/**
	 * @Title:查询已有IP列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryForListByObj(TbYicloudAppIpObj obj);

	/**
	 * @Title:查询一条IP信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public TbYicloudAppIpObj queryByObj(TbYicloudAppIpObj obj);

	/**
	 * @Title:查询业务应用Id列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryForAppIdList();

	/**
	 * @Title:查询绑定设备主机Id列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryForBindDeviceList();

	/**
	 * @Title:插入新添加的IP信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int insertByObj(TbYicloudAppIpObj obj);

	/**
	 * @Title:更新IP信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int updateByObj(TbYicloudAppIpObj obj);

	/**
	 * @Title:删除P信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int deleteByObj(TbYicloudAppIpObj obj);
}
