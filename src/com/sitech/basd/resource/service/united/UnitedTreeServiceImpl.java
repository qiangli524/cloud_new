package com.sitech.basd.resource.service.united;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.resource.dao.united.UnitedTreeDao;
import com.sitech.basd.resource.domain.united.UnitedTree;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.util.VirtualClient;
import com.sitech.basd.sxcloud.cloud.dao.resource.HostInfoDao;
import com.sitech.basd.util.PropertyUtil;
import com.sitech.basd.yicloud.web.busisystree.form.BusiSysTree;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.vo.united.ClusterUnitedVO;
import com.sitech.vo.united.HostUnitedVO;
import com.sitech.vo.united.VirtualMachineConnectStatus;
import com.sitech.vo.united.VirtualMachinePowerStateUnitedVO;
import com.sitech.vo.united.VirtualMachinePowerStatus;
import com.sitech.vo.util.UnitedConstant;
import com.sitech.vo.util.VirtualConstant;

@Service("unitedTreeService")
public class UnitedTreeServiceImpl implements UnitedTreeService {
	@Autowired
	private UnitedTreeDao unitedTreeDao;
	@Autowired
	private HostInfoDao hostInfoDao;
	@Autowired
	private PropertyUtil unitedTreeIconProp;
	// 虚拟机电源状态-key-hostCode,Map<string,String> key:vmCode value:vmStatus
	private Map<String, Map<String, String>> vmPowerState = new HashMap<String, Map<String, String>>();
	// 主机状态 Map<string,String> key:hostCode value:hostStatus
	private Map<String, String> hostState = new HashMap<String, String>();

	/**
	 * 
	 * @Title: initUnitedTree
	 * @Description: 获取统一树
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws HttpClientException
	 * @throws SQLException
	 * @createtime Jul 17, 2013 4:42:32 PM
	 */
	public List queryForUnitedTree(HttpServletRequest request) throws HttpClientException,
			SQLException {
		List<UnitedTree> list = new ArrayList<UnitedTree>();
		UnitedTreeObj tempObj = new UnitedTreeObj();
		UnitedTreeObj obj = new UnitedTreeObj();
		String id = request.getParameter("id");
		String uuid = request.getParameter("uuid");
		String connect_id = request.getParameter("connect_id");
		String node_type = request.getParameter("type");
		String vtype = request.getParameter("vtype");
		String name = request.getParameter("name");
		// 获取用户ID和主机、虚拟机权限配置标识
		String flag = request.getParameter("flag");
		if (id == null || "".equals(id)) {
			obj.setType(UnitedConstant.ROOT);
		} else {
			obj.setParent_id(id);
			obj.setFlag(flag);
		}
		if (name != null && !"".equals(name)) {
			obj.setName(name);
		}
		if (vtype != null) {
			// 实例宿主机下虚拟机电源状态
			if (UnitedConstant.VMWARE.equals(vtype)) {
				if (UnitedConstant.HOST.equals(node_type)) {
					initVMPowerStateMap(vtype, connect_id, uuid, id);
				} else if (UnitedConstant.CLUSTER.equals(node_type)) {
					getHostPowerState(vtype, connect_id, uuid);
				}
			} else if (UnitedConstant.XEN.equals(vtype)) {
				initVMPowerStateMap(vtype, connect_id, uuid, id);
			}
		}
		List<UnitedTreeObj> resultList = unitedTreeDao.queryForUnitedTree(obj);
		for (UnitedTreeObj u : resultList) {
			UnitedTree tObj = new UnitedTree();
			tObj.setId(u.getId());
			tObj.setName(u.getName());
			tObj.setType(u.getType());
			tObj.setVtype(u.getVtype());
			tObj.setUuid(u.getUuid());
			if (u.getConnect_id() != null && !"".equals(u.getConnect_id())) {// /某些节点的connect_id是null,故此处需要判断
				tObj.setConnect_id(u.getConnect_id());
			}

			// 判断是不是父节点
			tempObj.setParent_id(u.getId());
			List<UnitedTreeObj> lst = unitedTreeDao.queryForUnitedTree(tempObj);
			if (lst == null || lst.size() == 0) {
				tObj.setIsParent(false);
			}
			// 设置图标
			String type = u.getType();
			String vType = u.getVtype();
			if (UnitedConstant.ROOT.equals(type)) {// 安徽移动私有云管理平台名称
				tObj.setIcon(unitedTreeIconProp.getString("anhui"));
			} else if (UnitedConstant.AREA.equals(type)) {
				tObj.setIcon(unitedTreeIconProp.getString("area"));
			} else if (UnitedConstant.DATACENTER.equals(type)) { // 数据中心
				if (UnitedConstant.VMWARE.equals(vType) || UnitedConstant.XEN.equals(vType)) {// vmware及xen数据中心
					tObj.setIcon(unitedTreeIconProp.getString("datacenter"));
				} else if (UnitedConstant.NETWORKS.equals(vType)) {// 网络数据中心
					tObj.setIcon(unitedTreeIconProp.getString("networkCenter"));
				} else if (UnitedConstant.STORAGE.equals(vType)) {// /存储数据中心
					tObj.setIcon(unitedTreeIconProp.getString("storageCenter"));
				} else if (UnitedConstant.UNALLOCATED.equals(vType)) {// 其他两种暂时不确定，后续补充
					tObj.setIcon(unitedTreeIconProp.getString("unallocatedCenter"));
				}

			} else if (UnitedConstant.CLUSTER.equals(type)) { // 集群
				tObj.setIcon(unitedTreeIconProp.getString("cluster"));
			} else if (UnitedConstant.HOST.equals(type)) { // 主机(需要展示主机的链接状态以及电源状态)
				/*
				 * TbCloud2HostInfoObj host = new TbCloud2HostInfoObj();
				 * host.setEq_name(u.getName()); host =
				 * hostInfoDao.queryByObj(host); if (host != null) { String
				 * connectStatus = host.getConnectStatus();// 获取主机的链接状态 String
				 * powerState = host.getSTATUS();// 主机的电源状态 if
				 * (UnitedConstant.connected.equals(connectStatus)) {//
				 * 在主机链接状态下，有开机关机以及进入维护模式三种状态 if ("3".equals(powerState)) {//
				 * 维护模式
				 * tObj.setIcon(unitedTreeIconProp.getString("host_maitain"));
				 * tObj.setState("3"); } else if ("2".equals(powerState)) {// 关机
				 * tObj.setIcon(unitedTreeIconProp.getString("host"));//
				 * 暂时主机开机和关机是一个图标 tObj.setState("2"); } else {// 开机
				 * tObj.setIcon(unitedTreeIconProp.getString("host"));//
				 * 暂时主机开机和关机是一个图标 tObj.setState("1"); }
				 * 
				 * } else if (UnitedConstant.disconnected.equals(connectStatus))
				 * {// 断开链接
				 * tObj.setIcon(unitedTreeIconProp.getString("host_disconnect"
				 * )); tObj.setState("0"); } else {// 未分配资源池中的主机
				 * tObj.setIcon(unitedTreeIconProp.getString("host")); } } else
				 * {// 不在主机表中
				 * tObj.setIcon(unitedTreeIconProp.getString("host_disconnect"
				 * )); }
				 */
				/*
				 * if (hostState.get(u.getUuid()) == null) {
				 * tObj.setIcon(unitedTreeIconProp.getString("delete")); } else
				 * if
				 * (HostSystemConnectStatus.notResponding.equals(hostState.get
				 * (u.getUuid()))) {
				 * tObj.setIcon(unitedTreeIconProp.getString("host_disconnect"
				 * )); tObj.setName(u.getName() + "(无响应)"); } else if
				 * (HostSystemConnectStatus
				 * .connected.equals(hostState.get(u.getUuid()))) {
				 * tObj.setIcon(unitedTreeIconProp.getString("host")); } else {
				 * tObj.setIcon(unitedTreeIconProp.getString("host")); }
				 */
				// 后续添加维护模式
				tObj.setIcon(unitedTreeIconProp.getString("host"));

			} else if (UnitedConstant.VM.equals(type)) {
				String state = vmPowerState.get(uuid).get(u.getUuid());
				String[] status = null;
				if (state != null && !"".equals(state)) {
					status = state.split("@");
				}
				if (status != null && VirtualMachineConnectStatus.connected.equals(status[0])) {
					String powerState = status[1];
					if (VirtualMachinePowerStatus.powerOff.equals(powerState)) {
						tObj.setIcon(unitedTreeIconProp.getString("vm_stop"));
						tObj.setState("1");// 关机状态
					} else if (VirtualMachinePowerStatus.powerOn.equals(powerState)) {
						tObj.setIcon(unitedTreeIconProp.getString("vm_run"));
						tObj.setState("2");// 运行状态
					} else if (VirtualMachinePowerStatus.suspended.equals(powerState)) {
						tObj.setIcon(unitedTreeIconProp.getString("vm_supend"));
						tObj.setState("3");// 挂起状态
					}
				} else {
					tObj.setIcon(unitedTreeIconProp.getString("vm_noexist"));
					tObj.setState("4");// 虚拟机不存在
				}
			} else if (UnitedConstant.DOMAIN.equals(type)) {
				tObj.setIcon(unitedTreeIconProp.getString("domain"));
			} else if (UnitedConstant.SUBDOMIAN.equals(type)) {
				tObj.setIcon(unitedTreeIconProp.getString("subdomain"));
			} else if (UnitedConstant.VLAN.equals(type)) {
				tObj.setIcon(unitedTreeIconProp.getString("vlan"));
			} else if (UnitedConstant.STORAGE_DEVICE.equals(type)) {
				tObj.setIcon(unitedTreeIconProp.getString("storagedevice"));
			}
			list.add(tObj);
		}
		return list;
	}
	
	
	/**
	 * 
	 * @Title: queryForUnitedTreeByWorker
	 * @Description: 获取统一树BY 工单
	 * @param
	 * @return List
	 * @throws
	 * @author chenjl
	 * @version 1.0
	 * @throws HttpClientException
	 * @throws SQLException
	 * @createtime Jul 17, 2013 4:42:32 PM
	 */
	public List queryForUnitedTreeByWorker() throws SQLException {
		List<BusiSysTree> list = new ArrayList<BusiSysTree>();
		UnitedTreeObj tempObj = new UnitedTreeObj();
		UnitedTreeObj obj = new UnitedTreeObj();
		//obj.setType("0");
		List<UnitedTreeObj> resultList = unitedTreeDao.queryForUnitedTree(obj);
		for (UnitedTreeObj u : resultList) {
			BusiSysTree tObj = new BusiSysTree();
			tObj.setId(u.getId());
			tObj.setName(u.getName());
			tObj.setPId(u.getParent_id());
			// tObj.setType(u.getType());
			// tObj.setVtype(u.getVtype());
			// tObj.setUuid(u.getUuid());
			// if (u.getConnect_id() != null && !"".equals(u.getConnect_id()))
			// {// /某些节点的connect_id是null,故此处需要判断
			// tObj.setConnect_id(u.getConnect_id());
			// }

			// 判断是不是父节点
			tempObj.setParent_id(u.getId());
			List<UnitedTreeObj> lst = unitedTreeDao.queryForUnitedTree(tempObj);
			if (lst == null || lst.size() == 0) {
				tObj.setIsParent(false);
			}
			// 设置图标
			String type = u.getType();
			String vType = u.getVtype();
			if (UnitedConstant.ROOT.equals(type)) {// 安徽移动私有云管理平台名称
				tObj.setIcon(unitedTreeIconProp.getString("anhui"));
			} else if (UnitedConstant.AREA.equals(type)) {
				tObj.setIcon(unitedTreeIconProp.getString("area"));
			} else if (UnitedConstant.DATACENTER.equals(type)) { // 数据中心
				if (UnitedConstant.VMWARE.equals(vType)
						|| UnitedConstant.XEN.equals(vType)) {// vmware及xen数据中心
					tObj.setIcon(unitedTreeIconProp.getString("datacenter"));
				} else if (UnitedConstant.NETWORKS.equals(vType)) {// 网络数据中心
					tObj.setIcon(unitedTreeIconProp.getString("networkCenter"));
				} else if (UnitedConstant.STORAGE.equals(vType)) {// /存储数据中心
					tObj.setIcon(unitedTreeIconProp.getString("storageCenter"));
				} else if (UnitedConstant.UNALLOCATED.equals(vType)) {// 其他两种暂时不确定，后续补充
					tObj.setIcon(unitedTreeIconProp
							.getString("unallocatedCenter"));
				} else if (UnitedConstant.UNVIRTUAL.equals(vType)) {
					tObj.setIcon(unitedTreeIconProp.getString("poolcenter"));
				}

			} else if (UnitedConstant.CLUSTER.equals(type)) { // 集群
				tObj.setIcon(unitedTreeIconProp.getString("cluster"));
				tObj.setNocheck(false);
			} else if (UnitedConstant.HOST.equals(type)) { // 主机(需要展示主机的链接状态以及电源状态)
				/*
				 * TbCloud2HostInfoObj host = new TbCloud2HostInfoObj();
				 * host.setEq_name(u.getName()); host =
				 * hostInfoDao.queryByObj(host); if (host != null) { String
				 * connectStatus = host.getConnectStatus();// 获取主机的链接状态 String
				 * powerState = host.getSTATUS();// 主机的电源状态 if
				 * (UnitedConstant.connected.equals(connectStatus)) {//
				 * 在主机链接状态下，有开机关机以及进入维护模式三种状态 if ("3".equals(powerState)) {//
				 * 维护模式
				 * tObj.setIcon(unitedTreeIconProp.getString("host_maitain"));
				 * tObj.setState("3"); } else if ("2".equals(powerState)) {// 关机
				 * tObj.setIcon(unitedTreeIconProp.getString("host"));//
				 * 暂时主机开机和关机是一个图标 tObj.setState("2"); } else {// 开机
				 * tObj.setIcon(unitedTreeIconProp.getString("host"));//
				 * 暂时主机开机和关机是一个图标 tObj.setState("1"); }
				 * 
				 * } else if (UnitedConstant.disconnected.equals(connectStatus))
				 * {// 断开链接
				 * tObj.setIcon(unitedTreeIconProp.getString("host_disconnect"
				 * )); tObj.setState("0"); } else {// 未分配资源池中的主机
				 * tObj.setIcon(unitedTreeIconProp.getString("host")); } } else
				 * {// 不在主机表中
				 * tObj.setIcon(unitedTreeIconProp.getString("host_disconnect"
				 * )); }
				 */
				/*
				 * if (hostState.get(u.getUuid()) == null) {
				 * tObj.setIcon(unitedTreeIconProp.getString("delete")); } else
				 * if
				 * (HostSystemConnectStatus.notResponding.equals(hostState.get
				 * (u.getUuid()))) {
				 * tObj.setIcon(unitedTreeIconProp.getString("host_disconnect"
				 * )); tObj.setName(u.getName() + "(无响应)"); } else if
				 * (HostSystemConnectStatus
				 * .connected.equals(hostState.get(u.getUuid()))) {
				 * tObj.setIcon(unitedTreeIconProp.getString("host")); } else {
				 * tObj.setIcon(unitedTreeIconProp.getString("host")); }
				 */
				// 后续添加维护模式
				tObj.setIcon(unitedTreeIconProp.getString("host"));

			} else if (UnitedConstant.VM.equals(type)) {
				// String state = vmPowerState.get(uuid).get(u.getUuid());
				// String[] status = null;
				// if (state != null && !"".equals(state)) {
				// status = state.split("@");
				// }
				// if (status != null &&
				// VirtualMachineConnectStatus.connected.equals(status[0])) {
				// String powerState = status[1];
				// if (VirtualMachinePowerStatus.powerOff.equals(powerState)) {
				// tObj.setIcon(unitedTreeIconProp.getString("vm_stop"));
				// tObj.setState("1");// 关机状态
				// } else if
				// (VirtualMachinePowerStatus.powerOn.equals(powerState)) {
				// tObj.setIcon(unitedTreeIconProp.getString("vm_run"));
				// tObj.setState("2");// 运行状态
				// } else if
				// (VirtualMachinePowerStatus.suspended.equals(powerState)) {
				// tObj.setIcon(unitedTreeIconProp.getString("vm_supend"));
				// tObj.setState("3");// 挂起状态
				// }
				// } else {
				// tObj.setIcon(unitedTreeIconProp.getString("vm_noexist"));
				// tObj.setState("4");// 虚拟机不存在
				// }
			} else if (UnitedConstant.DOMAIN.equals(type)) {
				tObj.setIcon(unitedTreeIconProp.getString("domain"));
			} else if (UnitedConstant.SUBDOMIAN.equals(type)) {
				tObj.setIcon(unitedTreeIconProp.getString("subdomain"));
			} else if (UnitedConstant.VLAN.equals(type)) {
				tObj.setIcon(unitedTreeIconProp.getString("vlan"));
			} else if (UnitedConstant.STORAGE_DEVICE.equals(type)) {
				tObj.setIcon(unitedTreeIconProp.getString("storagedevice"));
				// } else if(UnitedConstant.PHY_HOST.equals(type)){
			} else if ("10".equals(type)) {
				tObj.setIcon(unitedTreeIconProp.getString("phyhost"));
			}
			list.add(tObj);
		}
		return list;
	}

	/**
	 * 
	 * @Title: initVMPowerStateMap
	 * @Description: 实例宿主机下虚拟机状态Map
	 * @param
	 * @return Map<String,Map<String,String>>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-3 下午3:11:14
	 */
	public void initVMPowerStateMap(String vtype, String connect_id, String hostCode,
			String treeNodeid) {
		try {
			if (UnitedConstant.VMWARE.equals(vtype)) {
				Map<String, String> vmStateMap = getVMPowerState(vtype, connect_id, hostCode);
				if (vmStateMap != null && vmStateMap.size() >= 0) {
					vmPowerState.remove(hostCode);
					vmPowerState.put(hostCode, vmStateMap);
				}
			} else if (UnitedConstant.XEN.equals(vtype)) {
				Map<String, String> vmStateMap = getXenVmPowerState(connect_id, treeNodeid);
				if (vmStateMap != null && vmStateMap.size() >= 0) {
					vmPowerState.remove(hostCode);
					vmPowerState.put(hostCode, vmStateMap);
				}
			}
		} catch (HttpClientException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: getXenVmPowerState
	 * @Description: 获取xen虚拟机电源状态
	 * @param
	 * @return Map<String,String>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws SQLException
	 * @throws HttpClientException
	 * @createtime Aug 1, 2013 8:05:36 PM
	 */
	public Map<String, String> getXenVmPowerState(String connect_id, String id)
			throws SQLException, HttpClientException {
		Map<String, String> powerState = new HashMap<String, String>();
		UnitedTreeObj treeObj = new UnitedTreeObj();
		treeObj.setParent_id(id);
		treeObj.setVtype(UnitedConstant.XEN);
		treeObj.setType(UnitedConstant.VM);
		List<UnitedTreeObj> trees = unitedTreeDao.queryForUnitedTree(treeObj);
		if (trees != null && trees.size() > 0) {
			String vmUuis = "";
			for (UnitedTreeObj unitedTreeObj : trees) {
				vmUuis += unitedTreeObj.getUuid() + ",";
			}
			if (vmUuis.length() > 0) {
				vmUuis = vmUuis.substring(0, vmUuis.length() - 1);
			}
			VirtualMachinePowerStateUnitedVO vo = new VirtualMachinePowerStateUnitedVO();

			String url = "virtualmachine/" + VirtualConstant.VT_XEN + "/vms/powerstate/"
					+ connect_id + "/" + vmUuis;
			try {
				vo = VirtualClient.get(url,
						new JacksonUtil.TypeReference<VirtualMachinePowerStateUnitedVO>() {
						});
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (vo.getIsSuccess()) {
				powerState = vo.getPowerStateMap();
			} else {
				return null;
			}
		} else {
			return null;
		}
		return powerState;
	}

	/**
	 * 
	 * @Title: getVMPowerState
	 * @Description: 获取虚拟机的电源状态
	 * @param
	 * @return Map<String,String>
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws HttpClientException
	 * @createtime Jul 27, 2013 3:47:41 PM
	 */
	public Map<String, String> getVMPowerState(String vtype, String connect_id, String hostCode)
			throws HttpClientException, SQLException {
		Map<String, String> powerState = new HashMap<String, String>();
		VirtualMachinePowerStateUnitedVO vo = new VirtualMachinePowerStateUnitedVO();
		if (UnitedConstant.VMWARE.equals(vtype)) {
			String url = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/powerstate/"
					+ connect_id + "/" + hostCode;
			vo = VirtualClient.get(url,
					new JacksonUtil.TypeReference<VirtualMachinePowerStateUnitedVO>() {
					});
			if (vo.getIsSuccess()) {
				powerState = vo.getPowerStateMap();
			}
		}

		return powerState;
	}

	/**
	 * 
	 * @Title: getHostPowerState
	 * @Description: 获取主机的电源状态
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 29, 2013 9:00:03 AM
	 */
	public void getHostPowerState(String vtype, String connect_id, String clusterCode) {
		String url = "cluster/" + VirtualConstant.VT_VMWARE + "/hostModeMsg/" + connect_id + "/"
				+ clusterCode;
		try {
			ClusterUnitedVO vo = VirtualClient.get(url,
					new JacksonUtil.TypeReference<ClusterUnitedVO>() {
					});
			if (vo.getIsSuccess()) {
				hostState = vo.getHostStates();
			}
		} catch (HttpClientException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: queryForTreeList
	 * @Description: 查询树列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 31, 2013 1:01:44 PM
	 */
	public List queryForTreeList(UnitedTreeObj obj) throws SQLException {
		return unitedTreeDao.queryForUnitedTree(obj);
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Aug 2, 2013 4:35:49 PM
	 */
	public String insertByObj(UnitedTreeObj obj) throws SQLException {
		return unitedTreeDao.insertByObj(obj);
	}

	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询单个节点
	 * @param
	 * @return UnitedTreeObj
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Aug 2, 2013 4:40:43 PM
	 */
	public UnitedTreeObj queryByObj(UnitedTreeObj obj) throws SQLException {
		return unitedTreeDao.queryByObj(obj);
	}

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新一条数据
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 22, 2013 2:26:47 PM
	 */
	public int updateByObj(UnitedTreeObj obj) throws SQLException {
		return unitedTreeDao.updateByObj(obj);
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除一条数据
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 22, 2013 2:27:11 PM
	 */
	public int deleteByObj(UnitedTreeObj obj) throws SQLException {
		return unitedTreeDao.deleteByObj(obj);
	}

	/**
	 * 
	 * @Title: queryForAbstract
	 * @Description: 统一树首页的摘要
	 * @param
	 * @return List<IndexAbstract>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws SQLException
	 * @throws Exception
	 * @createtime 2013-8-2 下午11:13:58
	 */
	public List<Map<String, String>> queryForAbstract() {
		return unitedTreeDao.queryForAbstract();
	}

	/**
	 * @Title: queryForTreeListUseIn
	 * @Description: 查询出满足条件的实体集合
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-14 上午9:48:43
	 */
	@Override
	public List<UnitedTreeObj> queryForTreeListUseIn(UnitedTreeObj unitedTreeObj) {
		return unitedTreeDao.queryForTreeListUseIn(unitedTreeObj);
	}

	/**
	 * @Title: queryByName
	 * @Description: 是否存在相同名字的虚拟机
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author yanggl_bj
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2013-8-15
	 */
	public int queryName(UnitedTreeObj unitedTreeObj) throws SQLException {
		return unitedTreeDao.queryName(unitedTreeObj);
	}

	/**
	 * 
	 * @Title: queryForUnitedTree
	 * @Description: 查询权限树
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 17, 2013 11:51:37 AM
	 */
	public List<UnitedTree> queryForAuthTree(HttpServletRequest request, int userId)
			throws SQLException {
		List<UnitedTree> list = new ArrayList<UnitedTree>();
		UnitedTreeObj tempObj = new UnitedTreeObj();
		UnitedTreeObj obj = new UnitedTreeObj();
		String id = request.getParameter("id");
		String uuid = request.getParameter("uuid");
		String connect_id = request.getParameter("connect_id");
		String node_type = request.getParameter("type");
		String vtype = request.getParameter("vtype");
		String name = request.getParameter("name");
		// 获取用户ID和主机、虚拟机权限配置标识
		String flag = request.getParameter("flag");
		if (id == null || "".equals(id)) {
			obj.setType(UnitedConstant.ROOT);
		} else {
			obj.setParent_id(id);
			obj.setFlag(flag);
		}
		if (name != null && !"".equals(name)) {
			obj.setName(name);
		}
		if (vtype != null) {
			// 实例宿主机下虚拟机电源状态
			if (UnitedConstant.VMWARE.equals(vtype)) {
				if (UnitedConstant.HOST.equals(node_type)) {
					initVMPowerStateMap(vtype, connect_id, uuid, id);
				} else if (UnitedConstant.CLUSTER.equals(node_type)) {
					getHostPowerState(vtype, connect_id, uuid);
				}
			} else if (UnitedConstant.XEN.equals(vtype)) {
				initVMPowerStateMap(vtype, connect_id, uuid, id);
			}
		}
		obj.setUser_id(userId);
		List<UnitedTreeObj> resultList = unitedTreeDao.queryForAuthTree(obj);
		for (UnitedTreeObj u : resultList) {
			UnitedTree tObj = new UnitedTree();
			tObj.setId(u.getId());
			tObj.setName(u.getName());
			tObj.setType(u.getType());
			tObj.setVtype(u.getVtype());
			tObj.setUuid(u.getUuid());
			tObj.setOper(u.getOper());
			if (u.getConnect_id() != null && !"".equals(u.getConnect_id())) {
				tObj.setConnect_id(u.getConnect_id());
			}

			// 判断是不是父节点
			tempObj.setParent_id(u.getId());
			tempObj.setUser_id(userId);
			List<UnitedTreeObj> lst = unitedTreeDao.queryForAuthTree(tempObj);
			if (lst == null || lst.size() == 0) {
				tObj.setIsParent(false);
			}
			// 设置图标
			String type = u.getType();
			if (UnitedConstant.ROOT.equals(type)) {// 安徽移动私有云管理平台名称
				tObj.setIcon(unitedTreeIconProp.getString("anhui"));
			} else if (UnitedConstant.DATACENTER.equals(type)) { // 数据中心
				tObj.setIcon(unitedTreeIconProp.getString("datacenter"));
			} else if (UnitedConstant.CLUSTER.equals(type)) { // 集群
				tObj.setIcon(unitedTreeIconProp.getString("cluster"));
			} else if (UnitedConstant.HOST.equals(type)) { // 主机
				/*
				 * TbCloud2HostInfoObj host = new TbCloud2HostInfoObj();
				 * host.setEq_name(u.getName()); host =
				 * hostInfoDao.queryByObj(host); if (host != null) { String
				 * connectStatus = host.getConnectStatus();// 获取主机的链接状态 String
				 * powerState = host.getSTATUS();// 主机的电源状态 if
				 * (UnitedConstant.connected.equals(connectStatus)) {//
				 * 在主机链接状态下，有开机关机以及进入维护模式三种状态 if ("3".equals(powerState)) {//
				 * 维护模式
				 * tObj.setIcon(unitedTreeIconProp.getString("host_maitain"));
				 * tObj.setState("3"); } else if ("2".equals(powerState)) {// 关机
				 * tObj.setIcon(unitedTreeIconProp.getString("host"));//
				 * 暂时主机开机和关机是一个图标 tObj.setState("2"); } else {// 开机
				 * tObj.setIcon(unitedTreeIconProp.getString("host"));//
				 * 暂时主机开机和关机是一个图标 tObj.setState("1"); }
				 * 
				 * } else if (UnitedConstant.disconnected.equals(connectStatus))
				 * {// 断开链接
				 * tObj.setIcon(unitedTreeIconProp.getString("host_disconnect"
				 * )); tObj.setState("0"); } else {// 未分配资源池中的主机
				 * tObj.setIcon(unitedTreeIconProp.getString("host")); } } else
				 * {// 不在主机表中
				 * tObj.setIcon(unitedTreeIconProp.getString("host_disconnect"
				 * )); }
				 */
				tObj.setIcon(unitedTreeIconProp.getString("host"));
			} else if (UnitedConstant.VM.equals(type)) {
				String state = vmPowerState.get(uuid).get(u.getUuid());
				String[] status = null;
				if (state != null && !"".equals(state)) {
					status = state.split("@");
				}
				if (status != null && VirtualMachineConnectStatus.connected.equals(status[0])) {
					String powerState = status[1];
					if (VirtualMachinePowerStatus.powerOff.equals(powerState)) {
						tObj.setIcon(unitedTreeIconProp.getString("vm_stop"));
						tObj.setState("1");// 关机状态
					} else if (VirtualMachinePowerStatus.powerOn.equals(powerState)) {
						tObj.setIcon(unitedTreeIconProp.getString("vm_run"));
						tObj.setState("2");// 运行状态
					} else if (VirtualMachinePowerStatus.suspended.equals(powerState)) {
						tObj.setIcon(unitedTreeIconProp.getString("vm_supend"));
						tObj.setState("3");// 挂起状态
					}
				} else {
					tObj.setIcon(unitedTreeIconProp.getString("vm_noexist"));
					tObj.setState("4");// 虚拟机不存在
				}
			}
			list.add(tObj);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryTreeNodeByName
	 * @Description: 根据名字模糊查询树节点列表
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-13 上午10:46:41
	 */
	public List<UnitedTreeObj> queryTreeNodeByName(UnitedTreeObj obj) throws SQLException {
		return unitedTreeDao.queryForUnitedTree(obj);
	}

	/**
	 * 
	 * @Title: getExpandNodesForVM
	 * @Description: 查询展开虚拟机所需节点集合
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws HttpClientException
	 * @createtime 2013-9-13 下午7:01:12
	 */
	public List getExpandNodesForVM(UnitedTreeObj obj) throws SQLException, HttpClientException {
		// 查询父节点的相关信息
		UnitedTreeObj un = unitedTreeDao.queryByObj(obj);// 查找parent_id
		UnitedTreeObj parent = new UnitedTreeObj();
		if (un != null) {
			parent.setId(un.getParent_id());
			parent = unitedTreeDao.queryByObj(parent);
		}

		// /查询爷爷节点的类型，
		// 当前存在三种情况：1.虚拟机所在的主机在集群下，2虚拟机所在的主机在数据中心下3，虚拟机在集群下
		UnitedTreeObj grand = new UnitedTreeObj();
		if (parent != null) {
			grand.setId(parent.getParent_id());
			grand = unitedTreeDao.queryByObj(grand);
		}

		// 判断爷爷结点之上是否存在地域结点
		UnitedTreeObj uObj = new UnitedTreeObj();
		uObj.setType("-1");
		List<UnitedTreeObj> treeList = queryUnitedTree(uObj);
		if (treeList != null && treeList.size() > 0) {// 树中存在地狱结点
			// 查询集群的父节点数据中心的信息，判断数据中心上是否存在地域结点
			UnitedTreeObj datacenter = new UnitedTreeObj();
			datacenter.setId(grand.getParent_id());
			datacenter = unitedTreeDao.queryByObj(datacenter);
			if (datacenter.getParent_id() != "1") {// 判断当前结点是否有地域结点
				return unitedTreeDao.getExpandNodesForVmHasLoaction(obj);
			} else {
				return unitedTreeDao.getExpandNodesForVMInHostAndCluster(obj);
			}
		} else {// 不存在地域结点
			String grant_type = grand.getType();// /爷爷节点的类型
			if (UnitedConstant.CLUSTER.equals(grant_type)) {// 说明此时虚拟机所在的主机处于集群下
				return unitedTreeDao.getExpandNodesForVMInHostAndCluster(obj);
			} else if (UnitedConstant.DATACENTER.equals(grant_type)
					&& UnitedConstant.HOST.equals(parent.getType())) {// 说明此时虚拟机所在的主机处于数据中心下
				return unitedTreeDao.getExpandNodesForVMInHostAndDc(obj);
			} else {// 虚拟机在集群下
				return unitedTreeDao.getExpandNodesForVMInCluster(obj);
			}
		}
	}

	/**
	 * 
	 * @Title: getExpandNodesForVM
	 * @Description: 查询展开主机=机所需节点集合
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws HttpClientException
	 * @createtime 2013-9-13 下午7:01:12
	 */
	public List getExpandNodesForHost(UnitedTreeObj obj) throws SQLException, HttpClientException {
		// 查询父节点的相关信息
		UnitedTreeObj un = unitedTreeDao.queryByObj(obj);// 查找parent_id
		UnitedTreeObj parent = new UnitedTreeObj();
		parent.setId(un.getParent_id());
		parent = unitedTreeDao.queryByObj(parent);
		String parent_type = parent.getType();

		// 判断集群的父节点数据中心之上是否存在地域结点
		UnitedTreeObj uObj = new UnitedTreeObj();
		uObj.setType("-1");
		List<UnitedTreeObj> treeList = new ArrayList<UnitedTreeObj>();
		treeList = queryUnitedTree(uObj);
		if (treeList != null && treeList.size() > 0) {// 树中存在地狱结点
			// 查询集群的父节点数据中心的信息，判断数据中心上是否存在地域结点
			UnitedTreeObj datacenter = new UnitedTreeObj();
			datacenter.setId(parent.getParent_id());
			datacenter = unitedTreeDao.queryByObj(datacenter);
			if (datacenter.getParent_id() != "1") {// 存在地域结点
				return unitedTreeDao.getExpandNodesForHosthasLocation(obj);
			} else {// 不存在地域结点
				return unitedTreeDao.getExpandNodesForHostInCluster(obj);
			}
		} else {
			if (UnitedConstant.CLUSTER.equals(parent_type)) {// 主机在集群下
				return unitedTreeDao.getExpandNodesForHostInCluster(obj);
			} else {// 主机在数据中心下
				return unitedTreeDao.getExpandNodesForHostInDc(obj);
			}
		}
	}

	/**
	 * 
	 * @Title: initUnitedTree
	 * @Description: 获取统一树
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 17, 2013 4:42:32 PM
	 */
	public List queryUnitedTree(UnitedTreeObj obj) throws HttpClientException, SQLException {
		return unitedTreeDao.queryForUnitedTree(obj);
	}

	/**
	 * @Title: queryForListForWorkOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-19 下午3:49:40
	 */
	@Override
	public List<UnitedTreeObj> queryForListForWorkOrder(UnitedTreeObj unitedTreeObj) {
		// TODO Auto-generated method stub
		return unitedTreeDao.queryForListForWorkOrder(unitedTreeObj);
	}

	/**
	 * @Title: queryForCenterTreeList
	 * @Description: 统计数据中心
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-3 下午6:52:09
	 */
	@Override
	public List<UnitedTreeObj> queryForCenterTreeList(UnitedTreeObj unitedTreeObj) {
		return unitedTreeDao.queryForCenterTreeList(unitedTreeObj);
	}

	/**
	 * 
	 * @Title: enterMaintenanceMode
	 * @Description: 主机进入维护模式
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-2-24 下午1:41:38
	 */
	public String enterMaintenanceMode(String vType, HostUnitedVO vo) {
		HostUnitedVO reVo = new HostUnitedVO();
		String result = "";
		String url = "";
		if (UnitedConstant.VMWARE.equals(vType)) {
			url = "hostsystem/" + VirtualConstant.VT_VMWARE + "/enterMaintenanceMode/";
		} else if (UnitedConstant.XEN.equals(vType)) {
			url = "hostsystem/" + VirtualConstant.VT_XEN + "/enterMaintenanceMode/";
		}
		try {
			reVo = VirtualClient.post(url, vo, new JacksonUtil.TypeReference<HostUnitedVO>() {
			});
			if (reVo.getIsSuccess()) {
				result = "success";
			} else {
				result = reVo.getLog();
			}
		} catch (HttpClientException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @Title: exitMaintenanceMode
	 * @Description: 主机退出维护模式
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-2-24 下午1:43:38
	 */
	public String exitMaintenanceMode(String vType, HostUnitedVO vo) {
		HostUnitedVO reVo = new HostUnitedVO();
		String result = "";
		String url = "";
		if (UnitedConstant.VMWARE.equals(vType)) {
			url = "hostsystem/" + VirtualConstant.VT_VMWARE + "/exitMaintenanceMode/";
		} else if (UnitedConstant.XEN.equals(vType)) {
			url = "hostsystem/" + VirtualConstant.VT_XEN + "/exitMaintenanceMode/";
		}
		try {
			reVo = VirtualClient.post(url, vo, new JacksonUtil.TypeReference<HostUnitedVO>() {
			});
			if (reVo.getIsSuccess()) {
				result = "success";
			} else {
				result = reVo.getLog();
			}
		} catch (HttpClientException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @Title: queryForListByObj
	 * @Description: 查询列表
	 * @return UnitedTreeObj
	 * @throws
	 * @Date 2014-6-1 上午10:52:53
	 * @author lipp
	 * @param parentObj
	 * @return
	 */
	@Override
	public List<UnitedTreeObj> queryForListByObj(UnitedTreeObj obj) {
		return unitedTreeDao.queryForListByObj(obj);
	}

	/**  
	  * @Title: getUnitedTreeObj  
	  * @Description: 获取树对象
	  * @return UnitedTreeObj   
	  * @throws  
	  * @Date 2014-6-17 下午2:08:18
	  * @author lipp
	  * @param id 当前节点的id
	  * @param entityType  目标节点的类型
	  * @param count 默认0
	  * @return
	  */
	@Override
	public UnitedTreeObj getUnitedTreeObj(String id, String entityType,
			int count) {
		count++;
		if (count > 5) {// 避免进入死循环
			return null;
		} else {
			UnitedTreeObj treeObj = new UnitedTreeObj();
			treeObj.setId(id);
			List<UnitedTreeObj> treeList = unitedTreeDao.queryForListByObj(treeObj);
			treeObj = treeList.get(0);
			if (entityType.equals(treeObj.getType())) {
				return treeObj;
			} else {
				treeObj = getUnitedTreeObj(treeObj.getParent_id(), entityType, count);
			}
			return treeObj;
		}
	}
}
