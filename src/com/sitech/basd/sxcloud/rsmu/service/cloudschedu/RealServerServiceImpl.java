package com.sitech.basd.sxcloud.rsmu.service.cloudschedu;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.cloudschedu.RealServerDao;
import com.sitech.basd.sxcloud.rsmu.dao.cloudschedu.VirtualServerDao;
import com.sitech.basd.sxcloud.rsmu.domain.cloudschedu.RealServerObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;


public class RealServerServiceImpl extends BaseService implements
		RealServerService {

	public int deleteByObj(RealServerObj obj) {
		// TODO Auto-generated method stub
		return realServerDao.deleteByObj(obj);
	}

	public int insertByObj(RealServerObj obj) {
		// TODO Auto-generated method stub
		return realServerDao.insertByObj(obj);
	}

	public RealServerObj queryByObj(RealServerObj obj) {
		// TODO Auto-generated method stub
		return realServerDao.queryByObj(obj);
	}

	public List queryForListByObj(RealServerObj obj) {
		// TODO Auto-generated method stub
		return realServerDao.queryForListByObj(obj);
	}

	public int updateByObj(RealServerObj obj) {
		// TODO Auto-generated method stub
		return realServerDao.updateByObj(obj);
	}

	RealServerDao realServerDao;

	public void setRealServerDao(RealServerDao realServerDao) {
		this.realServerDao = realServerDao;
	}

	VirtualServerDao virtualServerDao;

	public void setVirtualServerDao(VirtualServerDao virtualServerDao) {
		this.virtualServerDao = virtualServerDao;
	}

}
