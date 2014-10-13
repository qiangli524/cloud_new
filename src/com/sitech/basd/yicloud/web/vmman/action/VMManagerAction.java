package com.sitech.basd.yicloud.web.vmman.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sitech.basd.sxcloud.cloud.domain.image.TbCloud2ImageInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.service.image.ImageService;
import com.sitech.basd.sxcloud.cloud.service.net.IpService;
import com.sitech.basd.sxcloud.cloud.service.net.NetService;
import com.sitech.basd.sxcloud.rsmu.config.Constant;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.vmman.EntityConObj;
import com.sitech.basd.yicloud.domain.vmman.EntityTreeObj;
import com.sitech.basd.yicloud.domain.vmman.TbYicloudDeviceHealthObj;
import com.sitech.basd.yicloud.domain.vmman.TbYicloudOsTypeObj;
import com.sitech.basd.yicloud.domain.vmman.TreeObj;
import com.sitech.basd.yicloud.domain.vmman.VMManagerObj;
import com.sitech.basd.yicloud.service.vmman.VMManagerService;
import com.sitech.basd.yicloud.util.TypeConstant;
import com.sitech.basd.yicloud.web.vmman.form.VMManagerForm;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * 
 * <p>
 * Title: VMManagerAction
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
public class VMManagerAction extends CRUDBaseAction {
	private VMManagerService vmManagerService;
	private ImageService imageService;
	private NetService netService;
	private IpService ipService;
	private VMManagerForm theForm;
	private String ID;

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public VMManagerForm getTheForm() {
		return theForm;
	}

	public void setTheForm(VMManagerForm theForm) {
		this.theForm = theForm;
	}

	public void setNetService(NetService netService) {
		this.netService = netService;
	}

	public void setIpService(IpService ipService) {
		this.ipService = ipService;
	}

	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	public void setVmManagerService(VMManagerService vmManagerService) {
		this.vmManagerService = vmManagerService;
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
		/*
		 * if (theForm == null) { theForm = new VMManagerForm(); }
		 * HttpServletRequest request = Struts2Utils.getRequest();
		 * HttpServletResponse response = Struts2Utils.getResponse();
		 * VMManagerObj obj = new VMManagerObj(); obj.setTYPE("4");
		 * obj.setPagination(this.getPaginater().initPagination(request));// 分页
		 * List<VMManagerObj> resultList =
		 * vmManagerService.queryForListByObj(obj); String result = "{ id:0120,
		 * name:\"数据中心\",icon:\"sxcloud/images/host.gif\",open:true},"; for
		 * (VMManagerObj o : resultList) { result += "{id:" + o.getID() +
		 * ",pId:0120,name:\"" + o.getNAME_ZH() +
		 * "\",icon:\"sxcloud/images/virtual/monitor.png\""; VMManagerObj vmo =
		 * new VMManagerObj(); vmo.setTYPE("3");
		 * vmo.setVH_HOST(String.valueOf(o.getID())); List<VMManagerObj> oList =
		 * vmManagerService.queryForListByObj(vmo); if (oList != null &&
		 * oList.size() > 0) { result += ",children:["; for (VMManagerObj ol :
		 * oList) { result += " {id:" + ol.getID() + ",name:\"" +
		 * ol.getNAME_ZH() + "\",icon:\"sxcloud/images/virtual/accept.png\"},";
		 * } result = result.substring(0, result.length() - 1); result += "]},";
		 * } else { result += "},"; } } result = result.substring(0,
		 * result.length() - 1); String result = "{ id:\'031\', name:\'n3.n1\',
		 * isParent:true},{ id:\'032\', name:\'n3.n2\', isParent:false},{
		 * id:\'033\', name:\'n3.n3\', isParent:true},{ id:\'034\',
		 * name:\'n3.n4\', isParent:false}"; JSONObject json =
		 * JSONObject.fromObject(result); PrintWriter p =
		 * Struts2Utils.getResponse().getWriter(); p.print(json.toString());
		 * p.close(); theForm.setResultList(resultList);
		 * request.setAttribute("treeStr", result); theForm.setTreeStr(result);
		 */
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
	public String asyncForTree() throws Exception {
		EntityTreeObj treeObj = new EntityTreeObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		String str = request.getParameter("id");
		if (str == null || "".equals(str)) {
			treeObj.setType("8");
		} else {
			treeObj.setParentId(Integer.valueOf(str));
		}
		List<EntityTreeObj> resultList = vmManagerService.queryForTree(treeObj);
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
				// 判断是不是父节点
				tempObj.setParentId(obj.getId());
				List<EntityTreeObj> lst = vmManagerService.queryForTree(tempObj);
				if (lst == null || lst.size() == 0) {
					tObj.setIsParent(false);

				}
				// 设置图标
				if (obj.getType().equals("8")) {// 数据中心
					tObj.setIcon("sxcloud/images/virtual/Datacenter.png");
				}
				if (obj.getType().equals("3") || obj.getType().equals("21")) { // 集群
					tObj.setIcon("sxcloud/images/virtual/cluster.png");
				}
				if (obj.getType().equals("1") || obj.getType().equals("25")) { // 主机
					tObj.setIcon("sxcloud/images/virtual/Host.png");
				}
				if (obj.getType().equals("0")) { // 虚拟机
					tObj.setIcon("sxcloud/images/virtual/VirtualMachine.png");
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
	 * @Title: listYiCloudImages
	 * @Description: 获取虚拟机基准镜像列表
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 20, 2012 10:38:56 AM
	 */
	public String listYiCloudImage() throws BaseException {
		if (theForm == null) {
			theForm = new VMManagerForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbCloud2ImageInfoObj obj = new TbCloud2ImageInfoObj();
		List<TbCloud2ImageInfoObj> imageTypeList = imageService.queryForTypeList(obj);
		String result = "{ id:0120, name:\"虚拟镜像目录\",icon:\"sxcloud/images/alarm/home.gif\",open:true},";
		int i = 0;
		for (TbCloud2ImageInfoObj o : imageTypeList) {
			result += "{id:" + i++ + ",pId:0120,name:\"" + o.getIM_TYPE()
					+ "\",icon:\"sxcloud/images/virtual/folder_brick.png\"";
			TbCloud2ImageInfoObj imo = new TbCloud2ImageInfoObj();
			imo.setIM_TYPE(String.valueOf(o.getIM_TYPE()));
			List<TbCloud2ImageInfoObj> oList = imageService.queryForListByObj(imo);
			if (oList != null && oList.size() > 0) {
				result += ",children:[";
				for (TbCloud2ImageInfoObj ol : oList) {
					result += " {id:" + ol.getIM_ID() + ", name:\"" + ol.getIM_NAME()
							+ "\",icon:\"sxcloud/images/virtual/cd.png\"},";
				}
				result = result.substring(0, result.length() - 1);
				result += "]},";
			} else {
				result += "},";
			}
		}
		result = result.substring(0, result.length() - 1);
		request.setAttribute("imgTreeStr", result);
		String hostId = request.getParameter("hostId");
		request.setAttribute("hostId", hostId);
		return "listimg";
	}

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
			theForm = new VMManagerForm();
		}
		String ID = Struts2Utils.getParameter("ID");
		String TYPE = Struts2Utils.getParameter("TYPE");
		EntityTreeObj obj = new EntityTreeObj();
		obj.setId(Integer.parseInt(ID));
		obj = vmManagerService.queryTreeNode(obj);
		theForm.setNAME(obj.getName());
		HttpServletRequest request = Struts2Utils.getRequest();
		request.setAttribute("ID", ID);
		request.setAttribute("TYPE", TYPE);
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
	 * @createtime Jul 18, 2012 9:19:57 AM
	 */
	public String saveHost() {
		// kvm集群类型是21，vm集群类型是3
		String ID = Struts2Utils.getParameter("ID");
		String TYPE = Struts2Utils.getParameter("TYPE");
		EntityTreeObj obj = new EntityTreeObj();
		VMManagerObj vObj = new VMManagerObj();
		obj.setName(theForm.getNAME_ZH());
		vObj.setNAME_ZH(theForm.getNAME_ZH());
		vObj.setIP(theForm.getIP());
		vObj.setTYPE("3");
		vObj.setUSERNAME(theForm.getUSERNAME());
		theForm.setPASSWORD(theForm.getPASSWORD());
		if (TYPE.equals(TypeConstant.KVM_CLUSTER)) {
			obj.setType(TypeConstant.KVM_HOST);
		} else if (TYPE.equals(TypeConstant.VMWARE_CLUSTER)) {
			obj.setType(TypeConstant.VMWARE_HOST);
		}
		obj.setParentId(Integer.parseInt(ID));
		setID(ID);
		String DEVICE_ID = vmManagerService.getIdSequence();
		obj.setEntityId(DEVICE_ID);
		vObj.setID(Integer.parseInt(DEVICE_ID));
		int ret = vmManagerService.insertTreeNode(obj);
		int rets = vmManagerService.insertByObj(vObj);
		return "save_host";
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
		en = vmManagerService.queryTreeNode(en);
		int hostId = Integer.parseInt(en.getEntityId());
		int tree_id = Integer.parseInt(request.getParameter("ID"));
		String state = request.getParameter("state");
		VMManagerObj obj = new VMManagerObj();
		EntityTreeObj eObj = new EntityTreeObj();
		eObj.setId(tree_id);
		eObj = vmManagerService.queryTreeNode(eObj);
		obj.setID(Integer.parseInt(eObj.getEntityId()));// 获取虚拟机的实体ID
		VMManagerObj vObj = vmManagerService.queryByObj(obj);
		String result = vmManagerService.putVirtualStat(hostId, vObj.getNAME_EN(), state);
		if ("null".equals(result) && state.equals("destroy")) {
			int ret = vmManagerService.delTreeNode(eObj);
			int ret1 = vmManagerService.deleteByObj(obj);
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
		List list = vmManagerService.queryForListByObj(obj);
		String result = "{'error':'error'}";
		if (list != null && list.size() > 0) {
			result = null;
		}
		/** 将返回值装到JSON传到页面 */
		response.setContentType("type/html;charset=UTF-8");
		JSONObject jo = JSONObject.fromObject(result);
		// PrintWriter out = null;
		try {
			// out = response.getWriter();
			// out.println(jo.toString());
			// out.close();
			PrintWriterOut.printWirter(response, jo.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		// List list = vmManagerService.queryForListByObj(obj);
		//
		// if (list != null && list.size() > 0) {
		// result = null;
		// }
		/** 将返回值装到JSON传到页面 */
		response.setContentType("type/html;charset=UTF-8");
		JSONObject jo = JSONObject.fromObject(result);
		// PrintWriter out = null;
		try {
			// out = response.getWriter();
			// out.println(jo.toString());
			// out.close();
			PrintWriterOut.printWirter(response, jo.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		// PrintWriter out = null;
		try {
			// out = response.getWriter();
			// out.println(jo.toString());
			// out.close();
			PrintWriterOut.printWirter(response, jo.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			theForm = new VMManagerForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String oper = request.getParameter("oper");
		String ID = request.getParameter("ID");
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
		EntityTreeObj e = new EntityTreeObj();

		TbCloud2ImageInfoObj tObj = new TbCloud2ImageInfoObj();
		TbYicloudOsTypeObj to = new TbYicloudOsTypeObj();
		if (TYPE.equals(TypeConstant.KVM_IMAGE)) {
			tObj.setIM_TYPE("KVM");

		} else if (TYPE.equals(TypeConstant.VMWARE_IMAGE)) {
			tObj.setIM_TYPE("VMware");
		}

		e.setId(Integer.parseInt(ID));
		e = vmManagerService.queryTreeNode(e);
		tObj.setIM_ID(e.getEntityId());
		TbCloud2ImageInfoObj tbObj = vmManagerService.queryByImageObj(tObj);
		to.setID(tbObj.getOS_ID());
		TbYicloudOsTypeObj tyObj = vmManagerService.queryDefaultConfig(to);
		theForm.setIM_NAME(tbObj.getIM_NAME());
		theForm.setIM_DESC(tbObj.getIM_DESC());
		theForm.setIM_STATE(tbObj.getIM_STATE());
		theForm.setIM_VERSION(tbObj.getIM_VERSION());
		theForm.setIM_TYPE(tbObj.getIM_TYPE());
		theForm.setCURRENTCPU(tyObj.getCPU());
		theForm.setCURRENTMEMORY(tyObj.getMEM());
		theForm.setSTORAGE(tyObj.getSTORAGE());
		request.setAttribute("ID", ID);
		// 查询宿主机相关信息
		// vmManagerService.getConnectMsg(Integer.parseInt(hostId));
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
			theForm = new VMManagerForm();
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
		String ID = request.getParameter("ID");// tree节点ID
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
		if (TYPE.equals(TypeConstant.KVM_IMAGE)) {
			EntityTreeObj e = new EntityTreeObj();
			e.setId(Integer.parseInt(ID));
			e = vmManagerService.queryTreeNode(e);
			String IM_ID = e.getEntityId();// 获取当前节点的实体ID
			int parent_id = e.getParentId();// 获取主机在treeNOde中的Id
			EntityTreeObj en = new EntityTreeObj();
			en.setId(parent_id);
			en = vmManagerService.queryTreeNode(en);
			int host_Id = Integer.parseInt(en.getEntityId());// 获取主机的实体ID
			result = vmManagerService.createVirtual(host_Id, IM_ID, obj);// 创建KVM虚拟机
		} else if (TYPE.equals(TypeConstant.VMWARE_HOST)) {
			// 创建vmware虚拟机
			result = vmManagerService.createVMwareVirtual(Integer.parseInt(ID), obj);
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		/*
		 * if (!result.contains("error")) {
		 * vmManagerService.ftpUploadDomainInfo(request); }
		 */
		/** 将返回值装到JSON传到页面 */
		// response.setContentType("type/html;charset=UTF-8");
		JSONObject jo = JSONObject.fromObject(result);
		// PrintWriter out = null;
		try {
			// out = response.getWriter();
			// out.println(jo.toString());
			// out.close();
			PrintWriterOut.printWirter(response, jo.toString());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
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
			theForm = new VMManagerForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();

		String hostId = request.getParameter("hostId");// 虚拟机所在主机的节点ID
		String oper = request.getParameter("oper");
		String ID = request.getParameter("ID");// 虚拟机的节点ID
		EntityTreeObj e = new EntityTreeObj();
		// 获取主机节点所对应的实体ID
		e.setId(Integer.parseInt(hostId));
		e = vmManagerService.queryTreeNode(e);
		request.setAttribute("oper", oper);
		VMManagerObj hostObj = new VMManagerObj();
		String host_id = e.getEntityId();// 主机节点的实体ID
		hostObj.setID(Integer.parseInt(host_id));
		hostObj = vmManagerService.queryByObj(hostObj);
		theForm.setHostIP(hostObj.getIP());
		// 获取虚拟机所对应的实体ID
		EntityTreeObj en = new EntityTreeObj();
		en.setId(Integer.parseInt(ID));
		en = vmManagerService.queryTreeNode(e);
		VMManagerObj obj = new VMManagerObj();
		obj.setID(Integer.parseInt(e.getEntityId()));
		VMManagerObj vObj = vmManagerService.queryByObj(obj);
		String hostIP = vObj.getIP();
		String NAME_EN = vObj.getNAME_EN();
		theForm.setNAME_ZH(vObj.getNAME_ZH());
		theForm.setNAME_EN(NAME_EN);
		theForm.setIP(hostIP);
		String result = vmManagerService.getVirtualInfo(host_id, vObj.getNAME_EN());
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
			theForm = new VMManagerForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		VMManagerObj obj = new VMManagerObj();
		String ID = request.getParameter("ID");
		EntityTreeObj en = new EntityTreeObj();
		en.setId(Integer.parseInt(ID));// 虚拟机的节点ID
		en = vmManagerService.queryTreeNode(en);
		int parentId = en.getParentId();// 虚拟机所在主机的节点ID
		EntityTreeObj eObj = new EntityTreeObj();
		eObj.setId(parentId);
		eObj = vmManagerService.queryTreeNode(eObj);
		String entityId = eObj.getEntityId();
		obj.setVH_HOST(entityId);
		List<VMManagerObj> list = vmManagerService.queryForListByObj(obj);
		for (VMManagerObj d : list) {
			String NAME_EN = d.getNAME_EN();
			int ENTITY_ID = d.getID();
			EntityTreeObj ent = new EntityTreeObj();
			ent.setEntityId(String.valueOf(ENTITY_ID));
			ent = vmManagerService.queryTreeNode(ent);
			d.setID(ent.getId());
			String s = vmManagerService.getVirtualInfo(entityId, NAME_EN);
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
			theForm = new VMManagerForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		VMManagerObj obj = new VMManagerObj();
		String IP = request.getParameter("IP");
		obj.setIP(IP);
		List hostList = vmManagerService.queryForListByObj(obj);
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
		String result = vmManagerService.adjustKVMVirtualInfo(obj);
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
			theForm = new VMManagerForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String ID = request.getParameter("ID");
		obj.setDEVICE_ID(ID);
		VMManagerObj vObj = new VMManagerObj();
		vObj.setID(Integer.parseInt(ID));
		List<TbYicloudDeviceHealthObj> healthList = vmManagerService.queryHealthStateList(obj);
		vObj = vmManagerService.queryByObj(vObj);
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
		/*
		 * if (theForm == null) { theForm = new VMManagerForm(); }
		 */
		HttpServletRequest request = Struts2Utils.getRequest();
		// VMManagerObj obj = new VMManagerObj();
		String hostId = request.getParameter("hostId");
		String oper = request.getParameter("oper");
		String ID = request.getParameter("ID");
		String type = request.getParameter("type");// 得到实体类型
		String id = request.getParameter("id");
		/*
		 * request.setAttribute("oper", oper); obj.setID(Integer.parseInt(ID));
		 * VMManagerObj hostObj = new VMManagerObj();
		 * hostObj.setID(Integer.parseInt(hostId)); hostObj =
		 * vmManagerService.queryByObj(hostObj);
		 * theForm.setHostIP(hostObj.getIP()); VMManagerObj vObj =
		 * vmManagerService.queryByObj(obj);
		 * theForm.setNAME_ZH(vObj.getNAME_ZH());
		 * theForm.setNAME_EN(vObj.getNAME_EN()); theForm.setIP(vObj.getIP());
		 * String result = vmManagerService.getVirtualInfo(hostId, vObj
		 * .getNAME_EN()); if (!result.contains("Domain not found")) { String[]
		 * message = result.split(","); String[] s1 = message[4].split(":");
		 * theForm.setNAME_EN(s1[1]); String[] s2 = message[5].split(":");
		 * theForm.setIM_TYPE(s2[1]); String[] s3 = message[9].split(":"); int
		 * mem = Integer.parseInt(s3[1]) / 1024;
		 * theForm.setMEMORY(String.valueOf(mem)); String[] s4 =
		 * message[10].split(":"); theForm.setCPU(s4[1]); String[] s5 =
		 * message[11].split(":"); theForm.setSTATE(s5[1]); if
		 * (theForm.getSTATE().contains("RUNNING")) { theForm.setSTATE("正在运行");
		 * } else if (theForm.getSTATE().contains("PAUSED")) {
		 * theForm.setSTATE("暂停"); } else if
		 * (theForm.getSTATE().contains("SHUTOFF")) { theForm.setSTATE("已停止"); }
		 * }
		 */
		request.setAttribute("hostId", hostId);
		request.setAttribute("oper", oper);
		request.setAttribute("ID", ID);
		request.setAttribute("type", type);
		request.setAttribute("id", id);
		return "vmtabs";
	}

	public String testProgressbar() throws Exception {
		// 设置进度条进度
		Constant.PROCESS_LEVEL = 10;
		Thread.sleep(20 * 1000);
		// 设置进度条进度
		Constant.PROCESS_LEVEL = 50;
		Thread.sleep(1 * 30 * 1000);
		// 设置进度条进度
		Constant.PROCESS_LEVEL = 100;
		Thread.sleep(1 * 30 * 1000);
		return null;
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
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		if (id != null && !"".equals(id)) {
			EntityConObj conObj = new EntityConObj();
			conObj.setDataCenterId(Integer.parseInt(id));
			int hostCount = vmManagerService.queryHostCount(conObj);// 主机个数
			int vmCount = vmManagerService.queryVmCount(conObj);// 虚拟机个数
			int clusterCount = vmManagerService.queryClusterCount(conObj);// 集群个数
			conObj.setType(19);
			int netCount = vmManagerService.queryNetOrStoreCount(conObj);// 网络个数
			conObj.setType(18);
			int dsCount = vmManagerService.queryNetOrStoreCount(conObj);// 存储个数
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
			EntityConObj obj = new EntityConObj();
			obj.setDataCenterId(Integer.parseInt(id));
			obj.setTemp(0); // VMWARE_VM
			obj.setType(24); // KVM_VM
			List resultList = vmManagerService.queryEntityInfo(obj);
			if (theForm == null) {
				theForm = new VMManagerForm();
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
			EntityConObj obj = new EntityConObj();
			obj.setDataCenterId(Integer.parseInt(id));
			obj.setTemp(1);// VMWARE_HOST
			obj.setType(25);// KVM_HOST
			List resultList = vmManagerService.queryEntityInfo(obj);
			if (theForm == null) {
				theForm = new VMManagerForm();
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
		String id = request.getParameter("id");
		if (type != null && !"".equals(type)) {
			EntityConObj obj = new EntityConObj();
			obj.setClusterId(Integer.parseInt(id));
			obj.setTemp(0); // VMWARE_VM
			obj.setType(24); // KVM_VM
			List resultList = vmManagerService.queryEntityInfo(obj);
			if (theForm == null) {
				theForm = new VMManagerForm();
			}
			theForm.setResultList(resultList);
		}
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
		HttpServletRequest request = Struts2Utils.getRequest();
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		if (type != null && !"".equals(type)) {
			EntityConObj obj = new EntityConObj();
			obj.setClusterId(Integer.parseInt(id));
			obj.setTemp(1);// VMWARE_HOST
			obj.setType(25);// KVM_HOST
			List resultList = vmManagerService.queryEntityInfo(obj);
			if (theForm == null) {
				theForm = new VMManagerForm();
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
	public String clusterInfo() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		if (id != null && !"".equals(id)) {
			EntityConObj conObj = new EntityConObj();
			conObj.setClusterId(Integer.parseInt(id));
			int hostCount = vmManagerService.queryHostCount(conObj);// 主机个数
			int vmCount = vmManagerService.queryVmCount(conObj);// 虚拟机个数
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
		String id = Struts2Utils.getRequest().getParameter("id");// 得到实体的id,根据entityId查询相应的主机，虚拟机信息。
		if (id != null && !"".equals(id)) {
			EntityTreeObj obj = new EntityTreeObj();
			obj.setId(Integer.valueOf(id));
			List<EntityTreeObj> list = vmManagerService.queryForTree(obj);
			if (list != null && list.size() == 1) {
				VMManagerObj vmObj = new VMManagerObj();
				vmObj.setID(Integer.valueOf(list.get(0).getEntityId()));
				vmObj = vmManagerService.queryByObj(vmObj);
				if (theForm == null) {
					theForm = new VMManagerForm();
				}
				theForm.setNAME_ZH(vmObj.getNAME_ZH());
				theForm.setCODE(vmObj.getCODE());
				theForm.setIP(vmObj.getIP());
				theForm.setBRAND(vmObj.getBRAND());
				theForm.setMODEL(vmObj.getMODEL());
				theForm.setCPU(vmObj.getCPU());
				theForm.setMEMORY(vmObj.getMEMORY());
				theForm.setSTORAGE(vmObj.getSTORAGE());
				theForm.setINTERFACE(vmObj.getINTERFACE());
				theForm.setAUXILIARY(vmObj.getAUXILIARY());
				theForm.setREMARK(vmObj.getREMARK());
			}
		}
		return "hostInfo";
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
	public String vmInfo() {
		String id = Struts2Utils.getRequest().getParameter("id");// 得到实体的id,根据entityId查询相应的主机，虚拟机信息。
		if (id != null && !"".equals(id)) {
			EntityTreeObj obj = new EntityTreeObj();
			obj.setId(Integer.valueOf(id));
			List<EntityTreeObj> list = vmManagerService.queryForTree(obj);
			if (list != null && list.size() == 1) {
				VMManagerObj vmObj = new VMManagerObj();
				vmObj.setID(Integer.valueOf(list.get(0).getEntityId()));
				vmObj = vmManagerService.queryByObj(vmObj);
				if (theForm == null) {
					theForm = new VMManagerForm();
				}
				theForm.setNAME_ZH(vmObj.getNAME_ZH());
				theForm.setCODE(vmObj.getCODE());
				theForm.setIP(vmObj.getIP());
				theForm.setBRAND(vmObj.getBRAND());
				theForm.setMODEL(vmObj.getMODEL());
				theForm.setCPU(vmObj.getCPU());
				theForm.setMEMORY(vmObj.getMEMORY());
				theForm.setSTORAGE(vmObj.getSTORAGE());
				theForm.setINTERFACE(vmObj.getINTERFACE());
				theForm.setAUXILIARY(vmObj.getAUXILIARY());
				theForm.setREMARK(vmObj.getREMARK());
			}
		}
		return "vmInfo";
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
			theForm = new VMManagerForm();
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
	 * @Title: saveCluster
	 * @Description: 保存新建集群
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 2:45:58 PM
	 */
	public String saveCluster() {
		String ID = Struts2Utils.getParameter("ID");
		String TYPE = Struts2Utils.getParameter("TYPE");
		EntityTreeObj obj = new EntityTreeObj();
		obj.setName(theForm.getNAME());
		obj.setParentId(Integer.parseInt(ID));
		String cluster_type = theForm.getCLUSTER_TYPE();
		if (cluster_type.equals("1")) {
			obj.setType(TypeConstant.VMWARE_CLUSTER);
		} else {
			obj.setType(TypeConstant.KVM_CLUSTER);
		}
		int ret = vmManagerService.insertTreeNode(obj);
		return "save_cluster";

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
		EntityTreeObj treeObj = new EntityTreeObj();
		EntityTreeObj obj = new EntityTreeObj();
		obj.setId(Integer.parseInt(ID));
		treeObj.setParentId(Integer.parseInt(ID));
		List<EntityTreeObj> resultList = vmManagerService.queryForTree(treeObj);
		String result = null;
		if (resultList != null && resultList.size() > 0) {
			result = "1";
		} else {
			int ret = vmManagerService.delTreeNode(obj);
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
	 * @Title: modCluster
	 * @Description: 修改集群配置信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 18, 2012 9:18:12 AM
	 */
	public String modCluster() {
		String ID = Struts2Utils.getParameter("ID");
		EntityTreeObj obj = new EntityTreeObj();
		obj.setId(Integer.parseInt(ID));
		obj.setName(theForm.getNAME());
		int ret = vmManagerService.updateTreeNode(obj);
		return "mod_cluster";
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

}
