package com.sitech.basd.yicloud.web.kvm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.sitech.basd.common.ResponseCode;
import com.sitech.basd.sxcloud.cloud.domain.image.TbCloud2ImageInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.image.ImageService;
import com.sitech.basd.sxcloud.cloud.service.net.IpService;
import com.sitech.basd.sxcloud.cloud.service.net.NetService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.rsmu.config.Constant;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.cluster.ClusterObj;
import com.sitech.basd.yicloud.domain.datastore.DataStoreInfo;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.basd.yicloud.domain.entitytree.EntityConObj;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.domain.kvm.TbYicloudDeviceHealthObj;
import com.sitech.basd.yicloud.domain.kvm.TbYicloudOsTypeObj;
import com.sitech.basd.yicloud.domain.kvm.VMManagerObj;
import com.sitech.basd.yicloud.domain.nic.NicObj;
import com.sitech.basd.yicloud.domain.nic.NicRelationObj;
import com.sitech.basd.yicloud.domain.nic.VirtualNicObj;
import com.sitech.basd.yicloud.domain.portgroup.PortGroup;
import com.sitech.basd.yicloud.domain.switches.VirtualSwitch;
import com.sitech.basd.yicloud.domain.templettree.TempletTreeObj;
import com.sitech.basd.yicloud.domain.vmauthority.VmAuthorityObj;
import com.sitech.basd.yicloud.service.cluster.ClusterService;
import com.sitech.basd.yicloud.service.datastore.DataStoreService;
import com.sitech.basd.yicloud.service.entitytree.EntityTreeService;
import com.sitech.basd.yicloud.service.kvm.KvmManService;
import com.sitech.basd.yicloud.service.nic.NicRelationService;
import com.sitech.basd.yicloud.service.nic.NicService;
import com.sitech.basd.yicloud.service.nic.VirtualNicService;
import com.sitech.basd.yicloud.service.portgroup.PortGroupService;
import com.sitech.basd.yicloud.service.switches.VirtualSwitchService;
import com.sitech.basd.yicloud.service.templettree.TempletTreeService;
import com.sitech.basd.yicloud.service.vmauthority.VmAuthorityService;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.JSONUtil;
import com.sitech.basd.yicloud.util.JsonUtils;
import com.sitech.basd.yicloud.util.ParamParser;
import com.sitech.basd.yicloud.util.TypeConstant;
import com.sitech.basd.yicloud.web.kvm.form.KvmManForm;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.ws.Operation;
import com.sitech.ws.web.NoticeUtil;

/**
 * 
 * <p>
 * Title: KvmManagerAction
 * </p>
 * <p>
 * Description: 虚拟机管理Action
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
public class KvmManAction extends CRUDBaseAction {
	private KvmManService kvmManService;
	private EntityTreeService entityTreeService;
	private NetService netService;
	private IpService ipService;
	private KvmManForm theForm;
	private String ID;
	private ImageService imageService;
	private HostInfoService hostInfoService;
	private DataStoreService dataStoreService;
	private ClusterService clusterService;
	private VirtualSwitchService virtualSwitchService;
	private PortGroupService portGroupService;
	private VMHostService vmHostService;
	private TempletTreeService templetTreeService;
	@Autowired
	private NicService nicService;
	@Autowired
	private VirtualNicService virtualNicService;
	@Autowired
	private NicRelationService nicRelationService;
	@Autowired
	private VmAuthorityService vmAuthorityService;

	public void setVmHostService(VMHostService vmHostService) {
		this.vmHostService = vmHostService;
	}

	public void setTempletTreeService(TempletTreeService templetTreeService) {
		this.templetTreeService = templetTreeService;
	}

	public void setPortGroupService(PortGroupService portGroupService) {
		this.portGroupService = portGroupService;
	}

	public void setKvmManService(KvmManService kvmManService) {
		this.kvmManService = kvmManService;
	}

	public void setEntityTreeService(EntityTreeService entityTreeService) {
		this.entityTreeService = entityTreeService;
	}

	public void setDataStoreService(DataStoreService dataStoreService) {
		this.dataStoreService = dataStoreService;
	}

	public void setVirtualSwitchService(VirtualSwitchService virtualSwitchService) {
		this.virtualSwitchService = virtualSwitchService;
	}

	public void setClusterService(ClusterService clusterService) {
		this.clusterService = clusterService;
	}

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public KvmManForm getTheForm() {
		return theForm;
	}

	public void setTheForm(KvmManForm theForm) {
		this.theForm = theForm;
	}

	public void setNetService(NetService netService) {
		this.netService = netService;
	}

	public void setIpService(IpService ipService) {
		this.ipService = ipService;
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
	public String putVirtualStat() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		int hostTreeId = Integer.parseInt(request.getParameter("hostId"));// 获取主机的节点ID
		EntityTreeObj en = new EntityTreeObj();
		en.setId(hostTreeId);
		en = entityTreeService.queryTreeNode(en);
		int hostId = Integer.parseInt(en.getEntityId());
		String ENTITY_ID = request.getParameter("ENTITY_ID");// 虚拟机实体ID
		String state = request.getParameter("state");
		String ID = request.getParameter("ID");// 虚拟机节点ID
		EntityTreeObj eo = new EntityTreeObj();
		eo.setId(Integer.parseInt(ID));
		VMManagerObj obj = new VMManagerObj();
		obj.setID(Integer.parseInt(ENTITY_ID));// 获取虚拟机的实体ID
		VMManagerObj vObj = kvmManService.queryByObj(obj);
		String result = kvmManService.putVirtualStat(hostId, vObj.getNAME_EN(), state);
		if ("null".equals(result) && state.equals("destroy")) {
			int ret = entityTreeService.delTreeNode(eo);
			int ret1 = kvmManService.deleteByObj(obj);
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
		VMManagerObj obj = new VMManagerObj();
		obj.setNAME_EN(NAME_EN);
		List list = kvmManService.queryForListByObj(obj);
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
		// obj.setNAME_EN(NAME_EN);
		// List list = kvmManService.queryForListByObj(obj);
		//
		// if (list != null && list.size() > 0) {
		// result = null;
		// }
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
	 * @Title: lookupImage
	 * @Description: 查看虚拟镜像信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 21, 2012 11:36:25 AM
	 */
	public String lookupImage() throws BaseException {
		if (theForm == null) {
			theForm = new KvmManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String oper = request.getParameter("oper");
		String ENTITY_ID = request.getParameter("ENTITY_ID");
		String TYPE = request.getParameter("TYPE");

		/** 查询网络列表 */
		TbCloud2NetInfoObj netObj = new TbCloud2NetInfoObj();
		List netList = netService.queryForListByObj(netObj);
		theForm.setNetList(netList);
		request.setAttribute("oper", oper);
		request.setAttribute("TYPE", TYPE);
		if (oper.equals("2")) {
			theForm.setCURRENTCPU("3");
			theForm.setCURRENTMEMORY("1024");
			return "lookupimg";
		}
		TbCloud2ImageInfoObj tObj = new TbCloud2ImageInfoObj();
		TbYicloudOsTypeObj to = new TbYicloudOsTypeObj();
		if (TYPE.equals(TypeConstant.KVM_IMAGE)) {
			tObj.setIM_TYPE("KVM");

		} else if (TYPE.equals(TypeConstant.VMWARE_IMAGE)) {
			tObj.setIM_TYPE("VMware");
		}

		tObj.setIM_ID(ENTITY_ID);
		TbCloud2ImageInfoObj tbObj = kvmManService.queryByImageObj(tObj);
		to.setID(tbObj.getOS_ID());
		TbYicloudOsTypeObj tyObj = kvmManService.queryDefaultConfig(to);
		theForm.setIM_NAME(tbObj.getIM_NAME());
		theForm.setIM_DESC(tbObj.getIM_DESC());
		theForm.setIM_STATE(tbObj.getIM_STATE());
		theForm.setIM_VERSION(tbObj.getIM_VERSION());
		theForm.setIM_TYPE(tbObj.getIM_TYPE());
		theForm.setCURRENTCPU(tyObj.getCPU());
		theForm.setCURRENTMEMORY(tyObj.getMEM());
		theForm.setSTORAGE(tyObj.getSTORAGE());
		request.setAttribute("ENTITY_ID", ENTITY_ID);
		// 查询宿主机相关信息

		return "lookupimg";
	}

	/**
	 * 
	 * @Title: deployImage
	 * @Description: 部署虚拟镜像
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 21, 2012 11:32:12 AM
	 */
	public String deployVirtualMachine() throws BaseException {
		if (theForm == null) {
			theForm = new KvmManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String TYPE = request.getParameter("TYPE");
		String name_en = request.getParameter("name_en");
		String cpu = request.getParameter("cpu");
		String currentCpu = request.getParameter("currentcpu");
		String memory = request.getParameter("memory");
		String currentMemory = request.getParameter("currentmemory");
		String ip = request.getParameter("ip");
		String unit = request.getParameter("unit");
		VMManagerObj obj = new VMManagerObj();
		String ENTITY_ID = request.getParameter("ENTITY_ID");// tree节点ID
		obj.setCPU(cpu);
		obj.setCURRENTCPU(currentCpu);
		// 判断内存单位是MB还是GB
		obj.setMEMORY(String.valueOf(Long.parseLong(memory.trim()) * 1024));
		if (unit.equals("1")) {
			obj.setCURRENTMEMORY(String.valueOf(Long.parseLong(currentMemory.trim()) * 1024));
		} else if (unit.equals("2")) {
			obj.setCURRENTMEMORY(String.valueOf(Long.parseLong(currentMemory.trim()) * 1024 * 1024));
		}
		/** 虚拟机英文名称(唯一标示) */
		obj.setNAME_EN(name_en);
		obj.setIP(ip);

		String result = "";

		String IM_ID = ENTITY_ID;// 虚拟镜像ID
		EntityTreeObj e = new EntityTreeObj();
		e.setEntityId(ENTITY_ID);
		e = entityTreeService.queryTreeNode(e);
		int parent_id = e.getParentId();// 获取主机在treeNOde中的Id
		// 查询主机的ID
		EntityTreeObj en = new EntityTreeObj();
		en.setId(parent_id);
		en = entityTreeService.queryTreeNode(en);
		int host_Id = Integer.parseInt(en.getEntityId());// 获取主机的实体ID
		result = kvmManService.createVirtual(host_Id, IM_ID, obj);// 创建KVM虚拟机
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		/*
		 * if (!result.contains("error")) {
		 * kvmManService.ftpUploadDomainInfo(request); }
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
	 * @Title: checkKVMVirtualInfo
	 * @Description: 查看虚拟机信息(需要确定从库里查还是从接口)
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 21, 2012 5:08:46 PM
	 */
	public String checkKVMVirtualInfo() throws BaseException {
		if (theForm == null) {
			theForm = new KvmManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();

		String hostId = request.getParameter("hostId");// 虚拟机所在主机的节点ID
		String oper = request.getParameter("oper");
		String ENTITY_ID = request.getParameter("ENTITY_ID");// 虚拟机的节点ID
		EntityTreeObj e = new EntityTreeObj();
		// 获取主机节点所对应的实体ID
		e.setId(Integer.parseInt(hostId));
		e = entityTreeService.queryTreeNode(e);
		request.setAttribute("oper", oper);
		VMManagerObj hostObj = new VMManagerObj();
		String host_id = e.getEntityId();// 主机节点的实体ID
		hostObj.setID(Integer.parseInt(host_id));
		hostObj = kvmManService.queryByObj(hostObj);
		theForm.setHostIP(hostObj.getIP());
		// 获取虚拟机所对应的实体ID
		VMManagerObj obj = new VMManagerObj();
		obj.setID(Integer.parseInt(ENTITY_ID));
		VMManagerObj vObj = kvmManService.queryByObj(obj);
		String hostIP = vObj.getIP();
		String NAME_EN = vObj.getNAME_EN();
		theForm.setNAME_ZH(vObj.getNAME_ZH());
		theForm.setNAME_EN(NAME_EN);
		theForm.setIP(hostIP);
		String result = kvmManService.getVirtualInfo(host_id, vObj.getNAME_EN());
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
	@SuppressWarnings("unchecked")
	public String virtual_state() throws BaseException, IOException {
		if (theForm == null) {
			theForm = new KvmManForm();
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
		List<VMManagerObj> list = kvmManService.queryForListByObj(obj);
		for (VMManagerObj d : list) {
			String NAME_EN = d.getNAME_EN();
			int ENTITY_ID = d.getID();
			EntityTreeObj ent = new EntityTreeObj();
			ent.setEntityId(String.valueOf(ENTITY_ID));
			ent = entityTreeService.queryTreeNode(ent);
			d.setID(ent.getId());
			String s = kvmManService.getVirtualInfo(entityId, NAME_EN);
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
	 * @Title: listHostInfo
	 * @Description: 通过IP获取对应主机信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 15, 2012 11:37:51 AM
	 */
	public String listMacInfo() throws BaseException, IOException {
		if (theForm == null) {
			theForm = new KvmManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		VMManagerObj obj = new VMManagerObj();
		String IP = request.getParameter("IP");
		obj.setIP(IP);
		List hostList = kvmManService.queryForListByObj(obj);
		theForm.setResultList(hostList);
		return "listmac";

	}

	/**
	 * 
	 * @Title: adjustKVMVirtualInfo
	 * @Description: 调整虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime May 16, 2012 2:04:02 PM
	 */
	public String adjustKVMVirtualInfo() throws BaseException, IOException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String cpu = request.getParameter("cpu");
		String memory = request.getParameter("memory");
		String hostIP = request.getParameter("hostIP");
		String NAME_EN = request.getParameter("NAME_EN");
		VMManagerObj obj = new VMManagerObj();
		obj.setCPU(cpu);
		obj.setMEMORY(memory);
		obj.setNAME_EN(NAME_EN);
		// 传入主机IP
		obj.setIP(hostIP);
		String result = kvmManService.adjustKVMVirtualInfo(obj);
		JSONObject jo = JSONObject.fromObject(result);
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
	public String listHealthState() throws BaseException, IOException {
		TbYicloudDeviceHealthObj obj = new TbYicloudDeviceHealthObj();
		if (theForm == null) {
			theForm = new KvmManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String ID = request.getParameter("ID");
		obj.setDEVICE_ID(ID);
		VMManagerObj vObj = new VMManagerObj();
		vObj.setID(Integer.parseInt(ID));
		List<TbYicloudDeviceHealthObj> healthList = kvmManService.queryHealthStateList(obj);
		vObj = kvmManService.queryByObj(vObj);
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
	 * @Title: addCluster
	 * @Description: 增加集群
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 10:57:45 AM
	 */
	/*
	 * public String addCluster() { if (theForm == null) { theForm = new
	 * KvmManForm(); } String ID = Struts2Utils.getParameter("id"); String TYPE
	 * = Struts2Utils.getParameter("TYPE"); HttpServletRequest request =
	 * Struts2Utils.getRequest(); request.setAttribute("ID", ID);
	 * request.setAttribute("TYPE", TYPE); return "add_cluster"; }
	 */

	/**
	 * 
	 * @Title: saveCluster
	 * @Description: 保存新建集群
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 2:45:58 PM
	 */
	/*
	 * public String saveCluster() { String ID =
	 * Struts2Utils.getParameter("ID"); String TYPE =
	 * Struts2Utils.getParameter("TYPE"); EntityTreeObj obj = new
	 * EntityTreeObj(); obj.setName(theForm.getNAME());
	 * obj.setParentId(Integer.parseInt(ID)); String cluster_type =
	 * theForm.getCLUSTER_TYPE(); if (cluster_type.equals("1")) {
	 * obj.setType(TypeConstant.VMWARE_CLUSTER); } else {
	 * obj.setType(TypeConstant.KVM_CLUSTER); } int ret =
	 * entityTreeService.insertTreeNode(obj); return "save_cluster"; }
	 */

	/**
	 * 
	 * @Title: addHost
	 * @Description: 添加主机
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 18, 2012 11:42:00 AM
	 */
	public String addHost() throws BaseException {
		if (theForm == null) {
			theForm = new KvmManForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String ID = request.getParameter("ID");
		String TYPE = request.getParameter("TYPE");
		String name = request.getParameter("name");// 集群名称
		String entityId = request.getParameter("entityId");// 集群节点entityId
		String clId = request.getParameter("clId");
		String pool_uuid = request.getParameter("pool_uuid");
		try {
			name = URLDecoder.decode(name, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String dcName = request.getParameter("dcName");// 数据中心名称
		/*
		 * EntityTreeObj obj = new EntityTreeObj();
		 * obj.setId(Integer.parseInt(ID)); obj =
		 * entityTreeService.queryTreeNode(obj); theForm.setNAME(obj.getName());
		 */
		request.setAttribute("ID", ID);
		request.setAttribute("TYPE", TYPE);
		request.setAttribute("clName", name);
		request.setAttribute("dcName", dcName);
		request.setAttribute("clId", clId);
		request.setAttribute("pool_uuid", pool_uuid);
		request.setAttribute("entityId", entityId);
		return "add_host";
	}

	/**
	 * 
	 * @Title: saveHost
	 * @Description: 保存添加的主机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 18, 2012 9:19:57 AM
	 */
	public String saveHost() throws Exception {
		// kvm集群类型是21，vm集群类型是3
		HttpServletRequest request = Struts2Utils.getRequest();
		String ID = request.getParameter("ID");
		String TYPE = request.getParameter("TYPE");
		String hostName = request.getParameter("ip");
		String hostUser = request.getParameter("username");
		String hostPass = request.getParameter("password");
		String dcName = request.getParameter("dcName");
		String clName = request.getParameter("clName");
		String clId = request.getParameter("clId");
		String entityId = request.getParameter("entityId");// 集群节点entityId,即集群code,唯一标识
		String result = "-1";
		int dcId = 0;
		try {
			if (TYPE.equals("3")) {
				hostPass = URLEncoder.encode(hostPass, "utf-8");
				String url = "/vmware/cluster/addHost/[dcName:" + dcName + "].[clName:" + entityId
						+ "].[hostName:" + hostName + "].[hostUser:" + hostUser + "].[hostPass:"
						+ hostPass + "]/";
				String addResult = InvokeUtil.invoke(url);
				Map ps = ParamParser.makeup(addResult);
				String responsecode = (String) ps.get(ResponseCode.RESPONSE_CODE);
				if (responsecode.equals(ResponseCode.SUCCESS)) {// 调用接口添加主机成功
					String code = (String) ps.get(ResponseCode.CODE);// 唯一标识
					/** 通知 */
					try {
						NoticeUtil.getInstance().updateCMDBCluster(entityId,
								Operation.OPER_REL_MODI);// cmdb
					} catch (Exception e) {

					}
					try {
						NoticeUtil.getInstance().addHost(code);
					} catch (Exception ex) {

					}

					String getHostInfoUrl = "/vmware/host/getHostInfo/[hostName:" + code + "]/";
					String getHostInfoResult = InvokeUtil.invoke(getHostInfoUrl);
					TbCloud2HostInfoObj infoObj = (TbCloud2HostInfoObj) JsonUtils.jsonToBean(
							getHostInfoResult, TbCloud2HostInfoObj.class);
					int hostId = hostInfoService.getIdSequence();
					infoObj.setId(hostId);
					infoObj.setEq_id(String.valueOf(hostId));
					if (infoObj.getSTATUS() != null && infoObj.getSTATUS().equals("connected")) {
						infoObj.setSTATUS("1");
					} else {
						infoObj.setSTATUS("0");
					}
					infoObj.setH_uuid(code);
					infoObj.setHasvertual("4");// 4为vmware虚拟化类型
					infoObj.setEq_type("2");// 2为刀片
					int intId = hostInfoService.insertByObj(infoObj);
					if (intId != -1) {
						intId = hostId;
					}
					// 查询主机的存储信息
					String getDsUrl = "/vmware/storage/collDatastoreInfo/[hostname:" + code + "]/";
					String getDsResult = InvokeUtil.invoke(getDsUrl);
					HashMap<String, DataStoreInfo> dsmap = JSONUtil.fromJSON(getDsResult,
							new JSONUtil.TypeReference<HashMap<String, DataStoreInfo>>() {
							});
					Collection<DataStoreInfo> c = dsmap.values();
					ClusterObj cobj = new ClusterObj();
					int tempDcId = 0;
					int tempClId = 0;
					for (Iterator<DataStoreInfo> it = c.iterator(); it.hasNext();) {
						DataStoreInfo ds = it.next();
						DataStoreObj dsObj = new DataStoreObj();
						dsObj.setNAME(ds.getName());
						dsObj.setFREE_SPACE(ds.getFreeSpace());
						dsObj.setSTORAGE_URL(ds.getStorageUrl());
						dsObj.setHOST_ID(code);
						dsObj.setCAPACITY(ds.getCapacity());
						dsObj.setTYPE(ds.getType());
						dsObj.setStore_uuid(ds.getCode());
						// 查询数据中心Id，暂时没有数据中心表，存入entitytree的Id
						EntityTreeObj eObj = new EntityTreeObj();
						eObj.setName(dcName);
						eObj.setType(TypeConstant.VMWARE_DATACENTER);
						eObj = entityTreeService.queryTreeNode(eObj);
						tempDcId = eObj.getId();
						// 查询集群id
						cobj.setC_uuid(entityId);
						cobj = clusterService.queryByObj(cobj);
						dsObj.setDATACENTER_ID(eObj.getId() + "");
						dcId = eObj.getId();
						dsObj.setCLUSTER_ID(cobj.getId() + "");
						tempClId = cobj.getId();
						int dsret = dataStoreService.insertDatastore(dsObj);// 插入存储信息表
					}

					// /** 保存虚拟交换机和端口组信息 */
					// // 端口组列表
					// String portgroupUrl =
					// "/vmware/network/getportgroup/[hostName:" + code + "]/";
					// String getResult = InvokeUtil.invoke(portgroupUrl);
					// List<PortGroup> portGroupList = new
					// ArrayList<PortGroup>();
					// if (getResult != null) {
					// portGroupList =
					// JSONArray.toList(JSONArray.fromObject(getResult),
					// PortGroup.class);
					// }
					// // 虚拟交换机列表
					// String vswitchUrl =
					// "/vmware/network/getvirtualswitch/[hostName:" + code +
					// "]/";
					// String getvSwitch = InvokeUtil.invoke(vswitchUrl);
					// if (getvSwitch != null) {
					// int ret = -1;
					// List<VirtualSwitch> switchList = new
					// ArrayList<VirtualSwitch>();
					// switchList =
					// JSONArray.toList(JSONArray.fromObject(getvSwitch),
					// VirtualSwitch.class);
					// for (VirtualSwitch vs : switchList) {
					// ret = virtualSwitchService.insertByObj(vs);
					// if (ret != -1) {
					// for (PortGroup pg : portGroupList) {
					// if (pg.getVswitchName().equals(vs.getName())) {
					// PortGroup obj = new PortGroup();
					// obj.setVswitchId(ret);
					// obj.setName(pg.getName());
					// obj.setType(pg.getType());
					// obj.setVswitchName(pg.getVswitchName());
					// portGroupService.insertByObj(false, hostName, obj);
					// }
					// }
					// }
					// }
					// }

					/** 查询主机下的网络，包括主机、虚拟机交换机、物理网卡、虚拟机网卡、虚拟机、端口组之间的关系 */
					// this is wrong ,please change in the future ----note by
					// duangh
					String net_param = "/vmware/domain/synVCenterTemp/[TYPE:HOST].[CODE:" + code
							+ "]/";
					String net = InvokeUtil.invoke(net_param);
					List<JSONObject> netjo = JSONArray.toList(JSONArray.fromObject(net),
							JSONObject.class);
					for (JSONObject jo : netjo) {
						if (jo.get("TYPE") != null) {
							if (jo.getString("TYPE").equals("PORTGROUP")) {
								PortGroup obj = new PortGroup();
								obj.setName(jo.get("NAME") == null ? "" : jo.getString("NAME"));
								obj.setType("");
								obj.setVlanId(jo.get("VLAN") == null ? 0 : jo.getInt("VLAN"));
								obj.setPguuid(jo.get("CODE") == null ? "" : jo.getString("CODE"));
								portGroupService.insertByObj(false, hostName, obj);
							} else if (jo.getString("TYPE").equals("VSS")) {
								VirtualSwitch vs = new VirtualSwitch();
								vs.setName(jo.get("NAME") == null ? "" : jo.getString("NAME"));
								vs.setNumPorts(jo.get("NUMPORTS") == null ? 0 : jo
										.getInt("NUMPORTS"));
								vs.setNumPortsAvailable(jo.get("NUMPORTSAVAILABLE") == null ? 0
										: jo.getInt("NUMPORTSAVAILABLE"));
								vs.setVssuuid(jo.get("CODE") == null ? "" : jo.getString("CODE"));
								vs.setHostName(hostName);
								virtualSwitchService.insertByObj(vs);
							} else if (jo.getString("TYPE").equals("NIC")) {
								NicObj nicObj = new NicObj();
								nicObj.setName(jo.get("NAME") == null ? "" : jo.getString("NAME"));
								nicObj.setLinkduplex(jo.get("LINKDUPLEX") == null ? "" : jo
										.getString("LINKDUPLEX"));
								nicObj.setLinkspeed(jo.get("LINKSPEED") == null ? 0 : jo
										.getInt("LINKSPEED"));
								nicObj.setStatus(jo.get("STATUS") == null ? "" : jo
										.getString("STATUS"));
								nicObj.setUuid(jo.get("CODE") == null ? "" : jo.getString("CODE"));
								nicService.insertByObj(nicObj);
							} else if (jo.getString("TYPE").equals("VNIC")) {
								VirtualNicObj vNicObj = new VirtualNicObj();
								vNicObj.setName(jo.get("NAME") == null ? "" : jo.getString("NAME"));
								vNicObj.setIpAddress(jo.get("IPADDRESS") == null ? "" : jo
										.getString("IPADDRESS"));
								vNicObj.setUuid(jo.get("CODE") == null ? "" : jo.getString("CODE"));
								virtualNicService.insertByObj(vNicObj);
							}
						} else if (jo.get("RELATIONSHIP") != null) {
							// 暂只取主机和网卡、虚拟网卡和虚拟机、端口组和虚拟机、网卡和虚拟交换机、虚拟交换机和主机的关系
							if (jo.getString("RELATIONSHIP").equals("NIC_HOST")
									|| jo.getString("RELATIONSHIP").equals("VNIC_VM")
									|| jo.getString("RELATIONSHIP").equals("PORTGROUP_VM")
									|| jo.getString("RELATIONSHIP").equals("NIC_VSS")
									|| jo.getString("RELATIONSHIP").equals("VSS_HOST")
									|| jo.getString("RELATIONSHIP").equals("PORTGROUP_VSS")
									|| jo.getString("RELATIONSHIP").equals("VNIC_NIC")
									|| jo.getString("RELATIONSHIP").equals("VNIC_PORTGROUP")) {
								NicRelationObj nicRelationObj = new NicRelationObj();
								nicRelationObj.setType(jo.get("RELATIONSHIP") == null ? "" : jo
										.getString("RELATIONSHIP"));
								nicRelationObj.setToUuid(jo.get("TO_CODE") == null ? "" : jo
										.getString("TO_CODE"));
								nicRelationObj.setFromUuid(jo.get("FROM_CODE") == null ? "" : jo
										.getString("FROM_CODE"));
								nicRelationService.insertByObj(nicRelationObj);
							}
						}
					}

					EntityTreeObj obj = new EntityTreeObj();
					obj.setEntityId(code);
					obj.setName(hostName);
					obj.setParentId(Integer.parseInt(ID));
					obj.setType(TypeConstant.VMWARE_HOST);
					int ret = entityTreeService.insertTreeNode(obj);
					// Tree-Con表
					EntityConObj hostCObj = new EntityConObj();
					hostCObj.setDataCenterId(dcId);
					hostCObj.setClusterId(cobj.getId());
					hostCObj.setName(hostName);
					hostCObj.setType(1);
					hostCObj.setEntityId(code);
					entityTreeService.insertConObj(hostCObj);

					// 插入权限
					VmAuthorityObj authObj = new VmAuthorityObj();
					HttpSession session = Struts2Utils.getRequest().getSession();
					authObj.setUSERID(Integer.parseInt(session.getAttribute("id").toString()));
					authObj.setENTITY_NAME(hostName);
					authObj.setENTITY_ID(code);
					authObj.setTYPE(TypeConstant.VMWARE_HOST);
					authObj.setOPERAUTHORITY("0");
					vmAuthorityService.insertByObj(authObj);

					String vm_param = "/vmware/domain/synDomainData/[hostName:" + code + "]/";
					String vm = InvokeUtil.invoke(vm_param);
					List<VMHostObj> vmList = (List<VMHostObj>) JsonUtils.jsonToList(vm,
							VMHostObj.class);
					for (VMHostObj vmObj : vmList) {
						String vmName = vmObj.getVH_NAME();
						/** 保存虚拟机数据 */
						// 主机表
						int vmId = vmHostService.queryVhostIdSequence();
						vmObj.setID(vmId);
						vmObj.setVH_ID(createNewVhostId2(String.valueOf(hostId)));
						if (vmObj.getVH_TYPE().equals("5")) {// 虚拟机模板
							vmObj.setVH_DESC("cpu:" + vmObj.getVH_CPU() + ";内存:"
									+ vmObj.getVH_MEM() + "(MB),存储:" + vmObj.getVH_STORAGE()
									+ "(KB)");
						}
						vmObj.setEQ_ID(String.valueOf(hostId));
						vmHostService.insertByVMhostObj(vmObj);

						// 模板树表
						if (vmObj.getVH_TYPE().equals("5")) {
							TempletTreeObj temObj = new TempletTreeObj();
							temObj.setParent_id(-1);
							temObj.setName(vmObj.getVH_NAME());
							temObj.setType(2);
							temObj.setTemplet_id(String.valueOf(vmId));
							templetTreeService.creatMenu(temObj);
						}

						// Tree表
						EntityTreeObj vmTreeObj = new EntityTreeObj();
						if (vmObj.getVH_TYPE().equals("5")) {
							vmTreeObj.setType("23");
						} else if (vmObj.getVH_TYPE().equals("1")) {
							vmTreeObj.setType("0");
						}
						vmTreeObj.setParentId(ret);
						vmTreeObj.setName(vmName);
						vmTreeObj.setEntityId(vmObj.getVH_UUID());
						int vmInsert = entityTreeService.insertTreeNode(vmTreeObj);
						vmTreeObj = entityTreeService.queryTreeNode(vmTreeObj);
						// Tree-Con表
						EntityConObj vmCObj = new EntityConObj();
						vmCObj.setDataCenterId(tempDcId);
						vmCObj.setClusterId(tempClId);
						vmCObj.setName(vmName);
						if (vmObj.getVH_TYPE().equals("5")) {
							vmCObj.setType(23);
						} else if (vmObj.getVH_TYPE().equals("1")) {
							vmCObj.setType(0);
						}
						vmCObj.setEntityId(vmObj.getVH_UUID());
						entityTreeService.insertConObj(vmCObj);
						/** 保存虚拟机数据 */

						// 插入权限
						VmAuthorityObj vmAuthObj = new VmAuthorityObj();
						vmAuthObj
								.setUSERID(Integer.parseInt(session.getAttribute("id").toString()));
						vmAuthObj.setENTITY_NAME(vmName);
						vmAuthObj.setENTITY_ID(vmObj.getVH_UUID());
						vmAuthObj.setTYPE(TypeConstant.VMWARE_VM);
						vmAuthObj.setOPERAUTHORITY("0");
						vmAuthorityService.insertByObj(vmAuthObj);
					}
					result = "1";
				}
			}
		} catch (Exception e) {
			result = "-1";
		}
		String json = "{\"result\":" + result + "}";
		PrintWriter p = Struts2Utils.getResponse().getWriter();
		p.print(json);
		p.close();
		return null;
		// EntityTreeObj obj = new EntityTreeObj();
		// VMManagerObj vObj = new VMManagerObj();
		// obj.setName(theForm.getNAME_ZH());
		// vObj.setNAME_ZH(theForm.getNAME_ZH());
		// vObj.setIP(theForm.getIP());
		// vObj.setTYPE("3");
		// vObj.setUSERNAME(theForm.getUSERNAME());
		// theForm.setPASSWORD(theForm.getPASSWORD());
		// if (TYPE.equals(TypeConstant.KVM_CLUSTER)) {
		// obj.setType(TypeConstant.KVM_HOST);
		// } else if (TYPE.equals(TypeConstant.VMWARE_CLUSTER)) {
		// obj.setType(TypeConstant.VMWARE_HOST);
		// } else if (TYPE.equals(TypeConstant.XEN_CLUSTER)) {
		// obj.setType(TypeConstant.XEN_HOST);
		// }
		// obj.setParentId(Integer.parseInt(ID));
		// setID(ID);
		// String DEVICE_ID = kvmManService.getIdSequence();
		// obj.setEntityId(DEVICE_ID);
		// vObj.setID(Integer.parseInt(DEVICE_ID));
		// int rets = kvmManService.insertByObj(vObj);
		// return "save_host";
	}

	/**
	 * 
	 * @Title: createNewVhostId2
	 * @Description: 创建虚拟机ID
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 28, 2012 3:24:59 PM
	 */
	public String createNewVhostId2(String eqId) {
		String vhostId = null;
		if (null != eqId && !"".equals(eqId)) {
			int seq = vmHostService.queryVhostIdSequence();
			NumberFormat formatter = NumberFormat.getNumberInstance(); // 设置数据格式
			formatter.setMinimumIntegerDigits(3); // 设置最小长度
			formatter.setMaximumIntegerDigits(3); // 设置最大长度
			vhostId = eqId + "_" + formatter.format(seq);
		}
		return vhostId;
	}
}
