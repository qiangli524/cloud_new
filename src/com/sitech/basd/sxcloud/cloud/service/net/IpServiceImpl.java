package com.sitech.basd.sxcloud.cloud.service.net;

import java.util.List;
import java.util.Map;

import com.sitech.basd.rest.network.domain.IpInfo;
import com.sitech.basd.sxcloud.cloud.dao.alarm.TbCloud2MonitorAlarmDao;
import com.sitech.basd.sxcloud.cloud.dao.net.TbIpDao;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.AlarmHostStatistics;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class IpServiceImpl extends BaseService implements IpService {
	/**
	 * @Title:删除已有IP
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int deleteByObj(TbCloud2IpInfoObj obj) {

		// boolean flag = NetWorkOperation.deleteNetworkConfIP(obj.getNET_ID(),
		// obj.getIPADDRESS());

		// if (flag == true) {
		return tbIpDao.deleteByObj(obj);
		// } else {
		// return 0;
		// }
	}

	/**
	 * @Title:查询已有IP列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public List<TbCloud2IpInfoObj> queryIPForList(TbCloud2IpInfoObj obj) {
		return tbIpDao.queryIPForList(obj);
	}

	/**
	 * @Title:创建IP
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int insertByObj(TbCloud2IpInfoObj obj) {
		// List<IpInfo> iList = null;
		// try {
		// iList = NetWorkOperation.addIpAddresses(obj.getNET_ID(), obj
		// .getIPADDRESS());
		// } catch (Exception e) {
		// LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
		// /** 插入告警 */
		// tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
		// }
		// List<TbCloud2IpInfoObj> list = new ArrayList<TbCloud2IpInfoObj>();
		// if (iList != null) {
		// for (IpInfo i : iList) {
		// TbCloud2IpInfoObj tObj = new TbCloud2IpInfoObj();
		// tObj.setIPADDRESS(i.getIpAddress());
		// list.add(tObj);
		// }
		//
		// }
		// if (list != null) {
		return tbIpDao.insertByObj(obj);
		// } else {
		// return 0;
		// }

	}

	/**
	 * @Title:创建多个IP
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	@SuppressWarnings("unused")
	public int insertManyIpByObj(TbCloud2IpInfoObj obj) {
		List<IpInfo> iList = null;
		String start_ip = obj.getSTARTIPADDRESS();
		String end_ip = obj.getENDIPADDRESS();
		String[] start = start_ip.split("\\.");
		String[] end = end_ip.split("\\.");
		String head = start_ip.substring(0, start_ip.lastIndexOf(".") + 1);
		int i = Integer.parseInt(start[3]);
		int j = Integer.parseInt(end[3]);
		for (; i <= j; i++) {
			String ip = head + String.valueOf(i);
			obj.setIPADDRESS(ip);
			tbIpDao.insertByObj(obj);
		}
		return 1;
	}

	/**
	 * @Title:查询并返回一个IP对象
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public TbCloud2IpInfoObj queryByObj(TbCloud2IpInfoObj obj) {

		boolean flag = false;
		if (!flag) {
			return tbIpDao.queryByObj(obj);
		}
		return tbIpDao.queryByObj(obj);
	}

	/**
	 * @Title:查询已有IP列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List<TbCloud2IpInfoObj> queryForListByObj(TbCloud2IpInfoObj obj) {
		// List<TbCloud2IpInfoObj> list = new ArrayList<TbCloud2IpInfoObj>();
		// List<IpInfo> iList = null;
		// try {
		// iList = NetWorkOperation.getIpAddresses(obj.getNET_ID());
		// } catch (Exception e) {
		// LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
		// /** 插入告警 */
		// tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
		// }
		//
		// if (obj.getPagination() != null) {
		// obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
		// obj.setPAGESIZE(obj.getPagination().getPageSize());
		// obj.getPagination().setTotalCount(iList.size());
		// }
		// int pageSize = 0;
		// if ((obj.getPAGESIZE() + obj.getFIRSTROWNUM()) < iList.size()) {
		// pageSize = obj.getPAGESIZE() + obj.getFIRSTROWNUM();
		//
		// } else {
		// pageSize = iList.size();
		// }
		// if (iList != null && iList.size() > 0) {
		// List<IpInfo> nList = iList.subList(obj.getFIRSTROWNUM(), pageSize);
		// for (IpInfo i : nList) {
		// TbCloud2IpInfoObj tObj = new TbCloud2IpInfoObj();
		// tObj.setIPADDRESS(i.getIpAddress());
		// if (Boolean.parseBoolean(i.getIsUsed())) {
		// tObj.setISUSED("1");
		// } else {
		// tObj.setISUSED("0");
		// }
		// if (Boolean.parseBoolean(i.getIsBlocked())) {
		// tObj.setISBLOCKED("1");
		// } else {
		// tObj.setISBLOCKED("0");
		// }
		// list.add(tObj);
		// }
		// }

		// if (list != null) {
		// return list;
		// } else {
		List lst = tbIpDao.queryForListByObj(obj);
		return lst;
		// }
	}

	/**
	 * 
	 * @Title: sortIp
	 * @Description: 对IP地址进行排序
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-3 下午5:46:13
	 */
	private void sortIp(List ipLst) {
		for (int i = 0; i < ipLst.size(); i++) {

		}
	}

	/**
	 * @Title:更新IP信息
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int updateByObj(TbCloud2IpInfoObj obj) {

		boolean flag = false;
		if (!flag) {
			return tbIpDao.updateByObj(obj);
		}
		return tbIpDao.updateByObj(obj);
	}

	/**
	 * @Title:查询NET表作为IP的下拉列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	@SuppressWarnings("rawtypes")
	public List queryForListByNetObj(TbCloud2NetInfoObj obj) {
		boolean flag = false;
		{
			// 默认从接口获取数据
			// 获取数据成功，则flag设为true
		}
		if (!flag) {
			return tbIpDao.queryForListByNetObj(obj);
		}
		return tbIpDao.queryForListByNetObj(obj);
	}

	/**
	 * @Title:阻塞已有IP
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int backupByObj(TbCloud2IpInfoObj obj) {
		// boolean flag = NetWorkOperation.blockNetworkConfIP(obj.getNET_ID(),
		// obj
		// .getIPADDRESS());
		// if (flag == true) {
		return tbIpDao.backupByObj(obj);
		// } else {
		// return 0;
		// }
	}

	/**
	 * @Title:发部已有IP
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int issuanceByObj(TbCloud2IpInfoObj obj) {
		//
		// boolean flag = NetWorkOperation.unBlockNetworkConfIP(obj.getNET_ID(),
		// obj.getIPADDRESS());
		//
		// if (flag == true) {
		return tbIpDao.issuanceByObj(obj);
		// } else {
		// return 0;
		// }

	}

	/**
	 * 
	 * @Title: updateIPStat
	 * @Description: 更新IP状态
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2012-9-3
	 */

	public int updateIPStat(TbCloud2IpInfoObj obj) {
		return tbIpDao.updateIPStat(obj);
	}

	/**
	 * 
	 * @Title: queryForListByIPObj
	 * @Description: 根据条件查询ip
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 27, 2013 4:25:20 PM
	 */
	@SuppressWarnings("rawtypes")
	public List queryForListByIPObj(TbCloud2IpInfoObj obj) {
		return tbIpDao.queryForListByIPObj(obj);
	}

	private TbIpDao tbIpDao;
	@SuppressWarnings("unused")
	private TbCloud2MonitorAlarmDao tbCloud2MonitorAlarmDao;

	public void setTbCloud2MonitorAlarmDao(TbCloud2MonitorAlarmDao tbCloud2MonitorAlarmDao) {
		this.tbCloud2MonitorAlarmDao = tbCloud2MonitorAlarmDao;
	}

	public void setTbIpDao(TbIpDao tbIpDao) {
		this.tbIpDao = tbIpDao;
	}

	/**
	 * 查询所有可用的ip，包括未用的和非阻塞的
	 * 
	 * @author lipp
	 * */
	@Override
	public List<TbCloud2IpInfoObj> queryAchivableIpList(TbCloud2IpInfoObj obj) {
		// TODO Auto-generated method stub
		return tbIpDao.queryAchivableIpList(obj);
	}

	@Override
	public String findIpAddressById(String id) {
		// TODO Auto-generated method stub
		return tbIpDao.findIpAddressById(id);
	}

	@Override
	public String findIdByIpAddress(String ip) {
		// TODO Auto-generated method stub
		return tbIpDao.findIdByIpAddress(ip);
	}

	@Override
	public int updateIPByObj(TbCloud2IpInfoObj tbCloud2IpInfoObj) {
		// TODO Auto-generated method stub
		return tbIpDao.updateIPByObj(tbCloud2IpInfoObj);
	}

	/**
	 * @Title: queryByIpAddress
	 * @Description: 根据ip地址选择ip
	 * @param
	 * @return List<TbCloud2IpInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-7 下午5:49:36
	 */
	@Override
	public List<TbCloud2IpInfoObj> queryByIpAddress(TbCloud2IpInfoObj ipInfoObj) {
		return tbIpDao.queryByIpAddress(ipInfoObj);
	}

	/**
	 * @Title: queryForListUseNetIn
	 * @Description: 根据vla_id查询集合
	 * @param
	 * @return List<TbCloud2IpInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-13 上午8:24:02
	 */
	@Override
	public List<TbCloud2IpInfoObj> queryForListUseNetIn(
			TbCloud2IpInfoObj ipInfoObj) {
		return tbIpDao.queryForListUseNetIn(ipInfoObj);
	}

	/**
	 * @Title: queryIpUsageByVlanId
	 * @Description: 查询vlan下每个ip的利用率
	 * @param
	 * @return List<AlarmHostStatistics>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-14 上午9:48:42
	 */
	@Override
	public List<AlarmHostStatistics> queryIpUsageByVlanId(
			TbCloud2IpInfoObj ipInfoObj) {
		return tbIpDao.queryIpUsageByVlanId(ipInfoObj);
	}
	
	/**
	 * @Title: queryIpForTree
	 * @Description: 根据net_id查询对应的ip、虚拟机、业务系统
	 * @param
	 * @return List<Map<String, String>>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-10-29  下午16:21
	 */
	public List<Map<String, String>> queryIpForTree(TbCloud2IpInfoObj ipInfoObj){
		return tbIpDao.queryIpForTree(ipInfoObj);
	}

	/** (非 Javadoc) 
	* <p>Title: getARandomIp</p> 
	* <p>Description:根据条件查询随机查询出一条记录 </p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param ipInfoObj
	* @return 
	* @see com.sitech.basd.sxcloud.cloud.service.net.IpService#getARandomIp(com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj) 
	*/
	@Override
	public TbCloud2IpInfoObj getARandomIp(TbCloud2IpInfoObj ipInfoObj) {
		return tbIpDao.queryARandomIp(ipInfoObj);
	}

}
