package com.sitech.ssd.ah.zookeeper.service;

import com.sitech.ssd.ah.zookeeper.domain.ZookeeperTreeObj;

public interface ZookeeperTreeService {
	/**
	 * @Title: queryTreeByObj
	 * @Description:查询树节点
	 * @param
	 * @return ZookeeperTreeObj
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-11 下午6:04:35
	 */
	public ZookeeperTreeObj queryTreeByObj(ZookeeperTreeObj obj);

	/**
	 * @Title: updateZookeeperTreeObj
	 * @Description: 修改节点
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-12 下午3:22:16
	 */
	public void updateZookeeperTreeObj(ZookeeperTreeObj obj);

	/**
	 * @Title: deleteZookeeperTreeObj
	 * @Description: 删除节点
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-12 下午3:22:43
	 */
	public void deleteZookeeperTreeObj(ZookeeperTreeObj obj);
}
