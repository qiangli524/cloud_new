package com.sitech.basd.sxcloud.cloud.service.workorder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.cloud3.domain.departproject.DepartProjectObj;
import com.sitech.basd.resource.service.united.UnitedVMService;
import com.sitech.basd.sxcloud.cloud.dao.net.TbIpDao;
import com.sitech.basd.sxcloud.cloud.dao.net.TbNetDao;
import com.sitech.basd.sxcloud.cloud.dao.workorder.WorkOrderDao;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloudEntityUser;
import com.sitech.basd.sxcloud.cloud.domain.workorder.WorkOrderObj;

/**
 * 接收bocmc同步资源的工单，进行页面的展示
 * 
 * @author lipengpeng
 * 
 */
@Service("workOrderService")
public class WorkOrderServiceImpl implements WorkOrderService {

	@Autowired
	private WorkOrderDao workOrderDao;
	@Autowired
	private UnitedVMService unitedVMService;
	@Autowired
	private TbNetDao tbNetDao;
	@Autowired
	private TbIpDao tbIpDao;

	@Override
	public List<WorkOrderObj> queryByObj(WorkOrderObj workOrderObj) {
		return workOrderDao.queryByObj(workOrderObj);
	}

	@Override
	public List<WorkOrderObj> queryResourceList(WorkOrderObj workOrderObj) {
		return workOrderDao.queryResourceList(workOrderObj);
	}

	/**
	 * @Title: queryForWholeListByObj
	 * @Description: 查询工单和资源的合体
	 * @param
	 * @return List<WorkOrderObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-23 上午11:54:21
	 */
	@Override
	public List<WorkOrderObj> queryForWholeListByObj(WorkOrderObj workOrderObj) {
		return workOrderDao.queryForWholeListByObj(workOrderObj);
	}

	/**
	 * @Title: updateByObj
	 * @Description: 更新一条记录
	 * @param
	 * @return
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-23 下午2:55:46
	 */
	@Override
	public int updateByObj(WorkOrderObj obj) {
		return workOrderDao.updateByObj(obj);
	}

	/**
	 * @Title: updateWorkOrderTable
	 * @Description: 更新工单表
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-23 下午3:04:30
	 */
	@Override
	public int updateWorkOrderTable(WorkOrderObj obj) {
		return workOrderDao.updateWorkOrderTable(obj);
	}

	/**
	 * @Title: queryProjectList
	 * @Description: 查询项目集合
	 * @param
	 * @return List<DepartProjectObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-29 上午10:40:10
	 */
	@Override
	public List<DepartProjectObj> queryProjectList() {
		return workOrderDao.queryProjectList();
	}

	/**
	 * @Title: deleteResourceByObj
	 * @Description: 删除工单下的任务
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-7 下午12:19:59
	 */
	@Override
	public int deleteResourceByObj(WorkOrderObj workOrderObj) {
		// TODO Auto-generated method stub
		return workOrderDao.deleteResourceByObj(workOrderObj);
	}

	/**
	 * @Title: queryByProject
	 * @Description: 查询项目资源使用量
	 * @param
	 * @return List<WorkOrderObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-7 下午8:59:52
	 */
	@Override
	public List<WorkOrderObj> queryUsedByProject(String project_ID) {
		// TODO Auto-generated method stub
		return workOrderDao.queryUsedByProject(project_ID);
	}

	/**
	 * @Title: insertWorkOrderTable
	 * @Description: 插入工单表
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-9 上午8:44:30
	 */
	@Override
	public int insertWorkOrderTable(WorkOrderObj obj) {
		// TODO Auto-generated method stub
		return workOrderDao.insertWorkOrderTable(obj);
	}

	/**
	 * @Title: insertResource
	 * @Description: 插入资源表
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-9 上午9:07:47
	 */
	@Override
	public int insertResource(WorkOrderObj workOrderObj) {
		return workOrderDao.insertResource(workOrderObj);
	}

	/**
	 * @Title: queryByProject
	 * @Description: 查询已经创建成功的资源占用量
	 * @param
	 * @return List<WorkOrderObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-11 下午7:24:21
	 */
	@Override
	public List<WorkOrderObj> queryByProject(String projectid) {
		// TODO Auto-generated method stub
		return workOrderDao.queryByProject(projectid);
	}

	/**
	 * @Title: deleteWorkOrderByObj
	 * @Description: 删除工单记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-12 下午8:04:25
	 */
	@Override
	public int deleteWorkOrderByObj(WorkOrderObj workOrderObj) {
		return workOrderDao.deleteWorkOrderByObj(workOrderObj);
	}

	/**
	 * @Title: queryUnDealedResource
	 * @Description: 查询处理状态不是成功的资源
	 * @param
	 * @return List<WorkOrderObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-10-16 下午2:28:11
	 */
	@Override
	public List<WorkOrderObj> queryUnDealedResource(WorkOrderObj workOrderObj) {
		return workOrderDao.queryUnDealedResource(workOrderObj);
	}

	/**
	 * @Title: queruForHostInfo
	 * @Description: 查询指定主机的相关信息
	 * @param
	 * @return WorkOrderObj
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-19 下午3:05:46
	 */
	@Override
	public WorkOrderObj queruForHostInfo(WorkOrderObj wobj) {
		return workOrderDao.queruForHostInfo(wobj);
	}

	/**
	 * @Title: queryUsedByWorkorder
	 * @Description: 查询工单中已使用
	 * @param
	 * @return List<WorkOrderObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-23 上午11:53:58
	 */
	@Override
	public List<WorkOrderObj> queryUsedByWorkorder(WorkOrderObj wobj) {
		return workOrderDao.queryUsedByWorkorder(wobj);
	}

	/**
	 * 
	 * @Title: lockIpStats
	 * @Description:根据工单传递IP进行IP锁定，更新IP使用状态
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-6-28 上午11:31:30
	 */
	public WorkOrderObj lockIpStats(WorkOrderObj workOrderObj, HttpServletRequest request)
			throws Exception {
		String ipsresult = "";
		String netWay = request.getParameter("netWay");
		String[] netWays = filterStringSplit(netWay);
		String net_id = request.getParameter("net_id");
		String ips = request.getParameter("ips");
		String[] ipArray = filterStringSplit(ips);

		String[] net_ids = filterStringSplit(net_id);

		for (int i = 0; i < net_ids.length; i++) {
			String ip = null;
			String ip_if_auto = netWays[i];
			if ("1".equals(ip_if_auto)) {// 手动配置IP
				ip = ipArray[i];
				TbCloud2IpInfoObj ipObj = new TbCloud2IpInfoObj();
				ipObj.setIPADDRESS(ip);
				ipObj.setISUSED("1");
				tbIpDao.updateIPByObj(ipObj);
			} else {
				ip = getAutoIpAddress(net_ids[i]);
			}
			ipsresult += ip + ",";
		}
		if (ipsresult.indexOf(",") != -1) {
			ipsresult = ipsresult.substring(0, ipsresult.lastIndexOf(","));
		}
		if (net_id.indexOf(",") != -1) {
			net_id = net_id.substring(0, net_id.lastIndexOf(","));
		}
		workOrderObj.setIPADDRESS(ipsresult);
		workOrderObj.setNETWORK_ID(net_id);
		return workOrderObj;
	}

	/**
	 * 
	 * @Title: filterStringSplit
	 * @Description: 处理界面传递多网卡数据字符串
	 * @param
	 * @return String[]
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-20 下午3:36:44
	 */
	private String[] filterStringSplit(String param) {
		if (param.indexOf(",") != -1) {
			param = param.substring(0, param.lastIndexOf(","));
		}
		return param.split(",");
	}

	/**
	 * 
	 * @Title: getAutoIpAddress
	 * @Description:获取自动获取IP
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-28 上午11:43:57
	 */
	private synchronized String getAutoIpAddress(String network_id) throws Exception {
		String ip = null;
		TbCloud2NetInfoObj netObj = new TbCloud2NetInfoObj();
		netObj.setNET_ID(network_id);
		@SuppressWarnings("unchecked")
		List<TbCloud2NetInfoObj> netList = tbNetDao.queryByNetObjForList(netObj);
		if (netList.size() > 0) {
			netObj = netList.get(0);
		} else {
			throw new Exception("没有合适的网络");
		}

		if (netObj != null) {
			TbCloud2IpInfoObj ipobj = new TbCloud2IpInfoObj();
			ipobj.setNET_ID(netObj.getNET_ID());
			ipobj.setISUSED("0");
			List<TbCloud2IpInfoObj> iplist = tbIpDao.queryForListByObj(ipobj);
			if (iplist != null && iplist.size() > 0) {
				TbCloud2IpInfoObj ipObj = iplist.get(0);
				ip = ipObj.getIPADDRESS();
				// 将ip的使用状态标记为已使用,锁定ip，避免多线程时选取同一个ip
				ipObj.setISUSED("1");
				tbIpDao.updateIPByObj(ipObj);
			} else {
				throw new Exception("没有剩余的ip");
			}
		} else {
			throw new Exception("没有合适的网络域");
		}
		return ip;
	}

	@Override
	public List<TbCloud2HostInfoObj> queryHostObjByIOS(
			String ios) {
		return workOrderDao.queryHostObjByIOS(ios);
	}

	@Override
	public void insertTbCloudEntityUser(TbCloudEntityUser entityObj) {
		// TODO Auto-generated method stub
		workOrderDao.insertTbCloudEntityUser(entityObj);
	}
}
