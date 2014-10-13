package com.sitech.ssd.ah.zookeeper.dao;

import com.sitech.ssd.ah.zookeeper.domain.ZookeeperTreeObj;

public interface ZookeeperTreeDao {
	/**
	 * @Title: queryTreeByObj
	 * @Description:查询某节点
	 * @param
	 * @return ZookeeperTreeObj
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-11 下午6:05:19
	 */
	public ZookeeperTreeObj queryTreeByObj(ZookeeperTreeObj obj);

	/**
	 * @Title: updateZookeeperTreeObj
	 * @Description: 修改节点
	 * @param
	 * @return int
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-12 下午3:15:38
	 */
	public int updateZookeeperTreeObj(ZookeeperTreeObj obj);

	/**
	 * @Title: deleteZookeeperTreeObj
	 * @Description:删除节点
	 * @param
	 * @return int
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-12 下午3:17:23
	 */
	public int deleteZookeeperTreeObj(ZookeeperTreeObj obj);
}
