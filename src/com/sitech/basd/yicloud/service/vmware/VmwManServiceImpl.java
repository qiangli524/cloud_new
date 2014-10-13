package com.sitech.basd.yicloud.service.vmware;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sitech.basd.common.ResponseCode;
import com.sitech.basd.exception.CloudUnitedException;
import com.sitech.basd.resource.dao.template.TemManDao;
import com.sitech.basd.resource.domain.template.TemManObj;
import com.sitech.basd.sxcloud.cloud.dao.image.TbImageInfoDao;
import com.sitech.basd.sxcloud.cloud.dao.net.TbIpDao;
import com.sitech.basd.sxcloud.cloud.dao.net.TbNetDao;
import com.sitech.basd.sxcloud.cloud.dao.vmhost.VMHostDao;
import com.sitech.basd.sxcloud.cloud.domain.image.TbCloud2ImageInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.image.ImageService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.rsmu.config.Constant;
import com.sitech.basd.sxcloud.rsmu.dao.hostmanage.TbBusiHostConfigDao;
import com.sitech.basd.sxcloud.rsmu.dao.hostmanage.TbBusiHostObjDao;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.dao.datastore.DataStoreDao;
import com.sitech.basd.yicloud.dao.entitytree.EntityTreeDao;
import com.sitech.basd.yicloud.dao.kvm.KvmManDao;
import com.sitech.basd.yicloud.dao.nic.NicRelationDao;
import com.sitech.basd.yicloud.dao.nic.VirtualNicDao;
import com.sitech.basd.yicloud.dao.script.ScriptDao;
import com.sitech.basd.yicloud.dao.templettree.TempletTreeDao;
import com.sitech.basd.yicloud.domain.datastore.DataStoreInfo;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.basd.yicloud.domain.entitytree.EntityConObj;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.domain.kvm.TbYicloudDeviceHealthObj;
import com.sitech.basd.yicloud.domain.kvm.TbYicloudOsTypeObj;
import com.sitech.basd.yicloud.domain.kvm.VMManagerObj;
import com.sitech.basd.yicloud.domain.nic.NicRelationObj;
import com.sitech.basd.yicloud.domain.nic.VirtualNicObj;
import com.sitech.basd.yicloud.domain.script.ScriptConObj;
import com.sitech.basd.yicloud.domain.script.ScriptObj;
import com.sitech.basd.yicloud.domain.templettree.TempletTreeObj;
import com.sitech.basd.yicloud.domain.vmauthority.VmAuthorityObj;
import com.sitech.basd.yicloud.service.vmauthority.VmAuthorityService;
import com.sitech.basd.yicloud.util.CfgUtil;
import com.sitech.basd.yicloud.util.HttpClientUtil;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.JSONUtil;
import com.sitech.basd.yicloud.util.ParamParser;
import com.sitech.basd.yicloud.util.TypeConstant;
import com.sitech.basd.yicloud.web.vmsyndata.constant.CompConstant;
import com.sitech.basd.yicloud.web.vmware.form.VmwManForm;
import com.sitech.ws.Operation;
import com.sitech.ws.web.NoticeUtil;

/**
 * 
 * <p>
 * Title: VMWManServiceImpl
 * </p>
 * <p>
 * Description: vmware管理Service实现类
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
 * @createtime Jul 25, 2012 9:04:01 AM
 * 
 */
public class VmwManServiceImpl implements VmwManService {
	private static Logger logger = Logger.getLogger(VmwManServiceImpl.class);
	private static final String RESPONSE_CODE = "responseCode";
	private static final String MESS = "responseRemark";
	private TbImageInfoDao tbImageInfoDao;
	private EntityTreeDao entityTreeDao;
	private KvmManDao kvmManDao;
	private TbIpDao tbIpDao;
	private TbBusiHostObjDao tbBusiHostObjDao;
	private VMHostDao vmHostDao;
	private TbBusiHostConfigDao tbBusiHostConfigDao;
	private OperateVMHostInfo operateVMHostInfo;
	private TempletTreeDao templetTreeDao;
	private ScriptDao scriptDao;
	private ImageService imageService;
	private HostInfoService hostInfoService;
	private VirtualNicDao virtualNicDao;
	private NicRelationDao nicRelationDao;
	private TemManDao temManDao;
	@Autowired
	private VmAuthorityService vmAuthorityService;
	@Autowired
	private TbNetDao tbNetDao;
	@Autowired
	private DataStoreDao dataStoreDao;

	public void setTemManDao(TemManDao temManDao) {
		this.temManDao = temManDao;
	}

	public void setVirtualNicDao(VirtualNicDao virtualNicDao) {
		this.virtualNicDao = virtualNicDao;
	}

	public void setNicRelationDao(NicRelationDao nicRelationDao) {
		this.nicRelationDao = nicRelationDao;
	}

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	public void setScriptDao(ScriptDao scriptDao) {
		this.scriptDao = scriptDao;
	}

	public void setTempletTreeDao(TempletTreeDao templetTreeDao) {
		this.templetTreeDao = templetTreeDao;
	}

	public void setTbBusiHostObjDao(TbBusiHostObjDao tbBusiHostObjDao) {
		this.tbBusiHostObjDao = tbBusiHostObjDao;
	}

	public void setVmHostDao(VMHostDao vmHostDao) {
		this.vmHostDao = vmHostDao;
	}

	public void setTbBusiHostConfigDao(TbBusiHostConfigDao tbBusiHostConfigDao) {
		this.tbBusiHostConfigDao = tbBusiHostConfigDao;
	}

	public void setTbIpDao(TbIpDao tbIpDao) {
		this.tbIpDao = tbIpDao;
	}

	public void setTbImageInfoDao(TbImageInfoDao tbImageInfoDao) {
		this.tbImageInfoDao = tbImageInfoDao;
	}

	public void setKvmManDao(KvmManDao kvmManDao) {
		this.kvmManDao = kvmManDao;
	}

	/**
	 * @Title:创建VMware虚拟机
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public String createVMwareVirtual(String ID, VMManagerObj vObj) {
		// 设置进度条进度
		Constant.PROCESS_LEVEL = 10;
		/** 暂未使用,创建裸机时没有IP地址 */
		// 占用IP
		/*
		 * String ip = vObj.getIP(); TbCloud2IpInfoObj ipObj = new
		 * TbCloud2IpInfoObj(); ipObj.setISUSED("1"); ipObj.setIPADDRESS(ip); //
		 * 更新IP使用状态 tbIpDao.updateIPStat(ipObj);
		 */
		/** 查询主机在哪个数据中心下 */
		// 判断是主机上的操作还是集群上的操作
		String dcName = null;
		int dcId = -1;
		int clusterId = -1;
		String clName = null;
		String hostName = "";
		if (vObj.getTYPE().equals("1")) {// 从主机上创建裸机
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("host_code", ID);
			List<Map<String, Object>> resList = entityTreeDao.queryDcClHostRelat(map);
			if (resList != null && resList.size() > 0) {
				dcName = (String) resList.get(0).get(CompConstant.VDC_CODE);
				clName = (String) resList.get(0).get(CompConstant.CL_CODE);
				hostName = (String) resList.get(0).get(CompConstant.HOST_CODE);
				EntityTreeObj enObj = new EntityTreeObj();
				enObj.setEntityId(clName);
				enObj = entityTreeDao.queryTreeNode(enObj);// 查询集群信息
				dcId = enObj.getParentId();
				clusterId = enObj.getId();
			}
		} else {// 从集群上创建虚拟机
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cl_id", ID);
			List<Map<String, Object>> resList = entityTreeDao.queryDcClRelat(map);
			if (resList != null && resList.size() > 0) {
				dcName = (String) resList.get(0).get(CompConstant.VDC_CODE);
				clName = (String) resList.get(0).get(CompConstant.CL_CODE);
				EntityTreeObj enObj = new EntityTreeObj();
				enObj.setEntityId(clName);
				enObj = entityTreeDao.queryTreeNode(enObj);// 查询集群信息
				dcId = enObj.getParentId();
				clusterId = enObj.getId();
			}
		}

		String result = "";
		String fail = null;
		String NAME_EH = vObj.getNAME_EN();// 虚拟机名称
		String CURRENT_CPU = vObj.getCURRENTCPU();
		String CURRENT_MEMORY = vObj.getCURRENTMEMORY();
		String datastoreName = vObj.getDatastoreName();// 存储名称
		String guestOsId = vObj.getGuestOsId();// 客户机操作系统类型
		String diskMode = "persistent";// 硬盘模式
		String netName = "VM Network";// 网络名称
		String nicName = "Network Adapter 1";// 网卡名称
		long diskSizeKB = Long.parseLong(vObj.getSTORAGE());
		String code = null;
		// 创建VMWare虚拟机
		if (vObj.getTYPE().equals("1")) {
			String createVMUrl = null;
			try {
				createVMUrl = "/vmware/domain/create/[dcName:" + dcName + "].[hostName:" + hostName
						+ "].[clName:" + clName + "].[vmName:"
						+ URLEncoder.encode(NAME_EH, "utf-8") + "].[memorySizeMB:" + CURRENT_MEMORY
						+ "].[cupCount:" + CURRENT_CPU + "].[datastoreName:" + datastoreName
						+ "].[diskSizeKB:" + diskSizeKB + "].[diskMode:" + diskMode
						+ "].[guestOsId:" + guestOsId.trim() + "].[netName:" + netName
						+ "].[nicName:" + nicName + "]/";
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String createResult = InvokeUtil.invoke(createVMUrl);
			Map ps = ParamParser.makeup(createResult);
			result = (String) ps.get(RESPONSE_CODE);
			if (ps.size() > 0) {
				code = (String) ps.get(ResponseCode.CODE);
				fail = (String) ps.get(ResponseCode.RESPONSEREMARK);
			}
		} else {// 选择集群，根据集群的DRS不选择主机创建虚拟机
			String createUrl = "/vmware/domain/create/[dcName:" + dcName + "].[clName:" + clName
					+ "].[vmName:" + NAME_EH + "].[memorySizeMB:" + CURRENT_MEMORY + "].[cupCount:"
					+ CURRENT_CPU + "].[datastoreName:" + datastoreName + "].[diskSizeKB:"
					+ diskSizeKB + "].[diskMode:" + diskMode + "].[guestOsId:" + guestOsId.trim()
					+ "].[netName:" + netName + "].[nicName:" + nicName + "]/";
			String createResult = InvokeUtil.invoke(createUrl);
			Map ps = ParamParser.makeup(createResult);
			if (ps.size() > 0) {
				result = (String) ps.get(RESPONSE_CODE);
				fail = (String) ps.get(ResponseCode.RESPONSEREMARK);
			}
			if (result.equals(ResponseCode.SUCCESS)) {
				code = (String) ps.get(ResponseCode.CODE);
				// 调用接口查询虚拟机创建在哪个主机上
				String getMsg = "/vmware/domain/msg/[vmName:" + code + "]/";
				String getResult = InvokeUtil.invoke(getMsg);
				Map map = ParamParser.makeup(getResult);
				// hostName = (String) map.get("hostName");
				hostName = (String) map.get("hostCode");
				code = (String) ps.get(ResponseCode.CODE);
				
				//插入权限
				VmAuthorityObj authObj = new VmAuthorityObj();
				HttpSession session = Struts2Utils.getRequest().getSession();
				authObj.setUSERID(Integer.parseInt(session.getAttribute("id").toString()));
				authObj.setENTITY_NAME(NAME_EH);
				authObj.setENTITY_ID(code);
				authObj.setTYPE(TypeConstant.VMWARE_VM);
				authObj.setOPERAUTHORITY("0");
				vmAuthorityService.insertByObj(authObj);
			}
		}
		if (result.equals("1")) {
			/** 通知 */
			try {
				NoticeUtil.getInstance().updateCMDBHost(hostName, Operation.OPER_REL_MODI);// cmdb
			} catch (Exception e) {

			}
			try {
				NoticeUtil.getInstance().addVM(code);
			} catch (Exception ex) {

			}
			// 挂载DVD驱动器
			String mountCdUrl = "/vmware/domain/reconfig/[vmName:" + code
					+ "].[cd:cd].[op:Add].[value:" + code + "Cd]/";
			String mountResult = InvokeUtil.invoke(mountCdUrl);

			/** cmdb接口发送数据 */
			/*
			 * JSONObject json = new JSONObject(); json.put("VH_CPU",
			 * CURRENT_CPU); json.put("VH_ID", VH_ID); json.put("VH_IP", "");
			 * json.put("VH_DESC", ""); json.put("VH_MEM", CURRENT_MEMORY);
			 * json.put("VH_NAME", NAME_EH); json.put("VH_STAT", "");
			 * json.put("VH_STORAGE", String.valueOf(diskSizeKB * 1024));
			 * json.put("VH_TYPE", TypeConstant.VMWARE_VM); InvokeMonUtil .post(
			 * "http://172.21.104.129:8080/cmdb_1.0.7/messagebroker/vmhost/addVmhosts" ,
			 * json);
			 */
			// 插入虚拟机信息
			VMHostObj vmObj = new VMHostObj();

			// 向cmdb接口发送数据并返回cId,返回的值插入数据库
			/*
			 * String cId =
			 * operateVMHostInfo.postAddVMHostInfoForCMDBInte(VH_ID, NAME_EH,
			 * "", "", CURRENT_CPU, CURRENT_MEMORY, String .valueOf(diskSizeKB *
			 * 1024), "", TypeConstant.VMWARE_VM); if (cId != null &&
			 * !"".equals(cId)) { vmObj.setVH_STATUS("1"); // cmdb接口数据发送成功 }
			 */
			int vmId = vmHostDao.queryVhostIdSequence();
			vmObj.setVH_CPU(CURRENT_CPU);// cpu
			vmObj.setVH_MEM(CURRENT_MEMORY);// 内存
			vmObj.setVH_STORAGE(String.valueOf(diskSizeKB * 1024));// 存储
			vmObj.setVH_SYSTEM(vObj.getTYPENAME());// 操作系统
			vmObj.setVH_NAME(NAME_EH);
			vmObj.setVH_STAT("0");
			TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
			hostObj.setH_uuid(hostName);
			List lst = hostInfoService.queryForListByObj(hostObj);
			if (lst.size() > 0) {
				hostObj = (TbCloud2HostInfoObj) lst.get(0);
				vmObj.setEQ_ID(hostObj.getEq_id());
			}
			vmObj.setID(vmId);// 虚拟机唯一标识
			vmObj.setVH_UUID(code);// code,唯一标识
			String VH_ID = createNewVhostId2(vmObj.getEQ_ID());
			vmObj.setVH_ID(VH_ID);
			vmObj.setVH_TYPE("1");// vmhost_info表中vmware虚拟机类型为1
			// vmObj.setVH_CID(cId);
			int ret3 = vmHostDao.insertByVMhostObj(vmObj);

			// 向entity——tree中插入数据
			EntityTreeObj eObj = new EntityTreeObj();
			if (vObj.getTYPE().equals(TypeConstant.VMWARE_HOST)) {
				eObj.setEntityId(String.valueOf(ID));// 主机的entityId
			} else {
				/** 查询VM关系 */
				String vm_param = "/vmware/entity/singlerelation/[type:" + CompConstant.VM
						+ "].[code:" + code + "]/";
				String vm = InvokeUtil.invoke(vm_param);
				JSONObject clus = JSONObject.fromObject(vm);
				String host_code = (String) clus.get(CompConstant.HOST_CODE);
				eObj.setEntityId(host_code);
			}
			EntityTreeObj e = entityTreeDao.queryTreeNode(eObj);// 通过主机的entityId查询其id
			eObj.setParentId(e.getId());
			eObj.setName(NAME_EH);
			eObj.setType(TypeConstant.VMWARE_VM);
			eObj.setEntityId(code);
			int ret4 = entityTreeDao.insertTreeNode(eObj);

			// 向entity-Con中插入数据
			EntityConObj ecObj = new EntityConObj();
			ecObj.setDataCenterId(dcId);
			ecObj.setClusterId(clusterId);
			ecObj.setType(Integer.parseInt(TypeConstant.VMWARE_VM));
			ecObj.setEntityId(code);
			ecObj.setName(NAME_EH);
			int ret5 = entityTreeDao.insertConObj(ecObj);
			Constant.PROCESS_LEVEL = 100;
			try {
				Thread.sleep(1100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			// 向库中插入虚拟机网络相关的关系
			String net_param = "/vmware/domain/synVCenterTemp/[TYPE:HOST].[CODE:" + hostName + "]/";
			String net = InvokeUtil.invoke(net_param);
			List<JSONObject> netjo = JSONArray.toList(JSONArray.fromObject(net), JSONObject.class);
			List<VirtualNicObj> vnicLst = new ArrayList<VirtualNicObj>();
			List<NicRelationObj> pg_vmLst = new ArrayList<NicRelationObj>();
			List<NicRelationObj> vnic_vmLst = new ArrayList<NicRelationObj>();
			List<NicRelationObj> vnic_nicLst = new ArrayList<NicRelationObj>();
			List<NicRelationObj> vnic_pgLst = new ArrayList<NicRelationObj>();
			for (JSONObject jo : netjo) {
				if (jo.get("TYPE") != null) {
					if (jo.getString("TYPE").equals("VNIC")) {
						VirtualNicObj vNicObj = new VirtualNicObj();
						vNicObj.setName(jo.get("NAME") == null ? "" : jo.getString("NAME"));
						vNicObj.setIpAddress(jo.get("IPADDRESS") == null ? "" : jo
								.getString("IPADDRESS"));
						vNicObj.setUuid(jo.get("CODE") == null ? "" : jo.getString("CODE"));
						vnicLst.add(vNicObj);
					}
				} else if (jo.get("RELATIONSHIP") != null) {
					// 获取虚拟机网络关联关系
					NicRelationObj nicRelationObj = new NicRelationObj();
					if (jo.getString("RELATIONSHIP").equals("VNIC_VM")) {
						nicRelationObj.setType(jo.get("RELATIONSHIP") == null ? "" : jo
								.getString("RELATIONSHIP"));
						nicRelationObj.setToUuid(jo.get("TO_CODE") == null ? "" : jo
								.getString("TO_CODE"));
						nicRelationObj.setFromUuid(jo.get("FROM_CODE") == null ? "" : jo
								.getString("FROM_CODE"));
						vnic_vmLst.add(nicRelationObj);
					} else if (jo.getString("RELATIONSHIP").equals("PORTGROUP_VM")) {
						nicRelationObj.setType(jo.get("RELATIONSHIP") == null ? "" : jo
								.getString("RELATIONSHIP"));
						nicRelationObj.setToUuid(jo.get("TO_CODE") == null ? "" : jo
								.getString("TO_CODE"));
						nicRelationObj.setFromUuid(jo.get("FROM_CODE") == null ? "" : jo
								.getString("FROM_CODE"));
						pg_vmLst.add(nicRelationObj);
					} else if (jo.getString("RELATIONSHIP").equals("VNIC_NIC")) {
						nicRelationObj.setType(jo.get("RELATIONSHIP") == null ? "" : jo
								.getString("RELATIONSHIP"));
						nicRelationObj.setToUuid(jo.get("TO_CODE") == null ? "" : jo
								.getString("TO_CODE"));
						nicRelationObj.setFromUuid(jo.get("FROM_CODE") == null ? "" : jo
								.getString("FROM_CODE"));
						vnic_nicLst.add(nicRelationObj);
					} else if (jo.getString("RELATIONSHIP").equals("VNIC_PORTGROUP")) {
						nicRelationObj.setType(jo.get("RELATIONSHIP") == null ? "" : jo
								.getString("RELATIONSHIP"));
						nicRelationObj.setToUuid(jo.get("TO_CODE") == null ? "" : jo
								.getString("TO_CODE"));
						nicRelationObj.setFromUuid(jo.get("FROM_CODE") == null ? "" : jo
								.getString("FROM_CODE"));
						vnic_pgLst.add(nicRelationObj);
					}
				}
			}
			for (NicRelationObj vnic_vmObj : vnic_vmLst) {
				if (vnic_vmObj.getToUuid().equals(code)) {
					nicRelationDao.insertByObj(vnic_vmObj);
					for (VirtualNicObj virtualNicObj : vnicLst) {
						if (virtualNicObj.getUuid().equals(vnic_vmObj.getFromUuid())) {
							virtualNicDao.insertByObj(virtualNicObj);
						}
					}
					for (NicRelationObj vnic_pgObj : vnic_pgLst) {
						if (vnic_pgObj.getFromUuid().equals(vnic_vmObj.getFromUuid())) {
							nicRelationDao.insertByObj(vnic_pgObj);
						}
					}
					for (NicRelationObj vnic_nicObj : vnic_nicLst) {
						if (vnic_nicObj.getFromUuid().equals(vnic_vmObj.getFromUuid())) {
							nicRelationDao.insertByObj(vnic_nicObj);
						}
					}
				}
			}
			for (NicRelationObj nicRelationObj2 : pg_vmLst) {
				if (nicRelationObj2.getToUuid().equals(code)) {
					nicRelationDao.insertByObj(nicRelationObj2);
				}
			}
		} else {
			result = fail;
		}

		return "{\"result\":\"" + result + "\"}";
	}

	/**
	 * 
	 * @Title: getConnectMsg
	 * @Description: 查询宿主机名称、freeMem、maxVCpu信息
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime May 9, 2012 3:57:12 PM
	 */
	public String getConnectMsg(int hostId) {
		VMManagerObj dObj = new VMManagerObj();
		dObj.setID(hostId);
		dObj = kvmManDao.queryByObj(dObj);
		String url = "/kvm/connect/getMsg/" + dObj.getIP() + "/" + "kvm" + "/";
		String result = InvokeUtil.invoke(url);
		return result;
	}

	/**
	 * 
	 * @Title: putVirtualStat
	 * @Description: 修改虚拟机状态(启动、停止、重启等)--删除虚拟机待测试修改
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 21, 2012 3:27:11 PM
	 */
	public String putVirtualStat(int id, String op, String ENTITY_ID) {
		EntityTreeObj obj = new EntityTreeObj();
		obj.setId(id);
		obj = entityTreeDao.queryTreeNode(obj);
		String name = obj.getName();
		String url = "";
		String result = "";
		// 检查虚拟机状态
		String statUrl = null;
		statUrl = "/vmware/domain/msg/[vmName:" + ENTITY_ID + "]/";
		String param = InvokeUtil.invoke(statUrl);
		Map ps = ParamParser.makeup(param);
		String strMsg = (String) ps.get(RESPONSE_CODE);
		String stat;
		if (strMsg.equals(ResponseCode.SUCCESS)) {
			stat = (String) ps.get("power");
		} else {
			stat = (String) ps.get(MESS);
		}
		String opUrl = "";
		if ("create".equals(op)) {
			if (stat.equals("poweredOff") || stat.equals("suspended")) {
				opUrl = "/vmware/domain/power/[vmName:" + ENTITY_ID + "].[op:poweron]/";
				result = InvokeUtil.invoke(opUrl);
				Map resultMap = ParamParser.makeup(result);
				String str = (String) resultMap.get(RESPONSE_CODE);
				if (str.equals(ResponseCode.SUCCESS)) {
					/** 通知 */
					try {
						NoticeUtil.getInstance().updateCMDBVM(ENTITY_ID, Operation.OPER_MODI);
					} catch (Exception e) {

					}
				}
				if (!str.equals("1")) {
					str = (String) resultMap.get(MESS);
				}
				return str;
			} else {
				String str = stat;
				return str;
			}
		} else if ("shutdown".equals(op)) {
			if (stat.equals("poweredOn")) {
				try {
					String unitIdUrl = "http://" + CfgUtil.getString("monitor.ip") + ":"
							+ CfgUtil.getString("monitor.port") + "/"
							+ CfgUtil.getString("monitor.web") + "/ibnms/monitor/code/" + ENTITY_ID
							+ "/unitid";
					String unitIdResult = HttpClientUtil.get(unitIdUrl);
					String stopUrl = "http://" + CfgUtil.getString("monitor.ip") + ":"
							+ CfgUtil.getString("monitor.port") + "/"
							+ CfgUtil.getString("monitor.web") + "/ibnms/monitor/" + unitIdResult
							+ "/collecting";
					String stopResult = HttpClientUtil.del(stopUrl);
					logger.debug("停止监控=====" + stopResult);
				} catch (Exception e) {

				}

				opUrl = "/vmware/domain/power/[vmName:" + ENTITY_ID + "].[op:poweroff]/";
				result = InvokeUtil.invoke(opUrl);
				Map resultMap = ParamParser.makeup(result);
				String str = (String) resultMap.get(RESPONSE_CODE);
				/** 通知 */
				try {
					NoticeUtil.getInstance().updateCMDBVM(ENTITY_ID, Operation.OPER_MODI);
				} catch (Exception e) {

				}
				if (!str.equals("1")) {
					str = (String) resultMap.get(MESS);
				}
				return str;
			} else {
				String str = stat;
				return str;
			}
		} else if ("suspend".equals(op)) {
			if (stat.equals("poweredOn")) {
				opUrl = "/vmware/domain/power/[vmName:" + ENTITY_ID + "].[op:suspend]/";
				result = InvokeUtil.invoke(opUrl);
				Map resultMap = ParamParser.makeup(result);
				String str = (String) resultMap.get(RESPONSE_CODE);
				/** 通知 */
				try {
					NoticeUtil.getInstance().updateCMDBVM(ENTITY_ID, Operation.OPER_MODI);
				} catch (Exception e) {

				}
				if (!str.equals("1")) {
					str = (String) resultMap.get(MESS);
				}
				return str;
			} else {
				String str = stat;
				return str;
			}
		} else if ("reboot".equals(op)) {
			if (stat.equals("poweredOn")) {
				opUrl = "/vmware/domain/power/[vmName:" + ENTITY_ID + "].[op:reset]/";
				result = InvokeUtil.invoke(opUrl);
				Map resultMap = ParamParser.makeup(result);
				String str = (String) resultMap.get(RESPONSE_CODE);
				/** 通知 */
				try {
					NoticeUtil.getInstance().updateCMDBVM(ENTITY_ID, Operation.OPER_MODI);
				} catch (Exception e) {

				}
				if (!str.equals("1")) {
					str = (String) resultMap.get(MESS);
				}
				return str;
			} else {
				String str = stat;
				return str;
			}

		} else if ("destroy".equals(op)) {
			if (stat.equals("poweredOff") || stat.equals("suspended")) {
				try {
					String unitIdUrl = "http://" + CfgUtil.getString("monitor.ip") + ":"
							+ CfgUtil.getString("monitor.port") + "/"
							+ CfgUtil.getString("monitor.web") + "/ibnms/monitor/code/" + ENTITY_ID
							+ "/unitid";
					String unitIdResult = HttpClientUtil.get(unitIdUrl);
					String stopUrl = "http://" + CfgUtil.getString("monitor.ip") + ":"
							+ CfgUtil.getString("monitor.port") + "/"
							+ CfgUtil.getString("monitor.web") + "/ibnms/host/" + unitIdResult;
					String stopResult = HttpClientUtil.del(stopUrl);
					logger.debug("删除监控=====" + stopResult);
				} catch (Exception e) {

				}

				opUrl = "/vmware/domain/destroy/[vmName:" + ENTITY_ID + "]/";
				result = InvokeUtil.invoke(opUrl);
				Map resultMap = ParamParser.makeup(result);
				String str = (String) resultMap.get(RESPONSE_CODE);
				if (str.equals("1")) {// 删除成功后删除表中的数据
					/** 通知cmdb */
					try {
						NoticeUtil.getInstance().delCMDBVM(ENTITY_ID, Operation.OPER_DEL);
					} catch (Exception e) {

					}
					try {
						NoticeUtil.getInstance().delVM(ENTITY_ID);
					} catch (Exception e) {

					}
					// 删除entitytree表中的数据
					entityTreeDao.delTreeNode(obj);
					// 删除TB_CLOUD_ENTITY_CON表中的数据
					EntityConObj conObj = new EntityConObj();
					conObj.setType(0);
					conObj.setEntityId(obj.getEntityId());
					entityTreeDao.deleteConObj(conObj);
					// 删除虚拟机表vhhost_info数据
					VMHostObj vmObj = new VMHostObj();
					vmObj.setVH_UUID(obj.getEntityId());
					vmHostDao.deleteByObj(vmObj);

					// 删除tb_cloud2_script_con表里，和虚拟机关联的脚本信息
					ScriptConObj scriptConObj = new ScriptConObj();
					scriptConObj.setTempletId(obj.getEntityId());
					scriptDao.deleteScriptRelation(scriptConObj);
					// 删除和虚拟机相关的网络关系
					NicRelationObj vnic_vmReObj = new NicRelationObj();
					vnic_vmReObj.setType("VNIC_VM");
					vnic_vmReObj.setToUuid(ENTITY_ID);
					List lst = nicRelationDao.queryForListByObj(vnic_vmReObj);
					for (Object object : lst) {
						NicRelationObj nicReobj = (NicRelationObj) object;
						VirtualNicObj vnicObj = new VirtualNicObj();
						vnicObj.setUuid(nicReobj.getFromUuid());
						virtualNicDao.deleteByObj(vnicObj);
					}
					nicRelationDao.deleteByObj(vnic_vmReObj);
					NicRelationObj pg_vmReObj = new NicRelationObj();
					pg_vmReObj.setType("PORTGROUP_VM");
					pg_vmReObj.setToUuid(ENTITY_ID);
					nicRelationDao.deleteByObj(pg_vmReObj);

					return str;
				} else {
					str = (String) resultMap.get(MESS);
					return str;
				}
			} else {
				String str = stat;
				return str;
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: getVirtualInfo
	 * @Description: 获取虚拟机信息
	 * @param 主机Id
	 *            ，虚拟机英文名称（唯一标识）
	 * @return ActionForward
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 24, 2012 9:05:59 AM
	 */
	public String getVirtualInfo(String hostId, String NAME_EN) {
		VMManagerObj dObj = new VMManagerObj();
		dObj.setID(Integer.parseInt(hostId));
		dObj = kvmManDao.queryByObj(dObj);
		String Ip = dObj.getIP();
		String url = "/kvm/domain/getMsg/" + Ip + "/" + NAME_EN + "/";
		String result = InvokeUtil.invoke(url);
		return result;
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
			int seq = vmHostDao.queryVhostIdSequence();
			NumberFormat formatter = NumberFormat.getNumberInstance(); // 设置数据格式
			formatter.setMinimumIntegerDigits(3); // 设置最小长度
			formatter.setMaximumIntegerDigits(3); // 设置最大长度
			vhostId = eqId + "_" + formatter.format(seq);
		}
		return vhostId;
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
	 * @createtime May 16, 2012 2:23:00 PM
	 */
	public String adjustVmwVirtualInfo(VMManagerObj obj) {
		// 查询虚拟机原来的值来做比较
		VMHostObj vObj = new VMHostObj();
		vObj.setVH_TYPE("1");// vm_host_info表中的vmware虚拟机类型为1
		// vObj.setVH_NAME(obj.getNAME_EN());
		vObj.setVH_UUID(obj.getNAME_EN());
		vObj = vmHostDao.queryByObj(vObj);
		String cpu = obj.getCPU();
		String memory = obj.getMEMORY();
		String storage = obj.getSTORAGE();
		String result = "1";
		// 调整cpu;
		if (cpu != null && !"".equals(cpu)) {
			if (!vObj.getVH_CPU().equals(cpu)) {
				String cpuResult = "";
				String cpuUrl = "/vmware/domain/reconfig/[vmName:" + obj.getNAME_EN()
						+ "].[cpu:cpu].[cpuValue:" + obj.getCPU() + "]/";
				cpuResult = InvokeUtil.invoke(cpuUrl);
				Map ps = ParamParser.makeup(cpuResult);
				result = (String) ps.get(RESPONSE_CODE);
				if (result.equals("1")) {// 更改cpu成功后，更改数据库中的值
					/** 通知 */
					try {
						NoticeUtil.getInstance()
								.updateCMDBVM(obj.getNAME_EN(), Operation.OPER_MODI);// cmdb
					} catch (Exception e) {
						NoticeUtil.getInstance().updateVM(obj.getNAME_EN());
					}
					vObj.setVH_CPU(cpu);
					vmHostDao.updateCpuAndMem(vObj);
				}
			}
		}
		// 调整内存
		if (memory != null && !"".equals(memory)) {
			if (!vObj.getVH_MEM().equals(memory)) {
				String memResult = "";
				String memUrl = "/vmware/domain/reconfig/[vmName:" + obj.getNAME_EN()
						+ "].[memory:memory].[memoryValue:" + obj.getMEMORY() + "]/";
				memResult = InvokeUtil.invoke(memUrl);
				Map ps = ParamParser.makeup(memResult);
				result = (String) ps.get(RESPONSE_CODE);// 更改成功后更改数据库中的值
				if (result.equals("1")) {
					/** 通知 */
					try {
						NoticeUtil.getInstance()
								.updateCMDBVM(obj.getNAME_EN(), Operation.OPER_MODI);// cmdb
					} catch (Exception e) {
						NoticeUtil.getInstance().updateVM(obj.getNAME_EN());
					}
					vObj.setVH_MEM(memory);
					vmHostDao.updateCpuAndMem(vObj);
				}
			}
		}
		// 调整存储
		if (storage != null && !"".equals(storage)) {
			if (!vObj.getVH_STORAGE().equals(storage)) {
				// 增加存储
				if (Integer.parseInt(vObj.getVH_STORAGE()) < Integer.parseInt(storage)) {
					String storageResult = "";
					String storageUrl = "/vmware/domain/reconfig/[vmName:" + obj.getNAME_EN()
							+ "].[disk:disk" + "].[op:Edit].[diskSize:" + storage + "]/";
					storageResult = InvokeUtil.invoke(storageUrl);
					Map ps = ParamParser.makeup(storageResult);
					result = (String) ps.get(RESPONSE_CODE);
					if (result.equals("1")) {
						/** 通知 */
						try {
							NoticeUtil.getInstance().updateCMDBVM(obj.getNAME_EN(),
									Operation.OPER_MODI);// cmdb
						} catch (Exception e) {
							NoticeUtil.getInstance().updateVM(obj.getNAME_EN());
						}
						vObj.setVH_STORAGE(storage);
						vmHostDao.updateCpuAndMem(vObj);
					}
				}
			}
		}
		// 调整网络适配器
		try {
			// String nic = obj.getAUXILIARY();
			String nic1 = obj.getSUB_TYPE();
			String nic1Code = obj.getSUB_TYPENAME();
			String nic2 = obj.getBRAND();
			String nic2Code = obj.getBRANDNAME();
			String hostCode = obj.getHostUuid();
			String nic1value = "";
			String nic2value = "";
			if (nic1Code != null && !nic1Code.equals("")) {
				nic1value = nic1Code.split(hostCode)[1].substring(1);
			}
			if (nic2Code != null && !nic2Code.equals("")) {
				nic2value = nic2Code.split(hostCode)[1].substring(1);
			}
			// String nicUrl = "/vmware/network/getVmportgroup/[vmName:"
			// + obj.getNAME_EN() + "]/";
			// String nicResult = InvokeUtil.invoke(nicUrl);
			// if (nicResult != null) {
			// if (!nic.equals(nicResult)) {
			String nicconfig = "";
			String url = "/vmware/domain/reconfig/[vmName:" + obj.getNAME_EN()
					+ "].[nic:nic].[op:Edit].[networkName1:" + nic1value + "].[nic1:" + nic1
					+ "].[nic2:" + nic2 + "].[networkName2:" + nic2value + "]/";
			nicconfig = InvokeUtil.invoke(url);
			Map ps = ParamParser.makeup(nicconfig);
			result = (String) ps.get(RESPONSE_CODE);
			if (result.equals(ResponseCode.SUCCESS)) {
				// 更新数据库中nic的关系
				NicRelationObj nicReobj = new NicRelationObj();
				nicReobj.setType("PORTGROUP_VM");
				nicReobj.setToUuid(obj.getNAME_EN());
				nicRelationDao.deleteByObj(nicReobj);
				NicRelationObj addnic1 = new NicRelationObj();
				addnic1.setType("PORTGROUP_VM");
				addnic1.setToUuid(obj.getNAME_EN());
				addnic1.setFromUuid(nic1value);
				nicRelationDao.insertByObj(addnic1);
				if (nic2 != null && !nic2.equals("")) {
					NicRelationObj addnic2 = new NicRelationObj();
					addnic1.setType("PORTGROUP_VM");
					addnic1.setToUuid(obj.getNAME_EN());
					addnic1.setFromUuid(nic2value);
					nicRelationDao.insertByObj(addnic2);
				}
				// 通知
				try {
					NoticeUtil.getInstance()
							.updateCMDBVM(obj.getNAME_EN(), Operation.OPER_REL_MODI);// cmdb
				} catch (Exception e) {

				}
				// try {
				// NoticeUtil.getInstance().updateVM(obj.getNAME_EN());
				// } catch (Exception ex) {
				//
				// }
			}
			// }
			// }
		} catch (Exception ex) {

		}
		return result;
	}

	/**
	 * 
	 * @Title: queryHealthStateList
	 * @Description: 查询主机或虚拟机健康状态信息
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 21, 2012 10:34:05 AM
	 */
	public List queryHealthStateList(TbYicloudDeviceHealthObj obj) {
		return kvmManDao.queryHealthStateList(obj);
	}

	/**
	 * 
	 * @Title: queryDefaultConfig
	 * @Description: 查询各个操作系统的默认配置值
	 * @param
	 * @return TbYicloudOsTypeObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 10, 2012 1:14:31 PM
	 */
	public TbYicloudOsTypeObj queryDefaultConfig(TbYicloudOsTypeObj obj) {
		return kvmManDao.queryDefaultConfig(obj);
	}

	/**
	 * 
	 * @Title: getIdSequence
	 * @Description: 查询device表的ID
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 18, 2012 9:44:10 AM
	 */
	public String getIdSequence() {
		return kvmManDao.getIdSequence();
	}

	/**
	 * @Title:查询设备主机数据结果集
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public List queryForListByObj(VMManagerObj obj) {
		return kvmManDao.queryForListByObj(obj);
	}

	/**
	 * @Title:查询一个虚拟映像
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public TbCloud2ImageInfoObj queryByImageObj(TbCloud2ImageInfoObj obj) {
		return tbImageInfoDao.queryByObj(obj);
	}

	/**
	 * 
	 * @Title: getVirtualInfo
	 * @Description: 查询一条信息（从库中查）
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 24, 2012 9:05:59 AM
	 */
	public VMManagerObj queryByObj(VMManagerObj obj) {
		return kvmManDao.queryByObj(obj);
	}

	/**
	 * @Title:添加虚拟机(向数据库中)
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int insertByObj(VMManagerObj obj) {
		return kvmManDao.insertByObj(obj);
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除主机
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 18, 2012 10:24:14 AM
	 */
	public int deleteByObj(VMManagerObj obj) {
		return kvmManDao.deleteByObj(obj);

	}

	public void setEntityTreeDao(EntityTreeDao entityTreeDao) {
		this.entityTreeDao = entityTreeDao;
	}

	/**
	 * 
	 * @Title: queryOSList
	 * @Description: 获取操作系统列表
	 * @param
	 * @return List<TbYicloudOsTypeObj>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Jul 25, 2012 2:40:34 PM
	 */
	public List<TbYicloudOsTypeObj> queryOSList(TbYicloudOsTypeObj obj) {
		return kvmManDao.queryOSList(obj);
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
	 * @createtime Jul 26, 2012 3:12:50 PM
	 */
	public String cloneVirtualMachine(String old_name, String new_name, String host_name) {
		String param = "/vmware/domain/clone/" + "[vmName:" + old_name + "].[cloneName:" + new_name
				+ "].[hostName:" + host_name + "]/";
		String result = InvokeUtil.invoke(param);
		Map ps = ParamParser.makeup(result);
		String CloneResult = (String) ps.get(RESPONSE_CODE);
		if (CloneResult.equals("1")) {
			// 将模板转化为虚拟机,数据中心，主机名暂时写死
			String markAsVm = "/vmware/domain/markAsVM/[dcName:si-tech].[hostName:]" + host_name
					+ "].[vmName:" + new_name + "]/";
			String markResult = InvokeUtil.invoke(markAsVm);
			Map map = ParamParser.makeup(markResult);
			String vmResult = (String) map.get(RESPONSE_CODE);
			if (vmResult.equals("1")) {
				logger.debug("-------模板转化为虚拟机成功------");
				String opUrl = "/vmware/domain/power/[vmName:" + URLEncoder.encode(new_name)
						+ "].[op:poweron]/";
				result = InvokeUtil.invoke(opUrl);
				Map resultMap = ParamParser.makeup(result);
				String str = (String) resultMap.get(RESPONSE_CODE);
				if (str.equals(1)) {
					logger.debug("--------启动虚拟机成功---------");
				} else {
					logger.debug("--------启动虚拟机失败---------");
				}
			} else {
				logger.debug("-------模板转化为虚拟机失败------");
			}
		}
		if (result.contains("successfully")) {
			// 向实体树表中插入数据
			EntityTreeObj obj = new EntityTreeObj();
			obj.setName(host_name);
			obj = entityTreeDao.queryTreeNode(obj);
			EntityTreeObj en = new EntityTreeObj();
			en.setName(new_name);
			en.setType("0");
			en.setParentId(obj.getId());
			String id = kvmManDao.getIdSequence();
			en.setEntityId(id);
			int ret1 = entityTreeDao.insertTreeNode(en);
			// 向device表中插入数据
			VMManagerObj v = new VMManagerObj();
			v.setID(Integer.parseInt(id));
			v.setNAME_EN(new_name);
			v.setTYPE("3");
			v.setVH_HOST(obj.getEntityId());
			int ret2 = kvmManDao.insertByObj(v);
			// 向tb_cloud_entity_con表中插入数据
			EntityConObj ec = new EntityConObj();
			ec.setDataCenterId(2);
			ec.setClusterId(en.getParentId());
			ec.setName(new_name);
			ec.setType(0);
			ec.setEntityId(id);
			int ret3 = entityTreeDao.insertConObj(ec);

			// 向监控发送信息,返回unitid
			String url = "http://172.21.1.100:51010/WebService/ibnms/host.json";
			JSONObject json = new JSONObject();
			json.put("deviceId", new_name);
			json.put("ipAddr", "172.21.0.123");
			json.put("osManufacturer", "redhat");
			json.put("physicalHostId", "10-10-25:BASD");
			String unitId = HttpClientUtil.post(url, json);

			// 向VMHOST_INFO表中插入数据
			VMHostObj vmObj = new VMHostObj();
			vmObj.setVH_NAME(new_name);
			vmObj.setVH_UNITID(unitId);
			vmObj.setEQ_ID("1_01_001_0029_01");
			String VH_ID = createNewVhostId2(vmObj.getEQ_ID());
			vmObj.setVH_ID(VH_ID);
			vmObj.setVH_TYPE("0");

			// 版本，内存和IP暂时写死
			vmObj.setVH_SYSTEM("Redhat6.2");
			vmObj.setVH_IP("172.21.0.123");
			vmObj.setVH_MEM("1024");

			int ret4 = vmHostDao.insertByVMhostObj(vmObj);

			// 如果向监控管理发送数据成功，返回unitId后开启监控
			if (unitId != null && !"".equals(unitId)) {
				// 开启监控
				String startUrl = "http://172.21.1.100:51010/WebService/ibnms/monitor/" + unitId
						+ "/collecting/vmware.json";
				JSONObject startMoni = new JSONObject();
				startMoni.put("VMNAME", new_name);
				String startResult = HttpClientUtil.post(startUrl, startMoni);
			}
		}
		return result;

	}

	/**
	 * 
	 * @Title: getDSinfo
	 * @Description: 获取存储详细信息
	 * @param
	 * @return DataStoreInfo
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Jul 28, 2012 3:05:16 PM
	 */
	public DataStoreInfo getDSinfo(String hostname, String dsname) {
		String json = "/vmware/storage/collDatastoreInfo/[hostname:" + hostname + "]/";
		String result = InvokeUtil.invoke(json);
		HashMap<String, DataStoreInfo> map = JSONUtil.fromJSON(result,
				new JSONUtil.TypeReference<HashMap<String, DataStoreInfo>>() {
				});
		DataStoreInfo info = map.get(dsname);
		if (info != null) {
			return info;
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @Title: migrateVM
	 * @Description: 虚拟机迁移（在主机之间迁移）
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 31, 2012 3:19:52 PM
	 */
	public String migrateVMByHost(String vmName, String newHostName) {
		String param = "/vmware/domain/migrate/[vmName:" + vmName + "].[newHostName:" + newHostName
				+ "]/";
		// 调用接口查询虚拟机在哪个主机上
		String getMsg = "/vmware/domain/msg/[vmName:" + vmName + "]/";
		String getResult = InvokeUtil.invoke(getMsg);
		Map map = ParamParser.makeup(getResult);
		String hostCode = (String) map.get("hostCode");// 未迁移前的主机的code

		String migrateResult = InvokeUtil.invoke(param);
		Map ps = ParamParser.makeup(migrateResult);
		String result = (String) ps.get("responseCode");
		if (result.equals(ResponseCode.SUCCESS)) {
			/** 通知 */
			try {
				NoticeUtil.getInstance().updateCMDBHost(newHostName, Operation.OPER_REL_MODI);// cmdb
				NoticeUtil.getInstance().updateCMDBHost(hostCode, Operation.OPER_REL_MODI);// cmdb
			} catch (Exception e) {

			}
			try {
				NoticeUtil.getInstance().updateCMDBVM(vmName, Operation.OPER_REL_MODI);
			} catch (Exception ex) {

			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: migrateVMByDs
	 * @Description: 虚拟机迁移（在存储之间迁移）
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 1, 2012 11:23:52 AM
	 */
	public String migrateVMByDs(String vmName, String dsName) {
		String param = "/vmware/domain/migrate/[vmName:" + vmName + "].[dsName:" + dsName + "]/";
		String migrateResult = InvokeUtil.invoke(param);
		Map ps = ParamParser.makeup(migrateResult);
		String result = (String) ps.get("responseCode");
		return result;
	}

	public void setOperateVMHostInfo(OperateVMHostInfo operateVMHostInfo) {
		this.operateVMHostInfo = operateVMHostInfo;
	}

	/**
	 * 
	 * @Title: cloneVMwareVirtual
	 * @Description: 虚拟机创建，浙江竞标
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 1, 2012 11:23:52 AM
	 */
	public String cloneVMwareVirtual(int ID, VMManagerObj vObj) {
		// 设置进度条进度
		Constant.PROCESS_LEVEL = 10;
		/** 暂未使用,创建裸机时没有IP地址 */
		// 占用IP
		/*
		 * String ip = vObj.getIP(); TbCloud2IpInfoObj ipObj = new
		 * TbCloud2IpInfoObj(); ipObj.setISUSED("1"); ipObj.setIPADDRESS(ip); //
		 * 更新IP使用状态 tbIpDao.updateIPStat(ipObj);
		 */
		/** 查询主机在哪个数据中心下 */
		String type = vObj.getTYPE();// 主机类型
		EntityConObj cObj = new EntityConObj();
		cObj.setType(Integer.parseInt(type));
		cObj.setEntityId(String.valueOf(ID));
		List<EntityConObj> list = entityTreeDao.queryEntityData(cObj);
		if (list != null && list.size() > 0) {
			cObj = list.get(0);
		}
		String dcName = cObj.getName();// 数据中心名称
		int dcId = cObj.getDataCenterId();// 数据中心id
		int clusterId = cObj.getClusterId();// 集群id
		String result = "";
		// String hostUsername = "root";// 虚拟机用户名
		String NAME_ZH = vObj.getNAME_ZH();// 虚拟机名称
		String NAME_EH = vObj.getNAME_EN();// 虚拟机名称
		String CURRENT_CPU = vObj.getCURRENTCPU();
		String CURRENT_MEMORY = vObj.getCURRENTMEMORY();
		String datastoreName = vObj.getDatastoreName();// 存储名称
		String guestOsId = vObj.getGuestOsId();// 客户机操作系统类型
		String diskMode = "persistent";// 硬盘模式
		String netName = "VM Network";// 网络名称
		String nicName = "Network Adapter 1";// 网卡名称
		long diskSizeKB = Long.parseLong(vObj.getSTORAGE());
		String host_name = vObj.getHost_name();
		String NET_ID = vObj.getNET_ID();
		// 查询未被占用的IP
		/** 获取IP */
		// TbCloud2IpInfoObj t = new TbCloud2IpInfoObj();
		// t.setNET_ID(NET_ID);
		// t.setISUSED("0");
		// List<TbCloud2IpInfoObj> ipList = tbIpDao.queryForListByObj(t);
		// String changeIp = ipList.get(0).getIPADDRESS();
		// TbCloud2IpInfoObj ipObj = new TbCloud2IpInfoObj();
		// ipObj.setISUSED("1");
		// ipObj.setIPADDRESS(changeIp);
		// tbIpDao.updateIPStat(ipObj);
		// IP地址暂时写死
		String changeIp = "172.21.1.112";
		/** 获取IP */
		// 创建VMWare虚拟机
		/*
		 * String createVMUrl = "/vmware/domain/create/[dcName:" + dcName +
		 * "].[vmName:" + URLEncoder.encode(NAME_EH) + "].[memorySizeMB:" +
		 * CURRENT_MEMORY + "].[cupCount:" + CURRENT_CPU + "].[datastoreName:" +
		 * datastoreName + "].[diskSizeKB:" + diskSizeKB + "].[diskMode:" +
		 * diskMode + "].[guestOsId:" + guestOsId.trim() + "].[netName:" +
		 * netName + "].[nicName:" + nicName + "]/";
		 */
		String old_name = "Linux-tom-tem";// 要选定的虚拟机名称（暂时需要写死）
		String param = "/vmware/domain/clone/" + "[vmName:" + old_name + "].[cloneName:" + NAME_EH
				+ "].[hostName:" + host_name + "]/";
		result = InvokeUtil.invoke(param);
		Map ps = ParamParser.makeup(result);
		String CloneResult = (String) ps.get(RESPONSE_CODE);
		if (CloneResult.equals("1")) {
			logger.debug("--------克隆模板成功---------");
			// 将模板转化为虚拟机,数据中心，主机名暂时写死
			String markAsVm = "/vmware/domain/markAsVM/[dcName:si-tech].[hostName:]" + host_name
					+ "].[vmName:" + NAME_EH + "]/";
			String markResult = InvokeUtil.invoke(markAsVm);
			String ipConfig = "/vmware/domain/changeIP/[vmName:" + NAME_EH + "].[ip:" + changeIp
					+ "].[gateway:172.21.1.1]/";
			String ipResult = InvokeUtil.invoke(ipConfig);
			Map map = ParamParser.makeup(markResult);
			String vmResult = (String) map.get(RESPONSE_CODE);
			if (vmResult.equals("1")) {
				logger.debug("-------模板转化为虚拟机成功------");
				String opUrl = "/vmware/domain/power/[vmName:" + URLEncoder.encode(NAME_EH)
						+ "].[op:poweron]/";
				result = InvokeUtil.invoke(opUrl);
				Map resultMap = ParamParser.makeup(result);
				String str = (String) resultMap.get(RESPONSE_CODE);
				if (str.equals("1")) {
					logger.debug("--------启动虚拟机成功---------");
				} else {
					logger.debug("--------启动虚拟机失败---------");
				}
			} else {
				logger.debug("-------模板转化为虚拟机失败------");
			}
		}

		// 向监控发送信息,返回unitid
		String url = "http://172.21.1.100:51010/WebService/ibnms/host.json";
		JSONObject json = new JSONObject();
		json.put("deviceId", NAME_EH);
		json.put("ipAddr", "172.21.0.123");
		json.put("osManufacturer", "redhat");
		json.put("physicalHostId", "10-10-25:BASD");
		String unitId = "";
		// try {
		// unitId = HttpClientUtil.post(url, json);
		// } catch (Exception e) {
		// String res = "通知监控发生异常！";
		// logger.error(e.getMessage() + res);
		// }
		// 设置进度条进度
		/** 数据插入 */
		// 插Divice表数据
		VMManagerObj virtualObj = new VMManagerObj();
		virtualObj.setNAME_EN(NAME_EH);
		virtualObj.setNAME_ZH(NAME_ZH);
		virtualObj.setIP(vObj.getIP());
		virtualObj.setTYPE("3");
		virtualObj.setVH_HOST(String.valueOf(ID));
		virtualObj.setCPU(CURRENT_CPU);
		virtualObj.setMEMORY(CURRENT_MEMORY);
		String id = kvmManDao.getIdSequence();
		virtualObj.setID(Integer.parseInt(id));
		int rest = kvmManDao.insertByObj(virtualObj);

		int vmId = vmHostDao.queryVhostIdSequence();// 查询vmhost_info表序列作为虚拟机唯一标识；

		// 向entity——tree中插入数据
		EntityTreeObj eObj = new EntityTreeObj();
		eObj.setEntityId(String.valueOf(ID));// 主机的entityId
		// eObj.setName(hostName);
		EntityTreeObj e = entityTreeDao.queryTreeNode(eObj);// 通过主机的entityId查询其id
		eObj.setParentId(e.getId());
		eObj.setName(NAME_EH);
		eObj.setType(TypeConstant.VMWARE_VM);
		eObj.setEntityId(String.valueOf(vmId));
		int ret4 = entityTreeDao.insertTreeNode(eObj);

		// 向entity-Con中插入数据
		EntityConObj ecObj = new EntityConObj();
		ecObj.setDataCenterId(dcId);
		ecObj.setClusterId(clusterId);
		ecObj.setType(0);
		ecObj.setEntityId(String.valueOf(vmId));
		ecObj.setName(NAME_EH);
		int ret5 = entityTreeDao.insertConObj(ecObj);

		// 插入虚拟机信息
		VMHostObj vmObj = new VMHostObj();
		vmObj.setEQ_ID("1_01_001_0029_02");
		String VH_ID = createNewVhostId2(vmObj.getEQ_ID());
		/** 暂未使用cmdb接口 */
		// 向cmdb接口发送数据并返回cId,返回的值插入数据库
		// String cId = operateVMHostInfo
		// .postAddVMHostInfoForCMDBInte(VH_ID, NAME_EH, "", "",
		// CURRENT_CPU, CURRENT_MEMORY, String
		// .valueOf(diskSizeKB * 1024), "",
		// TypeConstant.VMWARE_VM);
		// if (cId != null && !"".equals(cId)) {
		// vmObj.setVH_STATUS("1"); // cmdb接口数据发送成功
		// }
		vmObj.setVH_CPU(CURRENT_CPU);// cpu
		vmObj.setVH_MEM(CURRENT_MEMORY);// 内存
		vmObj.setVH_STORAGE(String.valueOf(diskSizeKB * 1024));// 存储
		vmObj.setVH_SYSTEM(vObj.getTYPENAME());// 操作系统
		vmObj.setVH_NAME(NAME_EH);
		vmObj.setVH_IP(changeIp);
		vmObj.setVH_ID(VH_ID);
		vmObj.setID(vmId);
		vmObj.setVH_TYPE(TypeConstant.VMWARE_VM);
		vmObj.setVH_UNITID(unitId);
		// vmObj.setVH_CID(cId);
		int ret3 = vmHostDao.insertByVMhostObj(vmObj);

		// 插入Web主机信息--浙江
		TbBusiHostObj tbHostObj = new TbBusiHostObj(); //
		tbHostObj.setIP(changeIp);
		tbHostObj.setSTATUS("1");
		tbHostObj.setHOSTNAME(NAME_EH);
		int ret1 = tbBusiHostObjDao.insertByObj(tbHostObj);
		TbBusiHostObj tbBusiObj = tbBusiHostObjDao.queryByObj(tbHostObj); // 插入主机配置信息

		Constant.PROCESS_LEVEL = 100;
		try {
			Thread.sleep(30 * 1100);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		StringBuffer sf = new StringBuffer();
		sf.append("{'vhId':'").append(VH_ID + "',").append("'vhName':'").append(NAME_EH + "',")
				.append("'vhIp':'").append(changeIp + "',").append("'hostName':'").append(
						host_name + "'}");
		return sf.toString();
		// return "{'result':'" + 1 + "'}";
	}

	/**
	 * 
	 * @Title: cloneVmwarevm
	 * @Description: 从虚拟机或模板克隆虚拟机,从主机克隆
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 26, 2012 3:12:50 PM
	 */

	public String cloneVmwarevm(String vmCode,String type,VmwManForm theForm) {
		String CloneResult = "-1";
		String param = null;
		String hostCode = theForm.getHostCode();
		String hostname = theForm.getHostname();
		String newName = theForm.getNAME_EN();
		String numCpus = theForm.getCPU();
		String memInMb = theForm.getMEMORY();
		String memUnit = theForm.getMemUnit();
		if(memUnit.equals("2")){
			memInMb = String.valueOf(Integer.parseInt(memInMb)*1024);
		}
		String storageId = theForm.getStorageId();
		String diskSize = String.valueOf(Integer.parseInt(theForm.getSTORAGE())*1024*1024);
		String netType = theForm.getNetType();
		String net = theForm.getNET();
		String ip = "";
		String dns = "";
		String gateway = "";
		String subnetMask = "";
		String vlan = "";
		
		DataStoreObj storeObj = new DataStoreObj();
		storeObj.setID(Integer.parseInt(storageId));
		List storeLst = dataStoreDao.queryForListByObj(storeObj);
		storeObj = (DataStoreObj)storeLst.get(0);
		String dsCode = storeObj.getStore_uuid();
		//初始化参数
		TbCloud2NetInfoObj netInfoObj = new TbCloud2NetInfoObj();
		netInfoObj.setNET_ID(net);
		netInfoObj = tbNetDao.queryByObj(netInfoObj);
		dns = netInfoObj.getDNS1();
		gateway = netInfoObj.getGATEWAY1();
		subnetMask = netInfoObj.getSUBNET();
		vlan = netInfoObj.getVLAN_ID();
		if ("1".equals(netType)) {
			ip = theForm.getIP();
		} else {
			TbCloud2IpInfoObj ipobj = new TbCloud2IpInfoObj();
			ipobj.setNET_ID(net);
			ipobj.setISUSED("0");
			List ipLst = tbIpDao.queryForListByObj(ipobj);
			if(ipLst!=null && ipLst.size()>0){
				ipobj = (TbCloud2IpInfoObj)ipLst.get(0);
				ip = ipobj.getIPADDRESS();
			}
		}
		
		if (type.equals(TypeConstant.VMWARE_VM)) {
			param = "/vmware/domain/cloneAndConfig/" + "[vmcode:"+vmCode+"].[hostCode:"+hostCode+"].[newName:"+newName+"].[numCpus:"+numCpus+
					"].[memInMb:"+memInMb+"].[dsCode:"+ dsCode +"].[ip:"+ip+"].[dns:"+dns+"].[gateway:"+gateway+"].[subnetMask:"+subnetMask
					+"].[diskSize:"+ diskSize +"].[vlan:"+ vlan +"].[hostname:"+ hostname +"]/";
		} else if (type.equals(TypeConstant.VMWARE_IMAGE)) {
			// 查询主机所在的集群
			EntityTreeObj obj = new EntityTreeObj();
			// obj.setName(host_name);
			obj.setEntityId(hostCode);
			obj.setType(TypeConstant.VMWARE_HOST);
			obj = entityTreeDao.queryTreeNode(obj);
			EntityTreeObj clusterObj = new EntityTreeObj();
			clusterObj.setId(obj.getParentId());
			clusterObj = entityTreeDao.queryTreeNode(clusterObj);
			String clusterCode = clusterObj.getEntityId();
			param = "/vmware/domain/cloneAndConfig/" + "[vmcode:"+vmCode+"].[hostCode:"+hostCode+"].[newName:"+newName+"].[numCpus:"+numCpus+
					"].[memInMb:"+memInMb+"].[dsCode:"+ dsCode +"].[ip:"+ip+"].[dns:"+dns+"].[gateway:"+gateway+"].[subnetMask:"+subnetMask
					+"].[diskSize:"+ diskSize +"].[vlan:"+ vlan +"].[clusterCode:"+ clusterCode +"].[hostname:"+hostname+"]/";
		}
		String result = InvokeUtil.invoke(param);
		Map ps = ParamParser.makeup(result);
		CloneResult = (String) ps.get(RESPONSE_CODE);
		if (CloneResult.equals("1")) {
			String code = (String) ps.get(ResponseCode.CODE);
			/** 通知 */
			try {
				NoticeUtil.getInstance().updateCMDBHost(hostCode, Operation.OPER_REL_MODI);// cmdb
			} catch (Exception e) {

			}

			try {
				String vmcode = (String) ps.get(ResponseCode.CODE);
				NoticeUtil.getInstance().addVM(vmcode);
			} catch (Exception ex) {

			}
			int vmId = vmHostDao.queryVhostIdSequence();// 查询vmhost_info表序列作为虚拟机唯一标识；

			// 查询主机在哪个数据中心和集群下

			EntityTreeObj etbj = new EntityTreeObj();
			etbj.setEntityId(hostCode);
			List<Map<String, Integer>> temp = entityTreeDao.queryHostClDcId(etbj);
			Map<String, Integer> map = null;
			if (temp != null && temp.size() > 0) {
				map = temp.get(0);
			}
			// 向实体树表中插入数据
			EntityTreeObj en = new EntityTreeObj();
			en.setName(newName);
			en.setType(TypeConstant.VMWARE_VM);
			en.setEntityId(code);
			en.setParentId(temp.get(0).get("hostId"));
			int ret1 = entityTreeDao.insertTreeNode(en);
			// 向tb_cloud_entity_con表中插入数据
			EntityConObj ec = new EntityConObj();
			try {
				ec.setDataCenterId(temp.get(0).get("dcId"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			ec.setClusterId(temp.get(0).get("clusterId"));
			ec.setName(newName);
			ec.setType(0);
			ec.setEntityId(code);
			int ret3 = entityTreeDao.insertConObj(ec);

			// 向VMHOST_INFO表中插入数据
			VMHostObj vmObj = new VMHostObj();
			vmObj.setVH_NAME(newName);
			vmObj.setEQ_ID("1_01_001_0029_01");
			String VH_ID = createNewVhostId2(vmObj.getEQ_ID());
			vmObj.setVH_ID(VH_ID);
			vmObj.setID(vmId);
			vmObj.setVH_UUID(code);
			vmObj.setVH_TYPE("1");// vm_host_info表中的vmware的类型为1
			// 查询原虚拟机的数据
			VMHostObj tempObj = new VMHostObj();
			tempObj.setVH_UUID(vmCode);
			tempObj = vmHostDao.queryByObj(tempObj);
			if (tempObj != null) {
				vmObj.setVH_SYSTEM(tempObj.getVH_SYSTEM());
				vmObj.setVH_STAT(tempObj.getVH_STAT());
			}
			vmObj.setVH_IP(ip);
			vmObj.setVH_CPU(numCpus);
			vmObj.setVH_MEM(memInMb);
			vmObj.setVH_STORAGE(String.valueOf(Integer.parseInt(diskSize)*1024));
			int ret4 = vmHostDao.insertByVMhostObj(vmObj);

			// 向tb_cloud2_script_con表中插入数据，建立虚拟机和脚本的关联
			ScriptConObj scriptConObj = new ScriptConObj();
			VMHostObj VMobj = new VMHostObj();
			VMobj.setVH_UNITID(vmCode);
			List lst = imageService.queryVMHostByVMObj(VMobj);
			VMHostObj reObj = null;
			if (lst.size() > 0) {
				reObj = (VMHostObj) lst.get(0);
				scriptConObj.setTempletId(String.valueOf(reObj.getID()));
				scriptConObj.setType("0");
				List relationList = scriptDao.queryScriptListByTempletObj(scriptConObj);
				for (Object o : relationList) {
					ScriptConObj scrCon = (ScriptConObj) o;
					ScriptObj scriptObj = new ScriptObj();
					scriptObj.setId(Integer.parseInt(scrCon.getScriptId()));
					List scriptList = scriptDao.listScript(scriptObj);
					scriptObj = (ScriptObj) scriptList.get(0);
					ScriptConObj addScriptConObj = new ScriptConObj();
					addScriptConObj.setScriptId(String.valueOf(scriptObj.getId()));
					addScriptConObj.setTempletId(String.valueOf(vmId));
					addScriptConObj.setType("1");
					int ret = scriptDao.addRelation(addScriptConObj);
				}
			}
			//把ip设为使用状态
			TbCloud2IpInfoObj ipObj = new TbCloud2IpInfoObj();
			ipObj.setIPADDRESS(ip);
			ipObj.setISUSED("1");
			tbIpDao.updateIPByObj(ipObj);
			
			//插入权限
			VmAuthorityObj obj = new VmAuthorityObj();
			HttpSession session = Struts2Utils.getRequest().getSession();
			obj.setUSERID(Integer.parseInt(session.getAttribute("id").toString()));
			obj.setENTITY_NAME(theForm.getNAME_EN());
			obj.setENTITY_ID(code);
			obj.setTYPE(TypeConstant.VMWARE_VM);
			obj.setOPERAUTHORITY("0");
			vmAuthorityService.insertByObj(obj);
		}
		return CloneResult;
	}
	
	/**
	 * 
	 * @Title: cloneVmwareTem
	 * @Description: 虚拟机克隆为模板
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Sep 18, 2012 1:35:01 PM
	 */
	public String cloneVmwareTem(String old_name, String new_name, String host_name,
			String entity_id, String parent_id, String dsname) {
		// 克隆虚拟机
		String CloneResult = "-1";
		String param = "/vmware/domain/clone/" + "[vmName:" + old_name + "].[cloneName:" + new_name
				+ "].[isTemplate:true].[hostName:" + host_name + "].[dsName:" + dsname + "]/";
		String result = InvokeUtil.invoke(param);
		Map ps = ParamParser.makeup(result);
		CloneResult = (String) ps.get(RESPONSE_CODE);
		if (CloneResult.equals(ResponseCode.SUCCESS)) {
			String code = (String) ps.get(ResponseCode.CODE);
			/** 通知 */
			try {
				NoticeUtil.getInstance().updateCMDBHost(host_name, Operation.OPER_REL_MODI);// cmdb
			} catch (Exception e) {

			}
			try {
				NoticeUtil.getInstance().addTem(code);
			} catch (Exception ex) {

			}
			// 查询原虚拟机的信息
			VMHostObj vmobj = new VMHostObj();
			// vmobj.setID(Integer.parseInt(entity_id));
			vmobj.setVH_UUID(entity_id);
			List lst = vmHostDao.queryForListByObj(vmobj);
			VMHostObj reObj = (VMHostObj) lst.get(0);
			// 向数据库中插入数据
			EntityTreeObj en = new EntityTreeObj();
			int vmId = vmHostDao.queryVhostIdSequence();// 查询vmhost_info表序列作为虚拟机唯一标识；
			EntityTreeObj etbj = new EntityTreeObj();
			etbj.setEntityId(host_name);
			List<Map<String, Integer>> temp = entityTreeDao.queryHostClDcId(etbj);

			// 向实体树表中插入数据
			en.setName(new_name);
			en.setType(TypeConstant.VMWARE_IMAGE);
			en.setParentId(temp.get(0).get("hostId"));
			en.setEntityId(code);
			int ret1 = entityTreeDao.insertTreeNode(en);
			// 向tb_cloud_entity_con表中插入数据
			EntityConObj ec = new EntityConObj();
			ec.setDataCenterId(temp.get(0).get("dcId"));
			ec.setClusterId(temp.get(0).get("clusterId"));
			ec.setName(new_name);
			ec.setType(Integer.parseInt(TypeConstant.VMWARE_IMAGE));
			ec.setEntityId(code);
			int ret3 = entityTreeDao.insertConObj(ec);

			// 向VMHOST_INFO表中插入数据
			VMHostObj vmObj = new VMHostObj();
			vmObj.setVH_NAME(new_name);
			vmObj.setEQ_ID("1_01_001_0029_01");
			String VH_ID = createNewVhostId2(vmObj.getEQ_ID());
			vmObj.setVH_ID(VH_ID);
			vmObj.setID(vmId);
			vmObj.setVH_UUID(code);
			vmObj.setVH_TYPE("5");// vm_host_info表中的vmwareImage的类型为5

			// 版本，内存和IP暂时写死
			vmObj.setVH_SYSTEM(reObj.getVH_SYSTEM());
			vmObj.setVH_MEM(reObj.getVH_MEM());
			vmObj.setVH_STORAGE(reObj.getVH_STORAGE());
			vmObj.setVH_CPU(reObj.getVH_CPU());
			vmObj.setVH_DESC("CPU:" + reObj.getVH_CPU() + ",内存：" + reObj.getVH_MEM() + "(MB),存储:"
					+ reObj.getVH_STORAGE() + "(KB)");
			int ret4 = vmHostDao.insertByVMhostObj(vmObj);

			// 向模板树表写入数据
			TempletTreeObj treeObj = new TempletTreeObj();
			treeObj.setName(new_name);
			treeObj.setTemplet_id(String.valueOf(vmId));
			treeObj.setType(2);
			treeObj.setParent_id(Integer.parseInt(parent_id));
			int creatMenuRet = templetTreeDao.creatMenu(treeObj);
		}

		return CloneResult;
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
	public String removeTemplet(EntityTreeObj obj) {
		String name = obj.getName();
		String entityId = obj.getEntityId();
		String result = "-1";
		try {
			String url = "/vmware/domain/destroy/[vmName:" + URLEncoder.encode(entityId, "utf-8")
					+ "]/";
			String remove = InvokeUtil.invoke(url);

			Map ps = ParamParser.makeup(remove);
			String code = (String) ps.get(RESPONSE_CODE);
			if (code.equals("1")) {
				try {
					NoticeUtil.getInstance().delCMDBTem(entityId, Operation.OPER_DEL);// cmdb
				} catch (Exception e) {

				}
				try {
					NoticeUtil.getInstance().delTem(entityId);
				} catch (Exception ex) {

				}
				result = "1";

				// 查询实体的自增序列ID
				VMHostObj queryObj = new VMHostObj();
				queryObj.setVH_UNITID(entityId);
				List lst = imageService.queryVMHostByVMObj(queryObj);
				VMHostObj reObj = null;
				if (lst.size() > 0) {
					reObj = (VMHostObj) lst.get(0);
				}
				// 删除entitytree表中的数据
				entityTreeDao.delTreeNode(obj);
				// 删除TB_CLOUD_ENTITY_CON表中的数据
				EntityConObj conObj = new EntityConObj();
				conObj.setType(Integer.parseInt(TypeConstant.VMWARE_IMAGE));
				conObj.setEntityId(entityId);
				entityTreeDao.deleteConObj(conObj);
				// 删除虚拟机表vhhost_info数据
				VMHostObj vmObj = new VMHostObj();
				vmObj.setVH_UUID(entityId);
				vmHostDao.deleteByObj(vmObj);

				// 删除模板树表中数据
				TempletTreeObj treeObj = new TempletTreeObj();
				if (reObj != null) {
					treeObj.setTemplet_id(String.valueOf(reObj.getID()));
					templetTreeDao.deleteMenu(treeObj);
				}
				// 删除脚本关联表里的数据
				ScriptConObj SCObj = new ScriptConObj();
				if (reObj != null) {
					SCObj.setTempletId(String.valueOf(reObj.getID()));
					scriptDao.deleteScriptRelation(SCObj);
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
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
	 * @createtime Sep 18, 2012 1:35:01 PM
	 */
	public String moveToDateCenter(String dcName, String clName, EntityTreeObj obj) {
		String result = null;
		String entity_id = obj.getEntityId();
		try {
			String url = "/vmware/host/moveDataCenter/[dcName:" + dcName + "].[hostName:"
					+ entity_id + "]/";
			String moveResult = InvokeUtil.invoke(url);
			Map ps = ParamParser.makeup(moveResult);
			String code = (String) ps.get(ResponseCode.RESPONSE_CODE);
			if (code.equals(ResponseCode.SUCCESS)) {
				/** 通知 */
				try {
					NoticeUtil.getInstance().updateCMDBDC(dcName, Operation.OPER_REL_MODI);// cmdb
					NoticeUtil.getInstance().updateCMDBCluster(clName, Operation.OPER_REL_MODI);// cmdb
				} catch (Exception e) {

				}
				// try {
				// NoticeUtil.getInstance().updateHostRelation(entity_id);
				// } catch (Exception ex) {
				//
				// }
				entityTreeDao.updateTreeNode(obj);
				result = "1";
			} else {
				String mark = (String) ps.get(ResponseCode.RESPONSEREMARK);
				result = mark;
			}
		} catch (Exception e) {
			result = e.getMessage();
		}
		return result;
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
	public String cloneVirtualToCluster(String vmCode, VmwManForm theForm) {
		String CloneResult = "-1";
		
		String clusterCode = theForm.getCLUSTER_ID();
		String newName = theForm.getNAME_EN();
		String hostname = theForm.getHostname();
		String numCpus = theForm.getCPU();
		String memInMb = theForm.getMEMORY();
		String memUnit = theForm.getMemUnit();
		if(memUnit.equals("2")){
			memInMb = String.valueOf(Integer.parseInt(memInMb)*1024);
		}
		String storageId = theForm.getDATASTORE_ID();
		String diskSize = String.valueOf(Integer.parseInt(theForm.getSTORAGE())*1024*1024);
		String netType = theForm.getNetType();
		String net = theForm.getNET();
		String ip = "";
		String dns = "";
		String gateway = "";
		String subnetMask = "";
		String vlan = "";
		
		DataStoreObj storeObj = new DataStoreObj();
		storeObj.setID(Integer.parseInt(storageId));
		List storeLst = dataStoreDao.queryForListByObj(storeObj);
		storeObj = (DataStoreObj)storeLst.get(0);
		String dsCode = storeObj.getStore_uuid();
		//初始化参数
		TbCloud2NetInfoObj netInfoObj = new TbCloud2NetInfoObj();
		netInfoObj.setNET_ID(net);
		netInfoObj = tbNetDao.queryByObj(netInfoObj);
		dns = netInfoObj.getDNS1();
		gateway = netInfoObj.getGATEWAY1();
		subnetMask = netInfoObj.getSUBNET();
		vlan = netInfoObj.getVLAN_ID();
		if ("1".equals(netType)) {
			ip = theForm.getIP();
		} else {
			TbCloud2IpInfoObj ipobj = new TbCloud2IpInfoObj();
			ipobj.setNET_ID(net);
			ipobj.setISUSED("0");
			List ipLst = tbIpDao.queryForListByObj(ipobj);
			if(ipLst!=null && ipLst.size()>0){
				ipobj = (TbCloud2IpInfoObj)ipLst.get(0);
				ip = ipobj.getIPADDRESS();
			}
		}
		
		try {
			String param = "/vmware/domain/cloneAndConfig/" + "[vmcode:"+vmCode+"].[clusterCode:"+clusterCode+"].[newName:"+newName+"].[numCpus:"+numCpus+
					"].[memInMb:"+memInMb+"].[dsCode:"+ dsCode +"].[ip:"+ip+"].[dns:"+dns+"].[gateway:"+gateway+"].[subnetMask:"+subnetMask
					+"].[diskSize:"+ diskSize +"].[vlan:"+ vlan +"].[hostname:"+hostname+"]/";
			String result = InvokeUtil.invoke(param);
			Map ps = ParamParser.makeup(result);
			CloneResult = (String) ps.get(RESPONSE_CODE);
			if (CloneResult.equals(ResponseCode.SUCCESS)) {
				String code = (String) ps.get(ResponseCode.CODE);
				// try {
				// NoticeUtil.getInstance().addVM(vmName);
				// } catch (Exception ex) {
				//
				// }
				// 调用接口查询克隆的虚拟机创建在哪个主机上
				String getMsg = "/vmware/domain/msg/[vmName:" + code + "]/";
				String getResult = InvokeUtil.invoke(getMsg);
				Map map = ParamParser.makeup(getResult);
				String hostName = (String) map.get("hostName");
				String hostCode = (String) map.get("hostCode");
				/** 通知 */
				// 通知cmdb
				try {
					NoticeUtil.getInstance().updateCMDBHost(hostCode, Operation.OPER_REL_MODI);
				} catch (Exception e) {

				}
				try {
					String vmcode = (String) map.get("CODE");
					NoticeUtil.getInstance().addVM(vmcode);
				} catch (Exception ex) {

				}
				int vmId = vmHostDao.queryVhostIdSequence();// 查询vmhost_info表序列作为虚拟机唯一标识；
				// 向实体树表中插入数据
				EntityTreeObj obj = new EntityTreeObj();
				obj.setName(hostName);
				obj.setType(TypeConstant.VMWARE_HOST);
				obj = entityTreeDao.queryTreeNode(obj);
				EntityTreeObj en = new EntityTreeObj();
				en.setName(newName);
				en.setType(TypeConstant.VMWARE_VM);
				en.setParentId(obj.getId());
				en.setEntityId(code);
				int ret1 = entityTreeDao.insertTreeNode(en);
				// 向tb_cloud_entity_con表中插入数据
				EntityConObj ec = new EntityConObj();
				// 查询主机在哪个数据中心下
				// EntityConObj cObj = new EntityConObj();
				// cObj.setName(hostName);
				// cObj.setType(Integer.parseInt(TypeConstant.VMWARE_HOST));
				// List temp = entityTreeDao.queryEntityData(cObj);
				// if (temp != null) {
				// cObj = (EntityConObj) temp.get(0);
				// ec.setDataCenterId(cObj.getDataCenterId());
				// ec.setClusterId(cObj.getClusterId());
				// }

				EntityTreeObj etbj = new EntityTreeObj();
				etbj.setEntityId(hostCode);
				List<Map<String, Integer>> templist = entityTreeDao.queryHostClDcId(etbj);
				Map<String, Integer> cldcIdMap = null;
				if (templist != null && templist.size() > 0) {
					cldcIdMap = templist.get(0);
				}
				ec.setDataCenterId(cldcIdMap.get("dcId"));
				ec.setClusterId(cldcIdMap.get("clusterId"));
				ec.setName(newName);
				ec.setType(0);
				ec.setEntityId(code);
				int ret3 = entityTreeDao.insertConObj(ec);

				// 向VMHOST_INFO表中插入数据
				VMHostObj vmObj = new VMHostObj();
				vmObj.setVH_NAME(newName);
				vmObj.setEQ_ID("1_01_001_0029_01");
				String VH_ID = createNewVhostId2(vmObj.getEQ_ID());
				vmObj.setVH_ID(VH_ID);
				vmObj.setID(vmId);
				vmObj.setVH_TYPE("1");// vm_host_info表中的vmware的类型为1
				vmObj.setVH_UUID(code);
				String system = (String) map.get("guestName");
				String memoryMB = (String) map.get("memoryMB");
				String cpuNum = (String) map.get("cpuNum");
				String storage = (String) map.get("storage");
				String power = (String) map.get("power");
				String newip = (String) map.get("ip");
				if (newip != null) {
					vmObj.setVH_IP(newip);
				}
				if (power != null && power.equals("poweredOn")) {
					vmObj.setVH_STAT("1");
				} else if (power != null && power.equals("poweredOff")) {
					vmObj.setVH_STAT("0");
				}

				vmObj.setVH_CPU(cpuNum);
				vmObj.setVH_SYSTEM(system);
				// vmObj.setVH_IP("172.21.0.123");
				vmObj.setVH_MEM(memoryMB);
				vmObj.setVH_STORAGE(storage);
				int ret4 = vmHostDao.insertByVMhostObj(vmObj);

				// 查询实体的自增序列ID
				VMHostObj queryObj = new VMHostObj();
				queryObj.setVH_UNITID(vmCode);
				List lst = imageService.queryVMHostByVMObj(queryObj);
				VMHostObj reObj = null;
				if (lst.size() > 0) {
					reObj = (VMHostObj) lst.get(0);
					// 向tb_cloud2_script_con表中插入数据，建立虚拟机和脚本的关联
					ScriptConObj scriptConObj = new ScriptConObj();
					scriptConObj.setTempletId(String.valueOf(reObj.getID()));
					scriptConObj.setType("0");
					List relationList = scriptDao.queryScriptListByTempletObj(scriptConObj);
					for (Object o : relationList) {
						ScriptConObj scrCon = (ScriptConObj) o;
						ScriptObj scriptObj = new ScriptObj();
						scriptObj.setId(Integer.parseInt(scrCon.getScriptId()));
						List scriptList = scriptDao.listScript(scriptObj);
						scriptObj = (ScriptObj) scriptList.get(0);
						ScriptConObj addScriptConObj = new ScriptConObj();
						addScriptConObj.setScriptId(String.valueOf(scriptObj.getId()));
						addScriptConObj.setTempletId(String.valueOf(vmId));
						addScriptConObj.setType("1");
						int ret = scriptDao.addRelation(addScriptConObj);
					}
				}
				
				//把ip设为使用状态
				TbCloud2IpInfoObj ipObj = new TbCloud2IpInfoObj();
				ipObj.setIPADDRESS(ip);
				ipObj.setISUSED("1");
				tbIpDao.updateIPByObj(ipObj);
				
				//插入权限
				VmAuthorityObj authObj = new VmAuthorityObj();
				HttpSession session = Struts2Utils.getRequest().getSession();
				authObj.setUSERID(Integer.parseInt(session.getAttribute("id").toString()));
				authObj.setENTITY_NAME(theForm.getNAME_EN());
				authObj.setENTITY_ID(code);
				authObj.setTYPE(TypeConstant.VMWARE_VM);
				authObj.setOPERAUTHORITY("0");
				vmAuthorityService.insertByObj(authObj);
				
			}
		} catch (Exception ex) {
			CloneResult = ex.getMessage();
		}
		return CloneResult;
	}

	/**
	 * 
	 * @Title: cloneVMByTem
	 * @Description: 在主机节点通过模板克隆虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 20, 2013 10:04:52 AM
	 */
	public String cloneVMByTem(Map map) {
		String result = "";
		EntityTreeObj obj = new EntityTreeObj();
		String host_code = map.get("host_code").toString();
		obj.setEntityId(host_code);
		obj.setType(TypeConstant.VMWARE_HOST);
		obj = entityTreeDao.queryTreeNode(obj);
		EntityTreeObj clusterObj = new EntityTreeObj();
		clusterObj.setId(obj.getParentId());
		clusterObj = entityTreeDao.queryTreeNode(clusterObj);
		String clusterName = clusterObj.getEntityId();
		String name = map.get("name").toString();
		String tem_code = map.get("tem_code").toString();
		String param = "/vmware/domain/clone/" + "[vmName:" + tem_code + "].[cloneName:" + name
				+ "].[hostName:" + host_code + "].[dsName:" + map.get("store_code").toString()
				+ "].[clusterName:" + clusterName + "]/";
		result = InvokeUtil.invoke(param);
		Map ps = ParamParser.makeup(result);
		result = ps.get("responseCode").toString();
		String vm_code = ps.get("CODE").toString();
		if ("1".equals(result)) {
			// 向实体树表中插入数据
			EntityTreeObj en = new EntityTreeObj();
			en.setName(name);
			en.setType(TypeConstant.VMWARE_VM);
			en.setEntityId(vm_code);
			en.setParentId(obj.getId());
			int ret1 = entityTreeDao.insertTreeNode(en);
			// 向tb_cloud_entity_con表中插入数据
			EntityConObj ec = new EntityConObj();
			try {
				ec.setDataCenterId(clusterObj.getParentId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			ec.setClusterId(clusterObj.getId());
			ec.setName(name);
			ec.setType(0);
			ec.setEntityId(vm_code);
			int ret3 = entityTreeDao.insertConObj(ec);
			int vmId = vmHostDao.queryVhostIdSequence();// 查询vmhost_info表序列作为虚拟机唯一标识；
			// 向VMHOST_INFO表中插入数据
			// /查询模板对应的虚拟机的部分虚拟机-----start
			TemManObj tem = new TemManObj();
			tem.setId(tem_code);
			List<TemManObj> list = temManDao.queryForList(tem);
			if (list != null && list.size() > 0) {
				tem = list.get(0);
			}
			// /查询模板对应的虚拟机的部分虚拟机-----end
			VMHostObj vmObj = new VMHostObj();
			vmObj.setVH_NAME(name);
			vmObj.setEQ_ID("1_01_001_0029_01");
			String VH_ID = createNewVhostId2(vmObj.getEQ_ID());
			vmObj.setVH_ID(VH_ID);
			vmObj.setID(vmId);
			vmObj.setVH_UUID(vm_code);
			vmObj.setVH_TYPE("1");// vm_host_info表中的vmware的类型为1
			vmObj.setVH_CPU(map.get("cpu").toString());
			vmObj.setVH_MEM(map.get("mem").toString());
			vmObj.setVH_STORAGE(map.get("store").toString());
			vmObj.setVH_SYSTEM(tem.getSystem());
			vmObj.setVH_IP(map.get("ip").toString());
			vmObj.setVH_STAT("1");
			int ret4 = vmHostDao.insertByVMhostObj(vmObj);
			// ///重新配置虚拟机-----------start
			// //重新配置虚拟机结束-------end
		}
		return result;

	}
}
