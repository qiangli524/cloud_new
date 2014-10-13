package com.sitech.basd.sxcloud.cloud.web.sxworkorder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import rabbitmq.QueueDefind;

import com.sitech.basd.cloud3.domain.departproject.DepartProjectObj;
import com.sitech.basd.cloud3.service.departproject.DepartProjectService;
import com.sitech.basd.cloud3.service.departproject.RelationService;
import com.sitech.basd.resource.domain.template.TemManObj;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.template.TemManService;
import com.sitech.basd.resource.service.united.UnitedTreeService;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.sxworkorder.OrderTaskObj;
import com.sitech.basd.sxcloud.cloud.domain.sxworkorder.WorkOrderObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.cloud.service.net.IpService;
import com.sitech.basd.sxcloud.cloud.service.net.NetService;
import com.sitech.basd.sxcloud.cloud.service.sxworkorder.WorkOrderService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.yicloud.domain.busisystem.BusiSystemObj;
import com.sitech.basd.yicloud.domain.cluster.ClusterObj;
import com.sitech.basd.yicloud.service.busisystem.BusiSystemService;
import com.sitech.basd.yicloud.service.cluster.ClusterService;
import com.sitech.ssd.sx.AddWorkLoadsObj;
import com.sitech.ssd.sx.OrderForCreate;
import com.sitech.ssd.sx.TaskForCreate;
import com.sitech.ssd.sx.VmHost;
import com.sitech.ssd.sx.comstant.VmTypeConstant;
import com.sitech.utils.exception.RabbitMQException;
import com.sitech.utils.rabbitmq.RabbitMQUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.utils.vmname.RandomNameForVM;

@SuppressWarnings("all")
@Controller("sxworkOrderAction")
@Scope("prototype")
public class WorkOrderAction extends BaseAction {

	@Autowired
	private WorkOrderService workOrderService;

	@Autowired
	private DepartProjectService departProjectService;

	@Autowired
	private TemManService temManService;

	@Autowired
	private NetService netService;

	@Autowired
	private ClusterService clusterService;

	@Autowired
	private IpService ipService;

	@Autowired
	private RelationService relationService;

	@Autowired
	private VMHostService vmHostService;

	@Autowired
	private UnitedTreeService unitedTreeService;

	@Autowired
	private RabbitMQUtil rabbitmqUtil;

	@Autowired
	private BusiSystemService busiSystemService;
	@Autowired
	private TbGlobalConfigService tbGlobalConfigService;

	private WorkOrderObj workOrderObj;

	private OrderTaskObj orderTaskObj;

	private List<WorkOrderObj> resultList;

	private List<OrderTaskObj> orderTaskList;

	private List<DepartProjectObj> projectList;

	private List<TemManObj> temList;

	private List<TbCloud2IpInfoObj> ipList;

	private List<VMHostObj> vmhostList;

	private String net_id;

	private String oper;// 操作

	private Integer type;// 工单类型

	private String uuid;

	private String wrid;

	private String projectid;

	private String dealState;

	public String getDealState() {
		return dealState;
	}

	public void setDealState(String dealState) {
		this.dealState = dealState;
	}

	private Integer state;

	private Double srsize;

	private String temid;

	private Integer wstat;

	private Integer cpu;

	private Integer memsize;

	private List<UnitedTreeObj> domainList;// 网络域集合

	private String domainid;

	private Integer flag = 1;

	private String vlainid;

	private TbCloud2IpInfoObj ipInfoObj;

	private TemManObj temManObj;

	private DepartProjectObj projectObj;

	private VMHostObj vmHostObj;

	private String entityname;// 回收时展示的资源名称

	private BusiSystemObj busiSystemObj;

	private List<BusiSystemObj> busiList;

	private String busisystemid;

	private String ipaddress;

	private String userName;// 工单申请人：
	private String cameForm;// 工单来源
	private String requestType;// 需求类型
	private String addReason;// 新增原因
	private String expansionReason;// 现网扩容原因
	private String currSituation;// 现网运行情况
	private String isInPlan;// 是否计划内
	private String isInvest;// 是否投资类

	public List<OrderTaskObj> getOrderTaskList() {
		return orderTaskList;
	}

	public void setOrderTaskList(List<OrderTaskObj> orderTaskList) {
		this.orderTaskList = orderTaskList;
	}

	public BusiSystemService getBusiSystemService() {
		return busiSystemService;
	}

	public void setBusiSystemService(BusiSystemService busiSystemService) {
		this.busiSystemService = busiSystemService;
	}

	public OrderTaskObj getOrderTaskObj() {
		return orderTaskObj;
	}

	public void setOrderTaskObj(OrderTaskObj orderTaskObj) {
		this.orderTaskObj = orderTaskObj;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getAddReason() {
		return addReason;
	}

	public void setAddReason(String addReason) {
		this.addReason = addReason;
	}

	public String getExpansionReason() {
		return expansionReason;
	}

	public void setExpansionReason(String expansionReason) {
		this.expansionReason = expansionReason;
	}

	public String getCurrSituation() {
		return currSituation;
	}

	public void setCurrSituation(String currSituation) {
		this.currSituation = currSituation;
	}

	public String getIsInPlan() {
		return isInPlan;
	}

	public void setIsInPlan(String isInPlan) {
		isInPlan = isInPlan;
	}

	public String getIsInvest() {
		return isInvest;
	}

	public void setIsInvest(String isInvest) {
		isInvest = isInvest;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getBusisystemid() {
		return busisystemid;
	}

	public void setBusisystemid(String busisystemid) {
		this.busisystemid = busisystemid;
	}

	public List<BusiSystemObj> getBusiList() {
		return busiList;
	}

	public void setBusiList(List<BusiSystemObj> busiList) {
		this.busiList = busiList;
	}

	public BusiSystemObj getBusiSystemObj() {
		return busiSystemObj;
	}

	public void setBusiSystemObj(BusiSystemObj busiSystemObj) {
		this.busiSystemObj = busiSystemObj;
	}

	public String getEntityname() {
		return entityname;
	}

	public void setEntityname(String entityname) {
		this.entityname = entityname;
	}

	public VMHostObj getVmHostObj() {
		return vmHostObj;
	}

	public void setVmHostObj(VMHostObj vmHostObj) {
		this.vmHostObj = vmHostObj;
	}

	public DepartProjectObj getProjectObj() {
		return projectObj;
	}

	public void setProjectObj(DepartProjectObj projectObj) {
		this.projectObj = projectObj;
	}

	public TemManObj getTemManObj() {
		return temManObj;
	}

	public void setTemManObj(TemManObj temManObj) {
		this.temManObj = temManObj;
	}

	public TbCloud2IpInfoObj getIpInfoObj() {
		return ipInfoObj;
	}

	public void setIpInfoObj(TbCloud2IpInfoObj ipInfoObj) {
		this.ipInfoObj = ipInfoObj;
	}

	public String getVlainid() {
		return vlainid;
	}

	public void setVlainid(String vlainid) {
		this.vlainid = vlainid;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getDomainid() {
		return domainid;
	}

	public void setDomainid(String domainid) {
		this.domainid = domainid;
	}

	public List<UnitedTreeObj> getDomainList() {
		return domainList;
	}

	public void setDomainList(List<UnitedTreeObj> domainList) {
		this.domainList = domainList;
	}

	public Integer getCpu() {
		return cpu;
	}

	public void setCpu(Integer cpu) {
		this.cpu = cpu;
	}

	public Integer getMemsize() {
		return memsize;
	}

	public void setMemsize(Integer memsize) {
		this.memsize = memsize;
	}

	public Integer getWstat() {
		return wstat;
	}

	public void setWstat(Integer wstat) {
		this.wstat = wstat;
	}

	public String getTemid() {
		return temid;
	}

	public void setTemid(String temid) {
		this.temid = temid;
	}

	public Double getSrsize() {
		return srsize;
	}

	public void setSrsize(Double srsize) {
		this.srsize = srsize;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public List<VMHostObj> getVmhostList() {
		return vmhostList;
	}

	public void setVmhostList(List<VMHostObj> vmhostList) {
		this.vmhostList = vmhostList;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public String getWrid() {
		return wrid;
	}

	public void setWrid(String wrid) {
		this.wrid = wrid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public List<TbCloud2IpInfoObj> getIpList() {
		return ipList;
	}

	public void setIpList(List<TbCloud2IpInfoObj> ipList) {
		this.ipList = ipList;
	}

	public String getNet_id() {
		return net_id;
	}

	public void setNet_id(String net_id) {
		this.net_id = net_id;
	}

	public List<TemManObj> getTemList() {
		return temList;
	}

	public void setTemList(List<TemManObj> temList) {
		this.temList = temList;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<DepartProjectObj> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<DepartProjectObj> projectList) {
		this.projectList = projectList;
	}

	public WorkOrderObj getWorkOrderObj() {
		return workOrderObj;
	}

	public void setWorkOrderObj(WorkOrderObj workOrderObj) {
		this.workOrderObj = workOrderObj;
	}

	public List<WorkOrderObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<WorkOrderObj> resultList) {
		this.resultList = resultList;
	}

	/**
	 * @Title: listWorkOrder
	 * @Description: 展示工单
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-8 下午12:58:30
	 */
	public String listWorkOrder() {
		if (workOrderObj == null) {
			workOrderObj = new WorkOrderObj();
		}
		// if (workOrderObj.getTYPE() != null && -1 == workOrderObj.getTYPE()) {
		// workOrderObj.setTYPE(null);
		// }
		// if (workOrderObj.getPROJECT_ID() != null
		// && "-1".equals(workOrderObj.getPROJECT_ID())) {
		// workOrderObj.setPROJECT_ID(null);
		// }
		// if (workOrderObj.getCAMEFROM() != null
		// && "-1".equals(workOrderObj.getCAMEFROM())) {
		// workOrderObj.setCAMEFROM(null);
		// }
		// if (workOrderObj.getWSTAT() != null && -1 == workOrderObj.getWSTAT())
		// {
		// workOrderObj.setWSTAT(null);
		// }
		workOrderObj.setPagination(this.getPaginater().initPagination(request));

		// String account = session.get("account").toString();
		// String userId = session.get("id").toString();
		// 全局配置里边配置了几个用户的权限
		// TbGlobalConfigObj global = new TbGlobalConfigObj();
		// global.setKEY("user_auth");
		// global = tbGlobalConfigService.queryByObj(global);
		// String[] users = new String[] { "" };
		// if (global != null) {
		// users = global.getVALUE().split(",");
		// }
		//
		// if (!"1".equals(userId)) {// 对于admin用户，不需要分配权限
		// // 对于普通登录用户，需要进行权限控制
		// int flag = 0;
		// for (int i = 0; i < users.length; i++) {
		// if (account.equals(users[i])) {
		// flag = 1;
		// }
		// }
		// if (flag != 1) {
		// // workOrderObj.setPROJECT_USER_ID(userId);
		// }
		//
		// }

		resultList = workOrderService.queryWorkOrderList(workOrderObj);
		// projectList = workOrderService.queryProjectList();
		return "sxlistWorkOrder";
	}

	/**
	 * @Title: viewDealedWorkOrder
	 * @Description: 查看已经处理的任务
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-12 下午8:18:53
	 */
	public String viewDealedWorkOrder() {
		if (workOrderObj == null) {
			workOrderObj = new WorkOrderObj();
		}
		// workOrderObj.setWSTAT(1);
		// if (workOrderObj.getTYPE() != null && -1 == workOrderObj.getTYPE()) {
		// workOrderObj.setTYPE(null);
		// }
		// if (workOrderObj.getPROJECT_ID() != null
		// && "-1".equals(workOrderObj.getPROJECT_ID())) {
		// workOrderObj.setPROJECT_ID(null);
		// }
		// workOrderObj.setPagination(this.getPaginater().initPagination(request));
		//
		// String account = session.get("account").toString();
		// if (!"admin".equals(account)) {
		// workOrderObj.setPROJECT_USER_ID(account);
		// }

		resultList = workOrderService.queryWorkOrderList(workOrderObj);
		projectList = workOrderService.queryProjectList();

		return "sxviewDealedWorkOrder";
	}

	/**
	 * @Title: addWorkOrder
	 * @Description: 添加工单
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-8 下午12:59:19
	 */
	public String addWorkOrder() {
		return "sxaddw";
	}

	/**
	 * @Title: deleteWorkOrder
	 * @Description: 删除工单记录
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-12 下午8:04:06
	 */
	public void deleteWorkOrder() {
		if (workOrderObj == null) {
			workOrderObj = new WorkOrderObj();
		}
		workOrderObj.setUuid(uuid);
		int ret = -1;
		ret = workOrderService.deleteWorkOrderByObj(workOrderObj);
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(ret);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: selectProject
	 * @Description: 添加工单时选择项目列表
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-8 下午1:00:25
	 */
	public String selectProject() {
		if (projectObj == null) {
			projectObj = new DepartProjectObj();
		}
		String account = session.get("account").toString();
		if (!"admin".equals(account)) {
			projectObj.setPROJECT_LEADER(account);
		}
		projectObj.setPagination(this.getPaginater().initPagination(request));
		projectList = departProjectService.queryForList(projectObj);
		return "sxlistpro";
	}

	/**
	 * @Title: saveWorkOrder
	 * @Description: 添加之后保存工单信息
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-8 下午1:02:12
	 */

	public void saveWorkOrder() {
		WorkOrderObj obj = new WorkOrderObj();
		int ret = -1;
		if ("add".equals(oper)) {

			obj.setUuid(RandomUUID.getUuid());
			obj.setType(type);
			// obj.setUSERNAME(request.get);
			obj.setAddReason(addReason);
			obj.setCameFrom("1"); // 工单来自云平台
			obj.setCurrSituation(currSituation);
			obj.setExpansionReason(expansionReason);
			obj.setIsInPlan(isInPlan);
			obj.setIsInvest(isInvest);
			obj.setRequestType(requestType);
			obj.setType(type);
			ret = workOrderService.insertWorkOrderTable(obj);
		}
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(ret);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// /**
	// * @Title: saveWorkOrder
	// * @Description: 消息队列方式 // 改成入库方式
	// * @param
	// * @return void
	// * @throws
	// * @author lipengpeng
	// * @version 1.0
	// * @createtime 2013-9-22 下午8:16:19
	// */
	// public void saveWorkOrder() {
	// int ret = -1;
	// if ("add".equals(oper)) {
	// JSONObject jo = new JSONObject();
	// String work_order_id = new SimpleDateFormat("yyyyMMddHHmmss")
	// .format(new Date());
	// if (0 == type) {
	// jo.put("type", "work_order");
	// jo.put("work_order_id", work_order_id);
	// jo.put("project_user_id", workOrderObj.getPROJECT_USER_ID());
	// jo.put("project_id", workOrderObj.getPROJECT_ID());
	// jo.put("camefrom", "1");
	// jo.put("busiid", workOrderObj.getBUSISYSTEMID());
	// jo.put("resource", "");
	// } else if (2 == type) {
	// jo.put("type", "work_order_recover");
	// jo.put("work_order_id", work_order_id);
	// jo.put("project_user_id", workOrderObj.getPROJECT_USER_ID());
	// jo.put("project_id", workOrderObj.getPROJECT_ID());
	// jo.put("camefrom", "1");
	// jo.put("vm", "");
	// }
	// ret = this.sendOrderToQuene(jo);
	// }
	// try {
	// PrintWriter pw = response.getWriter();
	// pw.print(ret);
	// pw.flush();
	// pw.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * @Title: addResource
	 * @Description: 给工单添加vmw任务
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-8 下午1:04:30
	 */
	@SuppressWarnings("unchecked")
	public String addResource() {
		if (orderTaskObj == null) {
			orderTaskObj = new OrderTaskObj();
		}
		if (0 == type) {
			TemManObj temobj = new TemManObj();
			temobj.setType("1"); // vmw模板
			// temobj.setPagination(this.getPaginater().initPagination(request));
			List templetList = temManService.queryForList(temobj);
			orderTaskObj.setTempleteList(templetList);

			TbCloud2NetInfoObj netobj = new TbCloud2NetInfoObj();
			netobj.setNET_TYPE("3"); // vmw网络池
			List netList = netService.queryForListByObj(netobj);
			orderTaskObj.setNetList(netList);

			ClusterObj clusterobj = new ClusterObj();
			clusterobj.setType("1"); // vmw 集群
			List clusterList = clusterService.queryListByObj(clusterobj);
			orderTaskObj.setClusterList(clusterList);
			orderTaskObj.setCpuCount("2");
			orderTaskObj.setMemory("2048");
			orderTaskObj.setStorage("50");
			orderTaskObj.setExpandStorage("0");
			return "sxaddr";// 工单类型创建
		} else if (2 == type) {
			return "sxaddrr";// 工单类型回收
		}
		return null;
	}

	/**
	 * @Title: addSCEResource
	 * @Description: 给工单添加sce任务
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-9-8 下午1:04:30
	 */
	@SuppressWarnings("unchecked")
	public String addSceResource() {
		if (orderTaskObj == null) {
			orderTaskObj = new OrderTaskObj();
		}
		if (0 == type) {
			TemManObj temobj = new TemManObj();
			temobj.setType("4"); // sce模板
			List templetList = temManService.queryForList(temobj);
			orderTaskObj.setTempleteList(templetList);

			TbCloud2NetInfoObj netobj = new TbCloud2NetInfoObj();
			netobj.setNET_TYPE("1"); // sce网络池
			List netList = netService.queryForListByObj(netobj);
			orderTaskObj.setNetList(netList);
			// UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
			// unitedTreeObj.setType(UnitedConstant.SUBDOMIAN);
			// unitedTreeObj.setVtype(UnitedConstant.NETWORKS);
			// try {
			// domainList = unitedTreeService.queryForTreeList(unitedTreeObj);
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
			// orderTaskObj.setWorkOrderId(request.getAttribute("workOrderId")
			// .toString());
			return "sxaddscer";// 工单类型:创建
		} else if (2 == type) {
			return "sxaddrr";// 工单类型:回收
		}
		return null;
	}

	/**
	 * @Title: selectVlan
	 * @Description: 根据传过来的网络域id选择该网络域下面的vlan
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-16 上午9:08:03
	 */
	public void selectVlan() {
		List<TbCloud2NetInfoObj> netList = netService.queryForListByDomainId(domainid);
		JSONArray json = JSONArray.fromObject(netList);
		// JSONObject jo = new JSONObject();
		// for (TbCloud2NetInfoObj tbCloud2NetInfoObj : netList) {
		// jo.put("net_id", tbCloud2NetInfoObj.getNET_ID());
		// jo.put("net_name", tbCloud2NetInfoObj.getNAME());
		// json.add(jo);
		// }
		try {
			response.setCharacterEncoding("UTF-8");
			// PrintWriter pw = response.getWriter();
			// pw.print(json.toString());
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: selectBusiSystem
	 * @Description: 选择业务系统
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-24 下午5:05:45
	 */
	public String selectBusiSystem() {
		if (busiSystemObj == null) {
			busiSystemObj = new BusiSystemObj();
		}
		if (busisystemid == null || "".equals(busisystemid)) {
			busiSystemObj.setTYPE(1);
		} else {
			busiSystemObj.setTYPE(2);
			busiSystemObj.setPARENT_ID(busisystemid);
		}
		busiSystemObj.setPagination(this.getPaginater().initPagination(request));
		busiList = busiSystemService.queryForListByObj(busiSystemObj);
		return "sxlistBusi";
	}

	/**
	 * @Title: selectTemMan
	 * @Description: 模板列表，添加创建任务时供使用
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-8 下午1:16:36
	 */
	@SuppressWarnings("unchecked")
	public String selectTemMan() {
		if (temManObj == null) {
			temManObj = new TemManObj();
		}
		temManObj.setPagination(this.getPaginater().initPagination(request));
		temList = temManService.queryForList(temManObj);
		return "sxlistTem";
	}

	/**
	 * @Title: selectIp
	 * @Description: 添加创建任务时，选择ip
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-8 下午1:27:07
	 */
	public String selectIp() {
		if (ipInfoObj == null) {
			ipInfoObj = new TbCloud2IpInfoObj();
		}
		ipInfoObj.setISUSED("0");
		ipInfoObj.setNET_ID(net_id);
		ipInfoObj.setPagination(this.getPaginater().initPagination(request));
		ipList = ipService.queryForListByObj(ipInfoObj);
		return "sxlistip";
	}

	/**
	 * @Title: checkStore
	 * @Description: 检测存储是否满足模板要求
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-8 下午4:46:04
	 */
	@SuppressWarnings("unchecked")
	public void checkStore() {
		if (temManObj == null) {
			temManObj = new TemManObj();
		}
		temManObj.setTemplateCode(splitLine(temid)[1]);
		temManObj.setConnectId(splitLine(temid)[0]);
		temList = temManService.queryForList(temManObj);
		if (temList.size() > 0) {
			temManObj = temList.get(0);
		}
		double temsrsize = temManObj.getStore();
		int ret = -1;
		if (temsrsize <= srsize * 1024) {
			ret = 1;
		}
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(ret);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: checkResourceLeft
	 * @Description: 检测项目的剩余资源是否能够满足此创建任务
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-8 下午2:20:34
	 */
	public void checkResourceLeft() {
		DepartProjectObj departProjectObj = new DepartProjectObj();
		departProjectObj.setID(projectid);
		departProjectObj = departProjectService.queryDepartProjectOne(departProjectObj);
		int cpuall = departProjectObj.getCPU_COUNT();
		int memall = departProjectObj.getMEMORY_SIZE();
		double storeall = departProjectObj.getSTORAGE_SIZE();

		List<WorkOrderObj> wlist = workOrderService.queryUsedByProject(projectid);
		int used_cpu = 0;
		int used_mem = 0;
		double used_sr = 0;
		for (WorkOrderObj workOrderResourceObj : wlist) {
			// used_cpu += workOrderResourceObj.getCPU_NUM();
			// used_mem += workOrderResourceObj.getMEM_SIZE();
			// used_sr += workOrderResourceObj.getSR_SIZE();
		}

		wlist = workOrderService.queryByProject(projectid);
		for (WorkOrderObj workOrderResourceObj : wlist) {
			// used_cpu += workOrderResourceObj.getCPU_NUM();
			// used_mem += workOrderResourceObj.getMEM_SIZE();
			// used_sr += workOrderResourceObj.getSR_SIZE();
		}

		int leftcpu = cpuall - used_cpu;
		int leftmem = memall - used_mem;
		double leftsr = storeall - used_sr;

		int ret = -1;
		if (leftcpu >= cpu && leftmem >= memsize * 1024 && leftsr >= srsize * 1024) {
			ret = 1;
		}
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(ret);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: selectRecoverResouce
	 * @Description: 创建回收任务时，列出可供回收的资源实体
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-8 下午2:25:15
	 */
	public String selectRecoverResouce() {
		if (vmHostObj == null) {
			vmHostObj = new VMHostObj();
		}
		vmHostObj.setPROJECT_ID(projectid);
		vmHostObj.setPagination(this.getPaginater().initPagination(request));
		vmhostList = vmHostService.queryForListByPro(vmHostObj);

		// List<RelationObj> list =
		// relationService.queryForListByPro(relationObj);
		// List<String> entitylist = new ArrayList<String>();
		// for (RelationObj relationObj2 : list) {
		// entitylist.add(relationObj2.getConnectId() + "_" +
		// relationObj2.getVmuuId());
		// }
		// WorkOrderObj obj = new WorkOrderObj();
		// obj.setTYPE(2);
		// obj.setSTATE(0);// status应该为0或者1，若为3，则可以再次回收
		// obj.setSTATUS(0);
		// obj.setPROJECT_ID(projectid);
		// resultList = workOrderService.queryForWholeListByObj(obj);
		// List<String> exsitlist = new ArrayList<String>();
		// for (WorkOrderObj string : resultList) {
		// exsitlist.add(string.getENTITY_ID());
		// }
		// entitylist.removeAll(exsitlist);
		// if (vmhostList == null) {
		// vmhostList = new ArrayList<VMHostObj>();
		// }
		// for (String string : entitylist) {
		// VMHostObj vmHostObj = new VMHostObj();
		// vmHostObj.setConnectId(splitLine(string)[0]);
		// vmHostObj.setVH_UUID(splitLine(string)[1]);
		// vmHostObj = vmHostService.queryByObj(vmHostObj);
		// if (vmHostObj != null) {
		// vmhostList.add(vmHostObj);
		// }
		// }
		return "sxlistEntity";
	}

	/**
	 * @Title: deleteResource
	 * @Description: 删除工单下的任务
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-8 下午2:30:44
	 */
	public void deleteResource() {
		if (orderTaskObj == null) {
			orderTaskObj = new OrderTaskObj();
		}
		orderTaskObj.setUuid(request.getParameter("wrid").toString());
		int ret = -1;
		ret = workOrderService.deleteResourceByObj(orderTaskObj);
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(ret);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: editResource
	 * @Description: 编辑任务
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-8 下午2:40:43
	 */
	@SuppressWarnings("unchecked")
	public String editResource() {
		if (orderTaskObj == null) {
			orderTaskObj = new OrderTaskObj();
		}
		String wrid = request.getParameter("wrid");
		String orderTaskType = request.getParameter("orderTaskType");
		orderTaskObj.setUuid(wrid);
		List<OrderTaskObj> list = workOrderService.queryResourceList(orderTaskObj);
		if (list.size() > 0) {
			orderTaskObj = list.get(0);
		}

		if (0 == type) {
			if ("1".equals(orderTaskType)) {
				TemManObj temobj = new TemManObj();
				temobj.setType("1"); // vmw模板
				// temobj.setPagination(this.getPaginater()
				// .initPagination(request));
				List templetList = temManService.queryForList(temobj);
				orderTaskObj.setTempleteList(templetList);

				TbCloud2NetInfoObj netobj = new TbCloud2NetInfoObj();
				netobj.setNET_TYPE("3"); // vmw网络池
				List netList = netService.queryForListByObj(netobj);
				orderTaskObj.setNetList(netList);

				ClusterObj clusterobj = new ClusterObj();
				clusterobj.setType("1"); // vmw 集群
				List clusterList = clusterService.queryListByObj(clusterobj);
				orderTaskObj.setClusterList(clusterList);
				return "sxeditr";
			} else if ("0".equals(orderTaskType)) {
				TemManObj temobj = new TemManObj();
				temobj.setType("4"); // sce模板
				// temobj.setPagination(this.getPaginater()
				// .initPagination(request));
				List templetList = temManService.queryForList(temobj);
				orderTaskObj.setTempleteList(templetList);

				TbCloud2NetInfoObj netobj = new TbCloud2NetInfoObj();
				netobj.setNET_TYPE("1"); // sce网络池
				List netList = netService.queryForListByObj(netobj);
				orderTaskObj.setNetList(netList);

				return "sxeditscer";
			}
		} else if (2 == type) {
			entityname = vmHostObj.getVH_NAME();
			return "sxeditrr";
		}
		return null;
	}

	/**
	 * @Title: saveResource
	 * @Description: 入库
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-22 下午10:15:41
	 */
	public void saveResource() {
		int ret = -1;
		if (orderTaskObj == null) {
			orderTaskObj = new OrderTaskObj();
		}
		if ("edit".equals(oper)) {// 直接改库
			if (0 == type) {

				ret = workOrderService.updateResourceByObj(orderTaskObj);
			} else if (2 == type) {

				OrderTaskObj obj = new OrderTaskObj();
				// List<WorkOrderObj> list = workOrderService
				// .queryForWholeListByObj(obj);
				// if (list.size() > 0) {
				// obj = list.get(0);
				ret = workOrderService.updateResourceByObj(obj);
				// }
			}
		} else if ("add".equals(oper)) {
			if (0 == type) {
				orderTaskObj.setType("1"); // 任务类型。1：vmw 0：sce
				orderTaskObj.setUuid(RandomUUID.getUuid());
				orderTaskObj.setWorkOrderId(uuid);
				orderTaskObj.setHostname(orderTaskObj.getName()); // 此字段做displayname用
				ret = workOrderService.insertResource(orderTaskObj);
			} else if (2 == type) {
				// 回收资源工单
			}
		}
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(ret);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: saveSceResource
	 * @Description: 入库
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-22 下午10:15:41
	 */
	public void saveSceResource() {
		int ret = -1;
		if (orderTaskObj == null) {
			orderTaskObj = new OrderTaskObj();
		}
		if ("edit".equals(oper)) {// 直接改库
			if (0 == type) {

				ret = workOrderService.updateResourceByObj(orderTaskObj);
			} else if (2 == type) {

				// WorkOrderObj obj = new WorkOrderObj();
				// List<WorkOrderObj> list = workOrderService
				// .queryForWholeListByObj(obj);
				// if (list.size() > 0) {
				// obj = list.get(0);
				ret = workOrderService.updateResourceByObj(orderTaskObj);
				// }
			}
		} else if ("add".equals(oper)) {
			if (0 == type) {
				orderTaskObj.setType("0"); // 任务类型。1：vmw 0：sce
				orderTaskObj.setUuid(RandomUUID.getUuid());
				orderTaskObj.setWorkOrderId(uuid);
				ret = workOrderService.insertResource(orderTaskObj);
			} else if (2 == type) {
				// 回收资源工单
			}
		}
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(ret);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: toJSONForCreate
	 * @Description: 构造申请参数
	 * @param
	 * @return JSONObject
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-8 下午2:44:19
	 */
	private JSONObject toJSONForCreate(WorkOrderObj workOrderObj2, String uuid2) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid2);

		List<JSONObject> rlist = new ArrayList<JSONObject>();
		JSONObject joa = new JSONObject();
		// joa.put("cpu_num", workOrderObj2.getCPU_NUM());
		// joa.put("mem_size", workOrderObj2.getMEM_SIZE() * 1024);
		// joa.put("sr_size", workOrderObj2.getSR_SIZE() * 1024);
		// joa.put("network_id", workOrderObj2.getNETWORK_ID());
		// joa.put("template_id", workOrderObj2.getTEMPLATE_ID());
		// joa.put("vm_name", workOrderObj2.getVM_NAME());
		// joa.put("ipaddress", workOrderObj2.getIPADDRESS());
		// joa.put("template_type", workOrderObj2.getTEMPLATE_TYPE());
		rlist.add(joa);
		map.put("resource", rlist);

		JSONObject jo = JSONObject.fromObject(map);
		return jo;
	}

	/**
	 * @Title: toJSONForRecover
	 * @Description: 构造回收参数
	 * @param
	 * @return JSONObject
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-8 下午2:49:25
	 */
	private JSONObject toJSONForRecover(WorkOrderObj workOrderObj2, String uuid2) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid2);

		List<JSONObject> rlist = new ArrayList<JSONObject>();
		JSONObject joa = new JSONObject();
		// joa.put("vm_id", workOrderObj2.getENTITY_ID());
		rlist.add(joa);
		map.put("vm", rlist);
		JSONObject jo = JSONObject.fromObject(map);
		return jo;
	}

	/**
	 * @Title: listResource
	 * @Description: 展示任务
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-8 下午2:50:50
	 */
	public String listResource() {
		if (orderTaskObj == null) {
			orderTaskObj = new OrderTaskObj();
		}
		if (uuid != null && !"".equals(uuid)) {
			orderTaskObj.setWorkOrderId(request.getParameter("uuid").toString());
		}
		if (type != null && !"".equals(type)) {
			this.setType(Integer.valueOf(request.getParameter("type").toString()));
			// orderTaskObj.setType(request.getAttribute("type").toString());
		}
		orderTaskList = workOrderService.queryResourceList(orderTaskObj);
		return "sxlistResource";
	}

	/**
	 * @Title: dealByHand
	 * @Description: 手动处理失败的任务
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-8 下午2:55:34
	 */
	public void dealByHand() {
		if (orderTaskObj == null) {
			orderTaskObj = new OrderTaskObj();
		}
		int ret = -1;
		orderTaskObj.setUuid(request.getParameter("wrid"));
		orderTaskList = workOrderService.queryResourceList(orderTaskObj);
		OrderForCreate orderForCreate = orderTaskToOrderForCreate(orderTaskList);
		try {
			rabbitmqUtil.publishMessage("", QueueDefind.SX_CREATEVM_QUEUE, orderForCreate);
		} catch (RabbitMQException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// if ("1".equals(orderTaskObj.getStatus())) {
		orderTaskObj.setStatus("1"); // 处理中
		ret = workOrderService.updateResourceByObj(orderTaskObj);
		// }
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(ret);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: exeWorkOrder
	 * @Description: 执行工单
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-8 下午3:00:08
	 */
	public void exeWorkOrder() {
		if (orderTaskObj == null) {
			orderTaskObj = new OrderTaskObj();
		}
		if (workOrderObj == null) {
			workOrderObj = new WorkOrderObj();
		}
		orderTaskObj.setWorkOrderId(request.getParameter("uuid"));
		List<OrderTaskObj> taskList = workOrderService.queryResourceList(orderTaskObj);
		OrderForCreate orderForCreate = orderTaskToOrderForCreate(taskList);

		try {
			rabbitmqUtil.publishMessage("", QueueDefind.SX_CREATEVM_QUEUE, orderForCreate);
		} catch (RabbitMQException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (request.getParameter("uuid") != null && !"".equals(request.getParameter("uuid"))) {
			workOrderObj.setUuid(request.getParameter("uuid"));
		}
		workOrderObj.setDealState("1");
		int ret = workOrderService.updateWorkOrderTable(workOrderObj);
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(ret);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: sendOrderToQuene
	 * @Description: 向后台发送消息队列
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-8 下午3:03:50
	 */
	private int sendOrderToQuene(JSONObject jo) {
		try {
			rabbitmqUtil.publishMessage("", "bomc.work.order.oper", jo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	/**
	 * @Title: splitLine
	 * @Description: 下划线分割
	 * @param
	 * @return String[]
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-8 下午2:39:16
	 */
	private String[] splitLine(String templateId) {
		String[] str = templateId.split("_");
		if (str.length > 2) {
			int index = templateId.indexOf("_");
			str[0] = templateId.substring(0, index);
			str[1] = templateId.substring(index, templateId.length());
		}
		return str;
	}

	/**
	 * @Title: listWOByResource
	 * @Description: 展示工单信息用于资源概述
	 * @param
	 * @return String
	 * @throws
	 * @author yangl
	 * @version 1.0
	 * @createtime 2013-9-19
	 */
	public String listWOByResource() {
		if (workOrderObj == null) {
			workOrderObj = new WorkOrderObj();
		}
		// if (workOrderObj.getTYPE() != null && -1 == workOrderObj.getTYPE()) {
		// workOrderObj.setTYPE(null);
		// }
		// if (workOrderObj.getPROJECT_ID() != null
		// && "-1".equals(workOrderObj.getPROJECT_ID())) {
		// workOrderObj.setPROJECT_ID(null);
		// }
		// if (workOrderObj.getCAMEFROM() != null
		// && "-1".equals(workOrderObj.getCAMEFROM())) {
		// workOrderObj.setCAMEFROM(null);
		// }
		// if (workOrderObj.getWSTAT() != null && -1 == workOrderObj.getWSTAT())
		// {
		// workOrderObj.setWSTAT(null);
		// }
		// workOrderObj.setPagination(this.getPaginater().initPagination(request));
		//
		String account = session.get("account").toString();
		// if (!"admin".equals(account)) {
		// workOrderObj.setPROJECT_USER_ID(account);
		// }
		resultList = workOrderService.queryWorkOrderList(workOrderObj);
		projectList = workOrderService.queryProjectList();
		return "sxlistWOByResource";
	}

	/**
	 * @Title: randomVmName
	 * @Description: 生成虚拟机名称
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-10-16 上午9:39:04
	 */
	public void randomVmName() {
		if (temManObj == null) {
			temManObj = new TemManObj();
		}
		temManObj.setTemplateCode(splitLine(temid)[1]);
		temManObj.setConnectId(splitLine(temid)[0]);
		temList = temManService.queryForList(temManObj);
		if (temList.size() > 0) {
			temManObj = temList.get(0);
		}
		String virtualMachineName = this.getNewVmName(temManObj, busisystemid, ipaddress, domainid,
				uuid);
		try {
			response.setCharacterEncoding("UTF-8");
			// PrintWriter pw = response.getWriter();
			// pw.print(virtualMachineName);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, virtualMachineName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: getNewVmName
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-10-16 上午10:22:24
	 */
	private String getNewVmName(TemManObj temManObj, String busiid, String ipaddress,
			String domainid, String uuid) {
		try {
			String temRemark = temManObj.getRemark();

			// 获取安全域(网络域)名称规则
			UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
			unitedTreeObj.setId(domainid);
			List<UnitedTreeObj> ulist = unitedTreeService.queryForTreeList(unitedTreeObj);
			if (ulist.size() > 0) {
				unitedTreeObj = ulist.get(0);
			}
			String uname = unitedTreeObj.getName();

			// 获取业务系统名称
			BusiSystemObj busiSystemObj = new BusiSystemObj();
			busiSystemObj.setID(busiid);
			List<BusiSystemObj> busiList = busiSystemService.queryForListByObj(busiSystemObj);
			if (busiList.size() > 0) {
				busiSystemObj = busiList.get(0);
			}
			String busiName = busiSystemObj.getNAME();

			// 查询当前虚拟机该有的序号
			String subVmName = RandomNameForVM.randomSubThirdName(uname, temRemark, busiName);
			String squenceNum = this.getForthName(subVmName);

			String voName = "";
			if (ipaddress != null && !"".equals(ipaddress) && !"null".equals(ipaddress)) {
				voName = RandomNameForVM.randomName(subVmName, squenceNum, ipaddress);
			} else {
				voName = subVmName + "_" + squenceNum;
			}

			return voName;
		} catch (Exception e) {
			e.printStackTrace();
			return "sx生成虚拟机名称出错";
		}

	}

	/**
	 * @Title: getForthName
	 * @Description: 获取相同规则下的虚拟机的序号
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-25 下午2:54:03
	 */
	private String getForthName(String subVmName) throws Exception {
		try {
			String squenceNum = "";
			List<String> squList = new ArrayList<String>();

			if (vmHostObj == null) {
				vmHostObj = new VMHostObj();
			}
			vmHostObj.setVH_NAME(subVmName);
			List<VMHostObj> list = vmHostService.queryForListByObj(vmHostObj);

			if (workOrderObj == null) {
				workOrderObj = new WorkOrderObj();
			}
			// workOrderObj.setVM_NAME(subVmName);
			List<WorkOrderObj> workList = workOrderService.queryUnDealedResource(workOrderObj);

			if (list.size() == 0 && workList.size() == 0) {
				squenceNum = "001";
			} else {
				for (VMHostObj obj : list) {
					String vmname = obj.getVH_NAME();
					String[] vmnamearr = vmname.split("_");
					squList.add(vmnamearr[3]);
				}
				for (WorkOrderObj workOrderObj : workList) {
					// String vmname = workOrderObj.getVM_NAME();
					// String[] vmnamearr = vmname.split("_");
					// if (vmnamearr.length > 3) {
					// squList.add(vmnamearr[3]);
					// }
				}
				if (squList.size() > 0) {
					squenceNum = this.getSquenceNumByExsit(squList);
				} else {
					squenceNum = "001";
				}
			}
			return squenceNum;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("构造虚拟机名称获取序号时出错：" + e.getMessage() + e.getClass().getName());
		}
	}

	/**
	 * @Title: getSquenceNumByExsit
	 * @Description: 分析已存在的虚拟机的序号，生成下一个序号
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-25 下午3:21:40
	 */
	private String getSquenceNumByExsit(List<String> squList) throws Exception {
		try {
			String squenceNum = "";
			List<Integer> list = new ArrayList<Integer>();
			for (String string : squList) {
				string = this.trimPrefixZero(string);
				list.add(Integer.parseInt(string));
			}
			// System.out.println(JSONArray.fromObject(list));

			Collections.sort(list);
			// System.out.println(JSONArray.fromObject(list));
			squenceNum = (list.get(list.size() - 1) + 1) + "";
			int len = squenceNum.length();
			if (len == 1) {
				squenceNum = "00" + squenceNum;
			} else if (len == 2) {
				squenceNum = "0" + squenceNum;
			}
			return squenceNum;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * @Title: trimPrefixZero
	 * @Description: 去掉前缀"0"
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-25 下午3:26:14
	 */
	private String trimPrefixZero(String string) {
		if (string.indexOf("0") == 0) {
			string = string.substring(1);
			string = trimPrefixZero(string);
		}
		return string;
	}

	/**
	 * @Title: listWorkOrderForTabs
	 * @Description: 展示工单信息用于业务资源
	 * @param
	 * @return String
	 * @throws
	 * @author yangl
	 * @version 1.0
	 * @createtime 2013-9-19
	 */
	public String listWorkOrderForTabs() {
		if (workOrderObj == null) {
			workOrderObj = new WorkOrderObj();
		}
		// if (workOrderObj.getTYPE() != null && -1 == workOrderObj.getTYPE()) {
		// workOrderObj.setTYPE(null);
		// }
		// if (workOrderObj.getPROJECT_ID() != null
		// && "-1".equals(workOrderObj.getPROJECT_ID())) {
		// workOrderObj.setPROJECT_ID(null);
		// }
		// if (workOrderObj.getCAMEFROM() != null
		// && "-1".equals(workOrderObj.getCAMEFROM())) {
		// workOrderObj.setCAMEFROM(null);
		// }
		// if (workOrderObj.getWSTAT() != null && -1 == workOrderObj.getWSTAT())
		// {
		// workOrderObj.setWSTAT(null);
		// }
		// workOrderObj.setPagination(this.getPaginater().initPagination(request));
		//
		// String account = session.get("account").toString();
		// if (!"admin".equals(account)) {
		// workOrderObj.setPROJECT_USER_ID(account);
		// }
		resultList = workOrderService.queryWorkOrderList(workOrderObj);
		projectList = workOrderService.queryProjectList();
		return "sxlistWorkOrderForTabs";
	}

	/**
	 * @Title: orderTaskToOrderForCreate
	 * @Description: orderTask转化成OrderForCreate
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-9-19
	 */
	public OrderForCreate orderTaskToOrderForCreate(List<OrderTaskObj> taskList) {
		OrderForCreate orderForCreate = new OrderForCreate();
		orderForCreate.setOrderid(uuid);
		List<TaskForCreate> taskForCreateList = new ArrayList<TaskForCreate>();
		for (OrderTaskObj orderTaskObj1 : taskList) {
			TaskForCreate taskForCreate = new TaskForCreate();
			taskForCreate.setVmNum(Integer.valueOf(orderTaskObj1.getVmNum()));
			taskForCreate.setType(orderTaskObj1.getType());// 任务类型。0：vmw 1：sce
			taskForCreate.setTaskid(orderTaskObj1.getUuid());
			if (VmTypeConstant.X86_VM.equals(orderTaskObj1.getType())) {
				// vmw
				VmHost vo = new VmHost();
				vo.setCbmsServiceTemplate(orderTaskObj1.getTemplateId());
				vo.setClusterId(orderTaskObj1.getClusterId());
				vo.setClusterName(orderTaskObj1.getClusterName());
				// vo.setCorebizdomainName("测试域");
				vo.setCpuCount(Integer.valueOf(orderTaskObj1.getCpuCount()));
				vo.setDisplayName(orderTaskObj1.getName());
				// vo.setEndTime("");
				vo.setExpandStorageValue(orderTaskObj1.getExpandStorage());
				vo.setIpPoolOneName(orderTaskObj1.getNet1name());
				vo.setNicCount(1);
				if (!"-1".equals(orderTaskObj1.getNet2())) {
					vo.setIpPoolTwoName(orderTaskObj1.getNet2name());
					vo.setNicCount(2);
				}
				vo.setName(orderTaskObj1.getName());
				vo.setOrderId(orderTaskObj1.getWorkOrderId());
				// vo.setPerformanceConfig("低端配置");
				vo.setVmNum(1); // 每次都创建一台
				vo.setStorageValue(orderTaskObj1.getStorage());
				// vo.setResourceDefinitionName("低端配置");
				vo.setRemValue(orderTaskObj1.getMemory());
				taskForCreate.setVmwVm(vo);
				vo.setRemark(orderTaskObj1.getRemark());

			} else if (VmTypeConstant.IBM_VM.equals(orderTaskObj1.getType())) {
				// sce
				AddWorkLoadsObj vo = new AddWorkLoadsObj();
				vo.setAppliancesid(orderTaskObj1.getTemplateId());
				vo.setWorkLoadName(orderTaskObj1.getName());
				vo.setDescription("sitech云管理平台创建");
				vo.setInstances(1);
				vo.setProject("1");
				vo.setState("EXECUTING");
				vo.setMemory(Integer.valueOf(orderTaskObj1.getMemory()));
				vo.setCpushared(Integer.valueOf(orderTaskObj1.getCpushared()));
				vo.setCpushu(Double.valueOf(orderTaskObj1.getCpuCount()));
				vo.setDisk1(Integer.valueOf(orderTaskObj1.getStorage()) * 1024);
				vo.setHostname(orderTaskObj1.getHostname());
				vo.setIpv4defaultgateway("192.168.88.1");
				vo.setNet1(Integer.valueOf(orderTaskObj1.getNet1()));
				vo.setNet2(Integer.valueOf(orderTaskObj1.getNet2()));
				taskForCreate.setSceVm(vo);
			}
			taskForCreateList.add(taskForCreate);
		}

		orderForCreate.setTaskList(taskForCreateList);
		return orderForCreate;
	}
}
