package com.sitech.basd.yicloud.dao.deploy;

import com.sitech.basd.yicloud.domain.deploy.TbCloud2DepFileObj;

public interface TbCloud2DepFileDao {
	

	/**
	 * @Title:插入部署文件信息
	 * @Copyright: Copyright (c) 201206
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public int insertByObj(TbCloud2DepFileObj obj);
	
	/**
	 * @Title:得到最大的批次号Id
	 * @Copyright: Copyright (c) 201206
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public int queryBatchID();

}
