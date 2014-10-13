package com.sitech.basd.ibmmanager.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.ibmmanager.dao.IBMManagerTreeDao;
import com.sitech.basd.ibmmanager.domain.IBMManagerTreeObj;
import com.sitech.basd.ibmmanager.domain.PowerStateVo;
import com.sitech.basd.ibmmanager.domain.ReturnList;
import com.sitech.basd.ibmmanager.util.IBMConstant;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.util.PropertyUtil;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.http.HttpClientUtil;
import com.sitech.utils.jackson.JacksonUtil;

/**
 * 
 * <p>
 * Title: SsManagerTreeServiceImpl
 * </p>
 * <p>
 * Description: IBM小型机相关操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2013-11-1 下午5:40:25
 * 
 */
@Service("ibmManagerTreeService")
public class IBMManagerTreeServiceImpl implements IBMManagerTreeService {
	@Autowired
	private IBMManagerTreeDao ibmManagerTreeDao;
	@Autowired
	private PropertyUtil ibmTreeIconProp;
	// 虚拟机电源状态-key-hostCode,Map<string,String> key:vmName value:vmStatus
	private final Map<String, Map<String, String>> vmPowerState = new HashMap<String, Map<String, String>>();

	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询树节点
	 * @param
	 * @return List<IBMManagerTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-2 下午5:46:13
	 */
	@Override
	public List<IBMManagerTreeObj> queryForTree(IBMManagerTreeObj obj) {
		return ibmManagerTreeDao.queryForTree(obj);
	}

	/**
	 * 
	 * @Title: initTreelist
	 * @Description: 初始化树节点
	 * @param
	 * @return List<IBMManagerTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-2 下午5:46:28
	 */
	@Override
	public List<IBMManagerTreeObj> initTreelist(
			List<IBMManagerTreeObj> resultList) {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		String uuid = request.getParameter("uuid");
		String node_type = request.getParameter("type");
		String vtype = request.getParameter("vtype");
		String name = request.getParameter("name");
		// 实例宿主机下虚拟机电源状态
		if (IBMConstant.X86.equals(vtype) && IBMConstant.HOST.equals(node_type)) {
			initVMPowerStateMap(vtype, uuid, name);
		}
		List<IBMManagerTreeObj> list = new ArrayList<IBMManagerTreeObj>();
		IBMManagerTreeObj tempObj = new IBMManagerTreeObj();
		for (IBMManagerTreeObj obj : resultList) {
			IBMManagerTreeObj tObj = new IBMManagerTreeObj();
			tObj.setId(obj.getId());
			tObj.setName(obj.getName());
			tObj.setType(obj.getType());
			tObj.setUuid(obj.getUuid());
			tObj.setVtype(obj.getVtype());
			tObj.setParent_id(obj.getParent_id());
			tempObj.setParent_id(obj.getId());
			// 判断是不是父节点
			List<IBMManagerTreeObj> lst = queryForTree(tempObj);
			if (lst == null || lst.size() == 0) {
				tObj.setIsParent(false);
			} else {
				tObj.setIsParent(true);
			}
			// 设置图标
			if (IBMConstant.ROOT.equals(obj.getType())) {// 资源
				tObj.setIcon(ibmTreeIconProp.getString("busi.sys.center.png"));
				tObj.setNocheck(true);
			} else if (IBMConstant.IBMNODE.equals(obj.getType())) { // 小型机
				tObj.setIcon(ibmTreeIconProp
						.getString("busi.sys.center.root.png"));
				tObj.setNocheck(true);
			} else if (IBMConstant.X86NODE.equals(obj.getType())) {
				tObj.setIcon(ibmTreeIconProp.getString("datacenter"));
				tObj.setNocheck(true);
			} else if (IBMConstant.HMC.equals(obj.getType())) { // HMC-1
				tObj.setIcon(ibmTreeIconProp.getString("busi.sys.png"));
				tObj.setNocheck(true);
			} else if (IBMConstant.POWER.equals(obj.getType())) { // IBM整机(宿主机)-2
				tObj.setIcon(ibmTreeIconProp.getString("sys.app.png"));
				tObj.setNocheck(true);
			} else if (IBMConstant.LPAR.equals(obj.getType())) { // 逻辑分区(虚拟机)-3
				tObj.setIcon(ibmTreeIconProp.getString("app.deploy.png"));
				tObj.setNocheck(true);
			} else if (IBMConstant.CLUSTER.equals(obj.getType())) { // 集群
				tObj.setIcon(ibmTreeIconProp.getString("cluster"));
				tObj.setNocheck(true);
			} else if (IBMConstant.HOST.equals(obj.getType())) { // 主机
				tObj.setIcon(ibmTreeIconProp.getString("host"));
				tObj.setNocheck(true);
			} else if (IBMConstant.VM.equals(obj.getType())) { // 虚拟机
				if (vmPowerState.get(uuid) != null) {
					if (IBMConstant.poweredOff.equals(vmPowerState.get(uuid)
							.get(obj.getName()))) {
						tObj.setIcon(ibmTreeIconProp.getString("vm_stop"));
						tObj.setState("1");// 关机状态
					} else if (IBMConstant.poweredOn.equals(vmPowerState.get(
							uuid).get(obj.getName()))) {
						tObj.setIcon(ibmTreeIconProp.getString("vm_run"));
						tObj.setState("2");// 运行状态
					} else if (IBMConstant.standBy.equals(vmPowerState
							.get(uuid).get(obj.getName()))) {
						tObj.setIcon(ibmTreeIconProp.getString("vm_supend"));
						tObj.setState("3");// 挂起状态
					} else {
						tObj.setIcon(ibmTreeIconProp.getString("vm_noexist"));
						tObj.setState("4");// 状态未知
					}
				}
			}
			list.add(tObj);
		}
		return list;
	}

	/**
	 * 
	 * @Title: initVMPowerStateMap
	 * @Description: 实例X86主机下虚拟机状态Map
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-18 下午5:23:54
	 */
	public void initVMPowerStateMap(String vtype, String hostCode, String name) {
		try {
			if (IBMConstant.X86.equals(vtype)) {
				Map<String, String> vmStateMap = getVMPowerState(vtype, name);
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
	 * @Title: getVMPowerState
	 * @Description: 获取虚拟机的电源状态
	 * @param
	 * @return Map<String,String>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-18 下午5:19:46
	 */
	public Map<String, String> getVMPowerState(String vtype, String name)
			throws HttpClientException, SQLException {
		Map<String, String> powerState = new HashMap<String, String>();
		PowerStateVo vo = new PowerStateVo();
		if (IBMConstant.X86.equals(vtype)) {
			String url = "http://10.208.100.93:8286/vmware/api/hostSystem/"
					+ name + "/resideon";
			String urlString = HttpClientUtil.get(url);
			vo = JacksonUtil.fromJSON(urlString,
					new JacksonUtil.TypeReference<PowerStateVo>() {
					});
			if (vo.getOptResult().equals("success")) {
				for (ReturnList stateVo : vo.getReturnList()) {
					powerState.put(stateVo.getName(), stateVo.getPowerState());
				}
			}
		}
		return powerState;
	}

	/**
	 * 
	 * @Title: insertTree
	 * @Description: 插入操作
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-2 下午5:46:56
	 */
	@Override
	public void insertTree(IBMManagerTreeObj obj) {
		ibmManagerTreeDao.insertTree(obj);
	}

	/**
	 * 
	 * @Title: deleteTreeById
	 * @Description: 删除操作
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-2 下午5:47:09
	 */
	@Override
	public int deleteTreeById(IBMManagerTreeObj obj) {
		return ibmManagerTreeDao.deleteTreeById(obj);
	}

	/**
	 * 
	 * @Title: updateTreeByObj
	 * @Description: 修改操作
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-2 下午5:47:19
	 */
	@Override
	public int updateTreeByObj(IBMManagerTreeObj obj) {
		return ibmManagerTreeDao.updateTreeByObj(obj);
	}

	/**
	 * 
	 * @Title: queryExpandNodesForPower
	 * @Description: 查询展开树所需结点(整机)
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午5:42:37
	 */
	@Override
	public List<IBMManagerTreeObj> queryExpandNodesForPower(
			IBMManagerTreeObj obj) {
		return ibmManagerTreeDao.queryExpandNodesForPower(obj);
	}

	/**
	 * 
	 * @Title: queryExpandNodesForLP
	 * @Description: 查询展开树所需节点(逻辑分区)
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午5:41:41
	 */
	@Override
	public List<IBMManagerTreeObj> queryExpandNodesForLP(IBMManagerTreeObj obj) {
		return ibmManagerTreeDao.queryExpandNodesForLP(obj);
	}

	/**
	 * 
	 * @Title: queryForHostTreeByName
	 * @Description: 通过名字查询左侧树节点(主机)
	 * @param
	 * @return List<IBMManagerTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午6:01:20
	 */
	@Override
	public List<IBMManagerTreeObj> queryForHostTreeByName(IBMManagerTreeObj obj) {
		List<IBMManagerTreeObj> list = new ArrayList<IBMManagerTreeObj>();
		obj.setType(IBMConstant.HOST);// X86主机
		list = ibmManagerTreeDao.queryForTreeByName(obj);
		List<IBMManagerTreeObj> listAll = new ArrayList<IBMManagerTreeObj>();
		obj.setType(IBMConstant.POWER);// IBM Power
		listAll = ibmManagerTreeDao.queryForTreeByName(obj);
		listAll.addAll(list);
		return listAll;
	}

	/**
	 * 
	 * @Title: queryForVMTreeByName
	 * @Description: 通过名字查询左侧树节点(虚拟机)
	 * @param
	 * @return List<IBMManagerTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-26 下午6:34:59
	 */
	@Override
	public List<IBMManagerTreeObj> queryForVMTreeByName(IBMManagerTreeObj obj) {
		List<IBMManagerTreeObj> list = new ArrayList<IBMManagerTreeObj>();
		obj.setType(IBMConstant.VM);
		list = ibmManagerTreeDao.queryForTreeByName(obj);
		List<IBMManagerTreeObj> listAll = new ArrayList<IBMManagerTreeObj>();
		obj.setType(IBMConstant.LPAR);
		listAll = ibmManagerTreeDao.queryForTreeByName(obj);
		listAll.addAll(list);
		return listAll;
	}
}
