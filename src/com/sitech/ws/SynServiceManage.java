package com.sitech.ws;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sitech.basd.cloud3.domain.departproject.RelationObj;
import com.sitech.basd.cloud3.service.departproject.RelationService;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.united.UnitedTreeService;
import com.sitech.basd.sxcloud.cloud.dao.vmhost.VMHostDao;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VmRelationObj;
import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysUserinfoDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.yicloud.dao.entitytree.EntityTreeDao;
import com.sitech.basd.yicloud.domain.entitytree.EntityConObj;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.domain.vmauthority.VmAuthorityObj;
import com.sitech.basd.yicloud.domain.xentree.XenEntityConObj;
import com.sitech.basd.yicloud.domain.xentree.XenEntityTreeObj;
import com.sitech.basd.yicloud.service.vmauthority.VmAuthorityService;
import com.sitech.basd.yicloud.service.xentree.XenEntityTreeService;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.TypeConstant;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.vo.util.UnitedConstant;

@Path("/syn")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Service("synServiceManage")
@Transactional("cloudIbatisTransactionManager")
public class SynServiceManage {
	@Autowired
	private VMHostDao vmHostDao;
	@Autowired
	private EntityTreeDao entityTreeDao;
	@Autowired
	private XenEntityTreeService xenEntityTreeService;
	@Autowired
	private UnitedTreeService unitedTreeService;
	@Autowired
	private VmAuthorityService vmAuthorityService;
	@Autowired
	private TbSysUserinfoDao sysUserinfoDao;

	@Autowired
	private RelationService relationService;

	/**
	 * 
	 * @Title: synServiceManageVm
	 * @Description: 同步服务管理流程中创建的虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @createtime Mar 2, 2013 3:24:48 PM
	 */
	@POST
	@Path("/syn/servicemanage")
	public String servicemanage(String params) throws SQLException {
		params = JacksonUtil.fromJSON(params, new JacksonUtil.TypeReference<String>() {
		});
		JSONObject js = JSONObject.fromObject(params);
		String vm_uuid = js.getString("vmCode");
		String user_name = js.getString("user_name");
		String type = js.getString("type");
		int entity_id = vmHostDao.queryVhostIdSequence();
		VmRelationObj obj = new VmRelationObj();
		obj.setVm_uuid(vm_uuid);
		obj.setUser_name(user_name);
		vmHostDao.synServiceManageVm(obj);
		if ("1".equals(type)) {// vmware虚拟机
			String host_code = js.getString("hostCode");
			// String cluster_code = js.getString("cluster_code");
			// EntityTreeObj vmware = new EntityTreeObj();
			String connectCode = js.getString("connectCode");
			UnitedTreeObj vmware = new UnitedTreeObj();
			vmware.setUuid(host_code);
			vmware.setConnect_id(connectCode);
			vmware = unitedTreeService.queryByObj(vmware);

			// tree表
			UnitedTreeObj vmw = new UnitedTreeObj();
			vmw.setName(js.getString("newVmName"));
			vmw.setUuid(vm_uuid);
			vmw.setType(UnitedConstant.VM);
			vmw.setVtype(UnitedConstant.VMWARE);
			vmw.setParent_id(vmware.getId());
			vmw.setConnect_id(connectCode);
			unitedTreeService.insertByObj(vmw);
			// vmhost_info表
			VMHostObj vObj = new VMHostObj();
			vObj.setID(entity_id);
			vObj.setVH_NAME(js.getString("newVmName"));
			vObj.setVH_ID("0");
			vObj.setVH_CPU(js.getString("numCPUs"));
			vObj.setVH_MEM(js.getString("memoryMB"));
			vObj.setVH_STORAGE(js.getString("storageSizeInMb"));
			vObj.setVH_UUID(vm_uuid);
			vObj.setVH_TYPE("4");// vmware类型
			vObj.setVH_SYSTEM("Microsoft window 7(32位)");
			vObj.setConnectId(connectCode);
			vmHostDao.insertByVMhostObj(vObj);

			// entityuser表
			VmAuthorityObj auth = new VmAuthorityObj();
			auth.setCONNECT_ID(connectCode);
			auth.setENTITY_ID(vm_uuid);
			auth.setENTITY_NAME(js.getString("newVmName"));
			auth.setENTITY_TYPE("4");
			auth.setTYPE("1");
			TbSysUserinfoObj user = new TbSysUserinfoObj();
			user.setACCOUNT(user_name);
			user = sysUserinfoDao.queryByObj(user);
			if (user != null) {
				auth.setUSERID(user.getID());
			}
			auth.setOPERAUTHORITY("1");
			vmAuthorityService.insertByObj(auth);

			/*-----------------维护项目与虚拟机关系表-------------------*/
			RelationObj relationObj = new RelationObj();
			relationObj.setRelatonId(RandomUUID.getUuid());
			relationObj.setProjectId(js.getString("project_id"));
			relationObj.setConnectId(connectCode);
			relationObj.setVmuuId(vm_uuid);
			relationService.insertRelation(relationObj);

		} else {// xen虚拟机
			// 将成功创建的虚拟机同步到表中,暂时使用的模板是在nfs上的，所以虚拟机需要挂在集群的下边，后续该部分需要完善
			// 查询集群的id及数据中心的id,以便于插入entity_tree_con表
			XenEntityTreeObj cluster = new XenEntityTreeObj();
			cluster.setUuid((String) js.getString("cluster_code"));
			cluster = xenEntityTreeService.queryTreeNode(cluster);

			XenEntityTreeObj eObj = new XenEntityTreeObj();
			eObj.setName(js.getString("vm_name"));
			eObj.setUuid(vm_uuid);
			eObj.setEntityId(String.valueOf(entity_id));
			eObj.setType(TypeConstant.XEN_VM);
			eObj.setParentId(cluster.getId());
			xenEntityTreeService.insertTreeNode(eObj);

			XenEntityConObj conObj = new XenEntityConObj();
			conObj.setEntityId(String.valueOf(entity_id));
			conObj.setClusterId(cluster.getId());
			conObj.setDataCenterId(cluster.getParentId());
			conObj.setName(js.getString("vm_name"));
			conObj.setType(Integer.parseInt(TypeConstant.XEN_VM));
			xenEntityTreeService.insertConObj(conObj);

			VMHostObj vObj = new VMHostObj();
			vObj.setID(entity_id);
			vObj.setVH_NAME(js.getString("vm_name"));
			vObj.setVH_ID("0");
			vObj.setVH_CPU(js.getString("cpu"));
			vObj.setVH_MEM(js.getString("mem"));
			vObj.setVH_STORAGE(js.getString("store"));
			vObj.setVH_UUID(vm_uuid);
			vObj.setVH_TYPE("3");// xen类型
			vObj.setVH_SYSTEM("Red Hat Enterprise Linux Server release 6.2 (Santiago)");
			vmHostDao.insertByVMhostObj(vObj);
			// 数据插入数据库之后需要启动虚拟机
			String start_param = "/xen/vm/power/[connPoolUuid:" + cluster.getUuid() + "].[vmUuid:"
					+ vm_uuid + "].[powerState:" + "RUNNING" + "].[operateHostUuid:"
					+ "fc11228c-fba1-42fc-8db3-ff90b84b5af2" + "]/";
			String start_result = InvokeUtil.invoke(start_param);

			/*-----------------维护项目与虚拟机关系表-------------------*/
			String connectCode = js.getString("connectCode");
			RelationObj relationObj = new RelationObj();
			relationObj.setRelatonId(RandomUUID.getUuid());
			relationObj.setProjectId(js.getString("project_id"));
			relationObj.setConnectId(connectCode);
			relationObj.setVmuuId(vm_uuid);
			relationService.insertRelation(relationObj);
		}
		return "success";
	}

	/**
	 * 
	 * @Title: getClusterInfo
	 * @Description: 获取集群及数据中心的相关信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 4, 2013 8:45:22 AM
	 */
	/*
	 * @RequestMapping(value = "/syn/servicemanage/{params}", method =
	 * RequestMethod.GET) public @ResponseBody String
	 * getClusterInfo(@PathVariable("params") String params) { XenEntityTreeObj
	 * obj = new XenEntityTreeObj(); obj.setType(TypeConstant.XEN_CLUSTER); obj
	 * = xenEntityTreeService.queryTreeNode(obj); Map map = new HashMap();
	 * map.put("cluster_id", obj.getId()); map.put("dataCenter_id",
	 * obj.getParentId()); return null; }
	 */

	public static void main(String[] args) {
		String start_param = "/xen/vm/power/[connPoolUuid:"
				+ "5c16c2e3-0eb3-c62a-1a51-695cfd425d68" + "].[vmUuid:"
				+ "b6372218-5fd2-b0aa-3af9-efc3eda9db12" + "].[powerState:" + "RUNNING"
				+ "].[operateHostUuid:" + "fc11228c-fba1-42fc-8db3-ff90b84b5af2" + "]/";
		String start_result = InvokeUtil.invoke(start_param);
	}

	// 调整虚拟机
	@RequestMapping(value = "/syn/adjustVM/{params}", method = RequestMethod.GET)
	public @ResponseBody
	String adjustVM(@PathVariable("params") String params) {
		JSONObject js = JSONObject.fromObject(params);
		String type = js.getString("type");
		String vm_code = js.getString("vmUuid");
		VMHostObj obj = new VMHostObj();
		obj.setVH_UUID(vm_code);
		obj.setVH_CPU(js.getString("cpu"));
		obj.setVH_MEM(js.getString("mem"));
		double store = Double.parseDouble((String) js.getString("store"));
		obj.setVH_STORAGE(String.valueOf(store * 1024 * 1024));
		vmHostDao.updateCpuAndMem(obj);
		return null;
	}

	@RequestMapping(value = "/syn/recycleVM/{params}", method = RequestMethod.GET)
	public @ResponseBody
	String recycleVM(@PathVariable("params") String params) {
		JSONObject js = JSONObject.fromObject(params);
		String type = js.getString("type");
		String vm_code = js.getString("vmUuid");
		if ("1".equals(type)) {// vmware
			EntityTreeObj obj = new EntityTreeObj();
			obj.setEntityId(vm_code);
			obj = entityTreeDao.queryTreeNode(obj);
			entityTreeDao.delTreeNode(obj);
			// con
			EntityConObj con = new EntityConObj();
			con.setEntityId(vm_code);
			con.setType(0);
			entityTreeDao.deleteConObj(con);
			// vmhost
			VMHostObj vm = new VMHostObj();
			vm.setVH_UUID(vm_code);
			vmHostDao.deleteByObj(vm);
		} else {
			XenEntityTreeObj xen = new XenEntityTreeObj();
			xen.setUuid(vm_code);
			xen = xenEntityTreeService.queryTreeNode(xen);
			XenEntityConObj con = new XenEntityConObj();
			con.setEntityId(xen.getEntityId());
			xenEntityTreeService.delTreeNode(xen);
			xenEntityTreeService.deleteConObj(con);
			VMHostObj vmh = new VMHostObj();
			vmh.setVH_UUID(vm_code);
			vmHostDao.deleteByObj(vmh);

		}
		return null;
	}

}
