package com.sitech.basd.sxcloud.cloud.service.exportconfig;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.exportconfig.ExportConfigObj;

public interface ExportConfigService {

	/**
	 * @Title:查询所有的导出配置管理信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryForListByObj(ExportConfigObj obj);

	/**
	 * @Title:查询并返回一个项目对象
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public ExportConfigObj queryByObj(ExportConfigObj obj);

	/**
	 * @Title:更新导出配置管理信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updateByObj(ExportConfigObj obj);

	/**
	 * @Title:删除导出配置管理信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int deleteByObj(ExportConfigObj obj);

	/**
	 * @Title:插入导出配置管理信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(ExportConfigObj obj);

	/**
	 * 
	 * @Title: 获取单条导出配置信息
	 * @Copyright:Copyright (c) Jul 23, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public int queryForObjByObj(ExportConfigObj obj);
}
