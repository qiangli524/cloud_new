package com.sitech.basd.sxcloud.rsmu.dao.deploy;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployVideorecordObj;

public interface TbBusiDeployVideorecordDao {

	/**
	 * @Title:录像过程记录列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	*/
	public List queryForListByObj(TbBusiDeployVideorecordObj obj);

	/**
	* @Title:录像过程记录
	* @Copyright: Copyright (c) 201006
	* @Company: si-tech
	* @author yangwenchao
	* @version 1.0
	*/
	public int insertByObj(TbBusiDeployVideorecordObj obj);

	/**
	 * @Title:录像过程记录列表(只查ID)
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	*/
	public List queryIDListByObj(TbBusiDeployVideorecordObj obj);

	/**
	 * @Title:获得执行编号
	 * @Copyright: Copyright (c) 201008
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	*/
	public int queryForID_FREQ_SEQUENCES();

	/**
	 * @Title:查询序列作为videoid
	 * @Copyright: Copyright (c) 201008
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	*/
	public int queryForVideoId();
}
