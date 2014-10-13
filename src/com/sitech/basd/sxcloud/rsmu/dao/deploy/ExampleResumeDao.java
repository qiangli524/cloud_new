package com.sitech.basd.sxcloud.rsmu.dao.deploy;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.ExampleResumeObj;

public interface ExampleResumeDao {

	/**
	 * @Title:根据升级信息查询匹配的所有升级信息
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(ExampleResumeObj obj);;

	
	/**
	 * @Title:删除升级信息
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public int deleteByObj(ExampleResumeObj obj);
	/**
	 * @Title:恢复操作
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public int resumeByObj(ExampleResumeObj obj);

	
}
