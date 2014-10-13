package com.sitech.basd.sxcloud.rsmu.service.cloudschedu;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.cloudschedu.VirtualServerDao;
import com.sitech.basd.sxcloud.rsmu.domain.cloudschedu.VirtualServerObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;


public class VirtualServerServiceImpl extends BaseService implements
		VirtualServerService {

	public int deleteByObj(VirtualServerObj obj) {
		// TODO Auto-generated method stub
		return virtualServerDao.deleteByObj(obj);
	}

	public int insertByObj(VirtualServerObj obj) {
		// TODO Auto-generated method stub
		return virtualServerDao.insertByObj(obj);
	}

	public VirtualServerObj queryByObj(VirtualServerObj obj) {
		// TODO Auto-generated method stub
		return virtualServerDao.queryByObj(obj);
	}

	public List queryForListByObj(VirtualServerObj obj) {
		// TODO Auto-generated method stub
		return virtualServerDao.queryForListByObj(obj);
	}

	public int updateByObj(VirtualServerObj obj) {
		// TODO Auto-generated method stub
		return virtualServerDao.updateByObj(obj);
	}
	VirtualServerDao virtualServerDao;
	
	public void setVirtualServerDao(VirtualServerDao virtualServerDao) {
		this.virtualServerDao = virtualServerDao;
	}
	
	
}
