package com.sitech.taokeeper.dao.impl;


import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.taokeeper.dao.SettingsDAO;
import com.sitech.taokeeper.model.TaoKeeperSettings;
import common.toolkit.java.exception.DaoException;
/**
 * Description: Access DB for taokeeper settings
 * @author   yinshi.nc
 * @Date	 2011-10-28
 */
@Repository("taoKeeperSettingsDAO")
public class SettingsDAOImpl extends BaseDao implements SettingsDAO{
	
	public TaoKeeperSettings getTaoKeeperSettingsBySettingsId( int settingsId )throws DaoException{
		
		TaoKeeperSettings taoKeeperSettings = null;
    	try {
    		
    		TaoKeeperSettings taoKeeperSettingsObj = new TaoKeeperSettings();
    		taoKeeperSettingsObj.setSettingsId(settingsId);
    		taoKeeperSettings = (TaoKeeperSettings)getSqlMapClient().queryForObject("ZookeeperMonitor.queryTaoKeeperSettingsObjList", taoKeeperSettingsObj);
			return taoKeeperSettings;
		} catch ( Exception e ) {
			throw new DaoException( "Error when query TaoKeeperSettings by settings_id: " + settingsId + ", Error: " + e.getMessage(), e );
		}
	}

	@Override
	public boolean updateTaoKeeperSettingsBySettingsId( TaoKeeperSettings taoKeeperSettings ) throws DaoException {

		if( null == taoKeeperSettings )
			return false;
		//从数据库中获取指定settings_id配置
    	try {
    		
    		int num = getSqlMapClient().update("ZookeeperMonitor.updateTaokeeperSettings", taoKeeperSettings);
			if( 1 == num ){
				return true;
			}
			throw new DaoException( "Not exist such record" );
		} catch ( Exception e ) {
			throw new DaoException( "Error when update taoKeeperSettings by settingsId: " + taoKeeperSettings + ", Error: " + e.getMessage(), e );
		}
	}

	@Override
	public boolean addTaoKeeperSettings( TaoKeeperSettings taoKeeperSettings ) throws DaoException {
		
		if( null == taoKeeperSettings )
			return false;
		
    	try {
    		Object result = getSqlMapClient().insert("ZookeeperMonitor.insertTaokeeperSettings", taoKeeperSettings);
			if( result != null ){
				return true;
			}
			throw new DaoException( "Not exist such record" );
		} catch ( Exception e ) {
			throw new DaoException( "Error when insert taoKeeperSettings: " + taoKeeperSettings + ", Error: " + e.getMessage(), e );
		}
		
		
	}

	
}
