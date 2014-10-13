package com.sitech.basd.cloud3.service.machineroom;

import java.util.List;

import com.sitech.basd.cloud3.dao.machineroom.AreaDao;
import com.sitech.basd.cloud3.domain.machineroom.RoomAreaObj;

public class AreaServiceImpl implements AreaService{
	
	AreaDao areaDao;
	
	
	
	public AreaDao getAreaDao() {
		return areaDao;
	}

	public void setAreaDao(AreaDao areaDao) {
		this.areaDao = areaDao;
	}

	@Override
	public int deleteAreaObj(RoomAreaObj obj) {
		// TODO Auto-generated method stub
		return areaDao.deleteAreaObj(obj);
	}

	@Override
	public int insertAreaObj(RoomAreaObj obj) {
		// TODO Auto-generated method stub
		return areaDao.insertAreaObj(obj);
	}

	@Override
	public List queryAreaList(RoomAreaObj obj) {
		// TODO Auto-generated method stub
		return areaDao.queryAreaList(obj);
	}

	@Override
	public RoomAreaObj queryOneArea(RoomAreaObj obj) {
		// TODO Auto-generated method stub
		return areaDao.queryOneArea(obj);
	}

	@Override
	public int updateAreaObj(RoomAreaObj obj) {
		// TODO Auto-generated method stub
		return areaDao.updateAreaObj(obj);
	}


}
