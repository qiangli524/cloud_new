package com.sitech.basd.sxcloud.cloud.dao.exportconfig;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.exportconfig.ExportConfigObj;

public interface ExportConfigDao {

	/**
	 * @Title:根据KEY查询匹配的所有导出配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryForListByObj(ExportConfigObj obj);

	/**
	 * @Title:查询匹配的导出配置信息返回一个对象
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public ExportConfigObj queryByObj(ExportConfigObj obj);

	/**
	 * @Title:更新导出配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updateByObj(ExportConfigObj obj);

	/**
	 * @Title:删除导出配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int deleteByObj(ExportConfigObj obj);

	/**
	 * @Title:插入导出配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(ExportConfigObj obj);

	/**
	 * 
	 * @Title: 查询单条导出配置信息
	 * @Copyright:Copyright (c) Jul 23, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public int queryForObjByObj(ExportConfigObj obj);
}
