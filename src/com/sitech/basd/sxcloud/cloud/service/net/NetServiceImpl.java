package com.sitech.basd.sxcloud.cloud.service.net;

import java.util.ArrayList;
import java.util.List;

import com.sitech.basd.rest.network.domain.NetWorkConfigInfo;
import com.sitech.basd.rest.network.operation.NetWorkOperation;
import com.sitech.basd.sxcloud.cloud.dao.alarm.TbCloud2MonitorAlarmDao;
import com.sitech.basd.sxcloud.cloud.dao.net.TbNetDao;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@SuppressWarnings("all")
public class NetServiceImpl extends BaseService implements NetService {
	/**
	 * @Title:删除已有网络
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int deleteByObj(TbCloud2NetInfoObj obj) {

		// boolean flag = NetWorkOperation.deleteNetworkConfs(obj.getNET_ID());
		// if (flag == true) {
		return tbNetDao.deleteByObj(obj);
		// } else {
		// return 0;
		// }
	}

	/**
	 * @Title:创建网络
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int insertByObj(TbCloud2NetInfoObj obj) {
		// NetWorkInfo info = new NetWorkInfo();
		// info.setDescription(obj.getDESCRIPTION());
		// info.setName(obj.getNAME());
		// info.setUseDHCP(obj.getUSEDHCP());
		// info.setSubnet(obj.getSUBNET());
		// info.setGateway1(obj.getGATEWAY1());
		// info.setGateway2(obj.getGATEWAY2());
		// info.setDns1(obj.getDNS1());
		// info.setDns2(obj.getDNS2());
		// info.setDomainSuffixes(obj.getDOMAINSUFFIXES());
		// info.setDomain(obj.getDOMAIN());
		// info.setHostnamePrefix(obj.getHOSTNAMEPREFIX());
		// info.setComputerNamePrefix(obj.getCOMPUTERNAMEPREFIX());
		// info.setWorkgroup(obj.getWORKGROUP());
		// info.setWins1(obj.getWINS1());
		// info.setWins2(obj.getWINS2());
		// NetWorkInfo tInfo = null;
		// try {
		// tInfo = NetWorkOperation.addNetworkConfs(info);
		// obj.setNET_ID(tInfo.getId());
		// } catch (Exception e) {
		// LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
		// /** 插入告警 */
		// tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
		// }
		// if (tInfo == null) {
		// // 默认从接口获取数据
		// // 获取数据成功，则flag设为true
		// return 0;
		// } else {
		return tbNetDao.insertByObj(obj);

		// }
	}

	/**
	 * @Title:查询并返回一个网络对象
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public TbCloud2NetInfoObj queryByObj(TbCloud2NetInfoObj obj) {
		// NetWorkInfo info = new NetWorkInfo();
		// info.setId(obj.getNET_ID());
		// NetWorkInfo nInfo = null;
		// try {
		// nInfo = NetWorkOperation.getNetWork(info);
		// } catch (Exception e) {
		// LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
		// /** 插入告警 */
		// tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
		// }
		// if (nInfo != null) {
		// TbCloud2NetInfoObj tObj = new TbCloud2NetInfoObj();
		// tObj.setNET_ID(nInfo.getId());
		// tObj.setNAME(nInfo.getName());
		// tObj.setUSEDHCP(nInfo.getUseDHCP());
		// tObj.setDESCRIPTION(nInfo.getDescription());
		// tObj.setSUBNET(nInfo.getSubnet());
		// tObj.setGATEWAY1(nInfo.getGateway1());
		// tObj.setGATEWAY2(nInfo.getGateway2());
		// tObj.setDNS1(nInfo.getDns1());
		// tObj.setDNS2(nInfo.getDns2());
		// tObj.setGATEWAY2(nInfo.getDns2());
		// tObj.setDOMAINSUFFIXES(nInfo.getDomainSuffixes());
		// tObj.setDOMAIN(nInfo.getDomain());
		// tObj.setHOSTNAMEPREFIX(nInfo.getHostnamePrefix());
		// tObj.setCOMPUTERNAMEPREFIX(nInfo.getComputerNamePrefix());
		// tObj.setWORKGROUP(nInfo.getWorkgroup());
		// tObj.setWINS1(nInfo.getWins1());
		// tObj.setWINS2(nInfo.getWins2());
		// if (tObj != null) {
		// return tObj;
		// } else {
		return tbNetDao.queryByObj(obj);
		// }
		// } else {
		// return null;
		// }
	}

	/**
	 * @Title:查询已有网络列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public List queryForListByObj(TbCloud2NetInfoObj obj) {
		List<NetWorkConfigInfo> nList = null;
		// try {
		// nList = NetWorkOperation.getAllNetWork();
		// } catch (Exception e) {
		// LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
		// /** 插入告警 */
		// tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
		// return null;
		// }
		List<TbCloud2NetInfoObj> list = new ArrayList<TbCloud2NetInfoObj>();
		// if (nList != null) {
		// if (obj.getPagination() != null) {
		// obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
		// obj.setPAGESIZE(obj.getPagination().getPageSize());
		// obj.getPagination().setTotalCount(nList.size());
		// }
		// int pageSize = 0;
		// if ((obj.getPAGESIZE() + obj.getFIRSTROWNUM()) < nList.size()) {
		// pageSize = obj.getPAGESIZE() + obj.getFIRSTROWNUM();
		// } else {
		// pageSize = nList.size();
		// }
		// List<NetWorkConfigInfo> nnList = nList.subList(obj.getFIRSTROWNUM(),
		// pageSize);
		// for (NetWorkConfigInfo n : nnList) {
		// TbCloud2NetInfoObj tObj = new TbCloud2NetInfoObj();
		// tObj.setNET_ID(n.getId());
		// tObj.setNAME(n.getName());
		// tObj.setDESCRIPTION(n.getDescription());
		// tObj.setFREECOUNT(String.valueOf(n.getFreeCount()));
		// tObj.setUSEDCOUNT(String.valueOf(n.getUsedCount()));
		// list.add(tObj);
		// }
		// } else {
		list = tbNetDao.queryForListByObj(obj);
		// }
		return list;

	}

	/**
	 * @Title:更新网络信息
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int updateByObj(TbCloud2NetInfoObj obj) {
		// NetWorkInfo info = new NetWorkInfo();
		// info.setId(obj.getNET_ID());
		// info.setDescription(obj.getDESCRIPTION());
		// info.setName(obj.getNAME());
		// info.setUseDHCP(obj.getUSEDHCP());
		// info.setSubnet(obj.getSUBNET());
		// info.setGateway1(obj.getGATEWAY1());
		// info.setGateway2(obj.getGATEWAY2());
		// info.setDns1(obj.getDNS1());
		// info.setDns2(obj.getDNS2());
		// info.setDomainSuffixes(obj.getDOMAINSUFFIXES());
		// info.setDomain(obj.getDOMAIN());
		// info.setHostnamePrefix(obj.getHOSTNAMEPREFIX());
		// info.setComputerNamePrefix(obj.getCOMPUTERNAMEPREFIX());
		// info.setWorkgroup(obj.getWORKGROUP());
		// info.setWins1(obj.getWINS1());
		// info.setWins2(obj.getWINS2());
		// boolean flag = false;
		// try {
		// flag = NetWorkOperation.updateNetWork(info);
		// } catch (Exception e) {
		// LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
		// /** 插入告警 */
		// tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
		// }
		// if (flag == false) {
		// return -1;
		// } else {
		return tbNetDao.updateByObj(obj);
		// }
	}

	/**
	 * @Title:为下拉列表查询NET表
	 * @Copyright: Copyright (c) 20120104
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public List queryByNetObjForList(TbCloud2NetInfoObj obj) {
		List<NetWorkConfigInfo> nList = null;
		try {
			nList = NetWorkOperation.getAllNetWork();
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
		}
		if (nList == null) {
			return tbNetDao.queryByNetObjForList(obj);
		} else {
			List list = new ArrayList();
			for (NetWorkConfigInfo info : nList) {
				TbCloud2NetInfoObj tObj = new TbCloud2NetInfoObj();
				tObj.setNAME(info.getName());
				tObj.setNET_ID(info.getId());
				list.add(tObj);
			}
			return list;
		}
	}
	
	/**
	 * 
	 * @Title: queryNetListByObj
	 * @Description: 查询net列表
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 17, 2013 6:17:58 PM
	 */
	public List queryNetListByObj(TbCloud2NetInfoObj obj){
		return tbNetDao.queryByNetObjForList(obj);
	}

	/**
	 * 
	 * @Title: getNetResource
	 * @Description: 获取网络资源信息
	 * @param
	 * @return Map
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Feb 21, 2013 2:00:58 PM
	 */
	
	public List getNetResource() {
		return tbNetDao.getNetResource();
	}
	/**
	 * @Title:创建网络
	 * @Copyright: Copyright (c) 2013-09-12
	 * @Company: si-tech
	 * @author yanggl
	 * @version 1.0
	 */
	public String insertNet(TbCloud2NetInfoObj obj){
		return tbNetDao.insertNet(obj);
	}
	private TbNetDao tbNetDao;
	private TbCloud2MonitorAlarmDao tbCloud2MonitorAlarmDao;

	public void setTbCloud2MonitorAlarmDao(
			TbCloud2MonitorAlarmDao tbCloud2MonitorAlarmDao) {
		this.tbCloud2MonitorAlarmDao = tbCloud2MonitorAlarmDao;
	}

	public void setTbNetDao(TbNetDao tbNetDao) {
		this.tbNetDao = tbNetDao;
	}

	/**
	 * @Title: queryForListByDomainId
	 * @Description: 通过网络域id查询vlan信息
	 * @param
	 * @return List<TbCloud2NetInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-16 上午9:52:38
	 */
	@Override
	public List<TbCloud2NetInfoObj> queryForListByDomainId(String domainid) {
		// TODO Auto-generated method stub
		return tbNetDao.queryForListByDomainId(domainid);
	}
}
