package com.sitech.basd.cloud3.dao.sysapp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.cloud3.domain.sysapp.SysAppObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("sysAppDao")
public class SysAppDaoImpl extends BaseDao implements SysAppDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<SysAppObj> queryAllAttach() {
		List<SysAppObj> list = new ArrayList<SysAppObj>();
		try {
			list = getSqlMap().queryForList("SysApp.queryAllAttach");
		} catch (Exception e) {
			LogHelper.error("SysApp.queryAllAttach: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	@Override
	public SysAppObj queryOne(SysAppObj sysAppObj) {
		SysAppObj obj = null;
		try {
			obj = (SysAppObj) getSqlMap().queryForObject("SysApp.queryOne", sysAppObj);
		} catch (Exception e) {
			LogHelper.error("SysApp.queryOne: " + e.getMessage() + e.getClass().getName());
		}
		return obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysAppObj> querySysAppConfiged() {
		List<SysAppObj> list = new ArrayList<SysAppObj>();
		try {
			list = getSqlMap().queryForList("SysApp.querySysAppConfiged");
		} catch (Exception e) {
			LogHelper.error("SysApp.querySysAppConfiged: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

}
