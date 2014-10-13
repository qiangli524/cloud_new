package com.sitech.basd.envmanager.dao.develop;

import java.sql.SQLException;
import java.util.List;


import com.sitech.basd.envmanager.domain.develop.DevelopObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class DevelopDaoImpl extends BaseDao implements DevelopDao{

	@Override
	public int deleteDevelopObj(DevelopObj obj) {
		int ret=0;
		try {								
			Object o = getSqlMap().delete("[...].deleteDevelopObj", obj);
			if(o!=null){
				ret=Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret=-1;
			LogHelper.error("[...].deleteDevelopObj:"+
			e.getMessage()+getClass().getName());
		}
		return ret;
	}

	@Override
	public int insertDevelopObj(DevelopObj obj) {
		int ret = 0;
		try {
			Object o =  getSqlMap().insert("[...].insertDevelopObj", obj);
			if(o!=null){
				ret =Integer.parseInt( o.toString());
			}
		} catch (Exception e) {
			ret=-1;
			LogHelper.error("[...].insertDevelopObj:"+
			e.getMessage()+getClass().getName());
		}
		return ret;
	}

	@Override
	public List queryDevelopObj(DevelopObj obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DevelopObj queryDevelopOne(DevelopObj obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateDevelopObj(DevelopObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("[...].updateDevelopObj", obj);
			if(o!=null){
				Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("[...].updateDevelopObj:"+
			e.getMessage()+getClass().getName());
		}
		return ret;
	}

}
