package com.sitech.basd.yicloud.web.showVm.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.united.UnitedTreeService;
import com.sitech.basd.scheduler.service.ResourceSchedulerService;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.cloud.web.resource.form.HostManageForm;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployExampleObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.DeployExampleService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.service.entitytree.EntityTreeService;
import com.sitech.basd.yicloud.service.xentree.XenEntityTreeService;
import com.sitech.basd.yicloud.web.showVm.form.ShowVmForm;
import com.sitech.vo.util.UnitedConstant;

@SuppressWarnings("all")
public class ShowVmAction extends CRUDBaseAction {
	private ShowVmForm theForm;
	private VMHostService vmHostService;
	private XenEntityTreeService xenEntityTreeService;
	private HostInfoService hostInfoService;
	private EntityTreeService entityTreeService;
	private BusiHostService busiHostService;
	private DeployExampleService deployExampleService;
	private HostManageForm hostForm;
	private UnitedTreeService unitedTreeService;
	@Autowired
	private TbGlobalConfigService tbGlobalConfigService;
	@Autowired
	private ResourceSchedulerService resourceSchedulerService;
	public void setUnitedTreeService(UnitedTreeService unitedTreeService) {
		this.unitedTreeService = unitedTreeService;
	}

	private String busiId;//业务系统Id
	
	public HostManageForm getHostForm() {
		return hostForm;
	}

	public ShowVmForm getTheForm() {
		return theForm;
	}

	public void setTheForm(ShowVmForm theForm) {
		this.theForm = theForm;
	}

	public void setDeployExampleService(DeployExampleService deployExampleService) {
		this.deployExampleService = deployExampleService;
	}

	public void setBusiHostService(BusiHostService busiHostService) {
		this.busiHostService = busiHostService;
	}

	public void setEntityTreeService(EntityTreeService entityTreeService) {
		this.entityTreeService = entityTreeService;
	}

	public void setVmHostService(VMHostService vmHostService) {
		this.vmHostService = vmHostService;
	}

	public void setXenEntityTreeService(XenEntityTreeService xenEntityTreeService) {
		this.xenEntityTreeService = xenEntityTreeService;
	}

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	/**
	 * 
	 * @Title: listvm
	 * @Description: 展示虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 1, 2013 4:34:31 PM
	 */
	public String listvm() {
		if (theForm == null) {
			theForm = new ShowVmForm();
		}
		VMHostObj obj = new VMHostObj();
		
		String entityIds=request.getParameter("entityId");
		String entityType=request.getParameter("entityType");
		if("alarm".equals(entityType)){//告警管理中的弹出列表
			if(entityIds!=null){
				String[] arrays=entityIds.split("_");
				if(arrays.length==2){
					String connectId=arrays[0];
					String entityId=arrays[1];
					obj.setConnectId(connectId);
					obj.setVH_UUID(entityId);
				}
			}
		}
		
		if (theForm.getEQ_ID() != null && !theForm.getEQ_ID().equals("")) {
			obj.setEQ_ID(theForm.getEQ_ID());
		}
		if (theForm.getQueryName() != null && !theForm.getQueryName().equals("")) {
			obj.setVH_NAME(theForm.getQueryName());
		}
		if (theForm.getQueryVHIP() != null && !theForm.getQueryVHIP().equals("")) {
			obj.setVH_IP(theForm.getQueryVHIP());
		}
		if (theForm.getQueryHostIp() != null && !theForm.getQueryHostIp().equals("")
				&& !theForm.getQueryHostIp().equals("0")) {
			String hostIp = theForm.getQueryHostIp();
			TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
			hostObj.setEq_ip(hostIp);
			hostObj = hostInfoService.queryForIdByIp(hostObj);
			if (hostObj != null && hostObj.getEq_id() != null) {
				obj.setEQ_ID(hostObj.getEq_id());
			} else {
				// 根据Ip查找主机不存在
				obj.setEQ_ID("-1");
			}
		}
		if (theForm.getQueryType() != null && !theForm.getQueryType().equals("")) {
			String type = theForm.getQueryType();
			obj.setVH_TYPE(type);
			/*
			 * TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj(); if
			 * (type.equals("1")) { // vmware hostObj.setHasvertual("4"); } else
			 * if (type.equals("3")) { // xen hostObj.setHasvertual("3"); } else
			 * if (type.equals("9")) { // 其他类型 hostObj.setHasvertual("9"); }
			 * List<TbCloud2HostInfoObj> hostList =
			 * hostInfoService.queryAllHost(hostObj);
			 * theForm.setQueryHostIpList(hostList);
			 */
		} else {
			obj.setVH_TYPE("0");
			// theForm.setQueryHostIpList(new ArrayList<TbCloud2HostInfoObj>());
		}
		if (theForm.getQueryState() != null && !theForm.getQueryState().equals("")) {
			obj.setVH_STAT(theForm.getQueryState());
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String eq_id = request.getParameter("eq_id");
		// 主机列表页面到虚拟机、还是直接虚拟机列表页面的标识
		String flag = request.getParameter("flag");
		if (eq_id != null && !eq_id.equals("")) {
			obj.setEQ_ID(eq_id);
			theForm.setEQ_ID(eq_id);
			theForm.setFlag(flag);
		}
		// 查询当前用户
		// TbSysUserinfoObj userObj = (TbSysUserinfoObj)
		// Struts2Utils.getRequest().getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		List vmList = new ArrayList();
		String userId = session.get("id").toString();
		String account = session.get("account").toString();
		if (userId != null && !"".equals(userId)) {
			obj.setUSER_ID(userId);
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		// 全局配置里边配置了几个用户的权限(在安徽移动，有几个用户需要具备管理员权限，即查看虚拟机和主机的全部列表)
		TbGlobalConfigObj global = new TbGlobalConfigObj();
		global.setKEY("user_auth");
		global = tbGlobalConfigService.queryByObj(global);
		try {
			if ("1".equals(userId)) {// 对于admin用户，不需要分配权限
				obj.setUSER_ID(null);
			} else {// 对于普通登录用户，需要进行权限控制

				if (global.getVALUE().contains(account)) {
					obj.setUSER_ID(null);

				}
			}
			vmList = vmHostService.queryForVmList(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		theForm.setResultList(vmList);
		return "listvm";
	}
	
	/**
	 * 
	 * @Title: listvm
	 * @Description: 安徽移动云平台，展示虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author liudan_bj1
	 * @version 1.0
	 * @createtime Mar 1, 2013 4:34:31 PM
	 */
	public String ah_listvm() {
		if (theForm == null) {
			theForm = new ShowVmForm();
		}
		VMHostObj obj = new VMHostObj();
		if (theForm.getEQ_ID() != null && !theForm.getEQ_ID().equals("")) {
			obj.setEQ_ID(theForm.getEQ_ID());
		}
		if (theForm.getQueryName() != null && !theForm.getQueryName().equals("")) {
			obj.setVH_NAME(theForm.getQueryName());
		}
		if (theForm.getQueryVHIP() != null && !theForm.getQueryVHIP().equals("")) {
			obj.setVH_IP(theForm.getQueryVHIP());
		}
		if (busiId != null && !"".equals(busiId)) {
			obj.setVH_CID(busiId);
		}
		if (theForm.getQueryHostIp() != null && !theForm.getQueryHostIp().equals("")
				&& !theForm.getQueryHostIp().equals("0")) {
			String hostIp = theForm.getQueryHostIp();
			TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
			hostObj.setEq_ip(hostIp);
			hostObj = hostInfoService.queryForIdByIp(hostObj);
			if (hostObj != null && hostObj.getEq_id() != null) {
				obj.setEQ_ID(hostObj.getEq_id());
			} else {
				// 根据Ip查找主机不存在
				obj.setEQ_ID("-1");
			}
		}
		if (theForm.getQueryType() != null && !theForm.getQueryType().equals("")) {
			String type = theForm.getQueryType();
			obj.setVH_TYPE(type);
			/*
			 * TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj(); if
			 * (type.equals("1")) { // vmware hostObj.setHasvertual("4"); } else
			 * if (type.equals("3")) { // xen hostObj.setHasvertual("3"); } else
			 * if (type.equals("9")) { // 其他类型 hostObj.setHasvertual("9"); }
			 * List<TbCloud2HostInfoObj> hostList =
			 * hostInfoService.queryAllHost(hostObj);
			 * theForm.setQueryHostIpList(hostList);
			 */
		} else {
			obj.setVH_TYPE("0");
			// theForm.setQueryHostIpList(new ArrayList<TbCloud2HostInfoObj>());
		}
		if (theForm.getQueryState() != null && !theForm.getQueryState().equals("")) {
			obj.setVH_STAT(theForm.getQueryState());
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String eq_id = request.getParameter("eq_id");
		// 主机列表页面到虚拟机、还是直接虚拟机列表页面的标识
		String flag = request.getParameter("flag");
		if (eq_id != null && !eq_id.equals("")) {
			obj.setEQ_ID(eq_id);
			theForm.setEQ_ID(eq_id);
			theForm.setFlag(flag);
		}
		//增加数据中心
		theForm.setDataCenterList(initTreeList());
		/*if(theForm.getCenter_uuid() == null){
			obj.setCenterid(theForm.getDataCenterList() != null && theForm.getDataCenterList().size() > 0?theForm.getDataCenterList().get(0).getUuid():"");
		}else{*/
			obj.setCenterid(theForm.getCenter_uuid());
//		}
		
		// 查询当前用户
		// TbSysUserinfoObj userObj = (TbSysUserinfoObj)
		// Struts2Utils.getRequest().getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		List vmList = new ArrayList();
		String userId = session.get("id").toString();
		String account = session.get("account").toString();
		if (userId != null && !"".equals(userId)) {
			obj.setUSER_ID(userId);
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		// 全局配置里边配置了几个用户的权限(在安徽移动，有几个用户需要具备管理员权限，即查看虚拟机和主机的全部列表)
		TbGlobalConfigObj global = new TbGlobalConfigObj();
		global.setKEY("user_auth");
		global = tbGlobalConfigService.queryByObj(global);
		try {
			if ("1".equals(userId)) {// 对于admin用户，不需要分配权限
				obj.setUSER_ID(null);
			} else {// 对于普通登录用户，需要进行权限控制

				if (global.getVALUE().contains(account)) {
					obj.setUSER_ID(null);

				}
			}
			vmList = vmHostService.queryForVmList(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		theForm.setResultList(vmList);
		return "ah_listvm";
	}
	
	private List<UnitedTreeObj> initTreeList() {
		List<UnitedTreeObj> treeList = null;
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setType(UnitedConstant.DATACENTER);
		try {
			List<UnitedTreeObj> utreeList = unitedTreeService
					.queryForTreeList(unitedTreeObj);
			for (UnitedTreeObj unitedTreeObj2 : utreeList) {
				if (UnitedConstant.VMWARE.equals(unitedTreeObj2.getVtype())
						|| UnitedConstant.XEN.equals(unitedTreeObj2.getVtype())) {
					if (treeList == null) {
						treeList = new ArrayList<UnitedTreeObj>();
					}
					treeList.add(unitedTreeObj2);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return treeList;
	}
	/**
	 * 
	 * @Title: listvm２
	 * @Description: 虚拟机展示_定时开关机专用
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 1, 2013 4:34:31 PM
	 */
	public String listvm2() {
		
		HttpServletRequest req = Struts2Utils.getRequest();
		if (theForm == null) {
			theForm = new ShowVmForm();
		}
		VMHostObj obj = new VMHostObj();
		if (theForm.getEQ_ID() != null && !theForm.getEQ_ID().equals("")) {
			obj.setEQ_ID(theForm.getEQ_ID());
		}
		if (theForm.getQueryName() != null && !theForm.getQueryName().equals("")) {
			obj.setVH_NAME(theForm.getQueryName());
		}
		if (theForm.getQueryVHIP() != null && !theForm.getQueryVHIP().equals("")) {
			obj.setVH_IP(theForm.getQueryVHIP());
		}
		if (theForm.getQueryHostIp() != null && !theForm.getQueryHostIp().equals("")
				&& !theForm.getQueryHostIp().equals("0")) {
			String hostIp = theForm.getQueryHostIp();
			TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
			hostObj.setEq_ip(hostIp);
			hostObj = hostInfoService.queryForIdByIp(hostObj);
			if (hostObj != null && hostObj.getEq_id() != null) {
				obj.setEQ_ID(hostObj.getEq_id());
			} else {
				// 根据Ip查找主机不存在
				obj.setEQ_ID("-1");
			}
		}
		if (theForm.getQueryType() != null && !theForm.getQueryType().equals("")) {
			String type = theForm.getQueryType();
			obj.setVH_TYPE(type);
			/*
			 * TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj(); if
			 * (type.equals("1")) { // vmware hostObj.setHasvertual("4"); } else
			 * if (type.equals("3")) { // xen hostObj.setHasvertual("3"); } else
			 * if (type.equals("9")) { // 其他类型 hostObj.setHasvertual("9"); }
			 * List<TbCloud2HostInfoObj> hostList =
			 * hostInfoService.queryAllHost(hostObj);
			 * theForm.setQueryHostIpList(hostList);
			 */
		} else {
			obj.setVH_TYPE("0");
			// theForm.setQueryHostIpList(new ArrayList<TbCloud2HostInfoObj>());
		}
		if (theForm.getQueryState() != null && !theForm.getQueryState().equals("")) {
			obj.setVH_STAT(theForm.getQueryState());
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String eq_id = request.getParameter("eq_id");
		// 主机列表页面到虚拟机、还是直接虚拟机列表页面的标识
		String flag = request.getParameter("flag");
		if (eq_id != null && !eq_id.equals("")) {
			obj.setEQ_ID(eq_id);
			theForm.setEQ_ID(eq_id);
			theForm.setFlag(flag);
		}
		// 查询当前用户
		// TbSysUserinfoObj userObj = (TbSysUserinfoObj)
		// Struts2Utils.getRequest().getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		List vmList = new ArrayList();
		String userId = session.get("id").toString();
		String account = session.get("account").toString();
		if (userId != null && !"".equals(userId)) {
			obj.setUSER_ID(userId);
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		// 全局配置里边配置了几个用户的权限(在安徽移动，有几个用户需要具备管理员权限，即查看虚拟机和主机的全部列表)
		TbGlobalConfigObj global = new TbGlobalConfigObj();
		global.setKEY("user_auth");
		global = tbGlobalConfigService.queryByObj(global);
		try {
			if ("1".equals(userId)) {// 对于admin用户，不需要分配权限
				obj.setUSER_ID(null);
			} else {// 对于普通登录用户，需要进行权限控制

				if (global.getVALUE().contains(account)) {
					obj.setUSER_ID(null);

				}
			}
			
		    obj.setUuidList(resourceSchedulerService.exclude());
			vmList = vmHostService.queryForVmList(obj);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		theForm.setResultList(vmList);
		return "schedulervmlist";
	}
	/**
	 * 
	 * @Title: showVmwareDetail
	 * @Description: 展示vmware虚拟机的详细信息
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Feb 27, 2013 1:47:02 PM
	 */
	public String showVmwareDetail() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String VH_NAME = request.getParameter("VH_NAME");
		String EQ_NAME = request.getParameter("EQ_NAME");
		String VH_SYSTEM = request.getParameter("VH_SYSTEM");
		try {
			if (VH_NAME != null && !VH_NAME.equals("")) {
				VH_NAME = URLDecoder.decode(VH_NAME, "UTF-8");
			}
			if (EQ_NAME != null && !EQ_NAME.equals("")) {
				EQ_NAME = URLDecoder.decode(EQ_NAME, "UTF-8");
			}
			if (VH_SYSTEM != null && !VH_SYSTEM.equals("")) {
				VH_SYSTEM = URLDecoder.decode(VH_SYSTEM, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String VH_TYPE = request.getParameter("VH_TYPE");
		String EQ_ID = request.getParameter("EQ_ID");
		String VH_CPU = request.getParameter("VH_CPU");
		String VH_MEM = request.getParameter("VH_MEM");
		String VH_STORAGE = request.getParameter("VH_STORAGE");
		String VH_NETWORK = request.getParameter("VH_NETWORK");
		String VH_STAT = request.getParameter("VH_STAT");
		String VH_UUID = request.getParameter("VH_UUID");
		String VH_IP = request.getParameter("VH_IP");
		String connId = request.getParameter("connectId");

		if (theForm == null) {
			theForm = new ShowVmForm();
		}
		// 查询主机的应用
		StringBuffer appName = new StringBuffer();
		if (VH_IP == null || VH_IP.equals("")) {
			theForm.setAPP_NAME("暂无");
			theForm.setVH_IP("未提取到数据");
		} else {
			theForm.setVH_IP(VH_IP);
			TbBusiHostObj hostObj = new TbBusiHostObj();
			hostObj.setIP(VH_IP);
			List HostLst = busiHostService.queryIDByIP(hostObj);
			if (HostLst != null && HostLst.size() > 0) {
				hostObj = (TbBusiHostObj) HostLst.get(0);
				TbBusiDeployExampleObj deployObj = new TbBusiDeployExampleObj();
				deployObj.setHOSTID(hostObj.getID());
				List deployList = deployExampleService.queryForListByObj(deployObj);
				if (deployList != null && deployList.size() > 0) {
					for (Object o : deployList) {
						deployObj = (TbBusiDeployExampleObj) o;
						appName.append(deployObj.getAPPNAME()).append(",");
					}
					String name = appName.toString().substring(0, appName.length() - 1);
					theForm.setAPP_NAME(name);
				} else {
					theForm.setAPP_NAME("暂无");
				}
			} else {
				theForm.setAPP_NAME("暂无");
			}
		}

		EntityTreeObj treeObj = new EntityTreeObj();
		treeObj.setEntityId(VH_UUID);
		treeObj = entityTreeService.queryTreeNode(treeObj);
		String treeNodeId = "";
		if (treeObj != null) {
			treeNodeId = treeObj.getId() + "";
		}
		/*
		 * String param = "/vmware/domain/msg/[vmName:" + VH_UUID + "]"; String
		 * result = InvokeUtil.invoke(param); Map resource =
		 * ParamParser.makeup(result); String nowState = ""; if (((String)
		 * resource.get("responseCode")).equals("1")) { nowState =
		 * resource.get("power").toString(); } if
		 * (nowState.equals("poweredOff")) { VH_STAT = "0"; } else if
		 * (nowState.equals("poweredOn")) { VH_STAT = "1"; }
		 */
		theForm.setVH_NAME(VH_NAME);
		theForm.setEQ_NAME(EQ_NAME);
		theForm.setVH_TYPE(VH_TYPE);
		theForm.setEQ_ID(EQ_ID);
		theForm.setVH_SYSTEM(VH_SYSTEM);
		theForm.setVH_CPU(VH_CPU);
		theForm.setVH_MEM(VH_MEM);
		theForm.setVH_STORAGE(VH_STORAGE);
		theForm.setVH_NETWORK(VH_NETWORK);
		theForm.setVH_STAT(VH_STAT);
		theForm.setVH_UUID(VH_UUID);
		theForm.setTREENODE_ID(treeNodeId);
		theForm.setPOOL_UUID(connId);
		return "showVmwareDetail";
	}

	/**
	 * 
	 * @Title: showXenDetail
	 * @Description: 展示xen虚拟机的详细信息
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 1, 2013 4:34:00 PM
	 */
	public String showXenDetail() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String VH_NAME = request.getParameter("VH_NAME");
		String EQ_NAME = request.getParameter("EQ_NAME");
		String VH_SYSTEM = request.getParameter("VH_SYSTEM");
		try {
			if (VH_NAME != null && !VH_NAME.equals("")) {
				VH_NAME = URLDecoder.decode(VH_NAME, "UTF-8");
			}
			if (EQ_NAME != null && !EQ_NAME.equals("")) {
				EQ_NAME = URLDecoder.decode(EQ_NAME, "UTF-8");
			}
			if (VH_SYSTEM != null && !VH_SYSTEM.equals("")) {
				VH_SYSTEM = URLDecoder.decode(VH_SYSTEM, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String VH_TYPE = request.getParameter("VH_TYPE");
		String EQ_ID = request.getParameter("EQ_ID");
		String VH_CPU = request.getParameter("VH_CPU");
		String VH_MEM = request.getParameter("VH_MEM");
		String VH_STORAGE = request.getParameter("VH_STORAGE");
		String VH_NETWORK = request.getParameter("VH_NETWORK");
		String VH_STAT = request.getParameter("VH_STAT");
		String VH_UUID = request.getParameter("VH_UUID");
		String VH_IP = request.getParameter("VH_IP");
		String connId = request.getParameter("connectId");
		if (theForm == null) {
			theForm = new ShowVmForm();
		}
		// 查询主机的应用
		StringBuffer appName = new StringBuffer();
		if (VH_IP == null || VH_IP.equals("")) {
			theForm.setAPP_NAME("暂无");
			theForm.setVH_IP("未提取到数据");
		} else {
			theForm.setVH_IP(VH_IP);
			TbBusiHostObj hostObj = new TbBusiHostObj();
			hostObj.setIP(VH_IP);
			List HostLst = busiHostService.queryIDByIP(hostObj);
			if (HostLst != null && HostLst.size() > 0) {
				hostObj = (TbBusiHostObj) HostLst.get(0);
				TbBusiDeployExampleObj deployObj = new TbBusiDeployExampleObj();
				deployObj.setHOSTID(hostObj.getID());
				List deployList = deployExampleService.queryForListByObj(deployObj);
				if (deployList != null && deployList.size() > 0) {
					for (Object o : deployList) {
						deployObj = (TbBusiDeployExampleObj) o;
						appName.append(deployObj.getAPPNAME()).append(",");
					}
					String name = appName.toString().substring(0, appName.length() - 1);
					theForm.setAPP_NAME(name);
				} else {
					theForm.setAPP_NAME("暂无");
				}
			} else {
				theForm.setAPP_NAME("暂无");
			}
		}

		try {
			UnitedTreeObj vmObj = new UnitedTreeObj();
			vmObj.setUuid(VH_UUID);
			vmObj = unitedTreeService.queryByObj(vmObj);
			if (vmObj != null) {
				UnitedTreeObj hostObj = new UnitedTreeObj();
				hostObj.setId(vmObj.getParent_id());
				hostObj = unitedTreeService.queryByObj(hostObj);
				if (hostObj != null && hostObj.getType().equals(UnitedConstant.HOST)) {
					theForm.setHOST_UUID(hostObj.getUuid());
				} else {
					theForm.setHOST_UUID("");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// XenEntityTreeObj treeObj = new XenEntityTreeObj();
		// treeObj.setUuid(VH_UUID);
		// treeObj = xenEntityTreeService.queryTreeNode(treeObj);
		// String pool_uuid = "";
		// if (treeObj != null) {
		// XenEntityTreeObj hostObj = new XenEntityTreeObj();
		// hostObj.setId(treeObj.getParentId());
		// hostObj = xenEntityTreeService.queryTreeNode(hostObj);
		// if (hostObj != null) {
		// if (hostObj.getType().equals("26")) {
		// XenEntityTreeObj poolObj = new XenEntityTreeObj();
		// poolObj.setId(hostObj.getParentId());
		// poolObj = xenEntityTreeService.queryTreeNode(poolObj);
		// theForm.setHOST_UUID(hostObj.getUuid());
		// if (poolObj != null) {
		// pool_uuid = poolObj.getUuid();
		// theForm.setPOOL_UUID(poolObj.getUuid());
		// }
		// theForm.setTREENODE_ID(treeObj.getId() + "");
		// theForm.setENTITY_ID(treeObj.getEntityId());
		// } else {
		// theForm.setHOST_UUID("");
		// theForm.setPOOL_UUID(hostObj.getUuid());
		// pool_uuid = hostObj.getUuid();
		// theForm.setTREENODE_ID(treeObj.getId() + "");
		// theForm.setENTITY_ID(treeObj.getEntityId());
		// }
		// }
		// }

		// XenEntityTreeObj treeObj = new XenEntityTreeObj();
		//
		// String param = "/xen/vm/state/vif/count/get/[poolUuid:" + pool_uuid +
		// "].[vmUuid:"
		// + VH_UUID + "]/";
		// String result = InvokeUtil.invoke(param);
		// JSONObject jo = JSONObject.fromObject(result);
		// String nowState = "";
		// if (((String) jo.get("responseCode")).equals("1")) {
		// nowState = jo.get("powerState").toString();
		// }
		// if (nowState.equals("Halted")) {
		// VH_STAT = "0";
		// } else if (nowState.equals("Running")) {
		// VH_STAT = "1";
		// } else if (nowState.equals("Suspended")) {
		// VH_STAT = "2";
		// }

		theForm.setVH_NAME(VH_NAME);
		theForm.setEQ_NAME(EQ_NAME);
		theForm.setVH_TYPE(VH_TYPE);
		theForm.setEQ_ID(EQ_ID);
		theForm.setVH_SYSTEM(VH_SYSTEM);
		theForm.setVH_CPU(VH_CPU);
		theForm.setVH_MEM(VH_MEM);
		theForm.setVH_STORAGE(VH_STORAGE);
		theForm.setVH_NETWORK(VH_NETWORK);
		theForm.setVH_STAT(VH_STAT);
		theForm.setVH_UUID(VH_UUID);
		theForm.setPOOL_UUID(connId);
		return "showXenDetail";
	}

	/**
	 * 
	 * @Title: queryHostIp
	 * @Description: 查询主机ip列表
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 20, 2013 6:56:14 PM
	 */
	public String queryHostIp() {
		if (hostForm == null) {
			hostForm = new HostManageForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		String type = request.getParameter("type");
		String flag = request.getParameter("flag");
		if (flag != null && !flag.equals("")) {
			hostForm.setFlag(flag);
		}
		if (type != null && !type.equals("")) {
			if (type.equals("1")) {
				// vmware
				obj.setHasvertual("4");
			} else if (type.equals("3")) {
				// xen
				obj.setHasvertual("3");
			} else if (type.equals("9")) {
				// 其他类型
				obj.setHasvertual("9");
			}
			hostForm.setTYPE(Integer.parseInt(type));
		}
		if (hostForm.getEq_name() != null && !hostForm.getEq_name().equals("")) {
			obj.setEq_name(hostForm.getEq_name());
		}
		if (hostForm.getEq_type() != null && !hostForm.getEq_type().equals("")) {
			obj.setEq_type(hostForm.getEq_type());
		}
		if (hostForm.getEq_ip() != null && !hostForm.getEq_ip().equals("")) {
			obj.setEq_ip(hostForm.getEq_ip());
		}
		if (hostForm.getHasvertual() != null && !hostForm.getHasvertual().equals("")) {
			obj.setHasvertual(hostForm.getHasvertual());
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页

		List<TbCloud2HostInfoObj> resultList = hostInfoService.queryAllHost(obj);

		hostForm.setResultList(resultList);

		return "listhost";
	}
	
	
	public void setHostForm(HostManageForm hostForm) {
		this.hostForm = hostForm;
	}

	public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}
	
}
