package com.sitech.taokeeper.dao.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.taokeeper.dao.ReportDAO;
import com.sitech.taokeeper.model.TaoKeeperStat;
import common.toolkit.java.entity.DateFormat;
import common.toolkit.java.exception.DaoException;
import common.toolkit.java.util.DateUtil;
import common.toolkit.java.util.StringUtil;
import common.toolkit.java.util.collection.CollectionUtil;

/**
 * Description: Access DB for taokeeper_stat
 * @author yinshi.nc
 * @since 2012-01-05
 */
@Repository("reportDAO")
public class ReportDAOImpl extends BaseDao implements ReportDAO {

	@Override
	public void addTaoKeeperStat( TaoKeeperStat taoKeeperStat ) throws DaoException {

		if ( null == taoKeeperStat ) {
			return;
		}
		try {
			TaoKeeperStat taoKeeperStatObj = new TaoKeeperStat();
			taoKeeperStatObj.setClusterId(taoKeeperStat.getClusterId());
			taoKeeperStatObj.setServer(taoKeeperStat.getServer());
			taoKeeperStatObj.setStatDateTime(StringUtil.defaultIfBlank( taoKeeperStat.getStatDateTime(), DateUtil.getNowTime( DateFormat.DateTime ) ));
			taoKeeperStatObj.setStatDate(StringUtil.defaultIfBlank( taoKeeperStat.getStatDate(), DateUtil.getNowTime( DateFormat.Date ) ));
			taoKeeperStatObj.setConnections(taoKeeperStat.getConnections());
			taoKeeperStatObj.setWatches(taoKeeperStat.getWatches());
			taoKeeperStatObj.setSendTimes((Integer)taoKeeperStat.getSendTimes().intValue());
			taoKeeperStatObj.setReceiveTimes((Integer)taoKeeperStat.getReceiveTimes().intValue());
			taoKeeperStatObj.setNodeCount((Integer)taoKeeperStat.getNodeCount().intValue());
			taoKeeperStatObj.setRwps(taoKeeperStat.getRwps());
			getSqlMapClient().insert("ZookeeperMonitor.insertTaokeeperStat", taoKeeperStatObj);
		} catch ( Exception e ) {
			throw new DaoException( "Error when add taoKeeperStat" + taoKeeperStat + ", Error: " + e.getMessage(), e );
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List< TaoKeeperStat > queryTaoKeeperStatByClusterIdAndServerAndStatDate( int clusterId, String server, String statDate )
			throws DaoException {

		if ( 0 == clusterId || StringUtil.isBlank( server ) || StringUtil.isBlank( statDate ) ) {
			return new ArrayList< TaoKeeperStat >();
		}

		List< TaoKeeperStat > taoKeeperStatList = new ArrayList< TaoKeeperStat >();
		try {
			TaoKeeperStat obj = new TaoKeeperStat();
			obj.setClusterId(clusterId);
			obj.setServer(server);
			obj.setStatDate(statDate);
			//String statDateTime = StringUtil.trimToEmpty( StringUtil.trimToEmpty( rs.getString( "stat_date_time" ) ).replaceFirst( statDate, EMPTY_STRING ) );
			taoKeeperStatList = (List< TaoKeeperStat >)getSqlMapClient().queryForList("ZookeeperMonitor.queryTaoKeeperStatObjList",obj);
			if ( null == taoKeeperStatList ) {
				return new ArrayList< TaoKeeperStat >();
			}
			return taoKeeperStatList;
		} catch ( Exception e ) {
			throw new DaoException( "Error when queryTaoKeeperStatByClusterIdAndServerAndStatDate: clusterId:" + clusterId + " server:" + server
					+ "statDate:" + statDate + ", Error: " + e.getMessage(), e );
		} 

	}

	@SuppressWarnings("unchecked")
	@Override
	public Map< String, List< TaoKeeperStat > > queryStatByClusterIdAndStatDate( int clusterId, String statDate ) throws DaoException {

		if ( 0 == clusterId || StringUtil.isBlank( statDate ) ) {
			return CollectionUtil.emptyMap();
		}

		Map< String, List< TaoKeeperStat > > taoKeeperStatMap = new HashMap< String, List< TaoKeeperStat > >();

		
		
		List< TaoKeeperStat > taoKeeperStatList = new ArrayList< TaoKeeperStat >();
		try {
			TaoKeeperStat obj = new TaoKeeperStat();
			obj.setClusterId(clusterId);
			obj.setStatDate(statDate);
			taoKeeperStatList = (List< TaoKeeperStat >)getSqlMapClient().queryForList("ZookeeperMonitor.queryTaoKeeperStatObjList",obj);
			if ( null == taoKeeperStatList ) {
				return CollectionUtil.emptyMap();
			}
			for(TaoKeeperStat taoKeeperStat : taoKeeperStatList){
				this.addStatToTaokeeperStatMap( taoKeeperStatMap, taoKeeperStat);
			}
			return taoKeeperStatMap;
		} catch ( Exception e ) {
			throw new DaoException( "Error when queryStatByClusterIdAndStatDate: clusterId:" + clusterId
					+ "statDate:" + statDate + ", Error: " + e.getMessage(), e );
		} 
		
		
	}

	/**
	 * Tool: Add taoKeeperStat to Map<String, List< TaoKeeperStat > >
	 * taoKeeperStatMap
	 * */
	private void addStatToTaokeeperStatMap( Map< String, List< TaoKeeperStat > > taoKeeperStatMap, TaoKeeperStat taoKeeperStat ) {

		if ( null == taoKeeperStatMap ) {
			taoKeeperStatMap = new HashMap< String, List< TaoKeeperStat > >();
		}

		String server = StringUtil.trimToEmpty( taoKeeperStat.getServer() );
		if ( StringUtil.isBlank( server ) )
			return;

		List< TaoKeeperStat > taoKeeperStatList = null;
		// The server not in keySet
		if ( null == ( taoKeeperStatList = taoKeeperStatMap.get( server ) ) ) {
			taoKeeperStatList = new ArrayList< TaoKeeperStat >();
			taoKeeperStatList.add( taoKeeperStat );
			taoKeeperStatMap.put( server, taoKeeperStatList );
		}
		// The server in keySet
		else {
			taoKeeperStatList.add( taoKeeperStat );
			taoKeeperStatMap.put( server, taoKeeperStatList );
		}

	}

}
