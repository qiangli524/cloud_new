package com.sitech.basd.yicloud.service.portgroup;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sitech.basd.common.ResponseCode;
import com.sitech.basd.yicloud.dao.entitytree.EntityTreeDao;
import com.sitech.basd.yicloud.dao.nic.NicRelationDao;
import com.sitech.basd.yicloud.dao.portgroup.PortGroupDao;
import com.sitech.basd.yicloud.dao.switches.VirtualSwitchDao;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.domain.nic.NicRelationObj;
import com.sitech.basd.yicloud.domain.portgroup.PortGroup;
import com.sitech.basd.yicloud.domain.switches.VirtualSwitch;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.ParamParser;
import com.sitech.ws.Operation;
import com.sitech.ws.web.NoticeUtil;

public class PortGroupServiceImpl implements PortGroupService {
	private PortGroupDao portGroupDao;
	private EntityTreeDao entityTreeDao;
	private VirtualSwitchDao virtualSwitchDao;
	private NicRelationDao nicRelationDao;

	public void setNicRelationDao(NicRelationDao nicRelationDao) {
		this.nicRelationDao = nicRelationDao;
	}

	public void setVirtualSwitchDao(VirtualSwitchDao virtualSwitchDao) {
		this.virtualSwitchDao = virtualSwitchDao;
	}

	public void setEntityTreeDao(EntityTreeDao entityTreeDao) {
		this.entityTreeDao = entityTreeDao;
	}

	public void setPortGroupDao(PortGroupDao portGroupDao) {
		this.portGroupDao = portGroupDao;
	}

	/**
	 * @Title:插入端口组信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(Boolean b, String hostName, PortGroup obj) {
		int ret = 0;
		if (b == true) {
			String portGroupName = obj.getName();
			EntityTreeObj treeObj = new EntityTreeObj();
			treeObj.setName(hostName);
			treeObj = entityTreeDao.queryTreeNode(treeObj);
			String hostCode = treeObj.getEntityId();
			String switchName = obj.getVswitchName();
			String vlanId = String.valueOf(obj.getVlanId());
			String url = "/vmware/network/portgroup/add/[hostCode:" + hostCode + "].[portGroupName:" + portGroupName
					+ "].[switchName:" + switchName + "].[vlan:" + vlanId + "]/";
			String addResult = InvokeUtil.invoke(url);
			Map ps = ParamParser.makeup(addResult);
			String code = (String) ps.get(ResponseCode.RESPONSE_CODE);

			String pgUuid = ps.get("PORTGROUP") == null ? "" : (String) ps.get("PORTGROUP");
			obj.setPguuid(pgUuid);
			if (code.equals(ResponseCode.SUCCESS)) {
				/** 通知 */
				try {
					NoticeUtil.getInstance().updateCMDBHost(hostCode, Operation.OPER_REL_MODI);// cmdb
				} catch (Exception e) {

				}
				// 插入端口组
				obj.setType("0");
				ret = portGroupDao.insertByObj(obj);
				// 插入端口组的关系

				// 向库中插入虚拟机网络相关的关系
				String net_param = "/vmware/domain/synVCenterTemp/[TYPE:HOST].[CODE:" + hostCode + "]/";
				String net = InvokeUtil.invoke(net_param);
				List<JSONObject> netjo = JSONArray.toList(JSONArray.fromObject(net), JSONObject.class);
				for (JSONObject jo : netjo) {
					if (jo.get("RELATIONSHIP") != null) {
						// 插入虚拟机网络关联关系
						NicRelationObj nicRelationObj = new NicRelationObj();
						if (jo.getString("RELATIONSHIP").equals("PORTGROUP_VSS")
								&& jo.getString("FROM_CODE").equals(pgUuid)) {
							nicRelationObj.setType(jo.get("RELATIONSHIP") == null ? "" : jo.getString("RELATIONSHIP"));
							nicRelationObj.setToUuid(jo.get("TO_CODE") == null ? "" : jo.getString("TO_CODE"));
							nicRelationObj.setFromUuid(jo.get("FROM_CODE") == null ? "" : jo.getString("FROM_CODE"));
							nicRelationDao.insertByObj(nicRelationObj);
						} else if (jo.getString("RELATIONSHIP").equals("PORTGROUP_VM")
								&& jo.getString("FROM_CODE").equals(pgUuid)) {
							nicRelationObj.setType(jo.get("RELATIONSHIP") == null ? "" : jo.getString("RELATIONSHIP"));
							nicRelationObj.setToUuid(jo.get("TO_CODE") == null ? "" : jo.getString("TO_CODE"));
							nicRelationObj.setFromUuid(jo.get("FROM_CODE") == null ? "" : jo.getString("FROM_CODE"));
							nicRelationDao.insertByObj(nicRelationObj);
						} else if (jo.getString("RELATIONSHIP").equals("VNIC_PORTGROUP")
								&& jo.getString("FROM_CODE").equals(pgUuid)) {
							nicRelationObj.setType(jo.get("RELATIONSHIP") == null ? "" : jo.getString("RELATIONSHIP"));
							nicRelationObj.setToUuid(jo.get("TO_CODE") == null ? "" : jo.getString("TO_CODE"));
							nicRelationObj.setFromUuid(jo.get("FROM_CODE") == null ? "" : jo.getString("FROM_CODE"));
							nicRelationDao.insertByObj(nicRelationObj);
						}
					}
				}

			}

		} else {
			ret = portGroupDao.insertByObj(obj);
		}
		return ret;
	}

	/**
	 * @Title:查询虚拟交换机对应的端口组
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public List queryPGListByPortGroup(PortGroup obj) {
		return portGroupDao.queryPGListByPortGroup(obj);
	}

	/**
	 * @Title:修改端口组信息
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int updateByObj(String hostName, PortGroup obj) {
		int ret = 0;
		String portGroupName = obj.getName();
		String switchName = obj.getVswitchName();
		String vlanId = String.valueOf(obj.getVlanId());
		EntityTreeObj treeObj = new EntityTreeObj();
		treeObj.setName(hostName);
		treeObj = entityTreeDao.queryTreeNode(treeObj);
		String hostCode = treeObj.getEntityId();

		// 查询原来端口组的名称，即要更改的端口组名称
		PortGroup tempObj = portGroupDao.queryPortGroupById(obj);
		String oldPortGroupName = tempObj.getName();
		String url = "/vmware/network/portgroup/config/[hostName:" + hostCode + "].[portGroupName:" + oldPortGroupName
				+ "].[switchName:" + switchName + "].[vlanId:" + vlanId + "].[newPortGroupName:" + portGroupName + "]/";
		String config = InvokeUtil.invoke(url);
		Map ps = ParamParser.makeup(config);
		String code = (String) ps.get(ResponseCode.RESPONSE_CODE);
		if (code.equals(ResponseCode.SUCCESS)) {
			/** 通知 */
			try {
				NoticeUtil.getInstance().updateCMDBHost(hostCode, Operation.OPER_MODI);// cmdb
			} catch (Exception e) {

			}
			// try {
			// NoticeUtil.getInstance().updateHost(hostName);
			// } catch (Exception ex) {
			//
			// }
			obj.setType(tempObj.getType());
			ret = portGroupDao.updateByObj(obj);
		}
		return ret;
	}

	/**
	 * @Title:删除端口组信息
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int deleteByObj(PortGroup obj, String vSwitchId) {
		VirtualSwitch vs = new VirtualSwitch();
		vs.setId(Integer.parseInt(vSwitchId));
		List vsLst = virtualSwitchDao.queryByObj(vs);
		String hostName = "";
		String dcname = "";
		if (vsLst != null && vsLst.size() > 0) {
			vs = (VirtualSwitch) vsLst.get(0);
			EntityTreeObj treeObj = new EntityTreeObj();
			treeObj.setName(vs.getHostName());
			hostName = vs.getHostName();
			treeObj = entityTreeDao.queryTreeNode(treeObj);
			EntityTreeObj clObj = new EntityTreeObj();
			clObj.setId(treeObj.getParentId());
			clObj = entityTreeDao.queryTreeNode(clObj);
			EntityTreeObj dcobj = new EntityTreeObj();
			dcobj.setId(clObj.getParentId());
			dcobj = entityTreeDao.queryTreeNode(dcobj);
			dcname = dcobj.getName();
		}
		String url = "/vmware/network/removeVSPortGroup/[hostName:" + hostName + "].[portGroupName:" + obj.getName()
				+ "].[dcname:" + dcname + "]/";
		String invokeResult = InvokeUtil.invoke(url);
		Map ps = ParamParser.makeup(invokeResult);
		String reponseCode = (String) ps.get(ResponseCode.RESPONSE_CODE);
		int result = 0;
		if (reponseCode.equals(ResponseCode.SUCCESS)) {
			result = 1;
			// 删除数据库中的数据
			portGroupDao.deleteByObj(obj);// 删除交换机的端口组
			// 删除关系
			NicRelationObj pg_vmReOjb = new NicRelationObj();
			pg_vmReOjb.setType("PORTGROUP_VM");
			pg_vmReOjb.setFromUuid(obj.getPguuid());
			nicRelationDao.deleteByObj(pg_vmReOjb);

			NicRelationObj vnic_pgReOjb = new NicRelationObj();
			vnic_pgReOjb.setType("VNIC_PORTGROUP");
			vnic_pgReOjb.setToUuid(obj.getPguuid());
			nicRelationDao.deleteByObj(vnic_pgReOjb);

			NicRelationObj pg_vssReOjb = new NicRelationObj();
			pg_vssReOjb.setType("PORTGROUP_VSS");
			pg_vssReOjb.setFromUuid(obj.getPguuid());
			nicRelationDao.deleteByObj(pg_vssReOjb);

		} else if (reponseCode.equals(ResponseCode.FAIL)) {
			result = -1;
		}
		return result;
	}

	/**
	 * @Title:根据端口组ID查询端口组
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public PortGroup queryPortGroupById(PortGroup obj) {
		return portGroupDao.queryPortGroupById(obj);
	}

	/**
	 * @Title:查询主机对应的端口组
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List<NicRelationObj> queryHostPortGroup(VirtualSwitch obj) {
		return portGroupDao.queryHostPortGroup(obj);
	}

	/**
	 * 
	 * @Title: createNetWork
	 * @Description: 创建vmware网络
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 18, 2013 5:54:15 PM
	 */
	public String createNetWork(Map map) {
		String type = map.get("type").toString();
		String nic = (String) map.get("nic");
		String param = "";
		String param_vswitch = "/vmware/network/creatVirtualSwitch/[hostCode:" + map.get("host_code").toString()
				+ "].[portGroupName:" + map.get("name") + "].[switchName:" + map.get("name");
		String param_port = "/vmware/network/portgroup/add/[hostCode:" + map.get("host_code").toString()
				+ "].[portGroupName:" + map.get("name") + "].[switchName:" + nic;
		if (type.equals("1") && nic.equals("创建vSphere标准交换机")) {// 创建普通虚拟交换机
			param = param_vswitch + "]/";
		} else if (type.equals("1") && !nic.equals("创建vSphere标准交换机")) {// 创建普通端口组
			param = param_port + "]/";
		} else if (type.equals("2") && nic.equals("创建vSphere标准交换机")) {// 创建vmkernel虚拟交换机
			param = param_vswitch + "].[isVMKernal:" + true + "].[ip:" + map.get("ip") + "].[mask:"
					+ map.get("sub_net") + "]/";
		} else {
			param = param_port + "].[isVMKernal:" + true + "].[ip:" + map.get("ip") + "].[mask:" + map.get("sub_net")
					+ "]/";
		}
		String result = InvokeUtil.invoke(param);
		Map ps = ParamParser.makeup(result);
		if (((String) ps.get("responseCode")).equals("1")) {
			EntityTreeObj obj = new EntityTreeObj();
			obj.setEntityId(map.get("host_code").toString());
			obj = entityTreeDao.queryTreeNode(obj);
			if (nic.equals("创建vSphere标准交换机")) {
				// 插入虚拟交换机和虚拟交换机下的端口组
				String net_param = "/vmware/domain/synVCenterTemp/[TYPE:HOST].[CODE:" + map.get("host_code").toString()
						+ "]/";
				String net = InvokeUtil.invoke(net_param);
				List<JSONObject> netjo = JSONArray.toList(JSONArray.fromObject(net), JSONObject.class);
				for (JSONObject jo : netjo) {
					if (jo.get("TYPE") != null) {
						if (jo.getString("TYPE").equals("VSS") && jo.getString("CODE").equals(ps.get("PORTGROUP"))) {
							VirtualSwitch vs = new VirtualSwitch();
							vs.setName(jo.get("NAME") == null ? "" : jo.getString("NAME"));
							vs.setNumPorts(jo.get("NUMPORTS") == null ? 0 : jo.getInt("NUMPORTS"));
							vs.setNumPortsAvailable(jo.get("NUMPORTSAVAILABLE") == null ? 0 : jo
									.getInt("NUMPORTSAVAILABLE"));
							vs.setVssuuid(jo.get("CODE") == null ? "" : jo.getString("CODE"));
							vs.setHostName(obj.getName());
							vs.setType("0");//暂时使用 后续添加分布式时，修改
							virtualSwitchDao.insertByObj(vs);
						} else if (jo.getString("TYPE").equals("PORTGROUP")
								&& jo.getString("CODE").equals(ps.get("PORTGROUP"))) {
							PortGroup pgobj = new PortGroup();
							pgobj.setName(jo.get("NAME") == null ? "" : jo.getString("NAME"));
							pgobj.setType("0");//暂时使用 后续添加分布式时，修改
							pgobj.setVlanId(jo.get("VLAN") == null ? 0 : jo.getInt("VLAN"));
							pgobj.setPguuid(jo.get("CODE") == null ? "" : jo.getString("CODE"));
							insertByObj(false, obj.getName(), pgobj);
						}
					} else if (jo.get("RELATIONSHIP") != null) {
						// 暂只取主机和网卡、虚拟网卡和虚拟机、端口组和虚拟机、网卡和虚拟交换机、虚拟交换机和主机的关系
						if (jo.getString("RELATIONSHIP").equals("PORTGROUP_VSS")
								&& jo.getString("TO_CODE").equals(ps.get("PORTGROUP"))) {
							NicRelationObj nicRelationObj = new NicRelationObj();
							nicRelationObj.setType(jo.get("RELATIONSHIP") == null ? "" : jo.getString("RELATIONSHIP"));
							nicRelationObj.setToUuid(jo.get("TO_CODE") == null ? "" : jo.getString("TO_CODE"));
							nicRelationObj.setFromUuid(jo.get("FROM_CODE") == null ? "" : jo.getString("FROM_CODE"));
							nicRelationDao.insertByObj(nicRelationObj);
						}
					}
				}

			} else {// 创建端口组
				VirtualSwitch sObj = new VirtualSwitch();
				sObj.setName(nic);
				sObj.setHostName(obj.getName());
				List<VirtualSwitch> list = virtualSwitchDao.queryByObj(sObj);
				if (list != null && list.size() > 0) {
					// 插入端口组信息
					PortGroup pObj = new PortGroup();
					pObj.setName(map.get("name").toString());
					pObj.setVswitchId(0);
					pObj.setVlanId(Integer.parseInt(map.get("vlan").toString()));
					pObj.setPguuid((String) ps.get("PORTGROUP"));
					pObj.setType("0");//暂时使用 后续添加分布式时，修改
					portGroupDao.insertByObj(pObj);
					// 插入端口组关系
					NicRelationObj nicReObj = new NicRelationObj();
					nicReObj.setType("PORTGROUP_VSS");
					nicReObj.setFromUuid((String) ps.get("PORTGROUP"));
					nicReObj.setToUuid(list.get(0).getVssuuid());
					nicRelationDao.insertByObj(nicReObj);
				}
			}
			result = "{\"responseCode\":\"1\"}";
		}
		return result;
	}

	/**
	 * 
	 * @Title: removeVirtualSwitch
	 * @Description: 删除虚拟交换机
	 * @return String
	 * @author duangh
	 * @version 1.0
	 */
	public String removeVirtualSwitch(VirtualSwitch vs) {
		String hostCode = vs.getHostCode();
		String vsName = vs.getName();
		String result = "1";
		String reason = "";
		try {
			String url = "/vmware/network/removeVirtualSwitch/[hostName:" + hostCode + "].[vswitchName:" + vsName
					+ "]/";
			String invokeResult = InvokeUtil.invoke(url);
			Map ps = ParamParser.makeup(invokeResult);
			String reponseCode = (String) ps.get(ResponseCode.RESPONSE_CODE);
			if (reponseCode.equals(ResponseCode.SUCCESS)) {
				result = "1";
				// 删除数据库中的数据
				portGroupDao.deleteVirtualSwitch(vs);// 删除虚拟交换机
				portGroupDao.deleteByVswitchid(vs);// 删除交换机的端口组
			} else if (reponseCode.equals(ResponseCode.FAIL)) {
				result = "-1";
				reason = (String) ps.get(ResponseCode.RESPONSEREMARK);
			}

		} catch (Exception e) {
			result = "-1";
			reason = e.getMessage();
		}
		String json = "{\"result\":\"" + result + "\",\"reason\":\"" + reason + "\"}";
		return json;
	}
}
