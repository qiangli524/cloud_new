package com.sitech.basd.yicloud.service.xen;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sitech.basd.sxcloud.cloud.dao.net.TbIpDao;
import com.sitech.basd.sxcloud.cloud.dao.resource.HostInfoDao;
import com.sitech.basd.sxcloud.cloud.dao.vmhost.VMHostDao;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.rsmu.config.Constant;
import com.sitech.basd.sxcloud.util.EncryptUtil;
import com.sitech.basd.yicloud.dao.kvm.KvmManDao;
import com.sitech.basd.yicloud.dao.xencluster.XenClusterDao;
import com.sitech.basd.yicloud.dao.xenconn.ConnInfoDao;
import com.sitech.basd.yicloud.dao.xenstore.XenStoreDao;
import com.sitech.basd.yicloud.dao.xentree.XenEntityTreeDao;
import com.sitech.basd.yicloud.domain.kvm.TbYicloudOsTypeObj;
import com.sitech.basd.yicloud.domain.xen.Host;
import com.sitech.basd.yicloud.domain.xen.Nic;
import com.sitech.basd.yicloud.domain.xen.Sr;
import com.sitech.basd.yicloud.domain.xen.Vif;
import com.sitech.basd.yicloud.domain.xen.Vm;
import com.sitech.basd.yicloud.domain.xenconn.ConnectionInfo;
import com.sitech.basd.yicloud.domain.xenstore.XenStoreObj;
import com.sitech.basd.yicloud.domain.xentree.XenClusterObj;
import com.sitech.basd.yicloud.domain.xentree.XenEntityConObj;
import com.sitech.basd.yicloud.domain.xentree.XenEntityTreeObj;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.TypeConstant;

public class XenManServiceImpl implements XenManService {
	private KvmManDao kvmManDao;
	private VMHostDao vmHostDao;
	private XenEntityTreeDao xenEntityTreeDao;
	private HostInfoDao hostInfoDao;
	private XenClusterDao xenClusterDao;
	private XenStoreDao xenStoreDao;
	private ConnInfoDao connInfoDao;
	private TbIpDao tbIpDao;
	

	public void setTbIpDao(TbIpDao tbIpDao) {
		this.tbIpDao = tbIpDao;
	}

	public void setConnInfoDao(ConnInfoDao connInfoDao) {
		this.connInfoDao = connInfoDao;
	}

	public void setXenStoreDao(XenStoreDao xenStoreDao) {
		this.xenStoreDao = xenStoreDao;
	}

	public void setXenClusterDao(XenClusterDao xenClusterDao) {
		this.xenClusterDao = xenClusterDao;
	}

	public void setHostInfoDao(HostInfoDao hostInfoDao) {
		this.hostInfoDao = hostInfoDao;
	}

	public void setVmHostDao(VMHostDao vmHostDao) {
		this.vmHostDao = vmHostDao;
	}

	public void setKvmManDao(KvmManDao kvmManDao) {
		this.kvmManDao = kvmManDao;
	}

	public void setXenEntityTreeDao(XenEntityTreeDao xenEntityTreeDao) {
		this.xenEntityTreeDao = xenEntityTreeDao;
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
	 * @createtime Sep 9, 2012 10:02:38 AM
	 */
	public String createNakeMac(String osId, String vmName, Integer cpu,
			String storage, Long mem, XenEntityTreeObj e, String clusterId,
			String store_uuid, String net, String pool_uuid, String host_uuid, String vidUuid) {
		// 设置进度条进度
		Constant.PROCESS_LEVEL = 90;
		String param = "/xen/vm/create/[poolUuid:"+pool_uuid+"].[vmUuid:" + osId + "].[vmName:"
				+ vmName + "].[cpuNum:" + cpu + "].[vdiSize:" + storage 
				+ "].[hostUuid:" + host_uuid+ "].[vdiUuid:" + vidUuid 
				+ "].[memDynamicMax:" + mem + "].[srUuid:" + store_uuid 
				+ "].[networkUuid:" + net + "]/";
		String result = InvokeUtil.invoke(param);
		Constant.PROCESS_LEVEL = 100;
		JSONObject j = JSONObject.fromObject(result);
		String VH_UUID = null;
		if (j.get("responseCode").equals("1")) {
			VH_UUID = (String) j.get("vmUuid");
			// 向entity_tree表中插入数据
			int ID = vmHostDao.queryVhostIdSequence();
			e.setEntityId(String.valueOf(ID));
			e.setType(TypeConstant.XEN_VM);
			e.setUuid(VH_UUID);
			int ret = xenEntityTreeDao.insertTreeNode(e);
			// 向虚拟机表中插入数据
			VMHostObj v = new VMHostObj();
			v.setVH_NAME(vmName);
			v.setID(ID);
			v.setVH_ID("0");
			v.setVH_CPU(cpu.toString());
			v.setVH_MEM(mem.toString());
			v.setVH_STORAGE(storage);
			v.setVH_UUID(VH_UUID);
			v.setVH_TYPE("3");// xen类型
			ret = vmHostDao.insertByVMhostObj(v);
			// 向entity_con表中插入数据
			// 查询数据中心id
			XenEntityTreeObj dcTree = new XenEntityTreeObj();
			dcTree.setId(Integer.parseInt(clusterId));
			dcTree = xenEntityTreeDao.queryTreeNode(dcTree);
			XenEntityConObj en = new XenEntityConObj();
			en.setDataCenterId(dcTree.getParentId());// 暂时写死，后续多个数据中心时，需要修改
			en.setClusterId(Integer.parseInt(clusterId));
			en.setName(vmName);
			en.setEntityId(String.valueOf(ID));
			en.setMem(mem.toString());
			en.setCpu(cpu.toString());
			en.setType(Integer.parseInt(TypeConstant.XEN_VM));
			en.setStorage(storage);
			en.setHost(String.valueOf(e.getParentId()));
			ret = xenEntityTreeDao.insertConObj(en);
		}
		return result;
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
	 * @createtime Sep 9, 2012 10:35:42 AM
	 */
	public String listVMInfo(String VH_UUID, String pool_uuid) {
		String param = "/xen/vminfo/lookup/[vmUuid:" + VH_UUID + "].[poolUuid:"
				+ pool_uuid + "]/";
		String result = InvokeUtil.invoke(param);
		return result;
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
	public String adjustVM(String cpu, String cpuMax, String VH_UUID,
			String pool_uuid, String host_uuid, long dynamicMax,
			long dynamicMin, long staticMax, long staticMin, long storage) {
		String param = "/xen/vm/static/reconfig/[vmUuid:" + VH_UUID
				+ "].[poolUuid:" + pool_uuid + "].[hostUuid:" + host_uuid
				+ "].[cpuMax:" + Long.parseLong(cpuMax) + "].[cpuNum:"
				+ Long.parseLong(cpu) + "].[addSrSize:" + storage
				+ "].[memStaticMax:" + staticMax + "].[memDynamicMax:"
				+ dynamicMax + "].[memDynamicMin:" + dynamicMin
				+ "].[memStaticMin:" + staticMin + "]/";
		String result = InvokeUtil.invoke(param);
		return result;
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
	 * @createtime Sep 10, 2012 8:49:23 AM
	 */
	public String putVMState(String VH_UUID, String state, XenEntityTreeObj e,
			String pool_uuid,String masterHost_uuid, String host_uuid) {
		String param = null;
		String result = null;
		if (state.equals("destory")) {
			param = "/xen/vm/destory/[vmUuid:" + VH_UUID + "].[poolUuid:"+ pool_uuid + "]/";
			result = InvokeUtil.invoke(param);
			JSONObject jso = JSONObject.fromObject(result);
			if (jso.get("responseCode").equals("1")) {
				int ret = xenEntityTreeDao.delTreeNode(e);
				XenEntityConObj en = new XenEntityConObj();
				en.setType(Integer.parseInt(TypeConstant.XEN_VM));
				en.setEntityId(e.getEntityId());
				ret = xenEntityTreeDao.deleteConObj(en);
				VMHostObj v = new VMHostObj();
				v.setID(Integer.parseInt(e.getEntityId()));
				ret = vmHostDao.deleteByObj(v);
			}
		} else {
			param = "/xen/vm/power/[vmUuid:" + VH_UUID + "].[powerState:"
					+ state + "].[poolUuid:" + pool_uuid
					+ "].[hostUuid:" + host_uuid + "]/";
			result = InvokeUtil.invoke(param);
		}
		JSONObject js = JSONObject.fromObject(result);
		if (js.get("responseCode").equals("1")
				&& (state.equals("RUNNING") || state.equals("HALTED"))) {
			js.put("refresh", "no");
			String hostUuid = js.getString("hostUuid");
			if (!hostUuid.equals(host_uuid)) {
				XenEntityTreeObj obj = new XenEntityTreeObj();
				obj.setUuid(hostUuid);
				obj = xenEntityTreeDao.queryTreeNode(obj);
				XenEntityTreeObj xen = new XenEntityTreeObj();
				xen.setParentId(obj.getId());
				xen.setUuid(VH_UUID);
				xen.setName(e.getName());
				xen.setId(e.getId());
				xenEntityTreeDao.updateTreeNode(xen);
				//关闭时，暂时先考虑在两个主机之间的操作，不考虑在虚拟机在集群上，后续更改
				if(state.equals("HALTED")){
					js.remove("refresh");
					js.put("refresh", "yes");
					js.put("nodeId1", obj.getId());
				}
			}
			result = js.toString();
		}

		return result;
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
	public String listHostInfo(String hostUuid, String pool_uuid) {
		String param = "/xen/hostinfo/lookup/[hostUuid:" + hostUuid
				+ "].[poolUuid:" + pool_uuid + "]/";
		String result = InvokeUtil.invoke(param);
		return result;
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
	 * @createtime Sep 10, 2012 9:12:30 AM
	 */
	public String createVMSnapShot(String VH_UUID, String shot_name,
			String desc, String pool_uuid, String host_uuid) {
		String param = "/xen/vm/snapshot/[vmUuid:" + VH_UUID
				+ "].[snapshotName:" + shot_name + "].[description:" + desc
				+ "].[poolUuid:" + pool_uuid  + "]/";
		String result = InvokeUtil.invoke(param);
		return result;
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
	public String createTemByVm(String temName, String vh_uuid, int parent_id,
			String pool_uuid) {
		String param = "/xen/template/create/[vmUuid:" + vh_uuid + "].[vmName:"
				+ temName + "].[poolUuid:" + pool_uuid + "]/";
		String result = InvokeUtil.invoke(param);
		JSONObject j = JSONObject.fromObject(result);
		//查询集群节点
		XenEntityTreeObj entityObj = new XenEntityTreeObj();
		entityObj.setUuid(pool_uuid);
		entityObj = xenEntityTreeDao.queryTreeNode(entityObj);
		//向库中插入数据
		if (j.get("responseCode").equals("1")) {
			XenEntityTreeObj e = new XenEntityTreeObj();
			e.setName(temName);
			String templateUuid = (String) j.get("vmUuid");
			e.setUuid(templateUuid);
			e.setType(TypeConstant.XEN_IMAGE);
			
			if(Boolean.parseBoolean(j.getString("srShared"))){
				//模板在集群下
				e.setParentId(entityObj.getId());
			}else{
				//模板在主机下
				String hostUuid = j.getString("hostUuid");
				XenEntityTreeObj hostObj = new XenEntityTreeObj();
				hostObj.setUuid(hostUuid);
				hostObj = xenEntityTreeDao.queryTreeNode(entityObj);
				e.setParentId(hostObj.getId());
			}
			int id = vmHostDao.queryVhostIdSequence();
			// 向entity_tree表中插入数据
			
			e.setEntityId(String.valueOf(id));
			int ret = xenEntityTreeDao.insertTreeNode(e);
			// 获取虚拟机的详细信息
			String vm_param = "/xen/vminfo/lookup/[vmUuid:" + vh_uuid
					+ "].[poolUuid:" + pool_uuid + "]/";
			String vm_result = InvokeUtil.invoke(vm_param);
			JSONObject o = JSONObject.fromObject(vm_result);
			long cpu = Long.parseLong((String) o.get("cpuNum").toString());
			Double mem = Double.parseDouble(o.get("memDynamicMax").toString());
			DecimalFormat nf = new DecimalFormat("0.00");
			String memory = nf.format(mem);
			// 向虚拟机表中插入模板信息
			VMHostObj v = new VMHostObj();
			v.setID(id);
			v.setVH_NAME(temName);
			v.setVH_ID("0");
			v.setVH_CPU(String.valueOf(cpu));
			v.setVH_MEM(memory);
			v.setVH_UUID(j.get("vmUuid").toString());
			v.setVH_TYPE("6");// xen类型
			ret = vmHostDao.insertByVMhostObj(v);
			
			
			//插入tb_xen_entity_con表
			XenEntityConObj con = new XenEntityConObj();
			con.setClusterId(entityObj.getId());
			con.setDataCenterId(entityObj.getParentId());
			con.setEntityId(String.valueOf(id));
			con.setName(temName);
			con.setType(Integer.parseInt(TypeConstant.XEN_IMAGE));
			xenEntityTreeDao.insertConObj(con);
			
			
		}
		return result;
	}

	public String createVMByTem(String tem_uuid, String cpu, String currentcpu,
			String staticMin, String dynamicMin, String dynamicMax,
			String staticMax, XenEntityTreeObj e, String clusterId,
			String pool_uuid, String host_uuid,String ipAddress) {
		Constant.PROCESS_LEVEL = 100;
		String param = "/xen/vm/clone/[vmUuid:" + tem_uuid + "].[vmName:"
				+ e.getName() + "].[cpuMax:" + cpu + "].[cpuNum:" + cpu
				+ "].[memStaticMax:" + Long.parseLong(staticMax) * 1024 * 1024
				+ "].[memDynamicMax:" + Long.parseLong(dynamicMax) * 1024
				* 1024 + "].[memDynamicMin:" + Long.parseLong(dynamicMin)
				* 1024 * 1024 + "].[memStaticMin:" + Long.parseLong(staticMin)
				* 1024 * 1024 + "].[poolUuid:" + pool_uuid + "].[hostUuid:"
				+ host_uuid + "].[mac:da-9c-87-b3-1a-56]/";
		String result = InvokeUtil.invoke(param);
		JSONObject j = JSONObject.fromObject(result);
		if (j.get("responseCode").equals("1")) {
			String VH_UUID = j.get("vmUuid").toString();
			// 向entity_tree表中插入数据
			int ID = vmHostDao.queryVhostIdSequence();
			e.setEntityId(String.valueOf(ID));
			e.setType(TypeConstant.XEN_VM);
			e.setUuid(VH_UUID);
			int ret = xenEntityTreeDao.insertTreeNode(e);
			// 向虚拟机表中插入数据
			VMHostObj v = new VMHostObj();
			v.setVH_NAME(e.getName());
			v.setID(ID);
			v.setVH_ID("0");
			v.setVH_CPU(String.valueOf(cpu));
			v.setVH_MEM(String.valueOf(dynamicMax));
			v.setVH_UUID(VH_UUID);
			v.setVH_TYPE("3");// xen类型
			ret = vmHostDao.insertByVMhostObj(v);
			// 向entity_con表中插入数据
			XenEntityConObj en = new XenEntityConObj();
			en.setDataCenterId(2);// 暂时写死，后续多个数据中心时，需要修改
			en.setClusterId(Integer.parseInt(clusterId));
			en.setName(e.getName());
			en.setEntityId(String.valueOf(ID));
			en.setMem(String.valueOf(dynamicMax));
			en.setCpu(String.valueOf(cpu));
			en.setType(Integer.parseInt(TypeConstant.XEN_VM));
			en.setHost(String.valueOf(e.getParentId()));
			ret = xenEntityTreeDao.insertConObj(en);
			j.put("nodeId", e.getParentId());
			result = j.toString();
		
			/**
			 * 检测虚拟机是否启动成功
			 */
			try {
				Thread.sleep(50000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			/**
			 * 通过脚本的方式修改ip地址
			 */
			param = "/xen/ip/vm/modify/[scriptName:~opt~auto_modify_ip.sh].[ip:"
					+ ipAddress
					+ "].[originalIp:172.21.3.32].[ethName:eth0].[gateway:172.21.3.1].[netmask:255.255.255.0].[password:111111]/";
			result = InvokeUtil.invoke(param);
			j = JSONObject.fromObject(result);
			if (j.get("responseCode").equals("1")) {
				TbCloud2IpInfoObj ipObj = new TbCloud2IpInfoObj();
				ipObj.setISUSED("1");
				ipObj.setIPADDRESS(ipAddress);
				tbIpDao.updateIPStat(ipObj);
			}
			/**
			 * 获取虚拟机的所有网卡信息
			 * 目前只修改eth0所以只需要获取eth0的网卡信息即可
			 */
			String vifUuid="";
			param = "/xen/vif/info/get/[vmUuid:" + VH_UUID+ "].[poolUuid:" + pool_uuid + "]/";
			result = InvokeUtil.invoke(param);
			JSONArray jo = JSONArray.fromObject(result);
			List<JSONObject> nets = JSONArray.toList(jo, JSONObject.class);
			if (nets.get(0).get("responseCode").equals("1")) {
				nets.remove(0);
				for (JSONObject json : nets) {
					if("0".equals((String)json.get("vifDevice"))){
						vifUuid=(String)json.get("vifUuid");
						break;
					}
				}
				
			}
			
//			/**
//			 * 检测虚拟机是否启动成功
//			 */
//			try {
//				Thread.sleep(50000);
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}
			
			/**
			 * ip修改成功后，把修改网卡的mac地址，设置为自动获取即可
			 */
			if(!vifUuid.equals("")){
				param="/xen/vif/vm/modify/[vifUuid:"+vifUuid+"].[macAutogenerated:true]/";
				result = InvokeUtil.invoke(param);
				j = JSONObject.fromObject(result);
				if (j.get("responseCode").equals("1")) {
					//修改mac地址成功，修改ip结束
				}
			}
		}
		return result;
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
	public String queryCreateVMByTem(String tem_uuid, XenEntityTreeObj e,
			String clusterId, String pool_uuid, String host_uuid) {
		Constant.PROCESS_LEVEL = 100;
		String param = "/xen/vm/create/quickly/[vmUuid:" + tem_uuid
				+ "].[vmName:" + e.getName() + "].[poolUuid:" + pool_uuid
				+ "]/";
		String result = InvokeUtil.invoke(param);
		JSONObject j = JSONObject.fromObject(result);
		if (j.get("responseCode").equals("1")) {
			String VH_UUID = j.get("vmUuid").toString();
			// 向entity_tree表中插入数据
			int ID = vmHostDao.queryVhostIdSequence();
			e.setEntityId(String.valueOf(ID));
			e.setType(TypeConstant.XEN_VM);
			int ret = xenEntityTreeDao.insertTreeNode(e);
			// 向虚拟机表中插入数据
			VMHostObj v = new VMHostObj();
			v.setVH_NAME(e.getName());
			v.setID(ID);
			v.setVH_ID("0");
			v.setVH_UUID(VH_UUID);
			v.setVH_TYPE("3");// xen类型
			ret = vmHostDao.insertByVMhostObj(v);
			// 向entity_con表中插入数据
			XenEntityConObj en = new XenEntityConObj();
			en.setDataCenterId(2);// 暂时写死，后续多个数据中心时，需要修改
			en.setClusterId(Integer.parseInt(clusterId));
			en.setEntityId(String.valueOf(ID));
			en.setType(Integer.parseInt(TypeConstant.XEN_VM));
			en.setHost(String.valueOf(e.getParentId()));
			ret = xenEntityTreeDao.insertConObj(en);
		}
		return result;
	}

	/**
	 * 
	 * @Title: cloneVMByVM
	 * @Description: 通过虚拟机克隆虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @createtime Sep 13, 2012 2:44:14 PM
	 */
	public String cloneVMByVM(String vmUuid, XenEntityTreeObj e,
			String clusterId, String pool_uuid, String host_uuid,
			String store_uuid, String desc) {
		String param = null;
		Constant.PROCESS_LEVEL = 90;
		if (store_uuid != null && !"".equals(store_uuid)) {
			param = "/xen/vm/full/copy/[poolUuid:" + pool_uuid + "].[hostUuid:"
					+ host_uuid + "].[vmUuid:" + vmUuid + "].[vmName:"
					+ e.getName() + "].[srUuid:" + store_uuid + "].[vmDesc:"
					+ desc + "]/";
		} else {
			param = "/xen/vm/fast/copy/[poolUuid:" + pool_uuid + "].[hostUuid:"
					+ host_uuid + "].[vmUuid:" + vmUuid + "].[vmName:"
					+ e.getName() + "].[vmDesc:" + desc + "]/";
		}

		String result = InvokeUtil.invoke(param);
		Constant.PROCESS_LEVEL = 100;
		JSONObject j = JSONObject.fromObject(result);
		if (j.get("responseCode").equals("1")) {
			// 向entity_tree表中插入数据
			int ID = vmHostDao.queryVhostIdSequence();
			e.setEntityId(String.valueOf(ID));
			e.setType(TypeConstant.XEN_VM);
			e.setUuid((String) j.getString("vmUuid"));
			int ret = xenEntityTreeDao.insertTreeNode(e);
			// 向虚拟机表中插入数据
			VMHostObj v = new VMHostObj();
			v.setVH_NAME(e.getName());
			v.setID(ID);
			v.setVH_ID("0");
			v.setVH_CPU(String.valueOf(j.get("cpuSize")));
			v.setVH_MEM(String.valueOf(j.get("memSize")));
			// v.setVH_STORAGE(j.get(""));
			v.setVH_UUID(j.get("vmUuid").toString());
			v.setVH_TYPE("3");// xen类型
			ret = vmHostDao.insertByVMhostObj(v);
			// 向entity_con表中插入数据
			XenEntityConObj en = new XenEntityConObj();
			en.setDataCenterId(2);// 暂时写死，后续多个数据中心时，需要修改
			en.setClusterId(Integer.parseInt(clusterId));
			en.setName(e.getName());
			en.setEntityId(String.valueOf(ID));
			en.setMem(String.valueOf(j.get("memSize")));
			en.setCpu(String.valueOf(j.get("cpuSize")));
			en.setType(Integer.parseInt(TypeConstant.XEN_VM));
			// en.setStorage(String.valueOf(store));
			en.setHost(String.valueOf(e.getParentId()));
			ret = xenEntityTreeDao.insertConObj(en);
		}

		return result;
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
	public String snapshotManager(String vh_uuid, String pool_uuid,
			String host_uuid) {
		String param = "/xen/snapshot/lookup/[vmUuid:" + vh_uuid
				+ "].[poolUuid:" + pool_uuid + "]/";
		String result = InvokeUtil.invoke(param);
		return result;
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
	public String delSnapshot(String uuid, String pool_uuid, String host_uuid) {
		String param = "/xen/vm/destory/[vmUuid:" + uuid + "].[poolUuid:"
				+ pool_uuid + "].[hostUuid:" + host_uuid + "]/";
		String result = InvokeUtil.invoke(param);
		return result;
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
	public String recoverSnapShot(String uuid, String pool_uuid,
			String host_uuid) {
		String param = "/xen/snapshot/revert/[vmUuid:" + uuid
				+ "].[poolUuid:" + pool_uuid + "].[hostUuid:"
				+ host_uuid + "]/";
		String result = InvokeUtil.invoke(param);
		return result;
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
	public String listDefaultTem(String pool_uuid) {
		String param = "/xen/defaulttemplates/lookup/[poolUuid:"
				+ pool_uuid + "]/";
		String result = InvokeUtil.invoke(param);
		JSONArray j = JSONArray.fromObject(result);
		List<JSONObject> jsonO = JSONArray.toList(j, JSONObject.class);

		if (jsonO.get(0).get("responseCode").equals("1")) {
			jsonO.remove(0);
			for (int i = 0; i < jsonO.size(); i++) {
				TbYicloudOsTypeObj t = new TbYicloudOsTypeObj();
				t.setVIR_TYPE("xen");
				String type_name = jsonO.get(i).get("defaultTemplateName")
						.toString();
				t.setTYPE_NAME(type_name);
				if (type_name.contains("CentOS") || type_name.contains("Red")
						|| type_name.contains("Linux")) {
					t.setOS_TYPE("2");
				} else if (type_name.contains("Windows")) {
					t.setOS_TYPE("1");
				} else {
					t.setOS_TYPE("3");
				}
				t.setTYPE_ID(jsonO.get(i).get("defaultTemplateUuid").toString());
				kvmManDao.insertByOsType(t);

			}

			// List<TbYicloudOsTypeObj> list = (List<TbYicloudOsTypeObj>)
			// JsonUtils
			// .jsonToList(subJson, TbYicloudOsTypeObj.class);

		}
		return null;
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
	public String listAllVMByHost(int DataCenterId) {
		TbCloud2HostInfoObj t = new TbCloud2HostInfoObj();
		t.setEq_type("3");
		List<TbCloud2HostInfoObj> list = hostInfoDao.queryForListByObj(t);
		for (TbCloud2HostInfoObj tb : list) {
			String host_uuid = tb.getH_uuid();
			int ID = tb.getId();// 主机ID
			XenEntityTreeObj ent = new XenEntityTreeObj();
			ent.setEntityId(String.valueOf(ID));
			ent = xenEntityTreeDao.queryTreeNode(ent);
			int parent_id = ent.getId();
			String param = "/xen/vms/lookup/[hostUuid:" + host_uuid + "]/";
			String result = InvokeUtil.invoke(param);
			JSONArray j = JSONArray.fromObject(result);
			List<JSONObject> jsonO = JSONArray.toList(j, JSONObject.class);
			if (jsonO.get(0).get("responseCode").equals("1")) {
				jsonO.remove(0);
				for (JSONObject js : jsonO) {
					String vmName = js.get("vmName").toString();
					String vm_uuid = js.get("vmUuid").toString();
					// 向entity_tree中插入数据
					int vm_id = vmHostDao.queryVhostIdSequence();
					XenEntityTreeObj e = new XenEntityTreeObj();
					e.setEntityId(String.valueOf(vm_id));
					e.setName(vmName);
					e.setUuid(vm_uuid);
					e.setParentId(parent_id);
					e.setType(TypeConstant.XEN_VM);
					int ret = xenEntityTreeDao.insertTreeNode(e);
					// 向entity_con中插入数据
					XenEntityConObj en = new XenEntityConObj();
					en.setClusterId(ent.getParentId());
					en.setDataCenterId(DataCenterId);
					en.setName(vmName);
					en.setEntityId(String.valueOf(ID));
					en.setType(0);
					ret = xenEntityTreeDao.insertConObj(en);
					// 向vmHost表中插入数据
					VMHostObj vm = new VMHostObj();
					vm.setID(vm_id);
					vm.setVH_ID("0");
					vm.setVH_NAME(vmName);
					vm.setVH_UUID(vm_uuid);
					ret = vmHostDao.insertByVMhostObj(vm);
				}
			}
		}
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
	public String listTeminfo(String uuid, String pool_uuid, String host_uuid) {
		String param = "/xen/vminfo/lookup/[poolUuid:" + pool_uuid + "].[vmUuid:"
				+ uuid + "]/";
		String result = InvokeUtil.invoke(param);
		return result;
	}

	public void listAllHostsByCluster(int dataCenterId, String poolUuid,
			String poolName, String ip) {
		/**
		 * 查询池链接下所有虚拟机的信息
		 */
		String vm_param = "/xen/vms/lookup/[poolUuid:" + poolUuid + "]/";
		String vm_result = InvokeUtil.invoke(vm_param);
		JSONArray jm = JSONArray.fromObject(vm_result);
		List<JSONObject> vms = JSONArray.toList(jm, JSONObject.class);
		if (vms.get(0).get("responseCode").equals("-1")) {
			return;
		} else {
			vms.remove(0);
		}
		/**
		 * 查询链接下所有主机的信息
		 */
		String host_param = "/xen/hostbypool/lookup/[poolUuid:" + poolUuid
				+ "]/";
		String host_result = InvokeUtil.invoke(host_param);
		JSONArray jo = JSONArray.fromObject(host_result);
		List<JSONObject> hosts = JSONArray.toList(jo, JSONObject.class);
		if (hosts.get(0).get("responseCode").equals("-1")) {
			return;
		} else {
			hosts.remove(0);
		}
		
		/**
		 * 查询链接下所有存储的信息
		 */
		String sr_param = "/xen/srs/all/get/[poolUuid:" + poolUuid
				+ "]/";
		String sr_result = InvokeUtil.invoke(sr_param);
		JSONArray jsr = JSONArray.fromObject(sr_result);
		List<JSONObject> srs = JSONArray.toList(jsr, JSONObject.class);
		if (srs.get(0).get("responseCode").equals("-1")) {
			return;
		} else {
			srs.remove(0);
		}
		// ----------------------接口查询成功，进行数据库操作--------------------------------------
		int cluster_id = xenClusterDao.getIdSequence();
		// 向树表中插入数据
		XenEntityTreeObj e = new XenEntityTreeObj();
		// 向集群表中插入数据
		XenClusterObj clu = new XenClusterObj();
		if (poolName == null || "".equals(poolName)) {
			e.setName(ip);
			clu.setName(ip);
		} else {
			e.setName(poolName);
			clu.setName(poolName);
		}
		e.setEntityId(String.valueOf(cluster_id));
		e.setParentId(dataCenterId);
		e.setType(TypeConstant.XEN_CLUSTER);
		e.setUuid(poolUuid);
		// ---------------------
		clu.setId(cluster_id);
		clu.setC_uuid(poolUuid);
		clu.setType(TypeConstant.XEN_CLUSTER);
		clu.setDrsstate("0");
		clu.setHastate("0");
		xenEntityTreeDao.insertTreeNode(e);
		xenClusterDao.insertForConnection(clu);

		XenEntityTreeObj tree = new XenEntityTreeObj();
		tree.setType(TypeConstant.XEN_CLUSTER);
		tree.setName(clu.getName());
		tree = xenEntityTreeDao.queryTreeNode(tree);
		XenEntityTreeObj cluster_enty = new XenEntityTreeObj();
		cluster_enty.setEntityId(String.valueOf(cluster_id));
		cluster_enty = xenEntityTreeDao.queryTreeNode(cluster_enty);

		// ----------------------构建树中主机和虚拟机和存储之间的关系操作
		List<Host> tempHosts = analysisHostAndVm(hosts, vms,srs, poolUuid);
		
		for (Host host : tempHosts) {
			int host_id = hostInfoDao.getIdSequence();
			String hostName = host.getHostName();
			String hostUuid = host.getHostUuid();
			Integer hostCpuNum=host.getHostCpuNum();
			String hostCpuDesc=host.getHostCpuDesc();
			Double hostCpuSpeed=host.getHostCpuSpeed();
			String hostIp=host.getHostIp();
			long memSize=host.getMemSize();
			XenEntityTreeObj enti = new XenEntityTreeObj();
			if (!hostUuid.equals(poolUuid)) {
				// 向entity_tree中插入数据
				XenEntityTreeObj en = new XenEntityTreeObj();
				en.setEntityId(String.valueOf(host_id));
				en.setName(hostName);
				en.setUuid(hostUuid);
				en.setParentId(tree.getId());
				en.setType(TypeConstant.XEN_HOST);
				xenEntityTreeDao.insertTreeNode(en);
				// 查询主机的id
				enti.setEntityId(String.valueOf(host_id));
				enti = xenEntityTreeDao.queryTreeNode(enti);
				// 向entity_con中插入数据
				XenEntityConObj ent = new XenEntityConObj();
				ent.setClusterId(enti.getParentId());
				ent.setDataCenterId(dataCenterId);
				ent.setName(hostName);
				ent.setEntityId(String.valueOf(host_id));
				ent.setType(Integer.parseInt(TypeConstant.XEN_HOST));
				xenEntityTreeDao.insertConObj(ent);
				// 向Host表中插入数据
				TbCloud2HostInfoObj tb = new TbCloud2HostInfoObj();
				tb.setId(host_id);
				tb.setEq_id(host_id+"");
				tb.setEq_name(hostName);
				tb.setH_uuid(hostUuid);
				tb.setEq_type("2");//表示刀片
				tb.setHasvertual("3");//表示虚拟化类型
				tb.setCpu_fq(hostCpuSpeed+"");
				tb.setCpu_cl(hostCpuNum+"");
				tb.setCPU_DESC(hostCpuDesc);
				tb.setEq_ip(hostIp);
				tb.setMem(memSize+"");
				tb.setNIC_NUM(2);//暂时写死，需要从接口获取。----------------------------------------------
				hostInfoDao.insertByObj(tb);
			}
			/**
			 * 遍历虚拟机
			 */
			List<Vm> tempVms = host.getVms();
			for (Vm vm : tempVms) {
				String type = vm.getVmType();
				String vmName = vm.getVmName();
				String vm_uuid = vm.getVmUuid();
				long cpuNum=vm.getCpuNum();
				long memDynamicMax=vm.getMemDynamicMax();
				long virtualSrSize=vm.getVirtualSrSize();
				String vmIp=vm.getVmIps();
				String powerState=vm.getPowerState();
				String osName=vm.getOsName();
				long vifNum=vm.getVifNum();
				int parent_id = enti.getId();
				// 向entity_tree中插入数据
				int vm_id = vmHostDao.queryVhostIdSequence();
				XenEntityTreeObj eo = new XenEntityTreeObj();
				XenEntityConObj eno = new XenEntityConObj();
				eo.setEntityId(String.valueOf(vm_id));
				eo.setName(vmName);
				eo.setUuid(vm_uuid);
				if (hostUuid.equals(poolUuid)) {
					eo.setParentId(cluster_enty.getId());
					eno.setClusterId(cluster_enty.getId());
				} else {
					eo.setParentId(parent_id);
					eno.setClusterId(enti.getParentId());
				}
				// 向entity_con中插入数据
				eno.setDataCenterId(dataCenterId);
				eno.setName(vmName);
				eno.setEntityId(String.valueOf(vm_id));
				// 向vmHost表中插入数据
				VMHostObj tempVm = new VMHostObj();
				if (type.equals("IsVm")) {
					eo.setType(TypeConstant.XEN_VM);
					eno.setType(Integer.parseInt(TypeConstant.XEN_VM));
					tempVm.setVH_TYPE("3");
				} else {
					eno.setType(Integer.parseInt(TypeConstant.XEN_IMAGE));
					eo.setType(TypeConstant.XEN_IMAGE);
					tempVm.setVH_TYPE("6");
				}
				xenEntityTreeDao.insertTreeNode(eo);
				xenEntityTreeDao.insertConObj(eno);
				tempVm.setID(vm_id);
				tempVm.setVH_ID(String.valueOf(vm_id));
				tempVm.setVH_NAME(vmName);
				tempVm.setVH_UUID(vm_uuid);
				tempVm.setVH_CPU(cpuNum+"");
				tempVm.setVH_MEM(memDynamicMax/1024/1024+"");
				tempVm.setVH_STORAGE(virtualSrSize/1024+"");
				tempVm.setVH_SYSTEM(osName);
				tempVm.setEQ_ID(host_id+"");
				tempVm.setVH_IP(vmIp);
				if("HALTED".equalsIgnoreCase(powerState)){
					tempVm.setVH_STAT("0");
				}else if("RUNNING".equalsIgnoreCase(powerState)){
					tempVm.setVH_STAT("1");
				}else{
					//暂时写成这样
					tempVm.setVH_STAT("2");
				}
				tempVm.setVH_NETWORK(vifNum+"");
				vmHostDao.insertByVMhostObj(tempVm);
			}
			/**
			 * 遍历存储
			 */
			List<Sr> stores = host.getSrs();
			for (Sr sr : stores) {
				String srName = sr.getSrName();
				String srUuid = sr.getSrUuid();
				String srType = sr.getSrType();
				String srState = sr.getSrState();
				Boolean srShare = sr.isSrShared();
				long srSize = sr.getSrPhysicalSize();
				long useSize = sr.getSrPhysicalUtilisation();
				long freeSize = srSize - useSize;
				String srUrl = sr.getSrUrl();
				DecimalFormat format = new DecimalFormat("0");
				String usePer = "";
				if(useSize>0 && srSize>0){
					usePer = format.format(useSize*100/srSize);
				}else{
					usePer = "0";
				}
				if(srSize!=0){
					srSize = srSize/1024;
				}
				if(freeSize>0){
					freeSize = freeSize/1024;
				}
				String srHostName = sr.getHostName();
				List<String> srHostUuid = sr.getHostUuid();
				
				int parent_id = enti.getId();
				// 向entity_tree中插入数据
				int vm_id = vmHostDao.queryVhostIdSequence();
				int sr_id = xenStoreDao.querySRIdSequence();
				XenEntityTreeObj treeObj = new XenEntityTreeObj();
				XenEntityConObj conObj = new XenEntityConObj();
				treeObj.setEntityId(String.valueOf(sr_id));
				treeObj.setName(srName);
				treeObj.setUuid(srUuid);
				if (hostUuid.equals(poolUuid)) {
					treeObj.setParentId(cluster_enty.getId());
					conObj.setClusterId(cluster_enty.getId());
				} else {
					treeObj.setParentId(parent_id);
					conObj.setClusterId(enti.getParentId());
				}
				// 向entity_con中插入数据
				conObj.setDataCenterId(dataCenterId);
				conObj.setName(srName);
				conObj.setEntityId(String.valueOf(sr_id));
				treeObj.setType(TypeConstant.XEN_HOST_STORAGE);
				conObj.setType(Integer.parseInt(TypeConstant.XEN_HOST_STORAGE));
				xenEntityTreeDao.insertTreeNode(treeObj);
				xenEntityTreeDao.insertConObj(conObj);
				
				// 向datestore_info表中插入数据
				XenStoreObj storeObj = new XenStoreObj();
				storeObj.setId(sr_id);
				storeObj.setName(srName);
				storeObj.setStore_uuid(srUuid);
				storeObj.setSr_url(srUrl);
				storeObj.setState(srState);
				storeObj.setType(srType);
				storeObj.setFree_size(freeSize+"");
				storeObj.setUse_per(usePer);
				storeObj.setSr_size(srSize+"");
				storeObj.setShared(srShare.toString());
				storeObj.setDependent_host_uuid(host.getHostUuid());
				boolean isPool = false;
				if(!host.getHostUuid().equals(poolUuid)){
					xenStoreDao.insertDatastore(storeObj);
				}else{
					isPool = true;
				}
				if(srShare){
					if(sr.getHostUuid()!=null && sr.getHostUuid().size()>0){
						List<String> hostUuids = sr.getHostUuid();
						for (String s : hostUuids) {
							storeObj.setDependent_host_uuid(s);
							if(isPool){
								int ret = xenStoreDao.insertDatastore(storeObj);
								if(ret!=-1) isPool = false;
							}else{
								storeObj.setId(xenStoreDao.querySRIdSequence());
								xenStoreDao.insertDatastore(storeObj);
							}
						}
					}
				}
				
			}
		}

	}

	/**
	 * 分析HOST和VM之间的关系，并返回list,主机的uui等于pooluuid的时候为虚拟机挂在池的下面
	 * @param hosts
	 * @param vms
	 * @return
	 */
	public List<Host> analysisHostAndVm(List<JSONObject> hosts,List<JSONObject> vms,List<JSONObject> srs, String poolUuid) {
		List<Host> hosts1 = new ArrayList<Host>();
		Host poolHost = new Host();
		poolHost.setHostUuid(poolUuid);
		
		for (int i = 0; i < hosts.size(); i++) {
			JSONObject host = hosts.get(i);
			String hostUuid = (String) host.get("hostUuid");
			String hostName = (String) host.get("hostName");
			Host h = new Host();
			h.setHostUuid(hostUuid);
			h.setHostName(hostName);
			h.setHostCpuDesc((String) host.get("hostCpuDesc"));
			h.setHostCpuNum(host.get("hostCpuNum")==null?0:host.getInt("hostCpuNum"));
			h.setHostCpuSpeed(host.get("hostCpuSpeed")==null?0:host.getDouble("hostCpuSpeed"));
			h.setHostIp((String)host.get("hostIp"));
			h.setMemSize(host.get("memSize")==null?0:host.getLong("memSize"));
			for (int k = 0; k < vms.size(); k++) {
				JSONObject jso = vms.get(k);
				String residentUuid = (String) jso.get("residentOnUuid");// 当前驻留的主机
				/**
				 * 找出所有有驻留主机的虚拟机
				 */
				String vmType = (String) jso.get("vmType");
				String srHostUuid = (String) jso.get("srHostUuid");
				boolean isShare = (Boolean) jso.get("vdiShare");
				String vmName = (String) jso.get("vmName");
				String vmUuid = (String) jso.get("vmUuid");
				long cpuNum=jso.get("cpuNum")==null?0:jso.getLong("cpuNum");
				long memDynamicMax=jso.get("memDynamicMax")==null?0:jso.getLong("memDynamicMax");
				long virtualSrSize=jso.get("virtualSrSize")==null?0:jso.getLong("virtualSrSize");
				long vifNum=jso.get("vifNum")==null?0:jso.getLong("vifNum");
				String osName=(String)jso.get("osName");
				String powerState=(String)jso.get("powerState");
				String vmIps=(String)jso.get("vmIps");
				Vm v = new Vm();
				v.setResidentOnUuid(residentUuid);
				v.setVmType(vmType);
				v.setVifNum(vifNum);
				v.setVmName(vmName);
				v.setVmUuid(vmUuid);
				v.setShare(isShare);
				v.setCpuNum(cpuNum);
				v.setOsName(osName);
				v.setMemDynamicMax(memDynamicMax);
				v.setVirtualSrSize(virtualSrSize);
				v.setPowerState(powerState);
				v.setVmIps(vmIps);
				if (vmType.equals("IsTemplate")) {
					poolHost.getVms().add(v);
					vms.remove(k);
					k--;
				} else if (vmType.equals("IsVm")
						&& hostUuid.equals(residentUuid)) {
					h.getVms().add(v);
					vms.remove(k);
					k--;
				}
			}
			hosts1.add(h);
		}
		for (int i = 0; i < hosts1.size(); i++) {
			Host h = hosts1.get(i);
			String hostUuid = h.getHostUuid();
			for (int k = 0; k < vms.size(); k++) {
				JSONObject jso = vms.get(k);
				String affinityUuid = (String) jso.get("affinityUuid");// 优先启动的主机
				if (hostUuid.equals(affinityUuid)) {
					/**
					 * 找出所有有驻留主机的虚拟机
					 */
					String vmType = (String) jso.get("vmType");
					boolean isShare = jso.get("vdiShare")==null?false:jso.getBoolean("vdiShare");
					String vmName = (String) jso.get("vmName");
					String vmUuid = (String) jso.get("vmUuid");
					long cpuNum=jso.get("cpuNum")==null?0:jso.getLong("cpuNum");
					long memDynamicMax=jso.get("memDynamicMax")==null?0:jso.getLong("memDynamicMax");
					long virtualSrSize=jso.get("virtualSrSize")==null?0:jso.getLong("virtualSrSize");
					long vifNum=jso.get("vifNum")==null?0:jso.getLong("vifNum");
					String osName=(String)jso.get("osName");
					String powerState=(String)jso.get("powerState");
					String vmIps=(String)jso.get("vmIps");
					Vm v = new Vm();
					v.setResidentOnUuid(affinityUuid);
					v.setVmType(vmType);
					v.setVifNum(vifNum);
					v.setVmName(vmName);
					v.setVmUuid(vmUuid);
					v.setShare(isShare);
					v.setCpuNum(cpuNum);
					v.setOsName(osName);
					v.setMemDynamicMax(memDynamicMax);
					v.setVirtualSrSize(virtualSrSize);
					v.setPowerState(powerState);
					v.setVmIps(vmIps);
					h.getVms().add(v);
					vms.remove(k);
					k--;
				}
			}
		}
		for (JSONObject jso : vms) {
			String vmType = (String) jso.get("vmType");
			boolean isShare = jso.get("vdiShare")==null?false:jso.getBoolean("vdiShare");
			String vmName = (String) jso.get("vmName");
			String vmUuid = (String) jso.get("vmUuid");
			long cpuNum=jso.get("cpuNum")==null?0:jso.getLong("cpuNum");
			long memDynamicMax=jso.get("memDynamicMax")==null?0:jso.getLong("memDynamicMax");
			long virtualSrSize=jso.get("virtualSrSize")==null?0:jso.getLong("virtualSrSize");
			long vifNum=jso.get("vifNum")==null?0:jso.getLong("vifNum");
			String osName=(String)jso.get("osName");
			String powerState=(String)jso.get("powerState");
			String vmIps=(String)jso.get("vmIps");
			Vm v = new Vm();
			v.setVmType(vmType);
			v.setVmName(vmName);
			v.setVifNum(vifNum);
			v.setVmUuid(vmUuid);
			v.setShare(isShare);
			v.setCpuNum(cpuNum);
			v.setOsName(osName);
			v.setMemDynamicMax(memDynamicMax);
			v.setVirtualSrSize(virtualSrSize);
			v.setPowerState(powerState);
			v.setVmIps(vmIps);
			poolHost.getVms().add(v);
		}
		
		for (int a = 0; a < srs.size(); a++) {
			JSONObject jso = srs.get(a);
			boolean isShared=jso.get("srShared")==null?false:jso.getBoolean("srShared");
			Sr sr=new Sr();
			String hostUuids = (String) jso.get("hostUuids");
			String[] arrays=hostUuids.split(",");
			List<String> strs=new ArrayList<String>();
			for(String str:arrays){
				strs.add(str);
			}
			sr.setHostUuid(strs);
			sr.setSrDesc((String) jso.get("srDesc"));
			sr.setSrName((String) jso.get("srName"));
			sr.setSrPhysicalSize(jso.get("srSize")==null?0L:jso.getLong("srSize"));
			sr.setSrShared(isShared);
			sr.setSrPhysicalUtilisation(jso.get("srUtilisation")==null?0L:jso.getLong("srUtilisation"));
			sr.setSrState((String)jso.get("srState"));
			sr.setSrType(jso.get("srType")==null?"":jso.getString("srType").toUpperCase());
			sr.setSrUuid((String)jso.get("srUuid"));
			sr.setVirtualSrSize(jso.get("virtualSize")==null?0L:jso.getLong("virtualSize"));
			sr.setSrUrl(jso.get("srUrl")==null?"":jso.getString("srUrl"));
			if(isShared){
				poolHost.getSrs().add(sr);
			}else{
				String tempHostUuid=strs.get(0);
				for(int j=0;j<hosts1.size();j++){
					Host host=hosts1.get(j);
					if(host.getHostUuid().equals(tempHostUuid)){
						hosts1.get(j).getSrs().add(sr);
					}
				}
			}
		}
		hosts1.add(poolHost);
		return hosts1;
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
	public String saveCluster(String clusterName, String clusterType,
			String parent_id, String hostName, String username, String password) {
		String param = "/xen/pool/create/[masterAddress:" + hostName
				+ "].[masterUsername:" + username + "].[masterPassword:"
				+ password + "].[poolName:" + clusterName + "].[poolDesc:"
				+ clusterType + "]/";
		String result = InvokeUtil.invoke(param);
		JSONObject js = JSONObject.fromObject(result);
		if (js.get("responseCode").equals("1")) {
			XenClusterObj c = new XenClusterObj();
			c.setName(clusterName);
			c.setType(clusterType);
			c.setC_uuid((String) js.get("poolUuid"));
			c.setHastate("0");
			c.setDrsstate("0");
			int ret = xenClusterDao.insertByObj(c);
			XenEntityTreeObj e = new XenEntityTreeObj();
			if (ret != -1) {
				e.setEntityId(String.valueOf(ret));
			}
			e.setName(clusterName);
			e.setUuid((String) js.get("poolUuid"));
			e.setParentId(Integer.parseInt(parent_id));
			e.setType(TypeConstant.XEN_CLUSTER);
			int ret1 = xenEntityTreeDao.insertTreeNode(e);

			// 同步集群下的主机和虚拟机
			String host_param = "/xen/hostbypool/lookup/[poolUuid:"
					+ c.getC_uuid() + "]/";
			String host_result = InvokeUtil.invoke(host_param);
			JSONArray jo = JSONArray.fromObject(host_result);
			List<JSONObject> host = JSONArray.toList(jo, JSONObject.class);
			if (host.get(0).get("responseCode").equals("1")) {
				host.remove(0);
				XenEntityTreeObj tree = new XenEntityTreeObj();
				tree.setType(TypeConstant.XEN_CLUSTER);
				tree.setName(clusterName);
				tree = xenEntityTreeDao.queryTreeNode(tree);
				for (JSONObject json : host) {
					String host_name = json.get("hostName").toString();
					String host_uuid = json.get("hostUuid").toString();
					int host_id = hostInfoDao.getIdSequence();
					// 向entity_tree中插入数据
					XenEntityTreeObj en = new XenEntityTreeObj();
					en.setEntityId(String.valueOf(host_id));
					en.setName(host_name);
					en.setUuid(host_uuid);
					en.setParentId(tree.getId());
					en.setType(TypeConstant.XEN_HOST);
					ret = xenEntityTreeDao.insertTreeNode(en);
					// 查询主机的id
					XenEntityTreeObj enti = new XenEntityTreeObj();
					enti.setEntityId(String.valueOf(host_id));
					enti = xenEntityTreeDao.queryTreeNode(enti);
					// 向entity_con中插入数据
					XenEntityConObj ent = new XenEntityConObj();
					ent.setClusterId(enti.getParentId());
					ent.setDataCenterId(Integer.parseInt(parent_id));
					ent.setName(host_name);
					ent.setEntityId(String.valueOf(host_id));
					ent.setType(Integer.parseInt(TypeConstant.XEN_HOST));
					ret = xenEntityTreeDao.insertConObj(ent);
					// 向Host表中插入数据
					TbCloud2HostInfoObj tb = new TbCloud2HostInfoObj();
					tb.setId(host_id);
					tb.setEq_id("0");
					tb.setEq_name(host_name);
					tb.setH_uuid(host_uuid);
					tb.setEq_type("3");
					ret = hostInfoDao.insertByObj(tb);

					// 查询虚拟机信息
					String vm_param = "/xen/vms/lookup/[hostUuid:" + host_uuid
							+ "].[poolUuid:" + c.getC_uuid() + "]/";
					String vm_result = InvokeUtil.invoke(vm_param);
					JSONArray jm = JSONArray.fromObject(vm_result);
					List<JSONObject> jmsonO = JSONArray.toList(jm,
							JSONObject.class);
					if (jmsonO.get(0).get("responseCode").equals("1")) {
						jmsonO.remove(0);
						for (JSONObject jso : jmsonO) {
							String vmName = (String) jso.get("vmName");
							String vm_uuid = (String) jso.get("vmUuid");

							int vm_parentId = enti.getId();
							// 向entity_tree中插入数据
							int vm_id = vmHostDao.queryVhostIdSequence();
							XenEntityTreeObj eo = new XenEntityTreeObj();
							eo.setEntityId(String.valueOf(vm_id));
							eo.setName(vmName);
							eo.setUuid(vm_uuid);
							eo.setParentId(vm_parentId);
							eo.setType(TypeConstant.XEN_VM);
							ret1 = xenEntityTreeDao.insertTreeNode(eo);
							// 向entity_con中插入数据
							XenEntityConObj eno = new XenEntityConObj();
							eno.setClusterId(enti.getParentId());
							eno.setDataCenterId(Integer.parseInt(parent_id));
							eno.setName(vmName);
							eno.setEntityId(String.valueOf(vm_id));
							eno.setType(Integer.parseInt(TypeConstant.XEN_VM));
							ret = xenEntityTreeDao.insertConObj(eno);
							// 向vmHost表中插入数据
							VMHostObj vm = new VMHostObj();
							vm.setID(vm_id);
							vm.setVH_ID(String.valueOf(vm_id));
							vm.setVH_NAME(vmName);
							vm.setVH_UUID(vm_uuid);
							ret = vmHostDao.insertByVMhostObj(vm);
						}
					}

				}
			}

		}
		return null;
	}

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新集群信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 10, 2012 10:43:29 AM
	 */
	public String updateByObj(String clusterName, String clusterType,
			String entity_id) {
		String param = "";// 待修改
		String result = InvokeUtil.invoke(param);
		JSONObject js = JSONObject.fromObject(result);
		if (js.get("responseCode").equals("1")) {
			XenClusterObj c = new XenClusterObj();
			c.setName(clusterName);
			c.setId(Integer.parseInt(entity_id));
			int ret = xenClusterDao.updateByObj(c);
			XenEntityTreeObj e = new XenEntityTreeObj();
			e.setName(clusterName);
			e.setEntityId(entity_id);
			int ret1 = xenEntityTreeDao.updateTreeNode(e);
		}
		return null;
	}

	/**
	 * 检查集群是否存在
	 * 
	 * @param name
	 * @param username
	 * @param password
	 * @param DataCenterId
	 * @return
	 */
	public boolean checkConXenCluster(String name) {
		ConnectionInfo connObj = new ConnectionInfo();
		connObj.setADDRESS(name);
		ConnectionInfo reConnObj = connInfoDao.queryConnInfoByObj(connObj);
		if (reConnObj != null) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: conXenCluster
	 * @Description: 链接至xen集群
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 12, 2012 10:05:58 AM
	 */
	public String conXenCluster(String name, String username, String password,
			int DataCenterId) {
		String result = null;
		password = EncryptUtil.encode(password);
		String param = "/xen/conn/init/[masterAddress:" + name
				+ "].[masterUsername:" + username + "].[masterPassword:"
				+ password + "]/";
		result = InvokeUtil.invoke(param);
		JSONObject js = JSONObject.fromObject(result);
		if (js.get("responseCode").equals("1")) {
			String poolUuid = (String) js.get("poolUuid");
			String poolName = (String) js.get("poolName");
			listAllHostsByCluster(DataCenterId, poolUuid, poolName, name);
		}
		return result;
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
	 * @createtime Oct 12, 2012 2:16:09 PM
	 */
	public String saveHost(String parent_id, String username, String password,
			String ip, String pool_uuid) {
		String param = "/xen/hostbypool/join/[hostIp:" + ip
				+ "].[username:" + username + "].[password:" + password
				+ "].[poolUuid:" + pool_uuid + "]/";
		String result = InvokeUtil.invoke(param);
		JSONObject js = JSONObject.fromObject(result);
		if (js.get("responseCode").equals("1")) {
			// 主机名称
			String hostName = js.get("hostName").toString();
			String hostUuid = js.get("hostUuid").toString();
			int host_id = hostInfoDao.getIdSequence();
			// 向entity_tree插入数据
			XenEntityTreeObj e = new XenEntityTreeObj();
			e.setName(hostName);
			e.setUuid(hostUuid);
			e.setParentId(Integer.parseInt(parent_id));
			e.setEntityId(String.valueOf(host_id));
			e.setType(TypeConstant.XEN_HOST);
			int ret = xenEntityTreeDao.insertTreeNode(e);
			XenEntityTreeObj ent = new XenEntityTreeObj();
			ent.setId(Integer.parseInt(parent_id));
			ent = xenEntityTreeDao.queryTreeNode(ent);
			// 向entity_con插入数据
			XenEntityConObj en = new XenEntityConObj();
			en.setClusterId(Integer.parseInt(parent_id));
			en.setDataCenterId(ent.getParentId());
			en.setEntityId(String.valueOf(host_id));
			en.setName(username);
			en.setType(Integer.parseInt(TypeConstant.XEN_HOST));
			ret = xenEntityTreeDao.insertConObj(en);
			TbCloud2HostInfoObj t = new TbCloud2HostInfoObj();
			t.setId(host_id);
			t.setEq_id(String.valueOf(host_id));
			t.setEq_name(hostName);
			t.setH_uuid(hostUuid);
			t.setEq_type("3");
			t.setEq_ip(ip);
			ret = hostInfoDao.insertByObj(t);
			// 同步主机下所有虚拟机  
			//此接口已经更改，只返回虚拟机，并没有返回主机下的所有模版，需要修改。-----------------------------------------------------
			String vm_param = "/xen/vms/lookup/[hostUuid:" + hostUuid
					+ "].[poolUuid:" + pool_uuid + "]/";
			String vm_result = InvokeUtil.invoke(vm_param);
			JSONArray jm = JSONArray.fromObject(vm_result);
			List<JSONObject> jmsonO = JSONArray.toList(jm, JSONObject.class);
			if (jmsonO.get(0).get("responseCode").equals("1")) {
				jmsonO.remove(0);
				for (JSONObject jso : jmsonO) {
					String vmName = (String) jso.get("vmName");
					String vm_uuid = (String) jso.get("vmUuid");

					// 向entity_tree中插入数据
					int vm_id = vmHostDao.queryVhostIdSequence();
					XenEntityTreeObj eo = new XenEntityTreeObj();
					eo.setEntityId(String.valueOf(vm_id));
					eo.setName(vmName);
					eo.setUuid(vm_uuid);
					eo.setParentId(host_id);
					eo.setType(TypeConstant.XEN_VM);
					int ret1 = xenEntityTreeDao.insertTreeNode(eo);
					// 向entity_con中插入数据
					XenEntityConObj eno = new XenEntityConObj();
					eno.setClusterId(Integer.parseInt(parent_id));
					eno.setDataCenterId(ent.getParentId());
					eno.setName(vmName);
					eno.setEntityId(String.valueOf(vm_id));
					eno.setType(Integer.parseInt(TypeConstant.XEN_VM));
					ret = xenEntityTreeDao.insertConObj(eno);
					// 向vmHost表中插入数据
					VMHostObj vm = new VMHostObj();
					vm.setID(vm_id);
					vm.setVH_ID(String.valueOf(vm_id));
					vm.setVH_NAME(vmName);
					vm.setVH_UUID(vm_uuid);
					vm.setVH_TYPE("3");
					ret = vmHostDao.insertByVMhostObj(vm);
				}
			}
		}
		return result;
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
	public String delHost(String ID, String entity_id, String pool_uuid,
			String host_uuid) {
		TbCloud2HostInfoObj t = new TbCloud2HostInfoObj();
		t.setId(Integer.parseInt(entity_id));
		t = hostInfoDao.queryByObj(t);
		String result = null;
		// 判断当前主机是否为当前集群的主控机
		String master_param = "/xen/hostbypool/lookup/[poolUuid:" + pool_uuid
				+ "]/";
		String master_result = InvokeUtil.invoke(master_param);
		JSONArray masterJson = JSONArray.fromObject(master_result);
		List<JSONObject> masterJsonO = JSONArray.toList(masterJson,
				JSONObject.class);
		if (masterJsonO.get(0).get("responseCode").equals("1")) {
			masterJsonO.remove(0);
			for (JSONObject jso : masterJsonO) {
				String hostUuid = (String) jso.get("hostUuid");
				boolean isMaster = (Boolean) jso.get("isMasterHost");
				if (hostUuid.equals(host_uuid) && !isMaster == true) {// 判断是否为主控机
					String param = "/xen/hostfrompool/eject/[hostUuid:"
							+ host_uuid + "].[poolUuid:" + pool_uuid + "]/";
					result = InvokeUtil.invoke(param);
					JSONObject js = JSONObject.fromObject(result);
					if (js.get("responseCode").equals("1")) {
						XenEntityTreeObj e = new XenEntityTreeObj();
						e.setId(Integer.parseInt(ID));
						int ret = xenEntityTreeDao.delTreeNode(e);
						XenEntityConObj en = new XenEntityConObj();
						en.setEntityId(entity_id);
						en.setType(Integer.parseInt(TypeConstant.XEN_HOST));
						ret = xenEntityTreeDao.deleteConObj(en);
						ret = hostInfoDao.deleteByObj(t);
					}
				} else if (hostUuid.equals(host_uuid) && isMaster == true) {// 是主控机,并且该池中只包含该主控机
					String mas_param = "/xen/pool/delete/[poolUuid:"
							+ pool_uuid + "]/";
					String mas_result = InvokeUtil.invoke(mas_param);
					JSONObject js = JSONObject.fromObject(mas_result);
					if (js.get("responseCode").equals("1")) {
						XenEntityTreeObj e = new XenEntityTreeObj();
						e.setId(Integer.parseInt(ID));
						int ret = xenEntityTreeDao.delTreeNode(e);
						XenEntityConObj en = new XenEntityConObj();
						en.setEntityId(entity_id);
						en.setType(Integer.parseInt(TypeConstant.XEN_HOST));
						ret = xenEntityTreeDao.deleteConObj(en);
						ret = hostInfoDao.deleteByObj(t);
					}

				}

			}

		}
		return result;
	}

	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询一条链接信息
	 * @param
	 * @return ConnectionInfo
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 17, 2012 11:08:08 AM
	 */
	public ConnectionInfo queryByObj(ConnectionInfo obj) {
		ConnectionInfo c = connInfoDao.queryConnInfoByObj(obj);
		return c;
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
	public String connectCluster(String ip, String username, String password,String poolUuid) {
		password = EncryptUtil.encode(password);
		//验证连接是否可用
		String connParam = "/xen/conn/verification/[poolUuid:"+poolUuid+"]/";
		String connResult = InvokeUtil.invoke(connParam);
		JSONObject connJs = JSONObject.fromObject(connResult);
		String result = "";
		if(connJs.get("responseCode").equals("1")){
			result = "{\"result\":\"1\"}";
		}else{
			String initParam = "/xen/conn/init/[masterAddress:" + ip
			+ "].[masterUsername:" + username + "].[masterPassword:"
			+ password + "]/";
			String initResult = InvokeUtil.invoke(initParam);
			JSONObject initJs = JSONObject.fromObject(initResult);
			if (initJs.get("responseCode").equals("1")) {
				String cluster_uuid = (String) initJs.get("poolUuid");
				XenClusterObj c = new XenClusterObj();
				c.setName(ip);
				c.setC_uuid(cluster_uuid);
				int ret = xenClusterDao.updateForConnection(c);
				result = "{\"result\":\"2\"}";
			}else{
				result = "{\"result\":\"-1\"}";
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: migrate
	 * @Description: 迁移虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 22, 2012 4:32:10 PM
	 */
	public String migrate(int host_id) {
		return null;
	}

	/**
	 * 
	 * @Title: getSRList
	 * @Description: 获取已存在的SR列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 25, 2012 4:32:53 PM
	 */
	public List getSRList(String pool_uuid, String path) {
		String param = "/xen/nfs/serverpath/get/[poolUuid:" + pool_uuid
				+ "].[serverPath:" + path + "]/";
		String result = InvokeUtil.invoke(param);
		JSONArray jso = JSONArray.fromObject(result);
		List<JSONObject> JsonO = JSONArray.toList(jso, JSONObject.class);
		List<XenStoreObj> list = new ArrayList<XenStoreObj>();
		if (JsonO.get(0).get("responseCode").equals("1")) {
			JsonO.remove(0);
			for (JSONObject js : JsonO) {
				XenStoreObj storeObj = new XenStoreObj();
				String srState = js.getString("srState");
				if(srState.equals("detach")){
					//已分离的
					storeObj.setName(js.getString("srName")+"(已分离)");
					storeObj.setStore_uuid(js.getString("srUuid"));
					list.add(storeObj);
				}else{
					//已忘记的
					storeObj.setName(js.getString("srUuid"));
					storeObj.setStore_uuid(js.getString("srUuid"));
					list.add(storeObj);
				}
//				String nfs_uuid = (String) js.get("srUuid");
//				XenStoreObj d = new XenStoreObj();
//				d.setStore_uuid(nfs_uuid);
//				List lst = xenStoreDao.queryForListByObj(d);
//				if (lst != null && lst.size() != 0) {
//					XenStoreObj da = (XenStoreObj) lst.get(0);
//					list.add(da);
//				}else{
//					list.add(d);
//				}

			}
		}
		return list;
	}

	/**
	 * 
	 * @Title: createSR
	 * @Description: 创建SR
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 26, 2012 2:22:09 PM
	 */
	public String createSR(String pool_uuid, String host_uuid, String path,
			String name, String desc, String host_id, String storageId,
			String createStyle, boolean share, String store_uuid,
			String bind_uuid, String iso_type, String username, String password) {
		String param = "";
		String change_path = path.replace('/', '~');
		String last_path = change_path.replace(':', '!');
		last_path = last_path.replace('\\', '~');
		if (storageId.equals("1") && "0".equals(createStyle)) {// 新建nfs
			param = "/xen/nfs/vhd/create/[poolUuid:" + pool_uuid
					+ "].[hostUuid:" + host_uuid + "].[serverPath:" + last_path
					+ "].[srName:" + name + "].[shared:" + share + "]/";
		} else if (storageId.equals("1") && "1".equals(createStyle)) {// 挂载nfs
			if (share == true) {
				param = "/xen/nfs/mount/[poolUuid:" + pool_uuid
						+ "].[serverPath:" + last_path + "].[srName:" + name
						+ "].[srUuid:" + store_uuid + "].[shared:" + share
						+ "]/";
			} else {
				param = "/xen/nfs/mount/[poolUuid:" + pool_uuid
						+ "].[hostUuid:" + bind_uuid + "].[serverPath:"
						+ last_path + "].[srName:" + name + "].[srUuid:"
						+ store_uuid + "].[shared:" + share + "]/";
			}
		} else if (iso_type.equals("6")) {// 挂载nfs iso共享存储
			param = "/xen/iso/create/[poolUuid:" + pool_uuid + "].[serverPath:"
					+ last_path + "].[srName:" + name + "].[shared:" + share
					+ "].[hostUuid:" + host_uuid + "]/";
		} else {// 挂载windows iso
			param = "/xen/iso/create/[poolUuid:" + pool_uuid + "].[serverPath:"
					+ last_path + "].[srName:" + name + "].[shared:" + share
					+ "].[hostUuid:" + host_uuid + "].[username:" + username
					+ "].[password:" + password + "]/";
		}
		String result = InvokeUtil.invoke(param);
		JSONObject js = JSONObject.fromObject(result);
		if (js.get("responseCode").equals("1")) {
			if (storageId.equals("1") && "0".equals(createStyle)
					|| storageId.equals("2")) {// 创建新的nfs
				int sr_id = xenStoreDao.querySRIdSequence();
				XenStoreObj d = new XenStoreObj();
				d.setId(sr_id);
				d.setStore_uuid(js.getString("srUuid"));
				d.setDependent_host_uuid(host_uuid);
				d.setName(name);
				d.setSr_url(path);
				d.setState("connection");
				d.setShared(String.valueOf(share));
				long allsize = js.get("srSize")==null?0l:js.getLong("srSize");
				long useSize = js.get("srUtilisation")==null?0l:js.getLong("srUtilisation");
				long freeSize = 0l;
				if(allsize> 0 && useSize >= 0){
					freeSize = allsize - useSize;
				}
				DecimalFormat format = new DecimalFormat("0");
				String usePer = "";
				if(useSize>0 && allsize>0){
					usePer = format.format(useSize*100/allsize);
				}else{
					usePer = "0";
				}
				d.setFree_size(freeSize/1024+"");
				d.setSr_size(allsize/1024+"");
				d.setUse_per(usePer);
				d.setType(js.getString("srType").toUpperCase());
				int ret = xenStoreDao.insertDatastore(d);
				//共享存储插入和同一集群下其他主机的关系
				XenEntityTreeObj xenTreeObj = new XenEntityTreeObj();
				XenEntityTreeObj xenClObj = new XenEntityTreeObj();
				if(share){
					xenTreeObj.setUuid(host_uuid);
					xenTreeObj = xenEntityTreeDao.queryTreeNode(xenTreeObj);
					xenClObj.setId(xenTreeObj.getParentId());
					xenClObj = xenEntityTreeDao.queryTreeNode(xenClObj);
					XenEntityTreeObj xenClSonObj = new XenEntityTreeObj();
					xenClSonObj.setParentId(xenTreeObj.getParentId());
					List clLst = xenEntityTreeDao.queryForTree(xenClSonObj);
					for (Object obj : clLst) {
						XenEntityTreeObj treeObj = (XenEntityTreeObj)obj;
						if(treeObj.getType().equals(TypeConstant.XEN_HOST) && !treeObj.getUuid().equals(host_uuid)){
							d.setDependent_host_uuid(treeObj.getUuid());
							d.setId(xenStoreDao.querySRIdSequence());
							xenStoreDao.insertDatastore(d);
						}
					}
				}
				
				// 向entity_tree中插入数据
				XenEntityTreeObj treeObj = new XenEntityTreeObj();
				XenEntityConObj conObj = new XenEntityConObj();
				treeObj.setEntityId(String.valueOf(sr_id));
				treeObj.setName(name);
				treeObj.setUuid(js.getString("srUuid"));
				treeObj.setParentId(xenTreeObj.getParentId());
				conObj.setClusterId(xenTreeObj.getParentId());
				// 向entity_con中插入数据
				conObj.setDataCenterId(xenClObj.getParentId());
				conObj.setName(name);
				conObj.setEntityId(String.valueOf(sr_id));
				treeObj.setType(TypeConstant.XEN_HOST_STORAGE);
				conObj.setType(Integer.parseInt(TypeConstant.XEN_HOST_STORAGE));
				xenEntityTreeDao.insertTreeNode(treeObj);
				xenEntityTreeDao.insertConObj(conObj);
			} else {// 重新挂载nfs
				XenStoreObj d = new XenStoreObj();
				d.setState("connection");
				d.setStore_uuid(store_uuid);
				d.setName(name);
				int ret = xenStoreDao.updateByObj(d);
			}
		}
		return result;
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
	public String operateStore(String pool_uuid, String host_uuid,
			String sr_uuid, String oper, String name) {
		String param = null;
		if(pool_uuid==null || "".equals(pool_uuid)){
			pool_uuid = host_uuid;
			host_uuid = "";
		}
		if (oper.equals("1")) {// 分离存储
			param = "/xen/sr/detach/[poolUuid:" + pool_uuid + "].[hostUuid:"
					+ host_uuid + "].[srUuid:" + sr_uuid + "]/";
		} else if (oper.equals("2")) {// 忘记存储
			param = "/xen/sr/forget/[poolUuid:" + pool_uuid + "].[hostUuid:"
					+ host_uuid + "].[srUuid:" + sr_uuid + "]/";
		} else {// 销毁存储
			param = "/xen/sr/destroy/[poolUuid:" + pool_uuid + "].[srUuid:"
					+ sr_uuid + "]/";
		}
		String result = InvokeUtil.invoke(param);
		JSONObject js = JSONObject.fromObject(result);
		if (js.get("responseCode").equals("1")) {// (1.链接2.分离3.忘记)
			XenStoreObj obj = new XenStoreObj();
			obj.setStore_uuid(sr_uuid);
			List lst = xenStoreDao.queryForListByObj(obj);
			if (lst!=null && lst.size() > 0) {
				if (oper.equals("1")) {
					obj.setState("detach");
					xenStoreDao.updateByObj(obj);
				}else if(oper.equals("2")){
					xenStoreDao.deleteByObj(obj);
					XenEntityTreeObj entityObj = new XenEntityTreeObj();
					entityObj.setUuid(sr_uuid);
					entityObj = xenEntityTreeDao.queryTreeNode(entityObj);
					if(entityObj!=null){
						XenEntityConObj conObj = new XenEntityConObj();
						conObj.setEntityId(entityObj.getEntityId());
						xenEntityTreeDao.deleteConObj(conObj);
						xenEntityTreeDao.delTreeNode(entityObj);
					}
				}else if(oper.equals("3")){
					xenStoreDao.deleteByObj(obj);
					XenEntityTreeObj entityObj = new XenEntityTreeObj();
					entityObj.setUuid(sr_uuid);
					entityObj = xenEntityTreeDao.queryTreeNode(entityObj);
					if(entityObj!=null){
						XenEntityConObj conObj = new XenEntityConObj();
						conObj.setEntityId(entityObj.getEntityId());
						xenEntityTreeDao.deleteConObj(conObj);
						xenEntityTreeDao.delTreeNode(entityObj);
					}
				}
			} 

		}
		// String result =
		// "{\"responseCode\":\"1\",\"srUtilisation\":\"139871649792\",\"srUuid\":\"05381c4a-4374-d448-2463-a1affc11daf4\",\"srName\":\"NFS
		// 虚拟磁盘存储 (2)\",\"srSize\":\"431213248512\",\"srDesc\":\"NFS SR
		// [172.21.3.58:/var/test-nfs/xen]\",\"srType\":\"nfs\",\"srShared\":\"true\",\"virtualSize\":\"0\"}";
		return result;
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
	public String delTem(String pool_uuid, String host_uuid, String vm_uuid,
			String id, String entity_id) {
		String param = "/xen/vm/destory/[vmUuid:" + vm_uuid + "].[poolUuid:"
				+ pool_uuid + "].[hostUuid:" + host_uuid + "]/";
		String result = InvokeUtil.invoke(param);
		JSONObject js = JSONObject.fromObject(result);
		if (js.get("responseCode").equals("1")) {
			//删除tb_xen_entity_con表
			XenEntityConObj con = new XenEntityConObj();
			XenEntityTreeObj queryNode = new XenEntityTreeObj();
			queryNode.setUuid(vm_uuid);
			queryNode = xenEntityTreeDao.queryTreeNode(queryNode);
			con.setEntityId(queryNode.getEntityId());
			xenEntityTreeDao.deleteConObj(con);
			
			XenEntityTreeObj eo = new XenEntityTreeObj();
			eo.setId(Integer.parseInt(id));
			int ret = xenEntityTreeDao.delTreeNode(eo);
			VMHostObj v = new VMHostObj();
			v.setID(Integer.parseInt(entity_id));
			ret = vmHostDao.deleteByObj(v);
		}
		return result;
	}

	/**
	 * 
	 * @Title: listAbility
	 * @Description: 虚拟机性能信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Nov 15, 2012 2:04:37 PM
	 */
	public String listAbility(String pool_uuid, String host_uuid,
			String[] vmUuids) {

		String vmUuid = "";
		for (int i = 0; i < vmUuids.length; i++) {
			if (i < vmUuids.length - 1) {
				vmUuid += vmUuids[i] + ",";
			} else if (i == vmUuids.length - 1) {
				vmUuid += vmUuids[i];
			}

		}
		String param = "/xen/vm/monitoring/performance/[poolUuid:" + pool_uuid
				+ "].[hostUuid:" + host_uuid + "].[vmUuids:" + vmUuid + "]/";
		String result = InvokeUtil.invoke(param);
		return result;
	}

	/**
	 * 
	 * @Title: queryVMCount
	 * @Description: 查询集群下的所有虚拟机
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-1-22 下午02:42:10
	 */
	public List<XenEntityTreeObj> queryXenVMCount(XenEntityTreeObj obj) {
		List<XenEntityTreeObj> list = new ArrayList<XenEntityTreeObj>();
		XenEntityTreeObj poolNode = xenEntityTreeDao.queryTreeNode(obj);
		XenEntityTreeObj queryPoolSonNode = new XenEntityTreeObj();
		queryPoolSonNode.setParentId(poolNode.getId());
		List<XenEntityTreeObj> poolSonNodeList = xenEntityTreeDao.queryForTree(queryPoolSonNode);
		for(XenEntityTreeObj xenObj : poolSonNodeList){
			if(xenObj.getType().equals("26")){
				XenEntityTreeObj queryHostSonNode = new XenEntityTreeObj();
				queryHostSonNode.setParentId(xenObj.getId());
				List<XenEntityTreeObj> HostSonNodeList = xenEntityTreeDao.queryForTree(queryHostSonNode);
				list.addAll(HostSonNodeList);
			}else if(xenObj.getType().equals("27")){
				list.add(xenObj);
			}else if(xenObj.getType().equals("28")){
				list.add(xenObj);
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @Title: getOtherHostPifUuids
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 15, 2013 11:39:06 AM
	 */
	public String getOtherHostPifUuids(String poolUuid,String hostUuid,String nicName){
		StringBuffer pifUuids = new StringBuffer();
		XenEntityTreeObj poolObj = new XenEntityTreeObj();
		poolObj.setUuid(poolUuid);
		poolObj = xenEntityTreeDao.queryTreeNode(poolObj);
		if(poolObj!=null){
			XenEntityTreeObj hostObj = new XenEntityTreeObj();
			hostObj.setParentId(poolObj.getId());
			List hostList = xenEntityTreeDao.queryForTree(hostObj);
			for (Object obj : hostList) {
				hostObj = (XenEntityTreeObj)obj;
				if(!hostObj.getUuid().equals(hostUuid)){
					String param = "/xen/nics/get/[hostUuid:" + hostObj.getUuid() + "].[poolUuid:"
							+ poolUuid + "]/";
					String result = InvokeUtil.invoke(param);
					JSONArray jo = JSONArray.fromObject(result);
					List<JSONObject> nics = JSONArray.toList(jo, JSONObject.class);
					if (nics.get(0).get("responseCode").equals("1")) {
						nics.remove(0);
						for (JSONObject json : nics) {
							String uuid = json.get("nicUuid")==null?"":json.getString("nicUuid");
							String name = json.get("nicName")==null?"":json.getString("nicName");
							if(name.equals(nicName) && !uuid.equals("")){
								pifUuids.append(",").append(uuid);
							}
						}
					}	
				}
			}
		}
		return pifUuids.toString();
	}

	@Override
	public String loadToolForDVD(String poolUuid, String vmUuid) {
		String param = "/xen/iso/load/[vmUuid:" + vmUuid + "].[poolUuid:"
				+ poolUuid + "]/";
		String result = InvokeUtil.invoke(param);
		return result;
	}

	@Override
	public String loadVdiForDVD(String vdiUuid, String poolUuid, String vmUuid) {
		String param = "/xen/iso/load/[vmUuid:" + vmUuid + "].[poolUuid:"
				+ poolUuid + "].[vdiUuid:" + vdiUuid + "]/";
		String result = InvokeUtil.invoke(param);
		return result;
	}

	@Override
	public String removeVdiForDVD(String poolUuid, String vmUuid) {
		String param = "/xen/iso/eject/[poolUuid:" + poolUuid + "].[vmUuid:"
				+ vmUuid + "]/";
		String result = InvokeUtil.invoke(param);
		return result;
	}

	public String deleteVdiVm(String poolUuid, String vdiUuid) {
		String param = "/xen/vdi/destroy/[poolUuid:" + poolUuid + "].[vdiUuid:"
				+ vdiUuid + "]/";
		String result = InvokeUtil.invoke(param);
		return result;
	}

	public String editVdiForVm(String poolUuid, String vdiUuid, String vdiSize) {
		String param = "/xen/vdi/modify/[poolUuid:" + poolUuid + "].[vdiUuid:"
				+ vdiUuid + "].[vdiSize:" + vdiSize + "]/";
		String result = InvokeUtil.invoke(param);
		return result;
	}

	public static void main(String[] args) {
		/*
		 * XenManServiceImpl x = new XenManServiceImpl(); //
		 * x.listAllHostByCluster(); // x.listAllVMByHost(466); String s =
		 * "测试的手势"; Reader r = new StringReader(s); char[] buffer = new
		 * char[256]; try { int dataLen = r.read(buffer); } catch (Exception e) { }
		 */

	}
}
