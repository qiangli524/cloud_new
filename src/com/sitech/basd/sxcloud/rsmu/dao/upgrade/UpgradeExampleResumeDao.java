package com.sitech.basd.sxcloud.rsmu.dao.upgrade;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.upgrade.UpgradeExampleResumeObj;

public interface UpgradeExampleResumeDao {

	/**
	 * @Title:根据升级信息查询匹配的所有升级信息
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(UpgradeExampleResumeObj obj);;

	/**
	 * @Title:删除升级信息
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public int deleteByObj(UpgradeExampleResumeObj obj);

	/**
	 * @Title:恢复操作
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public int resumeByObj(UpgradeExampleResumeObj obj);

}
