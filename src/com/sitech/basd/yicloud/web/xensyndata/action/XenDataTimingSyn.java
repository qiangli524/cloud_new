package com.sitech.basd.yicloud.web.xensyndata.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.dao.xenconn.ConnInfoDao;
import com.sitech.basd.yicloud.domain.xenconn.ConnectionInfo;
import com.sitech.basd.yicloud.domain.xentree.XenEntityConObj;
import com.sitech.basd.yicloud.domain.xentree.XenEntityTreeObj;
import com.sitech.basd.yicloud.service.xen.XenManService;
import com.sitech.basd.yicloud.service.xentree.XenEntityTreeService;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.TypeConstant;

public class XenDataTimingSyn extends CRUDBaseAction{
	private ConnInfoDao connInfoDao;
	private XenManService xenManService;
	private VMHostService vmHostService;
	private XenEntityTreeService xenEntityTreeService;
	private HostInfoService hostInfoService;
	
	public HostInfoService getHostInfoService() {
		return hostInfoService;
	}

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public void setConnInfoDao(ConnInfoDao connInfoDao) {
		this.connInfoDao = connInfoDao;
	}

	public ConnInfoDao getConnInfoDao() {
		return connInfoDao;
	}

	public XenManService getXenManService() {
		return xenManService;
	}

	public void setXenManService(XenManService xenManService) {
		this.xenManService = xenManService;
	}

	public VMHostService getVmHostService() {
		return vmHostService;
	}

	public void setVmHostService(VMHostService vmHostService) {
		this.vmHostService = vmHostService;
	}

	public XenEntityTreeService getXenEntityTreeService() {
		return xenEntityTreeService;
	}

	public void setXenEntityTreeService(XenEntityTreeService xenEntityTreeService) {
		this.xenEntityTreeService = xenEntityTreeService;
	}

	/**
	 * 
	 * @Title: xenTimingSys
	 * @Description: xen数据定时比对
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-1-22 下午01:34:01
	 */
	public void xenTimingSys(){
		try{
			ConnectionInfo connObj = new ConnectionInfo();
			List lst = connInfoDao.queryAllConn(connObj);
			if(lst.size()>0){
				for(Object o : lst){
					connObj = (ConnectionInfo)o;
					XenEntityTreeObj xenEntityObj = new XenEntityTreeObj();
					xenEntityObj.setUuid(connObj.getPOOLUUID());
					List<XenEntityTreeObj> list = xenManService.queryXenVMCount(xenEntityObj);
					//比较虚拟机的个数
//					String xenCountParam = "/xen/vms/count/synchronization/[poolUuid:" + connObj.getPOOLUUID()
//					+ "]/";
//					String result = InvokeUtil.invoke(xenCountParam);
//					JSONObject js = JSONObject.fromObject(result);
					int reCount = 0;
//					int differ = 0;
//					int count = list.size();
//					if(js.getString("responseCode").equals("1")){
//						reCount = js.getInt("sysStateCount");
//						differ = Math.abs(reCount-count);
//					}else{
//						//不做比较
//						differ=6;
//					}
					//当前阀值为5，大于5个不做比较
//					if(differ>0&&differ <= 100){
						String uuids = "";
						for(XenEntityTreeObj obj : list){
							uuids += obj.getUuid()+",";
						}
						uuids = uuids.substring(0, uuids.length()-1);
						String xenOperationParam = "/xen/vms/modify/synchronization/[poolUuid:" + connObj.getPOOLUUID()
						+ "].[vmUuids:" + uuids + "]/";
						String operationResult = InvokeUtil.invoke(xenOperationParam);
						JSONArray jo = JSONArray.fromObject(operationResult);
						List<JSONObject> hostSList = JSONArray.toList(jo, JSONObject.class);
						if (hostSList.get(0).get("responseCode").equals("1")) {
							hostSList.remove(0);
							for (JSONObject json : hostSList) {
								if (json.get("operType").equals("add")){
									String vmType = (String)json.get("vmType");
									if(vmType.equals("IsVm")){
										//增加虚拟机
										addVirtualMachine(connObj, json);
									}else if(vmType.equals("IsTemplate")){
										//增加模板
										Boolean srShared = Boolean.parseBoolean((String)json.get("srShared"));
										String parentUUID = "";
										String parent = "";
										if(srShared){
											parentUUID = connObj.getPOOLUUID();
											parent = "cluster";
										}else{
											parentUUID = (String)json.get("hostUuid");
											parent = "host";
										}
										addTemplet(json, parent, parentUUID);
									}
								}else if(json.get("operType").equals("remove")){
									//删除虚拟机
									//删除tb_xen_entity_con表
									XenEntityConObj con = new XenEntityConObj();
									XenEntityTreeObj queryNode = new XenEntityTreeObj();
									queryNode.setUuid((String)json.get("vmUuid"));
									queryNode = xenEntityTreeService.queryTreeNode(queryNode);
									con.setEntityId(queryNode.getEntityId());
									xenEntityTreeService.deleteConObj(con);
									//删除tb_cloud2_vmHost_info表
									VMHostObj vmobj = new VMHostObj();
									vmobj.setVH_UUID((String)json.get("vmUuid"));
									vmHostService.deleteByObj(vmobj);
									//删除tb_xen_entity_tree表
									XenEntityTreeObj vm_tree = new XenEntityTreeObj();
									vm_tree.setUuid((String)json.get("vmUuid"));
									xenEntityTreeService.delTreeNode(vm_tree);
								}
							}
						}
//					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void xenTimingSysVmState(){
		try{
			ConnectionInfo connObj = new ConnectionInfo();
			List lst = connInfoDao.queryAllConn(connObj);
			if(lst.size()>0){
				for(Object o : lst){
					connObj = (ConnectionInfo)o;
					XenEntityTreeObj xenEntityObj = new XenEntityTreeObj();
					xenEntityObj.setUuid(connObj.getPOOLUUID());
					List<XenEntityTreeObj> list = xenManService.queryXenVMCount(xenEntityObj);
					String uuids = "";
					for(XenEntityTreeObj obj : list){
						uuids += obj.getUuid()+",";
					}
					uuids = uuids.substring(0, uuids.length()-1);
					String xenOperationParam = "/xen/vms/uuids/lookup/[poolUuid:" + connObj.getPOOLUUID()
					+ "].[vmUuids:" + uuids + "]/";
					String operationResult = InvokeUtil.invoke(xenOperationParam);
					JSONArray jo = JSONArray.fromObject(operationResult);
					List<JSONObject> hostSList = JSONArray.toList(jo, JSONObject.class);
					//--------------------------------------------------------------------------------需要修改，先查询在比较，然后根据不同再更新，每次都更新会给数据库带来压力
					if (hostSList.get(0).get("responseCode").equals("1")) {
						hostSList.remove(0);
						for (JSONObject json : hostSList) {
								String vmUuid = json.getString("vmUuid");
								String powerState = json.getString("powerState");
								int ps=0;
								if("HALTED".equalsIgnoreCase(powerState)){
									ps=0;
								}else if("RUNNING".equalsIgnoreCase(powerState)){
									ps=1;
								}else if("SUSPENDED".equalsIgnoreCase(powerState)){
									ps=2;
								}else{
									ps=0;
								}
								VMHostObj vmobj = new VMHostObj();
								vmobj.setVH_UUID((String)json.get("vmUuid"));
								vmobj.setVH_STAT(ps+"");
								vmHostService.updateVmStateByObj(vmobj);
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Title: addVirtualMachine
	 * @Description: 数据定时同步时，添加虚拟机
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jan 29, 2013 10:03:34 AM
	 */
	public void addVirtualMachine(ConnectionInfo connObj,JSONObject json){
		String checkUuid = (String)json.get("vmUuid");
		XenEntityTreeObj checkNode = new XenEntityTreeObj();
		checkNode.setUuid(checkUuid);
		checkNode = xenEntityTreeService.queryTreeNode(checkNode);
		if(checkNode==null){
			//增加虚拟机
			String parentUUID = "";
			String parent = "";
			if(json.getString("residentOnUuid")==null || json.getString("residentOnUuid").equals("")){
				if(json.getString("affinityUuid")==null || json.getString("affinityUuid").equals("")){
					//虚拟机在集群上
					parentUUID = connObj.getPOOLUUID();
					parent = "cluster";
				}else{
					parentUUID = json.getString("affinityUuid");
					parent = "host";
				}
			}else{
				parentUUID = json.getString("residentOnUuid");
				parent = "host";
			}
			
			int vm_id = vmHostService.queryVhostIdSequence();
			//插入tb_cloud2_vmHost_info表
			VMHostObj obj = new VMHostObj();
			obj.setID(vm_id);
			obj.setVH_ID(String.valueOf(vm_id));
			obj.setVH_NAME((String)json.get("vmName"));
			obj.setVH_UUID((String)json.get("vmUuid"));
			obj.setVH_CPU(json.get("cpuNum")==null ? "0":json.get("cpuNum").toString());
			long mem = json.get("memDynamicMax")==null ? 0L:Long.parseLong(json.get("memDynamicMax").toString());
			obj.setVH_MEM((mem/1024/1024)+"");
			obj.setVH_STAT((String)json.get("powerState"));
			obj.setVH_IP((String)json.get("vmIps"));
			obj.setVH_SYSTEM((String)json.get("osName"));
			long store =json.get("virtualSrSize")==null ? 0L:Long.parseLong(json.get("virtualSrSize").toString());
			obj.setVH_STORAGE((store/1024)+"");
			obj.setVH_ID(vm_id+"");
			//查询父节点的EQ_id
			TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
			if(parent.equals("host")){
				hostObj.setH_uuid(parentUUID);
				List hostLst = hostInfoService.queryForListByObj(hostObj);
				if(hostLst!=null && hostLst.size()>0){
					hostObj = (TbCloud2HostInfoObj)hostLst.get(0);
					obj.setEQ_ID(hostObj.getEq_id());	
				}
			}else{
				//虚拟机不在主机上
				obj.setEQ_ID("");
			}
			obj.setVH_TYPE("3");
			vmHostService.insertByVMhostObj(obj);
			//查询父节点
			XenEntityTreeObj queryNode = new XenEntityTreeObj();
			queryNode.setUuid(parentUUID);
			queryNode = xenEntityTreeService.queryTreeNode(queryNode);
			//插入tb_xen_entity_tree表
			XenEntityTreeObj vm_tree = new XenEntityTreeObj();
			vm_tree.setParentId(queryNode.getId());
			vm_tree.setType(TypeConstant.XEN_VM);
			vm_tree.setEntityId(String.valueOf(vm_id));
			vm_tree.setUuid((String)json.get("vmUuid"));
			vm_tree.setName((String)json.get("vmName"));
			xenEntityTreeService.insertTreeNode(vm_tree);
			//插入tb_xen_entity_con表
			XenEntityConObj con = new XenEntityConObj();
			
			if(parent.equals("host")){
				//父节点是主机
				XenEntityTreeObj host = new XenEntityTreeObj();
				host.setId(queryNode.getId());
				host = xenEntityTreeService.queryTreeNode(host);
				con.setClusterId(host.getParentId());
				XenEntityTreeObj cl = new XenEntityTreeObj();
				cl.setId(host.getParentId());
				cl = xenEntityTreeService.queryTreeNode(cl);
				con.setDataCenterId(cl.getParentId());
			}else{
				//父节点是集群
				con.setClusterId(queryNode.getId());
				XenEntityTreeObj cl = new XenEntityTreeObj();
				cl.setId(queryNode.getId());
				cl = xenEntityTreeService.queryTreeNode(cl);
				con.setDataCenterId(cl.getParentId());
			}
			con.setEntityId(String.valueOf(vm_id));
			con.setName((String)json.get("vmName"));
			con.setType(Integer.parseInt(TypeConstant.XEN_VM));
			int result = xenEntityTreeService.insertConObj(con);
		}
	}
	
	/**
	 * 
	 * @Title: addTemplet
	 * @Description: 数据定时同步时，添加模板
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jan 29, 2013 10:02:57 AM
	 */
	public void addTemplet(JSONObject json,String parent,String parentUUID){
			String checkUuid = (String)json.get("vmUuid");
			XenEntityTreeObj checkNode = new XenEntityTreeObj();
			checkNode.setUuid(checkUuid);
			checkNode = xenEntityTreeService.queryTreeNode(checkNode);
			if(checkNode==null){
				int vm_id = vmHostService.queryVhostIdSequence();
				//插入tb_cloud2_vmHost_info表
				VMHostObj obj = new VMHostObj();
				obj.setID(vm_id);
				obj.setVH_ID(String.valueOf(vm_id));
				obj.setVH_NAME((String)json.get("vmName"));
				obj.setVH_UUID((String)json.get("vmUuid"));
				obj.setVH_TYPE("6");
				vmHostService.insertByVMhostObj(obj);
				//查询父节点
				XenEntityTreeObj queryNode = new XenEntityTreeObj();
				queryNode.setUuid(parentUUID);
				queryNode = xenEntityTreeService.queryTreeNode(queryNode);
				//插入tb_xen_entity_tree表
				XenEntityTreeObj vm_tree = new XenEntityTreeObj();
				vm_tree.setParentId(queryNode.getId());
				vm_tree.setType(TypeConstant.XEN_IMAGE);
				vm_tree.setEntityId(String.valueOf(vm_id));
				vm_tree.setUuid((String)json.get("vmUuid"));
				vm_tree.setName((String)json.get("vmName"));
				xenEntityTreeService.insertTreeNode(vm_tree);
				//插入tb_xen_entity_con表
				XenEntityConObj con = new XenEntityConObj();
				
				if(parent.equals("host")){
					//父节点是主机
					XenEntityTreeObj host = new XenEntityTreeObj();
					host.setId(queryNode.getId());
					host = xenEntityTreeService.queryTreeNode(host);
					con.setClusterId(host.getParentId());
					XenEntityTreeObj cl = new XenEntityTreeObj();
					cl.setId(host.getParentId());
					cl = xenEntityTreeService.queryTreeNode(cl);
					con.setDataCenterId(cl.getParentId());
				}else{
					//父节点是集群
					con.setClusterId(queryNode.getId());
					XenEntityTreeObj cl = new XenEntityTreeObj();
					cl.setId(queryNode.getId());
					cl = xenEntityTreeService.queryTreeNode(cl);
					con.setDataCenterId(cl.getParentId());
				}
				con.setEntityId(String.valueOf(vm_id));
				con.setName((String)json.get("vmName"));
				con.setType(Integer.parseInt(TypeConstant.XEN_IMAGE));
				xenEntityTreeService.insertConObj(con);
			}
	}
	
}
