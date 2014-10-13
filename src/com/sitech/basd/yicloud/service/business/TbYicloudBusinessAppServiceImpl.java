package com.sitech.basd.yicloud.service.business;

import java.util.List;

import com.sitech.basd.yicloud.dao.business.TbYicloudBusinessAppDao;
import com.sitech.basd.yicloud.domain.business.TbYicloudBusinessAppObj;

public class TbYicloudBusinessAppServiceImpl implements
		TbYicloudBusinessAppService {
	/**
	 * @Title:查询已有列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryForListByObj(TbYicloudBusinessAppObj obj) {
		return tbYicloudBusinessAppDao.queryForListByObj(obj);
	}

	/**
	 * @Title:查询一条信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public TbYicloudBusinessAppObj queryByObj(TbYicloudBusinessAppObj obj) {
		return tbYicloudBusinessAppDao.queryByObj(obj);
	}

	/**
	 * @Title:插入新添加的信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int insertByObj(TbYicloudBusinessAppObj obj) {
		return tbYicloudBusinessAppDao.insertByObj(obj);
	}

	/**
	 * @Title:更新信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int updateByObj(TbYicloudBusinessAppObj obj) {
		return tbYicloudBusinessAppDao.updateByObj(obj);
	}

	/**
	 * @Title:删除信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int deleteByObj(TbYicloudBusinessAppObj obj) {
		return tbYicloudBusinessAppDao.deleteByObj(obj);
	}

	/**
	 * @Title:查询业务应用ID
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryAppIdList(TbYicloudBusinessAppObj obj) {
		return tbYicloudBusinessAppDao.queryAppIdList(obj);
	}

	TbYicloudBusinessAppDao tbYicloudBusinessAppDao;

	public void setTbYicloudBusinessAppDao(
			TbYicloudBusinessAppDao tbYicloudBusinessAppDao) {
		this.tbYicloudBusinessAppDao = tbYicloudBusinessAppDao;
	}

}
