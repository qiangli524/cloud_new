package com.sitech.basd.sxcloud.cloud.dao.leader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sitech.basd.sxcloud.cloud.domain.leader.HostBusiSysObj;
import com.sitech.basd.sxcloud.cloud.domain.leader.MonitoringObj;
import com.sitech.basd.sxcloud.cloud.domain.leader.TbCloud2BizsysInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.leader.TbCloud2CubinetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.leader.TbCloud2DistObj;
import com.sitech.basd.sxcloud.cloud.domain.leader.TbCloud2DomainsInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.leader.TbCloud2EquipmentObj;
import com.sitech.basd.sxcloud.cloud.domain.leader.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.leader.TbCloud2MroomInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.leader.TbCloud2RoomInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.leader.TbCloud2VmhostInfoObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class LeaderViewBusinessDaoImpl extends BaseDao implements
		LeaderViewBusinessDao {

	/**
	 * 
	 * @Title: 查询所有的区域
	 * @Copyright: Copyright (c) 2011-12-1
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<TbCloud2DomainsInfoObj> queryAllRegionList() {
		List<TbCloud2DomainsInfoObj> lst = null;
		try {
			lst = getSqlMap().queryForList(
					"LeaderViewBusiness.queryAllRegionList");
		} catch (Exception sqlexception) {
			LogHelper.error("LeaderViewBusiness.queryAllRegionList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: 获得所有业务系统信息
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<TbCloud2BizsysInfoObj> queryAllBizSysList() {
		List<TbCloud2BizsysInfoObj> lst = null;
		try {
			lst = getSqlMap().queryForList(
					"LeaderViewBusiness.queryAllBizSysList");
		} catch (Exception sqlexception) {
			LogHelper.error("LeaderViewBusiness.queryAllBizSysList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: 查询所有的地区信息
	 * @Copyright: Copyright (c) 2011-12-1
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<TbCloud2DistObj> queryAllDistInfoList() {
		List<TbCloud2DistObj> lst = null;
		try {
			lst = getSqlMap().queryForList(
					"LeaderViewBusiness.queryAllDistInfoList");
		} catch (Exception sqlexception) {
			LogHelper.error("LeaderViewBusiness.queryAllDistInfoList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: 查询所有的机房信息
	 * @Copyright: Copyright (c) 2011-12-5
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<TbCloud2MroomInfoObj> queryAllMroomList() {
		List<TbCloud2MroomInfoObj> lst = null;
		try {
			lst = getSqlMap().queryForList(
					"LeaderViewBusiness.queryAllMroomList");
		} catch (Exception sqlexception) {
			LogHelper.error("LeaderViewBusiness.queryAllMroomList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: 查询机柜列表
	 * @Copyright: Copyright (c) 2011-12-15
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<TbCloud2CubinetInfoObj> queryCubListByRoomId(
			TbCloud2CubinetInfoObj obj) {
		List<TbCloud2CubinetInfoObj> lst = null;
		try {
			lst = getSqlMap().queryForList(
					"LeaderViewBusiness.queryCubListByRoomId", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("LeaderViewBusiness.queryCubListByRoomId:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: 根据机房编号查询机房信息
	 * @Copyright: Copyright (c) 2011-12-1
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public TbCloud2MroomInfoObj queryMRoomInfoByObj(TbCloud2MroomInfoObj obj) {
		TbCloud2MroomInfoObj ret = null;
		try {
			ret = (TbCloud2MroomInfoObj) getSqlMap().queryForObject(
					"LeaderViewBusiness.queryMRoomInfoByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("LeaderViewBusiness.queryMRoomInfoByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: 根据业务系统编号查询业务系统部署的虚拟机以及寻虚拟机所在的主机信息
	 * @Copyright: Copyright (c) 2011-12-1
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List queryBizDetialListById(String Id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @Title: 根据机柜的编号查询机柜的详细信息
	 * @Copyright: Copyright (c) 2011-12-13
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List queryCubinetDetialsById(String Id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @Title: 查询符合房间号的机柜列表信息
	 * @Copyright: Copyright (c) 2011-12-13
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List queryCubinetListByRoom(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @Title: 查询符合条件的楼层以及房间信息
	 * @Copyright: Copyright (c) 2011-12-10
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List queryFloorRoomListByMroom(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @Title: 根据机柜的编号查询机柜的中主机列表
	 * @Copyright: Copyright (c) 2011-12-13
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List queryHostListByCubinet(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @Title: 根据虚拟机编号查询虚拟机详细信息
	 * @Copyright: Copyright (c) 2011-12-13
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List queryVmDetialsById(String Id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @Title: 根据主机信息查询虚拟机列表
	 * @Copyright: Copyright (c) 2011-12-13
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List queryVmListByHost(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @Title: 根据虚拟机编号查询虚拟机监控信息
	 * @Copyright: Copyright (c) 2011-12-13
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List queryVmMonitoringDetialsById(String Id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @Title: 查询主机序号
	 * @Copyright: Copyright (c) 2011-12-16
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String queryHostIdByCid(String cid) {
		String ret = null;
		try {
			ret = (String) getSqlMap().queryForObject(
					"LeaderViewBusiness.queryHostIdByCid", cid);
		} catch (Exception sqlexception) {
			LogHelper.error("LeaderViewBusiness.queryHostIdByCid:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	/*
	<!-- 查询机房机柜温控数据 -->
	*/
	public String queryHpData(String kpiId,String kpiColl) {
		String ret = null;
		try {
			Map params = new HashMap();
			params.put("kpiId", kpiId);
			params.put("kpiColl", kpiColl);
			ret = (String) getSqlMap().queryForObject(
					"LeaderViewBusiness.queryHpData",params);
		} catch (Exception sqlexception) {
			LogHelper.error("LeaderViewBusiness.queryHpData:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: 查询设备所占比
	 * @Copyright: Copyright (c) 2012-2-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<TbCloud2EquipmentObj> queryEquipmentPerctent(
			TbCloud2EquipmentObj obj) {
		List<TbCloud2EquipmentObj> lst = null;
		try {
			lst = getSqlMap().queryForList(
					"LeaderViewBusiness.queryEquipmentPerctent", obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("LeaderViewBusiness.queryEquipmentPerctent:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: 获得业务系统关联的机柜
	 * @Copyright: Copyright (c) 2012-2-17
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<String> querySysAssoCabListBySysId(String sysId) {
		List<String> ret = null;
		try {
			ret = (List<String>) getSqlMap().queryForList(
					"LeaderViewBusiness.querySysAssoCabListBySysId", sysId);
		} catch (Exception sqlexception) {
			LogHelper.error("LeaderViewBusiness.querySysAssoCabListBySysId:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: 根据机柜Id查询机柜中主机列表
	 * @Copyright: Copyright (c) 2012-2-18
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<TbCloud2HostInfoObj> queryHostMachListByCid(String cid) {
		List<TbCloud2HostInfoObj> lst = null;
		try {
			lst = getSqlMap().queryForList(
					"LeaderViewBusiness.queryHostMachListByCid", cid);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("LeaderViewBusiness.queryHostMachListByCid:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: 根据机柜Id查询机柜中部署在主机上的虚拟机列表信息
	 * @Copyright: Copyright (c) 2012-2-18
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<TbCloud2VmhostInfoObj> queryVmhostListByCid(String cid) {
		List<TbCloud2VmhostInfoObj> lst = null;
		try {
			lst = getSqlMap().queryForList(
					"LeaderViewBusiness.queryVmhostListByCid", cid);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("LeaderViewBusiness.queryVmhostListByCid:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: 获得机房所在的房间信息
	 * @Copyright: Copyright (c) 2012-3-22
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<TbCloud2RoomInfoObj> queryRoomListByMid(String mid) {
		List<TbCloud2RoomInfoObj> lst = null;
		try {
			lst = getSqlMap().queryForList(
					"LeaderViewBusiness.queryRoomListByMid", mid);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("LeaderViewBusiness.queryRoomListByMid:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: 根据房间号查询云平台管理主机所在的机柜信息
	 * @Copyright: Copyright (c) 2012-3-24
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<TbCloud2HostInfoObj> queryHostCabinetByRid(String roomId) {
		List<TbCloud2HostInfoObj> lst = null;
		try {
			lst = getSqlMap().queryForList(
					"LeaderViewBusiness.queryHostCabinetByRid", roomId);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("LeaderViewBusiness.queryHostCabinetByRid:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title:
	 * @Copyright: Copyright (c) 2012-3-26
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<HostBusiSysObj> queryHostBusiSysByCid(String cid) {
		List<HostBusiSysObj> lst = null;
		try {
			lst = getSqlMap().queryForList(
					"LeaderViewBusiness.queryHostBusiSysByCid", cid);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("LeaderViewBusiness.queryHostBusiSysByCid:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: 查询主机监控
	 * @Copyright: Copyright (c) 2012-3-27
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<MonitoringObj> queryHostMonitoringByObj(MonitoringObj obj) {
		List<MonitoringObj> lst = null;
		try {
			lst = getSqlMap().queryForList(
					"LeaderViewBusiness.queryHostMonitoringByObj", obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("LeaderViewBusiness.queryHostMonitoringByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: 查询虚拟机监控
	 * @Copyright: Copyright (c) 2012-3-27
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<MonitoringObj> queryVhostMonitoringByObj(MonitoringObj obj) {
		List<MonitoringObj> lst = null;
		try {
			lst = getSqlMap().queryForList(
					"LeaderViewBusiness.queryVhostMonitoringByObj", obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("LeaderViewBusiness.queryVhostMonitoringByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: 查询应用监控
	 * @Copyright: Copyright (c) 2012-3-27
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<MonitoringObj> queryAppMonitoringByObj(MonitoringObj obj) {
		List<MonitoringObj> lst = null;
		try {
			lst = getSqlMap().queryForList(
					"LeaderViewBusiness.queryAppMonitoringByObj", obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("LeaderViewBusiness.queryAppMonitoringByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryHostStatusNum
	 * @Description: 根据机柜ID查询机柜中主机状态种类
	 * @param params---机柜ID--cq_id
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 26, 2013 9:53:42 AM
	 */
	public int queryHostStatusNum(Map params) {
		int result = 0;
		try {
			Object o = getSqlMap().queryForObject(
					"LeaderViewBusiness.queryHostStatusNum", params);
			if (o != null) {
				result = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("LeaderViewBusiness.queryHostStatusNum:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return result;
	}
	
	public static void main(String[] args) {
		// String input = "192.168.88.231, 10.208.229.25";

		//int gap = 6;
		//Date date = new Date();
		//String ret = new LeaderViewBusinessServiceImpl().queryDateRegion(gap,
	//			date);
		
		String ret = new LeaderViewBusinessDaoImpl().queryHpData("203", "1");
	}
	
}
