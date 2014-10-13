package com.sitech.basd.yicloud.web.cluster.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.cluster.ClusterObj;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.service.cluster.ClusterService;
import com.sitech.basd.yicloud.service.entitytree.EntityTreeService;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.ParamParser;
import com.sitech.basd.yicloud.util.TypeConstant;
import com.sitech.basd.yicloud.web.cluster.form.ClusterForm;
import com.sitech.utils.servlet.PrintWriterOut;

public class ClusterAction extends CRUDBaseAction {
	private ClusterForm theForm;
	private ClusterService clusterService;
	private EntityTreeService entityTreeService;
	private static final Map clusterMap = new HashMap();
	private static String configResult = "";

	public void setEntityTreeService(EntityTreeService entityTreeService) {
		this.entityTreeService = entityTreeService;
	}

	public void setClusterService(ClusterService clusterService) {
		this.clusterService = clusterService;
	}

	public ClusterForm getTheForm() {
		return theForm;
	}

	public void setTheForm(ClusterForm theForm) {
		this.theForm = theForm;
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
	 * @throws UnsupportedEncodingException
	 * @createtime Jul 17, 2012 10:57:45 AM
	 */
	public String addCluster() throws UnsupportedEncodingException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String ID = request.getParameter("id");
		String dcName = request.getParameter("dcName");// 集群所在的数据中心
		dcName = URLDecoder.decode(dcName, "utf-8");
		String entityId = request.getParameter("entityId");// 实体Id，即code
		request.setAttribute("dcName", dcName);
		request.setAttribute("ID", ID);
		request.setAttribute("entityId", entityId);
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
	 * @throws Exception
	 * @createtime Jul 17, 2012 2:45:58 PM
	 */
	public String saveCluster() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String ID = request.getParameter("ID");
		String dcName = request.getParameter("dcName");
		String clusterName = request.getParameter("clusterName");
		// String clustertype = request.getParameter("clustertype");
		dcName = URLDecoder.decode(dcName, "utf-8");
		clusterName = URLDecoder.decode(clusterName, "utf-8");
		String entityId = request.getParameter("entityId");
		int ret = 0;
		String flag = "-1";
		ClusterObj cObj = new ClusterObj();
		cObj.setName(clusterName);
		// cObj.setType(clustertype);
		cObj.setDcName(dcName);
		cObj.setParentId(ID);
		cObj.setC_uuid(entityId);
		ret = clusterService.insertByObj(cObj);
		if (ret != -1) {// 集群创建成功后插入节点数据
			/*
			 * EntityTreeObj obj = new EntityTreeObj();
			 * obj.setParentId(Integer.parseInt(ID)); obj.setName(clusterName);
			 * obj.setEntityId(String.valueOf(ret)); if
			 * (clustertype.equals("1")) {
			 * obj.setType(TypeConstant.VMWARE_CLUSTER); } else if
			 * (clustertype.equals("2")) {
			 * obj.setType(TypeConstant.KVM_CLUSTER); }
			 * entityTreeService.insertTreeNode(obj);
			 */
			flag = "1";
		}
		String result = "{\"result\":" + flag + "}";
		PrintWriter p = Struts2Utils.getResponse().getWriter();
		p.print(result);
		p.close();
		return null;
	}

	/**
	 * 
	 * @Title: conCluster
	 * @Description: 配置集群
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2012-9-12
	 */
	public String conCluster() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String entityId = request.getParameter("id");// 得到节点的entityId,即集群Id
		String name = request.getParameter("name");
		String dcName = request.getParameter("dcName");// 集群所在数据中心名称
		ClusterObj obj = new ClusterObj();
		obj.setC_uuid(entityId);
		obj = clusterService.queryByObj(obj);
		String ha = obj.getHastate();
		String drs = obj.getDrsstate();
		request.setAttribute("id", entityId);
		request.setAttribute("name", name);
		request.setAttribute("dcName", dcName);
		request.setAttribute("ha", ha);
		request.setAttribute("drs", drs);
		return "con_Cluster";
	}

	/**
	 * 
	 * @Title: saveConCluster
	 * @Description: 保存配置集群
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2012-9-12
	 */
	public String saveConCluster() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");// 此为c_uuid 即code，唯一标识
		String hastate = request.getParameter("hastate");
		String drsstate = request.getParameter("drsstate");
		String name = request.getParameter("name");
		String dcName = request.getParameter("dcName");// 集群所在数据中心名称
		ClusterObj obj = new ClusterObj();
		obj.setDcName(dcName);
		obj.setName(name);
		// obj.setId(Integer.parseInt(id));
		obj.setC_uuid(id);
		obj.setHastate(hastate);
		obj.setDrsstate(drsstate);
		int ret = clusterService.updateByObj(obj);
		String json = "{\"result\":" + String.valueOf(ret) + "}";
		PrintWriter p = Struts2Utils.getResponse().getWriter();
		p.print(json);
		p.close();
		return null;
	}

	/**
	 * @Title: delCluster
	 * @Description: 删除集群
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2012-9-12
	 */
	public String delCluster() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");// 节点Id
		String entityId = request.getParameter("entityId");// 得到
		// entityId,即集群code,唯一标识
		String name = request.getParameter("name");
		String dcName = request.getParameter("dcName");
		ClusterObj obj = new ClusterObj();
		obj.setName(name);
		obj.setDcName(dcName);
		obj.setC_uuid(entityId);
		String ret = clusterService.deleteByObj(obj);
		JSONObject json = JSONObject.fromObject(ret);
		if (json.get("result").equals("1")) {
			EntityTreeObj eObj = new EntityTreeObj();
			eObj.setId(Integer.parseInt(id));
			entityTreeService.delTreeNode(eObj);
		}
		PrintWriter p = Struts2Utils.getResponse().getWriter();
		p.print(json);
		p.close();
		return null;
	}

	/**
	 * 
	 * @Title: configHA
	 * @Description: 进入配置集群HA功能页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 19, 2013 2:45:09 PM
	 */
	public String goConfigHA() {
		if (theForm == null) {
			theForm = new ClusterForm();
		}

		EntityTreeObj obj = new EntityTreeObj();
		obj.setType(TypeConstant.VMWARE_CLUSTER);
		List<EntityTreeObj> clusterList = entityTreeService.queryForTree(obj);
		String code = clusterList.get(0).getEntityId();
		// please change ,this is wrong --note by duangh
		String param = "/vmware/domain/synVCenterTemp/[TYPE:CLUSTER].[CODE:" + code
				+ "].[FILTER:+HADRS+]/";
		configResult = InvokeUtil.invoke(param);
		JSONArray array = JSONArray.fromObject(configResult);
		List<JSONObject> jsonO = JSONArray.toList(array, JSONObject.class);
		for (JSONObject js : jsonO) {
			// Map map = new HashMap();
			// map.put("vmotionRate", js.get("vmotionRate"));
			// map.put("enableDRS", js.get("enableDRS"));
			// map.put("vmBehavior", js.get("vmBehavior"));
			// map.put("enableHA", js.get("enableHA"));
			// map.put("hostMonitoring", js.get("hostMonitoring"));
			// map.put("numFailover", js.get("numFailover"));
			// map.put("enableAdmissionControl",
			// js.get("enableAdmissionControl"));
			// String numHostGroup = (String) js.get("numHostGroup");
			// clusterMap.put(code, map);
			// 同步DRS组
			theForm.setHastate((String) js.get("enableHA"));
			theForm.setHost_monitor((String) js.get("hostMonitoring"));// 是否启用主机监控
			theForm.setState((String) js.get("enableAdmissionControl"));// 是否启用接入控制
			String strategy = String.valueOf(js.get("failover"));
			theForm.setAdmission(strategy);
			if ("1".equals(strategy)) {
				theForm.setNum(String.valueOf(js.get("numFailover")));
			} else if ("2".equals(strategy)) {
				theForm.setCpu(String.valueOf(js.get("cpuFailoverPercent")));
				theForm.setMem(String.valueOf(js.get("memoryFailoverPercent")));
			} else {
				theForm.setHost((String) js.get("failoverHosts"));
			}
		}
		theForm.setResultList(clusterList);
		obj.setType(TypeConstant.VMWARE_HOST);

		// theForm.setAdmission(admission)
		List<EntityTreeObj> hostList = entityTreeService.queryForTree(obj);
		theForm.setVmList(hostList);
		return "cluster_ha";
	}

	/**
	 * 
	 * @Title: selectHost
	 * @Description: 配置集群HA选择主机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 25, 2013 1:42:31 PM
	 */
	public String selectHost() {
		if (theForm == null) {
			theForm = new ClusterForm();
		}

		EntityTreeObj en = new EntityTreeObj();
		en.setType(TypeConstant.VMWARE_CLUSTER);
		List<EntityTreeObj> clusterList = entityTreeService.queryForTree(en);
		String code = clusterList.get(0).getEntityId();
		// please change ,this is wrong --note by duangh
		String param = "/vmware/domain/synVCenterTemp/[TYPE:CLUSTER].[CODE:" + code
				+ "].[FILTER:+HADRS+]/";
		configResult = InvokeUtil.invoke(param);
		JSONArray array = JSONArray.fromObject(configResult);
		List<JSONObject> jsonO = JSONArray.toList(array, JSONObject.class);
		for (JSONObject js : jsonO) {
			String host = (String) js.get("failoverHosts");
			EntityTreeObj eo = new EntityTreeObj();
			eo.setEntityId(host);
			if (host != null && !"".equals(host)) {
				List selectList = entityTreeService.queryForTree(eo);
				theForm.setForSelectList(selectList);
				eo.setType("1");
				List hostList = entityTreeService.getClusterFailHost(eo);
				theForm.setVmList(hostList);
			} else {
				theForm.setForSelectList(new ArrayList<EntityTreeObj>());
				EntityTreeObj obj = new EntityTreeObj();
				obj.setType(TypeConstant.VMWARE_HOST);
				List<EntityTreeObj> hostList = entityTreeService.queryForTree(obj);
				theForm.setVmList(hostList);
			}

		}
		return "host";
	}

	/**
	 * 
	 * @Title: configHA
	 * @Description: 配置集群HA功能
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 20, 2013 9:11:04 AM
	 */
	public String configHA() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String cluster_code = request.getParameter("cluster_code");
		String host_monitor = request.getParameter("host_monitor");// 0.不启用1.启用
		String state = request.getParameter("state");// 1.启用2.禁用
		String admission = request.getParameter("admission");// 1.集群允许的主机数目2.故障切换容量
		String host_num = request.getParameter("host_num");
		String cpu = request.getParameter("cpu");
		String mem = request.getParameter("mem");
		String host_code = request.getParameter("host_code");
		String hastate = request.getParameter("hastate");
		boolean host_flag = "1".equals(host_monitor) ? true : false;
		boolean ha_flag = "1".equals(hastate) ? true : false;
		String param = "/vmware/cluster/edit/[clusterCode:" + cluster_code + "].[enableHA:"
				+ ha_flag + "].[enableDRS:" + true + "].[hostMonitoring:" + host_flag
				+ "].[enableAdmissionControl:" + true + "].[";
		if (admission.equals("1")) {
			param = param + "numFailover:" + host_num + "]/";
		} else if (admission.equals("2")) {
			param = param + "cpuFailoverPercent:" + cpu + "].[memoryFailoverPercent:" + mem + "]/";
		} else {
			param = param + "failoverHosts:" + host_code + "]/";
		}
		String result = InvokeUtil.invoke(param);
		Map ps = ParamParser.makeup(result);
		if (((String) ps.get("responseCode")).equals("1")) {
			result = "{\"responseCode\":\"1\"}";
		} else {
			result = "{\"responseCode\":\"-1\"}";
		}
		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(result);
		// out.close();
		PrintWriterOut.printWirter(response, result);
		return null;
	}

	/**
	 * 
	 * @Title: goConfigDRS
	 * @Description: 进入配置集群DRS页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 20, 2013 11:15:35 AM
	 */
	public String goConfigDRS() {
		if (theForm == null) {
			theForm = new ClusterForm();
		}
		EntityTreeObj obj = new EntityTreeObj();
		obj.setType(TypeConstant.VMWARE_CLUSTER);
		List<EntityTreeObj> clusterList = entityTreeService.queryForTree(obj);
		String code = clusterList.get(0).getEntityId();
		// please change ,this is wrong --note by duangh
		String param = "/vmware/domain/synVCenterTemp/[TYPE:CLUSTER].[CODE:" + code
				+ "].[FILTER:+HADRS+]/";
		configResult = InvokeUtil.invoke(param);
		JSONArray array = JSONArray.fromObject(configResult);
		List<JSONObject> jsonO = JSONArray.toList(array, JSONObject.class);

		for (JSONObject js : jsonO) {
			theForm.setDrsstate((String) js.get("enableDRS"));
			String vmBehavior = (String) js.get("vmBehavior");
			theForm.setLevel(vmBehavior);
			theForm.setPriority(String.valueOf(js.get("vmotionRate")));
		}
		theForm.setResultList(clusterList);
		return "cluster_drs";
	}

	/**
	 * 
	 * @Title: configDRS
	 * @Description: 配置集群DRS
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 20, 2013 1:47:02 PM
	 */
	public String configDRS() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String level = request.getParameter("level");
		String priority = request.getParameter("priority");
		String cluster_code = request.getParameter("cluster_code");
		String drsstate = request.getParameter("drsstate");
		boolean drs_flag = "1".equals(drsstate) ? true : false;
		if (level.equals("1")) {
			level = "manual";
		} else if (level.equals("2")) {
			level = "partiallyAutomated";
		} else {
			level = "fullyAutomated";
		}
		String param = "";
		if (!priority.equals("0")) {
			param = "/vmware/cluster/edit/[enableDRS:" + drs_flag + "].[clusterCode:"
					+ cluster_code + "].[vmBehavior:" + level + "].[vmotionRate:" + priority + "]/";
		} else {
			param = "/vmware/cluster/edit/[enableDRS:" + drs_flag + "].[clusterCode:"
					+ cluster_code + "].[vmBehavior:" + level + "]/";
		}

		String result = InvokeUtil.invoke(param);
		Map ps = ParamParser.makeup(result);
		if (((String) ps.get("responseCode")).equals("1")) {
			result = "{\"responseCode\":\"1\"}";
		} else {
			result = "{\"responseCode\":\"-1\"}";
		}
		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(result);
		// out.close();
		PrintWriterOut.printWirter(response, result);
		return null;
	}

	/**
	 * 
	 * @Title: goDRSGroup
	 * @Description: 进入DRS组管理器页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 20, 2013 2:41:15 PM
	 */
	public String goDRSGroup() {
		if (theForm == null) {
			theForm = new ClusterForm();
		}
		EntityTreeObj obj = new EntityTreeObj();
		obj.setType(TypeConstant.VMWARE_CLUSTER);
		List<EntityTreeObj> clusterList = entityTreeService.queryForTree(obj);
		theForm.setResultList(clusterList);
		return "drs_group";
	}

	/**
	 * 
	 * @Title: addVMDRS
	 * @Description: 进入添加DRS组页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 20, 2013 3:11:50 PM
	 */
	public String addDRSGroup() {
		if (theForm == null) {
			theForm = new ClusterForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String type = request.getParameter("type");
		EntityTreeObj obj = new EntityTreeObj();
		obj.setType(TypeConstant.VMWARE_CLUSTER);
		List<EntityTreeObj> list = entityTreeService.queryForTree(obj);
		if (list != null || list.size() > 0) {
			theForm.setResultList(list);
		}
		obj.setType(type);
		List reList = entityTreeService.queryForTree(obj);
		if (list != null || list.size() > 0) {
			theForm.setVmList(reList);
		}
		theForm.setForSelectList(new ArrayList());
		request.setAttribute("type", type);
		return "add_group";
	}

	/**
	 * 
	 * @Title: saveDRSGroup
	 * @Description: 保存DRS组
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @createtime Mar 20, 2013 3:56:59 PM
	 */
	public String saveDRSGroup() throws UnsupportedEncodingException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String entityId = request.getParameter("str");
		String name = request.getParameter("name");
		name = URLDecoder.decode(name, "UTF-8");
		String type = request.getParameter("type");
		String cluster_code = request.getParameter("cluster_code");
		String param = "";
		if ("0".equals(type)) {// 虚拟机组
			param = "/vmware/cluster/edit/[clusterCode:" + cluster_code + "].[numVMGroup:" + 1
					+ "].[VMGroup1:" + name + "," + entityId + "]/";
		} else {// 主机组
			param = "/vmware/cluster/edit/[clusterCode:" + cluster_code + "].[numHostGroup:" + 1
					+ "].[HostGroup1:" + name + "," + entityId + "]/";
		}
		String result = InvokeUtil.invoke(param);
		Map ps = ParamParser.makeup(result);
		if (((String) ps.get("responseCode")).equals("1")) {
			String[] entityIds = entityId.split(",");

			int group_id = clusterService.queryForGroupId();
			Map map = new HashMap();
			map.put("group_id", group_id);
			map.put("name", name);
			map.put("type", type);
			map.put("cluster_code", cluster_code);
			clusterService.addDRSGroup(map);
			for (String s : entityIds) {
				EntityTreeObj obj = new EntityTreeObj();
				obj.setEntityId(s);
				obj = entityTreeService.queryTreeNode(obj);
				Map detailMap = new HashMap();
				detailMap.put("group_id", group_id);
				detailMap.put("entity_id", s);
				detailMap.put("name", obj.getName());
				clusterService.addDRSGroupDetail(detailMap);
			}
			result = "{\"responseCode\":\"1\"}";
		} else {
			result = "{\"responseCode\":\"-1\"}";
		}
		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(result);
		// out.close();
		PrintWriterOut.printWirter(response, result);
		return "redirect";
	}

	/**
	 * 
	 * @Title: listGroup
	 * @Description: 获取DRS组列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 21, 2013 10:43:05 AM
	 */
	public String listGroup() {
		if (theForm == null) {
			theForm = new ClusterForm();
		}
		// 同步DRS组，先删除所有数据库中的组，然后同步接口中的数据
		clusterService.deleteAllGroup();
		EntityTreeObj obj = new EntityTreeObj();
		obj.setType(TypeConstant.VMWARE_CLUSTER);
		List<EntityTreeObj> clusterList = entityTreeService.queryForTree(obj);
		String code = clusterList.get(0).getEntityId();
		// please change ,this is wrong --note by duangh
		String param = "/vmware/domain/synVCenterTemp/[TYPE:CLUSTER].[CODE:" + code
				+ "].[FILTER:+HADRS+]/";
		configResult = InvokeUtil.invoke(param);
		JSONArray array = JSONArray.fromObject(configResult);
		List<JSONObject> jsonO = JSONArray.toList(array, JSONObject.class);
		for (JSONObject js : jsonO) {
			if (js.get("numHostGroup") != null && !"".equals(js.get("numHostGroup"))) {
				int hostGroupNum = Integer.parseInt(js.get("numHostGroup").toString());

				if (hostGroupNum > 0) {
					for (int i = 1; i <= hostGroupNum; i++) {
						String para = "HostGroup" + i;
						String info = (String) js.get(para);
						String hostCodes[] = info.split(",");
						int group_id = clusterService.queryForGroupId();
						Map map = new HashMap();
						map.put("group_id", group_id);
						map.put("name", hostCodes[0]);
						map.put("type", "1");
						map.put("cluster_code", code);
						clusterService.addDRSGroup(map);
						for (int j = 1; j <= hostCodes.length - 1; j++) {
							Map detailMap = new HashMap();
							detailMap.put("group_id", group_id);
							detailMap.put("entity_id", hostCodes[j]);
							EntityTreeObj en = new EntityTreeObj();
							en.setEntityId(hostCodes[j]);
							en = entityTreeService.queryTreeNode(en);
							detailMap.put("name", en.getName());
							clusterService.addDRSGroupDetail(detailMap);
						}
					}
				}
			}
			if (js.get("numVMGroup") != null && !"".equals(js.get("numVMGroup"))) {
				int vmGroupNum = Integer.parseInt(js.get("numVMGroup").toString());
				if (vmGroupNum > 0) {
					for (int i = 1; i <= vmGroupNum; i++) {
						String para = "VMGroup" + i;
						String info = (String) js.get(para);
						String hostCodes[] = info.split(",");
						int group_id = clusterService.queryForGroupId();
						Map map = new HashMap();
						map.put("group_id", group_id);
						map.put("name", hostCodes[0]);
						map.put("type", "0");
						map.put("cluster_code", code);
						clusterService.addDRSGroup(map);
						for (int j = 1; j <= hostCodes.length - 1; j++) {
							Map detailMap = new HashMap();
							detailMap.put("group_id", group_id);
							detailMap.put("entity_id", hostCodes[j]);
							EntityTreeObj en = new EntityTreeObj();
							en.setEntityId(hostCodes[j]);
							en = entityTreeService.queryTreeNode(en);
							detailMap.put("name", en.getName());
							clusterService.addDRSGroupDetail(detailMap);
						}
					}
				}
			}
		}
		ClusterObj cObj = new ClusterObj();
		cObj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		List groupList = clusterService.queryGroupList(cObj);
		if (groupList != null) {
			theForm.setGroupList(groupList);
		} else {
			theForm.setGroupList(new ArrayList<ClusterObj>());
		}
		return "list_group";
	}

	/**
	 * 
	 * @Title: validateGroupName
	 * @Description: 验证DRS组名的唯一性
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 25, 2013 3:17:37 PM
	 */
	public String validateGroupName() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String result = "";
		String name = request.getParameter("name");
		ClusterObj obj = new ClusterObj();
		obj.setName(name);
		List list = clusterService.queryGroupList(obj);
		if (list != null && list.size() > 0) {
			result = "{\"responseCode\":\"-1\"}";
		} else {
			result = "{\"responseCode\":\"1\"}";
		}
		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(result);
		// out.close();
		PrintWriterOut.printWirter(response, result);
		return null;
	}

	/**
	 * 
	 * @Title: delGroup
	 * @Description: 删除DRS组（支持多项删除）
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @createtime Mar 25, 2013 4:00:18 PM
	 */
	public String delGroup() throws UnsupportedEncodingException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		ClusterObj obj = new ClusterObj();
		String group_id = request.getParameter("group_id");
		String code = request.getParameter("code");
		String[] group_ids = group_id.split(",");
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		name = URLDecoder.decode(name, "utf-8");
		// String[] codes = code.split(",");

		String param = "/vmware/cluster/edit/[clusterCode:" + code + "].[enableDRS:TRUE].";
		if ("0".equals(type)) {
			param += "[opVMGroup:DELETE].[deleteVMGroups:" + name.substring(0, name.length() - 1)
					+ "]/";
		} else {
			param += "[opHostGroup:DELETE].[deleteHostGroups:"
					+ name.substring(0, name.length() - 1) + "]/";
		}
		String result = InvokeUtil.invoke(param);
		Map resultMap = ParamParser.makeup(result);
		if (resultMap.get("responseCode").equals("1")) {
			for (int i = 0; i <= group_ids.length - 1; i++) {
				Map map = new HashMap();
				map.put("group_id", group_ids[i]);
				clusterService.deleteGroupDetail(map);
				clusterService.deleteGroup(map);
			}
			result = "{\"responseCode\":\"1\"}";
		} else {
			result = "{\"responseCode\":\"-1\"}";
		}
		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(result);
		// out.close();
		PrintWriterOut.printWirter(response, result);
		return null;

	}

	/**
	 * 
	 * @Title: listGroupMember
	 * @Description: 查看组成员
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 2, 2013 3:31:55 PM
	 */
	public String listGroupMember() {
		if (theForm == null) {
			theForm = new ClusterForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String group_id = request.getParameter("group_id");
		String type = request.getParameter("type");
		Map map = new HashMap();
		map.put("group_id", group_id);
		ClusterObj cObj = new ClusterObj();
		cObj.setGroup_id(group_id);
		List<ClusterObj> group = clusterService.queryGroupList(cObj);
		theForm.setName(group.get(0).getGroup_name());
		List<ClusterObj> selectList = clusterService.listGroupMember(map);
		theForm.setForSelectList(selectList);
		List reList = new ArrayList();
		EntityTreeObj obj = new EntityTreeObj();
		obj.setType(type);
		obj.setGroup_id(group_id);
		reList = entityTreeService.getClusterFailHost(obj);

		if (reList != null && reList.size() > 0) {
			theForm.setVmList(reList);
		} else {
			theForm.setVmList(new ArrayList<EntityTreeObj>());
		}
		request.setAttribute("type", type);
		EntityTreeObj en = new EntityTreeObj();
		en.setType(TypeConstant.VMWARE_CLUSTER);
		List resultList = entityTreeService.queryForTree(en);
		theForm.setResultList(resultList);
		return "edit";
	}

	/**
	 * 
	 * @Title: saveDRSGroup
	 * @Description: 保存DRS组
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @createtime Mar 20, 2013 3:56:59 PM
	 */
	public String saveEditDRSGroup() throws UnsupportedEncodingException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String entityId = request.getParameter("str");
		String name = request.getParameter("name");
		name = URLDecoder.decode(name, "utf-8");
		String type = request.getParameter("type");
		String cluster_code = request.getParameter("cluster_code");
		String param = "";
		if ("0".equals(type)) {// 虚拟机组
			param = "/vmware/cluster/edit/[clusterCode:" + cluster_code
					+ "].[opVMGroup:UPDATE].[numVMGroup:" + 1 + "].[VMGroup1:" + name + ","
					+ entityId + "]/";
		} else {// 主机组
			param = "/vmware/cluster/edit/[clusterCode:" + cluster_code
					+ "].[opHostGroup:UPDATE].[numHostGroup:" + 1 + "].[HostGroup1:" + name + ","
					+ entityId + "]/";
		}
		String result = InvokeUtil.invoke(param);
		Map ps = ParamParser.makeup(result);

		if (((String) ps.get("responseCode")).equals("1")) {
			/*
			 * String[] entityIds = entityId.split(",");
			 * 
			 * int group_id = clusterService.queryForGroupId(); Map map = new
			 * HashMap(); map.put("group_id", group_id); map.put("name", name);
			 * map.put("type", type); map.put("cluster_code", cluster_code);
			 * clusterService.addDRSGroup(map); for (String s : entityIds) {
			 * EntityTreeObj obj = new EntityTreeObj(); obj.setEntityId(s); obj
			 * = entityTreeService.queryTreeNode(obj); Map detailMap = new
			 * HashMap(); detailMap.put("group_id", group_id);
			 * detailMap.put("entity_id", s); detailMap.put("name",
			 * obj.getName()); clusterService.addDRSGroupDetail(detailMap);
			 */
			result = "{\"responseCode\":\"1\"}";
		} else {
			result = "{\"responseCode\":\"-1\"}";
		}
		// out = response.getWriter();
		// out.println(result);
		// out.close();
		PrintWriterOut.printWirter(response, result);
		return "redirect";
	}
}
