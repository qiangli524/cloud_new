package com.sitech.basd.yicloud.dao.middleware;

import java.util.List;

import com.sitech.basd.yicloud.domain.middleware.MiddlewareObj;

public interface MiddlewareDao {

	/**
	 * 获得所有中间件信息列表
	 * 
	 * @Title:
	 * @author： duangh
	 * @Company: si-tech
	 * @date : May 14, 2012
	 * @version 1.0
	 */
	public List queryForListByObj(MiddlewareObj obj);

	/**
	 * 插入中间件信息
	 * 
	 * @Title:
	 * @author： duangh
	 * @Company: si-tech
	 * @date : May 14, 2012
	 * @version 1.0
	 */
	public int insertByObj(MiddlewareObj obj);

	/**
	 * 查询并获得一个中间件信息对象
	 * 
	 * @Title:
	 * @author： duangh
	 * @Company: si-tech
	 * @date : May 14, 2012
	 * @version 1.0
	 */
	public MiddlewareObj queryByObj(MiddlewareObj obj);

	/**
	 * 更新中间件信息
	 * 
	 * @Title:
	 * @author： duangh
	 * @Company: si-tech
	 * @date : May 14, 2012
	 * @version 1.0
	 */
	public int updateByObj(MiddlewareObj obj);

	/**
	 * 删除中间件信息
	 * 
	 * @Title:
	 * @author： duangh
	 * @Company: si-tech
	 * @date : May 14, 2012
	 * @version 1.0
	 */
	public int deleteByObj(MiddlewareObj obj);
}
