package com.sitech.basd.sxcloud.cloud.dao.leader;

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

public interface LeaderViewBusinessDao {

	/**
	 * 
	 * @Title: 查询所有的区域
	 * @Copyright: Copyright (c) 2011-12-1
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<TbCloud2DomainsInfoObj> queryAllRegionList();

	/**
	 * 
	 * @Title: 查询所有业务系统信息
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<TbCloud2BizsysInfoObj> queryAllBizSysList();

	/**
	 * 
	 * @Title: 查询所有的地区信息
	 * @Copyright: Copyright (c) 2011-12-1
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<TbCloud2DistObj> queryAllDistInfoList();

	/**
	 * 
	 * @Title: 查询所有的机房信息
	 * @Copyright: Copyright (c) 2011-12-5
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<TbCloud2MroomInfoObj> queryAllMroomList();

	/**
	 * 
	 * @Title: 查询机柜列表
	 * @Copyright: Copyright (c) 2011-12-15
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<TbCloud2CubinetInfoObj> queryCubListByRoomId(
			TbCloud2CubinetInfoObj obj);

	/**
	 * 
	 * @Title: 根据业务系统编号查询业务系统部署的虚拟机以及寻虚拟机所在的主机信息
	 * @Copyright: Copyright (c) 2011-12-1
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List queryBizDetialListById(String Id);

	/**
	 * 
	 * @Title: 根据机房编号查询机房信息
	 * @Copyright: Copyright (c) 2011-12-1
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public TbCloud2MroomInfoObj queryMRoomInfoByObj(TbCloud2MroomInfoObj obj);

	/**
	 * 
	 * @Title: 根据机房编号查询机房楼层以及房间信息
	 * @Copyright: Copyright (c) 2011-12-10
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List queryFloorRoomListByMroom(Object obj);

	/**
	 * 
	 * @Title: 根据房间查询机柜列表信息
	 * @Copyright: Copyright (c) 2011-12-13
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List queryCubinetListByRoom(Object obj);

	/**
	 * 
	 * @Title: 根据机柜的编号查询机柜的详细信息
	 * @Copyright: Copyright (c) 2011-12-13
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List queryCubinetDetialsById(String Id);

	/**
	 * 
	 * @Title: 根据机柜的编号查询机柜的中主机列表
	 * @Copyright: Copyright (c) 2011-12-13
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List queryHostListByCubinet(Object obj);

	/**
	 * 
	 * @Title: 根据主机信息查询虚拟机列表
	 * @Copyright: Copyright (c) 2011-12-13
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List queryVmListByHost(Object obj);

	/**
	 * 
	 * @Title: 根据虚拟机编号查询虚拟机详细信息
	 * @Copyright: Copyright (c) 2011-12-13
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List queryVmDetialsById(String Id);

	/**
	 * 
	 * @Title: 根据虚拟机编号查询虚拟机监控信息
	 * @Copyright: Copyright (c) 2011-12-13
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List queryVmMonitoringDetialsById(String Id);

	/**
	 * 
	 * @Title: 查询主机序号
	 * @Copyright: Copyright (c) 2011-12-16
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String queryHostIdByCid(String cid);

	/**
	 * 
	 * @Title: 查询设备所占比
	 * @Copyright: Copyright (c) 2012-2-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<TbCloud2EquipmentObj> queryEquipmentPerctent(
			TbCloud2EquipmentObj obj);

	/**
	 * 
	 * @Title: 获得业务系统关联的机柜
	 * @Copyright: Copyright (c) 2012-2-17
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<String> querySysAssoCabListBySysId(String sysId);

	/**
	 * 
	 * @Title: 根据机柜Id查询机柜中主机列表
	 * @Copyright: Copyright (c) 2012-2-18
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<TbCloud2HostInfoObj> queryHostMachListByCid(String cid);

	/**
	 * 
	 * @Title: 根据机柜Id查询机柜中部署在主机上的虚拟机列表信息
	 * @Copyright: Copyright (c) 2012-2-18
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<TbCloud2VmhostInfoObj> queryVmhostListByCid(String cid);

	/**
	 * 
	 * @Title: 获得机房所在的房间信息
	 * @Copyright: Copyright (c) 2012-3-22
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<TbCloud2RoomInfoObj> queryRoomListByMid(String mid);

	/**
	 * 
	 * @Title: 根据房间号查询云平台管理主机所在的机柜信息
	 * @Copyright: Copyright (c) 2012-3-24
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<TbCloud2HostInfoObj> queryHostCabinetByRid(String roomId);

	/**
	 * 
	 * @Title: 通过机柜编号查询机柜的主机中安装的业务系统
	 * @Copyright: Copyright (c) 2012-3-26
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<HostBusiSysObj> queryHostBusiSysByCid(String cid);

	/**
	 * 
	 * @Title: 查询主机监控
	 * @Copyright: Copyright (c) 2012-3-27
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<MonitoringObj> queryHostMonitoringByObj(MonitoringObj obj);

	/**
	 * 
	 * @Title: 查询虚拟机监控
	 * @Copyright: Copyright (c) 2012-3-27
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<MonitoringObj> queryVhostMonitoringByObj(MonitoringObj obj);

	/**
	 * 
	 * @Title: 查询应用监控
	 * @Copyright: Copyright (c) 2012-3-27
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<MonitoringObj> queryAppMonitoringByObj(MonitoringObj obj);

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
	public int queryHostStatusNum(Map params);
	
	public String queryHpData(String kpiId,String kpiColl);

}
