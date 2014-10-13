package com.sitech.basd.envmanager.dao.version;

import java.util.List;

import com.sitech.basd.envmanager.domain.version.VersionObj;

public interface VersionDao {
	
	/**
	 * @Title:查询所有版本信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public List queryVersionObj(VersionObj obj);
	/**
	 * @Title:查询所有版本信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public VersionObj queryVersionOne(VersionObj obj);
	/**
	 * @Title:添加版本信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int insertVersionObj(VersionObj obj);
	/**
	 * @Title:修改版本信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int updateVersionObj(VersionObj obj);
	/**
	 * @Title:删除版本信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int deleteVersionObj(VersionObj obj);


}
