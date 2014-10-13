package com.sitech.basd.yicloud.service.storage;

import java.util.List;

import com.sitech.basd.yicloud.dao.storage.TbYicloudAppStorageDao;
import com.sitech.basd.yicloud.domain.storage.TbYicloudAppStorageObj;

public class TbYicloudAppStorageServiceImpl implements
		TbYicloudAppStorageService {
	/**
	 * @Title:查询已有列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryForListByObj(TbYicloudAppStorageObj obj) {
		return tbYicloudAppStorageDao.queryForListByObj(obj);
	}

	/**
	 * @Title:查询一条信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public TbYicloudAppStorageObj queryByObj(TbYicloudAppStorageObj obj) {
		return tbYicloudAppStorageDao.queryByObj(obj);
	}

	/**
	 * @Title:插入新添加的信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int insertByObj(TbYicloudAppStorageObj obj) {
		return tbYicloudAppStorageDao.insertByObj(obj);
	}

	/**
	 * @Title:更新信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int updateByObj(TbYicloudAppStorageObj obj) {
		return tbYicloudAppStorageDao.updateByObj(obj);
	}

	/**
	 * @Title:删除信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int deleteByObj(TbYicloudAppStorageObj obj) {
		return tbYicloudAppStorageDao.deleteByObj(obj);
	}

	TbYicloudAppStorageDao tbYicloudAppStorageDao;

	public void setTbYicloudAppStorageDao(
			TbYicloudAppStorageDao tbYicloudAppStorageDao) {
		this.tbYicloudAppStorageDao = tbYicloudAppStorageDao;
	}

}
