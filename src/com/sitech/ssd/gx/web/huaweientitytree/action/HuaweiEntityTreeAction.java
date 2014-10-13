package com.sitech.ssd.gx.web.huaweientitytree.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.ssd.gx.domain.huaweientitytree.HuaweiEntityTreeObj;
import com.sitech.ssd.gx.domain.huaweientitytree.HuaweiTreeObj;
import com.sitech.ssd.gx.service.entitytree.HuaweiEntityTreeService;
import com.sitech.ssd.gx.service.huaweiCluster.HuaweiClusterService;
import com.sitech.ssd.gx.service.huaweihost.HuaweiHostService;
import com.sitech.ssd.gx.service.huaweivm.HuaweiVMHostService;
import com.sitech.ssd.gx.service.sync.SyncHuaweiData;
import com.sitech.ssd.gx.utils.HwEntityTypeConstant;
import com.sitech.ssd.gx.web.huaweientitytree.form.HuaweiEntityTreeForm;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.vo.huawei.Cluster;
import com.sitech.vo.huawei.HostVO;
import com.sitech.vo.huawei.VMDetailVO;
import com.sitech.vo.huawei.VMStateConstent;

/**
 * 
 * <p>
 * Title: EntityTreeAction
 * </p>
 * <p>
 * Description: ztree管理Action
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Apr 18, 2012 11:05:32 AM
 * 
 */
@SuppressWarnings("all")
public class HuaweiEntityTreeAction extends CRUDBaseAction {
	private static Map<String, Map<String, Object>> vmStatMap = new HashMap<String, Map<String, Object>>();
	private HuaweiEntityTreeService huaweiEntityTreeService;
	private SyncHuaweiData syncHuaweiData;
	private HuaweiClusterService huaweiClusterService;
	private HuaweiHostService huaweiHostService;
	private HuaweiVMHostService huaweiVMHostService;
	private HuaweiEntityTreeForm treeForm;

	public HuaweiEntityTreeForm getTreeForm() {
		return treeForm;
	}

	public void setTreeForm(HuaweiEntityTreeForm treeForm) {
		this.treeForm = treeForm;
	}

	public void setHuaweiEntityTreeService(HuaweiEntityTreeService huaweiEntityTreeService) {
		this.huaweiEntityTreeService = huaweiEntityTreeService;
	}

	public void setSyncHuaweiData(SyncHuaweiData syncHuaweiData) {
		this.syncHuaweiData = syncHuaweiData;
	}

	public void setHuaweiClusterService(HuaweiClusterService huaweiClusterService) {
		this.huaweiClusterService = huaweiClusterService;
	}

	public void setHuaweiHostService(HuaweiHostService huaweiHostService) {
		this.huaweiHostService = huaweiHostService;
	}

	public void setHuaweiVMHostService(HuaweiVMHostService huaweiVMHostService) {
		this.huaweiVMHostService = huaweiVMHostService;
	}

	/**
	 * 
	 * @Title: listhuaweientitytree
	 * @Description: 获取虚拟机主机树
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-2 下午5:04:43
	 */
	public String listhuaweientitytree() throws Exception {
		return LIST;
	}

	/**
	 * @Title: asyncForHuaweiTree
	 * @Description: 采用异步的方式生成树
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-2 下午5:25:24
	 */
	public String asyncForHuaweiTree() throws Exception, IOException {
		HuaweiEntityTreeObj treeObj = new HuaweiEntityTreeObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		String str = request.getParameter("id");
		int parentType = Integer.parseInt(request.getParameter("type") == null ? "0" : request
				.getParameter("type"));
		String entityId = request.getParameter("entityId");

		if (str == null || "".equals(str)) {
			treeObj.setType(HwEntityTypeConstant.HUAWEI_DATACENTER);
		} else {
			treeObj.setParent_id(str);
		}
		Map<String, String> cluSonVmState = new HashMap<String, String>();
		Map<String, Boolean> cluSonHostState = new HashMap<String, Boolean>();
		Map<String, String> hostSonState = new HashMap<String, String>();
		List<HuaweiEntityTreeObj> resultList = new ArrayList<HuaweiEntityTreeObj>();
		if (HwEntityTypeConstant.HUAWEI_CLUSTER == parentType) {
			treeObj.setType(HwEntityTypeConstant.HUAWEI_HOST);
			List<HuaweiEntityTreeObj> hostList = huaweiEntityTreeService.queryForTree(treeObj);
			treeObj.setType(HwEntityTypeConstant.HUAWEI_VM);
			List<HuaweiEntityTreeObj> vmList = huaweiEntityTreeService.queryForTree(treeObj);
			treeObj.setType(HwEntityTypeConstant.HUAWEI_IMAGE);
			List<HuaweiEntityTreeObj> imageList = huaweiEntityTreeService.queryForTree(treeObj);
			if (hostList != null) {
				resultList.addAll(hostList);
			}
			if (vmList != null) {
				resultList.addAll(vmList);
			}
			if (imageList != null) {
				resultList.addAll(imageList);
			}
			// 获取集群下主机和虚拟机状态
			HuaweiEntityTreeObj sonTreeObj = resultList.get(0);
			String[] siteUrns = sonTreeObj.getDc_code().split(":");
			String siteCode = siteUrns[siteUrns.length - 1];
			String cluUrn = sonTreeObj.getCluser_code();
			cluSonHostState = huaweiHostService.queryHostInGroupState(siteCode, cluUrn);
			cluSonVmState = huaweiVMHostService.queryVmInGroupState(siteCode, cluUrn);
		} else if (HwEntityTypeConstant.HUAWEI_HOST == parentType) {
			resultList = huaweiEntityTreeService.queryForTree(treeObj);
			// 获取主机下虚拟机状态
			StringBuffer vmUrns = new StringBuffer();
			String[] dcUrns = resultList.get(0).getDc_code().split(":");
			String siteCode = dcUrns[dcUrns.length - 1];
			String hostUrn = resultList.get(0).getHost_code();
			hostSonState = huaweiVMHostService.queryVmInGroupState(siteCode, hostUrn);
		} else {
			resultList = huaweiEntityTreeService.queryForTree(treeObj);
		}
		List<HuaweiTreeObj> list = new ArrayList<HuaweiTreeObj>();
		if (resultList != null) {
			HuaweiEntityTreeObj tempObj = new HuaweiEntityTreeObj();
			for (HuaweiEntityTreeObj obj : resultList) {
				// 暂不显示资源池
				HuaweiTreeObj tObj = new HuaweiTreeObj();
				tObj.setId(obj.getId());
				tObj.setName(obj.getName());
				tObj.setType(obj.getType());
				tObj.setEntityId(obj.getEntity_id());
				tObj.setDcCode(obj.getDc_code());
				// 判断是不是父节点
				tempObj.setParent_id(obj.getId());
				List<HuaweiEntityTreeObj> lst = huaweiEntityTreeService.queryForTree(tempObj);
				if (lst == null || lst.size() == 0) {
					tObj.setIsParent(false);
				}
				int enType = obj.getType();
				// 设置图标
				if (enType == HwEntityTypeConstant.HUAWEI_DATACENTER) {// 数据中心
					tObj.setIcon("sxcloud/images/virtual/Datacenter.png");
				}
				if (enType == HwEntityTypeConstant.HUAWEI_CLUSTER) { // 集群
					tObj.setIcon("sxcloud/images/virtual/cluster.png");
				}

				if (enType == HwEntityTypeConstant.HUAWEI_HOST) { // vmware主机
					try {
						Boolean stat = cluSonHostState.get(obj.getCode());
						if (stat == null) {// 没有主机
							tObj.setIcon("sxcloud/images/virtual/delete.png");
						} else {
							if (stat) {
								tObj.setState("0");// 主机进入维护模式
								tObj.setIcon("sxcloud/images/virtual/maintenance.png");
							} else {
								tObj.setState("1");// 主机退出维护模式
								tObj.setIcon("sxcloud/images/virtual/Host.png");
							}
						}
					} catch (Exception e) {
						tObj.setIcon("sxcloud/images/virtual/Host.png");
					}
				}

				if (enType == HwEntityTypeConstant.HUAWEI_VM) {// 虚拟机
					// 检查虚拟机状态
					try {
						String stat = null;
						if (parentType == HwEntityTypeConstant.HUAWEI_CLUSTER) {
							stat = cluSonVmState.get(obj.getCode());
						} else if (parentType == HwEntityTypeConstant.HUAWEI_HOST) {
							stat = hostSonState.get(obj.getCode());
						}

						if (stat == null) {// 没有该虚拟机
							tObj.setIcon("sxcloud/images/virtual/delete.png");
						} else {
							if (stat.equals(VMStateConstent.RUNNING)) {
								tObj.setIcon("sxcloud/images/virtual/running.png");
								tObj.setState("0");// 虚拟机开启
							} else {
								tObj.setIcon("sxcloud/images/virtual/VirtualMachine.png");
								tObj.setState("1");// 虚拟机关闭
							}
						}
					} catch (Exception e) {
						tObj.setIcon("sxcloud/images/virtual/VirtualMachine.png");
					}
				}
				list.add(tObj);
			}
		}
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray json = JSONArray.fromObject(list);
		// PrintWriter p = response.getWriter();
		// p.print(json.toString());
		// p.close();
		PrintWriterOut.printWirter(response, json.toString());
		return null;
	}

	/**
	 * 
	 * @Title: synchroData
	 * @Description: 同步数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-3 下午10:12:59
	 */
	public String synchroData() {
		// 删除所有数据
		int result = huaweiEntityTreeService.deleteAllData();
		String json = "{\"result\":\"" + result + "\"}";
		PrintWriter p = null;
		try {
			// 同步新的数据
			syncHuaweiData.initRestEntities();
			p = Struts2Utils.getResponse().getWriter();
		} catch (Exception e) {
			json = "{\"result\":\"" + -1 + "\"}";
		}
		p.print(json);
		p.close();
		return null;
	}

	/**
	 * 
	 * @Title: vmtabs
	 * @Description: 单击时进入tab页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-6 下午2:48:20
	 */
	public String vmtabs() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String type = request.getParameter("type");// 得到实体类型
		String id = request.getParameter("id");
		request.setAttribute("type", type);
		request.setAttribute("id", id);
		return "vmtabs";
	}

	/**
	 * 
	 * @Title: dataCenterInfo
	 * @Description: 数据中心概要展示
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-6 下午3:44:38
	 */
	public String dataCenterInfo() {
		if (treeForm == null) {
			treeForm = new HuaweiEntityTreeForm();
		}
		String dcId = request.getParameter("id");
		HuaweiEntityTreeObj dcTreeObj = new HuaweiEntityTreeObj();
		dcTreeObj.setId(dcId);
		dcTreeObj = huaweiEntityTreeService.queryTreeNode(dcTreeObj);
		HuaweiEntityTreeObj queryObj = new HuaweiEntityTreeObj();
		queryObj.setDc_code(dcTreeObj.getCode());
		List<HuaweiEntityTreeObj> sonNodes = huaweiEntityTreeService.queryForTree(queryObj);
		int vmSize = 0;
		int hostSize = 0;
		int clusterSize = 0;
		if (sonNodes != null && sonNodes.size() > 0) {
			for (HuaweiEntityTreeObj huaweiEntityTreeObj : sonNodes) {
				if (huaweiEntityTreeObj.getType() == HwEntityTypeConstant.HUAWEI_CLUSTER) {
					clusterSize++;
				} else if (huaweiEntityTreeObj.getType() == HwEntityTypeConstant.HUAWEI_HOST) {
					hostSize++;
				} else if (huaweiEntityTreeObj.getType() == HwEntityTypeConstant.HUAWEI_VM) {
					vmSize++;
				}
			}
		}
		treeForm.setDcClusterSize(clusterSize);
		treeForm.setDcHostSize(hostSize);
		treeForm.setDcVmSize(vmSize);
		return "dataCenterInfo";
	}

	/**
	 * 
	 * @Title: clusterInfo
	 * @Description: 集群概要展示
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-6 下午3:44:38
	 */
	public String clusterInfo() {
		if (treeForm == null) {
			treeForm = new HuaweiEntityTreeForm();
		}
		String clusterId = request.getParameter("id");
		HuaweiEntityTreeObj cluTreeObj = new HuaweiEntityTreeObj();
		cluTreeObj.setId(clusterId);
		cluTreeObj = huaweiEntityTreeService.queryTreeNode(cluTreeObj);
		// 查询集群下统计信息
		HuaweiEntityTreeObj queryObj = new HuaweiEntityTreeObj();
		queryObj.setDc_code(cluTreeObj.getDc_code());
		queryObj.setCluser_code(cluTreeObj.getCode());
		List<HuaweiEntityTreeObj> sonNodes = huaweiEntityTreeService.queryForTree(queryObj);
		int vmSize = 0;
		int hostSize = 0;
		if (sonNodes != null && sonNodes.size() > 0) {
			for (HuaweiEntityTreeObj huaweiEntityTreeObj : sonNodes) {
				if (huaweiEntityTreeObj.getType() == HwEntityTypeConstant.HUAWEI_HOST) {
					hostSize++;
				} else if (huaweiEntityTreeObj.getType() == HwEntityTypeConstant.HUAWEI_VM) {
					vmSize++;
				}
			}
		}
		treeForm.setCluHostSize(hostSize);
		treeForm.setCluVmSize(vmSize);
		String[] dcUrns = cluTreeObj.getDc_code().split(":");
		String dcCode = dcUrns[dcUrns.length - 1];
		String[] cluUrsn = cluTreeObj.getCode().split(":");
		String cluCode = cluUrsn[cluUrsn.length - 1];
		Cluster cluster = null;
		try {
			cluster = huaweiClusterService.queryClusterInfoFromRest(dcCode, cluCode);
		} catch (HttpClientException e) {
			e.printStackTrace();
		}
		treeForm.setCluName(cluster.getName());
		treeForm.setCluDesc(cluster.getDescription());
		treeForm.setIssEnableHa(cluster.getIsEnableHa());
		treeForm.setIssMemOvercommit(cluster.getIsMemOvercommit());
		treeForm.setIssEnableDrs(cluster.getIsEnableDrs());
		treeForm.setDrsLevel(cluster.getDrsSetting().getDrsLevel());
		treeForm.setDrsLimen(cluster.getDrsSetting().getDrsLimen());
		return "clusterInfo";
	}

	/**
	 * 
	 * @Title: hostInfo
	 * @Description: 主机概要展示
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-6 下午3:47:18
	 */
	public String hostInfo() {
		if (treeForm == null) {
			treeForm = new HuaweiEntityTreeForm();
		}
		// 查询主机的统计信息
		String hostId = request.getParameter("id");
		HuaweiEntityTreeObj hostTreeObj = new HuaweiEntityTreeObj();
		hostTreeObj.setId(hostId);
		hostTreeObj = huaweiEntityTreeService.queryTreeNode(hostTreeObj);
		HuaweiEntityTreeObj queryObj = new HuaweiEntityTreeObj();
		queryObj.setDc_code(hostTreeObj.getDc_code());
		queryObj.setHost_code(hostTreeObj.getCode());
		queryObj.setType(HwEntityTypeConstant.HUAWEI_VM);
		List<HuaweiEntityTreeObj> sonNodes = huaweiEntityTreeService.queryForTree(queryObj);
		int vmSize = 0;
		if (sonNodes != null && sonNodes.size() > 0) {
			vmSize = sonNodes.size();
		}
		treeForm.setHostVmSize(vmSize);
		String[] dcUrns = hostTreeObj.getDc_code().split(":");
		String dcCode = dcUrns[dcUrns.length - 1];
		String[] hostUrns = hostTreeObj.getCode().split(":");
		String hostCode = hostUrns[hostUrns.length - 1];
		// 从接口获取主机的详细信息
		HostVO hostVO = null;
		try {
			hostVO = huaweiHostService.queryHostInfoFromRest(dcCode, hostCode);
		} catch (HttpClientException e) {
			e.printStackTrace();
		}
		treeForm.setHostName(hostVO.getName());
		treeForm.setHostDesc(hostVO.getDescription());
		treeForm.setHostAttachedISOVM(hostVO.getAttachedISOVM());
		treeForm.setHostBmcIp(hostVO.getBmcIp());
		treeForm.setHostClusterName(hostVO.getClusterName());
		treeForm.setHostIp(hostVO.getIp());
		treeForm.setHostStatus(hostVO.getStatus());
		treeForm.setHostIsMaintaining(hostVO.getIsMaintaining());
		return "hostInfo";
	}

	/**
	 * 
	 * @Title: vmInfo
	 * @Description: 虚拟机概要展示
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-6 下午3:47:56
	 */
	public String vmInfo() {
		if (treeForm == null) {
			treeForm = new HuaweiEntityTreeForm();
		}
		// 查询主机的统计信息
		String vmId = request.getParameter("id");
		String type = request.getParameter("type");
		HuaweiEntityTreeObj vmTreeObj = new HuaweiEntityTreeObj();
		vmTreeObj.setId(vmId);
		vmTreeObj = huaweiEntityTreeService.queryTreeNode(vmTreeObj);
		VMDetailVO vmDetailVO = null;
		String[] dcUrns = vmTreeObj.getDc_code().split(":");
		String dcCode = dcUrns[dcUrns.length - 1];
		String[] vmUrns = vmTreeObj.getCode().split(":");
		String vmCode = vmUrns[vmUrns.length - 1];
		try {
			vmDetailVO = huaweiVMHostService.quertyVmInfoFromRest(dcCode, vmCode);
		} catch (HttpClientException e) {
			e.printStackTrace();
		}
		treeForm.setVmname(vmDetailVO.getName());
		treeForm.setVmdesc(vmDetailVO.getDescription());
		treeForm.setVmcode(vmCode);
		HuaweiEntityTreeObj hostTreeObj = new HuaweiEntityTreeObj();
		hostTreeObj.setCode(vmDetailVO.getHostUrn());
		hostTreeObj.setDc_code(vmTreeObj.getDc_code());
		hostTreeObj = huaweiEntityTreeService.queryTreeNode(hostTreeObj);
		treeForm.setRunningHostName(hostTreeObj.getName());
		treeForm.setVmisLinkClone(vmDetailVO.getIsLinkClone());
		treeForm.setVmlocationName(vmDetailVO.getLocationName());
		treeForm.setVmpvDriverStatus(vmDetailVO.getPvDriverStatus());
		treeForm.setVmcreateTime(vmDetailVO.getCreateTime());
		treeForm.setVmstatus(vmDetailVO.getStatus());
		String parentType = vmDetailVO.getLocation().split(":")[3];
		if (parentType.equals("hosts")) {
			treeForm.setVmisBindHost(true);
		} else if (parentType.equals("clusters")) {
			treeForm.setVmisBindHost(false);
		}
		request.setAttribute("type", Integer.parseInt(type));
		return "vmInfo";
	}
}
