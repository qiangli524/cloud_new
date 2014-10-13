package com.sitech.basd.cloud3.dao.machineroom;

import java.util.List;

import com.sitech.basd.cloud3.domain.machineroom.MachineRoomObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class RoomDaoImpl extends BaseDao implements RoomDao{

	@Override
	public int deleteRoomObj(MachineRoomObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("MachineRoom.deleteRoomObj", obj);
			if(o!=null){
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("MachineRoom.deleteRoomObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		
		return ret;
	}

	@Override
	public int insertRoomObj(MachineRoomObj obj) {
		int ret = 0;
		try {
			Object o = this.getSqlMap().insert("MachineRoom.insertRoomObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("MachineRoom.insertRoomObj: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	@Override
	public List queryRoomList(MachineRoomObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"MachineRoom.queryRoomCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("MachineRoom.queryRoomList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("MachineRoom.queryRoomList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	@Override
	public MachineRoomObj queryRoomOne(MachineRoomObj obj) {
		MachineRoomObj mObj=null;
		try {
			mObj= (MachineRoomObj) getSqlMap().queryForObject("MachineRoom.queryRoomOne",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("MachineRoom.queryRoomOne:" 
					+ sqlexception.getMessage() + getClass().getName());
		}
		return mObj;
	}

	@Override
	public int updateRoomObj(MachineRoomObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("MachineRoom.updateRoomObj", obj);
			if(o!=null){ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("MachineRoom.updateRoomObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	@Override
	public List<MachineRoomObj> queryRoomObj(MachineRoomObj obj) {
		List<MachineRoomObj> lst = null;
		try {
			
			lst = getSqlMap().queryForList("MachineRoom.queryRoomObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("MachineRoom.queryRoomObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	

}
