package com.sitech.ssd.sc.os.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.sc.os.dao.OsXlsSupportDao;
import com.sitech.ssd.sc.os.domain.HostModel;

@Service("osXlsSupportService")
public class OsXlsSupportServiceImpl implements OsXlsSupportService {

	@Autowired
	private OsXlsSupportDao osXlsSupportDao;

	@Override
	public String insert(List<HostModel> hostModels) {
		String result = "";
		// TODO Auto-generated method stub
		try {
			result = osXlsSupportDao.insert(hostModels);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void setOsXlsSupportDao(OsXlsSupportDao osXlsSupportDao) {
		this.osXlsSupportDao = osXlsSupportDao;
	}

	public OsXlsSupportDao getOsXlsSupportDao() {
		return osXlsSupportDao;
	}
}
