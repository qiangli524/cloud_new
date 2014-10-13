package com.sitech.basd.resource.service.switchBoard;

import java.util.List;

import com.sitech.basd.resource.domain.switchBoard.StaticRouterObj;
/**
 * 
 * <p>Title: StaticRouterService</p>
 * <p>Description: 交换机路由相关操作</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author yanggl
 * @version 1.0
 * @createtime 2014-6-8 下午4:27:23
 *
 */
public interface StaticRouterService {
	/**
	 * 
	 * @Title: queryForList
	 * @Description: 查询
	 * @param
	 * @return List<StaticRouterObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-8 下午4:27:41
	 */
	public List<StaticRouterObj> queryForList(StaticRouterObj obj);
	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 添加
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-8 下午4:27:51
	 */
	public int insertByObj(StaticRouterObj obj);
	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 修改
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-8 下午4:28:01
	 */
	public int updateByObj(StaticRouterObj obj);
	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-8 下午4:28:10
	 */
	public int deleteByObj(StaticRouterObj obj);

}
