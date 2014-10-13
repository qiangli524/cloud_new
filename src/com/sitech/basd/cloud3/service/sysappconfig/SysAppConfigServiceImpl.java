package com.sitech.basd.cloud3.service.sysappconfig;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.cloud3.dao.sysappconfig.SysAppConfigDao;
import com.sitech.basd.cloud3.domain.sysappconfig.SysAppConfigObj;

@Service("sysAppConfigService")
public class SysAppConfigServiceImpl implements SysAppConfigService {

	@Autowired
	private SysAppConfigDao sysAppConfigDao;
	
	@Override
	public List<SysAppConfigObj> query4List(SysAppConfigObj sysAppConfigObj) {
		// TODO Auto-generated method stub
		return sysAppConfigDao.query4List(sysAppConfigObj);
	}

	@Override
	public SysAppConfigObj queryOne(SysAppConfigObj sysAppConfigObj) {
		// TODO Auto-generated method stub
		return sysAppConfigDao.queryOne(sysAppConfigObj);
	}

	@Override
	public int insertSysAppConfigObj(SysAppConfigObj sysAppConfigObj) {
		// TODO Auto-generated method stub
		return sysAppConfigDao.insertSysAppConfigObj(sysAppConfigObj);
	}

	@Override
	public int deleteSysAppConfigObj(SysAppConfigObj sysAppConfigObj) {
		// TODO Auto-generated method stub
		return sysAppConfigDao.deleteSysAppConfigObj(sysAppConfigObj);
	}

	@Override
	public int updateSysAppConfigObj(SysAppConfigObj sysAppConfigObj) {
		// TODO Auto-generated method stub
		return sysAppConfigDao.updateSysAppConfigObj(sysAppConfigObj);
	}

}
