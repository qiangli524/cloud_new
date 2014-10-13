package com.sitech.taokeeper.dao;

import java.util.List;

import com.sitech.taokeeper.model.ZooKeeperCluster;
import common.toolkit.java.exception.DaoException;

/**
 * Description: Access DB for zookeeper cluster
 * 
 * @author yinshi.nc
 * @version
 * @since
 * @Date 2011-10-28
 */
public interface ZooKeeperClusterDAO {
	/**
	 * @Title: queryClusterList
	 * @Description: 查询出zookeeper集群
	 * @param
	 * @return List<ZooKeeperCluster>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-13 下午3:30:32
	 */
	public List<ZooKeeperCluster> queryClusterList(ZooKeeperCluster obj);

	public ZooKeeperCluster getZooKeeperClusterByCulsterId(int clusterId) throws DaoException;

	public List<ZooKeeperCluster> getAllDetailZooKeeperCluster() throws DaoException;

	/** 获取所有clusterid name */
	public List<ZooKeeperCluster> getAllZooKeeperClusterIdAndName() throws DaoException;

	public boolean updateZooKeeperSettingsByClusterId(ZooKeeperCluster zooKeeperCluster)
			throws DaoException;

	/***
	 * 添加一个zookeeper集群
	 */
	public int addZooKeeper(ZooKeeperCluster zooKeeperCluster) throws DaoException;

	/**
	 * 删除集群
	 */
	public void deleteZooKeeperCluster(ZooKeeperCluster obj);

}
