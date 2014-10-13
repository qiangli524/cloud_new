package com.sitech.basd.sxcloud.cloud.service.globalconfig;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;

public interface TbGlobalConfigService {

	/**
	 * @Title:查询所有的全局配置管理信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryForListByObj(TbGlobalConfigObj obj);

	/**
	 * @Title:查询并返回一个项目对象
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public TbGlobalConfigObj queryByObj(TbGlobalConfigObj obj);

	/**
	 * @Title:更新全局配置管理信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updateByObj(TbGlobalConfigObj obj);

	/**
	 * @Title:删除全局配置管理信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int deleteByObj(TbGlobalConfigObj obj);

	/**
	 * @Title:插入全局配置管理信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(TbGlobalConfigObj obj);

	/**
	 * 
	 * @Title: 判断关键字是否重复
	 * @Copyright:Copyright (c) Aug 16, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public int checkKeyExists(TbGlobalConfigObj obj);
	
	/**
	 * @Title: checkUserContains
	 * @Description: 判断user_auth配置中是否含有指定用户，包含返回true
	 * @param
	 * @return boolean
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-22 下午3:16:17
	 */
	public boolean checkUserContains(String username);
}
