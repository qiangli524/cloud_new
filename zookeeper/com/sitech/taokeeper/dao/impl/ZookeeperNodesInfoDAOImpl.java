package com.sitech.taokeeper.dao.impl;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.taokeeper.dao.ZookeeperNodesInfoDAO;
import com.sitech.taokeeper.model.TaokeeperNodesInfo;

import common.toolkit.java.exception.DaoException;
import common.toolkit.java.util.StringUtil;
/**
 * Description: Access DB for zookeeper cluster
 * @author   yinshi.nc
 * @Date	 2011-10-28
 */
@Repository("zookeeperNodesInfoDAO")
public class ZookeeperNodesInfoDAOImpl extends BaseDao implements ZookeeperNodesInfoDAO{
	
	
	public int deleteTaokeeperNodesInfo(TaokeeperNodesInfo obj) throws DaoException{
		int num  = 0;
		try {
    		num = getSqlMapClient().delete("zookeeperNodesInfo.deleteTaokeeperNodesInfo", obj);
		} catch ( Exception e ) {
			throw new DaoException( "Error when delete zookeeperNodesInfo by uid: " + obj + ", Error: " + e.getMessage(), e );
		}
		return num;
	}
	
	@SuppressWarnings("unchecked")
	public List<TaokeeperNodesInfo> getZookeeperNodesInfosByCulsterId( int clusterId )throws DaoException{
    	try {
    		TaokeeperNodesInfo obj = new TaokeeperNodesInfo();
    		obj.setClusterId(clusterId);
    		Object result = getSqlMapClient().queryForList("zookeeperNodesInfo.queryTaokeeperNodesInfoObjList", obj);
			return result != null?(List<TaokeeperNodesInfo>)result:null;
		} catch ( Exception e ) {
			throw new DaoException( "Error when query zookeeper cluster by cluster_id: " + clusterId + ", Error: " + e.getMessage(), e );
		}
	}



	@Override
	public boolean updateTaokeeperNodesInfo(
			TaokeeperNodesInfo taokeeperNodesInfo) throws DaoException {
		if( null == taokeeperNodesInfo || taokeeperNodesInfo.getClusterId() == null || StringUtil.isBlank(taokeeperNodesInfo.getServer()))
			return false;
		
		//从数据库中获取指定zookeeper集群中所有机器
    	try {
    		int num = getSqlMapClient().update("zookeeperNodesInfo.updateTaokeeperNodesInfo", taokeeperNodesInfo);
			if( 1 == num ){
				return true;
			}
			return false;
		} catch ( Exception e ) {
			throw new DaoException( "Error when update taokeeperNodesInfo by cluster_id: " + taokeeperNodesInfo + ", Error: " + e.getMessage(), e );
		}
	}

	@Override
	public int addTaokeeperNodesInfo(TaokeeperNodesInfo taokeeperNodesInfo)
			throws DaoException {
		
		if( null == taokeeperNodesInfo )
			return -1;
		
    	try {
    		getSqlMapClient().insert("zookeeperNodesInfo.insertTaokeeperNodesInfo", taokeeperNodesInfo);
			return 1;
		} catch ( Exception e ) {
			throw new DaoException( "Error when add taokeeperNodesInfo" + taokeeperNodesInfo + ", Error: " + e.getMessage(), e );
		}
	}
}
