package com.sitech.basd.sxcloud.cloud.service.appmessage;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.dao.appmessage.AppMessageDao;
import com.sitech.basd.sxcloud.cloud.domain.appmessage.AppMessageObj;
import com.sitech.basd.sxcloud.cloud.domain.appmessage.BizsysObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class AppMessageServiceImpl extends BaseService implements
		AppMessageService {

	@SuppressWarnings( { "unused", "unchecked" })
	private static List AppMessageList = null;

	/**
	 * @Title:删除应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int deleteByObj(AppMessageObj obj) {
		return appMessageDao.deleteByObj(obj);
	}

	/**
	 * @Title:插入应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int insertByObj(AppMessageObj obj) {
		return appMessageDao.insertByObj(obj);
	}

	/**
	 * @Title:查询出具体应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public AppMessageObj queryByObj(AppMessageObj obj) {
		return appMessageDao.queryByObj(obj);
	}

	/**
	 * @Title:根据部分应用信息查询匹配的所有应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(AppMessageObj obj) {

		return appMessageDao.queryForListByObj(obj);
	}

	/**
	 * @Title:更新应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int updateByObj(AppMessageObj obj) {
		return appMessageDao.updateByObj(obj);
	}

	/**
	 * @Title:根据部分应用信息查询匹配的所有应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByBizsysObj(BizsysObj obj) {
		return appMessageDao.queryForListByBizsysObj(obj);
	}

	/**
	 * @Title:根据模块ID模糊查询应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryAppMessageObjByUnion(AppMessageObj obj) {
		return appMessageDao.queryAppMessageObjByUnion(obj);
	}

	private AppMessageDao appMessageDao;

	public void setAppMessageDao(AppMessageDao appMessageDao) {
		this.appMessageDao = appMessageDao;
	}

}
