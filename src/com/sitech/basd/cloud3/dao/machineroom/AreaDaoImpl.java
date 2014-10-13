package com.sitech.basd.cloud3.dao.machineroom;

import java.util.List;

import com.sitech.basd.cloud3.domain.machineroom.MachineRoomObj;
import com.sitech.basd.cloud3.domain.machineroom.RoomAreaObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class AreaDaoImpl extends BaseDao implements AreaDao{
	
	@Override
	public int deleteAreaObj(RoomAreaObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("RoomArea.deleteAreaObj", obj);
			if(o!=null){
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("RoomArea.deleteAreaObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		
		return ret;
	}

	@Override
	public int insertAreaObj(RoomAreaObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("RoomArea.insertAreaObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("RoomArea.insertAreaObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	@Override
	public List queryAreaList(RoomAreaObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"RoomArea.queryAreaCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("RoomArea.queryAreaList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("RoomArea.queryAreaList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	@Override
	public RoomAreaObj queryOneArea(RoomAreaObj obj) {
		RoomAreaObj rObj=null;
		try {
			rObj= (RoomAreaObj) getSqlMap().queryForObject("RoomArea.queryOneArea",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("RoomArea.queryOneArea:" 
					+ sqlexception.getMessage() + getClass().getName());
		}
		return rObj;
	}

	@Override
	public int updateAreaObj(RoomAreaObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("RoomArea.updateAreaObj", obj);
			if(o!=null){ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("RoomArea.updateAreaObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

}
