package com.sitech.basd.yicloud.service.middleware;

import java.util.List;

import com.sitech.basd.yicloud.dao.middleware.MiddlewareDao;
import com.sitech.basd.yicloud.domain.middleware.MiddlewareObj;

public class MiddlewareServiceImpl implements MiddlewareService {
	/**
	 * @Title:查询已有列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryForListByObj(MiddlewareObj obj) {
		return middlewareDao.queryForListByObj(obj);
	}

	/**
	 * @Title:查询一条信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public MiddlewareObj queryByObj(MiddlewareObj obj) {
		return middlewareDao.queryByObj(obj);
	}

	/**
	 * @Title:插入新添加的信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int insertByObj(MiddlewareObj obj) {
		return middlewareDao.insertByObj(obj);
	}

	/**
	 * @Title:更新信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int updateByObj(MiddlewareObj obj) {
		return middlewareDao.updateByObj(obj);
	}

	/**
	 * @Title:删除信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int deleteByObj(MiddlewareObj obj) {
		return middlewareDao.deleteByObj(obj);
	}

	MiddlewareDao middlewareDao;

	public void setMiddlewareDao(MiddlewareDao middlewareDao) {
		this.middlewareDao = middlewareDao;
	}

}
