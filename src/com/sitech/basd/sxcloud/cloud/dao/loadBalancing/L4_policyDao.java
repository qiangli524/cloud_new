package com.sitech.basd.sxcloud.cloud.dao.loadBalancing;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.loadBalancing.L4PolicyObj;

public interface L4_policyDao {

	/**
	 * @Title:根据L4_policy部分信息查询匹配的所有L4_policy信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryForListByObj(L4PolicyObj obj);;

	/**
	 * @Title:查询出具体L4_policy信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public L4PolicyObj queryByObj(L4PolicyObj obj);;

	/**
	 * @Title:更新L4_policy信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int updateByObj(L4PolicyObj obj);

	/**
	 * @Title:刪除L4_policy信息
	 * @Copyright: Copyright (c) 201010
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int deleteByObj(L4PolicyObj obj);

	/**
	 * @Title:插入L4_policy信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int insertByObj(L4PolicyObj obj);
}
