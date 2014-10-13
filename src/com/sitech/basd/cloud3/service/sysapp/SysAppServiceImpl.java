package com.sitech.basd.cloud3.service.sysapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.cloud3.dao.sysapp.SysAppDao;
import com.sitech.basd.cloud3.domain.sysapp.SysAppObj;

@Service("sysAppService")
public class SysAppServiceImpl implements SysAppService {

	@Autowired
	private SysAppDao sysAppDao;

	@Override
	public List<SysAppObj> queryAllAttach() {
		return sysAppDao.queryAllAttach();
	}

	@Override
	public SysAppObj queryOne(SysAppObj sysAppObj) {
		return sysAppDao.queryOne(sysAppObj);
	}

	@Override
	public List<SysAppObj> querySysAppConfiged() {
		return sysAppDao.querySysAppConfiged();
	}
	

}
