package com.sitech.basd.yicloud.web.xen.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sitech.basd.resource.domain.template.TemManObj;
import com.sitech.basd.resource.service.template.TemManService;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.net.IpService;
import com.sitech.basd.sxcloud.cloud.service.net.NetService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.rsmu.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.cluster.ClusterObj;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.basd.yicloud.domain.kvm.TbYicloudOsTypeObj;
import com.sitech.basd.yicloud.domain.snapshot.Snapshot;
import com.sitech.basd.yicloud.domain.xen.Network;
import com.sitech.basd.yicloud.domain.xen.Nic;
import com.sitech.basd.yicloud.domain.xen.Vdi;
import com.sitech.basd.yicloud.domain.xen.Vif;
import com.sitech.basd.yicloud.domain.xenconn.ConnectionInfo;
import com.sitech.basd.yicloud.domain.xenstore.XenStoreObj;
import com.sitech.basd.yicloud.domain.xentree.XenEntityConObj;
import com.sitech.basd.yicloud.domain.xentree.XenEntityTreeObj;
import com.sitech.basd.yicloud.service.cluster.ClusterService;
import com.sitech.basd.yicloud.service.datastore.DataStoreService;
import com.sitech.basd.yicloud.service.kvm.KvmManService;
import com.sitech.basd.yicloud.service.vmware.VmwManService;
import com.sitech.basd.yicloud.service.xen.XenManService;
import com.sitech.basd.yicloud.service.xenstore.XenStoreService;
import com.sitech.basd.yicloud.service.xentree.XenEntityTreeService;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.TypeConstant;
import com.sitech.basd.yicloud.util.systemlog.MethodLog;
import com.sitech.basd.yicloud.web.xen.form.XenManForm;
import com.sitech.utils.servlet.PrintWriterOut;

@SuppressWarnings("all")
public class XenManAction extends BaseAction {
	private XenManForm theForm;
	private XenEntityTreeService xenEntityTreeService;
	private VMHostService vmHostService;
	private XenManService xenManService;
	private HostInfoService hostInfoService;
	private KvmManService kvmManService;
	private VmwManService vmwManService;
	private ClusterService clusterService;
	private DataStoreService dataStoreService;
	private XenStoreService xenStoreService;
	private NetService netService;
	private IpService ipService;
	private TemManService temManService;

	public void setTemManService(TemManService temManService) {
		this.temManService = temManService;
	}

	public void setNetService(NetService netService) {
		this.netService = netService;
	}

	public void setIpService(IpService ipService) {
		this.ipService = ipService;
	}

	public void setXenStoreService(XenStoreService xenStoreService) {
		this.xenStoreService = xenStoreService;
	}

	public void setDataStoreService(DataStoreService dataStoreService) {
		this.dataStoreService = dataStoreService;
	}

	public void setClusterService(ClusterService clusterService) {
		this.clusterService = clusterService;
	}

	public void setVmwManService(VmwManService vmwManService) {
		this.vmwManService = vmwManService;
	}

	public void setKvmManService(KvmManService kvmManService) {
		this.kvmManService = kvmManService;
	}

	public void setTheForm(XenManForm theForm) {
		this.theForm = theForm;
	}

	public XenManForm getTheForm() {
		return theForm;
	}

	public void setXenEntityTreeService(XenEntityTreeService xenEntityTreeService) {
		this.xenEntityTreeService = xenEntityTreeService;
	}

	public void setVmHostService(VMHostService vmHostService) {
		this.vmHostService = vmHostService;
	}

	public void setXenManService(XenManService xenManService) {
		this.xenManService = xenManService;
	}

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	/**
	 * 
	 * @Title: goNakeMacPage
	 * @Description: 进入创建裸机页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 10, 2012 9:53:04 AM
	 */
	public String goNakeMacPage() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String parent_id = request.getParameter("parent_id");
		String clusterId = request.getParameter("clusterId");
		String pool_uuid = request.getParameter("pool_uuid");// 集群uuid
		String host_uuid = request.getParameter("host_uuid");// 主机uuid
		TbYicloudOsTypeObj obj = new TbYicloudOsTypeObj();
		obj.setVIR_TYPE("xen");
		List<TbYicloudOsTypeObj> list = vmwManService.queryOSList(obj);
		theForm.setResultList(list);
		// 存储列表
		DataStoreObj dsObj = new DataStoreObj();
		XenEntityTreeObj hostObj = new XenEntityTreeObj();
		hostObj.setId(Integer.parseInt(parent_id));
		hostObj = xenEntityTreeService.queryTreeNode(hostObj);
		dsObj.setHOST_ID(hostObj.getEntityId());
		// List<DataStoreObj> dataList =
		// dataStoreService.queryForListByObj(dsObj);
		// theForm.setStorageList(dataList);
		// 查询主机所有存储
		String param = "/xen/storages/lookup/[hostUuid:" + host_uuid + "].[poolUuid:" + pool_uuid
				+ "]/";
		String result = InvokeUtil.invoke(param);
		JSONArray jo = JSONArray.fromObject(result);
		List<JSONObject> hostSList = JSONArray.toList(jo, JSONObject.class);
		List<DataStoreObj> store = new ArrayList<DataStoreObj>();
		if (hostSList.get(0).get("responseCode").equals("1")) {
			hostSList.remove(0);
			for (JSONObject json : hostSList) {
				DataStoreObj da = new DataStoreObj();
				if (json.get("srType").equals("lvm") || json.get("srType").equals("nfs")) {
					da.setNAME((String) json.get("srName"));
					da.setStore_uuid((String) json.get("srUuid"));
					store.add(da);
				}
			}
			theForm.setStorageList(store);
		}
		// 查询主机所有网络
		String net_param = "/xen/networks/get/[hostUuid:" + host_uuid + "].[poolUuid:" + pool_uuid
				+ "]/";
		String net_result = InvokeUtil.invoke(net_param);
		JSONArray jso = JSONArray.fromObject(net_result);
		List<JSONObject> netList = JSONArray.toList(jso, JSONObject.class);
		List<TbCloud2NetInfoObj> net = new ArrayList<TbCloud2NetInfoObj>();
		if (netList.get(0).get("responseCode").equals("1")) {
			netList.remove(0);
			for (JSONObject json : netList) {
				TbCloud2NetInfoObj netObj = new TbCloud2NetInfoObj();
				if (json.getString("networkName").equals("Pool-wide network associated with eth1")) {
					netObj.setNAME("网络 1");
				} else {
					netObj.setNAME(json.getString("networkName"));
				}
				netObj.setNet_uuid((String) json.get("networkUuid"));
				net.add(netObj);
			}
			theForm.setNetList(net);
		}

		/**
		 * 获取所有存储
		 */
		Map<String, String> vdiIsos = new LinkedHashMap<String, String>();
		String param1 = "/xen/isos/host/get/[poolUuid:" + pool_uuid + "].[hostUuid:" + host_uuid
				+ "]/";
		String result1 = InvokeUtil.invoke(param1);
		JSONArray j1 = JSONArray.fromObject(result1);
		List<JSONObject> jsonO1 = JSONArray.toList(j1, JSONObject.class);
		/**
		 * 查出所有iso存储
		 */
		if (jsonO1.get(0).get("responseCode").equals("1")) {
			jsonO1.remove(0);
			for (int i = 0; i < jsonO1.size(); i++) {
				JSONObject js = jsonO1.get(i);
				String srUuid = js.getString("srUuid");
				JSONArray j2 = js.getJSONArray("jsonArrays");
				List<JSONObject> jsonO2 = JSONArray.toList(j2, JSONObject.class);
				String srName = js.getString("srName");
				vdiIsos.put("sr" + "~" + srUuid, srName);
				for (int k = 0; k < jsonO2.size(); k++) {
					JSONObject js1 = jsonO2.get(k);
					String vdiName = js1.getString("vdiName");
					String vdiUuid = js1.getString("vdiUuid");
					vdiIsos.put(vdiUuid, vdiName);
				}
			}
		}
		theForm.setVdiIsos(vdiIsos);

		request.setAttribute("parent_id", parent_id);
		request.setAttribute("clusterId", clusterId);
		request.setAttribute("pool_uuid", pool_uuid);
		request.setAttribute("host_uuid", host_uuid);
		return "nake_page";
	}

	/**
	 * 
	 * @Title: getOsTypeList
	 * @Description: 获取操作系统列表
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Jul 25, 2012 2:25:53 PM
	 */
	@MethodLog(remark = "XenManAction-getOsTypeList", type = 4, message = "获取操作系统列表")
	public String getOsTypeList() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String osType = request.getParameter("osType");
		StringBuffer buff = new StringBuffer("{");
		String info = "";
		if (osType != null && !"".equals(osType)) {
			TbYicloudOsTypeObj to = new TbYicloudOsTypeObj();
			to.setOS_TYPE(osType);
			to.setVIR_TYPE("xen");
			List<TbYicloudOsTypeObj> osList = vmwManService.queryOSList(to);

			// TbSysOperationLogObj operObj =
			// this.getTbSysOperationLogObj(request);
			// operObj.setREMARK("");
			// operObj.setOPERTYPE(4);
			// operObj.setMESSAGE("获取操作系统列表");
			// int oper_result = 1;
			// if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			// operObj.setFUNCID(Integer.parseInt(request.getSession()
			// .getAttribute(Constant.FUNCID).toString()));
			// }
			// operObj.setRESULT(oper_result);
			// @SuppressWarnings("unused")
			// int retOper = operationService.insertByObj(operObj);// 写操作日志

			int j = 0;
			for (int i = 0; i < osList.size(); i++) {
				TbYicloudOsTypeObj os = new TbYicloudOsTypeObj();
				os = osList.get(i);
				if (j != 0) {
					buff.append(",");
				}
				buff.append("'");
				buff.append((String) os.getTYPE_ID());
				buff.append("':'");
				buff.append((String) os.getTYPE_NAME());
				buff.append("'");
				j++;
			}
		}
		buff.append("}");
		info = buff.toString();
		request.setAttribute("info", info);
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: createNakeMac
	 * @Description: 创建裸机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @createtime Sep 7, 2012 2:32:22 PM
	 */
	@MethodLog(remark = "XenManAction-createNakeMac", type = 1, message = "创建裸机")
	public String createNakeMac() throws Exception {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String parent_id = request.getParameter("parent_id");
		String osId = request.getParameter("osId");
		String cpu = request.getParameter("currentcpu");
		String mem = request.getParameter("currentmemory");
		String storage = request.getParameter("storage");
		String UNIT = request.getParameter("UNIT");
		String vmName = request.getParameter("name_en");
		vmName = URLDecoder.decode(vmName, "utf-8");
		String unit_store = request.getParameter("unit_store");
		String clusterId = request.getParameter("clusterId");
		String store_uuid = request.getParameter("store");
		String net = request.getParameter("net");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String vidUuid = request.getParameter("vidUuid");
		long memSize = 0;
		long store = 0;
		if (UNIT.equals("2")) {
			memSize = (Long.parseLong(mem)) * 1024 * 1024 * 1024;

		} else {
			memSize = (Long.parseLong(mem)) * 1024 * 1024;
		}
		if (unit_store.equals("2")) {
			store = Long.parseLong(storage) * 1024 * 1024 * 1024;
		} else {
			store = Long.parseLong(storage) * 1024 * 1024;
		}
		mem = String.valueOf(memSize);
		storage = String.valueOf(store);
		XenEntityTreeObj e = new XenEntityTreeObj();
		e.setParentId(Integer.parseInt(parent_id));
		e.setName(vmName);
		String result = xenManService.createNakeMac(osId, vmName, Integer.parseInt(cpu), storage,
				memSize, e, clusterId, store_uuid, net, pool_uuid, host_uuid, vidUuid);
		// 插入操作日志
		JSONObject j = JSONObject.fromObject(result);
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(1);
		operObj.setMESSAGE("创建虚拟机");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志

		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(j);
		// out.close();
		PrintWriterOut.printWirter(response, j);

		Constant.PROCESS_LEVEL = 0;
		return null;
	}

	/**
	 * 
	 * @Title: listVMInfo
	 * @Description: 查询虚拟机详细信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 9, 2012 10:23:43 AM
	 */
	@MethodLog(remark = "XenManAction-listVMInfo", type = 4, message = "查询虚拟机详细信息")
	public String listVMInfo() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpSession session = Struts2Utils.getSession();
		String entity_id = request.getParameter("entity_id");
		String oper = request.getParameter("oper");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String type = request.getParameter("type");
		VMHostObj v = new VMHostObj();
		v.setID(Integer.parseInt(entity_id));
		v = vmHostService.queryByObj(v);
		String VH_UUID = v.getVH_UUID();
		// 当虚拟机不在主机下时，只需传递池的uuid
		if (pool_uuid == null || "".equals(pool_uuid)) {
			pool_uuid = host_uuid;
			host_uuid = "";
		}
		String result = xenManService.listVMInfo(VH_UUID, pool_uuid);

		// //插入操作日志
		// TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		// operObj.setREMARK("");
		// operObj.setOPERTYPE(4);
		// operObj.setMESSAGE("查询虚拟机详细信息");
		// int oper_result = 1;
		// if (request.getSession().getAttribute(Constant.FUNCID) != null) {
		// operObj.setFUNCID(Integer.parseInt(request.getSession()
		// .getAttribute(Constant.FUNCID).toString()));
		// }
		// operObj.setRESULT(oper_result);
		// @SuppressWarnings("unused")
		// int retOper = operationService.insertByObj(operObj);// 写操作日志

		JSONObject js = JSONObject.fromObject(result);

		if (js.get("responseCode").equals("1")) {
			Double dynamicMax = Double.parseDouble(js.get("memDynamicMax").toString())
					/ (1024 * 1024);
			Double dynamicMin = Double.parseDouble(js.get("memDynamicMin").toString())
					/ (1024 * 1024);
			Double staticMax = Double.parseDouble(js.get("memStaticMax").toString())
					/ (1024 * 1024);
			Double staticMin = Double.parseDouble(js.get("memStaticMin").toString())
					/ (1024 * 1024);
			Double store = Double.parseDouble(js.get("virtualSrSize").toString())
					/ (1024 * 1024 * 1024);
			DecimalFormat nf = new DecimalFormat("0");
			theForm.setNAME_EN(js.get("vmName").toString());
			theForm.setCPU(js.get("cpuNum").toString());
			theForm.setCURRENTCPU(js.get("cpuMax").toString());
			theForm.setMEMORY(String.valueOf(nf.format(dynamicMax)));
			theForm.setDynamicMax(String.valueOf(nf.format(dynamicMax)));
			theForm.setDynamicMin(String.valueOf(nf.format(dynamicMin)));
			theForm.setStaticMax(String.valueOf(nf.format(staticMax)));
			theForm.setStaticMin(String.valueOf(nf.format(staticMin)));

			theForm.setDESC(js.get("description").toString());
			theForm.setSTATE(js.get("powerState").toString());
			theForm.setSTORAGE(String.valueOf(nf.format(store)));
			theForm.setOsType((String) js.get("osName"));
			theForm.setIP((String) js.get("vmIps"));
			theForm.setVirtual_state(String.valueOf((Boolean) js.get("virtualState")));
			theForm.setTYPE(type);
			request.setAttribute("oper", oper);
			request.setAttribute("entity_id", entity_id);
			request.setAttribute("pool_uuid", pool_uuid);
			request.setAttribute("host_uuid", host_uuid);
			request.setAttribute("vm_uuid", VH_UUID);
			/**
			 * 点击节点的时候获取虚拟机所有的信息，这个时候把信息放入session，在其他标签页用的时候，不必再和接口交互。
			 */
			session.setAttribute("state", theForm.getSTATE());

			return "vm_info";
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @Title: listVMInfo
	 * @Description: 调整虚拟机的时候信息展示
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 9, 2012 10:23:43 AM
	 */
	@MethodLog(remark = "XenManAction-editListVMInfo", type = 4, message = "调整虚拟机的时候信息展示")
	public String editListVMInfo() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpSession session = Struts2Utils.getSession();
		String entity_id = request.getParameter("entity_id");
		String oper = request.getParameter("oper");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String type = request.getParameter("type");
		VMHostObj v = new VMHostObj();
		v.setID(Integer.parseInt(entity_id));
		v = vmHostService.queryByObj(v);
		String VH_UUID = v.getVH_UUID();
		// 当虚拟机不在主机下时，只需传递池的uuid
		if (pool_uuid == null || "".equals(pool_uuid)) {
			pool_uuid = host_uuid;
			host_uuid = "";
		}
		String result = xenManService.listVMInfo(VH_UUID, pool_uuid);
		JSONObject js = JSONObject.fromObject(result);

		if (js.get("responseCode").equals("1")) {
			Double dynamicMax = Double.parseDouble(js.get("memDynamicMax").toString())
					/ (1024 * 1024);
			Double dynamicMin = Double.parseDouble(js.get("memDynamicMin").toString())
					/ (1024 * 1024);
			Double staticMax = Double.parseDouble(js.get("memStaticMax").toString())
					/ (1024 * 1024);
			Double staticMin = Double.parseDouble(js.get("memStaticMin").toString())
					/ (1024 * 1024);
			Double store = Double.parseDouble(js.get("virtualSrSize").toString())
					/ (1024 * 1024 * 1024);
			DecimalFormat nf = new DecimalFormat("0");
			theForm.setNAME_EN(js.get("vmName").toString());
			theForm.setCPU(js.get("cpuNum").toString());
			theForm.setCURRENTCPU(js.get("cpuMax").toString());
			theForm.setMEMORY(String.valueOf(nf.format(dynamicMax)));
			theForm.setDynamicMax(String.valueOf(nf.format(dynamicMax)));
			theForm.setDynamicMin(String.valueOf(nf.format(dynamicMin)));
			theForm.setStaticMax(String.valueOf(nf.format(staticMax)));
			theForm.setStaticMin(String.valueOf(nf.format(staticMin)));

			theForm.setDESC(js.get("description").toString());
			theForm.setSTATE(js.get("powerState").toString());
			theForm.setSTORAGE(String.valueOf(nf.format(store)));
			theForm.setOsType((String) js.get("osName"));
			theForm.setIP((String) js.get("vmIp1"));
			theForm.setIP2((String) js.get("vmIp2"));
			theForm.setVirtual_state(String.valueOf((Boolean) js.get("virtualState")));
			theForm.setTYPE(type);
			request.setAttribute("oper", oper);
			request.setAttribute("entity_id", entity_id);
			request.setAttribute("pool_uuid", pool_uuid);
			request.setAttribute("host_uuid", host_uuid);
			request.setAttribute("vm_uuid", VH_UUID);
			/**
			 * 点击节点的时候获取虚拟机所有的信息，这个时候把信息放入session，在其他标签页用的时候，不必再和接口交互。
			 */
			session.setAttribute("state", theForm.getSTATE());

			return "edit_vm_info";
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @Title: vmConsole
	 * @Description: 进入控制台页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Nov 27, 2012 4:43:37 PM
	 */
	public String vmConsole() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpSession session = Struts2Utils.getSession();
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String entityId = request.getParameter("entityId");
		String state = (String) session.getAttribute("state");
		String tempState = request.getParameter("state");
		if (tempState != null && tempState.equals("0")) {
			state = "Running";
		}
		VMHostObj v = new VMHostObj();
		v.setID(Integer.parseInt(entityId));
		v = vmHostService.queryByObj(v);
		String param = "/xen/console/vm/get/[poolUuid:" + pool_uuid + "].[hostUuid:" + host_uuid
				+ "].[vmUuid:" + v.getVH_UUID() + "]/";
		String result = InvokeUtil.invoke(param);
		JSONObject js = JSONObject.fromObject(result);
		if ("1".equals(js.get("responseCode"))) {
			boolean isExistPer = Boolean.parseBoolean(js.getString("isExistPer"));
			if (isExistPer) {
				theForm.setPath(js.getString("host"));
				theForm.setPort(js.getString("port"));
				theForm.setProxyhost(js.getString("proxyhost1"));
				theForm.setProxyport(js.getString("proxyport1"));
				theForm.setSocketfactory(js.getString("socketfactory"));
				request.setAttribute("path", js.getString("host"));
				request.setAttribute("host", js.getString("proxyhost1"));
				request.setAttribute("state", state);
			} else {
				request.setAttribute("path", "");
				request.setAttribute("host", "");
				request.setAttribute("state", "false");
			}
		}
		return "vm_console";
	}

	/**
	 * 
	 * @Title: listVmVdis
	 * @Description: 进入虚拟机的存储页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Nov 27, 2012 4:43:37 PM
	 */
	public String listVmVdis() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String pool_uuid = request.getParameter("pool_uuid");
		String entityId = request.getParameter("entity_id");
		String host_uuid = request.getParameter("host_uuid");

		// 当虚拟机不在主机下时，只需传递池的uuid
		if (pool_uuid == null || "".equals(pool_uuid)) {
			pool_uuid = host_uuid;
		}
		VMHostObj v = new VMHostObj();
		v.setID(Integer.parseInt(entityId));
		v = vmHostService.queryByObj(v);
		String vmUuid = v.getVH_UUID();
		Map<String, String> vdiIsos = new LinkedHashMap<String, String>();

		String param0 = "/xen/vminfo/lookup/[poolUuid:" + pool_uuid + "].[vmUuid:" + vmUuid + "]/";
		String result0 = InvokeUtil.invoke(param0);
		String state = "Running";
		JSONObject j0 = JSONObject.fromObject(result0);
		if ("1".equals(j0.get("responseCode"))) {
			state = (String) j0.get("powerState");
		}
		String param = "/xen/vdis/vm/lookup/[poolUuid:" + pool_uuid + "].[vmUuid:" + vmUuid + "]/";
		String result = InvokeUtil.invoke(param);
		JSONArray j = JSONArray.fromObject(result);
		List<JSONObject> jsonO = JSONArray.toList(j, JSONObject.class);
		/**
		 * 获取所有存储
		 */
		String param1 = "/xen/isos/host/get/[poolUuid:" + pool_uuid + "].[hostUuid:" + host_uuid
				+ "]/";
		String result1 = InvokeUtil.invoke(param1);
		JSONArray j1 = JSONArray.fromObject(result1);
		List<JSONObject> jsonO1 = JSONArray.toList(j1, JSONObject.class);
		/**
		 * 查出所有iso存储
		 */
		if (jsonO1.get(0).get("responseCode").equals("1")) {
			jsonO1.remove(0);
			for (int i = 0; i < jsonO1.size(); i++) {
				JSONObject js = jsonO1.get(i);
				String srUuid = js.getString("srUuid");
				JSONArray j2 = js.getJSONArray("jsonArrays");
				List<JSONObject> jsonO2 = JSONArray.toList(j2, JSONObject.class);
				String srName = js.getString("srName");
				vdiIsos.put("sr" + "~" + srUuid, srName);
				for (int k = 0; k < jsonO2.size(); k++) {
					JSONObject js1 = jsonO2.get(k);
					String vdiName = js1.getString("vdiName");
					String vdiUuid = js1.getString("vdiUuid");
					vdiIsos.put(vdiUuid, vdiName);
				}
			}
		}

		List<Vdi> vdis = new ArrayList<Vdi>();
		if (jsonO.get(0).get("responseCode").equals("1")) {
			jsonO.remove(0);
			for (int i = 0; i < jsonO.size(); i++) {
				Vdi vdi = new Vdi();
				JSONObject js = jsonO.get(i);
				String vdiUuid = js.getString("vdiUuid");
				vdi.setVdiUuid(vdiUuid);
				Double vdiSize = Double.parseDouble(js.getString("vdiSize").toString())
						/ (1024 * 1024 * 1024);
				String vdiType = js.getString("vdiType").toString();
				String srName = js.getString("srName").toString();
				String srUuid = js.getString("srUuid").toString();
				String srHostName = js.getString("srHostName").toString();
				boolean srShared = js.getBoolean("srShared");
				String srType = js.getString("srType");
				if ("iso".equals(srType)) {
					request.setAttribute("xentool", vdiUuid);
				} else {
					DecimalFormat nf = new DecimalFormat("0");
					vdi.setVdiSize(Long.parseLong(nf.format(vdiSize).toString()));
					vdi.setVdiName(js.getString("vdiName"));
					vdi.setReadOnly(Boolean.parseBoolean(js.getString("readOnly")));
					vdi.setVdiType(vdiType);
					vdi.setSrName(srName);
					vdi.setSrUuid(srUuid);
					vdi.setSrHostName(srHostName);
					vdi.setSrShared(srShared);
					vdi.setPowerState(state);
					vdis.add(vdi);
				}
			}
		}
		request.setAttribute("poolUuid", pool_uuid);
		request.setAttribute("vmUuid", vmUuid);
		request.setAttribute("hostUuid", host_uuid);
		theForm.setVdiIsos(vdiIsos);
		theForm.setVdis(vdis);
		return "vm_vdis";
	}

	/**
	 * 
	 * @Title: adjustVM
	 * @Description: 调整虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 12, 2012 11:04:11 AM
	 */
	@MethodLog(remark = "XenManAction-adjustVM", type = 3, message = "调整虚拟机")
	public String adjustVM() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String cpu = request.getParameter("cpu");
		String cpuMax = request.getParameter("cpuMax");
		String entity_id = request.getParameter("entity_id");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String dynamicMax = request.getParameter("dynamicMax");
		String dynamicMin = request.getParameter("dynamicMin");
		String staticMax = request.getParameter("staticMax");
		String staticMin = request.getParameter("staticMin");
		String storage = request.getParameter("storage");
		VMHostObj v = new VMHostObj();
		v.setID(Integer.parseInt(entity_id));
		v = vmHostService.queryByObj(v);
		String VH_UUID = v.getVH_UUID();
		Long dynamicmmax = Long.parseLong(dynamicMax) * 1024 * 1024;
		Long dynamicmmin = Long.parseLong(dynamicMin) * 1024 * 1024;
		Long staticmax = Long.parseLong(staticMax) * 1024 * 1024;
		Long staticmin = Long.parseLong(staticMin) * 1024 * 1024;
		long store = 0;
		if (storage != null && !"".equals(storage)) {
			store = Long.parseLong(storage) * 1024 * 1024;
		}
		if (pool_uuid == null || "".equals(pool_uuid)) {
			pool_uuid = host_uuid;
			host_uuid = "";
		}
		String result = xenManService.adjustVM(cpu, cpuMax, VH_UUID, pool_uuid, host_uuid,
				dynamicmmax, dynamicmmin, staticmax, staticmin, store);
		// 插入操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(3);
		operObj.setMESSAGE("调整xen虚拟机");
		int oper_result = 0;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(result);
		// out.close();
		PrintWriterOut.printWirter(response, result);
		return null;

	}

	/**
	 * 
	 * @Title: putVMState
	 * @Description: 修改虚拟机的状态
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 10, 2012 8:46:19 AM
	 */
	@MethodLog(remark = "XenManAction-putVMState", type = 3, message = "修改虚拟机的状态")
	public String putVMState() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String entity_id = request.getParameter("entity_id");
		String id = request.getParameter("id");
		String state = request.getParameter("state");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String masterHost_uuid = "";
		if (pool_uuid == null || "".equals(pool_uuid)) {
			pool_uuid = host_uuid;
			String param = "/xen/hostbypool/lookup/[poolUuid:" + pool_uuid + "]/";
			String pool_result = InvokeUtil.invoke(param);
			JSONArray j = JSONArray.fromObject(pool_result);
			List<JSONObject> jsonO = JSONArray.toList(j, JSONObject.class);
			if (jsonO.get(0).get("responseCode").equals("1")) {
				jsonO.remove(0);
				for (JSONObject js : jsonO) {
					boolean isMasterHost = (Boolean) js.get("isMasterHost");
					if (isMasterHost == true) {
						masterHost_uuid = (String) js.get("hostUuid");
					}
				}
			}

		} else {
			masterHost_uuid = host_uuid;
		}
		if (state.equals("create")) {
			state = "RUNNING";
		} else if (state.equals("shutdown")) {
			state = "HALTED";
		} else if (state.equals("suspend")) {
			state = "SUSPENDED";
		} else if (state.equals("destroy")) {
			state = "destory";
		} else if (state.equals("reboot")) {
			state = "RESET";
		} else if (state.equals("resume")) {
			state = "RESUME";
		}
		VMHostObj v = new VMHostObj();
		v.setID(Integer.parseInt(entity_id));
		v = vmHostService.queryByObj(v);
		String VH_UUID = "";
		if (v != null) {
			VH_UUID = v.getVH_UUID();
		}
		XenEntityTreeObj e = new XenEntityTreeObj();
		e.setEntityId(entity_id);
		e.setId(Integer.parseInt(id));
		String result = xenManService.putVMState(VH_UUID, state, e, pool_uuid, masterHost_uuid,
				host_uuid);
		// request.getSession().setAttribute("pool_uuid", pool_uuid);
		// request.getSession().setAttribute("host_uuid", host_uuid);

		// 插入操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(3);
		operObj.setMESSAGE("修改xen虚拟机状态");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志

		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(result);
		// out.close();
		PrintWriterOut.printWirter(response, result);
		return null;

	}

	/**
	 * 
	 * @Title: listHostInfo
	 * @Description: 查询主机详细信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 10, 2012 9:00:55 AM
	 */
	@MethodLog(remark = "XenManAction-listHostInfo", type = 4, message = "查询主机详细信息")
	public String listHostInfo() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String entity_id = request.getParameter("entity_id");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String result = xenManService.listHostInfo(host_uuid, pool_uuid);

		// //插入操作日志
		// TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		// operObj.setREMARK("");
		// operObj.setOPERTYPE(4);
		// operObj.setMESSAGE("查询主机详细信息");
		// int oper_result = 1;
		// if (request.getSession().getAttribute(Constant.FUNCID) != null) {
		// operObj.setFUNCID(Integer.parseInt(request.getSession()
		// .getAttribute(Constant.FUNCID).toString()));
		// }
		// operObj.setRESULT(oper_result);
		// @SuppressWarnings("unused")
		// int retOper = operationService.insertByObj(operObj);

		JSONObject js = JSONObject.fromObject(result);
		if (js.get("responseCode").equals("1")) {
			theForm.setNAME(js.get("hostName").toString());
			theForm.setDESC(js.get("description").toString());
			theForm.setIP(js.get("ip").toString());
			int cpu = (Integer) js.get("cpuSize");
			Double mem = Double.parseDouble(js.get("memSize").toString());
			mem = mem / (1024 * 1024);
			DecimalFormat nf = new DecimalFormat("0");
			theForm.setCPU(String.valueOf(cpu));
			theForm.setMEMORY(String.valueOf(nf.format(mem)));
			theForm.setSTATE(js.get("enabled").toString());
			Double store = Double.parseDouble(js.get("srSize").toString());
			store = store / (1024 * 1024 * 1024);
			theForm.setSTORAGE(String.valueOf(nf.format(store)));
			theForm.setMaster(js.get("master") == null ? false : js.getBoolean("master"));
			theForm.setHostCpuSpeed(js.get("hostCpuSpeed") == null ? 0.00 : js
					.getDouble("hostCpuSpeed"));
			theForm.setDESC(js.get("hostCpuDESC") == null ? "暂无" : js.getString("hostCpuDESC"));

			return "host_info";
		} else {
			return null;
		}

	}

	/**
	 * 
	 * @Title: goSnapshotPage
	 * @Description: 进入创建虚拟机快照页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 12, 2012 11:38:32 AM
	 */
	public String goSnapshotPage() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String entity_id = request.getParameter("entity_id");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		request.setAttribute("pool_uuid", pool_uuid);
		request.setAttribute("host_uuid", host_uuid);
		request.setAttribute("entity_id", entity_id);
		return "shot_page";
	}

	/**
	 * 
	 * @Title: createVMSnapShot
	 * @Description: 创建虚拟机快照
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @createtime Sep 10, 2012 9:12:30 AM
	 */
	@MethodLog(remark = "XenManAction-createVMSnapShot", type = 1, message = "创建虚拟机快照")
	public String createVMSnapShot() throws UnsupportedEncodingException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String entity_id = request.getParameter("entity_id");
		String shot_name = request.getParameter("name");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String desc = request.getParameter("desc");
		desc = URLDecoder.decode(desc, "utf-8");
		shot_name = URLDecoder.decode(shot_name, "utf-8");
		VMHostObj v = new VMHostObj();
		v.setID(Integer.parseInt(entity_id));
		v = vmHostService.queryByObj(v);
		String VH_UUID = v.getVH_UUID();
		if (pool_uuid == null || "".equals(pool_uuid)) {
			pool_uuid = host_uuid;
			host_uuid = "";
		}
		String result = xenManService.createVMSnapShot(VH_UUID, shot_name, desc, pool_uuid,
				host_uuid);

		// 插入日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(1);
		operObj.setMESSAGE("创建虚拟机快照");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		response.setContentType("type/html;charset=UTF-8");

		// out = response.getWriter();
		// out.println(result);
		// out.close();
		PrintWriterOut.printWirter(response, result);
		return null;

	}

	/**
	 * 
	 * @Title: goCreateVMByTem
	 * @Description: 进入通过模板（带操作系统）创建虚拟机页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 13, 2012 10:40:08 AM
	 */
	public String goCreateVMByTem() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String entity_id = request.getParameter("entity_id");
		String parent_id = request.getParameter("parent_id");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String parent_type = request.getParameter("parent_type");
		String nodeId = request.getParameter("nodeId");
		if (parent_type.equals(TypeConstant.XEN_CLUSTER)) {
			XenEntityTreeObj xo = new XenEntityTreeObj();
			xo.setType(TypeConstant.XEN_HOST);
			List hostList = xenEntityTreeService.queryForTree(xo);
			theForm.setResultList(hostList);
		}
		VMHostObj v = new VMHostObj();
		v.setID(Integer.parseInt(entity_id));
		v = vmHostService.queryByObj(v);
		XenEntityTreeObj en = new XenEntityTreeObj();
		en.setId(Integer.parseInt(parent_id));
		en = xenEntityTreeService.queryTreeNode(en);
		String clusterId = String.valueOf(en.getParentId());
		if (pool_uuid == null || "".equals(pool_uuid)) {
			pool_uuid = host_uuid;
			host_uuid = "";
		}
		String result = xenManService.listVMInfo(v.getVH_UUID(), pool_uuid);
		JSONObject js = JSONObject.fromObject(result);

		if (js.get("responseCode").equals("1")) {
			Double dynamicMax = Double.parseDouble(js.get("memDynamicMax").toString())
					/ (1024 * 1024);
			Double dynamicMin = Double.parseDouble(js.get("memDynamicMin").toString())
					/ (1024 * 1024);
			Double staticMax = Double.parseDouble(js.get("memStaticMax").toString())
					/ (1024 * 1024);
			Double staticMin = Double.parseDouble(js.get("memStaticMin").toString())
					/ (1024 * 1024);
			Double storage = Double.parseDouble(js.get("virtualSrSize").toString())
					/ (1024 * 1024 * 1024);
			DecimalFormat nf = new DecimalFormat("0");

			TbCloud2NetInfoObj netObj = new TbCloud2NetInfoObj();
			TbCloud2IpInfoObj ipObj = new TbCloud2IpInfoObj();
			List netLst = netService.queryNetListByObj(netObj);
			if (netLst != null && netLst.size() > 0) {
				ipObj.setNET_ID(((TbCloud2NetInfoObj) netLst.get(0)).getNET_ID());
				theForm.setNET(((TbCloud2NetInfoObj) netLst.get(0)).getNET_ID());
			}
			ipObj.setISUSED("0");
			List<TbCloud2IpInfoObj> ipLst = ipService.queryForListByIPObj(ipObj);
			if (ipLst != null && ipLst.size() > 0) {
				theForm.setIP(ipLst.get(0).getIP_ID());
			}
			theForm.setNetList(netLst);
			theForm.setIpList(ipLst);
			theForm.setNAME_EN(js.get("vmName").toString());
			theForm.setCPU(js.get("cpuNum").toString());
			theForm.setCURRENTCPU(js.get("cpuMax").toString());
			boolean vdiShare = js.get("vdiShare") == null ? true : js.getBoolean("vdiShare");
			String srHostUuid = js.get("srHostUuid") == null ? "" : js.getString("srHostUuid");
			theForm.setMEMORY(String.valueOf(nf.format(dynamicMax)));
			theForm.setDynamicMax(String.valueOf(nf.format(dynamicMax)));
			theForm.setDynamicMin(String.valueOf(nf.format(dynamicMin)));
			theForm.setStaticMax(String.valueOf(nf.format(staticMax)));
			theForm.setStaticMin(String.valueOf(nf.format(staticMin)));
			theForm.setSTORAGE(String.valueOf(nf.format(storage)));
			request.setAttribute("entity_id", entity_id);
			request.setAttribute("parent_id", parent_id);
			request.setAttribute("clusterId", clusterId);
			request.setAttribute("pool_uuid", pool_uuid);
			request.setAttribute("host_uuid", host_uuid);
			request.setAttribute("vdiShare", vdiShare);
			request.setAttribute("srHostUuid", srHostUuid);
			request.setAttribute("nodeId", nodeId);
		}
		return "vm_page";
	}

	/**
	 * 
	 * @Title: goCreateVMByTem
	 * @Description: 进入通过模板（带操作系统）创建虚拟机页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 13, 2012 10:40:08 AM
	 */
	public String goQueryCreateVMByTem() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String entity_id = request.getParameter("entity_id");
		String parent_id = request.getParameter("parent_id");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		XenEntityTreeObj en = new XenEntityTreeObj();
		en.setId(Integer.parseInt(parent_id));
		en = xenEntityTreeService.queryTreeNode(en);
		String clusterId = String.valueOf(en.getParentId());
		theForm.setNAME_EN("新建虚拟机");
		request.setAttribute("entity_id", entity_id);
		request.setAttribute("parent_id", parent_id);
		request.setAttribute("clusterId", clusterId);
		request.setAttribute("pool_uuid", pool_uuid);
		request.setAttribute("host_uuid", host_uuid);
		return "queryCreateByTem";
	}

	public String queryCreateVMByTem() throws Exception {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String entity_id = request.getParameter("entity_id");
		String parent_id = request.getParameter("parent_id");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String name = request.getParameter("name");
		name = URLDecoder.decode(name, "utf-8");
		String clusterId = request.getParameter("clusterId");
		long memSize = 0;

		VMHostObj v = new VMHostObj();
		v.setID(Integer.parseInt(entity_id));
		v = vmHostService.queryByObj(v);
		String tem_uuid = v.getVH_UUID();
		XenEntityTreeObj e = new XenEntityTreeObj();
		e.setEntityId(entity_id);
		e.setParentId(Integer.parseInt(parent_id));
		e.setName(name);
		if (pool_uuid == null || "".equals(pool_uuid)) {
			pool_uuid = host_uuid;
			host_uuid = "";
		}
		String result = xenManService.queryCreateVMByTem(tem_uuid, e, clusterId, pool_uuid,
				host_uuid);
		// // 插入操作日志
		// TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		// operObj.setREMARK("");
		// operObj.setOPERTYPE(4);
		// operObj.setMESSAGE("查询通过模板创建虚拟机");
		// int oper_result = 1;
		// operObj.setRESULT(oper_result);
		// operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(
		// Constant.FUNCID).toString()));
		// @SuppressWarnings("unused")
		// int retOper = operationService.insertByObj(operObj);// 写操作日志
		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(result);
		// out.close();
		// out = response.getWriter();
		// out.println(result);
		// out.close();
		PrintWriterOut.printWirter(response, result);
		Constant.PROCESS_LEVEL = 0;
		return null;
	}

	/**
	 * 
	 * @Title: cloneVMByTem
	 * @Description: 通过模板创建虚拟机（带操作系统）
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 10, 2012 9:23:06 AM
	 */
	@MethodLog(remark = "XenManAction-createVMByTem", type = 1, message = "通过模板创建虚拟机（带操作系统）")
	public String createVMByTem() throws Exception {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String entity_id = request.getParameter("entity_id");
		String parent_id = request.getParameter("parent_id"); // 当前模版所在位置的父元素
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String name = request.getParameter("name");
		name = URLDecoder.decode(name, "utf-8");
		String cpu = request.getParameter("cpu");
		String currentcpu = request.getParameter("currentcpu");
		String storage = request.getParameter("storage");
		String staticMin = request.getParameter("staticMin");
		String dynamicMin = request.getParameter("dynamicMin");
		String dynamicMax = request.getParameter("dynamicMax");
		String staticMax = request.getParameter("staticMax");
		String clusterId = request.getParameter("clusterId");
		String selectHostUuid = request.getParameter("selectHostUuid");
		String ipAddress = request.getParameter("ipAddress");
		if (selectHostUuid != null && !selectHostUuid.equals("")
				&& selectHostUuid.split(",").length == 2) {
			host_uuid = selectHostUuid.split(",")[0];
			parent_id = selectHostUuid.split(",")[1];// 模版创建成功虚拟机后，虚拟机所在位置的父元素
		}
		VMHostObj v = new VMHostObj();
		v.setID(Integer.parseInt(entity_id));
		v = vmHostService.queryByObj(v);
		String tem_uuid = v.getVH_UUID();
		XenEntityTreeObj e = new XenEntityTreeObj();
		e.setEntityId(entity_id);
		e.setParentId(Integer.parseInt(parent_id));
		e.setName(name);
		if (pool_uuid == null || "".equals(pool_uuid)) {
			pool_uuid = host_uuid;
			host_uuid = "";
		}
		String result = xenManService.createVMByTem(tem_uuid, cpu, currentcpu, staticMin,
				dynamicMin, dynamicMax, staticMax, e, clusterId, pool_uuid, host_uuid, ipAddress);
		// 插入操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(1);
		operObj.setMESSAGE("通过模板克隆xen虚拟机");
		int oper_result = 1;
		operObj.setRESULT(oper_result);
		Object object = request.getSession().getAttribute(Constant.FUNCID);
		if (object != null) {
			String tempInt = object.toString();
			operObj.setFUNCID(Integer.parseInt(tempInt));
			@SuppressWarnings("unused")
			int retOper = operationService.insertByObj(operObj);// 写操作日志
			response.setContentType("type/html;charset=UTF-8");
		}
		PrintWriterOut.printWirter(response, result);
		Constant.PROCESS_LEVEL = 0;
		return null;
	}

	/**
	 * 
	 * @Title: goCreateTemPage
	 * @Description: 进入（通过虚拟机）创建模板页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 12, 2012 3:21:05 PM
	 */
	public String goCreateTemPage() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String entity_id = request.getParameter("entity_id");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		request.setAttribute("entity_id", entity_id);
		request.setAttribute("pool_uuid", pool_uuid);
		request.setAttribute("host_uuid", host_uuid);
		return "tem_page";
	}

	/**
	 * 
	 * @Title: createTemByVm
	 * @Description: 通过虚拟机创建模板
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 12, 2012 3:14:50 PM
	 */
	@MethodLog(remark = "XenManAction-createTemByVM", type = 1, message = "通过虚拟机创建模板")
	public String createTemByVM() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String entity_id = request.getParameter("entity_id");
		String temName = request.getParameter("name");
		String pool_uuid = request.getParameter("pool_uuid");
		temName = URLDecoder.decode(temName, "utf-8");
		VMHostObj v = new VMHostObj();
		v.setID(Integer.parseInt(entity_id));
		v = vmHostService.queryByObj(v);
		String vh_uuid = v.getVH_UUID();
		XenEntityTreeObj e = new XenEntityTreeObj();
		e.setEntityId(entity_id);
		e = xenEntityTreeService.queryTreeNode(e);
		int parent_id = e.getParentId();
		Constant.PROCESS_LEVEL = 90;
		String result = xenManService.createTemByVm(temName, vh_uuid, parent_id, pool_uuid);

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(1);
		operObj.setMESSAGE("通过虚拟机创建xen虚拟机模板");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		Constant.PROCESS_LEVEL = 100;
		response.setContentType("type/html;charset=UTF-8");
		PrintWriterOut.printWirter(response, result);
		Constant.PROCESS_LEVEL = 0;
		return null;

	}

	/**
	 * 
	 * @Title: goCloneVM
	 * @Description: 进入克隆虚拟机页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 13, 2012 2:30:28 PM
	 */
	public String goCloneVM() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String entity_id = request.getParameter("entity_id");
		String parent_id = request.getParameter("parent_id");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		XenEntityTreeObj en = new XenEntityTreeObj();
		en.setId(Integer.parseInt(parent_id));
		en = xenEntityTreeService.queryTreeNode(en);
		String clusterId = String.valueOf(en.getParentId());
		String param = "/xen/storages/lookup/[hostUuid:" + host_uuid + "].[poolUuid:" + pool_uuid
				+ "]/";
		String result = InvokeUtil.invoke(param);
		JSONArray jo = JSONArray.fromObject(result);
		List<JSONObject> hostSList = JSONArray.toList(jo, JSONObject.class);
		List<DataStoreObj> store = new ArrayList<DataStoreObj>();
		if (hostSList.get(0).get("responseCode").equals("1")) {
			hostSList.remove(0);
			for (JSONObject json : hostSList) {
				DataStoreObj da = new DataStoreObj();
				String name = (String) json.get("srName");
				String type = (String) json.get("srType");
				if (!name.equals("XenServer Tools") && (type.equals("lvm") || type.equals("nfs"))) {
					da.setNAME(name);
					da.setStore_uuid((String) json.get("srUuid"));
					store.add(da);
				}
			}
		}
		theForm.setResultList(store);
		request.setAttribute("entity_id", entity_id);
		request.setAttribute("parent_id", parent_id);
		request.setAttribute("clusterId", clusterId);
		request.setAttribute("pool_uuid", pool_uuid);
		request.setAttribute("host_uuid", host_uuid);
		return "clone_page";
	}

	/**
	 * 
	 * @Title: cloneVMByvm
	 * @Description: 通过虚拟机克隆虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 13, 2012 2:29:39 PM
	 */
	@MethodLog(remark = "XenManAction-cloneVMByvm", type = 1, message = "通过虚拟机克隆虚拟机")
	public String cloneVMByvm() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String entity_id = request.getParameter("entity_id");
		String parent_id = request.getParameter("parent_id");
		String clusterId = request.getParameter("clusterId");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String name = request.getParameter("name");
		String desc = request.getParameter("desc");
		desc = URLDecoder.decode(desc, "utf-8");
		String store_uuid = request.getParameter("store_uuid");
		name = URLDecoder.decode(name, "utf-8");
		XenEntityTreeObj e = new XenEntityTreeObj();
		e.setEntityId(entity_id);
		VMHostObj v = new VMHostObj();
		v.setID(Integer.parseInt(entity_id));
		v = vmHostService.queryByObj(v);
		String vh_uuid = v.getVH_UUID();
		XenEntityTreeObj en = new XenEntityTreeObj();
		en.setParentId(Integer.parseInt(parent_id));
		en.setName(name);
		if (pool_uuid == null || "".equals(pool_uuid)) {
			pool_uuid = host_uuid;
			host_uuid = "";
		}
		String result = xenManService.cloneVMByVM(vh_uuid, en, clusterId, pool_uuid, host_uuid,
				store_uuid, desc);

		response.setContentType("type/html;charset=UTF-8");
		// 插入操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(1);
		operObj.setMESSAGE("通过虚拟机克隆xen虚拟机");
		int oper_result = 1;
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		// out = response.getWriter();
		// out.println(result);
		// out.close();

		PrintWriterOut.printWirter(response, result);
		Constant.PROCESS_LEVEL = 0;
		return null;
	}

	/**
	 * 
	 * @Title: snapshotManager
	 * @Description: 查询某一虚拟机的快照列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 14, 2012 9:14:11 AM
	 */
	@MethodLog(remark = "XenManAction-snapshotManager", type = 4, message = "查询某一虚拟机的快照列表")
	public String snapshotManager() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String entity_id = request.getParameter("entity_id");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String vm_uuid = request.getParameter("vm_uuid");
		VMHostObj v = new VMHostObj();
		v.setID(Integer.parseInt(entity_id));
		v = vmHostService.queryByObj(v);
		String vh_uuid = v.getVH_UUID();
		if (pool_uuid == null || "".equals(pool_uuid)) {
			pool_uuid = host_uuid;
			host_uuid = "";
		}
		String result = xenManService.snapshotManager(vh_uuid, pool_uuid, host_uuid);

		// // 写操作日志
		// TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		// operObj.setREMARK("");
		// operObj.setOPERTYPE(4);
		// operObj.setMESSAGE("查询某一虚拟机快照");
		// int oper_result = 1;
		// if (request.getSession().getAttribute(Constant.FUNCID) != null) {
		// operObj.setFUNCID(Integer.parseInt(request.getSession()
		// .getAttribute(Constant.FUNCID).toString()));
		// }
		// operObj.setRESULT(oper_result);
		// @SuppressWarnings("unused")
		// int retOper = operationService.insertByObj(operObj);

		JSONArray j = JSONArray.fromObject(result);
		List<JSONObject> jsonO = JSONArray.toList(j, JSONObject.class);
		List<Snapshot> list = new ArrayList<Snapshot>();
		if (jsonO.get(0).get("responseCode").equals("1")) {
			jsonO.remove(0);
			for (JSONObject js : jsonO) {
				Snapshot s = new Snapshot();
				s.setSnap_uuid(js.get("snapshotUuid").toString());
				s.setDescription((String) js.get("description"));
				s.setName(js.get("snapshotName").toString());
				String time = js.get("createTime").toString();
				Date d = new Date(Long.parseLong(time));
				SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				time = formatter1.format(d);
				s.setTime(time);
				list.add(s);
			}
		}
		theForm.setResultList(list);
		request.setAttribute("pool_uuid", pool_uuid);
		request.setAttribute("host_uuid", host_uuid);
		request.setAttribute("vm_uuid", vm_uuid);
		return "snapshot_Manager";

	}

	/**
	 * 
	 * @Title: delSnapshot
	 * @Description: 删除虚拟机快照
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 17, 2012 10:38:10 AM
	 */
	@MethodLog(remark = "XenManAction-delSnapshot", type = 2, message = "删除虚拟机快照")
	public String delSnapshot() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String uuid = request.getParameter("snap_uuid");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		if (pool_uuid == null || "".equals(pool_uuid)) {
			pool_uuid = host_uuid;
			host_uuid = "";
		}
		String result = xenManService.delSnapshot(uuid, pool_uuid, host_uuid);

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除xen虚拟机快照");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(result);
		// out.close();

		PrintWriterOut.printWirter(response, result);
		return null;
	}

	/**
	 * 
	 * @Title: recoverSnapShot
	 * @Description: 恢复虚拟机快照
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 17, 2012 10:48:16 AM
	 */
	@MethodLog(remark = "XenManAction-recoverSnapShot", type = 3, message = "恢复虚拟机快照")
	public String recoverSnapShot() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String uuid = request.getParameter("snap_uuid");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		if (pool_uuid == null || "".equals(pool_uuid)) {
			pool_uuid = host_uuid;
			host_uuid = "";
		}
		String result = xenManService.recoverSnapShot(uuid, pool_uuid, host_uuid);

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(1);
		operObj.setMESSAGE("恢复xen虚拟机快照");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(result);
		// out.close();
		PrintWriterOut.printWirter(response, result);
		return null;
	}

	/**
	 * 
	 * @Title: listDefaultTem
	 * @Description: 查询所有默认模板
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 10, 2012 9:31:20 AM
	 */
	@MethodLog(remark = "XenManAction-listDefaultTem", type = 4, message = "查询所有默认模板")
	public String listDefaultTem() {
		// String result = xenManService.listDefaultTem();
		return null;
	}

	/**
	 * 
	 * @Title: listAllHost
	 * @Description: 查询所有存在的主机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 11, 2012 9:44:52 AM
	 */
	@MethodLog(remark = "XenManAction-listAllHost", type = 4, message = "查询所有存在的主机")
	public String listAllHost() {
		// String result = xenManService.listAllHostByCluster(0);
		// String vm_result = xenManService.listAllVMByHost();
		return "clusterInfo";
	}

	/**
	 * 
	 * @Title: listAllVMByHost
	 * @Description: 获取指定主机下的所有虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 12, 2012 2:18:34 PM
	 */
	@MethodLog(remark = "XenManAction-listAllVMByHost", type = 4, message = "获取指定主机下的所有虚拟机")
	public String listAllVMByHost() {
		// String result = xenManService.listAllVMByHost();
		return null;
	}

	/**
	 * 
	 * @Title: getProgressbarVal
	 * @Description: 获取进度条值
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Jul 25, 2012 1:39:14 PM
	 */
	@MethodLog(remark = "XenManAction-getProgressbarVal", type = 4, message = "获取进度条值")
	public String getProgressbarVal() throws Exception {
		int val = Constant.PROCESS_LEVEL;
		String value = "{'val':" + val + "}";
		Struts2Utils.getResponse().setContentType("text/html;charset=UTF-8");
		JSONObject jo = JSONObject.fromObject(value);
		PrintWriter out = Struts2Utils.getResponse().getWriter();
		out.print(jo.toString());
		out.close();
		return null;
	}

	/**
	 * 
	 * @Title: listTeminfo
	 * @Description: 获取模板的详细信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 19, 2012 11:16:35 AM
	 */
	@MethodLog(remark = "XenManAction-listTeminfo", type = 4, message = "获取模板的详细信息")
	public String listTeminfo() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String entity_id = request.getParameter("entity_id");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		VMHostObj v = new VMHostObj();
		v.setID(Integer.parseInt(entity_id));
		v = vmHostService.queryByObj(v);
		String uuid = v.getVH_UUID();
		if (pool_uuid == null || "".equals(pool_uuid)) {
			pool_uuid = host_uuid;
			host_uuid = "";
		}
		String result = xenManService.listTeminfo(uuid, pool_uuid, host_uuid);

		// // 写操作日志
		// TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		// operObj.setREMARK("");
		// operObj.setOPERTYPE(4);
		// operObj.setMESSAGE("获取模板详细信息");
		// int oper_result = 1;
		// if (request.getSession().getAttribute(Constant.FUNCID) != null) {
		// operObj.setFUNCID(Integer.parseInt(request.getSession()
		// .getAttribute(Constant.FUNCID).toString()));
		// }
		// operObj.setRESULT(oper_result);
		// @SuppressWarnings("unused")
		// int retOper = operationService.insertByObj(operObj);

		JSONObject js = JSONObject.fromObject(result);
		if (js.get("responseCode").equals("1")) {
			theForm.setIM_NAME(js.get("vmName").toString());
			theForm.setIM_DESC(js.get("description").toString());
			int cpu = (Integer) js.get("cpuSize");
			Double mem = Double.parseDouble(js.get("memSize").toString());
			mem = mem / (1024 * 1024);
			DecimalFormat nf = new DecimalFormat("0");
			theForm.setCPU(String.valueOf(cpu));
			theForm.setMEMORY(String.valueOf(nf.format(mem)));
			theForm.setOsType(js.get("osName").toString());
			return "tem_info";
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @Title: saveCluster
	 * @Description: 保存集群信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 10, 2012 10:43:29 AM
	 */
	@MethodLog(remark = "XenManAction-saveCluster", type = 1, message = "保存集群信息")
	public String saveCluster() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String parent_id = request.getParameter("ID");// 集群父节点id
		String clusterName = request.getParameter("clusterName");
		String clusterType = request.getParameter("clustertype");
		// Xen主服务器信息
		String hostName = request.getParameter("hostName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String result = xenManService.saveCluster(clusterName, clusterType, parent_id, hostName,
				username, password);

		return result;
	}

	/**
	 * 
	 * @Title: modCluster
	 * @Description: 进入修改集群信息页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:36:53 AM
	 */
	public String modCluster() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String entity_id = request.getParameter("entity_id");
		ClusterObj c = new ClusterObj();
		c.setId(Integer.parseInt(entity_id));
		c = clusterService.queryByObj(c);
		if (theForm == null) {
			theForm = new XenManForm();
			theForm.setNAME(c.getName());
			theForm.setTYPE(c.getType());
		}
		return "mod_cluster";

	}

	/**
	 * 
	 * @Title: addXenDataCenter
	 * @Description: 添加xen数据中心
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 11, 2012 9:53:32 AM
	 */
	public String addXenDataCenter() {
		return "addXenDataCenter";
	}

	/**
	 * 
	 * @Title: saveDataCenter
	 * @Description: 保存创建的数据中心
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 11, 2012 9:26:37 AM
	 */
	@MethodLog(remark = "XenManAction-saveDataCenter", type = 1, message = "保存创建的数据中心")
	public String saveDataCenter() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String name = request.getParameter("name");
		name = URLDecoder.decode(name, "utf-8");
		XenEntityTreeObj e = new XenEntityTreeObj();
		// e.setParentId(1);
		e.setEntityId("1");
		e.setName(name);
		e.setType(TypeConstant.XEN_DATACENTER);
		int ret = xenEntityTreeService.insertTreeNode(e);
		String result = null;
		if (ret != -1) {
			result = "{'result':1}";
		} else {
			result = "{'result':0}";
		}
		Struts2Utils.getResponse().setContentType("text/html;charset=UTF-8");
		JSONObject jo = JSONObject.fromObject(result);
		PrintWriter out = Struts2Utils.getResponse().getWriter();
		out.print(jo.toString());
		out.close();
		return null;
	}

	/**
	 * 
	 * @Title: goconXenCluster
	 * @Description: 进入同步到xen集群页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 12, 2012 9:19:43 AM
	 */
	public String goConXenCluster() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String parent_id = request.getParameter("parent_id");
		request.setAttribute("parent_id", parent_id);
		return "con_cluster";
	}

	public String checkConXenCluster() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String name = request.getParameter("name");
		name = URLDecoder.decode(name, "utf-8");
		boolean flag = xenManService.checkConXenCluster(name);
		String result = "1";
		if (flag) {
			result = "2";
		}
		JSONObject jo = new JSONObject();
		jo.put("result", result);
		PrintWriter out = Struts2Utils.getResponse().getWriter();
		out.print(jo.toString());
		out.close();
		return null;
	}

	/**
	 * 
	 * @Title: conXenCluster
	 * @Description: 同步xen集群
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 12, 2012 9:19:43 AM
	 */
	@MethodLog(remark = "XenManAction-synConXenCluster", type = 1, message = "同步xen集群")
	public String synConXenCluster() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String parent_id = request.getParameter("parent_id");
		String name = request.getParameter("name");
		name = URLDecoder.decode(name, "utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		password = URLDecoder.decode(password, "utf-8");
		String result = xenManService.conXenCluster(name, username, password,
				Integer.parseInt(parent_id));

		// // 写操作日志
		// TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		// operObj.setREMARK("");
		// operObj.setOPERTYPE(1);
		// operObj.setMESSAGE("同步XEN集群");
		// int oper_result = 1;
		// if (request.getSession().getAttribute(Constant.FUNCID) != null) {
		// operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
		// .toString()));
		// }
		// operObj.setRESULT(oper_result);
		// @SuppressWarnings("unused")
		// int retOper = operationService.insertByObj(operObj);

		Struts2Utils.getResponse().setContentType("text/html;charset=UTF-8");
		JSONObject jo = JSONObject.fromObject(result);
		PrintWriter out = Struts2Utils.getResponse().getWriter();
		out.print(jo.toString());
		out.close();
		return null;
	}

	/**
	 * 在dvd中加载镜像文件
	 * 
	 * @return
	 * @throws Exception
	 */
	@MethodLog(remark = "XenManAction-loadVdiForDVD", type = 4, message = "在dvd中加载镜像文件")
	public String loadVdiForDVD() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String vdiUuid = request.getParameter("vdiUuid");
		String poolUuid = request.getParameter("poolUuid");
		String vmUuid = request.getParameter("vmUuid");
		/**
		 * 请求主体
		 */
		String result = xenManService.loadVdiForDVD(vdiUuid, poolUuid, vmUuid);

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(1);
		operObj.setMESSAGE("在xen dvd中加载镜像文件");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		Struts2Utils.getResponse().setContentType("text/html;charset=UTF-8");
		JSONObject jo = JSONObject.fromObject(result);
		PrintWriter out = Struts2Utils.getResponse().getWriter();
		out.print(jo.toString());
		out.close();
		return null;
	}

	/**
	 * 为虚拟机的DVD中加载xenTool，为其安装xenTool 工具
	 * 
	 * @return
	 * @throws Exception
	 */
	public String loadToolForDVD() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String poolUuid = request.getParameter("poolUuid");
		String vmUuid = request.getParameter("vmUuid");
		String result = xenManService.loadToolForDVD(poolUuid, vmUuid);

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(1);
		operObj.setMESSAGE("为xen虚拟机的dvd加载xentool");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		Struts2Utils.getResponse().setContentType("text/html;charset=UTF-8");
		JSONObject jo = JSONObject.fromObject(result);
		PrintWriter out = Struts2Utils.getResponse().getWriter();
		out.print(jo.toString());
		out.close();
		return null;
	}

	public String removeVdiForDVD() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String poolUuid = request.getParameter("poolUuid");
		String vmUuid = request.getParameter("vmUuid");
		String result = xenManService.removeVdiForDVD(poolUuid, vmUuid);

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("移除xen dvd中的存储");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		Struts2Utils.getResponse().setContentType("text/html;charset=UTF-8");
		JSONObject jo = JSONObject.fromObject(result);
		PrintWriter out = Struts2Utils.getResponse().getWriter();
		out.print(jo.toString());
		out.close();
		return null;
	}

	/**
	 * 删除虚拟机的存储（VDI）
	 * 
	 * @return
	 * @throws Exception
	 */
	@MethodLog(remark = "XenManAction-deleteVdiVm", type = 2, message = "删除虚拟机的存储（VDI）")
	public String deleteVdiVm() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String vdiUuid = request.getParameter("vdiUuid");
		String poolUuid = request.getParameter("poolUuid");
		String result = xenManService.deleteVdiVm(poolUuid, vdiUuid);

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("移除xen虚拟机中的存储");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		Struts2Utils.getResponse().setContentType("text/html;charset=UTF-8");
		JSONObject jo = JSONObject.fromObject(result);
		PrintWriter out = Struts2Utils.getResponse().getWriter();
		out.print(jo.toString());
		out.close();
		return null;
	}

	/**
	 * 添加虚拟机的存储（VDI）
	 * 
	 * @return
	 * @throws Exception
	 */
	@MethodLog(remark = "XenManAction-addVdiForVm", type = 1, message = "添加虚拟机的存储（VDI）")
	public String addVdiForVm() throws Exception {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String poolUuid = request.getParameter("poolUuid");
		String hostUuid = request.getParameter("hostUuid");
		String vmUuid = request.getParameter("vmUuid");
		String oper = request.getParameter("oper");
		String param = "/xen/storages/add/lookup/[hostUuid:" + hostUuid + "].[poolUuid:" + poolUuid
				+ "]/";
		String result = InvokeUtil.invoke(param);

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(1);
		operObj.setMESSAGE("添加xen虚拟机存储");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		JSONArray jo = JSONArray.fromObject(result);
		List<JSONObject> hostSList = JSONArray.toList(jo, JSONObject.class);
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (hostSList.get(0).get("responseCode").equals("1")) {
			hostSList.remove(0);
			for (JSONObject json : hostSList) {
				String name = (String) json.get("srName");
				String srType = (String) json.get("srType");
				if ("Local storage".equals(name)) {
					name = "本地存储";
				}
				if (!"udev".equals(srType) && !"iso".equals(srType)) {
					String hostName = (String) json.get("hostName");
					String uuid = (String) json.get("srUuid");
					DecimalFormat nf = new DecimalFormat("0");
					String allsize = (String) json.get("srSize").toString();
					double size = Double.parseDouble(allsize) / (1024 * 1024 * 1024);
					long forsize = Long.parseLong(nf.format(size));
					String useSize = (String) json.get("srUtilisation").toString();
					double use = Double.parseDouble(useSize) / (1024 * 1024 * 1024);
					long foruse = Long.parseLong(nf.format(use));
					boolean shared = Boolean.parseBoolean(json.get("srShared").toString());
					String temp = "";
					if (!shared) {
						temp = hostName + "  上的" + name + "    " + (forsize - foruse) + " GB 可用空间"
								+ "    总计" + forsize + " GB";
					} else {
						temp = name + "    " + (forsize - foruse) + " GB 可用空间" + "    总计" + forsize
								+ " GB";
					}
					map.put(uuid, temp);
				}
			}

		}
		if ("edit".equals(oper)) {
			request.setCharacterEncoding("utf-8");
			String vdiName = request.getParameter("vdiName");
			vdiName = URLDecoder.decode(vdiName, "utf-8");
			String vdiUuid = request.getParameter("vdiUuid");
			String vdiSize = request.getParameter("vdiSize");
			String srUuid = request.getParameter("srUuid");
			theForm.setNAME(vdiName);
			theForm.setSrs(map);
			theForm.setVdiSize(Long.parseLong(vdiSize));
			request.setAttribute("srUuid", srUuid);
			request.setAttribute("poolUuid", poolUuid);
			request.setAttribute("vmUuid", vmUuid);
			request.setAttribute("vdiUuid", vdiUuid);
			request.setAttribute("oper", oper);
			return "edit_vdi";
		}
		theForm.setSrs(map);
		request.setAttribute("poolUuid", poolUuid);
		request.setAttribute("vmUuid", vmUuid);
		request.setAttribute("oper", oper);
		return "add_vdi";
	}

	@MethodLog(remark = "XenManAction-addVdiForVm2", type = 1, message = "给虚拟机添加磁盘镜像")
	public String addVdiForVm2() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		request.setCharacterEncoding("utf-8");
		String mate = request.getParameter("mate");
		String poolUuid = request.getParameter("poolUuid");
		String vmUuid = request.getParameter("vmUuid");
		String vdiName = request.getParameter("vdiName");
		vdiName = URLDecoder.decode(vdiName, "utf-8");
		String vdiDesc = request.getParameter("vdiDesc");
		vdiDesc = URLDecoder.decode(vdiDesc, "utf-8");
		String srUuid = request.getParameter("srUuid");
		long vdiSize = Long.parseLong(request.getParameter("vdiSize"));
		if ("GB".equals(mate)) {
			vdiSize = vdiSize * 1024 * 1024 * 1024;
		} else {
			vdiSize = vdiSize * 1024 * 1024;
		}
		String vdiType = "user";
		String param = "/xen/vdi/vm/add/[srUuid:" + srUuid + "].[poolUuid:" + poolUuid
				+ "].[vdiName:" + vdiName + "].[vdiDesc:" + vdiDesc + "].[vdiSize:" + vdiSize
				+ "].[vdiType:" + vdiType + "].[vmUuid:" + vmUuid + "]/";
		String result = InvokeUtil.invoke(param);

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(1);
		operObj.setMESSAGE("为xen虚拟机添加存储");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		Struts2Utils.getResponse().setContentType("text/html;charset=UTF-8");
		JSONObject jo = JSONObject.fromObject(result);
		PrintWriter out = Struts2Utils.getResponse().getWriter();
		if (jo.get("responseCode").equals("1")) {
			Double vdiSizeTemp = Double.parseDouble(jo.getString("vdiSize").toString())
					/ (1024 * 1024 * 1024);
			String srType = jo.getString("srType");
			if (!"iso".equals(srType)) {
				DecimalFormat nf = new DecimalFormat("0");
				jo.put("vdiSize", Long.parseLong(nf.format(vdiSizeTemp).toString()));
				String param0 = "/xen/vminfo/lookup/[poolUuid:" + poolUuid + "].[vmUuid:" + vmUuid
						+ "]/";
				String result0 = InvokeUtil.invoke(param0);
				String state = "Running";
				JSONObject j0 = JSONObject.fromObject(result0);
				if ("1".equals(j0.get("responseCode"))) {
					state = (String) j0.get("powerState");
				}
				jo.put("powerState", state);
			}
		}
		out.print(jo.toString());
		out.close();
		return null;
	}

	@MethodLog(remark = "XenManAction-editVdiForVm2", type = 3, message = "编辑虚拟机的磁盘镜像")
	public String editVdiForVm2() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		request.setCharacterEncoding("utf-8");
		String mate = request.getParameter("mate");
		String poolUuid = request.getParameter("poolUuid");
		String vdiUuid = request.getParameter("vdiUuid");
		String vdiName = request.getParameter("vdiName");
		vdiName = URLDecoder.decode(vdiName, "utf-8");
		String vdiDesc = request.getParameter("vdiDesc");
		vdiDesc = URLDecoder.decode(vdiDesc, "utf-8");
		long vdiSize = Long.parseLong(request.getParameter("vdiSize"));
		if ("GB".equals(mate)) {
			vdiSize = vdiSize * 1024 * 1024 * 1024;
		} else {
			vdiSize = vdiSize * 1024 * 1024;
		}
		String param = "/xen/vdi/modify/[poolUuid:" + poolUuid + "].[vdiName:" + vdiName
				+ "].[vdiDesc:" + vdiDesc + "].[vdiSize:" + vdiSize + "].[vdiUuid:" + vdiUuid
				+ "]/";
		String result = InvokeUtil.invoke(param);

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(3);
		operObj.setMESSAGE("修改xen虚拟机存储");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		Struts2Utils.getResponse().setContentType("text/html;charset=UTF-8");
		JSONObject jo = JSONObject.fromObject(result);
		PrintWriter out = Struts2Utils.getResponse().getWriter();
		if (jo.get("responseCode").equals("1")) {
			Double vdiSizeTemp = Double.parseDouble(jo.getString("vdiSize").toString())
					/ (1024 * 1024 * 1024);
			DecimalFormat nf = new DecimalFormat("0");
			jo.put("vdiSize", Long.parseLong(nf.format(vdiSizeTemp).toString()));
		}
		out.print(jo.toString());
		out.close();
		return null;
	}

	/**
	 * 
	 * @Title: saveHost
	 * @Description: 添加主机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 12, 2012 2:12:46 PM
	 */
	@MethodLog(remark = "XenManAction-saveHost", type = 1, message = "添加主机")
	public String saveHost() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String parent_id = request.getParameter("ID");
		String ip = request.getParameter("ip");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String pool_uuid = request.getParameter("pool_uuid");
		String result = xenManService.saveHost(parent_id, username, password, ip, pool_uuid);

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(1);
		operObj.setMESSAGE("添加xen主机");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(result);
		// out.close();
		PrintWriterOut.printWirter(response, result);
		return null;

	}

	/**
	 * 
	 * @Title: saveHost
	 * @Description: 添加主机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 12, 2012 2:12:46 PM
	 */
	@MethodLog(remark = "XenManAction-saveHost1", type = 1, message = "添加主机")
	public String saveHost1() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String parent_id = request.getParameter("ID");
		String pool_uuid = request.getParameter("pool_uuid");
		request.setAttribute("ID", parent_id);
		request.setAttribute("poolUuid", pool_uuid);
		return "add_host";
	}

	/**
	 * 
	 * @Title: delHost
	 * @Description: 删除主机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 16, 2012 3:01:51 PM
	 */
	@MethodLog(remark = "XenManAction-delHost", type = 2, message = "删除主机")
	public String delHost() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String ID = request.getParameter("ID");
		String entity_id = request.getParameter("entity_id");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String result = null;
		XenEntityTreeObj en = new XenEntityTreeObj();
		en.setParentId(Integer.parseInt(ID));
		List<XenEntityTreeObj> list = xenEntityTreeService.queryForTree(en);
		if (list.size() != 0 && list != null) {
			result = "{responseCode:error}";
		}
		result = xenManService.delHost(ID, entity_id, pool_uuid, host_uuid);

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除xen主机");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(result);
		// out.close();
		PrintWriterOut.printWirter(response, result);
		return null;

	}

	/**
	 * 
	 * @Title: delHost
	 * @Description: 判断是否能删除主机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 16, 2012 3:01:51 PM
	 */
	public String isCanDelHost() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String ID = request.getParameter("ID");
		String entity_id = request.getParameter("entity_id");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String param = "/xen/is/can/eject/host/[hostUuid:" + host_uuid + "].[poolUuid:" + pool_uuid
				+ "]/";
		String result = InvokeUtil.invoke(param);
		JSONObject jo = JSONObject.fromObject(result);
		boolean flag = false;
		String strTemp = "";
		if (jo.get("responseCode").equals("1")) {
			flag = jo.getBoolean("isCanDel");
			if (!flag) {
				String str = jo.getString("msgKey");
				if ("0".equals(str)) {
					strTemp = "此主机为资源池的主控机，主控机只能最后一个删除,请先删除其他主机!";
				} else if ("1".equals(str)) {
					strTemp = "主机下有运行的虚拟机无法删除，请先关闭虚拟机!";
				} else {
					strTemp = "未知错误，无法删除主机!";
				}
			}
		}
		request.setAttribute("flag", flag);
		request.setAttribute("msg", strTemp);
		request.setAttribute("entity_id", entity_id);
		request.setAttribute("pool_uuid", pool_uuid);
		request.setAttribute("host_uuid", host_uuid);
		request.setAttribute("ID", ID);
		return "is_can_del_host";

	}

	/**
	 * 
	 * @Title: listStorage
	 * @Description: 获取主机的存储信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 16, 2012 9:49:33 AM
	 */
	@MethodLog(remark = "XenManAction-listStorage", type = 1, message = "获取主机的存储信息")
	public String listStorage() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String entityId = request.getParameter("entity_id");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		request.setAttribute("pool_uuid", pool_uuid);
		request.setAttribute("host_uuid", host_uuid);
		TbCloud2HostInfoObj t = new TbCloud2HostInfoObj();
		t.setId(Integer.parseInt(entityId));
		t = hostInfoService.queryByObj(t);
		String param = "/xen/storages/lookup/[hostUuid:" + t.getH_uuid() + "].[poolUuid:"
				+ pool_uuid + "]/";
		String result = InvokeUtil.invoke(param);

		// // 写操作日志
		// TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		// operObj.setREMARK("");
		// operObj.setOPERTYPE(4);
		// operObj.setMESSAGE("查询主机存储信息");
		// int oper_result = 1;
		// if (request.getSession().getAttribute(Constant.FUNCID) != null) {
		// operObj.setFUNCID(Integer.parseInt(request.getSession()
		// .getAttribute(Constant.FUNCID).toString()));
		// }
		// operObj.setRESULT(oper_result);
		// @SuppressWarnings("unused")
		// int retOper = operationService.insertByObj(operObj);

		JSONArray jo = JSONArray.fromObject(result);
		List<JSONObject> hostSList = JSONArray.toList(jo, JSONObject.class);

		String detachStorageParam = "/xen/storages/detach/lookup/[poolUuid:" + pool_uuid + "]/";
		String detachResult = InvokeUtil.invoke(detachStorageParam);
		JSONArray detachJo = JSONArray.fromObject(detachResult);
		List<JSONObject> detachList = JSONArray.toList(detachJo, JSONObject.class);

		List<XenStoreObj> store = new ArrayList<XenStoreObj>();
		if (hostSList.get(0).get("responseCode").equals("1")) {
			hostSList.remove(0);
			for (JSONObject json : hostSList) {
				XenStoreObj da = new XenStoreObj();
				String name = (String) json.get("srName");
				if (!name.equals("XenServer Tools")) {
					da.setName(name);
					da.setStore_uuid((String) json.get("srUuid"));
					DecimalFormat nf = new DecimalFormat("0");
					String allsize = (String) json.get("srSize").toString();
					double size = Double.parseDouble(allsize) / (1024 * 1024 * 1024);
					long forsize = Long.parseLong(nf.format(size));
					da.setSr_size(nf.format(size));
					String useSize = (String) json.get("srUtilisation").toString();
					double use = Double.parseDouble(useSize) / (1024 * 1024 * 1024);
					long foruse = Long.parseLong(nf.format(use));
					da.setFree_size(String.valueOf(foruse));
					String use_per;
					if (allsize.equals("0")) {
						use_per = "0";
					} else {
						use_per = String.valueOf(nf.format(100 * foruse / forsize));
					}

					da.setUse_per(use_per);
					da.setType((String) json.get("srType"));
					da.setShared(String.valueOf(json.get("srShared").toString()));
					da.setState(json.getString("srState"));
					da.setContraoper(json.get("powerState").toString());
					store.add(da);
				}
			}

		}
		// XenStoreObj obj = new XenStoreObj();
		// obj.setState("2");
		// List<XenStoreObj> dbList = xenStoreService.queryForListByObj(obj);
		// for(XenStoreObj x: dbList){
		// store.add(x);
		// }
		// //库中已分离的存储和后台查询出的已分离的存储匹配
		// if (detachList.get(0).get("responseCode").equals("1")) {
		// detachList.remove(0);
		// for(JSONObject json: detachList){
		// Boolean match = false;
		// for(XenStoreObj x: dbList){
		// if(json.getString("srUuid").equals(x.getStore_uuid())){
		// match = true;
		// }
		// }
		// if(!match){
		// XenStoreObj da = new XenStoreObj();
		// String name = (String) json.get("srName");
		// da.setName(name);
		// da.setStore_uuid((String) json.get("srUuid"));
		// DecimalFormat nf = new DecimalFormat("0");
		// String allsize = (String) json.get("srSize").toString();
		// double size = Double.parseDouble(allsize)
		// / (1024 * 1024 * 1024);
		// long forsize = Long.parseLong(nf.format(size));
		// da.setSr_size(nf.format(size));
		// String useSize = (String) json.get("srUtilisation")
		// .toString();
		// double use = Double.parseDouble(useSize)
		// / (1024 * 1024 * 1024);
		// long foruse = Long.parseLong(nf.format(use));
		// da.setFree_size(String.valueOf(foruse));
		// String use_per;
		// if (allsize.equals("0")) {
		// use_per = "0";
		// } else {
		// use_per = String.valueOf(nf.format(100 * foruse
		// / forsize));
		// }
		//
		// da.setUse_per(use_per);
		// da.setType((String) json.get("srType"));
		// da.setShared(String
		// .valueOf(json.get("srShared").toString()));
		// da.setState("2");
		// store.add(da);
		// }else{
		// match = false;
		// }
		// }
		// }
		theForm.setResultList(store);
		return "list_storage";
	}

	/**
	 * 列出所有主机的nic网络
	 * 
	 * @return
	 */
	@MethodLog(remark = "XenManAction-listNics", type = 4, message = "列出所有主机的nic网络")
	public String listNics() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String entityId = request.getParameter("entity_id");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		request.setAttribute("pool_uuid", pool_uuid);
		request.setAttribute("host_uuid", host_uuid);
		String param = "/xen/nics/get/[hostUuid:" + host_uuid + "].[poolUuid:" + pool_uuid + "]/";
		String result = InvokeUtil.invoke(param);

		// // 写操作日志
		// TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		// operObj.setREMARK("");
		// operObj.setOPERTYPE(4);
		// operObj.setMESSAGE("查询主机的nic网络");
		// int oper_result = 1;
		// if (request.getSession().getAttribute(Constant.FUNCID) != null) {
		// operObj.setFUNCID(Integer.parseInt(request.getSession()
		// .getAttribute(Constant.FUNCID).toString()));
		// }
		// operObj.setRESULT(oper_result);
		// @SuppressWarnings("unused")
		// int retOper = operationService.insertByObj(operObj);

		JSONArray jo = JSONArray.fromObject(result);
		List<JSONObject> nics = JSONArray.toList(jo, JSONObject.class);
		List<Nic> nics2 = new ArrayList<Nic>();
		if (nics.get(0).get("responseCode").equals("1")) {
			nics.remove(0);
			for (JSONObject json : nics) {
				Nic nic = new Nic();
				nic.setConnState(json.get("connState") == null ? false : json
						.getBoolean("connState"));
				nic.setDeviceName((String) json.get("deviceName"));
				nic.setDuplex(json.get("duplex") == null ? false : json.getBoolean("duplex"));
				nic.setMac((String) json.get("mac"));
				nic.setSpeed(json.get("speed") == null ? 0L : json.getLong("speed"));
				nic.setVendorName((String) json.get("vendorName"));
				nic.setNicName((String) json.get("nicName"));
				nic.setPciBusPath((String) json.get("pciBusPath"));
				nics2.add(nic);
			}

		}
		theForm.setResultList(nics2);
		return "list_nics";
	}

	/**
	 * 列出所有主机的nic网络
	 * 
	 * @return
	 */
	@MethodLog(remark = "XenManAction-listNetworks", type = 4, message = "列出所有主机的nic网络")
	public String listNetworks() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String entityId = request.getParameter("entity_id");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		request.setAttribute("pool_uuid", pool_uuid);
		request.setAttribute("host_uuid", host_uuid);
		String param = "/xen/networks/get/[hostUuid:" + host_uuid + "].[poolUuid:" + pool_uuid
				+ "]/";
		String result = InvokeUtil.invoke(param);

		// // 写操作日志
		// TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		// operObj.setREMARK("");
		// operObj.setOPERTYPE(4);
		// operObj.setMESSAGE("列出主机所有网络");
		// int oper_result = 1;
		// if (request.getSession().getAttribute(Constant.FUNCID) != null) {
		// operObj.setFUNCID(Integer.parseInt(request.getSession()
		// .getAttribute(Constant.FUNCID).toString()));
		// }
		// operObj.setRESULT(oper_result);
		// @SuppressWarnings("unused")
		// int retOper = operationService.insertByObj(operObj);

		JSONArray jo = JSONArray.fromObject(result);
		List<JSONObject> nets = JSONArray.toList(jo, JSONObject.class);
		List<Network> networks = new ArrayList<Network>();
		if (nets.get(0).get("responseCode").equals("1")) {
			nets.remove(0);
			for (JSONObject json : nets) {
				Network network = new Network();
				network.setConnState(json.get("connState") == null ? false : json
						.getBoolean("connState"));
				network.setMac(json.get("mac") == null ? "" : json.getString("mac"));
				network.setNicUuid(json.get("nicUuid") == null ? "" : json.getString("nicUuid"));
				network.setNicName(json.get("nicName") == null ? "" : json.getString("nicName"));
				network.setNetworkUuid(json.get("networkUuid") == null ? "" : json
						.getString("networkUuid"));
				network.setNetworkName(json.get("networkName") == null ? "" : json
						.getString("networkName"));
				network.setNetworkDesc(json.get("networkDesc") == null ? "" : json
						.getString("networkDesc"));
				network.setVlanNum(json.get("vlanNum") == null ? -1L : json.getLong("vlanNum"));
				network.setAutoMatic(json.get("autoMatic") == null ? false : json
						.getBoolean("autoMatic"));
				networks.add(network);
			}

		}
		theForm.setResultList(networks);

		String param1 = "/xen/nics/get/[hostUuid:" + host_uuid + "].[poolUuid:" + pool_uuid + "]/";
		String result1 = InvokeUtil.invoke(param1);

		// // 写操作日志
		// TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		// operObj.setREMARK("");
		// operObj.setOPERTYPE(4);
		// operObj.setMESSAGE("列出主机所有网络");
		// int oper_result = 1;
		// if (request.getSession().getAttribute(Constant.FUNCID) != null) {
		// operObj.setFUNCID(Integer.parseInt(request.getSession()
		// .getAttribute(Constant.FUNCID).toString()));
		// }
		// operObj.setRESULT(oper_result);
		// @SuppressWarnings("unused")
		// int retOper = operationService.insertByObj(operObj);

		JSONArray jo1 = JSONArray.fromObject(result1);
		List<JSONObject> nics = JSONArray.toList(jo1, JSONObject.class);
		List<Network> networks2 = new ArrayList<Network>();
		if (nics.get(0).get("responseCode").equals("1")) {
			nics.remove(0);
			for (JSONObject json : nics) {
				Network network = new Network();
				boolean management = json.get("management") == null ? false : json
						.getBoolean("management");
				if (management) {
					String nicUuid = (String) json.get("nicUuid");
					for (Network n : networks) {
						if (n.getNicUuid().equals(nicUuid)) {
							network.setNetworkName(n.getNetworkName());
							break;
						}
					}
					network.setIp((String) json.get("ip"));
					network.setDNS((String) json.get("DNS"));
					network.setGateway((String) json.get("gateway"));
					network.setNetmask((String) json.get("netmask"));
					network.setHostName((String) json.get("hostName"));
					networks2.add(network);
				}
			}

		}
		theForm.setHostResultList(networks2);
		return "list_networks";
	}

	/**
	 * 
	 * @Title: goConnectCluster
	 * @Description: 进入链接xen集群页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 17, 2012 10:34:41 AM
	 */
	public String goConnectCluster() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String cluster_uuid = request.getParameter("cluster_uuid");
		ConnectionInfo con = new ConnectionInfo();
		con.setPOOLUUID(cluster_uuid);
		con = xenManService.queryByObj(con);
		theForm.setIP(con.getADDRESS());
		theForm.setUSERNAME(con.getUSERNAME());
		theForm.setPASSWORD(con.getPASSWORD());
		request.setAttribute("cluster_uuid", cluster_uuid);
		return "connect_cluster";
	}

	/**
	 * 
	 * @Title: connectCluster
	 * @Description: 连接xen集群
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 17, 2012 10:45:11 AM
	 */
	public String connectCluster() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String cluster_uuid = request.getParameter("cluster_uuid");
		String ip = request.getParameter("ip");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String result = xenManService.connectCluster(ip, username, password, cluster_uuid);
		JSONObject json = JSONObject.fromObject(result);
		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(json);
		// out.close();
		PrintWriterOut.printWirter(response, json);
		return null;
	}

	/**
	 * 
	 * @Title: goMigrate
	 * @Description: 进入迁移虚拟机页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 22, 2012 11:19:00 AM
	 */
	public String goMigrate() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String cluster_id = request.getParameter("cluster_id");
		String nodeId = request.getParameter("nodeId");
		String id = request.getParameter("id");
		String vm_uuid = request.getParameter("vm_uuid");
		XenEntityTreeObj e = new XenEntityTreeObj();
		e.setParentId(Integer.parseInt(cluster_id));
		e.setId(Integer.parseInt(id));
		List<XenEntityTreeObj> list = new ArrayList<XenEntityTreeObj>();
		String param1 = "/xen/host/migrate/lookup/[poolUuid:" + pool_uuid + "].[vmUuid:" + vm_uuid
				+ "]/";
		String result1 = InvokeUtil.invoke(param1);

		JSONArray arrays = JSONArray.fromObject(result1);
		List<JSONObject> hostSList = JSONArray.toList(arrays, JSONObject.class);
		if (hostSList.get(0).get("responseCode").equals("1")) {
			hostSList.remove(0);
			if (hostSList.size() == 0) {
				request.setAttribute("flag", true);
				theForm.setResultList(list);
				return "migrate_page";
			}
			for (JSONObject json : hostSList) {
				XenEntityTreeObj xen = new XenEntityTreeObj();
				String hostName = (String) json.get("hostName");
				String hostUuid = (String) json.get("hostUuid");
				xen.setName(hostName);
				xen.setEntityId(hostUuid);
				list.add(xen);
			}
		}
		theForm.setResultList(list);
		request.setAttribute("pool_uuid", pool_uuid);
		request.setAttribute("host_uuid", host_uuid);
		request.setAttribute("id", id);
		request.setAttribute("vm_uuid", vm_uuid);
		request.setAttribute("flag", false);
		request.setAttribute("nodeId", nodeId);
		return "migrate_page";
	}

	/**
	 * 
	 * @Title: migrateVM
	 * @Description: 迁移虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws IOException
	 * @createtime Oct 22, 2012 4:26:41 PM
	 */
	@MethodLog(remark = "XenManAction-migrateVM", type = 3, message = "迁移虚拟机")
	public String migrateVM() throws IOException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String pool_uuid = request.getParameter("pool_uuid");
		String vm_uuid = request.getParameter("vm_uuid");
		String newHostUuid = request.getParameter("newHostEntityID");
		String param1 = "/xen/vm/migrate/[poolUuid:" + pool_uuid + "].[hostUuid:" + newHostUuid
				+ "].[vmUuid:" + vm_uuid + "]/";
		String result1 = InvokeUtil.invoke(param1);

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("迁移xen虚拟机");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		JSONObject j1 = JSONObject.fromObject(result1);
		if (j1.get("responseCode").equals("1")) {
			// 迁移成功 ，改变数据库中虚拟机的父节点
			XenEntityTreeObj obj = new XenEntityTreeObj();
			obj.setUuid(newHostUuid);
			obj = xenEntityTreeService.queryTreeNode(obj);
			if (obj != null) {
				XenEntityTreeObj treeObj = new XenEntityTreeObj();
				treeObj.setUuid(vm_uuid);
				treeObj.setParentId(obj.getId());
				xenEntityTreeService.updateNodeParentId(treeObj);
			}
			// 迁移成功后，改变数据库中虚拟机和主机关联的eq_id
			TbCloud2HostInfoObj hostInfoObj = new TbCloud2HostInfoObj();
			hostInfoObj.setH_uuid(newHostUuid);
			hostInfoObj = hostInfoService.queryByObj(hostInfoObj);
			if (hostInfoObj != null && hostInfoObj.getEq_id() != null) {
				VMHostObj vmObj = new VMHostObj();
				vmObj.setVH_UUID(vm_uuid);
				vmObj.setEQ_ID(hostInfoObj.getEq_id());
				vmHostService.updateVMHostEqId(vmObj);
			}
		}
		PrintWriter out = Struts2Utils.getResponse().getWriter();
		out.print(j1);
		out.close();
		return null;

	}

	/**
	 * 
	 * @Title: goMigrate
	 * @Description: 进入迁移虚拟机页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 22, 2012 11:19:00 AM
	 */
	public String goMove() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String cluster_id = request.getParameter("cluster_id");
		String nodeId = request.getParameter("nodeId");
		String id = request.getParameter("id");
		String vm_uuid = request.getParameter("vm_uuid");
		XenEntityTreeObj e = new XenEntityTreeObj();
		e.setParentId(Integer.parseInt(cluster_id));
		e.setId(Integer.parseInt(id));
		List<XenEntityTreeObj> list = new ArrayList<XenEntityTreeObj>();
		String param1 = "/xen/host/migrate/lookup/[poolUuid:" + pool_uuid + "].[vmUuid:" + vm_uuid
				+ "]/";
		String result1 = InvokeUtil.invoke(param1);
		JSONArray arrays = JSONArray.fromObject(result1);
		List<JSONObject> hostSList = JSONArray.toList(arrays, JSONObject.class);
		if (hostSList.get(0).get("responseCode").equals("1")) {
			hostSList.remove(0);
			if (hostSList.size() == 0) {
				request.setAttribute("flag", true);
				theForm.setResultList(list);
				return "migrate_page";
			}
			for (JSONObject json : hostSList) {
				XenEntityTreeObj xen = new XenEntityTreeObj();
				String hostName = (String) json.get("hostName");
				String hostUuid = (String) json.get("hostUuid");
				xen.setName(hostName);
				xen.setEntityId(hostUuid);
				list.add(xen);
			}
		}
		theForm.setResultList(list);
		request.setAttribute("pool_uuid", pool_uuid);
		request.setAttribute("host_uuid", host_uuid);
		request.setAttribute("id", id);
		request.setAttribute("vm_uuid", vm_uuid);
		request.setAttribute("flag", false);
		request.setAttribute("nodeId", nodeId);
		return "move_page";
	}

	/**
	 * 
	 * @Title: migrateVM
	 * @Description: 迁移虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws IOException
	 * @createtime Oct 22, 2012 4:26:41 PM
	 */
	@MethodLog(remark = "XenManAction-moveVM", type = 3, message = "迁移虚拟机")
	public String moveVM() throws IOException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String pool_uuid = request.getParameter("pool_uuid");
		String vm_uuid = request.getParameter("vm_uuid");
		String newHostUuid = request.getParameter("newHostEntityID");
		String param1 = "/xen/vm/move/[poolUuid:" + pool_uuid + "].[hostUuid:" + newHostUuid
				+ "].[vmUuid:" + vm_uuid + "]/";
		String result1 = InvokeUtil.invoke(param1);

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("迁移xen虚拟机");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		JSONObject j1 = JSONObject.fromObject(result1);
		if (j1.get("responseCode").equals("1")) {
			// 迁移成功 ，改变数据库中虚拟机的父节点
			XenEntityTreeObj obj = new XenEntityTreeObj();
			obj.setUuid(newHostUuid);
			obj = xenEntityTreeService.queryTreeNode(obj);
			if (obj != null) {
				XenEntityTreeObj treeObj = new XenEntityTreeObj();
				treeObj.setUuid(vm_uuid);
				treeObj.setParentId(obj.getId());
				xenEntityTreeService.updateNodeParentId(treeObj);
			}
		}
		PrintWriter out = Struts2Utils.getResponse().getWriter();
		out.print(j1);
		out.close();
		return null;

	}

	/**
	 * 
	 * @Title: addNFSPage
	 * @Description: 进入添加NFS存储页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 25, 2012 2:40:18 PM
	 */
	public String addNFSPage() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String host_id = request.getParameter("host_id");
		String FUNCSID = request.getParameter("FUNCSID");
		String type = request.getParameter("type");
		String nodeId = request.getParameter("nodeId");
		request.getSession().setAttribute(Constant.FUNCID, FUNCSID);
		// TbSysUserinfoObj userObj = (TbSysUserinfoObj)
		// request.getSession().getAttribute(
		// Constant.USER_SESSION_KEY);
		request.setAttribute("pool_uuid", pool_uuid);
		request.setAttribute("host_uuid", host_uuid);
		XenEntityTreeObj obj = new XenEntityTreeObj();
		obj.setType(TypeConstant.XEN_HOST);
		obj.setUserId(session.get("id").toString());
		List hostList = xenEntityTreeService.queryForTree(obj);
		theForm.setResultList(hostList);
		request.setAttribute("host_id", host_id);
		request.setAttribute("type", type);
		request.setAttribute("nodeId", nodeId);
		return "nfs_page";

	}

	/**
	 * 
	 * @Title: getSRList
	 * @Description: 获取SR列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 25, 2012 4:32:53 PM
	 */
	@MethodLog(remark = "XenManAction-getSRList", type = 4, message = "获取SR列表")
	public String getSRList() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String path = request.getParameter("path");
		// path = URLEncoder.encode(path, "utf-8");
		String change_path = path.replace('/', '~');
		List<XenStoreObj> list = xenManService.getSRList(pool_uuid, change_path.replace(':', '!'));
		StringBuffer buff = new StringBuffer("{");
		JSONArray array = new JSONArray();
		for (XenStoreObj obj : list) {
			JSONObject o = new JSONObject();
			o.put(obj.getStore_uuid(), obj.getName());
			array.add(o);
		}
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setCharacterEncoding("UTF-8");
		// PrintWriter o = response.getWriter();
		// o.print(array.toString());
		// o.close();
		PrintWriterOut.printWirter(response, array.toString());
		return null;
	}

	/**
	 * 
	 * @Title: createSR
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 26, 2012 2:16:48 PM
	 */
	@MethodLog(remark = "XenManAction-createSR", type = 1, message = "createSR")
	public String createSR() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String path = request.getParameter("path");
		String name = "";
		String desc = "";
		try {
			name = URLDecoder.decode(request.getParameter("name"), "UTF-8");
			desc = URLDecoder.decode(request.getParameter("desc"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String host_id = request.getParameter("host_id");
		String storageId = request.getParameter("storageId");
		String createStyle = request.getParameter("createStyle");
		String share = request.getParameter("share");
		String store_uuid = request.getParameter("store_uuid");
		String bind_uuid = request.getParameter("bind_uuid");
		String iso_type = request.getParameter("iso_type");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String result = xenManService.createSR(pool_uuid, host_uuid, path, name, desc, host_id,
				storageId, createStyle, Boolean.parseBoolean(share), store_uuid, bind_uuid,
				iso_type, username, password);

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(1);
		operObj.setMESSAGE("创建xen SR");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		JSONObject js = JSONObject.fromObject(result);
		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(js.toString());
		// out.close();
		PrintWriterOut.printWirter(response, js.toString());
		return null;
	}

	/**
	 * 
	 * @Title: operateStore
	 * @Description: 对共享存储进行操作
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 30, 2012 11:47:27 AM
	 */
	@MethodLog(remark = "XenManAction-operateStore", type = 3, message = "对共享存储进行操作")
	public String operateStore() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String sr_uuid = request.getParameter("sr_uuid");
		String oper = request.getParameter("oper");
		String name = request.getParameter("name");
		try {
			name = URLDecoder.decode(name, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String result = xenManService.operateStore(pool_uuid, host_uuid, sr_uuid, oper, name);

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(1);
		operObj.setMESSAGE("操作xen共享存储");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(result);
		// out.close();
		PrintWriterOut.printWirter(response, result);
		return null;
	}

	/**
	 * 
	 * @Title: delTem
	 * @Description: 删除模板
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Nov 2, 2012 10:52:52 AM
	 */
	@MethodLog(remark = "XenManAction-delTem", type = 2, message = "删除模板")
	public String delTem() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String id = request.getParameter("id");
		String entity_id = request.getParameter("entityId");
		VMHostObj v = new VMHostObj();
		v.setID(Integer.parseInt(entity_id));
		v = vmHostService.queryByObj(v);
		String vm_uuid = v.getVH_UUID();
		if (pool_uuid == null || "".equals(pool_uuid)) {
			pool_uuid = host_uuid;
			host_uuid = "";
		}
		String result = xenManService.delTem(pool_uuid, host_uuid, vm_uuid, id, entity_id);

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除xen模板");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(result);
		// out.close();
		PrintWriterOut.printWirter(response, result);
		return null;
	}

	/**
	 * 
	 * @Title: deleteXenDataCenter
	 * @Description: 删除xen数据中心
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws IOException
	 * @createtime Nov 14, 2012 8:59:28 AM
	 */
	@MethodLog(remark = "XenManAction-deleteXenDataCenter", type = 2, message = "删除xen数据中心")
	public String deleteXenDataCenter() throws IOException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("id");
		XenEntityTreeObj xenentityObj = new XenEntityTreeObj();
		xenentityObj.setParentId(Integer.parseInt(id));
		List reList = xenEntityTreeService.queryForTree(xenentityObj);
		String result = null;
		if (reList.size() != 0) {
			result = "{\"result\":\"2\"}";
		} else {
			XenEntityTreeObj e = new XenEntityTreeObj();
			e.setId(Integer.parseInt(id));
			int ret = xenEntityTreeService.delTreeNode(e);
			if (ret != -1) {
				result = "{\"result\":\"1\"}";
			} else {
				result = "{\"result\":\"-1\"}";
			}
		}

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除xen数据中心");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		Struts2Utils.getResponse().setContentType("text/html;charset=UTF-8");
		JSONObject jo = JSONObject.fromObject(result);
		PrintWriter out = Struts2Utils.getResponse().getWriter();
		out.print(jo.toString());
		out.close();
		return null;

	}

	/**
	 * 
	 * @Title: delCluster
	 * @Description: 删除xen集群
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Nov 14, 2012 9:53:47 AM
	 */
	@MethodLog(remark = "XenManAction-delCluster", type = 2, message = "删除xen集群")
	public String delCluster() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("id");
		String entity_id = request.getParameter("entityId");
		String pool_uuid = request.getParameter("pool_uuid");
		XenEntityTreeObj e = new XenEntityTreeObj();
		e.setParentId(Integer.parseInt(id));
		List list = xenEntityTreeService.queryForTree(e);
		String result = "";
		if (list != null && list.size() != 0) {
			result = "{result:-1}";
		} else {
			XenEntityTreeObj en = new XenEntityTreeObj();
			en.setId(Integer.parseInt(id));
			int ret = xenEntityTreeService.delTreeNode(en);
			if (ret != -1) {
				result = "{result:1}";
			}
		}

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除xen集群");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(result);
		// out.close();
		PrintWriterOut.printWirter(response, result);
		return null;
	}

	/**
	 * 
	 * @Title: ClusterVM
	 * @Description: 数据中心层次虚拟机的信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Nov 15, 2012 1:49:13 PM
	 */
	@MethodLog(remark = "XenManAction-clusterVM", type = 4, message = "数据中心层次虚拟机的信息")
	public String clusterVM() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String clusterId = request.getParameter("id");
		String pool_uuid = request.getParameter("pool_uuid");
		String FUNCSID = request.getParameter("FUNCSID");
		request.getSession().setAttribute(Constant.FUNCID, FUNCSID);
		// TbSysUserinfoObj userObj = (TbSysUserinfoObj)
		// request.getSession().getAttribute(
		// Constant.USER_SESSION_KEY);
		// String type = request.getParameter("type");
		// String host_uuid = request.getParameter("host_uuid");
		XenEntityConObj e = new XenEntityConObj();
		e.setClusterId(Integer.parseInt(clusterId));
		e.setType(27);// xen虚拟机
		e.setUserId(session.get("id").toString());
		List<XenEntityConObj> list = xenEntityTreeService.queryEntityInfo(e);
		String[] vmUuids = new String[list.size()];
		Map<String, String> maps = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {
			XenEntityConObj eo = list.get(i);
			VMHostObj v = new VMHostObj();
			v.setID(Integer.parseInt(eo.getEntityId()));
			v = vmHostService.queryByObj(v);
			vmUuids[i] = v.getVH_UUID();
			maps.put(vmUuids[i], v.getVH_NAME());
		}
		/**
		 * ----------------------------------------------------------
		 * 接口提供获取池下所与虚拟机性能信息的方法 方法有误待开发，暂时未使用
		 */
		String result = xenManService.listAbility(pool_uuid, pool_uuid, vmUuids);
		JSONArray jo = JSONArray.fromObject(result);
		List<JSONObject> ability = JSONArray.toList(jo, JSONObject.class);
		if (ability.get(0).get("responseCode").equals("1")) {
			ability.remove(0);
			List<VMHostObj> vmList = new ArrayList<VMHostObj>();
			for (JSONObject js : ability) {

				VMHostObj vo = new VMHostObj();
				vo.setVH_NAME(maps.get(js.get("vmUuid")));
				boolean isOpen = (Boolean) js.get("isExistPer");
				if (isOpen == true) {
					double cpuUsage = (Double) js.get("cpuUtilization");
					double memTotal = Double.parseDouble(js.getString("memory")) * 1.0
							/ (1024 * 1024);
					double memFree = Double.parseDouble(js

					.getString("memoryInternalFree")) * 1.0 / (1024 * 1024);
					DecimalFormat nf = new DecimalFormat("0");
					vo.setVH_CPU(String.valueOf(nf.format(cpuUsage)));

					vo.setVH_MEM(String.valueOf(nf.format(memTotal)));
					vo.setVH_MIN_MEM(String.valueOf(nf.format(memTotal - memFree)));
					vo.setVH_STATUS("1");
					vo.setVH_MIN_CPU(String.valueOf(js.get("numCPUs")));
				}
				vmList.add(vo);
			}
			theForm.setResultList(vmList);
		}

		return "clusterVM";

	}

	/**
	 * 
	 * @Title: hostVM
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Nov 16, 2012 10:14:53 AM
	 */
	public String hostVM() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String hostId = request.getParameter("id");
		String pool_uuid = request.getParameter("pool_uuid");
		// String type = request.getParameter("type");
		String host_uuid = request.getParameter("host_uuid");
		String FUNCSID = request.getParameter("FUNCSID");
		request.getSession().setAttribute(Constant.FUNCID, FUNCSID);
		// TbSysUserinfoObj userObj = (TbSysUserinfoObj)
		// request.getSession().getAttribute(
		// Constant.USER_SESSION_KEY);
		XenEntityTreeObj e = new XenEntityTreeObj();
		e.setParentId(Integer.parseInt(hostId));
		e.setType("27");// xen虚拟机
		e.setUserId(session.get("id").toString());
		List<XenEntityTreeObj> list = xenEntityTreeService.queryForTree(e);
		String[] vmUuids = new String[list.size()];
		Map<String, String> maps = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {
			XenEntityTreeObj eo = list.get(i);
			vmUuids[i] = eo.getUuid();
			maps.put(vmUuids[i], eo.getName());
		}
		/**
		 * --------------------------------------------------------------------
		 * ---待修改 获取主机性能信息 如果接口修改获取主机的时候把虚拟机的性能信息也获取到，会更好的提高性能
		 */
		String hostName = request.getParameter("name");
		String param = "/xen/host/monitoring/performance/[poolUuid:" + pool_uuid + "].[hostUuid:"
				+ host_uuid + "]/";
		String result1 = InvokeUtil.invoke(param);
		JSONObject js1 = JSONObject.fromObject(result1);
		if ("1".equals(js1.get("responseCode").toString())) {
			DecimalFormat nf = new DecimalFormat("0");
			List<TbCloud2HostInfoObj> hostList = new ArrayList<TbCloud2HostInfoObj>();
			TbCloud2HostInfoObj t = new TbCloud2HostInfoObj();
			double cpuUtilization = Double.parseDouble(js1.get("cpuUtilization").toString());
			t.setCpu_fq(String.valueOf(nf.format(cpuUtilization)));
			t.setCpu_cl(js1.getString("numCPUs"));
			double memTotal = Double.parseDouble(js1.get("totalMemoryKBs").toString()) / 1024;
			t.setMem(String.valueOf(nf.format(memTotal)));
			double memFree = Double.parseDouble(js1.get("freeMemoryKBs").toString()) / 1024;
			t.setMemUsage(String.valueOf(nf.format(memTotal - memFree)));
			t.setEq_name(hostName);
			hostList.add(t);
			theForm.setHostResultList(hostList);

			/**
			 * 获取虚拟机性能信息
			 */
			String result = xenManService.listAbility(pool_uuid, host_uuid, vmUuids);
			JSONArray jo = JSONArray.fromObject(result);
			List<JSONObject> ability = JSONArray.toList(jo, JSONObject.class);
			if (ability.get(0).get("responseCode").equals("1")) {
				ability.remove(0);
				List<VMHostObj> vmList = new ArrayList<VMHostObj>();
				for (JSONObject js : ability) {

					VMHostObj vo = new VMHostObj();
					vo.setVH_NAME(maps.get(js.get("vmUuid")));
					boolean isOpen = (Boolean) js.get("isExistPer");
					if (isOpen == true) {
						double cpuUsage = Double.parseDouble(js.get("cpuUtilization").toString());
						memTotal = Double.parseDouble(js.getString("memory")) * 1.0 / (1024 * 1024);
						memFree = Double.parseDouble(js.getString("memoryInternalFree")) * 1.0
								/ (1024 * 1024);
						DecimalFormat nf1 = new DecimalFormat("0");
						vo.setVH_CPU(String.valueOf(nf1.format(cpuUsage)));
						vo.setVH_MEM(String.valueOf(nf1.format(memTotal)));
						vo.setVH_MIN_MEM(String.valueOf(nf1.format(memTotal - memFree)));
						vo.setVH_STATUS("1");
						vo.setVH_MIN_CPU(String.valueOf(js.get("numCPUs")));
					}
					vmList.add(vo);
				}
				theForm.setResultList(vmList);
			}

		}
		return "clusterVM";
	}

	/**
	 * 
	 * @Title: hostAbility
	 * @Description: 主机性能信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Nov 16, 2012 3:09:55 PM
	 */
	@MethodLog(remark = "XenManAction-hostAbility", type = 4, message = "主机性能信息")
	public String hostAbility() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String name = request.getParameter("name");
		String param = "/xen/host/monitoring/performance/[poolUuid:" + pool_uuid + "].[hostUuid:"
				+ host_uuid + "]/";
		String result = InvokeUtil.invoke(param);
		JSONObject js = JSONObject.fromObject(result);
		if ("1".equals(js.get("responseCode").toString())) {
			DecimalFormat nf = new DecimalFormat("0");
			List<TbCloud2HostInfoObj> list = new ArrayList<TbCloud2HostInfoObj>();
			TbCloud2HostInfoObj t = new TbCloud2HostInfoObj();
			double cpuUtilization = Double.parseDouble(js.get("cpuUtilization").toString());
			t.setCpu_fq(String.valueOf(nf.format(cpuUtilization)));
			t.setCpu_cl(js.getString("numCPUs"));
			double memTotal = Double.parseDouble(js.get("totalMemoryKBs").toString()) / 1024;
			t.setMem(String.valueOf(nf.format(memTotal)));
			double memFree = Double.parseDouble(js.get("freeMemoryKBs").toString()) / 1024;
			t.setMemUsage(String.valueOf(nf.format(memTotal - memFree)));
			t.setEq_name(name);
			list.add(t);
			theForm.setResultList(list);
		}
		return "host_ability";

	}

	/**
	 * 
	 * @Title: getVmState
	 * @Description: 获取虚拟机的电源状态
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Feb 22, 2013 3:39:00 PM
	 */
	@MethodLog(remark = "XenManAction-getVmState", type = 4, message = "获取虚拟机的电源状态")
	public String getVmState() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String vm_uuid = request.getParameter("vm_uuid");
		String pool_uuid = request.getParameter("pool_uuid");
		String param = "/xen/vms/uuids/lookup/[poolUuid:" + pool_uuid + "].[vmUuids:" + vm_uuid
				+ "]/";
		String result = InvokeUtil.invoke(param);
		JSONArray jo = JSONArray.fromObject(result);
		List<JSONObject> list = JSONArray.toList(jo, JSONObject.class);
		if (list.get(0).getString("responseCode").equals("1")) {
			JSONObject json = list.get(1);
			String powerState = json.getString("powerState");
			HttpServletResponse response = Struts2Utils.getResponse();
			String state = "{\"state\":\"" + powerState + "\"}";
			// PrintWriter out = response.getWriter();
			// out.println(state);
			// out.close();
			PrintWriterOut.printWirter(response, state);
		}
		return null;

	}

	/**
	 * 
	 * @Title: getVidForStore
	 * @Description: 获取某个存储的Vdi
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Feb 25, 2013 5:13:34 PM
	 */
	@MethodLog(remark = "XenManAction-getVidForStore", type = 4, message = "获取某个存储的Vdi")
	public String getVidForStore() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String srUuid = request.getParameter("srUuid");
		String pool_uuid = request.getParameter("pool_uuid");
		String param = "/xen/vdi/sruuid/get/[poolUuid:" + pool_uuid + "].[srUuid:" + srUuid + "]/";
		String result = InvokeUtil.invoke(param);
		JSONArray jo = JSONArray.fromObject(result);
		List<JSONObject> vdiSList = JSONArray.toList(jo, JSONObject.class);
		JSONArray reJo = new JSONArray();
		if (vdiSList.get(0).get("responseCode").equals("1")) {
			vdiSList.remove(0);
			for (int j = 0; j < vdiSList.size(); j++) {
				JSONObject json = vdiSList.get(j);
				String vdiName = json.getString("vdiName");
				if (vdiName.length() > 3) {
					vdiName = vdiName.substring(vdiName.length() - 3);
				}
				if (vdiName.equals("iso")) {
					JSONObject addjs = new JSONObject();
					addjs.put(json.getString("vdiUuid"), json.getString("vdiName"));
					reJo.add(addjs);
				}
			}
		}
		// PrintWriter out = response.getWriter();
		// out.println(reJo.toString());
		// out.close();
		PrintWriterOut.printWirter(response, reJo.toString());
		return null;
	}

	/**
	 * 
	 * @Title: listStoreInfo
	 * @Description: 展示虚拟机详细信息
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 15, 2013 5:09:48 PM
	 */
	@MethodLog(remark = "XenManAction-listStoreInfo", type = 4, message = "展示虚拟机详细信息")
	public String listStoreInfo() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String entityId = request.getParameter("entity_id");
		XenStoreObj storeObj = new XenStoreObj();
		storeObj.setStore_uuid(entityId);
		List storeLst = xenStoreService.queryForListByObj(storeObj);
		DecimalFormat df = new DecimalFormat("0");
		if (storeLst != null && storeLst.size() > 0) {
			storeObj = (XenStoreObj) storeLst.get(0);
			Long srSize = 0L;
			Long srFreeSize = 0L;
			if (storeObj.getSr_size() != null) {
				srSize = Long.parseLong(storeObj.getSr_size()) / 1024 / 1024;
			}
			if (storeObj.getFree_size() != null) {
				srFreeSize = Long.parseLong(storeObj.getFree_size()) / 1024 / 1024;
			}
			theForm.setNAME(storeObj.getName());
			theForm.setDataSize(srSize + "");
			if (srSize > 0 && srFreeSize > 0) {
				theForm.setUSE_SPACE((srSize - srFreeSize) + "");
			} else {
				theForm.setUSE_SPACE("0");
			}
			theForm.setShare(storeObj.getShared());
			theForm.setSrUsePer(storeObj.getUse_per());
			theForm.setSrState(storeObj.getState());
			theForm.setSrType(storeObj.getType());
		}
		return "listStoreInfo";
	}

	/**
	 * 
	 * @Title: listStoreVdi
	 * @Description: 展示存储下的Vdi
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 18, 2013 1:49:40 PM
	 */
	@MethodLog(remark = "XenManAction-listStoreVdi", type = 4, message = "展示存储下的Vdi")
	public String listStoreVdi() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String entityId = request.getParameter("entity_id");
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		if (pool_uuid == null || "".equals(pool_uuid)) {
			pool_uuid = host_uuid;
		}

		String xen_url = "/xen/vdi/sruuid/get/[poolUuid:" + pool_uuid + "].[srUuid:" + entityId
				+ "]/";
		String xen_result = InvokeUtil.invoke(xen_url);
		JSONArray jo = JSONArray.fromObject(xen_result);
		List<JSONObject> vidList = JSONArray.toList(jo, JSONObject.class);
		List<XenStoreObj> lst = new ArrayList<XenStoreObj>();
		if (vidList.get(0).get("responseCode").equals("1")) {
			vidList.remove(0);
			for (int i = 0; i < vidList.size(); i++) {
				XenStoreObj storeObj = new XenStoreObj();
				JSONObject json = vidList.get(i);
				storeObj.setName(json.getString("vdiName"));
				storeObj.setSr_desc(json.getString("vdiDesc"));
				long srSize = 0L;
				if (json.get("vdiSize") != null && !json.getString("vdiSize").equals("")) {
					srSize = Long.parseLong(json.getString("vdiSize")) / 1024 / 1024 / 1024;
				}
				storeObj.setSr_size(srSize + "");
				storeObj.setVm_name(json.getString("vdiRelyMsg"));
				lst.add(storeObj);
			}
		}
		theForm.setResultList(lst);
		return "listStoreVdi";
	}

	@MethodLog(remark = "XenManAction-getIpList", type = 4, message = "getIpList")
	public String getIpList() {
		String net_id = Struts2Utils.getRequest().getParameter("net_id");
		TbCloud2IpInfoObj ipObj = new TbCloud2IpInfoObj();
		ipObj.setNET_ID(net_id);
		ipObj.setISUSED("0");
		List<TbCloud2IpInfoObj> ipLst = ipService.queryForListByIPObj(ipObj);
		Map ipmap = new HashMap<String, String>();
		if (ipLst != null && ipLst.size() > 0) {
			for (TbCloud2IpInfoObj o : ipLst) {
				ipmap.put(o.getIP_ID(), o.getIPADDRESS());
			}
		}
		JSONObject jo = JSONObject.fromObject(ipmap);
		try {
			PrintWriter w = Struts2Utils.getResponse().getWriter();
			w.println(jo);
			w.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: reConnectSrPage
	 * @Description: 跳转到SR重新连接页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 7, 2013 11:26:12 AM
	 */
	public String reConnectSrPage() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		if (pool_uuid == null || pool_uuid.equals("")) {
			pool_uuid = host_uuid;
		}
		String sr_uuid = request.getParameter("sr_uuid");
		// 查询存储所属主机
		XenStoreObj storeobj = new XenStoreObj();
		storeobj.setStore_uuid(sr_uuid);
		storeobj = xenStoreService.queryByObj(storeobj);
		if (storeobj != null && storeobj.getDependent_host_uuid() != null) {
			host_uuid = storeobj.getDependent_host_uuid();
		}

		String type = "";
		String srShared = "";
		String xen_url = "/xen/sr/smConfig/get/[poolUuid:" + pool_uuid + "].[srUuid:" + sr_uuid
				+ "]/";
		String xen_result = InvokeUtil.invoke(xen_url);
		JSONObject jo = JSONObject.fromObject(xen_result);
		if (jo.getString("responseCode").equals("1")) {
			type = jo.getString("smConfig");
			String name = jo.getString("srName");
			srShared = jo.getString("srShared");
			theForm.setNAME(name);
		}
		request.setAttribute("type", type);
		request.setAttribute("pool_uuid", pool_uuid);
		request.setAttribute("host_uuid", host_uuid);
		request.setAttribute("sr_uuid", sr_uuid);
		request.setAttribute("srShared", srShared);
		return "reConnPage";
	}

	/**
	 * 
	 * @Title: reConnSr
	 * @Description: 重新连接xen存储
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 7, 2013 6:50:03 PM
	 */
	public String reConnSr() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String name = "";
		try {
			name = URLDecoder.decode(request.getParameter("name"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String path = request.getParameter("path");
		String share = request.getParameter("share");
		String store_uuid = request.getParameter("store_uuid");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String change_path = path.replace('/', '~');
		String last_path = change_path.replace(':', '!');
		last_path = last_path.replace('\\', '~');
		String param = "";
		if (Boolean.parseBoolean(share)) {
			param = "/xen/nfs/mount/[poolUuid:" + pool_uuid + "].[serverPath:" + last_path
					+ "].[srName:" + name + "].[srUuid:" + store_uuid + "].[shared:" + share + "]/";
		} else {
			param = "/xen/nfs/mount/[poolUuid:" + pool_uuid + "].[hostUuid:" + host_uuid
					+ "].[serverPath:" + last_path + "].[srName:" + name + "].[srUuid:"
					+ store_uuid + "].[shared:" + share + "]/";
		}
		String result = InvokeUtil.invoke(param);
		JSONObject js = JSONObject.fromObject(result);
		if (js.getString("responseCode").equals("1")) {
			// 更新xen_storage_info
			XenStoreObj d = new XenStoreObj();
			d.setState("connection");
			d.setStore_uuid(store_uuid);
			d.setName(name);
			int ret = xenStoreService.updateByObj(d);
			// 更新xen_entity_tree
			XenEntityTreeObj treeObj = new XenEntityTreeObj();
			treeObj.setName(name);
			treeObj.setUuid(store_uuid);
			xenEntityTreeService.updateTreeNodeName(treeObj);
			// 更新xen_entity_con
			XenEntityTreeObj queryObj = new XenEntityTreeObj();
			queryObj.setUuid(store_uuid);
			queryObj = xenEntityTreeService.queryTreeNode(queryObj);
			XenEntityConObj conObj = new XenEntityConObj();
			conObj.setEntityId(queryObj.getEntityId());
			conObj.setName(name);
			conObj.setType(Integer.parseInt(TypeConstant.XEN_HOST_STORAGE));
			xenEntityTreeService.updateConObj(conObj);
			js.put("name", name);
		}
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			// response.setContentType("type/html;charset=UTF-8");
			// PrintWriter w = response.getWriter();
			// w.println(js);
			// w.close();
			PrintWriterOut.printWirter(response, js);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: fixStore
	 * @Description: 修复存储
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 9, 2013 5:39:04 PM
	 */
	@MethodLog(remark = "XenManAction-fixStore", type = 3, message = "修复存储")
	public String fixStore() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String sr_uuid = request.getParameter("sr_uuid");
		String name = "";
		Boolean shared = true;
		if (pool_uuid == null || "".equals(pool_uuid)) {
			pool_uuid = host_uuid;
		}
		XenStoreObj stoObj = new XenStoreObj();
		stoObj.setStore_uuid(sr_uuid);
		stoObj = xenStoreService.queryByObj(stoObj);
		if (stoObj != null && stoObj.getShared() != null) {
			shared = Boolean.parseBoolean(stoObj.getShared());
			host_uuid = stoObj.getDependent_host_uuid();
			name = stoObj.getName();
		}
		String param = "";
		if (shared) {
			param = "/xen/sr/fix/[poolUuid:" + pool_uuid + "].[srUuid:" + sr_uuid + "].[shared:"
					+ shared + "]/";
		} else {
			param = "/xen/sr/fix/[poolUuid:" + pool_uuid + "].[hostUuid:" + host_uuid
					+ "].[srUuid:" + sr_uuid + "].[shared:" + shared + "]/";
		}
		String result = InvokeUtil.invoke(param);

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(3);
		operObj.setMESSAGE("修复xen存储");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		JSONObject js = JSONObject.fromObject(result);
		if (js.getString("responseCode").equals("1")) {
			XenStoreObj d = new XenStoreObj();
			d.setState("connection");
			d.setStore_uuid(sr_uuid);
			d.setName(name);
			int ret = xenStoreService.updateByObj(d);
		}
		try {
			PrintWriter w = Struts2Utils.getResponse().getWriter();
			w.println(js);
			w.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 列出所有主机的nic网络
	 * 
	 * @return
	 */
	@MethodLog(remark = "XenManAction-listVmNetworks", type = 4, message = "列出所有主机的nic网络")
	public String listVmNetworks() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String pool_uuid = request.getParameter("pool_uuid");
		String entityId = request.getParameter("entity_id");
		String host_uuid = request.getParameter("host_uuid");
		VMHostObj v = new VMHostObj();
		v.setID(Integer.parseInt(entityId));
		v = vmHostService.queryByObj(v);
		String vm_uuid = v.getVH_UUID();
		String param = "/xen/vif/info/get/[vmUuid:" + vm_uuid + "].[poolUuid:" + pool_uuid + "]/";
		String result = InvokeUtil.invoke(param);
		JSONArray jo = JSONArray.fromObject(result);
		List<JSONObject> nets = JSONArray.toList(jo, JSONObject.class);
		List<Vif> vifs = new ArrayList<Vif>();
		if (nets.get(0).get("responseCode").equals("1")) {
			nets.remove(0);
			for (JSONObject json : nets) {
				Vif vif = new Vif();
				vif.setCurrentlyAttached(json.get("currentlyAttached") == null ? false : json
						.getBoolean("currentlyAttached"));
				vif.setMac((String) json.get("mac"));
				vif.setIp((String) json.get("ip"));
				vif.setDevice((String) json.get("vifDevice"));
				vif.setNetworkName((String) json.get("networkName"));
				vif.setNetworkUuid((String) json.get("networkUuid"));
				vif.setVifUuid((String) json.get("vifUuid"));
				vifs.add(vif);
			}

		}
		for (int i = 0; i < vifs.size(); i++) {
			for (int j = i + 1; j < vifs.size(); j++) {
				int device1 = Integer.parseInt(vifs.get(i).getDevice());
				int device2 = Integer.parseInt(vifs.get(j).getDevice());
				if (device2 < device1) {
					Vif temp = vifs.get(i);
					vifs.set(i, vifs.get(j));
					vifs.set(j, temp);
				}
			}
		}
		theForm.setResultList(vifs);
		request.setAttribute("vmUuid", vm_uuid);
		request.setAttribute("pool_uuid", pool_uuid);
		request.setAttribute("host_uuid", host_uuid);
		return "list_vm_networks";
	}

	/**
	 * 
	 * @Title: goAddInterfacePage
	 * @Description: 跳转到xen虚拟机添加网络接口的界面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 27, 2013 1:39:18 PM
	 */
	public String goAddInterfacePage() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String vm_uuid = request.getParameter("vm_uuid");
		String xen_url = "/xen/networks/get/[poolUuid:" + pool_uuid + "].[hostUuid:" + host_uuid
				+ "]/";
		String xen_result = InvokeUtil.invoke(xen_url);
		JSONArray jo = JSONArray.fromObject(xen_result);
		List<JSONObject> networkLst = JSONArray.toList(jo, JSONObject.class);
		List<Network> lst = new ArrayList<Network>();
		if (networkLst.get(0).get("responseCode").equals("1")) {
			networkLst.remove(0);
			for (JSONObject joObj : networkLst) {
				Network network = new Network();
				network.setNetworkUuid(joObj.get("networkUuid") == null ? "" : joObj
						.getString("networkUuid"));
				network.setNetworkName(joObj.get("networkName") == null ? "" : joObj
						.getString("networkName"));
				lst.add(network);
			}
		}
		if (networkLst.get(0).get("networkUuid") != null) {
			theForm.setNetworkUuid(networkLst.get(0).getString("networkUuid"));
		}
		request.setAttribute("pool_uuid", pool_uuid);
		request.setAttribute("host_uuid", host_uuid);
		request.setAttribute("vm_uuid", vm_uuid);
		int flag = 0;
		theForm.setFlag(flag);
		theForm.setResultList(lst);
		theForm.setMac("aa:bb:cc:dd:ee:ff");
		return "goAddInterfacePage";
	}

	/**
	 * 
	 * @Title: addXenVirtualNic
	 * @Description: xen虚拟机添加网络接口
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 27, 2013 1:39:47 PM
	 */
	@MethodLog(remark = "XenManAction-addXenVirtualNic", type = 1, message = "xen虚拟机添加网络接口")
	public String addXenVirtualNic() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String pool_uuid = request.getParameter("pool_uuid");
		String vm_uuid = request.getParameter("vm_uuid");
		String network_uuid = request.getParameter("netUuid");
		String mac = request.getParameter("mac");
		mac = mac.replace(":", "-");
		String getcm = request.getParameter("createMac");
		boolean macAutogenerated = false;
		if (getcm.equals("0")) {
			macAutogenerated = true;
		}
		String xen_url = "/xen/vm/create/vif/[poolUuid:" + pool_uuid + "].[vmUuid:" + vm_uuid
				+ "].[networkUuid:" + network_uuid + "].[macAutogenerated:" + macAutogenerated
				+ "].[mac:" + mac + "]/";
		String xen_result = InvokeUtil.invoke(xen_url);

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(1);
		operObj.setMESSAGE("xen虚拟机添加网络接口");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		JSONObject jo = JSONObject.fromObject(xen_result);
		String currentlyAttached = jo.get("currentlyAttached") == null ? "false" : jo
				.getString("currentlyAttached");
		if (currentlyAttached.equals("true")) {
			jo.put("currentlyAttached", "激活");
		} else {
			jo.put("currentlyAttached", "未激活");
		}
		String ip = jo.get("ip") == null ? "" : jo.getString("ip");
		if (ip.equals("")) {
			jo.put("ip", "");
		}
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("type/html;charset=UTF-8");
			// PrintWriter w = response.getWriter();
			// w.println(jo);
			// w.close();
			PrintWriterOut.printWirter(response, jo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: deleteVmVirtualNic
	 * @Description:删除虚拟机的网络接口
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 8, 2013 1:28:07 PM
	 */
	@MethodLog(remark = "XenManAction-deleteVmVirtualNic", type = 2, message = "删除虚拟机的网络接口")
	public String deleteVmVirtualNic() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String vifUuid = request.getParameter("vifUuid");
		String poolUuid = request.getParameter("poolUuid");
		String xen_url = "/xen/vm/delete/vif/[poolUuid:" + poolUuid + "].[vifUuid:" + vifUuid
				+ "]/";
		String xen_result = InvokeUtil.invoke(xen_url);

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除xen虚拟机网络接口");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		JSONObject jo = JSONObject.fromObject(xen_result);
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			// PrintWriter w = response.getWriter();
			// w.println(jo);
			// w.close();
			PrintWriterOut.printWirter(response, jo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: addNetworkPage
	 * @Description: 跳转到主机添加网络界面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 10, 2013 11:21:07 AM
	 */
	public String addNetworkPage() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String hostUuid = request.getParameter("hostUuid");
		String poolUuid = request.getParameter("poolUuid");
		String dialogName = request.getParameter("dialogName");

		String param1 = "/xen/nics/get/[hostUuid:" + hostUuid + "].[poolUuid:" + poolUuid + "]/";
		String result1 = InvokeUtil.invoke(param1);
		JSONArray jo1 = JSONArray.fromObject(result1);
		List<JSONObject> nics = JSONArray.toList(jo1, JSONObject.class);
		List<Nic> nicList = new ArrayList<Nic>();
		JSONObject vlanJo = new JSONObject();
		if (nics.get(0).get("responseCode").equals("1")) {
			nics.remove(0);
			for (JSONObject json : nics) {
				Nic nic = new Nic();
				String nicUuid = (String) json.get("nicUuid");
				String nicName = (String) json.get("nicName");
				String slaveVlan = json.getString("slaveVlan") == null ? "" : json
						.getString("slaveVlan");
				vlanJo.put(nicUuid, slaveVlan);
				nic.setNicName(nicName);
				nic.setNicUuid(nicUuid);
				nicList.add(nic);
			}

		}
		if (nics != null && nics.size() > 0) {
			theForm.setNic(nics.get(0).get("nicUuid") == null ? "" : nics.get(0).getString(
					"nicUuid"));
		}
		theForm.setSlaveVlan(vlanJo);
		theForm.setResultList(nicList);
		request.setAttribute("poolUuid", poolUuid);
		request.setAttribute("hostUuid", hostUuid);
		request.setAttribute("dialogName", dialogName);
		return "addNetworkPage";
	}

	/**
	 * 
	 * @Title: addNetwork
	 * @Description: 主机添加网络
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 14, 2013 12:00:13 PM
	 */
	@MethodLog(remark = "XenManAction-addNetwork", type = 1, message = "主机添加网络")
	public String addNetwork() {
		HttpServletRequest request = Struts2Utils.getRequest();
		try {
			String poolUuid = request.getParameter("poolUuid");
			String hostUuid = request.getParameter("hostUuid");
			String creatType = request.getParameter("creatType");
			String nicUuid = request.getParameter("nicUuid");
			String nicName = request.getParameter("nicName");
			String vlan = request.getParameter("vlan");
			String netName = URLDecoder.decode(request.getParameter("netName"), "UTF-8");
			String netExplain = URLDecoder.decode(request.getParameter("netExplain"), "UTF-8");
			boolean autoMatic = request.getParameter("autoMatic").equals("checked") ? true : false;

			String otherHostPifUuids = xenManService.getOtherHostPifUuids(poolUuid, hostUuid,
					nicName);
			String pifUUid = nicUuid + otherHostPifUuids;

			JSONObject jo = new JSONObject();
			if (creatType.equals("1")) {

				String xen_url = "/xen/network/vlan/create/[poolUuid:" + poolUuid
						+ "].[networkName:" + netName + "].[hostUuid:" + hostUuid + "].[pifUuids:"
						+ pifUUid + "].[autoMatic:" + autoMatic + "].[vlanNum:" + vlan
						+ "].[networkDesc:" + netExplain + "]/";
				String xen_result = InvokeUtil.invoke(xen_url);
				jo = JSONObject.fromObject(xen_result);
				if (jo.get("responseCode").equals("1")) {
					jo.put("mac", jo.get("mac") == null ? "" : jo.getString("mac"));
				}

			} else if (creatType.equals("2")) {

				String xen_url = "/xen/network/private/create/[poolUuid:" + poolUuid
						+ "].[networkName:" + netName + "].[autoMatic:" + autoMatic
						+ "].[networkDesc:" + netExplain + "]/";
				String xen_result = InvokeUtil.invoke(xen_url);
				jo = JSONObject.fromObject(xen_result);
				if (jo.get("responseCode").equals("1")) {
					jo.put("mac", jo.get("mac") == null ? "" : jo.getString("mac"));
					jo.put("nicName", jo.get("nicName") == null ? "" : jo.getString("nicName"));
					jo.put("nicUuid", jo.get("nicUuid") == null ? "" : jo.getString("nicUuid"));
				}
			} else if (creatType.equals("4")) {

			}

			// 写操作日志
			TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
			operObj.setREMARK("");
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("xen主机添加网络");
			int oper_result = 1;
			if (request.getSession().getAttribute(Constant.FUNCID) != null) {
				operObj.setFUNCID(Integer.parseInt(request.getSession()
						.getAttribute(Constant.FUNCID).toString()));
			}
			operObj.setRESULT(oper_result);
			@SuppressWarnings("unused")
			int retOper = operationService.insertByObj(operObj);

			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("type/html;charset=UTF-8");
			// PrintWriter w = response.getWriter();
			// w.println(jo);
			// w.close();
			PrintWriterOut.printWirter(response, jo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: cloneVMByTemOnHost
	 * @Description: 在主机节点上通过模板克隆虚拟机页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 21, 2013 11:05:25 AM
	 */
	@MethodLog(remark = "XenManAction-cloneVMByTemOnHostPage", type = 1, message = "在主机节点上通过模板克隆虚拟机页面")
	public String cloneVMByTemOnHostPage() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String host_id = request.getParameter("host_id");
		String host_uuid = request.getParameter("host_code");
		String clusterId = request.getParameter("clusterId");
		String pool_uuid = request.getParameter("pool_uuid");
		request.setAttribute("host_id", host_id);
		request.setAttribute("host_uuid", host_uuid);
		request.setAttribute("clusterId", clusterId);
		request.setAttribute("pool_uuid", pool_uuid);
		XenEntityTreeObj xen = new XenEntityTreeObj();
		xen.setType(TypeConstant.XEN_HOST);
		List list = xenEntityTreeService.queryForTree(xen);
		if (list != null && list.size() > 0) {
			theForm.setResultList(list);
		}
		TbCloud2NetInfoObj netObj = new TbCloud2NetInfoObj();
		TbCloud2IpInfoObj ipObj = new TbCloud2IpInfoObj();
		List netLst = netService.queryNetListByObj(netObj);
		if (netLst != null && netLst.size() > 0) {
			ipObj.setNET_ID(((TbCloud2NetInfoObj) netLst.get(0)).getNET_ID());
			theForm.setNET(((TbCloud2NetInfoObj) netLst.get(0)).getNET_ID());
		}
		ipObj.setISUSED("0");
		List<TbCloud2IpInfoObj> ipLst = ipService.queryForListByIPObj(ipObj);
		if (ipLst != null && ipLst.size() > 0) {
			theForm.setIP(ipLst.get(0).getIP_ID());
		}
		TemManObj tem = new TemManObj();
		tem.setType("2");
		List temList = temManService.queryForList(tem);
		if (temList != null && list.size() > 0) {
			theForm.setHealthList(temList);
		}
		theForm.setNetList(netLst);
		theForm.setIpList(ipLst);
		return "cloneByTem";
	}

	/**
	 * 
	 * @Title: goadjustInterface
	 * @Description: 跳转到调整虚拟机网络接口页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 22, 2013 10:59:46 AM
	 */
	public String goAdjustInterfacePage() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String pool_uuid = request.getParameter("pool_uuid");
		String host_uuid = request.getParameter("host_uuid");
		String vm_uuid = request.getParameter("vm_uuid");
		String mac = request.getParameter("mac");
		String networkUuid = request.getParameter("networkUuid");
		String vifUuid = request.getParameter("vifUuid");
		int flag = 1;

		String xen_url = "/xen/networks/get/[poolUuid:" + pool_uuid + "].[hostUuid:" + host_uuid
				+ "]/";
		String xen_result = InvokeUtil.invoke(xen_url);
		JSONArray jo = JSONArray.fromObject(xen_result);
		List<JSONObject> networkLst = JSONArray.toList(jo, JSONObject.class);
		List<Network> lst = new ArrayList<Network>();
		if (networkLst.get(0).get("responseCode").equals("1")) {
			networkLst.remove(0);
			for (JSONObject joObj : networkLst) {
				Network network = new Network();
				network.setNetworkUuid(joObj.get("networkUuid") == null ? "" : joObj
						.getString("networkUuid"));
				network.setNetworkName(joObj.get("networkName") == null ? "" : joObj
						.getString("networkName"));
				lst.add(network);
			}
		}

		theForm.setResultList(lst);
		theForm.setFlag(flag);
		theForm.setNetworkUuid(networkUuid);
		theForm.setMac(mac);
		request.setAttribute("vifUuid", vifUuid);
		request.setAttribute("pool_uuid", pool_uuid);
		request.setAttribute("host_uuid", host_uuid);
		request.setAttribute("vm_uuid", vm_uuid);
		return "goAddInterfacePage";
	}

	/**
	 * 
	 * @Title: adjustXenVirtualNic
	 * @Description: 调整虚拟机网络接口
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 23, 2013 9:51:31 AM
	 */
	@MethodLog(remark = "XenManAction-adjustXenVirtualNic", type = 3, message = "调整虚拟机网络接口")
	public String adjustXenVirtualNic() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String createMac = request.getParameter("createMac");
		String netUuid = request.getParameter("netUuid");
		String mac = request.getParameter("mac");
		mac = mac.replace(":", "-");
		String pool_uuid = request.getParameter("pool_uuid");
		String vm_uuid = request.getParameter("vm_uuid");
		String vifUuid = request.getParameter("vifUuid");
		boolean macAutogenerated = false;
		if (createMac.equals("0")) {
			macAutogenerated = true;
		}

		String xen_url = "/xen/vif/vm/modify/[poolUuid:" + pool_uuid + "].[vmUuid:" + vm_uuid
				+ "].[networkUuid:" + netUuid + "].[macAutogenerated:" + macAutogenerated
				+ "].[vifUuid:" + vifUuid + "].[mac:" + mac + "]/";
		String xen_result = InvokeUtil.invoke(xen_url);

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(3);
		operObj.setMESSAGE("调整xen虚拟机网络接口");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		JSONObject jo = JSONObject.fromObject(xen_result);
		String currentlyAttached = jo.get("currentlyAttached") == null ? "false" : jo
				.getString("currentlyAttached");
		if (currentlyAttached.equals("true")) {
			jo.put("currentlyAttached", "激活");
		} else {
			jo.put("currentlyAttached", "未激活");
		}
		String ip = jo.get("ip") == null ? "" : jo.getString("ip");
		if (ip.equals("")) {
			jo.put("ip", "");
		}
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("type/html;charset=UTF-8");
			// PrintWriter w = response.getWriter();
			// w.println(jo);
			// w.close();
			PrintWriterOut.printWirter(response, jo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @Title: deleteNetwork
	 * @Description: 删除网络
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 24, 2013 1:22:13 PM
	 */
	@MethodLog(remark = "XenManAction-deleteNetwork", type = 2, message = "删除网络")
	public String deleteNetwork() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String poolUuid = request.getParameter("poolUuid");
		String networkUuid = request.getParameter("networkUuid");
		String xen_url = "/xen/network/delete/[poolUuid:" + poolUuid + "].[networkUuid:"
				+ networkUuid + "]/";
		String xen_result = InvokeUtil.invoke(xen_url);

		// 写操作日志
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		operObj.setOPERTYPE(3);
		operObj.setMESSAGE("删除xen网络");
		int oper_result = 1;
		if (request.getSession().getAttribute(Constant.FUNCID) != null) {
			operObj.setFUNCID(Integer.parseInt(request.getSession().getAttribute(Constant.FUNCID)
					.toString()));
		}
		operObj.setRESULT(oper_result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);

		JSONObject jo = JSONObject.fromObject(xen_result);
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			// PrintWriter w = response.getWriter();
			// w.println(jo);
			// w.close();
			PrintWriterOut.printWirter(response, jo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: goAdjustNetworkPage
	 * @Description: 跳转到调整网络页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 27, 2013 12:51:05 PM
	 */
	public String goAdjustNetworkPage() {
		if (theForm == null) {
			theForm = new XenManForm();
		}
		try {
			HttpServletRequest request = Struts2Utils.getRequest();
			String poolUuid = request.getParameter("poolUuid");
			String hostUuid = request.getParameter("hostUuid");
			String networkUuid = request.getParameter("networkUuid");
			String nicName = URLDecoder.decode(request.getParameter("nicName") == null ? ""
					: request.getParameter("nicName"), "UTF-8");
			String networkName = URLDecoder.decode(request.getParameter("networkName") == null ? ""
					: request.getParameter("networkName"), "UTF-8");
			String networkDesc = URLDecoder.decode(request.getParameter("networkDesc") == null ? ""
					: request.getParameter("networkDesc"), "UTF-8");
			String VLAN = request.getParameter("VLAN") == null ? "" : request.getParameter("VLAN");
			String automatic = URLDecoder.decode(request.getParameter("automatic") == null ? ""
					: request.getParameter("automatic"), "UTF-8");
			String dialogName = request.getParameter("dialogName");
			// 查询物理网卡
			String param1 = "/xen/nics/get/[hostUuid:" + hostUuid + "].[poolUuid:" + poolUuid
					+ "]/";
			String result1 = InvokeUtil.invoke(param1);
			JSONArray jo1 = JSONArray.fromObject(result1);
			List<JSONObject> nics = JSONArray.toList(jo1, JSONObject.class);
			List<Nic> nicList = new ArrayList<Nic>();
			if (nics.get(0).get("responseCode").equals("1")) {
				nics.remove(0);
				Nic nic = new Nic();
				nic.setNicUuid("0");
				nic.setNicName("<内部>");
				nicList.add(nic);
				for (JSONObject json : nics) {
					Nic nic1 = new Nic();
					nic1.setNicName((String) json.get("nicName"));
					nic1.setNicUuid((String) json.get("nicUuid"));
					nicList.add(nic1);
				}
			}
			theForm.setResultList(nicList);
			if (nicName.equals("")) {
				theForm.setNic("0");
			} else {
				for (Nic nic : nicList) {
					if (nicName.equals(nic.getNicName())) {
						theForm.setNic(nic.getNicUuid());
					}
				}
			}
			theForm.setNetName(networkName);
			theForm.setNetExplain(networkDesc);
			if (VLAN.equals("-")) {
				theForm.setVlan("");
			} else {
				theForm.setVlan(VLAN);
			}
			theForm.setAutoMatic(automatic);
			request.setAttribute("poolUuid", poolUuid);
			request.setAttribute("hostUuid", hostUuid);
			request.setAttribute("networkUuid", networkUuid);
			request.setAttribute("dialogName", dialogName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAdjustNetworkPage";
	}

	/**
	 * 
	 * @Title: adjustNetwork
	 * @Description: 调整主机的网络
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 28, 2013 10:21:25 AM
	 */
	@MethodLog(remark = "XenManAction-adjustNetwork", type = 3, message = "调整主机的网络")
	public String adjustNetwork() {
		try {
			HttpServletRequest request = Struts2Utils.getRequest();
			String poolUuid = request.getParameter("poolUuid");
			String hostUuid = request.getParameter("hostUuid");
			String nicUuid = request.getParameter("nicUuid");
			String nicName = request.getParameter("nicName");
			String vlan = request.getParameter("vlan");
			String networkUuid = request.getParameter("networkUuid");
			String netName = URLDecoder.decode(request.getParameter("netName"), "UTF-8");
			String netExplain = URLDecoder.decode(request.getParameter("netExplain"), "UTF-8");
			boolean autoMatic = request.getParameter("autoMatic").equals("checked") ? true : false;
			String createType = "";
			String pifUUid = "";
			if (nicUuid.equals("0")) {
				createType = "notHaveVlan";
			} else {
				createType = "haveVlan";
				String otherHostPifUuids = xenManService.getOtherHostPifUuids(poolUuid, hostUuid,
						nicName);
				pifUUid = nicUuid + otherHostPifUuids;
			}
			String xen_url = "/xen/network/modify/[poolUuid:" + poolUuid + "].[networkName:"
					+ netName + "].[hostUuid:" + hostUuid + "].[networkUuid:" + networkUuid
					+ "].[createType:" + createType + "].[pifUuids:" + pifUUid + "].[autoMatic:"
					+ autoMatic + "].[vlanNum:" + vlan + "].[networkDesc:" + netExplain + "]/";
			String xen_result = InvokeUtil.invoke(xen_url);

			// 写操作日志
			TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
			operObj.setREMARK("");
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("调整xen主机网络");
			int oper_result = 1;
			if (request.getSession().getAttribute(Constant.FUNCID) != null) {
				operObj.setFUNCID(Integer.parseInt(request.getSession()
						.getAttribute(Constant.FUNCID).toString()));
			}
			operObj.setRESULT(oper_result);
			@SuppressWarnings("unused")
			int retOper = operationService.insertByObj(operObj);

			JSONObject jo = JSONObject.fromObject(xen_result);
			if (jo.get("responseCode").equals("1")) {
				jo.put("mac", jo.get("mac") == null ? "" : jo.getString("mac"));
				jo.put("nicName", jo.get("nicName") == null ? "" : jo.getString("nicName"));
				jo.put("nicUuid", jo.get("nicUuid") == null ? "" : jo.getString("nicUuid"));
			}
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("type/html;charset=UTF-8");
			// PrintWriter w = response.getWriter();
			// w.println(jo);
			// w.close();
			PrintWriterOut.printWirter(response, jo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
