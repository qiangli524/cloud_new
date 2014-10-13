package com.sitech.basd.yicloud.web.xentree.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sitech.basd.sxcloud.cloud.domain.image.TbCloud2ImageInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.cloud.service.image.ImageService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.rsmu.config.Constant;
import com.sitech.basd.sxcloud.rsmu.service.system.OperationService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.kvm.VMManagerObj;
import com.sitech.basd.yicloud.domain.taskevent.EventObj;
import com.sitech.basd.yicloud.domain.taskevent.TaskObj;
import com.sitech.basd.yicloud.domain.xenstore.XenStoreObj;
import com.sitech.basd.yicloud.domain.xentree.XenClusterObj;
import com.sitech.basd.yicloud.domain.xentree.XenEntityConObj;
import com.sitech.basd.yicloud.domain.xentree.XenEntityTreeObj;
import com.sitech.basd.yicloud.domain.xentree.XenTreeObj;
import com.sitech.basd.yicloud.service.kvm.KvmManService;
import com.sitech.basd.yicloud.service.taskevent.TaskEventService;
import com.sitech.basd.yicloud.service.templettree.TempletTreeService;
import com.sitech.basd.yicloud.service.vmauthority.VmAuthorityService;
import com.sitech.basd.yicloud.service.xencluster.XenClusterService;
import com.sitech.basd.yicloud.service.xenstore.XenStoreService;
import com.sitech.basd.yicloud.service.xentree.XenEntityTreeService;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.ParamParser;
import com.sitech.basd.yicloud.util.TypeConstant;
import com.sitech.basd.yicloud.web.xentree.form.XenTreeForm;
import com.sitech.utils.servlet.PrintWriterOut;

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
public class XenTreeAction extends CRUDBaseAction {
	private static Map<String, Map<String, Object>> vmStatMap = new HashMap<String, Map<String, Object>>();
	private Map<String, Object> map;
	private XenEntityTreeService xenEntityTreeService;
	private XenTreeForm theForm;
	private VMHostService vmHostService;
	private HostInfoService hostInfoService;
	private TaskEventService taskEventService;
	private TempletTreeService templetTreeService;
	private KvmManService kvmManService;
	private ImageService imageService;
	private TbGlobalConfigService tbGlobalConfigService;
	private VmAuthorityService vmAuthorityService;
	private XenStoreService xenStoreService;

	public void setXenStoreService(XenStoreService xenStoreService) {
		this.xenStoreService = xenStoreService;
	}

	public void setVmAuthorityService(VmAuthorityService vmAuthorityService) {
		this.vmAuthorityService = vmAuthorityService;
	}

	public void setTbGlobalConfigService(TbGlobalConfigService tbGlobalConfigService) {
		this.tbGlobalConfigService = tbGlobalConfigService;
	}

	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	public static void setVmStatMap(Map<String, Map<String, Object>> vmStatMap) {
		XenTreeAction.vmStatMap = vmStatMap;
	}

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public void setKvmManService(KvmManService kvmManService) {
		this.kvmManService = kvmManService;
	}

	public void setTempletTreeService(TempletTreeService templetTreeService) {
		this.templetTreeService = templetTreeService;
	}

	private XenClusterService xenClusterService;

	public void setXenClusterService(XenClusterService xenClusterService) {
		this.xenClusterService = xenClusterService;
	}

	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}

	public void setTaskEventService(TaskEventService taskEventService) {
		this.taskEventService = taskEventService;
	}

	public void setVmHostService(VMHostService vmHostService) {
		this.vmHostService = vmHostService;
	}

	public void setXenEntityTreeService(XenEntityTreeService xenEntityTreeService) {
		this.xenEntityTreeService = xenEntityTreeService;
	}

	public XenTreeForm getTheForm() {
		return theForm;
	}

	public void setTheForm(XenTreeForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title: listVMService
	 * @Description: 获取虚拟机主机树
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 18, 2012 11:42:00 AM
	 */
	public String listVMService() throws Exception {
		if (theForm == null) {
			theForm = new XenTreeForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String FUNCSID = request.getParameter("FUNCSID");
		request.getSession().setAttribute(Constant.FUNCID, FUNCSID);
		// TbSysUserinfoObj userObj = (TbSysUserinfoObj)
		// request.getSession().getAttribute(
		// Constant.USER_SESSION_KEY);
		int userId = (Integer) session.get("id");
		XenEntityTreeObj treeObj = new XenEntityTreeObj();
		treeObj.setType("34");
		List<XenEntityTreeObj> lst = xenEntityTreeService.queryForTree(treeObj);
		if (lst != null && lst.size() > 0) {
			treeObj = lst.get(0);
			theForm.setFirstDC_Id(treeObj.getId() + "");
		}
		return LIST;
	}

	/**
	 * 
	 * @Title: asyncForTree
	 * @Description: 采用异步的方式生成树
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 18, 2012 11:42:00 AM
	 */
	public String asyncForTree() throws Exception, IOException {
		XenEntityTreeObj treeObj = new XenEntityTreeObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		String str = request.getParameter("id");
		String type = request.getParameter("type");
		String entityId = request.getParameter("entityId");
		String treeUuid = request.getParameter("tree_uuid");
		String FUNCSID = request.getParameter("FUNCSID");
		request.getSession().setAttribute(Constant.FUNCID, FUNCSID);
		// TbSysUserinfoObj userObj = (TbSysUserinfoObj)
		// request.getSession().getAttribute(
		// Constant.USER_SESSION_KEY);
		/*
		 * TbGlobalConfigObj tbObj = new TbGlobalConfigObj();
		 * tbObj.setKEY("USER"); tbObj =
		 * tbGlobalConfigService.queryByObj(tbObj); Map<String, String> userMap
		 * = new HashMap<String, String>(); userMap.put("admin", "admin"); if
		 * (tbObj != null) { String users = tbObj.getVALUE(); String[] user =
		 * users.split(","); for (String u : user) { if (u != null &&
		 * !u.equals("")) { userMap.put(u, u); } } } if
		 * (!userMap.containsKey(userObj.getACCOUNT())) {
		 * treeObj.setUserId(String.valueOf(userObj.getID())); }
		 */
		if (!session.get("account").toString().equals("admin")) {
			treeObj.setUserId(session.get("id").toString());
		}
		if (str == null || "".equals(str)) {
			treeObj.setType(TypeConstant.XEN_DATACENTER);

		} else {
			treeObj.setParentId(Integer.valueOf(str));
		}

		List<XenEntityTreeObj> resultList = new ArrayList<XenEntityTreeObj>();
		resultList = xenEntityTreeService.queryForTree(treeObj);
		// 对主机下面的虚拟机进行比对
		if (type != null) {
			if (type.equals("26")) {
				XenEntityTreeObj queryTreeObj = new XenEntityTreeObj();
				queryTreeObj.setUuid(treeUuid);
				queryTreeObj = xenEntityTreeService.queryForHostUuidAndClusterUuid(queryTreeObj);
				String vmUuids = "";
				for (int i = 0; i < resultList.size(); i++) {
					XenEntityTreeObj obj = (XenEntityTreeObj) resultList.get(i);
					if (i != resultList.size() - 1) {
						vmUuids = vmUuids + obj.getUuid() + ",";
					} else {
						vmUuids = vmUuids + obj.getUuid();
					}
				}
				String xen_url = "/xen/vms/uuids/lookup/[poolUuid:" + queryTreeObj.getC_uuid()
						+ "].[vmUuids:" + vmUuids + "]/";
				String xen_result = InvokeUtil.invoke(xen_url);
				JSONArray jo = JSONArray.fromObject(xen_result);
				List<JSONObject> hostSList = JSONArray.toList(jo, JSONObject.class);
				List<XenEntityTreeObj> matechList = new ArrayList<XenEntityTreeObj>();
				if (hostSList.get(0).get("responseCode").equals("1")) {
					hostSList.remove(0);
					for (int i = 0; i < resultList.size(); i++) {
						XenEntityTreeObj obj = (XenEntityTreeObj) resultList.get(i);
						if (obj.getType().equals(TypeConstant.XEN_VM)) {
							for (int j = 0; j < hostSList.size(); j++) {
								JSONObject json = hostSList.get(j);
								if (json.get("vmUuid").equals(obj.getUuid())) {
									obj.setState(json.get("powerState").toString());
									obj.setName(json.get("vmName").toString());
									JSONArray oper = json.getJSONArray("currentOperations");
									if (oper.size() != 0) {
										obj.setOperation(true);
									} else {
										obj.setOperation(false);
									}
									matechList.add(obj);
									break;
								}
							}
						} else if (obj.getType().equals(TypeConstant.XEN_HOST_STORAGE)) {
							matechList.add(obj);
						}

					}
				}
				resultList = matechList;
			}
			// 刷新集群下虚拟机状态
			if (type.equals("29")) {
				XenEntityTreeObj queryTreeObj = new XenEntityTreeObj();
				queryTreeObj.setUuid(treeUuid);
				queryTreeObj = xenEntityTreeService.queryTreeNode(queryTreeObj);
				String vmUuids = "";
				for (int i = 0; i < resultList.size(); i++) {
					XenEntityTreeObj obj = (XenEntityTreeObj) resultList.get(i);
					if (obj.getType().equals("27")) {
						if (i != resultList.size() - 1) {
							vmUuids = vmUuids + obj.getUuid() + ",";
						} else {
							vmUuids = vmUuids + obj.getUuid();
						}
					}
				}
				if (!vmUuids.equals("") && vmUuids.indexOf(",") != -1) {
					String xen_url = "/xen/vms/uuids/lookup/[poolUuid:" + queryTreeObj.getUuid()
							+ "].[vmUuids:" + vmUuids + "]/";
					String xen_result = InvokeUtil.invoke(xen_url);
					JSONArray jo = JSONArray.fromObject(xen_result);
					List<JSONObject> hostSList = JSONArray.toList(jo, JSONObject.class);
					List<XenEntityTreeObj> matechList = new ArrayList<XenEntityTreeObj>();
					if (hostSList.get(0).get("responseCode").equals("1")) {
						hostSList.remove(0);
						for (int i = 0; i < resultList.size(); i++) {
							XenEntityTreeObj obj = (XenEntityTreeObj) resultList.get(i);
							if (obj.getType().equals("27")) {
								for (int j = 0; j < hostSList.size(); j++) {
									JSONObject json = hostSList.get(j);
									if (json.get("vmUuid").equals(obj.getUuid())) {
										obj.setState(json.get("powerState").toString());
										obj.setName(json.get("vmName").toString());
										JSONArray oper = json.getJSONArray("currentOperations");
										if (oper.size() != 0) {
											obj.setOperation(true);
										} else {
											obj.setOperation(false);
										}
										matechList.add(obj);
									}
								}
							} else {
								matechList.add(obj);
							}

						}
					}
					resultList = matechList;
				}
			}
		}

		List<XenTreeObj> list = new ArrayList<XenTreeObj>();
		if (list != null) {
			XenEntityTreeObj tempObj = new XenEntityTreeObj();
			for (XenEntityTreeObj obj : resultList) {
				// 暂不显示资源池
				if (obj.getType().equals("4")) {
					continue;
				}
				XenTreeObj tObj = new XenTreeObj();
				tObj.setId(obj.getId());
				tObj.setName(obj.getName());
				tObj.setType(obj.getType());
				tObj.setEntityId(obj.getEntityId());
				tObj.setAuthority(obj.getAuthority());
				// 判断是不是父节点
				tempObj.setParentId(obj.getId());
				tempObj.setUserId(session.get("id").toString());
				List<XenEntityTreeObj> lst = xenEntityTreeService.queryForTree(tempObj);
				if (lst == null || lst.size() == 0) {
					tObj.setIsParent(false);
				}

				if (obj.getType().equals(TypeConstant.XEN_DATACENTER)) {
					tObj.setType(TypeConstant.XEN_DATACENTER);
					tObj.setIcon("sxcloud/images/virtual/Datacenter.png");
				}

				if (obj.getType().equals(TypeConstant.XEN_HOST_STORAGE)) {// 存储
					tObj.setTree_uuid(obj.getUuid());
					XenStoreObj storeObj = new XenStoreObj();
					storeObj.setStore_uuid(obj.getUuid());
					List storeLst = xenStoreService.queryForListByObj(storeObj);
					if (storeLst != null && storeLst.size() > 0) {
						storeObj = (XenStoreObj) storeLst.get(0);
						if (storeObj != null && storeObj.getState() != null) {
							if (storeObj.getState().equals("connection")) {// 连接
								tObj.setState("connection");
								tObj.setIcon("sxcloud/images/virtual/storage.png");
							} else if (storeObj.getState().equals("detach")) {// 分离
								tObj.setState("detach");
								tObj.setIcon("sxcloud/images/virtual/detachStore.png");
							} else if (storeObj.getState().equals("destroy")) {// 损坏
								tObj.setState("destroy");
								tObj.setIcon("sxcloud/images/virtual/damagedStore.png");
							}
						} else {
							// 状态未知，暂先设成连接，有图片后再改
							tObj.setState("localStore");
							tObj.setIcon("sxcloud/images/virtual/storage.png");
						}
						if (obj.getName().equals("DVD drives")) {
							tObj.setName("DVD 驱动器");
						} else if (obj.getName().equals("Local storage")) {
							tObj.setName("本地存储");
						} else if (obj.getName().equals("Removable storage")) {
							tObj.setName("可移动存储");
						}
						if (storeObj.getType().equals("LVM") || storeObj.getType().equals("UDEV")) {
							tObj.setState("localStore");// 本地存储不做操作
						}
					} else {
						// 存储状态未知，不做操作
						tObj.setState("localStore");
						tObj.setIcon("sxcloud/images/virtual/storage.png");
					}
				}

				if (obj.getType().equals(TypeConstant.XEN_CLUSTER)) { // 集群

					XenClusterObj cluster = new XenClusterObj();
					if (obj.getEntityId() != null && !"".equals(obj.getEntityId())) {
						cluster.setId(Integer.parseInt(obj.getEntityId()));
						cluster = xenClusterService.queryForCollection(cluster);
					}
					if (cluster.getC_uuid() != null && !"".equals(cluster.getC_uuid())) {
						tObj.setTree_uuid(cluster.getC_uuid());

						String cluster_param = "/xen/conn/verification/[poolUuid:"
								+ cluster.getC_uuid() + "]/";
						String cluster_result = InvokeUtil.invoke(cluster_param);
						if (cluster_result.equals("error")) {
							tObj.setIcon("sxcloud/images/virtual/cancel.png");
							tObj.setState("2");// 后台连接不通
							tObj.setIsParent(false);
						} else {
							JSONObject cluster_js = JSONObject.fromObject(cluster_result);
							if (cluster_js.get("responseCode").equals("1")) {
								tObj.setIcon("sxcloud/images/virtual/cluster.png");
								tObj.setState("0");// 集群处于链接状态
							} else {
								tObj.setIcon("sxcloud/images/virtual/delete.png");
								tObj.setState("1");// 集群处于断开状态
								tObj.setIsParent(false);
							}
						}

					}

					else {
						tObj.setIcon("sxcloud/images/virtual/cluster.png");
					}
				}

				if (obj.getType().equals(TypeConstant.XEN_HOST)) { // 主机

					// TbCloud2HostInfoObj host = new TbCloud2HostInfoObj();
					// host.setId(Integer.parseInt(obj.getEntityId()));
					// host = hostInfoService.queryByObj(host);
					tObj.setTree_uuid(obj.getUuid());
					tObj.setIcon("sxcloud/images/virtual/Host.png");
				}

				if (obj.getType().equals(TypeConstant.XEN_VM)) { // 虚拟机

					try {
						// tObj.setOperation(obj.getOperation());
						tObj.setTree_uuid(obj.getUuid());
						String entity_id = obj.getEntityId();
						XenEntityTreeObj ent = new XenEntityTreeObj();
						if (obj.getOperation()) {
							tObj.setIcon("sxcloud/images/virtual/opering.png");
							tObj.setState("3");// 虚拟机正在操作过程中
						} else {
							if (obj.getState().equals("error")) {
								tObj.setIcon("sxcloud/images/virtual/3alert.png");
							}
							if (obj.getState().equals("Halted")) {
								tObj.setIcon("sxcloud/images/virtual/VirtualMachine.png");
								tObj.setState("1");// 虚拟机关闭
							} else if (obj.getState().equals("Suspended")) {
								tObj.setIcon("sxcloud/images/virtual/paused.png");
								tObj.setState("2");// 虚拟机挂起
							} else if (obj.getState().equals("Running")) {
								tObj.setIcon("sxcloud/images/virtual/running.png");
								tObj.setState("0");// 虚拟机开启
							} // else {
								//
								// }
						}
					} catch (Exception e) {
						tObj.setIcon("sxcloud/images/virtual/VirtualMachine.png");
					}
				}

				if (obj.getType().equals("18")) {// 存储
					tObj.setIcon("sxcloud/images/virtual/storage.png");
				}
				if (obj.getType().equals("19")) {// 网络
					tObj.setIcon("sxcloud/images/virtual/network.png");

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
	 * @Title: vmtabs
	 * @Description: 单击时进入tab页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 21, 2012 10:28:53 AM
	 */
	public String vmtabs() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String hostId = request.getParameter("hostId");
		String oper = request.getParameter("oper");
		String ID = request.getParameter("ID");
		String type = request.getParameter("type");// 得到实体类型
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String parentName = request.getParameter("parentName");
		String tempId = request.getParameter("tempId");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String vm_uuid = request.getParameter("vm_uuid");
		request.setAttribute("hostId", hostId);
		request.setAttribute("oper", oper);
		request.setAttribute("ID", ID);
		request.setAttribute("type", type);
		request.setAttribute("id", id);
		request.setAttribute("name", name);
		request.setAttribute("parentName", parentName);
		request.setAttribute("tempId", tempId);
		request.setAttribute("pool_uuid", pool_uuid);
		request.setAttribute("host_uuid", host_uuid);
		request.setAttribute("vm_uuid", vm_uuid);
		return "vmtabs";
	}

	/**
	 * 
	 * @Title: dataCenterInfo
	 * @Description: 单击时查看数据中心信息
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime May 21, 2012 10:28:53 AM
	 */
	public String dataCenterInfo() {
		if (theForm == null) {
			theForm = new XenTreeForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		if (id != null && !"".equals(id)) {
			XenEntityTreeObj datacenterObj = new XenEntityTreeObj();
			datacenterObj.setParentId(Integer.parseInt(id));
			int hostCount = 0;// 主机个数
			int vmCount = 0;// 虚拟机个数
			int clusterCount = 0;// 集群个数
			String vmIds = "";
			List daList = xenEntityTreeService.queryForTree(datacenterObj);
			if (daList != null && daList.size() > 0) {
				for (int i = 0; i < daList.size(); i++) {
					XenEntityTreeObj SecondObj = (XenEntityTreeObj) daList.get(i);
					if (SecondObj.getType().equals(TypeConstant.XEN_CLUSTER)) {
						clusterCount++;
						XenEntityTreeObj clusterObj = new XenEntityTreeObj();
						clusterObj.setParentId(SecondObj.getId());
						List clList = xenEntityTreeService.queryForTree(clusterObj);
						if (clList != null && clList.size() > 0) {
							for (int j = 0; j < clList.size(); j++) {
								XenEntityTreeObj thirdObj = (XenEntityTreeObj) clList.get(j);
								if (thirdObj.getType().equals(TypeConstant.XEN_HOST)) {
									hostCount++;
									XenEntityTreeObj hostObj = new XenEntityTreeObj();
									hostObj.setParentId(thirdObj.getId());
									List hostLst = xenEntityTreeService.queryForTree(hostObj);
									if (hostLst != null && hostLst.size() > 0) {
										for (int k = 0; k < hostLst.size(); k++) {
											XenEntityTreeObj fourthObj = (XenEntityTreeObj) hostLst
													.get(k);
											if (fourthObj.getType().equals(TypeConstant.XEN_VM)) {
												vmCount++;
												vmIds += fourthObj.getEntityId() + ",";
											}
										}
									}
								} else if (thirdObj.getType().equals(TypeConstant.XEN_VM)) {
									vmCount++;
									vmIds += thirdObj.getEntityId() + ",";
								}
							}
						}
					} else if (SecondObj.getType().equals(TypeConstant.XEN_HOST)) {
						hostCount++;
						XenEntityTreeObj hostObj = new XenEntityTreeObj();
						hostObj.setParentId(SecondObj.getId());
						List hostLst = xenEntityTreeService.queryForTree(hostObj);
						if (hostLst != null && hostLst.size() > 0) {
							for (int k = 0; k < hostLst.size(); k++) {
								XenEntityTreeObj fourthObj = (XenEntityTreeObj) hostLst.get(k);
								if (fourthObj.getType().equals(TypeConstant.XEN_VM)) {
									vmCount++;
									vmIds += fourthObj.getEntityId() + ",";
								}
							}
						}
					} else if (SecondObj.getType().equals(TypeConstant.XEN_VM)) {
						vmCount++;
						vmIds += SecondObj.getEntityId() + ",";
					}
				}
			}
			if (vmIds != null && vmIds.length() > 1) {
				vmIds = vmIds.substring(0, vmIds.length() - 1);
			}
			XenEntityConObj conObj = new XenEntityConObj();
			conObj.setDataCenterId(Integer.parseInt(id));
			// int hostCount = xenEntityTreeService.queryHostCount(conObj);//
			// 主机个数
			// int vmCount = xenEntityTreeService.queryVmCount(conObj);// 虚拟机个数
			// int clusterCount =
			// xenEntityTreeService.queryClusterCount(tree);// 集群个数
			conObj.setType(19);
			int netCount = xenEntityTreeService.queryNetOrStoreCount(conObj);// 网络个数
			conObj.setType(18);
			int dsCount = xenEntityTreeService.queryNetOrStoreCount(conObj);// 存储个数
			request.setAttribute("hostCount", hostCount);
			request.setAttribute("vmCount", vmCount);
			request.setAttribute("clusterCount", clusterCount);
			request.setAttribute("netCount", netCount);
			request.setAttribute("dsCount", dsCount);
		}
		return "dataCenterInfo";
	}

	/**
	 * 
	 * @Title: dataCenterVM
	 * @Description: 单击时查看数据中心中的虚拟机信息
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime May 21, 2012 10:28:53 AM
	 */
	public String dataCenterVM() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		if (type != null && !"".equals(type)) {
			XenEntityConObj obj = new XenEntityConObj();
			obj.setDataCenterId(Integer.parseInt(id));
			obj.setTemp(0); // VMWARE_VM
			obj.setType(24); // KVM_VM
			obj.setTemp_type(27);// xen_vm
			List resultList = xenEntityTreeService.queryEntityInfo(obj);
			if (theForm == null) {
				theForm = new XenTreeForm();
			}
			theForm.setResultList(resultList);
		}
		return "dataCenterVM";
	}

	/**
	 * 
	 * @Title: dataCenterHost
	 * @Description: 单击时查看数据中心中的主机信息
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime May 21, 2012 10:28:53 AM
	 */
	public String dataCenterHost() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		if (type != null && !"".equals(type)) {
			XenEntityConObj obj = new XenEntityConObj();
			obj.setDataCenterId(Integer.parseInt(id));
			obj.setTemp(1);// VMWARE_HOST
			obj.setType(25);// KVM_HOST
			obj.setTemp_type(26);// xen_host
			List resultList = xenEntityTreeService.queryEntityInfo(obj);
			if (theForm == null) {
				theForm = new XenTreeForm();
			}
			theForm.setResultList(resultList);
		}
		return "dataCenterHost";
	}

	/**
	 * 
	 * @Title: clusterVM
	 * @Description: 单击时查看集群中的虚拟机信息
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime May 21, 2012 10:28:53 AM
	 */
	public String clusterVM() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String type = request.getParameter("type");
		String id = request.getParameter("id");// entityId
		if (type != null && !"".equals(type)) {
			XenEntityConObj obj = new XenEntityConObj();
			if (type.equals("3")) {
				// 查询集群id
				XenClusterObj cObj = new XenClusterObj();
				cObj.setC_uuid(id);
				cObj = xenClusterService.queryByObj(cObj);
				obj.setClusterId(cObj.getId());
				obj.setType(0); // VMWARE_VM
			} else if (type.equals("21")) {
				obj.setClusterId(Integer.parseInt(id));
				obj.setType(24);// KVM_VM
			} else {
				obj.setClusterId(Integer.parseInt(id));
				obj.setType(27);// xen_vm
			}
			// obj.setTemp(0); // VMWARE_VM
			// /obj.setType(24); // KVM_VM
			// obj.setTemp_type(27);// xen_vm
			List resultList = xenEntityTreeService.queryEntityInfo(obj);
			if (theForm == null) {
				theForm = new XenTreeForm();
			}
			theForm.setResultList(resultList);
		}
		return "dataCenterVM";
	}

	/**
	 * 
	 * @Title: hostVM
	 * @Description: 单击时查看主机中的虚拟机信息
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime May 21, 2012 10:28:53 AM
	 */
	public String hostVM() {

		String id = Struts2Utils.getParameter("id");
		String type = Struts2Utils.getParameter("type");
		XenEntityTreeObj obj = new XenEntityTreeObj();
		obj.setParentId(Integer.parseInt(id));
		if (type.equals("1")) {
			obj.setType(TypeConstant.VMWARE_VM); // VMWARE_VM
		} else if (type.equals("21")) {
			obj.setType(TypeConstant.KVM_VM);// KVM_VM
		} else {
			obj.setType(TypeConstant.XEN_VM);// xen_vm
		}

		List<XenEntityConObj> resultList = new ArrayList<XenEntityConObj>();
		List<XenEntityTreeObj> list = xenEntityTreeService.queryForTree(obj);
		for (XenEntityTreeObj tempObj : list) {
			String entityId = tempObj.getEntityId();
			XenEntityConObj eObj = new XenEntityConObj();
			eObj.setEntityId(entityId);
			eObj.setType(Integer.parseInt(obj.getType()));
			eObj = xenEntityTreeService.queryEntityInfoByObj(eObj);
			resultList.add(eObj);
		}
		if (theForm == null) {
			theForm = new XenTreeForm();
		}
		theForm.setResultList(resultList);
		return "dataCenterVM";
	}

	/**
	 * 
	 * @Title: clusterHost
	 * @Description: 单击时查看集群中的主机信息
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime May 21, 2012 10:28:53 AM
	 */
	public String clusterHost() {
		if (theForm == null) {
			theForm = new XenTreeForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String pool_uuid = request.getParameter("pool_uuid");
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		if (type != null && !"".equals(type)) {
			XenEntityConObj obj = new XenEntityConObj();
			obj.setClusterId(Integer.parseInt(id));
			obj.setType(26);// xen_host
			List<XenEntityConObj> resultList = xenEntityTreeService.queryEntityInfo(obj);
			List<TbCloud2HostInfoObj> list = new ArrayList<TbCloud2HostInfoObj>();
			for (XenEntityConObj x : resultList) {
				TbCloud2HostInfoObj t = new TbCloud2HostInfoObj();
				t.setId(Integer.parseInt(x.getEntityId()));
				t = hostInfoService.queryByObj(t);
				/**
				 * ------------------------------------------------------------
				 * --------------接口提供获取池下所有主机信息和性能 循环太慢
				 */
				String param = "/xen/host/monitoring/performance/[poolUuid:" + pool_uuid
						+ "].[hostUuid:" + t.getH_uuid() + "]/";
				String result = InvokeUtil.invoke(param);
				JSONObject js = JSONObject.fromObject(result);
				if ("1".equals(js.get("responseCode").toString())) {
					DecimalFormat nf = new DecimalFormat("0");
					TbCloud2HostInfoObj tb = new TbCloud2HostInfoObj();
					double cpuUtilization = Double.parseDouble(js.get("cpuUtilization").toString());
					tb.setCpu_fq(String.valueOf(nf.format(cpuUtilization)));
					tb.setCpu_cl(js.getString("numCPUs"));
					double memTotal = Double.parseDouble(js.get("totalMemoryKBs").toString()) / 1024;
					tb.setMem(String.valueOf(nf.format(memTotal)));
					double memFree = Double.parseDouble(js.get("freeMemoryKBs").toString()) / 1024;
					tb.setMemUsage(String.valueOf(nf.format(memTotal - memFree)));
					tb.setEq_name(t.getEq_name());
					list.add(tb);
					theForm.setResultList(list);
				}
			}
		}
		return "dataCenterHost";
	}

	/**
	 * 
	 * @Title: clusterInfo
	 * @Description: 单击时查看集群摘要信息
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime May 21, 2012 10:28:53 AM
	 */
	public String clusterInfo() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		if (id != null && !"".equals(id)) {
			// XenEntityConObj conObj = new XenEntityConObj();
			// /*
			// * XenClusterObj cObj = new XenClusterObj(); cObj.setC_uuid(id);
			// * cObj = xenClusterService.queryByObj(cObj);
			// */
			// conObj.setClusterId(Integer.parseInt(id));
			// int hostCount = xenEntityTreeService.queryHostCount(conObj);//
			// 主机个数
			// int vmCount = xenEntityTreeService.queryVmCount(conObj);// 虚拟机个数
			// request.setAttribute("hostCount", hostCount);
			// request.setAttribute("vmCount", vmCount);
			int hostCount = 0;// 主机个数
			int vmCount = 0;// 虚拟机个数
			XenEntityTreeObj clusterObj = new XenEntityTreeObj();
			clusterObj.setParentId(Integer.parseInt(id));
			List clList = xenEntityTreeService.queryForTree(clusterObj);
			if (clList != null && clList.size() > 0) {
				for (int j = 0; j < clList.size(); j++) {
					XenEntityTreeObj thirdObj = (XenEntityTreeObj) clList.get(j);
					if (thirdObj.getType().equals(TypeConstant.XEN_HOST)) {
						hostCount++;
						XenEntityTreeObj hostObj = new XenEntityTreeObj();
						hostObj.setParentId(thirdObj.getId());
						List hostLst = xenEntityTreeService.queryForTree(hostObj);
						if (hostLst != null && hostLst.size() > 0) {
							for (int k = 0; k < hostLst.size(); k++) {
								XenEntityTreeObj fourthObj = (XenEntityTreeObj) hostLst.get(k);
								if (fourthObj.getType().equals(TypeConstant.XEN_VM)) {
									vmCount++;
								}
							}
						}
					} else if (thirdObj.getType().equals(TypeConstant.XEN_VM)) {
						vmCount++;
					}
				}
			}
			request.setAttribute("hostCount", hostCount);
			request.setAttribute("vmCount", vmCount);
		}
		return "clusterInfo";
	}

	/**
	 * 
	 * @Title: hostInfo
	 * @Description: 单击时查看主机摘要信息
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime May 21, 2012 10:28:53 AM
	 */
	public String hostInfo() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String entityId = request.getParameter("id");// 此id为entityId,根据entityId查询相应的主机，虚拟机信息。
		String type = request.getParameter("type");
		XenEntityConObj cObj = new XenEntityConObj();
		cObj.setEntityId(entityId);
		cObj.setType(Integer.parseInt(type));
		cObj = xenEntityTreeService.queryEntityInfoByObj(cObj);
		TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
		hostObj.setH_uuid(entityId);
		hostObj = hostInfoService.queryByObj(hostObj);
		if (theForm == null) {
			theForm = new XenTreeForm();
		}
		theForm.setMODEL(hostObj.getMODEL());
		theForm.setHOST_PROC(hostObj.getHOST_PROC());
		theForm.setCpu(hostObj.getCpu_cl() + "个CPU × " + hostObj.getCpu_fq() + "GHz");
		theForm.setCPU_DESC(hostObj.getCPU_DESC());
		theForm.setNIC_NUM(hostObj.getNIC_NUM());
		theForm.setHost_status(hostObj.getSTATUS());
		double mem = Double.parseDouble(hostObj.getMem()) / 1024 / 1024.0;
		DecimalFormat dec = new DecimalFormat("0.00");
		theForm.setMem(dec.format(mem));
		return "hostInfo";
	}

	/**
	 * 
	 * @Title: addCluster
	 * @Description: 增加集群
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 10:57:45 AM
	 */
	public String addCluster() {
		if (theForm == null) {
			theForm = new XenTreeForm();
		}
		String ID = Struts2Utils.getParameter("id");
		String TYPE = Struts2Utils.getParameter("TYPE");
		HttpServletRequest request = Struts2Utils.getRequest();
		request.setAttribute("ID", ID);
		request.setAttribute("TYPE", TYPE);
		return "add_cluster";
	}

	/**
	 * 
	 * @Title: delTreeNode
	 * @Description: 删除选定树节点
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 18, 2012 9:15:46 AM
	 */
	public String delTreeNode() {
		String ID = Struts2Utils.getParameter("ID");
		HttpServletResponse response = Struts2Utils.getResponse();
		XenEntityTreeObj treeObj = new XenEntityTreeObj();
		XenEntityTreeObj obj = new XenEntityTreeObj();
		obj.setId(Integer.parseInt(ID));
		treeObj.setParentId(Integer.parseInt(ID));
		List<XenEntityTreeObj> resultList = xenEntityTreeService.queryForTree(treeObj);
		String result = null;
		if (resultList != null && resultList.size() > 0) {
			result = "1";
		} else {
			int ret = xenEntityTreeService.delTreeNode(obj);
		}
		result = "{'result':'" + result + "'}";
		/** 将返回值装到JSON传到页面 */
		response.setContentType("type/html;charset=UTF-8");
		JSONObject jo = (JSONObject) JSONObject.fromObject(result);
		// out = response.getWriter();
		// out.println(jo.toString());
		// out.close();
		PrintWriterOut.printWirter(response, jo.toString());
		return null;
	}

	/**
	 * 
	 * @Title: clusterResource
	 * @Description: 查看集群中资源分配
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 18, 2012 9:18:12 AM
	 */
	public String clusterResource() {
		return "clusterResource";
	}

	/**
	 * 
	 * @Title: hostTask
	 * @Description: 查看主机，虚拟机任务
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 18, 2012 9:18:12 AM
	 */
	public String hostTask() {
		if (theForm == null) {
			theForm = new XenTreeForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String entity_id = request.getParameter("entity_id");
		List list = new ArrayList();
		if (type.equals("26")) {// xen主机
			XenEntityTreeObj t = new XenEntityTreeObj();
			t.setEntityId(entity_id);
			t = xenEntityTreeService.queryTreeNode(t);
			XenEntityTreeObj en = new XenEntityTreeObj();
			en.setParentId(t.getId());
			List<XenEntityTreeObj> vmList = xenEntityTreeService.queryForTree(en);
			if (vmList != null) {
				for (XenEntityTreeObj et : vmList) {
					String vm_entityId = et.getEntityId();
					VMHostObj v = new VMHostObj();
					v.setID(Integer.parseInt(vm_entityId));
					v = vmHostService.queryByObj(v);
					TaskObj e = new TaskObj();
					if (v != null) {
						e.setTask_uuid(v.getVH_UUID());
						List<TaskObj> taskList = taskEventService.queryForTaskList(e);
						if (taskList != null) {
							list.addAll(taskList);
						}
					}
				}
			}
			if (list.size() > 10) {
				list = list.subList(0, 10);
			}
			theForm.setResultList(list);
			return "xen_task";
		} else if (type.equals("27") || type.equals("28")) {// xen虚拟机或者模版
			VMHostObj v = new VMHostObj();
			v.setID(Integer.parseInt(entity_id));
			v = vmHostService.queryByObj(v);
			TaskObj e = new TaskObj();
			e.setTask_uuid(v.getVH_UUID());
			list = taskEventService.queryForTaskList(e);
			theForm.setResultList(list);
			return "xen_task";
		} else {
			list = xenEntityTreeService.taskInfo(type, name);
			theForm.setResultList(list);
			return "hostTask";
		}

	}

	/**
	 * 
	 * @Title: hostEvent
	 * @Description: 查看主机，虚拟机任务
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 18, 2012 9:18:12 AM
	 */
	public String hostEvent() {
		if (theForm == null) {
			theForm = new XenTreeForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String entity_id = request.getParameter("entity_id");
		List list = new ArrayList();
		if (type.equals("26")) {
			XenEntityTreeObj t = new XenEntityTreeObj();
			t.setEntityId(entity_id);
			t = xenEntityTreeService.queryTreeNode(t);
			XenEntityTreeObj en = new XenEntityTreeObj();
			en.setParentId(t.getId());
			List<XenEntityTreeObj> vmList = xenEntityTreeService.queryForTree(en);
			for (XenEntityTreeObj et : vmList) {
				String vm_entityId = et.getEntityId();
				VMHostObj v = new VMHostObj();
				v.setID(Integer.parseInt(vm_entityId));
				v = vmHostService.queryByObj(v);
				EventObj e = new EventObj();
				if (v != null) {
					e.setEvent_uuid(v.getVH_UUID());
					List<TaskObj> taskList = taskEventService.queryForEventList(e);
					list.addAll(taskList);
				}
			}
			theForm.setResultList(list);
			return "xen_event";

		} else if (type.equals("27") || type.equals("28")) {
			VMHostObj v = new VMHostObj();
			v.setID(Integer.parseInt(entity_id));
			v = vmHostService.queryByObj(v);
			EventObj e = new EventObj();
			e.setEvent_uuid(v.getVH_UUID());
			list = taskEventService.queryForEventList(e);
			theForm.setResultList(list);
			return "xen_event";
		} else if (type.equals("35")) {
			/**
			 * 存储的事件展示，待开发
			 */
			return "xen_event";
		} else {
			list = xenEntityTreeService.eventInfo(type, name);
			theForm.setResultList(list);
			return "hostEvent";
		}

	}

	/**
	 * 
	 * @Title: updateNodeName
	 * @Description: 对节点名称进行重命名
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 2, 2012 1:57:49 PM
	 */
	public String updateNodeName() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		name = URLDecoder.decode(name, "utf-8");
		String result = "";
		XenEntityTreeObj obj = new XenEntityTreeObj();
		obj.setId(Integer.parseInt(id));
		obj = xenEntityTreeService.queryTreeNode(obj);
		int ret = -1;
		if (type.equals(TypeConstant.VMWARE_VM)) {
			String url = "/vmware/domain/rename/[vmName:" + obj.getName() + "].[newName:" + name
					+ "]/";
			String rename = InvokeUtil.invoke(url);
			Map ps = ParamParser.makeup(rename);
			String code = (String) ps.get("responseCode");
			if (code.equals("1")) {
				result = null;
				// 更新entitytree表
				obj.setName(name);
				obj.setType(TypeConstant.VMWARE_VM);
				ret = xenEntityTreeService.updateTreeNode(obj);
				// 更新虚拟机相关表
				VMHostObj vm = new VMHostObj();
				String entityId = obj.getEntityId();
				vm.setVH_ID(entityId);
				vm.setVH_NAME(name);
				vmHostService.updateName(vm);
				// 更新entitytreecon表
				XenEntityConObj cObj = new XenEntityConObj();
				cObj.setName(name);
				cObj.setType(Integer.parseInt(TypeConstant.VMWARE_VM));
				cObj.setEntityId(obj.getEntityId());
				xenEntityTreeService.updateConObj(cObj);
			}
		} else {
			obj.setName(name);// 更新节点名称
			obj.setType(type);
			ret = xenEntityTreeService.updateTreeNode(obj);
		}
		VMManagerObj v = new VMManagerObj();

		v.setNAME_EN(name);

		// 更新与主机相关的表
		if (type.equals(TypeConstant.KVM_HOST) || type.equals(TypeConstant.VMWARE_HOST)) {
			String entityId = obj.getEntityId();
			v.setID(Integer.parseInt(entityId));
			int ret1 = kvmManService.updateByObj(v);
			if (ret != -1 && ret1 != -1) {
				result = null;
			}
		}// 更新与虚拟机相关的表
		else if (type.equals(TypeConstant.KVM_VM)) {
			int ret2 = kvmManService.updateByObj(v);
			VMHostObj vm = new VMHostObj();
			String entityId = obj.getEntityId();
			vm.setVH_ID(entityId);
			vm.setVH_NAME(name);
			int ret3 = vmHostService.updateName(vm);
			if (ret != -1 && ret2 != -1 && ret3 != -1) {
				result = null;
			}
		}// 更新与镜像相关的表
		else if (type.equals(TypeConstant.KVM_IMAGE) || type.equals(TypeConstant.VMWARE_IMAGE)) {
			TbCloud2ImageInfoObj tb = new TbCloud2ImageInfoObj();
			String entityId = obj.getEntityId();
			tb.setIM_ID(entityId);
			tb.setIM_NAME(name);
			int ret4 = imageService.updateName(tb);
			if (ret != -1 && ret4 != -1) {
				result = null;
			}
		} else {
			if (ret != -1) {
				result = null;
			}
		}
		result = "{'result':'" + result + "'}";
		/** 将返回值装到JSON传到页面 */
		response.setContentType("type/html;charset=UTF-8");
		JSONObject jo = (JSONObject) JSONObject.fromObject(result);
		// out = response.getWriter();
		// out.println(jo.toString());
		// out.close();
		PrintWriterOut.printWirter(response, jo.toString());
		return null;
	}

}
