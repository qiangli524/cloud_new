package com.sitech.basd.sxcloud.cloud.service.exportconfig;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.dao.exportconfig.ExportConfigDao;
import com.sitech.basd.sxcloud.cloud.domain.exportconfig.ExportConfigObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class ExportConfigServiceImpl extends BaseService implements ExportConfigService {

	/**
	 * @Title:删除导出配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int deleteByObj(ExportConfigObj obj) {
		// TODO Auto-generated method stub
		return exportConfigDao.deleteByObj(obj);
	}

	/**
	 * @Title:插入导出配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(ExportConfigObj obj) {
		// TODO Auto-generated method stub
		return exportConfigDao.insertByObj(obj);
	}

	/**
	 * @Title:查询匹配的所有导出配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryForListByObj(ExportConfigObj obj) {

		return exportConfigDao.queryForListByObj(obj);
	}

	/**
	 * @Title:查询导出配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public ExportConfigObj queryByObj(ExportConfigObj obj) {
		return exportConfigDao.queryByObj(obj);
	}

	/**
	 * @Title:更新导出配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updateByObj(ExportConfigObj obj) {
		// TODO Auto-generated method stub
		return exportConfigDao.updateByObj(obj);
	}

	private ExportConfigDao exportConfigDao;

	public ExportConfigDao getExportConfigDao() {
		return exportConfigDao;
	}

	public void setExportConfigDao(ExportConfigDao exportConfigDao) {
		this.exportConfigDao = exportConfigDao;
	}

	@Override
	public int queryForObjByObj(ExportConfigObj obj) {
		return exportConfigDao.queryForObjByObj(obj);
	}

}
