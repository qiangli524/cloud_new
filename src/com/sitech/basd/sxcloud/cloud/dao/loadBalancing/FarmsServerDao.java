package com.sitech.basd.sxcloud.cloud.dao.loadBalancing;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.loadBalancing.FarmServerObj;

public interface FarmsServerDao {

	/**
	 * @Title:根据FarmServer部分信息查询匹配的所有FarmServer信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryForListByObj(FarmServerObj obj);

	/**
	 * @Title:查询出具体FarmServer信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public FarmServerObj queryByObj(FarmServerObj obj);

	/**
	 * @Title:更新FarmServer信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int updateByObj(FarmServerObj obj);

	/**
	 * @Title:刪除FarmServer信息
	 * @Copyright: Copyright (c) 201010
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int deleteByObj(FarmServerObj obj);

	/**
	 * @Title:插入FarmServer信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int insertByObj(FarmServerObj obj);
}
