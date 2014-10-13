package com.sitech.basd.yicloud.web.entitytree.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sitech.basd.common.ResponseCode;
import com.sitech.basd.sxcloud.cloud.domain.image.TbCloud2ImageInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.monitor.MonitorAlarmObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.cloud.service.image.ImageService;
import com.sitech.basd.sxcloud.cloud.service.monitor.MonitorService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.cloud.web.image.form.ImageForm;
import com.sitech.basd.sxcloud.rsmu.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployVideoCommandsetObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.VideoExampleService;
import com.sitech.basd.sxcloud.rsmu.service.system.OperationService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.dao.vmauthority.VmAuthorityDao;
import com.sitech.basd.yicloud.domain.cluster.ClusterObj;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.basd.yicloud.domain.entitytree.EntityConObj;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.domain.entitytree.SolutionObj;
import com.sitech.basd.yicloud.domain.entitytree.TreeObj;
import com.sitech.basd.yicloud.domain.kvm.VMManagerObj;
import com.sitech.basd.yicloud.domain.nic.NicObj;
import com.sitech.basd.yicloud.domain.nic.NicRelationObj;
import com.sitech.basd.yicloud.domain.nic.VirtualNicObj;
import com.sitech.basd.yicloud.domain.portgroup.PortGroup;
import com.sitech.basd.yicloud.domain.script.ScriptConObj;
import com.sitech.basd.yicloud.domain.script.ScriptObj;
import com.sitech.basd.yicloud.domain.taskevent.EventObj;
import com.sitech.basd.yicloud.domain.taskevent.TaskObj;
import com.sitech.basd.yicloud.domain.templettree.TempletTreeObj;
import com.sitech.basd.yicloud.domain.vmauthority.VmAuthorityObj;
import com.sitech.basd.yicloud.service.cluster.ClusterService;
import com.sitech.basd.yicloud.service.entitytree.EntityTreeService;
import com.sitech.basd.yicloud.service.kvm.KvmManService;
import com.sitech.basd.yicloud.service.nic.NicRelationService;
import com.sitech.basd.yicloud.service.nic.NicService;
import com.sitech.basd.yicloud.service.nic.VirtualNicService;
import com.sitech.basd.yicloud.service.portgroup.PortGroupService;
import com.sitech.basd.yicloud.service.script.ScriptService;
import com.sitech.basd.yicloud.service.taskevent.TaskEventService;
import com.sitech.basd.yicloud.service.templettree.TempletTreeService;
import com.sitech.basd.yicloud.service.vmauthority.VmAuthorityService;
import com.sitech.basd.yicloud.service.vmware.VmwManService;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.JsonUtils;
import com.sitech.basd.yicloud.util.PageUtil;
import com.sitech.basd.yicloud.util.ParamParser;
import com.sitech.basd.yicloud.util.TypeConstant;
import com.sitech.basd.yicloud.util.systemlog.MethodLog;
import com.sitech.basd.yicloud.web.entitytree.form.EntityTreeForm;
import com.sitech.basd.yicloud.web.vmsyndata.action.DataSynchron;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.ws.Operation;
import com.sitech.ws.web.NoticeUtil;

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
public class EntityTreeAction extends CRUDBaseAction {
	private static Map<String, Map<String, Object>> vmStatMap = new HashMap<String, Map<String, Object>>();
	private Map<String, Object> map;
	private EntityTreeService entityTreeService;
	private EntityTreeForm theForm;
	private ImageForm imageForm;
	private KvmManService kvmManService;
	private VMHostService vmHostService;
	private ImageService imageService;
	private MonitorService monitorService;
	private VmwManService vmwManService;
	private VideoExampleService videoExampleService;
	private HostInfoService hostInfoService;
	private TaskEventService taskEventService;
	private OperationService operationService;
	private TempletTreeService templetTreeService;
	private ScriptService scriptService;
	private DataSynchron dataSynchron;
	private VmAuthorityService vmAuthorityService;
	private VmAuthorityDao vmAuthorityDao;
	private TbGlobalConfigService tbGlobalConfigService;
	private NicRelationService nicRelationService;
	private NicService nicService;
	private VirtualNicService virtualNicService;
	private PortGroupService portGroupService;

	private static List taskList = new ArrayList();

	public void setPortGroupService(PortGroupService portGroupService) {
		this.portGroupService = portGroupService;
	}

	public void setVirtualNicService(VirtualNicService virtualNicService) {
		this.virtualNicService = virtualNicService;
	}

	public void setNicService(NicService nicService) {
		this.nicService = nicService;
	}

	public void setNicRelationService(NicRelationService nicRelationService) {
		this.nicRelationService = nicRelationService;
	}

	public void setVmAuthorityDao(VmAuthorityDao vmAuthorityDao) {
		this.vmAuthorityDao = vmAuthorityDao;
	}

	public void setVmAuthorityService(VmAuthorityService vmAuthorityService) {
		this.vmAuthorityService = vmAuthorityService;
	}

	public void setDataSynchron(DataSynchron dataSynchron) {
		this.dataSynchron = dataSynchron;
	}

	public void setScriptService(ScriptService scriptService) {
		this.scriptService = scriptService;
	}

	public void setTempletTreeService(TempletTreeService templetTreeService) {
		this.templetTreeService = templetTreeService;
	}

	private ClusterService clusterService;

	public void setClusterService(ClusterService clusterService) {
		this.clusterService = clusterService;
	}

	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}

	public void setTaskEventService(TaskEventService taskEventService) {
		this.taskEventService = taskEventService;
	}

	public void setVideoExampleService(VideoExampleService videoExampleService) {
		this.videoExampleService = videoExampleService;
	}

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public void setVmwManService(VmwManService vmwManService) {
		this.vmwManService = vmwManService;
	}

	public void setMonitorService(MonitorService monitorService) {
		this.monitorService = monitorService;
	}

	public ImageForm getImageForm() {
		return imageForm;
	}

	public void setImageForm(ImageForm imageForm) {
		this.imageForm = imageForm;
	}

	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	public void setVmHostService(VMHostService vmHostService) {
		this.vmHostService = vmHostService;
	}

	public void setKvmManService(KvmManService kvmManService) {
		this.kvmManService = kvmManService;
	}

	public EntityTreeService getEntityTreeService() {
		return entityTreeService;
	}

	public void setEntityTreeService(EntityTreeService entityTreeService) {
		this.entityTreeService = entityTreeService;
	}

	public EntityTreeForm getTheForm() {
		return theForm;
	}

	public void setTheForm(EntityTreeForm theForm) {
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
	@MethodLog(remark = "EntityTreeAction-listVMService", type = 4, message = "获取虚拟机主机树")
	public String listVMService() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String FUNCSID = request.getParameter("FUNCSID");
		request.getSession().setAttribute(Constant.FUNCID, FUNCSID);
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
	@MethodLog(remark = "EntityTreeAction-asyncForTree", type = 4, message = "采用异步的方式生成树")
	public String asyncForTree() throws Exception, IOException {
		EntityTreeObj treeObj = new EntityTreeObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		String str = request.getParameter("id");
		String type = request.getParameter("type");
		String entityId = request.getParameter("entityId");
		// String userId = (String)
		// request.getSession().getAttribute(Constant.USER_SESSION_ID);
		// TbSysUserinfoObj userObj =
		// (TbSysUserinfoObj)request.getSession().getAttribute(Constant.USER_SESSION_KEY);

		// TbGlobalConfigObj tbObj = new TbGlobalConfigObj();
		// tbObj.setKEY("USER");
		// tbObj = tbGlobalConfigService.queryByObj(tbObj);
		/*
		 * Map<String, String> userMap = new HashMap<String, String>();
		 * userMap.put("admin", "admin"); if (tbObj != null) { String users =
		 * tbObj.getVALUE(); String[] user = users.split(","); for (String u :
		 * user) { if (u != null && !u.equals("")) { userMap.put(u, u); } } }
		 * 
		 * if (!userMap.containsKey(userObj.getACCOUNT())) {
		 * treeObj.setUserId(String.valueOf(userObj.getID())); }
		 */
		if (!session.get("account").toString().equals("admin")) {
			treeObj.setUserId(session.get("id").toString());
		}

		if (str == null || "".equals(str)) {
			treeObj.setType(TypeConstant.VMWARE_DATACENTER);
		} else {
			treeObj.setParentId(Integer.valueOf(str));
		}
		List<EntityTreeObj> resultList = new ArrayList<EntityTreeObj>();
		if (TypeConstant.VMWARE_HOST.equals(type)) {

			resultList = entityTreeService.queryForTree(treeObj);
			// 获取主机下虚拟机状态
			String vmStatUrl = "/vmware/domain/powerstate/[host_code:" + entityId + "]/";
			String statRestlt = InvokeUtil.invoke(vmStatUrl);
			JSONObject rsObj = JSONObject.fromObject(statRestlt);
			String rsCode = rsObj.getString("responseCode");
			if ("1".equals(rsCode)) {
				String rsMap = (String) rsObj.getString("result");
				vmStatMap.remove(entityId);
				Map<String, Object> tempMap = (HashMap<String, Object>) JsonUtils.jsonToBean(rsMap,
						HashMap.class);
				vmStatMap.put(entityId, tempMap);
			} else {

			}
		} else {
			resultList = entityTreeService.queryForTree(treeObj);
		}

		List<TreeObj> list = new ArrayList<TreeObj>();
		if (list != null) {
			EntityTreeObj tempObj = new EntityTreeObj();
			for (EntityTreeObj obj : resultList) {
				// 暂不显示资源池
				if (obj.getType().equals("4")) {
					continue;
				}
				TreeObj tObj = new TreeObj();
				tObj.setId(obj.getId());
				tObj.setName(obj.getName());
				tObj.setType(obj.getType());
				tObj.setEntityId(obj.getEntityId());
				tObj.setAuthority(obj.getAuthority());
				// 判断是不是父节点
				tempObj.setUserId(session.get("id").toString());
				tempObj.setParentId(obj.getId());
				List<EntityTreeObj> lst = entityTreeService.queryForTree(tempObj);
				if (lst == null || lst.size() == 0) {
					tObj.setIsParent(false);

				}
				// 设置图标
				if (obj.getType().equals(TypeConstant.VMWARE_DATACENTER)) {// 数据中心
					tObj.setIcon("sxcloud/images/virtual/Datacenter.png");
				}
				if (obj.getType().equals(TypeConstant.VMWARE_CLUSTER)
						|| obj.getType().equals(TypeConstant.KVM_CLUSTER)) { // 集群
					tObj.setIcon("sxcloud/images/virtual/cluster.png");

				}

				if (obj.getType().equals(TypeConstant.VMWARE_HOST)
						|| obj.getType().equals(TypeConstant.KVM_HOST)) { // 主机
					if (obj.getType().equals(TypeConstant.VMWARE_HOST)) { // vmware主机
						try {
							String statUrl = "/vmware/host/getModeMsg/[hostName:"
									+ obj.getEntityId() + "]/";
							String param = InvokeUtil.invoke(statUrl);
							if (param.equals("error")) {
								tObj.setIcon("sxcloud/images/virtual/3alert.png");
							} else {
								Map ps = ParamParser.makeup(param);
								String stat = (String) ps.get("mode");
								if (stat == null) {// 没有主机
									tObj.setIcon("sxcloud/images/virtual/delete.png");
								} else {
									if (stat.equals("true")) {
										tObj.setState("0");// 主机进入维护模式
										tObj.setIcon("sxcloud/images/virtual/maintenance.png");
									} else if (stat.equals("false")) {
										tObj.setState("1");// 主机退出维护模式
										tObj.setIcon("sxcloud/images/virtual/Host.png");
									} else if (stat.equals("notResponding")) {
										tObj.setState("2");
										tObj.setName(obj.getName() + "(无响应)");
										tObj.setIcon("sxcloud/images/virtual/Host.png");
									}
								}
							}
							if (request.getSession().getAttribute("account").toString()
									.equals("admin")) {
								tObj.setAuthority("0");
							} else {
								VmAuthorityObj v = new VmAuthorityObj();
								v.setENTITY_ID(obj.getEntityId());
								v.setUSERID(Integer.parseInt(request.getSession()
										.getAttribute("id").toString()));
								List<VmAuthorityObj> auth = vmAuthorityService.queryForList(v);
								tObj.setAuthority(auth.get(0).getOPERAUTHORITY());
							}

						} catch (Exception e) {
							tObj.setIcon("sxcloud/images/virtual/Host.png");
						}
					} else {
						tObj.setIcon("sxcloud/images/virtual/Host.png");
					}
				}
				if (obj.getType().equals(TypeConstant.VMWARE_VM)
						|| obj.getType().equals(TypeConstant.KVM_VM)) { // 虚拟机
					if (obj.getType().equals(TypeConstant.VMWARE_VM)) {// vmware虚拟机
						// 检查虚拟机状态
						try {
							if (request.getSession().getAttribute("account").toString()
									.equals("admin")) {
								tObj.setAuthority("0");
							} else {
								VmAuthorityObj v = new VmAuthorityObj();
								v.setENTITY_ID(obj.getEntityId());
								v.setUSERID(Integer.parseInt(request.getSession()
										.getAttribute("id").toString()));
								List<VmAuthorityObj> auth = vmAuthorityService.queryForList(v);
								tObj.setAuthority(auth.get(0).getOPERAUTHORITY());
							}

							map = vmStatMap.get(entityId);
							String stat = (String) map.get(obj.getEntityId());
							String param = "";
							if (stat == null) {
								tObj.setIcon("sxcloud/images/virtual/3alert.png");
							} else {
								if (stat == null) {// 没有该虚拟机
									tObj.setIcon("sxcloud/images/virtual/delete.png");
								} else {
									if (stat.equals("poweredOn")) {
										tObj.setIcon("sxcloud/images/virtual/running.png");
										tObj.setState("0");// 虚拟机开启
									} else if (stat.equals("poweredOff")) {
										tObj.setIcon("sxcloud/images/virtual/VirtualMachine.png");
										tObj.setState("1");// 虚拟机关闭
									} else if (stat.equals("suspended")) {
										tObj.setIcon("sxcloud/images/virtual/paused.png");
										tObj.setState("2");// 虚拟机挂起
									}
								}
							}
						} catch (Exception e) {
							tObj.setIcon("sxcloud/images/virtual/VirtualMachine.png");
						}
					} else {
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
	@MethodLog(remark = "EntityTreeAction-dataCenterInfo", type = 4, message = "单击时查看数据中心信息")
	public String dataCenterInfo() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		if (id != null && !"".equals(id)) {
			EntityConObj conObj = new EntityConObj();
			conObj.setDataCenterId(Integer.parseInt(id));
			int hostCount = entityTreeService.queryHostCount(conObj);// 主机个数
			int vmCount = entityTreeService.queryVmCount(conObj);// 虚拟机个数
			int clusterCount = entityTreeService.queryClusterCount(conObj);// 集群个数
			conObj.setType(19);
			int netCount = entityTreeService.queryNetOrStoreCount(conObj);// 网络个数
			conObj.setType(18);
			int dsCount = entityTreeService.queryNetOrStoreCount(conObj);// 存储个数
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
	@MethodLog(remark = "EntityTreeAction-dataCenterVM", type = 4, message = "单击时查看数据中心中的虚拟机信息")
	public String dataCenterVM() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		if (type != null && !"".equals(type)) {
			EntityConObj obj = new EntityConObj();
			obj.setDataCenterId(Integer.parseInt(id));
			obj.setTemp(0); // VMWARE_VM
			obj.setType(24); // KVM_VM
			obj.setTemp_type(27);// xen_vm
			List resultList = entityTreeService.queryEntityInfo(obj);
			if (theForm == null) {
				theForm = new EntityTreeForm();
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
	@MethodLog(remark = "EntityTreeAction-dataCenterHost", type = 4, message = "单击时查看数据中心中的主机信息")
	public String dataCenterHost() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		if (type != null && !"".equals(type)) {
			EntityConObj obj = new EntityConObj();
			obj.setDataCenterId(Integer.parseInt(id));
			obj.setTemp(1);// VMWARE_HOST
			obj.setType(25);// KVM_HOST
			obj.setTemp_type(26);// xen_host
			List resultList = entityTreeService.queryEntityInfo(obj);
			if (theForm == null) {
				theForm = new EntityTreeForm();
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
	@MethodLog(remark = "EntityTreeAction-clusterVM", type = 4, message = "单击时查看集群中的虚拟机信息")
	public String clusterVM() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String type = request.getParameter("type");
		String id = request.getParameter("id");// entityId
		if (type != null && !"".equals(type)) {
			EntityConObj obj = new EntityConObj();
			if (type.equals("3")) {
				// 查询集群id
				ClusterObj cObj = new ClusterObj();
				cObj.setC_uuid(id);
				cObj = clusterService.queryByObj(cObj);
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
			List resultList = entityTreeService.queryEntityInfo(obj);
			if (theForm == null) {
				theForm = new EntityTreeForm();
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
	@MethodLog(remark = "EntityTreeAction-hostVM", type = 4, message = "单击时查看主机中的虚拟机信息")
	public String hostVM() {
		if (theForm == null) {
			theForm = new EntityTreeForm();
		}
		String id = Struts2Utils.getParameter("id");
		String type = Struts2Utils.getParameter("type");
		EntityTreeObj obj = new EntityTreeObj();
		obj.setParentId(Integer.parseInt(id));
		DecimalFormat nf = new DecimalFormat("0");
		if (type.equals("1")) {
			obj.setType(TypeConstant.VMWARE_VM); // VMWARE_VM
		} else if (type.equals("21")) {
			obj.setType(TypeConstant.KVM_VM);// KVM_VM
		} else {
			obj.setType(TypeConstant.XEN_VM);// xen_vm
		}

		List<VMHostObj> resultList = new ArrayList<VMHostObj>();
		List<EntityTreeObj> list = entityTreeService.queryForTree(obj);
		for (EntityTreeObj tempObj : list) {
			String entityId = tempObj.getEntityId();
			// EntityConObj eObj = new EntityConObj();
			// eObj.setEntityId(entityId);
			// eObj.setType(Integer.parseInt(obj.getType()));
			// eObj = entityTreeService.queryEntityInfoByObj(eObj);
			VMHostObj vObj = new VMHostObj();
			vObj.setVH_UUID(entityId);
			vObj = vmHostService.queryByObj(vObj);
			String store = vObj.getVH_STORAGE();
			double storeSize = Double.parseDouble(store) / (1024.0 * 1024.0);
			vObj.setVH_STORAGE(nf.format(storeSize));
			resultList.add(vObj);
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
	@MethodLog(remark = "EntityTreeAction-clusterHost", type = 4, message = "单击时查看集群中的主机信息")
	public String clusterHost() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		if (type != null && !"".equals(type)) {
			EntityConObj obj = new EntityConObj();
			if (type.equals("3")) {
				ClusterObj cObj = new ClusterObj();
				cObj.setC_uuid(id);
				cObj = clusterService.queryByObj(cObj);
				obj.setClusterId(cObj.getId());
				obj.setTemp(1); // VMWARE_HOST
			} else if (type.equals("21")) {
				obj.setClusterId(Integer.parseInt(id));
				obj.setTemp(25);// KVM_HOST
			} else {
				obj.setClusterId(Integer.parseInt(id));
				obj.setTemp(26);// xen_host
			}
			// obj.setTemp(1);// VMWARE_HOST
			// obj.setType(25);// KVM_HOST
			// obj.setTemp_type(26);// xen_host
			List resultList = entityTreeService.queryEntityInfo(obj);
			if (theForm == null) {
				theForm = new EntityTreeForm();
			}
			theForm.setResultList(resultList);
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
	@MethodLog(remark = "EntityTreeAction-clusterInfo", type = 4, message = "单击时查看集群摘要信息")
	public String clusterInfo() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		if (id != null && !"".equals(id)) {
			EntityConObj conObj = new EntityConObj();
			// ClusterObj cObj = new ClusterObj();
			// cObj.setC_uuid(id);
			// cObj = clusterService.queryByObj(cObj);
			conObj.setEntityId(id);
			// conObj.setClusterId(cObj.getId());
			int hostCount = entityTreeService.queryHostCount(conObj);// 主机个数
			int vmCount = entityTreeService.queryVmCount(conObj);// 虚拟机个数
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
	@MethodLog(remark = "EntityTreeAction-hostInfo", type = 4, message = "单击时查看主机摘要信息")
	public String hostInfo() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String entityId = request.getParameter("id");// 此id为entityId,根据entityId查询相应的主机，虚拟机信息。
		String type = request.getParameter("type");
		EntityConObj cObj = new EntityConObj();
		cObj.setEntityId(entityId);
		cObj.setType(Integer.parseInt(type));
		cObj = entityTreeService.queryEntityInfoByObj(cObj);
		TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
		hostObj.setH_uuid(entityId);
		hostObj = hostInfoService.queryByObj(hostObj);
		if (theForm == null) {
			theForm = new EntityTreeForm();
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
		theForm.setIp(hostObj.getEq_ip());
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
	@MethodLog(remark = "EntityTreeAction-addCluster", type = 1, message = "增加集群")
	public String addCluster() {
		if (theForm == null) {
			theForm = new EntityTreeForm();
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
	@MethodLog(remark = "EntityTreeAction-delTreeNode", type = 2, message = "删除选定树节点")
	public String delTreeNode() {
		String ID = Struts2Utils.getParameter("ID");
		HttpServletResponse response = Struts2Utils.getResponse();
		EntityTreeObj treeObj = new EntityTreeObj();
		EntityTreeObj obj = new EntityTreeObj();
		obj.setId(Integer.parseInt(ID));
		treeObj.setParentId(Integer.parseInt(ID));
		List<EntityTreeObj> resultList = entityTreeService.queryForTree(treeObj);
		String result = null;
		if (resultList != null && resultList.size() > 0) {
			result = "1";
		} else {
			int ret = entityTreeService.delTreeNode(obj);
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
	@MethodLog(remark = "EntityTreeAction-hostTask", type = 4, message = "查看主机，虚拟机任务")
	public String hostTask() {
		if (theForm == null) {
			theForm = new EntityTreeForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String entity_id = request.getParameter("entity_id");
		String pagesizestr = request.getParameter("pagesize");
		String pagenumstr = request.getParameter("num");
		int pagesize = 10;
		int pagenum = 1;
		if (pagesizestr != null && !"".equals(pagesizestr) && !"null".equals(pagesizestr)) {
			pagesize = Integer.parseInt(pagesizestr);
		}
		if (pagenumstr != null && !"".equals(pagenumstr) && !"null".equals(pagenumstr)) {
			pagenum = Integer.parseInt(pagenumstr);
		}
		List list = new ArrayList();
		if (type.equals("26")) {// xen主机
			EntityTreeObj t = new EntityTreeObj();
			t.setEntityId(entity_id);
			t = entityTreeService.queryTreeNode(t);
			EntityTreeObj en = new EntityTreeObj();
			en.setParentId(t.getId());
			List<EntityTreeObj> vmList = entityTreeService.queryForTree(en);
			for (EntityTreeObj et : vmList) {
				String vm_entityId = et.getEntityId();
				VMHostObj v = new VMHostObj();
				v.setID(Integer.parseInt(vm_entityId));
				v = vmHostService.queryByObj(v);
				TaskObj e = new TaskObj();
				e.setTask_uuid(v.getVH_UUID());
				List<TaskObj> taskList = taskEventService.queryForTaskList(e);
				list.addAll(taskList);
			}
			theForm.setResultList(list);
			return "xen_task";
		} else if (type.equals("27")) {// xen虚拟机
			VMHostObj v = new VMHostObj();
			v.setID(Integer.parseInt(entity_id));
			v = vmHostService.queryByObj(v);
			TaskObj e = new TaskObj();
			e.setTask_uuid(v.getVH_UUID());
			list = taskEventService.queryForTaskList(e);
			theForm.setResultList(list);
			return "xen_task";
		} else {
			if (pagenum == 1) {
				taskList.clear();
				list = entityTreeService.taskInfo(type, name, 200, 1);
				taskList.addAll(list);
			}
			PageUtil page = new PageUtil(pagenum, taskList.size(), pagesize);
			List pagelist = new ArrayList();
			if (taskList.size() > 10) {
				int startindex = (pagenum - 1) * pagesize;
				int endindex = pagenum * pagesize;
				for (int i = startindex; i < endindex; i++) {
					pagelist.add(taskList.get(i));
				}
			} else {
				pagelist.addAll(taskList);
			}
			// if (list == null) {
			// page = new PageUtil(pagenum, pagenum * pagesize, pagesize);
			// QueryTaskInfo info = new QueryTaskInfo();
			// info.setName("最后一行");
			// info.setStatus("最后一行");
			// info.setRequestTime("最后一行");
			// info.setCompleteTime("最后一行");
			// list = new ArrayList<QueryTaskInfo>();
			// list.add(info);
			// }
			page.setPageData(pagelist);

			// theForm.setResultList(list);
			request.setAttribute("page", page);
			request.setAttribute("type", type);
			request.setAttribute("name", name);
			request.setAttribute("entity_id", entity_id);
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
	@MethodLog(remark = "EntityTreeAction-hostEvent", type = 4, message = "查看主机，虚拟机任务")
	public String hostEvent() {
		if (theForm == null) {
			theForm = new EntityTreeForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String entity_id = request.getParameter("entity_id");
		List list = new ArrayList();
		if (type.equals("26")) {
			EntityTreeObj t = new EntityTreeObj();
			t.setEntityId(entity_id);
			t = entityTreeService.queryTreeNode(t);
			EntityTreeObj en = new EntityTreeObj();
			en.setParentId(t.getId());
			List<EntityTreeObj> vmList = entityTreeService.queryForTree(en);
			for (EntityTreeObj et : vmList) {
				String vm_entityId = et.getEntityId();
				VMHostObj v = new VMHostObj();
				v.setID(Integer.parseInt(vm_entityId));
				v = vmHostService.queryByObj(v);
				EventObj e = new EventObj();
				e.setEvent_uuid(v.getVH_UUID());
				List<TaskObj> taskList = taskEventService.queryForEventList(e);
				list.addAll(taskList);
			}
			theForm.setResultList(list);
			return "xen_event";

		} else if (type.equals("27")) {
			VMHostObj v = new VMHostObj();
			v.setID(Integer.parseInt(entity_id));
			v = vmHostService.queryByObj(v);
			EventObj e = new EventObj();
			e.setEvent_uuid(v.getVH_UUID());
			list = taskEventService.queryForEventList(e);
			theForm.setResultList(list);
			return "xen_event";
		} else {
			list = entityTreeService.eventInfo(type, name);
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
	@MethodLog(remark = "EntityTreeAction-updateNodeName", type = 3, message = "对节点名称进行重命名")
	public String updateNodeName() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		name = URLDecoder.decode(name, "utf-8");
		String result = "";
		EntityTreeObj obj = new EntityTreeObj();
		obj.setId(Integer.parseInt(id));
		obj = entityTreeService.queryTreeNode(obj);
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
				ret = entityTreeService.updateTreeNode(obj);
				// 更新虚拟机相关表
				VMHostObj vm = new VMHostObj();
				String entityId = obj.getEntityId();
				vm.setVH_ID(entityId);
				vm.setVH_NAME(name);
				vmHostService.updateName(vm);
				// 更新entitytreecon表
				EntityConObj cObj = new EntityConObj();
				cObj.setName(name);
				cObj.setType(Integer.parseInt(TypeConstant.VMWARE_VM));
				cObj.setEntityId(obj.getEntityId());
				entityTreeService.updateConObj(cObj);
			}
		} else {
			obj.setName(name);// 更新节点名称
			obj.setType(type);
			ret = entityTreeService.updateTreeNode(obj);
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

	/**
	 * 
	 * @Title: imageInfo
	 * @Description: 查看镜像信息
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Aug 2, 2012 1:57:49 PM
	 */
	@MethodLog(remark = "EntityTreeAction-imageInfo", type = 4, message = "查看镜像信息")
	public String imageInfo() {
		String entityId = (String) Struts2Utils.getParameter("id");// 得到实体Id，即镜像Id
		VMHostObj obj = new VMHostObj();
		obj.setVH_UNITID(entityId);
		List lst = imageService.queryVMHostByVMObj(obj);
		if (imageForm == null) {
			imageForm = new ImageForm();
		}
		VMHostObj reObj = null;
		if (lst.size() > 0) {
			reObj = (VMHostObj) lst.get(0);
		}
		imageForm.setIM_NAME(reObj.getVH_NAME());
		imageForm.setIM_DESC(reObj.getVH_DESC());
		imageForm.setIM_STATE(reObj.getVH_STAT());
		imageForm.setIM_CPU(reObj.getVH_CPU());
		if (reObj.getVH_STORAGE() != null && !"".equals(reObj.getVH_STORAGE())) {
			int storage = Integer.parseInt(reObj.getVH_STORAGE()) / 1024 / 1024;
			imageForm.setIM_STORAGE(String.valueOf(storage));
		}
		// imageForm.setIM_STORAGE(reObj.getVH_STORAGE());
		imageForm.setIM_MEM(reObj.getVH_MEM());
		if (reObj.getVH_MEM() != null && !reObj.getVH_MEM().equals("")) {
			int memory = Integer.parseInt(reObj.getVH_MEM());
			imageForm.setIM_MEM(String.valueOf(memory));
		}
		imageForm.setIM_SYSTEM(reObj.getVH_SYSTEM());

		// 获取模板的路径
		String path = "";
		TempletTreeObj templetTreeObj = new TempletTreeObj();
		templetTreeObj.setName(reObj.getVH_NAME());
		TempletTreeObj reTempletTreeObj = templetTreeService.queryForTree(templetTreeObj).get(0);
		path += reTempletTreeObj.getName();
		for (; reTempletTreeObj.getParent_id() != -1;) {
			TempletTreeObj queryObj = new TempletTreeObj();
			queryObj.setId(reTempletTreeObj.getParent_id());
			reTempletTreeObj = templetTreeService.queryForTree(queryObj).get(0);
			path = reTempletTreeObj.getName() + "/" + path;
		}
		imageForm.setTempletPath(path);

		ScriptConObj scriptConObj = new ScriptConObj();
		if (reObj != null) {
			scriptConObj.setTempletId(entityId);
		}
		scriptConObj.setType("0");
		List resultList = new ArrayList<ScriptObj>();
		List relationList = scriptService.queryScriptListByTempletObj(scriptConObj);
		for (Object o : relationList) {
			ScriptConObj scrCon = (ScriptConObj) o;
			ScriptObj scriptObj = new ScriptObj();
			scriptObj.setId(Integer.parseInt(scrCon.getScriptId()));
			List scriptList = scriptService.listScript(scriptObj);
			scriptObj = (ScriptObj) scriptList.get(0);
			resultList.add(scriptObj);
		}
		imageForm.setResultList(resultList);

		return "imageInfo";
	}

	/**
	 * 
	 * @Title: listAlarm
	 * @Description:获取所有警报信息列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 9, 2012 1:55:44 PM
	 */
	@MethodLog(remark = "EntityTreeAction-listAlarm", type = 4, message = "获取所有警报信息列表")
	public String listAlarm() {
		if (theForm == null) {
			theForm = new EntityTreeForm();
		}
		MonitorAlarmObj obj = new MonitorAlarmObj();
		// obj.setEVENT_TYPE("1");
		List<MonitorAlarmObj> alarmList = monitorService.queryAlarmForListByObj(obj);
		/*
		 * for (MonitorAlarmObj m : alarmList) { String alarm_content =
		 * m.getCONTENT(); if (alarm_content.length() > 5) { alarm_content =
		 * alarm_content.substring(0, 4) + "..."; m.setCONTENT(alarm_content); }
		 * }
		 */
		theForm.setAlarmList(alarmList);
		return "list_alarm";
	}

	/**
	 * 
	 * @Title: listSolution
	 * @Description: 获取解决方案列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 10, 2012 9:43:30 AM
	 */
	@MethodLog(remark = "EntityTreeAction-listSolution", type = 4, message = "获取解决方案列表")
	public String listSolution() {
		if (theForm == null) {
			theForm = new EntityTreeForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String eventId = request.getParameter("eventId");
		String eventType = request.getParameter("eventType");
		// 查询解决方案信息
		SolutionObj tObj = new SolutionObj();
		tObj.setTYPE(eventType);
		List temList = entityTreeService.querySolutionList(tObj);
		theForm.setTemList(temList);
		// 查询告警信息
		MonitorAlarmObj mObj = new MonitorAlarmObj();
		mObj.setEVENT_ID(eventId);
		List mList = monitorService.queryAlarmForListByObj(mObj);
		if (mList != null && mList.size() > 0) {
			mObj = (MonitorAlarmObj) mList.get(0);
		}
		String oper = "";
		request.setAttribute("oper", oper);
		request.setAttribute("eventId", eventId);
		request.setAttribute("location", mObj.getEVENT_LOCATION());// 告警位置
		request.setAttribute("time", mObj.getINS_DATE());// 告警时间
		request.setAttribute("content", mObj.getCONTENT());// 告警内容
		return "list_solution";
	}

	/**
	 * 
	 * @Title: getSolutionInfo
	 * @Description: 根据不同解决方案ID获取相应信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 10, 2012 9:43:51 AM
	 */
	@MethodLog(remark = "EntityTreeAction-getSolutionInfo", type = 4, message = "根据不同解决方案ID获取相应信息")
	public String getSolutionInfo() {
		// if (theForm == null) {
		// theForm = new EntityTreeForm();
		// }
		HttpServletRequest request = Struts2Utils.getRequest();
		String TEM_ID = request.getParameter("TEM_ID");
		SolutionObj tObj = new SolutionObj();
		tObj.setTEM_ID(TEM_ID);
		List<SolutionObj> temList = entityTreeService.querySolutionList(tObj);
		tObj = temList.get(0);
		String oper = null;
		// theForm.setTEM_NAME(tObj.getTEM_NAME());
		if (TEM_ID.equals("1")) {
			// theForm.setCpu(tObj.getCPU());
			// theForm.setMem(tObj.getMEM());
			// theForm.setSTORAGE(tObj.getSTORAGE());
			Map<String, String> map = new HashMap<String, String>();
			map.put("cpu", tObj.getCPU());
			map.put("mem", tObj.getMEM());
			map.put("storage", tObj.getSTORAGE());
			String temName = "";
			String temDesc = "";
			try {
				temName = URLEncoder.encode(tObj.getTEM_NAME(), "utf-8");
				temDesc = URLEncoder.encode(tObj.getTEM_DESC(), "utf-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			map.put("temName", temName);
			map.put("temDesc", temDesc);
			JSONObject json = JSONObject.fromObject(map);
			try {
				Struts2Utils.getResponse().getWriter().print(json.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			oper = "1";
			return null;
		} else if (TEM_ID.equals("2")) {
			// theForm.setCLONE_NUM(tObj.getCLONE_NUM());
			Map<String, String> map = new HashMap<String, String>();
			map.put("num", tObj.getCLONE_NUM());
			String temName = "";
			String temDesc = "";
			try {
				temName = URLEncoder.encode(tObj.getTEM_NAME(), "utf-8");
				temDesc = URLEncoder.encode(tObj.getTEM_DESC(), "utf-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			map.put("temName", temName);
			map.put("temDesc", temDesc);
			JSONObject json = JSONObject.fromObject(map);
			try {
				Struts2Utils.getResponse().getWriter().print(json.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			oper = "2";
			return null;
		} else if (TEM_ID.equals("3")) {// 连接到主机
			// theForm.setPassword("111111");
			oper = "3";
			Map<String, String> map = new HashMap<String, String>();
			map.put("ip", "172.21.1.104");
			map.put("port", "22");
			String temName = "";
			String temDesc = "";
			try {
				temName = URLEncoder.encode(tObj.getTEM_NAME(), "utf-8");
				temDesc = URLEncoder.encode(tObj.getTEM_DESC(), "utf-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			map.put("temName", temName);
			map.put("temDesc", temDesc);
			JSONObject json = JSONObject.fromObject(map);
			try {
				Struts2Utils.getResponse().getWriter().print(json.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		} else {
			oper = "4";
			TbBusiDeployVideoCommandsetObj obj = new TbBusiDeployVideoCommandsetObj();
			obj.setVIDEOID(tObj.getVIDEOID());
			List<TbBusiDeployVideoCommandsetObj> resultList = videoExampleService
					.queryForCommandListByVideoid(obj);
			String str = "";
			for (int i = resultList.size() - 1; i >= 0; i--) {
				String temp = resultList.get(i).getCOMMAND() + ";" + "\n";
				str += temp;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("command", str);
			String temName = "";
			String temDesc = "";
			try {
				temName = URLEncoder.encode(tObj.getTEM_NAME(), "utf-8");
				temDesc = URLEncoder.encode(tObj.getTEM_DESC(), "utf-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			map.put("temName", temName);
			map.put("temDesc", temDesc);
			JSONObject json = JSONObject.fromObject(map);
			try {
				Struts2Utils.getResponse().getWriter().print(json.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			// theForm.setResultList(resultList);

		}
		// theForm.setTEM_DESC(tObj.getTEM_DESC());
		// List list = entityTreeService.querySolutionList(null);
		// theForm.setTemList(list);
		// request.setAttribute("oper", oper);
		// return "list_solutionInfo";
	}

	/**
	 * 
	 * @Title: realizeSolution
	 * @Description: 实现解决方案
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 10, 2012 11:34:58 AM
	 */
	@MethodLog(remark = "EntityTreeAction-realizeSolution", type = 1, message = "实现解决方案")
	public String realizeSolution() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String TEM_ID = request.getParameter("TEM_ID");
		String virtual_id = request.getParameter("virtual_id");
		String eventId = request.getParameter("eventId");
		MonitorAlarmObj alarm = new MonitorAlarmObj();
		alarm.setEVENT_ID(eventId);
		monitorService.updateByObj(alarm);
		EntityTreeObj e = new EntityTreeObj();
		e.setEntityId(virtual_id);
		e = entityTreeService.queryTreeNode(e);
		// 调整虚拟机CPU,内存等
		if (TEM_ID.equals("1")) {
			String cpu = theForm.getCpu();
			String memory = theForm.getMem();
			String dataSize = theForm.getSTORAGE();
			VMManagerObj obj = new VMManagerObj();
			obj.setNAME_EN(e.getName());
			obj.setCPU(cpu);
			obj.setMEMORY(memory);
			obj.setSTORAGE(dataSize);
			String result = vmwManService.adjustVmwVirtualInfo(obj);
			// 克隆虚拟机
		} else if (TEM_ID.equals("2")) {
			String old_name = e.getName();
			String new_name = "clone_alarm" + old_name;
			int parent_id = e.getParentId();
			EntityTreeObj en = new EntityTreeObj();
			en.setId(parent_id);
			en = entityTreeService.queryTreeNode(en);
			String host_name = en.getName();
			String result = vmwManService.cloneVirtualMachine(old_name, new_name, host_name);
		} else if (TEM_ID.equals("3")) {
			try {
				String ip = theForm.getIp();
				String port = theForm.getPort();
				String userName = theForm.getUserName();
				String pwd = theForm.getPassword();
				Struts2Utils.getResponse().sendRedirect(
						"depvideo_makeDeployVideo.do?ip=" + ip + "&port=" + port + "&user="
								+ userName + "&pwd=" + pwd);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (TEM_ID.equals("4")) {

		}
		return null;
	}

	/**
	 * 
	 * @Title: saveSolution
	 * @Description:保存解决方案
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 10, 2012 11:34:58 AM
	 */
	@MethodLog(remark = "EntityTreeAction-saveSolution", type = 1, message = "保存解决方案")
	public String saveSolution() {
		String videoid = Struts2Utils.getParameter("videoid");
		SolutionObj obj = new SolutionObj();
		obj.setVIDEOID(Integer.parseInt(videoid));
		// obj.setTEM_ID("4");
		obj.setTYPE("0");
		obj.setTEM_NAME("保存的修复模板");
		obj.setTEM_DESC("保存的修复模板");
		int result = entityTreeService.insertSolutionByObj(obj);
		List<String> jsonArr = new ArrayList<String>();
		if (result != -1) {
			jsonArr.add("1");// 保存成功
		} else {
			jsonArr.add("0");// 保存失败
		}
		JSONArray json = new JSONArray();
		try {
			json = JSONArray.fromObject(jsonArr);
			Struts2Utils.getResponse().getWriter().print(json.toString());
		} catch (IOException e) {
			LogHelper.debug("EntityTreeAction.saveSolution" + e.getMessage()
					+ e.getClass().getName());
		}
		return null;
	}

	/**
	 * 
	 * @Title: markAsTem
	 * @Description:将虚拟机标记为模板
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @createtime Aug 10, 2012 11:34:58 AM
	 */
	@MethodLog(remark = "EntityTreeAction-markAsTem", type = 3, message = "将虚拟机标记为模板")
	public String markAsTem() throws UnsupportedEncodingException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		name = URLDecoder.decode(name, "utf-8");
		String parentId = request.getParameter("parentId");
		String contentParent_id = request.getParameter("contentParent_id");
		EntityTreeObj obj = new EntityTreeObj();
		obj.setId(Integer.parseInt(id));
		obj.setName(name);
		obj.setParentId(Integer.parseInt(parentId));
		obj = entityTreeService.queryTreeNode(obj);
		String result = entityTreeService.markAsTem(obj, contentParent_id);

		/*
		 * 操作日志
		 */
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		int operResult = 0;
		operObj.setOPERTYPE(3);
		operObj.setMESSAGE("将虚拟机" + name + "标记为模板");
		if (result.equals("1")) {
			operResult = 1;
		}
		operObj.setRESULT(operResult);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志

		String json = "{\"result\":" + result + "}";
		try {
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(json);
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: turnToMarkPage
	 * @Description:转向将虚拟机标记为模板的页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @createtime Otc 16, 2012 11:34:58 AM
	 */
	public String turnToMarkPage() throws UnsupportedEncodingException {
		if (theForm == null) {
			theForm = new EntityTreeForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		name = URLDecoder.decode(name, "utf-8");
		String parentId = request.getParameter("parentId");
		request.setAttribute("id", id);
		request.setAttribute("name", name);
		request.setAttribute("parentId", parentId);
		List resultList = findContents();
		theForm.setResultList(resultList);
		return "turnToMarkPage";
	}

	/**
	 * 
	 * @Title: findContents
	 * @Description: 查找存放模板的目录
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Otc 15, 2012 1:35:01 PM
	 */
	public List<TempletTreeObj> findContents() {
		TempletTreeObj treeObj = new TempletTreeObj();
		treeObj.setType(1);
		List<TempletTreeObj> treeList = templetTreeService.queryForTree(treeObj);
		List<TempletTreeObj> reList = new ArrayList<TempletTreeObj>();
		for (Object o : treeList) {
			TempletTreeObj obj = (TempletTreeObj) o;
			TempletTreeObj addObj = new TempletTreeObj();
			addObj.setParent_id(obj.getId());
			for (; obj.getParent_id() != -1;) {
				TempletTreeObj queryObj = new TempletTreeObj();
				queryObj.setId(obj.getParent_id());
				TempletTreeObj reObj = (TempletTreeObj) templetTreeService.queryForTree(queryObj)
						.get(0);
				obj.setName(reObj.getName() + "/" + obj.getName());
				obj.setParent_id(reObj.getParent_id());
			}
			addObj.setName(obj.getName());
			reList.add(addObj);
		}
		return reList;
	}

	/**
	 * 
	 * @Title: enterMaintenanceMode
	 * @Description:主机进入维护模式
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Aug 10, 2012 11:34:58 AM
	 */
	@MethodLog(remark = "EntityTreeAction-enterMaintenanceMode", type = 3, message = "主机进入维护模式")
	public String enterMaintenanceMode() {
		String result = null;
		HttpServletRequest request = Struts2Utils.getRequest();
		String hostName = request.getParameter("hostName");
		String entityId = request.getParameter("entityId");
		try {
			String url = "/vmware/host/enterMaintenanceMode/[hostName:" + entityId + "]/";
			String enterResult = InvokeUtil.invoke(url);
			Map ps = ParamParser.makeup(enterResult);
			String code = (String) ps.get(ResponseCode.RESPONSE_CODE);
			if (code.equals(ResponseCode.SUCCESS)) {
				/** 通知 */
				try {
					NoticeUtil.getInstance().updateCMDBHost(entityId, Operation.OPER_MODI);// cmdb
				} catch (Exception e) {

				}
				try {
					NoticeUtil.getInstance().updateHost(entityId);
				} catch (Exception ex) {

				}
				result = "1";
			} else {
				String fail = (String) ps.get(ResponseCode.RESPONSEREMARK);
				result = fail;
			}
		} catch (Exception e) {
			result = e.getMessage();
		}

		/*
		 * 操作日志
		 */
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		int operResult = 0;
		operObj.setOPERTYPE(3);
		operObj.setMESSAGE("主机" + entityId + hostName + "进入维护模式");
		if (result.equals("1")) {
			operResult = 1;
		}
		operObj.setRESULT(operResult);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志

		String json = "{\"result\":\"" + result + "\"}";
		try {
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(json);
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: enterMaintenanceMode
	 * @Description:主机退出维护模式
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Sep 28, 2012 11:34:58 AM
	 */
	@MethodLog(remark = "EntityTreeAction-exitMaintenanceMode", type = 3, message = "主机退出维护模式")
	public String exitMaintenanceMode() {
		String result = null;
		HttpServletRequest request = Struts2Utils.getRequest();
		String entityId = request.getParameter("entityId");
		String hostName = request.getParameter("hostName");
		try {
			String url = "/vmware/host/exitMaintenanceMode/[hostName:" + entityId + "]/";
			String enterResult = InvokeUtil.invoke(url);
			Map ps = ParamParser.makeup(enterResult);
			String code = (String) ps.get(ResponseCode.RESPONSE_CODE);
			if (code.equals(ResponseCode.SUCCESS)) {
				/** 通知 */
				try {
					NoticeUtil.getInstance().updateCMDBHost(entityId, Operation.OPER_MODI);// cmdb
				} catch (Exception e) {

				}
				try {
					NoticeUtil.getInstance().updateHost(entityId);
				} catch (Exception ex) {

				}
				result = "1";
			} else {
				String fail = (String) ps.get(ResponseCode.RESPONSEREMARK);
				result = fail;
			}
		} catch (Exception e) {
			result = e.getMessage();
		}
		/*
		 * 操作日志
		 */
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		int operResult = 0;
		operObj.setOPERTYPE(3);
		operObj.setMESSAGE("主机" + entityId + hostName + "退出维护模式");
		if (result.equals("1")) {
			operResult = 1;
		}
		operObj.setRESULT(operResult);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志

		String json = "{\"result\":\"" + result + "\"}";
		try {
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(json);
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: moveintoCluster
	 * @Description:将主机从数据中心移到集群下
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Aug 10, 2012 11:34:58 AM
	 */
	@MethodLog(remark = "EntityTreeAction-moveintoCluster", type = 3, message = "将主机从数据中心移到集群下")
	public String moveintoCluster() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String dcName = request.getParameter("dcName");
		String clCode = request.getParameter("clCode");

		String targetId = request.getParameter("targetId");

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String entityId = request.getParameter("entityId");

		EntityTreeObj obj = new EntityTreeObj();
		obj.setId(Integer.parseInt(id));
		obj.setName(name);
		obj.setEntityId(entityId);
		String result = null;
		result = entityTreeService.moveintoCluster(dcName, clCode, obj, targetId);
		String json = "{'result':'" + result + "'}";
		try {
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(json);
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: wakeupHost
	 * @Description:远程唤醒主机
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Aug 10, 2012 11:34:58 AM
	 */
	@MethodLog(remark = "EntityTreeAction-wakeupHost", type = 3, message = "远程唤醒主机")
	public String wakeupHost() {
		String name = Struts2Utils.getParameter("name");
		if (name.indexOf("(") != -1) {
			name = name.substring(0, name.indexOf("("));
		}
		Properties prop = new Properties();
		InputStream in = null;
		String ip = null;
		String username = null;
		String password = null;
		String result = "-1";
		try {
			in = getClass().getResourceAsStream(
					"/com/sitech/basd/yicloud/web/entitytree/wakeup/wakeup.properties");
			prop.load(in);
			ip = (String) prop.getProperty(name + "_ip").trim();
			username = (String) prop.getProperty(name + "_username").trim();
			password = (String) prop.getProperty(name + "_password").trim();
			String cmd = "ipmitool -I lan -H " + ip + " -U " + username + " -P " + password
					+ " chassis power on";
			Runtime rt = Runtime.getRuntime();
			Process pro = rt.exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			String s = null;
			List<String> list = new ArrayList<String>();
			while ((s = br.readLine()) != null) {
				list.add(s);
			}
			if (s == null) {
				BufferedReader error = new BufferedReader(new InputStreamReader(
						pro.getErrorStream()));
				while ((s = error.readLine()) != null) {
					list.add(s);
				}
			}
			for (String str : list) {
				if (str.contains("Chassis Power Control: Up/On")) {
					result = "1";
				}
			}
			br.close();
			String json = "{\"result\":\"" + result + "\"}";
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(json);
			p.close();
		} catch (Exception e) {
			e.printStackTrace();
			String json = "{\"result\":\"" + -1 + "\"}";
			PrintWriter p = null;
			try {
				p = Struts2Utils.getResponse().getWriter();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			p.print(json);
			p.close();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title: synchroData
	 * @Description:同步数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Otc 27, 2012 11:34:58 AM
	 */
	@MethodLog(remark = "EntityTreeAction-synchroData", type = 3, message = "同步数据")
	public String synchroData() {
		// 删除所有数据
		int result = entityTreeService.deleteAllData();
		String json = "{\"result\":\"" + result + "\"}";
		PrintWriter p = null;
		try {
			// 同步新的数据
			dataSynchron.synDataCenter();
			p = Struts2Utils.getResponse().getWriter();
		} catch (Exception e) {
			json = "{\"result\":\"" + -1 + "\"}";
		}
		p.print(json);
		p.close();
		return null;
	}

	public void setTbGlobalConfigService(TbGlobalConfigService tbGlobalConfigService) {
		this.tbGlobalConfigService = tbGlobalConfigService;
	}

	/**
	 * 
	 * @Title: getVMList
	 * @Description: 获取虚拟机列表
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Nov 14, 2012 4:41:53 PM
	 */
	@MethodLog(remark = "EntityTreeAction-getVMList", type = 4, message = "获取虚拟机列表")
	public String getVMList() throws Exception {
		if (theForm == null) {
			theForm = new EntityTreeForm();
		}
		VMHostObj obj = new VMHostObj();
		obj.setVH_TYPE("1");
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		List<VMHostObj> list = vmHostService.queryForListByObj(obj);
		theForm.setResultList(list);
		return "vmList";
	}

	/**
	 * 
	 * @Title: getVMList
	 * @Description: 获取虚拟机列表
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Nov 14, 2012 4:41:53 PM
	 */
	@MethodLog(remark = "EntityTreeAction-mountPage", type = 4, message = "获取虚拟机列表")
	public String mountPage() throws Exception {
		String vm_code = Struts2Utils.getRequest().getParameter("vm_code");
		Struts2Utils.getRequest().setAttribute("vm_code", vm_code);
		return "mountPage";
	}

	/**
	 * 
	 * @Title: mountISO
	 * @Description: 挂载ISO
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Nov 14, 2012 9:28:44 PM
	 */
	public String mountISO() throws Exception {
		String vm_code = Struts2Utils.getRequest().getParameter("vm_code");
		String path = Struts2Utils.getRequest().getParameter("path");
		path = URLEncoder.encode(path, "UTF-8");
		String url = "/vmware/domain/bios/[vmname:" + vm_code + "]/";
		String result = InvokeUtil.invoke(url);
		url = "/vmware/domain/mountCd/[vmname:" + vm_code + "].[isopath:" + path + "]/";
		result = InvokeUtil.invoke(url);
		String json = "{\"result\":\"" + 1 + "\"}";
		PrintWriter p = null;
		try {
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
	 * @Title: mountISO
	 * @Description: 进入虚拟机权限选择页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2012/12/19
	 */
	public String authotitySelectPage() {
		return "select_page";
	}

	/**
	 * 
	 * @Title: searchNodes
	 * @Description: 搜索名称或ip地址匹配的主机或虚拟机
	 * @return String
	 * @author duangh
	 * @version 1.0
	 * @createtime Nov 14, 2012 4:41:53 PM
	 */
	public String searchNodes() {
		String param = Struts2Utils.getParameter("param");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("params", param);
		List list = entityTreeService.searchNodes(paramMap);
		try {
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			JSONArray json = JSONArray.fromObject(list);
			p.print(json.toString());
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: getVMStorage
	 * @Description: 获取主机存储
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-3-22 下午4:00:31
	 */
	@MethodLog(remark = "EntityTreeAction-getVMStorage", type = 4, message = "获取主机存储")
	public String getVMStorage() {
		if (theForm == null) {
			theForm = new EntityTreeForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		List<DataStoreObj> list = new ArrayList<DataStoreObj>();
		String vm_code = request.getParameter("vm_code");
		String param = "/vmware/domain/msg/[vmName:" + vm_code + "]/";
		String result = InvokeUtil.invoke(param);
		Map map = ParamParser.makeup(result);
		String store = (String) map.get("datastore");
		store = store.substring(1, store.length() - 1);
		// JSONObject js = JSONObject.fromObject(store);
		// map = store.
		// JSONObject js = JSONObject.fromObject(store);
		String[] storeArray = store.split(";");
		if (storeArray != null && storeArray.length > 0) {
			for (int i = 0; i <= storeArray.length - 1; i++) {
				String[] array = storeArray[i].split(",");
				DataStoreObj d = new DataStoreObj();
				d.setNAME(array[0]);
				d.setCAPACITY(array[1]);
				d.setFREE_SPACE(array[2]);
				d.setTYPE(array[3]);
				list.add(d);
			}
		}
		theForm.setResultList(list);

		return "vm_store";
	}

	/**
	 * 
	 * @Title: hostNic
	 * @Description: 展示主机的物理网卡
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 23, 2013 5:22:42 PM
	 */
	public String hostNic() {
		if (theForm == null) {
			theForm = new EntityTreeForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String hostUuid = request.getParameter("id");
		NicRelationObj nicRaObj = new NicRelationObj();
		nicRaObj.setType("NIC_HOST");
		nicRaObj.setToUuid(hostUuid);
		List nicRaList = nicRelationService.queryForListByObj(nicRaObj);
		List nicList = new ArrayList<NicObj>();
		if (nicRaList != null && nicRaList.size() > 0) {
			for (Object obj : nicRaList) {
				nicRaObj = (NicRelationObj) obj;
				NicObj nicObj = new NicObj();
				nicObj.setUuid(nicRaObj.getFromUuid());
				nicObj = nicService.queryByObj(nicObj);
				if (nicObj != null) {
					String mac = "";
					mac = nicObj.getUuid() == null ? "" : nicObj.getUuid().split("_")[1];
					nicObj.setMac(mac);
					if (nicObj.getStatus().equals("CONNECTED")) {
						nicObj.setStatus("已连接");
						if (nicObj.getLinkduplex().equals("TRUE")) {
							nicObj.setLinkduplex("全");
						} else {
							nicObj.setLinkduplex("半");
						}
					} else {
						nicObj.setStatus("已断开连接");
						nicObj.setLinkduplex("-");
						nicObj.setLinkspeed(0);
					}
					nicList.add(nicObj);
				}
			}
		}
		theForm.setResultList(nicList);
		return "hostNic";
	}

	/**
	 * 
	 * @Title: vmVirtualNic
	 * @Description: 展示虚拟机的虚拟网卡
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 24, 2013 11:22:21 AM
	 */
	@MethodLog(remark = "EntityTreeAction-vmVirtualNic", type = 4, message = "展示虚拟机的虚拟网卡")
	public String vmVirtualNic() {
		if (theForm == null) {
			theForm = new EntityTreeForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String vm_code = request.getParameter("vm_code");
		NicRelationObj nicRaObj = new NicRelationObj();
		nicRaObj.setType("VNIC_VM");
		nicRaObj.setToUuid(vm_code);
		List nicRaList = nicRelationService.queryForListByObj(nicRaObj);
		List vnicLst = new ArrayList<VirtualNicObj>();
		if (nicRaList != null && nicRaList.size() > 0) {
			for (Object obj : nicRaList) {
				nicRaObj = (NicRelationObj) obj;
				VirtualNicObj vNicObj = new VirtualNicObj();
				vNicObj.setUuid(nicRaObj.getFromUuid());
				vNicObj = virtualNicService.queryByObj(vNicObj);
				if (vNicObj != null) {
					// 查询虚拟网卡和物理网卡的关系
					NicRelationObj nic_vnic = new NicRelationObj();
					nic_vnic.setType("VNIC_NIC");
					nic_vnic.setFromUuid(nicRaObj.getFromUuid());
					List nic_vnicLst = nicRelationService.queryForListByObj(nic_vnic);
					if (nic_vnicLst != null && nic_vnicLst.size() > 0) {
						for (Object o : nic_vnicLst) {
							nic_vnic = (NicRelationObj) o;
							NicObj nicObj = new NicObj();
							nicObj.setUuid(nic_vnic.getToUuid());
							nicObj = nicService.queryByObj(nicObj);
							if (nicObj != null) {
								vNicObj.setNicName(nicObj.getName() == null ? "" : nicObj.getName());
							}
						}
					}
					vNicObj.setIpAddress(vNicObj.getIpAddress() == null
							|| vNicObj.getIpAddress().equals("") ? "-" : vNicObj.getIpAddress());
					// 查询虚拟网卡和端口组的关系
					NicRelationObj vnic_pg = new NicRelationObj();
					vnic_pg.setType("VNIC_PORTGROUP");
					vnic_pg.setFromUuid(nicRaObj.getFromUuid());
					List vnic_pgLst = nicRelationService.queryForListByObj(vnic_pg);
					if (vnic_pgLst != null && vnic_pgLst.size() > 0) {
						for (Object vnic_pgObj : vnic_pgLst) {
							vnic_pg = (NicRelationObj) vnic_pgObj;
							PortGroup pgObj = new PortGroup();
							pgObj.setPguuid(vnic_pg.getToUuid());
							pgObj = portGroupService.queryPortGroupById(pgObj);
							if (pgObj != null) {
								vNicObj.setPortgroupName(pgObj.getName() == null ? "" : pgObj
										.getName());
							}
						}
					}
					vnicLst.add(vNicObj);
				}
			}
		}
		theForm.setResultList(vnicLst);
		return "vmVirtualNic";
	}

	public static void main(String[] args) {
		List<DataStoreObj> list = new ArrayList<DataStoreObj>();
		String result = "[responseCode:1].[power:poweredOn].[memoryMB:2048].[cpuNum:2].[guestName:Red Hat Enterprise Linux 6 (64 位)].[guestId:rhel6_64Guest].[nicnum:1].[hostName:172.21.3.34].[hostCode:host-42].[storage:31457280].[datastore:{datastore1!926,130;nfs-58!401,269;}].[CODE:vm-535]";
		Map map = ParamParser.makeup(result);
		String store = (String) map.get("datastore");
		store = store.replace('!', ':');
		store = store.substring(1, store.length() - 1);
		// JSONObject js = JSONObject.fromObject(store);
		// map = store.
		// JSONObject js = JSONObject.fromObject(store);
		String[] storeArray = store.split(";");
		if (storeArray != null && storeArray.length > 0) {
			for (int i = 0; i < storeArray.length - 1; i++) {
				String[] array = storeArray[i].split(":");
				DataStoreObj d = new DataStoreObj();
				d.setNAME(array[0]);
				d.setCAPACITY(array[1].split(",")[0]);
				d.setFREE_SPACE(array[1].split(",")[1]);
				list.add(d);
			}
		}
	}
}
