package com.sitech.basd.component.service.support;

import java.io.InputStream;

public interface XlsSupportService {
	/**
	 * 
	 * @Title: importFromXls
	 * @Description:从excel导入数据，包括主机，用户和生成部署实例
	 * @author duangh
	 * @date Jul 29, 2013 4:24:34 PM
	 * @return
	 */
	public String importFromXls(InputStream is);
}
