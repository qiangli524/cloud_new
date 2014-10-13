package com.sitech.basd.resource.dao.switchBoard;

import java.util.List;

import com.sitech.basd.resource.domain.switchBoard.StaticRouterObj;
/**
 * 
 * <p>Title: StaticRouterDao</p>
 * <p>Description: 交换机 静态路由相关操作</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author yanggl
 * @version 1.0
 * @createtime 2014-6-8 下午4:24:11
 *
 */
public interface StaticRouterDao {
	/**
	 * 
	 * @Title: queryForList
	 * @Description: 查询
	 * @param
	 * @return List<StaticRouterObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-8 下午4:24:52
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
	 * @createtime 2014-6-8 下午4:25:04
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
	 * @createtime 2014-6-8 下午4:25:12
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
	 * @createtime 2014-6-8 下午4:25:20
	 */
	public int deleteByObj(StaticRouterObj obj);

}
