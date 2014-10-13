package com.sitech.basd.sxcloud.rsmu.service.deploy;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.ExampleResumeObj;

public interface ExampleResumeService {

	/**
	 * @Title:根据恢复信息查询匹配的所有恢复信息
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(ExampleResumeObj obj);;

	
	/**
	 * @Title:删除恢复信息
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
	 * @author 	wangzca
	 * @version 1.0
	 */
	public int resumeByObj(ExampleResumeObj obj);
	
}
