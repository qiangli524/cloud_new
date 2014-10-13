package com.sitech.basd.yicloud.web.vmware.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.sitech.basd.common.ResponseCode;
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
import com.sitech.basd.sxcloud.cloud.util.DES3;
import com.sitech.basd.sxcloud.rsmu.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.service.system.OperationService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.cluster.ClusterObj;
import com.sitech.basd.yicloud.domain.datastore.DataStoreInfo;
import com.sitech.basd.yicloud.domain.datastore.DataStoreInfoObj;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.domain.kvm.TbYicloudDeviceHealthObj;
import com.sitech.basd.yicloud.domain.kvm.TbYicloudOsTypeObj;
import com.sitech.basd.yicloud.domain.kvm.VMManagerObj;
import com.sitech.basd.yicloud.domain.nic.NicRelationObj;
import com.sitech.basd.yicloud.domain.nic.VirtualNicObj;
import com.sitech.basd.yicloud.domain.portgroup.PortGroup;
import com.sitech.basd.yicloud.domain.script.ScriptConObj;
import com.sitech.basd.yicloud.domain.script.ScriptObj;
import com.sitech.basd.yicloud.domain.switches.VirtualSwitch;
import com.sitech.basd.yicloud.domain.templettree.TempletTreeObj;
import com.sitech.basd.yicloud.service.cluster.ClusterService;
import com.sitech.basd.yicloud.service.datastore.DataStoreService;
import com.sitech.basd.yicloud.service.entitytree.EntityTreeService;
import com.sitech.basd.yicloud.service.nic.NicRelationService;
import com.sitech.basd.yicloud.service.nic.VirtualNicService;
import com.sitech.basd.yicloud.service.portgroup.PortGroupService;
import com.sitech.basd.yicloud.service.script.ScriptService;
import com.sitech.basd.yicloud.service.templettree.TempletTreeService;
import com.sitech.basd.yicloud.service.vmware.VmwManService;
import com.sitech.basd.yicloud.util.CfgUtil;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.JsonUtils;
import com.sitech.basd.yicloud.util.ParamParser;
import com.sitech.basd.yicloud.util.TypeConstant;
import com.sitech.basd.yicloud.util.systemlog.MethodLog;
import com.sitech.basd.yicloud.util.zhejiang.CollIBMWithCMD;
import com.sitech.basd.yicloud.web.vmware.form.VmwManForm;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.ws.Operation;
import com.sitech.ws.web.NoticeUtil;

/**
 * 
 * <p>
 * Title: VMWManAction
 * </p>
 * <p>
 * Description: VMware虚拟机管理Action
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
 * @createtime Jul 25, 2012 9:56:21 AM
 * 
 */
public class VmwManAction extends CRUDBaseAction {
	private static final Logger logger = Logger.getLogger(VmwManAction.class.getName());
	private VmwManService vmwManService;
	private EntityTreeService entityTreeService;
	private NetService netService;
	private IpService ipService;
	private VmwManForm theForm;
	private String ID;
	private DataStoreService dataStoreService;
	private VMHostService vmHostService;
	private OperationService operationService;
	private TempletTreeService templetTreeService;
	private PortGroupService portGroupService;
	private String vmNetWork = "VM Network";
	private ScriptService scriptService;
	private ClusterService clusterService;
	private HostInfoService hostInfoService;
	private VirtualNicService virtualNicService;
	private NicRelationService nicRelationService;
	private TemManService temManService;
	 // 下载文件名
    private String fileName;
    //下载的文件路径
    private String inputPath;

	public void setTemManService(TemManService temManService) {
		this.temManService = temManService;
	}

	public void setNicRelationService(NicRelationService nicRelationService) {
		this.nicRelationService = nicRelationService;
	}

	public void setVirtualNicService(VirtualNicService virtualNicService) {
		this.virtualNicService = virtualNicService;
	}

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public void setScriptService(ScriptService scriptService) {
		this.scriptService = scriptService;
	}

	public String getVmNetWork() {
		return vmNetWork;
	}

	public void setVmNetWork(String vmNetWork) {
		this.vmNetWork = vmNetWork;
	}

	public void setPortGroupService(PortGroupService portGroupService) {
		this.portGroupService = portGroupService;
	}

	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}

	public void setTempletTreeService(TempletTreeService templetTreeService) {
		this.templetTreeService = templetTreeService;
	}

	public void setVmHostService(VMHostService vmHostService) {
		this.vmHostService = vmHostService;
	}

	public void setDataStoreService(DataStoreService dataStoreService) {
		this.dataStoreService = dataStoreService;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public VmwManForm getTheForm() {
		return theForm;
	}

	public void setTheForm(VmwManForm theForm) {
		this.theForm = theForm;
	}

	public void setNetService(NetService netService) {
		this.netService = netService;
	}

	public void setIpService(IpService ipService) {
		this.ipService = ipService;
	}

	public void setEntityTreeService(EntityTreeService entityTreeService) {
		this.entityTreeService = entityTreeService;
	}

	public void setVmwManService(VmwManService vmwManService) {
		this.vmwManService = vmwManService;
	}

	/**
	 * 
	 * @Title: editDeployData
	 * @Description: 进入创建vmware裸机页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 21, 2012 11:36:25 AM
	 */
	public String editDeployData() throws BaseException {
		if (theForm == null) {
			theForm = new VmwManForm();
		}
		theForm.setOsType("1");
		HttpServletRequest request = Struts2Utils.getRequest();
		String entityId = request.getParameter("entity_id");
		String type = request.getParameter("TYPE");
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		// String oper = request.getParameter("oper");
		// String ID = request.getParameter("ID");
		// String TYPE = request.getParameter("TYPE");
		// /** 查询网络列表 */
		TbCloud2NetInfoObj netObj = new TbCloud2NetInfoObj();
		List netList = netService.queryForListByObj(netObj);
		/** 存储列表 */
		DataStoreObj dsObj = new DataStoreObj();
		dsObj.setHOST_ID(entityId);
		List<DataStoreObj> dataList = dataStoreService.queryForListByObj(dsObj);

		theForm.setNetList(netList);
		theForm.setResultList(dataList);
		// 查询主机资源信息
		EntityTreeObj eObj = new EntityTreeObj();
		eObj.setId(Integer.parseInt(id));
		eObj = entityTreeService.queryTreeNode(eObj);
		String host_name = eObj.getEntityId();
		String param = "/vmware/host/msg/[hostName:" + host_name + "]/";
		String result = InvokeUtil.invoke(param);
		Map resource = ParamParser.makeup(result);
		DecimalFormat nf = new DecimalFormat("0.00");
		String cpu = (String) resource.get("cpuSize");// cpu个数
		String cpuAll = (String) resource.get("cpu");
		// cpuFre = String.valueOf(cf / 1000);// cpu频率，单位MHz
		String cpuUsage = (String) resource.get("cpuUsage");// 已使用的cpu频率(MHz)
		String cpuPre = nf.format(Double.parseDouble((String) resource.get("cpuUsage%")));// 已使用的cpu频率(MHz)
		theForm.setCpuUsage(cpuUsage);
		theForm.setCpuPer(cpuPre);
		theForm.setResource_cpu(cpuAll);

		// 获取内存已使用的百分比
		String mem = nf.format(Double.parseDouble((String) resource.get("mem")));
		;
		String memUsage = (String) resource.get("memUsage");// 已使用的内存，单位MB
		theForm.setMemUsage(memUsage);
		String memPre = nf.format(Double.parseDouble((String) resource.get("memUsage%")));// 内存使用率
		theForm.setMemPer(memPre);

		theForm.setResource_mem(mem);

		request.setAttribute("id", entityId);// 主机实体Id
		request.setAttribute("type", type);// 主机类型
		request.setAttribute("name", name);
		// request.setAttribute("oper", oper);
		// request.setAttribute("TYPE", TYPE);
		// EntityTreeObj e = new EntityTreeObj();
		//
		// TbYicloudOsTypeObj to = new TbYicloudOsTypeObj();
		// e.setId(Integer.parseInt(ID));
		// e = entityTreeService.queryTreeNode(e);
		// TbYicloudOsTypeObj tyObj = vmwManService.queryDefaultConfig(to);
		// theForm.setCURRENTCPU(tyObj.getCPU());
		// theForm.setCURRENTMEMORY(tyObj.getMEM());
		// theForm.setSTORAGE(tyObj.getSTORAGE());
		// request.setAttribute("ID", ID);

		return "edit";
	}

	/**
	 * 
	 * @Title: deployVirtualMachine
	 * @Description: 创建裸机vmware
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 21, 2012 11:32:12 AM
	 */
	@MethodLog(remark = "VmwManAction-deployVirtualMachine", type = 1, message = "创建裸机vmware")
	public String deployVirtualMachine() throws Exception {
		if (theForm == null) {
			theForm = new VmwManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		request.setCharacterEncoding("UTF-8");
		String ID = request.getParameter("ID");// 实体Id，entityId
		String TYPE = request.getParameter("TYPE");// 实体类型
		String name_en = request.getParameter("name_en");
		name_en = URLDecoder.decode(name_en, "utf-8");
		String osId = request.getParameter("osId");// 虚拟机操作系统版本id
		String osName = request.getParameter("osName");// 虚拟机操作系统版本名称
		String dataName = request.getParameter("dataName");// 存储名称
		String dataSize = request.getParameter("dataSize");// 存储大小
		String currentMemory = request.getParameter("currentmemory");
		String cpu = request.getParameter("currentcpu");
		String unit = request.getParameter("UNIT");
		String NET = request.getParameter("NET");
		String IP = request.getParameter("IP");
		VMManagerObj obj = new VMManagerObj();
		// 修改ip状态
		// int ret = editIPState(IP);
		obj.setCURRENTCPU(cpu);
		// 判断内存单位是MB还是GB
		if (unit.equals("M")) {
			obj.setCURRENTMEMORY(currentMemory);
		} else if (unit.equals("G")) {
			obj.setCURRENTMEMORY(String.valueOf(Long.parseLong(currentMemory.trim()) * 1024));
		}
		obj.setTYPE(TYPE);
		obj.setDatastoreName(dataName);// 存储名称；
		obj.setSTORAGE(String.valueOf(Long.parseLong(dataSize.trim()) * 1024));// 存储大小
		obj.setGuestOsId(osId);// 客户机操作系统类型
		obj.setNAME_EN(name_en);// 虚拟机名称
		obj.setTYPENAME(osName);// 虚拟机操作系统版本名称
		/** 虚拟机英文名称(唯一标示) */
		/*
		 * obj.setNAME_EN(name_en); obj.setIP(ip);
		 */

		String result = "";
		if (TYPE.equals(TypeConstant.VMWARE_HOST)) {// 从vmware主机创建虚拟机
			// 创建vmware虚拟机
			result = vmwManService.createVMwareVirtual(ID, obj);
		} else if (TYPE.equals(TypeConstant.VMWARE_CLUSTER)) {// 从vmware集群创建虚拟机
			obj.setID(Integer.parseInt(ID));
			result = vmwManService.createVMwareVirtual(ID, obj);
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		/*
		 * if (!result.contains("error")) {
		 * vmwManService.ftpUploadDomainInfo(request); }
		 */
		/** 将返回值装到JSON传到页面 */
		response.setContentType("type/html;charset=UTF-8");
		// result += "'ip:' + useIpInfoObj.getIPADDRESS()";
		JSONObject jo = JSONObject.fromObject(result);
		// out = response.getWriter();
		// out.println(jo.toString());
		// out.close();
		PrintWriterOut.printWirter(response, jo.toString());
		// 重置进度条
		Constant.PROCESS_LEVEL = 0;

		/*
		 * 操作日志
		 */
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		int operResult = 0;
		operObj.setOPERTYPE(1);
		operObj.setMESSAGE("创建vmware裸机" + name_en);
		// 虚拟机创建成功
		if (result.equals("{\"result\":\"1\"}")) {
			operResult = 1;
		}
		operObj.setRESULT(operResult);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return null;
	}

	/**
	 * @Title: getIPAddress
	 * @Description: 获取IP地址
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2012-3-9
	 */
	@MethodLog(remark = "VmwManAction-getIPAddress", type = 4, message = "获取IP地址")
	public String getIPAddress() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String NET = request.getParameter("NET");
		String netType = String.valueOf(request.getParameter("netType"));
		TbCloud2IpInfoObj useIpInfoObj = new TbCloud2IpInfoObj();
		if (netType.equals("2")) {
			TbCloud2NetInfoObj netInfoObj = new TbCloud2NetInfoObj();
			netInfoObj.setNAME(NET);
			TbCloud2NetInfoObj reNetInfoObj = netService.queryByObj(netInfoObj);
			TbCloud2IpInfoObj ipInfoObj = new TbCloud2IpInfoObj();
			ipInfoObj.setNET_ID(reNetInfoObj.getNET_ID());
			ipInfoObj.setISUSED("0");
			List<TbCloud2IpInfoObj> list = ipService.queryForListByObj(ipInfoObj);
			if (list != null && list.size() > 0) {
				useIpInfoObj = list.get(0);
			}
		}
		// out = response.getWriter();
		// out.println(useIpInfoObj.getIPADDRESS());
		// out.close();
		PrintWriterOut.printWirter(response, useIpInfoObj.getIPADDRESS());

		// 占用IP未写
		return null;
	}

	/**
	 * @Title: putVirtualStat
	 * @Description: 修改虚拟机状态(启动、停止、暂停、重启等)
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 21, 2012 11:35:10 AM
	 */
	@MethodLog(remark = "VmwManAction-putVirtualStat", type = 3, message = " 修改虚拟机状态(启动、停止、暂停、重启等)")
	public String putVirtualStat() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		int id = Integer.parseInt(request.getParameter("id"));// 节点Id
		String op = request.getParameter("op");// 操作类型，启动，停止等
		String name = "Parse Error!";
		try {
			name = URLDecoder.decode(request.getParameter("name"), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			logger.error(e1.getMessage() + "--不支持编码！");
		}// 虚拟机名称
		String ENTITY_ID = request.getParameter("ENTITY_ID"); // 实体ID
		String result = "";
		result = vmwManService.putVirtualStat(id, op, ENTITY_ID);
		/*
		 * 操作日志
		 */
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		int operResult = 0;
		operObj.setOPERTYPE(3);
		if (op.equals("create")) {
			operObj.setMESSAGE("虚拟机" + ENTITY_ID + "," + name + "开启");
		} else if (op.equals("shutdown")) {
			operObj.setMESSAGE("虚拟机" + ENTITY_ID + "," + name + "关闭");
		} else if (op.equals("suspend")) {
			operObj.setMESSAGE("虚拟机" + ENTITY_ID + "," + name + "挂起");
		} else if (op.equals("reboot")) {
			operObj.setMESSAGE("重启虚拟机" + ENTITY_ID + "," + name);
		} else if (op.equals("destroy")) {
			operObj.setOPERTYPE(2);
			operObj.setMESSAGE("删除虚拟机" + ENTITY_ID + "," + name);
		}

		if (result.equals("1")) {
			operResult = 1;
		}
		operObj.setRESULT(operResult);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		/** 将返回值装到JSON传到页面 */
		response.setContentType("type/html;charset=UTF-8");
		result = "{'result':'" + result + "'}";
		JSONObject jo = (JSONObject) JSONObject.fromObject(result);
		// out = response.getWriter();
		// out.println(jo.toString());
		// out.close();
		PrintWriterOut.printWirter(response, jo.toString());
		return null;
	}

	/**
	 * 
	 * @Title: validateUnique
	 * @Description: 判断虚拟机英文名称的唯一性
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 23, 2012 10:49:54 AM
	 */
	public String validateUnique() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String NAME_EN = request.getParameter("NAME_EN");
		// VMManagerObj obj = new VMManagerObj();
		// obj.setNAME_EN(NAME_EN);
		VMHostObj obj = new VMHostObj();
		obj.setVH_NAME(NAME_EN);
		obj.setVH_TYPE("1");
		List list = vmHostService.queryForListByObj(obj);
		// List list = vmwManService.queryForListByObj(obj);
		String result = "{'error':'error'}";
		if (list != null && list.size() > 0) {
			result = null;
		}
		/** 将返回值装到JSON传到页面 */
		response.setContentType("type/html;charset=UTF-8");
		JSONObject jo = JSONObject.fromObject(result);
		// out = response.getWriter();
		// out.println(jo.toString());
		// out.close();
		PrintWriterOut.printWirter(response, jo.toString());
		return null;
	}

	/**
	 * 
	 * @Title: validateCPU
	 * @Description: 验证CPU最大值是否超过剩余资源
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 10, 2012 4:58:17 PM
	 */
	public String validateCPU() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String CPU = request.getParameter("CPU");
		VMManagerObj obj = new VMManagerObj();
		String result = "{'error':'error'}";
		if (Integer.parseInt(CPU) > 10) {
			result = null;

		}
		/** 将返回值装到JSON传到页面 */
		response.setContentType("type/html;charset=UTF-8");
		JSONObject jo = JSONObject.fromObject(result);
		// out = response.getWriter();
		// out.println(jo.toString());
		// out.close();
		PrintWriterOut.printWirter(response, jo.toString());
		return null;
	}

	/**
	 * 
	 * @Title: validateMEMORY
	 * @Description: 验证内存最大值是否超过剩余资源
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 18, 2012 2:47:02 PM
	 */
	public String validateMEMORY() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String MEMORY = request.getParameter("MEMORY");
		VMManagerObj obj = new VMManagerObj();
		String result = "{'error':'error'}";
		if (Integer.parseInt(MEMORY) > 2048) {
			result = null;

		}
		/** 将返回值装到JSON传到页面 */
		response.setContentType("type/html;charset=UTF-8");
		JSONObject jo = JSONObject.fromObject(result);
		// out = response.getWriter();
		// out.println(jo.toString());
		// out.close();
		PrintWriterOut.printWirter(response, jo.toString());
		return null;
	}

	/**
	 * 
	 * @Title: checkKVMVirtualInfo
	 * @Description: 查看虚拟机信息(需要确定从库里查还是从接口)
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 21, 2012 5:08:46 PM
	 */
	@MethodLog(remark = "VmwManAction-checkKVMVirtualInfo", type = 4, message = "查看虚拟机信息(需要确定从库里查还是从接口)")
	public String checkKVMVirtualInfo() throws BaseException {
		if (theForm == null) {
			theForm = new VmwManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();

		String hostId = request.getParameter("hostId");// 虚拟机所在主机的节点ID
		String oper = request.getParameter("oper");
		String ID = request.getParameter("ID");// 虚拟机的节点ID
		EntityTreeObj e = new EntityTreeObj();
		// 获取主机节点所对应的实体ID
		e.setId(Integer.parseInt(hostId));
		e = entityTreeService.queryTreeNode(e);
		request.setAttribute("oper", oper);
		VMManagerObj hostObj = new VMManagerObj();
		String host_id = e.getEntityId();// 主机节点的实体ID
		hostObj.setID(Integer.parseInt(host_id));
		hostObj = vmwManService.queryByObj(hostObj);
		theForm.setHostIP(hostObj.getIP());
		// 获取虚拟机所对应的实体ID
		EntityTreeObj en = new EntityTreeObj();
		en.setId(Integer.parseInt(ID));
		en = entityTreeService.queryTreeNode(e);
		VMManagerObj obj = new VMManagerObj();
		obj.setID(Integer.parseInt(e.getEntityId()));
		VMManagerObj vObj = vmwManService.queryByObj(obj);
		String hostIP = vObj.getIP();
		String NAME_EN = vObj.getNAME_EN();
		theForm.setNAME_ZH(vObj.getNAME_ZH());
		theForm.setNAME_EN(NAME_EN);
		theForm.setIP(hostIP);
		String result = vmwManService.getVirtualInfo(host_id, vObj.getNAME_EN());
		if (!result.contains("Domain not found")) {
			String[] message = result.split(",");
			String[] s1 = message[4].split(":");
			theForm.setNAME_EN(s1[1]);
			String[] s2 = message[5].split(":");
			theForm.setIM_TYPE(s2[1]);
			String[] s3 = message[9].split(":");
			int mem = Integer.parseInt(s3[1]) / 1024;
			theForm.setMEMORY(String.valueOf(mem));
			String[] s4 = message[10].split(":");
			theForm.setCPU(s4[1]);
			String[] s5 = message[11].split(":");
			theForm.setSTATE(s5[1]);
			if (theForm.getSTATE().contains("RUNNING")) {
				theForm.setSTATE("正在运行");
			} else if (theForm.getSTATE().contains("PAUSED")) {
				theForm.setSTATE("暂停");
			} else if (theForm.getSTATE().contains("SHUTOFF")) {
				theForm.setSTATE("已停止");
			}
		}
		request.setAttribute("oper", oper);
		return "checkkvm";
	}

	/**
	 * 
	 * @Title: virtual_state
	 * @Description: 定时获取虚拟机状态
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 24, 2012 9:05:59 AM
	 */
	@MethodLog(remark = "VmwManAction-virtual_state", type = 4, message = "定时获取虚拟机状态")
	public String virtual_state() throws BaseException, IOException {
		if (theForm == null) {
			theForm = new VmwManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		VMManagerObj obj = new VMManagerObj();
		String ID = request.getParameter("ID");
		EntityTreeObj en = new EntityTreeObj();
		en.setId(Integer.parseInt(ID));// 虚拟机的节点ID
		en = entityTreeService.queryTreeNode(en);
		int parentId = en.getParentId();// 虚拟机所在主机的节点ID
		EntityTreeObj eObj = new EntityTreeObj();
		eObj.setId(parentId);
		eObj = entityTreeService.queryTreeNode(eObj);
		String entityId = eObj.getEntityId();
		obj.setVH_HOST(entityId);
		List<VMManagerObj> list = vmwManService.queryForListByObj(obj);
		for (VMManagerObj d : list) {
			String NAME_EN = d.getNAME_EN();
			int ENTITY_ID = d.getID();
			EntityTreeObj ent = new EntityTreeObj();
			ent.setEntityId(String.valueOf(ENTITY_ID));
			ent = entityTreeService.queryTreeNode(ent);
			d.setID(ent.getId());
			String s = vmwManService.getVirtualInfo(entityId, NAME_EN);
			if (s.indexOf("state") != -1) {
				String sta = s.substring(s.indexOf("state"));
				String[] stat = sta.split(":");
				String state = stat[1];
				d.setState(state);
			} else {
				d.setState("error");
			}
		}
		JSONArray jo = JSONArray.fromObject(list);
		// out = response.getWriter();
		// out.println(jo.toString());
		// out.close();
		PrintWriterOut.printWirter(response, jo.toString());

		return null;
	}

	/**
	 * 
	 * @Title: adjustVmwPage
	 * @Description: 进入调整vmware虚拟机界面
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @createtime May 16, 2012 2:04:02 PM
	 */
	public String adjustVmwPage() throws UnsupportedEncodingException {
		HttpServletRequest request = Struts2Utils.getRequest();
		// String type = request.getParameter("type");// 虚拟机类型，即vmware虚拟机
		String id = request.getParameter("id");// 节点entityId，即虚拟机code，唯一标识
		String name = request.getParameter("name");// vmware虚拟机名称为唯一标识
		name = URLDecoder.decode(name, "utf-8");
		String parentName = request.getParameter("parentName");// 所在的主机，entityId,即code
		String hostName = request.getParameter("hostName");
		VMHostObj obj = new VMHostObj();
		// obj.setVH_TYPE(type);
		obj.setVH_TYPE("1");// 虚拟机类型
		obj.setVH_UUID(id);
		// obj.setID(Integer.parseInt(id));
		// obj.setVH_NAME(name);
		obj = vmHostService.queryByObj(obj);
		if (theForm == null) {
			theForm = new VmwManForm();
		}
		theForm.setNAME_EN(name);// 虚拟机名称
		theForm.setCPU(obj.getVH_CPU());
		theForm.setIP(obj.getVH_IP());
		theForm.setMEMORY(obj.getVH_MEM());
		VirtualSwitch vsObj = new VirtualSwitch();
		vsObj.setHostName(parentName);
		List<NicRelationObj> resultList = portGroupService.queryHostPortGroup(vsObj);
		List pgList = new ArrayList<PortGroup>();
		if (resultList != null && resultList.size() > 0) {
			for (Object o : resultList) {
				NicRelationObj nicReObj = (NicRelationObj) o;
				PortGroup pg = new PortGroup();
				pg.setPguuid(nicReObj.getFromUuid());
				pg = portGroupService.queryPortGroupById(pg);
				if (pg != null) {
					pgList.add(pg);
				}
			}
		}
		String storage = obj.getVH_STORAGE();
		if (storage != null && !"".equals(storage)) {
			theForm.setDataSize(String.valueOf(Integer.parseInt(obj.getVH_STORAGE()) / 1024 / 1024));
		}
		theForm.setResultList(pgList);
		theForm.setHostIP(hostName);
		// theForm.setID(Integer.parseInt(id));
		theForm.setCODE(id);// 虚拟机code
		String[] nicnum = null;
		try {
			String url = "/vmware/network/getVmportgroup/[vmName:" + id + "]/";
			String result = InvokeUtil.invoke(url);
			if (result != null || !result.equals("error")) {
				// List<Map<String, String>> nicList = JSONUtil
				// .fromJSON(
				// result,
				// new JSONUtil.TypeReference<List<Map<String, String>>>() {
				// });
				// request.setAttribute("nicList", nicList.size());

				List<Map<String, String>> nicList = (List<Map<String, String>>) JsonUtils.jsonToList(URLDecoder.decode(
						result, "utf-8"), HashMap.class);
				request.setAttribute("nicList", nicList);
				nicnum = new String[nicList.size()];
				for (int i = 0; i < nicList.size(); i++) {
					Map<String, String> map = nicList.get(i);
					Set<Entry<String, String>> enttySet = map.entrySet();
					for (Entry<String, String> entry : enttySet) {
						String value = parentName + "_" + entry.getValue();
						entry.setValue(value);
						String key = entry.getKey();
						nicnum[i] = URLEncoder.encode(key, "utf-8");
					}
					// request.setAttribute("nic" + i, map.keySet().iterator()
					// .next());
					// request.setAttribute("portgroup" + i, map.values()
					// .iterator().next());
				}

				// vmNetWork = result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			nicnum = new String[1];
		}
		request.setAttribute("nicnum", nicnum);
		request.setAttribute("hostCode", parentName);
		return "adjustVmwPage";
	}

	/**
	 * 
	 * @Title: adjustVmwVirtualInfo
	 * @Description: 调整vmware虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime May 16, 2012 2:04:02 PM
	 */
	@MethodLog(remark = "VmwManAction-adjustVmwVirtualInfo", type = 3, message = "调整vmware虚拟机")
	public String adjustVmwVirtualInfo() throws BaseException, IOException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String cpu = request.getParameter("cpu");
		String memory = request.getParameter("memory");
		String dataSize = request.getParameter("dataSize");
		String NAME_EN = request.getParameter("NAME_EN");// 此时的NAME_EN为code，唯一标识
		String id = request.getParameter("ID");
		String nic1 = URLDecoder.decode(request.getParameter("nic1"), "UTF-8");// 虚拟网卡广州测试临时值，以后修改
		String nic2 = URLDecoder.decode(request.getParameter("nic2"), "UTF-8");//
		String nic1value = request.getParameter("nic1value");
		String nic2value = request.getParameter("nic2value");
		String hostCode = request.getParameter("hostCode");
		VMManagerObj obj = new VMManagerObj();
		obj.setNAME_EN(NAME_EN);
		obj.setCPU(cpu);
		obj.setMEMORY(memory);
		obj.setAUXILIARY(nic1);
		obj.setSUB_TYPE(nic1);
		obj.setSUB_TYPENAME(nic1value);
		obj.setBRAND(nic2);
		obj.setBRANDNAME(nic2value);
		obj.setHostUuid(hostCode);
		if (dataSize != null && !"".equals(dataSize)) {
			obj.setSTORAGE(String.valueOf(Integer.parseInt(dataSize) * 1024 * 1024));
		}
		obj.setID(Integer.parseInt(id));
		String result = vmwManService.adjustVmwVirtualInfo(obj);

		/*
		 * 操作日志
		 */
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		int operResult = 0;
		operObj.setOPERTYPE(3);
		operObj.setMESSAGE("调整虚拟机" + NAME_EN + ",CPU:" + cpu + ",内存:" + memory + ",存储：" + dataSize + "GB");
		if (result.equals("1")) {
			operResult = 1;
		}
		operObj.setRESULT(operResult);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志

		List<String> list = new ArrayList<String>();
		list.add(result);
		JSONArray jo = JSONArray.fromObject(list);
		// out = response.getWriter();
		// out.println(jo.toString());
		// out.close();
		PrintWriterOut.printWirter(response, jo.toString());
		return null;
	}

	/**
	 * 
	 * @Title: listHealthState
	 * @Description: 查看主机或虚拟机的健康状态信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 21, 2012 10:28:53 AM
	 */
	@MethodLog(remark = "VmwManAction-listHealthState", type = 4, message = "查看主机或虚拟机的健康状态信息")
	public String listHealthState() throws BaseException, IOException {
		TbYicloudDeviceHealthObj obj = new TbYicloudDeviceHealthObj();
		if (theForm == null) {
			theForm = new VmwManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String ID = request.getParameter("ID");
		obj.setDEVICE_ID(ID);
		VMManagerObj vObj = new VMManagerObj();
		vObj.setID(Integer.parseInt(ID));
		List<TbYicloudDeviceHealthObj> healthList = vmwManService.queryHealthStateList(obj);
		vObj = vmwManService.queryByObj(vObj);
		theForm.setIP(vObj.getIP());
		theForm.setNAME_ZH(vObj.getNAME_ZH());
		if (healthList != null && healthList.size() > 0) {
			obj = healthList.get(0);
			theForm.setCPU(obj.getCPU());
			theForm.setIO(obj.getIO());
			theForm.setAPP_DOWN_NAMES(obj.getAPP_DOWN_NAMES());
			theForm.setAPP_HASDOWN(obj.getAPP_HASDOWN());
			String open = ":<img src=\"sxcloud/images/virtual/port_open.png\"/>";
			String close = ":<img src=\"sxcloud/images/virtual/port_close.png\">";
			request.setAttribute("maohao", ":");
			String port = obj.getPORT_ISBLOCKED();
			port = port.replace(":0", open);
			port = port.replace(":1", close);
			theForm.setPORT_ISBLOCKED(port);
			request.setAttribute("PORT_ISBLOCKED", port);
			theForm.setSTORAGE(obj.getSTORAGE());
			theForm.setMIDDLE_DOWN_NAMES(obj.getMIDDLE_DOWN_NAMES());
			theForm.setMIDDLE_HASDOWN(obj.getMIDDLE_HASDOWN());
			theForm.setMEMORY(obj.getMEM());
			theForm.setIP_ISBLOCKED(obj.getIP_ISBLOCKED());
		}
		request.setAttribute("ID", ID);
		return "listhealth";
	}

	/**
	 * 
	 * @Title: getIpAddress
	 * @Description: Ajax获取IP信息列表
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Jun 15, 2012 1:18:43 PM
	 */
	@MethodLog(remark = "VmwManAction-getIpAddress", type = 4, message = "Ajax获取IP信息列表")
	public String getIpAddress() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String NET_ID = request.getParameter("NET_ID");
		StringBuffer buff = new StringBuffer("{");
		String info = "";
		if (NET_ID != null && !"".equals(NET_ID)) {
			TbCloud2IpInfoObj ipobj = new TbCloud2IpInfoObj();
			ipobj.setNET_ID(NET_ID);
			ipobj.setISUSED("0");
			ipobj.setIP_TYPE("2");
			List ipList = ipService.queryIPForList(ipobj);
			int j = 0;
			for (int i = 0; i < ipList.size(); i++) {
				TbCloud2IpInfoObj objT = new TbCloud2IpInfoObj();
				objT = (TbCloud2IpInfoObj) ipList.get(i);
				if (objT.getIPADDRESS() != null && !"".equals(objT.getIPADDRESS())) {
					if (j != 0) {
						buff.append(",");
					}
					buff.append("'");
					buff.append((String) objT.getIP_ID());
					buff.append("':'");
					buff.append((String) objT.getIPADDRESS());
					buff.append("'");
					j++;
				}
			}
		}
		buff.append("}");
		info = buff.toString();
		request.setAttribute("info", info);
		return SUCCESS;
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
	@MethodLog(remark = "VmwManAction-getProgressbarVal", type = 4, message = "获取进度条值")
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
	 * @Title: delTreeNode
	 * @Description: 删除选定树节点
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 18, 2012 9:15:46 AM
	 */
	@MethodLog(remark = "VmwManAction-delTreeNode", type = 2, message = " 删除选定树节点")
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
	 * @Title: getOsTypeList
	 * @Description: 获取操作系统列表
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Jul 25, 2012 2:25:53 PM
	 */
	@MethodLog(remark = "VmwManAction-getOsTypeList", type = 4, message = "获取操作系统列表")
	public String getOsTypeList() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String osType = request.getParameter("osType");
		StringBuffer buff = new StringBuffer("{");
		String info = "";
		if (osType != null && !"".equals(osType)) {
			TbYicloudOsTypeObj to = new TbYicloudOsTypeObj();
			to.setOS_TYPE(osType);
			to.setVIR_TYPE("vmware");
			List<TbYicloudOsTypeObj> osList = vmwManService.queryOSList(to);
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
		PrintWriterOut.printWirter(response, info);
		// request.setAttribute("info", info);
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: cloneVM
	 * @Description: 进入clone虚拟机界面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 26, 2012 10:35:05 AM
	 */
	public String cloneVM() {
		if (theForm == null) {
			theForm = new VmwManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String parent_id = request.getParameter("parent_id");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String entityId = request.getParameter("entityId");
		EntityTreeObj obj = new EntityTreeObj();
		obj.setId(Integer.parseInt(parent_id));
		EntityTreeObj en = entityTreeService.queryTreeNode(obj);
		obj.setParentId(en.getParentId());
		List<EntityTreeObj> list = entityTreeService.queryForHostList(obj);
		TbCloud2NetInfoObj netObj = new TbCloud2NetInfoObj();
		List netList = netService.queryForListByObj(netObj);
		VMHostObj vmHostObj = new VMHostObj();
		vmHostObj.setVH_UUID(entityId);
		vmHostObj = vmHostService.queryByObj(vmHostObj);
		theForm.setHostList(list);
		theForm.setNAME_ZH(name);
		if (netList != null && netList.size() > 0) {
			theForm.setNetList(netList);
		} else {
			theForm.setNetList(new ArrayList());
		}
		if (vmHostObj != null) {
			theForm.setCPU(vmHostObj.getVH_CPU());
			theForm.setMEMORY(vmHostObj.getVH_MEM());
			int dssize = Integer.parseInt(vmHostObj.getVH_STORAGE()) / 1024 / 1024;
			theForm.setSTORAGE(dssize + "");
		}
		request.setAttribute("name", name);
		request.setAttribute("type", type);
		request.setAttribute("entityId", entityId);
		return "clone";

	}

	/**
	 * 
	 * @Title: getStorage
	 * @Description: 获取不同主机所对应的存储列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 26, 2012 10:32:58 AM
	 */
	@MethodLog(remark = "VmwManAction-getStorage", type = 4, message = "获取不同主机所对应的存储列表")
	public String getStorage() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String HOST_ID = request.getParameter("HOST_ID");
		StringBuffer buff = new StringBuffer("{");
		String info = "";
		if (HOST_ID != null && !"".equals(HOST_ID)) {
			DataStoreObj obj = new DataStoreObj();
			obj.setHOST_ID(HOST_ID);
			List<DataStoreObj> list = dataStoreService.queryForListByObj(obj);
			int j = 0;
			for (int i = 0; i < list.size(); i++) {
				DataStoreObj os = list.get(i);
				if (j != 0) {
					buff.append(",");
				}
				buff.append("'");
				buff.append(String.valueOf(os.getID()));
				buff.append("':'");
				buff.append((String) os.getNAME());
				buff.append("'");
				j++;
			}
		}
		buff.append("}");
		info = buff.toString();
		request.setAttribute("info", info);
		return "ajax";
	}

	/**
	 * 
	 * @Title: getClusterDs
	 * @Description: 获取不同集群所对应的存储列表
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 26, 2012 10:32:58 AM
	 */
	@MethodLog(remark = "VmwManAction-getClusterDs", type = 4, message = "获取不同集群所对应的存储列表")
	public String getClusterDs() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String clusterId = request.getParameter("clusterId");// 此时的clusterId为code
		// 根据code查询集群Id
		ClusterObj cObj = new ClusterObj();
		cObj.setC_uuid(clusterId);
		cObj = clusterService.queryByObj(cObj);
		int cId = cObj.getId();

		DataStoreObj dsObj = new DataStoreObj();
		if (clusterId != null) {
			dsObj.setCLUSTER_ID(cId + "");
		}
		List<DataStoreObj> dataList = dataStoreService.queryClusterds(dsObj);
		JSONArray json = JSONArray.fromObject(dataList);
		PrintWriter p = Struts2Utils.getResponse().getWriter();
		p.print(json.toString());
		p.close();
		return null;
	}

	/**
	 * 
	 * @Title: getStoreInfo
	 * @Description: 获取存储信息
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Jul 28, 2012 3:03:48 PM
	 */
	@MethodLog(remark = "VmwManAction-getStoreInfo", type = 4, message = "获取存储信息")
	public String getStoreInfo() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String hostname = request.getParameter("hostname");
		String entityId = request.getParameter("entityId");
		String dsname = request.getParameter("dsname");
		String host_code = request.getParameter("host_code");
		DataStoreInfo info = vmwManService.getDSinfo(host_code, dsname);
		DecimalFormat nf = new DecimalFormat("0.00");
		Double capa = Double.parseDouble(info.getCapacity());
		Double free = Double.parseDouble(info.getFreeSpace());
		info.setCapacity(nf.format(capa));
		info.setFreeSpace(nf.format(free));
		String storePer = nf.format((capa - free) / capa * 100) + "%";
		String result = "{'capacity':'" + info.getCapacity() + "'," + "'freeSpace':'" + info.getFreeSpace() + "',"
				+ "'storePer':'" + storePer + "'}";
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
	 * @Title: cloneVirtualMac
	 * @Description: 克隆虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @createtime Jul 26, 2012 3:12:50 PM
	 */
	@MethodLog(remark = "VmwManAction-cloneVirtualMac", type = 4, message = "克隆虚拟机")
	public String cloneVirtualMac() throws UnsupportedEncodingException {
		if (theForm == null) {
			theForm = new VmwManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String vmCode = request.getParameter("vmCode");
		String type = request.getParameter("type");
		String result = vmwManService.cloneVmwarevm(vmCode, type, theForm);
		String json = "{\"result\":\"" + result + "\"}";

		/*
		 * 操作日志
		 */
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		int operResult = 0;
		operObj.setOPERTYPE(1);
		operObj.setMESSAGE("从虚拟机" + vmCode + "克隆虚拟机" + theForm.getNAME_EN() + ",克隆到主机" + theForm.getHostCode());
		if (result.equals("1")) {
			operResult = 1;
		}
		operObj.setRESULT(operResult);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志

		try {
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(json);
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// return "oper_success";
		return null;
	}

	/**
	 * 
	 * @Title: migratePage
	 * @Description: 进入迁移页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @createtime Jul 31, 2012 9:39:11 AM
	 */
	public String migratePage() throws UnsupportedEncodingException {
		if (theForm == null) {
			theForm = new VmwManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String parent_id = request.getParameter("parent_id");
		String vmName = request.getParameter("vmName");// 此时的name为code,唯一标识
		String name = request.getParameter("name");// 虚拟机名称
		name = URLDecoder.decode(name, "utf-8");
		String host_code = request.getParameter("host_code");
		String id = request.getParameter("id");
		EntityTreeObj obj = new EntityTreeObj();
		obj.setId(Integer.parseInt(parent_id));
		obj = entityTreeService.queryTreeNode(obj);
		EntityTreeObj en = new EntityTreeObj();
		en.setId(Integer.parseInt(parent_id));
		List hostList = entityTreeService.queryForMigHostList(en);
		theForm.setHostList(hostList);
		theForm.setMigType("1");
		// 查询主机ID
		String HOST_ID = obj.getEntityId();
		String host_name = obj.getName();
		request.setAttribute("HOST_ID", HOST_ID);
		request.setAttribute("host_name", host_name);
		request.setAttribute("parent_id", parent_id);
		request.setAttribute("vmName", vmName);
		request.setAttribute("id", id);
		request.setAttribute("name", name);
		request.setAttribute("host_code", host_code);
		return "migrate_page";
	}

	/**
	 * 
	 * @Title: getMigTypeInfo
	 * @Description: 获取不同迁移类型所对应的信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 31, 2012 11:54:38 AM
	 */
	@MethodLog(remark = "VmwManAction-getMigTypeInfo", type = 4, message = "获取不同迁移类型所对应的信息")
	public String getMigTypeInfo() {
		if (theForm == null) {
			theForm = new VmwManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String migType = request.getParameter("migType");
		String host_id = request.getParameter("host_id");
		String parent_id = request.getParameter("parent_id");
		StringBuffer buff = new StringBuffer("{");
		String info = "";
		DataStoreObj obj = new DataStoreObj();
		if (migType.equals("2")) {
			obj.setHOST_ID(host_id);
			List<DataStoreObj> storeList = dataStoreService.queryForListByObj(obj);
			theForm.setStorageList(storeList);
			// request.setAttribute("migType", migType);
			int j = 0;
			for (int i = 0; i < storeList.size(); i++) {
				DataStoreObj os = new DataStoreObj();
				os = storeList.get(i);
				if (j != 0) {
					buff.append(",");
				}
				buff.append("'");
				buff.append(String.valueOf(os.getID()));
				buff.append("':'");
				buff.append((String) os.getNAME());
				buff.append("'");
				j++;
			}
			buff.append("}");
			info = buff.toString();
			request.setAttribute("info", info);
			return "ajax";
		} else {
			EntityTreeObj en = new EntityTreeObj();
			en.setId(Integer.parseInt(parent_id));
			List<EntityTreeObj> hostList = entityTreeService.queryForMigHostList(en);
			theForm.setHostList(hostList);
			theForm.setMigType("1");
			int j = 0;
			for (int i = 0; i < hostList.size(); i++) {
				EntityTreeObj os = new EntityTreeObj();
				os = hostList.get(i);
				if (j != 0) {
					buff.append(",");
				}
				buff.append("'");
				buff.append(String.valueOf(os.getId()));
				buff.append("':'");
				buff.append((String) os.getName());
				buff.append("'");
				j++;
			}
			buff.append("}");
			info = buff.toString();
			request.setAttribute("info", info);
			return "ajax";
		}
	}

	/**
	 * 
	 * @Title: migrateVM
	 * @Description: 虚拟机迁移
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 31, 2012 3:24:55 PM
	 */
	@MethodLog(remark = "VmwManAction-migrateVM", type = 3, message = "虚拟机迁移")
	public String migrateVM() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String vmName = request.getParameter("vmName");
		String migType = request.getParameter("migType");
		String hostname = request.getParameter("hostname");
		String dsname = request.getParameter("dsname");
		String HOST_ID = request.getParameter("HOST_ID");
		String host = request.getParameter("host");// 主机名称
		String name = request.getParameter("name");// 虚拟机名称
		String id = request.getParameter("id");// 节点Id
		String result = "";
		if (migType.equals("1")) {
			result = vmwManService.migrateVMByHost(vmName, hostname);// 此时的vmName和hostname都为code
			if (result.equals("1")) {// 迁移成功
				EntityTreeObj obj = new EntityTreeObj();
				obj.setEntityId(hostname);// entityId即为code,此时的hostname为code
				obj = entityTreeService.queryTreeNode(obj);
				int hostNodeId = obj.getId();// 得到迁移至主机的父节点Id
				EntityTreeObj en = new EntityTreeObj();
				en.setId(Integer.parseInt(id));
				// en.setName(vmName);
				en.setType("0");
				en.setParentId(hostNodeId);
				int ret = entityTreeService.updateNodeParentId(en);

				// 更新虚拟机关联主机
				TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
				hostObj.setH_uuid(hostname);
				hostObj = hostInfoService.queryByObj(hostObj);
				VMHostObj vmObj = new VMHostObj();
				vmObj.setEQ_ID(hostObj.getEq_id());
				vmObj.setVH_UUID(vmName);
				vmHostService.updateVMHostEqId(vmObj);
				/*
				 * 操作日志
				 */
				TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
				operObj.setREMARK("");
				int operResult = 0;
				operObj.setOPERTYPE(3);
				operObj.setMESSAGE("迁移虚拟机" + vmName + "," + name + "到主机" + hostname + "," + host);
				if (result.equals("1")) {
					operResult = 1;
				}
				operObj.setRESULT(operResult);
				@SuppressWarnings("unused")
				int retOper = operationService.insertByObj(operObj);// 写操作日志
			}
			List<String> list = new ArrayList<String>();
			list.add(result);
			JSONArray json = JSONArray.fromObject(list);
			try {
				Struts2Utils.getResponse().getWriter().print(json.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		} else {
			result = vmwManService.migrateVMByDs(vmName, dsname);
			List<String> list = new ArrayList<String>();
			list.add(result);
			JSONArray json = JSONArray.fromObject(list);
			try {
				Struts2Utils.getResponse().getWriter().print(json.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		/*
		 * if (result.contains("responseCode:-1")) { return "error"; } else {
		 * return "oper_success"; }
		 */
	}

	/**
	 * 
	 * @Title: vmInfo
	 * @Description: 单击时虚拟机摘要信息
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime May 21, 2012 10:28:53 AM
	 */
	@MethodLog(remark = "VmwManAction-vmInfo", type = 4, message = "单击时虚拟机摘要信息")
	public String vmInfo() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String parentName = request.getParameter("parentName");
		VMHostObj obj = new VMHostObj();
		obj.setVH_TYPE("1");
		obj.setVH_UUID(id);
		// obj.setVH_NAME(name);
		obj = vmHostService.queryByObj(obj);
		if (theForm == null) {
			theForm = new VmwManForm();
		}
		theForm.setNAME_EN(obj.getVH_NAME());// 虚拟机名称
		Double STORAGE = 0.0;
		DecimalFormat nf = new DecimalFormat("0.00");
		if (obj != null) {
			theForm.setOsType(obj.getVH_SYSTEM());
			theForm.setCPU(obj.getVH_CPU());
			theForm.setMEMORY(obj.getVH_MEM());
			theForm.setIM_VERSION(obj.getVH_SYSTEM());
			if (obj.getVH_STORAGE() != null) {
				theForm.setDataSize(String.valueOf(nf.format(Double.parseDouble(obj.getVH_STORAGE()) / 1024 / 1024)));
			}

			theForm.setHostIP(parentName);
			theForm.setIP(obj.getVH_IP());
			// 浙江竞标
			theForm.setREMARK("已开启");
			String vhStat = obj.getVH_STAT();
			if (vhStat != null && vhStat.equals("0")) {
				theForm.setINTERFACE("已关闭");
			} else if (vhStat != null && vhStat.equals("1")) {
				theForm.setINTERFACE("已开启");
			}
			theForm.setCODE("8");
			theForm.setDNS(obj.getDNS());
		}

		ScriptConObj scriptConObj = new ScriptConObj();
		scriptConObj.setTempletId(String.valueOf(obj.getID()));
		scriptConObj.setType("1");
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
		theForm.setResultList(resultList);

		return "vmInfo";
	}

	/**
	 * 
	 * @Title: queryUnitId
	 * @Description:查询虚拟机的unitId
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime May 21, 2012 10:28:53 AM
	 */
	@MethodLog(remark = "VmwManAction-queryUnitId", type = 4, message = "查询虚拟机的unitId")
	public String queryUnitId() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		VMHostObj obj = new VMHostObj();
		obj.setVH_NAME(name);
		obj.setVH_TYPE(type);
		obj = vmHostService.queryByObj(obj);
		String unitId = obj.getVH_UNITID();
		List<String> jsonArr = new ArrayList<String>();
		jsonArr.add(unitId);
		try {
			JSONArray json = new JSONArray();
			json = JSONArray.fromObject(jsonArr);
			Struts2Utils.getResponse().getWriter().print(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.debug("VmwManAction.queryUnitId:" + e.getMessage() + e.getClass().getName());
		}
		return null;
	}

	/**
	 * 
	 * @Title: editDeployData
	 * @Description: 进入创建vmware裸机页面(浙江演示)
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 21, 2012 11:36:25 AM
	 */
	public String intoForDemoPage() throws BaseException {
		if (theForm == null) {
			theForm = new VmwManForm();
		}
		theForm.setOsType("1");
		HttpServletRequest request = Struts2Utils.getRequest();
		String entityId = request.getParameter("entity_id");
		String type = request.getParameter("TYPE");
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		// String oper = request.getParameter("oper");
		// String ID = request.getParameter("ID");
		// String TYPE = request.getParameter("TYPE");
		// /** 查询网络列表 */
		TbCloud2NetInfoObj netObj = new TbCloud2NetInfoObj();
		List netList = netService.queryForListByObj(netObj);
		/** 存储列表 */
		DataStoreObj dsObj = new DataStoreObj();
		dsObj.setHOST_ID(entityId);
		List<DataStoreObj> dataList = dataStoreService.queryForListByObj(dsObj);

		theForm.setNetList(netList);
		theForm.setResultList(dataList);
		// 查询主机资源信息
		EntityTreeObj eObj = new EntityTreeObj();
		eObj.setId(Integer.parseInt(id));
		eObj = entityTreeService.queryTreeNode(eObj);
		String host_name = eObj.getName();
		String param = "/vmware/host/msg/[hostName:" + host_name + "]/";
		String result = InvokeUtil.invoke(param);
		Map resource = ParamParser.makeup(result);
		DecimalFormat nf = new DecimalFormat("0.00");
		String cpu = (String) resource.get("cpuSize");// cpu个数
		String cpuFre = (String) resource.get("cpu");
		double cf = Double.parseDouble(cpuFre);
		// cpuFre = String.valueOf(cf / 1000);// cpu频率，单位MHz
		String cpuUsage = (String) resource.get("cpuUsage");// 已使用的cpu频率(MHz)
		if (cpuUsage != null) {
			double cpuUs = Double.parseDouble(cpuUsage);
			String cpuPer = String.valueOf(nf.format(cpuUs / (Integer.parseInt(cpu) * cf / 1000000) * 100)) + "%";
			theForm.setCpuPer(cpuPer);
		}

		// 获取内存已使用的百分比
		String mem = (String) resource.get("mem");
		double me = Double.parseDouble(mem);
		// mem = String.valueOf(me / (1024 * 1024));// 单位MB
		String memUsage = (String) resource.get("memUsage");// 已使用的内存，单位MB
		if (memUsage != null) {
			me = (me / (1024 * 1024));
			// me = Double.parseDouble(memUsage);
			double meUs = Double.parseDouble(memUsage);
			double mPer = meUs / me;
			String memPer = nf.format((mPer * 100)) + "%";
			theForm.setMemPer(memPer);
		}

		theForm.setResource_cpu(cpu);
		theForm.setResource_mem(nf.format(me));
		// theForm.setCpuUsage(cpuUsage);
		// theForm.setMemUsage(memUsage);

		request.setAttribute("id", entityId);// 主机实体Id
		request.setAttribute("type", type);// 主机类型
		request.setAttribute("name", name);
		// request.setAttribute("oper", oper);
		// request.setAttribute("TYPE", TYPE);
		// EntityTreeObj e = new EntityTreeObj();
		//
		// TbYicloudOsTypeObj to = new TbYicloudOsTypeObj();
		// e.setId(Integer.parseInt(ID));
		// e = entityTreeService.queryTreeNode(e);
		// TbYicloudOsTypeObj tyObj = vmwManService.queryDefaultConfig(to);
		// theForm.setCURRENTCPU(tyObj.getCPU());
		// theForm.setCURRENTMEMORY(tyObj.getMEM());
		// theForm.setSTORAGE(tyObj.getSTORAGE());
		// request.setAttribute("ID", ID);
		request.setAttribute("host_name", host_name);
		request.setAttribute("entityId", entityId);
		return "demo";
	}

	/**
	 * 
	 * @Title: cloneVirtualMachine
	 * @Description: 创建裸机vmware(浙江演示)
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 21, 2012 11:32:12 AM
	 */
	@MethodLog(remark = "VmwManAction-cloneVirtualMachine", type = 1, message = "创建裸机vmware(浙江演示)")
	public String cloneVirtualMachine() throws Exception {
		if (theForm == null) {
			theForm = new VmwManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		request.setCharacterEncoding("UTF-8");
		String ID = request.getParameter("ID");// 实体Id，entityId
		String TYPE = request.getParameter("TYPE");// 实体类型
		String name_en = request.getParameter("name_en");
		name_en = URLDecoder.decode(name_en, "utf-8");
		String osId = request.getParameter("osId");// 虚拟机操作系统版本id
		String osName = request.getParameter("osName");// 虚拟机操作系统版本名称
		String dataName = request.getParameter("dataName");// 存储名称
		String dataSize = request.getParameter("dataSize");// 存储大小
		String currentMemory = request.getParameter("currentmemory");
		String cpu = request.getParameter("currentcpu");
		String unit = request.getParameter("UNIT");
		String NET_ID = theForm.getNET();
		VMManagerObj obj = new VMManagerObj();
		EntityTreeObj en = new EntityTreeObj();
		en.setEntityId(ID);
		en = entityTreeService.queryTreeNode(en);
		String host_name = en.getName();
		obj.setHost_name(host_name);
		obj.setCURRENTCPU(cpu);
		// 判断内存单位是MB还是GB
		if (unit.equals("MB")) {
			obj.setCURRENTMEMORY(currentMemory);
		} else if (unit.equals("GB")) {
			obj.setCURRENTMEMORY(String.valueOf(Long.parseLong(currentMemory.trim()) * 1024));
		}
		obj.setTYPE(TYPE);
		obj.setDatastoreName(dataName);// 存储名称；
		obj.setSTORAGE(String.valueOf(Long.parseLong(dataSize.trim()) * 1024));// 存储大小
		obj.setGuestOsId(osId);// 客户机操作系统类型
		obj.setNAME_EN(name_en);// 虚拟机名称
		obj.setTYPENAME(osName);// 虚拟机操作系统版本名称
		obj.setNET_ID(NET_ID);
		/** 虚拟机英文名称(唯一标示) */
		/*
		 * obj.setNAME_EN(name_en); obj.setIP(ip);
		 */

		String result = "";
		if (TYPE.equals(TypeConstant.VMWARE_HOST)) {
			// 创建vmware虚拟机
			result = vmwManService.cloneVMwareVirtual(Integer.parseInt(ID), obj);

		}

		/*
		 * if (!result.contains("error")) {
		 * vmwManService.ftpUploadDomainInfo(request); }
		 */
		/** 将返回值装到JSON传到页面 */
		response.setContentType("type/html;charset=UTF-8");
		JSONObject jo = JSONObject.fromObject(result);
		// out = response.getWriter();
		// out.println(jo.toString());
		// out.close();
		PrintWriterOut.printWirter(response, jo.toString());
		// 重置进度条
		Constant.PROCESS_LEVEL = 0;

		return null;
	}

	/**
	 * 
	 * @Title: startApply
	 * @Description: 开启应用
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Aug 18, 2012 8:11:56 PM
	 */
	@MethodLog(remark = "VmwManAction-startApply", type = 1, message = "开启应用")
	public String startApply() throws Exception {
		/** 开启tomcat服务 */
		HashMap params = new HashMap();
		params.put("EQ_ID", "test33333333");
		params.put("IP_ADDR", "172.21.1.112");
		params.put("USER_NAME", "root");
		params.put("PASSWORD", DES3.encrypt("111111"));
		params.put("PROTOCOL", "ssh");
		params.put("PORT", "22");
		params.put("HOST_NAME", "cloud2");
		params.put("HMC_SN", "");

		CollIBMWithCMD cmd = new CollIBMWithCMD(params);
		cmd.init(params);
		cmd.start();
		/** 开启tomcat服务 */
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("type/html;charset=UTF-8");
		JSONObject jo = JSONObject.fromObject("{'result':'success'}");
		// out = response.getWriter();
		// out.println(jo.toString());
		// out.close();
		PrintWriterOut.printWirter(response, jo.toString());
		return null;
	}

	/**
	 * 
	 * @Title: getEventInfo
	 * @Description: 获取事件的详细信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 4, 2012 4:28:01 PM
	 */
	public String getEventInfo() {

		return null;
	}

	/**
	 * 
	 * @Title: cloneToTem
	 * @Description: 克隆为模板
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Sep 18, 2012 1:35:01 PM
	 */
	@MethodLog(remark = "VmwManAction-cloneToTem", type = 1, message = "克隆为模板")
	public String cloneToTem() {
		if (theForm == null) {
			theForm = new VmwManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String parent_id = request.getParameter("parent_id");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String entityId = request.getParameter("entity_id");
		EntityTreeObj obj = new EntityTreeObj();
		obj.setId(Integer.parseInt(parent_id));
		EntityTreeObj en = entityTreeService.queryTreeNode(obj);
		obj.setParentId(en.getParentId());
		List<EntityTreeObj> list = entityTreeService.queryForHostList(obj);
		// 查询虚拟机信息
		VMHostObj vmObj = new VMHostObj();
		vmObj.setVH_UUID(entityId);
		VMHostObj revmObj = vmHostService.queryByObj(vmObj);
		theForm.setMEMORY(revmObj.getVH_MEM());
		theForm.setCPU(revmObj.getVH_CPU());
		Double STORAGE = 0.0;
		DecimalFormat nf = new DecimalFormat("0.00");
		if (revmObj.getVH_STORAGE() != null && !revmObj.getVH_STORAGE().equals("")) {
			STORAGE = Double.valueOf(revmObj.getVH_STORAGE());
		}
		theForm.setSTORAGE(String.valueOf(nf.format((STORAGE / 1024 / 1024))));
		theForm.setOsType(revmObj.getVH_SYSTEM());
		theForm.setHostList(list);
		theForm.setNAME_ZH(name);
		List<TempletTreeObj> contents = findContents();
		theForm.setResultList(contents);
		request.setAttribute("name", name);
		request.setAttribute("type", type);
		request.setAttribute("entity_id", entityId);
		return "cloneToTemplet";
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
				TempletTreeObj reObj = (TempletTreeObj) templetTreeService.queryForTree(queryObj).get(0);
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
	 * @Title: cloneVirtualMacFirStep
	 * @Description: 克隆为模板
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @createtime Sep 18, 2012 1:35:01 PM
	 */
	@MethodLog(remark = "VmwManAction-cloneVirtualMacFirStep", type = 1, message = "克隆为模板")
	public String cloneVirtualMacFirStep() throws UnsupportedEncodingException {
		if (theForm == null) {
			theForm = new VmwManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String old_name = request.getParameter("vmName");
		String new_name = request.getParameter("name");
		new_name = URLDecoder.decode(new_name, "utf_8");
		String host_name = request.getParameter("hostName");
		String dsname = request.getParameter("dsname");
		String entity_id = request.getParameter("entity_id");
		String parent_id = request.getParameter("parent_id");
		String result = vmwManService.cloneVmwareTem(old_name, new_name, host_name, entity_id, parent_id, dsname);
		String json = "{\"result\":\"" + result + "\"}";

		/*
		 * 操作日志
		 */
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		int operResult = 0;
		operObj.setOPERTYPE(1);
		operObj.setMESSAGE("虚拟机：" + entity_id + "," + old_name + "克隆为模板：" + new_name);
		if (result.equals("1")) {
			operResult = 1;
		}
		operObj.setRESULT(operResult);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志

		try {
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(json);
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "oper_success";

	}

	/**
	 * 
	 * @Title: removeTemplet
	 * @Description: 删除vmware模板
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 18, 2012 1:35:01 PM
	 */
	@MethodLog(remark = "VmwManAction-removeTemplet", type = 2, message = "删除vmware模板")
	public String removeTemplet() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");// 节点Id
		String name = request.getParameter("name");
		String entityId = request.getParameter("entityId");
		EntityTreeObj obj = new EntityTreeObj();
		obj.setId(Integer.parseInt(id));
		obj.setName(name);
		obj.setEntityId(entityId);
		String result = vmwManService.removeTemplet(obj);
		String json = "{\"result\":" + result + "}";
		/*
		 * 操作日志
		 */
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		int operResult = 0;
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除模板" + entityId + "," + name);
		if (result.equals("1")) {
			operResult = 1;
		}
		operObj.setRESULT(operResult);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志

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
	 * @Title: moveToDateCenter
	 * @Description: 将主机移动到数据中心
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Sep 25, 2012 1:35:01 PM
	 */
	@MethodLog(remark = "VmwManAction-moveToDateCenter", type = 3, message = "将主机移动到数据中心")
	public String moveToDateCenter() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");// 节点Id
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String hostEntityId = request.getParameter("entityId");
		String targetId = request.getParameter("targetId");
		String dcName = request.getParameter("dcName");
		String clName = request.getParameter("clName");
		String dcCode = request.getParameter("dcCode");
		String clCode = request.getParameter("clCode");
		EntityTreeObj obj = new EntityTreeObj();
		obj.setId(Integer.parseInt(id));
		obj.setName(name);
		obj.setType(type);
		obj.setParentId(Integer.parseInt(targetId));
		obj.setEntityId(hostEntityId);
		String result = vmwManService.moveToDateCenter(dcCode, clCode, obj);
		String json = "{\"result\":\"" + result + "\"}";
		try {
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(json);
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * 操作日志
		 */
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		int operResult = 0;
		operObj.setOPERTYPE(3);
		operObj.setMESSAGE("将主机" + hostEntityId + "," + name + "移到数据中心下");
		if (result.equals("1")) {
			operResult = 1;
		}
		operObj.setRESULT(operResult);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return null;
	}

	/**
	 * 
	 * @Title: cloneVMToCluster
	 * @Description: 克隆虚拟机到集群
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Sep 25, 2012 1:35:01 PM
	 */
	@MethodLog(remark = "VmwManAction-cloneVMToCluster", type = 3, message = "克隆虚拟机到集群")
	public String cloneVMToCluster() {
		if (theForm == null) {
			theForm = new VmwManForm();
		}
		EntityTreeObj entityObj = new EntityTreeObj();
		entityObj.setType(TypeConstant.VMWARE_CLUSTER);
		List clusterList = entityTreeService.queryClusterList(entityObj);
		TbCloud2NetInfoObj netObj = new TbCloud2NetInfoObj();
		List netList = netService.queryForListByObj(netObj);
		VMHostObj vmHostObj = new VMHostObj();
		String entityId = request.getParameter("entityId");
		vmHostObj.setVH_UUID(entityId);
		vmHostObj = vmHostService.queryByObj(vmHostObj);
		if (netList != null && netList.size() > 0) {
			theForm.setNetList(netList);
		} else {
			theForm.setNetList(new ArrayList());
		}
		if (vmHostObj != null) {
			theForm.setCPU(vmHostObj.getVH_CPU());
			theForm.setMEMORY(vmHostObj.getVH_MEM());
			int dssize = Integer.parseInt(vmHostObj.getVH_STORAGE()) / 1024 / 1024;
			theForm.setSTORAGE(dssize + "");
		}
		theForm.setClusterList(clusterList);
		return "cloneVMToCluster";
	}

	/**
	 * 
	 * @Title: getDataStoreInfo
	 * @Description: 获取存储信息
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Sep 25, 2012 3:03:48 PM
	 */
	@MethodLog(remark = "VmwManAction-getDataStoreInfo", type = 1, message = "获取存储信息")
	public String getDataStoreInfo() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String dsId = request.getParameter("dsId");
		DataStoreInfoObj infoObj = new DataStoreInfoObj();
		infoObj.setID(Integer.parseInt(dsId));
		DataStoreInfoObj reobj = (DataStoreInfoObj) dataStoreService.queryForListByDateStoreInfoObj(infoObj).get(0);
		DecimalFormat nf = new DecimalFormat("0.00");
		long vmfsCapacity = Long.parseLong(reobj.getVMFS_CAPACITY());
		long freeSpace = Long.parseLong(reobj.getFREE_SPACE());
		Double capa = vmfsCapacity / 1024.0 / 1024.0 / 1024.0;
		Double free = freeSpace / 1024.0 / 1024.0 / 1024.0;
		reobj.setVMFS_CAPACITY(nf.format(capa));
		reobj.setFREE_SPACE(nf.format(free));
		String result = "{\"capacity\":\"" + reobj.getVMFS_CAPACITY() + "\"," + "\"freeSpace\":\""
				+ reobj.getFREE_SPACE() + "\"}";
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
	 * @Title: getClusterDRS
	 * @Description: 获取集群drs信息
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Sep 25, 2012 3:03:48 PM
	 */
	@MethodLog(remark = "VmwManAction-getClusterDRS", type = 1, message = "获取集群drs信息")
	public String getClusterDRS() {
		String clusterName = Struts2Utils.getParameter("clusterName");
		String result = null;
		try {
			String url = "/vmware/cluster/getHAState/[clName:" + clusterName + "]/";
			String getResult = InvokeUtil.invoke(url);
			Map ps = ParamParser.makeup(getResult);
			String drs = (String) ps.get("drs");
			if (drs.equals("true")) {
				result = "1";
			}
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			String json = "{\"result\":\"" + result + "\"}";
			p.print(json);
			p.close();
		} catch (Exception e) {
			PrintWriter p = null;
			try {
				p = Struts2Utils.getResponse().getWriter();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			String json = "{\"result\":\"" + e.getMessage() + "\"}";
			p.print(json);
			p.close();

		}
		return null;
	}

	/**
	 * 
	 * @Title: cloneVirtualToCluster
	 * @Description: 克隆虚拟机到集群
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Sep 25, 2012 3:03:48 PM
	 */
	@MethodLog(remark = "VmwManAction-cloneVirtualToCluster", type = 3, message = "克隆虚拟机到集群")
	public String cloneVirtualToCluster() {
		if (theForm == null) {
			theForm = new VmwManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String vmCode = request.getParameter("vmCode");
		String result = vmwManService.cloneVirtualToCluster(vmCode, theForm);
		String json = "{\"result\":\"" + result + "\"}";

		/*
		 * 操作日志
		 */
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		int operResult = 0;
		operObj.setOPERTYPE(1);
		operObj.setMESSAGE("从虚拟机" + vmCode + "," + "克隆虚拟机" + theForm.getNAME_EN() + ",克隆到集群" + theForm.getCLUSTER_ID());
		if (result.equals("1")) {
			operResult = 1;
		}
		operObj.setRESULT(operResult);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志

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
	 * @Title: DeployVirtualMachineData
	 * @Description: 选择集群，不选择主机从集群创建虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Sep 26, 2012 3:03:48 PM
	 */
	@MethodLog(remark = "VmwManAction-deployVirtualMachineData", type = 1, message = "选择集群，不选择主机从集群创建虚拟机")
	public String deployVirtualMachineData() {
		if (theForm == null) {
			theForm = new VmwManForm();
		}
		theForm.setOsType("1");
		HttpServletRequest request = Struts2Utils.getRequest();
		String type = request.getParameter("TYPE");
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String parent_id = request.getParameter("parent_id");
		String entityId = request.getParameter("entityId");// 集群Id
		/** 查询网络列表 */
		TbCloud2NetInfoObj netObj = new TbCloud2NetInfoObj();
		List netList = netService.queryForListByObj(netObj);
		ClusterObj clusterObj = new ClusterObj();
		clusterObj.setC_uuid(entityId);
		clusterObj = clusterService.queryByObj(clusterObj);
		/** 存储列表 */
		DataStoreObj dsObj = new DataStoreObj();
		if (entityId != null) {
			dsObj.setCLUSTER_ID(clusterObj.getId() + "");
		}
		List<DataStoreObj> dataList = dataStoreService.queryClusterds(dsObj);

		theForm.setNetList(netList);
		theForm.setResultList(dataList);
		request.setAttribute("id", id);
		request.setAttribute("type", type);// 主机类型
		request.setAttribute("name", name);
		request.setAttribute("parent_id", parent_id);
		return "deployVMData";
	}

	/**
	 * 
	 * @Title: getDSInfo
	 * @Description: 从数据库中查询存储的具体信息
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Sep 26, 2012 3:03:48 PM
	 */
	@MethodLog(remark = "VmwManAction-getDSInfo", type = 4, message = "从数据库中查询存储的具体信息")
	public DataStoreObj getDSInfo() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String dsname = request.getParameter("dsname");
		DataStoreObj obj = new DataStoreObj();
		obj.setNAME(dsname);
		DataStoreObj reobj = (DataStoreObj) dataStoreService.queryForListByObj(obj).get(0);
		DecimalFormat nf = new DecimalFormat("0.00");
		Double capa = Double.parseDouble(reobj.getCAPACITY());
		Double free = Double.parseDouble(reobj.getFREE_SPACE());
		reobj.setCAPACITY(nf.format(capa));
		reobj.setFREE_SPACE(nf.format(free));
		String storePer = nf.format((capa - free) / capa * 100) + "%";
		String result = "{\"capacity\":\"" + reobj.getCAPACITY() + "\"," + "\"freeSpace\":\"" + reobj.getFREE_SPACE()
				+ "\"," + "\"storePer\":\"" + storePer + "\"}";
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
	 * @Title: openconsole
	 * @Description: 打开控制台
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 26, 2012 3:03:48 PM
	 */
	public String openconsole() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String vmName = request.getParameter("name");
		String ip = CfgUtil.getString("vcenter.ip");
		request.setAttribute("vmName", vmName);
		request.setAttribute("ip", ip);
		return "openconsole";
	}

	/**
	 * 
	 * @Title: getticket
	 * @Description: 得到连接虚拟机的ticket
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 26, 2012 3:03:48 PM
	 */
	public String getticket() {
		String getTicketUrl = "/vmware/domain/getticket/";
		String ticket = InvokeUtil.invoke(getTicketUrl);
		String vmName = Struts2Utils.getParameter("vmName");// vmName为code，唯一标识
		/*
		 * String getvmIdUrl = "/vmware/domain/getVMID/[vmName:" + vmName +
		 * "]/"; String getvmIdResult = InvokeUtil.invoke(getvmIdUrl); Map ps =
		 * ParamParser.makeup(getvmIdResult); String code = (String)
		 * ps.get(ResponseCode.RESPONSE_CODE); String vmId = ""; if
		 * (code.equals(ResponseCode.SUCCESS)) { vmId = (String) ps.get("vmId"); }
		 */
		String json = "{\"ticket\":\"" + ticket + "\",\"vmId\":\"" + vmName + "\"}";
		try {
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(json);
			p.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * @Title: restartSysttem
	 * @Description: 重新启动虚拟机操作系统
	 * @param
	 * @author hehui
	 * @version 1.0
	 * @createtime Otc 17, 2012 3:03:48 PM
	 */
	@MethodLog(remark = "VmwManAction-restartSysttem", type = 3, message = "重新启动虚拟机操作系统")
	public String restartSysttem() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String name = request.getParameter("name");
		String entityId = request.getParameter("entityId");
		String param = "/vmware/domain/power/[vmName:" + entityId + "].[op:reboot]/";
		String migrateResult = InvokeUtil.invoke(param);
		Map ps = ParamParser.makeup(migrateResult);
		String result = (String) ps.get("responseCode");
		String json = "";
		if (result.equals("1")) {
			json = "{\"result\":\"" + result + "\"}";
		} else {
			String resMsg = (String) ps.get("responseRemark");
			json = "{\"result\":\"" + result + "\",\"resMsg\":\"" + resMsg + "\"}";
		}
		try {
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(json);
			p.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	// 关闭虚拟机操作系统
	@MethodLog(remark = "VmwManAction-closeSystem", type = 3, message = "关闭虚拟机操作系统")
	public String closeSystem() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String name = request.getParameter("name");
		String entityId = request.getParameter("entityId");
		String param = "/vmware/domain/power/[vmName:" + entityId + "].[op:shutdownGuest]/";
		String migrateResult = InvokeUtil.invoke(param);
		Map ps = ParamParser.makeup(migrateResult);
		String result = (String) ps.get("responseCode");
		String json = "";
		if (result.equals("1")) {
			json = "{\"result\":\"" + result + "\"}";
		} else {
			String resMsg = (String) ps.get("responseRemark");
			json = "{\"result\":\"" + result + "\",\"resMsg\":\"" + resMsg + "\"}";
		}
		try {
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(json);
			p.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * @Title: rebootHost
	 * @Description: 重启主机
	 * @param
	 * @author duangh
	 * @version 1.0
	 * @createtime Otc 17, 2012 3:03:48 PM
	 */
	@MethodLog(remark = "VmwManAction-rebootHost", type = 3, message = "重启主机")
	public String rebootHost() {
		String result = null;
		String reason = null;
		try {
			String name = Struts2Utils.getParameter("name");
			String hostName = Struts2Utils.getParameter("hostName");
			String url = "/vmware/host/power/[hostName:" + name + "].[op:reboot]/";
			String invokeResult = InvokeUtil.invoke(url);
			Map ps = ParamParser.makeup(invokeResult);
			String code = (String) ps.get(ResponseCode.RESPONSE_CODE);
			if (code.equals(ResponseCode.SUCCESS)) {
				result = "1";
			} else {
				result = "-1";
				reason = (String) ps.get(ResponseCode.RESPONSEREMARK);
			}
			String json = "{\"result\":\"" + result + "\",\"reason\":\"" + reason + "\"}";
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(json);
			p.close();
			/*
			 * 操作日志
			 */
			TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
			operObj.setREMARK("");
			int operResult = 0;
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("重启主机" + name + "," + hostName);
			if (result.equals("1")) {
				operResult = 1;
			}
			operObj.setRESULT(operResult);
			@SuppressWarnings("unused")
			int retOper = operationService.insertByObj(operObj);// 写操作日志
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * @Title: shutdownHost
	 * @Description: 关闭主机
	 * @param
	 * @author huojla
	 * @version 1.0
	 * @createtime Otc 17, 2012 3:03:48 PM
	 */
	@MethodLog(remark = "VmwManAction-shutdownHost", type = 3, message = "关闭主机")
	public String shutdownHost() {
		String result = null;
		String reason = null;
		try {
			String name = Struts2Utils.getParameter("name");
			String hostName = Struts2Utils.getParameter("hostName");
			String url = "/vmware/host/power/[hostName:" + name + "].[op:shutdown]/";
			String invokeResult = InvokeUtil.invoke(url);
			Map ps = ParamParser.makeup(invokeResult);
			String code = (String) ps.get(ResponseCode.RESPONSE_CODE);
			if (code.equals(ResponseCode.SUCCESS)) {
				result = "1";
			} else {
				result = "-1";
				reason = (String) ps.get(ResponseCode.RESPONSEREMARK);
			}
			String json = "{\"result\":\"" + result + "\",\"reason\":\"" + reason + "\"}";
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(json);
			p.close();
			/*
			 * 操作日志
			 */
			TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
			operObj.setREMARK("");
			int operResult = 0;
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("关闭主机" + name + "," + hostName);
			if (result.equals("1")) {
				operResult = 1;
			}
			operObj.setRESULT(operResult);
			@SuppressWarnings("unused")
			int retOper = operationService.insertByObj(operObj);// 写操作日志
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * @Title: addnic
	 * @Description: 虚拟机添加网卡
	 * @param
	 * @author duangh
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @createtime Otc 17, 2012 3:03:48 PM
	 */
	@MethodLog(remark = "VmwManAction-addnic", type = 1, message = "虚拟机添加网卡")
	public String addnic() throws UnsupportedEncodingException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String name = request.getParameter("name");
		name = URLDecoder.decode(name, "utf-8");
		String entityId = request.getParameter("entityId");
		String hostCode = request.getParameter("hostCode");
		VirtualSwitch vs = new VirtualSwitch();
		vs.setHostName(hostCode);
		List list = portGroupService.queryHostPortGroup(vs);
		List pgList = new ArrayList<PortGroup>();
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				NicRelationObj nicReObj = (NicRelationObj) obj;
				PortGroup pg = new PortGroup();
				pg.setPguuid(nicReObj.getFromUuid());
				pg = portGroupService.queryPortGroupById(pg);
				if (pg != null) {
					pgList.add(pg);
				}
			}
		}
		request.setAttribute("list", pgList);
		request.setAttribute("name", name);
		request.setAttribute("entityId", entityId);
		request.setAttribute("hostCode", hostCode);
		return "addnic";
	}

	/**
	 * 
	 * @Title: savenic
	 * @Description: 保存虚拟机添加的网卡
	 * @param
	 * @author duangh
	 * @version 1.0
	 * @createtime Otc 17, 2012 3:03:48 PM
	 */
	@MethodLog(remark = "VmwManAction-savenic", type = 1, message = "保存虚拟机添加的网卡")
	public String savenic() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String vmcode = request.getParameter("vmcode");
		String type = request.getParameter("type");
		String portgroup = request.getParameter("portgroup");
		String hostCode = request.getParameter("hostCode");
		String result = null;
		try {
			String url = "/vmware/domain/reconfig/[nic:nic].[vmName:" + vmcode + "].[op:Add].[value:" + portgroup
					+ "].[type:" + type + "]/";
			String invokeResult = InvokeUtil.invoke(url);
			Map ps = ParamParser.makeup(invokeResult);
			String responseCode = (String) ps.get(ResponseCode.RESPONSE_CODE);
			if (responseCode.equals(ResponseCode.SUCCESS)) {
				result = "1";
				try {
					NoticeUtil.getInstance().updateCMDBVM(vmcode, Operation.OPER_REL_MODI);
				} catch (Exception ex) {

				}
				// 从库中查询出当前虚拟机的虚拟网卡
				Map dsVnicMap = getVmVnic(vmcode);
				// 向库中插入虚拟网卡和关系
				datastoreAddVnic(dsVnicMap, hostCode, vmcode);

			} else {
				result = "-1";
			}
			String json = "{\"result\":\"" + result + "\"}";
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(json);
			p.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * @Title: getVmVnic
	 * @Description: 查询虚拟的虚拟网卡
	 * @param
	 * @return List<VirtualNicObj>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 3, 2013 4:55:11 PM
	 */
	@MethodLog(remark = "VmwManAction-getVmVnic", type = 4, message = "查询虚拟的虚拟网卡")
	public Map getVmVnic(String vmcode) {
		Map map = new HashMap<String, String>();
		NicRelationObj nicReObj = new NicRelationObj();
		nicReObj.setType("VNIC_VM");
		nicReObj.setToUuid(vmcode);
		List nicReLst = nicRelationService.queryForListByObj(nicReObj);
		if (nicReLst != null && nicReLst.size() > 0) {
			for (Object object : nicReLst) {
				NicRelationObj obj = (NicRelationObj) object;
				map.put(obj.getFromUuid(), obj.getFromUuid());
			}
		}
		return map;
	}

	/**
	 * 
	 * @Title: datastoreAddVnic
	 * @Description: 向库中插入虚拟网卡和关系
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 3, 2013 5:49:24 PM
	 */
	@MethodLog(remark = "VmwManAction-datastoreAddVnic", type = 1, message = "向库中插入虚拟网卡和关系")
	private void datastoreAddVnic(Map dsVnicMap, String hostCode, String vmCode) {
		// 向库中插入虚拟机网络相关的关系
		// this is wrong,please change in the future ----note by duangh
		String net_param = "/vmware/domain/synVCenterTemp/[TYPE:HOST].[CODE:" + hostCode + "]/";
		String net = InvokeUtil.invoke(net_param);
		List<JSONObject> netjo = JSONArray.toList(JSONArray.fromObject(net), JSONObject.class);
		Map<String, VirtualNicObj> vnicMap = new HashMap<String, VirtualNicObj>();
		List<NicRelationObj> vnic_vmLst = new ArrayList<NicRelationObj>();
		List<NicRelationObj> vnic_nicLst = new ArrayList<NicRelationObj>();
		List<NicRelationObj> vnic_pgLst = new ArrayList<NicRelationObj>();
		for (JSONObject jo : netjo) {
			if (jo.get("TYPE") != null) {
				if (jo.getString("TYPE").equals("VNIC")) {
					VirtualNicObj vNicObj = new VirtualNicObj();
					vNicObj.setName(jo.get("NAME") == null ? "" : jo.getString("NAME"));
					vNicObj.setIpAddress(jo.get("IPADDRESS") == null ? "" : jo.getString("IPADDRESS"));
					String nicUuid = jo.get("CODE") == null ? "" : jo.getString("CODE");
					vNicObj.setUuid(nicUuid);
					vnicMap.put(nicUuid, vNicObj);
				}
			} else if (jo.get("RELATIONSHIP") != null) {
				// 获取虚拟机网络关联关系
				NicRelationObj nicRelationObj = new NicRelationObj();
				if (jo.getString("RELATIONSHIP").equals("VNIC_VM")) {
					nicRelationObj.setType(jo.get("RELATIONSHIP") == null ? "" : jo.getString("RELATIONSHIP"));
					nicRelationObj.setToUuid(jo.get("TO_CODE") == null ? "" : jo.getString("TO_CODE"));
					nicRelationObj.setFromUuid(jo.get("FROM_CODE") == null ? "" : jo.getString("FROM_CODE"));
					vnic_vmLst.add(nicRelationObj);
				} else if (jo.getString("RELATIONSHIP").equals("VNIC_NIC")) {
					nicRelationObj.setType(jo.get("RELATIONSHIP") == null ? "" : jo.getString("RELATIONSHIP"));
					nicRelationObj.setToUuid(jo.get("TO_CODE") == null ? "" : jo.getString("TO_CODE"));
					nicRelationObj.setFromUuid(jo.get("FROM_CODE") == null ? "" : jo.getString("FROM_CODE"));
					vnic_nicLst.add(nicRelationObj);
				} else if (jo.getString("RELATIONSHIP").equals("VNIC_PORTGROUP")) {
					nicRelationObj.setType(jo.get("RELATIONSHIP") == null ? "" : jo.getString("RELATIONSHIP"));
					nicRelationObj.setToUuid(jo.get("TO_CODE") == null ? "" : jo.getString("TO_CODE"));
					nicRelationObj.setFromUuid(jo.get("FROM_CODE") == null ? "" : jo.getString("FROM_CODE"));
					vnic_pgLst.add(nicRelationObj);
				}
			}
		}
		// 筛选未保存的虚拟网卡
		List noSaveNicLst = new ArrayList();
		for (NicRelationObj nicRelationObj : vnic_vmLst) {
			if (nicRelationObj.getToUuid().equals(vmCode) && dsVnicMap.get(nicRelationObj.getFromUuid()) == null) {
				noSaveNicLst.add(vnicMap.get(nicRelationObj.getFromUuid()));
			}
		}
		// 向库中插入数据
		for (Object obj : noSaveNicLst) {
			VirtualNicObj virtualNicObj = (VirtualNicObj) obj;
			virtualNicService.insertByObj(virtualNicObj);
			for (NicRelationObj vnic_pgObj : vnic_pgLst) {
				if (vnic_pgObj.getFromUuid().equals(virtualNicObj.getUuid())) {
					nicRelationService.insertByObj(vnic_pgObj);
				}
			}
			for (NicRelationObj vnic_nicObj : vnic_nicLst) {
				if (vnic_nicObj.getFromUuid().equals(virtualNicObj.getUuid())) {
					nicRelationService.insertByObj(vnic_nicObj);
				}
			}
			for (NicRelationObj nicRelationObj : vnic_vmLst) {
				if (nicRelationObj.getFromUuid().equals(virtualNicObj.getUuid())) {
					nicRelationService.insertByObj(nicRelationObj);
				}
			}
		}

	}

	/**
	 * 
	 * @Title: cloneVMByTemPage
	 * @Description: 进入通过模板克隆虚拟机页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 17, 2013 1:57:58 PM
	 */
	public String cloneVMByTemPage() {
		if (theForm == null) {
			theForm = new VmwManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String host_id = request.getParameter("host_id");
		String host_code = request.getParameter("host_code");
		request.setAttribute("host_id", host_id);
		request.setAttribute("host_code", host_code);
		// 模板列表
		TemManObj tem = new TemManObj();
		tem.setType("1");// vmware模板
		List temList = temManService.queryForList(tem);
		theForm.setResultList(temList);
		// /** 查询网络列表 */
		TbCloud2NetInfoObj netObj = new TbCloud2NetInfoObj();
		List netList = netService.queryForListByObj(netObj);
		/** 存储列表 */
		DataStoreObj dsObj = new DataStoreObj();
		dsObj.setHOST_ID(host_code);
		List<DataStoreObj> dataList = dataStoreService.queryForListByObj(dsObj);
		theForm.setNetList(netList);
		theForm.setStorageList(dataList);
		return "clone_page";
	}

	/**
	 * 
	 * @Title: cloneVMByTem
	 * @Description: 克隆虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @createtime May 20, 2013 9:07:26 AM
	 */
	@MethodLog(remark = "VmwManAction-cloneVMByTem", type = 1, message = "克隆虚拟机")
	public String cloneVMByTem() throws UnsupportedEncodingException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String host_id = request.getParameter("host_id");
		String host_code = request.getParameter("host_code");
		String tem_code = request.getParameter("tem_code");
		String store_code = request.getParameter("store_code");
		String name = request.getParameter("name");
		name = URLDecoder.decode(name, "utf-8");
		String cpu = request.getParameter("cpu");
		String mem = request.getParameter("mem");
		String unit = request.getParameter("unit");
		String store = request.getParameter("store");
		String ip = request.getParameter("IP");
		Map map = new HashMap();
		map.put("host_id", host_id);
		map.put("host_code", host_code);
		map.put("tem_code", tem_code);
		map.put("store_code", store_code);
		map.put("name", name);
		map.put("cpu", cpu);
		map.put("mem", mem);
		map.put("unit", unit);
		map.put("store", store);
		map.put("ip", ip);
		String result = vmwManService.cloneVMByTem(map);
		if ("1".equals(result)) {
			result = "{\"responseCode\":\"1\"}";
		} else {
			result = "{\"responseCode\":\"-1\"}";
		}
		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(result);
		// out.close();
		PrintWriterOut.printWirter(response, result);
		return null;

	}

	/**
	 * 
	 * @Title: getStorage
	 * @Description: 获取不同主机所对应的存储列表(统一树通过虚拟机克隆虚拟机使用)
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 26, 2012 10:32:58 AM
	 */
	@MethodLog(remark = "VmwManAction-getStorage", type = 4, message = "获取不同主机所对应的存储列表")
	public String getHostStorageList() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String HOST_ID = request.getParameter("HOST_ID");
		StringBuffer buff = new StringBuffer("{");
		String info = "";
		if (HOST_ID != null && !"".equals(HOST_ID)) {
			DataStoreObj obj = new DataStoreObj();
			obj.setHOST_ID(HOST_ID);
			List<DataStoreObj> list = dataStoreService.queryForListByObj(obj);
			int j = 0;
			for (int i = 0; i < list.size(); i++) {
				DataStoreObj os = list.get(i);
				if (j != 0) {
					buff.append(",");
				}
				buff.append("'");
				buff.append(os.getStore_uuid());
				buff.append("':'");
				buff.append((String) os.getNAME());
				buff.append("'");
				j++;
			}
		}
		buff.append("}");
		info = buff.toString();
		// request.setAttribute("info", info);
		PrintWriterOut.printWirter(response, info);
		return "ajax";
	}

	public void setClusterService(ClusterService clusterService) {
		this.clusterService = clusterService;
	}

	public String getDataStoreMess() {
		String dsId = request.getParameter("dsId");
		DataStoreObj dataStoreObj = new DataStoreObj();
		dataStoreObj.setID(Integer.parseInt(dsId));
		dataStoreObj = (DataStoreObj) dataStoreService.queryForListByObj(dataStoreObj).get(0);
		DecimalFormat nf = new DecimalFormat("0.00");
		String result = "{\"capacity\":\"" + nf.format(Double.parseDouble(dataStoreObj.getCAPACITY())) + "\","
				+ "\"freeSpace\":\"" + nf.format(Double.parseDouble(dataStoreObj.getFREE_SPACE())) + "\"}";
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
	 * <p>虚拟机远程控制台下载</p>
	 *
	 */
	public String downloadVmrc(){
		return "success";
	}
	public InputStream getDownloadFile() throws FileNotFoundException{
		InputStream is = null;
		try {
			inputPath = PropertiesUtil.getValue("properties/annexPath.properties", "vmrc.path");
			if(inputPath!=null && inputPath.length()!=0){	
				File file = new File(inputPath);
				fileName = file.getName();
				is = new FileInputStream(file);
			}
		} catch (Exception e) {			
			logger.info("vmrc下载出错");
			e.printStackTrace();
		}
		return is;
	}

	/**
	 *
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 *
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 *
	 * @return the inputPath
	 */
	public String getInputPath() {
		return inputPath;
	}

	/**
	 *
	 * @param inputPath the inputPath to set
	 */
	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
	
}
