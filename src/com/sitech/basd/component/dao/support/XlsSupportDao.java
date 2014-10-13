package com.sitech.basd.component.dao.support;

import java.util.List;
import java.util.Map;

public interface XlsSupportDao {
	/**
	 * 
	 * @Title: importFromXls
	 * @Description:从excel导入数据，包括主机，用户和生成部署实例
	 * @author duangh
	 * @date Jul 29, 2013 4:16:03 PM
	 * @return
	 */
	public String importFromXls(Map<String, List> importMap);
}
