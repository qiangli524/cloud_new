package com.sitech.basd.sxcloud.cloud.service.loadBalancing;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.loadBalancing.L4PolicyObj;

public interface L4_policyService {

	/**
	 * @Title:根据4层策略信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public List queryForListByObj(L4PolicyObj obj);;

	/**
	 * @Title:查询具体的农场信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public L4PolicyObj queryByObj(L4PolicyObj obj);;

	/**
	 * @Title:变更4层策略信息
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public int updateByObj(L4PolicyObj obj);

	/**
	 * @Title:删除4层策略信息
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public int deleteByObj(L4PolicyObj obj);

	/**
	 * @Title:插入4层策略信息
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public int insertByObj(L4PolicyObj obj);
}
