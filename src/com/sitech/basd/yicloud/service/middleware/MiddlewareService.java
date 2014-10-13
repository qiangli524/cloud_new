package com.sitech.basd.yicloud.service.middleware;

import java.util.List;

import com.sitech.basd.yicloud.domain.middleware.MiddlewareObj;

public interface MiddlewareService {
	/**
	 * @Title:查询已有中间件列表
	 * @Copyright: Copyright (c) 2012-5
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryForListByObj(MiddlewareObj obj);

	/**
	 * @Title:查询一条中间件信息
	 * @Copyright: Copyright (c) 2012-5
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public MiddlewareObj queryByObj(MiddlewareObj obj);

	/**
	 * @Title:插入新的中间件信息
	 * @Copyright: Copyright (c) 2012-5
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(MiddlewareObj obj);

	/**
	 * @Title:修改中间件信息
	 * @Copyright: Copyright (c) 2012-5
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updateByObj(MiddlewareObj obj);

	/**
	 * @Title:删除中间件信息
	 * @Copyright: Copyright (c) 2012-5
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int deleteByObj(MiddlewareObj obj);
}
