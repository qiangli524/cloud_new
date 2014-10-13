package com.sitech.basd.sxcloud.cloud.web.workorder_sc;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sitech.basd.cloud3.domain.departproject.DepartProjectObj;
import com.sitech.basd.cloud3.service.departproject.DepartProjectService;
import com.sitech.basd.cloud3.service.departproject.RelationService;
import com.sitech.basd.resource.domain.template.TemManObj;
import com.sitech.basd.resource.domain.united.UnitedTree;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.template.TemManService;
import com.sitech.basd.resource.service.united.UnitedTreeService;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.domain.workorder_sc.WorkOrderObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.cloud.service.net.IpService;
import com.sitech.basd.sxcloud.cloud.service.net.NetService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.cloud.service.workorder_sc.WorkOrderService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.yicloud.domain.busisystem.BusiSystemObj;
import com.sitech.basd.yicloud.service.busisystem.BusiSystemService;
import com.sitech.basd.yicloud.web.busisystree.form.BusiSysTree;
import com.sitech.ssd.sc.sendSms.domain.SendSmsObj;
import com.sitech.ssd.sc.sendSms.service.SendSmsService;
import com.sitech.utils.date.TimeformatCommon;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.exception.RabbitMQException;
import com.sitech.utils.rabbitmq.RabbitMQUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.utils.vmname.RandomNameForVM;
import com.sitech.vo.util.UnitedConstant;

@SuppressWarnings("all")
@Controller("workOrderActionSc")
@Scope("prototype")
public class WorkOrderAction extends BaseAction {

	@Autowired
	private WorkOrderService workOrderServiceSc;

	@Autowired
	private DepartProjectService departProjectService;

	@Autowired
	private TemManService temManService;

	@Autowired
	private RabbitMQUtil rabbitMQUtil;

	@Autowired
	private NetService netService;

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
	private SendSmsService sendSmsService;

	@Autowired
	private TbGlobalConfigService tbGlobalConfigService;

	@Autowired
	private HostInfoService hostInfoService;

	private WorkOrderObj workOrderObj;

	private List<WorkOrderObj> resultList;

	private List<DepartProjectObj> projectList;

	private List<TemManObj> temList;

	private List<TbCloud2IpInfoObj> ipList;

	private List<VMHostObj> vmhostList;

	private List<TbCloud2HostInfoObj> physicHostList;

	private List<UnitedTreeObj> unitedTreeList;

	private List<TbCloud2HostInfoObj> hostList;

	private String net_id;

	private String oper;// 操作

	private String operFrom;//

	private Integer type;// 工单类型

	private String resource_type;// 工单类型

	private String uuid;

	private String wrid;

	private String projectid;

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

	private TbCloud2HostInfoObj hostInfoObj;

	private DepartProjectObj projectObj;

	private VMHostObj vmHostObj;

	private String entityname;// 回收时展示的资源名称

	private BusiSystemObj busiSystemObj;

	private List<BusiSystemObj> busiList;

	private String busisystemid;

	private String ipaddress;

	private String vtype;// 虚拟化类型

	private String busi_pool_id;// 业务池ID

	private String busi_pool_name;// 业务池NAME

	public String getBusi_pool_id() {
		return busi_pool_id;
	}

	public void setBusi_pool_id(String busi_pool_id) {
		this.busi_pool_id = busi_pool_id;
	}

	public String getBusi_pool_name() {
		return busi_pool_name;
	}

	public void setBusi_pool_name(String busi_pool_name) {
		this.busi_pool_name = busi_pool_name;
	}

	public TbCloud2HostInfoObj getHostInfoObj() {
		return hostInfoObj;
	}

	public void setHostInfoObj(TbCloud2HostInfoObj hostInfoObj) {
		this.hostInfoObj = hostInfoObj;
	}

	public List<TbCloud2HostInfoObj> getPhysicHostList() {
		return physicHostList;
	}

	public void setPhysicHostList(List<TbCloud2HostInfoObj> physicHostList) {
		this.physicHostList = physicHostList;
	}

	public String getResource_type() {
		return resource_type;
	}

	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}

	public String getVtype() {
		return vtype;
	}

	public void setVtype(String vtype) {
		this.vtype = vtype;
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

	public String getOperFrom() {
		return operFrom;
	}

	public void setOperFrom(String operFrom) {
		this.operFrom = operFrom;
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

	public List<UnitedTreeObj> getUnitedTreeList() {
		return unitedTreeList;
	}

	public void setUnitedTreeList(List<UnitedTreeObj> unitedTreeList) {
		this.unitedTreeList = unitedTreeList;
	}

	public List<TbCloud2HostInfoObj> getHostList() {
		return hostList;
	}

	public void setHostList(List<TbCloud2HostInfoObj> hostList) {
		this.hostList = hostList;
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
		if (workOrderObj.getTYPE() != null && -1 == workOrderObj.getTYPE()) {
			workOrderObj.setTYPE(null);
		}
		if (workOrderObj.getPROJECT_ID() != null
				&& "-1".equals(workOrderObj.getPROJECT_ID())) {
			workOrderObj.setPROJECT_ID(null);
		}
		if (workOrderObj.getCAMEFROM() != null
				&& "-1".equals(workOrderObj.getCAMEFROM())) {
			workOrderObj.setCAMEFROM(null);
		}
		if (workOrderObj.getWSTAT() != null && -1 == workOrderObj.getWSTAT()) {
			workOrderObj.setWSTAT(null);
		}
		if (workOrderObj.getAUDIT_TYPE() != null
				&& "-1".equals(workOrderObj.getAUDIT_TYPE())) {
			workOrderObj.setAUDIT_TYPE(null);
		}
		workOrderObj.setPagination(this.getPaginater().initPagination(request));

		String account = session.get("account").toString();
		String userId = session.get("id").toString();
		// 全局配置里边配置了几个用户的权限
		TbGlobalConfigObj global = new TbGlobalConfigObj();
		global.setKEY("user_auth");
		global = tbGlobalConfigService.queryByObj(global);
		String[] users = new String[] { "" };
		if (global != null) {
			users = global.getVALUE().split(",");
		}

		if (!"1".equals(userId)) {// 对于admin用户，不需要分配权限
			// 对于普通登录用户，需要进行权限控制
			int flag = 0;
			for (int i = 0; i < users.length; i++) {
				if (account.equals(users[i])) {
					flag = 1;
				}
			}
			if (flag != 1) {
				workOrderObj.setPROJECT_USER_ID(userId);
			}

		}

		resultList = workOrderServiceSc.queryByObj(workOrderObj);
		projectList = workOrderServiceSc.queryProjectList();
		return "listWorkOrder";
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
		workOrderObj.setWSTAT(1);
		if (workOrderObj.getTYPE() != null && -1 == workOrderObj.getTYPE()) {
			workOrderObj.setTYPE(null);
		}
		if (workOrderObj.getPROJECT_ID() != null
				&& "-1".equals(workOrderObj.getPROJECT_ID())) {
			workOrderObj.setPROJECT_ID(null);
		}
		workOrderObj.setPagination(this.getPaginater().initPagination(request));

		String account = session.get("account").toString();
		if (!"admin".equals(account)) {
			workOrderObj.setPROJECT_USER_ID(account);
		}

		resultList = workOrderServiceSc.queryByObj(workOrderObj);
		projectList = workOrderServiceSc.queryProjectList();

		return "viewDealedWorkOrder";
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
		return "addw";
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
		workOrderObj.setUUID(uuid);
		int ret = -1;
		ret = workOrderServiceSc.deleteWorkOrderByObj(workOrderObj);
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
	 * @Title: sendAuditWorkOrder
	 * @Description: 发送工单审批
	 * @param
	 * @return void
	 * @throws
	 * @author chenjlc
	 * @version 1.0
	 * @createtime 2014-9-12 下午8:04:06
	 */
	public void sendAuditWorkOrder() {

		if (workOrderObj == null) {
			workOrderObj = new WorkOrderObj();
		}
		try {
			workOrderObj.setUUID(uuid);
			workOrderObj.setAUDIT_TYPE("1");
			int ret = -1;
			ret = workOrderServiceSc.updateWorkOrderTable(workOrderObj);

			// 发送工单短信，插入短信表
			workOrderObj = workOrderServiceSc.queruForAuditInfo(workOrderObj);
			String smsContent = "云管理平台工单到达提醒，工单名为：\""
					+ workOrderObj.getWORK_ORDER_TITLE()
					+ "\"的资源申请工单发送到你处审批，请处理,谢谢!";
			SendSmsObj smsObj = sendSmsService.selectAuditPhone();
			String tels = smsObj.getUSER_PHONE();
			if (tels != null && !"".equals(tels)) {
				String account = session.get("account").toString();
				smsObj.setLOGIN_ID(account);
				smsObj.setUSER_ID(account);
				smsObj.setSEND_CONTENT(smsContent);
				smsObj.setBUSI_TYPE("9");
				smsObj.setDO_FLAG("0");
				String[] tt = tels.split(",");
				for (int i = 0; i < tt.length; i++) {
					smsObj.setUSER_PHONE(tt[i]);
					sendSmsService.insertSendSms(smsObj);
				}
			}
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
		return "listpro";
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
			JSONObject jo = new JSONObject();
			String work_order_id = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date()); //
			// if (0 == type) {
			obj.setUUID(RandomUUID.getUuid());
			obj.setTYPE(type);
			obj.setUSERNAME(workOrderObj.getUSERNAME());
			obj.setRECEIVETIME(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(new Date()));
			obj.setPROJECT_ID(workOrderObj.getPROJECT_ID());
			obj.setPROJECT_USER_ID(workOrderObj.getPROJECT_USER_ID());
			obj.setBOMC_UUID(work_order_id);
			obj.setSTATE(0);
			obj.setCAMEFROM("1");
			obj.setWSTAT(0);
			obj.setBUSISYSTEMID(workOrderObj.getBUSISYSTEMID());
			obj.setRESOURCE_TYPE(resource_type);
			obj.setWORK_ORDER_TITLE(workOrderObj.getWORK_ORDER_TITLE());
			obj.setWORK_ORDER_COMM_MSG(workOrderObj.getWORK_ORDER_COMM_MSG());
			ret = workOrderServiceSc.insertWorkOrderTable(obj);
			// } else if (2 == type) {
			// obj.setUUID(RandomUUID.getUuid());
			// obj.setTYPE(type);
			// obj.setUSERNAME(workOrderObj.getUSERNAME());
			// obj.setRECEIVETIME(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			// .format(new Date()));
			// obj.setPROJECT_ID(workOrderObj.getPROJECT_ID());
			// obj.setPROJECT_USER_ID(workOrderObj.getPROJECT_USER_ID());
			// obj.setBOMC_UUID(work_order_id);
			// obj.setSTATE(0);
			// obj.setCAMEFROM("1");
			// obj.setWSTAT(0);
			// obj.setBUSISYSTEMID(workOrderObj.getBUSISYSTEMID());
			// ret = workOrderServiceSc.insertWorkOrderTable(obj);
			// }
		}
		try {
			PrintWriter pw = response.getWriter();
			pw.print(ret);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: saveWorkOrder
	 * @Description: 消息队列方式
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-22 下午8:16:19
	 */
	public void saveWorkOrder1() {
		int ret = -1;
		if ("add".equals(oper)) {
			JSONObject jo = new JSONObject();
			String work_order_id = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			if (0 == type) {
				jo.put("type", "work_order");
				jo.put("work_order_id", work_order_id);
				// jo.put("project_user_id", workOrderObj.getPROJECT_USER_ID());
				// jo.put("project_id", workOrderObj.getPROJECT_ID());
				jo.put("camefrom", "1");
				// jo.put("busiid", workOrderObj.getBUSISYSTEMID());
				jo.put("resource", "");
				jo.put("work_order_title", workOrderObj.getWORK_ORDER_TITLE());
				jo.put("resource_type", resource_type);
				if (workOrderObj.getWORK_ORDER_COMM_MSG() != null) {
					jo.put("work_order_comm_msg",
							workOrderObj.getWORK_ORDER_COMM_MSG());
				}
			} else if (2 == type) {
				jo.put("type", "work_order_recover");
				jo.put("work_order_id", work_order_id);
				// jo.put("project_user_id", workOrderObj.getPROJECT_USER_ID());
				// jo.put("project_id", workOrderObj.getPROJECT_ID());
				jo.put("camefrom", "1");
				jo.put("vm", "");
				jo.put("work_order_title", workOrderObj.getWORK_ORDER_TITLE());
				jo.put("resource_type", resource_type);
				if (workOrderObj.getWORK_ORDER_COMM_MSG() != null) {
					jo.put("work_order_comm_msg",
							workOrderObj.getWORK_ORDER_COMM_MSG());
				}
			}
			ret = this.sendOrderToQuene(jo);
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
	 * @Title: addResource
	 * @Description: 给工单添加任务
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-8 下午1:04:30
	 */
	@SuppressWarnings("unchecked")
	public String addResource() {

		if (2 == type) {
			if ("1".equals(resource_type)) {
				return "addRecoverPhysicResource";// 物理资源回收工单类型创建
			} else {
				return "addrr";// 工单类型回收
			}
		} else {
			UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
			unitedTreeObj.setType(UnitedConstant.SUBDOMIAN);
			unitedTreeObj.setVtype(UnitedConstant.NETWORKS);
			try {
				domainList = unitedTreeService.queryForTreeList(unitedTreeObj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if ("1".equals(resource_type)) {
				return "addPhysicResource";// 物理资源申请工单类型创建
			} else {
				return "addr";// 虚拟机资源工单类型创建
			}
		}
	}

	/**
	 * @Title: selectCluster
	 * @Description: 指定主机的时候，选择集群
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-19 上午11:20:53
	 */
	public String selectCluster() {
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		if (entityname != null && !"".equals(entityname)) {// 借用entityname属性，代表树上节点名称
			unitedTreeObj.setName(entityname);
		}
		if (!"0".equals(vtype)) {
			unitedTreeObj.setVtype(vtype);
		}
		unitedTreeObj.setType(UnitedConstant.CLUSTER);
		unitedTreeList = unitedTreeService
				.queryForListForWorkOrder(unitedTreeObj);
		return "listcluster";
	}

	public String selectHost() {
		TbCloud2HostInfoObj hostInfoObj = new TbCloud2HostInfoObj();
		hostInfoObj.setCluid(domainid);// 借用属性，此处表示集群id
		hostInfoObj.setConnectId(vlainid);// 属性借用，表示connectid
		hostInfoObj.setAllocated(1);
		hostList = hostInfoService.queryForListForCluster(hostInfoObj);
		return "listhost";
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
		List<TbCloud2NetInfoObj> netList = netService
				.queryForListByDomainId(domainid);
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
		busiSystemObj
				.setPagination(this.getPaginater().initPagination(request));
		busiList = busiSystemService.queryForListByObj(busiSystemObj);
		return "listBusi";
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
		return "listTem";
	}

	/**
	 * @Title: listResourcePool
	 * @Description: 选择划入资源池
	 * @param
	 * @return String
	 * @throws
	 * @author chenjlc
	 * @version 1.0
	 * @createtime 2014-7-8 下午1:16:36
	 */
	public String listResourcePool() {
		try {
			List<BusiSysTree> list = unitedTreeService
					.queryForUnitedTreeByWorker();
			JSONArray json = JSONArray.fromObject(list);
			ActionContext.getContext().put("nodes", json.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "listResourcePool";
	}

	/**
	 * @Title: selectResourceType
	 * @Description: 申请物理资源时，选择可用的物理资源类型
	 * @param
	 * @return String
	 * @throws
	 * @author chenjlc
	 * @version 1.0
	 * @createtime 2014-7-8 下午1:16:36
	 */
	public String listResourceType() {
		if (hostInfoObj == null) {
			hostInfoObj = new TbCloud2HostInfoObj();
		}
		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		if (hostInfoObj.getEq_type() != null
				&& !hostInfoObj.getEq_type().equals("")
				&& !"-1".equals(hostInfoObj.getEq_type())) {
			obj.setEq_type(hostInfoObj.getEq_type());
		}
		if (hostInfoObj.getCpu_cl() != null
				&& !hostInfoObj.getCpu_cl().equals("")) {
			obj.setCpu_cl(hostInfoObj.getCpu_cl());
		}
		if (hostInfoObj.getMem() != null && !hostInfoObj.getMem().equals("")) {
			obj.setMem(hostInfoObj.getMem());
		}
		if (hostInfoObj.getStore() != null
				&& !hostInfoObj.getStore().equals("")) {
			obj.setStore(String.valueOf(Integer.valueOf(hostInfoObj.getStore()) * 1024));
		}
		hostInfoObj.setPagination(this.getPaginater().initPagination(request));
		physicHostList = hostInfoService.queryAllHostType(obj);
		return "listResourceType";
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
		return "listip";
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
	/*
	 * @SuppressWarnings("unchecked") public void checkStore() { if (temManObj
	 * == null) { temManObj = new TemManObj(); }
	 * temManObj.setTemplateCode(splitLine(temid)[1]);
	 * temManObj.setConnectId(splitLine(temid)[0]); temList =
	 * temManService.queryForList(temManObj); if (temList.size() > 0) {
	 * temManObj = temList.get(0); } double temsrsize = temManObj.getStore();
	 * int ret = -1; if (temsrsize <= srsize * 1024) { ret = 1; } try {
	 * PrintWriter pw = response.getWriter(); pw.print(ret); pw.flush();
	 * pw.close(); } catch (Exception e) { e.printStackTrace(); } }
	 */

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
		departProjectObj = departProjectService
				.queryDepartProjectOne(departProjectObj);
		int cpuall = departProjectObj.getCPU_COUNT();
		int memall = departProjectObj.getMEMORY_SIZE();
		double storeall = departProjectObj.getSTORAGE_SIZE();

		WorkOrderObj wobj = new WorkOrderObj();
		wobj.setPROJECT_ID(projectid);
		if ("edit".equals(oper)) {// 修改去掉本身
			wobj.setID(wrid);
		}
		List<WorkOrderObj> wlist = workOrderServiceSc
				.queryUsedByWorkorder(wobj);
		int used_cpu = 0;
		int used_mem = 0;
		double used_sr = 0;
		for (WorkOrderObj workOrderResourceObj : wlist) {
			used_cpu += workOrderResourceObj.getCPU_NUM();
			used_mem += workOrderResourceObj.getMEM_SIZE();
			used_sr += workOrderResourceObj.getSR_SIZE();
		}

		wlist = workOrderServiceSc.queryByProject(projectid);
		for (WorkOrderObj workOrderResourceObj : wlist) {
			used_cpu += workOrderResourceObj.getCPU_NUM();
			used_mem += workOrderResourceObj.getMEM_SIZE();
			used_sr += workOrderResourceObj.getSR_SIZE();
		}

		int leftcpu = cpuall - used_cpu;
		int leftmem = memall - used_mem;
		double leftsr = storeall - used_sr;

		int ret = -1;
		if (leftcpu >= cpu && leftmem >= memsize * 1024
				&& leftsr >= srsize * 1024) {
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
		if ("1".equals(resource_type)) {
			if (workOrderObj == null) {
				workOrderObj = new WorkOrderObj();
			}
			TbCloud2HostInfoObj hostInfoObj = new TbCloud2HostInfoObj();
			// hostInfoObj.setEq_type("4");
			hostInfoObj.setAllocated(1);
			hostInfoObj.setEq_ip(workOrderObj.getEq_ip());
			hostInfoObj.setSn(workOrderObj.getSn());
			hostInfoObj.setPagination(this.getPaginater().initPagination(
					request));
			physicHostList = hostInfoService.queryAllHost(hostInfoObj);
			return "listPhysicEntity";

		} else {
			if (vmHostObj == null) {
				vmHostObj = new VMHostObj();
			}
			vmHostObj
					.setPagination(this.getPaginater().initPagination(request));
			String userId = session.get("id").toString();
			if (!"1".equals(userId)) {// 不是admin用户
				String account = session.get("account").toString();
				// 查询其他超级管理员
				TbGlobalConfigObj global = new TbGlobalConfigObj();
				global.setKEY("user_auth");
				global = tbGlobalConfigService.queryByObj(global);
				int flag = 0;
				if (global != null) {
					String[] userarr = global.getVALUE().split(",");
					for (String string : userarr) {
						if (account.equals(string)) {
							flag = 1;
						}
					}
				}
				if (flag != 1) {// 也不是其他超级管理员
					vmHostObj.setUSER_ID(account);
				}
			}
			vmhostList = vmHostService.queryForListByProLeader(vmHostObj);
			// vmhostList = vmHostService.queryForListByPro(vmHostObj);
			return "listEntity";
		}

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
		if (workOrderObj == null) {
			workOrderObj = new WorkOrderObj();
		}
		workOrderObj.setID(wrid);
		int ret = -1;
		resultList = workOrderServiceSc.queryResourceList(workOrderObj);
		if (resultList.size() > 0) {
			workOrderObj = resultList.get(0);
			ret = workOrderServiceSc.deleteResourceByObj(workOrderObj);
			String ip = workOrderObj.getIPADDRESS();
			if (ipInfoObj == null) {
				ipInfoObj = new TbCloud2IpInfoObj();
			}
			ipInfoObj.setIPADDRESS(ip);
			ipInfoObj.setISUSED("0");
			ipService.updateIPByObj(ipInfoObj);
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
		if (workOrderObj == null) {
			workOrderObj = new WorkOrderObj();
		}
		workOrderObj.setID(wrid);
		List<WorkOrderObj> list = workOrderServiceSc
				.queryResourceList(workOrderObj);
		if (list.size() > 0) {
			workOrderObj = list.get(0);
			workOrderObj.setMEM_SIZE(workOrderObj.getMEM_SIZE() / 1024);
			workOrderObj.setSR_SIZE(workOrderObj.getSR_SIZE() / 1024);
			if ("1".equals(workOrderObj.getTEMP_TYPE())) {
				workOrderObj.setTEMPLATE_TYPE("VMWARE模板");
			} else if ("2".equals(workOrderObj.getTEMP_TYPE())) {
				workOrderObj.setTEMPLATE_TYPE("XEN模板");
			} else if ("3".equals(workOrderObj.getTEMP_TYPE())) {
				workOrderObj.setTEMPLATE_TYPE("OVF模板");
			} else if ("4".equals(workOrderObj.getTEMP_TYPE())) {
				workOrderObj.setTEMPLATE_TYPE("物理机模板");
			}
		}
		if (2 == type) {
			String vm_id = workOrderObj.getENTITY_ID();
			String[] str = splitLine(vm_id);
			VMHostObj paramObj = new VMHostObj();
			paramObj.setVH_UUID(str[1]);
			paramObj.setConnectId(str[0]);
			vmHostObj = vmHostService.queryForObjByEntityID(paramObj);
			entityname = vmHostObj.getVH_NAME();
			return "editrr";
		} else {
			UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
			unitedTreeObj.setType(UnitedConstant.SUBDOMIAN);
			unitedTreeObj.setVtype(UnitedConstant.NETWORKS);
			try {
				domainList = unitedTreeService.queryForTreeList(unitedTreeObj);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (workOrderObj.getIPADDRESS() == null
					|| "".equals(workOrderObj.getIPADDRESS())) {
				flag = 0;
			} else {
				flag = 1;
			}
			vlainid = workOrderObj.getNETWORK_ID();
			UnitedTreeObj uObj = new UnitedTreeObj();
			uObj.setUuid(vlainid);
			try {
				List<UnitedTreeObj> unlist = unitedTreeService
						.queryForTreeList(uObj);
				if (unlist.size() > 0) {
					uObj = unlist.get(0);
					domainid = uObj.getParent_id();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 查询集群主机等信息
			WorkOrderObj wobj = new WorkOrderObj();
			wobj.setID(workOrderObj.getID());
			wobj = workOrderServiceSc.queruForHostInfo(wobj);
			workOrderObj.setCLUSTERID(wobj.getCLUSTERID());
			workOrderObj.setCLUSTERNAME(wobj.getCLUSTERNAME());
			workOrderObj.setHOST_NAME(wobj.getHOST_NAME());
			workOrderObj.setCONNECT_ID(wobj.getCONNECT_ID());
			// TbCloud2NetInfoObj netInfoObj = new TbCloud2NetInfoObj();
			// netList = netService.queryNetListByObj(netInfoObj);
			if ("1".equals(resource_type)) {
				return "editPhysicResource";// 物理资源申请工单类型创建
			} else {
				return "editr";// 虚拟机资源工单类型创建
			}
		}
	}

	/**
	 * @Title: saveResource
	 * @Description: 保存任务
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-8 下午2:42:26
	 */

	public void saveResource() {
		int ret = -1;
		if ("edit".equals(oper)) {
			if (0 == type) { // 锁ip
				if (workOrderObj.getIPADDRESS() != null
						&& !"".equals(workOrderObj.getIPADDRESS())) {
					if (ipInfoObj == null) {
						ipInfoObj = new TbCloud2IpInfoObj();
					}
					ipInfoObj.setIPADDRESS(workOrderObj.getIPADDRESS());
					ipInfoObj.setISUSED("1");
					ipService.updateIPByObj(ipInfoObj);
				}
				workOrderObj.setMEM_SIZE(workOrderObj.getMEM_SIZE() * 1024);
				workOrderObj.setSR_SIZE(workOrderObj.getSR_SIZE() * 1024);
				ret = workOrderServiceSc.updateByObj(workOrderObj);
			} else if (2 == type) {
				String vm_id = workOrderObj.getENTITY_ID();
				WorkOrderObj obj = new WorkOrderObj();
				obj.setENTITY_ID(vm_id);
				obj.setTYPE(0);
				obj.setSTATUS(2);
				List<WorkOrderObj> list = workOrderServiceSc
						.queryForWholeListByObj(obj);
				if (list.size() > 0) {
					obj = list.get(0);
					obj.setID(workOrderObj.getID());
					obj.setWORKORDER_ID(uuid);
					obj.setDEAL_COUNT(0);
					obj.setSTATUS(0);
					obj.setDEALTIME(null);
					ret = workOrderServiceSc.updateByObj(obj);
				}
			}
		} else if ("add".equals(oper)) {
			if (0 == type) {
				workOrderObj.setID(RandomUUID.getUuid());
				workOrderObj.setSTATUS(0);
				workOrderObj.setDEAL_COUNT(0);
				workOrderObj.setMEM_SIZE(workOrderObj.getMEM_SIZE() * 1024);
				workOrderObj.setSR_SIZE(workOrderObj.getSR_SIZE() * 1024);
				workOrderObj.setWORKORDER_ID(uuid);
				workOrderObj.setRESOURCE_TYPE(resource_type);
				workOrderObj.setTYPE(type);
				workOrderObj.setEQ_TYPE(workOrderObj.getEQ_TYPE_ID());
				ret = workOrderServiceSc.insertResource(workOrderObj);
				// 锁ip
				if (workOrderObj.getIPADDRESS() != null
						&& !"".equals(workOrderObj.getIPADDRESS())) {
					if (ipInfoObj == null) {
						ipInfoObj = new TbCloud2IpInfoObj();
					}
					ipInfoObj.setIPADDRESS(workOrderObj.getIPADDRESS());
					ipInfoObj.setISUSED("1");
					ipService.updateIPByObj(ipInfoObj);
				}
			} else {
				if ("2".equals(resource_type)) {
					String vm_id = workOrderObj.getENTITY_ID();
					VMHostObj vmHostObj = new VMHostObj(); // vmHostObj.setConnectId(splitLine(vm_id)[0]);
															// //
					vmHostObj.setVH_UUID(splitLine(vm_id)[1]); // vmHostObj =
					vmHostService.queryByObj(vmHostObj); // workOrderObj.setCAMEFROM("1");
															// //
					workOrderObj.setCPU_NUM(Integer.parseInt(vmHostObj
							.getVH_CPU())); //
					workOrderObj.setMEM_SIZE(Integer.parseInt(vmHostObj
							.getVH_MEM())); //
					workOrderObj.setSR_SIZE(Double.parseDouble(vmHostObj
							.getVH_STORAGE()));
					WorkOrderObj obj = new WorkOrderObj();
					obj.setENTITY_ID(vm_id);
					obj.setTYPE(0);
					obj.setSTATUS(2);
					List<WorkOrderObj> list = workOrderServiceSc
							.queryForWholeListByObj(obj);
					if (list.size() > 0) {
						obj = list.get(0);
						obj.setID(RandomUUID.getUuid());
						obj.setWORKORDER_ID(uuid);
						obj.setDEAL_COUNT(0);
						obj.setSTATUS(0);
						obj.setDEALTIME(null);
						ret = workOrderServiceSc.insertResource(obj);
					}
				} else {
					workOrderObj.setWORKORDER_ID(uuid);
					workOrderObj.setRESOURCE_TYPE(resource_type);
					workOrderObj.setTYPE(type);
					workOrderObj.setID(RandomUUID.getUuid());
					workOrderObj.setSTATUS(0);
					workOrderObj.setDEAL_COUNT(0);
					ret = workOrderServiceSc.insertResource(workOrderObj);
				}
			}

			// JSONObject jo = new JSONObject(); // if (0 == type) { // jo =
			// this.toJSONForCreate(workOrderObj, uuid); // } else if (2 ==
			// type) { //
			// jo = this.toJSONForRecover(workOrderObj, uuid); // } // if (jo !=
			// null) {
			// ret = this.sendOrderToQuene(jo); // } } try {
			// PrintWriter pw = response.getWriter();
			// pw.print(ret);
			// pw.flush();
			// pw.close();
			// catch
			// (Exception e) { e.printStackTrace(); }
		}
	}

	/**
	 * @Title: saveResource
	 * @Description: 消息队列方式
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-22 下午10:15:41
	 */
	public void saveResource1() {
		int ret = -1;
		if ("edit".equals(oper)) {// 直接改库
			if (0 == type) {
				WorkOrderObj woObj = new WorkOrderObj();
				woObj.setID(workOrderObj.getID());
				resultList = workOrderServiceSc.queryResourceList(woObj);
				WorkOrderObj oldWorkOrderObj = resultList.get(0);
				if (oldWorkOrderObj.getSTATUS() == 1
						|| oldWorkOrderObj.getSTATUS() == 2) {
					ret = -2;
				} else {
					// 锁ip
					if (workOrderObj.getIPADDRESS() != null
							&& !"".equals(workOrderObj.getIPADDRESS())) {
						if (ipInfoObj == null) {
							ipInfoObj = new TbCloud2IpInfoObj();
						}
						ipInfoObj.setIPADDRESS(workOrderObj.getIPADDRESS());
						ipInfoObj.setISUSED("1");
						ipService.updateIPByObj(ipInfoObj);
					}
					workOrderObj.setMEM_SIZE(workOrderObj.getMEM_SIZE() * 1024);
					workOrderObj.setSR_SIZE(workOrderObj.getSR_SIZE() * 1024);
					if (StringUtils.isNotEmpty(workOrderObj.getResAppSize())) {
						workOrderObj
								.setResAppSize(workOrderObj.getResAppSize());
					}
					// workOrderObj.setHOST_NAME("null");
					ret = workOrderServiceSc.updateByObj(workOrderObj);
				}

			} else if (2 == type) {
				String vm_id = workOrderObj.getENTITY_ID();
				// String[] str = splitLine(vm_id);
				// String connectid = str[0];
				// String vh_uuid = str[1];
				// if (vmHostObj == null) {
				// vmHostObj = new VMHostObj();
				// }
				// vmHostObj.setVH_UUID(vh_uuid);
				// vmHostObj.setConnectId(connectid);
				// vmHostObj = vmHostService.queryForWorkOrder(vmHostObj);
				//
				// workOrderObj.setCPU_NUM(vmHostObj.getVH_CPU() ==
				// null?0:Integer.parseInt(vmHostObj.getVH_CPU()));
				// workOrderObj.setMEM_SIZE(vmHostObj.getVH_MEM() ==
				// null?0:Integer.parseInt(vmHostObj.getVH_MEM()));
				// workOrderObj.setSR_SIZE(vmHostObj.getVH_STORAGE() ==
				// null?0:Double.parseDouble(vmHostObj.getVH_STORAGE()));
				// workOrderObj.setNETWORK_ID(vmHostObj.getVH_NETWORK());
				// workOrderObj.setTEMPLATE_ID(vmHostObj.getTemid());
				// workOrderObj.setWORKORDER_ID(uuid);
				// workOrderObj.setSTATUS(0);
				// workOrderObj.setDEAL_COUNT(0);
				// workOrderObj.setVM_NAME(vmHostObj.getVH_NAME());
				// workOrderObj.setIPADDRESS(vmHostObj.getVH_IP());
				WorkOrderObj obj = new WorkOrderObj();
				obj.setENTITY_ID(vm_id);
				obj.setTYPE(0);
				obj.setSTATUS(2);
				List<WorkOrderObj> list = workOrderServiceSc
						.queryForWholeListByObj(obj);
				if (list.size() > 0) {
					obj = list.get(0);
					obj.setID(workOrderObj.getID());
					obj.setWORKORDER_ID(uuid);
					obj.setDEAL_COUNT(0);
					obj.setSTATUS(0);
					obj.setDEALTIME(null);
					ret = workOrderServiceSc.updateByObj(obj);
				}
			}
		} else if ("add".equals(oper)) {
			JSONObject jo = new JSONObject();
			if (0 == type) {
				workOrderObj.setNETWORK_ID("");
				workOrderObj.setResBusiSytemId(workOrderObj.getBusi_pool_id());
				jo = this.toJSONForCreate(workOrderObj, uuid);
				jo.put("type", "work_order");
				jo.put("camefrom", "cloud_web");
				// 锁ip
				if (workOrderObj.getIPADDRESS() != null
						&& !"".equals(workOrderObj.getIPADDRESS())) {
					if (ipInfoObj == null) {
						ipInfoObj = new TbCloud2IpInfoObj();
					}
					ipInfoObj.setIPADDRESS(workOrderObj.getIPADDRESS());
					ipInfoObj.setISUSED("1");
					ipService.updateIPByObj(ipInfoObj);
				}
			} else if (2 == type) {
				jo = this.toJSONForRecover(workOrderObj, uuid);
				jo.put("type", "work_order_recover");
				jo.put("resource_type", resource_type);
			}
			if (jo != null) {
				ret = this.sendOrderToQuene(jo);
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
		joa.put("cpu_num", workOrderObj2.getCPU_NUM());
		joa.put("mem_size", workOrderObj2.getMEM_SIZE() * 1024);
		joa.put("sr_size", workOrderObj2.getSR_SIZE() * 1024);
		joa.put("network_id", workOrderObj2.getNETWORK_ID());
		joa.put("template_id", workOrderObj2.getTEMPLATE_ID());
		joa.put("vm_name", workOrderObj2.getVM_NAME());
		joa.put("ipaddress", workOrderObj2.getIPADDRESS());
		joa.put("template_type", workOrderObj2.getTEMPLATE_TYPE());
		//
		joa.put("busi_id", workOrderObj2.getResBusiSytemId());
		joa.put("app_dir", workOrderObj2.getResAppDir());
		joa.put("app_size", workOrderObj2.getResAppSize());
		joa.put("project_id", workOrderObj2.getPROJECT_ID());
		joa.put("project_user_id", workOrderObj2.getPROJECT_USER_ID());
		joa.put("host_id", workOrderObj2.getHOST_ID());
		joa.put("isreferhost", workOrderObj2.getISREFERHOST());
		joa.put("eq_type", workOrderObj2.getEQ_TYPE_ID());
		joa.put("resource_num", workOrderObj2.getRESOURCE_NUM());
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
		joa.put("vm_id", workOrderObj2.getENTITY_ID());

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
		if (workOrderObj == null) {
			workOrderObj = new WorkOrderObj();
		}
		workOrderObj.setWORKORDER_ID(uuid);
		resultList = workOrderServiceSc.queryResourceList(workOrderObj);
		if ("1".equals(resource_type)) {
			return "listPhysicResource";
		} else {
			return "listResource";
		}

	}

	/**
	 * 
	 * @Title: resourceDetail
	 * @Description: 资源详细查看
	 * @param
	 * @return String
	 * @throws
	 * @author liudan
	 * @version 1.0
	 * @createtime 2013-12-2 上午10:11:20
	 */
	public String resourceDetail() {
		if (workOrderObj == null) {
			workOrderObj = new WorkOrderObj();
		}
		workOrderObj.setID(wrid);
		resultList = workOrderServiceSc.queryResourceList(workOrderObj);
		return "resourceDetail";
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

		if (workOrderObj == null) {
			workOrderObj = new WorkOrderObj();
		}
		int ret = -1;
		workOrderObj.setID(wrid);
		if ("1".equals(resource_type)) {
			try {
				rabbitMQUtil.publishMessage("", "app.physicsHost.Divide.queue",
						workOrderObj);
			} catch (RabbitMQException e) {
				e.printStackTrace();
			}
		} else {
			resultList = workOrderServiceSc.queryResourceList(workOrderObj);
			workOrderObj = resultList.get(0);
			if (workOrderObj.getSTATUS() != 1) {
				workOrderObj.setSTATUS(0);
				ret = workOrderServiceSc.updateByObj(workOrderObj);
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

		if (workOrderObj == null) {
			workOrderObj = new WorkOrderObj();
		}
		workOrderObj.setUUID(uuid);
		if ("1".equals(resource_type)) {
			try {
				rabbitMQUtil.publishMessage("", "app.physicsHost.Divide.queue",
						workOrderObj);
			} catch (RabbitMQException e) {
				e.printStackTrace();
			}
		} else {
			resultList = workOrderServiceSc.queryByObj(workOrderObj);
			WorkOrderObj workResObj = new WorkOrderObj();
			workResObj.setWORKORDER_ID(uuid);
			List<WorkOrderObj> resList = workOrderServiceSc
					.queryResourceList(workResObj);

			boolean flag = false;
			for (WorkOrderObj workOrder : resList) {
				// 判断工单下的资源，是否存在虚拟机名称为空，存在，提示修改虚拟机名称
				if (StringUtils.isEmpty(workOrder.getVM_NAME())) {
					flag = true;
					break;
				}
			}
			int ret = -1;
			if (flag) {
				ret = -2;
			} else {
				workOrderObj = resultList.get(0);
				workOrderObj.setWSTAT(1);
				ret = workOrderServiceSc.updateWorkOrderTable(workOrderObj);
			}
			try {
				response.setContentType("text/text;charset=UTF-8");
				// PrintWriter pw = response.getWriter();
				// pw.print(ret);
				// pw.flush();
				// pw.close();
				PrintWriterOut.printWirter(response, ret);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
		if (workOrderObj.getTYPE() != null && -1 == workOrderObj.getTYPE()) {
			workOrderObj.setTYPE(null);
		}
		if (workOrderObj.getPROJECT_ID() != null
				&& "-1".equals(workOrderObj.getPROJECT_ID())) {
			workOrderObj.setPROJECT_ID(null);
		}
		if (workOrderObj.getCAMEFROM() != null
				&& "-1".equals(workOrderObj.getCAMEFROM())) {
			workOrderObj.setCAMEFROM(null);
		}
		if (workOrderObj.getWSTAT() != null && -1 == workOrderObj.getWSTAT()) {
			workOrderObj.setWSTAT(null);
		}
		workOrderObj.setPagination(this.getPaginater().initPagination(request));

		String account = session.get("account").toString();
		if (!"admin".equals(account)) {
			workOrderObj.setPROJECT_USER_ID(account);
		}
		resultList = workOrderServiceSc.queryByObj(workOrderObj);
		projectList = workOrderServiceSc.queryProjectList();
		return "listWOByResource";
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
		String virtualMachineName = this.getNewVmName(temManObj, busisystemid,
				ipaddress, domainid, uuid);
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
	private String getNewVmName(TemManObj temManObj, String busiid,
			String ipaddress, String domainid, String uuid) {
		try {
			String temRemark = temManObj.getRemark();

			// 获取安全域(网络域)名称规则
			UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
			unitedTreeObj.setId(domainid);
			List<UnitedTreeObj> ulist = unitedTreeService
					.queryForTreeList(unitedTreeObj);
			if (ulist.size() > 0) {
				unitedTreeObj = ulist.get(0);
			}
			String uname = unitedTreeObj.getName();

			// 获取业务系统名称
			BusiSystemObj busiSystemObj = new BusiSystemObj();
			busiSystemObj.setID(busiid);
			List<BusiSystemObj> busiList = busiSystemService
					.queryForListByObj(busiSystemObj);
			if (busiList.size() > 0) {
				busiSystemObj = busiList.get(0);
			}
			String busiName = busiSystemObj.getNAME();

			// 查询当前虚拟机该有的序号
			String subVmName = RandomNameForVM.randomSubThirdName(uname,
					temRemark, busiName);
			String squenceNum = this.getForthName(subVmName);

			String voName = "";
			if (ipaddress != null && !"".equals(ipaddress)
					&& !"null".equals(ipaddress)) {
				voName = RandomNameForVM.randomName(subVmName, squenceNum,
						ipaddress);
			} else {
				voName = subVmName + "_" + squenceNum;
			}

			return voName;
		} catch (Exception e) {
			e.printStackTrace();
			return "生成虚拟机名称出错";
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
			workOrderObj.setVM_NAME(subVmName);
			List<WorkOrderObj> workList = workOrderServiceSc
					.queryUnDealedResource(workOrderObj);

			if (list.size() == 0 && workList.size() == 0) {
				squenceNum = "001";
			} else {
				for (VMHostObj obj : list) {
					String vmname = obj.getVH_NAME();
					String[] vmnamearr = vmname.split("_");
					squList.add(vmnamearr[3]);
				}
				for (WorkOrderObj workOrderObj : workList) {
					String vmname = workOrderObj.getVM_NAME();
					if (StringUtils.isNotEmpty(vmname)) {
						String[] vmnamearr = vmname.split("_");
						if (vmnamearr.length > 3) {
							squList.add(vmnamearr[3]);
						}
					}
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
			throw new Exception("构造虚拟机名称获取序号时出错：" + e.getMessage()
					+ e.getClass().getName());
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
		if (workOrderObj.getTYPE() != null && -1 == workOrderObj.getTYPE()) {
			workOrderObj.setTYPE(null);
		}
		if (workOrderObj.getPROJECT_ID() != null
				&& "-1".equals(workOrderObj.getPROJECT_ID())) {
			workOrderObj.setPROJECT_ID(null);
		}
		if (workOrderObj.getCAMEFROM() != null
				&& "-1".equals(workOrderObj.getCAMEFROM())) {
			workOrderObj.setCAMEFROM(null);
		}
		if (workOrderObj.getWSTAT() != null && -1 == workOrderObj.getWSTAT()) {
			workOrderObj.setWSTAT(null);
		}
		workOrderObj.setPagination(this.getPaginater().initPagination(request));

		String account = session.get("account").toString();
		if (!"admin".equals(account)) {
			workOrderObj.setPROJECT_USER_ID(account);
		}
		resultList = workOrderServiceSc.queryByObj(workOrderObj);
		projectList = workOrderServiceSc.queryProjectList();
		return "listWorkOrderForTabs";
	}

	/**
	 * @Title: deployVM
	 * @Description: 虚拟机挂载完存储之后，将虚拟机交付给bomc
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-18 下午3:14:26
	 */
	public void deployVM() {
		if (workOrderObj == null) {
			workOrderObj = new WorkOrderObj();
		}
		workOrderObj.setID(wrid);
		workOrderObj = workOrderServiceSc.queryResourceList(workOrderObj)
				.get(0);
		workOrderObj.setSTATUS(2);
		int ret = workOrderServiceSc.updateByObj(workOrderObj);
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
}
