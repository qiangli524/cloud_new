package com.sitech.basd.sxcloud.rsmu.service.deploy.deployfileversion;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.deployfileversion.DeployFileVersionObj;

public interface DeployFileVersionService {

	/**
	 * 
	 * @Title:查询所有的文件版本信息
	 * @Copyright:Copyright (c) Jul 3, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryFileVersionList(DeployFileVersionObj obj);

	/**
	 * 
	 * @Title: 删除文件版本
	 * @Copyright:Copyright (c) Jul 3, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public int deleteFileVersionObj(DeployFileVersionObj obj);

	/**
	 * 
	 * @Title: 更新文件版本信息
	 * @Copyright:Copyright (c) Jul 3, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public int updateFileVersionObj(DeployFileVersionObj obj);

	/**
	 * 
	 * @Title: 新增文件版本信息
	 * @Copyright:Copyright (c) Jul 3, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public int insertFileVersionObj(DeployFileVersionObj obj);

	/**
	 * 
	 * @Title: 查询一条文件版本信息
	 * @Copyright:Copyright (c) Jul 3, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public DeployFileVersionObj queryFileVersionOne(DeployFileVersionObj obj);

	/**
	 * 
	 * @Title: 根据appid获取其下最大的版本号
	 * @Copyright:Copyright (c) Jul 8, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public double getMaxVersionNoByAppid(DeployFileVersionObj obj);

	/**
	 * 
	 * @Title: 插入历史表做备份
	 * @Copyright:Copyright (c) Jul 12, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public int insertFileVersionHis(DeployFileVersionObj obj);

	/**
	 * 
	 * @Title: 版本信息历史查询
	 * @Copyright:Copyright (c) Jul 15, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public List queryFileVersionHisList(DeployFileVersionObj obj);
	
	public String querySoftCatchFiles(DeployFileVersionObj obj);
}
