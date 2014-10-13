package com.sitech.basd.yicloud.service.opersystem;

import java.util.List;

import com.sitech.basd.yicloud.domain.opersystem.OperSystemObj;

public interface OperSystemService {
	/**
	 * @Title:查询已有操作系统列表
	 * @Copyright: Copyright (c) 2012-5
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryForListByObj(OperSystemObj obj);

	/**
	 * @Title:查询一条操作系统文件信息
	 * @Copyright: Copyright (c) 2012-5
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public OperSystemObj queryByObj(OperSystemObj obj);

	/**
	 * @Title:插入新的操作系统文件信息
	 * @Copyright: Copyright (c) 2012-5
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(OperSystemObj obj);

	/**
	 * @Title:修改操作系统文件信息
	 * @Copyright: Copyright (c) 2012-5
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updateByObj(OperSystemObj obj);

	/**
	 * @Title:删除操作系统信息
	 * @Copyright: Copyright (c) 2012-5
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int deleteByObj(OperSystemObj obj);
}
