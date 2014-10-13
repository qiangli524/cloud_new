package com.sitech.basd.cloud3.service.machineroom;

import java.util.List;

import com.sitech.basd.cloud3.dao.machineroom.RoomDao;
import com.sitech.basd.cloud3.domain.machineroom.MachineRoomObj;
import com.sitech.basd.cloud3.domain.machineroom.RoomAreaObj;

public class RoomServiceImpl implements RoomService{

	
	RoomDao roomDao;
	
	
	
	public RoomDao getRoomDao() {
		return roomDao;
	}

	public void setRoomDao(RoomDao roomDao) {
		this.roomDao = roomDao;
	}

	@Override
	public int deleteRoomObj(MachineRoomObj obj) {
		// TODO Auto-generated method stub
		return roomDao.deleteRoomObj(obj);
	}

	@Override
	public int insertRoomObj(MachineRoomObj obj) {
		// TODO Auto-generated method stub
		return roomDao.insertRoomObj(obj);
	}

	@Override
	public List queryRoomList(MachineRoomObj obj) {
		// TODO Auto-generated method stub
		return roomDao.queryRoomList(obj);
	}

	@Override
	public MachineRoomObj queryRoomOne(MachineRoomObj obj) {
		// TODO Auto-generated method stub
		return roomDao.queryRoomOne(obj);
	}

	@Override
	public int updateRoomObj(MachineRoomObj obj) {
		// TODO Auto-generated method stub
		return roomDao.updateRoomObj(obj);
	}

	@Override
	public List<MachineRoomObj> queryRoomObj(MachineRoomObj obj) {
		// TODO Auto-generated method stub
		return roomDao.queryRoomObj(obj);
	}

	
}
