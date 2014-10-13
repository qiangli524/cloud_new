package com.sitech.basd.sxcloud.cloud.service.globalconfig;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sitech.basd.sxcloud.cloud.dao.globalconfig.TbGlobalConfigDao;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

@Service("tbGlobalConfigService")
public class TbGlobalConfigServiceImpl extends BaseService implements TbGlobalConfigService {

	/**
	 * @Title:删除全局配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int deleteByObj(TbGlobalConfigObj obj) {
		// TODO Auto-generated method stub
		return tbGlobalConfigDao.deleteByObj(obj);
	}

	/**
	 * @Title:插入全局配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(TbGlobalConfigObj obj) {
		// TODO Auto-generated method stub
		return tbGlobalConfigDao.insertByObj(obj);
	}

	/**
	 * @Title:查询匹配的所有全局配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryForListByObj(TbGlobalConfigObj obj) {

		return tbGlobalConfigDao.queryForListByObj(obj);
	}

	/**
	 * @Title:查询全局配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public TbGlobalConfigObj queryByObj(TbGlobalConfigObj obj) {
		return tbGlobalConfigDao.queryByObj(obj);
	}

	/**
	 * @Title:更新全局配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updateByObj(TbGlobalConfigObj obj) {
		// TODO Auto-generated method stub
		return tbGlobalConfigDao.updateByObj(obj);
	}

	private TbGlobalConfigDao tbGlobalConfigDao;

	public void setTbGlobalConfigDao(TbGlobalConfigDao tbGlobalConfigDao) {
		this.tbGlobalConfigDao = tbGlobalConfigDao;
	}

	/**
	 * 
	 * @Title: 判断关键字是否重复
	 * @Copyright:Copyright (c) Aug 16, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public int checkKeyExists(TbGlobalConfigObj obj) {
		// TODO Auto-generated method stub
		return tbGlobalConfigDao.checkKeyExists(obj);
	}
	
	public boolean checkUserContains(String username){
		return tbGlobalConfigDao.checkUserContains(username);
	}
}
