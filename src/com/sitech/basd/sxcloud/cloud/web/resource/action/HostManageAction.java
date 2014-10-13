package com.sitech.basd.sxcloud.cloud.web.resource.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.united.UnitedTreeService;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.leader.TbCloud2CubinetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostHisObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.cloud.service.leader.LeaderViewBusinessService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.web.resource.form.HostManageForm;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.basd.yicloud.domain.datastore.StoreDeviceObj;
import com.sitech.basd.yicloud.domain.device.ManageProtocolObj;
import com.sitech.basd.yicloud.domain.device.SnmpParamObj;
import com.sitech.basd.yicloud.domain.vmauthority.VmAuthorityObj;
import com.sitech.basd.yicloud.domain.xenstore.XenStoreObj;
import com.sitech.basd.yicloud.service.datastore.DataStoreService;
import com.sitech.basd.yicloud.service.datastore.StoreDeviceService;
import com.sitech.basd.yicloud.service.device.DeviceService;
import com.sitech.basd.yicloud.service.device.ManageProtocolService;
import com.sitech.basd.yicloud.service.device.SnmpParamService;
import com.sitech.basd.yicloud.service.vmauthority.VmAuthorityService;
import com.sitech.basd.yicloud.service.xenstore.XenStoreService;
import com.sitech.ssd.sc.os.domain.HostModel;
import com.sitech.ssd.sc.os.service.OsHostService;
import com.sitech.utils.exception.RabbitMQException;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.utils.rabbitmq.RabbitMQUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.utils.ssh.SSHUtil;
import com.sitech.utils.ssh.SshConnection;
import com.sitech.utils.ssh.SshPingIp;
import com.sitech.vo.util.UnitedConstant;

@SuppressWarnings("all")
public class HostManageAction extends CRUDBaseAction {

	private HostInfoService hostInfoService;

	private LeaderViewBusinessService leaderViewBusinessService;

	private HostManageForm theForm;

	private DeviceService deviceService;

	private DataStoreService dataStoreService;

	private String hostID;

	private XenStoreService xenStoreService;
	@Autowired
	private VmAuthorityService vmAuthorityService;
	@Autowired
	private TbGlobalConfigService tbGlobalConfigService;
	@Autowired
	private StoreDeviceService storeDeviceService;
	@Autowired
	private RabbitMQUtil rabbitMQUtil;
	@Autowired
	private UnitedTreeService unitedTreeService;
	@Autowired
	private SnmpParamService snmpParamService;
	@Autowired
	private ManageProtocolService manageProtocolService;
	@Autowired
	private OsHostService osHostService;
	private String hostids;

	private String location;

	private String deviceId;// 主机关联的存储设备的id

	private StoreDeviceObj storeDeviceObj;// 存储设备

	private String type;
	private SnmpParamObj snmp;
	private ManageProtocolObj protocol;
	
	private String oper;//上电：start 下电：stop 重启：restart
	private String eq_id;

	/**
	 * 
	 * @Title: 获得机器列表
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String listHost() throws BaseException {
		if (theForm == null) {
			theForm = new HostManageForm();
		}

		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
			if (obj.getEq_id() != null && !obj.getEq_id().equals("")) {
				obj.setEq_id(obj.getEq_id().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<TbCloud2HostInfoObj> resultList = hostInfoService.queryForListByObj(obj);
		List<TbCloud2HostInfoObj> hostList1 = new ArrayList<TbCloud2HostInfoObj>();
		List<TbCloud2HostInfoObj> hostList2 = new ArrayList<TbCloud2HostInfoObj>();
		List<TbCloud2HostInfoObj> hostList3 = new ArrayList<TbCloud2HostInfoObj>();
		List<TbCloud2HostInfoObj> hostList4 = new ArrayList<TbCloud2HostInfoObj>();
		List<TbCloud2HostInfoObj> hostList5 = new ArrayList<TbCloud2HostInfoObj>();
		if (null != resultList && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				TbCloud2HostInfoObj hostObj = resultList.get(i);
				if ("1".equals(hostObj.getEq_type())) { // 设备为IBM小机
					hostList1.add(hostObj);
				} else if ("2".equals(hostObj.getEq_type())) { // 设备为IBM刀片
					hostList2.add(hostObj);
				} else if ("3".equals(hostObj.getEq_type())) { // 设备为IBM普通刀片
					hostList3.add(hostObj);
				} else if ("4".equals(hostObj.getEq_type())) { // 设备为HP X86刀片
					hostList4.add(hostObj);
				} else if ("5".equals(hostObj.getEq_type())) { // 设备为HP X86刀片
					hostList5.add(hostObj);
				}
			}
		}
		theForm.setHostList1(hostList1);
		theForm.setHostList2(hostList2);
		theForm.setHostList3(hostList3);
		theForm.setHostList4(hostList4);
		theForm.setHostList5(hostList5);
		return LIST;

	}

	/**
	 * 
	 * @Title: 获得所有主机列表(安徽移动竞标)
	 * @Copyright: Copyright (c) 2013-02-25
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String allHostList() throws BaseException {
		if (theForm == null) {
			theForm = new HostManageForm();
		}
		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		String hostID = request.getParameter("hostID");
		
		//VCENTER.m0_host-34
		String entityIds=request.getParameter("entityId");
		String entityType=request.getParameter("entityType");
		if("alarm".equals(entityType)){//告警管理中的弹出列表
			if(entityIds!=null){
				String connectId=entityIds.split("_")[0];
				String entityId=entityIds.split("_")[1];
				obj.setConnectId(connectId);
				obj.setH_uuid(entityId);
			}
		}
		if (theForm.getHostUuids() != null && !theForm.getHostUuids().equals("")) {
			obj.setHostUuids(theForm.getHostUuids());
		}
		if (null != hostID && !"".equals(hostID)) {
			obj.setHOST_POOL_ID(hostID.trim());
			theForm.setHOST_POOL_ID(hostID.trim());
		}
		if (theForm.getEq_id() != null && !theForm.getEq_id().equals("")) {
			obj.setEq_id(theForm.getEq_id().trim());
		}
		if (theForm.getEq_name() != null && !theForm.getEq_name().equals("")) {
			obj.setEq_name(theForm.getEq_name().trim());
		}
		if (theForm.getEq_ip() != null && !theForm.getEq_ip().equals("")) {
			obj.setEq_ip(theForm.getEq_ip().trim());
		}
		if (theForm.getHasvertual() != null && !theForm.getHasvertual().equals("-1")) {
			obj.setHasvertual(theForm.getHasvertual());
		}
		if (theForm.getEq_type() != null && !theForm.getEq_type().equals("-1")) {
			obj.setEq_type(theForm.getEq_type());
		}
		if (theForm.getAllocated() != null && !theForm.getAllocated().equals("")) {
			obj.setAllocated(theForm.getAllocated());
		}
		if (theForm.getSTATUS() != null && !"".equals(theForm.getSTATUS())) {
			obj.setSTATUS(theForm.getSTATUS());
		}

		String store_uuid = request.getParameter("store_uuid");
		try {
			if (store_uuid != null && !store_uuid.equals("")) {
				store_uuid = URLDecoder.decode(store_uuid, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String hostUuids = "";
		if (store_uuid != null && !store_uuid.equals("")) {
			DataStoreObj storeObj = new DataStoreObj();
			storeObj.setStore_uuid(store_uuid);
			List storeList = dataStoreService.queryForHostIdsList(storeObj);
			if (storeList != null && storeList.size() > 0) {
				for (Object o : storeList) {
					storeObj = (DataStoreObj) o;
					if (storeObj.getHOST_ID() != null && !storeObj.getHOST_ID().equals("")) {
						hostUuids += "'" + storeObj.getHOST_ID() + "'" + ",";
					}
				}
				if (hostUuids.length() > 1) {
					hostUuids = hostUuids.substring(0, hostUuids.length() - 1);
				}
			}
			XenStoreObj xenStoreObj = new XenStoreObj();
			xenStoreObj.setStore_uuid(store_uuid);
			List xenStoreList = xenStoreService.queryForStoreList(xenStoreObj);
			if (xenStoreList != null && xenStoreList.size() > 0) {
				for (Object o : xenStoreList) {
					xenStoreObj = (XenStoreObj) o;
					if (xenStoreObj.getDependent_host_uuid() != null
							&& !xenStoreObj.getDependent_host_uuid().equals("")) {
						hostUuids += "'" + xenStoreObj.getDependent_host_uuid() + "'" + ",";
					}
				}
				if (hostUuids.length() > 1) {
					hostUuids = hostUuids.substring(0, hostUuids.length() - 1);
				}
			}
			obj.setHostUuids(hostUuids);
			theForm.setHostUuids(hostUuids);
		}
		// 查询当前用户
		String userId = session.get("id").toString();
		if (userId != null && !"".equals(userId)) {
			// 若为超级管理员admin,无需根据用户进行过滤
			obj.setUSER_ID(userId);

		}
		List resultList = new ArrayList();
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		String account = session.get("account").toString();
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
			resultList = hostInfoService.queryAllHost(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// List<TbCloud2HostInfoObj> resultList =
		// hostInfoService.queryAllHost(obj);
		/*
		 * for (TbCloud2HostInfoObj host : resultList) { Map<String, String> map
		 * = new HashMap<String, String>(); map.put("hostId", host.getEq_id());
		 * if ("3".equals(host.getHasvertual())) { // xen map.put("type", "3");
		 * } else if ("4".equals(host.getHasvertual())) { // vmwar
		 * map.put("type", "1"); } else { map.put("type", ""); }
		 * host.setVm_num(hostInfoService.countvmNum(map));// 主机下虚拟机的个数
		 * Map<String, String> map2 = new HashMap<String, String>();
		 * map2.put("hostId", host.getH_uuid());
		 * host.setStorage_num(hostInfoService.countStorageNum(map2));//
		 * 主机下存储的个数 }
		 */
		
		List<TbCloud2HostInfoObj> hosts=new ArrayList<TbCloud2HostInfoObj>();

		/**
		 * 格式化时间
		 */
		for(int i=0;i<resultList.size();i++){
			TbCloud2HostInfoObj objTemp=(TbCloud2HostInfoObj) resultList.get(i);
			String timeTemp=objTemp.getIns_date();
//			timeTemp=timeTemp.split(" ")[0];
			objTemp.setIns_date(timeTemp);
			hosts.add(objTemp);
		}
		theForm.setResultList(hosts);
		HttpServletRequest request = Struts2Utils.getRequest();
		String flag = request.getParameter("flag");
		theForm.setFlag(flag);
		// 用以判断所在省份
		location = PropertiesUtil.getString("properties.location", "location");
		return "allHost";

	}

	/**
	 * @Title: ah_allHostList
	 * @Description: 安徽主机列表
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-11-20 下午5:11:24
	 */
	public String ah_allHostList() throws BaseException {
		if (theForm == null) {
			theForm = new HostManageForm();
		}
		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		String hostID = request.getParameter("hostID");
		if (theForm.getHostUuids() != null && !theForm.getHostUuids().equals("")) {
			obj.setHostUuids(theForm.getHostUuids());
		}
		if (null != hostID && !"".equals(hostID)) {
			obj.setHOST_POOL_ID(hostID.trim());
			theForm.setHOST_POOL_ID(hostID.trim());
		}
		if (theForm.getEq_id() != null && !theForm.getEq_id().equals("")) {
			obj.setEq_id(theForm.getEq_id().trim());
		}
		if (theForm.getEq_name() != null && !theForm.getEq_name().equals("")) {
			obj.setEq_name(theForm.getEq_name().trim());
		}
		if (theForm.getEq_ip() != null && !theForm.getEq_ip().equals("")) {
			obj.setEq_ip(theForm.getEq_ip().trim());
		}
		if (theForm.getHasvertual() != null && !theForm.getHasvertual().equals("-1")) {
			obj.setHasvertual(theForm.getHasvertual());
		}
		if (theForm.getEq_type() != null && !theForm.getEq_type().equals("-1")) {
			obj.setEq_type(theForm.getEq_type());
		}
		if (theForm.getAllocated() != null && !theForm.getAllocated().equals("")) {
			obj.setAllocated(theForm.getAllocated());
		}
		if (theForm.getSTATUS() != null && !"".equals(theForm.getSTATUS())) {
			obj.setSTATUS(theForm.getSTATUS());
		}
		// 数据中心列表
		theForm.setDataCenterList(initTreeList());
		/*
		 * if(theForm.getCenter_uuid() == null){
		 * obj.setCenterid(theForm.getDataCenterList() != null &&
		 * theForm.getDataCenterList().size() >
		 * 0?theForm.getDataCenterList().get(0).getUuid():""); }else{
		 */
		obj.setCenterid(theForm.getCenter_uuid());
		// }
		String store_uuid = request.getParameter("store_uuid");
		try {
			if (store_uuid != null && !store_uuid.equals("")) {
				store_uuid = URLDecoder.decode(store_uuid, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String hostUuids = "";
		if (store_uuid != null && !store_uuid.equals("")) {
			DataStoreObj storeObj = new DataStoreObj();
			storeObj.setStore_uuid(store_uuid);
			List storeList = dataStoreService.queryForHostIdsList(storeObj);
			if (storeList != null && storeList.size() > 0) {
				for (Object o : storeList) {
					storeObj = (DataStoreObj) o;
					if (storeObj.getHOST_ID() != null && !storeObj.getHOST_ID().equals("")) {
						hostUuids += "'" + storeObj.getHOST_ID() + "'" + ",";
					}
				}
				if (hostUuids.length() > 1) {
					hostUuids = hostUuids.substring(0, hostUuids.length() - 1);
				}
			}
			XenStoreObj xenStoreObj = new XenStoreObj();
			xenStoreObj.setStore_uuid(store_uuid);
			List xenStoreList = xenStoreService.queryForStoreList(xenStoreObj);
			if (xenStoreList != null && xenStoreList.size() > 0) {
				for (Object o : xenStoreList) {
					xenStoreObj = (XenStoreObj) o;
					if (xenStoreObj.getDependent_host_uuid() != null
							&& !xenStoreObj.getDependent_host_uuid().equals("")) {
						hostUuids += "'" + xenStoreObj.getDependent_host_uuid() + "'" + ",";
					}
				}
				if (hostUuids.length() > 1) {
					hostUuids = hostUuids.substring(0, hostUuids.length() - 1);
				}
			}
			obj.setHostUuids(hostUuids);
			theForm.setHostUuids(hostUuids);
		}
		// 查询当前用户
		String userId = session.get("id").toString();
		if (userId != null && !"".equals(userId)) {
			// 若为超级管理员admin,无需根据用户进行过滤
			obj.setUSER_ID(userId);

		}
		List resultList = new ArrayList();
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		String account = session.get("account").toString();
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
			resultList = hostInfoService.queryAllHost(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 查询主机所属存储设备
		List<TbCloud2HostInfoObj> hostList = new ArrayList<TbCloud2HostInfoObj>();
		TbCloud2HostInfoObj infoObj = new TbCloud2HostInfoObj();
		hostList = hostInfoService.queryForStoreDeviceList(infoObj);

		w: for (TbCloud2HostInfoObj tbCloud2HostInfoObj : hostList) {
			for (Object tObj : resultList) {
				TbCloud2HostInfoObj tiObj = (TbCloud2HostInfoObj) tObj;
				if (tiObj.getH_uuid() != null
						&& tiObj.getH_uuid().equals(tbCloud2HostInfoObj.getH_uuid())
						&& tiObj.getConnectId() != null
						&& tiObj.getConnectId().equals(tbCloud2HostInfoObj.getConnectId())) {
					tiObj.setDeviceName(tbCloud2HostInfoObj.getDeviceName());
					tiObj.setDeviceId(tbCloud2HostInfoObj.getDeviceId());
					continue w;
				}
			}
		}

		theForm.setResultList(resultList);
		HttpServletRequest request = Struts2Utils.getRequest();
		String flag = request.getParameter("flag");
		theForm.setFlag(flag);
		// 用以判断所在省份
		location = PropertiesUtil.getString("properties.location", "location");

		return "ah_allHost";

	}

	private List<UnitedTreeObj> initTreeList() {
		List<UnitedTreeObj> treeList = null;
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setType(UnitedConstant.DATACENTER);
		try {
			List<UnitedTreeObj> utreeList = unitedTreeService.queryForTreeList(unitedTreeObj);
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
	 * @Title:获取主机对应用户信息列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String listHostConfig() throws BaseException {
		if (theForm == null) {
			theForm = new HostManageForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		// theForm.reset(mapping, request);
		String eq_id = request.getParameter("eq_id");
		theForm.setID(0);
		TbCloud2HostConfigObj obj = new TbCloud2HostConfigObj();
		obj.setHOSTID(eq_id);
		obj.setHOSTNAME(theForm.getEq_hostname());
		if (theForm.getHostUserName() != null && !"".equals(theForm.getHostUserName())) {
			obj.setHOSTUSERNAME(theForm.getHostUserName());
		}
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		obj.setSTATUS(1);
		List<TbCloud2HostConfigObj> userInfoList = hostInfoService.queryForConfigListByObj(obj);
		theForm.setUserInfoList(userInfoList);
		theForm.setEq_id(eq_id);
		theForm.setFlag("host");
		return "listconfig";
	}

	/**
	 * @Title:增加主机配置请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String addHostConfig() throws BaseException {
		theForm = new HostManageForm();
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		String HOSTID = request.getParameter("HOSTID");
		obj.setEq_id(HOSTID);
		@SuppressWarnings("unused")
		List<TbCloud2HostConfigObj> configList = new ArrayList<TbCloud2HostConfigObj>();
		// TbCloud2HostConfigObj tempObj =
		// hostInfoService.queryByConfigObj(obj);
		// List configHostList = hostInfoService.queryForConfigListByObj(obj);
		List<TbCloud2HostInfoObj> resultList = hostInfoService.queryForListByObj(obj);
		// theForm.setEq_hostname(((TbCloud2HostConfigObj)
		// configHostList.get(0))
		// .getHOSTNAME());
		// theForm.setEq_id(String.valueOf(((TbCloud2HostConfigObj)
		// configHostList
		// .get(0)).getHOSTID()));
		theForm.setEq_id(resultList.get(0).getEq_id());
		theForm.setEq_hostname(resultList.get(0).getEq_hostname());
		theForm.setFlag("host");
		return "addconfig";
	}

	/**
	 * @Title:保存主机配置请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String saveHostConfig() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String HOSTID = request.getParameter("HOSTID");
		TbCloud2HostConfigObj obj = new TbCloud2HostConfigObj();
		// String password = DES3.encrypt(theForm.getPassword()); // 密码加密
		obj.setID(theForm.getID());
		obj.setHOSTID(HOSTID);
		obj.setHOSTUSERNAME(theForm.getHostUserName());
		obj.setHOSTNAME(theForm.getEq_hostname());
		// obj.setSTATUS(Integer.parseInt(theForm.getSTATUS()));
		obj.setHOSRPWD(theForm.getPassword());
		obj.setSPACE(theForm.getSPACE());
		obj.setTYPE(theForm.getTYPE());
		try {
			BeanUtils.copyProperties(obj, theForm);
			// obj.setHOSRPWD(password);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int ret = 0;
		int result = 0;

		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");

		if (theForm.getID() == 0) {
			obj.setHOSTID(HOSTID);
			// if (obj.getTYPE() == 1) {
			obj.setSTATUS(1);
			// }
			ret = hostInfoService.insertByConfigObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增主机配置信息");

		} else {
			obj.setSTATUS(1);
			ret = hostInfoService.updateByConfigObj(obj);
			// theForm.setSTATUS("1");
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改主机配置信息");
		}
		/*
		 * if (ret > 0) { // 修改主机配置信息成功的同时将删除信息插入到主机配置历史记录表
		 * TbBusiHostConfigHisObj busihostConfigHisObj = new
		 * TbBusiHostConfigHisObj(); try {
		 * BeanUtils.copyProperties(busihostConfigHisObj, theForm); } catch
		 * (Exception e) { e.printStackTrace(); } TbSysUserinfoObj tempObj =
		 * (TbSysUserinfoObj) request
		 * .getSession().getAttribute(Constant.USER_SESSION_KEY); //
		 * /busihostConfigHisObj.setUPDATEUSER(tempObj.getNAME()); //
		 * busihostConfigHisObj.setUPDATETYPE("1"); //
		 * busiHostConfigHisService.insertByObj(busihostConfigHisObj); } } if
		 * (ret > 0) { result = 1; } operObj.setRESULT(result);
		 * 
		 * @SuppressWarnings("unused") int retOper =
		 * operationService.insertByObj(operObj);// 写操作日志
		 */
		try {
			response.sendRedirect("resource_listHostConfig.do?eq_id=" + HOSTID);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		// return mapping.findForward("default");

	}

	/**
	 * @Title:修改主机配置请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String modHostConfig() throws BaseException {
		TbCloud2HostConfigObj obj = new TbCloud2HostConfigObj();
		obj.setID(theForm.getID());
		TbCloud2HostConfigObj tempObj = hostInfoService.queryByConfigObj(obj);
		// String password = DES3.decrypt(tempObj.getHOSRPWD());
		theForm.setEq_hostname(tempObj.getHOSTNAME());
		theForm.setHostUserName(tempObj.getHOSTUSERNAME());
		// theForm.setPassword(password);
		theForm.setSPACE(tempObj.getSPACE());
		theForm.setSTATUS(String.valueOf(tempObj.getSTATUS()));
		theForm.setTYPE(tempObj.getTYPE());
		theForm.setEq_id(tempObj.getHOSTID());
		theForm.setPassword(tempObj.getHOSRPWD());
		/*
		 * try { BeanUtils.copyProperties(theForm, tempObj);
		 * theForm.setHOSTUSERNAME_BF(tempObj.getHOSTUSERNAME());
		 * theForm.setTYPE_BF(tempObj.getTYPE()); } catch
		 * (IllegalAccessException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (InvocationTargetException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		Struts2Utils.getRequest().setAttribute("password", tempObj.getHOSRPWD());
		return "modconfig";
	}

	/**
	 * @Title:删除主机配置信息请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String delHostConfig() throws BaseException {
		HttpServletResponse response = Struts2Utils.getResponse();
		TbCloud2HostConfigObj obj = new TbCloud2HostConfigObj();
		int result = 0;
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		@SuppressWarnings("unused")
		TbCloud2HostConfigObj busihostConfigObj = hostInfoService.queryByConfigObj(obj);
		String hostId = busihostConfigObj.getHOSTID();
		TbCloud2HostConfigObj tempHostObj = hostInfoService.queryByConfigObj(busihostConfigObj);
		int ret = hostInfoService.deleteByConfigObj(obj);

		tempHostObj.setSTATUS(2);
		tempHostObj.setEXECUTE_FLAG(0);
		int ret1 = hostInfoService.updateByConfigObj(tempHostObj);
		/*
		 * if (ret > 0) { // 删除主机配置信息成功的同时将删除信息插入到主机配置历史记录表
		 * TbBusiHostConfigHisObj busihostConfigHisObj = new
		 * TbBusiHostConfigHisObj(); try {
		 * BeanUtils.copyProperties(busihostConfigHisObj, busihostConfigObj); }
		 * catch (Exception e) { e.printStackTrace(); } TbSysUserinfoObj tempObj
		 * = (TbSysUserinfoObj) request.getSession()
		 * .getAttribute(Constant.USER_SESSION_KEY); //
		 * busihostConfigHisObj.setUPDATEUSER(tempObj.getNAME()); //
		 * busihostConfigHisObj.setUPDATETYPE("2"); //
		 * busiHostConfigHisService.insertByObj(busihostConfigHisObj); result =
		 * 1; } // 插入操作日志表 TbSysOperationLogObj operObj =
		 * this.getTbSysOperationLogObj(request); operObj.setOPERTYPE(2);
		 * operObj.setMESSAGE("删除主机配置信息"); operObj.setREMARK("");
		 * operObj.setRESULT(result); @SuppressWarnings("unused") int retOper =
		 * operationService.insertByObj(operObj);// 写操作日志
		 */
		try {
			response.sendRedirect("resource_listHostConfig.do?eq_id=" + hostId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		// return mapping.findForward("default");

	}

	/**
	 * @Title:判断主机是否能删除，删除主机先要删除主机配置
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 * @throws IOException
	 */
	public String checkHostCanDel() throws BaseException, IOException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		TbCloud2HostConfigObj obj = new TbCloud2HostConfigObj();
		String hostId = (String) request.getParameter("hostid");
		obj.setHOSTID(hostId);
		List<?> list = hostInfoService.queryForConfigListByObj(obj);
		List<String> jsonArr = new ArrayList<String>();
		JSONArray json = new JSONArray();
		if (list != null && list.size() > 0) {
			jsonArr.add("NO");
		} else {
			jsonArr.add("YES");
		}
		json = JSONArray.fromObject(jsonArr);
		PrintWriterOut.printWirter(response, json.toString());
		return null;

	}

	/**
	 * @Title:验证主机用户是否已经存在
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String queryAjx_username() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		TbCloud2HostConfigObj obj = new TbCloud2HostConfigObj();
		String username = request.getParameter("hostusername");
		String hostid = request.getParameter("hostid");
		obj.setHOSTUSERNAME(username);
		obj.setHOSTID(hostid);
		TbCloud2HostConfigObj objt = hostInfoService.queryByConfigObj(obj);
		try {
			request.setCharacterEncoding("gb2312");// 字符串转换
			// response.setCharacterEncoding("utf-8");//字符串转换
			// PrintWriter out = response.getWriter();
			if (objt == null) {
				// out.print("ok");
				PrintWriterOut.printWirter(response, "ok");
			} else {
				// out.print("no");
				PrintWriterOut.printWirter(response, "no");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: 获得机器历史信息列表
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String listHostHis() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		TbCloud2HostHisObj obj = new TbCloud2HostHisObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.setPagination(this.paginater.initPagination(request));
		List resultList = hostInfoService.queryHisForListByObj(obj);
		theForm.setResultList(resultList);
		return "hosthis";

	}

	/**
	 * 
	 * @Title: 新增主机
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String addHost() throws BaseException {

		if (theForm == null) {
			theForm = new HostManageForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String flag = request.getParameter("flag");
		String flag_hostpool = request.getParameter("flag_hostpool");
		String poolid = request.getParameter("poolid");
		TbCloud2CubinetInfoObj obj = new TbCloud2CubinetInfoObj();
		List cubinetList = leaderViewBusinessService.queryCubListByRoomId(obj);
		theForm.setCubinetList(cubinetList);
		theForm.setHostflag(flag);
		theForm.setFlag(flag_hostpool);
		theForm.setHOST_POOL_ID(poolid);
		return ADD;

	}

	/**
	 * 
	 * @Title: 进入修改页面
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String modHost() throws BaseException {
		if (theForm == null) {
			theForm = new HostManageForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String eq_id = request.getParameter("eq_id");

		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		obj.setEq_id(eq_id);
		TbCloud2CubinetInfoObj tObj = new TbCloud2CubinetInfoObj();
		List cubinetList = leaderViewBusinessService.queryCubListByRoomId(tObj);
		List resultList = hostInfoService.queryForListByObj(obj);
		theForm.setCubinetList(cubinetList);
		obj = (TbCloud2HostInfoObj) resultList.get(0);
		try {
			BeanUtils.copyProperties(theForm, obj);
			// 查询snmp参数
			SnmpParamObj sn = new SnmpParamObj();
			sn.setEntity_id(obj.getEq_id());
			snmp = snmpParamService.queryByObj(sn);
			// 查询ssh参数
			ManageProtocolObj pro = new ManageProtocolObj();
			pro.setEntity_id(obj.getEq_id());
			protocol = manageProtocolService.queryByObj(pro);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 是主机注册还是主机管理的标识
		String flag = request.getParameter("flag");
		String flag_hostpool = request.getParameter("flag_hostpool");
		theForm.setHostflag(flag);
		theForm.setFlag(flag_hostpool);
		// 主机池的ID
		String poolid = request.getParameter("poolid");
		if (poolid != null) {
			theForm.setHOST_POOL_ID(poolid);
		} else {
			theForm.setHOST_POOL_ID(obj.getConnectId());
		}
		return MODIFY;

	}

	/**
	 * 
	 * @Title: 保存主机
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String saveHost() throws BaseException {
		if (theForm == null) {
			theForm = new HostManageForm();
		}
		String eq_id = theForm.getEq_id();
		// 只是主机注册的保存修改还是主机管理的保存标识
		String flag = theForm.getHostflag();
		// 单位转换G->MB
		if (theForm.getStore() != null && !"".equals(theForm.getStore())) {
			theForm.setStore(Integer.parseInt(theForm.getStore().replaceAll(",", "")) * 1024 + "");
		}
		if (theForm.getMem() != null && !"".equals(theForm.getMem())) {
			theForm.setMem(Integer.parseInt(theForm.getMem().replaceAll(",", "")) * 1024 + "");
		}

		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		TbCloud2HostHisObj hisObj = new TbCloud2HostHisObj();

		int ret = 0;
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != eq_id && !"".equals(eq_id)) { // 执行修改操作

			hisObj.setEq_id(theForm.getEq_id());
			hisObj.setOperation(1);// 修改操作
			ret = hostInfoService.insertHostHis(hisObj); // 添加到主机历史信息中
			if (ret != -1) {
				ret = hostInfoService.updateByObj(obj); // 更改主机信息
			}
			// 更新snmp协议等相关信息
			//何辉修改 未录入任何信息
//			snmp.setEntity_id(theForm.getEq_id() + "");
//			snmpParamService.updateByObj(snmp);
//			if(protocol==null){
//				protocol= new ManageProtocolObj();
//			}
//			protocol.setId(RandomUUID.getUuid());
//			protocol.setEntity_id(theForm.getEq_id() + "");
//			manageProtocolService.updateByObj(protocol);
		} else {// 执行新增操作
			int id = hostInfoService.getIdSequence();
			obj.setId(id);
			String cq_id = theForm.getCq_id();
			String device_id = deviceService.getIdSequence();
			obj.setDevice_id(Integer.parseInt(device_id));
			String temp_id = leaderViewBusinessService.queryHostIdByCid(cq_id);
			// if (null != temp_id && !"".equals(temp_id)) {
			// NumberFormat formatter = NumberFormat.getNumberInstance(); //
			// 设置数据格式
			// formatter.setMinimumIntegerDigits(2); // 设置最小长度
			// formatter.setMaximumIntegerDigits(2); // 设置最大长度
			// int hostId = Integer.parseInt(temp_id
			// .substring(cq_id.length() + 1)) + 1;
			// eq_id = cq_id + "_" + formatter.format(hostId);
			//
			// } else {
			// eq_id = cq_id + "_01";
			// }
			obj.setEq_id(id + "");// 设置新增主机编号
			obj.setH_uuid(RandomUUID.getUuid());// 对于非虚拟化的主机，在添加的时候是没有uuid，故此时将id作为uuid,进行统一。
			ret = hostInfoService.insertByObj(obj);
			// 录入snmp相关参数以及相关协议信息-- taoxue修改
			snmp.setId(RandomUUID.getUuid());
			snmp.setEntity_id(id + "");
			snmpParamService.insertByObj(snmp);
			if(protocol==null){
				protocol = new ManageProtocolObj();
			}
			protocol.setId(RandomUUID.getUuid());
			protocol.setEntity_id(id + "");
			manageProtocolService.insertByObj(protocol);

			// 新增成功后，若非admin用户，则往entityuser表插值
			int userId = Integer.parseInt(session.get("id").toString());
			if (userId != 1) {
				VmAuthorityObj authorObj = new VmAuthorityObj();
				// 赋值
				authorObj.setUSERID(userId);
				authorObj.setENTITY_NAME(obj.getEq_name());
				authorObj.setENTITY_TYPE("3"); // 资源类型3代表主机
				authorObj.setENTITY_ID(obj.getH_uuid());
				authorObj.setOPERAUTHORITY("0"); // 给与全部的权限
				authorObj.setTYPE(obj.getEq_type());
				// 执行插入操作
				ret = vmAuthorityService.insertByObj(authorObj);

			}
		}
		if (flag.equals("allhost")) {
			// 主机管理中
			return "hostsave";
		} else {
			// 主机注册
			return "save";
		}

	}

	/**
	 * 
	 * @Title: 删除主机
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String delHost() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String eq_id = request.getParameter("eq_id");
		// 主机管理还是主机注册的删除功能的标识
		String flag = request.getParameter("flag");

		TbCloud2HostInfoObj obj = null;
		TbCloud2HostHisObj hisObj = null;
		if (null != eq_id && !"".equals(eq_id)) {
			obj = new TbCloud2HostInfoObj();
			hisObj = new TbCloud2HostHisObj();
			obj.setEq_id(eq_id);
			hisObj.setEq_id(eq_id);
			hisObj.setOperation(0); // 设置操作类型为删除
			int ret = hostInfoService.insertHostHis(hisObj); // 添加到主机历史信息中
			if (ret != -1) {
				ret = hostInfoService.deleteByObj(obj); // 删除主机信息
				// 删除snmp协议等相关信息
				SnmpParamObj sn = new SnmpParamObj();
				sn.setEntity_id(eq_id);
				snmpParamService.deleteByObj(sn);
				ManageProtocolObj pro = new ManageProtocolObj();
				pro.setEntity_id(eq_id);
				manageProtocolService.deleteByObj(pro);
			}
		}
		if (flag.equals("allhost")) {
			return null;
		} else {
			return "del";
		}
	}

	/**
	 * 
	 * @Title: 查询已经监控的主机列表
	 * @Copyright: Copyright (c) 2012-04-06
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String listMonitorHost() throws BaseException {

		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		String eq_type = (String) request.getParameter("eq_type");
		if (eq_type != null && !"".equals(eq_type)) {
			obj.setEq_type(eq_type);
		}

		List<TbCloud2HostInfoObj> resultList = hostInfoService.queryMonitorHost(obj);

		List<TbCloud2HostInfoObj> hostList1 = new ArrayList<TbCloud2HostInfoObj>();
		List<TbCloud2HostInfoObj> hostList2 = new ArrayList<TbCloud2HostInfoObj>();
		List<TbCloud2HostInfoObj> hostList3 = new ArrayList<TbCloud2HostInfoObj>();
		List<TbCloud2HostInfoObj> hostList4 = new ArrayList<TbCloud2HostInfoObj>();
		if (null != resultList && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				TbCloud2HostInfoObj hostObj = resultList.get(i);
				if ("1".equals(hostObj.getEq_type())) { // 设备为IBM小机
					hostList1.add(hostObj);
				} else if ("2".equals(hostObj.getEq_type())) { // 设备为IBM刀片
					hostList2.add(hostObj);
				} else if ("3".equals(hostObj.getEq_type())) { // 设备为HP普通刀片
					hostList3.add(hostObj);
				} else if ("4".equals(hostObj.getEq_type())) { // 设备为HP X86刀片
					hostList4.add(hostObj);
				}
			}
		}
		theForm.setHostList1(hostList1);
		theForm.setHostList2(hostList2);
		theForm.setHostList3(hostList3);
		theForm.setHostList4(hostList4);
		return "";

	}

	/**
	 * 
	 * @Title: 查询未监控的主机列表
	 * @Copyright: Copyright (c) 2012-04-06
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String listNOtMonitorHost() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();

		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		String eq_type = (String) request.getParameter("eq_type");
		if (eq_type != null && !"".equals(eq_type)) {
			obj.setEq_type(eq_type);
		}

		List<TbCloud2HostInfoObj> resultList = hostInfoService.queryNotMonitorHost(obj);

		List<TbCloud2HostInfoObj> hostList1 = new ArrayList<TbCloud2HostInfoObj>();
		List<TbCloud2HostInfoObj> hostList2 = new ArrayList<TbCloud2HostInfoObj>();
		List<TbCloud2HostInfoObj> hostList3 = new ArrayList<TbCloud2HostInfoObj>();
		List<TbCloud2HostInfoObj> hostList4 = new ArrayList<TbCloud2HostInfoObj>();
		if (null != resultList && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				TbCloud2HostInfoObj hostObj = resultList.get(i);
				if ("1".equals(hostObj.getEq_type())) { // 设备为IBM小机
					hostList1.add(hostObj);
				} else if ("2".equals(hostObj.getEq_type())) { // 设备为IBM刀片
					hostList2.add(hostObj);
				} else if ("3".equals(hostObj.getEq_type())) { // 设备为HP普通刀片
					hostList3.add(hostObj);
				} else if ("4".equals(hostObj.getEq_type())) { // 设备为HP X86刀片
					hostList4.add(hostObj);
				}
			}
		}
		theForm.setHostList1(hostList1);
		theForm.setHostList2(hostList2);
		theForm.setHostList3(hostList3);
		theForm.setHostList4(hostList4);
		return "";

	}

	/**
	 * @Title: checkHostStatus
	 * @Description: 检测主机状态
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-18 下午5:00:03
	 */
	public void checkHostStatus() {
		JSONArray ja = new JSONArray();
		TbCloud2HostInfoObj tbCloud2HostInfoObj = new TbCloud2HostInfoObj();
		boolean flag = false;
		if (hostids != null) {
			String[] eq_idstr = hostids.split(",");
			for (String eq_id : eq_idstr) {
				tbCloud2HostInfoObj.setEq_id(eq_id);
				TbCloud2HostInfoObj obj = hostInfoService.queryByObj(tbCloud2HostInfoObj);
				JSONObject jo = new JSONObject();
				jo.put("hostid", eq_id);
				String ipaddress = obj.getEq_ip();
				if (ipaddress != null && !"".equals(ipaddress)) {
					if (SshPingIp.pingOtherServer(ipaddress)) {
						if (!"1".equals(obj.getSTATUS())) {
							obj.setSTATUS("1");
							flag = true;
						}
					} else {
						if ("1".equals(obj.getSTATUS())) {
							obj.setSTATUS("3");
							flag = true;
						}
					}
					if (flag) {
						hostInfoService.updateByObj(obj);
					}
					jo.put("status", obj.getSTATUS());
				}
				ja.add(jo);
			}
		}
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(ja.toString());
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, ja.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title:判断主机IP是否已经生成
	 * @Copyright:Copyright (c) Aug 17, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public void checkIpExists() {
		HttpServletRequest request = Struts2Utils.getRequest();
		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		// 获取入参
		String flag = request.getParameter("flag");
		// 判断主机名称是否重复
		if (flag.equals("name")) {
			String name = request.getParameter("name");
			obj.setEq_name(name);
		} else {
			// 判断主机IP是否重复
			String ip = request.getParameter("ip");
			obj.setEq_ip(ip);
		}

		String eid = request.getParameter("eq_id");

		// 查询是否存在
		TbCloud2HostInfoObj resobj = hostInfoService.queryByObj(obj);
		if (resobj != null) {
			String reseid = resobj.getEq_id();
			if ("".equals(eid)) {
				showErrorMsg(1);
			} else {
				if (eid.equals(reseid)) {
					showErrorMsg(0);
				} else {
					showErrorMsg(1);
				}
			}
		} else {
			showErrorMsg(0);
		}
	}

	/**
	 * 
	 * @Title:判断主机名称和主机IP是否已经生成
	 * @Copyright: 2013-09-13
	 * @Company: si-tech
	 * @author: yangl
	 * @version: 1.0
	 */
	public void checkNameExists() {
		HttpServletRequest request = Struts2Utils.getRequest();
		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		String id = request.getParameter("eq_id");
		String name = request.getParameter("name");
		if ("".equals(id) || id == null) {
			obj.setEq_name(name);
			// 查询是否存在
			TbCloud2HostInfoObj resobj = hostInfoService.queryByObj(obj);
			if (resobj != null) {
				String resName = resobj.getEq_name();
				if ("".equals(resName)) {
					showErrorMsg(0);
				} else {
					if (name.equals(resName)) {
						showErrorMsg(1);
					} else {
						showErrorMsg(0);
					}
				}
			} else {
				showErrorMsg(0);
			}
		} else {
			obj.setEq_name(name);
			obj.setEq_id(id);
			int count = hostInfoService.validateNameAndIP(obj);
			if (count > 0) {
				showErrorMsg(1);
			} else {
				showErrorMsg(0);
			}
		}
	}

	public void checkExists() {
		HttpServletRequest request = Struts2Utils.getRequest();
		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		String id = request.getParameter("eq_id");
		String ip = request.getParameter("ip");
		if ("".equals(id) || id == null) {
			// 添加时查询是否存在
			obj.setEq_ip(ip);
			TbCloud2HostInfoObj resobj = hostInfoService.queryByObj(obj);
			if (resobj != null) {
				String resIp = resobj.getEq_ip();
				if ("".equals(resIp)) {
					showErrorMsg(0);
				} else {
					if (ip.equals(resIp)) {
						showErrorMsg(1);
					} else {
						showErrorMsg(0);
					}
				}
			} else {
				showErrorMsg(0);
			}
		} else {
			obj.setEq_ip(ip);
			obj.setEq_id(id);
			int count = hostInfoService.validateNameAndIP(obj);
			if (count > 0) {
				showErrorMsg(1);
			} else {
				showErrorMsg(0);
			}
		}
	}

	/**
	 * @Title: viewDeviceDetail
	 * @Description: 查看主机所关联存储的详细信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-24 下午9:26:05
	 */
	public String viewDeviceDetail() {
		if (storeDeviceObj == null) {
			storeDeviceObj = new StoreDeviceObj();
		}
		storeDeviceObj.setId(deviceId);
		storeDeviceObj = storeDeviceService.queryByObj(storeDeviceObj);
		NumberFormat nf = NumberFormat.getIntegerInstance(Locale.CHINA);
		nf.setGroupingUsed(false);
		storeDeviceObj.setStoreBlockTotalSize(nf.format(Double.parseDouble(StringUtils
				.isEmpty(storeDeviceObj.getStoreBlockTotalSize()) ? "0" : storeDeviceObj
				.getStoreBlockTotalSize())));
		storeDeviceObj.setStoreBlockFreeSpace(nf.format(Double.parseDouble(StringUtils
				.isEmpty(storeDeviceObj.getStoreBlockFreeSpace()) ? "0" : storeDeviceObj
				.getStoreBlockFreeSpace())));
		storeDeviceObj.setPurchaseSpace(nf.format(Double.parseDouble(StringUtils
				.isEmpty(storeDeviceObj.getPurchaseSpace()) ? "0" : storeDeviceObj
				.getPurchaseSpace())));
		return "showHostDevice";
	}

	/**
	 * @Title: showHostDevicePie
	 * @Description: 查看主机所关联存储的饼图
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-24 下午9:26:05
	 */
	public void showHostDevicePie() {
		if (storeDeviceObj == null) {
			storeDeviceObj = new StoreDeviceObj();
		}
		storeDeviceObj.setId(deviceId);
		// storeDeviceObj.setType(1);
		Map<String, Double> map = storeDeviceService.getStoreMap(storeDeviceObj);
		String deviceXml = "";
		if (type != null && "1".equals(type)) {
			// 有效容量维度（接入、未接入）
			deviceXml = storeDeviceService.buildPieXml(map, "valid", "70");
		} else if (type != null && "2".equals(type)) {
			// 接入容量维度（分配、未分配）
			deviceXml = storeDeviceService.buildPieXml(map, "mount", "70");
		} else {
			// 分配容量维度（使用、未使用）
			deviceXml = storeDeviceService.buildPieXml(map, "allo", "70");
		}
		try {
			response.setCharacterEncoding("UTF-8");
			// PrintWriter pw = response.getWriter();
			// pw.write(deviceXml);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, deviceXml);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Title: updateHostState
	 * @Description: 修改电源状态
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-8 下午5:21:27
	 */
	public String addHostInfo(){
		//SC增加关联tb_os_host中的管理IP和用户名
		HostModel _host = new HostModel();
		_host.setEq_id(theForm.getEq_id());
		_host = osHostService.queryForObject(_host);		
		theForm.setEq_ip(_host.getMge_console_ip());
		theForm.setHostUserName(_host.getMge_console_username());
		return "addHostInfo";
	}
	
	/**
	 * 
	 * @Title: updateHostState
	 * @Description: 修改电源状态
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-8 下午5:33:30
	 */
	public void updateHostState(){
		if(theForm == null){
			theForm = new HostManageForm();
		}
		TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
		hostObj.setEq_id(theForm.getEq_id());
		String res = "";
		/**
		 * 请修改-----要发送到后台在连接cobbler服务器，前台没有相关类，目前写死，请jiangtao在修改-shijc
		 */
		SshConnection conn = SSHUtil.getSshConnection("10.7.75.34",22,"root","p@ssw0rd");
		res = "-1";
		if("start".equals(oper)){
			String command = "ipmitool -I lanplus -H " + theForm.getEq_ip() + " -U " + theForm.getHostUserName() + " -P "
					+ theForm.getPassword() + " power on";
			String result = "";// 用来判断成功失败的结果
			System.out.println("上电命令是：" + command);
			try {
				SSHUtil.sendShellToSSHChannel(conn, true, command);
				res="1";
			} catch (Exception e) {}
			hostObj.setSTATUS("1");
		}else if("stop".equals(oper)){
			String command = "ipmitool -I lanplus -H " + theForm.getEq_ip() + " -U " + theForm.getHostUserName() + " -P "
					+ theForm.getPassword() + " power off";
			String result = "";// 用来判断成功失败的结果
			System.out.println("下电命令是：" + command);
			try {
				SSHUtil.sendShellToSSHChannel(conn, true, command);
				res="1";
			} catch (Exception e) {}
			hostObj.setSTATUS("2");
		}else if("restart".equals(oper)){
			String command = "ipmitool -I lanplus -H " + theForm.getEq_ip() + " -U " + theForm.getHostUserName() + " -P "
					+ theForm.getPassword() + " power reset";
			String result = "";// 用来判断成功失败的结果
			System.out.println("重启命令是：" + command);
			try {
				SSHUtil.sendShellToSSHChannel(conn, true, command);
				res="1";
			} catch (Exception e) {}
			hostObj.setSTATUS("1");
		}
		System.out.println("执行结果:" + res);
		if("1".equals(res)){
			hostInfoService.updateInterByObj(hostObj);
		}	
		response.setContentType("text/html;charset=UTF-8");
		PrintWriterOut.printWirter(response, res);
	}
	
	private String exeCommand(String command, String result) {
		String response = "-1";
		try {
			Runtime rt = Runtime.getRuntime();
			Process pro = rt.exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			String s = null;
			List<String> list = new ArrayList<String>();
			while ((s = br.readLine()) != null) {
				list.add(s);
			}
			if (s == null) {
				BufferedReader error = new BufferedReader(new InputStreamReader(pro.getErrorStream()));
				while ((s = error.readLine()) != null) {
					list.add(s);
				}
			}
			for (String str : list) {
				if (str.contains(result)) {
					response = "1";
				}
			}
			br.close();
		} catch (IOException e) {
		}
		return response;
	}
	
	/**
	 * 
	 * @Title: showConsole
	 * @Description: 展示控制台
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-14 下午6:25:44
	 */
	public String showConsole(){
		return "showConsole";
	}
	
	public String collectConfig(){		
		System.out.println("eq_id:" + eq_id);
		if(eq_id.contains(",")){
			eq_id = eq_id.substring(0,eq_id.length() - 1);
		}
		
		String ret = "-1";
		try {
			rabbitMQUtil.publishMessage("", "app.automation.collect.queue", eq_id);
			ret = "1";
		} catch (RabbitMQException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriterOut.printWirter(response, ret);
		return null;
	}
	
	public HostManageForm getTheForm() {
		return theForm;
	}

	public void setTheForm(HostManageForm theForm) {
		this.theForm = theForm;
	}

	public HostInfoService getHostInfoService() {
		return hostInfoService;
	}

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public LeaderViewBusinessService getLeaderViewBusinessService() {
		return leaderViewBusinessService;
	}

	public void setLeaderViewBusinessService(LeaderViewBusinessService leaderViewBusinessService) {
		this.leaderViewBusinessService = leaderViewBusinessService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public String getHostID() {
		return hostID;
	}

	public void setHostID(String hostID) {
		this.hostID = hostID;
	}

	public void setDataStoreService(DataStoreService dataStoreService) {
		this.dataStoreService = dataStoreService;
	}

	public void setXenStoreService(XenStoreService xenStoreService) {
		this.xenStoreService = xenStoreService;
	}

	public VmAuthorityService getVmAuthorityService() {
		return vmAuthorityService;
	}

	public void setVmAuthorityService(VmAuthorityService vmAuthorityService) {
		this.vmAuthorityService = vmAuthorityService;
	}

	public String getHostids() {
		return hostids;
	}

	public void setHostids(String hostids) {
		this.hostids = hostids;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public StoreDeviceObj getStoreDeviceObj() {
		return storeDeviceObj;
	}

	public void setStoreDeviceObj(StoreDeviceObj storeDeviceObj) {
		this.storeDeviceObj = storeDeviceObj;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SnmpParamObj getSnmp() {
		return snmp;
	}

	public void setSnmp(SnmpParamObj snmp) {
		this.snmp = snmp;
	}

	public ManageProtocolObj getProtocol() {
		return protocol;
	}

	public void setProtocol(ManageProtocolObj protocol) {
		this.protocol = protocol;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getEq_id() {
		return eq_id;
	}

	public void setEq_id(String eq_id) {
		this.eq_id = eq_id;
	}
	
}
