package com.sitech.taokeeper.dao;

import java.util.List;

import com.sitech.taokeeper.model.TaokeeperNodesInfo;


import common.toolkit.java.exception.DaoException;

/**
 * Description: Access DB for zookeeper cluster
 * @author   yinshi.nc
 * @version  
 * @since   
 * @Date	 2011-10-28
 */
public interface ZookeeperNodesInfoDAO {
	
	
	
	public boolean updateTaokeeperNodesInfo( TaokeeperNodesInfo taokeeperNodesInfo ) throws DaoException;
	
	/***
	 * 添加一个zookeeper集群TaokeeperNodesInfo
	 */
	public int addTaokeeperNodesInfo( TaokeeperNodesInfo taokeeperNodesInfo ) throws DaoException;
	
	public int deleteTaokeeperNodesInfo(TaokeeperNodesInfo obj) throws DaoException;
	
	public List<TaokeeperNodesInfo> getZookeeperNodesInfosByCulsterId( int clusterId )throws DaoException;
	
	
}
