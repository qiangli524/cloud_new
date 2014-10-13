package com.sitech.basd.sxcloud.cloud.dao.loadBalancing;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.loadBalancing.FarmObj;

public interface FarmsDao {

	/**
	 * @Title:根据Farm部分信息查询匹配的所有Farm信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryForListByObj(FarmObj obj);

	/**
	 * @Title:查询出具体Farm信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public FarmObj queryByObj(FarmObj obj);

	/**
	 * @Title:更新Farm信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int updateByObj(FarmObj obj);

	/**
	 * @Title:刪除Farm信息
	 * @Copyright: Copyright (c) 201010
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int deleteByObj(FarmObj obj);

	/**
	 * @Title:插入Farm信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int insertByObj(FarmObj obj);
}
