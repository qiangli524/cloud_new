package com.sitech.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sitech.basd.common.ResponseCode;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.yicloud.domain.entitytree.EntityConObj;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.service.entitytree.EntityTreeService;
import com.sitech.basd.yicloud.util.CfgUtil;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.ParamParser;
import com.sitech.basd.yicloud.util.TypeConstant;
import com.sitech.ws.web.NoticeUtil;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IncrementalResources {
	private EntityTreeService entityTreeService;
	private HostInfoService hostInfoService;
	private VMHostService vmHostService;
	/** CMDB通知链接 */
	private static String cmdbUrl = "http://" + CfgUtil.getString("cmdb.ip") + ":"
			+ CfgUtil.getString("cmdb.port") + "/" + CfgUtil.getString("cmdb.web")
			+ "/rest/vmSynByIcrement/callerCMDB";
	/** 监控通知链接 */
	private static String monitorUrl = "http://" + CfgUtil.getString("monitor.ip") + ":"
			+ CfgUtil.getString("monitor.port") + "/" + CfgUtil.getString("monitor.web")
			+ "/ibnms/incrementalResources";

	public void setEntityTreeService(EntityTreeService entityTreeService) {
		this.entityTreeService = entityTreeService;
	}

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public void setVmHostService(VMHostService vmHostService) {
		this.vmHostService = vmHostService;
	}

	@POST
	@Path("/incrementalResources")
	public List incrementalResources(List<Map<String, String>> entityList) {
		List<Map<String, String>> list3 = new ArrayList<Map<String, String>>();
		// 如果对象的TTYPE=="TTYPE_CI" 此对象中存的是实体
		for (int i = 0; i < entityList.size(); i++) {
			Map<String, String> map = entityList.get(i);
			if (Type.TTYPE_CI.equals(map.get("TTYPE"))) {
				if (Type.CTYPE_VM.equals(map.get("TYPE"))) {// 虚拟机
					if (Operation.OPER_ADD.equals(map.get("OPERATE"))) {
						String param = "/vmware/domain/msg/[vmName:" + map.get("CODE") + "]/";
						String result = InvokeUtil.invoke(param);
						Map vmInfo = ParamParser.makeup(result);
						String hostName = (String) vmInfo.get("hostName");// 虚拟机所在主机
						String code = (String) vmInfo.get("CODE");
						String hostCode = (String) vmInfo.get("hostCode");
						String vmName = map.get("NAME");
						// 通知cmdb和监控管理
						try {
							NoticeUtil.getInstance().updateCMDBHost(hostCode,
									Operation.OPER_REL_MODI);
							/*
							 * // cmdb通知 String cmdbResult =
							 * HttpClientUtil.post(cmdbUrl,
							 * JSONArray.fromObject(entityList)); // 监控通知 String
							 * monitorResult = HttpClientUtil.post( monitorUrl,
							 * JSONArray .fromObject(entityList));
							 */
						} catch (Exception ex) {
						}

						String responseCode = (String) vmInfo.get(ResponseCode.RESPONSE_CODE);
						if (responseCode.equals(ResponseCode.FAIL))
							return list3;
						// 向entity_tree插入数据
						int id = vmHostService.queryVhostIdSequence();
						EntityTreeObj e = new EntityTreeObj();
						e.setEntityId(code);
						e.setName(vmName);

						EntityTreeObj queryObj = new EntityTreeObj();
						queryObj.setName(hostName);
						queryObj.setType(TypeConstant.VMWARE_HOST);
						queryObj = entityTreeService.queryTreeNode(queryObj);
						e.setParentId(queryObj.getId());
						e.setType(TypeConstant.VMWARE_VM);
						int ret = entityTreeService.insertTreeNode(e);

						// 向entity_con插入数据
						String cpu = vmInfo.get("cpuNum").toString();
						String memory = vmInfo.get("memoryMB").toString();
						String state = vmInfo.get("power").toString();
						String system = vmInfo.get("guestName").toString();
						EntityConObj con = new EntityConObj();
						con.setName(vmName);
						con.setType(Integer.parseInt(TypeConstant.VMWARE_VM));
						con.setCpu(cpu);
						con.setMem(memory);
						con.setStatus(state);
						// 查询主机在那个数据中心和集群下，虚拟机和主机同在数据中心和集群下
						EntityConObj queryConObj = new EntityConObj();
						queryConObj.setType(Integer.parseInt(TypeConstant.VMWARE_HOST));
						queryConObj.setName(hostName);
						queryConObj = entityTreeService.queryEntityInfoByObj(queryConObj);

						con.setDataCenterId(queryConObj.getDataCenterId());
						con.setClusterId(queryConObj.getClusterId());
						con.setEntityId(code);
						int ret1 = entityTreeService.insertConObj(con);
						// 向vmhost表中插入数据
						VMHostObj v = new VMHostObj();
						v.setVH_ID("0");
						v.setID(id);
						v.setVH_NAME(vmName);
						v.setVH_TYPE("1");// vmware虚拟机
						v.setVH_CPU(cpu);
						v.setVH_MEM(memory);
						v.setVH_SYSTEM(system);
						String storage = (String) vmInfo.get("storage");
						v.setVH_STORAGE(storage);
						v.setVH_UUID(code);
						int ret2 = vmHostService.insertByVMhostObj(v);

					} else if (Operation.OPER_DEL.equals(map.get("OPERATE"))) {
						// 通知cmdb和监控管理
						try {
							NoticeUtil.getInstance().delCMDBVM(map.get("CODE"), Operation.OPER_DEL);
							/*
							 * // cmdb通知 String cmdbResult =
							 * HttpClientUtil.post(cmdbUrl,
							 * JSONArray.fromObject(entityList)); // 监控通知 String
							 * monitorResult = HttpClientUtil.post( monitorUrl,
							 * JSONArray .fromObject(entityList));
							 */
						} catch (Exception ex) {
						}
						// 删除entity_tree表中数据
						EntityTreeObj en = new EntityTreeObj();
						en.setEntityId(map.get("CODE"));
						en.setType(TypeConstant.VMWARE_VM);
						en = entityTreeService.queryTreeNode(en);
						String entity_id = en.getEntityId();
						int ret1 = entityTreeService.delTreeNode(en);
						// 删除entity_con表中数据
						EntityConObj enc = new EntityConObj();
						enc.setEntityId(entity_id);
						enc.setType(Integer.parseInt(TypeConstant.VMWARE_VM));
						int ret2 = entityTreeService.deleteConObj(enc);
						// 删除vmhost表中数据
						VMHostObj v = new VMHostObj();
						v.setVH_UUID(entity_id);
						int ret3 = vmHostService.deleteByObj(v);

					} else if (Operation.OPER_MODI.equals(map.get("OPERATE"))) {
						// 通知cmdb和监控管理
						try {
							NoticeUtil.getInstance().updateCMDBVM(map.get("CODE"),
									Operation.OPER_MODI);
							/*
							 * // cmdb通知 String cmdbResult =
							 * HttpClientUtil.post(cmdbUrl,
							 * JSONArray.fromObject(entityList)); // 监控通知 String
							 * monitorResult = HttpClientUtil.post( monitorUrl,
							 * JSONArray .fromObject(entityList));
							 */
						} catch (Exception ex) {
						}
						// 修改entity_tree表中数据
						EntityTreeObj en = new EntityTreeObj();
						String entity_id = map.get("CODE");
						en.setEntityId(entity_id);
						en.setType(TypeConstant.VMWARE_VM);
						en = entityTreeService.queryTreeNode(en);
						// int ret1 = entityTreeService.updateTreeNode(en);
						// 修改entity_con表中数据
						String param = "/vmware/domain/msg/[vmName:" + map.get("CODE") + "]/";
						String result = InvokeUtil.invoke(param);
						Map vmInfo = ParamParser.makeup(result);
						String cpu = vmInfo.get("cpuNum").toString();
						String memory = vmInfo.get("memoryMB").toString();
						String state = vmInfo.get("power").toString();
						String system = vmInfo.get("guestName").toString();
						String storage = (String) vmInfo.get("storage");
						/*
						 * EntityConObj enc = new EntityConObj();
						 * enc.setEntityId(entity_id);
						 * enc.setType(Integer.parseInt
						 * (TypeConstant.VMWARE_VM)); enc.setCpu(cpu);
						 * enc.setMem(memory); enc.setStatus(state); int ret2 =
						 * entityTreeService.updateEntityConInfo(enc);
						 */
						// 修改vmhost表中数据
						VMHostObj v = new VMHostObj();
						v.setVH_UUID(entity_id);
						v.setVH_CPU(cpu);
						v.setVH_MEM(memory);
						v.setVH_STORAGE(storage);
						int ret3 = vmHostService.updateCpuAndMem(v);
					}
				} else if (Type.CTYPE_HOST.equals(map.get("TYPE"))) {// 宿主机
					if (Operation.OPER_ADD.equals(map.get("OPERATE"))) {
						int hostId = hostInfoService.getIdSequence();
						EntityTreeObj en = new EntityTreeObj();
						en.setEntityId(String.valueOf(hostId));
						en.setName(map.get("CODE"));
						// e.setParentId();
						en.setType(TypeConstant.VMWARE_HOST);
						int ret = entityTreeService.insertTreeNode(en);
						// 向entity_con插入数据
						EntityConObj con = new EntityConObj();
						con.setName(map.get("CODE"));
						con.setType(Integer.parseInt(TypeConstant.VMWARE_HOST));
						con.setDataCenterId(2);
						// con.setClusterId(clusterId);
						int ret1 = entityTreeService.insertConObj(con);
						// 向host_info表中插入数据
						TbCloud2HostInfoObj t = new TbCloud2HostInfoObj();
						t.setEq_name(map.get("CODE"));
						t.setId(hostId);
						t.setEq_id("0");
						int ret2 = hostInfoService.insertByObj(t);
					} else if (Operation.OPER_DEL.equals(map.get("OPERATE"))) {
						// 删除entity_tree表中数据
						EntityTreeObj en = new EntityTreeObj();
						en.setName(map.get("CODE"));
						en.setType(TypeConstant.VMWARE_HOST);
						en = entityTreeService.queryTreeNode(en);
						String entity_id = en.getEntityId();
						int ret1 = entityTreeService.delTreeNode(en);
						// 删除entity_con表中数据
						EntityConObj enc = new EntityConObj();
						enc.setEntityId(entity_id);
						enc.setType(Integer.parseInt(TypeConstant.VMWARE_VM));
						int ret2 = entityTreeService.deleteConObj(enc);
						// 删除host_info表中数据
						TbCloud2HostInfoObj t = new TbCloud2HostInfoObj();
						t.setId(Integer.parseInt(entity_id));
						int ret3 = hostInfoService.deleteByObj(t);
					} else if (Operation.OPER_MODI.equals(map.get("OPERATE"))) {
						// 删除entity_tree表中数据
						EntityTreeObj en = new EntityTreeObj();
						en.setName(map.get("CODE"));
						en.setType(TypeConstant.VMWARE_HOST);
						en = entityTreeService.queryTreeNode(en);
						String entity_id = en.getEntityId();
						int ret1 = entityTreeService.updateTreeNode(en);
						// 删除entity_con表中数据
						EntityConObj enc = new EntityConObj();
						enc.setEntityId(entity_id);
						enc.setType(Integer.parseInt(TypeConstant.VMWARE_VM));
						int ret2 = entityTreeService.updateConObj(enc);
						// 删除host_info表中数据
						TbCloud2HostInfoObj t = new TbCloud2HostInfoObj();
						t.setId(Integer.parseInt(entity_id));
						int ret3 = hostInfoService.updateByObj(t);
					}
				} else if (Type.CTYPE_CLUSTER.equals(map.get("TYPE"))) {// 集群
					if (Operation.OPER_ADD.equals(map.get("OPERATE"))) {
						// 向entity_tree插入数据
						EntityTreeObj e = new EntityTreeObj();
						e.setName(map.get("CODE"));
						// e.setParentId();
						e.setType(TypeConstant.VMWARE_CLUSTER);
						int ret = entityTreeService.insertTreeNode(e);
						// 向entity_con表插入数据
						EntityTreeObj en = new EntityTreeObj();
						en.setName(map.get("CODE"));
						en = entityTreeService.queryTreeNode(en);
						int clusterId = en.getId();
						EntityConObj ec = new EntityConObj();
						ec.setClusterId(clusterId);
						// ec.setDataCenterId(dataCenterId);
						int ret1 = entityTreeService.insertConObj(ec);
					} else if (Operation.OPER_DEL.equals(map.get("OPERATE"))) {

					} else if (Operation.OPER_MODI.equals(map.get("OPERATE"))) {

					}
				} else if (Type.CTYPE_VDC.equals(map.get("TYPE"))) {// 数据中心
					if (Operation.OPER_ADD.equals(map.get("OPERATE"))) {

					} else if (Operation.OPER_DEL.equals(map.get("OPERATE"))) {

					} else if (Operation.OPER_MODI.equals(map.get("OPERATE"))) {

					}
				} else if (Type.CTYPE_STORAGE.equals(map.get("TYPE"))) {// 数据存储
					if (Operation.OPER_ADD.equals(map.get("OPERATE"))) {

					} else if (Operation.OPER_DEL.equals(map.get("OPERATE"))) {

					} else if (Operation.OPER_MODI.equals(map.get("OPERATE"))) {

					}
				}
			}
		}
		return list3;
	}
}
