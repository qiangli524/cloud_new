package com.sitech.basd.sxcloud.cloud.dao.globalconfig;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;

public interface TbGlobalConfigDao {

	/**
	 * @Title:根据KEY查询匹配的所有全局配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryForListByObj(TbGlobalConfigObj obj);

	/**
	 * @Title:查询匹配的全局配置信息返回一个对象
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public TbGlobalConfigObj queryByObj(TbGlobalConfigObj obj);

	/**
	 * @Title:更新全局配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updateByObj(TbGlobalConfigObj obj);

	/**
	 * @Title:删除全局配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int deleteByObj(TbGlobalConfigObj obj);

	/**
	 * @Title:插入全局配置信息
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
