package com.sitech.basd.envmanager.service.configure;

import java.util.List;


import com.sitech.basd.envmanager.domain.configure.ConfigureObj;

public interface ConfigureService {
	
	/**
	 * @Title:查询所有开发环境使用情况信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public List queryConfigureObj(ConfigureObj obj);
	/**
	 * @Title:查某个开发环境使用情况信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public ConfigureObj queryConfigureOne(ConfigureObj obj);
	/**
	 * @Title:添加开发环境使用情况信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int insertConfigureObj(ConfigureObj obj);
	/**
	 * @Title:修改开发环境使用情况信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int updateConfigureObj(ConfigureObj obj);
	/**
	 * @Title:删除开发环境使用情况信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int deleteConfigureObj(ConfigureObj obj);

}
