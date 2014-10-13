package com.sitech.basd.sxcloud.cloud.dao.appmessage;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.appmessage.AppMessageObj;
import com.sitech.basd.sxcloud.cloud.domain.appmessage.BizsysObj;

public interface AppMessageDao {
	/**
	 * @Title:根据模块ID模糊查询应用信息列表
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryAppMessageObjByUnion(AppMessageObj obj);

	/**
	 * @Title:根据应用信息部分信息查询匹配的所有应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(AppMessageObj obj);

	/**
	 * @Title:查询出具体应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public AppMessageObj queryByObj(AppMessageObj obj);

	/**
	 * @Title:更新应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int updateByObj(AppMessageObj obj);

	/**
	 * @Title:删除应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int deleteByObj(AppMessageObj obj);

	/**
	 * @Title:插入应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int insertByObj(AppMessageObj obj);

	/**
	 * @Title:根据模块ID集查询所有补充信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByBizsysObj(BizsysObj obj);

}
