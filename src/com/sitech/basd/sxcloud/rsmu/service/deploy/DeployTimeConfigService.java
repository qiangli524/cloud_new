package com.sitech.basd.sxcloud.rsmu.service.deploy;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployTimeConfigObj;

public interface DeployTimeConfigService {

	/**
	 * @Title:根据部署时间信息查询匹配的所有部署时间信息
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(TbBusiDeployTimeConfigObj obj);

	/**
	 * @Title:更新部署基本信息 更新EXPRESSION字段
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public int updateByObj(TbBusiDeployTimeConfigObj obj);

	/**
	 * @Title:更新部署基本信息 更新STATUS字段
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public int updateByObj2(TbBusiDeployTimeConfigObj obj);

	/**
	 * @Title:更新部署基本信息 更新EXPRESSION字段和STATUS字段
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public int updateByObj3(TbBusiDeployTimeConfigObj obj);
}
